/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.comem.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Leo
 */
@Entity
@XmlRootElement
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int nbrPoints;
    @ManyToOne (fetch = FetchType.LAZY)
    private Partie partie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNbrPoints() {
        return nbrPoints;
    }

    public void setNbrPoints(int nbrPoints) {
        this.nbrPoints = nbrPoints;
    }
    @XmlTransient
    @JsonIgnore
    public Partie getPartie() {
        return partie;
    }
    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Permet d'ajouter des points à un joueur
     * @param points (doit être supérieur à 0)
     */
    public void addPoints(int points){
        
//        if(points >= 0){
            int temp = getNbrPoints()+ points;
            setNbrPoints(temp);
//        }
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
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.comem.models.Player[ id=" + id + " ]";
    }
    
}
