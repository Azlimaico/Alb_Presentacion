/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.mensajes;

import com.presentacion.parametros.sistema.ParametrosFormulario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mensajeEAS")
@ViewScoped
public class MensajeEAS implements Serializable {

    public static final String info = "Registros actualizados correctamente.";
    public static final String infoDuplicarNodoHijo = "Antes de guardar, recuerde cambiar los datos para la nueva unidad";
    public static final String infoCrearNodoHijo = "Ingrese los datos para una nueva unidad";
    public static final String infoCrearComandos = "Recuerde crear unidades Comando. Ingrese sus datos en el formulario";
    public static final String infoCrearBrigadas = "Recuerde crear unidades Brigada. Ingrese sus datos en el formulario";
    public static final String infoCrearBatallones = "Recuerde crear unidades Batallón. Ingrese sus datos en el formulario";
    public static final String infoCrearCompañias = "Recuerde crear unidades Compañía. Ingrese sus datos en el formulario";
    public static final String infoCrearPelotones = "Recuerde crear unidades Pelotón. Ingrese sus datos en el formulario";
    public static final String infoJuegoCreado = "Juego creado exitosamente";

    public static final String cancelar = "Se ha cancelado las acciones pendientes";
    public static final String errorLogin = "Error al entrar al sistema, credenciales incorrectas o su cuenta ha sido desactivada. Consulte con el Administrador";
    public static final String infoTemporal = "Registro agregado correctamente.";
    public static final String error = "Error al actualizar los registros. Consulte con el Administrador";
    public static final String errorGenerarIconos = "Error al generar los iconos. Consulte con el Administrador";
    public static final String errorDatosDuplicados = "Error al actualizar. Ya existen datos con éste mismo nombre.";
    public static final String errorCopia = "No se puede realizar una copia de ésta unidad";
    public static final String errorSeleccion = "Error al guardar, seleccione una unidad.";
    private static final String errorSeleccionPermiso = "Error al guardar, seleccione una unidad y un usuario";
    private static final String errorSeleccionCabecera = "Debe seleccionar el Comando Conjunto";

    private static final String errorUsuarioAsignado = "Error al asignar permiso, el usuario ya se encuentra asignado";
    public static final String errorSinDatos = "Error al guardar, no existen datos que procesar.";
    public static final String errorLlenarDatos = "Error al guardar, se necesita llenar todos los datos";
    public static final String errorInactivar = "No es posible inactivar esta Unidad, contiene unidades hijas";
    public static final String Guardar = "Guardado Exitoso.";
    public static final String Eliminar="Elemento Eliminado";
    public static final String Modificar="Elemento Modificado";
    public static final String errorDuplicado ="!Registro ya Existente!";
    
    private Integer tiempoDuracionMensaje, tiempoDuracionMensajet;
    private Integer tiempoDuracionMensajef = new Integer(0);

    public void info(Boolean sms) {
        FacesMessage message = null;
        if (sms) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, info, null);

        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoTemporal, null);

        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void mensajeValidaCampos(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void error() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorGenererarIconos() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorGenerarIconos, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorLogin() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorLogin, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorSiDatos() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorSinDatos, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorLlenarDatos() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorLlenarDatos, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void errorDublicado(){
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, errorDuplicado, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoCrearComandos() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoCrearComandos, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public void infoJuegoCreado() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoJuegoCreado, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoCrearNodoHijo() {
//     
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", infoCrearNodoHijo));

    }

    public void warn() {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", infoDuplicarNodoHijo));
    }

    public void infoCrearBrigadas() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoCrearBrigadas, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoCrearBatallones() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoCrearBatallones, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoCrearCompañias() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoCrearCompañias, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoCrearPelotones() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoCrearPelotones, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoCopia() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorCopia, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorSeleccion() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorSeleccion, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorSeleccionCabecera() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorSeleccionCabecera, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorSeleccionPermiso() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorSeleccionPermiso, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorUsuarioAsignado() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorUsuarioAsignado, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorDatosDuplicados() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorDatosDuplicados, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorInactivar() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorInactivar, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void cancelar() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, cancelar, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void Guardar() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, Guardar, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void Eliminar() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, Eliminar, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void Modificar() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, Modificar, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Integer getTiempoDuracionMensaje() {
        tiempoDuracionMensaje = tiempoDuracionMensajet;
        return tiempoDuracionMensaje;
    }

    public Integer getTiempoDuracionMensajef() {
        return tiempoDuracionMensajef;
    }

    public void setTiempoDuracionMensajef(Integer tiempoDuracionMensajef) {
        this.tiempoDuracionMensajef = tiempoDuracionMensajef;
    }

}
