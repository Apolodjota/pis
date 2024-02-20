package vista;

import controladores.MatriculaController;
import controladores.PeriodoController;
import controlador.TDALista.LinkedList;
import controladores.EstudianteControlador;
import javax.swing.JButton;
import javax.swing.JLabel;
import vista.listas.util.UtilVistaLista;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import modelo.Estudiante;
import modelo.Matricula;
import modelo.PeriodoAcademico;
import vista.listas.tablas.ModeloTablaMatricula;

/**
 *
 * @author Asus
 */
public class FrmMatricula extends javax.swing.JFrame {

    /**
     * @return the panelPrincipal
     */
    PeriodoController pcl = new PeriodoController();
    PeriodoAcademico pActual = new PeriodoAcademico();
    MatriculaController matriC = new MatriculaController();
    ModeloTablaMatricula mtmatriC = new ModeloTablaMatricula();
    public static Estudiante e = new Estudiante();
    EstudianteControlador ec = new EstudianteControlador();
    /**
     * Creates new form FrmVendedor
     */
    public FrmMatricula() {
        initComponents();
        try {
            pActual = pcl.getPerioVigente();
        } catch (Exception e) {
            System.out.println("No existen periodos: " + e);
        }
        limpiar();
    }

    public JPanel getJPanel1() {
        return panelPrincipal;
    }

    private void cargarCombo() {
        //System.out.println("Comentario a borrar");
    }

    private void limpiar() {
        cbxvalor.setVisible(false);
        try {
            txtperiodo.setText(pActual.getNombre());
            UtilVistaLista.cargarPeriodos(cbxvalor);
            //UtilVistaLista.cargarMarcaEst(cbxestudiante);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //cbxestudiante.setSelectedIndex(0);
        checkGratuidad.setSelected(true);
        checkGratuidad.setEnabled(false);
        cargarTabla();
        tbldatos.clearSelection();
        matriC.setIndex(-1);
        matriC.setMatricula(null);
        matriC.setMatriculas(new LinkedList<>());
    }

    private void cargarTabla() {
        mtmatriC.setMatriculas(matriC.getMatriculas());
        mtmatriC.fireTableDataChanged();
        tbldatos.setModel(mtmatriC);
        tbldatos.updateUI();
    }

    private void obtenerMatricula() {
        //matriC.getMatricula().setId_estudiante(cbxestudiante.getSelectedIndex() + 1);
        matriC.getMatricula().setId_estudiante(e.getId());
        try {
            matriC.getMatricula().setId_periodoAcademico(pActual.getId());
        } catch (Exception e) {
            System.out.println("Error al obtener datos de matricula: " + e.getMessage());
        }
    }

    private boolean validar() {
        //return !(cbxestudiante.getSelectedIndex() == -1);
        return e != null;
    }

    private void cargarVista() {
        checkGratuidad.setEnabled(true);
        matriC.setIndex(tbldatos.getSelectedRow());
        if (matriC.getIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                matriC.setMatricula(mtmatriC.getMatriculas().get(matriC.getIndex()));
                txtperiodo.setText(pcl.getPeriodos().get(matriC.getMatricula().getId_periodoAcademico() - 1).getNombre());
                //cbxestudiante.setSelectedIndex(matriC.getMatricula().getId_estudiante() - 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardar() {
        if (validar()) {
            try {
                obtenerMatricula();
                if (matriC.getMatricula().getId() == null) {
                    try {
                        matriC.save();
                        limpiar();
                        JOptionPane.showMessageDialog(null,
                                "Se ha registrado correctamente la matrícula", "Operación exitosa",
                                JOptionPane.INFORMATION_MESSAGE);
                        matriC.setMatricula(null);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                "No se ha podido guardar: " + e,
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    try {
                        matriC.update(matriC.getMatricula());
                        limpiar();
                        JOptionPane.showMessageDialog(null,
                                "Se ha actualizado correctamente", "Operación exitosa",
                                JOptionPane.INFORMATION_MESSAGE);
                        matriC.setMatricula(null);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                "No se ha podido editar: " + e,
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
                    "¡No ha seleccionado un estudiante!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void setearEstudiante(Estudiante nuevoE) {
        e = nuevoE;
        txtestudiante.setText(e.toString());
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
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        txtperiodo = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        checkGratuidad = new javax.swing.JCheckBox();
        txtestudiante = new javax.swing.JTextField();
        buttonGuardar1 = new org.edisoncor.gui.button.ButtonAero();
        buttonCancelar = new org.edisoncor.gui.button.ButtonAero();
        buttonMatTodos = new org.edisoncor.gui.button.ButtonAero();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldatos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        cbxcriterio = new javax.swing.JComboBox<>();
        txtvalor = new javax.swing.JTextField();
        cbxvalor = new javax.swing.JComboBox<>();
        buttonBuscar = new org.edisoncor.gui.button.ButtonAero();
        buttonSeleccionar = new org.edisoncor.gui.button.ButtonAero();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Matriculas");

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1140, 642));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Completar los campos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel33.setBackground(new java.awt.Color(204, 204, 255));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setText("Estudiante:");

        jLabel34.setBackground(new java.awt.Color(204, 204, 255));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setText("Periodo Vigente:");

        txtperiodo.setEditable(false);

        jLabel35.setBackground(new java.awt.Color(204, 204, 255));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setText("Gratuidad:");

        checkGratuidad.setSelected(true);
        checkGratuidad.setText("Mantiene");
        checkGratuidad.setEnabled(false);

        txtestudiante.setEditable(false);
        txtestudiante.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtestudiante.setText("Seleccione...");
        txtestudiante.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtestudiante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtestudianteMouseClicked(evt);
            }
        });
        txtestudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtestudianteActionPerformed(evt);
            }
        });

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

