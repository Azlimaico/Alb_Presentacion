/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.sistema.general;

import com.negocio.servicio.seguridad.sistema.AlbMenuServicio;
import com.negocio.servicio.seguridad.sistema.AlbPantallaServicio;
import com.negocio.servicio.seguridad.sistema.AlbPerfilServicio;
import com.negocio.servicio.seguridad.sistema.PantallaPerfilServicio;
import com.persistencia.parametros.sistema.ParametrosObjetos;
import com.persistencia.seguridad.sistema.AlbMenu;
import com.persistencia.seguridad.sistema.AlbPantalla;
import com.persistencia.seguridad.sistema.AlbPerfil;
import com.persistencia.seguridad.sistema.PantallaPerfil;
import com.presentacion.mensajes.MensajeEAS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import static org.hibernate.ejb.EntityManagerImpl.LOG;
import org.jboss.logging.Logger;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Zulay
 */
@ManagedBean
@ViewScoped
public class SeguridadSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(SeguridadSistema.class);
    @ManagedProperty(value = "#{AlbMenuServicioImpl}")
    AlbMenuServicio albMenúServicio;
    private List<AlbMenu> listaMenu = new ArrayList();
    private List<AlbMenu> listaTempMenu = new ArrayList();
    AlbMenu albMenu = new AlbMenu();
    private MensajeEAS mensajeEAS = new MensajeEAS();
    private Boolean guardadoCabecera = false;
    private Long IdSelectedMenu;
    List<SelectItem> genListaSelected = new ArrayList<SelectItem>();

    //EDITAR AlbMenu
    private AlbMenu selectedAlbMenuEditar = new AlbMenu();
    private AlbMenu segAlbMenuObjects = new AlbMenu();
    private Long IdEditarAlbMenu;
    //ELIMINAR AlbMenu
    private AlbMenu selectedAlbMenuEliminar = new AlbMenu();

    @ManagedProperty(value = "#{AlbPantallaServicioImpl}")
    AlbPantallaServicio albPantallaServicio;
    private List<AlbPantalla> listaPantalla = new ArrayList();
    private List<AlbPantalla> listaTempPantalla = new ArrayList();
    AlbPantalla albPantalla = new AlbPantalla();
    //EDITAR AlbPantalla
    private AlbPantalla selectedAlbPantallaEditar = new AlbPantalla();
    private AlbPantalla segAlbPantallaObjects = new AlbPantalla();
    private Long IdEditarAlbPantalla;
    //ELIMINAR Pantalla
    private AlbPantalla selectedAlbPantallaEliminar = new AlbPantalla();

    @ManagedProperty(value = "#{AlbPerfilServicioImpl}")
    AlbPerfilServicio albPerfilServicio;
    private List<AlbPerfil> listaPerfil = new ArrayList();
    private List<AlbPerfil> listaTempPerfil = new ArrayList();
    AlbPerfil albPerfil = new AlbPerfil();
    //EDITAR AlbPERFIL
    private AlbPerfil selectedAlbPerfilEditar = new AlbPerfil();
    private AlbPerfil segAlbPerfilObjects = new AlbPerfil();
    private Long IdEditarAlbPerfil;
    //ELIMINAR PERFIL
    private AlbPerfil selectedAlbPerfilEliminar = new AlbPerfil();

    //ASIGNAR DAMNIFICADOALBERGUE
    private AlbPerfil selectedPantallaPerfil = new AlbPerfil();
    private Long IdAsignar;
    private Long IdSeleccionPantalla;
    List<SelectItem> listaAsignarPantalla = new ArrayList<SelectItem>();
    private List<AlbPantalla> listaTempPerfilPantalla1 = new ArrayList<>();
    private String[] selectedPagina;
    @ManagedProperty(value = "#{PantallaPerfilServicioImpl}")
    PantallaPerfilServicio pantallaPerfilServicio;

    @PostConstruct
    public void init() {
        if (!guardadoCabecera) {
            listaMenu.clear();
            listaTempMenu.clear();
            listaPantalla.clear();
            listaTempPantalla.clear();
            listaPerfil.clear();
            listaTempPerfil.clear();
            listaTempPerfilPantalla1.clear();
            listaPantalla.clear();
            this.listaPantalla.addAll(getAlbPantallaServicio().listarPantalla());

        }
        listaMenu.clear();
        this.listaMenu.addAll(getAlbMenúServicio().listarMenu());
        listaPerfil.clear();
        this.listaPerfil.addAll(getAlbPerfilServicio().listarPerfil());

    }

    public AlbMenuServicio getAlbMenúServicio() {
        return albMenúServicio;
    }

    public void setAlbMenúServicio(AlbMenuServicio albMenúServicio) {
        this.albMenúServicio = albMenúServicio;
    }

    public List<AlbMenu> getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List<AlbMenu> listaMenu) {
        this.listaMenu = listaMenu;
    }

    public AlbMenu getAlbMenu() {
        return albMenu;
    }

    public void setAlbMenu(AlbMenu albMenu) {
        this.albMenu = albMenu;
    }

    public void guardarMenu() {
        if ("".equals(albMenu.getMenNombre())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                albMenu.setMenEstado(1);
                listaTempMenu.clear();
                listaTempMenu.add(albMenu);
                getAlbMenúServicio().guardarMenu(listaTempMenu);
                albMenu = new AlbMenu();
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

    public AlbMenu getSelectedAlbMenuEditar() {
        return selectedAlbMenuEditar;
    }

    public void setSelectedAlbMenuEditar(AlbMenu selectedAlbMenuEditar) {
        this.selectedAlbMenuEditar = selectedAlbMenuEditar;
        editarMenuSistema(selectedAlbMenuEditar);
    }

    public void editarMenuSistema(AlbMenu obj) {
        try {
            segAlbMenuObjects = new AlbMenu();
            IdEditarAlbMenu = obj.getMenId();
            obj.getMenNombre();
            segAlbMenuObjects.setMenNombre(obj.getMenNombre());
            segAlbMenuObjects.setMenEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public AlbMenu getSegAlbMenuObjects() {
        return segAlbMenuObjects;
    }

    public void setSegAlbMenuObjects(AlbMenu segAlbMenuObjects) {
        this.segAlbMenuObjects = segAlbMenuObjects;
    }

    public void actualizarMenuSistema() {
        try {
            AlbMenu obj = new AlbMenu();
            obj.setMenId(IdEditarAlbMenu);
            obj.setMenNombre(segAlbMenuObjects.getMenNombre());
            obj.setMenEstado(1);
            if ("".equals(albMenu.getMenNombre())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempMenu.clear();
                listaTempMenu.add(obj);
                getAlbMenúServicio().guardarMenu(listaTempMenu);
                albMenu = new AlbMenu();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbMenu getSelectedAlbMenuEliminar() {
        return selectedAlbMenuEliminar;
    }

    public void setSelectedAlbMenuEliminar(AlbMenu selectedAlbMenuEliminar) {
        this.selectedAlbMenuEliminar = selectedAlbMenuEliminar;
        eliminarMenuSistema(selectedAlbMenuEliminar);
    }

    public void eliminarMenuSistema(AlbMenu obj) {
        try {

            obj.setMenEstado(ParametrosObjetos.INACTIVO);
            getAlbMenúServicio().guardarMenuEl(obj);
            mensajeEAS.Eliminar();
            cargarTable();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTable() {
        albMenu = new AlbMenu();
        listaMenu.clear();
        this.listaMenu.addAll(getAlbMenúServicio().listarMenu());
    }

    public AlbPantallaServicio getAlbPantallaServicio() {
        return albPantallaServicio;
    }

    public void setAlbPantallaServicio(AlbPantallaServicio albPantallaServicio) {
        this.albPantallaServicio = albPantallaServicio;
    }

    public List<AlbPantalla> getListaPantalla() {
        return listaPantalla;
    }

    public void setListaPantalla(List<AlbPantalla> listaPantalla) {
        this.listaPantalla = listaPantalla;
    }

    public AlbPantalla getAlbPantalla() {
        return albPantalla;
    }

    public void setAlbPantalla(AlbPantalla albPantalla) {
        this.albPantalla = albPantalla;
    }

    public void guardarPantalla() {
        if ("".equals(albPantalla.getPanNombrePantallas())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                AlbMenu obj = new AlbMenu();
                obj.setMenId(IdSelectedMenu);
                albPantalla.setAlbMenu(obj);
                albPantalla.setPanEstado(1);
                listaTempPantalla.clear();
                listaTempPantalla.add(albPantalla);
                getAlbPantallaServicio().guardarPantalla(listaTempPantalla);
                albPantalla = new AlbPantalla();
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

    public AlbPantalla getSelectedAlbPantallaEditar() {
        return selectedAlbPantallaEditar;
    }

    public void setSelectedAlbPantallaEditar(AlbPantalla selectedAlbPantallaEditar) {
        this.selectedAlbPantallaEditar = selectedAlbPantallaEditar;
        editarPantallaSistema(selectedAlbPantallaEditar);
    }

    public void editarPantallaSistema(AlbPantalla obj) {
        try {
            segAlbPantallaObjects = new AlbPantalla();
            AlbMenu objMenu = new AlbMenu();
            objMenu.setMenId(obj.getAlbMenu().getMenId());
            objMenu.setMenNombre(obj.getAlbMenu().getMenNombre());
            IdEditarAlbPantalla = obj.getPanId();
            obj.getPanNombrePantallas();
            segAlbPantallaObjects.setAlbMenu(objMenu);
            segAlbPantallaObjects.setPanNombrePantallas(obj.getPanNombrePantallas());
            segAlbPantallaObjects.setPanEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public AlbPantalla getSegAlbPantallaObjects() {
        return segAlbPantallaObjects;
    }

    public void setSegAlbPantallaObjects(AlbPantalla segAlbPantallaObjects) {
        this.segAlbPantallaObjects = segAlbPantallaObjects;
    }

    public void actualizarPantallaSistema() {
        try {
            AlbMenu objMen = new AlbMenu();
            if (IdSelectedMenu == null) {
                objMen.setMenId(segAlbPantallaObjects.getAlbMenu().getMenId());
            } else {
                objMen.setMenId(IdSelectedMenu);
            }
            AlbPantalla obj = new AlbPantalla();
            obj.setPanId(IdEditarAlbPantalla);
            obj.setAlbMenu(objMen);
            obj.setPanNombrePantallas(segAlbPantallaObjects.getPanNombrePantallas());
            obj.setPanEstado(1);
            if ("".equals(albPantalla.getPanNombrePantallas())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempPantalla.clear();
                listaTempPantalla.add(obj);
                getAlbPantallaServicio().guardarPantalla(listaTempPantalla);
                albPantalla = new AlbPantalla();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbPantalla getSelectedAlbPantallaEliminar() {
        return selectedAlbPantallaEliminar;
    }

    public void setSelectedAlbPantallaEliminar(AlbPantalla selectedAlbPantallaEliminar) {
        this.selectedAlbPantallaEliminar = selectedAlbPantallaEliminar;
        eliminarPantallaSistema(selectedAlbPantallaEliminar);
    }

    public void eliminarPantallaSistema(AlbPantalla obj) {
        try {

            obj.setPanEstado(ParametrosObjetos.INACTIVO);
            getAlbPantallaServicio().guardarPantallaEl(obj);
            mensajeEAS.Eliminar();
            cargarTablePantalla();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTablePantalla() {
        albPantalla = new AlbPantalla();
        listaPantalla.clear();
        this.listaPantalla.addAll(getAlbPantallaServicio().listarPantalla());
    }

    public Long getIdSelectedMenu() {
        return IdSelectedMenu;
    }

    public void setIdSelectedMenu(Long IdSelectedMenu) {
        this.IdSelectedMenu = IdSelectedMenu;
    }

    public List<SelectItem> getListaMenuAux() {
        this.genListaSelected = new ArrayList<SelectItem>();
        for (AlbMenu obj : listaMenu) {
            SelectItem selectItem = new SelectItem(obj.getMenId(), obj.getMenNombre());
            this.genListaSelected.add(selectItem);
        }
        return genListaSelected;
    }

    public AlbPerfilServicio getAlbPerfilServicio() {
        return albPerfilServicio;
    }

    public void setAlbPerfilServicio(AlbPerfilServicio albPerfilServicio) {
        this.albPerfilServicio = albPerfilServicio;
    }

    public List<AlbPerfil> getListaPerfil() {
        return listaPerfil;
    }

    public void setListaPerfil(List<AlbPerfil> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    public AlbPerfil getAlbPerfil() {
        return albPerfil;
    }

    public void setAlbPerfil(AlbPerfil albPerfil) {
        this.albPerfil = albPerfil;
    }

    public void guardarPerfil() {
        if ("".equals(albPerfil.getPerNombre())) {
            mensajeEAS.errorLlenarDatos();
        } else {
            try {
                albPerfil.setPerEstado(1);
                listaTempPerfil.clear();
                listaTempPerfil.add(albPerfil);
                getAlbPerfilServicio().guardarPerfil(listaTempPerfil);
                albPerfil = new AlbPerfil();
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

    public AlbPerfil getSelectedAlbPerfilEditar() {
        return selectedAlbPerfilEditar;
    }

    public void setSelectedAlbPerfilEditar(AlbPerfil selectedAlbPerfilEditar) {
        this.selectedAlbPerfilEditar = selectedAlbPerfilEditar;
        editarPerfilSistema(selectedAlbPerfilEditar);
    }

    public void editarPerfilSistema(AlbPerfil obj) {
        try {
            segAlbPerfilObjects = new AlbPerfil();
            AlbPerfil objAlbPerfil = new AlbPerfil();
            objAlbPerfil.setPerId(obj.getPerId());
            objAlbPerfil.setPerNombre(obj.getPerNombre());
            IdEditarAlbPerfil = obj.getPerId();
            obj.getPerNombre();
            segAlbPerfilObjects.setPerNombre(obj.getPerNombre());
            segAlbPerfilObjects.setPerEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public AlbPerfil getSegAlbPerfilObjects() {
        return segAlbPerfilObjects;
    }

    public void setSegAlbPerfilObjects(AlbPerfil segAlbPerfilObjects) {
        this.segAlbPerfilObjects = segAlbPerfilObjects;
    }

    public void actualizarPerfilSistema() {
        try {
            AlbPerfil obj = new AlbPerfil();
            obj.setPerId(IdEditarAlbPerfil);
            obj.setPerNombre(segAlbPerfilObjects.getPerNombre());
            obj.setPerEstado(1);
            if ("".equals(albPerfil.getPerNombre())) {
                mensajeEAS.errorLlenarDatos();
            } else {
                listaTempPerfil.clear();
                listaTempPerfil.add(obj);
                getAlbPerfilServicio().guardarPerfil(listaTempPerfil);
                albPerfil = new AlbPerfil();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.errorDublicado();
        }
    }

    public AlbPerfil getSelectedAlbPerfilEliminar() {
        return selectedAlbPerfilEliminar;
    }

    public void setSelectedAlbPerfilEliminar(AlbPerfil selectedAlbPerfilEliminar) {
        this.selectedAlbPerfilEliminar = selectedAlbPerfilEliminar;
        eliminarPerfilSistema(selectedAlbPerfilEliminar);
    }

    public void eliminarPerfilSistema(AlbPerfil obj) {
        try {
            obj.setPerEstado(ParametrosObjetos.INACTIVO);
            getAlbPerfilServicio().guardarPerfilEl(obj);
            mensajeEAS.Eliminar();
            cargarTablePerfil();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void cargarTablePerfil() {
        albPerfil = new AlbPerfil();
        listaPerfil.clear();
        this.listaPerfil.addAll(getAlbPerfilServicio().listarPerfil());
    }

    public AlbPerfil getSelectedPantallaPerfil() {
        return selectedPantallaPerfil;
    }

    public void setSelectedPantallaPerfil(AlbPerfil selectedPantallaPerfil) {
        this.selectedPantallaPerfil = selectedPantallaPerfil;
        pantallaPerfilSistema(selectedPantallaPerfil);
    }

    public void pantallaPerfilSistema(AlbPerfil objPUE) {
        try {
            segAlbPerfilObjects = new AlbPerfil();
            IdAsignar = objPUE.getPerId();
            objPUE.getPerNombre();
            segAlbPerfilObjects.setPerNombre(objPUE.getPerNombre());
            segAlbPerfilObjects.setPerEstado(1);
            segAlbPerfilObjects.setPerId(objPUE.getPerId());
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public Long getIdSeleccionPantalla() {
        return IdSeleccionPantalla;
    }

    public void setIdSeleccionPantalla(Long IdSeleccionPantalla) {
        this.IdSeleccionPantalla = IdSeleccionPantalla;
    }

    public List<SelectItem> getListaAsignarPantalla() {
        this.listaAsignarPantalla = new ArrayList<SelectItem>();
        List<AlbPantalla> Lista = getAlbPantallaServicio().listarPantalla();
        for (AlbPantalla obj : Lista) {
            SelectItem selectItem = new SelectItem(obj.getPanNombrePantallas(), obj.getPanNombrePantallas());
            this.listaAsignarPantalla.add(selectItem);
        }
        return listaAsignarPantalla;
    }


    public void guardarPerfilDefinitivo() {
        try {
            AlbPerfil obj = new AlbPerfil();
            PantallaPerfil pantPerfil = new PantallaPerfil();
            AlbPantalla pantalla = new AlbPantalla();
            obj.setPerNombre(segAlbPerfilObjects.getPerNombre());
            obj.setPerEstado(1);

            for (AlbPerfil perfil : listaPerfil) {
                if (perfil.getPerNombre().equals(segAlbPerfilObjects.getPerNombre())) {
                    obj.setPerId(perfil.getPerId());
                    pantPerfil.setAlbPerfil(obj);
                }
            }
            for (String objTareas : selectedPagina) {
                for (AlbPantalla auxPan : listaPantalla) {
                    if (auxPan.getPanNombrePantallas().equals(objTareas)) {
                        pantalla.setPanId(auxPan.getPanId());
                        pantPerfil.setAlbPantalla(pantalla);
                        pantPerfil.getAlbPerfil();
                        pantPerfil.getAlbPantalla();
                        getPantallaPerfilServicio().guardarPantallaPerfil(pantPerfil);
                        mensajeEAS.Guardar();
                        
                    }
                }
            }
            init();

        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();

        }

    }

    public String[] getSelectedPagina() {
        return selectedPagina;
    }

    public void setSelectedPagina(String[] selectedPagina) {
        this.selectedPagina = selectedPagina;
    }

    public PantallaPerfilServicio getPantallaPerfilServicio() {
        return pantallaPerfilServicio;
    }

    public void setPantallaPerfilServicio(PantallaPerfilServicio pantallaPerfilServicio) {
        this.pantallaPerfilServicio = pantallaPerfilServicio;
    }

}
