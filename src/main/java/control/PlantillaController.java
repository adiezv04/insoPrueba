/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author "adrian"
 */
@Named
@ViewScoped
public class PlantillaController implements Serializable{
    
    
    public void verificarYMostrar() throws IOException{
        Object objeto = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        if(objeto == null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("../permisosInsuficientes.xhtml");
        } 
    }
}
