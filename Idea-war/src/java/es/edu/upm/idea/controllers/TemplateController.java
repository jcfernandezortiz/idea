
package es.edu.upm.idea.controllers;


import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author mfreire
 */
@Named("templateController")
@SessionScoped
public class TemplateController implements Serializable {
    private String lenged = "Details 123:";

    public TemplateController() {
        
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
