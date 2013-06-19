/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Question;
import ch.comem.models.Reponse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Leo
 */
@Stateless
public class QuestionsManager implements QuestionsManagerLocal {
    @PersistenceContext(unitName = "BreakFreeEnginePU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    /**
     * Permet de creer une question avec un titre, une difficultée et une url pour média
     * @param titre
     * @param difficultee
     * @param urlMedia
     * @return id de la question créée
     */
    @Override
    public Long createQuestion(String titre, int difficultee, String urlMedia) {
        
        Question question = new Question();
        
        question.setRank(difficultee);
        question.setTitle(titre);
        question.setUrlMedia(urlMedia);
        
        em.persist(question);
        em.flush();
        
        return question.getId();
    }
    
    /**
     * méthode qui permet de lire une question
     * @param id
     * @return un objet de type Question
     */
    @Override
    public Question readQuestion(Long questionId) {
        Question retourQuestion = em.find(Question.class, questionId);

        return retourQuestion;
    }
    /**
     * Permet de modifier une question en fonction de son id
     * @param titre
     * @param difficultee
     * @param urlMedia
     * @param questionId
     * @return id de la question modifiée
     */
    @Override
    public Long modifyQuestion(String titre, int difficultee, String urlMedia, Long questionId) {
        
        Question question = readQuestion(questionId);
        
        question.setRank(difficultee);
        question.setTitle(titre);
        question.setUrlMedia(urlMedia);
        
        em.persist(question);
        em.flush();
        
        return question.getId();
    }
    /**
     * Permet de supprimer une question en fonction de son id
     * @param questionId 
     */
    @Override
    public void deleteQuestion(Long questionId) {
        
        Question question = readQuestion(questionId);
        
        em.remove(question);
    }
    /**
     * Permet d'ajouter une réponse en fonction de son id à une question en fonction de son id
     * @param questionId
     * @param reponseId 
     */
    @Override
    public void addReponse(Long questionId, Long reponseId) {
        
        Question question = readQuestion(questionId);
        Reponse reponse = em.find(Reponse.class, reponseId);
        
        question.addReponse(reponse);
        reponse.setQuestion(question);
        
        em.persist(question);
        em.flush();
        
    }

    

}
