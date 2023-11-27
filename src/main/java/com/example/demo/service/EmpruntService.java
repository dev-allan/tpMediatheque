package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.AdherentRepository;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.EmpruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpruntService {
    @Autowired
    private AdherentRepository adherentRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private EmpruntRepository empruntRepository;

    public List<EmpruntOutputDTO> findAll() {
        return empruntRepository
                .findAll()
                .stream()
                .map(this::mapToDTOOutput)
                .collect(Collectors.toList());
    }

    public Long postEmprunt(Integer adherentId, Integer documentId) throws ResponseStatusException{
        Adherent adherent = adherentRepository.findById(adherentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Cet adhérent n'existe pas"));

        if (adherent.isAdhesionPerimee()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "L'adhésion de l'adhérent est périmée");
        }

        long nombreEmprunts = empruntRepository.countByAdherent(adherent);
        if (nombreEmprunts >= 3) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "L'adhérent a déjà plus de 3 livres");

        }

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Aucun document trouvé avec cet id"));

        Emprunt emprunt = new Emprunt();
        emprunt.setAdherent(adherent);
        emprunt.setDocument(document);

        return empruntRepository.save(emprunt).getId();
    }

    private EmpruntOutputDTO mapToDTOOutput(Emprunt emprunt){
        return new EmpruntOutputDTO(emprunt.getAdherent(), emprunt.getDocument());
    }
}
