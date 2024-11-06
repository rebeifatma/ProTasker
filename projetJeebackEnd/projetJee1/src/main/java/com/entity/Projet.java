package com.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="projet")
public class Projet implements Serializable{


	private static final long serialVersionUID = 1L;
	private String code;
	private String description;
	@JsonbDateFormat("yyyy-MM-dd")
    private LocalDate startDate;
	private Collection<Task> tasks;


	public Projet(String code, String description, LocalDate startDate) {
		super();
		this.code = code;
		this.description = description;
		this.startDate = startDate;
	}

	public Projet() {
		super();
	}

	@Id
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	@OneToMany(mappedBy="projet", cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@JsonbTransient
	public Collection<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}
	@Override
	public String toString() {
		return "Projet [code=" + code + ", description=" + description + ", startDate=" + startDate + ", tasks="
				+ tasks + "]";
	}

}
