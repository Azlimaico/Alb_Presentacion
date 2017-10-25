/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.sistema.general;

import com.negocio.servicio.albergue.AlbAlbergueServicio;
import com.negocio.servicio.general.sistema.AlbCiudadesServicio;
import com.persistencia.albergue.AlbAlbergue;
import com.persistencia.general.sistema.AlbAvanceImplementacion;
import com.persistencia.general.sistema.AlbCanton;
import com.persistencia.general.sistema.AlbParroquia;
import com.persistencia.general.sistema.AlbProvincia;
import com.persistencia.parametros.sistema.ParametrosObjetos;
import com.presentacion.mensajes.MensajeEAS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import static org.hibernate.ejb.EntityManagerImpl.LOG;
import org.jboss.logging.Logger;
import org.primefaces.component.datatable.DataTable;

@ManagedBean
@ViewScoped
public class Albergue implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(Albergue.class);
    @ManagedProperty(value = "#{AlbAlbergueServicioImpl}")
    AlbAlbergueServicio albAlbergueServicio;
    private List<AlbAlbergue> listaAlbergue = new ArrayList<>();
    private List<AlbAlbergue> listaTempAlbAlbergue = new ArrayList<>();
    private AlbAlbergue albAlbergue = new AlbAlbergue();
    private MensajeEAS mensajeEAS = new MensajeEAS();
    private Boolean guardadoCabecera = false;

    AlbProvincia objAlbergue;
    List<AlbProvincia> listaProvincia = new ArrayList<>();
    private Long IdSeleccionCO;
    private List<AlbProvincia> listaProvincia1 = new ArrayList<>();
    @ManagedProperty(value = "#{AlbCiudadesServicioImpl}")
    AlbCiudadesServicio albCiudadesServicio;
    List<SelectItem> genListaSelected = new ArrayList<SelectItem>();
    private Long IdSeleccionCanton;
    List<SelectItem> genListaSelectedCanton = new ArrayList<SelectItem>();
    private List<AlbCanton> listaCanton = new ArrayList<>();
    private Long IdSeleccionParroquia;
    List<SelectItem> genListaSelectedParroquia = new ArrayList<SelectItem>();
    private List<AlbParroquia> listaParroquia = new ArrayList<>();
    private Long IdSeleccionAvanceImp;
    List<SelectItem> genListaSelectedAvanceImplementacion = new ArrayList<SelectItem>();
    private List<AlbAvanceImplementacion> listaAvanceImplementacion = new ArrayList<>();

    //EDITAR ALBERGUE
    private AlbAlbergue selectedAlbergueEditar = new AlbAlbergue();
    private AlbAlbergue segAlbergueObjects = new AlbAlbergue();
    private Long estado, IdEditar;


    //ELIMINAR ALBERGUE
    private AlbAlbergue selectedSegUsuarioEliminar = new AlbAlbergue();

    @PostConstruct
    public void init() {
        //Hola
        if (!guardadoCabecera) {
            listaTempAlbAlbergue.clear();
            albAlbergue = new AlbAlbergue();
            listaProvincia1.clear();
            this.listaProvincia1.addAll(getAlbCiudadesServicio().listarProvincias());
            listaCanton.clear();
            this.listaCanton.addAll(getAlbCiudadesServicio().listarCantones());
            listaParroquia.clear();
            this.listaParroquia.addAll(getAlbCiudadesServicio().listarParroquias());
            listaAvanceImplementacion.clear();
            this.listaAvanceImplementacion.addAll(getAlbCiudadesServicio().listarAvanceImplementacion());

        }
        listaAlbergue.clear();
        this.listaAlbergue.addAll(getAlbAlbergueServicio().listarAlbergue());

    }

    public void cancelarCargaDatos() {
        albAlbergue = new AlbAlbergue();
    }

    public void cargarTable() {
        albAlbergue = new AlbAlbergue();
        listaAlbergue.clear();
        this.listaAlbergue.addAll(getAlbAlbergueServicio().listarAlbergue());

    }

    public void guardarAlbergue() {
        if ("".equals(albAlbergue.getAlbNombre()) || "".equals(albAlbergue.getAlbDireccion()) || "".equals(albAlbergue.getAlbTipoAlbergue())
                || "".equals(albAlbergue.getAlbArea()) || "".equals(albAlbergue.getAlbCoordx()) || "".equals(albAlbergue.getAlbCoordy()) || "".equals(albAlbergue.getAlbObservaciones())) {
            mensajeEAS.errorLlenarDatos();
        } else {

            try {
                AlbProvincia obj = new AlbProvincia();
                AlbCanton objCanton = new AlbCanton();
                AlbParroquia objParroquia = new AlbParroquia();
                AlbAvanceImplementacion objAvaImp = new AlbAvanceImplementacion();
                obj.setProId(IdSeleccionCO);
                objCanton.setCanId(IdSeleccionCanton);
                objParroquia.setParId(IdSeleccionParroquia);
                objAvaImp.setAvaId(IdSeleccionAvanceImp);
                albAlbergue.setAlbProvincia(obj);
                albAlbergue.setAlbCanton(objCanton);
                albAlbergue.setAlbParroquia(objParroquia);
                albAlbergue.setAlbAvanceImplementacion(objAvaImp);
                albAlbergue.setAlbEstado(1);
                listaTempAlbAlbergue.clear();
                listaTempAlbAlbergue.add(albAlbergue);
                getAlbAlbergueServicio().guardarJuego(listaTempAlbAlbergue);
                albAlbergue = new AlbAlbergue();
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

    public List<SelectItem> getListaProvincia1() {
        this.genListaSelected = new ArrayList<SelectItem>();
        for (AlbProvincia obj : listaProvincia1) {
            SelectItem selectItem = new SelectItem(obj.getProId(), obj.getProNombre());
            this.genListaSelected.add(selectItem);
        }
        return genListaSelected;

    }

    public List<SelectItem> getListaCanton() {
        this.genListaSelectedCanton = new ArrayList<SelectItem>();
        if (IdSeleccionCO != null) {
            List<AlbCanton> lista = getAlbCiudadesServicio().listarCantonCmbx(IdSeleccionCO);
            for (AlbCanton obj : lista) {
                SelectItem selectItem = new SelectItem(obj.getCanId(), obj.getCanNombre());
                this.genListaSelectedCanton.add(selectItem);
            }
        }
        return genListaSelectedCanton;
    }


    public List<SelectItem> getListaParroquia() {
        this.genListaSelectedParroquia = new ArrayList<SelectItem>();
        if (IdSeleccionCanton != null) {
            List<AlbParroquia> Lista = getAlbCiudadesServicio().ListParroquiaCmbx(IdSeleccionCanton);
            for (AlbParroquia obj : Lista) {
                SelectItem selectItem = new SelectItem(obj.getParId(), obj.getParNombre());
                this.genListaSelectedParroquia.add(selectItem);
            }
        }
        return genListaSelectedParroquia;
    }

    public List<SelectItem> getListaAvanceImplementacion() {
        this.genListaSelectedAvanceImplementacion = new ArrayList<SelectItem>();
        for (AlbAvanceImplementacion obj : listaAvanceImplementacion) {
            SelectItem selectItem = new SelectItem(obj.getAvaId(), obj.getAvaNombre());
            this.genListaSelectedAvanceImplementacion.add(selectItem);
        }
        return genListaSelectedAvanceImplementacion;
    }

    public void editarUsuarioSistema(AlbAlbergue objPUE) {
        try {
            segAlbergueObjects = new AlbAlbergue();
            AlbProvincia obj = new AlbProvincia();
            obj.setProId(objPUE.getAlbProvincia().getProId());
            obj.setProNombre(objPUE.getAlbProvincia().getProNombre());
            AlbCanton objCanton = new AlbCanton();
            objCanton.setCanId(objPUE.getAlbCanton().getCanId());
            objCanton.setCanNombre(objPUE.getAlbCanton().getCanNombre());
            AlbParroquia objParroquia = new AlbParroquia();
            objParroquia.setParId(objPUE.getAlbParroquia().getParId());
            objParroquia.setParNombre(objPUE.getAlbParroquia().getParNombre());
            AlbAvanceImplementacion objAvaImp = new AlbAvanceImplementacion();
            objAvaImp.setAvaId(objPUE.getAlbAvanceImplementacion().getAvaId());
            objAvaImp.setAvaNombre(objPUE.getAlbAvanceImplementacion().getAvaNombre());
            IdEditar = objPUE.getAlbId();
            objPUE.getAlbDireccion();
            objPUE.getAlbTipoAlbergue();
            objPUE.getAlbArea();
            objPUE.getAlbCoordx();
            objPUE.getAlbCoordy();
            objPUE.getAlbObservaciones();
            segAlbergueObjects.setAlbNombre(objPUE.getAlbNombre());
            segAlbergueObjects.setAlbProvincia(obj);
            segAlbergueObjects.setAlbCanton(objCanton);
            segAlbergueObjects.setAlbParroquia(objParroquia);
            segAlbergueObjects.setAlbDireccion(objPUE.getAlbDireccion());
            segAlbergueObjects.setAlbTipoAlbergue(objPUE.getAlbTipoAlbergue());
            segAlbergueObjects.setAlbArea(objPUE.getAlbArea());
            segAlbergueObjects.setAlbAvanceImplementacion(objAvaImp);
            segAlbergueObjects.setAlbCoordx(objPUE.getAlbCoordx());
            segAlbergueObjects.setAlbCoordy(objPUE.getAlbCoordy());
            segAlbergueObjects.setAlbObservaciones(objPUE.getAlbObservaciones());
            segAlbergueObjects.setAlbEstado(1);
        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void eliminarUsuarioSistema(AlbAlbergue objPU) {
        try {

            objPU.setAlbEstado(ParametrosObjetos.INACTIVO);
            getAlbCiudadesServicio().guardarSegUsuario(objPU);
            mensajeEAS.Eliminar();
            cargarTable();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void actualizarUsuarioSistema() {
        try {
            AlbProvincia objPro = new AlbProvincia();
            AlbAlbergue objAlb = new AlbAlbergue();
            AlbCanton objCanton = new AlbCanton();
            AlbParroquia objParroquia = new AlbParroquia();
            AlbAvanceImplementacion objAvaImp = new AlbAvanceImplementacion();

            if (IdSeleccionCO == null) {
                objPro.setProId(segAlbergueObjects.getAlbProvincia().getProId());
            } else {
                objPro.setProId(IdSeleccionCO);
            }
            if (IdSeleccionCanton == null) {
                objCanton.setCanId(segAlbergueObjects.getAlbCanton().getCanId());
            } else {
                objCanton.setCanId(IdSeleccionCanton);
            }
            if (IdSeleccionParroquia == null) {
                objParroquia.setParId(segAlbergueObjects.getAlbParroquia().getParId());
            } else {
                objParroquia.setParId(IdSeleccionParroquia);
            }
            if (IdSeleccionAvanceImp == null) {
                objAvaImp.setAvaId(segAlbergueObjects.getAlbAvanceImplementacion().getAvaId());
            } else {
                objAvaImp.setAvaId(IdSeleccionAvanceImp);
            }
            objAlb.setAlbId(IdEditar);
            objAlb.setAlbNombre(segAlbergueObjects.getAlbNombre());
            objAlb.setAlbProvincia(objPro);
            objAlb.setAlbCanton(objCanton);
            objAlb.setAlbParroquia(objParroquia);
            objAlb.setAlbDireccion(segAlbergueObjects.getAlbDireccion());
            objAlb.setAlbTipoAlbergue(segAlbergueObjects.getAlbTipoAlbergue());
            objAlb.setAlbArea(segAlbergueObjects.getAlbArea());
            objAlb.setAlbAvanceImplementacion(objAvaImp);
            objAlb.setAlbCoordx(segAlbergueObjects.getAlbCoordx());
            objAlb.setAlbCoordy(segAlbergueObjects.getAlbCoordy());
            objAlb.setAlbObservaciones(segAlbergueObjects.getAlbObservaciones());
            objAlb.setAlbEstado(1);

            if ("".equals(albAlbergue.getAlbNombre()) || "".equals(albAlbergue.getAlbDireccion()) || "".equals(albAlbergue.getAlbTipoAlbergue())
                    || "".equals(albAlbergue.getAlbArea()) || "".equals(albAlbergue.getAlbCoordx()) || "".equals(albAlbergue.getAlbCoordy()) || "".equals(albAlbergue.getAlbObservaciones())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempAlbAlbergue.clear();
                listaTempAlbAlbergue.add(objAlb);
                getAlbAlbergueServicio().guardarJuego(listaTempAlbAlbergue);
                albAlbergue = new AlbAlbergue();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }

        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public AlbAlbergueServicio getAlbAlbergueServicio() {
        return albAlbergueServicio;
    }

    public void setAlbAlbergueServicio(AlbAlbergueServicio albAlbergueServicio) {
        this.albAlbergueServicio = albAlbergueServicio;
    }

    public List<AlbAlbergue> getListaAlbergue() {
        return listaAlbergue;
    }

    public void setListaAlbergue(List<AlbAlbergue> listaAlbergue) {
        this.listaAlbergue = listaAlbergue;
    }

    public AlbAlbergue getAlbAlbergue() {
        return albAlbergue;
    }

    public void setAlbAlbergue(AlbAlbergue albAlbergue) {
        this.albAlbergue = albAlbergue;
    }

    public Long getIdSeleccionCO() {
        return IdSeleccionCO;
    }

    public void setIdSeleccionCO(Long IdSeleccionCO) {
        this.IdSeleccionCO = IdSeleccionCO;
    }

    public Long getIdSeleccionCanton() {
        return IdSeleccionCanton;
    }

    public void setIdSeleccionCanton(Long IdSeleccionCanton) {
        this.IdSeleccionCanton = IdSeleccionCanton;
    }

    public Long getIdSeleccionParroquia() {
        return IdSeleccionParroquia;
    }

    public void setIdSeleccionParroquia(Long IdSeleccionParroquia) {
        this.IdSeleccionParroquia = IdSeleccionParroquia;
    }

    public Long getIdSeleccionAvanceImp() {
        return IdSeleccionAvanceImp;
    }

    public void setIdSeleccionAvanceImp(Long IdSeleccionAvanceImp) {
        this.IdSeleccionAvanceImp = IdSeleccionAvanceImp;
    }

    public AlbCiudadesServicio getAlbCiudadesServicio() {
        return albCiudadesServicio;
    }

    public void setAlbCiudadesServicio(AlbCiudadesServicio albCiudadesServicio) {
        this.albCiudadesServicio = albCiudadesServicio;
    }

    public AlbAlbergue getSelectedAlbergueEditar() {
        return selectedAlbergueEditar;
    }

    public void setSelectedAlbergueEditar(AlbAlbergue selectedAlbergueEditar) {
        this.selectedAlbergueEditar = selectedAlbergueEditar;
        editarUsuarioSistema(selectedAlbergueEditar);

    }

    public AlbAlbergue getSegAlbergueObjects() {
        return segAlbergueObjects;
    }

    public void setSegAlbergueObjects(AlbAlbergue segAlbergueObjects) {
        this.segAlbergueObjects = segAlbergueObjects;
    }

    public AlbAlbergue getSelectedSegUsuarioEliminar() {
        return selectedSegUsuarioEliminar;
    }

    public void setSelectedSegUsuarioEliminar(AlbAlbergue selectedSegUsuarioEliminar) {
        this.selectedSegUsuarioEliminar = selectedSegUsuarioEliminar;
        eliminarUsuarioSistema(selectedSegUsuarioEliminar);
    }

    
}
