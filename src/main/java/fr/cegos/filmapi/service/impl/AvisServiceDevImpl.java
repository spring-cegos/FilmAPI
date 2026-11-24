package fr.cegos.filmapi.service.impl;



import fr.cegos.filmapi.bo.Avis;
import fr.cegos.filmapi.bo.Film;
import fr.cegos.filmapi.service.AvisService;
import fr.cegos.filmapi.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class AvisServiceDevImpl implements AvisService {

    private FilmService filmService;

    // Dans cette implémentation c'est dans cette liste qu'on gère nos tâches
    List<Avis> listeAvis = new ArrayList<>();

    @Override
    public List<Avis> listAvis(Long idFilm) {
        return searchAvis(idFilm, null);
    }

    @Override
    public List<Avis> searchAvis(Long idFilm, String search) {
        // 1 - je récupère le film
        Film film = filmService.getFilm(idFilm);

        // 2 - je liste les avis
        return film.getAvis();
    }

    @Override
    public void saveAvis(Long idFilm, Avis avis) {
        // je met la date du jour à l'avis
        avis.setDate(LocalDate.now());

        // 1 - je récupère le film dans un prmeier temps
        Film film = filmService.getFilm(idFilm);

        // 2 - j'ajoute l'avis au film
        film.getAvis().add(avis);

        for (int i = 0; i < listeAvis.size(); i++) {
            if (listeAvis.get(i).getId().equals(avis.getId())) {
                listeAvis.set(i, avis);
            }
        }
    }

    @Override
    public void deleteAvis(Long id) {
        for (int i = 0; i < listeAvis.size(); i++) {
            if (listeAvis.get(i).getId().equals(id)) {
                listeAvis.remove(i);
            }
        }
    }
}