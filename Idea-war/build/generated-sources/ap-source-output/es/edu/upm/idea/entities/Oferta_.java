package es.edu.upm.idea.entities;

import es.edu.upm.idea.entities.Usuario;
import es.edu.upm.idea.entities.Venta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-24T01:40:33")
@StaticMetamodel(Oferta.class)
public class Oferta_ { 

    public static volatile SingularAttribute<Oferta, Usuario> idusuario;
    public static volatile SingularAttribute<Oferta, Venta> idventa;
    public static volatile SingularAttribute<Oferta, Date> fechaRegistro;
    public static volatile SingularAttribute<Oferta, Integer> idoferta;
    public static volatile SingularAttribute<Oferta, Short> activo;
    public static volatile SingularAttribute<Oferta, Double> monto;

}