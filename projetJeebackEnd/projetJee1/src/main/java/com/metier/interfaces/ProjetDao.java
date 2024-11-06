package com.metier.interfaces;

import java.util.List;

import com.entity.Projet;
import com.entity.Task;

import jakarta.ejb.Local;

@Local
public interface ProjetDao {
	List<Projet> listerProjet();
	void creerProjet(Projet Projet);
	void modifierProjet(Projet Projet);
	Projet supprimerProjet(String code);
	void ajouterTacheProjet(Task t);
	void supprimerTacheProjet(Task t);
	Projet projetParCode(String codeProj);
}
