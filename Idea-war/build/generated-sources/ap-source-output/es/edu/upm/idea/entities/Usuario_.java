package es.edu.upm.idea.entities;

import es.edu.upm.idea.entities.Comentario;
import es.edu.upm.idea.entities.Idea;
import es.edu.upm.idea.entities.Oferta;
import es.edu.upm.idea.entities.Perfil;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-24T15:29:02")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile ListAttribute<Usuario, Comentario> comentarioList;
    public static volatile SingularAttribute<Usuario, Integer> idusuario;
    public static volatile ListAttribute<Usuario, Idea> ideaList;
    public static volatile SingularAttribute<Usuario, Date> fechaRegistro;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile SingularAttribute<Usuario, String> correo;
    public static volatile SingularAttribute<Usuario, Short> activo;
    public static volatile SingularAttribute<Usuario, Perfil> idperfil;
    public static volatile ListAttribute<Usuario, Oferta> ofertaList;

}