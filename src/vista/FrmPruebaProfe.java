package vista;
import controlador.TDALista.LinkedList;
import controladores.AsignacionController;
import controladores.CursaController;
import controladores.DocenteControlador;
import controladores.MateriaControllerListas;
import controladores.TareaController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Asignacion;
import modelo.Cursa;
import modelo.Docente;
import modelo.Tarea;
import vista.listas.tablas.ModeloTablaAsignacion;
import vista.listas.tablas.ModeloTablaAsignacionEstudiante;
import vista.listas.tablas.ModeloTablaTarea;
import vista.tablas.EstudianteModeloTabla;

/**
 *
 * @author alexg
 */
public class FrmPruebaProfe extends javax.swing.JFrame {
    DocenteControlador dc = new DocenteControlador();
    CursaController cursac = new CursaController();
    MateriaControllerListas mac = new MateriaControllerListas();
    AsignacionController asc = new AsignacionController();
    TareaController tarc = new TareaController();
    EstudianteModeloTabla emt = new EstudianteModeloTabla(new LinkedList<>());
    ModeloTablaTarea mtt = new ModeloTablaTarea();
    ModeloTablaAsignacion mtasig = new ModeloTablaAsignacion();
    ModeloTablaAsignacionEstudiante mtae = new ModeloTablaAsignacionEstudiante();
    private Docente docenteLogeado = new Docente();
    private Asignacion asignacion = new Asignacion();
    private Tarea tareaSeleccionada = new Tarea();
    private File archivoDocente;
    private File archivoEstudiante;
    public Boolean isInicioClicked = false;
    public Boolean isCreacionClicked = false;
    public Boolean isRevisionClicked = false;
    public Boolean isListadoClicked = false;
    public String clickeado= "";
    private LinkedList<Cursa> cursasDocente = new LinkedList<>();
    //Variables que cambian al entrar a un cursa:
    private String nombreCursa = "";
    private Integer id_materia;
    private String paralelo;
    private Integer unidadActual;
    /**
     * Creates new form FrmPrincipalAdministrador
     */
    public FrmPruebaProfe(Integer id_docente) {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarImagenes();
        //jplInicio.setVisible(false);
        docenteLogeado = dc.buscarDocente(id_docente);
        jblNombre.setText(docenteLogeado.getNombres() + " " + docenteLogeado.getApellidos());
        jplInicio.setEnabled(false);
        jplUnidad1.setVisible(false);
        jplUnidad2.setVisible(false);
        jplUnidad3.setVisible(false);
        cargarDatos();
    }

    private void cargarDatos(){
        try {
            cursasDocente = cursac.listarCursosDocente(docenteLogeado.getId());
            if (cursasDocente.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No encuentra impartiendo asignaturas", "Atención", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int yinicial = 100;
                for (int i = 0; i < cursasDocente.getSize(); i++) {
                    id_materia = cursasDocente.get(i).getId_materia();
                    paralelo = cursasDocente.get(i).getParalelo();
                    JLabel lbl = new javax.swing.JLabel();
                    lbl.setLocation(100, yinicial);
                    lbl.setBackground(new java.awt.Color(204, 204, 255));
                    lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    lbl.setFont(new java.awt.Font("Times New Roman", 1, 14));
                    lbl.setSize(400, 36);
                    lbl.setText(mac.buscarMateria(id_materia).toString() + " "
                            + paralelo);
                    //cursaActual = cursasEstudiante.get(i);
                    //Integer id_cursa = cursasDocente.get(i).getId();
                    nombreCursa = lbl.getText();
                    String nombreDocente = docenteLogeado.getNombres()+" "+docenteLogeado.getApellidos();
                    
                    //posCursaActual = Integer.valueOf(lbl.getName());
                    lbl.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                pestañas.setSelectedIndex(1);
                                emt.setEstudiantes(cursac.listarCursa_Participan(docenteLogeado.getId(), id_materia, paralelo));
                                tblParticipantes.setModel(emt);
                                tblParticipantes.updateUI();
                                txtcursa.setText(nombreCursa);
                                jplInicio.setEnabled(true);
                                jplUnidad1.setVisible(true);
                                jplUnidad2.setVisible(true);
                                jplUnidad3.setVisible(true);
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
                    pageIN.add(lbl);
                    //labelsCursas.add(lbl);
                    lbl.setVisible(true);
                    yinicial += 50;
                }
            }
        } catch (Exception e) {
        }
    }
    
    private String sacarClick(){
        return "Hola";
    }
    // Todo lo siguiente realiza un metodo para verificar el cambio de los atributos
    //public Boolean isInicioClicked = false;
    //public Boolean isCreacionClicked = false;
    //public Boolean isRevisionClicked = false;
    //public Boolean isListadoClicked = false;
    // Y que sean leidos en el Frame de FrmPrincipalDocente
    private static List<ListenerVistas> listeners = new ArrayList<>();

    public interface ListenerVistas {

        void clicksCambio();
    }

    // Método para añadir observadores
    public static void addEstadoChangeListener(ListenerVistas listener) {
        listeners.add(listener);
    }

