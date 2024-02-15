package vista.listas.util;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controladores.EstudianteControlador;
import javax.swing.JComboBox;
import modelo.Estudiante;

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
}
