package com.scumbox.mm.usersapi.usersapi.controller;

import com.google.common.net.HttpHeaders;
import com.scumbox.mm.usersapi.usersapi.exception.ImageProfileException;
import com.scumbox.mm.usersapi.usersapi.exception.MaxSizeException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.ImageProfile;
import com.scumbox.mm.usersapi.usersapi.service.ImageProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/imageProfile")
public class ImageProfileController {

    private ImageProfileService imageProfileService;

    @Autowired
    public ImageProfileController(ImageProfileService imageProfileService) {
        this.imageProfileService = imageProfileService;
    }


    @PostMapping("/{documentNumber}/upload")
    public ImageProfile uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Integer documentNumber) {
        try {
            return imageProfileService.store(file, documentNumber);
        } catch (MaxUploadSizeExceededException mue) {
            throw new MaxSizeException(2L);
        } catch (Exception ex) {
            throw new ImageProfileException();
        }
    }

    @GetMapping("/{documentNumber}")
    public ImageProfile findByDocumentNumber(@PathVariable Integer documentNumber) {
        return imageProfileService.findByDocumentNumber(documentNumber);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
        ImageProfile imageProfile = imageProfileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageProfile.getName() + "\"")
                .body(imageProfile.getData());
    }

    @DeleteMapping("/imageProfile/{id}")
    public void delete(@PathVariable Integer id) {
        imageProfileService.delete(id);
    }

}

