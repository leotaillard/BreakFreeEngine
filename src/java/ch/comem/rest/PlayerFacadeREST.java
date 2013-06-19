/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest;

import ch.comem.models.Player;
import ch.comem.services.PlayersManagerLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Leo
 */
@Stateless
@Path("player")
public class PlayerFacadeREST {
    @EJB
    private PlayersManagerLocal playersManager;
    /**
     * Permet de modifier un joueur
     * @param entity
     * @return un objet de type player modifié
     */
    @PUT
    @Consumes({"application/json"})
    @Produces("application/json")
    public Player edit(Player entity) {
        Long playerId = playersManager.modifyPlayer(entity.getId(), entity.getName(), entity.getEmail());
        
        Player player = playersManager.readPlayer(playerId);
        return player;
    }
    /**
     * Permet de retourner un player en fonction de son id
     * @param id
     * @return un objet de type player
     */
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Player find(@PathParam("id") Long id) {
        Player player = playersManager.readPlayer(id);
        return player;
    }
    /**
     * Permet d'ajouter un nombre de points à un joueur en fonction son id
     * @param id
     * @param points
     * @return un objet de type player
     */
    @PUT
    @Path("{id}")
    public Player addPoints(@PathParam("id") Long id, @QueryParam("nbrPoints") int points) {
        
        Player player = playersManager.readPlayer(id);
        player.addPoints(points);
        
        return player;
    }

}
