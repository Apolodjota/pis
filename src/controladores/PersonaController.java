/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controlador.BDD.DAO.AdaptadorDao;
import controlador.TDALista.LinkedList;
import java.util.Date;
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
        return guardar(persona);
    }
    
    public void update() throws Exception {
        modificar(persona);
    }

    public Persona buscar(Integer id){
        return obtener(id);
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
            
    public static void main(String[] args) throws Exception {
        PersonaController pc = new PersonaController();

//        pc.getPersona().setNombres("Apolo Andres");
//        pc.getPersona().setApellidos("Washinton Cueva");
//        pc.getPersona().setCedula("1234567890");
//        pc.getPersona().setDireccionResidencia("Zamora");
//        pc.getPersona().setFechaNacimiento(new Date());
//        pc.getPersona().setGenero("M");
//        pc.getPersona().setId_rol(1);
//        pc.getPersona().setTelefonoCasa("1902725689");
//        pc.getPersona().setTelefonoCelular("1902721234");
//        pc.save();
//
//        pc.getPersona().setNombres("Alexis Gradyn");
//        pc.getPersona().setApellidos("Ludena Cueva");
//        pc.getPersona().setCedula("0645892561");
//        pc.getPersona().setDireccionResidencia("Loja");
//        pc.getPersona().setFechaNacimiento(new Date());
//        pc.getPersona().setGenero("M");
//        pc.getPersona().setId_rol(1);
//        pc.getPersona().setTelefonoCasa("0273319381");
//        pc.getPersona().setTelefonoCelular("0101322441");
//        pc.save();       

        pc.getPersona().setNombres("Gerardo Insano");
        pc.getPersona().setApellidos("Gavilan Potencia");
        pc.getPersona().setCedula("0987654321");
        pc.getPersona().setDireccionResidencia("Loja");
        pc.getPersona().setFechaNacimiento(new Date());
        pc.getPersona().setGenero("M");
        pc.getPersona().setId_rol(1);
        pc.getPersona().setTelefonoCasa("1782243555");
        pc.getPersona().setTelefonoCelular("0923666661");
        pc.save();      

//        cc.getCuenta().setId(2);
//        cc.getCuenta().setCorreo("admin@gmail.com");
//        cc.getCuenta().setClave("2004");
//        cc.getCuenta().setId_persona(2);
////        cc.getCuenta().setId_rol(1);
//        cc.save();        
//        
//        cc.getCuenta().setId(3);
//        cc.getCuenta().setCorreo("ger");
//        cc.getCuenta().setClave("456");
//        cc.getCuenta().setId_persona(3);
////        cc.getCuenta().setId_rol(3);
//        cc.save();         
    }
    
}
