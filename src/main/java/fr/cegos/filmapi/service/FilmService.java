package fr.cegos.filmapi.service;

import fr.cegos.filmapi.bo.Film;

import java.util.List;

public interface FilmService {
    List<Film> listFilms();
    List<Film> searchFilms(String search);
    Film addFilm(Film todo);
    Film getFilm(Long id);
    void saveFilm(Film todo);
    void deleteFilm(Long id);
}