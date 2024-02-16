/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controladores.AdministradorControlador;
import controladores.CuentaControllerListas;
import controladores.RolControllerListas;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Persona;

/**
 *
 * @author alexg
 */
public class FrmCambiarClave extends javax.swing.JFrame {

    private CuentaControllerListas rc = new CuentaControllerListas();
    private Persona persona = new Persona();

    /**
     * Creates new form FrmCambiarClave
     */
    public FrmCambiarClave() {
        initComponents();
        this.setLocationRelativeTo(null);
        panelImage1.setIcon(new ImageIcon("src/icono/fondo_l1.png"));
        panelImage9.setIcon(new ImageIcon("src/icono/avatar-de-usuario.png"));
        panelImage10.setIcon(new ImageIcon("src/icono/ver.png"));
        panelImage11.setIcon(new ImageIcon("src/icono/ojo.png"));
        panelImage12.setIcon(new ImageIcon("src/icono/ver.png"));
        panelImage13.setIcon(new ImageIcon("src/icono/ojo.png"));
        panelImage14.setIcon(new ImageIcon("src/icono/ver.png"));
        panelImage15.setIcon(new ImageIcon("src/icono/ojo.png"));
        panelImage16.setIcon(new ImageIcon("src/icono/salir.png"));
        txtClave1.setVisible(false);
        panelImage11.setVisible(false);
        txtClave3.setVisible(false);
        panelImage13.setVisible(false);
        txtClave4.setVisible(false);
        panelImage15.setVisible(false);
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
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        buttonColoredActionGuardar = new org.edisoncor.gui.button.ButtonColoredAction();
        panelImage9 = new org.edisoncor.gui.panel.PanelImage();
        txtClave1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelImage12 = new org.edisoncor.gui.panel.PanelImage();
        panelImage13 = new org.edisoncor.gui.panel.PanelImage();
        txtClave2 = new javax.swing.JPasswordField();
        panelImage10 = new org.edisoncor.gui.panel.PanelImage();
        panelImage11 = new org.edisoncor.gui.panel.PanelImage();
        txtClave3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtClave5 = new javax.swing.JPasswordField();
        panelImage15 = new org.edisoncor.gui.panel.PanelImage();
        panelImage14 = new org.edisoncor.gui.panel.PanelImage();
        txtClave4 = new javax.swing.JTextField();
        jplSalir = new javax.swing.JPanel();
        panelImage16 = new org.edisoncor.gui.panel.PanelImage();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Configuracion de cuenta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        panelImage1.add(jLabel6, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Harlow Solid Italic", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ingrese su correo Institucional:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 369;
        panelImage1.add(jLabel4, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 17;
        panelImage1.add(txtCorreo, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Harlow Solid Italic", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Contraseña actual:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelImage1.add(jLabel5, gridBagConstraints);

        txtClave.setForeground(java.awt.Color.lightGray);
        txtClave.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClave.setText("**********************");
        txtClave.setBorder(null);
        txtClave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtClaveMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 12;
        panelImage1.add(txtClave, gridBagConstraints);

        buttonColoredActionGuardar.setBackground(new java.awt.Color(0, 0, 255));
        buttonColoredActionGuardar.setText("Guardar cambios");
        buttonColoredActionGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonColoredActionGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonColoredActionGuardarMouseExited(evt);
            }
        });
        buttonColoredActionGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonColoredActionGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 46;
        gridBagConstraints.insets = new java.awt.Insets(50, 20, 20, 20);
        panelImage1.add(buttonColoredActionGuardar, gridBagConstraints);

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 82;
        gridBagConstraints.ipady = 82;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelImage1.add(panelImage9, gridBagConstraints);

        txtClave1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtClave1.setForeground(java.awt.Color.lightGray);
        txtClave1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClave1.setText("Ingrese su correo institucional");
        txtClave1.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 12;
        panelImage1.add(txtClave1, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Ebrima", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("de usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        panelImage1.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Harlow Solid Italic", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Nueva contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelImage1.add(jLabel8, gridBagConstraints);

        panelImage12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelImage12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelImage12Layout = new javax.swing.GroupLayout(panelImage12);
        panelImage12.setLayout(panelImage12Layout);
        panelImage12Layout.setHorizontalGroup(
            panelImage12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelImage12Layout.setVerticalGroup(
            panelImage12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 0, 140);
        panelImage1.add(panelImage12, gridBagConstraints);

        panelImage13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelImage13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelImage13Layout = new javax.swing.GroupLayout(panelImage13);
        panelImage13.setLayout(panelImage13Layout);
        panelImage13Layout.setHorizontalGroup(
            panelImage13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelImage13Layout.setVerticalGroup(
            panelImage13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 0, 140);
        panelImage1.add(panelImage13, gridBagConstraints);

        txtClave2.setForeground(java.awt.Color.lightGray);
        txtClave2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClave2.setText("**********************");
        txtClave2.setBorder(null);
        txtClave2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtClave2MousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 12;
        panelImage1.add(txtClave2, gridBagConstraints);

        panelImage10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelImage10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelImage10Layout = new javax.swing.GroupLayout(panelImage10);
        panelImage10.setLayout(panelImage10Layout);
        panelImage10Layout.setHorizontalGroup(
            panelImage10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelImage10Layout.setVerticalGroup(
            panelImage10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 0, 140);
        panelImage1.add(panelImage10, gridBagConstraints);

        panelImage11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelImage11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelImage11Layout = new javax.swing.GroupLayout(panelImage11);
        panelImage11.setLayout(panelImage11Layout);
        panelImage11Layout.setHorizontalGroup(
            panelImage11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelImage11Layout.setVerticalGroup(
            panelImage11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 0, 140);
        panelImage1.add(panelImage11, gridBagConstraints);

        txtClave3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtClave3.setForeground(java.awt.Color.lightGray);
        txtClave3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClave3.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 12;
        panelImage1.add(txtClave3, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Harlow Solid Italic", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Repetir nueva contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelImage1.add(jLabel9, gridBagConstraints);

        txtClave5.setForeground(java.awt.Color.lightGray);
        txtClave5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClave5.setText("**********************");
        txtClave5.setBorder(null);
        txtClave5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtClave5MousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 12;
        panelImage1.add(txtClave5, gridBagConstraints);

        panelImage15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelImage15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelImage15Layout = new javax.swing.GroupLayout(panelImage15);
        panelImage15.setLayout(panelImage15Layout);
        panelImage15Layout.setHorizontalGroup(
            panelImage15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        panelImage15Layout.setVerticalGroup(
            panelImage15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 0, 140);
        panelImage1.add(panelImage15, gridBagConstraints);

        panelImage14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelImage14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelImage14Layout = new javax.swing.GroupLayout(panelImage14);
        panelImage14.setLayout(panelImage14Layout);
        panelImage14Layout.setHorizontalGroup(
            panelImage14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        panelImage14Layout.setVerticalGroup(
            panelImage14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 0, 140);
        panelImage1.add(panelImage14, gridBagConstraints);

        txtClave4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtClave4.setForeground(java.awt.Color.lightGray);
        txtClave4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClave4.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 12;
        panelImage1.add(txtClave4, gridBagConstraints);

        jplSalir.setBackground(new java.awt.Color(255, 204, 0));
        jplSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jplSalir.setPreferredSize(new java.awt.Dimension(167, 70));
        jplSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplSalirMouseClicked(evt);
            }
        });
        jplSalir.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout panelImage16Layout = new javax.swing.GroupLayout(panelImage16);
        panelImage16.setLayout(panelImage16Layout);
        panelImage16Layout.setHorizontalGroup(
            panelImage16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );
        panelImage16Layout.setVerticalGroup(
            panelImage16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 35;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jplSalir.add(panelImage16, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Regresar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jplSalir.add(jLabel14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        panelImage1.add(jplSalir, gridBagConstraints);

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

    private void txtCorreoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCorreoMousePressed
        if (txtCorreo.getText().equals("Ingrese su correo institucional")) {
            txtCorreo.setText("");
            txtCorreo.setForeground(Color.blue);
        }

        if (String.valueOf(txtClave.getPassword()).isEmpty()) {
            txtClave.setText("**********************");
            txtClave.setForeground(Color.lightGray);
        }

        if (String.valueOf(txtClave2.getPassword()).isEmpty()) {
            txtClave2.setText("**********************");
            txtClave2.setForeground(Color.lightGray);
        }
        
        if (String.valueOf(txtClave5.getPassword()).isEmpty()) {
            txtClave5.setText("**********************");
            txtClave5.setForeground(Color.lightGray);
        }
    }//GEN-LAST:event_txtCorreoMousePressed

    private void txtClaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClaveMousePressed
        if (String.valueOf(txtClave.getPassword()).equals("**********************")) {
            txtClave.setText("");
            txtClave.setForeground(Color.blue);
        }

        if (txtCorreo.getText().isEmpty()) {
            txtCorreo.setText("Ingrese su correo institucional");
            txtCorreo.setForeground(Color.lightGray);
        }

        if (String.valueOf(txtClave2.getPassword()).isEmpty()) {
            txtClave2.setText("**********************");
            txtClave2.setForeground(Color.lightGray);
        }

        if (String.valueOf(txtClave5.getPassword()).isEmpty()) {
            txtClave5.setText("**********************");
            txtClave5.setForeground(Color.lightGray);
        }
    }//GEN-LAST:event_txtClaveMousePressed

    private void buttonColoredActionGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonColoredActionGuardarMouseEntered
        buttonColoredActionGuardar.setForeground(Color.YELLOW);
    }//GEN-LAST:event_buttonColoredActionGuardarMouseEntered

    private void buttonColoredActionGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonColoredActionGuardarMouseExited
        buttonColoredActionGuardar.setForeground(Color.white);
        String correo = txtCorreo.getText();
        String clave = String.valueOf(txtClave.getPassword());
        try {
            Integer idCuentaCorreo = rc.obtenerIdPorCorreo(rc.getCuentas(), correo);
            System.out.println("ID cuenta correo: " + idCuentaCorreo);
            Integer idCuentaClave = rc.obtenerIdPorClave(rc.getCuentas(), clave);
            System.out.println("ID cuenta clave: " + idCuentaClave);
            if (idCuentaCorreo == idCuentaClave && idCuentaCorreo != -1) {
                Integer idPersona = rc.getCuentas().get(idCuentaCorreo).getId_persona();
                Integer idRol = persona.getId_rol();
                if (idRol == 1) {
                    new FrmPrincipalAdministrador().setVisible(true);
                    this.setVisible(false);
                } else if (idRol == 2) {
                    new FrmMainDocente().setVisible(true);
                    this.setVisible(false);
                } else if (idRol == 3) {
                    new FrmPrincipalEstudiante().setVisible(true);
                    this.setVisible(false);
                }
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
    }//GEN-LAST:event_buttonColoredActionGuardarMouseExited

    private void buttonColoredActionGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonColoredActionGuardarActionPerformed

    }//GEN-LAST:event_buttonColoredActionGuardarActionPerformed

    private void txtClave2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClave2MousePressed
        if (String.valueOf(txtClave2.getPassword()).equals("**********************")) {
            txtClave2.setText("");
            txtClave2.setForeground(Color.blue);
        }

        if (txtCorreo.getText().isEmpty()) {
            txtCorreo.setText("Ingrese su correo institucional");
            txtCorreo.setForeground(Color.lightGray);
        }

        if (String.valueOf(txtClave.getPassword()).isEmpty()) {
            txtClave.setText("**********************");
            txtClave.setForeground(Color.lightGray);
        }

        if (String.valueOf(txtClave5.getPassword()).isEmpty()) {
            txtClave5.setText("**********************");
            txtClave5.setForeground(Color.lightGray);
        }
    }//GEN-LAST:event_txtClave2MousePressed

    private void panelImage11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage11MouseClicked
        panelImage10.setVisible(false);
        txtClave.setText(txtClave1.getText());
        txtClave.setVisible(true);
        panelImage11.setVisible(false);
        panelImage10.setVisible(true);
    }//GEN-LAST:event_panelImage11MouseClicked

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

    private void panelImage13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage13MouseClicked
        panelImage12.setVisible(false);
        txtClave2.setText(txtClave3.getText());
        txtClave2.setVisible(true);
        panelImage13.setVisible(false);
        panelImage12.setVisible(true);
    }//GEN-LAST:event_panelImage13MouseClicked

    private void panelImage12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage12MouseClicked
        char[] password = txtClave2.getPassword();
        String passwordStr = new String(password);
        if (String.valueOf(txtClave2.getPassword()).equals("**********************")) {
            txtClave2.setText("**********************");
            txtClave2.setForeground(Color.lightGray);
        } else {
            txtClave2.setVisible(false);
            txtClave3.setVisible(true);
            txtClave3.setText(passwordStr);
            txtClave3.setForeground(Color.blue);
            panelImage12.setVisible(false);
            panelImage13.setVisible(true);
        }
    }//GEN-LAST:event_panelImage12MouseClicked

    private void txtClave5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClave5MousePressed
        if (String.valueOf(txtClave5.getPassword()).equals("**********************")) {
            txtClave5.setText("");
            txtClave5.setForeground(Color.blue);
        }

        if (txtCorreo.getText().isEmpty()) {
            txtCorreo.setText("Ingrese su correo institucional");
            txtCorreo.setForeground(Color.lightGray);
        }

        if (String.valueOf(txtClave.getPassword()).isEmpty()) {
            txtClave.setText("**********************");
            txtClave.setForeground(Color.lightGray);
        }

        if (String.valueOf(txtClave2.getPassword()).isEmpty()) {
            txtClave2.setText("**********************");
            txtClave2.setForeground(Color.lightGray);
        }
    }//GEN-LAST:event_txtClave5MousePressed

    private void panelImage14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage14MouseClicked
        char[] password = txtClave5.getPassword();
        String passwordStr = new String(password);
        if (String.valueOf(txtClave5.getPassword()).equals("**********************")) {
            txtClave5.setText("**********************");
            txtClave5.setForeground(Color.lightGray);
        } else {
            txtClave5.setVisible(false);
            txtClave4.setVisible(true);
            txtClave4.setText(passwordStr);
            txtClave4.setForeground(Color.blue);
            panelImage14.setVisible(false);
            panelImage15.setVisible(true);
        }
    }//GEN-LAST:event_panelImage14MouseClicked

