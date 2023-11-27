package com.example.demo.controller;

import com.example.demo.model.Document;
import com.example.demo.model.DocumentDTO;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        return ResponseEntity.ok(documentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Long> createDocument(@RequestBody DocumentDTO document) {
        return new ResponseEntity<>(documentService.postDocument(document), HttpStatus.CREATED);
    }
}
