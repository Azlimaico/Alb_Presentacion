/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.parametros.sistema;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;


@ManagedBean(name = "PForm")
@ViewScoped
public class ParametrosFormulario {

    public Integer longitudMaximaCaracteresNombres = 100;
    public Integer longitudMaximaCaracteresSigla = 10;
    public Integer longitudMaximaCaracteresObservacion = 200;
    public Integer alturaPanel = 300;
    public Integer anchuraPanel = 500;
    public Integer cantidadItemsTabla = 10;
    public Integer borderTablaPanel = 1;
    public Integer cellpaddingTablaPanel = 0;
    public Integer cellspacingTablaPanel = 0;
    public Integer anchoCajaTextoNombre = 53;
    public Integer anchoCajaTextoSigla = 14;
    public Integer tiempoPresentacionSms;

    public Integer getLongitudMaximaCaracteresSigla() {
        return longitudMaximaCaracteresSigla;
    }

    public void setLongitudMaximaCaracteresSigla(Integer longitudMaximaCaracteresSigla) {
        this.longitudMaximaCaracteresSigla = longitudMaximaCaracteresSigla;
    }

    public Integer getAnchoCajaTextoSigla() {
        return anchoCajaTextoSigla;
    }

    public void setAnchoCajaTextoSigla(Integer anchoCajaTextoSigla) {
        this.anchoCajaTextoSigla = anchoCajaTextoSigla;
    }

    public Integer getLongitudMaximaCaracteresNombres() {
        return longitudMaximaCaracteresNombres;
    }

    public Integer getAnchoCajaTextoNombre() {
        return anchoCajaTextoNombre;
    }

    public void setAnchoCajaTextoNombre(Integer anchoCajaTextoNombre) {
        this.anchoCajaTextoNombre = anchoCajaTextoNombre;
    }

    public Integer getLongitudMaximaCaracteresObservacion() {
        return longitudMaximaCaracteresObservacion;
    }

    public void setLongitudMaximaCaracteresNombres(Integer longitudMaximaCaracteresNombres) {
        this.longitudMaximaCaracteresNombres = longitudMaximaCaracteresNombres;
    }

    public void setLongitudMaximaCaracteresObservacion(Integer longitudMaximaCaracteresObservacion) {
        this.longitudMaximaCaracteresObservacion = longitudMaximaCaracteresObservacion;
    }

    public Integer getAlturaPanel() {
        return alturaPanel;
    }

    public void setAlturaPanel(Integer alturaPanel) {
        this.alturaPanel = alturaPanel;
    }

    public Integer getAnchuraPanel() {
        return anchuraPanel;
    }

    public void setAnchuraPanel(Integer anchuraPanel) {
        this.anchuraPanel = anchuraPanel;
    }

    public Integer getCantidadItemsTabla() {
        return cantidadItemsTabla;
    }

    public void setCantidadItemsTabla(Integer cantidadItemsTabla) {
        this.cantidadItemsTabla = cantidadItemsTabla;
    }

    public Integer getBorderTablaPanel() {
        return borderTablaPanel;
    }

    public void setBorderTablaPanel(Integer borderTablaPanel) {
        this.borderTablaPanel = borderTablaPanel;
    }

    public Integer getCellpaddingTablaPanel() {
        return cellpaddingTablaPanel;
    }

    public void setCellpaddingTablaPanel(Integer cellpaddingTablaPanel) {
        this.cellpaddingTablaPanel = cellpaddingTablaPanel;
    }

    public Integer getCellspacingTablaPanel() {
        return cellspacingTablaPanel;
    }

    public void setCellspacingTablaPanel(Integer cellspacingTablaPanel) {
        this.cellspacingTablaPanel = cellspacingTablaPanel;
    }

    public Integer getTiempoPresentacionSms() {
        return tiempoPresentacionSms;
    }

    public void setTiempoPresentacionSms(Integer tiempoPresentacionSms) {
        this.tiempoPresentacionSms = tiempoPresentacionSms;
    }

    public static String getPathSistema() {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
                    .getExternalContext().getContext();
            return ctx.getRealPath("/");

        } catch (Exception e) {
            return "";
        }

    }

}
