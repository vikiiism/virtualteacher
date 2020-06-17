package com.virtual.teacher.services;

import com.virtual.teacher.models.CoursePhoto;
import com.virtual.teacher.repositories.CoursePhotoRepository;
import com.virtual.teacher.services.interfaces.CoursePhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoursePhotoImpl implements CoursePhotoService {

    private final CoursePhotoRepository coursePhotoRepository;

    private static String UPLOADED_FOLDER = System.getProperty("user.dir") +
            File.separator + "coursePhotos" + File.separator;

    @Override
    public CoursePhoto savePhotoImage(CoursePhoto coursePhoto, MultipartFile imageFile) {
        coursePhoto.setPath(UPLOADED_FOLDER);
        List<CoursePhoto> coursePhotos = coursePhoto.getCourse().getCoursePhotos();
        if (imageFile == null || imageFile.isEmpty()) {
            setDefaultPhoto(coursePhoto, coursePhotos);
        } else {
            setUploadedPhoto(coursePhoto, imageFile);
        }
        return save(coursePhoto);
    }


    @Override
    public CoursePhoto save(CoursePhoto coursePhoto) {
        return coursePhotoRepository.save(coursePhoto);
    }

    private void writeFile(CoursePhoto coursePhoto, MultipartFile imageFile) throws IOException {
        byte[] bytes = imageFile.getBytes();
        String fileName = imageFile.getOriginalFilename();
        coursePhoto.setFileName(coursePhoto.getCourse().getTitle() + fileName);
        Path path = Paths.get(UPLOADED_FOLDER + coursePhoto.getCourse().getTitle() + fileName);
        Files.write(path, bytes);
    }

    private void fileCheck(MultipartFile imageFile) {
        if (!imageFile.getOriginalFilename().matches("([^\\s]+(\\.(?i)(jpg|jpeg|png))$)")) {
            throw new IllegalArgumentException("File type is not valid");
        }
    }


    private void setDefaultPhoto(CoursePhoto coursePhoto, List<CoursePhoto> coursePhotos) {
        if (coursePhotos == null || coursePhotos.isEmpty()) {
            coursePhoto.setFileName("course_12.jpg");
        } else {
            coursePhoto.setFileName(coursePhotos.get(coursePhotos.size() - 1).getFileName());
        }
    }

    private void setUploadedPhoto(CoursePhoto coursePhoto, MultipartFile imageFile) {
        fileCheck(imageFile);
        try {
            writeFile(coursePhoto, imageFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("Image cannot be stored no, try again later!");
        }
    }
}
