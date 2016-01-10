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
public interface ConnectLocal {
    
    public Utilisateur connect(String login,String password);
}
