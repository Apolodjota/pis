package vista;
import controlador.TDALista.LinkedList;
import controlador.TDALista.exceptions.VacioException;
import controladores.AsignacionController;
import controladores.CuentaControllerListas;
import controladores.CursaController;
import controladores.EstudianteControlador;
import controladores.MateriaControllerListas;
import controladores.MatriculaController;
import controladores.PersonaController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Asignacion;
import modelo.Cursa;
import modelo.Estudiante;

/**
 *
 * @author alexg
 */
public class FrmPrincipalEstudiante1 extends javax.swing.JFrame {

    private CuentaControllerListas cc = new CuentaControllerListas();
    private Boolean isDocentesClicked = false;
    private Boolean isEstudiantesClicked = false;
    private Boolean isMateriasClicked = false;
    private Boolean isCursoClicked = false;
    private Boolean isMallaClicked = false;
    private Boolean isMatriculaClicked = false;
    private Boolean isPeriodoAcademicoClicked = false;
    private Boolean isCursaClicked = false;
    private Boolean isInicioClicked = false;
    private Integer posCursaActual = -1;
    private Estudiante estudianteLogeado = new Estudiante();
    public LinkedList<Cursa> cursasEstudiante = new LinkedList<>();
    private LinkedList<JLabel> labelsCursas = new LinkedList<>();
    AsignacionController asc = new AsignacionController();
    EstudianteControlador ec = new EstudianteControlador();
    PersonaController perc = new PersonaController();
    CursaController cursac = new CursaController();
    MateriaControllerListas mac = new MateriaControllerListas();
    MatriculaController mtrc = new MatriculaController();
    private LinkedList<Asignacion> asignacionesCursa = new LinkedList<>();
    private JPanel panelMAIN = new JPanel();
    private Cursa cursaActual = new Cursa();
    FrmInfoCursa infoCursa;

    /**
     * Creates new form FrmPrincipalAdministrador
     */
    public FrmPrincipalEstudiante1(String nombre, String apellido) {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarImagenes();
        jplInicio.setVisible(false);
        jblNombre.setText(nombre + " " + apellido);
    }

    public FrmPrincipalEstudiante1(Integer id_estudiante) {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarImagenes();
        jplInicio.setEnabled(false);
        jplUnidad1.setVisible(false);
        jplUnidad2.setVisible(false);
        jplUnidad3.setVisible(false);
        estudianteLogeado = ec.buscarEstudiante(id_estudiante);
        cargarDatos();
    }

    private void cargarImagenes() {
        panelImage2.setIcon(new ImageIcon("src/icono/docentes.png"));
        panelImage4.setIcon(new ImageIcon("src/icono/estudiantes.png"));
        panelImage5.setIcon(new ImageIcon("src/icono/materias.png"));
        //panelImage7.setIcon(new ImageIcon("src/icono/malla.png"));
        //panelImage8.setIcon(new ImageIcon("src/icono/matricula.png"));
        //panelImage3.setIcon(new ImageIcon("src/icono/periodoAcademico.png"));
        panelImage9.setIcon(new ImageIcon("src/icono/usuario_prin.png"));
        panelImage11.setIcon(new ImageIcon("src/icono/salir.png"));
        //panelImage12.setIcon(new ImageIcon("src/icono/cursa.png"));
        //panelImage1.setIcon(new ImageIcon("src/icono/logoEva_1.png"));
        panelImage10.setIcon(new ImageIcon("src/icono/inicio.png"));
        jplCursa.setVisible(false);
        jplMatricula.setVisible(false);
        jplMalla.setVisible(false);
        jplPeriodoAcademico.setVisible(false);

    }

