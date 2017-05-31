/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.vista;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import es.uma.informatica.sii.agendaee.entidades.Valoracion;
import es.uma.informatica.sii.agendaee.negocio.NegocioEv;
import es.uma.informatica.sii.agendaee.negocio.NegocioUsuario;
import es.uma.informatica.sii.agendaee.negocio.NegocioValoracion;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author gordo
 */
@Named(value = "vc")
@RequestScoped

public class ValoracionControlador {

    @EJB
    private NegocioValoracion negocioVal;
    @EJB
    private NegocioUsuario negocioUs;
    @EJB
    private NegocioEv negocioEv;

    @Inject
    UsuarioControlador user;

    @Inject
    EventoControlador ev;

    private String comentario;
    private int puntuacion;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void addValoracion(Valoracion va) {
        ev.getEselected().getValoraciones().add(va);
        negocioVal.addValoracion(va);
        negocioEv.modificarEvento(ev.getEselected());
    }

    public List<Valoracion> verValoraciones() {
        return ev.getEselected().getValoraciones();
    }

    public String creaValoracion() {

        FacesContext ctx = FacesContext.getCurrentInstance();

        if (haValorado()) {

            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya ha valorado", "Ya ha valorado"));

        } else {

            Valoracion val = new Valoracion();

            val.setAutor(user.getUser());
            val.setComentario(comentario);
            val.setPuntuacion(puntuacion);

            addValoracion(val);
        }

        return "evento.xhtml";
    }

    public boolean haValorado() {

        boolean encontrado = false;
        int i = 0;

        if (ev.getEselected().getValoraciones().isEmpty() || ev.getEselected().getValoraciones() == null) {
            return encontrado;
        } else {

            while (!encontrado && i < ev.getEselected().getValoraciones().size()) {

                if (ev.getEselected().getValoraciones().get(i).getAutor().equals(user.getUser())) {
                    encontrado = true;
                }
                i++;

            }
        }

        return encontrado;

    }

    public int verLikes() {

        return ev.getEselected().getLikes().size();

    }

    public String verMedia() {

        double media = 0.0;

        for (Valoracion val : ev.getEselected().getValoraciones()) {

            media += val.getPuntuacion();

        }

        media /= ev.getEselected().getValoraciones().size();

        if (Double.isNaN(media)) {
            return "No hay valoraciones";
        }
        media = Math.round(media * 10) / 10;
        String med = Double.toString(media);
        char[] m = med.toCharArray();

        if (m[2] == '0') {
            med = med.substring(0, med.length() - 2);
        } 
        

        return med + "/5";
        
    }


    public String doLike() {

        if (haLike()) {
            ev.getEselected().getLikes().remove(user.getUser());
            negocioEv.modificarEvento(ev.getEselected());
        } else {
            ev.getEselected().getLikes().add(user.getUser());
            negocioEv.modificarEvento(ev.getEselected());
        }

        return "evento.xhtml";
    }

    public boolean haLike() {

        boolean encontrado = false;
        int i = 0;

        if (ev.getEselected().getLikes().isEmpty() || ev.getEselected().getLikes() == null) {
            return encontrado;
        } else {

            while (!encontrado && i < ev.getEselected().getLikes().size()) {
                if (ev.getEselected().getLikes().get(i).equals(user.getUser())) {
                    encontrado = true;
                }
                i++;

            }
        }

        return encontrado;

    }

}
