/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.negocio;

import es.uma.informatica.sii.agendaee.entidades.Evento;
import es.uma.informatica.sii.agendaee.entidades.Usuarioreg;
import es.uma.informatica.sii.agendaee.entidades.Valoracion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author francis
 */
@Stateless
public class NegocioEvImpl implements NegocioEv {

    @PersistenceContext(unitName = "AgendaEE-EntidadesPU")
    private EntityManager em;

    
    @Override
    public List<Evento> getEventos(){
        return em.createQuery("select e from Evento e").getResultList();
    }

    @Override     
     public void crearEvento(Evento e){
        em.persist(e);
    }
     
    @Override    
    public void eliminarEvento(Evento e){
                em.merge(e);
    }
    @Override
    public void eliminarEventoP(Evento e){
                em.remove(em.merge(e));
    }

    @Override
    public void modificarEvento(Evento e) {
        em.merge(e);
    }

}
