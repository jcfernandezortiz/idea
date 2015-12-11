
package es.edu.upm.idea.controllers;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

/**
 *
 * @author mfreire
 */
@Named("templateController")
@SessionScoped
public class TemplateController implements Serializable {
    private String lenged = "Details 123:";
    private ArrayList<String> tabsNames;

    public TemplateController() {
        tabsNames = new ArrayList<>();
        tabsNames.add("Lista");
        tabsNames.add("Otra cosa");
        tabsNames.add("Tres");
        
    }
    
    public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
    public String listIdeas(){
        return "/pages/idea/OtherList.xhtml";
    }
    
    public String createIdea(){
        return "/pages/idea/Create.xhtml";
    }
    
    public String myIdeas(){
        return "/pages/idea/List.xhtml";
    }
         
    public void onTabClose(TabCloseEvent event) {
        //FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getLenged() {
        FacesContext fc = FacesContext.getCurrentInstance();       
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
                String text = params.get("action_label");
                if (text != null){
                    return text;
                }else{
                    return "Ideas:";
                }
    }
    
    public void setLenged(String lenged) {
        this.lenged = lenged;
    }
    
}
