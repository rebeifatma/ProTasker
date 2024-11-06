package com.metier.impl;

import java.util.List;

import com.entity.Projet;
import com.entity.Task;
import com.metier.interfaces.ProjetDao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Stateless
public class ProjetDaoImpl implements ProjetDao{

	@PersistenceContext(unitName = "projetjee")
	EntityManager em;

	@Override
	public List<Projet> listerProjet() {
		String requeteJPQL= "select p from Projet p ";
		Query query= em.createQuery(requeteJPQL);

		return query.getResultList();
	}

	@Override
	public void creerProjet(Projet Projet) {
		em.persist(Projet);
	}

	@Override
	public void modifierProjet(Projet Projet) {
		em.merge(Projet);
	}

	@Override
	public Projet supprimerProjet(String code) {
		Projet p = em.find(Projet.class, code);
		em.remove(p);
		return p;
	}

	@Override
	public void ajouterTacheProjet(Task t) {
		Projet p = em.find(Projet.class, t.getProjet().getCode());
		p.getTasks().add(t);
		em.merge(p);
	}

	@Override
	public void supprimerTacheProjet(Task t) {
	    Projet p = em.find(Projet.class, t.getProjet().getCode());
	    p.getTasks().remove(t);
	    em.merge(p);
	}
	
	@Override
	public Projet projetParCode(String codeProj) {
		 String requeteJPQL = "SELECT p FROM Projet p WHERE p.code = :code";
		    Query query = em.createQuery(requeteJPQL);
		    query.setParameter("code", codeProj);

		    return (Projet) query.getSingleResult();
	}

}
