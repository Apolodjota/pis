package vista.listas.tablas;
import controlador.TDALista.LinkedList;
import controladores.EstudianteControlador;
import controladores.PeriodoController;
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import modelo.Estudiante;
import modelo.Matricula;
import modelo.PeriodoAcademico;

/**
 *
 * @author alexg
 */
public class ModeloTablaMatricula extends AbstractTableModel{
    EstudianteControlador ec = new EstudianteControlador();
    PeriodoController pc = new PeriodoController();
    private LinkedList<Matricula> matriculas = new LinkedList<>();
    
    @Override
    public int getRowCount() {
        return getMatriculas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Matricula matricula = getMatriculas().get(rowIndex);
            Estudiante estudiante = ec.getEstudiantes().get(matricula.getId_estudiante() - 1);
            PeriodoAcademico periodo = pc.getPeriodos().getLast();
            //String estado = (matricula.getGratuidad()== true) ? "Conservada" : "Retirada";
            switch (columnIndex) {
                case 0:
                    return (matricula != null) ? matricula.getId() : "";
                case 1:
                    return (matricula != null) ? estudiante.toString(): "";  
                case 2:
                    return (matricula != null) ? periodo.getNombre() : ""; 
                case 3:
                    //return (matricula != null) ? estado: ""; 
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Matricula";
            case 1:
                return "Estudiante";  
            case 2:
                return "Periodo";  
            /*case 3:
                return "Estado";*/
            default:
                return null;
        }
    }

    public LinkedList<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(LinkedList<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}

