/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.vista;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import es.uma.informatica.sii.agendaee.entidades.Usuarioreg;
import es.uma.informatica.sii.agendaee.negocio.NegocioUsuario;
import javax.ejb.EJB;

/**
 *
 * @author Omars
 */
@Named(value = "signup")
@RequestScoped
public class Signup {

    /**
     * Creates a new instance of Signup
     */
    @EJB
    private NegocioUsuario negocioUs;

    private String nickname;
    private String contraseña;
    private String contraseña2;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

   

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getContraseña2() {
        return contraseña2;
    }

    public void setContraseña2(String contraseña2) {
        this.contraseña2 = contraseña2;
    }

    public Signup() {
    }

    public String crearUsuario() {

        FacesContext ctx = FacesContext.getCurrentInstance();

        if ("".equals(Signup.this.getNickname())) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario vacio", "Usuario vacio"));
            return "sign-up.xhtml";
        }

        if (!Signup.this.getContraseña().equals(Signup.this.getContraseña2())) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseñas no coinciden", "Contraseñas no coinciden"));
            return "sign-up.xhtml";
        }

     

        int i = 0;
        boolean encontrado = false;

        while (!encontrado && i < negocioUs.getUsuarios().size()) {

            if (negocioUs.getUsuarios().get(i).getNickname().equalsIgnoreCase(nickname)) {

                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Usuario ya se encuentra en la base de datos", "El Usuario ya se encuentra en la base de datos"));
                return "sign-up.xhtml";

            }
            i++;
        }

        Usuarioreg u = new Usuarioreg();
        u.setNickname(nickname);
        u.setContraseña(contraseña);
        u.setRol(Usuarioreg.tipoRol.normal);
        u.setBorrado(Boolean.FALSE);
        negocioUs.crearUsuario(u);

        return "index.xhtml";
    }

    public String crearPeriodista() {

        FacesContext ctx = FacesContext.getCurrentInstance();

        if ("".equals(Signup.this.getNickname())) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario vacio", "Usuario vacio"));
            return "sign-up-periodista.xhtml";
        }

        if (!Signup.this.getContraseña().equals(Signup.this.getContraseña2())) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Contraseñas no coinciden", "Contraseñas no coinciden"));
            return "sign-up-periodista.xhtml";
        }

        

        int i = 0;
        boolean encontrado = false;

        while (!encontrado && i < negocioUs.getUsuarios().size()) {

            if (negocioUs.getUsuarios().get(i).getNickname().equalsIgnoreCase(nickname)) {

                encontrado = true;
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Usuario ya se encuentra en la base de datos", "El Usuario ya se encuentra en la base de datos"));
                return "sign-up-periodista.xhtml";

            }
            i++;
        }

        Usuarioreg u = new Usuarioreg();
        u.setNickname(nickname);
        u.setContraseña(contraseña);
        u.setRol(Usuarioreg.tipoRol.periodista);
        u.setBorrado(Boolean.FALSE);
        negocioUs.crearUsuario(u);

        return "mostrar-periodistas.xhtml";
    }
}
