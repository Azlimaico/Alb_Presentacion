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

    //EDITAR SERVICIO
    private AlbSituacion selectedServicioEditar = new AlbSituacion();
    private AlbSituacion segSituacionObjects = new AlbSituacion();
    private Long estado, IdEditar;
    

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
    Long aux;

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
            IdEditar = objPUE.getAlbId();
            for (AlbSituacion obj : listaAlbSituacion) {
                aux = obj.getServicioSituacionAlbergue().getAlbAlbergue().getAlbId();
                if (aux == IdEditar) {
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerNombre().equals("AGUA")) {
                        listaAgua.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 2) {
                        listaElectricidad.clear();
                        listaElectricidad.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 3) {
                        listaInternet.clear();
                        listaInternet.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 4) {
                        listaBateriasSanitarias.clear();
                        listaBateriasSanitarias.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 5) {
                        listaServicioSalud.clear();
                        listaServicioSalud.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 14) {
                        listaDesechosSolidos.clear();
                        listaDesechosSolidos.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 15) {
                        listaEducacion.clear();
                        listaEducacion.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 16) {
                        listaCombustibleFosil.clear();
                        listaCombustibleFosil.add(obj);
                    }
                    if (obj.getServicioSituacionAlbergue().getAlbServicio().getSerId() == 17) {
                        listaAlimentos.clear();
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

    public void guardarServicio() {
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
                    if (obj.getSerId() == 1) {
                        albServicio.setSerNombre(obj.getSerNombre());
                        listaTempAlbServicio.clear();
                        listaTempAlbServicio.add(albServicio);
                        getAlbServicioServicio().guardarServicio(listaTempAlbServicio);
                        break;
                    }
                }
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

            } catch (Exception ex) {
                guardadoCabecera = false;
                LOG.error("Error: " + ex.getMessage());
                mensajeEAS.error();
            }
        }

    }

    public void editarServicioSistema(AlbSituacion objPUE) {
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
            mensajeEAS.error();
        }
    }

    public void editarServicio() {

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
        this.editarServicioSistema(selectedServicioEditar);
    }

}
