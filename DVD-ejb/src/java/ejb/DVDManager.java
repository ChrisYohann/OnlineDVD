/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void addDVD(DVD bean) {
        create(bean);
    }
    
}
