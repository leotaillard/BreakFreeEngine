/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.test;

import ch.comem.services.PartiesManagerLocal;
import ch.comem.services.PlayersManagerLocal;
import ch.comem.services.QuestionsManagerLocal;
import ch.comem.services.ReponsesManagerLocal;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;

/**
 *
 * @author Leo
 */
@WebService(serviceName = "PopulateBDB")
@Stateless()
public class PopulateBDB {
    @EJB
    private ReponsesManagerLocal reponsesManager;
    @EJB
    private QuestionsManagerLocal questionsManager;
    @EJB
    private PlayersManagerLocal playersManager;
    @EJB
    private PartiesManagerLocal partiesManager;

    public void populateDB() {
        
        Long partieId = partiesManager.createPartie();
        
        for (int i = 0; i < 4; i++) {            
            Long playerId = playersManager.createPlayer(partieId);
        }
        
        playersManager.setName("Léo", Long.valueOf(3));
        playersManager.setEmail("leo.taillard@gmail.com", Long.valueOf(3));
        playersManager.addPoint(100, Long.valueOf(3));
        
        for (int i = 0; i < 24; i++) {
            int randomNum = 1 + (int)(Math.random()*3);
            Long questionId = questionsManager.createQuestion("Cool "+i, randomNum, "media/cool_"+i+".png");
            partiesManager.addQuestion(questionId, partieId);
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    Long reponseId = reponsesManager.createReponse("Reponse"+j, "media/reponse_"+j+".png", true, questionId);
                    questionsManager.addReponse(questionId, reponseId);
                }
                else{
                    Long reponseId = reponsesManager.createReponse("Reponse"+j, "media/reponse_"+j+".png", false, questionId);
                    questionsManager.addReponse(questionId, reponseId);
                }
            }
            
        }


    }

    public void createQuestion(String titre, int diff, String urlMedia) {
        
        questionsManager.createQuestion(titre, diff, urlMedia);
        
    }

    /**
     * This is a sample web service operation
     */
    
}
