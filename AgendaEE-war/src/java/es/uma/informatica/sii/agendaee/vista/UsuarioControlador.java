/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.vista;

import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import es.uma.informatica.sii.agendaee.entidades.Usuarioreg;
import es.uma.informatica.sii.agendaee.entidades.Usuarioreg.tipoRol;
import es.uma.informatica.sii.agendaee.negocio.NegocioUsuario;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author gordo
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

    @EJB
    private NegocioUsuario negocioUs;

    Usuarioreg user;
    Usuarioreg usuarioSelected;

    public Usuarioreg getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuarioreg usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    public Usuarioreg getUser() {
        return user;
    }

    public void setUser(Usuarioreg user) {
        this.user = user;
    }

    /**
     * Creates a new instance of UsuarioControlador
     */
    public UsuarioControlador() {
    }

    public String modificarUsuario() {
        tipoRol rolActual = user.getRol();
        FacesContext ctx = FacesContext.getCurrentInstance();

        if (rolActual == tipoRol.administrador) {
            if (estaEnBD(usuarioSelected)) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pueden repetir Nickname ni e-mail", "No se pueden repetir Nickname ni e-mail"));
                usuarioSelected = negocioUs.refrescarUsuario(usuarioSelected);
                return "modificar-datos.xhtml";
            } else {
                tipoRol rolSet = usuarioSelected.getRol();

                negocioUs.modificarUsuario(usuarioSelected);
                if (rolSet == tipoRol.periodista) {
                    return "mostrar-periodistas.xhtml";
                } else {
                    return "mostrar-usuarios.xhtml";
                }
            }
        } else {
            if (estaEnBD(user)) {
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pueden repetir Nickname ni e-mail", "No se pueden repetir Nickname ni e-mail"));
                user = negocioUs.refrescarUsuario(user);
                return "modificar-datos.xhtml";
            } else {
                negocioUs.modificarUsuario(user);
                return "modificar-datos.xhtml";
            }

        }
    }

    private boolean estaEnBD(Usuarioreg user) {

        int i = 0;
        boolean encontrado = false;
        List<Usuarioreg> lista = negocioUs.getUsuarios();

        while (!encontrado && i < lista.size()) {
            Usuarioreg aux = lista.get(i);
            if (!lista.get(i).equals(user)) {
                if (aux.getNickname().equalsIgnoreCase(user.getNickname())) {

                    encontrado = true;

                }
            }
            i++;
        }
        return encontrado;

    }

    public List<Usuarioreg> muestraUsuarios() {
        List<Usuarioreg> p = new ArrayList<>();
        List<Usuarioreg> todos = negocioUs.getUsuarios();

        for (Usuarioreg u : todos) {
            if (u.getRol().equals(tipoRol.normal) && !u.isBorrado()) {
                p.add(u);
            }
        }

        return p;
    }

    public List<Usuarioreg> muestraPeriodistas() {
        List<Usuarioreg> p = new ArrayList<>();
        List<Usuarioreg> todos = negocioUs.getUsuarios();

        for (Usuarioreg u : todos) {
            if (u.getRol().equals(tipoRol.periodista) && !u.isBorrado()) {
                p.add(u);
            }
        }
        return p;
    }

    public String seleccionaUsuario(Usuarioreg p) {
        usuarioSelected = p;
        return "modificar-datos.xhtml";
    }

    public String borrarUsuario() {
        tipoRol rolActual = user.getRol();
        if (rolActual == tipoRol.administrador) {
            usuarioSelected.setBorrado(Boolean.TRUE);
            tipoRol rolRemoved = usuarioSelected.getRol();
            negocioUs.eliminarUsuario(usuarioSelected);
            usuarioSelected = null;
            if (rolRemoved == tipoRol.periodista) {
                return "mostrar-periodistas.xhtml";
            } else {
                return "mostrar-usuarios.xhtml";
            }
        } else {
            user.setBorrado(Boolean.TRUE);
            negocioUs.eliminarUsuario(user);
            user = null;
            return "index.xhtml";
        }
    }

    public String vamosAModificarDatos() {
        if (user != null && user.getRol() == tipoRol.administrador) {
            usuarioSelected = null;
        }
        return "modificar-datos.xhtml";
    }

    public String asteriscoContraseña() {
        String t = user.getContraseña();
        int length = t.toCharArray().length;
        StringBuilder db = new StringBuilder();

        for (int i = 0; i < length; i++) {
            db.append("*");
        }

        return db.toString();
    }

    public String asteriscoContraseñaP() {
        String t = usuarioSelected.getContraseña();
        int length = t.toCharArray().length;
        StringBuilder db = new StringBuilder();

        for (int i = 0; i < length; i++) {
            db.append("*");
        }

        return db.toString();
    }

}
