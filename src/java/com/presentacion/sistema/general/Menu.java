/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.sistema.general;

import com.negocio.servicio.seguridad.sistema.AlbMenuServicio;
import com.persistencia.seguridad.sistema.AlbMenu;
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

    @ManagedProperty(value = "#{AlbMenuServicioImpl}")
    AlbMenuServicio albMenuServicio;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        List<AlbMenu> listaMenus = getAlbMenuServicio().listarMenu();
        //primer menu ADMINISTRACIÓN
        List<DefaultSubMenu> listaMenTemp = new ArrayList<>();
        for (AlbMenu obj : listaMenus) {
//            DefaultSubMenu firstSubmenu = new DefaultSubMenu(obj.getMenNombre(),obj.getMenId().toString());
            DefaultSubMenu firstSubmenu = new DefaultSubMenu(obj.getMenNombre());
            firstSubmenu.setId(obj.getMenId().toString());
            listaMenTemp.add(firstSubmenu);
            model.addElement(firstSubmenu);
        }

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

}
