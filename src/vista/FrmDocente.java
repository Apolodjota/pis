package vista;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controladores.DocenteControlador;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Docente;
import vista.Util.Util;
import vista.tablas.DocenteModeloTabla;


/**
 *
 * @author Asus
 */

public class FrmDocente extends javax.swing.JFrame {
    private DocenteControlador dc = new DocenteControlador();
    private DocenteModeloTabla dt = new DocenteModeloTabla(dc.getDocentes());
    private Integer fila = -1;
    private Boolean band = false; 
    /**
     * Creates new form FrmVendedor
     */
    public FrmDocente() {
        initComponents();
        limpiar();
    }
    
    public JPanel getJPanel1() {
        return panelPrincipal;
    }    
    
    private void Ordenar(Integer type, String atribute) throws VacioException{
            dt.setDocentes(dc.ordenarQuickSort(dc.getDocentes(), type, atribute));
            table.setModel(dt);
            table.updateUI();
    }
    
    private void busqueda(String metodo) throws Exception {    
        if (metodo.equalsIgnoreCase("id") || metodo.equalsIgnoreCase("cedula")){
            if (metodo.equalsIgnoreCase("id")){
                Integer id = Integer.parseInt(txtBusqueda.getText().toString());
                dt.setDocentes(Util.retornarDocente(dc.BusquedaID(dc.getDocentes(), id, metodo)));
            } else if (metodo.equalsIgnoreCase("cedula")) { 
                String cedula = txtBusqueda.getText().toString();
                dt.setDocentes(Util.retornarDocente(dc.BusquedaCedula(dc.getDocentes(), cedula, metodo)));
            }        
        } else {
            if (metodo.equalsIgnoreCase("nombres")) {
                String nombres = txtBusqueda.getText().toString();
                dt.setDocentes(dc.buscarNombres(dc.getDocentes(), metodo, nombres));
            } else if (metodo.equalsIgnoreCase("apellidos")) { 
                String apellidos = txtBusqueda.getText().toString();
                dt.setDocentes(dc.buscarApellidos(dc.getDocentes(), metodo, apellidos));
            } else {
                String tituloTN = txtBusqueda.getText().toString();
                dt.setDocentes(dc.buscarTituloTercerN(dc.getDocentes(), metodo, tituloTN));
            }
        }
        table.setModel(dt);
        table.updateUI();
    }
    
    private void limpiar(){
        cargarTabla();
        txtapellidos.setText("");
        txtTituloTercer.setText("");
        txtdni.setText("");
        txtNombres.setText("");
        txtNacionalidad.setText("");
        txtTelefono.setText("");
        txtTituloCuarto.setText("");
        txtAnioE.setText("");
        dc.setDocente(null);
        dc.setDocentes(new LinkedList<Docente>());
        cargarTabla();
        table.clearSelection();
    }
    
    private void cargarTabla(){
        dt.setDocentes(dc.getDocentes());
        table.setModel(dt);
        table.updateUI();
    }
    
    private Boolean validar (){
        return !txtapellidos.getText().trim().isEmpty() &&
                !txtAnioE.getText().trim().isEmpty() &&
                !txtdni.getText().trim().isEmpty() &&
                !txtNombres.getText().trim().isEmpty() &&
                !txtNacionalidad.getText().trim().isEmpty() &&
                !txtTelefono.getText().trim().isEmpty() &&
                !txtTituloCuarto.getText().trim().isEmpty() &&
                !txtTituloTercer.getText().trim().isEmpty();
    }
    
    private void cargarVista() {
        fila = table.getSelectedRow();
        if (fila < 0){
            JOptionPane.showMessageDialog(null, "Seleccione una fila porfavor"
            , "ERROR"
            , JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                dc.setDocente(dt.getDocentes().get(fila));
                txtapellidos.setText(dc.getDocente().getApellidos());
                txtNombres.setText(dc.getDocente().getNombres());
                calendar.setDate(dc.getDocente().getFecha_nac());
                txtNacionalidad.setText(dc.getDocente().getNacionalidad());
                txtdni.setText(dc.getDocente().getCedula());
                txtTelefono.setText(dc.getDocente().getTelefono());
                txtTituloCuarto.setText(dc.getDocente().getTitulo_cuartoNivel());
                txtTituloTercer.setText(dc.getDocente().getTitulo_tercerNivel());
                txtAnioE.setText(String.valueOf(dc.getDocente().getAnios_exp_docente()));
            } catch (Exception e) {
                System.out.println("EEORR" + e.getMessage());
            }
            
        }
            
    }
    
    private void obtenerDocente(){
        dc.getDocente().setNombres(txtNombres.getText().toString());
        dc.getDocente().setApellidos(txtapellidos.getText().toString());
        dc.getDocente().setCedula(txtdni.getText().toString());
        dc.getDocente().setFecha_nac(calendar.getDate());
        dc.getDocente().setTelefono(txtTelefono.getText().toString());
        dc.getDocente().setNacionalidad(txtNacionalidad.getText().toString()); 
        dc.getDocente().setTitulo_cuartoNivel(txtTituloCuarto.getText().toString());
        dc.getDocente().setTitulo_tercerNivel(txtTituloTercer.getText().toString());
        dc.getDocente().setAnios_exp_docente(Integer.parseInt(txtAnioE.getText().toString()));
    }
    
