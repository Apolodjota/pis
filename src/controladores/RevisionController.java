/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controlador.listas.DAO.DataAccesObject;
import modelo.Revision;

/**
 *
 * @author apolo
 */
public class RevisionController extends DataAccesObject<Revision>{
    private Revision revision = new Revision();
    private LinkedList<Revision> revisiones = new LinkedList<>();
    
    public RevisionController() {
        super(Revision.class);
    }
    
    
    
    
    public Boolean guardar() {
        getRevision().setId(generated_id());
        return save(getRevision());
    }

    public Boolean update(int fila) {
        return update(getRevision(), fila);
    }

//    public String generatedCode() {
//        StringBuilder code = new StringBuilder();
//        Integer length = listall().getSize() + 1;
//        Integer pos = length.toString().length();
//        for (int i = 0; i < (10 - pos); i++) {
//            code.append("0");
//        }
//        code.append(length.toString());
//        return code.toString();
//    }


    public LinkedList<Revision> mergeSort(LinkedList<Revision> lista, Integer type, String field) {
        Revision[] m = lista.toArray();
        mergeSort(m, 0, m.length - 1, field, type);
        return lista = lista.toList(m);
    }

    private void mergeSort(Revision[] m, Integer ini, Integer fin, String field, Integer type) {
        if (ini < fin) {
            int medio = (ini + fin) / 2;
            mergeSort(m, ini, medio, field, type);
            mergeSort(m, medio + 1, fin, field, type);
            merge(m, ini, medio, fin, field, type);
        }
    }

    private void merge(Revision[] m, Integer ini, Integer medio, Integer fin, String field, Integer type) {
        Integer izq = ini;
        Integer der = medio + 1;
        Integer k = 0;
        Integer n = fin - ini + 1;
        Revision[] result = new Revision[n];
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

    public LinkedList<Revision> quickSort(LinkedList<Revision> lista, Integer type, String field) {
        Revision[] m = lista.toArray();
        Integer fin = m.length - 1;
        quickSort(m, type, field, 0, fin);
        //setVentas(lista.toList(m));
        return lista = lista.toList(m);
    }

    private void quickSort(Revision[] m, Integer type, String field, Integer inicio, Integer fin) {
        
        if (inicio >= fin) return;
        
        Revision pivote = m[inicio];
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
                Revision temp = m[elemIzq];
                m[elemIzq] = m[elemDer];
                m[elemDer] = temp;
            }
        }
        if (elemDer > inicio) {
            Revision temp = m[inicio];
            m[inicio] = m[elemDer];
            m[elemDer] = temp;
        }
        quickSort(m, type, field, inicio, elemDer - 1);
        quickSort(m, type, field, elemDer + 1, fin);
    }

    public LinkedList<Revision> buscar(LinkedList<Revision> lista, String text) throws VacioException, Exception {
        LinkedList<Revision> lo = this.quickSort(lista, 0, "id");
        Revision[] m = lo.toArray();
        LinkedList<Revision> result = new LinkedList<>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (m[i].getId().toString().toLowerCase().contains(text.toLowerCase())) {
                result.add(m[i]);
            }
        }
        return result;
    }
 
    /**
     * @return the revision
     */
    public Revision getRevision() {
        return revision;
    }

    /**
     * @param revision the revision to set
     */
    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    /**
     * @return the revisiones
     */
    public LinkedList<Revision> getRevisiones() {
        return revisiones;
    }

    /**
     * @param revisiones the revisiones to set
     */
    public void setRevisiones(LinkedList<Revision> revisiones) {
        this.revisiones = revisiones;
    }
    
    
    
}
