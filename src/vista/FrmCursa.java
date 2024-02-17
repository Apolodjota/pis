package vista;

import controladores.PeriodoController;
import controlador.TDALista.LinkedList;
import controladores.CursaController;
import controladores.CursoControllerListas;
import controladores.RevisionController;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.Matricula;
import vista.listas.tablas.ModeloTablaCursa;
import vista.listas.util.UtilVista;

/**
 *
 * @author Asus
 */
public class FrmCursa extends javax.swing.JFrame {

    /**
     * @return the panelPrincipal
     */
    CursaController cC = new CursaController();
    //CursoControllerListas csoC = new CursoControllerListas();
    //RevisionController revC = new RevisionController();
    ModeloTablaCursa mtcc = new ModeloTablaCursa();
    public static Matricula m;

    /**
     * Creates new form FrmVendedor
     */
    public FrmCursa() {
        initComponents();
//        limpiar();
    }

    public JPanel getJPanel1() {
        return panelPrincipal;
    }

    private void cargarParalelo() {
        
    }

//    private void limpiar() {
//        try {
//            UtilVista.cargarMateria(cbxMateria);
//            UtilVista.cargarDocente(cbxDocente);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,
//                    "Problemas con los combos de seleccion: " + e.getMessage(),
//                    "Error",
//                    JOptionPane.ERROR_MESSAGE);
//        }
//        txtMatricula.setEnabled(false);
//        cbxMateria.setSelectedIndex(0);
//        cbxDocente.setSelectedIndex(0);
//        tbltabla.clearSelection();
//        cC.setCursa(null);
//        cC.setCursas(new LinkedList<>());
//        cargarTabla();
//    }
    private void limpiar() {
        try {
            UtilVista.cargarMateria(cbxMateria);
            UtilVista.cargarDocente(cbxDocente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Problemas con los combos de seleccion: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        txtMatricula.setEnabled(false);
        tbltabla.clearSelection();
        cC.setCursa(null);
        cC.setCursas(new LinkedList<>());
        cargarTabla();
    }

    private void cargarTabla() {
        mtcc.setCursas(mtcc.getCursas());
        tbltabla.setModel(mtcc);
        tbltabla.updateUI();
    }

    private void cargarVista() {
        Integer fila = tbltabla.getSelectedRow();
        cC.setIndex(fila);
        if (cC.getIndex().intValue() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Selecccione la fila", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                cC.setCursa(mtcc.getCursas().get(cC.getIndex()));
                txtMatricula.setText(cC.getCursa().getId_matricula().toString());
                cbxDocente.setSelectedIndex(cC.getCursa().getId_docente() - 1);
                cbxMateria.setSelectedIndex(cC.getCursa().getId_materia() - 1);
                //cbxPeriodo.setSelectedIndex(cC.getCursa().getId_periodo()- 1);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validar() {
        //return !(cbxestudiante.getSelectedIndex() == -1);
        return m != null
                && txtParalelo.getText() != null;
    }

    private void guardar() {
        Integer respuesta = JOptionPane.showConfirmDialog(null,
                "Realmente desea guardar este nuevo paralelo?",
                "Confirmar guaardado",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            if (validar()) {
                try {
                    cC.getCursa().setId_matricula(m.getId());
                    cC.getCursa().setId_docente(UtilVista.getComboDocentes(cbxDocente).getId());
                    cC.getCursa().setId_materia(UtilVista.getComboMateria(cbxMateria).getId());
                    cC.getCursa().setParalelo(txtParalelo.getText());
                    cC.guardar();
                    cargarTabla();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            e.getMessage(), "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null,
                    "Perfecto ", "OK",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void setearMatricula(Matricula mat) {
        m = mat;
        txtMatricula.setText(m.toString());
    }
    private void ordenar(){
        Integer ascdesc = descendente.getState() ? 1 : 0;
        String criterio = "";
        if(cbxOrdenamiento.getSelectedItem().toString().equalsIgnoreCase("CODIGO CURSA")){
            criterio = "id";
        }else if(cbxOrdenamiento.getSelectedItem().toString().equalsIgnoreCase("MATRICULA")){
            criterio = "id_matricula";
        }else{
            criterio = "id_docente";
        }
//        if (cbxOrdenamiento.getSelectedItem().toString().equalsIgnoreCase("QuickSort")) {
//            vc.quickSort(vc.getVentas(), ascdesc, cbxOrdenarPOR.getSelectedItem().toString());
//            //limpiarVentas();
//            cargarTablaVentas();
//        } else {
//            vc.mergeSort(vc.getVentas(), ascdesc, cbxOrdenarPOR.getSelectedItem().toString());
//            cargarTablaVentas();
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        panelPrincipal = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbxMateria = new javax.swing.JComboBox<>();
        cbxDocente = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        lblPeriodo = new javax.swing.JLabel();
        txtParalelo = new javax.swing.JTextField();
        jblTexto2 = new javax.swing.JLabel();
        cbxOrdenamiento = new javax.swing.JComboBox<>();
        descendente = new java.awt.Checkbox();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltabla = new javax.swing.JTable();
        btncancelar1 = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        jblTexto = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        cbxCriterio = new javax.swing.JComboBox<>();
        jblTexto1 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Matriculas");
        setPreferredSize(new java.awt.Dimension(1140, 572));

        panelPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1140, 642));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Completar los campos:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        panelPrincipal.setBackground(new java.awt.Color(153, 153, 153));
        panelPrincipal.setMinimumSize(new java.awt.Dimension(622, 251));
        panelPrincipal.setPreferredSize(new java.awt.Dimension(1140, 572));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Registrar un nuevo CURSA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT", 1, 14))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setBackground(new java.awt.Color(204, 204, 255));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setText("Paralelo:");
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 94, -1));

        txtMatricula.setEditable(false);
        txtMatricula.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        txtMatricula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMatricula.setText("Seleccione");
        txtMatricula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMatriculaMouseClicked(evt);
            }
        });
        txtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatriculaActionPerformed(evt);
            }
        });
        jPanel6.add(txtMatricula, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 125, -1));

        jLabel33.setBackground(new java.awt.Color(204, 204, 255));
        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setText("Materia:");
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 89, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel6.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 112, 31));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel6.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 112, 30));
        jPanel6.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 120, 30));

        cbxMateria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMateria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMateriaItemStateChanged(evt);
            }
        });
        cbxMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMateriaActionPerformed(evt);
            }
        });
        jPanel6.add(cbxMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 125, -1));

        cbxDocente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDocenteActionPerformed(evt);
            }
        });
        jPanel6.add(cbxDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 125, -1));

        jLabel36.setBackground(new java.awt.Color(204, 204, 255));
        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setText("Docente");
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 89, -1));
        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setText("Docente:");
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 89, -1));

        lblPeriodo.setBackground(new java.awt.Color(204, 204, 255));
        lblPeriodo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPeriodo.setText("Matrícula:");
        jPanel6.add(lblPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        txtParalelo.setEditable(false);
        txtParalelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtParaleloActionPerformed(evt);
            }
        });
        jPanel6.add(txtParalelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 125, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jblTexto2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblTexto2.setText("Ordenar cursas por?");
        jPanel6.add(jblTexto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, -1, -1));

        cbxOrdenamiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CODIGO CURSA", "MATRICULA", "MATERIA" }));
        cbxOrdenamiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxOrdenamientoItemStateChanged(evt);
            }
        });
        cbxOrdenamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOrdenamientoActionPerformed(evt);
            }
        });
        jPanel6.add(cbxOrdenamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 140, -1));

        descendente.setLabel("Descendente");
        jPanel6.add(descendente, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 102, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Registros Existentes:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT", 1, 14))); // NOI18N

        tbltabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbltabla);

        btncancelar1.setText("Seleccionar.");
        btncancelar1.setBackground(new java.awt.Color(204, 204, 255));
        btncancelar1.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        btncancelar1.setText("Seleccionar");
        btncancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar1ActionPerformed(evt);
            }
        });

        jblTexto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblTexto.setText("Texto:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOMBRE", "CURSO" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });

        jblTexto1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblTexto1.setText("Criterio");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 844, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(btncancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jblTexto)
                        .addGap(20, 20, 20)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jblTexto1)
                        .addGap(133, 133, 133))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(btncancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addComponent(btncancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
                .addGap(13, 13, 13)
                .addComponent(jblTexto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jblTexto))
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar)
                        .addComponent(cbxCriterio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncancelar1)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btncancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar1ActionPerformed
        cargarVista();
    }//GEN-LAST:event_btncancelar1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
