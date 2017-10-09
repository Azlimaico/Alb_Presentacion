/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.sistema.general;

import com.negocio.servicio.general.sistema.AlbEstadoCivilServicio;
import com.negocio.servicio.general.sistema.AlbFuerzaServicio;
import com.negocio.servicio.general.sistema.AlbProfesionServicio;
import com.negocio.servicio.general.sistema.AlbRangoServicio;
import com.negocio.servicio.seguridad.sistema.AlbPersonalAdministrativoServicio;
import com.persistencia.general.sistema.AlbEstadoCivil;
import com.persistencia.general.sistema.AlbFuerza;
import com.persistencia.general.sistema.AlbProfesion;
import com.persistencia.general.sistema.AlbRango;
import com.persistencia.seguridad.sistema.AlbPersonalAdministrativo;
import com.presentacion.mensajes.MensajeEAS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.jboss.logging.Logger;

/**
 *
 * @author Zulay
 */
@ManagedBean
@ViewScoped
public class PersonalAdministrativo implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(PersonalAdministrativo.class);
    @ManagedProperty(value = "#{AlbPersonalAdministrativoServicioImpl}")
    AlbPersonalAdministrativoServicio albPersonalAdministrativoServicio;
    private MensajeEAS mensajeEAS = new MensajeEAS();
    private List<AlbPersonalAdministrativo> listaAlbPersonalAdministrativo = new ArrayList<>();
    private Boolean guardadoCabecera = false;
    @ManagedProperty(value = "#{AlbEstadoCivilServicioImpl}")
    AlbEstadoCivilServicio albEstadoCivilServicio;
    @ManagedProperty(value = "#{AlbProfesionServicioImpl}")
    AlbProfesionServicio albProfesionServicio;
    @ManagedProperty(value = "#{AlbFuerzaServicioImpl}")
    AlbFuerzaServicio albFuerzaServicio;
    @ManagedProperty(value = "#{AlbRangoServicioImpl}")
    AlbRangoServicio albRangoServicio;

    private AlbPersonalAdministrativo albPersonalAdministrativo = new AlbPersonalAdministrativo();
    private List<AlbPersonalAdministrativo> listaTempAlbPerAdmnin = new ArrayList<>();

    private Long IdSeleccionEstadoCivil;
    List<SelectItem> listarEstadoCivil = new ArrayList<SelectItem>();
    private List<AlbEstadoCivil> listaEstadoCivil = new ArrayList<>();

    private Long IdSeleccionProfesion;
    List<SelectItem> listarProfesion = new ArrayList<SelectItem>();
    private List<AlbProfesion> listaProfesion = new ArrayList<>();

    private Long IdSeleccionFuerza;
    List<SelectItem> listarFuerza = new ArrayList<SelectItem>();
    private List<AlbFuerza> listaFuerza = new ArrayList<>();

    private Long IdSeleccionRango;
    List<SelectItem> listarRango = new ArrayList<SelectItem>();
    private List<AlbRango> listaRango = new ArrayList<>();

    
    private Date date5;

    public Date getDate5() {
        return date5;
    }

    public void setDate5(Date date5) {
        this.date5 = date5;
    }
    
    
    @PostConstruct
    public void init() {
        if (!guardadoCabecera) {
            albPersonalAdministrativo = new AlbPersonalAdministrativo();
            listaTempAlbPerAdmnin.clear();
            listaEstadoCivil.clear();
            this.listaEstadoCivil.addAll(getAlbEstadoCivilServicio().listarEstadoCivil());
            listaProfesion.clear();
            this.listaProfesion.addAll(getAlbProfesionServicio().listarProfesion());
            listaFuerza.clear();
            this.listaFuerza.addAll(getAlbFuerzaServicio().listarFuerza());
            listaRango.clear();
            this.listaRango.addAll(getAlbRangoServicio().listarRango());
        }
        this.listaAlbPersonalAdministrativo.clear();
        this.listaAlbPersonalAdministrativo.addAll(getAlbPersonalAdministrativoServicio().listarPersonalAdmin());
    }

    public AlbPersonalAdministrativoServicio getAlbPersonalAdministrativoServicio() {
        return albPersonalAdministrativoServicio;
    }

    public void setAlbPersonalAdministrativoServicio(AlbPersonalAdministrativoServicio albPersonalAdministrativoServicio) {
        this.albPersonalAdministrativoServicio = albPersonalAdministrativoServicio;
    }

    public List<AlbPersonalAdministrativo> getListaAlbPersonalAdministrativo() {
        return listaAlbPersonalAdministrativo;
    }

    public void setListaAlbPersonalAdministrativo(List<AlbPersonalAdministrativo> listaAlbPersonalAdministrativo) {
        this.listaAlbPersonalAdministrativo = listaAlbPersonalAdministrativo;
    }

    public AlbPersonalAdministrativo getAlbPersonalAdministrativo() {
        return albPersonalAdministrativo;
    }

    public void setAlbPersonalAdministrativo(AlbPersonalAdministrativo albPersonalAdministrativo) {
        this.albPersonalAdministrativo = albPersonalAdministrativo;
    }

    public Long getIdSeleccionEstadoCivil() {
        return IdSeleccionEstadoCivil;
    }

    public void setIdSeleccionEstadoCivil(Long IdSeleccionEstadoCivil) {
        this.IdSeleccionEstadoCivil = IdSeleccionEstadoCivil;
    }

    public List<SelectItem> getListarEstadoCivil() {
        this.listarEstadoCivil = new ArrayList<SelectItem>();
        for (AlbEstadoCivil obj : listaEstadoCivil) {
            SelectItem selectItem = new SelectItem(obj.getEciId(), obj.getEciTipo());
            this.listarEstadoCivil.add(selectItem);
        }
        return listarEstadoCivil;
    }

    public void setListarEstadoCivil(List<SelectItem> listarEstadoCivil) {
        this.listarEstadoCivil = listarEstadoCivil;
    }

    public AlbEstadoCivilServicio getAlbEstadoCivilServicio() {
        return albEstadoCivilServicio;
    }

    public void setAlbEstadoCivilServicio(AlbEstadoCivilServicio albEstadoCivilServicio) {
        this.albEstadoCivilServicio = albEstadoCivilServicio;
    }

    public AlbProfesionServicio getAlbProfesionServicio() {
        return albProfesionServicio;
    }

    public void setAlbProfesionServicio(AlbProfesionServicio albProfesionServicio) {
        this.albProfesionServicio = albProfesionServicio;
    }

    public Long getIdSeleccionProfesion() {
        return IdSeleccionProfesion;
    }

    public void setIdSeleccionProfesion(Long IdSeleccionProfesion) {
        this.IdSeleccionProfesion = IdSeleccionProfesion;
    }

    public Long getIdSeleccionFuerza() {
        return IdSeleccionFuerza;
    }

    public void setIdSeleccionFuerza(Long IdSeleccionFuerza) {
        this.IdSeleccionFuerza = IdSeleccionFuerza;
    }

    public Long getIdSeleccionRango() {
        return IdSeleccionRango;
    }

    public void setIdSeleccionRango(Long IdSeleccionRango) {
        this.IdSeleccionRango = IdSeleccionRango;
    }

    public List<SelectItem> getListarProfesion() {
        this.listarProfesion = new ArrayList<SelectItem>();
        for (AlbProfesion obj : listaProfesion) {
            SelectItem selectItem = new SelectItem(obj.getPrfId(), obj.getPrfProfesion());
            this.listarProfesion.add(selectItem);
        }
        return listarProfesion;
    }

    public void setListarProfesion(List<SelectItem> listarProfesion) {
        this.listarProfesion = listarProfesion;
    }

    public AlbFuerzaServicio getAlbFuerzaServicio() {
        return albFuerzaServicio;
    }

    public void setAlbFuerzaServicio(AlbFuerzaServicio albFuerzaServicio) {
        this.albFuerzaServicio = albFuerzaServicio;
    }

    public List<SelectItem> getListarFuerza() {
        this.listarFuerza = new ArrayList<SelectItem>();
        for (AlbFuerza obj : listaFuerza) {
            SelectItem selectItem = new SelectItem(obj.getFueId(), obj.getFueFuerza());
            this.listarFuerza.add(selectItem);
        }

        return listarFuerza;
    }

    public void setListarFuerza(List<SelectItem> listarFuerza) {
        this.listarFuerza = listarFuerza;
    }

    public AlbRangoServicio getAlbRangoServicio() {
        return albRangoServicio;
    }

    public void setAlbRangoServicio(AlbRangoServicio albRangoServicio) {
        this.albRangoServicio = albRangoServicio;
    }

    public List<SelectItem> getListarRango() {
        this.listarRango = new ArrayList<SelectItem>();
        for (AlbRango obj : listaRango) {
            SelectItem selectItem = new SelectItem(obj.getRanId(), obj.getRanRango());
            this.listarRango.add(selectItem);
        }
        return listarRango;
    }

    public void setListarRango(List<SelectItem> listarRango) {
        this.listarRango = listarRango;
    }
