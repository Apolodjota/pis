package vista.listas.util;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controladores.EstudianteControlador;
import controladores.PeriodoController;
import javax.swing.JComboBox;
import modelo.Estudiante;
import modelo.PeriodoAcademico;

/**
 *
 * @author Asus
 */
public class UtilVistaLista {
    public static void cargarMarcaEst(JComboBox cbxmarca) throws VacioException{
        EstudianteControlador ec = new EstudianteControlador();
        LinkedList<Estudiante> estudiantes = new LinkedList<>();
        //LinkedList<Estudiante> estudiantes = new EstudianteControlador().listar();
        cbxmarca.removeAllItems();
        for(int i = 0; i < estudiantes.getSize(); i++){
            cbxmarca.addItem(estudiantes.get(i).toString());
        }
    }
    
    public static Estudiante getcomboMarcasEst(JComboBox cbx){
        return (Estudiante) cbx.getSelectedItem();
    }
    
    public static void cargarPeriodos(JComboBox cbx) throws VacioException{
        PeriodoController pc = new PeriodoController();
        LinkedList<PeriodoAcademico> periodos = pc.listar();
        cbx.removeAllItems();
        for(int i = 0; i < periodos.getSize(); i++){
            cbx.addItem(periodos.get(i));
        }
    }
    
    public static PeriodoAcademico getcomboPeriodo(JComboBox cbx){
        return (PeriodoAcademico) cbx.getSelectedItem();
    }
}
