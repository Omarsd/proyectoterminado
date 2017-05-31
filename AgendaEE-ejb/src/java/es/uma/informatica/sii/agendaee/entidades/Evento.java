/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author User
 */
@Entity
public class Evento implements Serializable {

    public enum tipoEvento{social, cultural, empresarial, otro};
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_evento;
    @Column(nullable = false)
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_fin;
   
    private String descripcion;
    @Column(nullable = false)
    private String organizador;
    @Column(nullable = false)
    private String tlf_contacto;
    private Boolean destacado;
    private String localidad;
    private String direccion;
    private String complejo;
    private Double precio;
    @Column(nullable = false)
    private Boolean visible;
    @Column(nullable = false)
    private Boolean propuesto;
    private tipoEvento tevento;
    @OneToMany
    private List<Valoracion> valoraciones;
    @ManyToMany
    private List<Usuarioreg> likes;
    
    public Evento(){
        
    }
        
    public void setId_evento(Long id_evento) {
        this.id_evento = id_evento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

   /* public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }*/

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public void setDestacado(Boolean destacado) {
        this.destacado = destacado;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setComplejo(String complejo) {
        this.complejo = complejo;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void setPropuesto(Boolean propuesto) {
        this.propuesto = propuesto;
    }

    public void setTe(tipoEvento te) {
        this.tevento = te;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public void setLikes(List<Usuarioreg> likes) {
        this.likes = likes;
    }    
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId_evento() {
        return id_evento;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    /*public Time getHora_inicio() {
        return hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }*/

    public String getDescripcion() {
        return descripcion;
    }

    public String getOrganizador() {
        return organizador;
    }

    public Boolean getDestacado() {
        return destacado;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getComplejo() {
        return complejo;
    }

    public Double getPrecio() {
        return precio;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Boolean getPropuesto() {
        return propuesto;
    }

    public tipoEvento getTe() {
        return tevento;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public List<Usuarioreg> getLikes() {
        return likes;
    }
    
    public int verLikes(){
         
         return likes.size();
         
     }
     
     public double verMedia(){
         
         double media=0.0;
         
         for (Valoracion val : valoraciones) {
             
             media += val.getPuntuacion();
             
         }
         
         return media/valoraciones.size();
     }
    
    public void setTlf_contacto(String tlf_contacto) {
        this.tlf_contacto = tlf_contacto;
    }

    public String getTlf_contacto() {
        return tlf_contacto;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id_evento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.id_evento, other.id_evento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evento{" + "id_evento=" + id_evento + '}';
    }

}
