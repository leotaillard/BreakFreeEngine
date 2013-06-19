/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.rest;

import ch.comem.models.Question;
import ch.comem.models.Reponse;
import ch.comem.services.QuestionsManagerLocal;
import ch.comem.services.ReponsesManagerLocal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("question")
public class QuestionFacadeREST {
    @EJB
    private ReponsesManagerLocal reponsesManager;
    @PersistenceContext(unitName = "BreakFreeEnginePU")
    private EntityManager em;
    @EJB
    private QuestionsManagerLocal questionsManager;
    /**
     * Permet de créer une question
     * @param entity
     * @return un objet de type Question
     */
    @POST
    @Consumes({"application/json"})
    public Question create(Question entity) {
        Long questionId = questionsManager.createQuestion(entity.getTitle(), entity.getRank(), entity.getUrlMedia());
        Question question = questionsManager.readQuestion(questionId);
        
        for (Reponse rep : entity.getReponses()) {
            
            Long reponseId = reponsesManager.createReponse(rep.getTitle(), rep.getUrlMedia(), rep.isIsCorrect(), questionId);
            Reponse reponse = reponsesManager.readReponse(reponseId);
            question.addReponse(reponse);
        }
        
        return question;
        
    }
    /**
     * Permet de modifier une question
     * @param entity
     * @return un objet de type question
     */
    @PUT
    @Consumes({"application/json"})
    public Question edit(Question entity) {
        Long questionId = questionsManager.modifyQuestion(entity.getTitle(), entity.getRank(), entity.getUrlMedia(), entity.getId());
        
        Question question = questionsManager.readQuestion(questionId);
        return question;
    }
    /**
     * Permetm de supprimer une question en fonction de son id
     * @param id
     * @return une réponse
     */
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        questionsManager.deleteQuestion(id);
        return Response.status(200).entity("la question a bien été supprimée").build();

    }
    /**
     * Permet de retourner une question en fonction de son id
     * @param id
     * @return un objet de type question
     */
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Question find(@PathParam("id") Long id) {
        Question question = questionsManager.readQuestion(id);
        return question;
    }


    public void persist(Object object) {
        em.persist(object);
    }
}
