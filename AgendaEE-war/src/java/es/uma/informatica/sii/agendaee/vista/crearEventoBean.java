/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.vista;

import es.uma.informatica.sii.agendaee.entidades.Evento;
import es.uma.informatica.sii.agendaee.entidades.Evento.tipoEvento;
import es.uma.informatica.sii.agendaee.entidades.Usuarioreg;
import es.uma.informatica.sii.agendaee.entidades.Usuarioreg.tipoRol;
import es.uma.informatica.sii.agendaee.entidades.Valoracion;
import es.uma.informatica.sii.agendaee.negocio.NegocioEv;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author gordo
 */
@Named(value = "newEv")
@RequestScoped
public class crearEventoBean {

    @EJB
    private NegocioEv negocioEv;

    @Inject
    private UsuarioControlador us;

    //private Long id_evento;
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_fin;
    //private Time hora_inicio;
    //private Time hora_fin;
    private String descripcion;
    private String organizador;
    private String tlf_contacto;
    //private Boolean destacado;
    private String localidad;
    private String direccion;
    private String complejo;
    private double precio;
    //private Boolean visible;
    //private Boolean propuesto;
    private tipoEvento tevento;

    private List<Valoracion> valoraciones;

    private List<Usuarioreg> likes;

    public crearEventoBean() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public String getTlf_contacto() {
        return tlf_contacto;
    }

    public void setTlf_contacto(String tlf_contacto) {
        this.tlf_contacto = tlf_contacto;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComplejo() {
        return complejo;
    }

    public void setComplejo(String complejo) {
        this.complejo = complejo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public tipoEvento getTevento() {
        return tevento;
    }

    public void setTevento(tipoEvento tevento) {
        this.tevento = tevento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public List<Usuarioreg> getLikes() {
        return likes;
    }

    public void setLikes(List<Usuarioreg> likes) {
        this.likes = likes;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String creaEvento() {

        FacesContext ctx = FacesContext.getCurrentInstance();

        if (fecha_inicio == null || fecha_fin == null || fecha_inicio.after(fecha_fin)) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fechas incorrectas", "fechas incorrectas"));
            return "crear-evento.xhtml";
        }

        Evento e = new Evento();
        e.setNombre(nombre);
        e.setComplejo(complejo);
        e.setDescripcion(descripcion);
        e.setDestacado(Boolean.FALSE);
        e.setDireccion(direccion);
        e.setLocalidad(localidad);
        e.setOrganizador(organizador);
        e.setPrecio(precio);
        e.setTe(tevento);
        e.setTlf_contacto(tlf_contacto);
        e.setValoraciones(new ArrayList<Valoracion>());
        e.setLikes(new ArrayList<Usuarioreg>());
        e.setFecha_inicio(fecha_inicio);
        e.setFecha_fin(fecha_fin);

        if (us.getUser()==null || us.getUser().getRol()==tipoRol.normal){
            e.setVisible(Boolean.FALSE);
            e.setPropuesto(Boolean.TRUE);
        }
        else{
            e.setVisible(Boolean.TRUE);
            e.setPropuesto(Boolean.FALSE);
        }
        
        negocioEv.crearEvento(e);

        return "index.xhtml";
    }
}
