package vista.listas.tablas;
import controlador.TDALista.LinkedList;
import controladores.CursoControllerListas;
import controladores.DocenteControlador;
import controladores.MateriaControllerListas;
import javax.swing.table.AbstractTableModel;
import modelo.Cursa;
import modelo.Curso;
import modelo.Docente;
import modelo.Materia;

/**
 *
 * @author alexg
 */
public class ModeloTablaCursa extends AbstractTableModel{
    private LinkedList<Cursa> cursas = new LinkedList<>();
    MateriaControllerListas mC = new MateriaControllerListas();
    DocenteControlador dC = new DocenteControlador();
    CursoControllerListas cC = new CursoControllerListas();
    @Override
    public int getRowCount() {
        return getCursas().getSize();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Cursa cursa = getCursas().get(rowIndex);
            Materia materia = mC.getMaterias().get(cursa.getId_materia() - 1);
            Docente docente = dC.getDocentes().get(cursa.getId_docente() - 1);
            Curso curso = cC.getCursos().get(materia.getId_curso() - 1 );
            switch (columnIndex) {
                case 0:
                    return (cursa != null) ? cursa.getId(): "";
                case 1:
                    return (cursa != null) ? cursa.getId_periodo(): "";   
                case 2:
                    return (cursa != null) ? materia.getId() + materia.getNombre(): "";   
                case 3:
                    return (cursa != null) ? docente.getId()+". "+ docente.getCedula(): ""; 
                case 4:
                    return (cursa != null) ? curso.toString(): ""; 
                case 5:
                    return (cursa != null) ? cursa.getParalelo(): ""; 
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
                return "Código";
            case 1:
                return "Periodo";  
            case 2:
                return "Materia";
            case 3:
                return "Docente";
            case 4:
                return "Ciclo";
            case 5:
                return "Paralelo";
            default:
                return null;
        }
    }

    public LinkedList<Cursa> getCursas() {
        return cursas;
    }

    public void setCursas(LinkedList<Cursa> cursas) {
        this.cursas = cursas;
    }
}

