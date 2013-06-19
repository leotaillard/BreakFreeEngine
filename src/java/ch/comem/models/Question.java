/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Leo
 */
@Entity
@XmlRootElement
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String urlMedia;
    private int rank;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Reponse> reponses = new LinkedList<Reponse>();
    @ManyToMany(mappedBy = "questions", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Partie> parties = new LinkedList<Partie>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titre) {
        this.title = titre;
    }

    public String getUrlMedia() {
        return urlMedia;
    }

    public void setUrlMedia(String urlMedia) {
        this.urlMedia = urlMedia;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int difficulte) {
        this.rank = difficulte;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }
    @XmlTransient
    @JsonIgnore
    public List<Partie> getParties() {
        return parties;
    }

    public void setParties(List<Partie> parties) {
        this.parties = parties;
    }


    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }
    /**
     * Permet d'ajouter une réponse
     * @param reponse 
     */
    public void addReponse(Reponse reponse){
        if(!getReponses().contains(reponse)){
            getReponses().add(reponse);
        }    
    }
    /**
     * Permet d'ajouter une partie
     * @param partie 
     */
    public void addPartie(Partie partie){
        if(!getParties().contains(partie)){
            getParties().add(partie);
        }
    
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
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.models.Question[ id=" + id + " ]";
    }
    
}
