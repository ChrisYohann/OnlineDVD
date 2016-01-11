/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
public class Cart extends AbstractFacade<Commande> implements CartLocal {

    @EJB
    private DVDStockFacade dvdStockFacade;

    @EJB
    private DVDManagerLocal dvdManager;
    
    @PersistenceContext(unitName = "DVD-ejbPU")
    private EntityManager em;
    
    public List<DVD> products ;
    
    public Cart(){
        super(Commande.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em ;
    }
    
    @PostConstruct
    public void init(){
        products = new ArrayList<DVD>();
    }
    
    @Override
    public void addDVDToCart(DVD dvd_bean) {
        products.add(dvd_bean);      
    }

    @Override
    public void removeFromCart(DVD dvd_bean) {
        products.remove(dvd_bean);
    }


    @Override
    public void checkout(Utilisateur user) {
        System.out.println("Actual user :"+user);        
        System.out.println("Nombre de produits : "+products.size());        
        for(DVD dividi : products ){            
            try {                    
                Commande commande = new Commande();
                commande.setDvd(dividi);
                commande.setLogin(user);
                if(dvdManager.getAvailable(dividi) > 0){
                    commande.setEtat("EN COURS");
                    dvdStockFacade.decrementStock(dividi);
                    
                } else {
                commande.setEtat("EN ATTENTE");
                }
                create(commande);
            
            } catch (NoResultException e){
                System.out.println("User "+user+" does not exist or is not actually connected");
            }
        }
        products.clear();
    }

    @Override
    public List<DVD> getCart() {
        return products ;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void manageStock(DVD dvd){
        
    }
    
    
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Commande> findWaitingCommand() {
        Query q = em.createNamedQuery("commande.encours");
        q.setParameter("etat","EN ATTENTE");
        return q.getResultList();
    }

    
}
