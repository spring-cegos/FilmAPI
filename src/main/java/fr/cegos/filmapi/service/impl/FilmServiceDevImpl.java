package fr.cegos.filmapi.service.impl;



import fr.cegos.filmapi.bo.Film;
import fr.cegos.filmapi.service.FilmService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation qui n'intéragit pas avec la base de donnée
 * => tout est stocké en mémoire Java
 */
@Service
public class FilmServiceDevImpl implements FilmService {
    // Dans cette implémentation c'est dans cette liste qu'on gère nos tâches
    List<Film> listeFilm = new ArrayList<>();

    public FilmServiceDevImpl() {
        listeFilm.add(new Film(1L, "E.T.", "Science-Fiction", 115, LocalDate.of(1982,6,11), "Une soucoupe volante atterrit en pleine nuit près de Los Angeles. Quelques extraterrestres, envoyés sur Terre en mission d'exploration botanique, sortent de l'engin, mais un des leurs s'aventure au-delà de la clairière où se trouve la navette. Celui-ci se dirige alors vers la ville. C'est sa première découverte de la civilisation humaine. Bientôt traquée par des militaires et abandonnée par les siens, cette petite créature apeurée se nommant E.T. se réfugie dans une résidence de banlieue." ));
        listeFilm.add(new Film(2L, "Le seigneur des anneaux : la communautée de l'anneau", "Fantastique", 178, LocalDate.of(2001,12,19), "Dans ce chapitre de la trilogie, le jeune et timide Hobbit, Frodon Sacquet, hérite d'un anneau. Bien loin d'être une simple babiole, il s'agit de l'Anneau Unique, un instrument de pouvoir absolu qui permettrait à Sauron, le Seigneur des ténèbres, de régner sur la Terre du Milieu et de réduire en esclavage ses peuple" ));
    }

    @Override
    public List<Film> listFilms() {
        return listeFilm;
    }

    @Override
    public List<Film> searchFilms(String search) {
        List<Film> listeFiltree = new ArrayList<>();
        for (Film film : listeFilm)
        {
            if (film.getTitre().contains(search)) {
                listeFiltree.add(film);
            }
        }
        return listeFiltree;
    }

    @Override
    public Film addFilm(Film film) {

        if (listeFilm.isEmpty()){
            // je définit l'id de ma tâche à 1
            film.setId(1L);
        }
        else{
            // je définit l'id de ma tâche comme celui de ma dernière tâche + 1
            int lastIndex = listeFilm.size() -1;
            film.setId(listeFilm.get(lastIndex).getId() + 1);
        }

        listeFilm.add(film);
        return film;
    }

    @Override
    public Film getFilm(Long id) {
        for (int i = 0; i < listeFilm.size(); i++) {
            if (listeFilm.get(i).getId().equals(id)) {
                return listeFilm.get(i);
            }
        }return null;

    }

    @Override
    public void saveFilm(Film film) {
        for (int i = 0; i < listeFilm.size(); i++) {
            if (listeFilm.get(i).getId().equals(film.getId())) {
                listeFilm.set(i, film);
            }
        }
    }

    @Override
    public void deleteFilm(Long id) {
        for (int i = 0; i < listeFilm.size(); i++) {
            if (listeFilm.get(i).getId().equals(id)) {
                listeFilm.remove(i);
            }
        }
    }
}