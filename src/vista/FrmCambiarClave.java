/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controladores.AdministradorControlador;
import controladores.CuentaControllerListas;
import controladores.RolControllerListas;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Integer band = 0;
    private Integer aux = -1;

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
        txtClave2.setVisible(false);
        panelImage13.setVisible(false);
        txtClave4.setVisible(false);
        panelImage15.setVisible(false);       
        cambiarVisibilidadF();
    }
    
    private void cambiarVisibilidadT(){
        txtClave1.setVisible(true);
        txtClave2.setVisible(true);
        txtClave4.setVisible(true);
        j1.setVisible(true);
        jLabel8.setVisible(true);
        jLabel9.setVisible(true);
        txtClaveActual.setVisible(true);
        txtClaveNueva.setVisible(true);
        txtClaveConfrimar.setVisible(true); //false
        panelImage10.setVisible(true);
        panelImage11.setVisible(true);
        panelImage12.setVisible(true);
        panelImage13.setVisible(true);
        panelImage14.setVisible(true);
        panelImage15.setVisible(true);
    }
    
    private void cambiarVisibilidadF(){
        j1.setVisible(false);
        jLabel8.setVisible(false);
        jLabel9.setVisible(false);
        txtClaveActual.setVisible(false);
        txtClaveNueva.setVisible(false);
        txtClaveConfrimar.setVisible(false);
        panelImage10.setVisible(false);
        panelImage11.setVisible(false);
        panelImage12.setVisible(false);
        panelImage13.setVisible(false);
        panelImage14.setVisible(false);
        panelImage15.setVisible(false);
    }
    
    private Integer comprobarCorreo() throws Exception{
        for (int i = 0; i < rc.getCuentas().getSize(); i++){
           if(txtCorreo.getText().toString().equals(rc.getCuentas().get(i).getCorreo())){
               cambiarVisibilidadT();
               band = 1;
               return i;
           } 
        } 
        return -1;
    }
    
    private void darValores(){
        char[] password = txtClaveActual.getPassword();
        String passwordStr = new String(password);
        txtClave1.setText(passwordStr);
        password = txtClave2.getPassword();
        passwordStr = new String(password);
        txtClaveNueva.setText(passwordStr);
        password = txtClaveConfrimar.getPassword();
        passwordStr = new String(password);       
        txtClave4.setText(passwordStr);
    }
    
    private void cambiarClave(Integer index) throws Exception{
        darValores();
        if (txtClave1.getText().toString().equals(rc.getCuentas().get(index).getClave())){
            if (txtClaveNueva.getText().toString().equals(txtClave4.getText().toString())){
                rc.getCuentas().get(index).setClave(txtClaveNueva.getText().toString());
                JOptionPane.showMessageDialog(null, "Cambio exitoso");
                rc.setCuenta(rc.getCuentas().get(index));
                rc.update(rc.getCuenta().getId());
            } else {
                JOptionPane.showMessageDialog(null, "La confirmacion de contraseñas no es correcta, intentelo nuevamente");               
            }
           
       } else {
           JOptionPane.showMessageDialog(null, "La contraseña ingresada no coincide con el correo, intentelo nuevamente");
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
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        j1 = new javax.swing.JLabel();
        txtClaveActual = new javax.swing.JPasswordField();
        buttonColoredActionGuardar = new org.edisoncor.gui.button.ButtonColoredAction();
        panelImage9 = new org.edisoncor.gui.panel.PanelImage();
        txtClave1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtClave2 = new javax.swing.JPasswordField();
        panelImage12 = new org.edisoncor.gui.panel.PanelImage();
        txtClaveNueva = new javax.swing.JTextField();
        panelImage13 = new org.edisoncor.gui.panel.PanelImage();
        panelImage10 = new org.edisoncor.gui.panel.PanelImage();
        panelImage11 = new org.edisoncor.gui.panel.PanelImage();
        jLabel9 = new javax.swing.JLabel();
        txtClaveConfrimar = new javax.swing.JPasswordField();
        panelImage14 = new org.edisoncor.gui.panel.PanelImage();
        txtClave4 = new javax.swing.JTextField();
        panelImage15 = new org.edisoncor.gui.panel.PanelImage();
        jplSalir = new javax.swing.JPanel();
        panelImage16 = new org.edisoncor.gui.panel.PanelImage();
        jLabel14 = new javax.swing.JLabel();
        buttonColoredActionGuardar1 = new org.edisoncor.gui.button.ButtonColoredAction();

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
        gridBagConstraints.insets = new java.awt.Insets(110, 0, 0, 0);
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

        j1.setFont(new java.awt.Font("Harlow Solid Italic", 1, 18)); // NOI18N
        j1.setForeground(new java.awt.Color(255, 255, 255));
        j1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        j1.setText("Contraseña actual:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelImage1.add(j1, gridBagConstraints);

        txtClaveActual.setForeground(java.awt.Color.lightGray);
        txtClaveActual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClaveActual.setBorder(null);
        txtClaveActual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtClaveActualMousePressed(evt);
            }
        });
        txtClaveActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActualActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 12;
        panelImage1.add(txtClaveActual, gridBagConstraints);

        buttonColoredActionGuardar.setBackground(new java.awt.Color(0, 0, 255));
        buttonColoredActionGuardar.setText("Aceptar");
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
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 50, 50);
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
        gridBagConstraints.insets = new java.awt.Insets(30, 30, 30, 30);
        panelImage1.add(panelImage9, gridBagConstraints);

        txtClave1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtClave1.setForeground(java.awt.Color.lightGray);
        txtClave1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClave1.setBorder(null);
        txtClave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClave1ActionPerformed(evt);
            }
        });
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(140, 230, 0, 0);
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

        txtClave2.setForeground(java.awt.Color.lightGray);
        txtClave2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

        txtClaveNueva.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtClaveNueva.setForeground(java.awt.Color.lightGray);
        txtClaveNueva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClaveNueva.setBorder(null);
        txtClaveNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveNuevaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 12;
        panelImage1.add(txtClaveNueva, gridBagConstraints);

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

        txtClaveConfrimar.setForeground(java.awt.Color.lightGray);
        txtClaveConfrimar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtClaveConfrimar.setBorder(null);
        txtClaveConfrimar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtClaveConfrimarMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 12;
        panelImage1.add(txtClaveConfrimar, gridBagConstraints);

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
        txtClave4.setText("blabla");
        txtClave4.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 258;
        gridBagConstraints.ipady = 12;
        panelImage1.add(txtClave4, gridBagConstraints);

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
        jLabel14.setText("Cerrar Sesion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jplSalir.add(jLabel14, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelImage1.add(jplSalir, gridBagConstraints);

        buttonColoredActionGuardar1.setBackground(new java.awt.Color(0, 0, 255));
        buttonColoredActionGuardar1.setText("Guardar cambios");
        buttonColoredActionGuardar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonColoredActionGuardar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonColoredActionGuardar1MouseExited(evt);
            }
        });
        buttonColoredActionGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonColoredActionGuardar1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 46;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 50, 50);
        panelImage1.add(buttonColoredActionGuardar1, gridBagConstraints);

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

