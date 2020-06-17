package com.virtual.teacher.services;

import com.virtual.teacher.models.LectureVideo;
import com.virtual.teacher.repositories.LectureVideoRepository;
import com.virtual.teacher.services.functions.interfaces.YoutubeCodeExtractor;
import com.virtual.teacher.services.interfaces.LectureVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TransactionRequiredException;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LectureVideoServiceImpl implements LectureVideoService {

    private final LectureVideoRepository lectureVideoRepository;
    private final YoutubeCodeExtractor youtubeCodeExtractor;

    @Override
    public LectureVideo getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be found on null");
        }
        LectureVideo lectureVideo;
        try {
            lectureVideo = lectureVideoRepository.getOne(id);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Video not found");
        }
        if (lectureVideo == null) {
            throw new IllegalArgumentException("Video not found");
        }
        return lectureVideo;
    }

    @Override
    public LectureVideo save(LectureVideo lectureVideo) {
        checkLectureVideo(lectureVideo);
        lectureVideo.setCode(youtubeCodeExtractor.extractCode(lectureVideo.getCode()));
        String exceptionMessage;
        try {
            return lectureVideoRepository.save(lectureVideo);
        } catch (IllegalArgumentException iae) {
            exceptionMessage = "Not video instance or removed one!";
        } catch (TransactionRequiredException tre) {
            exceptionMessage = "Repository error!";
        }
        throw new IllegalArgumentException(exceptionMessage);
    }

    private void checkLectureVideo(LectureVideo lectureVideo) {
        if (lectureVideo == null) {
            throw new IllegalArgumentException("Cannot save null video");
        }
        if (lectureVideo.getCode() == null || lectureVideo.getCode().isEmpty()) {
            throw new IllegalArgumentException("Cannot save empty code");
        }
    }

}
