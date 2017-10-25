/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.sistema.general;

import com.negocio.servicio.general.sistema.AlbCiudadesServicio;
import com.persistencia.general.sistema.AlbCanton;
import com.persistencia.general.sistema.AlbParroquia;
import com.persistencia.general.sistema.AlbProvincia;
import com.persistencia.parametros.sistema.ParametrosObjetos;
import com.presentacion.mensajes.MensajeEAS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.jboss.logging.Logger;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Zulay
 */
@ManagedBean
@ViewScoped
public class Mantenimiento_prov implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(Mantenimiento_prov.class);
   
    @ManagedProperty(value = "#{AlbCiudadesServicioImpl}")
    AlbCiudadesServicio albCiudadesServicio;
    
    //PROVINCIA
    private List<AlbProvincia> listaProvincia = new ArrayList();
    private List<AlbProvincia> listaTempProvincia = new ArrayList();
    private AlbProvincia albProvincia = new AlbProvincia();
    List<SelectItem> genListaSelected = new ArrayList<SelectItem>();
    //EDITAR PROVINCIA
    private AlbProvincia selectedProvinciaEditar = new AlbProvincia();
    private AlbProvincia segProvinciaObjects = new AlbProvincia();
    private Long IdEditarProvincia;
    //ELIMINAR PROVINCIA
    private AlbProvincia selectedProvinciaEliminar = new AlbProvincia();
    
    //CANTÓN
    private List<AlbCanton> listaCanton = new ArrayList();
    private List<AlbCanton> listaTempCanton = new ArrayList();
    private AlbCanton albCanton = new AlbCanton();
    List<SelectItem> genListaSelectedCanton = new ArrayList<SelectItem>();
    //EDITAR CANTÓN
    private AlbCanton selectedCantonEditar = new AlbCanton();
    private AlbCanton segCantonObjects = new AlbCanton();
    private Long IdEditarCanton;
    //ELIMINAR CANTÓN
    private AlbCanton selectedCantonEliminar = new AlbCanton();
    
    //PARROQUIA
    private List<AlbParroquia> listaParroquia = new ArrayList();
    private List<AlbParroquia> listaTempParroq = new ArrayList();
    AlbParroquia albParroquia = new AlbParroquia();
     //EDITAR PARROQUIA
    private AlbParroquia selectedParroqEditar = new AlbParroquia();
    private AlbParroquia segParroqObjects = new AlbParroquia();
    private Long IdEditarParroquia;
    //ELIMINAR PARROQUIA
    private AlbParroquia selectedParroqEliminar = new AlbParroquia();
    

    private Boolean guardadoCabecera = false;
    private MensajeEAS mensajeEAS = new MensajeEAS();

    @PostConstruct
    public void init() {
        if (!guardadoCabecera) {
            listaCanton.clear();
            listaParroquia.clear();
            listaProvincia.clear();
        }
        listaCanton.clear();
        this.listaCanton.addAll(getAlbCiudadesServicio().listarCantones());
        listaParroquia.clear();
        this.listaParroquia.addAll(getAlbCiudadesServicio().listarParroquias());
        listaProvincia.clear();
        this.listaProvincia.addAll(getAlbCiudadesServicio().listarProvincias());
    }

    public List<AlbProvincia> getListaProvincia() {
        return listaProvincia;
    }

    public void setListaProvincia(List<AlbProvincia> listaProvincia) {
        this.listaProvincia = listaProvincia;
    }

    public List<AlbCanton> getListaCanton() {
        return listaCanton;
    }

    public void setListaCanton(List<AlbCanton> listaCanton) {
        this.listaCanton = listaCanton;
    }

    public AlbCiudadesServicio getAlbCiudadesServicio() {
        return albCiudadesServicio;
    }

    public void setAlbCiudadesServicio(AlbCiudadesServicio albCiudadesServicio) {
        this.albCiudadesServicio = albCiudadesServicio;
    }

    public List<AlbParroquia> getListaParroquia() {
        return listaParroquia;
    }

    public void setListaParroquia(List<AlbParroquia> listaParroquia) {
        this.listaParroquia = listaParroquia;

    }

    public AlbParroquia getAlbParroquia() {
        return albParroquia;
    }

    public void setAlbParroquia(AlbParroquia albParroquia) {
        this.albParroquia = albParroquia;
    }

    public AlbProvincia getAlbProvincia() {
        return albProvincia;
    }

    public void setAlbProvincia(AlbProvincia albProvincia) {
        this.albProvincia = albProvincia;
    }

    public void guardarProvincia() {
        if ("".equals(albProvincia.getProNombre())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                albProvincia.setProEstado(1);
                listaTempProvincia.clear();
                listaTempProvincia.add(albProvincia);
                getAlbCiudadesServicio().guardarProvincia(listaTempProvincia);
                albProvincia = new AlbProvincia();
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

    public AlbProvincia getSelectedProvinciaEditar() {
        return selectedProvinciaEditar;
    }

    public void setSelectedProvinciaEditar(AlbProvincia selectedProvinciaEditar) {
        this.selectedProvinciaEditar = selectedProvinciaEditar;
        editarProvinciaSistema(selectedProvinciaEditar);
    }

    public void editarProvinciaSistema(AlbProvincia objP) {
        try {
            segProvinciaObjects = new AlbProvincia();
            IdEditarProvincia = objP.getProId();
            objP.getProNombre();
            segProvinciaObjects.setProNombre(objP.getProNombre());
            segProvinciaObjects.setProEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();

        }

    }

    public AlbProvincia getSegProvinciaObjects() {
        return segProvinciaObjects;
    }

    public void setSegProvinciaObjects(AlbProvincia segProvinciaObjects) {
        this.segProvinciaObjects = segProvinciaObjects;
    }

    public void actualizarProvinciaSistema() {
        try {
            AlbProvincia objProvi = new AlbProvincia();
            objProvi.setProId(IdEditarProvincia);
            objProvi.setProNombre(segProvinciaObjects.getProNombre());
            objProvi.setProEstado(1);
            if ("".equals(albProvincia.getProNombre())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempProvincia.clear();
                listaTempProvincia.add(objProvi);
                getAlbCiudadesServicio().guardarProvincia(listaTempProvincia);
                albProvincia = new AlbProvincia();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbProvincia getSelectedProvinciaEliminar() {
        return selectedProvinciaEliminar;
    }

    public void setSelectedProvinciaEliminar(AlbProvincia selectedProvinciaEliminar) {
        this.selectedProvinciaEliminar = selectedProvinciaEliminar;
        eliminarProvinciaSistema(selectedProvinciaEliminar);
    }

    public void eliminarProvinciaSistema(AlbProvincia objP) {
        try {

            objP.setProEstado(ParametrosObjetos.INACTIVO);
            getAlbCiudadesServicio().guardarProvinciaEl(objP);
            mensajeEAS.Eliminar();
            cargarTable();

        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTable() {
        albProvincia = new AlbProvincia();
        listaProvincia.clear();
        this.listaProvincia.addAll(getAlbCiudadesServicio().listarProvincias());
    }

    public AlbCanton getAlbCanton() {
        return albCanton;
    }

    public void setAlbCanton(AlbCanton albCanton) {
        this.albCanton = albCanton;
    }

    public void guardarCanton() {
        if ("".equals(albCanton.getCanNombre())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                AlbProvincia obj = new AlbProvincia();
                obj.setProId(IdEditarProvincia);
                albCanton.setAlbProvincia(obj);
                albCanton.setCanEstado(1);
                listaTempCanton.clear();
                listaTempCanton.add(albCanton);
                getAlbCiudadesServicio().guardarCanton(listaTempCanton);
                albCanton = new AlbCanton();
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

    public AlbCanton getSelectedCantonEditar() {
        return selectedCantonEditar;
    }

    public void setSelectedCantonEditar(AlbCanton selectedCantonEditar) {
        this.selectedCantonEditar = selectedCantonEditar;
        editarCantonSistema(selectedCantonEditar);
    }
    
    public void editarCantonSistema(AlbCanton objP) {
        try {
            segCantonObjects = new AlbCanton();
            AlbProvincia obj = new AlbProvincia();
            obj.setProId(objP.getAlbProvincia().getProId());
            obj.setProNombre(objP.getAlbProvincia().getProNombre());
            IdEditarCanton = objP.getCanId();
            objP.getCanNombre();
            segCantonObjects.setAlbProvincia(obj);
            segCantonObjects.setCanNombre(objP.getCanNombre());
            segCantonObjects.setCanEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();

        }

    }

    public AlbCanton getSegCantonObjects() {
        return segCantonObjects;
    }

    public void setSegCantonObjects(AlbCanton segCantonObjects) {
        this.segCantonObjects = segCantonObjects;
    }
    
    public void actualizarCantonSistema() {
        try {
            AlbProvincia objPro = new AlbProvincia();
             if (IdEditarProvincia == null) {
                objPro.setProId(segCantonObjects.getAlbProvincia().getProId());
            } else {
                objPro.setProId(IdEditarProvincia);
            }
            AlbCanton objCan = new AlbCanton();
            objCan.setCanId(IdEditarCanton);
            objCan.setAlbProvincia(objPro);
            objCan.setCanNombre(segCantonObjects.getCanNombre());
            objCan.setCanEstado(1);
            if ("".equals(albCanton.getCanNombre())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempCanton.clear();
                listaTempCanton.add(objCan);
                getAlbCiudadesServicio().guardarCanton(listaTempCanton);
                albCanton = new AlbCanton();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbCanton getSelectedCantonEliminar() {
        return selectedCantonEliminar;
    }

    public void setSelectedCantonEliminar(AlbCanton selectedCantonEliminar) {
        this.selectedCantonEliminar = selectedCantonEliminar;
        eliminarCantonSistema(selectedCantonEliminar);
    }
    
    public void eliminarCantonSistema(AlbCanton objP) {
        try {

            objP.setCanEstado(ParametrosObjetos.INACTIVO);
            getAlbCiudadesServicio().guardarCantonEl(objP);
            mensajeEAS.Eliminar();
            cargarTableCanton();

        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTableCanton() {
        albCanton = new AlbCanton();
        listaCanton.clear();
        this.listaCanton.addAll(getAlbCiudadesServicio().listarCantones());
    }
    
    public Long getIdEditarCanton() {
        return IdEditarCanton;
    }

    public void setIdEditarCanton(Long IdEditarCanton) {
        this.IdEditarCanton = IdEditarCanton;
    }
    
    public Long getIdEditarProvincia() {
        return IdEditarProvincia;
    }

    public void setIdEditarProvincia(Long IdEditarProvincia) {
        this.IdEditarProvincia = IdEditarProvincia;
    }
    
    public List<SelectItem> getListaProvincia1() {
        this.genListaSelected = new ArrayList<SelectItem>();
        for (AlbProvincia obj : listaProvincia) {
            SelectItem selectItem = new SelectItem(obj.getProId(), obj.getProNombre());
            this.genListaSelected.add(selectItem);
        }
        return genListaSelected;
    }
    
     public List<SelectItem> getListaCanton1() {
        this.genListaSelectedCanton = new ArrayList<SelectItem>();
        if (IdEditarProvincia != null) {
            List<AlbCanton> lista = getAlbCiudadesServicio().listarCantonCmbx(IdEditarProvincia);
            for (AlbCanton obj : lista) {
                SelectItem selectItem = new SelectItem(obj.getCanId(), obj.getCanNombre());
                this.genListaSelectedCanton.add(selectItem);
            }
        }
        return genListaSelectedCanton;
    }
     
     public void guardarParroquia() {
        if ("".equals(albParroquia.getParNombre())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                AlbProvincia obj = new AlbProvincia();
                AlbCanton objCanton = new AlbCanton();
                AlbParroquia objParroquia = new AlbParroquia();
                obj.setProId(IdEditarProvincia);
                objCanton.setCanId(IdEditarCanton);
                albCanton.setAlbProvincia(obj);
                albParroquia.setAlbCanton(objCanton);
                albParroquia.setParEstado(1);
                albParroquia.getAlbCanton().setAlbProvincia(obj);
                listaTempParroq.clear();
                listaTempParroq.add(albParroquia);
                getAlbCiudadesServicio().guardarParroquia(listaTempParroq);
                albParroquia = new AlbParroquia();
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

    public AlbParroquia getSelectedParroqEditar() {
        return selectedParroqEditar;
    }

    public void setSelectedParroqEditar(AlbParroquia selectedParroqEditar) {
        this.selectedParroqEditar = selectedParroqEditar;
        editarParroquiaSistema(selectedParroqEditar);
    }
     
      public void editarParroquiaSistema(AlbParroquia objP) {
        try {
            segParroqObjects = new AlbParroquia();
            AlbProvincia obj = new AlbProvincia();
            obj.setProId(objP.getAlbCanton().getAlbProvincia().getProId());
            obj.setProNombre(objP.getAlbCanton().getAlbProvincia().getProNombre());
            AlbCanton objCanton = new AlbCanton();
            objCanton.setCanId(objP.getAlbCanton().getCanId());
            objCanton.setCanNombre(objP.getAlbCanton().getCanNombre());
            objCanton.setAlbProvincia(obj);
            IdEditarParroquia = objP.getParId();
            objP.getParNombre();
            segParroqObjects.setAlbCanton(objCanton);
            segParroqObjects.setParNombre(objP.getParNombre());
            segParroqObjects.setParEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();

        }

    }

    public AlbParroquia getSegParroqObjects() {
        return segParroqObjects;
    }

    public void setSegParroqObjects(AlbParroquia segParroqObjects) {
        this.segParroqObjects = segParroqObjects;
    }
      
    public void actualizarParroquiaSistema() {
        try {
            AlbProvincia objPro = new AlbProvincia();
            AlbCanton objCanton = new AlbCanton();
            if (IdEditarProvincia == null) {
                objPro.setProId(segParroqObjects.getAlbCanton().getAlbProvincia().getProId());
            } else {
                objPro.setProId(IdEditarProvincia);
            }
            if (IdEditarCanton == null) {
                objCanton.setCanId(segParroqObjects.getAlbCanton().getCanId());
            } else {
                objCanton.setCanId(IdEditarCanton);
            }
            
            AlbParroquia objParr = new AlbParroquia();
            objParr.setParId(IdEditarParroquia);
            objCanton.setAlbProvincia(objPro);
            objParr.setAlbCanton(objCanton);
            objParr.setParNombre(segParroqObjects.getParNombre());
            objParr.setParEstado(1);
            if ("".equals(albParroquia.getParNombre())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempParroq.clear();
                listaTempParroq.add(objParr);
                getAlbCiudadesServicio().guardarParroquia(listaTempParroq);
                albParroquia = new AlbParroquia();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbParroquia getSelectedParroqEliminar() {
        return selectedParroqEliminar;
    }

    public void setSelectedParroqEliminar(AlbParroquia selectedParroqEliminar) {
        this.selectedParroqEliminar = selectedParroqEliminar;
        eliminarParroquiaSistema(selectedParroqEliminar);
    }
    
    public void eliminarParroquiaSistema(AlbParroquia objP) {
        try {

            objP.setParEstado(ParametrosObjetos.INACTIVO);
            getAlbCiudadesServicio().guardarParroquiaEl(objP);
            mensajeEAS.Eliminar();
            cargarTableParroq();

        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTableParroq() {
        albParroquia = new AlbParroquia();
        listaParroquia.clear();
        this.listaParroquia.addAll(getAlbCiudadesServicio().listarParroquias());
    }
    
}

