package com.example.demo.service;

import com.example.demo.model.Adherent;
import com.example.demo.model.AdherentDTO;
import com.example.demo.repository.AdherentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdherentService {
    AdherentRepository adherentRepository;

    public AdherentService(AdherentRepository adherentRepository){
        this.adherentRepository = adherentRepository;
    }

    public List<AdherentDTO> findAll(){
        return adherentRepository
                .findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Long postAdherent(AdherentDTO adherent){
        Adherent adherentToEnty = mapToEntity(adherent);
        return adherentRepository.save(adherentToEnty).getId();
    }

    private AdherentDTO mapToDTO(Adherent adherent){
        return new AdherentDTO(adherent.getNom(), adherent.getDateExpiration());
    }

    private Adherent mapToEntity(AdherentDTO adherent){
        return new Adherent(null, adherent.getNom(), adherent.getDateExpiration());
    }

}
