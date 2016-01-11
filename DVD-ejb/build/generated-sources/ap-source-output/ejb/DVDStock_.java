package ejb;

import ejb.DVD;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-11T21:02:41")
@StaticMetamodel(DVDStock.class)
public class DVDStock_ { 

    public static volatile SingularAttribute<DVDStock, Long> id;
    public static volatile SingularAttribute<DVDStock, DVD> dvd;
    public static volatile SingularAttribute<DVDStock, String> nameFournisseur;
    public static volatile SingularAttribute<DVDStock, Long> quantity;

}