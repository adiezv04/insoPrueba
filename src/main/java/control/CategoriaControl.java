/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import EJB.CategoriaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categoria;

/**
 *
 * @author "adrian"
 */

@Named
@ViewScoped
public class CategoriaControl implements Serializable{
    
    private List<Categoria> listaCategorias;
    private Categoria cat;
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @PostConstruct
    public void inicio(){
        cat = new Categoria();
        listaCategorias = categoriaEJB.findAll();
    }
    
    public void insertaCategoria(){
        try{
            categoriaEJB.create(cat);
        }catch(Exception e){
            System.out.println("Error al insertar categoría. " + e.getMessage());
        }
    }
    
    public void eliminarCategoria(){
        try{
            for(Categoria c: listaCategorias){
                if(c.getIdCategoria()==cat.getIdCategoria()){
                    cat = c;
                    break;
                }
            }
           categoriaEJB.remove(cat);
        }catch(Exception e){
            System.out.println("Error al eliminar categoría. " + e.getMessage());
        }
    }
    
    public void modificarCategoria(){
        try{
            String nuevoNombre = cat.getNombre();
            for(Categoria c: listaCategorias){
                if(c.getIdCategoria()==cat.getIdCategoria()){
                    cat = c;
                    break;
                }
            }
            
            cat.setNombre(nuevoNombre);
            categoriaEJB.edit(cat);
        }catch(Exception e){
            System.out.println("Error al modificar categoría. " + e.getMessage());
        }
    }
    
    public Categoria getCat(){
        return cat;
    }
    
    public void setCat(Categoria cat){
        this.cat = cat;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
    
}
