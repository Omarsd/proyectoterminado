/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.negocio;

import es.uma.informatica.sii.agendaee.entidades.Usuarioreg;
import java.util.List;


import javax.ejb.Local;

@Local
public interface NegocioUsuario {

    public void crearUsuario(Usuarioreg e);
    public void modificarUsuario(Usuarioreg e);
    public void eliminarUsuario(Usuarioreg e);
    public List<Usuarioreg> getUsuarios();
    public Usuarioreg refrescarUsuario(Usuarioreg u);

}
