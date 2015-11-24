package es.edu.upm.idea.entities;

import es.edu.upm.idea.entities.Clasificacion;
import es.edu.upm.idea.entities.Comentario;
import es.edu.upm.idea.entities.EstadoIdea;
import es.edu.upm.idea.entities.Usuario;
import es.edu.upm.idea.entities.Venta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< Updated upstream
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-24T01:40:33")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-23T23:40:29")
>>>>>>> Stashed changes
@StaticMetamodel(Idea.class)
public class Idea_ { 

    public static volatile ListAttribute<Idea, Comentario> comentarioList;
    public static volatile SingularAttribute<Idea, String> titulo;
    public static volatile SingularAttribute<Idea, Usuario> idusuario;
    public static volatile ListAttribute<Idea, Venta> ventaList;
    public static volatile SingularAttribute<Idea, Integer> ididea;
    public static volatile SingularAttribute<Idea, String> descripcion;
    public static volatile ListAttribute<Idea, Clasificacion> clasificacionList;
    public static volatile SingularAttribute<Idea, Date> fechaRegistro;
    public static volatile SingularAttribute<Idea, EstadoIdea> idestadoIdea;
    public static volatile SingularAttribute<Idea, Short> activo;

}