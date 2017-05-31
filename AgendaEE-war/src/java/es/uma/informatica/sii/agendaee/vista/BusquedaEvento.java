/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.vista;

import es.uma.informatica.sii.agendaee.entidades.Evento;
import es.uma.informatica.sii.agendaee.entidades.Evento.tipoEvento;
import es.uma.informatica.sii.agendaee.negocio.NegocioEv;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author gordo
 */
@Named(value = "be")
@RequestScoped
public class BusquedaEvento {

    @Inject
    EventoControlador ec;
    @EJB
    private NegocioEv negocioEv;

    //Atributos para el filtro
    private boolean factivado;//filtro activado
    private String f_nombre;
    private String f_localidad;
    private String f_tipo; //en un switch se arregla
    private Date f_fechai;
    private Date f_fechaf;

    public boolean isFactivado() {
        return factivado;
    }

    public void setFactivado(boolean factivado) {
        this.factivado = factivado;
    }

    public String getF_nombre() {
        return f_nombre;
    }

    public void setF_nombre(String f_nombre) {
        this.f_nombre = f_nombre;
    }

    public String getF_localidad() {
        return f_localidad;
    }

    public void setF_localidad(String f_localidad) {
        this.f_localidad = f_localidad;
    }

    public String getF_tipo() {
        return f_tipo;
    }

    public void setF_tipo(String f_tipo) {
        this.f_tipo = f_tipo;
    }

    public Date getF_fechai() {
        return f_fechai;
    }

    public void setF_fechai(Date f_fecha) {
        this.f_fechai = f_fecha;
    }

    public Date getF_fechaf() {
        return f_fechaf;
    }

    public void setF_fechaf(Date f_fecha) {
        this.f_fechaf = f_fecha;
    }

    public BusquedaEvento() {
    }

    private boolean filtraNombre(Evento e) {
        if (f_nombre == null || f_nombre.equals(""))//""
        {
            return true;
        } else {
            return f_nombre.equalsIgnoreCase(e.getNombre());
        }
    }

    private boolean filtraLocalidad(Evento e) {
        if (f_localidad == null)//""
        {
            return true;
        } else {
            return f_localidad.equalsIgnoreCase(e.getLocalidad());
        }
    }

    private boolean filtraFecha(Evento e) {
        if (f_fechai == null && f_fechaf == null) {//""
            return true;
        } else if (f_fechai == null) {

            return f_fechaf.after(e.getFecha_fin()) || f_fechaf.equals(e.getFecha_fin());

        } else if (f_fechaf == null) {
            return f_fechai.before(e.getFecha_inicio()) || f_fechai.equals(e.getFecha_inicio());

        } else {
            return (f_fechai.before(e.getFecha_inicio()) || f_fechai.equals(e.getFecha_inicio())) && (f_fechaf.after(e.getFecha_fin()) || f_fechaf.equals(e.getFecha_fin()));

        }
    }

    private boolean filtraTipo(Evento e) {
        tipoEvento tipo = null;

        if (f_tipo == null || f_tipo.equals("")) {
            return true;
        } else {
            switch (f_tipo) {
                case "Social":
                    tipo = tipoEvento.social;
                    break;
                case "Cultural":
                    tipo = tipoEvento.cultural;
                    break;
                case "Empresarial":
                    tipo = tipoEvento.empresarial;
                    break;
                case "Otro":
                    tipo = tipoEvento.otro;
                    break;
            }
        }
        if (tipo != null) {
            return tipo.equals(e.getTe());
        } else {
            return false;
        }
    }

    public List<Evento> filtraEventos() {
        List<Evento> buscados = new ArrayList<>();

        for (Evento e : negocioEv.getEventos()) {
            if (e.getVisible()
                    && filtraNombre(e) && filtraLocalidad(e)
                    && filtraFecha(e) && filtraTipo(e)) {
                buscados.add(e);
            }
        }

        return ec.ordenarEventos(buscados);
    }

    public String buscarEventos() {
        return "faces/busqueda.xhtml";
    }

    public String calcularFecha(Date fecha) {

        if (fecha == null) {
            return "No inicializado";
        }
        Calendar cal = Calendar.getInstance();

        cal.setTime(fecha);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);

        return cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR) + "   Hora: " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);

    }
    
     public String calcularFechaSola(Date fecha) {

        if (fecha == null) {
            return "No inicializado";
        }
        Calendar cal = Calendar.getInstance();

        cal.setTime(fecha);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);

        return cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);

    }

}
