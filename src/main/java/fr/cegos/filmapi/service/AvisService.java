package fr.cegos.filmapi.service;



import fr.cegos.filmapi.bo.Avis;

import java.util.List;

public interface AvisService {
    List<Avis> listAvis(Long idFilm);
    List<Avis> searchAvis(Long idFilm, String search);
    void saveAvis(Long idFilm, Avis avis);
    void deleteAvis(Long id);
}