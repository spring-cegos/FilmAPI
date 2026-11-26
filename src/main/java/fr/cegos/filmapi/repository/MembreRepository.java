package fr.cegos.filmapi.repository;


import fr.cegos.filmapi.bo.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembreRepository extends JpaRepository<Membre, Long> {
    // on utilise un JPA Query Method pour récupérer le membre avec le username donné (dans l'authentification)
    Membre findFirstByUsername(String username);

}
