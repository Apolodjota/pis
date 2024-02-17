package pis;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import controladores.CuentaControllerListas;
import controladores.EstudianteControlador;
import controladores.PersonaController;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import modelo.Cuenta;
import modelo.Persona;

/**
 *
 * @author alexg
 */
public class PIS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        CuentaControllerListas cc = new CuentaControllerListas();
//        Cuenta cuenta = new Cuenta();
//        Field[] fields = cuenta.getClass().getDeclaredFields();
//        for (Field f : fields) {
//            System.out.println("--" + f.getName());
//        }
//        EstudianteControlador ec = new EstudianteControlador();
//        ec.getEstudiantes();
//        System.out.println("estudaintes-->" + ec.getEstudiantes().getSize());
//        System.out.println("1er " + ec.getEstudiantes().get(1).getTrabaja());
//        JDateChooser data = new JDateChooser();
//        
//        // Establecer la fecha en el JDateChooser
//        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
//        Date date;
//        try {
//            date = formato.parse("03-04-2023");
//            data.setDate(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Obtener la fecha del JDateChooser
//        Date fecha = data.getDate();
//        
//        Persona persona = new Persona();
//        persona.setNombres("nector");
//        persona.setApellidos("jacinto");
//        persona.setCedula("221");
//        persona.setDireccionResidencia("loa");
//        persona.setFechaNacimiento(fecha);
//        persona.setGenero("f");
//        persona.setId_rol(1);
//        persona.setTelefonoCasa("09");
//        persona.setTelefonoCelular("0998");        
//        persona.setNombres("juan");
//        PersonaController pc = new PersonaController();
//        pc.setPersona(persona);
//        
//        try {
//            Field [] campos = persona.getClass().getDeclaredFields();
//            campos[0].setAccessible(true);
//            campos[0].get(persona);
//            
//            System.out.println("valor" + campos[0].getName());
//            
//        } catch (SecurityException ex) {
//            System.out.println("-->" + ex.getMessage());
//        }
//        
    }

}
