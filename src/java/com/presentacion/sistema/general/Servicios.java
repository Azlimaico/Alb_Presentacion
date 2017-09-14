/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.sistema.general;

import com.negocio.servicio.albergue.ServicioSituacionAlbergueServicio;
import com.negocio.servicio.albergue.servicio.basico.AlbServicioServicio;
import com.negocio.servicio.albergue.servicio.basico.AlbSituacionServicio;
import com.persistencia.albergue.ServicioSituacionAlbergue;
import com.persistencia.albergue.servicio.AlbServicio;
import com.persistencia.albergue.servicio.AlbSituacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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

    //SERVICIO SITUACION ALBERGUE
    @ManagedProperty(value = "#{ServicioSituacionAlbergueServicioImpl}")
    ServicioSituacionAlbergueServicio servicioSituacionAlbergueServicio;
    private List<ServicioSituacionAlbergue> listaServicioSituacionAlbergue = new ArrayList<>();

    //SITUACION
    @ManagedProperty(value = "#{AlbSituacionServicioImpl}")
    AlbSituacionServicio albSituacionServicio;
    private List<AlbSituacion> listaAlbSituacion = new ArrayList<>();

    @PostConstruct
    public void init() {
        if (!guardadoCabecera) {

        }
        listaServicio.clear();
        this.listaServicio.addAll(getAlbServicioServicio().listarServicio());
        listaServicioSituacionAlbergue.clear();
        this.listaServicioSituacionAlbergue.addAll(getServicioSituacionAlbergueServicio().listarServicioSituacionAlbergue());
        listaAlbSituacion.clear();
        this.listaAlbSituacion.addAll(getAlbSituacionServicio().listarAlbSituacion());
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

    
}
