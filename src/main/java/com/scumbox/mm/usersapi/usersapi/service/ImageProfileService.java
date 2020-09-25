package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ImageProfile;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.ImageProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImageProfileService {

    private ImageProfileRepository imageProfileRepository;

    @Autowired
    public ImageProfileService(ImageProfileRepository imageProfileRepository) {
        this.imageProfileRepository = imageProfileRepository;
    }

    public List<ImageProfile> getAll() {
        return imageProfileRepository.findAll();
    }

    public ImageProfile store(MultipartFile file, Integer documentNumber) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ImageProfile imageProfile = imageProfileRepository.findByDocumentNumber(documentNumber)
                .orElse(new ImageProfile(documentNumber, fileName, file.getContentType(), file.getBytes()));

        imageProfile.setName(fileName);
        imageProfile.setType(file.getContentType());
        imageProfile.setData(file.getBytes());
        imageProfile.setLastUpdate(new Date());
        return imageProfileRepository.save(imageProfile);
    }

    public ImageProfile getFile(Integer id) {
        return imageProfileRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void delete(Integer id) {
        imageProfileRepository.deleteById(id);
    }

    public ImageProfile findByDocumentNumber(Integer documentNumber) {
        return imageProfileRepository.findByDocumentNumber(documentNumber).orElseThrow(NotFoundException::new);
    }
}

