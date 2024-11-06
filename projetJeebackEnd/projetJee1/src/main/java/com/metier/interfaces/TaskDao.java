package com.metier.interfaces;

import java.util.List;

import com.entity.Task;

import jakarta.ejb.Local;

@Local
public interface TaskDao {
	List<Task> listerTache();
	void creertTache(Task task);
	void modifierTache(Task task);
	Task suprimerTache(String code);
}
