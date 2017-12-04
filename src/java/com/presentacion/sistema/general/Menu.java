/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.sistema.general;

import com.negocio.servicio.seguridad.sistema.AlbMenuServicio;
import com.negocio.servicio.seguridad.sistema.PantallaPerfilServicio;
import com.persistencia.seguridad.sistema.AlbMenu;
import com.persistencia.seguridad.sistema.PantallaPerfil;
import com.presentacion.seguridad.sistema.AccesoSistema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.jboss.logging.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Zulay
 */
@ManagedBean
@SessionScoped
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(Menu.class);
    private MenuModel model;
    @ManagedProperty("#{accesoSistema}")
    private AccesoSistema accesoSistema;
    
    @ManagedProperty(value = "#{AlbMenuServicioImpl}")
    AlbMenuServicio albMenuServicio;
    
    @ManagedProperty(value = "#{PantallaPerfilServicioImpl}")
    PantallaPerfilServicio pantallaPerfilServicio;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        if (accesoSistema.objectUsuarioSession.getPadId() != null) {
            List<AlbMenu> listaMenus = getAlbMenuServicio().listarMenu();
            List<PantallaPerfil> listaPantalla = getPantallaPerfilServicio().listarPantallaPerfilByPerfilId(accesoSistema.objectUsuarioSession.getAlbPerfil().getPerId());

            //primer menu ADMINISTRACIÓN
            List<DefaultSubMenu> listaMenTemp = new ArrayList<>();
            for (AlbMenu obj : listaMenus) {
//            DefaultSubMenu firstSubmenu = new DefaultSubMenu(obj.getMenNombre(),obj.getMenId().toString());
                DefaultSubMenu firstSubmenu = new DefaultSubMenu(obj.getMenNombre());
                firstSubmenu.setId(obj.getMenId().toString());
                listaMenTemp.add(firstSubmenu);
            }
            for (DefaultSubMenu onj : listaMenTemp) {
                for (PantallaPerfil obj : listaPantalla) {
                    DefaultMenuItem item = new DefaultMenuItem(obj.getAlbPantalla().getPanNombrePantallas());
                    item.setUrl(obj.getAlbPantalla().getPanUrl());
                    if (onj.getId().equals(obj.getAlbPantalla().getAlbMenu().getMenId().toString())) {
                        onj.addElement(item);
                    }
                }
                if (!onj.getElements().isEmpty()) {
                    model.addElement(onj);
                }
            }

        } else {
            enviarLogin();
        }
    }

    private String enviarLogin() {
        return "Login";
    }

    

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public AlbMenuServicio getAlbMenuServicio() {
        return albMenuServicio;
    }

    public void setAlbMenuServicio(AlbMenuServicio albMenuServicio) {
        this.albMenuServicio = albMenuServicio;
    }

    public AccesoSistema getAccesoSistema() {
        return accesoSistema;
    }

    public void setAccesoSistema(AccesoSistema accesoSistema) {
        this.accesoSistema = accesoSistema;
    }

    public PantallaPerfilServicio getPantallaPerfilServicio() {
        return pantallaPerfilServicio;
    }

    public void setPantallaPerfilServicio(PantallaPerfilServicio pantallaPerfilServicio) {
        this.pantallaPerfilServicio = pantallaPerfilServicio;
    }

    
}
