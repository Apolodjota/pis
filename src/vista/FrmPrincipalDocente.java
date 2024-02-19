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
public class FrmPrincipalDocente extends javax.swing.JFrame implements FrmPrincipalDocenteGrande.ListenerVistas{
    Frame frameproveniente = null;
    DocenteControlador dc = new DocenteControlador();
    private Docente docenteLogeado = new Docente();
    CursaController cursaC = new CursaController();
    public LinkedList<Cursa> cursasDocente = new LinkedList<>();
    MateriaControllerListas mac = new MateriaControllerListas();
    Boolean isInicioClicked = null;
    

    /**
     * Creates new form FrmPrincipalEstudiante
     */
    public FrmPrincipalDocente() {
        initComponents();
        setLocationRelativeTo(null);
        cargarImagenes();
        jplInicio.setEnabled(false);
    }
    public FrmPrincipalDocente(Frame frame, Integer id_docente, String clicked) {
        initComponents();
        FrmPrincipalDocenteGrande.addEstadoChangeListener(this);
        this.setLocationRelativeTo(null);
        cargarImagenes();
        cargarDatos();
        frameproveniente = frame;
        docenteLogeado = dc.buscarDocente(id_docente);
        jplInicio.setEnabled(false);
        cargarDatos();
    }
    
    private void cargarImagenes(){
        ImagenInicio.setIcon(new ImageIcon("src/icono/inicio.png"));
        ImagenMain.setIcon(new ImageIcon("src/icono/comunidad.png"));
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }
    
    private void cargarDatos() {
        try {
            Frame frmactual = this;
            cursasDocente = cursaC.listarCursosDocente(docenteLogeado.getId());
            if (cursasDocente.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                        "No encuentra impartiendo ninguna clase", 
                        "Atención", 
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                Integer yinicial = 90;
                for (int i = 0; i < cursasDocente.getSize(); i++) {
                    JLabel lbl = new javax.swing.JLabel();
                    lbl.setLocation(50, yinicial);
                    lbl.setBackground(new java.awt.Color(60,63,65));
                    lbl.setForeground(new java.awt.Color(187, 187, 187));
                    lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    lbl.setFont(new java.awt.Font("Segoe UI", 2, 14));
                    lbl.setSize(250, 20);
                    lbl.setText(mac.buscarMateria(cursasDocente.get(i).getId_materia()).toString() + " "
                            + cursasDocente.get(i).getParalelo());
                    Integer id_cursa = cursasDocente.get(i).getId();
                    lbl.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                jplInicio.setEnabled(true);
                            } catch (Exception ex) {
                                System.out.println("Error en el mouse event de: " + lbl.getText());
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                        }
                    });
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jplInicio = new javax.swing.JPanel();
        ImagenInicio = new org.edisoncor.gui.panel.PanelImage();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        ImagenMain = new org.edisoncor.gui.panel.PanelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Docente");
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(1140, 642));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));
        jPanel3.setLayout(new java.awt.GridBagLayout());

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

        javax.swing.GroupLayout ImagenInicioLayout = new javax.swing.GroupLayout(ImagenInicio);
        ImagenInicio.setLayout(ImagenInicioLayout);
        ImagenInicioLayout.setHorizontalGroup(
            ImagenInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );
        ImagenInicioLayout.setVerticalGroup(
            ImagenInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Inicio");

        javax.swing.GroupLayout jplInicioLayout = new javax.swing.GroupLayout(jplInicio);
        jplInicio.setLayout(jplInicioLayout);
        jplInicioLayout.setHorizontalGroup(
            jplInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplInicioLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(ImagenInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jplInicioLayout.setVerticalGroup(
            jplInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jplInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ImagenInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(21, 21, 21))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.ipady = -6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 581, 6);
        jPanel3.add(jplInicio, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = -10;
        gridBagConstraints.ipady = 99;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1140, 642));
        jPanel5.setRequestFocusEnabled(false);
        jPanel5.setLayout(new java.awt.GridBagLayout());

        ImagenMain.setPreferredSize(new java.awt.Dimension(500, 450));

        javax.swing.GroupLayout ImagenMainLayout = new javax.swing.GroupLayout(ImagenMain);
        ImagenMain.setLayout(ImagenMainLayout);
        ImagenMainLayout.setHorizontalGroup(
            ImagenMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        ImagenMainLayout.setVerticalGroup(
            ImagenMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        jPanel5.add(ImagenMain, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 928;
        gridBagConstraints.ipady = 750;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
        jPanel1.add(jPanel5, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1140, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jplInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplInicioMouseExited
        //        if (!isInicioClicked) {
            //            jplCurso1.setBackground(Color.WHITE);
            //            jLabel16.setForeground(Color.black);
            //        }
    }//GEN-LAST:event_jplInicioMouseExited

    private void jplInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplInicioMouseEntered
        //        if (!isInicioClicked) {
            //            jplCurso1.setBackground(new Color(255, 204, 0));
            //            jLabel16.setForeground(Color.black);
            //        } else {
            //
            //        }
    }//GEN-LAST:event_jplInicioMouseEntered

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
    private org.edisoncor.gui.panel.PanelImage ImagenInicio;
    private org.edisoncor.gui.panel.PanelImage ImagenMain;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jplInicio;
    // End of variables declaration//GEN-END:variables

    @Override
    public void clicksCambio() {
        System.out.println("Funciona carajooooooo");
    }
}
