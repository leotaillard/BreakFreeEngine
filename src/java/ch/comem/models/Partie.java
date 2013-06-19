/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leo
 */
@Entity
@XmlRootElement
public class Partie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="isOver")
    private boolean isOver;
    @OneToMany(mappedBy = "partie", cascade = CascadeType.ALL)
    private List<Player> players = new LinkedList<Player>();
    @ManyToMany
    private List<Question> questions = new LinkedList<Question>();
    
    public boolean isIsOver() {
        return isOver;
    }

    public void setIsOver(boolean status) {
        this.isOver = status;
    }
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    /**
     * Permet d'ajouter un joueur dans la liste des joueurs de la partie
     * @param player 
     */
    public void addPlayer(Player player){
        if(!getPlayers().contains(player)){
            getPlayers().add(player);
        }
    }
    /**
     * Permet d'ajouter une question dans la liste des questions d'une partie
     * @param question 
     */
    public void addQuestion(Question question){
        if(!getQuestions().contains(question)){
            getQuestions().add(question);
        }
    }
    /**
     * Méthode permettant de changer le status de la partie à termniner et setant le boolean isOver à true
     */
    public void changeStatus(){
        setIsOver(true);
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partie)) {
            return false;
        }
        Partie other = (Partie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.models.Partie[ id=" + id + " ]";
    }
    
}
