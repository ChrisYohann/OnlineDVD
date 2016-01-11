/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chris
 */
@JMSDestinationDefinition(name = "java:app/jms/NewMessage", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "NewMessage")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/jms/NewMessage")
})
public class NotifyGetDVD implements MessageListener {

    @PersistenceContext(unitName = "DVD-ejbPU")
    private EntityManager em;
    
    @Resource
    private MessageDrivenContext mdc;
    
    public NotifyGetDVD() {
    }
    
    @Override
    public void onMessage(Message message) {
         ObjectMessage msg = null;
    try {
        if (message instanceof ObjectMessage) {
            msg = (ObjectMessage) message;
            Message m = (Message) msg.getObject();
            save(m);            
        }
    } catch (JMSException e) {
        e.printStackTrace();
        mdc.setRollbackOnly();
    } catch (Throwable te) {
        te.printStackTrace();
    }
    }

    public void save(Object object) {
        em.persist(object);
    }
    
}
