/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author chris
 */
@Stateless
public class DVDStockFacade extends AbstractFacade<DVDStock> {

    @PersistenceContext(unitName = "DVD-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DVDStockFacade() {
        super(DVDStock.class);
    }
    
    public void decrementStock(DVD dvd){
       Query q = em.createNamedQuery("list.stock");
       q.setParameter("iddvd", dvd.getId());
       List<DVDStock> stocks = (List<DVDStock>)q.getResultList() ;
       Random random = new Random(System.currentTimeMillis());
       int length ;
        do {
            length = random.nextInt(stocks.size());
        } while(stocks.get(length).getQuantity() <= 0 ) ;
        DVDStock fournisseur = stocks.get(length);
                 fournisseur.setQuantity(fournisseur.getQuantity()-1);
                 edit(fournisseur);        
    }
    
}
