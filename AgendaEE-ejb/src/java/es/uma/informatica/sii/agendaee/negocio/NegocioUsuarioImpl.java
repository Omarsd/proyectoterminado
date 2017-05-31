/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.negocio;

import es.uma.informatica.sii.agendaee.entidades.Usuarioreg;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gordo
 */
@Stateless
public class NegocioUsuarioImpl implements NegocioUsuario {

    @PersistenceContext(unitName = "AgendaEE-EntidadesPU")
    private EntityManager em;

    @Override
    public void crearUsuario(Usuarioreg u) {
        if (getUsuarios().isEmpty()) {
            u.setRol(Usuarioreg.tipoRol.administrador);
        }
        em.persist(u);
    }

    @Override
    public void modificarUsuario(Usuarioreg u) {
        em.merge(u);
    }

    @Override
    public void eliminarUsuario(Usuarioreg u) {
        em.merge(u);
    }

    @Override
    public Usuarioreg refrescarUsuario(Usuarioreg u){
        Usuarioreg user = em.find(Usuarioreg.class,u.getId_usuario());
       return  user;
        
    }

    @Override
    public List<Usuarioreg> getUsuarios() {;
        return em.createQuery("select u from Usuarioreg u").getResultList();
    }

}