    private void save(){
        if (validar()) {
            try {
                obtenerDocente();
                    if (dc.getDocente().getId() == null){
                        if (dc.guardar()){
                            limpiar();
                            JOptionPane.showMessageDialog(null, "Se ha guardado Correctamente");
                            dc.setDocente(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha podido Guardar");
                        }
                            
                    } else {
                        if (dc.modificar(fila)){
                            limpiar();
                            JOptionPane.showMessageDialog(null, "Se ha modificado correctamente");
                            dc.setDocente(null);
                        } else {
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
        java.awt.GridBagConstraints gridBagConstraints;

        jSeparator1 = new javax.swing.JSeparator();
        panelPrincipal = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtAnioE = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        txtTituloTercer = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtTituloCuarto = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        calendar = new com.toedter.calendar.JCalendar();
        btncancelar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxOrdenarPor = new javax.swing.JComboBox<>();
        cnxBuscarPor = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxtipo = new javax.swing.JComboBox<>();
        txtBusqueda = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btmSeleccionar1 = new javax.swing.JButton();
        btmBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Docentes");

        panelPrincipal.setBackground(new java.awt.Color(0, 153, 102));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1140, 520));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Completar los campos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT", 1, 14))); // NOI18N

        jLabel31.setBackground(new java.awt.Color(204, 204, 255));
        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setText("Cédula:");

        jLabel32.setBackground(new java.awt.Color(204, 204, 255));
        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setText("Nacionalidad:");

        jLabel33.setBackground(new java.awt.Color(204, 204, 255));
        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setText("Nombres:");

        jLabel34.setBackground(new java.awt.Color(204, 204, 255));
        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setText("Apellidos:");

        jLabel35.setBackground(new java.awt.Color(204, 204, 255));
        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setText("FechaNacimiento");

        jLabel36.setBackground(new java.awt.Color(204, 204, 255));
        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setText("Teléfono:");

        jLabel37.setBackground(new java.awt.Color(204, 204, 255));
        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel37.setText("Experiencia:");

        jLabel38.setBackground(new java.awt.Color(204, 204, 255));
        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel38.setText("TítuloCuartoN:");

        jLabel39.setBackground(new java.awt.Color(204, 204, 255));
        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel39.setText("TítuloTercerN:");

        btncancelar.setBackground(new java.awt.Color(0, 153, 102));
        btncancelar.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnguardar.setBackground(new java.awt.Color(0, 153, 102));
        btnguardar.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtTituloTercer, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTituloCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAnioE, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTituloTercer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel39)
                                .addComponent(jLabel31))
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel37)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel32))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtTituloCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(txtAnioE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel34)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel36)
                                .addGap(25, 25, 25)
                                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros Existentes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT", 1, 14))); // NOI18N

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordenar y Buscar"));

        jLabel1.setText("Ordenar Por:");

        jLabel2.setText("Buscar Por:");

        cbxOrdenarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "nombres", "apellidos", "fecha_nac", "anios_exp_docente" }));
        cbxOrdenarPor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxOrdenarPorItemStateChanged(evt);
            }
        });

        cnxBuscarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "nombres", "apellidos", "cedula", "titulo_tercerNivel", "" }));

        jLabel3.setText("Tipo");

        cbxtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        cbxtipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxtipoItemStateChanged(evt);
            }
        });

        jLabel4.setText("Busqueda");

        btmSeleccionar1.setBackground(new java.awt.Color(0, 204, 153));
        btmSeleccionar1.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        btmSeleccionar1.setText("Buscar");
        btmSeleccionar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmSeleccionar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxOrdenarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cnxBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btmSeleccionar1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbxOrdenarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cbxtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cnxBuscarPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btmSeleccionar1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btmBuscar.setBackground(new java.awt.Color(0, 204, 153));
        btmBuscar.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        btmBuscar.setText("Seleccionar");
        btmBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(638, 638, 638)
                .addComponent(btmBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(147, 147, 147)
                .addComponent(btmBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 218, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btmBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmBuscarActionPerformed
        cargarVista();
    }//GEN-LAST:event_btmBuscarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        save();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void cbxOrdenarPorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxOrdenarPorItemStateChanged
        int aux;
        if (cbxtipo.getSelectedItem().toString().equalsIgnoreCase("Ascendente")){
            aux = 0;
        } else {
            aux = 1;
        }
        try {
            Ordenar(aux, cbxOrdenarPor.getSelectedItem().toString());
        } catch (VacioException ex) {   
            System.out.println("Hiustom, we have problems");
        }
    }//GEN-LAST:event_cbxOrdenarPorItemStateChanged

    private void cbxtipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxtipoItemStateChanged
        int aux;
        if (cbxtipo.getSelectedItem().toString().equalsIgnoreCase("Ascendente")){
            aux = 0;
        } else {
            aux = 1;
        }
        try {
            Ordenar(aux, cbxOrdenarPor.getSelectedItem().toString());
        } catch (VacioException ex) {   
            System.out.println("Hiustom, we have problems");
        }
    }//GEN-LAST:event_cbxtipoItemStateChanged

    private void btmSeleccionar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmSeleccionar1ActionPerformed
        try {
            busqueda(cnxBuscarPor.getSelectedItem().toString());
        } catch (Exception ex) {
            System.out.println("we have problems" + ex.getMessage());

        }
    }//GEN-LAST:event_btmSeleccionar1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDocente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btmBuscar;
    private javax.swing.JButton btmSeleccionar1;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private com.toedter.calendar.JCalendar calendar;
    private javax.swing.JComboBox<String> cbxOrdenarPor;
    private javax.swing.JComboBox<String> cbxtipo;
    private javax.swing.JComboBox<String> cnxBuscarPor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtAnioE;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTituloCuarto;
    private javax.swing.JTextField txtTituloTercer;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtdni;
    // End of variables declaration//GEN-END:variables

}