//        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatriculaActionPerformed
        //LLLamadi a dialoj
    }//GEN-LAST:event_txtMatriculaActionPerformed

    private void cbxDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDocenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDocenteActionPerformed

    private void cbxMateriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMateriaItemStateChanged
        //nadita de nada
    }//GEN-LAST:event_cbxMateriaItemStateChanged

    private void txtParaleloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtParaleloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtParaleloActionPerformed

    private void txtMatriculaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMatriculaMouseClicked
        new DialogSelecMatricula(this, true).setVisible(true);
    }//GEN-LAST:event_txtMatriculaMouseClicked

    private void cbxMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMateriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMateriaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        //buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged
        ordenar();
//        if (evt.getItem().toString().equalsIgnoreCase("NOMBRE")) {
//            txtBusqueda.setVisible(true);
//            cbxCursoB.setVisible(false);
//            jblTexto.setVisible(true);
//            jblCiclo.setVisible(false);
//        } else if (evt.getItem().toString().equalsIgnoreCase("CURSO")) {
//            txtBusqueda.setVisible(false);
//            cbxCursoB.setVisible(true);
//            jblTexto.setVisible(false);
//            jblCiclo.setVisible(true);
//        }
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void cbxOrdenamientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxOrdenamientoItemStateChanged
        ordenar();
    }//GEN-LAST:event_cbxOrdenamientoItemStateChanged

    private void cbxOrdenamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrdenamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxOrdenamientoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCursa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new FrmCursa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btncancelar1;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxDocente;
    private javax.swing.JComboBox<String> cbxMateria;
    private javax.swing.JComboBox<String> cbxOrdenamiento;
    private java.awt.Checkbox descendente;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel jblTexto;
    private javax.swing.JLabel jblTexto1;
    private javax.swing.JLabel jblTexto2;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tbltabla;
    private javax.swing.JTextField txtBusqueda;
    private static javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtParalelo;
    // End of variables declaration//GEN-END:variables
}
