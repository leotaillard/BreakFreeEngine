/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest;

import ch.comem.models.Partie;
import ch.comem.models.Player;
import ch.comem.models.Question;
import ch.comem.services.PartiesManagerLocal;
import ch.comem.services.PlayersManagerLocal;
import ch.comem.services.QuestionsManagerLocal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Leo
 */
@Stateless
@Path("partie")
public class PartieFacadeREST {
    @EJB
    private QuestionsManagerLocal questionsManager;
    @EJB
    private PlayersManagerLocal playersManager;
    @PersistenceContext(unitName = "BreakFreeEnginePU")
    private EntityManager em;
    @EJB
    private PartiesManagerLocal partiesManager;

    /**
     * Permet de créer une partie avec un nombre de joueurs en paramètre et d'avoir une sélection de 12 questions aléatoires de la collections.
     * @param entity
     * @param nbrPlayer
     * @return un objet de type partie
     */
    @POST
    @Consumes({"application/json"})
    public Partie create(Partie entity, @QueryParam("nbrPlayer") int nbrPlayer) {
        
        Long partieId = partiesManager.createPartie();
        
        for (int i = 0; i < nbrPlayer; i++) {
            
            Long playerId = playersManager.createPlayer(partieId);
            playersManager.setName("player"+i, playerId);
            
        }
        
        List<Long> partiesIdEasy = null;
        List<Long> partiesIdMedium = null;
        List<Long> partiesIdHard = null;

        // début de la rêquete pour sélectionner tous les id de questions de type facile
        Query query = em.createQuery("SELECT q.id FROM Question q WHERE q.rank ='1'");
        partiesIdEasy = query.getResultList();
        List<Long> partiesIdRand = pickNRandom(partiesIdEasy, 3);
        Partie partie = partiesManager.readPartie(partieId);
        for (int i = 0; i < partiesIdRand.size(); i++) {
            Question question = questionsManager.readQuestion(partiesIdRand.get(i));
            partie.addQuestion(question);
        }
        // début de la rêquete pour sélectionner tous les id de questions de type moyne
        Query queryMedium = em.createQuery("SELECT q.id FROM Question q WHERE q.rank ='2'");
        partiesIdMedium = queryMedium.getResultList();
        List<Long> partiesIdMediumRand = pickNRandom(partiesIdMedium, 6);
        for (int i = 0; i < partiesIdMediumRand.size(); i++) {
            Question question = questionsManager.readQuestion(partiesIdMediumRand.get(i));
            partie.addQuestion(question);
        }
        // début de la rêquete pour sélectionner tous les id de questions de type difficile
        Query queryHard = em.createQuery("SELECT q.id FROM Question q WHERE q.rank ='3'");
        partiesIdHard = queryHard.getResultList();
        List<Long> partiesIdHardRand = pickNRandom(partiesIdHard, 3);
        for (int i = 0; i < partiesIdHardRand.size(); i++) {
            Question question = questionsManager.readQuestion(partiesIdHardRand.get(i));
            partie.addQuestion(question);
        }

        return partie;
    }
    /**
     * Permet de retourner une partie en fonction de son id
     * @param id
     * @return un objet de type partie
     */
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Partie find(@PathParam("id") Long id) {
        
        Partie partie = partiesManager.readPartie(id);
        return partie;
    }
    /**
     * Permet de retourner le gagnant d'une partie en fonctin de son id
     * @param id
     * @return un objet de type player
     */
    @GET
    @Path("{id}/gagnant")
    @Produces({"application/json"})
    public List<Player> getWinner(@PathParam("id") Long id) {
        
        List<Player> players = partiesManager.getWinner(id);
        
        return players;
    }
    /**
     * Permet de terminer en setant son statut isOver à true
     * @param id
     * @return un objet de type réponse
     */
    @PUT
    @Path("{id}/terminePartie")
    public Response endGame(@PathParam("id") Long id) {
        
        Long partieId = partiesManager.changeStatus(id);
        
        return Response.status(200).entity("la partie est terminée").build();
    }
    /**
     * Permet de retourner une liste des parties en cours
     * @return la liste des parties qui ont leur statut isOver à false
     */
    @GET
    @Path("partieEnCours")
    @Produces({"application/json"})
    public List<Partie> getPartieEnCours() {
        
        List<Partie> partie = null;
        Query query = em.createQuery("SELECT p FROM Partie p where p.isOver = '0'");
        partie = query.getResultList();

        return partie;
    }
    /**
     * Méthode qui retourne une question qui n'a pas encore été posée lors de la partie
     * @param id
     * @return un objet de type Question
     */
    @GET
    @Path("{id}/questionRandom")
    @Produces({"application/json"})
    public Question getQuestionRandom(@PathParam("id") Long id){
        
        Partie partie = partiesManager.readPartie(id);
        List<Long> questionsId = new LinkedList<Long>();
        
        for (Question q : partie.getQuestions()) {
            questionsId.add(q.getId());
        }
        
        List<Long> allQuestionId = new LinkedList<Long>();
        Query query = em.createQuery("SELECT q.id FROM Question q");
        allQuestionId = query.getResultList();

        allQuestionId.removeAll(questionsId);
        
        List<Long> idRand = pickNRandom(allQuestionId, 1);
        
        Question questionRandom = questionsManager.readQuestion(idRand.get(0));
        partie.addQuestion(questionRandom);
        return questionRandom;
    }
    /**
     * Méthode qui retourne la liste des players d'une partie en fonction de son id
     * @param id
     * @return une liste d'objet de type player
     */
    @GET
    @Path("{id}/players")
    @Produces({"application/json"})
    public List<Player> getAllPlayer(@PathParam("id") Long id) {
        
        Partie partie = partiesManager.readPartie(id);
        
        List<Player> players = partie.getPlayers();
        
        return players;
    }

    public void persist(Object object) {
        em.persist(object);
    }
    /**
     * Méthode qui permet de choisir n élément dans une liste de façon aléatoire
     * @param lst
     * @param n
     * @return 
     */
    public static List<Long> pickNRandom(List<Long> lst, int n) {
        List<Long> copy = new LinkedList<Long>(lst);
        Collections.shuffle(copy);
        return copy.subList(0, n);
    }
    
    
    
}
