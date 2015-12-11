/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.edu.upm.idea.controllers;

import es.edu.upm.idea.controllers.util.JsfUtil;
import es.edu.upm.idea.controllers.util.PaginationHelper;
import es.edu.upm.idea.entities.Clasificacion;
import es.edu.upm.idea.entities.Comentario;
import es.edu.upm.idea.entities.EstadoIdea;
import es.edu.upm.idea.entities.Idea;
import es.edu.upm.idea.entities.Usuario;
import es.edu.upm.idea.services.ClasificacionFacade;
import es.edu.upm.idea.services.IdeaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author DieGo
 */
@Named("otherListController")
@SessionScoped
public class OtherListController implements Serializable {
    
    int IDEA_ACTIVE_FALSE = 0;
    int IDEA_ACTIVE_TRUE = 1;
    int IDEA_ESTADO_ACTIVE = 1;
    int IDEA_ESTADO_INACTION = 2;
    int IDEA_ESTADO_SOLD = 3;
    int IDEA_ESTADO_STANDBY = 4;
    Integer selectedEstadoIdea;

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
    private List<Idea> filteredIdeas;
    private String[] coments;
    private String myComent;
            
    public OtherListController() {
       this.clasifications = new ArrayList<Clasificacion>();
       selectedClasification = null;
       myComent = null;
       coments = null;
       selectedEstadoIdea = IDEA_ESTADO_ACTIVE;
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
        return "OtherList";
    }

    public String prepareView() {
        current = (Idea) getItems().getRowData();
        List<Clasificacion> list=getClasification().findAll();
        selectedEstadoIdea = current.getIdestadoIdea().getIdestadoIdea();
        coments = null;
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
        selectedEstadoIdea = IDEA_ESTADO_ACTIVE;
        clasifications = getClasification().findAll();
        current.setClasificacionList(clasifications);
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        Usuario currentUser= JsfUtil.getLoggedUser();
        if(currentUser == null){
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("No user active"));
            return null;
        }
        try {
            //Se setea la Fecha actual:
            current.setFechaRegistro(new Date());
            current.setActivo((short) IDEA_ACTIVE_TRUE);
            current.setIdusuario(currentUser);
            current.setIdestadoIdea(new EstadoIdea(selectedEstadoIdea));
            List<Clasificacion> list =  new ArrayList<Clasificacion>();
            for(String s : selectedClasification ){
               list.add(getClasification().find( Integer.parseInt(s)));
            }
            if (list.isEmpty()){
             JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoClasificationSelected"));
             return null;
            }
            current.setClasificacionList(list);
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeaCreated"));
            returnToList();
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Idea) getItems().getRowData();
        List<Clasificacion> list=getClasification().findAll();
        selectedClasification = new String[list.size()];
        selectedEstadoIdea = current.getIdestadoIdea().getIdestadoIdea();
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
            current.setIdestadoIdea(new EstadoIdea(selectedEstadoIdea));
            List<Clasificacion> list =  new ArrayList<Clasificacion>();
            for(String s : selectedClasification ){
               list.add(getClasification().find( Integer.parseInt(s)));
            }
            if (list.isEmpty()){
             JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NoClasificationSelected"));
             return null;
            }
            current.setClasificacionList(list);
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IdeaUpdated"));
            returnToList();
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String comment() {
        try {            
            if (!myComent.isEmpty()){
                // crea comentario 
                Comentario coment = new Comentario();
                coment.setComentario(myComent);
                coment.setIdidea(current);
                coment.setFechaRegistro(new Date());
                coment.setIdusuario(JsfUtil.getLoggedUser());
                List<Comentario> comentList = current.getComentarioList();
                comentList.add(coment);
                current.setComentarioList(comentList);
                myComent = null;
            }
            getFacade().edit(current);
            return null;
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
    public void returnToList() throws Exception{
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().getExternalContext().redirect("OtherList.xhtml");          
        
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
        selectedEstadoIdea = IDEA_ESTADO_ACTIVE;
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

    public String[] getComents() {
        return coments;
    }

    public void setComents(String[] coments) {
        this.coments = coments;
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

    public String getMyComent() {
        return myComent;
    }

    public void setMyComent(String myComent) {
        this.myComent = myComent;
    }

    public List<Idea> getFilteredIdeas() {
        return filteredIdeas;
    }

    public void setFilteredIdeas(List<Idea> filteredIdeas) {
        this.filteredIdeas = filteredIdeas;
    }
    
    

    @FacesConverter(forClass = Idea.class)
    public static class OtherListControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OtherListController controller = (OtherListController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "otherListController");
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

    public Integer getSelectedEstadoIdea() {
        return selectedEstadoIdea;
    }

    public void setSelectedEstadoIdea(Integer selectedEstadoIdea) {
        this.selectedEstadoIdea = selectedEstadoIdea;
    }    
}
