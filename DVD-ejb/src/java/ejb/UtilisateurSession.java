/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author chris
 */
@Stateful
public class UtilisateurSession implements UtilisateurSessionLocal {
    
    private String login ;
    private boolean admin ;
       
    @PersistenceContext(unitName = "DVD-ejbPU")
    private EntityManager em;
    
    public void init(){
        Query q = em.createNamedQuery("programmeur.find") ;
        q.setParameter("login",login);
        try{
           Programmeur programmeur = (Programmeur) q.getSingleResult() ;
           setAdmin(true);
        } catch (NoResultException e){
            setAdmin(false) ;
        }
    }
    
    @Override
    public void setLogin(String login){
        this.login = login ;
        init();
    }
    
    public String getLogin(){
        return this.login ;
    }
    
    public void setAdmin(boolean boule){
        this.admin = boule ;
    }
        
    public boolean isAdmin(){
        return admin ;
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }

    
}
