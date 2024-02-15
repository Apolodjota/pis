
package vista.listas.util;

import controladores.AdministradorControlador;
import controladores.CursoControllerListas;
import controladores.MallaControllerListas;
import controladores.RolControllerListas;
import controlador.TDALista.exceptions.VacioException;
import controladores.DocenteControlador;
import controladores.EstudianteControlador;
import controladores.MateriaControllerListas;
import controladores.PeriodoController;
import javax.swing.JComboBox;
import modelo.Administrador;
import modelo.Curso;
import modelo.Docente;
import modelo.Estudiante;
import modelo.Malla;
import modelo.Materia;
import modelo.PeriodoAcademico;
import modelo.Rol;

/**
 *
 * @author alexg
 */
public class UtilVista {
    
    public static void cargarMalla(JComboBox cbxMalla) throws VacioException {
        MallaControllerListas ac = new MallaControllerListas();
        cbxMalla.removeAllItems();
        for (int i = 0; i < ac.getMallas().getSize(); i++) {
                cbxMalla.addItem(ac.getMallas().get(i));
        }
    }  
    
    public static Malla getComboMallas(JComboBox cbx) {
        return (Malla)cbx.getSelectedItem();
    }    
     
    public static void cargarCurso(JComboBox cbxCurso) throws VacioException {
        CursoControllerListas ac = new CursoControllerListas();
        cbxCurso.removeAllItems();
        for (int i = 0; i < ac.getCursos().getSize(); i++) {
            cbxCurso.addItem(ac.getCursos().get(i));
        }
    }  
    
    public static Curso getComboCursos(JComboBox cbx) {
        return (Curso)cbx.getSelectedItem();
    }        
    
    public static void cargarRol(JComboBox cbxRol) throws VacioException {
        RolControllerListas rc = new RolControllerListas();
        cbxRol.removeAllItems();
        for (int i = 0; i < rc.getRoles().getSize(); i++) {
            cbxRol.addItem(rc.getRoles().get(i));
        }
    }  
    
    public static Rol getComboRoles(JComboBox cbx) {
        return (Rol)cbx.getSelectedItem();
    }      

    public static void cargarEstudiante(JComboBox cbxEstudiante) throws VacioException {
        EstudianteControlador ec = new EstudianteControlador();
        cbxEstudiante.removeAllItems();
        for (int i = 0; i < ec.getEstudiantes().getSize(); i++) {
            cbxEstudiante.addItem(ec.getEstudiantes().get(i));
        }
    }  
    
    public static Estudiante getComboEstudiantes(JComboBox cbx) {
        return (Estudiante)cbx.getSelectedItem();
    }  
    
    public static void cargarDocente(JComboBox cbxDocente) throws VacioException {
        DocenteControlador dc = new DocenteControlador();
        cbxDocente.removeAllItems();
        for (int i = 0; i < dc.getDocentes().getSize(); i++) {
            cbxDocente.addItem(dc.getDocentes().get(i));
        }
    }  
    
    public static Docente getComboDocentes(JComboBox cbx) {
        return (Docente)cbx.getSelectedItem();
    }     
    
    public static void cargarAdministrador(JComboBox cbxAdministrador) throws VacioException {
        AdministradorControlador ac = new AdministradorControlador();
        cbxAdministrador.removeAllItems();
        for (int i = 0; i < ac.getAdministradores().getSize(); i++) {
            cbxAdministrador.addItem(ac.getAdministradores().get(i));
        }
    }  
    
    public static Administrador getAdministradores(JComboBox cbx) {
        return (Administrador)cbx.getSelectedItem();
    }    
    public static void cargarPeriodoAcademico (JComboBox cbxPeriodoAcademico) throws VacioException {
        PeriodoController pc = new PeriodoController();
        cbxPeriodoAcademico.removeAllItems();
        for (int i = 0; i < pc.getPeriodos().getSize(); i++) {
            cbxPeriodoAcademico.addItem(pc.getPeriodos().get(i));
        }
    }  
    
    public static PeriodoAcademico getComboPeriodoAcademico(JComboBox cbx) {
        return (PeriodoAcademico)cbx.getSelectedItem();
    } 
    
    public static void cargarMateria (JComboBox cbxMateria) throws VacioException {
        MateriaControllerListas pc = new MateriaControllerListas();
        cbxMateria.removeAllItems();
        for (int i = 0; i < pc.getMaterias().getSize(); i++) {
            cbxMateria.addItem(pc.getMaterias().get(i));
        }
    }  
    
    public static Materia getComboMateria(JComboBox cbx) {
        return (Materia)cbx.getSelectedItem();
    } 
    
}
