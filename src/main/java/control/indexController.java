/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;

/**
 *
 * @author "adrian"
 */

@Named
@ViewScoped
public class indexController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
            
    
    @PostConstruct
    public void inicio(){
        usuario = new Usuario();

    }
    
    public String verificarUsuario(){
        Usuario us = usuarioEJB.verificarUsuario(usuario);
        if(us == null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", null);
            return "permisosInsuficientes.xhtml?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
            return "private/principal.xhtml?faces-redirect=true";
        }
    }   
    
    public Usuario getUsuario(){
        return usuario;
    }
    
}
