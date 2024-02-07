package vista.listas.tablas;
import controlador.TDALista.LinkedList;
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import modelo.PeriodoAcademico;

/**
 *
 * @author alexg
 */
public class ModeloTablaPeriodo extends AbstractTableModel{
    private LinkedList<PeriodoAcademico> periodos = new LinkedList<>();
    
    @Override
    public int getRowCount() {
        return getPeriodos().getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            PeriodoAcademico periodo = getPeriodos().get(rowIndex);
            String estado = (periodo.getEstado() == true) ? "En Vigencia" : "Finalizado";
            switch (columnIndex) {
                case 0:
                    return (periodo != null) ? periodo.getId() : "";
                case 1:
                    return (periodo != null) ? periodo.getNombre() : "";   
                case 2:
                    return (periodo != null) ? new SimpleDateFormat().format(periodo.getFechaDesde()): "";   
                case 3:
                    return (periodo != null) ? new SimpleDateFormat().format(periodo.getFechaHasta()): ""; 
                case 4:
                    return (periodo != null) ? estado : "";
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nro";
            case 1:
                return "Nombre";  
            case 2:
                return "Fecha de Inicio";  
            case 3:
                return "Fecha de Fin";
            case 4:
                return "Estado";
            default:
                return null;
        }
    }

    public LinkedList<PeriodoAcademico> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(LinkedList<PeriodoAcademico> periodos) {
        this.periodos = periodos;
    }
    
    
}

