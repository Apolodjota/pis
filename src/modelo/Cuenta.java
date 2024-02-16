package modelo;

/**
 *
 * @author alexg
 */
public class Cuenta {
    private Integer id;
    private Integer id_persona;
    private String estado;
    private String correo;
    private String clave;

    public Cuenta() {
    }

    public Cuenta(Integer id, Integer id_persona, String estado, String correo, String clave) {
        this.id = id;
        this.id_persona = id_persona;
        this.estado = estado;
        this.correo = correo;
        this.clave = clave;
    }

    public Boolean comparar(Cuenta c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() > c.getId().intValue();
                } else if (field.equalsIgnoreCase("correo")) {
                    return getCorreo().compareTo(c.getCorreo()) > 0;
                } else if (field.equalsIgnoreCase("clave")) {
                    return getClave().compareTo(c.getClave()) > 0;
                } else if (field.equalsIgnoreCase("estado")) {
                    return getEstado().compareTo(c.getEstado()) > 0;
                } else if (field.equalsIgnoreCase("id_persona")) {
                    return getId_persona().intValue() > c.getId_persona().intValue();
                }

            case 0:
                if (field.equalsIgnoreCase("id")) {
                    return getId().intValue() < c.getId().intValue();
                } else if (field.equalsIgnoreCase("correo")) {
                    return getCorreo().compareTo(c.getCorreo()) < 0;
                } else if (field.equalsIgnoreCase("clave")) {
                    return getClave().compareTo(c.getClave()) < 0;
                } else if (field.equalsIgnoreCase("estado")) {
                    return getEstado().compareTo(c.getEstado()) < 0;
                } else if (field.equalsIgnoreCase("id_persona")) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

}
