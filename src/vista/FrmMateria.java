package vista;

import controladores.CursoControllerListas;
import controladores.MateriaControllerListas;
import controlador.TDALista.LinkedList;
import controladores.MallaControllerListas;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Curso;
import modelo.Malla;
import modelo.Materia;
import vista.listas.tablas.ModeloTablaMateriaListas;
import vista.listas.util.UtilVista;

/**
 *
 * @author alexg
 */
public class FrmMateria extends javax.swing.JFrame {

    private MateriaControllerListas mcl = new MateriaControllerListas();
    private CursoControllerListas ccl = new CursoControllerListas();
    private ModeloTablaMateriaListas mtml = new ModeloTablaMateriaListas();

    public JPanel getJPanel1() {
        return jPanel1;
    }

    /**
     * Creates new form FrmAutos
     */
    public FrmMateria() {
        initComponents();
        limpiar();
    }

    private void ordenar() {
        String criterio = cbxCriterio.getSelectedItem().toString().toLowerCase();
        Integer ascdesc = cbxAscDesc.getSelectedIndex();
        try {
            if (criterio.equalsIgnoreCase("nombre")) {
                mtml.setMaterias(mcl.quickSort(ascdesc, criterio, mtml.getMaterias()));
            } else if (criterio.equalsIgnoreCase("curso")) {
                mtml.setMaterias(mcl.quickSort(ascdesc, "id_curso", mtml.getMaterias()));
            }
            tblTabla.setModel(mtml);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscar() {
        String criterio = cbxCriterio.getSelectedItem().toString().toLowerCase();
        try {
            if (criterio.equalsIgnoreCase("nombre")) {
                mtml.setMaterias(mcl.buscarNombre(mcl.getMaterias(), criterio, txtBusqueda.getText()));
            } else if (criterio.equalsIgnoreCase("curso")) {
                mtml.setMaterias(mcl.buscarCurso(mcl.getMaterias(), "id_curso", UtilVista.getComboCursos(cbxCursoB)));
            }

            tblTabla.setModel(mtml);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiar() {
        txtNombre.setText("");
        txtBusqueda.setText("");
        mcl.setMateria(null);
        mcl.setMaterias(new LinkedList<>());
        cargarTabla();
        mcl.setMateria(null);
        mcl.setIndex(-1);
        try {
            UtilVista.cargarMalla(cbxMalla);
            txtBusqueda.setVisible(true);
            cbxCursoB.setVisible(false);
            jblTexto.setVisible(true);
            jblCiclo.setVisible(false);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void cargarTabla() {
        mtml.setMaterias(mcl.getMaterias());
        tblTabla.setModel(mtml);
        tblTabla.updateUI();
    }

    private Boolean validar() {
        return !txtNombre.getText().trim().isEmpty();
    }

    private void guardar() {
        if (validar()) {
            try {
                mcl.getMateria().setNombre(txtNombre.getText());
                mcl.getMateria().setId_curso(UtilVista.getComboCursos(cbxCurso).getId());

                if (mcl.getMateria().getId() == null) {
                    try {
                        Integer id = mcl.save();
                        System.out.println("ID: " + id);
                        limpiar();
                        JOptionPane.showMessageDialog(null,
                                "Se ha guardado correctamente", "Ok",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                "No se pudo guardar: " + e,
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                } else {
                    try {
                        mcl.update(mcl.getMateria());
                        limpiar();
                        JOptionPane.showMessageDialog(null,
                                "Se ha actualizado correctamente", "Ok",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                "No se pudo actualizar: " + e,
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Llene todos los campos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cargarVista() {
        mcl.setIndex(tblTabla.getSelectedRow());
        if (mcl.getIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                mcl.setMateria(mtml.getMaterias().get(mcl.getIndex()));
                txtNombre.setText(mcl.getMateria().getNombre());
                cbxMalla.setSelectedItem(UtilVista.getComboMallas(cbxMalla).getId().equals(mcl.getMateria().getId_curso()));
                cbxCurso.setSelectedItem(mcl.getMateria().getId_curso());
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxCurso = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbxMalla = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();
        jblTexto = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbxCriterio = new javax.swing.JComboBox<>();
        cbxAscDesc = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        cbxCursoB = new javax.swing.JComboBox<>();
        jblCiclo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1140, 642));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Informacion de la Materia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnGuardar, gridBagConstraints);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnCancelar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(txtNombre, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Nombre de la materia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Curso:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(cbxCurso, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Ciclo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel3.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Malla:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel3.add(jLabel12, gridBagConstraints);

        cbxMalla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMalla.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMallaItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(cbxMalla, gridBagConstraints);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Lista de Materias", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.setLayout(new java.awt.GridBagLayout());

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = -200;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jScrollPane2, gridBagConstraints);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(btnSeleccionar, gridBagConstraints);

        jblTexto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblTexto.setText("Texto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jblTexto, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(txtBusqueda, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Criterio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jLabel9, gridBagConstraints);

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOMBRE", "CURSO" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(cbxCriterio, gridBagConstraints);

        cbxAscDesc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDETE" }));
        cbxAscDesc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxAscDescItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(cbxAscDesc, gridBagConstraints);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(btnBuscar, gridBagConstraints);

        cbxCursoB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxCursoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCursoBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel5.add(cbxCursoB, gridBagConstraints);

        jblCiclo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblCiclo.setText("Ciclo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jblCiclo, gridBagConstraints);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
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
        ordenar();
        if (evt.getItem().toString().equalsIgnoreCase("NOMBRE")) {
            txtBusqueda.setVisible(true);
            cbxCursoB.setVisible(false);
            jblTexto.setVisible(true);
            jblCiclo.setVisible(false);
        } else if (evt.getItem().toString().equalsIgnoreCase("CURSO")) {
            txtBusqueda.setVisible(false);
            cbxCursoB.setVisible(true);
            jblTexto.setVisible(false);
            jblCiclo.setVisible(true);
        }
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void cbxAscDescItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAscDescItemStateChanged
        ordenar();
    }//GEN-LAST:event_cbxAscDescItemStateChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbxCursoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCursoBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCursoBActionPerformed

    private void cbxMallaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMallaItemStateChanged
        Malla malla = null;
        try {
            malla = new MallaControllerListas().getMallas().get(UtilVista.getComboMallas(cbxMalla).getId() - 1);
            System.out.println("MALLA ID: " + malla.getId());
            UtilVista.cargarCurso(cbxCurso, malla.getId());
        } catch (Exception e) {

        }
    }//GEN-LAST:event_cbxMallaItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmMateria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMateria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMateria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMateria.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMateria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> cbxAscDesc;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxCurso;
    private javax.swing.JComboBox<String> cbxCursoB;
    private javax.swing.JComboBox<String> cbxMalla;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jblCiclo;
    private javax.swing.JLabel jblTexto;
    private javax.swing.JTable tblTabla;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
