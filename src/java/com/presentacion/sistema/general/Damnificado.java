/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentacion.sistema.general;

import com.negocio.servicio.albergue.AlbAlbergueServicio;
import com.negocio.servicio.damnificado.AlbDamnificadoServicio;
import com.negocio.servicio.general.sistema.AlbDiscapacidadServicio;
import com.negocio.servicio.general.sistema.AlbEstadoCivilServicio;
import com.negocio.servicio.general.sistema.AlbFamiliaServicio;
import com.negocio.servicio.general.sistema.AlbInstruccionServicio;
import com.negocio.servicio.general.sistema.AlbProfesionServicio;
import com.persistencia.albergue.AlbAlbergue;
import com.persistencia.damnificado.AlbDamnificado;
import com.persistencia.general.sistema.AlbAvanceImplementacion;
import com.persistencia.general.sistema.AlbCanton;
import com.persistencia.general.sistema.AlbDiscapacidad;
import com.persistencia.general.sistema.AlbEstadoCivil;
import com.persistencia.general.sistema.AlbFamilia;
import com.persistencia.general.sistema.AlbInstruccion;
import com.persistencia.general.sistema.AlbParroquia;
import com.persistencia.general.sistema.AlbProfesion;
import com.persistencia.general.sistema.AlbProvincia;
import com.persistencia.parametros.sistema.ParametrosObjetos;
import com.presentacion.mensajes.MensajeEAS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Zulay
 */
