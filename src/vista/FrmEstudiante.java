package vista;
import controladores.EstudianteControlador;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
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

    private EstudianteControlador ec = new EstudianteControlador();
    private EstudianteModeloTabla et = new EstudianteModeloTabla(ec.getEstudiantes());
    private Integer fila = -1;
    private Boolean band = false; 
    
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
            et.setAutos(ec.ordenarQuickSort(ec.getEstudiantes(), type, atribute));
            table.setModel(et);
            table.updateUI();
    }
    
    private void limpiar(){
        cargarTabla();
        txtapellidos.setText("");
        txtProcedencia.setText("");
        txtdni.setText("");
        txtnombres.setText("");
        txtNacionalidad.setText("");
        txttelefono.setText("");
        cbxBusqueda.setVisible(false);
        ec.setEstudiante(null);
        ec.setEstudiantes(new LinkedList<Estudiante>());
        cargarTabla();
        table.clearSelection();
    }
    
    private void cargarTabla(){
        et.setAutos(ec.getEstudiantes());
        table.setModel(et);
        table.updateUI();
    }
    
    private void busqueda(String metodo) throws Exception {    
        if (metodo.equalsIgnoreCase("id") || metodo.equalsIgnoreCase("cedula")){
            if (metodo.equalsIgnoreCase("id")){
                Integer id = Integer.parseInt(txtBusqueda.getText().toString());
                et.setAutos(Util.retornar(ec.BusquedaID(ec.getEstudiantes(), id, metodo)));
            } else if (metodo.equalsIgnoreCase("cedula")) { 
                String cedula = txtBusqueda.getText().toString();
                et.setAutos(Util.retornar(ec.BusquedaCedula(ec.getEstudiantes(), cedula, metodo)));
            }        
        } else {
            if (metodo.equalsIgnoreCase("nombres")) {
                String nombres = txtBusqueda.getText().toString();
                et.setAutos(ec.buscarNombres(ec.getEstudiantes(), metodo, nombres));
            } else if (metodo.equalsIgnoreCase("apellidos")) { 
                String apellidos = txtBusqueda.getText().toString();
                et.setAutos(ec.buscarApellidos(ec.getEstudiantes(), metodo, apellidos));
            } else {
                String titulo = cbxBusqueda.getSelectedItem().toString();
                et.setAutos(ec.buscarTituloBachiller(ec.getEstudiantes(), metodo, titulo));
            }
        }
        table.setModel(et);
        table.updateUI();
    }

    private Boolean validar (){
        return !txtapellidos.getText().trim().isEmpty() &&
                !txtProcedencia.getText().trim().isEmpty() &&
                !txtdni.getText().trim().isEmpty() &&
                !txtnombres.getText().trim().isEmpty() &&
                !txtNacionalidad.getText().trim().isEmpty() &&
                !txttelefono.getText().trim().isEmpty();
    }
    
    private void cargarVista() {
        fila = table.getSelectedRow();
        if (fila < 0){
            JOptionPane.showMessageDialog(null, "Seleccione una fila porfavor"
            , "ERROR"
            , JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ec.setEstudiante(et.getAutos().get(fila));
                txtapellidos.setText(ec.getEstudiante().getApellidos());
                txtnombres.setText(ec.getEstudiante().getNombres());
                Calendar.setDate(ec.getEstudiante().getFecha_nac());
                //txtEdad.setText(String.valueOf(ec.getEstudiante().getEdad()));
                txtNacionalidad.setText(ec.getEstudiante().getNacionalidad());
                txtdni.setText(ec.getEstudiante().getCedula());
                txttelefono.setText(ec.getEstudiante().getTelefono());
                cbxNachiller.setSelectedItem(ec.getEstudiante().getTitulo_bachiller());
                txtProcedencia.setText(ec.getEstudiante().getCiudad_Procedencia());
            } catch (Exception e) {
                System.out.println("EEORR" + e.getMessage());
            }
            
        }
            
    }
    
    private void obtenerEstudiante(){
        ec.getEstudiante().setNombres(txtnombres.getText().toString());
        ec.getEstudiante().setApellidos(txtapellidos.getText().toString());
        ec.getEstudiante().setCedula(txtdni.getText().toString());
        ec.getEstudiante().setFecha_nac(Calendar.getDate());
        //ec.getEstudiante().setEdad(Integer.parseInt(txtEdad.getText().toString()));
        ec.getEstudiante().setTelefono(txttelefono.getText().toString());
        ec.getEstudiante().setNacionalidad(txtNacionalidad.getText().toString()); 
        ec.getEstudiante().setTitulo_bachiller(cbxNachiller.getSelectedItem().toString());
        ec.getEstudiante().setCiudad_Procedencia(txtProcedencia.getText().toString());
    }

    private void save(){
        if (validar()) {
            try {
                obtenerEstudiante();
                    if (ec.getEstudiante().getId() == null){
                        System.out.println("entra42");
                        if (ec.guardar()){
                            
                            limpiar();
                            JOptionPane.showMessageDialog(null, "Se ha guardado Correctamente");
                            ec.setEstudiante(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha podido Guardar");
                        }
                            
                    } else {
                        if (ec.modificar(fila)){
                            limpiar();
                            JOptionPane.showMessageDialog(null, "Se ha modificado correctamente");
                            ec.setEstudiante(null);
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

        panelPrincipal = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        txtNacionalidad = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtProcedencia = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        btnguardar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        txtnombres = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        Calendar = new com.toedter.calendar.JCalendar();
        jLabel1 = new javax.swing.JLabel();
        cbxNachiller = new javax.swing.JComboBox<>();
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
        btmBusqueda = new javax.swing.JButton();
        btmSeleccionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Estudiantes");

        panelPrincipal.setBackground(new java.awt.Color(0, 102, 102));
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
        jLabel35.setText("Fecha Nac...");

        jLabel36.setBackground(new java.awt.Color(204, 204, 255));
        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setText("Teléfono:");

        btnguardar.setBackground(new java.awt.Color(0, 153, 102));
        btnguardar.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btncancelar.setBackground(new java.awt.Color(0, 153, 102));
        btncancelar.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        jLabel38.setBackground(new java.awt.Color(204, 204, 255));
        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel38.setText("Procedencia");

        jLabel1.setFont(new java.awt.Font("Nimbus Sans", 1, 15)); // NOI18N
        jLabel1.setText("TituloBachiller");

        cbxNachiller.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bachillerato G.Unificado", "Bachillerato en Ciencias", "Bachillerato Tecnico", "Bachillerato Modificado" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(Calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtProcedencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                    .addComponent(txtnombres, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxNachiller, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtapellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                .addComponent(txtNacionalidad)))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel34)
                                .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtProcedencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel38))
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxNachiller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnguardar)
                            .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Calendar, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros Existentes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT", 1, 14))); // NOI18N
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordenacion y Busqueda"));

        jLabel2.setText("Ordenar Por:");

        cbxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "nombres", "apellidos", "fecha_nac" }));
        cbxOrdenar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxOrdenarItemStateChanged(evt);
            }
        });

        jLabel3.setText("tipo");

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ascendente", "descendente" }));

        jLabel4.setText("Buscar Por:");

        cbxBusquedapor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "id", "nombres", "apellidos", "cedula", "tituloBachiller" }));
        cbxBusquedapor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBusquedaporItemStateChanged(evt);
            }
        });

        jLabel5.setText("Buscar Por:");

        cbxBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bachillerato G.Unificado", "Bachillerato en Ciencias", "Bachillerato Tecnico", "Bachillerato Modificado" }));

        btmBusqueda.setBackground(new java.awt.Color(0, 153, 102));
        btmBusqueda.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        btmBusqueda.setText("Busqueda");
        btmBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmBusquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(cbxBusquedapor, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btmBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btmBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );

        btmSeleccionar.setBackground(new java.awt.Color(0, 153, 102));
        btmSeleccionar.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        btmSeleccionar.setText("Selecc.");
        btmSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btmSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btmSeleccionar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
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

    private void btmSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmSeleccionarActionPerformed
        cargarVista();
    }//GEN-LAST:event_btmSeleccionarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        save();
    }//GEN-LAST:event_btnguardarActionPerformed

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

    private void btmBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmBusquedaActionPerformed
        try {
            busqueda(cbxBusquedapor.getSelectedItem().toString());
        } catch (Exception ex) {
            System.out.println("Have problems in search" + ex.getMessage()); 
        }
    }//GEN-LAST:event_btmBusquedaActionPerformed

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
    private com.toedter.calendar.JCalendar Calendar;
    private javax.swing.JButton btmBusqueda;
    private javax.swing.JButton btmSeleccionar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JComboBox<String> cbxBusqueda;
    private javax.swing.JComboBox<String> cbxBusquedapor;
    private javax.swing.JComboBox<String> cbxNachiller;
    private javax.swing.JComboBox<String> cbxOrdenar;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtProcedencia;
    private javax.swing.JTextField txtapellidos;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtnombres;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
