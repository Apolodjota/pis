package controladores;
import controlador.BDD.DAO.AdaptadorDao;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controlador.listas.DAO.DataAccesObject;
import controlador.listas.DAO.TransferObject;
import java.lang.reflect.Field;
import modelo.PeriodoAcademico;

/**
 *
 * @author asus
 */
public class PeriodoController extends AdaptadorDao<PeriodoAcademico>{
    private LinkedList<PeriodoAcademico> periodos = new LinkedList<>();
    private PeriodoAcademico periodo = new PeriodoAcademico();
    private Integer index = -1;
    
    public PeriodoController() {
        super(PeriodoAcademico.class);
    }

    
    public LinkedList<PeriodoAcademico> getPeriodos() {
        if(periodos.isEmpty()){
            periodos = listar();
            //periodos = listall();
        }return periodos;
    }

    public void setPeriodos(LinkedList<PeriodoAcademico> periodos) {
        this.periodos = periodos;
    }

    public PeriodoAcademico getPeriodo() {
        if(periodo == null)
            periodo = new PeriodoAcademico();
        return periodo;
    }

    public void setPeriodo(PeriodoAcademico periodo) {
        this.periodo = periodo;
    }

    public Integer getIndex() {
        return index;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }


    
    public Integer save() throws Exception{
        /*Integer id = generated_id();
        if(id != 1){
            try {
                PeriodoAcademico periodoant = getPeriodos().get(id - 2);
                periodoant.setEstado(false);
                update(periodoant, id-2);
            } catch (Exception e) {
                System.out.println("Error al cambiar estado de periodo anterior: "+e.getMessage());
            }
        }
        this.periodo.setId(id);
        return save(periodo);*/
        return guardar(periodo);
    }
    
    public void update(PeriodoAcademico perio) throws Exception{
        //return update(periodo, index);
        modificar(perio);
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
    public LinkedList<PeriodoAcademico> quickSort (PeriodoAcademico[] arreglo, int inicio , int fin, int orden, String field) throws VacioException {
        int i = inicio; // i siempre avanza en el arreglo hacia la derecha
        int j = fin; // j siempre avanza hacia la izquierda
        PeriodoAcademico pivote = arreglo[(inicio + fin)/2] ;
        do{
            while(arreglo[i].comparar(pivote, field, orden))//si ya esta ordenado incrementa i
                i++;
            while(pivote.comparar(arreglo[j], field, orden))//si ya esta ordenado decrementa j
                j--;
            if(i <= j){// Hace el intercambio
                PeriodoAcademico aux = arreglo[i];
                arreglo[i] = arreglo[j] ;
                arreglo[j] = aux ;
                i++;
                j--;
            }
            }while(i <= j);
            if(inicio < j)
                quickSort(arreglo,inicio,j, orden, field);// invocación recursiva
            if(i < fin)
                quickSort(arreglo, i , fin, orden, field);// invocacion recursiva
            return new LinkedList<PeriodoAcademico>().toList(arreglo);
    }
    
    //Busqueda Lineal
    
    public LinkedList<PeriodoAcademico> buscarVarios(String field, Object valor, Integer orden) throws VacioException{
        PeriodoAcademico[] arreglo = getPeriodos().toArray();
        PeriodoAcademico[] arrayOrdenado = this.quickSort(arreglo, 0, (arreglo.length - 1), 0, field).toArray();
        LinkedList<PeriodoAcademico> result = new LinkedList<>();
        for(int i = 0; i < arrayOrdenado.length ; i++){
            int buscado = arrayOrdenado[i].esSimilar(field, valor);
            if(buscado == 0 && orden == 0)
                result.add(arrayOrdenado[i]);
            else if(buscado < 0 && orden == 1)
                result.add(arrayOrdenado[i]);
            else if(buscado > 0 && orden == 2)
                result.add(arrayOrdenado[i]);
        }
        return result;
    }

}
