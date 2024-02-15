
package pis;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import controladores.PersonaController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Persona;

/**
 *
 * @author alexg
 */
public class PIS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        JDateChooser data = new JDateChooser();

        // Establecer la fecha en el JDateChooser
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = formato.parse("03-04-2023");
            data.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Obtener la fecha del JDateChooser
        Date fecha = data.getDate();
        
        Persona persona = new Persona();
        persona.setNombres("nector");
        persona.setApellidos("jacinto");
        persona.setCedula("221");
        persona.setDireccionResidencia("loa");
        persona.setFechaNacimiento(fecha);
        persona.setGenero("f");
        persona.setId_rol(1);
        persona.setTelefonoCasa("09");
        persona.setTelefonoCelular("0998");        
        persona.setNombres("juan");
        PersonaController pc = new PersonaController();
        pc.setPersona(persona);
        try {
        System.out.println("Integer de bd persona -->" + pc.save());
        } catch (Exception e) {
            System.out.println("error " + e.getMessage());
        }
        
    }
    
}
