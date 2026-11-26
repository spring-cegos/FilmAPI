package fr.cegos.filmapi.service.impl;



import fr.cegos.filmapi.bo.Avis;
import fr.cegos.filmapi.bo.Film;
import fr.cegos.filmapi.bo.Membre;
import fr.cegos.filmapi.exception.BusinessException;
import fr.cegos.filmapi.repository.AvisRepository;
import fr.cegos.filmapi.repository.FilmRepository;
import fr.cegos.filmapi.service.AvisService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public void saveAvis(Long idFilm, Avis avis, Membre membre) {
        // VALIDATION : l'utilisateur ne peut pas mettre 2 avis pou le même film
        List<Avis> avisDejaDonnePourLeFilm = avisRepository.findByNomAndFilmId(avis.getNom(),idFilm);
        if (!avisDejaDonnePourLeFilm.isEmpty()){
            throw new BusinessException("vous ne pouvez pas donner 2 avis pour le même film");
        }

        // on met à jou la date de l'avis
        avis.setDate(LocalDate.now());
        Film film = filmRepository.findById(idFilm).orElseThrow(() ->  new EntityNotFoundException("Film n'existe pas"));
        film.getAvis().add(avis);
        filmRepository.save(film);
        //pas besoin de créer l'avis car on a un : cascade = CascadeType.ALL sur l'attribut avis de Film
    }

    @Override
    public void deleteAvis(Long id, Membre membre) {
        // VALIDATION : un membre ne peux modifier ou supprimer qu'un avis qu'il/elle a laissé
        Avis avis = avisRepository.findById(id).orElse(null);

        if (avis == null){
            throw new BusinessException("vous ne pouvez supprimer un avis qui n'existe pas");
        }
        if (!avis.getNom().equals(membre.getUsername())){
            throw new BusinessException("vous ne pouvez supprimer un avis que vous n'avez pas créé");
        }

        avisRepository.deleteById(id);
    }

    @Override
    public Avis getAvisById(Long id) {
        return avisRepository.findById(id).orElse(null);
    }
}