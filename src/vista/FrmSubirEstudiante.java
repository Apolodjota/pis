package vista;

import controlador.Utilidades.Utilidades;
import controladores.AsignacionController;
import java.awt.Frame;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Asignacion;
import modelo.Tarea;
import vista.FrmPrincipalEstudiante1;
import vista.FrmInfoCursa;

/**
 *
 * @author apolo
 */
public class FrmSubirEstudiante extends javax.swing.JFrame {
    private AsignacionController ac = new AsignacionController();
    private File archivoDocente;
    private File archivoEstudiante;
    private Asignacion asignacion = new Asignacion();
    private Tarea tarea = new Tarea();
    private java.awt.Frame padre;

    /**
     * Creates new form FrmRevision
     */
    public FrmSubirEstudiante() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public FrmSubirEstudiante(java.awt.Frame parent,Asignacion a, Tarea t) {
        
        initComponents();
        setLocationRelativeTo(null);
        padre = parent;
        setearAsignacionTarea(a, t);
        cargarDatos();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public FrmSubirEstudiante(Asignacion a, Tarea t) {
        initComponents();
        setLocationRelativeTo(null);
        setearAsignacionTarea(a, t);
        cargarDatos();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void setearAsignacionTarea(Asignacion a, Tarea t) {
        asignacion = a;
        tarea = t;
    }

    public void cargarDatos() {
        txttitulo.setText(tarea.getTitulo());
        txtdescripcion.setText(tarea.getDescripcion());
        //archivoDocente = tarea.getArchivo();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        txtfechaasignacion.setText(formato.format(tarea.getFechaAsignacion()));
        txtfechaentregatarea.setText(formato.format(tarea.getFechaEntrega()));
        String estado = "Sin entregar";
        if (asignacion.getEstado().equalsIgnoreCase("E")) {estado = "Entregado"; }
        if (asignacion.getEstado().equalsIgnoreCase("C")) {estado = "Calificado";}
        txtestadoasignacion.setText(estado);
        if(tarea.getFechaEntrega().before(new Date())){
            if(asignacion.getEstado().equalsIgnoreCase("C")){txtcalificacion.setText(asignacion.getCalificacion().toString());}
            btnSelecArchivo.setEnabled(false);
            btnGuardar.setText("Enviar comentario");
        }
        String coment = (asignacion.getComentario() != null) ? asignacion.getComentario(): "";
        txtcomentario.setText(coment);
        txttiemporestante.setText(Utilidades.calcularDiferencia(tarea.getFechaEntrega(), new Date()));
        if(tarea.getArchivo() != null){txtarchivoTarea.setText(tarea.getArchivo().getName());}
        if(asignacion.getArchivo() != null){txtnomarchivo.setText(asignacion.getArchivo().getName());}
    }

    public File getArchivoDocente() {
        return archivoDocente;
    }

    public File getArchivoEstudiante() {
        return archivoEstudiante;
    }

    public JPanel getPrincipalAsignacion() {
        return principalAsignacion;
    }

    public JTextArea getTxtdescripcion() {
        return txtdescripcion;
    }

    public JTextField getTxtestadoasignacion() {
        return txtestadoasignacion;
    }

    public JTextField getTxtfechaasignacion() {
        return txtfechaasignacion;
    }

    public JTextField getTxtfechaentregatarea() {
        return txtfechaentregatarea;
    }

    public JTextField getTxttiemporestante() {
        return txttiemporestante;
    }

    public JTextField getTxttitulo() {
        return txttitulo;
    }

    private void entregar(){
        try {
            asignacion.setArchivo(archivoEstudiante);
            asignacion.setComentario(txtcomentario.getText());
            asignacion.setFechaEntrega(new Date());
            asignacion.setEstado("E");
            System.out.println(new Date());
            ac.actualizarAsignacion(asignacion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al entregar tarea","Error",JOptionPane.ERROR_MESSAGE);
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

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        principalAsignacion = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtarchivoTarea = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelArchivosEnviados = new javax.swing.JPanel();
        btnSelecArchivo = new javax.swing.JButton();
        txtnomarchivo = new javax.swing.JTextField();
        txttitulo = new javax.swing.JTextField();
        txtestadoasignacion = new javax.swing.JTextField();
        txtfechaentregatarea = new javax.swing.JTextField();
        txttiemporestante = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtfechaasignacion = new javax.swing.JTextField();
        panelCalificacion = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtcomentario = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtcalificacion = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1008, 1118));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1008, 1118));
        jPanel1.setMinimumSize(new java.awt.Dimension(1008, 1118));
        jPanel1.setPreferredSize(new java.awt.Dimension(1008, 1118));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        principalAsignacion.setBackground(new java.awt.Color(255, 255, 255));
        principalAsignacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Mi Asignacion:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        principalAsignacion.setPreferredSize(new java.awt.Dimension(1140, 642));

        jLabel2.setText("Fecha de Asignación:");

        jLabel4.setText("Fecha de entrega: ");

        jLabel5.setText("Tiempo restante:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Archivo complementario:"));