    private void cargarDatos() {
        try {
            Frame frmactual = this;
            //lblnombres.setText(estudianteLogeado.toString());
            jblNombre.setText(estudianteLogeado.getNombres() + " " + estudianteLogeado.getApellidos());
            cursasEstudiante = cursac.listarCursasMatricula(mtrc.obtenerMatriculaActual(estudianteLogeado.getId()).getId());
            if (cursasEstudiante.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No encuentra cursando asignaturas", "Atención", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int yinicial = 100;
                for (int i = 0; i < cursasEstudiante.getSize(); i++) {
                    JLabel lbl = new javax.swing.JLabel();
                    lbl.setName(String.valueOf(i));
                    lbl.setLocation(100, yinicial);
                    lbl.setBackground(new java.awt.Color(204, 204, 255));
                    lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    lbl.setFont(new java.awt.Font("Times New Roman", 1, 14));
                    lbl.setSize(400, 36);
                    lbl.setText(mac.buscarMateria(cursasEstudiante.get(i).getId_materia()).toString() + " "
                            + cursasEstudiante.get(i).getParalelo());
                    cursaActual = cursasEstudiante.get(i);
                    Integer id_cursa = cursasEstudiante.get(i).getId();
                    String nombreCursa = lbl.getText();
                    String nombreDocente = perc.buscar(cursaActual.getId_docente()).toString();
                    //posCursaActual = Integer.valueOf(lbl.getName());
                    lbl.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                jplInicio.setEnabled(true);
                                jplUnidad1.setVisible(true);
                                jplUnidad2.setVisible(true);
                                jplUnidad3.setVisible(true);
                                //cursaActual = cursasEstudiante.get(0);
                                //FrmInfoCursa infoCursa = new FrmInfoCursa(frmactual, true, id_cursa);
                                infoCursa = new FrmInfoCursa(frmactual, true, id_cursa);
                                infoCursa.getTxtcursa().setText(nombreCursa);
                                infoCursa.getTxtdocente().setText(nombreDocente);
                                //asignacionesCursa = cursac.asignacionesdeCursa(id_cursa);
                                JPanel panelCursa = infoCursa.getJPanel1();
                                panelPrincipal.removeAll();
                                panelPrincipal.add(panelCursa);
                                panelPrincipal.revalidate();
                                panelPrincipal.repaint();
                            } catch (Exception ex) {
                                System.out.println("Error en el mouse event de: " + lbl.getText());
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                        }
                    });
                    panelCursas.add(lbl);
                    labelsCursas.add(lbl);
                    lbl.setVisible(true);
                    yinicial += 50;
                }
            }
        } catch (Exception e) {
            System.out.println("Error cargando los cursas: " + e);
        }
    }

