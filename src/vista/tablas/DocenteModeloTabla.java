/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.tablas;

import controlador.TDALista.LinkedList;
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import modelo.Docente;
import modelo.Estudiante;

/**
 *
 * @author sakotaz
 */
public class DocenteModeloTabla extends AbstractTableModel{
private LinkedList <Docente> lista = new LinkedList <Docente>();
    
    public DocenteModeloTabla (LinkedList <Docente> list){
        this.lista = list;
    }
    
    @Override
    public int getRowCount() {
        return lista.getSize();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Docente d = null;
        try {
            d = lista.get(i);  
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }    
        switch (i1) {
                case 0:
                    return (d != null) ? d.getId() : " ";
                case 1:
                    return (d != null) ? d.getNombres() : " ";
                case 2:
                    return (d != null) ? d.getApellidos(): " ";
                case 3:
                    return (d != null) ? d.getCedula() : " ";
                case 4: 
                    return (d != null) ? new SimpleDateFormat("dd-MM-yyyy").format(d.getFecha_nac()) : " ";
                case 5:
                    return (d != null) ? d.getAnios_exp_docente() : " ";
                case 6:
                    return (d != null) ? d.getTitulo_tercerNivel() : " ";
                default:
                    return null;
        } 
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Nombres";
            case 2: 
                return "Apellidos";
            case 3:
                return "Cedula";
            case 4:
                return "Fecha Nacimiento";
            case 5:
                return "Años Experiencia";
            case 6:
                return "Titulo Tercer Nivel";
            default:
                return " ";
        }
    }

    public LinkedList <Docente> getDocentes() {
        return lista;
    }

    public void setDocentes(LinkedList <Docente> docentes) {
        this.lista = docentes;
    }
    
}
