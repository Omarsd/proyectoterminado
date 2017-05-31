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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gordo
 */
@Stateless
public class NegocioValoracionImpl implements NegocioValoracion {

    @PersistenceContext(unitName = "AgendaEE-EntidadesPU")
    private EntityManager em;
    
    @Override
    public void addValoracion(Valoracion v) {
        em.persist(v);
    }    
}