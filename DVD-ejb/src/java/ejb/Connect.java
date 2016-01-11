/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author chris
 */
@Stateless
public class Connect implements ConnectLocal {

    @PersistenceContext(unitName = "DVD-ejbPU")
    private EntityManager em;

    @Override
    public Utilisateur connect(String login, String password) {
       Query q = em.createNamedQuery("utilisateur.find");
       q.setParameter("login",login);
       try{
           Utilisateur user = (Utilisateur) q.getSingleResult();
           return user ;
       } catch (NoResultException e){
           return null ;
       }
       
    }
   
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }
}
