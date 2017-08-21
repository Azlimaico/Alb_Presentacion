/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.sistema.general;

import com.negocio.servicio.albergue.AlbAlbergueServicio;
import com.negocio.servicio.general.sistema.AlbCiudadesServicio;
import com.persistencia.general.sistema.AlbAvanceImplementacion;
import com.persistencia.general.sistema.AlbCanton;
import com.persistencia.general.sistema.AlbParroquia;
import com.persistencia.general.sistema.AlbProvincia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class Ciudades implements Serializable {

    private AlbProvincia albProvincia = new AlbProvincia();
    private List<AlbProvincia> listaProvincia = new ArrayList<>();

    private static final long serialVersionUID = 1L;
    private static org.jboss.logging.Logger LOG = org.jboss.logging.Logger.getLogger(Ciudades.class);
    @ManagedProperty(value = "#{AlbCiudadesServicioImpl}")
    AlbCiudadesServicio albCiudadesServicio;

    //PARA VER LAS PROVINCIAS EN UN COMBOBOX
    private Long IdSeleccionCO;
    List<SelectItem> genListaSelected = new ArrayList<SelectItem>();
    private List<AlbProvincia> listaProvincia1 = new ArrayList<>();
    @ManagedProperty(value = "#{AlbAlbergueServicioImp}")
    AlbAlbergueServicio albAlbergueServicio;
    String IdSeleccionProvincia = null;
    private AlbProvincia provincia = new AlbProvincia();
    String IdSeleccionCanton = null;
    private AlbCanton canton = new AlbCanton();
    List<SelectItem> genListaSelectedCanton = new ArrayList<SelectItem>();
    private List<AlbCanton> listaCanton = new ArrayList<>();
    List<SelectItem> genListaSelectedParroquia = new ArrayList<SelectItem>();
    private List<AlbParroquia> listaParroquia = new ArrayList<>();
    List<SelectItem> genListaSelectedAvanceImplementacion = new ArrayList<SelectItem>();
    private List<AlbAvanceImplementacion> listaAvanceImplementacion = new ArrayList<>();

    @PostConstruct
    public void init() {
        albProvincia = new AlbProvincia();
        provincia = new AlbProvincia();

        listaProvincia1.clear();
        this.listaProvincia1.addAll(getAlbCiudadesServicio().listarProvincias());
        listaCanton.clear();
        this.listaCanton.addAll(getAlbCiudadesServicio().listarCantones());
        listaParroquia.clear();
        this.listaParroquia.addAll(getAlbCiudadesServicio().listarParroquias());
        listaAvanceImplementacion.clear();
        this.listaAvanceImplementacion.addAll(getAlbCiudadesServicio().listarAvanceImplementacion());

    }

    public void guardarProvincia() {
        try {
            albProvincia.setProNombre("Pichincha");
            albProvincia.setProEstado(1);
            listaProvincia.clear();
            listaProvincia.add(albProvincia);
            getAlbCiudadesServicio().guardarProvincia(listaProvincia);
            albProvincia = new AlbProvincia();
            init();
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
        }
    }

    public List<SelectItem> getListaCanton() {
        this.genListaSelectedCanton = new ArrayList<SelectItem>();
        for (AlbCanton obj : listaCanton) {
            SelectItem selectItem = new SelectItem(obj.getCanId(), obj.getCanNombre());
            this.genListaSelectedCanton.add(selectItem);
        }
        return genListaSelectedCanton;
    }

    public List<SelectItem> getListaProvincia1() {
        this.genListaSelected = new ArrayList<SelectItem>();
        for (AlbProvincia obj : listaProvincia1) {
            SelectItem selectItem = new SelectItem(obj.getProId(), obj.getProNombre());
            this.genListaSelected.add(selectItem);
        }
        return genListaSelected;
    }

    public List<SelectItem> getListaParroquia() {
        this.genListaSelectedParroquia = new ArrayList<SelectItem>();
        for (AlbParroquia obj : listaParroquia) {
            SelectItem selectItem = new SelectItem(obj.getParId(), obj.getParNombre());
            this.genListaSelectedParroquia.add(selectItem);
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

    public AlbCiudadesServicio getAlbCiudadesServicio() {
        return albCiudadesServicio;
    }

    public void setAlbCiudadesServicio(AlbCiudadesServicio albCiudadesServicio) {
        this.albCiudadesServicio = albCiudadesServicio;
    }

    public List<AlbProvincia> getListaProvincia() {
        return listaProvincia;
    }

    public void setListaProvincia(List<AlbProvincia> listaProvincia) {
        this.listaProvincia = listaProvincia;
    }

    public Long getIdSeleccionCO() {
        return IdSeleccionCO;
    }

    public void setIdSeleccionCO(Long IdSeleccionCO) {
        this.IdSeleccionCO = IdSeleccionCO;
    }

    public AlbAlbergueServicio getAlbAlbergueServicio() {
        return albAlbergueServicio;
    }

    public void setAlbAlbergueServicio(AlbAlbergueServicio albAlbergueServicio) {
        this.albAlbergueServicio = albAlbergueServicio;
    }

    public AlbProvincia getProvincia() {
        return provincia;
    }

    public void setProvincia(AlbProvincia provincia) {
        this.provincia = provincia;
    }

}
