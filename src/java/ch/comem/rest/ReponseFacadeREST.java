/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest;

import ch.comem.models.Reponse;
import ch.comem.services.ReponsesManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Leo
 */
@Stateless
@Path("reponse")
public class ReponseFacadeREST {
    @EJB
    private ReponsesManagerLocal reponsesManager;
    /**
     * Permet de créer une réponse
     * @param entity
     * @return un objet de type réponse
     */
    @POST
    @Consumes({"application/json"})
    public Reponse create(Reponse entity) {
        Long reponseId = reponsesManager.createReponse(entity.getTitle(), entity.getUrlMedia(), entity.isIsCorrect(), entity.getId());
        Reponse reponse = reponsesManager.readReponse(reponseId);

        
        return reponse;
    }
    /**
     * Permet de modifier une réponse
     * @param entity
     * @return un objet de type réponse modifié
     */
    @PUT
    @Consumes({"application/json"})
    public Reponse edit(Reponse entity) {
        
        Long reponseId = reponsesManager.modifyReponse(entity.getTitle(), entity.getUrlMedia(), entity.isIsCorrect(), entity.getId());
    
        Reponse reponse = reponsesManager.readReponse(reponseId);
        return reponse;
    }
    /**
     * Permet de supprimer une réponse en fonction de son id
     * @param id
     * @return une réponse
     */
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        reponsesManager.deleteReponse(id);
        return Response.status(200).entity("la réponse a bien été supprimée").build();
    }
    /**
     * Permet de retourner une réponse en fonction de son id
     * @param id
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Reponse find(@PathParam("id") Long id) {
        Reponse reponse = reponsesManager.readReponse(id);
        return reponse;
    }
    
}
