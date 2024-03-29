
package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.TDALista.LinkedList;
import controlador.listas.DAO.DataAccesObject;
import controlador.Utilidades.Utilidades;
import java.lang.reflect.Field;
import modelo.Curso;
import modelo.Malla;

/**
 *
 * @author alexg
 */
public class CursoControllerListas extends AdaptadorDao<Curso>{
    
    private LinkedList<Curso> cursos = new LinkedList<>();
    private Curso curso = new Curso();
    private Integer index = -1;
    
    public CursoControllerListas() {
        super(Curso.class);
    }

    /**
     * @return the cursos
     */
    public LinkedList<Curso> getCursos() {
        if (cursos.isEmpty()) 
            cursos = listar();
            return cursos;
    }

    /**
     * @param cursos the cursos to set
     */
    public void setCursos(LinkedList<Curso> cursos) {
        this.cursos = cursos;
    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        if (curso == null) {
            curso = new Curso();
        }
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
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

    public Integer save() {
        return guardar(curso);
    }
    
    public void update(Curso curso) throws Exception {
        modificar(curso);
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
    
    public LinkedList<Curso> quickSort(Integer type, String field, LinkedList<Curso> lista) throws Exception {
        getCurso();  
        Integer n = lista.getSize();    
        Curso[] c = lista.toArray();   
        Field faux = Utilidades.getField(Curso.class, field);    
        if (faux != null) {    
            quickSort(c, 0, n - 1, type, field);    
            lista = lista.toList(c);    
        } else {
            throw new Exception("No existe ese criterio de busqueda");      
        }
        return lista;      
    }

    //Metodo de Ordenamiento: QUICK SORT

    public void quickSort(Curso[] arreglo, int inicio, int fin, Integer type, String field) throws Exception {
        int i = inicio;
        int j = fin;
        Curso pivote = arreglo[(inicio + fin) / 2];
        do {
            while (arreglo[i].comparar(pivote, field, type)) {
                i++;
            }
            while (pivote.comparar(arreglo[j], field, type)) {
                j--;
            }
            if (i <= j) {
                Curso aux = arreglo[i];
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

    //Busqueda Lineal
    
    public LinkedList<Curso> buscarCiclo(LinkedList<Curso> lista, String text, Integer ciclo) throws Exception {
        LinkedList<Curso> lo = this.quickSort(0, text, lista);
        Curso[] c = lo.toArray();
        LinkedList<Curso> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getCiclo().equals(ciclo)) {
                result.add(c[i]);
            }
        }
        return result;
    }
    
    public Curso buscarCurso(LinkedList <Curso> lista, String text, Integer id)throws Exception{
        lista = this.quickSort(0, text, lista);
        for (int i = 0; i < lista.getSize(); i++) {
            if (lista.get(i).getId().intValue() == id.intValue()){
                return lista.get(i);
            }
        }
        return null;
    }

//    public LinkedList<Curso> buscarParalelo(LinkedList<Curso> lista, String text, String paralelo) throws Exception {
//        LinkedList<Curso> lo = this.quickSort(0, text, lista);
//        Curso[] c = lo.toArray();
//        LinkedList<Curso> result = new LinkedList<>();
//        for (int i = 0; i < lo.getSize(); i++) {
//            if (c[i].getParalelo().equals(paralelo)) {
//                result.add(c[i]);
//            }
//        }
//        return result;
//    }
    
    public LinkedList<Curso> buscarMalla(LinkedList<Curso> lista, String text, Malla malla) throws Exception {
        LinkedList<Curso> lo = this.quickSort(0, text, lista);
        Curso[] c = lo.toArray();
        LinkedList<Curso> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (c[i].getId_malla().equals(malla.getId())) {
                result.add(c[i]);
            }
        }
        return result;
    }
    
}
