/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.sistema.general;

import com.negocio.servicio.albergue.AlbAlbergueServicio;
import com.negocio.servicio.albergue.ServicioSituacionAlbergueServicio;
import com.negocio.servicio.albergue.servicio.basico.AlbServicioServicio;
import com.negocio.servicio.albergue.servicio.basico.AlbSituacionServicio;
import com.persistencia.albergue.AlbAlbergue;
import com.persistencia.albergue.ServicioSituacionAlbergue;
import com.persistencia.albergue.servicio.AlbServicio;
import com.persistencia.albergue.servicio.AlbSituacion;
import com.persistencia.general.sistema.AlbAvanceImplementacion;
import com.persistencia.general.sistema.AlbCanton;
import com.persistencia.general.sistema.AlbEmpresa;
import com.persistencia.general.sistema.AlbParroquia;
import com.persistencia.general.sistema.AlbProvincia;
import com.persistencia.general.sistema.AlbTipoEmpresa;
import com.persistencia.parametros.sistema.ParametrosObjetos;
import com.presentacion.mensajes.MensajeEAS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;
import org.jboss.logging.Logger;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Zulay
 */
@ManagedBean
@ViewScoped
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(Servicios.class);
    @ManagedProperty(value = "#{AlbServicioServicioImpl}")
    AlbServicioServicio albServicioServicio;
    private List<AlbServicio> listaServicio = new ArrayList<>();
    private Boolean guardadoCabecera = false;
    //NUEVO AGUA
    private AlbSituacion albSituacion = new AlbSituacion();
    private List<AlbSituacion> listaTempAlbSituacion = new ArrayList<>();
    private Long IdSeleccionAgua;
    private List<AlbTipoEmpresa> listaTipoEmpresa = new ArrayList<>();
    List<SelectItem> genListaSelected = new ArrayList<SelectItem>();
    //SERVICIO SITUACION ALBERGUE
    @ManagedProperty(value = "#{ServicioSituacionAlbergueServicioImpl}")
    ServicioSituacionAlbergueServicio servicioSituacionAlbergueServicio;
    private List<ServicioSituacionAlbergue> listaServicioSituacionAlbergue = new ArrayList<>();

    //SITUACION
    @ManagedProperty(value = "#{AlbSituacionServicioImpl}")
    AlbSituacionServicio albSituacionServicio;
    private List<AlbSituacion> listaAlbSituacion = new ArrayList<>();
    private List<AlbSituacion> listaElectricidad = new ArrayList<>();
    private List<AlbSituacion> listaAgua = new ArrayList<>();
    private List<AlbSituacion> listaInternet = new ArrayList<>();
    private List<AlbSituacion> listaBateriasSanitarias = new ArrayList<>();
    private List<AlbSituacion> listaServicioSalud = new ArrayList<>();
    private List<AlbSituacion> listaDesechosSolidos = new ArrayList<>();
    private List<AlbSituacion> listaEducacion = new ArrayList<>();
    private List<AlbSituacion> listaCombustibleFosil = new ArrayList<>();
    private List<AlbSituacion> listaAlimentos = new ArrayList<>();
    //AÑADIR SERVICIO A ALBERGUE
    private AlbAlbergue selectedAlbergueAsignar = new AlbAlbergue();
    private MensajeEAS mensajeEAS = new MensajeEAS();

    private AlbServicio albServicio = new AlbServicio();
    private List<AlbServicio> listaTempAlbServicio = new ArrayList<>();
    private AlbEmpresa albEmpresa = new AlbEmpresa();
    private List<AlbEmpresa> listaTempAlbEmpresa = new ArrayList<>();
    private ServicioSituacionAlbergue servicioSituacionAlbergue = new ServicioSituacionAlbergue();
    private List<ServicioSituacionAlbergue> listaTempServicioSituacionAlbergue = new ArrayList<>();
    @ManagedProperty(value = "#{AlbAlbergueServicioImpl}")
    AlbAlbergueServicio albAlbergueServicio;
    private AlbAlbergue albAlbergue = new AlbAlbergue();
    private List<AlbAlbergue> listaTempAlbAlbergue = new ArrayList<>();
    private Long IdGeneral;
    //EDITAR SERVICIO AGUA
    private AlbSituacion selectedServicioEditar = new AlbSituacion();
    private AlbSituacion segSituacionObjects = new AlbSituacion();
    private Long IdEditar;
    //ELIMINAR SERVICIO AGUA
    private AlbSituacion selectedServicioAguaEliminar = new AlbSituacion();

    //EDITAR SERVICIO ELECTRICIDAD
    private AlbSituacion selectedElectriEditar = new AlbSituacion();
    private AlbSituacion segElectriObjects = new AlbSituacion();
    private Long IdEditarElectri;
    //ELIMINAR SERVICIO ELECTRICIDAD
    private AlbSituacion selectedServicioElectriEliminar = new AlbSituacion();
    
    //EDITAR SERVICIO TELEFONÍA E INTERNET
    private AlbSituacion selectedInterEditar = new AlbSituacion();
    private AlbSituacion segInterObjects = new AlbSituacion();
    private Long IdEditarInter;
    //ELIMINAR SERVICIO ELECTRICIDAD
    private AlbSituacion selectedServicioInterEliminar = new AlbSituacion();

    @PostConstruct
    public void init() {
        if (!guardadoCabecera) {
            albServicio = new AlbServicio();
            albEmpresa = new AlbEmpresa();
            servicioSituacionAlbergue = new ServicioSituacionAlbergue();
            albAlbergue = new AlbAlbergue();
            listaTempAlbServicio.clear();
            listaTempAlbEmpresa.clear();
            listaTempServicioSituacionAlbergue.clear();
            listaTempAlbAlbergue.clear();
            albSituacion = new AlbSituacion();
            listaTempAlbSituacion.clear();
            listaTipoEmpresa.clear();
            this.listaTipoEmpresa.addAll(getAlbSituacionServicio().listarTipoEmpresa());
            
        }
        listaServicio.clear();
        this.listaServicio.addAll(getAlbServicioServicio().listarServicio());
        listaServicioSituacionAlbergue.clear();
        this.listaServicioSituacionAlbergue.addAll(getServicioSituacionAlbergueServicio().listarServicioSituacionAlbergue());
        listaAlbSituacion.clear();
        this.listaAlbSituacion.addAll(getAlbSituacionServicio().listarAlbSituacion());
    }

    public List<SelectItem> getListaAgua1() {
        this.genListaSelected = new ArrayList<SelectItem>();
        for (AlbTipoEmpresa obj : listaTipoEmpresa) {
            SelectItem selectItem = new SelectItem(obj.getTieId(), obj.getTieNombre());
            this.genListaSelected.add(selectItem);
        }
        return genListaSelected;

    }

    public AlbServicioServicio getAlbServicioServicio() {
        return albServicioServicio;
    }

    public void setAlbServicioServicio(AlbServicioServicio albServicioServicio) {
        this.albServicioServicio = albServicioServicio;
    }

    public List<AlbServicio> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(List<AlbServicio> listaServicio) {
        this.listaServicio = listaServicio;
    }

    public List<ServicioSituacionAlbergue> getListaServicioSituacionAlbergue() {
        return listaServicioSituacionAlbergue;
    }

    public void setListaServicioSituacionAlbergue(List<ServicioSituacionAlbergue> listaServicioSituacionAlbergue) {
        this.listaServicioSituacionAlbergue = listaServicioSituacionAlbergue;
    }

    public ServicioSituacionAlbergueServicio getServicioSituacionAlbergueServicio() {
        return servicioSituacionAlbergueServicio;
    }

    public void setServicioSituacionAlbergueServicio(ServicioSituacionAlbergueServicio servicioSituacionAlbergueServicio) {
        this.servicioSituacionAlbergueServicio = servicioSituacionAlbergueServicio;
    }

    public AlbSituacionServicio getAlbSituacionServicio() {
        return albSituacionServicio;
    }

    public void setAlbSituacionServicio(AlbSituacionServicio albSituacionServicio) {
        this.albSituacionServicio = albSituacionServicio;
    }

    public List<AlbSituacion> getListaAlbSituacion() {
        return listaAlbSituacion;
    }

    public void setListaAlbSituacion(List<AlbSituacion> listaAlbSituacion) {
        this.listaAlbSituacion = listaAlbSituacion;
    }

    public List<AlbSituacion> getListaElectricidad() {
        return listaElectricidad;
    }

    public void setListaElectricidad(List<AlbSituacion> listaElectricidad) {
        this.listaElectricidad = listaElectricidad;
    }

    public List<AlbSituacion> getListaAgua() {
        return listaAgua;
    }

    public void setListaAgua(List<AlbSituacion> listaAgua) {
        this.listaAgua = listaAgua;
    }

    public List<AlbSituacion> getListaInternet() {
        return listaInternet;
    }

    public void setListaInternet(List<AlbSituacion> listaInternet) {
        this.listaInternet = listaInternet;
    }

    public AlbSituacion getAlbSituacion() {
        return albSituacion;
    }

    public void setAlbSituacion(AlbSituacion albSituacion) {
        this.albSituacion = albSituacion;
    }

    public Long getIdSeleccionAgua() {
        return IdSeleccionAgua;

    }

    public void setIdSeleccionAgua(Long IdSeleccionAgua) {
        this.IdSeleccionAgua = IdSeleccionAgua;
    }

    public AlbAlbergue getSelectedAlbergueAsignar() {
        return selectedAlbergueAsignar;
    }

    public void setSelectedAlbergueAsignar(AlbAlbergue selectedAlbergueAsignar) {
        this.selectedAlbergueAsignar = selectedAlbergueAsignar;
        this.obtenerAlbergue(selectedAlbergueAsignar);

    }
    private Long aux;

    public void obtenerAlbergue(AlbAlbergue objPUE) {
        try {
            listaAgua.clear();
            listaElectricidad.clear();
            listaInternet.clear();
            listaBateriasSanitarias.clear();
            listaServicioSalud.clear();
            listaDesechosSolidos.clear();
            listaEducacion.clear();
            listaCombustibleFosil.clear();
            listaAlimentos.clear();
            IdGeneral = objPUE.getAlbId();
            for (AlbSituacion obj : listaAlbSituacion) {
                aux = obj.getServicioSituacionAlbergue().getAlbAlbergue().getAlbId();
                if (aux == IdGeneral) {
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerNombre().equals("AGUA")) {
                        listaAgua.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerNombre().equals("ELECTRICIDAD")) {
                        listaElectricidad.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerNombre().equals("TELECOMUNICACIONES Y SERVICIO A INTERNET")) {
                        listaInternet.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 4) {
                        listaBateriasSanitarias.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 5) {
                        listaServicioSalud.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 14) {
                        listaDesechosSolidos.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 15) {
                        listaEducacion.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 16) {
                        listaCombustibleFosil.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 17) {
                        listaAlimentos.add(obj);
                    }
                }

            }

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cancelarCargaDatos() {
        albSituacion = new AlbSituacion();
    }

    public void guardarServicioAgua() {
        String nombre = albEmpresa.getEmpNombre();
        if ("".equals(nombre) || "".equals(albSituacion.getSitMetProvision()) || "".equals(albSituacion.getSitAlmacenamiento()) || "".equals(albSituacion.getSitAguaBebible())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                AlbTipoEmpresa objTipoEmpresa = new AlbTipoEmpresa();
                objTipoEmpresa.setTieId(IdSeleccionAgua);
                albEmpresa.setAlbTipoEmpresa(objTipoEmpresa);
                albEmpresa.setEmpEstado(1);
                listaTempAlbEmpresa.clear();
                listaTempAlbEmpresa.add(albEmpresa);
                getAlbServicioServicio().guardarEmpresa(listaTempAlbEmpresa);
                albServicio.setAlbEmpresa(albEmpresa);
                albServicio.setSerEstado(1);
                for (AlbServicio obj : listaServicio) {
                    if (obj.getSerNombre().equals("AGUA")) {
                        albServicio.setSerNombre(obj.getSerNombre());
                        listaTempAlbServicio.clear();
                        listaTempAlbServicio.add(albServicio);
                        getAlbServicioServicio().guardarServicio(listaTempAlbServicio);
                        servicioSituacionAlbergue.setAlbServicio(albServicio);
                        servicioSituacionAlbergue.setAlbAlbergue(selectedAlbergueAsignar);
                        servicioSituacionAlbergue.setSerAlbEstado(1);
                        listaTempServicioSituacionAlbergue.clear();
                        listaTempServicioSituacionAlbergue.add(servicioSituacionAlbergue);
                        getAlbServicioServicio().guardarServicioSituacionAlbergue(listaTempServicioSituacionAlbergue);
                        albSituacion.getSitMetProvision();
                        albSituacion.getSitAlmacenamiento();
                        albSituacion.getSitAguaBebible();
                        albSituacion.setSitMetProvision(albSituacion.getSitMetProvision());
                        albSituacion.setSitAlmacenamiento(albSituacion.getSitAlmacenamiento());
                        albSituacion.setSitAguaBebible(albSituacion.getSitAguaBebible());
                        albSituacion.setSitEstado(1);
                        albSituacion.setServicioSituacionAlbergue(servicioSituacionAlbergue);
                        listaTempAlbSituacion.clear();
                        listaAlbSituacion.add(albSituacion);
                        listaTempAlbSituacion.add(albSituacion);
                        getAlbServicioServicio().guardarSituacion(listaTempAlbSituacion);
                        albSituacion = new AlbSituacion();
                        guardadoCabecera = true;
                        mensajeEAS.info(true);
                        init();
                        this.obtenerAlbergue(selectedAlbergueAsignar);
                        break;
                    }
                }
            } catch (Exception ex) {
                guardadoCabecera = false;
                LOG.error("Error: " + ex.getMessage());
                mensajeEAS.error();
            }
        }

    }

    public void editarAguaSistema(AlbSituacion objPUE) {
        try {
            segSituacionObjects = new AlbSituacion();
            ServicioSituacionAlbergue objServSitAl = new ServicioSituacionAlbergue();
            objServSitAl.setSerAlbId(objPUE.getServicioSituacionAlbergue().getSerAlbId());
            AlbTipoEmpresa objTipoEmpresa = new AlbTipoEmpresa();
            objTipoEmpresa.setTieId(objPUE.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getAlbTipoEmpresa().getTieId());
            objTipoEmpresa.setTieNombre(objPUE.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getAlbTipoEmpresa().getTieNombre());
            AlbEmpresa objEmpresa = new AlbEmpresa();
            objEmpresa.setEmpId(objPUE.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpId());
            objEmpresa.setEmpNombre(objPUE.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpNombre());
            AlbServicio objServicio = new AlbServicio();
            objServicio.setSerId(objPUE.getServicioSituacionAlbergue().getAlbServicio().getSerId());
            objServicio.setSerNombre(objPUE.getServicioSituacionAlbergue().getAlbServicio().getSerNombre());
            AlbAlbergue objAlbergue = new AlbAlbergue();
            objAlbergue.setAlbId(objPUE.getServicioSituacionAlbergue().getAlbAlbergue().getAlbId());
            IdEditar = objPUE.getSitId();
            objPUE.getSitMetProvision();
            objPUE.getSitAlmacenamiento();
            objPUE.getSitAguaBebible();
            segSituacionObjects.setServicioSituacionAlbergue(objServSitAl);
            objEmpresa.setAlbTipoEmpresa(objTipoEmpresa);
            objServicio.setAlbEmpresa(objEmpresa);
            segSituacionObjects.getServicioSituacionAlbergue().setAlbServicio(objServicio);
            segSituacionObjects.getServicioSituacionAlbergue().setAlbAlbergue(objAlbergue);
            segSituacionObjects.setSitMetProvision(objPUE.getSitMetProvision());
            segSituacionObjects.setSitAlmacenamiento(objPUE.getSitAlmacenamiento());
            segSituacionObjects.setSitAguaBebible(objPUE.getSitAguaBebible());
            segSituacionObjects.setSitEstado(1);
        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public void editarServicioAgua() {

        try {
            AlbTipoEmpresa objTipoEmpresa = new AlbTipoEmpresa();
            AlbSituacion objSituacion = new AlbSituacion();
            AlbEmpresa objEmpresa = new AlbEmpresa();
            AlbServicio objServicio = new AlbServicio();
            ServicioSituacionAlbergue objServSitAl = new ServicioSituacionAlbergue();
            AlbAlbergue objAlbergue = new AlbAlbergue();
            if (IdSeleccionAgua == null) {
                objTipoEmpresa.setTieId(segSituacionObjects.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getAlbTipoEmpresa().getTieId());
            } else {
                objTipoEmpresa.setTieId(IdSeleccionAgua);
            }
            objSituacion.setSitId(IdEditar);
            objEmpresa.setAlbTipoEmpresa(objTipoEmpresa);
            objEmpresa.setEmpEstado(1);
            objEmpresa.setEmpId(segSituacionObjects.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpId());
            objEmpresa.setEmpNombre(segSituacionObjects.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpNombre());
            listaTempAlbEmpresa.clear();
            listaTempAlbEmpresa.add(objEmpresa);
            getAlbServicioServicio().guardarEmpresa(listaTempAlbEmpresa);
            objServicio.setAlbEmpresa(objEmpresa);
            objServicio.setSerNombre(segSituacionObjects.getServicioSituacionAlbergue().getAlbServicio().getSerNombre());
            objServicio.setSerId(segSituacionObjects.getServicioSituacionAlbergue().getAlbServicio().getSerId());
            objAlbergue.setAlbId(segSituacionObjects.getServicioSituacionAlbergue().getAlbAlbergue().getAlbId());
            objServSitAl.setAlbServicio(objServicio);
            objServSitAl.setAlbAlbergue(objAlbergue);
            objServSitAl.setSerAlbId(segSituacionObjects.getServicioSituacionAlbergue().getSerAlbId());
            objSituacion.setServicioSituacionAlbergue(objServSitAl);
            objSituacion.setSitMetProvision(segSituacionObjects.getSitMetProvision());
            objSituacion.setSitAlmacenamiento(segSituacionObjects.getSitAlmacenamiento());
            objSituacion.setSitAguaBebible(segSituacionObjects.getSitAguaBebible());
            objSituacion.setSitEstado(1);

            if ("".equals(objEmpresa.getEmpNombre()) || "".equals(albSituacion.getSitMetProvision()) || "".equals(albSituacion.getSitAlmacenamiento()) || "".equals(albSituacion.getSitAguaBebible())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempAlbSituacion.clear();
                listaTempAlbSituacion.add(objSituacion);
                getAlbServicioServicio().guardarSituacion(listaTempAlbSituacion);
                albSituacion = new AlbSituacion();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
                obtenerAlbergue(selectedAlbergueAsignar);
            }

        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();

        }
    }

    public void cargarTable() {
        albSituacion = new AlbSituacion();
        listaAlbSituacion.clear();
        this.listaAlbSituacion.addAll(getAlbSituacionServicio().listarAlbSituacion());
    }

    public void eliminarAguaSistema(AlbSituacion objPU) {
        try {
            objPU.setSitEstado(ParametrosObjetos.INACTIVO);
            getAlbServicioServicio().guardarServicioEliminar(objPU);
            mensajeEAS.Eliminar();
            this.cargarTable();
            this.obtenerAlbergue(selectedAlbergueAsignar);
        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public List<AlbSituacion> getListaBateriasSanitarias() {
        return listaBateriasSanitarias;
    }

    public void setListaBateriasSanitarias(List<AlbSituacion> listaBateriasSanitarias) {
        this.listaBateriasSanitarias = listaBateriasSanitarias;
    }

    public List<AlbSituacion> getListaServicioSalud() {
        return listaServicioSalud;
    }

    public void setListaServicioSalud(List<AlbSituacion> listaServicioSalud) {
        this.listaServicioSalud = listaServicioSalud;
    }

    public AlbServicio getAlbServicio() {
        return albServicio;
    }

    public void setAlbServicio(AlbServicio albServicio) {
        this.albServicio = albServicio;
    }

    public AlbEmpresa getAlbEmpresa() {
        return albEmpresa;
    }

    public void setAlbEmpresa(AlbEmpresa albEmpresa) {
        this.albEmpresa = albEmpresa;
    }

    public AlbAlbergueServicio getAlbAlbergueServicio() {
        return albAlbergueServicio;
    }

    public void setAlbAlbergueServicio(AlbAlbergueServicio albAlbergueServicio) {
        this.albAlbergueServicio = albAlbergueServicio;
    }

    public List<AlbSituacion> getListaDesechosSolidos() {
        return listaDesechosSolidos;
    }

    public void setListaDesechosSolidos(List<AlbSituacion> listaDesechosSolidos) {
        this.listaDesechosSolidos = listaDesechosSolidos;
    }

    public List<AlbSituacion> getListaEducacion() {
        return listaEducacion;
    }

    public void setListaEducacion(List<AlbSituacion> listaEducacion) {
        this.listaEducacion = listaEducacion;
    }

    public List<AlbSituacion> getListaCombustibleFosil() {
        return listaCombustibleFosil;
    }

    public void setListaCombustibleFosil(List<AlbSituacion> listaCombustibleFosil) {
        this.listaCombustibleFosil = listaCombustibleFosil;
    }

    public List<AlbSituacion> getListaAlimentos() {
        return listaAlimentos;
    }

    public void setListaAlimentos(List<AlbSituacion> listaAlimentos) {
        this.listaAlimentos = listaAlimentos;
    }

    public AlbSituacion getSegSituacionObjects() {
        return segSituacionObjects;
    }

    public void setSegSituacionObjects(AlbSituacion segSituacionObjects) {
        this.segSituacionObjects = segSituacionObjects;
    }

    public AlbSituacion getSelectedServicioEditar() {
        return selectedServicioEditar;
    }

    public void setSelectedServicioEditar(AlbSituacion selectedServicioEditar) {
        this.selectedServicioEditar = selectedServicioEditar;
        this.editarAguaSistema(selectedServicioEditar);
    }

    public AlbSituacion getSelectedServicioAguaEliminar() {
        return selectedServicioAguaEliminar;
    }

    public void setSelectedServicioAguaEliminar(AlbSituacion selectedServicioAguaEliminar) {
        this.selectedServicioAguaEliminar = selectedServicioAguaEliminar;
        eliminarAguaSistema(selectedServicioAguaEliminar);
    }

    public void guardarServicioElectricidad() {
        String nombre = albEmpresa.getEmpNombre();
        if ("".equals(nombre) || "".equals(albSituacion.getSitMetProvision()) || "".equals(albSituacion.getSitAlmacenamiento()) || "".equals(albSituacion.getSitAguaBebible())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                AlbTipoEmpresa objTipoEmpresa = new AlbTipoEmpresa();
                objTipoEmpresa.setTieId(IdSeleccionAgua);
                albEmpresa.setAlbTipoEmpresa(objTipoEmpresa);
                albEmpresa.setEmpEstado(1);
                listaTempAlbEmpresa.clear();
                listaTempAlbEmpresa.add(albEmpresa);
                getAlbServicioServicio().guardarEmpresa(listaTempAlbEmpresa);
                albServicio.setAlbEmpresa(albEmpresa);
                albServicio.setSerEstado(1);
                for (AlbServicio obj : listaServicio) {
                    if (obj.getSerNombre().equals("ELECTRICIDAD")) {
                        albServicio.setSerNombre(obj.getSerNombre());
                        listaTempAlbServicio.clear();
                        listaTempAlbServicio.add(albServicio);
                        getAlbServicioServicio().guardarServicio(listaTempAlbServicio);
                        servicioSituacionAlbergue.setAlbServicio(albServicio);
                        servicioSituacionAlbergue.setAlbAlbergue(selectedAlbergueAsignar);
                        servicioSituacionAlbergue.setSerAlbEstado(1);
                        listaTempServicioSituacionAlbergue.clear();
                        listaTempServicioSituacionAlbergue.add(servicioSituacionAlbergue);
                        getAlbServicioServicio().guardarServicioSituacionAlbergue(listaTempServicioSituacionAlbergue);
                        //SE MODIFICA DEPENDIENDO DE LAS ESPECIFICACIONES DEL SERVICIO
                        albSituacion.getSitMetProvision();
                        albSituacion.getSitAlcanceServicio();
                        albSituacion.setSitMetProvision(albSituacion.getSitMetProvision());
                        albSituacion.setSitAlcanceServicio(albSituacion.getSitAlcanceServicio());
                        albSituacion.setSitEstado(1);
                        albSituacion.setServicioSituacionAlbergue(servicioSituacionAlbergue);
                        listaTempAlbSituacion.clear();
                        listaAlbSituacion.add(albSituacion);
                        listaTempAlbSituacion.add(albSituacion);
                        getAlbServicioServicio().guardarSituacion(listaTempAlbSituacion);
                        albSituacion = new AlbSituacion();
                        guardadoCabecera = true;
                        mensajeEAS.info(true);
                        init();
                        this.obtenerAlbergue(selectedAlbergueAsignar);
                        break;
                    }
                }
            } catch (Exception ex) {
                guardadoCabecera = false;
                LOG.error("Error: " + ex.getMessage());
                mensajeEAS.error();
            }
        }

    }

    public AlbSituacion getSelectedElectriEditar() {
        return selectedElectriEditar;
    }

    public void setSelectedElectriEditar(AlbSituacion selectedElectriEditar) {
        this.selectedElectriEditar = selectedElectriEditar;
        editarElectricidadSistema(selectedElectriEditar);
    }

    public void editarElectricidadSistema(AlbSituacion objPUE) {
        try {
            segElectriObjects = new AlbSituacion();
            ServicioSituacionAlbergue objServSitAl = new ServicioSituacionAlbergue();
            objServSitAl.setSerAlbId(objPUE.getServicioSituacionAlbergue().getSerAlbId());
            AlbTipoEmpresa objTipoEmpresa = new AlbTipoEmpresa();
            objTipoEmpresa.setTieId(objPUE.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getAlbTipoEmpresa().getTieId());
            objTipoEmpresa.setTieNombre(objPUE.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getAlbTipoEmpresa().getTieNombre());
            AlbEmpresa objEmpresa = new AlbEmpresa();
            objEmpresa.setEmpId(objPUE.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpId());
            objEmpresa.setEmpNombre(objPUE.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpNombre());
            AlbServicio objServicio = new AlbServicio();
            objServicio.setSerId(objPUE.getServicioSituacionAlbergue().getAlbServicio().getSerId());
            objServicio.setSerNombre(objPUE.getServicioSituacionAlbergue().getAlbServicio().getSerNombre());
            AlbAlbergue objAlbergue = new AlbAlbergue();
            objAlbergue.setAlbId(objPUE.getServicioSituacionAlbergue().getAlbAlbergue().getAlbId());
            IdEditarElectri = objPUE.getSitId();
            objPUE.getSitMetProvision();
            objPUE.getSitAlcanceServicio();
            segElectriObjects.setServicioSituacionAlbergue(objServSitAl);
            objEmpresa.setAlbTipoEmpresa(objTipoEmpresa);
            objServicio.setAlbEmpresa(objEmpresa);
            segElectriObjects.getServicioSituacionAlbergue().setAlbServicio(objServicio);
            segElectriObjects.getServicioSituacionAlbergue().setAlbAlbergue(objAlbergue);
            segElectriObjects.setSitMetProvision(objPUE.getSitMetProvision());
            segElectriObjects.setSitAlcanceServicio(objPUE.getSitAlcanceServicio());
            segElectriObjects.setSitEstado(1);
        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbSituacion getSegElectriObjects() {
        return segElectriObjects;
    }

    public void setSegElectriObjects(AlbSituacion segElectriObjects) {
        this.segElectriObjects = segElectriObjects;
    }

    public void editarServicioElectricidad() {

        try {
            AlbTipoEmpresa objTipoEmpresa = new AlbTipoEmpresa();
            AlbSituacion objSituacion = new AlbSituacion();
            AlbEmpresa objEmpresa = new AlbEmpresa();
            AlbServicio objServicio = new AlbServicio();
            ServicioSituacionAlbergue objServSitAl = new ServicioSituacionAlbergue();
            AlbAlbergue objAlbergue = new AlbAlbergue();
            if (IdSeleccionAgua == null) {
                objTipoEmpresa.setTieId(segElectriObjects.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getAlbTipoEmpresa().getTieId());
            } else {
                objTipoEmpresa.setTieId(IdSeleccionAgua);
            }
            objSituacion.setSitId(IdEditarElectri);
            objEmpresa.setAlbTipoEmpresa(objTipoEmpresa);
            objEmpresa.setEmpEstado(1);
            objEmpresa.setEmpId(segElectriObjects.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpId());
            objEmpresa.setEmpNombre(segElectriObjects.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpNombre());
            listaTempAlbEmpresa.clear();
            listaTempAlbEmpresa.add(objEmpresa);
            getAlbServicioServicio().guardarEmpresa(listaTempAlbEmpresa);
            objServicio.setAlbEmpresa(objEmpresa);
            objServicio.setSerNombre(segElectriObjects.getServicioSituacionAlbergue().getAlbServicio().getSerNombre());
            objServicio.setSerId(segElectriObjects.getServicioSituacionAlbergue().getAlbServicio().getSerId());
            objAlbergue.setAlbId(segElectriObjects.getServicioSituacionAlbergue().getAlbAlbergue().getAlbId());
            objServSitAl.setAlbServicio(objServicio);
            objServSitAl.setAlbAlbergue(objAlbergue);
            objServSitAl.setSerAlbId(segElectriObjects.getServicioSituacionAlbergue().getSerAlbId());
            objSituacion.setServicioSituacionAlbergue(objServSitAl);
            objSituacion.setSitMetProvision(segElectriObjects.getSitMetProvision());
            objSituacion.setSitAlcanceServicio(segElectriObjects.getSitAlcanceServicio());
            objSituacion.setSitEstado(1);

            if ("".equals(objEmpresa.getEmpNombre()) || "".equals(albSituacion.getSitMetProvision()) || "".equals(albSituacion.getSitAlcanceServicio())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempAlbSituacion.clear();
                listaTempAlbSituacion.add(objSituacion);
                getAlbServicioServicio().guardarSituacion(listaTempAlbSituacion);
                albSituacion = new AlbSituacion();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
                obtenerAlbergue(selectedAlbergueAsignar);
            }

        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();

        }
    }

    public AlbSituacion getSelectedServicioElectriEliminar() {
        return selectedServicioElectriEliminar;
    }

    public void setSelectedServicioElectriEliminar(AlbSituacion selectedServicioElectriEliminar) {
        this.selectedServicioElectriEliminar = selectedServicioElectriEliminar;
        eliminarElectriSistema(selectedServicioElectriEliminar);
    }

    public void eliminarElectriSistema(AlbSituacion obj) {
        try {
            obj.setSitEstado(ParametrosObjetos.INACTIVO);
            getAlbServicioServicio().guardarServicioEliminar(obj);
            mensajeEAS.Eliminar();
            this.cargarTable();
            this.obtenerAlbergue(selectedAlbergueAsignar);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }

    public void guardarServicioInternet() {
        String nombre = albEmpresa.getEmpNombre();
        if ("".equals(nombre) || "".equals(albSituacion.getSitMetProvision()) || "".equals(albSituacion.getSitMetProvisionInternet()) || "".equals(albSituacion.getSitAlcanceServicio()) || "".equals(albSituacion.getSitAlcanceInternet())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                AlbTipoEmpresa objTipoEmpresa = new AlbTipoEmpresa();
                objTipoEmpresa.setTieId(IdSeleccionAgua);
                albEmpresa.setAlbTipoEmpresa(objTipoEmpresa);
                albEmpresa.setEmpEstado(1);
                listaTempAlbEmpresa.clear();
                listaTempAlbEmpresa.add(albEmpresa);
                getAlbServicioServicio().guardarEmpresa(listaTempAlbEmpresa);
                albServicio.setAlbEmpresa(albEmpresa);
                albServicio.setSerEstado(1);
                for (AlbServicio obj : listaServicio) {
                    if (obj.getSerNombre().equals("TELECOMUNICACIONES Y SERVICIO A INTERNET")) {
                        albServicio.setSerNombre(obj.getSerNombre());
                        listaTempAlbServicio.clear();
                        listaTempAlbServicio.add(albServicio);
                        getAlbServicioServicio().guardarServicio(listaTempAlbServicio);
                        servicioSituacionAlbergue.setAlbServicio(albServicio);
                        servicioSituacionAlbergue.setAlbAlbergue(selectedAlbergueAsignar);
                        servicioSituacionAlbergue.setSerAlbEstado(1);
                        listaTempServicioSituacionAlbergue.clear();
                        listaTempServicioSituacionAlbergue.add(servicioSituacionAlbergue);
                        getAlbServicioServicio().guardarServicioSituacionAlbergue(listaTempServicioSituacionAlbergue);
                        //SE MODIFICA DEPENDIENDO DE LAS ESPECIFICACIONES DEL SERVICIO
                        albSituacion.getSitMetProvision();
                        albSituacion.getSitMetProvisionInternet();
                        albSituacion.getSitAlcanceServicio();
                        albSituacion.getSitAlcanceInternet();
                        albSituacion.setSitMetProvision(albSituacion.getSitMetProvision());
                        albSituacion.setSitMetProvision(albSituacion.getSitMetProvisionInternet());
                        albSituacion.setSitAlcanceServicio(albSituacion.getSitAlcanceServicio());
                        albSituacion.setSitAlcanceServicio(albSituacion.getSitAlcanceInternet());
                        albSituacion.setSitEstado(1);
                        albSituacion.setServicioSituacionAlbergue(servicioSituacionAlbergue);
                        listaTempAlbSituacion.clear();
                        listaAlbSituacion.add(albSituacion);
                        listaTempAlbSituacion.add(albSituacion);
                        getAlbServicioServicio().guardarSituacion(listaTempAlbSituacion);
                        albSituacion = new AlbSituacion();
                        guardadoCabecera = true;
                        mensajeEAS.info(true);
                        init();
                        this.obtenerAlbergue(selectedAlbergueAsignar);
                        break;
                    }
                }
            } catch (Exception ex) {
                guardadoCabecera = false;
                LOG.error("Error: " + ex.getMessage());
                mensajeEAS.error();
            }
        }

    }

    public AlbSituacion getSelectedInterEditar() {
        return selectedInterEditar;
    }

    public void setSelectedInterEditar(AlbSituacion selectedInterEditar) {
        this.selectedInterEditar = selectedInterEditar;
        editarInternetSistema(selectedInterEditar);
    }
    
    public void editarInternetSistema(AlbSituacion obj) {
        try {
            segInterObjects = new AlbSituacion();
            ServicioSituacionAlbergue objServSitAl = new ServicioSituacionAlbergue();
            objServSitAl.setSerAlbId(obj.getServicioSituacionAlbergue().getSerAlbId());
            AlbTipoEmpresa objTipoEmpresa = new AlbTipoEmpresa();
            objTipoEmpresa.setTieId(obj.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getAlbTipoEmpresa().getTieId());
            objTipoEmpresa.setTieNombre(obj.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getAlbTipoEmpresa().getTieNombre());
            AlbEmpresa objEmpresa = new AlbEmpresa();
            objEmpresa.setEmpId(obj.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpId());
            objEmpresa.setEmpNombre(obj.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpNombre());
            AlbServicio objServicio = new AlbServicio();
            objServicio.setSerId(obj.getServicioSituacionAlbergue().getAlbServicio().getSerId());
            objServicio.setSerNombre(obj.getServicioSituacionAlbergue().getAlbServicio().getSerNombre());
            AlbAlbergue objAlbergue = new AlbAlbergue();
            objAlbergue.setAlbId(obj.getServicioSituacionAlbergue().getAlbAlbergue().getAlbId());
            IdEditarInter = obj.getSitId();
            obj.getSitMetProvision();
            obj.getSitAlcanceServicio();
            segInterObjects.setServicioSituacionAlbergue(objServSitAl);
            objEmpresa.setAlbTipoEmpresa(objTipoEmpresa);
            objServicio.setAlbEmpresa(objEmpresa);
            segInterObjects.getServicioSituacionAlbergue().setAlbServicio(objServicio);
            segInterObjects.getServicioSituacionAlbergue().setAlbAlbergue(objAlbergue);
            //VARÍA DEPENDIENDO DEL SERVICIO
            segInterObjects.setSitMetProvision(obj.getSitMetProvision());
            segInterObjects.setSitMetProvisionInternet(obj.getSitMetProvisionInternet());
            segInterObjects.setSitAlcanceServicio(obj.getSitAlcanceServicio());
            segInterObjects.setSitAlcanceInternet(obj.getSitAlcanceInternet());
            segInterObjects.setSitEstado(1);
        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbSituacion getSegInterObjects() {
        return segInterObjects;
    }

    public void setSegInterObjects(AlbSituacion segInterObjects) {
        this.segInterObjects = segInterObjects;
    }
    
    public void editarServicioInternet() {

        try {
            AlbTipoEmpresa objTipoEmpresa = new AlbTipoEmpresa();
            AlbSituacion objSituacion = new AlbSituacion();
            AlbEmpresa objEmpresa = new AlbEmpresa();
            AlbServicio objServicio = new AlbServicio();
            ServicioSituacionAlbergue objServSitAl = new ServicioSituacionAlbergue();
            AlbAlbergue objAlbergue = new AlbAlbergue();
            if (IdSeleccionAgua == null) {
                objTipoEmpresa.setTieId(segInterObjects.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getAlbTipoEmpresa().getTieId());
            } else {
                objTipoEmpresa.setTieId(IdSeleccionAgua);
            }
            objSituacion.setSitId(IdEditarInter);
            objEmpresa.setAlbTipoEmpresa(objTipoEmpresa);
            objEmpresa.setEmpEstado(1);
            objEmpresa.setEmpId(segInterObjects.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpId());
            objEmpresa.setEmpNombre(segInterObjects.getServicioSituacionAlbergue().getAlbServicio().getAlbEmpresa().getEmpNombre());
            listaTempAlbEmpresa.clear();
            listaTempAlbEmpresa.add(objEmpresa);
            getAlbServicioServicio().guardarEmpresa(listaTempAlbEmpresa);
            objServicio.setAlbEmpresa(objEmpresa);
            objServicio.setSerNombre(segInterObjects.getServicioSituacionAlbergue().getAlbServicio().getSerNombre());
            objServicio.setSerId(segInterObjects.getServicioSituacionAlbergue().getAlbServicio().getSerId());
            objAlbergue.setAlbId(segInterObjects.getServicioSituacionAlbergue().getAlbAlbergue().getAlbId());
            objServSitAl.setAlbServicio(objServicio);
            objServSitAl.setAlbAlbergue(objAlbergue);
            objServSitAl.setSerAlbId(segInterObjects.getServicioSituacionAlbergue().getSerAlbId());
            objSituacion.setServicioSituacionAlbergue(objServSitAl);
            //VARÍA DEPENDIENDO DEL SERVICIO
            objSituacion.setSitMetProvision(segInterObjects.getSitMetProvision());
            objSituacion.setSitMetProvisionInternet(segInterObjects.getSitMetProvisionInternet());
            objSituacion.setSitAlcanceServicio(segInterObjects.getSitAlcanceServicio());
            objSituacion.setSitAlcanceInternet(segInterObjects.getSitAlcanceInternet());
            objSituacion.setSitEstado(1);

            if ("".equals(objEmpresa.getEmpNombre()) || "".equals(albSituacion.getSitMetProvision()) || "".equals(albSituacion.getSitMetProvisionInternet()) || "".equals(albSituacion.getSitAlcanceServicio()) || "".equals(albSituacion.getSitAlcanceInternet())) {
            mensajeEAS.errorLlenarDatos();
        } else {
                listaTempAlbSituacion.clear();
                listaTempAlbSituacion.add(objSituacion);
                getAlbServicioServicio().guardarSituacion(listaTempAlbSituacion);
                albSituacion = new AlbSituacion();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
                obtenerAlbergue(selectedAlbergueAsignar);
            }

        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();

        }
    }

    public AlbSituacion getSelectedServicioInterEliminar() {
        return selectedServicioInterEliminar;
    }

    public void setSelectedServicioInterEliminar(AlbSituacion selectedServicioInterEliminar) {
        this.selectedServicioInterEliminar = selectedServicioInterEliminar;
        eliminarInternetSistema(selectedServicioInterEliminar);
    }
    
    
    public void eliminarInternetSistema(AlbSituacion obj) {
        try {
            obj.setSitEstado(ParametrosObjetos.INACTIVO);
            getAlbServicioServicio().guardarServicioEliminar(obj);
            mensajeEAS.Eliminar();
            this.cargarTable();
            this.obtenerAlbergue(selectedAlbergueAsignar);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }
    
}
