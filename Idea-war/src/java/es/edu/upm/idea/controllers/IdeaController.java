package es.edu.upm.idea.controllers;

import es.edu.upm.idea.entities.Idea;
import es.edu.upm.idea.controllers.util.JsfUtil;
import es.edu.upm.idea.controllers.util.PaginationHelper;
import es.edu.upm.idea.entities.Clasificacion;
import es.edu.upm.idea.entities.EstadoIdea;
import es.edu.upm.idea.entities.Usuario;
import es.edu.upm.idea.services.ClasificacionFacade;
import es.edu.upm.idea.services.IdeaFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;


@Named("ideaController")
@SessionScoped
public class IdeaController implements Serializable {

    private Idea current;
    private DataModel items = null;
    @EJB
    private es.edu.upm.idea.services.IdeaFacade ejbFacade;
    
    @EJB
    private es.edu.upm.idea.services.ClasificacionFacade ejbFacadeClasification;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private String[] selectedClasification;
    private List<Clasificacion> clasifications;

    public IdeaController() {
       this.clasifications = new ArrayList<Clasificacion>();
       selectedClasification = null;
    }

    private ClasificacionFacade getClasification(){
      return ejbFacadeClasification;
    }
    
    public Idea getSelected() {
        if (current == null) {
            current = new Idea();
            
            for(Clasificacion c : getClasification().findAll() ){
                current.setDescripcion(c.getNombre());
            }
            current.setClasificacionList(clasifications);
            selectedItemIndex = -1;
        }
        return current;
    }

    private IdeaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Idea) getItems().getRowData();
        List<Clasificacion> list=getClasification().findAll();
        selectedClasification = new String[list.size()];
        int count = 0 ;
        
        for (Clasificacion selected : current.getClasificacionList() ){
            selectedClasification[count++] = selected.getIdclasificacion()+"";        
        }        
        current.setClasificacionList(list); 
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Idea();
        selectedClasification = null;
        clasifications = getClasification().findAll();
        current.setClasificacionList(clasifications);
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            //Se setea la Fecha actual:
            current.setFechaRegistro(new Date());
            current.setActivo((short) 1);
            current.setIdusuario(new Usuario(1));
            current.setIdestadoIdea(new EstadoIdea(1));
            List<Clasificacion> list =  new ArrayList<Clasificacion>();
            for(String s : selectedClasification ){
               list.add(getClasification().find( Integer.parseInt(s)));
            }
            current.setClasificacionList(list);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Idea) getItems().getRowData();
        List<Clasificacion> list=getClasification().findAll();
        selectedClasification = new String[list.size()];
        int count = 0 ;
        for (Clasificacion selected : current.getClasificacionList() ){
            selectedClasification[count++] = selected.getIdclasificacion()+"";        
        }        
        current.setClasificacionList(list); 
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            List<Clasificacion> list =  new ArrayList<Clasificacion>();
            for(String s : selectedClasification ){
               list.add(getClasification().find( Integer.parseInt(s)));
            }
            current.setClasificacionList(list);
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Idea) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
//        current = null;
        selectedClasification = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Idea getIdea(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public String[] getSelectedClasification() {
        return selectedClasification;
    }

    public void setSelectedClasification(String[] selectedClasification) {
        this.selectedClasification = selectedClasification;
    }

    @FacesConverter(forClass = Idea.class)
    public static class IdeaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IdeaController controller = (IdeaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ideaController");
            return controller.getIdea(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Idea) {
                Idea o = (Idea) object;
                return getStringKey(o.getIdidea());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Idea.class.getName());
            }
        }

    }

}
