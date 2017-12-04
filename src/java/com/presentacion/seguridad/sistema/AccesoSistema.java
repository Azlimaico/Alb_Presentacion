/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.seguridad.sistema;

import com.negocio.servicio.seguridad.sistema.PerAdministrativoAlberguePerfilServicio;
import com.persistencia.seguridad.sistema.PerAdministrativoAlberguePerfil;
import com.presentacion.general.calculo.Util;
import com.presentacion.mensajes.MensajeEAS;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.jboss.logging.Logger;

@ManagedBean
@SessionScoped
public class AccesoSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(AccesoSistema.class);
    @ManagedProperty(value = "#{PerAdministrativoAlberguePerfilServicioImpl}")
    PerAdministrativoAlberguePerfilServicio perAdministrativoAlberguePerfilServicioImpl;

    private boolean logeado = false;

    PerAdministrativoAlberguePerfil Usuario = new PerAdministrativoAlberguePerfil();
    
    public PerAdministrativoAlberguePerfil objectUsuarioSession = new PerAdministrativoAlberguePerfil();
    
    private MensajeEAS mensajeEAS = new MensajeEAS();
    private int tiempoSesion ;

    public String accederSistema() {
//        RequestContext context = RequestContext.getCurrentInstance();
        objectUsuarioSession = new PerAdministrativoAlberguePerfil();
        if (getUsuario()!= null) {
            objectUsuarioSession = getPerAdministrativoAlberguePerfilServicioImpl().listarUsuContra(getUsuario().getPadUsuario(), getUsuario().getPadContrasenia());
            if (objectUsuarioSession != null) {
//            logeado = true;
                // get Http Session and store username
                HttpSession session = Util.getSession();
                session.setAttribute("usu_nombre", objectUsuarioSession);
                session.setMaxInactiveInterval(2400);//40 minutos de sesion
                setTiempoSesion(session.getMaxInactiveInterval()*1000);//asigna 40 minutos de sesion

                return "home";
            } else {
//            logeado = false;
                mensajeEAS.errorLogin();
                return "Login";
            }
        } else {
            return "Login";
        }

//        context.addCallbackParam("estaLogeado", logeado);
//        if (logeado) {
//            context.addCallbackParam("view", "Layout.xhtml");
//        }
    }

    public String logout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        return "Login";
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
//                .getExternalContext().getSession(false);
//        session.invalidate();
//        logeado = false;
    }

    public PerAdministrativoAlberguePerfilServicio getPerAdministrativoAlberguePerfilServicioImpl() {
        return perAdministrativoAlberguePerfilServicioImpl;
    }

    public void setPerAdministrativoAlberguePerfilServicioImpl(PerAdministrativoAlberguePerfilServicio perAdministrativoAlberguePerfilServicioImpl) {
        this.perAdministrativoAlberguePerfilServicioImpl = perAdministrativoAlberguePerfilServicioImpl;
    }

   
    public PerAdministrativoAlberguePerfil getUsuario() {
        return Usuario;
    }

    public void setUsuario(PerAdministrativoAlberguePerfil Usuario) {
        this.Usuario = Usuario;
    }    

    public boolean estaLogeado() {
        return logeado;
    }

    public int getTiempoSesion() {
        return tiempoSesion;
    }

    public void setTiempoSesion(int tiempoSesion) {
        this.tiempoSesion = tiempoSesion;
    }

   
}
