package com.webservice;

import java.util.List;

import com.entity.Projet;
import com.metier.interfaces.ProjetDao;

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
@Path("/Projet")
public class ProjetService {

	@EJB
	ProjetDao ProjetDao;

	@GET
	@Path("/listeProjet")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerProjet() {
	    try {
	        List<Projet> Projets = ProjetDao.listerProjet();
	        return Response.status(Response.Status.OK).entity(Projets).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur d'extraction Projets").build();
	    }
	}

	@POST
	@Path("/creerProjet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response creerProjet(Projet Projet) {
	    try {
	        ProjetDao.creerProjet(Projet);
	        return Response.status(Response.Status.CREATED).entity(Projet).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur creation Projet").build();
	    }
	}
	@PUT
	@Path("/modifierProjet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifierProjet(Projet Projet){
	    try {
	        ProjetDao.modifierProjet(Projet);
	        return Response.status(Response.Status.OK).entity(Projet).build();
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error miseajour projet").build();
	    }
	}

	@DELETE
	@Path("/supprimerProjet/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerProjet(@PathParam(value="code") String code) {
	    try {
	        Projet projet = ProjetDao.supprimerProjet(code);
	        if (projet != null) {
	            return Response.status(Response.Status.OK).entity(projet).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).entity("Projet introuvable").build();
	        }
	    } catch (Exception e) {
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur supression Projet").build();
	    }
	}

}