    // Método para notificar a todos los observadores
    private static void notifyEstadoChange() {
        for (ListenerVistas listener : listeners) {
            listener.clicksCambio();
        }
    }

    // Modificar el setter de isInicioClicked para que notifique a los observadores
    public void setIsInicioClicked(Boolean isInicioClicked) {
        this.isInicioClicked = isInicioClicked;
        notifyEstadoChange();
    }
    public void setIsCreacionClicked(Boolean isCreacionClicked) {
        this.isCreacionClicked = isCreacionClicked;
        notifyEstadoChange();
    }
    public void setIsRevisionClicked(Boolean isRevisionClicked) {
        this.isRevisionClicked = isRevisionClicked;
        notifyEstadoChange();
    }
    public void setIsListadoClicked(Boolean isListadoClicked) {
        this.isListadoClicked = isListadoClicked;
        notifyEstadoChange();
    }

    private void cargarImagenes() {
        ImagenNombre.setIcon(new ImageIcon("src/icono/usuario_prin.png"));
        ImagenInicio.setIcon(new ImageIcon("src/icono/inicio.png"));
        ImagenCreacion.setIcon(new ImageIcon("src/icono/docentes.png"));
        ImagenRevision.setIcon(new ImageIcon("src/icono/estudiantes.png"));
        ImagenListado.setIcon(new ImageIcon("src/icono/materias.png"));
        ImagenSalir.setIcon(new ImageIcon("src/icono/salir.png"));
        //ImagenMain.setIcon(new ImageIcon("src/icono/logoEva_1.png"));

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
        ImagenSalir = new org.edisoncor.gui.panel.PanelImage();
        jLabel14 = new javax.swing.JLabel();
        jblNombre = new javax.swing.JLabel();
        ImagenNombre = new org.edisoncor.gui.panel.PanelImage();
        jPanel3 = new javax.swing.JPanel();
        jplUnidad1 = new javax.swing.JPanel();
        ImagenCreacion = new org.edisoncor.gui.panel.PanelImage();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jplUnidad2 = new javax.swing.JPanel();
        ImagenRevision = new org.edisoncor.gui.panel.PanelImage();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jplUnidad3 = new javax.swing.JPanel();
        ImagenListado = new org.edisoncor.gui.panel.PanelImage();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jplInicio = new javax.swing.JPanel();
        ImagenInicio = new org.edisoncor.gui.panel.PanelImage();
        jLabel16 = new javax.swing.JLabel();
        pestañas = new tabbed.MaterialTabbed();
        pageCursa = new javax.swing.JPanel();
        panelEstudiantes = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblParticipantes = new javax.swing.JTable();
        txtcursa = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        pageUno = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtcursa2 = new javax.swing.JTextField();
        panelAsignaciones2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbltareas = new javax.swing.JTable();
        lblnombreUnidad = new javax.swing.JLabel();
        buttonNuevaTarea = new org.edisoncor.gui.button.ButtonAero();
        buttonEditarTarea = new org.edisoncor.gui.button.ButtonAero();
        buttonVerAsignaciones = new org.edisoncor.gui.button.ButtonAero();
        pageDos = new javax.swing.JPanel();
        panelAsignaciones1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAsignacionesEstudiantes = new javax.swing.JTable();
        buttonAbrirAsignacion = new org.edisoncor.gui.button.ButtonAero();
        jLabel20 = new javax.swing.JLabel();
        txtcursa1 = new javax.swing.JTextField();
        pageTres = new javax.swing.JPanel();
        principalAsignacion = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtarchivoTarea = new javax.swing.JTextField();
        buttonSubir = new org.edisoncor.gui.button.ButtonAero();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txttitulo = new javax.swing.JTextField();
        txtfechaasignacion = new javax.swing.JTextField();
        panelAsigEstudiante = new javax.swing.JPanel();
        txttiemporestante = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtestadoasignacion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        panelArchivosEnviados = new javax.swing.JPanel();
        txtnomarchivo = new javax.swing.JTextField();
        buttonDescargar = new org.edisoncor.gui.button.ButtonAero();
        panelCalificacion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtcomentario = new javax.swing.JTextPane();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtcalificacion = new javax.swing.JTextPane();
        jLabel22 = new javax.swing.JLabel();
        dtcFin = new com.toedter.calendar.JDateChooser();
        spnhora = new javax.swing.JSpinner();
        spnminutos = new javax.swing.JSpinner();
        buttonGuardarySalir = new org.edisoncor.gui.button.ButtonAero();
        buttonCancelar = new org.edisoncor.gui.button.ButtonAero();
        pageIN = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
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

        javax.swing.GroupLayout ImagenSalirLayout = new javax.swing.GroupLayout(ImagenSalir);
        ImagenSalir.setLayout(ImagenSalirLayout);
        ImagenSalirLayout.setHorizontalGroup(
            ImagenSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );
        ImagenSalirLayout.setVerticalGroup(
            ImagenSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 35;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jplSalir.add(ImagenSalir, gridBagConstraints);

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

        javax.swing.GroupLayout ImagenNombreLayout = new javax.swing.GroupLayout(ImagenNombre);
        ImagenNombre.setLayout(ImagenNombreLayout);
        ImagenNombreLayout.setHorizontalGroup(
            ImagenNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );
        ImagenNombreLayout.setVerticalGroup(
            ImagenNombreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(ImagenNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jplSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ImagenNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jplSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(255, 255, 255)));
        jPanel3.setLayout(new java.awt.GridBagLayout());

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

        javax.swing.GroupLayout ImagenCreacionLayout = new javax.swing.GroupLayout(ImagenCreacion);
        ImagenCreacion.setLayout(ImagenCreacionLayout);
        ImagenCreacionLayout.setHorizontalGroup(
            ImagenCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        ImagenCreacionLayout.setVerticalGroup(
            ImagenCreacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Unidad");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("I");

        javax.swing.GroupLayout jplUnidad1Layout = new javax.swing.GroupLayout(jplUnidad1);
        jplUnidad1.setLayout(jplUnidad1Layout);
        jplUnidad1Layout.setHorizontalGroup(
            jplUnidad1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidad1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(ImagenCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(ImagenCreacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplUnidad1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3))
                    .addComponent(jLabel2)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 0, 0);
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

        javax.swing.GroupLayout ImagenRevisionLayout = new javax.swing.GroupLayout(ImagenRevision);
        ImagenRevision.setLayout(ImagenRevisionLayout);
        ImagenRevisionLayout.setHorizontalGroup(
            ImagenRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        ImagenRevisionLayout.setVerticalGroup(
            ImagenRevisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Unidad");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("II");

        javax.swing.GroupLayout jplUnidad2Layout = new javax.swing.GroupLayout(jplUnidad2);
        jplUnidad2.setLayout(jplUnidad2Layout);
        jplUnidad2Layout.setHorizontalGroup(
            jplUnidad2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidad2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(ImagenRevision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(ImagenRevision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jplUnidad2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel7))
                    .addComponent(jLabel6)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
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

        javax.swing.GroupLayout ImagenListadoLayout = new javax.swing.GroupLayout(ImagenListado);
        ImagenListado.setLayout(ImagenListadoLayout);
        ImagenListadoLayout.setHorizontalGroup(
            ImagenListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        ImagenListadoLayout.setVerticalGroup(
            ImagenListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Unidad");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("III");

        javax.swing.GroupLayout jplUnidad3Layout = new javax.swing.GroupLayout(jplUnidad3);
        jplUnidad3.setLayout(jplUnidad3Layout);
        jplUnidad3Layout.setHorizontalGroup(
            jplUnidad3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidad3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(ImagenListado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jplUnidad3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addContainerGap())
        );
        jplUnidad3Layout.setVerticalGroup(
            jplUnidad3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplUnidad3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jplUnidad3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ImagenListado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(jplUnidad3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel9))))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 44;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 0, 222, 0);
        jPanel3.add(jplUnidad3, gridBagConstraints);

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

        javax.swing.GroupLayout ImagenInicioLayout = new javax.swing.GroupLayout(ImagenInicio);
        ImagenInicio.setLayout(ImagenInicioLayout);
        ImagenInicioLayout.setHorizontalGroup(
            ImagenInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        ImagenInicioLayout.setVerticalGroup(
            ImagenInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addComponent(ImagenInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(ImagenInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = -6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(220, 0, 0, 0);
        jPanel3.add(jplInicio, gridBagConstraints);

        pestañas.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        pestañas.setMinimumSize(new java.awt.Dimension(1180, 550));
        pestañas.setPreferredSize(new java.awt.Dimension(1180, 550));

        pageCursa.setBackground(new java.awt.Color(255, 255, 255));
        pageCursa.setPreferredSize(new java.awt.Dimension(1140, 642));

        panelEstudiantes.setBackground(new java.awt.Color(255, 255, 255));
        panelEstudiantes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Estudiantes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblParticipantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblParticipantes);

        javax.swing.GroupLayout panelEstudiantesLayout = new javax.swing.GroupLayout(panelEstudiantes);
        panelEstudiantes.setLayout(panelEstudiantesLayout);
        panelEstudiantesLayout.setHorizontalGroup(
            panelEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstudiantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelEstudiantesLayout.setVerticalGroup(
            panelEstudiantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEstudiantesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );

        txtcursa.setEditable(false);
        txtcursa.setText("Periodo Academico ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Curso:");

        javax.swing.GroupLayout pageCursaLayout = new javax.swing.GroupLayout(pageCursa);
        pageCursa.setLayout(pageCursaLayout);
        pageCursaLayout.setHorizontalGroup(
            pageCursaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageCursaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pageCursaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEstudiantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pageCursaLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcursa, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pageCursaLayout.setVerticalGroup(
            pageCursaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageCursaLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pageCursaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtcursa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(322, Short.MAX_VALUE))
        );

        pestañas.addTab("", pageCursa);

        pageUno.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Curso:");

        txtcursa2.setEditable(false);
        txtcursa2.setText("Periodo Academico ");

        panelAsignaciones2.setBackground(new java.awt.Color(255, 255, 255));
        panelAsignaciones2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbltareas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbltareas);

        lblnombreUnidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblnombreUnidad.setText("Tareas de la Unidad");

        buttonNuevaTarea.setBackground(new java.awt.Color(0, 204, 204));
        buttonNuevaTarea.setText("Nueva Tarea");
        buttonNuevaTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNuevaTareaActionPerformed(evt);
            }
        });

        buttonEditarTarea.setBackground(new java.awt.Color(0, 204, 204));
        buttonEditarTarea.setText("Editar Tarea");
        buttonEditarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarTareaActionPerformed(evt);
            }
        });

        buttonVerAsignaciones.setBackground(new java.awt.Color(0, 204, 204));
        buttonVerAsignaciones.setText("Ver Asignaciones");
        buttonVerAsignaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerAsignacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAsignaciones2Layout = new javax.swing.GroupLayout(panelAsignaciones2);
        panelAsignaciones2.setLayout(panelAsignaciones2Layout);
        panelAsignaciones2Layout.setHorizontalGroup(
            panelAsignaciones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaciones2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAsignaciones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAsignaciones2Layout.createSequentialGroup()
                        .addComponent(lblnombreUnidad)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelAsignaciones2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                        .addGroup(panelAsignaciones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAsignaciones2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(buttonVerAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAsignaciones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAsignaciones2Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(buttonNuevaTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelAsignaciones2Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(buttonEditarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(11, Short.MAX_VALUE))))
        );
        panelAsignaciones2Layout.setVerticalGroup(
            panelAsignaciones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaciones2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblnombreUnidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAsignaciones2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAsignaciones2Layout.createSequentialGroup()
                        .addComponent(buttonNuevaTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEditarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonVerAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pageUnoLayout = new javax.swing.GroupLayout(pageUno);
        pageUno.setLayout(pageUnoLayout);
        pageUnoLayout.setHorizontalGroup(
            pageUnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageUnoLayout.createSequentialGroup()
                .addGroup(pageUnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pageUnoLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcursa2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pageUnoLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(panelAsignaciones2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(304, Short.MAX_VALUE))
        );
        pageUnoLayout.setVerticalGroup(
            pageUnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageUnoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pageUnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtcursa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelAsignaciones2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(293, Short.MAX_VALUE))
        );

        pestañas.addTab("", pageUno);

        pageDos.setBackground(new java.awt.Color(255, 255, 255));

        panelAsignaciones1.setBackground(new java.awt.Color(255, 255, 255));
        panelAsignaciones1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Seleccione el estudiante para revisar su asignación:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblAsignacionesEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblAsignacionesEstudiantes);

        buttonAbrirAsignacion.setBackground(new java.awt.Color(0, 204, 204));
        buttonAbrirAsignacion.setText("Abrir Asignacion");
        buttonAbrirAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAbrirAsignacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAsignaciones1Layout = new javax.swing.GroupLayout(panelAsignaciones1);
        panelAsignaciones1.setLayout(panelAsignaciones1Layout);
        panelAsignaciones1Layout.setHorizontalGroup(
            panelAsignaciones1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaciones1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(panelAsignaciones1Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(buttonAbrirAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAsignaciones1Layout.setVerticalGroup(
            panelAsignaciones1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignaciones1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(buttonAbrirAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Curso:");

        txtcursa1.setEditable(false);
        txtcursa1.setText("Periodo Academico ");

        javax.swing.GroupLayout pageDosLayout = new javax.swing.GroupLayout(pageDos);
        pageDos.setLayout(pageDosLayout);
        pageDosLayout.setHorizontalGroup(
            pageDosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageDosLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(pageDosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAsignaciones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pageDosLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcursa1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(386, Short.MAX_VALUE))
        );
        pageDosLayout.setVerticalGroup(
            pageDosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageDosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pageDosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtcursa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelAsignaciones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
        );

        pestañas.addTab("", pageDos);

        pageTres.setBackground(new java.awt.Color(255, 255, 204));

        principalAsignacion.setBackground(new java.awt.Color(255, 255, 255));
        principalAsignacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Detalles de la tarea:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        principalAsignacion.setPreferredSize(new java.awt.Dimension(1140, 642));

        jLabel4.setText("Fecha de Asignación:");

        jLabel5.setText("Fecha de entrega: ");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Archivo complementario:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        txtarchivoTarea.setEditable(false);
        txtarchivoTarea.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtarchivoTarea.setText("No se ha proporcionado un archivo");

        buttonSubir.setBackground(new java.awt.Color(0, 204, 204));
        buttonSubir.setText("Subir");
        buttonSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSubirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtarchivoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtarchivoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        txtdescripcion.setColumns(20);
        txtdescripcion.setRows(5);
        jScrollPane5.setViewportView(txtdescripcion);

        jLabel12.setText("Titulo");

        jLabel13.setText("Descripcion");

        txtfechaasignacion.setEditable(false);

        panelAsigEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        panelAsigEstudiante.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txttiemporestante.setEditable(false);

        jLabel10.setText("Tiempo restante:");

        txtestadoasignacion.setEditable(false);

        jLabel18.setText("Estado de la Asignación:");

        panelArchivosEnviados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Archivo entregado:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        txtnomarchivo.setEditable(false);
        txtnomarchivo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtnomarchivo.setText("Ningun archivo seleccionado");
        txtnomarchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnomarchivoMouseClicked(evt);
            }
        });

        buttonDescargar.setBackground(new java.awt.Color(0, 204, 204));
        buttonDescargar.setText("Descargar");
        buttonDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDescargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelArchivosEnviadosLayout = new javax.swing.GroupLayout(panelArchivosEnviados);
        panelArchivosEnviados.setLayout(panelArchivosEnviadosLayout);
        panelArchivosEnviadosLayout.setHorizontalGroup(
            panelArchivosEnviadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArchivosEnviadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnomarchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonDescargar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelArchivosEnviadosLayout.setVerticalGroup(
            panelArchivosEnviadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArchivosEnviadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtnomarchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonDescargar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelCalificacion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane1.setViewportView(txtcomentario);

        jLabel19.setText("Agregar comentario:");

        jLabel21.setText("Calificacion");

        txtcalificacion.setEditable(false);
        jScrollPane6.setViewportView(txtcalificacion);

        jLabel22.setFont(new java.awt.Font("Inter", 0, 15)); // NOI18N
        jLabel22.setText("/ 10");

        javax.swing.GroupLayout panelCalificacionLayout = new javax.swing.GroupLayout(panelCalificacion);
        panelCalificacion.setLayout(panelCalificacionLayout);
        panelCalificacionLayout.setHorizontalGroup(
            panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCalificacionLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCalificacionLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(104, 104, 104))
        );
        panelCalificacionLayout.setVerticalGroup(
            panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCalificacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout panelAsigEstudianteLayout = new javax.swing.GroupLayout(panelAsigEstudiante);
        panelAsigEstudiante.setLayout(panelAsigEstudianteLayout);
        panelAsigEstudianteLayout.setHorizontalGroup(
            panelAsigEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsigEstudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAsigEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAsigEstudianteLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(txtestadoasignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(12, 12, 12)
                        .addComponent(txttiemporestante, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelArchivosEnviados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCalificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelAsigEstudianteLayout.setVerticalGroup(
            panelAsigEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsigEstudianteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAsigEstudianteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtestadoasignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel10)
                    .addComponent(txttiemporestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelArchivosEnviados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonGuardarySalir.setBackground(new java.awt.Color(0, 204, 204));
        buttonGuardarySalir.setText("Guardar y Salir");
        buttonGuardarySalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarySalirActionPerformed(evt);
            }
        });

        buttonCancelar.setBackground(new java.awt.Color(0, 204, 204));
        buttonCancelar.setText("Cancelar");
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout principalAsignacionLayout = new javax.swing.GroupLayout(principalAsignacion);
        principalAsignacion.setLayout(principalAsignacionLayout);
        principalAsignacionLayout.setHorizontalGroup(
            principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalAsignacionLayout.createSequentialGroup()
                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalAsignacionLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelAsigEstudiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(principalAsignacionLayout.createSequentialGroup()
                                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addGroup(principalAsignacionLayout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txttitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(principalAsignacionLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel4)
                                        .addGap(37, 37, 37)
                                        .addComponent(txtfechaasignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dtcFin, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnhora, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnminutos, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(principalAsignacionLayout.createSequentialGroup()
                        .addGap(336, 336, 336)
                        .addComponent(buttonGuardarySalir, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        principalAsignacionLayout.setVerticalGroup(
            principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalAsignacionLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txttitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(principalAsignacionLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtfechaasignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(spnhora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spnminutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(dtcFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAsigEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGuardarySalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pageTresLayout = new javax.swing.GroupLayout(pageTres);
        pageTres.setLayout(pageTresLayout);
        pageTresLayout.setHorizontalGroup(
            pageTresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pageTresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(principalAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );
        pageTresLayout.setVerticalGroup(
            pageTresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pageTresLayout.createSequentialGroup()
                .addGap(0, 68, Short.MAX_VALUE)
                .addComponent(principalAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pestañas.addTab("", pageTres);

        pageIN.setBackground(new java.awt.Color(255, 255, 255));
        pageIN.setPreferredSize(new java.awt.Dimension(1140, 642));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mis cursos:");

        javax.swing.GroupLayout pageINLayout = new javax.swing.GroupLayout(pageIN);
        pageIN.setLayout(pageINLayout);
        pageINLayout.setHorizontalGroup(
            pageINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageINLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel1)
                .addContainerGap(925, Short.MAX_VALUE))
        );
        pageINLayout.setVerticalGroup(
            pageINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageINLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(662, Short.MAX_VALUE))
        );

        pestañas.addTab("", pageIN);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 230, Short.MAX_VALUE)
                        .addComponent(pestañas, javax.swing.GroupLayout.PREFERRED_SIZE, 1140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1176, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pestañas, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jplUnidad2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad2MouseClicked
        unidadActual = 2;
        pestañas.setSelectedIndex(2);
        txtcursa2.setText(nombreCursa);
        jplInicio.setEnabled(true);
        lblnombreUnidad.setText("Tareas de la Unidad 2:");
        mtt.setTareas(tarc.tareasCursoParUnidad(docenteLogeado.getId(), id_materia, paralelo,2));
        tbltareas.setModel(mtt);
        tbltareas.updateUI();
        /*setIsRevisionClicked(true);
        isRevisionClicked = true;
        clickeado = "Revision";
        jplInicio.setVisible(true);
        
        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7,jLabel8, jLabel9, jLabel16};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplInicio, jplCreacion, jplListado};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }
        jplRevision.setBackground(new Color(255, 204, 0));
        jLabel6.setForeground(Color.black);
        jLabel7.setForeground(Color.black);

        FrmPrincipalDocente vistaPequania = new FrmPrincipalDocente(this, docenteLogeado.getId(), clickeado);
        JPanel panelDocente = vistaPequania.getjPanel1();
        jPanel5.removeAll();
        jPanel5.add(panelDocente);
        jPanel5.revalidate();
        jPanel5.repaint();*/
    }//GEN-LAST:event_jplUnidad2MouseClicked

    private void jplUnidad3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad3MouseClicked
        unidadActual = 3;
        pestañas.setSelectedIndex(2);
        txtcursa2.setText(nombreCursa);
        jplInicio.setEnabled(true);
        lblnombreUnidad.setText("Tareas de la Unidad 3:");
        mtt.setTareas(tarc.tareasCursoParUnidad(docenteLogeado.getId(), id_materia, paralelo,3));
        tbltareas.setModel(mtt);
        tbltareas.updateUI();
        /*isListadoClicked = true;
        clickeado = "Listado";
        jplInicio.setVisible(true);

        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7,jLabel8, jLabel9, jLabel16};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplInicio, jplCreacion, jplRevision};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }
        jplListado.setBackground(new Color(255, 204, 0));
        jLabel8.setForeground(Color.black);
        jLabel9.setForeground(Color.black);

        FrmPrincipalDocente vistaPequania = new FrmPrincipalDocente(this, docenteLogeado.getId(), sacarClick());
        JPanel panelDocente = vistaPequania.getjPanel1();
        jPanel5.removeAll();
        jPanel5.add(panelDocente);
        jPanel5.revalidate();
        jPanel5.repaint();*/
    }//GEN-LAST:event_jplUnidad3MouseClicked

    private void jplSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplSalirMouseClicked
        new FrmIniciarSesion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jplSalirMouseClicked

    private void jplUnidad2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad2MouseEntered
        if (!isRevisionClicked) {
            jplUnidad2.setBackground(new Color(255, 204, 0));
            jLabel6.setForeground(Color.black);
            jLabel7.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad2MouseEntered

    private void jplUnidad2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad2MouseExited
        if (!isRevisionClicked) {
            jplUnidad2.setBackground(Color.WHITE);
            jLabel6.setForeground(Color.black);
            jLabel7.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad2MouseExited

    private void jplUnidad3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad3MouseEntered
        if (!isListadoClicked) {
            jplUnidad3.setBackground(new Color(255, 204, 0));
            jLabel8.setForeground(Color.black);
            jLabel9.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad3MouseEntered

    private void jplUnidad3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad3MouseExited
        if (!isListadoClicked) {
            jplUnidad3.setBackground(Color.WHITE);
            jLabel8.setForeground(Color.black);
            jLabel9.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad3MouseExited

    private void jplInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplInicioMouseClicked
        unidadActual = 0;
        pestañas.setSelectedIndex(0);
        jplInicio.setEnabled(false);
        jplUnidad1.setVisible(false);
        jplUnidad2.setVisible(false);
        jplUnidad3.setVisible(false);
        /*isInicioClicked = true;
        
        jplInicio.setVisible(false);

        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7,jLabel8, jLabel9, jLabel16};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplCreacion, jplRevision, jplListado};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }
        jplInicio.setBackground(new Color(255, 204, 0));
        jLabel16.setForeground(Color.black);

        jPanel5.removeAll();
        jPanel5.add(ImagenMain);
        jPanel5.revalidate();
        jPanel5.repaint();*/
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

    private void jplUnidad1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad1MouseExited
        if (!isCreacionClicked) {
            jplUnidad1.setBackground(Color.WHITE);
            jLabel2.setForeground(Color.black);
            jLabel3.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad1MouseExited

    private void jplUnidad1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad1MouseEntered
        if (!isCreacionClicked) {
            jplUnidad1.setBackground(new Color(255, 204, 0));
            jLabel2.setForeground(Color.black);
            jLabel3.setForeground(Color.black);
        }
    }//GEN-LAST:event_jplUnidad1MouseEntered

    private void jplUnidad1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplUnidad1MouseClicked
        unidadActual = 1;
        pestañas.setSelectedIndex(2);
        txtcursa2.setText(nombreCursa);
        jplInicio.setEnabled(true);
        lblnombreUnidad.setText("Tareas de la Unidad 1:");
        mtt.setTareas(tarc.tareasCursoParUnidad(docenteLogeado.getId(), id_materia, paralelo,1));
        tbltareas.setModel(mtt);
        tbltareas.updateUI();
        /*isCreacionClicked = true;
        clickeado = "Creacion";
        jplInicio.setVisible(true);

        jplInicio.setBackground(Color.white);
        jplRevision.setBackground(Color.white);
        jplListado.setBackground(Color.white);

        JLabel[] labelsBlancos = {jLabel2, jLabel3, jLabel6, jLabel7,jLabel8, jLabel9, jLabel16};
        for (JLabel label : labelsBlancos) {
            label.setForeground(Color.black);
        }

        JPanel[] panelsCianOscuro = {jplInicio, jplRevision, jplListado};
        for (JPanel panel : panelsCianOscuro) {
            panel.setBackground(Color.WHITE);
        }
        jplCreacion.setBackground(new Color(255, 204, 0));
        jLabel2.setForeground(Color.black);
        jLabel3.setForeground(Color.black);

        FrmPrincipalDocente vistaPequania = new FrmPrincipalDocente(this, docenteLogeado.getId(), sacarClick());
        JPanel panelDocente = vistaPequania.getjPanel1();
        jPanel5.removeAll();
        jPanel5.add(panelDocente);
        jPanel5.revalidate();
        jPanel5.repaint();*/
    }//GEN-LAST:event_jplUnidad1MouseClicked

    private void limpiarpanelPrincipalAsignacion(){
        txttitulo.setText("");
        txtdescripcion.setText("");
        txtarchivoTarea.setText("No se ha proporcionado un archivo");
        txtfechaasignacion.setText("");
        txtcomentario.setText("");
    }
    
    private void txtnomarchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnomarchivoMouseClicked
        
    }//GEN-LAST:event_txtnomarchivoMouseClicked

    private void buttonNuevaTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevaTareaActionPerformed
        pestañas.setSelectedIndex(4);
        panelAsigEstudiante.setVisible(false);
    }//GEN-LAST:event_buttonNuevaTareaActionPerformed

    private void buttonEditarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarTareaActionPerformed
        try {
            Integer fila = tbltareas.getSelectedRow();
            if(fila > -1){
                tareaSeleccionada = mtt.getTareas().get(fila);
                txttitulo.setText(tareaSeleccionada.getTitulo());
                txtdescripcion.setText(tareaSeleccionada.getDescripcion());
                //if(tareaSeleccionada.getArchivo() != null)
                txtfechaasignacion.setText(tareaSeleccionada.getFechaAsignacion().toString());
                dtcFin.setDate(tareaSeleccionada.getFechaEntrega());
                archivoDocente = tareaSeleccionada.getArchivo();
                pestañas.setSelectedIndex(4);
                panelAsigEstudiante.setVisible(false);
            }else
                JOptionPane.showMessageDialog(null, "Seleccione una tarea primero...","Atención",JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_buttonEditarTareaActionPerformed

    private void buttonVerAsignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerAsignacionesActionPerformed
        pestañas.setSelectedIndex(3);
        try {
            mtae.setAsignaciones(asc.asignacionesCursoUnidad(id_materia, docenteLogeado.getId(), paralelo, unidadActual));
            tblAsignacionesEstudiantes.setModel(mtae);
            tblAsignacionesEstudiantes.updateUI();
        } catch (Exception e) {
            System.out.println("Error ver asignaciones de estudiante");
        }
//        emt.setEstudiantes(cursac.listarCursa_Participan(docenteLogeado.getId(), id_materia, paralelo));
//                                tblParticipantes.setModel(emt);
//                                tblParticipantes.updateUI();
    }//GEN-LAST:event_buttonVerAsignacionesActionPerformed

    private void buttonAbrirAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAbrirAsignacionActionPerformed
        Integer fila = tblAsignacionesEstudiantes.getSelectedRow();
        if(fila < 0){
            JOptionPane.showMessageDialog(null, "Seleccione una asignación", paralelo, 0);
        }else{
            pestañas.setSelectedIndex(3);
            panelAsigEstudiante.setVisible(true);
            String archivo = (asignacion.getArchivo() != null) ? "Archivo Entregado":"No se ha subido un archivo";
        }
        
        
        //asignacion = asc
    }//GEN-LAST:event_buttonAbrirAsignacionActionPerformed

    private void buttonSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSubirActionPerformed
        JFileChooser jf = new JFileChooser(archivoDocente);
        jf.setMultiSelectionEnabled(false);
        jf.setAcceptAllFileFilterUsed(false);
        jf.setFileFilter(new FileNameExtensionFilter("Archivos PDF","pdf"));
        if(jf.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            archivoDocente = jf.getSelectedFile();
            txtarchivoTarea.setText(archivoDocente.getName());
        }else
        JOptionPane.showMessageDialog(null,"Ningún archivo seleccionado", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_buttonSubirActionPerformed

    private void buttonDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDescargarActionPerformed
        try {
            String descargasDir = System.getProperty("user.home") + "/Downloads";

            // Creamos un objeto Path para la carpeta de descargas
            Path descargasPath = Paths.get(descargasDir);

            // Copiamos el archivo temporal a la carpeta de descargas
            Files.copy(asignacion.getArchivo().toPath(), descargasPath.resolve(asignacion.getArchivo().getName()), StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(null, "Guardado en Descargas", "Descargado Correctamente", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonDescargarActionPerformed

    private void buttonGuardarySalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarySalirActionPerformed
        if(txttitulo.getText().isEmpty() == (dtcFin.getDate() == null || dtcFin.getDate().after(new Date()))){
            JOptionPane.showMessageDialog(null,"Revise los campos: Título o Fecha de Entrega", "Validación Errónea", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                if(tarc.getTarea().getId() != null){
                    tarc.getTarea().setUnidad(unidadActual);
                    tarc.getTarea().setTitulo(txttitulo.getText());
                    tarc.getTarea().setDescripcion(txtdescripcion.getText());
                    tarc.getTarea().setArchivo(archivoDocente);
                    tarc.getTarea().setFechaAsignacion(new Date());
                    tarc.getTarea().setFechaEntrega(dtcFin.getDate());
                }else{
                    
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se ha podido guardar la información de la tarea","Error",JOptionPane.ERROR_MESSAGE);
            }
            
            
        }
        tarc.setTarea(null);
    }//GEN-LAST:event_buttonGuardarySalirActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        pestañas.setSelectedIndex(2);
    }//GEN-LAST:event_buttonCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPruebaProfe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPruebaProfe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPruebaProfe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPruebaProfe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Integer id = null;
                new FrmPruebaProfe(id).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage ImagenCreacion;
    private org.edisoncor.gui.panel.PanelImage ImagenInicio;
    private org.edisoncor.gui.panel.PanelImage ImagenListado;
    private org.edisoncor.gui.panel.PanelImage ImagenNombre;
    private org.edisoncor.gui.panel.PanelImage ImagenRevision;
    private org.edisoncor.gui.panel.PanelImage ImagenSalir;
    private org.edisoncor.gui.button.ButtonAero buttonAbrirAsignacion;
    private org.edisoncor.gui.button.ButtonAero buttonCancelar;
    private org.edisoncor.gui.button.ButtonAero buttonDescargar;
    private org.edisoncor.gui.button.ButtonAero buttonEditarTarea;
    private org.edisoncor.gui.button.ButtonAero buttonGuardar1;
    private org.edisoncor.gui.button.ButtonAero buttonGuardar2;
    private org.edisoncor.gui.button.ButtonAero buttonGuardarySalir;
    private org.edisoncor.gui.button.ButtonAero buttonNuevaTarea;
    private org.edisoncor.gui.button.ButtonAero buttonSubir;
    private org.edisoncor.gui.button.ButtonAero buttonVerAsignaciones;
    private com.toedter.calendar.JDateChooser dtcFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel jblNombre;
    private javax.swing.JPanel jplInicio;
    private javax.swing.JPanel jplSalir;
    private javax.swing.JPanel jplUnidad1;
    private javax.swing.JPanel jplUnidad2;
    private javax.swing.JPanel jplUnidad3;
    private javax.swing.JLabel lblnombreUnidad;
    private javax.swing.JPanel pageCursa;
    private javax.swing.JPanel pageDos;
    private javax.swing.JPanel pageIN;
    private javax.swing.JPanel pageTres;
    private javax.swing.JPanel pageUno;
    private javax.swing.JPanel panelArchivosEnviados;
    private javax.swing.JPanel panelAsigEstudiante;
    private javax.swing.JPanel panelAsignaciones1;
    private javax.swing.JPanel panelAsignaciones2;
    private javax.swing.JPanel panelCalificacion;
    private javax.swing.JPanel panelEstudiantes;
    private tabbed.MaterialTabbed pestañas;
    private javax.swing.JPanel principalAsignacion;
    private javax.swing.JSpinner spnhora;
    private javax.swing.JSpinner spnminutos;
    private javax.swing.JTable tblAsignacionesEstudiantes;
    private javax.swing.JTable tblParticipantes;
    private javax.swing.JTable tbltareas;
    private javax.swing.JTextField txtarchivoTarea;
    private javax.swing.JTextPane txtcalificacion;
    private javax.swing.JTextPane txtcomentario;
    private javax.swing.JTextField txtcursa;
    private javax.swing.JTextField txtcursa1;
    private javax.swing.JTextField txtcursa2;
    private javax.swing.JTextArea txtdescripcion;
    private javax.swing.JTextField txtestadoasignacion;
    private javax.swing.JTextField txtfechaasignacion;
    private javax.swing.JTextField txtnomarchivo;
    private javax.swing.JTextField txttiemporestante;
    private javax.swing.JTextField txttitulo;
    // End of variables declaration//GEN-END:variables
}
