
package vista.Util;

import controlador.TDALista.LinkedList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import modelo.Docente;
import modelo.Estudiante;

/**
 *
 * @author sakotaz
 */
public class Util {
    private final SecretKey claveSecreta = generarClaveSecreta("pepe");

    public Util() throws Exception {
    }
    
    public static SecretKey generarClaveSecreta(String clave) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(clave.getBytes());
        byte[] claveBytes = new byte[8];
        System.arraycopy(hash, 0, claveBytes, 0, 8);
        return new SecretKeySpec(claveBytes, "DES");
    }
    
    public SecretKey getClave(){
        return claveSecreta;
    }
    
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
    
    public String cifrarMensaje(String mensaje) throws Exception{ 
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, getClave());
        byte[] msjCifradoBit = cipher.doFinal(mensaje.getBytes());
        String mensajeCifrado = Base64.getEncoder().encodeToString(msjCifradoBit);
        return mensajeCifrado;
    }
    
    public String descifrarMensaje(String mensaje) throws Exception {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, getClave());
            byte[] msjDescifradoBit = cipher.doFinal(Base64.getDecoder().decode(mensaje));
            String mensajeDecifrado = new String(msjDescifradoBit);
            return mensajeDecifrado;
    }
}
