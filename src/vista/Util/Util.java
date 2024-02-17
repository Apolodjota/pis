
package vista.Util;

import controlador.TDALista.LinkedList;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import modelo.Docente;
import modelo.Estudiante;

/**
 *
 * @author sakotaz
 */
public class Util {
    public static LinkedList <Estudiante> retornar(Estudiante e) {
        LinkedList <Estudiante> lista = new LinkedList<>();
        lista.add(e);
        return lista;
    }
    
    public static LinkedList <Docente> retornarDocente (Docente d) {
        LinkedList <Docente> lista = new LinkedList<>();
        lista.add(d);
        return lista;
    }
    
    private static SecretKey getClave() throws Exception{
        KeyGenerator key = KeyGenerator.getInstance("DES");
        SecretKey claveSecreta = key.generateKey(); 
        return claveSecreta;
    }
    
    public static String cifrarMensaje(String mensaje) throws Exception{
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, getClave());
        byte[] msjCifradoBit = cipher.doFinal(mensaje.getBytes());
        String mensajeCifrado = Base64.getEncoder().encodeToString(msjCifradoBit);
        return mensajeCifrado;
    }
    
    public static String descifrarMensaje(String mensaje) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, getClave());
        byte[] msjDescifradoBit = cipher.doFinal(Base64.getDecoder().decode(mensaje));
        String mensajeDecifrado = new String(msjDescifradoBit);
        return mensajeDecifrado;
    }
}
