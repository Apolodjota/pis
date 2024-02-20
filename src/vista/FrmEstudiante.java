package vista;
import controladores.EstudianteControlador;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controladores.CuentaControllerListas;
import controladores.PersonaController;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Estudiante;
import vista.Util.Util;
import vista.tablas.EstudianteModeloTabla;

/**
 *
 * @author Asus
 */
public class FrmEstudiante extends javax.swing.JFrame {
    private CuentaControllerListas cc = new CuentaControllerListas();
    private PersonaController pc = new PersonaController();
    private EstudianteControlador ec = new EstudianteControlador();
    private EstudianteModeloTabla et = new EstudianteModeloTabla(ec.getEstudiantes());
    private Integer fila = -1;
    private Boolean band = false; 
    private Util u;
    
    /**
     * Creates new form FrmVendedor
     */
    public FrmEstudiante() {
        initComponents();
        limpiar();
    }
    
    public JPanel getJPanel1() {
        return panelPrincipal;
    }    
    
    private void Ordenar(Integer type, String atribute) throws VacioException{
            et.setEstudiantes(ec.ordenarQuickSort(ec.getEstudiantes(), type, atribute));
            table.setModel(et);
            table.updateUI();
    }
    
    private void limpiar(){
        try {
            u = new Util();
            cargarTabla();
            txtApellido.setText("");
            txtDireccionResidencia.setText("");
            txtCelular.setText("");
            txtdni.setText("");
            txtnombres.setText("");
            txtTelefono.setText("");
            cbxBusqueda.setVisible(false);
            ec.setEstudiante(null);
            ec.setEstudiantes(new LinkedList<Estudiante>());
            table.clearSelection();
        } catch (Exception e) {
        }
        
    }
    
    private void cargarTabla(){
        et.setEstudiantes(ec.getEstudiantes());
        table.setModel(et);
        table.updateUI();
    }
    
    private void busqueda(String metodo) throws Exception {    
        if (metodo.equalsIgnoreCase("id") || metodo.equalsIgnoreCase("cedula")){
            if (metodo.equalsIgnoreCase("id")){
                Integer id = Integer.parseInt(txtBusqueda.getText().toString());
                et.setEstudiantes(Util.retornar(ec.BusquedaID(ec.getEstudiantes(), id, metodo)));
            } else if (metodo.equalsIgnoreCase("cedula")) { 
                String cedula = txtBusqueda.getText().toString();
                et.setEstudiantes(Util.retornar(ec.BusquedaCedula(ec.getEstudiantes(), cedula, metodo)));
            }        
        } else {
            if (metodo.equalsIgnoreCase("nombres")) {
                String nombres = txtBusqueda.getText().toString();
                et.setEstudiantes(ec.buscarNombres(ec.getEstudiantes(), metodo, nombres));
            } else if (metodo.equalsIgnoreCase("apellidos")) { 
                String apellidos = txtBusqueda.getText().toString();
                et.setEstudiantes(ec.buscarApellidos(ec.getEstudiantes(), metodo, apellidos));
            } else {
                String titulo = cbxBusqueda.getSelectedItem().toString();
                et.setEstudiantes(ec.buscarTituloBachiller(ec.getEstudiantes(), metodo, titulo));
            }
        }
        table.setModel(et);
        table.updateUI();
    }

    private Boolean validar (){
        if (!validarInfo()) {
            return false;
        } else {
            return !txtApellido.getText().trim().isEmpty() &&
                !txtDireccionResidencia.getText().trim().isEmpty() &&
                !txtdni.getText().trim().isEmpty() &&
                !txtnombres.getText().trim().isEmpty() &&
                !txtCelular.getText().trim().isEmpty() &&
                !txtTelefono.getText().trim().isEmpty();
        }
    }
    
        private Boolean validarInfo(){
        return !txtapellidos.getText().matches(".*\\d+.*") &&
                !txtnombres.getText().matches(".*\\d+.*") &&
                !txtCelular.getText().matches(".*[a-zA-Z\\\\W]+.*") && 
                !txtTelefono.getText().matches(".*[a-zA-Z\\W]+.*") &&
                !txtdni.getText().matches(".*[a-zA-Z\\\\W]+.*");
    }
    
