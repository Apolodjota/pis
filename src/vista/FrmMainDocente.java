package vista;

import controladores.CuentaControllerListas;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author alexg
 */
public class FrmMainDocente extends javax.swing.JFrame {

    private CuentaControllerListas cc = new CuentaControllerListas();
    private boolean isRevisionesClicked = false;
    private boolean isAsignasionesClicked = false;

    /**
     * Creates new form FrmPrincipalAdministrador
     */
    public FrmMainDocente() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarImagenes();
    }

    private void cargarImagenes() {
        panelImageAsignacion.setIcon(new ImageIcon("src/icono/asignaronline.png"));
        panelImageRevision.setIcon(new ImageIcon("src/icono/revisar.png"));
        panelImageLogo.setIcon(new ImageIcon("src/icono/usuario_prin.png"));
        panelImage11.setIcon(new ImageIcon("src/icono/cerrar-sesion.png"));
        panelImageCentral.setIcon(new ImageIcon("src/icono/logoEva_1.png"));
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jplSalir = new javax.swing.JPanel();
        panelImage11 = new org.edisoncor.gui.panel.PanelImage();
        jLabel14 = new javax.swing.JLabel();
        panelImage10 = new org.edisoncor.gui.panel.PanelImage();
        jPanel3 = new javax.swing.JPanel();
        jplAsignacion = new javax.swing.JPanel();
        panelImageAsignacion = new org.edisoncor.gui.panel.PanelImage();
        lblAsignacion = new javax.swing.JLabel();
        lblAsignacion2 = new javax.swing.JLabel();
        jplRevision = new javax.swing.JPanel();
        panelImageRevision = new org.edisoncor.gui.panel.PanelImage();
        lblRevision = new javax.swing.JLabel();
        lblRevision2 = new javax.swing.JLabel();
        panelImageLogo = new org.edisoncor.gui.panel.PanelImage();
        jblNombre = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        panelImageCentral = new org.edisoncor.gui.panel.PanelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));

        jplSalir.setBackground(new java.awt.Color(255, 255, 204));
        jplSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jplSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplSalirMouseClicked(evt);
            }
        });
        jplSalir.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout panelImage11Layout = new javax.swing.GroupLayout(panelImage11);
        panelImage11.setLayout(panelImage11Layout);
        panelImage11Layout.setHorizontalGroup(
            panelImage11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );
        panelImage11Layout.setVerticalGroup(
            panelImage11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 35;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jplSalir.add(panelImage11, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Cerrar Sesion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jplSalir.add(jLabel14, gridBagConstraints);

        javax.swing.GroupLayout panelImage10Layout = new javax.swing.GroupLayout(panelImage10);
        panelImage10.setLayout(panelImage10Layout);
        panelImage10Layout.setHorizontalGroup(
            panelImage10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );
        panelImage10Layout.setVerticalGroup(
            panelImage10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelImage10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jplSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jplSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelImage10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jplAsignacion.setBackground(new java.awt.Color(0, 204, 204));
        jplAsignacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jplAsignacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplAsignacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplAsignacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplAsignacionMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelImageAsignacionLayout = new javax.swing.GroupLayout(panelImageAsignacion);
        panelImageAsignacion.setLayout(panelImageAsignacionLayout);
        panelImageAsignacionLayout.setHorizontalGroup(
            panelImageAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );
        panelImageAsignacionLayout.setVerticalGroup(
            panelImageAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblAsignacion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAsignacion.setText("Asignación");

        lblAsignacion2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAsignacion2.setText("de tareas");

        javax.swing.GroupLayout jplAsignacionLayout = new javax.swing.GroupLayout(jplAsignacion);
        jplAsignacion.setLayout(jplAsignacionLayout);
        jplAsignacionLayout.setHorizontalGroup(
            jplAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplAsignacionLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(panelImageAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jplAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAsignacion2)
                    .addComponent(lblAsignacion))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jplAsignacionLayout.setVerticalGroup(
            jplAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplAsignacionLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jplAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelImageAsignacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jplAsignacionLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblAsignacion2))
                    .addComponent(lblAsignacion))
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(166, 5, 0, 2);
        jPanel3.add(jplAsignacion, gridBagConstraints);

        jplRevision.setBackground(new java.awt.Color(0, 204, 204));
        jplRevision.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jplRevision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplRevisionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplRevisionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplRevisionMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelImageRevisionLayout = new javax.swing.GroupLayout(panelImageRevision);
        panelImageRevision.setLayout(panelImageRevisionLayout);
        panelImageRevisionLayout.setHorizontalGroup(
            panelImageRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );
        panelImageRevisionLayout.setVerticalGroup(
            panelImageRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblRevision.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRevision.setText("Revisión de");

        lblRevision2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRevision2.setText("Asignaciones");

        javax.swing.GroupLayout jplRevisionLayout = new javax.swing.GroupLayout(jplRevision);
        jplRevision.setLayout(jplRevisionLayout);
        jplRevisionLayout.setHorizontalGroup(
            jplRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplRevisionLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(panelImageRevision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jplRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRevision2)
                    .addComponent(lblRevision)))
        );
        jplRevisionLayout.setVerticalGroup(
            jplRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplRevisionLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jplRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelImageRevision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jplRevisionLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblRevision2))
                    .addComponent(lblRevision))
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 352, 2);
        jPanel3.add(jplRevision, gridBagConstraints);

        javax.swing.GroupLayout panelImageLogoLayout = new javax.swing.GroupLayout(panelImageLogo);
        panelImageLogo.setLayout(panelImageLogoLayout);
        panelImageLogoLayout.setHorizontalGroup(
            panelImageLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );
        panelImageLogoLayout.setVerticalGroup(
            panelImageLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 85;
        gridBagConstraints.ipady = 81;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 57, 0, 0);
        jPanel3.add(panelImageLogo, gridBagConstraints);

        jblNombre.setFont(new java.awt.Font("Harlow Solid Italic", 1, 18)); // NOI18N
        jblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblNombre.setText("Docente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 57, 0, 0);
        jPanel3.add(jblNombre, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1140, 572));
        jPanel5.setRequestFocusEnabled(false);
        jPanel5.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout panelImageCentralLayout = new javax.swing.GroupLayout(panelImageCentral);
        panelImageCentral.setLayout(panelImageCentralLayout);
        panelImageCentralLayout.setHorizontalGroup(
            panelImageCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
        );
        panelImageCentralLayout.setVerticalGroup(
            panelImageCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 595;
        gridBagConstraints.ipady = 592;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 50, 50);
        jPanel5.add(panelImageCentral, gridBagConstraints);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1398, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE))
                    .addGap(10, 10, 10)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 674, Short.MAX_VALUE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE))
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jplAsignacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplAsignacionMouseClicked
        isAsignasionesClicked = true;

        JLabel[] labelsBlancos = {lblRevision, lblRevision2};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.white);
        }

        JPanel[] panelsCianOscuro = {jplRevision, jplAsignacion};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(new Color(0, 204, 204));
        }

        jplAsignacion.setBackground(Color.WHITE);
        lblAsignacion.setForeground(Color.blue);
        lblAsignacion2.setForeground(Color.blue);

        FrmDocente frmDocente = new FrmDocente();
        JPanel panelDocente = frmDocente.getJPanel1();
        jPanel5.removeAll();
        jPanel5.add(panelDocente);
        jPanel5.revalidate();
        jPanel5.repaint();
    }//GEN-LAST:event_jplAsignacionMouseClicked

    private void jplRevisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplRevisionMouseClicked
        isRevisionesClicked = true;

        JLabel[] labelsBlancos = {lblAsignacion, lblAsignacion2};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.white);
        }

        JPanel[] panelsCianOscuro = {jplAsignacion, jplRevision};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(new Color(0, 204, 204));
        }

        jplRevision.setBackground(Color.WHITE);
        lblRevision.setForeground(Color.blue);
        lblRevision2.setForeground(Color.blue);

        FrmEstudiante frmEstudiante = new FrmEstudiante();
        JPanel panelDocente = frmEstudiante.getJPanel1();
        jPanel5.removeAll();
        jPanel5.add(panelDocente);
        jPanel5.revalidate();
        jPanel5.repaint();
    }//GEN-LAST:event_jplRevisionMouseClicked

    private void jplSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplSalirMouseClicked
        new FrmIniciarSesion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jplSalirMouseClicked

    private void jplAsignacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplAsignacionMouseEntered
        if (!isAsignasionesClicked) {
            jplAsignacion.setBackground(Color.WHITE);
            lblAsignacion.setForeground(Color.blue);
            lblAsignacion2.setForeground(Color.blue);
        }
    }//GEN-LAST:event_jplAsignacionMouseEntered

    private void jplAsignacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplAsignacionMouseExited
        if (!isAsignasionesClicked) {
            jplAsignacion.setBackground(new Color(0, 204, 204));
            lblAsignacion.setForeground(Color.black);
            lblAsignacion2.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplAsignacionMouseExited

    private void jplRevisionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplRevisionMouseEntered
        if (!isRevisionesClicked) {
            jplRevision.setBackground(Color.WHITE);
            lblRevision.setForeground(Color.blue);
            lblRevision2.setForeground(Color.blue);
        }
    }//GEN-LAST:event_jplRevisionMouseEntered

    private void jplRevisionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplRevisionMouseExited
        if (!isRevisionesClicked) {
            jplRevision.setBackground(new Color(0, 204, 204));
            lblRevision.setForeground(Color.black);
            lblRevision2.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplRevisionMouseExited

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
            java.util.logging.Logger.getLogger(FrmMainDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMainDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMainDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMainDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String nombre = null;
                String apellidos = null;
                new FrmMainDocente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel14;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jblNombre;
    private javax.swing.JPanel jplAsignacion;
    private javax.swing.JPanel jplRevision;
    private javax.swing.JPanel jplSalir;
    private javax.swing.JLabel lblAsignacion;
    private javax.swing.JLabel lblAsignacion2;
    private javax.swing.JLabel lblRevision;
    private javax.swing.JLabel lblRevision2;
    private org.edisoncor.gui.panel.PanelImage panelImage10;
    private org.edisoncor.gui.panel.PanelImage panelImage11;
    private org.edisoncor.gui.panel.PanelImage panelImageAsignacion;
    private org.edisoncor.gui.panel.PanelImage panelImageCentral;
    private org.edisoncor.gui.panel.PanelImage panelImageLogo;
    private org.edisoncor.gui.panel.PanelImage panelImageRevision;
    // End of variables declaration//GEN-END:variables
}