        txtarchivoTarea.setEditable(false);
        txtarchivoTarea.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtarchivoTarea.setText("No se ha proporcionado un archivo");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtarchivoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtarchivoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        txtdescripcion.setEditable(false);
        txtdescripcion.setColumns(20);
        txtdescripcion.setRows(5);
        jScrollPane3.setViewportView(txtdescripcion);

        jLabel6.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel6.setText("Titulo");

        jLabel7.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel7.setText("Descripcion");

        panelArchivosEnviados.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Subir archivo:"));

        btnSelecArchivo.setText("Subir");
        btnSelecArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecArchivoActionPerformed(evt);
            }
        });

        txtnomarchivo.setEditable(false);
        txtnomarchivo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtnomarchivo.setText("Ningun archivo seleccionado");
        txtnomarchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnomarchivoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelArchivosEnviadosLayout = new javax.swing.GroupLayout(panelArchivosEnviados);
        panelArchivosEnviados.setLayout(panelArchivosEnviadosLayout);
        panelArchivosEnviadosLayout.setHorizontalGroup(
            panelArchivosEnviadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArchivosEnviadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnomarchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelecArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelArchivosEnviadosLayout.setVerticalGroup(
            panelArchivosEnviadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArchivosEnviadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnSelecArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addComponent(txtnomarchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txttitulo.setEditable(false);

        txtestadoasignacion.setEditable(false);

        txtfechaentregatarea.setEditable(false);

        txttiemporestante.setEditable(false);

        jLabel3.setText("Estado de la Asignación:");

        txtfechaasignacion.setEditable(false);

        panelCalificacion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane1.setViewportView(txtcomentario);

        jLabel1.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        jLabel1.setText("Agregar comentrario:");

        jLabel8.setText("Calificacion");

        txtcalificacion.setEditable(false);
        jScrollPane4.setViewportView(txtcalificacion);

        jLabel9.setFont(new java.awt.Font("Inter", 0, 15)); // NOI18N
        jLabel9.setText("/ 10");

        javax.swing.GroupLayout panelCalificacionLayout = new javax.swing.GroupLayout(panelCalificacion);
        panelCalificacion.setLayout(panelCalificacionLayout);
        panelCalificacionLayout.setHorizontalGroup(
            panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCalificacionLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCalificacionLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(104, 104, 104))
        );
        panelCalificacionLayout.setVerticalGroup(
            panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCalificacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCalificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        btnGuardar.setText("Entregar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout principalAsignacionLayout = new javax.swing.GroupLayout(principalAsignacion);
        principalAsignacion.setLayout(principalAsignacionLayout);
        principalAsignacionLayout.setHorizontalGroup(
            principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(principalAsignacionLayout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(principalAsignacionLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principalAsignacionLayout.createSequentialGroup()
                        .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(principalAsignacionLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(panelArchivosEnviados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCalificacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(principalAsignacionLayout.createSequentialGroup()
                        .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(principalAsignacionLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txttitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(principalAsignacionLayout.createSequentialGroup()
                                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(principalAsignacionLayout.createSequentialGroup()
                                        .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(principalAsignacionLayout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(41, 41, 41))
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(principalAsignacionLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(txtestadoasignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(principalAsignacionLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtfechaasignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txttiemporestante, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfechaentregatarea, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        principalAsignacionLayout.setVerticalGroup(
            principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principalAsignacionLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txttitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtfechaasignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtfechaentregatarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principalAsignacionLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(panelArchivosEnviados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(principalAsignacionLayout.createSequentialGroup()
                        .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttiemporestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(5, 5, 5)
                        .addGroup(principalAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtestadoasignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(principalAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 990, 600));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(archivoEstudiante != null){
            int i = JOptionPane.showConfirmDialog(null, "¿Desea entregar la asignación?", "Confirmación", JOptionPane.OK_CANCEL_OPTION);
            if (i == JOptionPane.OK_OPTION) {
            try {
                if(new Date().before(tarea.getFechaEntrega())){
                    entregar();//Si aún está dentro de la fecha, se aceptan entregas
                    JOptionPane.showMessageDialog(null, "Entregado correctamente", "Operación exitorsa", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Está fuera del límite de entrega, pero se guardará su comentario", 
                            "Atención", JOptionPane.INFORMATION_MESSAGE);
                    asignacion.setComentario(txtcomentario.getText());
                    ac.actualizarComentario(asignacion);
                }
//                FrmInfoCursa.jPanel1.removeAll();
//                FrmInfoCursa.jPanel1.add(FrmInfoCursa.copia);
//                FrmInfoCursa.jPanel1.revalidate();
//                FrmInfoCursa.jPanel1.repaint();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "No se pudo guardar la tarea", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        }else
            JOptionPane.showMessageDialog(null, "Seleccione un archivo primero", "Imposible", JOptionPane.WARNING_MESSAGE);
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        /*JPanel panelCursa = new FrmInfoCursa(this, true, WAIT_CURSOR).getJPanel1();
        jPanel3.removeAll();
        jPanel3.add(panelAsignacion);
        jPanel3.revalidate();
        jPanel3.repaint();
        this.removeAll();
        System.out.println("BORRANDO");
        FrmPrincipalEstudiante1.panelCursas.removeAll();
        FrmPrincipalEstudiante1.panelCursas.add(FrmPrincipalEstudiante1.panelCursaSeleccionado);
        FrmPrincipalEstudiante1.panelCursas.revalidate();
        FrmPrincipalEstudiante1.panelCursas.repaint();*/
        FrmInfoCursa.jPanel1.removeAll();
        FrmInfoCursa.jPanel1.add(new FrmInfoCursa(padre,true,asignacion.getId_cursa()).getJPanel1());
        FrmInfoCursa.jPanel1.revalidate();
        FrmInfoCursa.jPanel1.repaint();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnSelecArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecArchivoActionPerformed
        JFileChooser jf = new JFileChooser(archivoDocente);
        jf.setMultiSelectionEnabled(false);
        jf.setAcceptAllFileFilterUsed(false);
        jf.setFileFilter(new FileNameExtensionFilter("Archivos PDF","pdf"));
        if(jf.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            archivoEstudiante = jf.getSelectedFile();
            txtnomarchivo.setText(archivoEstudiante.getName());      
        }else
            JOptionPane.showMessageDialog(null,"Ningún archivo seleccionado", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnSelecArchivoActionPerformed

    private void txtnomarchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnomarchivoMouseClicked
        try {
            String descargasDir = System.getProperty("user.home") + "/Downloads";
        
            // Creamos un objeto Path para la carpeta de descargas
            Path descargasPath = Paths.get(descargasDir);
        
            // Copiamos el archivo temporal a la carpeta de descargas
            Files.copy(asignacion.getArchivo().toPath(), descargasPath.resolve(asignacion.getArchivo().getName()), StandardCopyOption.REPLACE_EXISTING);
            JOptionPane.showMessageDialog(null, "Guardado en Descargas", "Descargado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtnomarchivoMouseClicked

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
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSubirEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new FrmSubirEstudiante().setVisible(true);
            }
        });
    }

    public void tipo() {
        if (getTitle().equalsIgnoreCase("Asignacion de tareas DOCENTE")) {
            apagar();
        } else if (getTitle().equalsIgnoreCase("Revision de tareas ESTUDIANTE")) {
            System.out.println(";");
        }
    }

    public void apagar() {
        panelArchivosEnviados.setVisible(false);
        panelCalificacion.setVisible(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSelecArchivo;
    private javax.swing.JButton btncancelar;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panelArchivosEnviados;
    private javax.swing.JPanel panelCalificacion;
    private javax.swing.JPanel principalAsignacion;
    private javax.swing.JTextField txtarchivoTarea;
    private javax.swing.JTextPane txtcalificacion;
    private javax.swing.JTextPane txtcomentario;
    private javax.swing.JTextArea txtdescripcion;
    private javax.swing.JTextField txtestadoasignacion;
    private javax.swing.JTextField txtfechaasignacion;
    private javax.swing.JTextField txtfechaentregatarea;
    private javax.swing.JTextField txtnomarchivo;
    private javax.swing.JTextField txttiemporestante;
    private javax.swing.JTextField txttitulo;
    // End of variables declaration//GEN-END:variables
}
