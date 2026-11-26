package fr.cegos.filmapi.controller;


import fr.cegos.filmapi.bo.Film;
import fr.cegos.filmapi.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Documentation d'API ajoutée via les annotations @Tag, @Operation
 * Possibilité de le faire à partir de la JvaDoc : https://springdoc.org/#javadoc-support
 */
@Tag(name = "Films")
@RestController
@CrossOrigin
@RequestMapping("/films")
public class FilmRestController {
    @Autowired
    FilmService filmService;

    /**
     * Sur mon Get, j'ajoute l'option d'avoir un filtre de recherche : http://localhost:8080/film?search=eni
     * Si je veux ajouter un paramètre optionnel , ne pas oublier de mettre @RequestParam(required = false)     *
     */
    @GetMapping
    @Operation(summary = "lister ou chercher parmis les films")
    public List<Film> getFilms(@RequestParam(required = false) String search){
        if (search == null || search.isBlank()){
            return filmService.listFilms();
        }
        else{
            return filmService.searchFilms(search);
        }
    }

    /**
     * @RequestBody : permet d'affecter les attributs de l'argument Film avec les attributs JSON de la requête HTTP
     * Si on ouble cette annotation, on va avoir un objet Film vide
     * Si besoin de récupérer un fichier (envoyer avec un input type="file") : @RequestParam("file") MultipartFile file (cf : https://spring.io/guides/gs/uploading-files)
     */
    @RolesAllowed("admin") // Method-Level Security : Accès à la méthode uniquement pour les utilisateurs connectes avec le role "admin"
    @PostMapping
    @Operation(summary = "créer un film")
    public Film postFilm(@Valid @RequestBody Film film){
        return filmService.addFilm(film);
    }

    /**
     * @PathVariable : permet de récupérer en argument une variable de l'URL de la requête HTTP
     * Ne pas oublier d'ajouter dans l'url de mappin comment retrouver cette variable : ici "/{id}"
     */
    @RolesAllowed("admin") // Method-Level Security : Accès à la méthode uniquement pour les utilisateurs connectes avec le role "admin"
    @DeleteMapping("/{id}")
    @Operation(summary = "supprimer un film")
    public void deleteFilm(@PathVariable Long id){
        filmService.deleteFilm(id);
    }

    /**
     * On peut combiner PathVariable et RequestBody
     */
    @RolesAllowed("admin") // Method-Level Security : Accès à la méthode uniquement pour les utilisateurs connectes avec le role "admin"
    @PutMapping("/{id}")
    @Operation(summary = "modifier un film")
    public void postFilm(@PathVariable Long id, @RequestBody Film film){
        film.setId(id);
        filmService.saveFilm(film);
    }
}
