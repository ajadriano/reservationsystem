package domain.user;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-24T21:21:53")
@StaticMetamodel(SearchRequest.class)
public class SearchRequest_ { 

    public static volatile SingularAttribute<SearchRequest, Date> fromDate;
    public static volatile SingularAttribute<SearchRequest, String> toAirport;
    public static volatile SingularAttribute<SearchRequest, Integer> passengerCount;
    public static volatile SingularAttribute<SearchRequest, Date> toDate;
    public static volatile SingularAttribute<SearchRequest, Date> fromTime;
    public static volatile SingularAttribute<SearchRequest, String> flightClass;
    public static volatile SingularAttribute<SearchRequest, String> flightType;
    public static volatile SingularAttribute<SearchRequest, Integer> stops;
    public static volatile SingularAttribute<SearchRequest, String> fromAirport;
    public static volatile SingularAttribute<SearchRequest, Date> toTime;

}