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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "programmeur")
@NamedQuery(name = "programmeur.find",query = "Select p from Programmeur p where p.id = :login")
public class Programmeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "login",nullable = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    private String nomUt ;
    private String prenomUt ;

    public String getNomUt() {
        return nomUt;
    }

    public void setNomUt(String nomUt) {
        this.nomUt = nomUt;
    }

    public String getPrenomUt() {
        return prenomUt;
    }

    public void setPrenomUt(String prenomUt) {
        this.prenomUt = prenomUt;
    }

    public String getAdresseUt() {
        return adresseUt;
    }

    public void setAdresseUt(String adresseUt) {
        this.adresseUt = adresseUt;
    }

    public String getPasswordUt() {
        return passwordUt;
    }

    public void setPasswordUt(String passwordUt) {
        this.passwordUt = passwordUt;
    }
    private String adresseUt ;
    
    @Column(nullable = false )
    private String passwordUt ;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programmeur)) {
            return false;
        }
        Programmeur other = (Programmeur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Programmeur[ id=" + id + " ]";
    }
    
}