public void cancelarCargaDatos() {
        albPersonalAdministrativo = new AlbPersonalAdministrativo();
    }
    
    public void guardarPerAdmin() {
        if ("".equals(albPersonalAdministrativo.getPeaNombres()) || "".equals(albPersonalAdministrativo.getPeaApellidos()) || "".equals(albPersonalAdministrativo.getPeaCedula())
                 || "".equals(albPersonalAdministrativo.getPeaLugarNacimiento()) 
                 | "".equals(albPersonalAdministrativo.getPeaCelular()) || "".equals(albPersonalAdministrativo.getPeaEmail()) || "".equals(albPersonalAdministrativo.getPeaObservaciones())) {
            mensajeEAS.errorLlenarDatos();
        } else {

            try {
                
                albPersonalAdministrativo.getPeaSexo();
                albPersonalAdministrativo.getPeaFechaNacimiento();
                AlbEstadoCivil objEstCivil = new AlbEstadoCivil();
                AlbProfesion objProfesion = new AlbProfesion();
                AlbFuerza objFuerza= new AlbFuerza();
                AlbRango objRango= new AlbRango();
                objEstCivil.setEciId(IdSeleccionEstadoCivil);
                objProfesion.setPrfId(IdSeleccionProfesion);
                objFuerza.setFueId(IdSeleccionFuerza);
                objRango.setRanId(IdSeleccionRango);
                albPersonalAdministrativo.setAlbEstadoCivil(objEstCivil);
                albPersonalAdministrativo.setAlbProfesion(objProfesion);
                albPersonalAdministrativo.setAlbFuerza(objFuerza);
                albPersonalAdministrativo.setAlbRango(objRango);
                albPersonalAdministrativo.setPeaEstado(1);                
                listaTempAlbPerAdmnin.clear();
                listaTempAlbPerAdmnin.add(albPersonalAdministrativo);
                getAlbPersonalAdministrativoServicio().guardarPersonalAdmin(listaTempAlbPerAdmnin);
                albPersonalAdministrativo = new AlbPersonalAdministrativo();
                guardadoCabecera = true;
                mensajeEAS.info(true);
                init();
            } catch (Exception ex) {
                guardadoCabecera = false;
                LOG.error("Error: " + ex.getMessage());
                mensajeEAS.error();
            }
        }

    }
}
