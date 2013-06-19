package ch.comem.models;

import ch.comem.models.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-06-17T11:15:36")
@StaticMetamodel(Reponse.class)
public class Reponse_ { 

    public static volatile SingularAttribute<Reponse, Long> id;
    public static volatile SingularAttribute<Reponse, String> urlMedia;
    public static volatile SingularAttribute<Reponse, String> title;
    public static volatile SingularAttribute<Reponse, Boolean> isCorrect;
    public static volatile SingularAttribute<Reponse, Question> question;

}