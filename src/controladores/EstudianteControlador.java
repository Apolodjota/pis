/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controlador.listas.DAO.DataAccesObject;
import modelo.Estudiante;

/**
 *
 * @author sakotaz
 */
public class EstudianteControlador extends DataAccesObject <Estudiante>{
    private LinkedList <Estudiante> estudiantes = new LinkedList <Estudiante>();
    private Estudiante estudiante = new Estudiante();

    public EstudianteControlador() {
        super(Estudiante.class);
    }

    public LinkedList <Estudiante> getEstudiantes() {
        if (estudiantes.isEmpty())
            estudiantes = listall();
        return estudiantes;
    }

    public void setEstudiantes(LinkedList <Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Estudiante getEstudiante() {
        if (estudiante == null)
            estudiante = new Estudiante();
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public Boolean guardar(){
        estudiante.setId(generated_id());
        return save(estudiante);
    }
    
    public Boolean modificar(Integer index){
        return update(estudiante, index);
    }
    
    private void intercambio(Estudiante e[], int i, int j){
        Estudiante aux = e[j];
        e[j] = e[i];
        e[i] = aux;
    }  
    
    public LinkedList <Estudiante> ordenarQuickSort (LinkedList <Estudiante> lista, Integer type, String atribute) throws VacioException{
        Estudiante estudent[] = lista.toArray();
        LinkedList <Estudiante> a = ordenarQuickSort(estudent, type, atribute, 0, lista.getSize()-1);
        return a;
    }
    
    
    public LinkedList <Estudiante> ordenarQuickSort(Estudiante estudiante[], Integer type, String atribute, Integer primero, Integer ultimo) throws VacioException{
        LinkedList <Estudiante> es = new LinkedList <Estudiante>();
            int i, j, central;
            Estudiante pivote;
            central = (primero + ultimo) / 2;                  
            pivote = estudiante[central];   
            i = primero; 
            j = ultimo; 
            do {                                
                while (estudiante[i].comparar(pivote, type, atribute)) {
                    
                    i++;      
                }
                while (estudiante[j].comparar(pivote, type, atribute, 1)) {
                    j--; 
                }
                if (i <= j) {                    
                    intercambio(estudiante, i, j);
                    i++; 
                    j--; 
                }
            } while (i <= j);
            if (primero < j)
            {
                ordenarQuickSort(estudiante, type, atribute, primero, j);
            }

            if (i < ultimo) {
                ordenarQuickSort(estudiante, type, atribute, i, ultimo);
            }
        es = es.toList(estudiante);        
        return es;
    }
    
        public LinkedList <Estudiante> buscarNombres(LinkedList <Estudiante> lista, String text, String nombre) throws Exception {
        LinkedList <Estudiante> lo = this.ordenarQuickSort(lista,0 , text);
        Estudiante[] e = lo.toArray();
        LinkedList <Estudiante> result = new LinkedList <Estudiante>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (e[i].getNombres().equalsIgnoreCase(nombre)) {
                result.add(e[i]);
            }
        }
        return result;
    }
    
    public LinkedList <Estudiante> buscarApellidos(LinkedList <Estudiante> lista, String text, String apellidos) throws Exception {
        LinkedList <Estudiante> lo = this.ordenarQuickSort(lista,0 , text);
        Estudiante[] e = lo.toArray();
        LinkedList <Estudiante> result = new LinkedList <Estudiante>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (e[i].getNombres().equalsIgnoreCase(apellidos)) {
                result.add(e[i]);
            }
        }
        return result;
    }
    
    public LinkedList <Estudiante> buscarTituloBachiller(LinkedList <Estudiante> lista, String text, String titulo) throws Exception {
        LinkedList <Estudiante> lo = this.ordenarQuickSort(lista,0 , text);
        Estudiante[] e = lo.toArray();
        LinkedList <Estudiante> result = new LinkedList <Estudiante>();
        for (int i = 0; i < lo.getSize(); i++) {
            if (e[i].getTitulo_bachiller().equalsIgnoreCase(titulo)) {
                result.add(e[i]);
            }
        }
        return result;
    }
    
    public Estudiante BusquedaID (LinkedList <Estudiante> lista, Integer id, String metodo) throws VacioException{
        lista = ordenarQuickSort(lista, 0, metodo);
        Boolean band = false;
        Estudiante [] estu = lista.toArray();
        Estudiante estudianteHallado = new Estudiante();
        int inicio = 0;
        int fin = estu.length-1;
        while (inicio <= fin && !band) {
            int medio = (inicio + fin)/2;
            if (metodo.equalsIgnoreCase("id")){
                if (estu[medio].getId().intValue() == id.intValue()) {
                    band = true;
                    estudianteHallado = estu[medio];
                } else {
                    if (estu[medio].getId().intValue() > id.intValue()) {
                        fin = medio - 1;
                    } else {
                        inicio = medio + 1;
                    }
                }
            } else {
                System.out.println("No se ha encontrado ese atributo");
            }          
        }
        return estudianteHallado;
    }
    
    public Estudiante BusquedaCedula (LinkedList <Estudiante> lista, String dni, String metodo) throws VacioException{
        lista = ordenarQuickSort(lista, 0, metodo);
        Boolean band = false;
        Estudiante [] estu = lista.toArray();
        Estudiante estudianteHallado = new Estudiante();
        int inicio = 0;
        int fin = estu.length-1;
        while (inicio <= fin && !band) {
            int medio = (inicio + fin)/2;
            if (metodo.equalsIgnoreCase("cedula")){
                if (estu[medio].getCedula().equalsIgnoreCase(dni)) {
                    band = true;
                    estudianteHallado = estu[medio];
                } else {
                    if (estu[medio].getCedula().compareToIgnoreCase(dni) > 0) {
                        fin = medio - 1;
                    } else {
                        inicio = medio + 1;
                    }
                }
            } else {
                System.out.println("No se ha encontrado ese atributo");
            }          
        }
        return estudianteHallado;
    }
}
