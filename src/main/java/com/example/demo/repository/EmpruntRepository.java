package com.example.demo.repository;

import com.example.demo.model.Adherent;
import com.example.demo.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Integer> {
    long countByAdherent(Adherent adherent);
}
