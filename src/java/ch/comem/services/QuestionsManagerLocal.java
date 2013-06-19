/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Question;
import javax.ejb.Local;

/**
 *
 * @author Leo
 */
@Local
public interface QuestionsManagerLocal {

    Long createQuestion(String titre, int difficultee, String urlMedia);
    Question readQuestion(Long questionId);
    Long modifyQuestion(String titre, int difficultee, String urlMedia, Long questionId);
    void deleteQuestion(Long questionId);

    void addReponse(Long questionId, Long reponseId);
}
