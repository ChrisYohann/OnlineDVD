/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Local;

/**
 *
 * @author chris
 */
@Local
public interface UtilisateurSessionLocal {
    
    public void setLogin(String login);
    
    public String getLogin();
    
    public boolean isAdmin();
    
}
