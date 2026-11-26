package fr.cegos.filmapi.repository;

import fr.cegos.filmapi.bo.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findByNomAndFilmId(String nom, Long idFilm);
}
