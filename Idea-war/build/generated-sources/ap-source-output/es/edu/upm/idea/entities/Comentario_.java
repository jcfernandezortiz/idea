package es.edu.upm.idea.entities;

import es.edu.upm.idea.entities.Idea;
import es.edu.upm.idea.entities.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-24T15:29:02")
@StaticMetamodel(Comentario.class)
public class Comentario_ { 

    public static volatile SingularAttribute<Comentario, Usuario> idusuario;
    public static volatile SingularAttribute<Comentario, Idea> ididea;
    public static volatile SingularAttribute<Comentario, Integer> idcomentario;
    public static volatile SingularAttribute<Comentario, Date> fechaRegistro;
    public static volatile SingularAttribute<Comentario, Short> activo;
    public static volatile SingularAttribute<Comentario, String> comentario;

}