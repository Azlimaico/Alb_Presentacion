/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.general.calculo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Desarrollador1
 */
public class FuncionGeneral {

    public String obtenerFechaActual(String formato) {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat(formato);
        return formateador.format(ahora);
    }

    public Date obtenerFechaActualDate() {
        Date fechaActual = new Date();
        return fechaActual;
    }

    public String escanearOpe(String ope) {
        char charBuscar = ':';
        Integer cont = 0, i;
        String opeNuevo = "";
        for (i = 0; i < ope.length(); i++) {
            opeNuevo += ope.charAt(i);
            if (ope.charAt(i) == charBuscar) {
                cont++;
                if (cont == 6) {//poner 6
                    Integer y = ope.length() - (i + 1);
                    String lo = ope.substring((i + 1), ope.length());
                    Integer ko = Integer.parseInt(lo);
                    ko = ko + 1;
                    opeNuevo += ko;
                    break;
                }
            }
        }
        return opeNuevo;
    }
    
    public String generarCoordenadasMapa() {
        Integer contadorX = 0, contadorY = 0;
        String cx = null;
        contadorX = contadorX + 5;
        contadorY = contadorY + 5;
        Integer x = 25, y = 25, z = -3;
        x = x + contadorX;
        cx = z + ":" + x.toString() + ":" + y.toString();
        return cx;
    }
    public String armarNombreIconoGenerado(String sigla, String idUnidadJuego) {
        String nuevo = sigla.replaceAll(" +", " ");
        String nuevo2 = nuevo.replaceAll(" ", "-");
        String nuevo3 = nuevo2.replaceAll("/", "-");
        String sig = nuevo3.replaceAll("--", "-");
        return sig + "-" + idUnidadJuego + ".png";
    }
//    
//    public String transformarOpeAOpeAgreg(String ope) {
//        String opeNuevo = null,opeLetra=null;
//        opeLetra += ope.charAt(0);//captura el tres
//        opeNuevo=ope.replace(opeLetra, "11");
//        return opeNuevo;
//    }
}
