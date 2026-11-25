package fr.cegos.filmapi.repository;

import fr.cegos.filmapi.bo.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
