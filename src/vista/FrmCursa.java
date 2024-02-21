package vista;

import controladores.PeriodoController;
import controlador.TDALista.LinkedList;
import controladores.CursaController;
import controladores.CursoControllerListas;
import controladores.RevisionController;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Docente;
import modelo.Matricula;
import modelo.Persona;
import vista.listas.tablas.ModeloTablaCursa;
import vista.listas.util.UtilVista;

/**
 *
 * @author Asus
 */
public class FrmCursa extends javax.swing.JFrame {
    
    /**
     * @return the panelPrincipal
     */
    CursaController cC = new CursaController();
    //CursoControllerListas csoC = new CursoControllerListas();
    //RevisionController revC = new RevisionController();
    ModeloTablaCursa mtcc = new ModeloTablaCursa();
    public static Matricula m;
    public static Docente d;

    /**
     * Creates new form FrmVendedor
     */
    public FrmCursa() {
        initComponents();
        limpiar();
    }
    public FrmCursa(Persona p) {
        initComponents();
        limpiar();
    }

    public JPanel getJPanel1() {
        return panelPrincipal;
    }

    private void limpiar() {
        try {
            UtilVista.cargarMateria(cbxMateria);
            //UtilVista.cargarDocente(cbxDocente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con los combos de seleccion: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        txtMatricula.setEnabled(false);
        txtDocente.setEditable(false);
        txtParalelo.setEnabled(true);
        tbltabla.clearSelection();
        cC.setCursa(null);
        cC.setCursas(new LinkedList<>());
        cargarTabla();
    }

    private void cargarTabla() {
        mtcc.setCursas(cC.getCursas());
        tbltabla.setModel(mtcc);
        tbltabla.updateUI();
    }

    private void cargarVista() {
        Integer fila = tbltabla.getSelectedRow();
        cC.setIndex(fila);
        if (cC.getIndex().intValue() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Selecccione la fila", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                cC.setCursa(mtcc.getCursas().get(cC.getIndex()));
                txtMatricula.setText(cC.getCursa().getId_matricula().toString());
                txtDocente.setText(cC.getCursa().getId_docente().toString());
                cbxMateria.setSelectedIndex(cC.getCursa().getId_materia() - 1);
                //cbxPeriodo.setSelectedIndex(cC.getCursa().getId_periodo()- 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validar() {
        //return !(cbxestudiante.getSelectedIndex() == -1);
        return m != null
                && txtParalelo.getText() != null;
    }

    private void guardar() {
        Integer respuesta = JOptionPane.showConfirmDialog(null,
                "Realmente desea guardar este nuevo paralelo?",
                "Confirmar guaardado",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            if (validar()) {
                try {
                    cC.getCursa().setId_matricula(m.getId());
                    cC.getCursa().setId_docente(d.getId());
                    cC.getCursa().setId_materia(UtilVista.getComboMateria(cbxMateria).getId());
                    cC.getCursa().setParalelo(txtParalelo.getText());
                    cC.guardar();
                    cargarTabla();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            e.getMessage(), "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "Perfecto ", "OK",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void setearMatricula(Matricula mat) {
        m = mat;
        txtMatricula.setText(m.toString());
    }
    
    public static void setearDocente(Docente docente){
        d = docente;
        txtDocente.setText(d.toString());
    }
    
    private void ordenar(){
        Integer ascdesc = descendente.getState() ? 1 : 0;
        String criterio = "";
        if(cbxOrdenamiento.getSelectedItem().toString().equalsIgnoreCase("CODIGO CURSA")){
            criterio = "id";
        }else if(cbxOrdenamiento.getSelectedItem().toString().equalsIgnoreCase("MATRICULA")){
            criterio = "id_matricula";
        }else{
            criterio = "id_docente";
        }
            cC.quickSort(cC.getCursas(), ascdesc, criterio);
            limpiar();
            cargarTabla();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        panelPrincipal = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        cbxMateria = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        lblPeriodo = new javax.swing.JLabel();
        txtParalelo = new javax.swing.JTextField();
        jblTexto2 = new javax.swing.JLabel();
        cbxOrdenamiento = new javax.swing.JComboBox<>();
        descendente = new java.awt.Checkbox();
        buttonGuardar1 = new org.edisoncor.gui.button.ButtonAero();
        buttonCancelar = new org.edisoncor.gui.button.ButtonAero();
        txtDocente = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltabla = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        jblTexto = new javax.swing.JLabel();
        cbxCriterio = new javax.swing.JComboBox<>();
        jblTexto1 = new javax.swing.JLabel();
        buttonGuardar3 = new org.edisoncor.gui.button.ButtonAero();
        buttonBuscar1 = new org.edisoncor.gui.button.ButtonAero();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Cursas");
        setResizable(false);

        panelPrincipal.setBackground(new java.awt.Color(153, 153, 153));
        panelPrincipal.setMinimumSize(new java.awt.Dimension(622, 251));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1140, 622));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Registrar un nuevo CURSA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT", 1, 14))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(1140, 642));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setBackground(new java.awt.Color(204, 204, 255));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("Paralelo:");
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 94, -1));

        txtMatricula.setEditable(false);
        txtMatricula.setBackground(new java.awt.Color(204, 204, 255));
        txtMatricula.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtMatricula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMatricula.setText("Seleccione");
        txtMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMatriculaMouseClicked(evt);
            }
        });
        txtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatriculaActionPerformed(evt);
            }
        });
        jPanel6.add(txtMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 125, -1));

        jLabel33.setBackground(new java.awt.Color(204, 204, 255));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setText("Materia:");
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 89, -1));

        cbxMateria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMateria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMateriaItemStateChanged(evt);
            }
        });
        cbxMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMateriaActionPerformed(evt);
            }
        });
        jPanel6.add(cbxMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 250, -1));

        jLabel36.setBackground(new java.awt.Color(204, 204, 255));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setText("Docente:");
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 89, -1));

        lblPeriodo.setBackground(new java.awt.Color(204, 204, 255));
        lblPeriodo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPeriodo.setText("Estudiante matriculado:");
        jPanel6.add(lblPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        txtParalelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtParaleloActionPerformed(evt);
            }
        });
        jPanel6.add(txtParalelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 125, -1));

        jblTexto2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblTexto2.setText("Ordenar cursas por?");
        jPanel6.add(jblTexto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, -1, -1));

        cbxOrdenamiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CODIGO CURSA", "MATRICULA", "MATERIA" }));
        cbxOrdenamiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxOrdenamientoItemStateChanged(evt);
            }
        });
        cbxOrdenamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOrdenamientoActionPerformed(evt);
            }
        });
        jPanel6.add(cbxOrdenamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 140, -1));

        descendente.setLabel("Descendente");
        jPanel6.add(descendente, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 102, -1));

        buttonGuardar1.setBackground(new java.awt.Color(0, 204, 204));
        buttonGuardar1.setText("Guardar");
        buttonGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardar1ActionPerformed(evt);
            }
        });
        jPanel6.add(buttonGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 120, 30));

        buttonCancelar.setBackground(new java.awt.Color(0, 204, 204));
        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });
        jPanel6.add(buttonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 120, 30));

        txtDocente.setEditable(false);
        txtDocente.setBackground(new java.awt.Color(204, 204, 255));
        txtDocente.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtDocente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDocente.setText("Seleccione");
        txtDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDocenteMouseClicked(evt);
            }
        });
        txtDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocenteActionPerformed(evt);
            }
        });
        jPanel6.add(txtDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 125, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Registros Existentes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT", 1, 14))); // NOI18N
        jPanel7.setLayout(new java.awt.GridBagLayout());

        tbltabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbltabla);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 645;
        gridBagConstraints.ipady = 385;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(24, 26, 0, 27);
        jPanel7.add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(txtBusqueda, gridBagConstraints);

        jblTexto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblTexto.setText("Texto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(jblTexto, gridBagConstraints);

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOMBRE", "CURSO" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(cbxCriterio, gridBagConstraints);

        jblTexto1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblTexto1.setText("Criterio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(jblTexto1, gridBagConstraints);

        buttonGuardar3.setBackground(new java.awt.Color(0, 204, 204));
        buttonGuardar3.setText("Seleccionar");
        buttonGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardar3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        jPanel7.add(buttonGuardar3, gridBagConstraints);

        buttonBuscar1.setBackground(new java.awt.Color(0, 204, 204));
        buttonBuscar1.setText("Buscar");
        buttonBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscar1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 44;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel7.add(buttonBuscar1, gridBagConstraints);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatriculaActionPerformed
        //LLLamadi a dialoj
    }//GEN-LAST:event_txtMatriculaActionPerformed

    private void cbxMateriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMateriaItemStateChanged
        //nadita de nada
    }//GEN-LAST:event_cbxMateriaItemStateChanged

    private void txtParaleloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtParaleloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtParaleloActionPerformed

    private void txtMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMatriculaMouseClicked
        new DialogSelecMatricula(this, true).setVisible(true);
    }//GEN-LAST:event_txtMatriculaMouseClicked

    private void cbxMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMateriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMateriaActionPerformed

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged
        //ordenar();
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void cbxOrdenamientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxOrdenamientoItemStateChanged
        ordenar();
    }//GEN-LAST:event_cbxOrdenamientoItemStateChanged

    private void cbxOrdenamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrdenamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxOrdenamientoActionPerformed

    private void buttonGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardar1ActionPerformed
        guardar();
    }//GEN-LAST:event_buttonGuardar1ActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardar3ActionPerformed
        cargarVista();
    }//GEN-LAST:event_buttonGuardar3ActionPerformed

    private void buttonBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscar1ActionPerformed
        //buscar();
    }//GEN-LAST:event_buttonBuscar1ActionPerformed

    private void txtDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDocenteMouseClicked
        new DialogSelecDocente(this, true).setVisible(true);
    }//GEN-LAST:event_txtDocenteMouseClicked

    private void txtDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocenteActionPerformed
        
    }//GEN-LAST:event_txtDocenteActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCursa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAero buttonBuscar1;
    private org.edisoncor.gui.button.ButtonAero buttonCancelar;
    private org.edisoncor.gui.button.ButtonAero buttonGuardar1;
    private org.edisoncor.gui.button.ButtonAero buttonGuardar3;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxMateria;
    private javax.swing.JComboBox<String> cbxOrdenamiento;
    private java.awt.Checkbox descendente;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel jblTexto;
    private javax.swing.JLabel jblTexto1;
    private javax.swing.JLabel jblTexto2;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tbltabla;
    private javax.swing.JTextField txtBusqueda;
    private static javax.swing.JTextField txtDocente;
    private static javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtParalelo;
    // End of variables declaration//GEN-END:variables
}