        buttonMatTodos.setBackground(new java.awt.Color(0, 204, 204));
        buttonMatTodos.setText("Matricular Todos");
        buttonMatTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMatTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtperiodo)
                            .addComponent(txtestudiante, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(buttonMatTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(checkGratuidad, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(161, 161, 161))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)))))
                .addContainerGap(236, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(txtperiodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtestudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonMatTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(checkGratuidad))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(buttonGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Registros Existentes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbldatos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbldatos);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 80, 720, 239));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Filtrar por:");
        jPanel7.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        cbxcriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombres de Estudiante", "Apellidos de Estudiante", "Periodo Academico" }));
        cbxcriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxcriterioItemStateChanged(evt);
            }
        });
        jPanel7.add(cbxcriterio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));
        jPanel7.add(txtvalor, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 36, 340, 30));

        cbxvalor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel7.add(cbxvalor, new org.netbeans.lib.awtextra.AbsoluteConstraints(336, 40, 310, -1));

        buttonBuscar.setBackground(new java.awt.Color(0, 204, 204));
        buttonBuscar.setText("Buscar");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });
        jPanel7.add(buttonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 120, 30));

        buttonSeleccionar.setBackground(new java.awt.Color(0, 204, 204));
        buttonSeleccionar.setText("Seleccionar");
        buttonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeleccionarActionPerformed(evt);
            }
        });
        jPanel7.add(buttonSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 170, 120, 30));

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtestudianteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtestudianteMouseClicked
        new DlgSelecEstudiante(this, true).setVisible(true);
    }//GEN-LAST:event_txtestudianteMouseClicked

    private void txtestudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtestudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtestudianteActionPerformed

    private void cbxcriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxcriterioItemStateChanged
        if(cbxcriterio.getSelectedIndex() == 2){
            txtvalor.setVisible(false);
            cbxvalor.setVisible(true);
        }else{
            txtvalor.setVisible(true);
            cbxvalor.setVisible(false);
        }
    }//GEN-LAST:event_cbxcriterioItemStateChanged

    private void buttonGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardar1ActionPerformed
        guardar();
    }//GEN-LAST:event_buttonGuardar1ActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        //limpiar();
        int yinicial = 25;
        for (int i = 0; i < 5; i++) {
            JLabel bot = new javax.swing.JLabel();
            jPanel6.add(bot);
            bot.setLocation(100, yinicial);
            bot.setBackground(new java.awt.Color(204, 204, 255));
            bot.setFont(new java.awt.Font("Times New Roman", 1, 14));
            bot.setSize(111, 36);
            bot.setText("XDDDD");
            yinicial += 25;
        }
        
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonMatTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMatTodosActionPerformed
        try {
            matriC.matricularVarios(ec.getEstudiantesNoMatriculados(), pActual.getId());
            JOptionPane.showMessageDialog(null,
                    "Estudiantes matriculados correctamente",
                    "OK",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "No se logró matrícular a varios estudiantes: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonMatTodosActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        LinkedList<Matricula> list = new LinkedList<>();
        if(txtvalor.getText().isEmpty())
            //JOptionPane.showMessageDialog(null, "Ingrese el valor de búsqueda", "Error", JOptionPane.WARNING_MESSAGE);
            System.out.println("");
        else{
            Integer index = cbxcriterio.getSelectedIndex();
            String atributo = cbxcriterio.getSelectedItem().toString();
            String valor = txtvalor.getText();
            try {
                /*if(index.intValue() == 0){
                    list.add(ec.BusquedaCedula(modelo.getEstudiantes(), valor, atributo));
                }
                else if(index.intValue() == 1){
                    list = ec.buscarNombres(modelo.getEstudiantes(), atributo, valor);
                }    
                else
                    list = matriC.matriculasporPeriodo(UtilVistaLista.getcomboPeriodo(cbxvalor).getId());*/
            } catch (Exception e) { 
            }
            
        }list = matriC.matriculasporPeriodo(UtilVistaLista.getcomboPeriodo(cbxvalor).getId());
            mtmatriC.setMatriculas(list);
            tbldatos.setModel(mtmatriC);
            tbldatos.updateUI();
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void buttonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeleccionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSeleccionarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMatricula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMatricula().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAero buttonBuscar;
    private org.edisoncor.gui.button.ButtonAero buttonCancelar;
    private org.edisoncor.gui.button.ButtonAero buttonGuardar1;
    private org.edisoncor.gui.button.ButtonAero buttonMatTodos;
    private org.edisoncor.gui.button.ButtonAero buttonSeleccionar;
    private javax.swing.JComboBox<String> cbxcriterio;
    private javax.swing.JComboBox<String> cbxvalor;
    private javax.swing.JCheckBox checkGratuidad;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tbldatos;
    public static javax.swing.JTextField txtestudiante;
    private javax.swing.JTextField txtperiodo;
    private javax.swing.JTextField txtvalor;
    // End of variables declaration//GEN-END:variables
}
