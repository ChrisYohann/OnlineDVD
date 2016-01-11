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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author chris
 */
@Entity
@Table(name = "DVDStock")
@NamedQueries({
@NamedQuery(name = "find.available", query = "Select d.dvd ,sum(d.quantity) from DVDStock d where d.dvd.id = :id group by d.dvd"),
@NamedQuery(name = "list.stock",query = "Select s from DVDStock s where s.dvd.id = :iddvd")})
public class DVDStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDVDStock")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "idDVD")
    private DVD dvd ;
    
    private Long quantity ;
    
    private String nameFournisseur ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getQuantity(){
        return quantity ;
    }
    
    public void setQuantity(Long stock){
        this.quantity = stock ;
    }
    
    public DVD getDVD(){
        return dvd ;
    }
    
    public void setDVD(DVD dvd){
        this.dvd = dvd ;
    }
    
    public String getNameFournisseur(){
        return nameFournisseur ;
    }
    
    public void setNameFournisseur(String name){
        nameFournisseur = name ;
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
        if (!(object instanceof DVDStock)) {
            return false;
        }
        DVDStock other = (DVDStock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.DVDStock[ id=" + id + " ]";
    }
    
}
