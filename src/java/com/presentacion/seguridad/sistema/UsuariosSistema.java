/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.seguridad.sistema;

import com.negocio.servicio.albergue.AlbAlbergueServicio;
import com.negocio.servicio.seguridad.sistema.AlbPerfilServicio;
import com.negocio.servicio.seguridad.sistema.AlbPersonalAdministrativoServicio;
import com.negocio.servicio.seguridad.sistema.PerAdministrativoAlberguePerfilServicio;
import com.persistencia.albergue.AlbAlbergue;
import com.persistencia.seguridad.sistema.AlbPerfil;
import com.persistencia.seguridad.sistema.AlbPersonalAdministrativo;
import com.persistencia.seguridad.sistema.PerAdministrativoAlberguePerfil;
import com.presentacion.mensajes.MensajeEAS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;


/**
 *
 * @author Zulay
 */
@ManagedBean
@ViewScoped
public class UsuariosSistema implements Serializable {

    @ManagedProperty(value = "#{PerAdministrativoAlberguePerfilServicioImpl}")
    PerAdministrativoAlberguePerfilServicio perAdministrativoAlberguePerfilServicio;
    @ManagedProperty(value = "#{AlbPerfilServicioImpl}")
    AlbPerfilServicio albPerfilServicio;
    @ManagedProperty(value = "#{AlbAlbergueServicioImpl}")
    AlbAlbergueServicio albAlbergueServicio;
    @ManagedProperty(value = "#{AlbPersonalAdministrativoServicioImpl}")
    AlbPersonalAdministrativoServicio albPersonalAdministrativoServicio;
    private List<PerAdministrativoAlberguePerfil> listaUsuario = new ArrayList<>();
    private List<AlbPersonalAdministrativo> listaPerAdmin = new ArrayList<>();
    List<SelectItem> genListaSelected = new ArrayList<SelectItem>();
    List<SelectItem> ListaSelectedAlbergue = new ArrayList<SelectItem>();
    private PerAdministrativoAlberguePerfil perAdministrativoAlberguePerfil = new PerAdministrativoAlberguePerfil();
    private PerAdministrativoAlberguePerfil UsuarioObjects = new PerAdministrativoAlberguePerfil();
    private Long IdSeleccion, idSeleccionAlbergue;
    private MensajeEAS mensajeEAS = new MensajeEAS();

    @PostConstruct
    public void init() {
        perAdministrativoAlberguePerfil = new PerAdministrativoAlberguePerfil();
        listaUsuario.clear();
        this.listaUsuario.addAll(getPerAdministrativoAlberguePerfilServicio().listarUsuario());
        listaPerAdmin.clear();
        this.listaPerAdmin.addAll(getAlbPersonalAdministrativoServicio().listarPersonalAdmin());
    }

    public List<SelectItem> getListaPerfil() {
        this.genListaSelected = new ArrayList<SelectItem>();
        List<AlbPerfil> Lista = getAlbPerfilServicio().listarPerfil();
        for (AlbPerfil obj : Lista) {
            SelectItem selectItem = new SelectItem(obj.getPerId(), obj.getPerNombre());
            this.genListaSelected.add(selectItem);
        }
        return genListaSelected;
    }

    public List<SelectItem> getListaAlbergue() {
        this.ListaSelectedAlbergue = new ArrayList<SelectItem>();
        List<AlbAlbergue> Lista = getAlbAlbergueServicio().listarAlbergue();
        for (AlbAlbergue obj : Lista) {
            SelectItem selectItem = new SelectItem(obj.getAlbId(), obj.getAlbNombre());
            this.ListaSelectedAlbergue.add(selectItem);
        }
        return ListaSelectedAlbergue;
    }
    
    public void ex()
    {
        int suma=1+1;
        int div=suma/1;
        
    }
            

   public void guardarUsuario()
    {
        try {
            AlbPerfil obj = new AlbPerfil();
            AlbAlbergue objAlbergue = new AlbAlbergue();
            PerAdministrativoAlberguePerfil objUsu = new PerAdministrativoAlberguePerfil();
            obj.setPerId(IdSeleccion);
            objAlbergue.setAlbId(idSeleccionAlbergue);
            objUsu.setPadUsuario(UsuarioObjects.getPadUsuario());
            objUsu.setPadContrasenia(UsuarioObjects.getPadContrasenia());
            objUsu.setAlbPerfil(obj);
            objUsu.setAlbAlbergue(objAlbergue);
            objUsu.setPadEstado(1);

            if ("".equals(objUsu.getPadUsuario()) || "".equals(objUsu.getPadContrasenia())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                for (AlbPersonalAdministrativo objAux : listaPerAdmin) {
                    int cedula = UsuarioObjects.getAlbPersonalAdministrativo().getPeaCedula();
                    if (objAux.getPeaCedula() == cedula) {
                        AlbPersonalAdministrativo per = new AlbPersonalAdministrativo();
                        per.getPeaId();
                        per.setPeaId(per.getPeaId());
                        objUsu.setAlbPersonalAdministrativo(per);
                    }
                }
                getPerAdministrativoAlberguePerfilServicio().guardarUsuario(objUsu);
                mensajeEAS.Guardar();
            }
        } catch (Exception ex) {

           // LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }

    public void cancelarCargaDatos() {
        perAdministrativoAlberguePerfil = new PerAdministrativoAlberguePerfil();
    }
    
    public List<PerAdministrativoAlberguePerfil> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<PerAdministrativoAlberguePerfil> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public PerAdministrativoAlberguePerfilServicio getPerAdministrativoAlberguePerfilServicio() {
        return perAdministrativoAlberguePerfilServicio;
    }

    public void setPerAdministrativoAlberguePerfilServicio(PerAdministrativoAlberguePerfilServicio perAdministrativoAlberguePerfilServicio) {
        this.perAdministrativoAlberguePerfilServicio = perAdministrativoAlberguePerfilServicio;
    }

    public PerAdministrativoAlberguePerfil getUsuarioObjects() {
        return UsuarioObjects;
    }

    public void setUsuarioObjects(PerAdministrativoAlberguePerfil UsuarioObjects) {
        this.UsuarioObjects = UsuarioObjects;
    }

    public Long getIdSeleccion() {
        return IdSeleccion;
    }

    public void setIdSeleccion(Long IdSeleccion) {
        this.IdSeleccion = IdSeleccion;
    }

    public AlbPerfilServicio getAlbPerfilServicio() {
        return albPerfilServicio;
    }

    public void setAlbPerfilServicio(AlbPerfilServicio albPerfilServicio) {
        this.albPerfilServicio = albPerfilServicio;
    }

    public Long getIdSeleccionAlbergue() {
        return idSeleccionAlbergue;
    }

    public void setIdSeleccionAlbergue(Long idSeleccionAlbergue) {
        this.idSeleccionAlbergue = idSeleccionAlbergue;
    }

    public AlbAlbergueServicio getAlbAlbergueServicio() {
        return albAlbergueServicio;
    }

    public void setAlbAlbergueServicio(AlbAlbergueServicio albAlbergueServicio) {
        this.albAlbergueServicio = albAlbergueServicio;
    }

    public AlbPersonalAdministrativoServicio getAlbPersonalAdministrativoServicio() {
        return albPersonalAdministrativoServicio;
    }

    public void setAlbPersonalAdministrativoServicio(AlbPersonalAdministrativoServicio albPersonalAdministrativoServicio) {
        this.albPersonalAdministrativoServicio = albPersonalAdministrativoServicio;
    }

}
