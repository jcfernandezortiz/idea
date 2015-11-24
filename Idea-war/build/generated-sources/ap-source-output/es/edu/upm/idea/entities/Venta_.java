package es.edu.upm.idea.entities;

import es.edu.upm.idea.entities.EstadoSubasta;
import es.edu.upm.idea.entities.Idea;
import es.edu.upm.idea.entities.Oferta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-24T01:40:33")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Integer> idventa;
    public static volatile SingularAttribute<Venta, Idea> ididea;
    public static volatile SingularAttribute<Venta, EstadoSubasta> idestadoSubasta;
    public static volatile SingularAttribute<Venta, Date> fechaFin;
    public static volatile SingularAttribute<Venta, Date> fechaInicio;
    public static volatile SingularAttribute<Venta, Integer> activo;
    public static volatile ListAttribute<Venta, Oferta> ofertaList;

}