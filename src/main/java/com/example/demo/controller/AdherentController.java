package com.example.demo.controller;


import com.example.demo.model.Adherent;
import com.example.demo.model.AdherentDTO;
import com.example.demo.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("adherents")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;

    @GetMapping
    public ResponseEntity<List<AdherentDTO>> getAllAdherents() {
        return ResponseEntity.ok(adherentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Long> createAdherent(@RequestBody AdherentDTO adherent) {
        return new ResponseEntity<>(adherentService.postAdherent(adherent), HttpStatus.CREATED);
    }
}
