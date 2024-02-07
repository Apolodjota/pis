package vista.listas.util;
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
        cbxmarca.removeAllItems();
        for(int i = 0; i < ec.getEstudiantes().getSize(); i++){
            cbxmarca.addItem(ec.getEstudiantes().get(i).toString());
        }
    }
    
    public static Estudiante getcomboMarcasEst(JComboBox cbx){
        return (Estudiante) cbx.getSelectedItem();
    }
}
