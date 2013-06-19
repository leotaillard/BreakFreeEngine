/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Partie;
import ch.comem.models.Player;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leo
 */
@Stateless
public class PlayersManager implements PlayersManagerLocal {
    @PersistenceContext(unitName = "BreakFreeEnginePU")
    private EntityManager em;
    
    /**
     * Méthode qui permet de créer un joueur et de seter son nombre de points à 0 et de l'attribuer à une partie en fonction de son id
     * @param partieId
     * @return id du joueur créé
     */
    @Override
    public Long createPlayer(Long partieId) {
        
        Player player = new Player();
        player.setNbrPoints(0);
        
        Partie partie = em.find(Partie.class, partieId);
        partie.addPlayer(player);
        player.setPartie(partie);
        
        em.persist(player);
        em.flush();
        
        return player.getId();
    }
    /**
     * méthode qui permet de lire un player
     * @param id
     * @return un objet de type player
     */
    @Override
    public Player readPlayer(Long playerId) {
        Player retourPlayer = em.find(Player.class, playerId);

        return retourPlayer;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Long setName(String name, Long playerId) {
        
        Player player = readPlayer(playerId);
        
        player.setName(name);
        
        return player.getId();
    }
    @Override
    public Long setEmail(String email, Long playerId) {
        
        Player player = readPlayer(playerId);
        
        player.setEmail(email);
        
        return player.getId();
    }
    /**
     * Permet d'ajouter des points à un joueur en fonction de son id
     * @param nbrPoints
     * @param playerId
     * @return le nombre de points du joueurs
     */
    @Override
    public int addPoint(int nbrPoints, Long playerId) {
        
        Player player = readPlayer(playerId);
        player.addPoints(nbrPoints);
        
        em.persist(player);
        em.flush();
        
        
        return player.getNbrPoints();
    }
    /**
     * Permet de modifier un joueur en fonction de son id
     * @param playerId
     * @param name
     * @param email
     * @return id du joueur modifié
     */
    @Override
    public Long modifyPlayer(Long playerId, String name, String email) {
        
        Player player = readPlayer(playerId);
        
        player.setName(name);
        player.setEmail(email);
        
        return player.getId();
    }
    
}
