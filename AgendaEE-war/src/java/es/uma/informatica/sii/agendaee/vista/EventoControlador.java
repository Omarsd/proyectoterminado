/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.vista;

import java.util.Date;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import es.uma.informatica.sii.agendaee.entidades.Evento;
import es.uma.informatica.sii.agendaee.entidades.Evento.tipoEvento;
import es.uma.informatica.sii.agendaee.negocio.NegocioEv;

// fechas
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author gordo
 */
@Named(value = "ec")
@ApplicationScoped
public class EventoControlador {

    @EJB
    private NegocioEv negocioEv;

    //evento seleccionado para mostrar
    private Evento eselected;

    public Evento getEselected() {
        return eselected;
    }

    public void setEselected(Evento eselect) {
        this.eselected = eselect;
    }

    public String seleccionaEvento(Evento e) {
        eselected = e;
        return "evento.xhtml";
    }

    public List<String> getLocalidades() {
        List<String> local = new ArrayList<>();
        for (Evento e : negocioEv.getEventos()) {
            
                local.add(e.getLocalidad());
            
        }
        return local;
    }

    public List<String> getLocalidadesNoProp() {
        List<String> local = new ArrayList<>();
        for (Evento e : negocioEv.getEventos()) {
            if (e.getLocalidad()!=null && !e.getPropuesto() && e.getVisible() && !local.contains(e.getLocalidad()) && !e.getLocalidad().equals("")) {
                local.add(e.getLocalidad());
            }
        }
        return local;
    }

    public List<Date> getFechasNoProp() {
        List<Date> date = new ArrayList<>();
        for (Evento e : negocioEv.getEventos()) {
            if (!e.getPropuesto() && e.getVisible()) {
                date.add(e.getFecha_inicio());
            }
        }
        return date;
    }

    /**
     * Creates a new instance of EventoControlador
     */
    public EventoControlador() {
    }

    /*Devuelve todos los eventos ordenados en el siguiente orden
        Destacados
        Mayor número likes
        Fecha
    
      Descarta los eventos pasados*/
    public List<Evento> verEventos() {
        List<Evento> eventos = new ArrayList<>();
        Date date = new Date();
        
        for (Evento e : negocioEv.getEventos()) {
            if (e.getVisible() && !e.getPropuesto() && !e.getFecha_inicio().before(date)) {
                eventos.add(e);
            }
        }
        return ordenarEventos(eventos);

    }

    public List<Evento> eventosPropuestos() {
        List<Evento> propuestos = new ArrayList<>();
        for (Evento e : negocioEv.getEventos()) {
            if (e.getPropuesto()) {
                propuestos.add(e);
            }
        }
        return propuestos;
    }

    /*Periodista da el visto bueno a un evento para ser publicado*/
    public String validarEvento() {

        eselected.setPropuesto(false);
        eselected.setVisible(true);
        negocioEv.modificarEvento(eselected);

        return "proponer-evento.xhtml";
    }

    public String eliminarEvento() {
        eselected.setVisible(Boolean.FALSE);
        negocioEv.eliminarEvento(eselected);
        eselected = null;
        return "index.xhtml";

    }

    public String eliminarEventoPropuesto() {
        negocioEv.eliminarEventoP(eselected);
        eselected = null;
        return "proponer-evento.xhtml";
    }

 

    /*Para ordenar eventos*/
   public List<Evento> ordenarEventos(List<Evento> des){
    Collections.sort(des, new Comparator<Evento> (){
            @Override
            public int compare(Evento ev1, Evento ev2){
                if (ev1.getDestacado() && !ev2.getDestacado())
                    return -1;
                else if (!ev1.getDestacado() && ev2.getDestacado())
                    return 1;
                else if (ev1.getLikes().size()>ev2.getLikes().size())
                    return -1;
                else if (ev1.getLikes().size()<ev2.getLikes().size())
                    return 1;
                else  if (ev1.getFecha_inicio().before(ev2.getFecha_inicio()))
                    return -1;
                else if (ev1.getFecha_inicio().after(ev2.getFecha_inicio()))
                    return 1;
                else
                    return 0;
            }
        });
    
        return des;
    }
    public String modificarEvento() {
        negocioEv.modificarEvento(eselected);
        return "evento.xhtml";
    }

    // FECHAS
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

}
