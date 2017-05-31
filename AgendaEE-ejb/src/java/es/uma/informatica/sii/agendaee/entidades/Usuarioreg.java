/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class Usuarioreg implements Serializable {

    public enum tipoRol{normal, periodista, administrador};

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;
    @Column(nullable = false, unique=true)    
    private String nickname;
    //@Column(nullable = false)
    private String nombre;
    //@Column(nullable = false)
    private String apellidos;
    private String dni;
    private String direccion;
    private String telefono;
    @Column(nullable = false)
    private String contraseña;
    private String email;
    private Date fecha_nacimiento;
    @Column(nullable = false)
    private tipoRol rol;    
    @Column(nullable = false)
    private Boolean borrado;
    
    public Usuarioreg(){
    
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getEmail() {
        return email;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public tipoRol getRol() {
        return rol;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setRol(tipoRol rol) {
        this.rol = rol;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_usuario != null ? id_usuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarioreg)) {
            return false;
        }
        Usuarioreg other = (Usuarioreg) object;
        if ((this.id_usuario == null && other.id_usuario != null) || (this.id_usuario != null && !this.id_usuario.equals(other.id_usuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "agenda.Usuarioreg[ id=" + id_usuario + " ]";
    }
    
}
