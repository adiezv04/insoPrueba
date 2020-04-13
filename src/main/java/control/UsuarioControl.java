/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import EJB.RolFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Persona;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author "adrian"
 */

@Named
@ViewScoped
public class UsuarioControl implements Serializable{
    
    @EJB
    private RolFacadeLocal rolEJB;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    private Usuario usuario;
    private Persona persona;
    private Rol rol;
    private List<Rol> listaRoles;
    
    @PostConstruct
    public void inicio(){
        usuario = new Usuario();
        persona = new Persona();
        rol = new Rol();
        listaRoles = rolEJB.findAll();
    }
    
    public void insertarUsuario(){
        try{
            persona.setSexo("M");
            
            for(Rol r: listaRoles){
                if(r.getIdRol() == rol.getIdRol()){
                    rol = r;
                    break;
                }
            }
            usuario.setPersona(persona);
            usuario.setRol(rol);
            usuarioEJB.create(usuario);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario registrado"));
            
        }catch(Exception e){
            System.out.println("Error al insertar usuario. " + e.getMessage());
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }
    
    @Override
    public String toString() {
        return "UsuarioControl{" + " usuario=" + usuario.toString() + ", persona=" + persona.toString() + ", rol=" + rol.toString() + '}';
    }
}
