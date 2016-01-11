/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "Commande")
@NamedQuery(name = "commande.enattente", query = "select c from Commande c where c.etat = :etat")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCommande")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "login")
    private Utilisateur login ;
    
    @ManyToOne
    @JoinColumn(name = "idDVD")
    private DVD dvd ;
    
    private String etat ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
     public Utilisateur getLogin() {
        return login;
    }

    public void setLogin(Utilisateur login) {
        this.login = login;
    }

    public DVD getDvd() {
        return dvd;
    }

    public void setDvd(DVD dvd) {
        this.dvd = dvd;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Commande[ id=" + id + " ]";
    }
    
}
