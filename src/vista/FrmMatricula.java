package vista;

import controladores.MatriculaController;
import controladores.PeriodoController;
import controlador.TDALista.LinkedList;
import controladores.EstudianteControlador;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import vista.listas.util.UtilVistaLista;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import modelo.Estudiante;
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
        try {
            txtperiodo.setText(pActual.getNombre());
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
        buttonGuardar = new org.edisoncor.gui.button.ButtonAero();
        buttonCancelar = new org.edisoncor.gui.button.ButtonAero();
        buttonMatTodos = new org.edisoncor.gui.button.ButtonAero();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldatos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        cbxcriterio = new javax.swing.JComboBox<>();
        txtvalor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        buttonCancelar1 = new org.edisoncor.gui.button.ButtonAero();

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

        buttonMatTodos.setBackground(new java.awt.Color(0, 204, 204));
        buttonMatTodos.setText("Matricular todos");
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
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtperiodo)
                            .addComponent(txtestudiante, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(357, 357, 357)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(buttonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkGratuidad, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(78, 78, 78)
                .addComponent(buttonMatTodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Registros Existentes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Filtrar por:");

        cbxcriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Curso", "Periodo", "Estudiante" }));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Filtrar por:");

        buttonCancelar1.setBackground(new java.awt.Color(0, 204, 204));
        buttonCancelar1.setText("Seleccionar");
        buttonCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(cbxcriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jLabel10))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116)
                .addComponent(buttonCancelar1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(92, 92, 92))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbxcriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtvalor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(buttonCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

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

    private void buttonCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelar1ActionPerformed
        cargarVista();
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
    private org.edisoncor.gui.button.ButtonAero buttonCancelar;
    private org.edisoncor.gui.button.ButtonAero buttonCancelar1;
    private org.edisoncor.gui.button.ButtonAero buttonGuardar;
    private org.edisoncor.gui.button.ButtonAero buttonMatTodos;
    private javax.swing.JComboBox<String> cbxcriterio;
    private javax.swing.JCheckBox checkGratuidad;
    private javax.swing.JLabel jLabel10;
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
