package com.scumbox.mm.usersapi.usersapi.service;

import com.scumbox.mm.usersapi.usersapi.exception.NotFoundException;
import com.scumbox.mm.usersapi.usersapi.persistence.domain.Document;
import com.scumbox.mm.usersapi.usersapi.persistence.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class DocumentService {

    private DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    public Document store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Document document = new Document(fileName, file.getContentType(), file.getBytes());
        document.setLastUpdate(new Date());
        return documentRepository.save(document);
    }

    public Document getFile(Integer id) {
        return documentRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void delete(Integer id) {
        documentRepository.deleteById(id);
    }

    public List<Document> findByDocumentNumber(Integer documentNumber) {
        return documentRepository.findByDocumentNumber(documentNumber);
    }
}
