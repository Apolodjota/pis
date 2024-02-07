package modelo;
import com.sun.source.tree.BreakTree;
import controlador.TDALista.exceptions.VacioException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class PeriodoAcademico {
    private Integer id;
    private String nombre;
    private Date fechaDesde;
    private Date fechaHasta;
    private Boolean estado;

    public PeriodoAcademico() {
    }

    public PeriodoAcademico(Integer id, String nombre, Date fechaDesde, Date fechaHasta, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.estado = estado;
    }

    public Boolean comparar(PeriodoAcademico pa, String field, Integer type) throws VacioException{
        switch (type) {
            case 0:
                if(field.equalsIgnoreCase("nombre"))
                    return getNombre().compareToIgnoreCase(pa.getNombre()) < 0;
                else if(field.equalsIgnoreCase("fechaDesde"))
                    return getFechaDesde().before(pa.getFechaDesde());
                else if(field.equalsIgnoreCase("fechaHasta"))
                    return getFechaHasta().before(pa.getFechaHasta());
            case 1:
                if(field.equalsIgnoreCase("nombre"))
                    return getNombre().compareToIgnoreCase(pa.getNombre()) > 0;
                else if(field.equalsIgnoreCase("fechaDesde"))
                    return getFechaDesde().after(pa.getFechaDesde());
                else if(field.equalsIgnoreCase("fechaHasta"))
                    return getFechaHasta().after(pa.getFechaHasta());
            default:
                throw new AssertionError();
        }
    }
    
    public Integer esSimilar(String field, Object valor){
        if(field.equalsIgnoreCase("nombre")){
            String nomActual = getNombre().toLowerCase();
            if(nomActual.contains(valor.toString().toLowerCase()))
                return 0;
            else
                return 1;
        }else if(field.equalsIgnoreCase("fechaDesde")){
            SimpleDateFormat formatofe = new SimpleDateFormat("yy-MM-dd");
            String fecha = formatofe.format(getFechaDesde());
            return fecha.compareTo(valor.toString());
        }else if(field.equalsIgnoreCase("fechaHasta")){
            SimpleDateFormat formatofe = new SimpleDateFormat("yy-MM-dd");
            String fecha = formatofe.format(getFechaHasta());
            return fecha.compareTo(valor.toString());
        }
        return null;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    
}
