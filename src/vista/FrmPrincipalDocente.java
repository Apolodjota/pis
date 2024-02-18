package vista;

import controlador.TDALista.LinkedList;
import controladores.CursaController;
import controladores.DocenteControlador;
import controladores.MateriaControllerListas;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Cursa;
import modelo.Docente;

/**
 *
 * @author alexg
 */
public class FrmPrincipalDocente extends javax.swing.JFrame {
    
    DocenteControlador dc = new DocenteControlador();
    private Docente docenteLogeado = new Docente();
    CursaController cursaC = new CursaController();
    public LinkedList<Cursa> cursasDocente = new LinkedList<>();
    MateriaControllerListas mac = new MateriaControllerListas();
    
    
    
    /**
     * Creates new form FrmPrincipalEstudiante
     */
    public FrmPrincipalDocente() {
        initComponents();
        setLocationRelativeTo(null);
        cargarImagenes();
        jplInicio.setEnabled(false);
        /*jplCurso2.setVisible(false);
        jplCurso3.setVisible(false);
        jplCurso4.setVisible(false);
        jplCurso5.setVisible(false);
        jplCurso6.setVisible(false);
        jplCurso7.setVisible(false);
        jplCurso8.setVisible(false);
        //jPanel3.setSize(197, 642);
        //docenteLogeado = dc.buscarDocente(id_docente);*/
        cargarDatos();
    }
    public FrmPrincipalDocente(Integer id_docente) {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarImagenes();
        jplInicio.setEnabled(false);
        /*jplCurso2.setVisible(false);
        jplCurso3.setVisible(false);
        jplCurso4.setVisible(false);
        jplCurso5.setVisible(false);
        jplCurso6.setVisible(false);
        jplCurso7.setVisible(false);
        jplCurso8.setVisible(false);*/
        //jPanel3.setSize(197, 642);
        docenteLogeado = dc.buscarDocente(id_docente);
        cargarDatos();
    }
    private void cargarImagenes(){
        //panelImage2.setIcon(new ImageIcon("src/icono/docentes.png"));
        panelImage4.setIcon(new ImageIcon("src/icono/estudiantes.png"));
        //panelImage5.setIcon(new ImageIcon("src/icono/materias.png"));
        panelImage6.setIcon(new ImageIcon("src/icono/asignaronline.png"));
        //panelImage7.setIcon(new ImageIcon("src/icono/malla.png"));
        //panelImage8.setIcon(new ImageIcon("src/icono/matricula.png"));
        //panelImage3.setIcon(new ImageIcon("src/icono/periodoAcademico.png"));
        //panelImage9.setIcon(new ImageIcon("src/icono/usuario_prin.png"));
        panelImage11.setIcon(new ImageIcon("src/icono/salir.png"));
        //panelImage12.setIcon(new ImageIcon("src/icono/cursa.png"));
        //panelImage1.setIcon(new ImageIcon("src/icono/logoEva_1.png"));
        panelImage10.setIcon(new ImageIcon("src/icono/inicio.png"));
        //jplCursa.setVisible(false);
        //jplMatricula.setVisible(false);
        //jplMalla.setVisible(false);
        //jplPeriodoAcademico.setVisible(false);
    }
    
    private void cargarDatos() {
        try {
            Frame frmactual = this;
            jblNombre.setText(docenteLogeado.getNombres() + " " + docenteLogeado.getApellidos());
            cursasDocente = cursaC.listarCursosDocente(docenteLogeado.getId());
            if (cursasDocente.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No encuentra impartiendo ninguna clase", "Atención", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Integer yinicial = 90;
                for (int i = 0; i < cursasDocente.getSize(); i++) {
                    JLabel lbl = new javax.swing.JLabel();
                    lbl.setLocation(50, yinicial);
                    lbl.setBackground(new java.awt.Color(204, 204, 255));
                    lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    lbl.setFont(new java.awt.Font("Times New Roman", 1, 14));
                    lbl.setSize(400, 36);
                    lbl.setText(mac.buscarMateria(cursasDocente.get(i).getId_materia()).toString() + " "
                            + cursasDocente.get(i).getParalelo());
//                    Integer id_cursa = cursasEstudiante.get(i).getId();
//                    lbl.addMouseListener(new MouseListener() {
//                        @Override
//                        public void mouseClicked(MouseEvent e) {
//                            try {
//                                jplInicio.setEnabled(true);
//                                jplUnidad1.setVisible(true);
//                                jplUnidad2.setVisible(true);
//                                jplUnidad3.setVisible(true);
//                                FrmInfoCursa infoCursa = new FrmInfoCursa(frmactual, true, id_cursa);
//                                JPanel panelCursa = infoCursa.getJPanel1();
//                                panelPrincipal.removeAll();
//                                panelPrincipal.add(panelCursa);
//                                panelPrincipal.revalidate();
//                                panelPrincipal.repaint();
//                            } catch (Exception ex) {
//                                System.out.println("Error en el mouse event de: " + lbl.getText());
//                            }
//                        }
//                    });
//                    panelCursas.add(lbl);
//                    labelsCursas.add(lbl);
//                    lbl.setVisible(true);
//                    yinicial += 50;
                }
            }
        } catch (Exception e) {
            System.out.println("Error cargando los cursas: " + e);
        }
    }

