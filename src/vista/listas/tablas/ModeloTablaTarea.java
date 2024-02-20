package vista.listas.tablas;
import controlador.TDALista.LinkedList;
import controladores.TareaController;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import modelo.Asignacion;
import modelo.Cursa;
import modelo.Curso;
import modelo.Docente;
import modelo.Materia;
import modelo.Tarea;

/**
 *
 * @author Asus
 */
public class ModeloTablaTarea extends AbstractTableModel{
    private TareaController tc = new TareaController();
    private LinkedList<Tarea> tareas = new LinkedList<>();

    @Override
    public int getRowCount() {
        return tareas.getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
                Tarea t = getTareas().get(rowIndex);
                switch (columnIndex) {
                case 0:
                    return (t != null) ? t.getTitulo(): "";
                case 1:
                    return (t != null) ? new SimpleDateFormat("dd-MM-yyyy HH:mm").format(t.getFechaAsignacion()): "";   //cursa.getId_periodo(): "";   
                    //return (a != null) ? t.getFechaEntrega(): "";
                case 2:
                    return (t != null) ? new SimpleDateFormat("dd-MM-yyyy HH:mm").format(t.getFechaEntrega()): "";   //cursa.getId_periodo(): "";     
//                case 3:
//                    return (a != null && t.getFechaEntrega().before(new Date())) ? a.getCalificacion() : ""; 
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
                return "Titulo";
            case 1:
                return "Fecha de asignación";  
            case 2:
                return "Fecha de entrega";
//            case 3:
//                return "Descripción";
            default:
                return null;
        }
    }

    public LinkedList<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(LinkedList<Tarea> tareas) {
        this.tareas = tareas;
    }
    
    
}
