package vista.listas.tablas;
import controlador.TDALista.LinkedList;
import controladores.EstudianteControlador;
import controladores.TareaController;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import modelo.Asignacion;
import modelo.Cursa;
import modelo.Curso;
import modelo.Docente;
import modelo.Estudiante;
import modelo.Materia;
import modelo.Tarea;

/**
 *
 * @author Asus
 */
public class ModeloTablaAsignacionEstudiante extends AbstractTableModel{
    private TareaController tc = new TareaController();
    private EstudianteControlador ec = new EstudianteControlador();
    private LinkedList<Asignacion> asignaciones = new LinkedList<>();
    private LinkedList<Estudiante> estudiantesFilas = new LinkedList<>();
    private LinkedList<Tarea> tareasFilas = new LinkedList<>();

    
    @Override
    public int getRowCount() {
        return asignaciones.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
              Asignacion a = getAsignaciones().get(rowIndex);
              Estudiante e = estudiantesFilas.get(rowIndex);
              Tarea t = tareasFilas.get(rowIndex);
              String estado = "Sin entregar";
              if(a.getEstado().equalsIgnoreCase("E")) estado = "Entregado";
              if(a.getEstado().equalsIgnoreCase("C")) estado = "Calificado";
//            Cursa cursa = getCursas().get(rowIndex);
//            Materia materia = mC.getMaterias().get(cursa.getId_materia() - 1);
//            Docente docente = dC.getDocentes().get(cursa.getId_docente() - 1);
//            Curso curso = cC.getCursos().get(materia.getId_curso() - 1 );
            switch (columnIndex) {
                case 0:
                    return (a != null) ? e.toString() : "";
                case 1:
                    return (a != null) ? t.getTitulo(): "";   //cursa.getId_periodo(): "";   
                    //return (a != null) ? t.getFechaEntrega(): "";
                case 2:
                    return (a != null) ? estado: "";   
                case 3:
                    //return (a != null && t.getFechaEntrega().before(new Date())) ? a.getCalificacion() : "";
                    return (a.getFechaEntrega() != null ) ? a.getFechaEntrega(): ""; 
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Error en modelo tabla asignacion: "+e.getMessage());
            //e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Estudiante";
            case 1:
                return "Tarea";  
            case 2:
                return "Estado";
            case 3:
                return "Fecha de entrega";
            default:
                return null;
        }
    }

    public LinkedList<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(LinkedList<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
        try {
            for (int i = 0; i < this.asignaciones.getSize(); i++) {
                estudiantesFilas.add(ec.obtenerEstudianteporIDcursa(this.asignaciones.get(i).getId_cursa()));
                tareasFilas.add(tc.buscarTarea(this.asignaciones.get(i).getId_tarea()));
            }   
        } catch (Exception e) {
            System.out.println("error construyendo MTAsigEstu: "+e);
        }
    }
    
    
}
