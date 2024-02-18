package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.TDALista.LinkedList;
import controlador.listas.DAO.DataAccesObject;
import controlador.Utilidades.Utilidades;
import java.lang.reflect.Field;
import modelo.Cuenta;
import modelo.Estudiante;
import modelo.Persona;

/**
 *
 * @author alexg
 */
public class CuentaControllerListas extends AdaptadorDao<Cuenta> {

    private LinkedList<Cuenta> cuentas = new LinkedList<>();
    private Cuenta cuenta = new Cuenta();
    private Integer index = -1;

    public CuentaControllerListas() {
        super(Cuenta.class);
    }

    /**
     * @return the cuentas
     */
    public LinkedList<Cuenta> getCuentas() {
        if (cuentas.isEmpty()) {
            cuentas = listar();
        }
        return cuentas;
    }

    /**
     * @param cuentas the cuentas to set
     */
    public void setCuentas(LinkedList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        if (cuenta == null) {
            cuenta = new Cuenta();
        }
        return cuenta;
    }

    /**
     * @param cuenta the materia to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    public void save() {
        guardar(cuenta);
    }

    public void update(Integer index) throws Exception{
        modificar(cuenta);
    }

    public Cuenta obtenerCuenta(Integer id_cuenta){
        return obtener(id_cuenta);
    }
    
    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer lenght = listar().getSize() + 1;
        Integer pos = lenght.toString().length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(lenght.toString());
        return code.toString();
    }

    public LinkedList<Cuenta> quickSort(Integer type, String field, LinkedList<Cuenta> lista) throws Exception {
        getCuenta();
        Integer n = lista.getSize();
        Cuenta[] c = lista.toArray();
        Field faux = Utilidades.getField(Cuenta.class, field);
        if (faux != null) {
            quickSort(c, 0, n - 1, type, field);
            lista = lista.toList(c);
        } else {
            throw new Exception("No existe ese criterio de busqueda");
        }
        return lista;
    }

    //Metodo de Ordenamiento: QUICK SORT
    public void quickSort(Cuenta[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Cuenta pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Cuenta aux = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);

        if (inicio < j) {
            quickSort(arreglo, inicio, j, type, field);
        }
        if (i < fin) {
            quickSort(arreglo, i, fin, type, field);
        }
    }

    public LinkedList<Cuenta> buscarCorreo(LinkedList<Cuenta> lista, String text, String correo) throws Exception {
        LinkedList<Cuenta> lo = this.quickSort(0, text, lista);
        Cuenta[] c = lo.toArray();
        LinkedList<Cuenta> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getCorreo().equals(correo)) {
                result.add(c[i]);
            }
        }
        return result;
    }

    public LinkedList<Cuenta> buscarClave(LinkedList<Cuenta> lista, String text, String clave) throws Exception {
        LinkedList<Cuenta> lo = this.quickSort(0, text, lista);
        Cuenta[] c = lo.toArray();
        LinkedList<Cuenta> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getClave().toString().equals(clave)) {
                result.add(c[i]);
            }
        }
        return result;
    }

    public Integer obtenerIdPorCorreo(LinkedList<Cuenta> lista, String correo) throws Exception {
        LinkedList<Cuenta> lo = this.quickSort(0, "correo", lista);
        Cuenta[] c = lo.toArray();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getCorreo().equals(correo)) {
                return c[i].getId();
            }
        }
        return -1; // Devuelve -1 si no se encuentra ninguna cuenta con el correo ingresado
    }

    public Integer obtenerIdPorClave(LinkedList<Cuenta> lista, String clave) throws Exception {
        LinkedList<Cuenta> lo = this.quickSort(0, "clave", lista);
        Cuenta[] c = lo.toArray();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getClave().toString().equals(clave)) {
                return c[i].getId();
            }
        }
        return -1; // Devuelve -1 si no se encuentra ninguna cuenta con la clave ingresada
    }

    
    
//    public Integer obtenerIdPorRol(LinkedList<Cuenta> lista, Integer id) throws Exception {
//        LinkedList<Cuenta> lo = this.quickSort(0, "id_rol", lista);
//        Cuenta[] c = lo.toArray();
//        for (int i = 0; i < lo.getSize(); i++) {
//            if (c[i].getId_rol().equals(id)) {
//                return c[i].getId();
//            }
//        }
//        return -1; // Devuelve -1 si no se encuentra ninguna cuenta con la clave ingresada
//    }

//    public Integer obtenerIdPorCredencial(LinkedList<Cuenta> lista, String credencial, String tipo) throws Exception {
//        LinkedList<Cuenta> lo = this.quickSort(0, tipo, lista);
//        Cuenta[] c = lo.toArray();
//        for (int i = 0; i < lo.getSize(); i++) {
//            if (tipo.equals("correo")) {
//                if (c[i].getCorreo().equals(credencial)) {
//                    return c[i].getId();
//                }
//            } else if (tipo.equals("clave")) {
//                if (c[i].getClave().toString().equals(credencial)) {
//                    return c[i].getId();
//                }
//            }
//        }
//        return -1; // Devuelve -1 si no se encuentra ninguna cuenta con la credencial ingresada
//    }
    
    private Persona retornarInfoCuenta(){
        return new Estudiante();
    }
    public static void main(String[] args) {
        CuentaControllerListas cc = new CuentaControllerListas();

        cc.getCuenta().setCorreo("apolo");
        cc.getCuenta().setClave("123");
        cc.getCuenta().setEstado("T");
        cc.getCuenta().setId_persona(8);
//        cc.getCuenta().setId_rol(2);
        cc.save();
        
//        cc.getCuenta().setCorreo("admin");
//        cc.getCuenta().setClave("2004");
//        cc.getCuenta().setId_persona(6);
//        cc.getCuenta().setEstado("T");
////        cc.getCuenta().setId_rol(1);
//        cc.save();        
////        
//        cc.getCuenta().setCorreo("ger");
//        cc.getCuenta().setClave("456");
//        cc.getCuenta().setId_persona(7);
//        cc.getCuenta().setEstado("T");
////        cc.getCuenta().setId_rol(3);
//        cc.save();         
    }
}
