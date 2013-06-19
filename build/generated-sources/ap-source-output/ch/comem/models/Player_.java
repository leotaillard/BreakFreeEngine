package ch.comem.models;

import ch.comem.models.Partie;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-06-17T11:15:36")
@StaticMetamodel(Player.class)
public class Player_ { 

    public static volatile SingularAttribute<Player, Long> id;
    public static volatile SingularAttribute<Player, String> email;
    public static volatile SingularAttribute<Player, Integer> nbrPoints;
    public static volatile SingularAttribute<Player, String> name;
    public static volatile SingularAttribute<Player, Partie> partie;

}