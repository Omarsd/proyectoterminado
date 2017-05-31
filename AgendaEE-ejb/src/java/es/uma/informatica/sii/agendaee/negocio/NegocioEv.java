/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.negocio;

import es.uma.informatica.sii.agendaee.entidades.Evento;
import es.uma.informatica.sii.agendaee.entidades.Valoracion;
import java.util.List;

import javax.ejb.Local;

/**
 *
 * @author francis
 */
@Local
public interface NegocioEv {

    public void crearEvento(Evento e);
    public void modificarEvento(Evento e);
    public void eliminarEvento(Evento e);
    public void eliminarEventoP(Evento e);

    public List<Evento> getEventos();   

}