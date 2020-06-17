package com.virtual.teacher.services;

import com.virtual.teacher.models.Photo;
import com.virtual.teacher.repositories.PhotoRepository;
import com.virtual.teacher.services.interfaces.PhotoService;
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
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = System.getProperty("user.dir") + File.separator
            + "photos" + File.separator;

    @Override
    public Photo savePhotoImage(Photo photo, MultipartFile imageFile) {
        photo.setPath(UPLOADED_FOLDER);
        List<Photo> photos = photo.getUser().getPhotos();
        if (imageFile == null || imageFile.isEmpty()) {
            if (defaultPhoto(photo, photos)) return null;
        } else {
            fileTypeCheck(imageFile);
            try {
                writePhoto(photo, imageFile);
            } catch (IOException e) {
                throw new IllegalArgumentException("Image cannot be stored now, try again later!");
            }
        }
        return save(photo);
    }

    @Override
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    private void writePhoto(Photo photo, MultipartFile imageFile) throws IOException {
        byte[] bytes;
        String fileName;
        bytes = imageFile.getBytes();
        fileName = imageFile.getOriginalFilename();
        photo.setFileName(photo.getUser().getUsername() + fileName);
        Path path = Paths.get(UPLOADED_FOLDER + photo.getUser().getUsername() + fileName);
        Files.write(path, bytes);
    }

    private void fileTypeCheck(MultipartFile imageFile) {
        if (!imageFile.getOriginalFilename().matches("([^\\s]+(\\.(?i)(jpg|jpeg|png))$)")) {
            throw new IllegalArgumentException("File type is not valid");
        }
    }

    private boolean defaultPhoto(Photo photo, List<Photo> photos) {
        if (photos == null || photos.isEmpty()) {
            photo.setFileName("signup-image.jpg");
        } else {
            return true;
        }
        return false;
    }
}
