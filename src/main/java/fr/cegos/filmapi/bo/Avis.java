package fr.cegos.filmapi.bo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// annotations Lombok pour générer constructeurs / getters / setters
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne // en fait j'ai envie d'avoir une association bidirectionnelle
    // afin d'accéder aux infos du film depuis un avis
    @JsonManagedReference // pour éviter la boucle infinie dans le JSON, il faut définir quelle association je met en priorité @JsonManagedReference
    private Film film;

}
