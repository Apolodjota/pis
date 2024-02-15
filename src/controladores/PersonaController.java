/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.TDALista.LinkedList;
import modelo.PeriodoAcademico;
import modelo.Persona;

/**
 *
 * @author GERARDO
 */
public class PersonaController extends AdaptadorDao<Persona>{
    private Persona persona = new Persona();
    private LinkedList <Persona> lista = new LinkedList<>();
    
    
    public PersonaController() {
        super(Persona.class);
    }
    
    public Integer save() throws Exception{
        return guardar(getPersona());
    }

    public Persona getPersona() {
        if (persona == null)
            persona = new Persona();
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LinkedList <Persona> getLista() {
        if (lista.isEmpty()) {
            lista = listar();
        }
        return lista;
    }

    public void setLista(LinkedList <Persona> lista) {
        this.lista = lista;
    }
            
    
}
