/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Partie;
import ch.comem.models.Player;
import ch.comem.models.Question;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leo
 */
@Stateless
public class PartiesManager implements PartiesManagerLocal {
    @EJB
    private PlayersManagerLocal playersManager;
    @PersistenceContext(unitName = "BreakFreeEnginePU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    /**
     * méthode qui permet de lire une partie
     * @param id
     * @return un objet de type partie
     */
    @Override
    public Partie readPartie(Long partieId) {
        Partie retourPartie = em.find(Partie.class, partieId);

        return retourPartie;
    }
    /**
     * Méthode qui permet de créer une partie en cours
     * @return id de la partie créée
     */
    @Override
    public Long createPartie() {
        Partie partie = new Partie();
        partie.setIsOver(false);
        
        
        em.persist(partie);
        em.flush();
        
        return partie.getId();
    }
    /**
     * Méthode qui permet de mettre le statut du boolean isOver à true d'une partie en fonction de son id
     * @param partieId
     * @return id de la partie modifiée
     */
    @Override
    public Long changeStatus(Long partieId) {
        
        Partie partie = readPartie(partieId);
        
        partie.changeStatus();
        
        return partie.getId();
    }
    /**
     * Méthode qui permet d'ajouter une question en fonction de son id à une partie en fonction de son id
     * @param questionId
     * @param partieId 
     */
    @Override
    public void addQuestion(Long questionId, Long partieId) {
        
        Question question = em.find(Question.class, questionId);
        Partie partie = em.find(Partie.class, partieId);
        
        question.addPartie(partie);
        partie.addQuestion(question);
        
        em.persist(partie);
        em.flush();
        
    }
    /**
     * Méthode qui permet de retourner le gagnant d'une partie en fonction de son id
     * @param partieId
     * @return un objet de type player
     */
    @Override
    public List<Player> getWinner(Long partieId) {
        Partie partie = readPartie(partieId);
        
        List<Player> players = partie.getPlayers();
        List<Player> playerExAequo = new LinkedList<Player>();
        
        int pointTemp = 0;

        for (Player pl : players) {
            if(pointTemp <= pl.getNbrPoints()){
                pointTemp = pl.getNbrPoints();
            }
        }
        
        for (Player player : players) {
            if (pointTemp == player.getNbrPoints()) {
                playerExAequo.add(player);
            }
        }
        return playerExAequo;
    }
}
