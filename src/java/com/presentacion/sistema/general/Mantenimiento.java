package com.presentacion.sistema.general;

import com.negocio.servicio.general.sistema.AlbDiscapacidadServicio;
import com.negocio.servicio.general.sistema.AlbEstadoCivilServicio;
import com.negocio.servicio.general.sistema.AlbFamiliaServicio;
import com.negocio.servicio.general.sistema.AlbFuerzaServicio;
import com.negocio.servicio.general.sistema.AlbInstruccionServicio;
import com.negocio.servicio.general.sistema.AlbProfesionServicio;
import com.negocio.servicio.general.sistema.AlbRangoServicio;
import com.persistencia.albergue.AlbAlbergue;
import com.persistencia.general.sistema.AlbDiscapacidad;
import com.persistencia.general.sistema.AlbEstadoCivil;
import com.persistencia.general.sistema.AlbFamilia;
import com.persistencia.general.sistema.AlbFuerza;
import com.persistencia.general.sistema.AlbInstruccion;
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

    @ManagedProperty(value = "#{AlbEstadoCivilServicioImpl}")
    AlbEstadoCivilServicio albEstadoCivilServicio;
    private List<AlbEstadoCivil> listaEstadoCivil = new ArrayList();
    private List<AlbEstadoCivil> listaTempEstadoCivil = new ArrayList<>();
    AlbEstadoCivil albEstadoCivil = new AlbEstadoCivil();

    @ManagedProperty(value = "#{AlbInstruccionServicioImpl}")
    AlbInstruccionServicio albInstruccionServicio;
    private List<AlbInstruccion> listaInstruccion = new ArrayList();
    private List<AlbInstruccion> listaTempInstruccion = new ArrayList<>();
    AlbInstruccion albInstruccion = new AlbInstruccion();

    @ManagedProperty(value = "#{AlbFamiliaServicioImpl}")
    AlbFamiliaServicio albFamiliaServicio;
    private List<AlbFamilia> listaFlia = new ArrayList();
    private List<AlbFamilia> listaTempFlia = new ArrayList<>();
    AlbFamilia albFamilia = new AlbFamilia();

    @ManagedProperty(value = "#{AlbDiscapacidadServicioImpl}")
    AlbDiscapacidadServicio albDiscapacidadServicio;
    private List<AlbDiscapacidad> listaDisca = new ArrayList();
    private List<AlbDiscapacidad> listaTempDisca = new ArrayList<>();
    AlbDiscapacidad albDiscapacidad = new AlbDiscapacidad();

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
    //ELIMINAR RANGO
    private AlbRango selectedRangoEliminar = new AlbRango();

    //EDITAR ESTADO CIVIL
    private AlbEstadoCivil selectedECivilEditar = new AlbEstadoCivil();
    private AlbEstadoCivil segECivilObjects = new AlbEstadoCivil();
    private Long IdEditarECivil;
    //ELIMINAR ESTADO CIVIL
    private AlbEstadoCivil selectedECivilEliminar = new AlbEstadoCivil();

    //EDITAR INSTRUCCION
    private AlbInstruccion selectedInstruEditar = new AlbInstruccion();
    private AlbInstruccion segInstruObjects = new AlbInstruccion();
    private Long IdEditarInstru;
    //ELIMINAR INSTRUCCION
    private AlbInstruccion selectedInstruEliminar = new AlbInstruccion();

    //EDITAR FLIA
    private AlbFamilia selectedFliaEditar = new AlbFamilia();
    private AlbFamilia segFliaObjects = new AlbFamilia();
    private Long IdEditarFlia;
    //ELIMINAR INSTRUCCION
    private AlbFamilia selectedFliaEliminar = new AlbFamilia();

    //EDITAR DISCAPACIDAD
    private AlbDiscapacidad selectedDiscaEditar = new AlbDiscapacidad();
    private AlbDiscapacidad segDiscaObjects = new AlbDiscapacidad();
    private Long IdEditarDisca;
    //ELIMINAR DISCAPACIDAD
    private AlbDiscapacidad selectedDiscaEliminar = new AlbDiscapacidad();

    @PostConstruct
    public void init() {
        if (!guardadoCabecera) {
            listaTempAlbProfesion.clear();
            listaTempAlbFuerza.clear();
            listaFuerza.clear();
            listaProfesion.clear();
            albProfesion = new AlbProfesion();
            albFuerza = new AlbFuerza();
            listaRango.clear();
            listaEstadoCivil.clear();
            listaInstruccion.clear();
            listaFlia.clear();
            listaDisca.clear();
        }
        listaFuerza.clear();
        this.listaFuerza.addAll(getAlbFuerzaServicio().listarFuerza());
        listaProfesion.clear();
        this.listaProfesion.addAll(getAlbProfesionServicio().listarProfesion());
        listaRango.clear();
        this.listaRango.addAll(getAlbRangoServicio().listarRango());
        listaEstadoCivil.clear();
        this.listaEstadoCivil.addAll(getAlbEstadoCivilServicio().listarEstadoCivil());
        listaInstruccion.clear();
        this.listaInstruccion.addAll(getAlbInstruccionServicio().listarInstruccion());
        listaFlia.clear();
        this.listaFlia.addAll(getAlbFamiliaServicio().listarFamilia());
        listaDisca.clear();
        this.listaDisca.addAll(getAlbDiscapacidadServicio().listarDiscapacidad());
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
                mensajeEAS.errorDublicado();
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
                mensajeEAS.errorDublicado();
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
            mensajeEAS.errorDublicado();
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
            mensajeEAS.errorDublicado();
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
                mensajeEAS.errorDublicado();
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
            mensajeEAS.errorDublicado();
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

    public AlbEstadoCivilServicio getAlbEstadoCivilServicio() {
        return albEstadoCivilServicio;
    }

    public void setAlbEstadoCivilServicio(AlbEstadoCivilServicio albEstadoCivilServicio) {
        this.albEstadoCivilServicio = albEstadoCivilServicio;
    }

    public List<AlbEstadoCivil> getListaEstadoCivil() {
        return listaEstadoCivil;
    }

    public void setListaEstadoCivil(List<AlbEstadoCivil> listaEstadoCivil) {
        this.listaEstadoCivil = listaEstadoCivil;
    }

    public AlbEstadoCivil getAlbEstadoCivil() {
        return albEstadoCivil;
    }

    public void setAlbEstadoCivil(AlbEstadoCivil albEstadoCivil) {
        this.albEstadoCivil = albEstadoCivil;
    }

    public void guardarEstadoCivil() {
        if ("".equals(albEstadoCivil.getEciTipo())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                albEstadoCivil.setEciEstado(1);
                listaTempEstadoCivil.clear();
                listaTempEstadoCivil.add(albEstadoCivil);
                getAlbEstadoCivilServicio().guardarEstadoCivil(listaTempEstadoCivil);
                albEstadoCivil = new AlbEstadoCivil();
                guardadoCabecera = true;
                mensajeEAS.info(true);
                init();
            } catch (Exception ex) {
                guardadoCabecera = false;
                LOG.error("Error: " + ex.getMessage());
                mensajeEAS.errorDublicado();
            }

        }
    }

    public AlbEstadoCivil getSelectedECivilEditar() {
        return selectedECivilEditar;
    }

    public void setSelectedECivilEditar(AlbEstadoCivil selectedECivilEditar) {
        this.selectedECivilEditar = selectedECivilEditar;
        editarECivilSistema(selectedECivilEditar);
    }

    public void editarECivilSistema(AlbEstadoCivil objPUE) {
        try {
            segECivilObjects = new AlbEstadoCivil();
            IdEditarECivil = objPUE.getEciId();
            objPUE.getEciTipo();
            segECivilObjects.setEciTipo(objPUE.getEciTipo());
            segECivilObjects.setEciEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }

    public AlbEstadoCivil getSegECivilObjects() {
        return segECivilObjects;
    }

    public void setSegECivilObjects(AlbEstadoCivil segECivilObjects) {
        this.segECivilObjects = segECivilObjects;
    }

    public void actualizarECivilSistema() {
        try {
            AlbEstadoCivil objECivil = new AlbEstadoCivil();
            objECivil.setEciId(IdEditarECivil);
            objECivil.setEciTipo(segECivilObjects.getEciTipo());
            objECivil.setEciEstado(1);
            if ("".equals(albEstadoCivil.getEciTipo())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempEstadoCivil.clear();
                listaTempEstadoCivil.add(objECivil);
                getAlbEstadoCivilServicio().guardarEstadoCivil(listaTempEstadoCivil);
                albEstadoCivil = new AlbEstadoCivil();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbEstadoCivil getSelectedECivilEliminar() {
        return selectedECivilEliminar;
    }

    public void setSelectedECivilEliminar(AlbEstadoCivil selectedECivilEliminar) {
        this.selectedECivilEliminar = selectedECivilEliminar;
        eliminarECivilSistema(selectedECivilEliminar);
    }

    public void eliminarECivilSistema(AlbEstadoCivil objPU) {
        try {

            objPU.setEciEstado(ParametrosObjetos.INACTIVO);
            getAlbEstadoCivilServicio().guardarEstadoCivilEl(objPU);
            mensajeEAS.Eliminar();
            cargarTableECivil();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTableECivil() {
        albEstadoCivil = new AlbEstadoCivil();
        listaEstadoCivil.clear();
        this.listaEstadoCivil.addAll(getAlbEstadoCivilServicio().listarEstadoCivil());
    }

    public List<AlbInstruccion> getListaInstruccion() {
        return listaInstruccion;
    }

    public void setListaInstruccion(List<AlbInstruccion> listaInstruccion) {
        this.listaInstruccion = listaInstruccion;
    }

    public AlbInstruccionServicio getAlbInstruccionServicio() {
        return albInstruccionServicio;
    }

    public void setAlbInstruccionServicio(AlbInstruccionServicio albInstruccionServicio) {
        this.albInstruccionServicio = albInstruccionServicio;
    }

    public AlbInstruccion getAlbInstruccion() {
        return albInstruccion;
    }

    public void setAlbInstruccion(AlbInstruccion albInstruccion) {
        this.albInstruccion = albInstruccion;
    }

    public void guardarInstruccion() {
        if ("".equals(albInstruccion.getInsNombre())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                albInstruccion.setInsEstado(1);
                listaTempInstruccion.clear();
                listaTempInstruccion.add(albInstruccion);
                getAlbInstruccionServicio().guardarInstruccion(listaTempInstruccion);
                albInstruccion = new AlbInstruccion();
                guardadoCabecera = true;
                mensajeEAS.info(true);
                init();
            } catch (Exception ex) {
                guardadoCabecera = false;
                LOG.error("Error: " + ex.getMessage());
                mensajeEAS.errorDublicado();
            }

        }
    }

    public AlbInstruccion getSelectedInstruEditar() {
        return selectedInstruEditar;
    }

    public void setSelectedInstruEditar(AlbInstruccion selectedInstruEditar) {
        this.selectedInstruEditar = selectedInstruEditar;
        editarInstruSistema(selectedInstruEditar);

    }

    public void editarInstruSistema(AlbInstruccion objPUE) {
        try {
            segInstruObjects = new AlbInstruccion();
            IdEditarInstru = objPUE.getInsId();
            objPUE.getInsNombre();
            segInstruObjects.setInsNombre(objPUE.getInsNombre());
            segInstruObjects.setInsEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }

    public AlbInstruccion getSegInstruObjects() {
        return segInstruObjects;
    }

    public void setSegInstruObjects(AlbInstruccion segInstruObjects) {
        this.segInstruObjects = segInstruObjects;
    }

    public void actualizarInstruSistema() {
        try {
            AlbInstruccion objInstru = new AlbInstruccion();
            objInstru.setInsId(IdEditarInstru);
            objInstru.setInsNombre(segInstruObjects.getInsNombre());
            objInstru.setInsEstado(1);
            if ("".equals(albInstruccion.getInsNombre())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempInstruccion.clear();
                listaTempInstruccion.add(objInstru);
                getAlbInstruccionServicio().guardarInstruccion(listaTempInstruccion);
                albInstruccion = new AlbInstruccion();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbInstruccion getSelectedInstruEliminar() {
        return selectedInstruEliminar;
    }

    public void setSelectedInstruEliminar(AlbInstruccion selectedInstruEliminar) {
        this.selectedInstruEliminar = selectedInstruEliminar;
        eliminarInstruSistema(selectedInstruEliminar);
    }

    public void eliminarInstruSistema(AlbInstruccion objPU) {
        try {

            objPU.setInsEstado(ParametrosObjetos.INACTIVO);
            getAlbInstruccionServicio().guardarInstruccionEl(objPU);
            mensajeEAS.Eliminar();
            cargarTableInstru();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTableInstru() {
        albInstruccion = new AlbInstruccion();
        listaInstruccion.clear();
        this.listaInstruccion.addAll(getAlbInstruccionServicio().listarInstruccion());
    }

    public List<AlbFamilia> getListaFlia() {
        return listaFlia;
    }

    public void setListaFlia(List<AlbFamilia> listaFlia) {
        this.listaFlia = listaFlia;
    }

    public AlbFamiliaServicio getAlbFamiliaServicio() {
        return albFamiliaServicio;
    }

    public void setAlbFamiliaServicio(AlbFamiliaServicio albFamiliaServicio) {
        this.albFamiliaServicio = albFamiliaServicio;
    }

    public AlbFamilia getAlbFamilia() {
        return albFamilia;
    }

    public void setAlbFamilia(AlbFamilia albFamilia) {
        this.albFamilia = albFamilia;
    }

    public void guardarFlia() {
        if ("".equals(albFamilia.getFamNumIntegrantes())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                albFamilia.setFamEstado(1);
                listaTempFlia.clear();
                listaTempFlia.add(albFamilia);
                getAlbFamiliaServicio().guardarFlia(listaTempFlia);
                albFamilia = new AlbFamilia();
                guardadoCabecera = true;
                mensajeEAS.info(true);
                init();
            } catch (Exception ex) {
                guardadoCabecera = false;
                LOG.error("Error: " + ex.getMessage());
                mensajeEAS.errorDublicado();
            }

        }
    }

    public AlbFamilia getSelectedFliaEditar() {
        return selectedFliaEditar;
    }

    public void setSelectedFliaEditar(AlbFamilia selectedFliaEditar) {
        this.selectedFliaEditar = selectedFliaEditar;
        editarFliaSistema(selectedFliaEditar);
    }

    public void editarFliaSistema(AlbFamilia objPUE) {
        try {
            segFliaObjects = new AlbFamilia();
            IdEditarFlia = objPUE.getFamId();
            objPUE.getFamNumIntegrantes();
            segFliaObjects.setFamNumIntegrantes(objPUE.getFamNumIntegrantes());
            segFliaObjects.setFamEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }

    public AlbFamilia getSegFliaObjects() {
        return segFliaObjects;
    }

    public void setSegFliaObjects(AlbFamilia segFliaObjects) {
        this.segFliaObjects = segFliaObjects;
    }

    public void actualizarFliaSistema() {
        try {
            AlbFamilia objFlia = new AlbFamilia();
            objFlia.setFamId(IdEditarFlia);
            objFlia.setFamNumIntegrantes(segFliaObjects.getFamNumIntegrantes());
            objFlia.setFamEstado(1);
            if ("".equals(albFamilia.getFamNumIntegrantes())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempFlia.clear();
                listaTempFlia.add(objFlia);
                getAlbFamiliaServicio().guardarFlia(listaTempFlia);
                albFamilia = new AlbFamilia();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbFamilia getSelectedFliaEliminar() {
        return selectedFliaEliminar;
    }

    public void setSelectedFliaEliminar(AlbFamilia selectedFliaEliminar) {
        this.selectedFliaEliminar = selectedFliaEliminar;
        eliminarFliaSistema(selectedFliaEliminar);

    }

    public void eliminarFliaSistema(AlbFamilia objPU) {
        try {

            objPU.setFamEstado(ParametrosObjetos.INACTIVO);
            getAlbFamiliaServicio().guardarFliaEl(objPU);
            mensajeEAS.Eliminar();
            cargarTableFlia();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTableFlia() {
        albFamilia = new AlbFamilia();
        listaFlia.clear();
        this.listaFlia.addAll(getAlbFamiliaServicio().listarFamilia());
    }

    public AlbDiscapacidadServicio getAlbDiscapacidadServicio() {
        return albDiscapacidadServicio;
    }

    public void setAlbDiscapacidadServicio(AlbDiscapacidadServicio albDiscapacidadServicio) {
        this.albDiscapacidadServicio = albDiscapacidadServicio;
    }

    public List<AlbDiscapacidad> getListaDisca() {
        return listaDisca;
    }

    public void setListaDisca(List<AlbDiscapacidad> listaDisca) {
        this.listaDisca = listaDisca;
    }

    public AlbDiscapacidad getAlbDiscapacidad() {
        return albDiscapacidad;
    }

    public void setAlbDiscapacidad(AlbDiscapacidad albDiscapacidad) {
        this.albDiscapacidad = albDiscapacidad;
    }

    public void guardarDisca() {
        if ("".equals(albDiscapacidad.getDisTipo())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                albDiscapacidad.setDisEstado(1);
                listaTempDisca.clear();
                listaTempDisca.add(albDiscapacidad);
                getAlbDiscapacidadServicio().guardarDisca(listaTempDisca);
                albDiscapacidad = new AlbDiscapacidad();
                guardadoCabecera = true;
                mensajeEAS.info(true);
                init();
            } catch (Exception ex) {
                guardadoCabecera = false;
                LOG.error("Error: " + ex.getMessage());
                mensajeEAS.errorDublicado();
            }

        }
    }

    public AlbDiscapacidad getSelectedDiscaEditar() {
        return selectedDiscaEditar;
    }

    public void setSelectedDiscaEditar(AlbDiscapacidad selectedDiscaEditar) {
        this.selectedDiscaEditar = selectedDiscaEditar;
        editarDiscaSistema(selectedDiscaEditar);
    }

    public void editarDiscaSistema(AlbDiscapacidad objPUE) {
        try {
            segDiscaObjects = new AlbDiscapacidad();
            IdEditarDisca = objPUE.getDisId();
            objPUE.getDisTipo();
            segDiscaObjects.setDisTipo(objPUE.getDisTipo());
            segDiscaObjects.setDisEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }

    public AlbDiscapacidad getSegDiscaObjects() {
        return segDiscaObjects;
    }

    public void setSegDiscaObjects(AlbDiscapacidad segDiscaObjects) {
        this.segDiscaObjects = segDiscaObjects;
    }

    public void actualizarDiscaSistema() {
        try {
            AlbDiscapacidad objDisca = new AlbDiscapacidad();
            objDisca.setDisId(IdEditarDisca);
            objDisca.setDisTipo(segDiscaObjects.getDisTipo());
            objDisca.setDisEstado(1);
            if ("".equals(albDiscapacidad.getDisTipo())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempDisca.clear();
                listaTempDisca.add(objDisca);
                getAlbDiscapacidadServicio().guardarDisca(listaTempDisca);
                albDiscapacidad = new AlbDiscapacidad();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbDiscapacidad getSelectedDiscaEliminar() {
        return selectedDiscaEliminar;
    }

    public void setSelectedDiscaEliminar(AlbDiscapacidad selectedDiscaEliminar) {
        this.selectedDiscaEliminar = selectedDiscaEliminar;
        eliminarDiscaSistema(selectedDiscaEliminar);
    }

    public void eliminarDiscaSistema(AlbDiscapacidad objPU) {
        try {

            objPU.setDisEstado(ParametrosObjetos.INACTIVO);
            getAlbDiscapacidadServicio().guardarDiscaEl(objPU);
            mensajeEAS.Eliminar();
            cargarTableDisca();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTableDisca() {
        albDiscapacidad = new AlbDiscapacidad();
        listaDisca.clear();
        this.listaDisca.addAll(getAlbDiscapacidadServicio().listarDiscapacidad());
    }

}
