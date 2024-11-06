package com.metier.impl;

import java.util.List;
import com.entity.Task;
import com.metier.interfaces.ProjetDao;
import com.metier.interfaces.TaskDao;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class TaskDaoImpl implements TaskDao{
	@PersistenceContext(unitName = "projetjee")
	EntityManager em;
	@EJB
	ProjetDao ProjetDao;
	@Override
	public List<Task> listerTache() {
		String requeteJPQL= "select t from Task t ";
		Query query= em.createQuery(requeteJPQL);
		List<Task>  tasks = query.getResultList();
		return tasks;
	}
	@Override
	public void creertTache(Task task) {
		em.persist(task);
	    ProjetDao.ajouterTacheProjet(task);
	}
	@Override
	public void modifierTache(Task task) {
		em.merge(task);
	}
	@Override
	public Task suprimerTache(String code) {
		Task t = em.find(Task.class, code);
		ProjetDao.supprimerTacheProjet(t);
		em.remove(t);
		return t;
	}
}
