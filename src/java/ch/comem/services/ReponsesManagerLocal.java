/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Reponse;
import javax.ejb.Local;

/**
 *
 * @author Leo
 */
@Local
public interface ReponsesManagerLocal {

    Long createReponse(String titre, String urlMedia, boolean isCorrect, Long questionId);
    Reponse readReponse(Long reponseId);
    Long modifyReponse(String titre, String urlMedia, boolean isCorrect, Long reponseId);
    void deleteReponse(Long reponseId);
}
