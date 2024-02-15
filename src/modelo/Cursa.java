package modelo;

/**
 *
 * @author Asus
 */
public class Cursa {
    private Integer id;
    private Integer id_materia;
    private String paralelo;
    private Integer id_docente;
    private Integer id_periodo;
    

    public Cursa() {
    }

    public Cursa(Integer id, Integer id_materia, String paralelo, Integer id_docente, Integer id_periodo) {
        this.id = id;
        this.id_materia = id_materia;
        this.paralelo = paralelo;
        this.id_docente = id_docente;
        this.id_periodo = id_periodo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_materia() {
        return id_materia;
    }

    public void setId_materia(Integer id_materia) {
        this.id_materia = id_materia;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public Integer getId_docente() {
        return id_docente;
    }

    public void setId_docente(Integer id_docente) {
        this.id_docente = id_docente;
    }

    public Integer getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(Integer id_periodo) {
        this.id_periodo = id_periodo;
    }

   public Boolean comparar(Cursa c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() > c.getId().intValue();
                else if (field.equalsIgnoreCase("id_materia"))
                    return getId_materia()> c.getId_materia();
                else if (field.equalsIgnoreCase("paralelo"))
                    return getParalelo().compareToIgnoreCase(c.getParalelo()) > 0;
                else if (field.equalsIgnoreCase("id_docente")) 
                    return getId_docente() > c.getId_docente();
                else if (field.equalsIgnoreCase("id_periodo"))
                    return getId_periodo() > c.getId_periodo();
            case 0:
                if (field.equalsIgnoreCase("id"))
                    return getId().intValue() < c.getId().intValue();
                else if (field.equalsIgnoreCase("id_materia"))
                    return getId_materia() < c.getId_materia();
                else if (field.equalsIgnoreCase("paralelo"))
                    return getParalelo().compareToIgnoreCase(c.getParalelo()) < 0;
                else if (field.equalsIgnoreCase("id_docente")) 
                    return getId_docente() < c.getId_docente();
                else if (field.equalsIgnoreCase("id_periodo"))
                    return getId_periodo() < c.getId_periodo();
            default:
                return null;
        }
   }
   
}
