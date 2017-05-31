/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.agendaee.vista;

//import es.uma.informatica.sii.jsf.autenticacion.modelo.Usuario;
//import es.uma.informatica.sii.jsf.autenticacion.modelo.Usuario.Rol;

import es.uma.informatica.sii.agendaee.negocio.NegocioUsuario;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author francis
 */


@Named(value = "login")
@RequestScoped
public class Login {

    /*
    private String usuario;
    private String contrasenia;
    private List<Usuario> usuarios;
    */
    
    @Inject
    private UsuarioControlador ctrl;
    @EJB
    private NegocioUsuario negocioUs;
    
    private String nickname;

    public String getNickname() {
        return nickname;
    }
     private String contraseña;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    
    /**
     * Creates a new instance of Login
     */
    public Login() {}

    
    public void setCtrl(UsuarioControlador ctrl) {
        this.ctrl = ctrl;
    }

    

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public UsuarioControlador getCtrl() {
        return ctrl;
    }

    

    public String getContraseña() {
        return contraseña;
    }
   
    
    public String salir(){
        ctrl.setUser(null);
      return "log-in.xhtml";
    }
    
    
    public String entrar(){
        
        FacesContext ctx = FacesContext.getCurrentInstance();

        if(Login.this.getNickname()==null || Login.this.getContraseña()==null){
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña vacio", "Usuario o contraseña vacio"));
        }
        
        int i=0;
        boolean encontrado = false;
        
        
        
        while(!encontrado && i<negocioUs.getUsuarios().size()){
        
            if(negocioUs.getUsuarios().get(i).getNickname().equalsIgnoreCase(nickname)  &&  negocioUs.getUsuarios().get(i).getContraseña().equals(contraseña) && !negocioUs.getUsuarios().get(i).isBorrado()){
                  
                encontrado = true;
                
                ctrl.setUser(negocioUs.getUsuarios().get(i));
                return "index.xhtml";
                
            }   
            i++;
        }
        
       
        
        // Implementar este método
        
      
          ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en usuario o contraseña", "Error en usuario o contraseña"));
                           return null;

        
    }
}
