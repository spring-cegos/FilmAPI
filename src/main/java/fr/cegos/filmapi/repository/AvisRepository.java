package fr.cegos.filmapi.repository;

import fr.cegos.filmapi.bo.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis, Long> {
}
