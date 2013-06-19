/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.services;

import ch.comem.models.Player;
import javax.ejb.Local;

/**
 *
 * @author Leo
 */
@Local
public interface PlayersManagerLocal {

    Long createPlayer(Long partieId);
    Player readPlayer(Long playerId);

    Long setName(String name, Long playerId);
    Long setEmail(String email, Long playerId);

    int addPoint(int nbrPoints, Long playerId);

    Long modifyPlayer(Long playerId, String name, String email);
}