//        if (String.valueOf(txtClaveActual.getPassword()).isEmpty()) {
//            txtClaveActual.setText("");
//            txtClaveActual.setForeground(Color.lightGray);
//        }
//
//        if (String.valueOf(txtClave2.getPassword()).isEmpty()) {
//            txtClave2.setText("");
//            txtClave2.setForeground(Color.lightGray);
//        }
    }//GEN-LAST:event_txtCorreoMousePressed

    private void buttonColoredActionGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonColoredActionGuardarMouseEntered
        buttonColoredActionGuardar.setForeground(Color.YELLOW);
    }//GEN-LAST:event_buttonColoredActionGuardarMouseEntered

    private void buttonColoredActionGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonColoredActionGuardarMouseExited

    }//GEN-LAST:event_buttonColoredActionGuardarMouseExited

    private void buttonColoredActionGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonColoredActionGuardarActionPerformed
        try {
            if (band == 0) {
                aux = comprobarCorreo();
            } else if (band == 1) {
                if (aux > -1) {
                    cambiarClave(aux);
                } else {
                    JOptionPane.showMessageDialog(null, "El correo ingresado no existe en el registro de usuarios, verifique que sea correcto");
                }
            }
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
        }
    }//GEN-LAST:event_buttonColoredActionGuardarActionPerformed

    private void txtClave2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClave2MousePressed
        if (txtCorreo.getText().isEmpty()) {
            txtCorreo.setText("Ingrese su correo institucional");
            txtCorreo.setForeground(Color.lightGray);
        }
    }//GEN-LAST:event_txtClave2MousePressed

    private void panelImage11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage11MouseClicked
        panelImage10.setVisible(false);
        txtClaveActual.setText(txtClave1.getText());
        txtClaveActual.setVisible(true);
        panelImage11.setVisible(false);
        panelImage10.setVisible(true);
    }//GEN-LAST:event_panelImage11MouseClicked

    private void panelImage10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage10MouseClicked
        char[] password = txtClaveActual.getPassword();
        String passwordStr = new String(password);
        txtClaveActual.setVisible(false);
        txtClave1.setVisible(true);
        txtClave1.setText(passwordStr);
        txtClave1.setForeground(Color.blue);
        panelImage10.setVisible(false);
        panelImage11.setVisible(true);
    }//GEN-LAST:event_panelImage10MouseClicked

    private void panelImage13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage13MouseClicked
        panelImage12.setVisible(false);
        txtClave2.setText(txtClaveNueva.getText());
        txtClave2.setVisible(true);
        panelImage13.setVisible(false);
       panelImage12.setVisible(true);
    }//GEN-LAST:event_panelImage13MouseClicked

    private void panelImage12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage12MouseClicked
        char[] password = txtClave2.getPassword();
        String passwordStr = new String(password);
        txtClave2.setVisible(false);
        txtClaveNueva.setVisible(true);
        txtClaveNueva.setText(passwordStr);
        txtClaveNueva.setForeground(Color.blue);
        panelImage12.setVisible(false);
        panelImage13.setVisible(true);
        
    }//GEN-LAST:event_panelImage12MouseClicked

    private void txtClaveConfrimarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClaveConfrimarMousePressed

        if (txtCorreo.getText().isEmpty()) {
            txtCorreo.setText("Ingrese su correo institucional");
            txtCorreo.setForeground(Color.lightGray);
        }

    }//GEN-LAST:event_txtClaveConfrimarMousePressed

    private void panelImage14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage14MouseClicked
        char[] password = txtClaveConfrimar.getPassword();
        String passwordStr = new String(password);
        txtClaveConfrimar.setVisible(false);
        txtClave4.setText(passwordStr);
        txtClave4.setVisible(true);
        txtClave4.setForeground(Color.blue);
        panelImage14.setVisible(false);
        panelImage15.setVisible(true);

    }//GEN-LAST:event_panelImage14MouseClicked

    private void panelImage15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelImage15MouseClicked
        panelImage14.setVisible(false);
        txtClaveConfrimar.setText(txtClave4.getText());
        txtClaveConfrimar.setVisible(true);
        panelImage15.setVisible(false);
        panelImage14.setVisible(true);
    }//GEN-LAST:event_panelImage15MouseClicked

    private void jplSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplSalirMouseClicked
        new FrmIniciarSesion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jplSalirMouseClicked

    private void txtClaveActualMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtClaveActualMousePressed
        if (txtCorreo.getText().isEmpty()) {
            txtCorreo.setText("Ingrese su correo institucional");
            txtCorreo.setForeground(Color.lightGray);
        }
    }//GEN-LAST:event_txtClaveActualMousePressed

    private void txtClaveActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActualActionPerformed

    private void txtClave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClave1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClave1ActionPerformed

    private void txtClaveNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveNuevaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveNuevaActionPerformed

    private void buttonColoredActionGuardar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonColoredActionGuardar1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonColoredActionGuardar1MouseEntered

    private void buttonColoredActionGuardar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonColoredActionGuardar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonColoredActionGuardar1MouseExited

    private void buttonColoredActionGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonColoredActionGuardar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonColoredActionGuardar1ActionPerformed

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
    private org.edisoncor.gui.button.ButtonColoredAction buttonColoredActionGuardar1;
    private javax.swing.JLabel j1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JTextField txtClave1;
    private javax.swing.JPasswordField txtClave2;
    private javax.swing.JTextField txtClave4;
    private javax.swing.JPasswordField txtClaveActual;
    private javax.swing.JPasswordField txtClaveConfrimar;
    private javax.swing.JTextField txtClaveNueva;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
