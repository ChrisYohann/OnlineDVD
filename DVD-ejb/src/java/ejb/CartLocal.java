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
public interface CartLocal {
    
    public List<DVD> getCart();
    
    public void addDVDToCart(DVD dvd_bean) ;
    
    public void removeFromCart(DVD dvd_bean);
    
    public void checkout(Utilisateur user);
    
    public List<Commande> findWaitingCommand() ;
    
}
