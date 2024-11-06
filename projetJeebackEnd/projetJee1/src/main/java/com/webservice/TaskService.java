package com.webservice;

import java.util.List;

import com.entity.Projet;
import com.entity.Task;
import com.metier.interfaces.ProjetDao;
import com.metier.interfaces.TaskDao;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
@Path("/tache")
public class TaskService {

	@EJB
	TaskDao taskDao;
	
	@EJB
	ProjetDao ProjetDao; 

	@GET
	@Path("/taches")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerTache() {
	    try {
	        List<Task> tasks = taskDao.listerTache();
	        return Response.status(Response.Status.OK).entity(tasks).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving tasks").build();
	    }
	}

	@POST
	@Path("/creerTache")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response creertTache(Task task) {
	    try {
	        Projet Projet = ProjetDao.projetParCode(task.getProjet().getCode());
	        Task t = new Task(task.getCode(), task.getDescription(), task.getStartDate(), task.getEndDate(), Projet);
	        taskDao.creertTache(t);

	        return Response.status(Response.Status.CREATED).entity(t).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("erreur").build();
	    }
	}



	@PUT
	@Path("/modifierTache")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierTache(Task task) {
	    try {
	    	Projet Projet = ProjetDao.projetParCode(task.getProjet().getCode());

	        Task t = new Task(task.getCode(),task.getDescription(),task.getStartDate(), task.getEndDate(), Projet);
	        taskDao.modifierTache(t);
	        return Response.status(Response.Status.OK).entity(t).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur").build();
	    }
	}

	@DELETE
	@Path("/supprimer/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response suprimerTache(@PathParam(value="code") String code) {
	    try {
	        Task tache = taskDao.suprimerTache(code);
	        if (tache != null) {
	            return Response.status(Response.Status.OK).entity(tache).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).entity("Tache n'existe pas").build();
	        }
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur").build();
	    }
	}

}
