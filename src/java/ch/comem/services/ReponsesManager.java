/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Question;
import ch.comem.models.Reponse;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leo
 */
@Stateless
public class ReponsesManager implements ReponsesManagerLocal {
    @EJB
    private QuestionsManagerLocal questionsManager;
    @PersistenceContext(unitName = "BreakFreeEnginePU")
    private EntityManager em;
    
    public void persist(Object object) {
        em.persist(object);
    }
    /**
     * Permet de créer une réponse avec un titre, une url, de définir s'il est juste ou pas en fonction de l'id d'une question
     * @param titre
     * @param urlMedia
     * @param isCorrect
     * @param questionId
     * @return id de la réponse créée
     */
    @Override
    public Long createReponse(String titre, String urlMedia, boolean isCorrect, Long questionId) {
        
        Reponse reponse = new Reponse();
        reponse.setTitle(titre);
        reponse.setUrlMedia(urlMedia);
        reponse.setIsCorrect(isCorrect);
        
        /* Creer la connexion entre reponse et question */
        Question question = questionsManager.readQuestion(questionId);
        question.addReponse(reponse);
        reponse.setQuestion(question);
        
        em.persist(reponse);
        em.flush();
        
        return reponse.getId();
    }
    /**
     * méthode qui permet de lire une reponse
     * @param reponseId
     * @return un objet de type Reponse
     */
    @Override
    public Reponse readReponse(Long reponseId) {
        Reponse retourReponse = em.find(Reponse.class, reponseId);

        return retourReponse;
    }
    /** 
     * Permet de modifier une réponse en fonction de son id
     * @param titre
     * @param urlMedia
     * @param isCorrect
     * @param reponseId
     * @return id de la réponse modifiée
     */
    @Override
    public Long modifyReponse(String titre, String urlMedia, boolean isCorrect, Long reponseId) {
        
        Reponse reponse = readReponse(reponseId);
        reponse.setTitle(titre);
        reponse.setUrlMedia(urlMedia);
        reponse.setIsCorrect(isCorrect);
        
        em.persist(reponse);
        em.flush();
        
        return reponse.getId();
    }
    /**
     * Permet de supprimer une réponse en fonction de son id
     * @param reponseId 
     */
    @Override
    public void deleteReponse(Long reponseId) {
        Reponse reponse = readReponse(reponseId);
        
        em.remove(reponse);
        
    }

}
