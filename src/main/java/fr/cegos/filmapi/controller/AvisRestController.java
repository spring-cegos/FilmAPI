package fr.cegos.filmapi.controller;


import fr.cegos.filmapi.bo.Avis;
import fr.cegos.filmapi.security.Utilisateur;
import fr.cegos.filmapi.service.AvisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Documentation d'API ajoutée via les annotations @Tag, @Operation
 * Possibilité de le faire à partir de la JvaDoc : https://springdoc.org/#javadoc-support
 */
@Tag(name = "Avis")
@RestController
@CrossOrigin
@RequestMapping("/films/{filmId}/avis")
public class AvisRestController {
    @Autowired
    AvisService avisService;

    /**
     * Sur mon Get, j'ajoute l'option d'avoir un filtre de recherche : http://localhost:8080/film?search=eni
     * Si je veux ajouter un paramètre optionnel , ne pas oublier de mettre @RequestParam(required = false)     *
     */
    @GetMapping
    @Operation(summary = "voir les avis d'un film")
    public List<Avis> getAvis(@PathVariable Long filmId, @RequestParam(required = false) String search){
        if (search == null || search.isBlank()){
            return avisService.listAvis(filmId);
        }
        else{
            return avisService.searchAvis(filmId, search);
        }
    }

    @GetMapping("/{avisId}")
    @Operation(summary = "voir un avis spécifique")
    public Avis getAvis(@PathVariable Long avisId){
        return avisService.getAvisById(avisId);
    }

    /**
     * @RequestBody : permet d'affecter les attributs de l'argument Film avec les attributs JSON de la requête HTTP
     * Si on ouble cette annotation, on va avoir un objet Film vide
     * Si besoin de récupérer un fichier (envoyer avec un input type="file") : @RequestParam("file") MultipartFile file (cf : https://spring.io/guides/gs/uploading-files)
     */
    @PreAuthorize("isAuthenticated()") // Method-Level Security : Accès à la méthode uniquement pour les utilisateurs connectes
    @PostMapping
    @Operation(summary = "ajouter un avis")
    public void postAvis(@PathVariable Long filmId, @Valid  @RequestBody Avis avis, Authentication authentication){

        // je récupère l'utilisateur connecté pour mettre son nom dans l'avis
        Utilisateur utilisateurConnecte = (Utilisateur) authentication.getPrincipal();
        avis.setNom(utilisateurConnecte.getMembre().getUsername());

        avisService.saveAvis(filmId, avis, utilisateurConnecte.getMembre());
    }

    /**
     * @PathVariable : permet de récupérer en argument une variable de l'URL de la requête HTTP
     * Ne pas oublier d'ajouter dans l'url de mappin comment retrouver cette variable : ici "/{id}"
     */
    @PreAuthorize("isAuthenticated()") // Method-Level Security : Accès à la méthode uniquement pour les utilisateurs connectes
    @DeleteMapping("/{id}")
    @Operation(summary = "supprimer un avis")
    public void deleteFilm(@PathVariable Long id, Authentication authentication){
        // je récupère l'utilisateur connecté pour l'ajouter en argument de l'appel au service (pour validation)
        Utilisateur utilisateurConnecte = (Utilisateur) authentication.getPrincipal();
        avisService.deleteAvis(id, utilisateurConnecte.getMembre());
    }

    /**
     * On peut combiner PathVariable et RequestBody
     */
    @PreAuthorize("isAuthenticated()") // Method-Level Security : Accès à la méthode uniquement pour les utilisateurs connectes
    @PutMapping("/{id}")
    @Operation(summary = "modifier un avis")
    public void postFilm(@PathVariable Long filmId, @PathVariable Long id, @RequestBody Avis avis, Authentication authentication){
        // je récupère l'utilisateur connecté pour l'ajouter en argument de l'appel au service (pour validation)
        Utilisateur utilisateurConnecte = (Utilisateur) authentication.getPrincipal();
        avis.setId(id);
        avisService.saveAvis(filmId,avis, utilisateurConnecte.getMembre());
    }
}
