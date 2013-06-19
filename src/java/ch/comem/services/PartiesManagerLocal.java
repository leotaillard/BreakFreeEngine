/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Partie;
import ch.comem.models.Player;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Leo
 */
@Local
public interface PartiesManagerLocal {

    Long createPartie();
    Partie readPartie(Long partieId);
    Long changeStatus(Long partieId);
    void addQuestion(Long questionId, Long PartieId);

    List<Player> getWinner(Long partieId);
}
