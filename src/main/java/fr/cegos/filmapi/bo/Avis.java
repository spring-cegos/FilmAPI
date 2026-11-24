package fr.cegos.filmapi.bo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// annotations Lombok pour générer constructeurs / getters / setters
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avis {
    private Long id;
    private String nom;
    @NotNull
    @Min(0) @Max(5)
    private Integer note;
    private LocalDate date;
    private String commentaire;

    /**
     * Association vers la classe Film
     * pas nécessaire si on a déjà specifié l'association dans le classe "Film"
     */
    // private Film film;

}