@ManagedBean
@ViewScoped
public class Damnificado implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger LOG = Logger.getLogger(Damnificado.class);
    private int console;
    private Date date1;
    @ManagedProperty(value = "#{AlbDamnificadoServicioImpl}")
    AlbDamnificadoServicio albDamnificadoServicio;
    private MensajeEAS mensajeEAS = new MensajeEAS();
    private List<AlbDamnificado> listaDamnificado = new ArrayList<>();
    private Boolean guardadoCabecera = false;
    @ManagedProperty(value = "#{AlbEstadoCivilServicioImpl}")
    AlbEstadoCivilServicio albEstadoCivilServicio;
    @ManagedProperty(value = "#{AlbInstruccionServicioImpl}")
    AlbInstruccionServicio albInstruccionServicio;
    @ManagedProperty(value = "#{AlbProfesionServicioImpl}")
    AlbProfesionServicio albProfesionServicio;
    @ManagedProperty(value = "#{AlbDiscapacidadServicioImpl}")
    AlbDiscapacidadServicio albDiscapacidadServicio;
    @ManagedProperty(value = "#{AlbFamiliaServicioImpl}")
    AlbFamiliaServicio albFamiliaServicio;
    private AlbDamnificado albDamnificado = new AlbDamnificado();
    @ManagedProperty(value = "#{AlbAlbergueServicioImpl}")
    AlbAlbergueServicio albAlbergueServicio;
    private List<AlbDamnificado> listaTempAlbDamnificado = new ArrayList<>();
    private List<AlbAlbergue> listaTempDamnificadoAlbergue = new ArrayList<>();

    //VER DAMNIFICADO
    private AlbDamnificado dammificadoObjects = new AlbDamnificado();
    private Long estado, IdEditar, IdAsignar;
    private Long IdSeleccionEstadoCivil;
    List<SelectItem> listaEditarEstadoCivil = new ArrayList<SelectItem>();
    private List<AlbEstadoCivil> listaEstadoCivil = new ArrayList<>();

    private Long IdSeleccionNivInstrucc;
    List<SelectItem> listaEditarNivInstrucc = new ArrayList<SelectItem>();
    private List<AlbInstruccion> listaNivelInstruccion = new ArrayList<>();

    private Long IdSeleccionProfesion;
    List<SelectItem> listaEditarProfesion = new ArrayList<SelectItem>();
    private List<AlbProfesion> listaProfesion = new ArrayList<>();

    private Long IdSeleccionDiscapacidad;
    List<SelectItem> listaEditarDiscapacida = new ArrayList<SelectItem>();
    private List<AlbDiscapacidad> listaDiscapacida = new ArrayList<>();

    private Long IdSeleccionNumFlia;
    List<SelectItem> listaEditarNumFlia = new ArrayList<SelectItem>();
    private List<AlbFamilia> listaNumFlia = new ArrayList<>();

    private Long IdSeleccionAlbergue;
    List<SelectItem> listaAsignarAlbergue = new ArrayList<SelectItem>();
    private List<AlbAlbergue> listaAlbergue = new ArrayList<>();

    //EDITAR ALBERGUE
    private AlbDamnificado selectedDamnificadoEditar = new AlbDamnificado();
    private AlbDamnificado segDamnificadoObjects = new AlbDamnificado();

    //ELIMINAR ALBERGUE
    private AlbDamnificado selectedSegDamnificadoEliminar = new AlbDamnificado();

    //ASIGNAR DAMNIFICADOALBERGUE
    private AlbDamnificado selectedAlbergueDamnificado = new AlbDamnificado();
    private AlbDamnificado segDamnificadoObjects1 = new AlbDamnificado();

    @PostConstruct
    public void init() {
        if (!guardadoCabecera) {
            listaTempDamnificadoAlbergue.clear();
            listaTempAlbDamnificado.clear();
            albDamnificado = new AlbDamnificado();
            listaEstadoCivil.clear();
            this.listaEstadoCivil.addAll(getAlbEstadoCivilServicio().listarEstadoCivil());
            listaNivelInstruccion.clear();
            this.listaNivelInstruccion.addAll(getAlbInstruccionServicio().listarInstruccion());
            listaProfesion.clear();
            this.listaProfesion.addAll(getAlbProfesionServicio().listarProfesion());
            listaDiscapacida.clear();
            this.listaDiscapacida.addAll(getAlbDiscapacidadServicio().listarDiscapacidad());
            listaNumFlia.clear();
            this.listaNumFlia.addAll(getAlbFamiliaServicio().listarFamilia());
            listaAlbergue.clear();
            this.listaAlbergue.addAll(getAlbAlbergueServicio().listarAlbergue());
        }
        this.listaDamnificado.clear();
        this.listaDamnificado.addAll(getAlbDamnificadoServicio().listarDamnificado());
    }

    public void cargarTable() {
        albDamnificado = new AlbDamnificado();
        listaDamnificado.clear();
        this.listaDamnificado.addAll(getAlbDamnificadoServicio().listarDamnificado());

    }
    
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
    
    public void editarDamnificadoSistema(AlbDamnificado objPUE) {
        try {
            segDamnificadoObjects = new AlbDamnificado();
            AlbEstadoCivil objEstCivil = new AlbEstadoCivil();
            objEstCivil.setEciId(objPUE.getAlbEstadoCivil().getEciId());
            objEstCivil.setEciTipo(objPUE.getAlbEstadoCivil().getEciTipo());
            AlbInstruccion objInstruccion = new AlbInstruccion();
            objInstruccion.setInsId(objPUE.getAlbInstruccion().getInsId());
            objInstruccion.setInsNombre(objPUE.getAlbInstruccion().getInsNombre());
            AlbProfesion objProfesion = new AlbProfesion();
            objProfesion.setPrfId(objPUE.getAlbProfesion().getPrfId());
            objProfesion.setPrfProfesion(objPUE.getAlbProfesion().getPrfProfesion());
            AlbDiscapacidad objDiscapacidad = new AlbDiscapacidad();
            objDiscapacidad.setDisId(objPUE.getAlbDiscapacidad().getDisId());
            objDiscapacidad.setDisTipo(objPUE.getAlbDiscapacidad().getDisTipo());
            AlbFamilia objFamilia = new AlbFamilia();
            objFamilia.setFamId(objPUE.getAlbFamilia().getFamId());
            objFamilia.setFamNumIntegrantes(objPUE.getAlbFamilia().getFamNumIntegrantes());
            IdEditar = objPUE.getDamId();
            objPUE.getDamNombres();
            objPUE.getDamApellidos();
            objPUE.getDamCedula();
            objPUE.getDamSexo();
            objPUE.getDamLugarNacimiento();
            objPUE.getDamFechaNacimiento();
            objPUE.getDamDireccionDomicilio();
            objPUE.getDamDatosPadre();
            objPUE.getDamDatosMadre();
            objPUE.getDamCelular();
            objPUE.getDamEmail();
            objPUE.getDamEmbarazo();
            objPUE.getDamObservaciones();
            segDamnificadoObjects.setDamNombres(objPUE.getDamNombres());
            segDamnificadoObjects.setDamApellidos(objPUE.getDamApellidos());
            segDamnificadoObjects.setDamCedula(objPUE.getDamCedula());
            segDamnificadoObjects.setAlbEstadoCivil(objEstCivil);
            segDamnificadoObjects.setDamSexo(objPUE.getDamSexo());
            segDamnificadoObjects.setDamLugarNacimiento(objPUE.getDamLugarNacimiento());
            segDamnificadoObjects.setDamFechaNacimiento(objPUE.getDamFechaNacimiento());
            segDamnificadoObjects.setDamDireccionDomicilio(objPUE.getDamDireccionDomicilio());
            segDamnificadoObjects.setAlbInstruccion(objInstruccion);
            segDamnificadoObjects.setAlbProfesion(objProfesion);
            segDamnificadoObjects.setDamDatosPadre(objPUE.getDamDatosPadre());
            segDamnificadoObjects.setDamDatosMadre(objPUE.getDamDatosMadre());
            segDamnificadoObjects.setDamCelular(objPUE.getDamCelular());
            segDamnificadoObjects.setDamEmail(objPUE.getDamEmail());
            segDamnificadoObjects.setAlbDiscapacidad(objDiscapacidad);
            segDamnificadoObjects.setDamEmbarazo(objPUE.getDamEmbarazo());
            segDamnificadoObjects.setAlbFamilia(objFamilia);
            segDamnificadoObjects.setDamObservaciones(objPUE.getDamObservaciones());
            segDamnificadoObjects.setDamEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void albergueDamnificadoSistema(AlbDamnificado objPUE) {
        try {
            segDamnificadoObjects1 = new AlbDamnificado();
            AlbEstadoCivil objEstCivil = new AlbEstadoCivil();
            objEstCivil.setEciId(objPUE.getAlbEstadoCivil().getEciId());
            objEstCivil.setEciTipo(objPUE.getAlbEstadoCivil().getEciTipo());
            AlbInstruccion objInstruccion = new AlbInstruccion();
            objInstruccion.setInsId(objPUE.getAlbInstruccion().getInsId());
            objInstruccion.setInsNombre(objPUE.getAlbInstruccion().getInsNombre());
            AlbProfesion objProfesion = new AlbProfesion();
            objProfesion.setPrfId(objPUE.getAlbProfesion().getPrfId());
            objProfesion.setPrfProfesion(objPUE.getAlbProfesion().getPrfProfesion());
            AlbDiscapacidad objDiscapacidad = new AlbDiscapacidad();
            objDiscapacidad.setDisId(objPUE.getAlbDiscapacidad().getDisId());
            objDiscapacidad.setDisTipo(objPUE.getAlbDiscapacidad().getDisTipo());
            AlbFamilia objFamilia = new AlbFamilia();
            objFamilia.setFamId(objPUE.getAlbFamilia().getFamId());
            objFamilia.setFamNumIntegrantes(objPUE.getAlbFamilia().getFamNumIntegrantes());
            IdAsignar = objPUE.getDamId();
            objPUE.getDamNombres();
            objPUE.getDamApellidos();
            objPUE.getDamCedula();
            objPUE.getDamSexo();
            objPUE.getDamLugarNacimiento();
            objPUE.getDamFechaNacimiento();
            objPUE.getDamDireccionDomicilio();
            objPUE.getDamDatosPadre();
            objPUE.getDamDatosMadre();
            objPUE.getDamCelular();
            objPUE.getDamEmail();
            objPUE.getDamEmbarazo();
            objPUE.getDamObservaciones();
            segDamnificadoObjects1.setDamNombres(objPUE.getDamNombres());
            segDamnificadoObjects1.setDamApellidos(objPUE.getDamApellidos());
            segDamnificadoObjects1.setDamCedula(objPUE.getDamCedula());
            segDamnificadoObjects1.setAlbEstadoCivil(objEstCivil);
            segDamnificadoObjects1.setDamSexo(objPUE.getDamSexo());
            segDamnificadoObjects1.setDamLugarNacimiento(objPUE.getDamLugarNacimiento());
            segDamnificadoObjects1.setDamId(objPUE.getDamId());
            segDamnificadoObjects1.setDamFechaNacimiento(objPUE.getDamFechaNacimiento());
            segDamnificadoObjects1.setDamDireccionDomicilio(objPUE.getDamDireccionDomicilio());
            segDamnificadoObjects1.setAlbInstruccion(objInstruccion);
            segDamnificadoObjects1.setAlbProfesion(objProfesion);
            segDamnificadoObjects1.setDamDatosPadre(objPUE.getDamDatosPadre());
            segDamnificadoObjects1.setDamDatosMadre(objPUE.getDamDatosMadre());
            segDamnificadoObjects1.setDamCelular(objPUE.getDamCelular());
            segDamnificadoObjects1.setDamEmail(objPUE.getDamEmail());
            segDamnificadoObjects1.setAlbDiscapacidad(objDiscapacidad);
            segDamnificadoObjects1.setDamEmbarazo(objPUE.getDamEmbarazo());
            segDamnificadoObjects1.setAlbFamilia(objFamilia);
            segDamnificadoObjects1.setDamObservaciones(objPUE.getDamObservaciones());
            segDamnificadoObjects1.setDamEstado(1);
        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void eliminarUsuarioSistema(AlbDamnificado objPU) {
        try {

            objPU.setDamEstado(ParametrosObjetos.INACTIVO);
            getAlbDamnificadoServicio().guardarEliminarDamnificado(objPU);
            mensajeEAS.Eliminar();
            cargarTable();

        } catch (Exception ex) {

            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }

    public void guardarDamnificado() {
        if ("".equals(albDamnificado.getDamNombres()) || "".equals(albDamnificado.getDamApellidos()) || "".equals(albDamnificado.getDamCedula())
                 || "".equals(albDamnificado.getDamLugarNacimiento()) || "".equals(albDamnificado.getDamFechaNacimiento())
                || "".equals(albDamnificado.getDamDireccionDomicilio()) || "".equals(albDamnificado.getDamDatosPadre()) || "".equals(albDamnificado.getDamDatosMadre()) || "".equals(albDamnificado.getDamCelular()) || "".equals(albDamnificado.getDamEmail()) || "".equals(albDamnificado.getDamEmbarazo()) || "".equals(albDamnificado.getDamObservaciones())) {
            mensajeEAS.errorLlenarDatos();
        } else {

            try {
                
                albDamnificado.getDamSexo();
                AlbEstadoCivil objEstCivil = new AlbEstadoCivil();
                AlbInstruccion objInstruccion = new AlbInstruccion();
                AlbProfesion objProfesion = new AlbProfesion();
                AlbDiscapacidad objDiscapacidad = new AlbDiscapacidad();;
                AlbFamilia objFamilia = new AlbFamilia();
                objEstCivil.setEciId(IdSeleccionEstadoCivil);
                objInstruccion.setInsId(IdSeleccionNivInstrucc);
                objProfesion.setPrfId(IdSeleccionProfesion);
                objDiscapacidad.setDisId(IdSeleccionDiscapacidad);
                objFamilia.setFamId(IdSeleccionNumFlia);
                albDamnificado.setAlbEstadoCivil(objEstCivil);
                albDamnificado.setAlbInstruccion(objInstruccion);
                albDamnificado.setAlbProfesion(objProfesion);
                albDamnificado.setAlbDiscapacidad(objDiscapacidad);
                albDamnificado.setAlbFamilia(objFamilia);
                albDamnificado.setDamEstado(1);
                listaTempAlbDamnificado.clear();
                listaTempAlbDamnificado.add(albDamnificado);
                getAlbDamnificadoServicio().guardarDamnificado(listaTempAlbDamnificado);
                albDamnificado = new AlbDamnificado();
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

    public void cancelarCargaDatos() {
        albDamnificado = new AlbDamnificado();
    }

    public void actualizarDamnificad() {
        try {
            AlbEstadoCivil objEstCivil = new AlbEstadoCivil();
            AlbInstruccion objInstruccion = new AlbInstruccion();
            AlbProfesion objProfesion = new AlbProfesion();
            AlbDiscapacidad objDiscapacidad = new AlbDiscapacidad();;
            AlbFamilia objFamilia = new AlbFamilia();
            AlbDamnificado objDamnificado = new AlbDamnificado();

            if (IdSeleccionEstadoCivil == null) {
                objEstCivil.setEciId(segDamnificadoObjects.getAlbEstadoCivil().getEciId());
            } else {
                objEstCivil.setEciId(IdSeleccionEstadoCivil);
            }
            if (IdSeleccionNivInstrucc == null) {
                objInstruccion.setInsId(segDamnificadoObjects.getAlbInstruccion().getInsId());
            } else {
                objInstruccion.setInsId(IdSeleccionNivInstrucc);
            }
            if (IdSeleccionProfesion == null) {
                objProfesion.setPrfId(segDamnificadoObjects.getAlbProfesion().getPrfId());
            } else {
                objProfesion.setPrfId(IdSeleccionProfesion);
            }
            if (IdSeleccionDiscapacidad == null) {
                objDiscapacidad.setDisId(segDamnificadoObjects.getAlbDiscapacidad().getDisId());
            } else {
                objDiscapacidad.setDisId(IdSeleccionDiscapacidad);
            }
            if (IdSeleccionNumFlia == null) {
                objFamilia.setFamId(segDamnificadoObjects.getAlbFamilia().getFamId());
            } else {
                objFamilia.setFamId(IdSeleccionNumFlia);
            }
            objDamnificado.setDamId(IdEditar);
            objDamnificado.setDamNombres(segDamnificadoObjects.getDamNombres());
            objDamnificado.setDamApellidos(segDamnificadoObjects.getDamApellidos());
            objDamnificado.setDamCedula(segDamnificadoObjects.getDamCedula());
            objDamnificado.setAlbEstadoCivil(objEstCivil);
            objDamnificado.setDamSexo(segDamnificadoObjects.getDamSexo());
            objDamnificado.setDamLugarNacimiento(segDamnificadoObjects.getDamLugarNacimiento());
            objDamnificado.setDamFechaNacimiento(segDamnificadoObjects.getDamFechaNacimiento());
            objDamnificado.setDamDireccionDomicilio(segDamnificadoObjects.getDamDireccionDomicilio());
            objDamnificado.setAlbInstruccion(objInstruccion);
            objDamnificado.setAlbProfesion(objProfesion);
            objDamnificado.setDamDatosPadre(segDamnificadoObjects.getDamDatosPadre());
            objDamnificado.setDamDatosMadre(segDamnificadoObjects.getDamDatosMadre());
            objDamnificado.setDamCelular(segDamnificadoObjects.getDamCelular());
            objDamnificado.setDamEmail(segDamnificadoObjects.getDamEmail());
            objDamnificado.setAlbDiscapacidad(objDiscapacidad);
            objDamnificado.setDamEmbarazo(segDamnificadoObjects.getDamEmbarazo());
            objDamnificado.setAlbFamilia(objFamilia);
            objDamnificado.setDamObservaciones(segDamnificadoObjects.getDamObservaciones());
            objDamnificado.setDamEstado(1);

            if ("".equals(albDamnificado.getDamNombres()) || "".equals(albDamnificado.getDamApellidos()) || "".equals(albDamnificado.getDamCedula())
                    || "".equals(albDamnificado.getDamSexo()) || "".equals(albDamnificado.getDamLugarNacimiento()) || "".equals(albDamnificado.getDamFechaNacimiento())
                    || "".equals(albDamnificado.getDamDireccionDomicilio()) || "".equals(albDamnificado.getDamDatosPadre()) || "".equals(albDamnificado.getDamDatosMadre()) || "".equals(albDamnificado.getDamCelular()) || "".equals(albDamnificado.getDamEmail()) || "".equals(albDamnificado.getDamEmbarazo()) || "".equals(albDamnificado.getDamObservaciones())) {
                mensajeEAS.errorLlenarDatos();
            } else {

                listaTempAlbDamnificado.clear();
                listaTempAlbDamnificado.add(objDamnificado);
                getAlbDamnificadoServicio().guardarDamnificado(listaTempAlbDamnificado);
                albDamnificado = new AlbDamnificado();
                guardadoCabecera = true;
                init();
                mensajeEAS.Modificar();
            }

        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }

    }
    //  AlbDamnificado damnificadoAlbergue = new AlbDamnificado();
    AlbDamnificado albDamnificado1 = new AlbDamnificado();
    AlbAlbergue albAlbergue = new AlbAlbergue();

    public void actualizarDamnificadoAlbergue() {
        try {
            if (IdSeleccionAlbergue != null) {
                for (AlbAlbergue obj : listaAlbergue) {
                    if (IdSeleccionAlbergue == obj.getAlbId()) {
                        AlbProvincia objProv = new AlbProvincia();
                        AlbCanton objCanton = new AlbCanton();
                        AlbParroquia objParroquia = new AlbParroquia();
                        AlbAvanceImplementacion objAvaImp = new AlbAvanceImplementacion();
                        objProv.setProId(obj.getAlbProvincia().getProId());
                        objCanton.setCanId(obj.getAlbCanton().getCanId());
                        objParroquia.setParId(obj.getAlbParroquia().getParId());
                        objAvaImp.setAvaId(obj.getAlbAvanceImplementacion().getAvaId());
                        albAlbergue.setAlbProvincia(objProv);
                        albAlbergue.setAlbCanton(objCanton);
                        albAlbergue.setAlbParroquia(objParroquia);
                        albAlbergue.setAlbAvanceImplementacion(objAvaImp);
                        albAlbergue.setAlbEstado(1);
                        obj.getAlbId();
                        obj.getAlbNombre();
                        obj.getAlbDireccion();
                        obj.getAlbTipoAlbergue();
                        obj.getAlbArea();
                        obj.getAlbCoordx();
                        obj.getAlbCoordy();
                        obj.getAlbObservaciones();
                        albAlbergue.setAlbId(obj.getAlbId());
                        albAlbergue.setAlbNombre(obj.getAlbNombre());
                        albAlbergue.setAlbDireccion(obj.getAlbDireccion());
                        albAlbergue.setAlbTipoAlbergue(obj.getAlbTipoAlbergue());
                        albAlbergue.setAlbArea(obj.getAlbArea());
                        albAlbergue.setAlbCoordx(obj.getAlbCoordx());
                        albAlbergue.setAlbCoordy(obj.getAlbCoordy());
                        albAlbergue.setAlbObservaciones(obj.getAlbObservaciones());
                        
                    }
                }
            }

            albAlbergue.addDamnificado(segDamnificadoObjects1);
            albDamnificado1.addAlbergue(albAlbergue);
            listaTempDamnificadoAlbergue.clear();
            listaTempDamnificadoAlbergue.add(albAlbergue);
            // REVIZAR EN ESTA LINEA  getAlbDamnificadoServicio().guardarDamnificadoAlbergue(listaTempDamnificadoAlbergue); LA LISTA TEM SOLO EL ID DEL ALBERGUE Y EL PERSIST
            getAlbDamnificadoServicio().guardarDamnificadoAlbergue(listaTempDamnificadoAlbergue);
//            damnificadoAlbergue = new DamnificadoAlbergue();
            guardadoCabecera = true;
            init();

        } catch (Exception ex) {
            LOG.error("Error: " + ex.getMessage());
            mensajeEAS.error();
        }
    }

    public AlbDamnificadoServicio getAlbDamnificadoServicio() {
        return albDamnificadoServicio;
    }

    public void setAlbDamnificadoServicio(AlbDamnificadoServicio albDamnificadoServicio) {
        this.albDamnificadoServicio = albDamnificadoServicio;
    }

    public List<AlbDamnificado> getListaDamnificado() {
        return listaDamnificado;
    }

    public void setListaDamnificado(List<AlbDamnificado> listaDamnificado) {
        this.listaDamnificado = listaDamnificado;
    }

    public AlbDamnificado getDammificadoObjects() {
        return dammificadoObjects;
    }

    public void setDammificadoObjects(AlbDamnificado dammificadoObjects) {
        this.dammificadoObjects = dammificadoObjects;
    }

    public Long getIdSeleccionEstadoCivil() {
        return IdSeleccionEstadoCivil;
    }

    public void setIdSeleccionEstadoCivil(Long IdSeleccionEstadoCivil) {
        this.IdSeleccionEstadoCivil = IdSeleccionEstadoCivil;
    }

    public List<SelectItem> getListaEditarEstadoCivil() {
        this.listaEditarEstadoCivil = new ArrayList<SelectItem>();
        for (AlbEstadoCivil obj : listaEstadoCivil) {
            SelectItem selectItem = new SelectItem(obj.getEciId(), obj.getEciTipo());
            this.listaEditarEstadoCivil.add(selectItem);
        }
        return listaEditarEstadoCivil;
    }

    public void setListaEditarEstadoCivil(List<SelectItem> listaEditarEstadoCivil) {
        this.listaEditarEstadoCivil = listaEditarEstadoCivil;
    }

    public AlbEstadoCivilServicio getAlbEstadoCivilServicio() {
        return albEstadoCivilServicio;
    }

    public void setAlbEstadoCivilServicio(AlbEstadoCivilServicio albEstadoCivilServicio) {
        this.albEstadoCivilServicio = albEstadoCivilServicio;
    }

    public Long getIdSeleccionNivInstrucc() {
        return IdSeleccionNivInstrucc;
    }

    public void setIdSeleccionNivInstrucc(Long IdSeleccionNivInstrucc) {
        this.IdSeleccionNivInstrucc = IdSeleccionNivInstrucc;
    }

    public AlbInstruccionServicio getAlbInstruccionServicio() {
        return albInstruccionServicio;
    }

    public void setAlbInstruccionServicio(AlbInstruccionServicio albInstruccionServicio) {
        this.albInstruccionServicio = albInstruccionServicio;
    }

    public List<SelectItem> getListaEditarNivInstrucc() {
        this.listaEditarNivInstrucc = new ArrayList<SelectItem>();
        for (AlbInstruccion obj : listaNivelInstruccion) {
            SelectItem selectItem = new SelectItem(obj.getInsId(), obj.getInsNombre());
            this.listaEditarNivInstrucc.add(selectItem);
        }
        return listaEditarNivInstrucc;
    }

    public void setListaEditarNivInstrucc(List<SelectItem> listaEditarNivInstrucc) {
        this.listaEditarNivInstrucc = listaEditarNivInstrucc;
    }

    public Long getIdSeleccionProfesion() {
        return IdSeleccionProfesion;
    }

    public void setIdSeleccionProfesion(Long IdSeleccionProfesion) {
        this.IdSeleccionProfesion = IdSeleccionProfesion;
    }

    public List<SelectItem> getListaEditarProfesion() {
        this.listaEditarProfesion = new ArrayList<SelectItem>();
        for (AlbProfesion obj : listaProfesion) {
            SelectItem selectItem = new SelectItem(obj.getPrfId(), obj.getPrfProfesion());
            this.listaEditarProfesion.add(selectItem);
        }
        return listaEditarProfesion;
    }

    public void setListaEditarProfesion(List<SelectItem> listaEditarProfesion) {
        this.listaEditarProfesion = listaEditarProfesion;
    }

    public AlbProfesionServicio getAlbProfesionServicio() {
        return albProfesionServicio;
    }

    public void setAlbProfesionServicio(AlbProfesionServicio albProfesionServicio) {
        this.albProfesionServicio = albProfesionServicio;
    }

    public Long getIdSeleccionDiscapacidad() {
        return IdSeleccionDiscapacidad;
    }

    public void setIdSeleccionDiscapacidad(Long IdSeleccionDiscapacidad) {
        this.IdSeleccionDiscapacidad = IdSeleccionDiscapacidad;
    }

    public List<SelectItem> getListaEditarDiscapacida() {
        this.listaEditarDiscapacida = new ArrayList<SelectItem>();
        for (AlbDiscapacidad obj : listaDiscapacida) {
            SelectItem selectItem = new SelectItem(obj.getDisId(), obj.getDisTipo());
            this.listaEditarDiscapacida.add(selectItem);
        }
        return listaEditarDiscapacida;
    }
    
      public List<SelectItem> getListaEditarNumFlia() {
        this.listaEditarNumFlia = new ArrayList<SelectItem>();
        for (AlbFamilia obj : listaNumFlia) {
            SelectItem selectItem = new SelectItem(obj.getFamId(), String.valueOf(obj.getFamNumIntegrantes()));
            this.listaEditarNumFlia.add(selectItem);
        }
        return listaEditarNumFlia;
    }

    public void setListaEditarDiscapacida(List<SelectItem> listaEditarDiscapacida) {
        this.listaEditarDiscapacida = listaEditarDiscapacida;
    }

    public AlbDiscapacidadServicio getAlbDiscapacidadServicio() {
        return albDiscapacidadServicio;
    }

    public void setAlbDiscapacidadServicio(AlbDiscapacidadServicio albDiscapacidadServicio) {
        this.albDiscapacidadServicio = albDiscapacidadServicio;
    }

    public Long getIdSeleccionNumFlia() {
        return IdSeleccionNumFlia;
    }

    public void setIdSeleccionNumFlia(Long IdSeleccionNumFlia) {
        this.IdSeleccionNumFlia = IdSeleccionNumFlia;
    }

  

    public void setListaEditarNumFlia(List<SelectItem> listaEditarNumFlia) {
        this.listaEditarNumFlia = listaEditarNumFlia;
    }

    public AlbFamiliaServicio getAlbFamiliaServicio() {
        return albFamiliaServicio;
    }

    public void setAlbFamiliaServicio(AlbFamiliaServicio albFamiliaServicio) {
        this.albFamiliaServicio = albFamiliaServicio;
    }

    public AlbDamnificado getSelectedDamnificadoEditar() {
        return selectedDamnificadoEditar;
    }

    public void setSelectedDamnificadoEditar(AlbDamnificado selectedDamnificadoEditar) {
        this.selectedDamnificadoEditar = selectedDamnificadoEditar;
        editarDamnificadoSistema(selectedDamnificadoEditar);

    }

    public AlbDamnificado getSelectedAlbergueDamnificado() {
        return selectedAlbergueDamnificado;
    }

    public void setSelectedAlbergueDamnificado(AlbDamnificado selectedDamnificadoEditar) {
        this.selectedAlbergueDamnificado = selectedDamnificadoEditar;
        albergueDamnificadoSistema(selectedDamnificadoEditar);
    }

    public AlbDamnificado getSegDamnificadoObjects() {
        return segDamnificadoObjects;
    }

    public void setSegDamnificadoObjects(AlbDamnificado segDamnificadoObjects) {
        this.segDamnificadoObjects = segDamnificadoObjects;
    }

    public AlbDamnificado getSelectedSegDamnificadoEliminar() {
        return selectedSegDamnificadoEliminar;
    }

    public void setSelectedSegDamnificadoEliminar(AlbDamnificado selectedSegDamnificadoEliminar) {
        this.selectedSegDamnificadoEliminar = selectedSegDamnificadoEliminar;
        eliminarUsuarioSistema(selectedSegDamnificadoEliminar);
    }

    public AlbDamnificado getAlbDamnificado() {
        return albDamnificado;
    }

    public void setAlbDamnificado(AlbDamnificado albDamnificado) {
        this.albDamnificado = albDamnificado;
    }

    public AlbAlbergueServicio getAlbAlbergueServicio() {
        return albAlbergueServicio;
    }

    public void setAlbAlbergueServicio(AlbAlbergueServicio albAlbergueServicio) {
        this.albAlbergueServicio = albAlbergueServicio;
    }

    public List<SelectItem> getListaAsignarAlbergue() {
        this.listaAsignarAlbergue = new ArrayList<SelectItem>();
        for (AlbAlbergue obj : listaAlbergue) {
            SelectItem selectItem = new SelectItem(obj.getAlbId(), obj.getAlbNombre());
            this.listaAsignarAlbergue.add(selectItem);
        }
        return listaAsignarAlbergue;
    }

//    public void getListaCanton() {
//        AlbAlbergue obj1 = new AlbAlbergue();
//        if (IdSeleccionAlbergue != null) {
//            for (AlbAlbergue obj : listaAlbergue) {
//                if (IdSeleccionAlbergue == obj.getAlbId()) {
//                    obj.getAlbId();
//                    obj.getAlbNombre();
//                    obj.getAlbDireccion();
//                    albDamnificado1.addAlbergue(obj);                   
//                }
//            }
//
//        }
//
//    }
    public void setListaAsignarAlbergue(List<SelectItem> listaAsignarAlbergue) {
        this.listaAsignarAlbergue = listaAsignarAlbergue;
    }

    public Long getIdSeleccionAlbergue() {
        return IdSeleccionAlbergue;
    }

    public void setIdSeleccionAlbergue(Long IdSeleccionAlbergue) {
        this.IdSeleccionAlbergue = IdSeleccionAlbergue;
        // this.getListaCanton();
    }

    public AlbDamnificado getSegDamnificadoObjects1() {
        return segDamnificadoObjects1;
    }

    public void setSegDamnificadoObjects1(AlbDamnificado segDamnificadoObjects1) {
        this.segDamnificadoObjects1 = segDamnificadoObjects1;
    }

    public int getConsole() {
        return console;
    }

    public void setConsole(int console) {
        this.console = console;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }
    
    
   

    }
