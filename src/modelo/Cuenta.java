package modelo;

/**
 *
 * @author alexg
 */
public class Cuenta {
    private Integer id;
    private String correo;
    private String clave;
    private Boolean estado;
    private Integer id_rol;
    private Integer id_persona;

    public Cuenta() {
    }

    public Cuenta(Integer id, String correo, String clave, Boolean estado, Integer id_rol, Integer id_persona) {
        this.id = id;
        this.correo = correo;
        this.clave = clave;
        this.estado = estado;
        this.id_rol = id_rol;
        this.id_persona = id_persona;
    }

    public Boolean comparar(Cuenta c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("correo")) {
                    return  getCorreo().compareTo(c.getCorreo()) > 0;
                }
                else if (field.equalsIgnoreCase("clave")) {
                    return getClave().compareTo(c.getClave()) > 0;
                }
                else if (field.equalsIgnoreCase("estado")) {
                    return getEstado().compareTo(c.getEstado()) > 0;
                }
                else if (field.equalsIgnoreCase("id_rol")) {
                    return getId_rol().intValue() > c.getId_rol().intValue();
                }
                else if (field.equalsIgnoreCase("id_persona")) {
                    return getId_persona().intValue() > c.getId_persona().intValue();
                }
                
            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } 
                else if (field.equalsIgnoreCase("correo")) {
                    return  getCorreo().compareTo(c.getCorreo()) < 0;
                }
                else if (field.equalsIgnoreCase("clave")) {
                    return getClave().compareTo(c.getClave()) < 0;
                }
                else if (field.equalsIgnoreCase("estado")) {
                    return getEstado().compareTo(c.getEstado()) < 0;
                }       
                else if (field.equalsIgnoreCase("id_rol")) {
                    return getId_rol().intValue() < c.getId_rol().intValue();
                }  
                else if (field.equalsIgnoreCase("id_persona")) {
                    return getId_persona().intValue() < c.getId_persona().intValue();
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
    

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the id_rol
     */
    public Integer getId_rol() {
        return id_rol;
    }

    /**
     * @param id_rol the id_rol to set
     */
    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    /**
     * @return the id_persona
     */
    public Integer getId_persona() {
        return id_persona;
    }

    /**
     * @param id_persona the id_persona to set
     */
    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }
     
}
