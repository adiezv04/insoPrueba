/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Menu;
import modelo.Usuario;

/**
 *
 * @author "adrian"
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {

    @PersistenceContext(unitName = "publicacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }
    
    @Override
    public List <Menu> menuPersonalizado(Usuario us){
        String consulta = "FROM Menu m WHERE m.idRol = :param1";
        Query query = em.createQuery(consulta);
        query.setParameter("param1", us.getRol().getIdRol());
        
        List<Menu> resultado = query.getResultList();
        return resultado;
    }
    
}
