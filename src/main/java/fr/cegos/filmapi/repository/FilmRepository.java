package fr.cegos.filmapi.repository;

import fr.cegos.filmapi.bo.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("select f from Film f " +
            "left join f.avis a " +
            "where f.titre like %:search% " +
            "OR f.genre like %:search% " +
            "OR f.synopsis like %:search% " +
            "OR a.commentaire like %:search% " )
    List<Film> recherche(String search);
}