    private void cargarVista() {
        fila = table.getSelectedRow();
        if (fila < 0){
            JOptionPane.showMessageDialog(null, "Seleccione una fila porfavor"
            , "ERROR"
            , JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ec.setEstudiante(et.getEstudiantes().get(fila));
                txtApellido.setText(ec.getEstudiante().getApellidos());
                txtnombres.setText(ec.getEstudiante().getNombres());
                dateNacimiento.setDate(ec.getEstudiante().getFechaNacimiento());
                //txtEdad.setText(String.valueOf(ec.getEstudiante().getEdad()));
                txtCelular.setText(ec.getEstudiante().getTelefonoCelular());
                txtdni.setText(ec.getEstudiante().getCedula());
                txtTelefono.setText(ec.getEstudiante().getTelefonoCasa());
                cbxNachiller.setSelectedItem(ec.getEstudiante().getTitulo_bachiller());
                cbxGenero.setSelectedItem((ec.getEstudiante().getGenero().equalsIgnoreCase("O")) ? "Otro" : (ec.getEstudiante().getGenero().equalsIgnoreCase("F")) ? "Femenino" : "Masculino");
                cbxTrabaja.setSelectedItem((ec.getEstudiante().getTrabaja().equalsIgnoreCase("T") ? "Si" : "No"));
                txtDireccionResidencia.setText(ec.getEstudiante().getDireccionResidencia());
            } catch (Exception e) {
                System.out.println("EEORR" + e.getMessage());
            }
            
        }
            
    }
    
