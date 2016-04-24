package domain.user;

import domain.user.CreditCard;
import domain.user.SearchRequest;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-24T21:21:53")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, SearchRequest> searchRequest;
    public static volatile SingularAttribute<Person, String> password;
    public static volatile SingularAttribute<Person, String> emailAddress;
    public static volatile SingularAttribute<Person, String> address;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, Long> id;
    public static volatile SingularAttribute<Person, CreditCard> creditCard;
    public static volatile SingularAttribute<Person, String> username;

}