//    public void mouseClicked(MouseEvent evento) {
//        try {
//            for (int i = 0; i < labelsCursas.getSize(); i++) {
//                if (evento.getSource() == labelsCursas.get(0)) {
//                    ejemplo.setText("Clic en label: " + i + ", Cursa: " + cursasEstudiante.get(i).getId_materia().toString());
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error agregando eventos mouse: " + e);
//        }
//
//    }

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
        jblNombre = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jplCursa = new javax.swing.JPanel();
        panelImage12 = new org.edisoncor.gui.panel.PanelImage();
        jLabel15 = new javax.swing.JLabel();
        jplUnidad1 = new javax.swing.JPanel();
        panelImage2 = new org.edisoncor.gui.panel.PanelImage();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jplUnidad2 = new javax.swing.JPanel();
        panelImage4 = new org.edisoncor.gui.panel.PanelImage();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jplUnidad3 = new javax.swing.JPanel();
        panelImage5 = new org.edisoncor.gui.panel.PanelImage();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jplMalla = new javax.swing.JPanel();
        panelImage7 = new org.edisoncor.gui.panel.PanelImage();
        jLabel12 = new javax.swing.JLabel();
        jplMatricula = new javax.swing.JPanel();
        panelImage8 = new org.edisoncor.gui.panel.PanelImage();
        jLabel13 = new javax.swing.JLabel();
        jplPeriodoAcademico = new javax.swing.JPanel();
        panelImage3 = new org.edisoncor.gui.panel.PanelImage();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jplInicio = new javax.swing.JPanel();
        panelImage10 = new org.edisoncor.gui.panel.PanelImage();
        jLabel16 = new javax.swing.JLabel();
        panelPrincipal = new javax.swing.JPanel();
        panelCursas = new javax.swing.JPanel();
        panelImage9 = new org.edisoncor.gui.panel.PanelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        jPanel1.setBackground(new java.awt.Color(255, 204, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));

        jplSalir.setBackground(new java.awt.Color(255, 204, 0));
        jplSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        jblNombre.setText("Estudiante");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 542, Short.MAX_VALUE)
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
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jplCursa.setBackground(new java.awt.Color(255, 255, 255));
        jplCursa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jplCursa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplCursaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplCursaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplCursaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelImage12Layout = new javax.swing.GroupLayout(panelImage12);
        panelImage12.setLayout(panelImage12Layout);
        panelImage12Layout.setHorizontalGroup(
            panelImage12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panelImage12Layout.setVerticalGroup(
            panelImage12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Cursa");

        javax.swing.GroupLayout jplCursaLayout = new javax.swing.GroupLayout(jplCursa);
        jplCursa.setLayout(jplCursaLayout);
        jplCursaLayout.setHorizontalGroup(
            jplCursaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplCursaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(panelImage12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jplCursaLayout.setVerticalGroup(
            jplCursaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplCursaLayout.createSequentialGroup()
                .addGroup(jplCursaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplCursaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel15))
                    .addGroup(jplCursaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelImage12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.ipady = -6;
        jPanel3.add(jplCursa, gridBagConstraints);

        jplUnidad1.setBackground(new java.awt.Color(255, 255, 255));
        jplUnidad1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jplUnidad1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplUnidad1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplUnidad1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplUnidad1MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelImage2Layout = new javax.swing.GroupLayout(panelImage2);
        panelImage2.setLayout(panelImage2Layout);
        panelImage2Layout.setHorizontalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panelImage2Layout.setVerticalGroup(
            panelImage2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("UNIDAD");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("I");

        javax.swing.GroupLayout jplUnidad1Layout = new javax.swing.GroupLayout(jplUnidad1);
        jplUnidad1.setLayout(jplUnidad1Layout);
        jplUnidad1Layout.setHorizontalGroup(
            jplUnidad1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidad1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jplUnidad1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)))
        );
        jplUnidad1Layout.setVerticalGroup(
            jplUnidad1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidad1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jplUnidad1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplUnidad1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3))
                    .addComponent(jLabel2)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 7;
        jPanel3.add(jplUnidad1, gridBagConstraints);

        jplUnidad2.setBackground(new java.awt.Color(255, 255, 255));
        jplUnidad2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jplUnidad2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplUnidad2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplUnidad2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplUnidad2MouseExited(evt);
            }
        });

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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("UNIDAD");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("II");

        javax.swing.GroupLayout jplUnidad2Layout = new javax.swing.GroupLayout(jplUnidad2);
        jplUnidad2.setLayout(jplUnidad2Layout);
        jplUnidad2Layout.setHorizontalGroup(
            jplUnidad2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidad2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(panelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jplUnidad2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)))
        );
        jplUnidad2Layout.setVerticalGroup(
            jplUnidad2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidad2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jplUnidad2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplUnidad2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7))
                    .addComponent(jLabel6)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 7;
        jPanel3.add(jplUnidad2, gridBagConstraints);

        jplUnidad3.setBackground(new java.awt.Color(255, 255, 255));
        jplUnidad3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jplUnidad3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplUnidad3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplUnidad3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplUnidad3MouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelImage5Layout = new javax.swing.GroupLayout(panelImage5);
        panelImage5.setLayout(panelImage5Layout);
        panelImage5Layout.setHorizontalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panelImage5Layout.setVerticalGroup(
            panelImage5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("UNIDAD");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("III");

        javax.swing.GroupLayout jplUnidad3Layout = new javax.swing.GroupLayout(jplUnidad3);
        jplUnidad3.setLayout(jplUnidad3Layout);
        jplUnidad3Layout.setHorizontalGroup(
            jplUnidad3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidad3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(panelImage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jplUnidad3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)))
        );
        jplUnidad3Layout.setVerticalGroup(
            jplUnidad3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidad3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jplUnidad3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(jplUnidad3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel9))))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 7;
        jPanel3.add(jplUnidad3, gridBagConstraints);

        jplMalla.setBackground(new java.awt.Color(255, 255, 255));
        jplMalla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jplMalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplMallaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplMallaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplMallaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelImage7Layout = new javax.swing.GroupLayout(panelImage7);
        panelImage7.setLayout(panelImage7Layout);
        panelImage7Layout.setHorizontalGroup(
            panelImage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panelImage7Layout.setVerticalGroup(
            panelImage7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Malla");

        javax.swing.GroupLayout jplMallaLayout = new javax.swing.GroupLayout(jplMalla);
        jplMalla.setLayout(jplMallaLayout);
        jplMallaLayout.setHorizontalGroup(
            jplMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplMallaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(panelImage7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jplMallaLayout.setVerticalGroup(
            jplMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplMallaLayout.createSequentialGroup()
                .addGroup(jplMallaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jplMallaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel12))
                    .addGroup(jplMallaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelImage7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.ipady = -6;
        jPanel3.add(jplMalla, gridBagConstraints);

        jplMatricula.setBackground(new java.awt.Color(255, 255, 255));
        jplMatricula.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jplMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplMatriculaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplMatriculaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplMatriculaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelImage8Layout = new javax.swing.GroupLayout(panelImage8);
        panelImage8.setLayout(panelImage8Layout);
        panelImage8Layout.setHorizontalGroup(
            panelImage8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panelImage8Layout.setVerticalGroup(
            panelImage8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Matricula");

        javax.swing.GroupLayout jplMatriculaLayout = new javax.swing.GroupLayout(jplMatricula);
        jplMatricula.setLayout(jplMatriculaLayout);
        jplMatriculaLayout.setHorizontalGroup(
            jplMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplMatriculaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(panelImage8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel13)
                .addContainerGap())
        );
        jplMatriculaLayout.setVerticalGroup(
            jplMatriculaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplMatriculaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13)
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplMatriculaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelImage8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 40;
        jPanel3.add(jplMatricula, gridBagConstraints);

        jplPeriodoAcademico.setBackground(new java.awt.Color(255, 255, 255));
        jplPeriodoAcademico.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jplPeriodoAcademico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplPeriodoAcademicoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplPeriodoAcademicoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplPeriodoAcademicoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelImage3Layout = new javax.swing.GroupLayout(panelImage3);
        panelImage3.setLayout(panelImage3Layout);
        panelImage3Layout.setHorizontalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        panelImage3Layout.setVerticalGroup(
            panelImage3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Periodo");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Academico");

        javax.swing.GroupLayout jplPeriodoAcademicoLayout = new javax.swing.GroupLayout(jplPeriodoAcademico);
        jplPeriodoAcademico.setLayout(jplPeriodoAcademicoLayout);
        jplPeriodoAcademicoLayout.setHorizontalGroup(
            jplPeriodoAcademicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoAcademicoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(panelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jplPeriodoAcademicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)))
        );
        jplPeriodoAcademicoLayout.setVerticalGroup(
            jplPeriodoAcademicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplPeriodoAcademicoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jplPeriodoAcademicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplPeriodoAcademicoLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5))
                    .addComponent(jLabel4)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.ipadx = 34;
        gridBagConstraints.ipady = 7;
        jPanel3.add(jplPeriodoAcademico, gridBagConstraints);

        jplInicio.setBackground(new java.awt.Color(255, 255, 255));
        jplInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.ipady = -6;
        jPanel3.add(jplInicio, gridBagConstraints);

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "MIS CURSOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1140, 642));
        panelPrincipal.setRequestFocusEnabled(false);

        ejemplo.setText("XDDDD");

        javax.swing.GroupLayout panelCursasLayout = new javax.swing.GroupLayout(panelCursas);
        panelCursas.setLayout(panelCursasLayout);
        panelCursasLayout.setHorizontalGroup(
            panelCursasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCursasLayout.setVerticalGroup(
            panelCursasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(ejemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(601, Short.MAX_VALUE))
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelCursas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                .addComponent(panelCursas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(ejemplo)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout panelImage9Layout = new javax.swing.GroupLayout(panelImage9);
        panelImage9.setLayout(panelImage9Layout);
        panelImage9Layout.setHorizontalGroup(
            panelImage9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );
        panelImage9Layout.setVerticalGroup(
            panelImage9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(225, Short.MAX_VALUE)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(panelImage9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(panelImage9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void jplUnidad1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad1MouseClicked
        /*isDocentesClicked = true;
        jplInicio.setVisible(true);

        jplInicio.setBackground(Color.white);
        jplUnidad2.setBackground(Color.white);
        jplUnidad3.setBackground(Color.white);
        jplCursa.setBackground(Color.white);
        jplMalla.setBackground(Color.white);
        jplMatricula.setBackground(Color.white);
        jplPeriodoAcademico.setBackground(Color.white);

        JLabel[] labelsBlancos = {jLabel6, jLabel7, jLabel8, jLabel9, jLabel16, jLabel12, jLabel13, jLabel4, jLabel5, jLabel15};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplInicio, jplUnidad2, jplUnidad3, jplMalla, jplMatricula, jplPeriodoAcademico, jplCursa};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }
        jplUnidad1.setBackground(new Color(255, 204, 0));
        jLabel2.setForeground(Color.black);
        jLabel3.setForeground(Color.black);

        FrmDocente frmDocente = new FrmDocente();
        JPanel panelDocente = frmDocente.getJPanel1();
        panelPrincipal.removeAll();
        panelPrincipal.add(panelDocente);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();*/
        infoCursa.getPanelAsignaciones().setVisible(true);
        infoCursa.cargarTabla(asc.asignacionesCursaUnidad(infoCursa.getCursaActual().getId(), 1));
    }//GEN-LAST:event_jplUnidad1MouseClicked

    private void jplUnidad2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad2MouseClicked
        /*isEstudiantesClicked = true;
        jplInicio.setVisible(true);

        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel8, jLabel9, jLabel16, jLabel12, jLabel13, jLabel4, jLabel5, jLabel15};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplInicio, jplUnidad1, jplUnidad3, jplMalla, jplMatricula, jplPeriodoAcademico, jplCursa};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }
        jplUnidad2.setBackground(new Color(255, 204, 0));
        jLabel6.setForeground(Color.black);
        jLabel7.setForeground(Color.black);

        FrmEstudiante frmEstudiante = new FrmEstudiante();
        JPanel panelDocente = frmEstudiante.getJPanel1();
        panelPrincipal.removeAll();
        panelPrincipal.add(panelDocente);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();*/
        infoCursa.getPanelAsignaciones().setVisible(true);
        infoCursa.cargarTabla(asc.asignacionesCursaUnidad(infoCursa.getCursaActual().getId(), 2));
    }//GEN-LAST:event_jplUnidad2MouseClicked

    private void jplUnidad3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad3MouseClicked
        /*isMateriasClicked = true;
        jplInicio.setVisible(true);

        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7, jLabel16, jLabel12, jLabel13, jLabel4, jLabel5, jLabel15};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplUnidad1, jplUnidad2, jplMalla, jplInicio, jplMatricula, jplPeriodoAcademico, jplCursa};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }
        jplUnidad3.setBackground(new Color(255, 204, 0));
        jLabel8.setForeground(Color.black);
        jLabel9.setForeground(Color.black);

        FrmMateria frmMateria = new FrmMateria();
        JPanel panelMateria = frmMateria.getJPanel1();
        panelPrincipal.removeAll();
        panelPrincipal.add(panelMateria);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();*/
        infoCursa.getPanelAsignaciones().setVisible(true);
        infoCursa.cargarTabla(asc.asignacionesCursaUnidad(infoCursa.getCursaActual().getId(), 3));
    }//GEN-LAST:event_jplUnidad3MouseClicked

    private void jplSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplSalirMouseClicked
        new FrmIniciarSesion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jplSalirMouseClicked

    private void jplUnidad1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad1MouseEntered
        if (!isDocentesClicked) {
            jplUnidad1.setBackground(new Color(255, 204, 0));
            jLabel2.setForeground(Color.black);
            jLabel3.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad1MouseEntered

    private void jplUnidad1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad1MouseExited
        if (!isDocentesClicked) {
            jplUnidad1.setBackground(Color.WHITE);
            jLabel2.setForeground(Color.black);
            jLabel3.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad1MouseExited

    private void jplUnidad2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad2MouseEntered
        if (!isEstudiantesClicked) {
            jplUnidad2.setBackground(new Color(255, 204, 0));
            jLabel6.setForeground(Color.black);
            jLabel7.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad2MouseEntered

    private void jplUnidad2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad2MouseExited
        if (!isEstudiantesClicked) {
            jplUnidad2.setBackground(Color.WHITE);
            jLabel6.setForeground(Color.black);
            jLabel7.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad2MouseExited

    private void jplUnidad3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad3MouseEntered
        if (!isMateriasClicked) {
            jplUnidad3.setBackground(new Color(255, 204, 0));
            jLabel8.setForeground(Color.black);
            jLabel9.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad3MouseEntered

    private void jplUnidad3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad3MouseExited
        if (!isMateriasClicked) {
            jplUnidad3.setBackground(Color.WHITE);
            jLabel8.setForeground(Color.black);
            jLabel9.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad3MouseExited

    private void jplInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplInicioMouseClicked
        isInicioClicked = true;
        //jplInicio.setVisible(false);
        jplInicio.setEnabled(false);
        jplUnidad1.setVisible(false);
        jplUnidad2.setVisible(false);
        jplUnidad3.setVisible(false);

        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7, jLabel8, jLabel9, jLabel12, jLabel13, jLabel4, jLabel5, jLabel15};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplUnidad1, jplUnidad2, jplUnidad3, jplCursa, jplMalla, jplMatricula, jplPeriodoAcademico};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }
        jplInicio.setBackground(new Color(255, 204, 0));
        jLabel16.setForeground(Color.black);

        panelPrincipal.removeAll();
        panelPrincipal.add(panelCursas);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }//GEN-LAST:event_jplInicioMouseClicked

    private void jplInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplInicioMouseEntered
        if (!isInicioClicked) {
            jplInicio.setBackground(new Color(255, 204, 0));
            jLabel16.setForeground(Color.black);
        } else {

        }
    }//GEN-LAST:event_jplInicioMouseEntered

    private void jplInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplInicioMouseExited
        if (!isInicioClicked) {
            jplInicio.setBackground(Color.WHITE);
            jLabel16.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplInicioMouseExited

    private void jplPeriodoAcademicoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplPeriodoAcademicoMouseExited
        if (!isPeriodoAcademicoClicked) {
            jplPeriodoAcademico.setBackground(Color.WHITE);
            jLabel4.setForeground(Color.black);
            jLabel5.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplPeriodoAcademicoMouseExited

    private void jplPeriodoAcademicoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplPeriodoAcademicoMouseEntered
        if (!isPeriodoAcademicoClicked) {
            jplPeriodoAcademico.setBackground(new Color(255, 204, 0));
            jLabel4.setForeground(Color.black);
            jLabel5.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplPeriodoAcademicoMouseEntered

    private void jplPeriodoAcademicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplPeriodoAcademicoMouseClicked
        isPeriodoAcademicoClicked = true;
        jplInicio.setVisible(true);

        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7, jLabel8, jLabel9, jLabel16, jLabel12, jLabel13, jLabel15};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplMatricula, jplMalla, jplCursa, jplUnidad3, jplUnidad2, jplUnidad1, jplInicio};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }

        jplPeriodoAcademico.setBackground(new Color(255, 204, 0));
        jLabel4.setForeground(Color.black);
        jLabel5.setForeground(Color.black);

        FrmPeriodoAcademico frmPeriodo = new FrmPeriodoAcademico(null, true);
        JPanel panelDocente = frmPeriodo.getJPanel1();
        panelPrincipal.removeAll();
        panelPrincipal.add(panelDocente);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }//GEN-LAST:event_jplPeriodoAcademicoMouseClicked

    private void jplMatriculaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplMatriculaMouseExited
        if (!isMatriculaClicked) {
            jplMatricula.setBackground(Color.WHITE);
            jLabel13.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplMatriculaMouseExited

    private void jplMatriculaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplMatriculaMouseEntered
        if (!isMatriculaClicked) {
            jplMatricula.setBackground(new Color(255, 204, 0));
            jLabel13.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplMatriculaMouseEntered

    private void jplMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplMatriculaMouseClicked
        isMatriculaClicked = true;
        jplInicio.setVisible(true);

        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7, jLabel8, jLabel9, jLabel16, jLabel12, jLabel4, jLabel5, jLabel15};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplUnidad1, jplUnidad2, jplUnidad3, jplInicio, jplMalla, jplPeriodoAcademico, jplCursa};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }
        jplMatricula.setBackground(new Color(255, 204, 0));
        jLabel13.setForeground(Color.black);

        FrmMatricula frmMatricula = new FrmMatricula();
        JPanel panelDocente = frmMatricula.getJPanel1();
        panelPrincipal.removeAll();
        panelPrincipal.add(panelDocente);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }//GEN-LAST:event_jplMatriculaMouseClicked

    private void jplMallaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplMallaMouseExited
        if (!isMallaClicked) {
            jplMalla.setBackground(Color.WHITE);
            jLabel12.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplMallaMouseExited

    private void jplMallaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplMallaMouseEntered
        if (!isMallaClicked) {
            jplMalla.setBackground(new Color(255, 204, 0));
            jLabel12.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplMallaMouseEntered

    private void jplMallaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplMallaMouseClicked
        isMallaClicked = true;
        jplInicio.setVisible(true);

        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7, jLabel8, jLabel9, jLabel16, jLabel13, jLabel4, jLabel5, jLabel15};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplUnidad1, jplUnidad2, jplUnidad3, jplInicio, jplMatricula, jplPeriodoAcademico, jplCursa};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }
        jplMalla.setBackground(new Color(255, 204, 0));
        jLabel12.setForeground(Color.black);

        FrmMalla frmMalla = new FrmMalla();
        JPanel panelMalla = frmMalla.getJPanel1();
        panelPrincipal.removeAll();
        panelPrincipal.add(panelMalla);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }//GEN-LAST:event_jplMallaMouseClicked

    private void jplCursaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplCursaMouseExited
        if (!isCursaClicked) {
            jplCursa.setBackground(Color.WHITE);
            jLabel15.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplCursaMouseExited

    private void jplCursaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplCursaMouseEntered
        if (!isCursaClicked) {
            jplCursa.setBackground(new Color(255, 204, 0));
            jLabel15.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplCursaMouseEntered

    private void jplCursaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplCursaMouseClicked
        isCursaClicked = true;
        jplInicio.setVisible(true);

        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7, jLabel8, jLabel9, jLabel12, jLabel13, jLabel4, jLabel5, jLabel16};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplUnidad1, jplUnidad2, jplUnidad3, jplMalla, jplMatricula, jplPeriodoAcademico, jplInicio};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }

        jplCursa.setBackground(new Color(255, 204, 0));
        jLabel15.setForeground(Color.black);

        FrmCursa frmCursa = new FrmCursa();
        JPanel panelCursa = frmCursa.getJPanel1();
        panelPrincipal.removeAll();
        panelPrincipal.add(panelCursa);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }//GEN-LAST:event_jplCursaMouseClicked

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
            java.util.logging.Logger.getLogger(FrmPrincipalEstudiante1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalEstudiante1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalEstudiante1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipalEstudiante1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String nombre = null;
                String apellidos = null;
                new FrmPrincipalEstudiante1(nombre, apellidos).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public final javax.swing.JLabel ejemplo = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jblNombre;
    private javax.swing.JPanel jplCursa;
    private javax.swing.JPanel jplInicio;
    private javax.swing.JPanel jplMalla;
    private javax.swing.JPanel jplMatricula;
    private javax.swing.JPanel jplPeriodoAcademico;
    private javax.swing.JPanel jplSalir;
    private javax.swing.JPanel jplUnidad1;
    private javax.swing.JPanel jplUnidad2;
    private javax.swing.JPanel jplUnidad3;
    private javax.swing.JPanel panelCursas;
    private org.edisoncor.gui.panel.PanelImage panelImage10;
    private org.edisoncor.gui.panel.PanelImage panelImage11;
    private org.edisoncor.gui.panel.PanelImage panelImage12;
    private org.edisoncor.gui.panel.PanelImage panelImage2;
    private org.edisoncor.gui.panel.PanelImage panelImage3;
    private org.edisoncor.gui.panel.PanelImage panelImage4;
    private org.edisoncor.gui.panel.PanelImage panelImage5;
    private org.edisoncor.gui.panel.PanelImage panelImage7;
    private org.edisoncor.gui.panel.PanelImage panelImage8;
    private org.edisoncor.gui.panel.PanelImage panelImage9;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
