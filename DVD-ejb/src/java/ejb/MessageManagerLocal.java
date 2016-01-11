/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chris
 */
@Local
public interface MessageManagerLocal {
    
    public List<Message> generateMessageType1() ;
    
    public List<Message> generateMessageType2();
    
    public List<Message> generateMessageType3();

    public List<Message> retrieveAll();
}
