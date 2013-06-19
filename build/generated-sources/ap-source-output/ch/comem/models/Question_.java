package ch.comem.models;

import ch.comem.models.Partie;
import ch.comem.models.Reponse;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-06-17T11:15:36")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, Long> id;
    public static volatile SingularAttribute<Question, String> urlMedia;
    public static volatile SingularAttribute<Question, String> title;
    public static volatile SingularAttribute<Question, Integer> rank;
    public static volatile ListAttribute<Question, Partie> parties;
    public static volatile ListAttribute<Question, Reponse> reponses;

}