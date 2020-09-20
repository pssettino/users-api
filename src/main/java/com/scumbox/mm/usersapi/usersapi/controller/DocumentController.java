package com.scumbox.mm.usersapi.usersapi.controller;

import com.google.common.net.HttpHeaders;
import com.scumbox.mm.usersapi.usersapi.exception.DocumentException;
import com.scumbox.mm.usersapi.usersapi.exception.MaxSizeException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Document;
import com.scumbox.mm.usersapi.usersapi.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }


    @PostMapping("/upload")
    public Document uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            return documentService.store(file);
        } catch (MaxUploadSizeExceededException mue) {
            throw new MaxSizeException(2L);
        } catch (Exception ex) {
            throw new DocumentException();
        }
    }

    @GetMapping("/{documentNumber}")
    public List<Document> findByDocumentNumber(@PathVariable Integer documentNumber) {
        return documentService.findByDocumentNumber(documentNumber);
    }

    @GetMapping("/document/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
        Document document = documentService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(document.getData());
    }

    @DeleteMapping("/document/{id}")
    public void delete(@PathVariable Integer id) {
        documentService.delete(id);
    }

}
