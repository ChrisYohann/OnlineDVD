/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chris
 */
@Stateless
public class MessageManager extends AbstractFacade<Message> implements MessageManagerLocal  {

    @EJB
    private CartLocal cart;
    
    @PersistenceContext(unitName = "DVD-ejbPU")
    private EntityManager em ;
    
    @Override
    protected EntityManager getEntityManager() {
        return em ;
    }
    
    public MessageManager(){
        super(Message.class);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List<Message> generateMessageType1() {
        List<Message> messages_list = new ArrayList<Message>();
        
        List<Commande> commande_delayed = cart.findWaitingCommand() ;
        for(Commande c : commande_delayed){
           Message m = new Message();
           m.setIddvd(c.getDvd().getId());
           m.setType(1L);
           messages_list.add(m);
        }
        
        return messages_list ;
    }

    @Override
    public List<Message> generateMessageType2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> generateMessageType3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> retrieveAll() {
      return findAll();
    }
}
