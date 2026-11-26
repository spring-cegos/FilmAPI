package fr.cegos.filmapi.service;



import fr.cegos.filmapi.bo.Membre;

import java.util.List;

public interface MembreService {
    List<Membre> getMembres();
    Membre getMembreByUsername(String username);
    void  addMembre(Membre membre);
}
