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
import javax.ejb.Local;

/**
 *
 * @author gordo
 */
@Local
public interface NegocioValoracion {
    
    public void addValoracion(Valoracion v);   
}
