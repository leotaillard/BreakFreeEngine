package ch.comem.models;

import ch.comem.models.Player;
import ch.comem.models.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-06-17T11:15:36")
@StaticMetamodel(Partie.class)
public class Partie_ { 

    public static volatile SingularAttribute<Partie, Long> id;
    public static volatile ListAttribute<Partie, Player> players;
    public static volatile ListAttribute<Partie, Question> questions;
    public static volatile SingularAttribute<Partie, Boolean> isOver;

}