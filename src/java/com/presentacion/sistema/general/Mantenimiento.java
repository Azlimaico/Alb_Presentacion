package com.presentacion.sistema.general;

import com.negocio.servicio.general.sistema.AlbFuerzaServicio;
import com.negocio.servicio.general.sistema.AlbProfesionServicio;
import com.negocio.servicio.general.sistema.AlbRangoServicio;
import com.persistencia.albergue.AlbAlbergue;
import com.persistencia.general.sistema.AlbFuerza;
import com.persistencia.general.sistema.AlbProfesion;
import com.persistencia.general.sistema.AlbRango;
import com.persistencia.parametros.sistema.ParametrosObjetos;
import com.presentacion.mensajes.MensajeEAS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.jboss.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Zulay
 */
@ManagedBean
@ViewScoped
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(Mantenimiento.class);
    @ManagedProperty(value = "#{AlbProfesionServicioImpl}")
    AlbProfesionServicio albProfesionServicio;
    private List<AlbProfesion> listaProfesion = new ArrayList();
    private List<AlbProfesion> listaTempAlbProfesion = new ArrayList<>();
    AlbProfesion albProfesion = new AlbProfesion();
    //FUERZA
    @ManagedProperty(value = "#{AlbFuerzaServicioImpl}")
    AlbFuerzaServicio albFuerzaServicio;
    private List<AlbFuerza> listaFuerza = new ArrayList();
    private List<AlbFuerza> listaTempAlbFuerza = new ArrayList<>();
    AlbFuerza albFuerza = new AlbFuerza();
    
    @ManagedProperty(value = "#{AlbRangoServicioImpl}")
    AlbRangoServicio albRangoServicio;
    private List<AlbRango> listaRango = new ArrayList();
    private List<AlbRango> listaTempAlbRango = new ArrayList<>();
    AlbRango albRango = new AlbRango();

    private Boolean guardadoCabecera = false;
    private MensajeEAS mensajeEAS = new MensajeEAS();
    //EDITAR PROFESION
    private AlbProfesion selectedProfesionEditar = new AlbProfesion();
    private AlbProfesion segProfesionObjects = new AlbProfesion();
    private Long IdEditar;
    //ELIMINAR PROFESION
    private AlbProfesion selectedProfesionEliminar = new AlbProfesion();

    //EDITAR FUERZA
    private AlbFuerza selectedFuerzaEditar = new AlbFuerza();
    private AlbFuerza segFuerzaObjects = new AlbFuerza();
    private Long IdEditarFuerza;
    //ELIMINAR FUERZA
    private AlbFuerza selectedFuerzaEliminar = new AlbFuerza();
    
    //EDITAR RANGO
    private AlbRango selectedRangoEditar = new AlbRango();
    private AlbRango segRangoObjects = new AlbRango();
    private Long IdEditarRango;
    //ELIMINAR FUERZA
    private AlbRango selectedRangoEliminar = new AlbRango();

    @PostConstruct
    public void init() {
        if (!guardadoCabecera) {
            listaTempAlbProfesion.clear();
            listaTempAlbFuerza.clear();
            albProfesion = new AlbProfesion();
            albFuerza = new AlbFuerza();
        }
        listaFuerza.clear();
        this.listaFuerza.addAll(getAlbFuerzaServicio().listarFuerza());
        listaProfesion.clear();
        this.listaProfesion.addAll(getAlbProfesionServicio().listarProfesion());
        listaRango.clear();
        this.listaRango.addAll(getAlbRangoServicio().listarRango());
    }

    public AlbFuerzaServicio getAlbFuerzaServicio() {
        return albFuerzaServicio;
    }

    public void setAlbFuerzaServicio(AlbFuerzaServicio albFuerzaServicio) {
        this.albFuerzaServicio = albFuerzaServicio;
    }

    public AlbProfesionServicio getAlbProfesionServicio() {
        return albProfesionServicio;
    }

    public void setAlbProfesionServicio(AlbProfesionServicio albProfesionServicio) {
        this.albProfesionServicio = albProfesionServicio;
    }

    public List<AlbProfesion> getListaProfesion() {
        return listaProfesion;
    }

    public void setListaProfesion(List<AlbProfesion> listaProfesion) {
        this.listaProfesion = listaProfesion;
    }

    public AlbProfesion getAlbProfesion() {
        return albProfesion;
    }

    public void setAlbProfesion(AlbProfesion albProfesion) {
        this.albProfesion = albProfesion;
    }

    public void guardarProfesion() {
        if ("".equals(albProfesion.getPrfProfesion())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                albProfesion.setPrfEstado(1);
                listaTempAlbProfesion.clear();
                listaTempAlbProfesion.add(albProfesion);
                getAlbProfesionServicio().guardarProfesion(listaTempAlbProfesion);
                albProfesion = new AlbProfesion();
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

    public void guardarFuerza() {
        if ("".equals(albFuerza.getFueFuerza())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                albFuerza.setFueEstado(1);
                listaTempAlbFuerza.clear();
                listaTempAlbFuerza.add(albFuerza);
                getAlbFuerzaServicio().guardarFuerza(listaTempAlbFuerza);
                albFuerza = new AlbFuerza();
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

    public AlbProfesion getSelectedProfesionEditar() {
        return selectedProfesionEditar;
    }

    public void setSelectedProfesionEditar(AlbProfesion selectedProfesionEditar) {
        this.selectedProfesionEditar = selectedProfesionEditar;
        editarProfesionSistema(selectedProfesionEditar);
    }

    public void editarProfesionSistema(AlbProfesion objPUE) {
        try {
            segProfesionObjects = new AlbProfesion();
            IdEditar = objPUE.getPrfId();
            objPUE.getPrfProfesion();
            segProfesionObjects.setPrfProfesion(objPUE.getPrfProfesion());
            segProfesionObjects.setPrfEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void actualizarUsuarioSistema() {
        try {
            AlbProfesion objProf = new AlbProfesion();
            objProf.setPrfId(IdEditar);
            objProf.setPrfProfesion(segProfesionObjects.getPrfProfesion());
            objProf.setPrfEstado(1);
            if ("".equals(albProfesion.getPrfProfesion())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempAlbProfesion.clear();
                listaTempAlbProfesion.add(objProf);
                getAlbProfesionServicio().guardarProfesion(listaTempAlbProfesion);
                albProfesion = new AlbProfesion();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }

    public AlbProfesion getSegProfesionObjects() {
        return segProfesionObjects;
    }

    public void setSegProfesionObjects(AlbProfesion segProfesionObjects) {
        this.segProfesionObjects = segProfesionObjects;
    }

    public AlbProfesion getSelectedProfesionEliminar() {
        return selectedProfesionEliminar;
    }

    public void setSelectedProfesionEliminar(AlbProfesion selectedProfesionEliminar) {
        this.selectedProfesionEliminar = selectedProfesionEliminar;
        eliminarProfesionSistema(selectedProfesionEliminar);
    }

    public void eliminarProfesionSistema(AlbProfesion objPU) {
        try {

            objPU.setPrfEstado(ParametrosObjetos.INACTIVO);
            getAlbProfesionServicio().guardarProfesionEl(objPU);
            mensajeEAS.Eliminar();
            cargarTable();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTable() {
        albProfesion = new AlbProfesion();
        listaProfesion.clear();
        this.listaProfesion.addAll(getAlbProfesionServicio().listarProfesion());
    }

    public List<AlbFuerza> getListaFuerza() {
        return listaFuerza;
    }

    public void setListaFuerza(List<AlbFuerza> listaFuerza) {
        this.listaFuerza = listaFuerza;
    }

    public AlbFuerza getAlbFuerza() {
        return albFuerza;
    }

    public void setAlbFuerza(AlbFuerza albFuerza) {
        this.albFuerza = albFuerza;
    }

    public AlbFuerza getSelectedFuerzaEditar() {
        return selectedFuerzaEditar;
    }

    public void setSelectedFuerzaEditar(AlbFuerza selectedFuerzaEditar) {
        this.selectedFuerzaEditar = selectedFuerzaEditar;
        editarFuerzaSistema(selectedFuerzaEditar);
    }

    public void editarFuerzaSistema(AlbFuerza objPUE) {
        try {
            segFuerzaObjects = new AlbFuerza();
            IdEditarFuerza = objPUE.getFueId();
            objPUE.getFueFuerza();
            segFuerzaObjects.setFueFuerza(objPUE.getFueFuerza());
            segFuerzaObjects.setFueEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public AlbFuerza getSegFuerzaObjects() {
        return segFuerzaObjects;
    }

    public void setSegFuerzaObjects(AlbFuerza segFuerzaObjects) {
        this.segFuerzaObjects = segFuerzaObjects;
    }
    
    public void actualizarFuerzaSistema() {
        try {
            AlbFuerza objFue = new AlbFuerza();
            objFue.setFueId(IdEditarFuerza);
            objFue.setFueFuerza(segFuerzaObjects.getFueFuerza());
            objFue.setFueEstado(1);
            if ("".equals(albFuerza.getFueFuerza())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempAlbFuerza.clear();
                listaTempAlbFuerza.add(objFue);
                getAlbFuerzaServicio().guardarFuerza(listaTempAlbFuerza);
                albFuerza = new AlbFuerza();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }

    public AlbFuerza getSelectedFuerzaEliminar() {
        return selectedFuerzaEliminar;
    }

    public void setSelectedFuerzaEliminar(AlbFuerza selectedFuerzaEliminar) {
        this.selectedFuerzaEliminar = selectedFuerzaEliminar;
         eliminarFuerzaSistema(selectedFuerzaEliminar);
    }
    
    public void eliminarFuerzaSistema(AlbFuerza objPU) {
        try {

            objPU.setFueEstado(ParametrosObjetos.INACTIVO);
            getAlbFuerzaServicio().guardarFuerzaEl(objPU);
            mensajeEAS.Eliminar();
            cargarTableF();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTableF() {
        albFuerza = new AlbFuerza();
        listaFuerza.clear();
        this.listaFuerza.addAll(getAlbFuerzaServicio().listarFuerza());
    }

    public List<AlbRango> getListaRango() {
        return listaRango;
    }

    public void setListaRango(List<AlbRango> listaRango) {
        this.listaRango = listaRango;
    }

    public AlbRango getAlbRango() {
        return albRango;
    }

    public void setAlbRango(AlbRango albRango) {
        this.albRango = albRango;
    }

    public AlbRangoServicio getAlbRangoServicio() {
        return albRangoServicio;
    }

    public void setAlbRangoServicio(AlbRangoServicio albRangoServicio) {
        this.albRangoServicio = albRangoServicio;
    }

    public AlbRango getSegRangoObjects() {
        return segRangoObjects;
    }

    public void setSegRangoObjects(AlbRango segRangoObjects) {
        this.segRangoObjects = segRangoObjects;
    }
    
    
    public void guardarRango() {
        if ("".equals(albRango.getRanRango())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                albRango.setRanEstado(1);
                listaTempAlbRango.clear();
                listaTempAlbRango.add(albRango);
                getAlbRangoServicio().guardarRango(listaTempAlbRango);
                albRango = new AlbRango();
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

    public AlbRango getSelectedRangoEditar() {
        return selectedRangoEditar;
        
    }
    
     public void setSelectedRangoEditar(AlbRango selectedRangoEditar) {
        this.selectedRangoEditar = selectedRangoEditar;
        this.editarRangoSistema(selectedRangoEditar);
    }
    
    public void editarRangoSistema(AlbRango objPUE) {
        try {
            segRangoObjects = new AlbRango();
            IdEditarRango = objPUE.getRanId();
            objPUE.getRanRango();
            segRangoObjects.setRanRango(objPUE.getRanRango());
            segRangoObjects.setRanEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    
    
    public void actualizarRangoSistema() {
        try {
            AlbRango objRang = new AlbRango();
            objRang.setRanId(IdEditarRango);
            objRang.setRanRango(segRangoObjects.getRanRango());
            objRang.setRanEstado(1);
            if ("".equals(albRango.getRanEstado())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempAlbRango.clear();
                listaTempAlbRango.add(objRang);
                getAlbRangoServicio().guardarRango(listaTempAlbRango);
                albRango = new AlbRango();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }
    
    public AlbRango getSelectedRangoEliminar() {
        return selectedRangoEliminar;
    }
    public void setSelectedRangoEliminar(AlbRango selectedRangoEliminar) {
        this.selectedRangoEliminar = selectedRangoEliminar;
        eliminarRangoSistema(selectedRangoEliminar);
    }
    
     public void eliminarRangoSistema(AlbRango objPU) {
        try {

            objPU.setRanEstado(ParametrosObjetos.INACTIVO);
            getAlbRangoServicio().guardarRangoEl(objPU);
            mensajeEAS.Eliminar();
            cargarTableR();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTableR() {
        albRango = new AlbRango();
        listaRango.clear();
        this.listaRango.addAll(getAlbRangoServicio().listarRango());
    }
    
}
