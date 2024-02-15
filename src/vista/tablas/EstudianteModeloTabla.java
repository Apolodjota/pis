/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.tablas;

import controlador.TDALista.LinkedList;
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import modelo.Estudiante;

/**
 *
 * @author sakotaz
 */
public class EstudianteModeloTabla extends AbstractTableModel{
    private LinkedList <Estudiante> lista = new LinkedList <Estudiante>();
    
    public EstudianteModeloTabla (LinkedList <Estudiante> list){
        this.lista = list;
    }
    
    @Override
    public int getRowCount() {
        return lista.getSize();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Estudiante e = null;
        String estado = "";
        try {
            e = lista.get(i);
            estado = (e.getTrabaja().equalsIgnoreCase("T")) ? "SÍ" : "NO";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }    
        switch (i1) {
            case 0:
                return (e != null) ? e.getCedula() : " ";
            case 1:
                return (e != null) ? e.getNombres() : " ";
            case 2:
                return (e != null) ? e.getApellidos() : " ";
            case 3:
                return (e != null) ? e.getGenero() : " ";
            case 4:
                return (e != null) ? new SimpleDateFormat("dd-MM-yyyy").format(e.getFechaNacimiento()) : "";
            case 5:
                return (e != null) ? e.getTelefonoCasa() : " ";
            case 6:
                return (e != null) ? e.getTelefonoCelular() : " ";
            case 7:
                return (e != null) ? e.getDireccionResidencia() : " ";
            case 8:
                return (e != null) ? e.getTitulo_bachiller() : " ";
            case 9:
                return (e != null) ? estado: "";
            default:
                return null;
        } 
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Cedula";
            case 1:
                return "Nombres";
            case 2: 
                return "Apellidos";
            case 3:
                return "Genero";
            case 4:
                return "Fecha de Nacimiento";
            case 5:
                return "Telefono Domicilio";
            case 6:
                return "Celular";
            case 7:
                return "Direccion";
            case 8:
                return "Titulo de bachiller";
            case 9:
                return "Trabaja";
            default:
                return " ";
        }
    }

    public LinkedList <Estudiante> getEstudiantes() {
        return lista;
    }

    public void setEstudiantes(LinkedList <Estudiante> estudiantes) {
        this.lista = estudiantes;
    }
    
}
