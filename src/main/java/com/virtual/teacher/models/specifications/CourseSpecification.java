package com.virtual.teacher.models.specifications;

import com.virtual.teacher.models.Course;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CourseSpecification {
    public static Specification<Course> textInAllColumns(String text) {

        if (!text.contains("%")) {
            text = "%" + text + "%";
        }
        final String finalText = text;

        return (Specification<Course>) (root, cq, builder) -> {
            CriteriaQuery<Course> criteriaQuery = builder.createQuery(Course.class);
            List<Predicate> predicates = new ArrayList<>();
            Predicate prTopic = builder.like(builder.lower(root.get("topic").get("name")),
                    finalText);
            Predicate prFirstName = builder.like(builder.lower(root.get("author").get("firstName")),
                    finalText);
            Predicate prLastName = builder.like(builder.lower(root.get("author").get("lastName")),
                    finalText);
            Predicate prTitle = builder.like(builder.lower(root.get("title")),
                    finalText);
            Predicate prDescription = builder.like(builder.lower(root.get("description")),
                    finalText);
            predicates.add(builder.or(prLastName, prFirstName, prTitle, prTopic, prDescription));

            criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Course> filterByTopicAndAuthor(String topic, String author) {
        return (Specification<Course>) (root, query, builder) -> {
            CriteriaQuery<Course> criteriaQuery = builder.createQuery(Course.class);
            List<Predicate> predicates = new ArrayList<>();

            if (topic != null) {
                predicates.add(builder.like(builder.lower(root.get("topic").get("name")),
                        "%" + topic.toLowerCase() + "%"));
            }
            if (author != null) {
                Predicate prFirstName = builder.like(builder.lower(root.get("author").get("firstName")),
                        "%" + author.toLowerCase() + "%");
                Predicate prLastName = builder.like(builder.lower(root.get("author").get("lastName")),
                        "%" + author.toLowerCase() + "%");
                predicates.add(builder.or(prLastName, prFirstName));
            }
            criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Course> filterEnabled(){
      return  (Specification<Course>) (root, query, builder) -> {
            CriteriaQuery<Course> criteriaQuery = builder.createQuery(Course.class);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("enabled"), true));

            criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