    public void mouseClicked(MouseEvent evento) {
        try {
//            for (int i = 0; i < labelsCursas.getSize(); i++) {
//                if (evento.getSource() == labelsCursas.get(0)) {
//                    ejemplo.setText("Clic en label: " + i + ", Cursa: " + cursasEstudiante.get(i).getId_materia().toString());
//                }
//            }
        } catch (Exception e) {
            System.out.println("Error agregando eventos mouse: " + e);
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

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        jplSalir = new javax.swing.JPanel();
        panelImage11 = new org.edisoncor.gui.panel.PanelImage();
        jLabel14 = new javax.swing.JLabel();
        jblNombre = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jplInicio = new javax.swing.JPanel();
        panelImage10 = new org.edisoncor.gui.panel.PanelImage();
        jLabel16 = new javax.swing.JLabel();
        panelImage4 = new org.edisoncor.gui.panel.PanelImage();
        jPanel5 = new javax.swing.JPanel();
        panelImage6 = new org.edisoncor.gui.panel.PanelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Docente");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1371, 717));

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));

        jplSalir.setBackground(new java.awt.Color(255, 204, 0));
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
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jplSalir.add(jLabel14, gridBagConstraints);

        jblNombre.setFont(new java.awt.Font("Harlow Solid Italic", 1, 18)); // NOI18N
        jblNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jblNombre.setText("Docente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jplSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jplSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jplInicio.setBackground(new java.awt.Color(255, 255, 255));
        jplInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jplInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplInicioMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelImage10Layout = new javax.swing.GroupLayout(panelImage10);
        panelImage10.setLayout(panelImage10Layout);
        panelImage10Layout.setHorizontalGroup(
            panelImage10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panelImage10Layout.setVerticalGroup(
            panelImage10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Inicio");

        javax.swing.GroupLayout jplInicioLayout = new javax.swing.GroupLayout(jplInicio);
        jplInicio.setLayout(jplInicioLayout);
        jplInicioLayout.setHorizontalGroup(
            jplInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplInicioLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(panelImage10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jplInicioLayout.setVerticalGroup(
            jplInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplInicioLayout.createSequentialGroup()
                .addGroup(jplInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplInicioLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel16))
                    .addGroup(jplInicioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelImage10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        jPanel3.add(jplInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 200, 50));

        javax.swing.GroupLayout panelImage4Layout = new javax.swing.GroupLayout(panelImage4);
        panelImage4.setLayout(panelImage4Layout);
        panelImage4Layout.setHorizontalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panelImage4Layout.setVerticalGroup(
            panelImage4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jPanel3.add(panelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1140, 642));
        jPanel5.setRequestFocusEnabled(false);
        jPanel5.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout panelImage6Layout = new javax.swing.GroupLayout(panelImage6);
        panelImage6.setLayout(panelImage6Layout);
        panelImage6Layout.setHorizontalGroup(
            panelImage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
        );
        panelImage6Layout.setVerticalGroup(
            panelImage6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 595;
        gridBagConstraints.ipady = 592;
        gridBagConstraints.insets = new java.awt.Insets(50, 50, 50, 50);
        jPanel5.add(panelImage6, gridBagConstraints);

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jplSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplSalirMouseClicked
        new FrmIniciarSesion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jplSalirMouseClicked

    private void jplInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplInicioMouseClicked
//        isInicioClicked = true;
//        jplCurso1.setVisible(false);
//
//        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7, jLabel8, jLabel9, jLabel12, jLabel13, jLabel4, jLabel5, jLabel15};
//        for (JLabel label : labelsBlancos) {
//            label.setForeground(Color.black);
//        }
//
//        JPanel[] panelsCianOscuro = {jplCurso2, jplCurso3, jplCurso4, jplCurso5, jplCurso6, jplMatricula, jplPeriodoAcademico};
//        for (JPanel panel : panelsCianOscuro) {
//            panel.setBackground(Color.WHITE);
//        }
//        jplCurso1.setBackground(new Color(255, 204, 0));
//        jLabel16.setForeground(Color.black);
//
//        jPanel5.removeAll();
//        jPanel5.add(panelImage1);
//        jPanel5.revalidate();
//        jPanel5.repaint();
    }//GEN-LAST:event_jplInicioMouseClicked

    private void jplInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplInicioMouseEntered
//        if (!isInicioClicked) {
//            jplCurso1.setBackground(new Color(255, 204, 0));
//            jLabel16.setForeground(Color.black);
//        } else {
//
//        }
    }//GEN-LAST:event_jplInicioMouseEntered

    private void jplInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplInicioMouseExited
//        if (!isInicioClicked) {
//            jplCurso1.setBackground(Color.WHITE);
//            jLabel16.setForeground(Color.black);
//        }
    }//GEN-LAST:event_jplInicioMouseExited

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
            java.util.logging.Logger.getLogger(FrmPrincipalDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalDocente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipalDocente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jblNombre;
    private javax.swing.JPanel jplInicio;
    private javax.swing.JPanel jplSalir;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage panelImage10;
    private org.edisoncor.gui.panel.PanelImage panelImage11;
    private org.edisoncor.gui.panel.PanelImage panelImage4;
    private org.edisoncor.gui.panel.PanelImage panelImage6;
    // End of variables declaration//GEN-END:variables
}