    private void panelImage15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage15MouseClicked
        panelImage14.setVisible(false);
        txtClave5.setText(txtClave4.getText());
        txtClave5.setVisible(true);
        panelImage15.setVisible(false);
        panelImage14.setVisible(true);
    }//GEN-LAST:event_panelImage15MouseClicked

    private void jplSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplSalirMouseClicked
        new FrmIniciarSesion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jplSalirMouseClicked

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
            java.util.logging.Logger.getLogger(FrmCambiarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCambiarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCambiarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCambiarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCambiarClave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonColoredAction buttonColoredActionGuardar;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jplSalir;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage10;
    private org.edisoncor.gui.panel.PanelImage panelImage11;
    private org.edisoncor.gui.panel.PanelImage panelImage12;
    private org.edisoncor.gui.panel.PanelImage panelImage13;
    private org.edisoncor.gui.panel.PanelImage panelImage14;
    private org.edisoncor.gui.panel.PanelImage panelImage15;
    private org.edisoncor.gui.panel.PanelImage panelImage16;
    private org.edisoncor.gui.panel.PanelImage panelImage9;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtClave1;
    private javax.swing.JPasswordField txtClave2;
    private javax.swing.JTextField txtClave3;
    private javax.swing.JTextField txtClave4;
    private javax.swing.JPasswordField txtClave5;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
