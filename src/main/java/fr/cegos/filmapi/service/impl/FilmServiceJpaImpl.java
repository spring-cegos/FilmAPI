package fr.cegos.filmapi.service.impl;



import fr.cegos.filmapi.bo.Film;
import fr.cegos.filmapi.repository.FilmRepository;
import fr.cegos.filmapi.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation qui n'intéragit pas avec la base de donnée
 * => tout est stocké en mémoire Java
 */
@Profile("jpa")
@Service
@AllArgsConstructor
public class FilmServiceJpaImpl implements FilmService {
   private FilmRepository filmRepository;

    @Override
    public List<Film> listFilms() {
        return filmRepository.findAll();
    }

    @Override
    public List<Film> searchFilms(String search) {
        // todo : faire RECHERCHE et pas findAll()
        return filmRepository.findAll();
    }

    @Override
    public Film addFilm(Film film) {

        filmRepository.save(film);
        return film;
    }

    @Override
    public Film getFilm(Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    @Override
    public void saveFilm(Film film) {
       filmRepository.save(film);
    }

    @Override
    public void deleteFilm(Long id) {
        filmRepository.deleteById(id);
    }
}