
package controlador;

import controlador.TDALista.LinkedList;
import controlador.listas.DAO.DataAccesObject;
import controlador.util.Utilidades;
import java.lang.reflect.Field;
import modelo.Rol;

/**
 *
 * @author alexg
 */
public class RolControllerListas extends DataAccesObject<Rol>{
    
    private LinkedList<Rol> roles = new LinkedList<>();
    private Rol rol = new Rol();
    private Integer index = -1;
    
    public RolControllerListas() {
        super(Rol.class);
    }

    /**
     * @return the roles
     */
    public LinkedList<Rol> getRoles() {
        if (roles.isEmpty()) 
            roles = listall();
            return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(LinkedList<Rol> roles) {
        this.roles = roles;
    }

    /**
     * @return the rol
     */
    public Rol getRol() {
        if (rol == null) {
            rol = new Rol();
        }
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
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

    public Boolean save() {
        this.rol.setId(generated_id());
        return save(rol);
    }
    
    public Boolean update(Integer index) {
        return update(rol, index);
    }
    
    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer lenght = listall().getSize() + 1;
        Integer pos = lenght.toString().length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(lenght.toString());
        return code.toString();
    }
    
    public LinkedList<Rol> quickSort(Integer type, String field, LinkedList<Rol> lista) throws Exception {
        getRol();  
        Integer n = lista.getSize();    
        Rol[] r = lista.toArray();   
        Field faux = Utilidades.getField(Rol.class, field);    
        if (faux != null) {    
            quickSort(r, 0, n - 1, type, field);    
            lista = lista.toList(r);    
        } else {
            throw new Exception("No existe ese criterio de busqueda");      
        }
        return lista;      
    }

    //Metodo de Ordenamiento: QUICK SORT

    public void quickSort(Rol[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Rol pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Rol aux = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);

        if (inicio < j)
            quickSort(arreglo, inicio, j, type, field);
        if (i < fin)
            quickSort(arreglo, i, fin, type, field);
    }
    
    public static void main(String[] args) {
        RolControllerListas rc = new RolControllerListas();
        rc.getRol().setId(1);
        rc.getRol().setNombre("Admin");
        rc.save();
        
        rc.getRol().setId(2);
        rc.getRol().setNombre("Docente");
        rc.save();
        
        rc.getRol().setId(3);
        rc.getRol().setNombre("Estudiante");
        rc.save();        
    }
    
}
