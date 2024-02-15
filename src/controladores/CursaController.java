/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Cursa;
import modelo.Curso;
import modelo.Docente;
import modelo.Materia;
import modelo.PeriodoAcademico;

/**
 *
 * @author apolo
 */
public class CursaController extends  AdaptadorDao<Cursa>{
    private Cursa cursa = new Cursa();
    private Integer index = - 1;
    private LinkedList<Cursa> cursas = new LinkedList<>();
    
    public CursaController() {
        super(Cursa.class);
    }
    
    public Integer guardar() {
        try {
            return guardar(cursa);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                    e.getMessage(), 
                    "ERROR", 
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
    }

    public void update(Cursa cursa) {
        try {
            modificar(cursa);
        } catch (Exception ex) {
            Logger.getLogger(CursaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public LinkedList<Cursa> mergeSort(LinkedList<Cursa> lista, Integer type, String field) {
        Cursa[] m = lista.toArray();
        mergeSort(m, 0, m.length - 1, field, type);
        return lista = lista.toList(m);
    }

    private void mergeSort(Cursa[] m, Integer ini, Integer fin, String field, Integer type) {
        if (ini < fin) {
            int medio = (ini + fin) / 2;
            mergeSort(m, ini, medio, field, type);
            mergeSort(m, medio + 1, fin, field, type);
            merge(m, ini, medio, fin, field, type);
        }
    }

    private void merge(Cursa[] m, Integer ini, Integer medio, Integer fin, String field, Integer type) {
        Integer izq = ini;
        Integer der = medio + 1;
        Integer k = 0;
        Integer n = fin - ini + 1;
        Cursa[] result = new Cursa[n];
        while (izq <= medio && der <= fin) {
            if (m[izq].comparar(m[der], field, type)) {
                result[k] = m[izq];
                izq++;
            } else {
                result[k] = m[der];
                der++;
            }
            k++;
        }
        while (izq <= medio) {
            result[k] = m[izq];
            izq++;
            k++;
        }
        while (der <= fin) {
            result[k] = m[der];
            der++;
            k++;
        }
        for (k = 0; k < n; k++) {
            m[ini + k] = result[k];
        }
    }

    public LinkedList<Cursa> quickSort(LinkedList<Cursa> lista, Integer type, String field) {
        Cursa[] m = lista.toArray();
        Integer fin = m.length - 1;
        quickSort(m, type, field, 0, fin);
        //setVentas(lista.toList(m));
        return lista = lista.toList(m);
    }

    private void quickSort(Cursa[] m, Integer type, String field, Integer inicio, Integer fin) {
        
        if (inicio >= fin) return;
        
        Cursa pivote = m[inicio];
        Integer elemIzq = inicio + 1;
        Integer elemDer = fin;
        while (elemIzq <= elemDer) {
            while (elemIzq <= fin && m[elemIzq].comparar(pivote, field, type)) {
                elemIzq++;
            }
            while (elemDer > inicio && !m[elemDer].comparar(pivote, field, type)) {
                elemDer--;
            }
            if (elemIzq < elemDer) {
                Cursa temp = m[elemIzq];
                m[elemIzq] = m[elemDer];
                m[elemDer] = temp;
            }
        }
        if (elemDer > inicio) {
            Cursa temp = m[inicio];
            m[inicio] = m[elemDer];
            m[elemDer] = temp;
        }
        quickSort(m, type, field, inicio, elemDer - 1);
        quickSort(m, type, field, elemDer + 1, fin);
    }

    public LinkedList<Cursa> buscar(LinkedList<Cursa> lista, String text) throws VacioException, Exception {
        LinkedList<Cursa> lo = this.quickSort(lista, 0, "id");
        Cursa[] m = lo.toArray();
        LinkedList<Cursa> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getId().toString().toLowerCase().contains(text.toLowerCase())) {
                result.add(m[i]);
            }
        }
        return result;
    }
    public LinkedList<Cursa> buscarDocente(LinkedList<Cursa> lista, String text, Docente docente  ) throws VacioException, Exception {
        LinkedList<Cursa> lo = this.quickSort(lista, 1 , text);
        Cursa[] m = lo.toArray();
        LinkedList<Cursa> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getId_docente().intValue() == docente.getId().intValue()) {
                result.add(m[i]);
            }
        }
        return result;
    }
    
    public LinkedList<Cursa> buscarDocente(LinkedList<Cursa> lista, String text, Materia materia  ) throws VacioException, Exception {
        LinkedList<Cursa> lo = this.quickSort(lista, 1 , text);
        Cursa[] m = lo.toArray();
        LinkedList<Cursa> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getId_materia().intValue() == materia.getId().intValue()) {
                result.add(m[i]);
            }
        }
        return result;
    }
    
    public LinkedList<Cursa> buscarPeriodo(LinkedList<Cursa> lista, String text, PeriodoAcademico periodo  ) throws VacioException, Exception {
        LinkedList<Cursa> lo = this.quickSort(lista, 1 , text);
        Cursa[] m = lo.toArray();
        LinkedList<Cursa> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getId_periodo().intValue() == periodo.getId().intValue()) {
                result.add(m[i]);
            }
        }
        return result;
    }
    
    /**
     * @return the cursa
     */
    public Cursa getCursa() {
        if(cursa == null) cursa = new Cursa();
        return cursa;
    }

    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public LinkedList<Cursa> getCursas() {
        if(cursas.isEmpty()) cursas = listar();
        return cursas;
    }

    public void setCursas(LinkedList<Cursa> cursas) {
        this.cursas = cursas;
    }
}
