/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Apolo
 */
public class Asignasion {
    private Integer id;
    private Integer id_cursa;
    private Integer unidad;
    private String titulo;
    private String descripcion;
    private Byte archivo;
    private Date fechaAsignacion;
    private Date fechaEntrega;

    public Asignasion() {
    }

    public Asignasion(Integer id, Integer id_cursa, Integer unidad, String titulo, String descripcion, Byte archivo, Date fechaAsignacion, Date fechaEntrega) {
        this.id = id;
        this.id_cursa = id_cursa;
        this.unidad = unidad;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.archivo = archivo;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_cursa() {
        return id_cursa;
    }

    public void setId_cursa(Integer id_cursa) {
        this.id_cursa = id_cursa;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Byte getArchivo() {
        return archivo;
    }

    public void setArchivo(Byte archivo) {
        this.archivo = archivo;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    public Boolean comparar(Asignasion a, Integer type, String field){
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() > a.getId().intValue();
                else if (field.equalsIgnoreCase("id_cursa"))
                    return getId_cursa()> a.getId_cursa();
                else if (field.equalsIgnoreCase("unidad"))
                    return getUnidad() > a.getUnidad();
                else if (field.equalsIgnoreCase("titulo"))
                    return getTitulo().compareToIgnoreCase(a.getTitulo()) > 0 ;
                else if (field.equalsIgnoreCase("fechaAsignacion"))
                    return getFechaAsignacion().after(a.getFechaAsignacion());
                else if (field.equalsIgnoreCase("fechaEntrega"))
                    return getFechaEntrega().after(a.getFechaEntrega());
            case 0:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() < a.getId().intValue();
                else if (field.equalsIgnoreCase("id_cursa"))
                    return getId_cursa()< a.getId_cursa();
                else if (field.equalsIgnoreCase("unidad"))
                    return getUnidad() < a.getUnidad();
                else if (field.equalsIgnoreCase("titulo"))
                    return getTitulo().compareToIgnoreCase(a.getTitulo()) < 0 ;
                else if (field.equalsIgnoreCase("fechaAsignacion"))
                    return getFechaAsignacion().before(a.getFechaAsignacion());
                else if (field.equalsIgnoreCase("fechaEntrega"))
                    return getFechaEntrega().before(a.getFechaEntrega());
                
            default:
                return false;
        }
    }
    
    
    
}
