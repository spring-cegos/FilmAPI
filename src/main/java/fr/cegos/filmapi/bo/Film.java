package fr.cegos.filmapi.bo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// annotations Lombok pour générer constructeurs / getters / setters
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String titre;
    @NotEmpty
    private String genre;
    private Integer duree;
    @Past
    private LocalDate dateSortie;
    @Size(min = 10, max = 250)
    private String synopsis;

    public Film(Long id, String titre, String genre, Integer duree, LocalDate dateSortie, String synopsis) {
        this.id = id;
        this.titre = titre;
        this.genre = genre;
        this.duree = duree;
        this.dateSortie = dateSortie;
        this.synopsis = synopsis;
    }

    /**
     * Association vers la classe Avis
     * Si jamais on supprime un film c'est Ok de supprimer les avis également
     * => cascade = CascadeType.ALL)
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id") // plutôt que d'avoir une table de jointure, je préfère rajouter une colonne film_id à mes avis
    @JsonBackReference // Dans une relation bidirectionnelle afin d'éviter les boucles infinie de JSON, je définie quelle association je met en priorité et en secondaire(ici @JsonBackReference)
    private List<Avis> avis = new ArrayList<>();

}