    private void registrarCuenta(Integer id){
        String nombre = "", apellido = "";
        if (ec.getEstudiante().getNombres().trim().contains(" ")){
            Integer index = ec.getEstudiante().getNombres().indexOf(" ");
            if (index > -1) {
                nombre = ec.getEstudiante().getNombres().substring(0, index);
            }
        } else {
            nombre = ec.getEstudiante().getNombres().trim();
        } 
        if (ec.getEstudiante().getApellidos().trim().contains(" ")){
            Integer index = ec.getEstudiante().getApellidos().indexOf(" ");
            if (index > -1) {
                apellido = ec.getEstudiante().getApellidos().substring(0, index);
            }
        } else {
            apellido = ec.getEstudiante().getApellidos().trim();
        }
        try {
            cc.getCuenta().setId_persona(id);
            cc.getCuenta().setEstado("T");
            cc.getCuenta().setCorreo(nombre.toLowerCase()+apellido.toLowerCase()+"@unl.com.ec");
            cc.getCuenta().setClave(u.cifrarMensaje(ec.getEstudiante().getCedula().trim()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception "+ e.getMessage());
        }
       
    }
    
    private void obtenerPersona()throws Exception{
        pc.getPersona().setNombres(txtnombres.getText().toString());
        pc.getPersona().setApellidos(txtApellido.getText().toString());
        pc.getPersona().setCedula(txtdni.getText().toString());
        pc.getPersona().setFechaNacimiento(dateNacimiento.getDate());
        pc.getPersona().setTelefonoCelular(txtCelular.getText().toString());
        pc.getPersona().setId_rol(3);
        pc.getPersona().setTelefonoCasa(txtTelefono.getText().toString());
        pc.getPersona().setGenero((cbxGenero.getSelectedItem().toString().equalsIgnoreCase("Otro")) ? "O" : (cbxGenero.getSelectedItem().toString().equalsIgnoreCase("Femenino") ? "F" : "M"));
        pc.getPersona().setDireccionResidencia(txtDireccionResidencia.getText().toString());
    }
    
    private void obtenerEstudiante() throws Exception {
        ec.getEstudiante().setNombres(txtnombres.getText().toString());
        ec.getEstudiante().setApellidos(txtApellido.getText().toString());
        ec.getEstudiante().setCedula(txtdni.getText().toString());
        pc.getPersona().setFechaNacimiento(dateNacimiento.getDate());
        ec.getEstudiante().setTelefonoCelular(txtCelular.getText().toString());
        //ec.getEstudiante().setEdad(Integer.parseInt(txtEdad.getText().toString()));
        ec.getEstudiante().setTelefonoCasa(txtTelefono.getText().toString());
        ec.getEstudiante().setTitulo_bachiller(cbxNachiller.getSelectedItem().toString());
        ec.getEstudiante().setGenero(cbxGenero.getSelectedItem().toString());
        ec.getEstudiante().setTrabaja(cbxTrabaja.getSelectedItem().toString().equalsIgnoreCase("Si") ? "T" : "F");
        ec.getEstudiante().setDireccionResidencia(txtDireccionResidencia.getText().toString());
    }

    private void save() {
        if (validar()) {
            try {
                obtenerPersona();
                obtenerEstudiante();
                    if (ec.getEstudiante().getId() == null){
                        try {
                            Integer idE = pc.save();
                            ec.getEstudiante().setId(idE);
                            registrarCuenta(idE);
                            ec.guardar();                            
                            cc.save();
                            limpiar();
                            JOptionPane.showMessageDialog(null, "Se ha guardado Correctamente");
                            ec.setEstudiante(null); 
                            pc.setPersona(null);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage() + "No se ha podido Guardar");
                        }         
                    } else {
                        try {
                            ec.update();
                            pc.getPersona().setId(ec.getEstudiante().getId());
                            pc.update();
                            limpiar();
                            JOptionPane.showMessageDialog(null, "Se ha modificado correctamente");
                            ec.setEstudiante(null);
                            pc.setPersona(null);
                        } catch (Exception e) {
                             JOptionPane.showMessageDialog(null, "Error no se pudo mofidicar",
                                    "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }                         
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese todos los campos Porfavor",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        } 
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtDireccionResidencia = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtnombres = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbxNachiller = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        cbxGenero = new javax.swing.JComboBox<>();
        txtCelular = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbxTrabaja = new javax.swing.JComboBox<>();
        dateNacimiento = new com.toedter.calendar.JDateChooser();
        buttonGuardar1 = new org.edisoncor.gui.button.ButtonAero();
        buttonCancelar = new org.edisoncor.gui.button.ButtonAero();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxOrdenar = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbxBusquedapor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        cbxBusqueda = new javax.swing.JComboBox<>();
        buttonBuscar = new org.edisoncor.gui.button.ButtonAero();
        buttonGuardar3 = new org.edisoncor.gui.button.ButtonAero();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Estudiantes");

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1140, 642));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Completar los campos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT", 1, 14))); // NOI18N

        jLabel31.setBackground(new java.awt.Color(204, 204, 255));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("Cédula:");

        jLabel33.setBackground(new java.awt.Color(204, 204, 255));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setText("Nombres:");

        jLabel34.setBackground(new java.awt.Color(204, 204, 255));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setText("Apellidos:");

        jLabel35.setBackground(new java.awt.Color(204, 204, 255));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setText("Fecha de Nacimiento:");

        jLabel36.setBackground(new java.awt.Color(204, 204, 255));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setText("Celular:");

        jLabel38.setBackground(new java.awt.Color(204, 204, 255));
        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setText("Direccion Residencia:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Trabaja:");

        cbxNachiller.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bachillerato G.Unificado", "Bachillerato en Ciencias", "Bachillerato Tecnico", "Bachillerato Modificado" }));

        jLabel37.setBackground(new java.awt.Color(204, 204, 255));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setText("Genero ");

        jLabel39.setBackground(new java.awt.Color(204, 204, 255));
        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setText("Teléfono Casa:");

        cbxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Femenino", "Masculino", "Otro" }));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Titulo Bachiller:");

        cbxTrabaja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));

        buttonGuardar1.setBackground(new java.awt.Color(0, 204, 204));
        buttonGuardar1.setText("Guardar");
        buttonGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardar1ActionPerformed(evt);
            }
        });

        buttonCancelar.setBackground(new java.awt.Color(0, 204, 204));
        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(dateNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxTrabaja, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxNachiller, 0, 171, Short.MAX_VALUE)))
                            .addComponent(txtnombres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDireccionResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(cbxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37)
                            .addComponent(cbxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel34)
                                .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33))
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(dateNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(txtDireccionResidencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel38)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxNachiller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxTrabaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Registros Existentes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT", 1, 14))); // NOI18N
        jPanel7.setPreferredSize(new java.awt.Dimension(1122, 350));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Ordenacion y Busqueda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Ordenar Por:");

        cbxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "nombres", "apellidos", "fechaNacimiento" }));
        cbxOrdenar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxOrdenarItemStateChanged(evt);
            }
        });

        jLabel3.setText("tipo");

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ascendente", "descendente" }));
        cbxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoActionPerformed(evt);
            }
        });

        jLabel4.setText("Buscar Por:");

        cbxBusquedapor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "nombres", "apellidos", "cedula", "tituloBachiller" }));
        cbxBusquedapor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBusquedaporItemStateChanged(evt);
            }
        });

        jLabel5.setText("Buscar Por:");

        cbxBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bachillerato G.Unificado", "Bachillerato en Ciencias", "Bachillerato Tecnico", "Bachillerato Modificado" }));

        buttonBuscar.setBackground(new java.awt.Color(0, 204, 204));
        buttonBuscar.setText("Buscar");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxBusquedapor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cbxOrdenar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxBusquedapor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbxBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        buttonGuardar3.setBackground(new java.awt.Color(0, 204, 204));
        buttonGuardar3.setText("Seleccionar");
        buttonGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonGuardar3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonGuardar3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1128, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxOrdenarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxOrdenarItemStateChanged
        try {
            int aux;
            if (cbxTipo.getSelectedItem().toString().equalsIgnoreCase("descendente"))
                aux = 1;
            else
                aux = 0;
            Ordenar(aux, cbxOrdenar.getSelectedItem().toString());
        } catch (VacioException ex) {
            System.out.println("Have problems " + ex.getMessage()); 
        }
    }//GEN-LAST:event_cbxOrdenarItemStateChanged

    private void cbxBusquedaporItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBusquedaporItemStateChanged
        if (cbxBusquedapor.getSelectedItem().toString().equalsIgnoreCase("tituloBachiller") == true){
            cbxBusqueda.setVisible(true);
            txtBusqueda.setVisible(false);
        } else {
        }
            
    }//GEN-LAST:event_cbxBusquedaporItemStateChanged

    private void cbxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoActionPerformed
        try {
            int aux;
            if (cbxTipo.getSelectedItem().toString().equalsIgnoreCase("descendente"))
                aux = 1;
            else
                aux = 0;
            Ordenar(aux, cbxOrdenar.getSelectedItem().toString());
        } catch (VacioException ex) {
            System.out.println("Have problems " + ex.getMessage()); 
        }
    }//GEN-LAST:event_cbxTipoActionPerformed

    private void buttonGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardar1ActionPerformed
        save();
    }//GEN-LAST:event_buttonGuardar1ActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardar3ActionPerformed
        cargarVista();
