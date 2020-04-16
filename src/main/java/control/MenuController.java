/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Usuario;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author "adrian"
 */

@Named
@SessionScoped
public class MenuController implements Serializable{
    
    private MenuModel modelo;
    
    @PostConstruct
    public void obtenerMenu(){
        Usuario objeto = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        String tipo = "";
        
        if(objeto.getRol().getTipoUsuario().equals("P")){
            tipo="Profesor";
        }else if(objeto.getRol().getTipoUsuario().equals("A")){
            tipo="Alumno";
        }
        
        modelo = new DefaultMenuModel();
        DefaultSubMenu subMenu = new DefaultSubMenu("subMenu"+tipo);
        DefaultMenuItem item = new DefaultMenuItem("item"+tipo);
        modelo.addElement(item);
        modelo.addElement(subMenu);
    }
    
    public void destruirSesion() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
    }

    public MenuModel getModelo() {
        return modelo;
    }

    public void setModelo(MenuModel modelo) {
        this.modelo = modelo;
    }
}
