package controlador.BDD.DAO;

import controlador.TDALista.LinkedList;

/**
 *
 * @author Asus
 */
public interface InterfazDao<T> {
    /**
     * Metodo que permite realizar el guardaddo
     * @param obj Objeto del modelo
     * @return El id generado producto del guardado
     */
    public void guardarEyD(T obj) throws Exception;
    /**
     * Permite modificar los datos en un repositorio de datos
     * @param obj Objeto a modificar     
     */
    public void modificar(T obj) throws Exception;
    /**
     * LIstado de objetos en la BD
     * @return Una ListaEnlazada
     */
    public LinkedList<T> listar();
    public T obtener(Integer id);
}