//        Persona e = new Estudiante();
//        Estudiante et = new Estudiante();
//        for (Field f : Persona.class.getDeclaredFields()) {
//                System.out.println("Persona: "+f.getName());
//            }
//        for (Field f : e.getClass().getDeclaredFields()) {
//                System.out.println("Estudiante: "+f.getName());
//            }
        
    }//GEN-LAST:event_buttonGuardar3ActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        try {
            busqueda(cbxBusquedapor.getSelectedItem().toString());
        } catch (Exception ex) {
            System.out.println("Have problems in search" + ex.getMessage()); 
        }
    }//GEN-LAST:event_buttonBuscarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEstudiante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAero buttonBuscar;
    private org.edisoncor.gui.button.ButtonAero buttonCancelar;
    private org.edisoncor.gui.button.ButtonAero buttonGuardar1;
    private org.edisoncor.gui.button.ButtonAero buttonGuardar3;
    private javax.swing.JComboBox<String> cbxBusqueda;
    private javax.swing.JComboBox<String> cbxBusquedapor;
    private javax.swing.JComboBox<String> cbxGenero;
    private javax.swing.JComboBox<String> cbxNachiller;
    private javax.swing.JComboBox<String> cbxOrdenar;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JComboBox<String> cbxTrabaja;
    private com.toedter.calendar.JDateChooser dateNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDireccionResidencia;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtnombres;
    // End of variables declaration//GEN-END:variables
}
