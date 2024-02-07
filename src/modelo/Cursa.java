package modelo;

/**
 *
 * @author Asus
 */
public class Cursa {
    private Integer id;
    private Integer id_matricula;
    private Integer id_materia;
    private Integer id_docente;
    

    public Cursa() {
    }

    public Cursa(Integer id, Integer id_matricula, Integer id_materia, Integer id_docente) {
        this.id = id;
        this.id_matricula = id_matricula;
        this.id_materia = id_materia;
        this.id_docente = id_docente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_docente() {
        return id_docente;
    }

    public void setId_docente(Integer id_docente) {
        this.id_docente = id_docente;
    }
    
    public Integer getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(Integer id_matricula) {
        this.id_matricula = id_matricula;
    }

    public Integer getId_materia() {
        return id_materia;
    }

    public void setId_materia(Integer id_materia) {
        this.id_materia = id_materia;
    }
   public Boolean comparar(Cursa c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > c.getId().intValue();
                } else if (field.equalsIgnoreCase("id_docente")) {
                    return getId_docente() > c.getId_docente();
                } else if (field.equalsIgnoreCase("id_materia")) {
                    return getId_materia()> c.getId_materia();
                } else if (field.equalsIgnoreCase("id_matricula")) {
                    return getId_matricula() > c.getId_matricula();
                }
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } else if (field.equalsIgnoreCase("id_docente")) {
                    return getId_docente() < c.getId_docente();
                } else if (field.equalsIgnoreCase("id_materia")) {
                    return getId_materia() < c.getId_materia();
                } else if (field.equalsIgnoreCase("id_matricula")) {
                    return getId_matricula() < c.getId_matricula();
                }
            default:
                return null;
        }
   }
   
}
