package com.example.demo.service;

import com.example.demo.model.Document;
import com.example.demo.model.DocumentDTO;
import com.example.demo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    public List<DocumentDTO> findAll(){
        return documentRepository
                .findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Long postDocument(DocumentDTO document){
        Document dtoToEntity = mapToEntity(document);
        return documentRepository.save(dtoToEntity).getId();
    }

    private DocumentDTO mapToDTO(final Document document){
        return new DocumentDTO(document.getNom(), document.getDescription());
    }

    private Document mapToEntity(final DocumentDTO document){
        return new Document(null, document.getNom(), document.getDescription());
    }
}

