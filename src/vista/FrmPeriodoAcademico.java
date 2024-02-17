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
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dtcFin = new com.toedter.calendar.JDateChooser();
        txtnombre = new javax.swing.JTextField();
        vernombre = new javax.swing.JButton();
        dtcInicio = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbxCriterio = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        dtcbusqueda = new com.toedter.calendar.JDateChooser();
        cbxorden = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de Materias");

        jPanel1.setPreferredSize(new java.awt.Dimension(1140, 642));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Informacion del periodo académico:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel3.setLayout(null);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar);
        btnGuardar.setBounds(170, 160, 72, 23);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelar);
        btnCancelar.setBounds(390, 160, 76, 23);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Recuerde que al generarse un nuevo periodo, el existente quedará en desuso.");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(20, 130, 440, 16);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Nombre del periodo:");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(20, 30, 120, 16);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Fecha de Inicio:");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(20, 70, 120, 16);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Fecha de Fin:");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(20, 100, 120, 16);
        jPanel3.add(dtcFin);
        dtcFin.setBounds(150, 100, 260, 22);

        txtnombre.setEditable(false);
        txtnombre.setText("Periodo Academico ");
        jPanel3.add(txtnombre);
        txtnombre.setBounds(150, 30, 320, 22);

        vernombre.setText("Visualizar Nombre");
        vernombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vernombreActionPerformed(evt);
            }
        });
        jPanel3.add(vernombre);
        vernombre.setBounds(480, 30, 130, 23);
        jPanel3.add(dtcInicio);
        dtcInicio.setBounds(150, 70, 260, 22);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Periodos Registrados:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel5.setLayout(null);

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

        jPanel5.add(jScrollPane2);
        jScrollPane2.setBounds(30, 120, 950, 227);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        jPanel5.add(btnSeleccionar);
        btnSeleccionar.setBounds(1010, 210, 90, 23);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Valor:");
        jPanel5.add(jLabel8);
        jLabel8.setBounds(70, 70, 36, 16);
        jPanel5.add(txtBusqueda);
        txtBusqueda.setBounds(130, 70, 170, 22);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Criterio:");
        jPanel5.add(jLabel9);
        jLabel9.setBounds(450, 70, 60, 16);

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "FechaDesde", "FechaHasta" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        jPanel5.add(cbxCriterio);
        cbxCriterio.setBounds(530, 70, 138, 22);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel5.add(btnBuscar);
        btnBuscar.setBounds(1000, 70, 90, 23);
        jPanel5.add(dtcbusqueda);
        dtcbusqueda.setBounds(130, 70, 170, 22);

        cbxorden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fecha Exacta", "Menores", "Mayores" }));
        jPanel5.add(cbxorden);
        cbxorden.setBounds(790, 70, 130, 22);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        cargarVista();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void vernombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vernombreActionPerformed
        if(validar()){
            verNombre();
        }else
            JOptionPane.showMessageDialog(null, 
                        "Coloque las fechas para completar el nombre del periodo", 
                        "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_vernombreActionPerformed

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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSeleccionar;
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
    private javax.swing.JButton vernombre;
    // End of variables declaration//GEN-END:variables
}
