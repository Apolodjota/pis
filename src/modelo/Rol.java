package modelo;

/**
 *
 * @author alexg
 */
public class Rol {
    private Integer id;
    private String nombre;

    public Rol() {
    }

    public Rol(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre; 
    }
    
    public Boolean comparar(Rol c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("nombre")) {
                    return  getNombre().compareTo(c.getNombre()) > 0;
                }
                
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("nombre")) {
                    return  getNombre().compareTo(c.getNombre()) < 0;
                }
                          
            default:
                return null;
        }
    }        

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
