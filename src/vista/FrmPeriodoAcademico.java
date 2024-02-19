package vista;

import controladores.PeriodoController;
import controlador.TDALista.LinkedList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.PeriodoAcademico;
import vista.listas.tablas.ModeloTablaPeriodo;

/**
 *
 * @author alexg
 */
public class FrmPeriodoAcademico extends javax.swing.JDialog {

    LinkedList<PeriodoAcademico> p = new LinkedList<>();
    private PeriodoController pcl = new PeriodoController();
    private ModeloTablaPeriodo mtp = new ModeloTablaPeriodo();
    
    /**
     * Creates new form FrmAutos
     */
    public FrmPeriodoAcademico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        limpiar();
        dtcbusqueda.setVisible(false);
        cbxorden.setVisible(false);
    }    
    
    public JPanel getJPanel1() {
        return jPanel1;
    }    

    private void buscar() {
        String criterio = cbxCriterio.getSelectedItem().toString();
        Object valor = null;
        Integer orden;
        if(cbxCriterio.getSelectedIndex() == 0){
            valor = txtBusqueda.getText().toLowerCase();
            orden = 0;
        }else{
            SimpleDateFormat formatofe = new SimpleDateFormat("yy-MM-dd");
            valor = formatofe.format(dtcbusqueda.getDate());
            orden = cbxorden.getSelectedIndex();
        }try {
            LinkedList<PeriodoAcademico> busqueda = pcl.buscarVarios(criterio, valor, orden);
            if(!busqueda.isEmpty()){
                mtp.setPeriodos(busqueda);
                tblTabla.setModel(mtp);
                tblTabla.updateUI();
            }else
                JOptionPane.showMessageDialog(null, "No se ha encontrado lo solicitado", "Error en la búsqueda", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            /*JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);*/
            System.out.println(e.getMessage());                
        }
    }      
    
    private void limpiar() {
        txtnombre.setText("Periodo Academico ");
        txtBusqueda.setText("");
        pcl.setPeriodo(null);
        pcl.setPeriodos(new LinkedList<>());
        pcl.setIndex(-1);
        dtcInicio.setDate(null);
        dtcFin.setDate(null);
        cargarTabla();
    }
    
    private void cargarTabla() {
        mtp.setPeriodos(pcl.getPeriodos());
        tblTabla.setModel(mtp);
        tblTabla.updateUI();
    }
    
    private Boolean validar() {
        return !(dtcFin.getDate() == null) &&
                !(dtcFin.getDate() == null);
    }
    
    private void verNombre(){
        SimpleDateFormat sp = new SimpleDateFormat("MMMMMMMMMM YYYY");
        String fechaIni = sp.format(dtcInicio.getDate()).toUpperCase();
        String fechaFin = sp.format(dtcFin.getDate()).toUpperCase();
        txtnombre.setText("Periodo Academico"+" "+fechaIni+"-"+fechaFin);
    }
    
    private Date setearHorasCero(Date fecha){
        fecha.setHours(00);
        fecha.setMinutes(00);
        fecha.setSeconds(00);
        return fecha;
    }
    
    private void guardar() {
        if (validar()) {
            try {
                verNombre();
                pcl.getPeriodo().setNombre(txtnombre.getText());
                /*SimpleDateFormat format = new SimpleDateFormat("dd-MM-YY 00:00:00");
                String fecha = format.format(dtcInicio.getDate());
                Date fec = setearHorasCero(dtcInicio.getDate());*/
                pcl.getPeriodo().setFechaDesde(dtcInicio.getDate());
                pcl.getPeriodo().setFechaHasta(dtcFin.getDate());
                pcl.getPeriodo().setEstado("T");
                //pcl.getPeriodo().setEstado(true);
                if (pcl.getPeriodo().getId() == null) {
                    try {
                        Integer id = pcl.save();
                        System.out.println("ID: "+id);
                        limpiar();
                        JOptionPane.showMessageDialog(null, 
                                "Se ha guardado correctamente", "Ok", 
                                JOptionPane.INFORMATION_MESSAGE);   
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, 
                            "No se pudo guardar: "+e, 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                    /*if (pcl.save()) {   
                    } else {
                    }*/ 
            } else {
                    try {
                        pcl.update(pcl.getPeriodo());
                        limpiar();
                        JOptionPane.showMessageDialog(null, 
                                "Se ha actualizado correctamente", "Ok", 
                                JOptionPane.INFORMATION_MESSAGE);   
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, 
                            "No se pudo actualizar: "+e, 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    /*if (pcl.update(pcl.getIndex())) {
                    } else {
                    }*/  
               }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                        e.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);                   
            } 
        }else {
                JOptionPane.showMessageDialog(null, 
                        "Llene todos los campos", 
                        "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void cargarVista() {
        pcl.setIndex(tblTabla.getSelectedRow());
        if (pcl.getIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                pcl.setPeriodo(mtp.getPeriodos().get(pcl.getIndex()));
                txtnombre.setText(pcl.getPeriodo().getNombre());
                dtcInicio.setDate(pcl.getPeriodo().getFechaDesde());
                dtcFin.setDate(pcl.getPeriodo().getFechaHasta());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                        e.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dtcFin = new com.toedter.calendar.JDateChooser();
        txtnombre = new javax.swing.JTextField();
        dtcInicio = new com.toedter.calendar.JDateChooser();
        buttonGuardar = new org.edisoncor.gui.button.ButtonAero();
        buttonCancelar = new org.edisoncor.gui.button.ButtonAero();
        buttonCancelar1 = new org.edisoncor.gui.button.ButtonAero();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbxCriterio = new javax.swing.JComboBox<>();
        dtcbusqueda = new com.toedter.calendar.JDateChooser();
        cbxorden = new javax.swing.JComboBox<>();
        buttonSeleccionar = new org.edisoncor.gui.button.ButtonAero();
        buttonBuscar = new org.edisoncor.gui.button.ButtonAero();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de Materias");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1140, 642));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Informacion del periodo académico:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Recuerde que al generarse un nuevo periodo, el existente quedará en desuso.");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Nombre del periodo:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Fecha de Inicio:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Fecha de Fin:");

        txtnombre.setEditable(false);
        txtnombre.setText("Periodo Academico ");

        buttonGuardar.setBackground(new java.awt.Color(0, 204, 204));
        buttonGuardar.setText("Guardar");
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
            }
        });

        buttonCancelar.setBackground(new java.awt.Color(0, 204, 204));
        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        buttonCancelar1.setBackground(new java.awt.Color(0, 204, 204));
        buttonCancelar1.setText("Visualizar Nombre");
        buttonCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(dtcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(dtcFin, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(buttonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(506, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(dtcInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(dtcFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Periodos Registrados:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 200));

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblTabla);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Valor:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Criterio:");

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "FechaDesde", "FechaHasta" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });

        cbxorden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fecha Exacta", "Menores", "Mayores" }));

        buttonSeleccionar.setBackground(new java.awt.Color(0, 204, 204));
        buttonSeleccionar.setText("Seleccionar");
        buttonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeleccionarActionPerformed(evt);
            }
        });

        buttonBuscar.setBackground(new java.awt.Color(0, 204, 204));
        buttonBuscar.setText("Buscar");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtcbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(150, 150, 150)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(cbxorden, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(buttonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(buttonSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(dtcbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(buttonSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1140, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1140, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged
        if(evt.getItem().toString().equalsIgnoreCase("Nombre")){
            txtBusqueda.setVisible(true);
            dtcbusqueda.setVisible(false);
            cbxorden.setVisible(false);
        }else{
            txtBusqueda.setVisible(false);
            dtcbusqueda.setVisible(true);
            cbxorden.setVisible(true);
        }
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void buttonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeleccionarActionPerformed
        cargarVista();
    }//GEN-LAST:event_buttonSeleccionarActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelar1ActionPerformed
        if(validar()){
            verNombre();
        }else
            JOptionPane.showMessageDialog(null, 
                        "Coloque las fechas para completar el nombre del periodo", 
                        "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_buttonCancelar1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPeriodoAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPeriodoAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPeriodoAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPeriodoAcademico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmPeriodoAcademico dialog = new FrmPeriodoAcademico(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAero buttonBuscar;
    private org.edisoncor.gui.button.ButtonAero buttonCancelar;
    private org.edisoncor.gui.button.ButtonAero buttonCancelar1;
    private org.edisoncor.gui.button.ButtonAero buttonGuardar;
    private org.edisoncor.gui.button.ButtonAero buttonLimpiar;
    private org.edisoncor.gui.button.ButtonAero buttonLimpiar1;
    private org.edisoncor.gui.button.ButtonAero buttonSeleccionar;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxorden;
    private com.toedter.calendar.JDateChooser dtcFin;
    private com.toedter.calendar.JDateChooser dtcInicio;
    private com.toedter.calendar.JDateChooser dtcbusqueda;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTabla;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
