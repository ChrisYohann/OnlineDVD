/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author chris
 */
@Stateless
public class DVDManager extends AbstractFacade<DVD> implements DVDManagerLocal {

    @PersistenceContext(unitName = "DVD-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DVDManager() {
        super(DVD.class);
    }

    @Override
    public void addDVD(DVD bean,List<DVDStock> stocks) {
        bean.setStocks(stocks);
        create(bean);
        
    }

    @Override
    public List<DVD> showAll() {
        return findAll();
    }

    @Override
    public Long getAvailable(DVD bean) {
        Query q = em.createNamedQuery("find.available",Object[].class);
        q.setParameter("id",bean.getId());
        List<Object[]> results = q.getResultList();
        for (Object[] result : results) {
        System.out.println("idDVD: " + result[0] + ", Avalaible: " + result[1]);
        return (Long)result[1] ;
    }
        return 0L ;
    }
    
}
