package ejb;

import ejb.DVDStock;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-11T21:02:41")
@StaticMetamodel(DVD.class)
public class DVD_ { 

    public static volatile SingularAttribute<DVD, Long> id;
    public static volatile SingularAttribute<DVD, String> author;
    public static volatile SingularAttribute<DVD, String> description;
    public static volatile SingularAttribute<DVD, String> name;
    public static volatile SingularAttribute<DVD, String> director;
    public static volatile ListAttribute<DVD, DVDStock> stocks;

}