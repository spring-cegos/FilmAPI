package fr.cegos.filmapi.bo;

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
public class Film {
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
     */
    private List<Avis> avis = new ArrayList<>();

}
