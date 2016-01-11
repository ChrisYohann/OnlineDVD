package ejb;

import ejb.DVD;
import ejb.Utilisateur;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-11T21:02:41")
@StaticMetamodel(Commande.class)
public class Commande_ { 

    public static volatile SingularAttribute<Commande, Long> id;
    public static volatile SingularAttribute<Commande, String> etat;
    public static volatile SingularAttribute<Commande, DVD> dvd;
    public static volatile SingularAttribute<Commande, Utilisateur> login;

}