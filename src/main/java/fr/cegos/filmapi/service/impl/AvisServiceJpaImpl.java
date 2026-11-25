package fr.cegos.filmapi.service.impl;



import fr.cegos.filmapi.bo.Avis;
import fr.cegos.filmapi.bo.Film;
import fr.cegos.filmapi.repository.AvisRepository;
import fr.cegos.filmapi.repository.FilmRepository;
import fr.cegos.filmapi.service.AvisService;
import fr.cegos.filmapi.service.FilmService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Profile("jpa")
@Service
@AllArgsConstructor
public class AvisServiceJpaImpl implements AvisService {

    private AvisRepository avisRepository;
    private FilmRepository filmRepository;

    @Override
    public List<Avis> listAvis(Long idFilm) {
        return avisRepository.findAll();
    }

    @Override
    public List<Avis> searchAvis(Long idFilm, String search) {
        // todo : faire RECHERCHE et pas findAll()
        return avisRepository.findAll();
    }

    @Override
    public void saveAvis(Long idFilm, Avis avis) {
        // on met à jou la date de l'avis
        avis.setDate(LocalDate.now());
        Film film = filmRepository.findById(idFilm).orElseThrow(() ->  new EntityNotFoundException("Film n'existe pas"));
        film.getAvis().add(avis);
        filmRepository.save(film);
        //pas besoin de créer l'avis car on a un : cascade = CascadeType.ALL sur l'attribut avis de Film
    }

    @Override
    public void deleteAvis(Long id) {
        avisRepository.deleteById(id);
    }
}