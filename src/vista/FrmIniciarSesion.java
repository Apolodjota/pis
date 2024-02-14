package vista;

import controladores.AdministradorControlador;
import controladores.RolControllerListas;
import controladores.CuentaControllerListas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Persona;

/**
 *
 * @author alexg
 */
public class FrmIniciarSesion extends javax.swing.JFrame {

    private CuentaControllerListas rc = new CuentaControllerListas();
    private AdministradorControlador ac = new AdministradorControlador();
    private RolControllerListas rl = new RolControllerListas();
    private Persona persona = new Persona();
//    FondoPanel fondo = new FondoPanel();

    /**
     * Creates new form FrmPrincipal
     */
    public FrmIniciarSesion() {
//        this.setContentPane(fondo);
        initComponents();
        this.setLocationRelativeTo(null);
        panelImage1.setIcon(new ImageIcon("src/icono/fondo_l1.png"));
        panelImage9.setIcon(new ImageIcon("src/icono/avatar-de-usuario.png"));
        panelImage10.setIcon(new ImageIcon("src/icono/ver.png"));
        panelImage11.setIcon(new ImageIcon("src/icono/ojo.png"));
        txtClave1.setVisible(false);
        panelImage11.setVisible(false);
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
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        buttonColoredAction1 = new org.edisoncor.gui.button.ButtonColoredAction();
        panelImage9 = new org.edisoncor.gui.panel.PanelImage();
        panelImage10 = new org.edisoncor.gui.panel.PanelImage();
        txtClave1 = new javax.swing.JTextField();
        panelImage11 = new org.edisoncor.gui.panel.PanelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Iniciar Sesion");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("INICIAR SESIÓN");
        panelImage1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 142, -1, -1));

        jLabel4.setFont(new java.awt.Font("Harlow Solid Italic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Correo Institucional:");
        panelImage1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 281, 610, -1));

        txtCorreo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtCorreo.setForeground(java.awt.Color.lightGray);
        txtCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCorreo.setText("Ingrese su correo institucional");
        txtCorreo.setBorder(null);
        txtCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCorreoMousePressed(evt);
            }
        });
        panelImage1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 310, 322, 33));

        jLabel5.setFont(new java.awt.Font("Harlow Solid Italic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Contraseña:");
        panelImage1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 380, 101, -1));

        txtClave.setForeground(java.awt.Color.lightGray);
        txtClave.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClave.setText("**********************");
        txtClave.setBorder(null);
        txtClave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtClaveMousePressed(evt);
            }
        });
        panelImage1.add(txtClave, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 409, 322, 28));

        buttonColoredAction1.setBackground(new java.awt.Color(0, 0, 255));
        buttonColoredAction1.setText("Ingresar");
        buttonColoredAction1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonColoredAction1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonColoredAction1MouseExited(evt);
            }
        });
        buttonColoredAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonColoredAction1ActionPerformed(evt);
            }
        });
        panelImage1.add(buttonColoredAction1, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 481, 162, -1));

        javax.swing.GroupLayout panelImage9Layout = new javax.swing.GroupLayout(panelImage9);
        panelImage9.setLayout(panelImage9Layout);
        panelImage9Layout.setHorizontalGroup(
            panelImage9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );
        panelImage9Layout.setVerticalGroup(
            panelImage9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 186, -1, -1));

        panelImage10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelImage10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelImage10Layout = new javax.swing.GroupLayout(panelImage10);
        panelImage10.setLayout(panelImage10Layout);
        panelImage10Layout.setHorizontalGroup(
            panelImage10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        panelImage10Layout.setVerticalGroup(
            panelImage10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 409, -1, 28));

        txtClave1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtClave1.setForeground(java.awt.Color.lightGray);
        txtClave1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClave1.setText("Ingrese su correo institucional");
        txtClave1.setBorder(null);
        panelImage1.add(txtClave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 410, 322, 28));

        panelImage11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelImage11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelImage11Layout = new javax.swing.GroupLayout(panelImage11);
        panelImage11.setLayout(panelImage11Layout);
        panelImage11Layout.setHorizontalGroup(
            panelImage11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        panelImage11Layout.setVerticalGroup(
            panelImage11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        panelImage1.add(panelImage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 409, -1, 28));

        jPanel1.add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonColoredAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonColoredAction1ActionPerformed
        String correo = txtCorreo.getText();
        String clave = String.valueOf(txtClave.getPassword());
        try {
            Integer idCuentaCorreo = rc.obtenerIdPorCorreo(rc.getCuentas(), correo);
            Integer idCuentaClave = rc.obtenerIdPorClave(rc.getCuentas(), clave);
            if (idCuentaCorreo == idCuentaClave && idCuentaCorreo != -1) {
//                Integer idRol = rc.obtenerIdPorRol(rc.getCuentas(), idCuentaCorreo);
//                if (idRol == 1) {
//                    new FrmPrincipalAdministrador().setVisible(true);
//                    this.setVisible(false);
//                } else if (idRol == 2) {
//                    new FrmPrincipalDocente().setVisible(true);
//                    this.setVisible(false);
//                } else if (idRol == 3) {
//                    new FrmPrincipalEstudiante().setVisible(true);
//                    this.setVisible(false);
//                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Credenciales invalidas",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_buttonColoredAction1ActionPerformed

    private void buttonColoredAction1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonColoredAction1MouseExited
        buttonColoredAction1.setForeground(Color.white);
    }//GEN-LAST:event_buttonColoredAction1MouseExited

    private void buttonColoredAction1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonColoredAction1MouseEntered
        buttonColoredAction1.setForeground(Color.YELLOW);
    }//GEN-LAST:event_buttonColoredAction1MouseEntered

    private void txtClaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClaveMousePressed
        if (String.valueOf(txtClave.getPassword()).equals("**********************")) {
            txtClave.setText("");
            txtClave.setForeground(Color.blue);
        }

        if (txtCorreo.getText().isEmpty()) {
            txtCorreo.setText("Ingrese su correo institucional");
            txtCorreo.setForeground(Color.lightGray);
        }
    }//GEN-LAST:event_txtClaveMousePressed

    private void txtCorreoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCorreoMousePressed
        if (txtCorreo.getText().equals("Ingrese su correo institucional")) {
            txtCorreo.setText("");
            txtCorreo.setForeground(Color.blue);
        }

        if (String.valueOf(txtClave.getPassword()).isEmpty()) {
            txtClave.setText("**********************");
            txtClave.setForeground(Color.lightGray);
        }
    }//GEN-LAST:event_txtCorreoMousePressed

    private void panelImage10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage10MouseClicked
        char[] password = txtClave.getPassword();
        String passwordStr = new String(password);
        if (String.valueOf(txtClave.getPassword()).equals("**********************")) {
            txtClave.setText("**********************");
            txtClave.setForeground(Color.lightGray);
        } else {
            txtClave.setVisible(false);
            txtClave1.setVisible(true);
            txtClave1.setText(passwordStr);
            txtClave1.setForeground(Color.blue);
            panelImage10.setVisible(false);
            panelImage11.setVisible(true);
        }
    }//GEN-LAST:event_panelImage10MouseClicked

    private void panelImage11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage11MouseClicked
        panelImage10.setVisible(false);
        txtClave.setText(txtClave1.getText());
        txtClave.setVisible(true);
        panelImage11.setVisible(false);
        panelImage10.setVisible(true);
    }//GEN-LAST:event_panelImage11MouseClicked

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
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmIniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmIniciarSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonColoredAction buttonColoredAction1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage10;
    private org.edisoncor.gui.panel.PanelImage panelImage11;
    private org.edisoncor.gui.panel.PanelImage panelImage9;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtClave1;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables

    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/icono/fondo_l1.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }

    }
}
