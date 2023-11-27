package com.example.demo.controller;


import com.example.demo.model.Emprunt;
import com.example.demo.model.EmpruntDTO;
import com.example.demo.model.EmpruntOutputDTO;
import com.example.demo.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    @GetMapping
    public ResponseEntity<List<EmpruntOutputDTO>> getAllEmprunts() {
        return ResponseEntity.ok(empruntService.findAll());
    }

    @PostMapping
    public ResponseEntity<Long> createEmprunt(@RequestBody EmpruntDTO empruntDTO) throws ResponseStatusException {
        return new ResponseEntity<>(empruntService.postEmprunt(empruntDTO.getAdherentId(), empruntDTO.getDocumentId()), HttpStatus.CREATED);
    }
}
