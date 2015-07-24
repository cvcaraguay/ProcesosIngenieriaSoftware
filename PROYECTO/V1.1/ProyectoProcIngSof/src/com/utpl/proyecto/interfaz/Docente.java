/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utpl.proyecto.interfaz;

import com.utpl.proyecto.Comision;
import com.utpl.proyecto.Personas;
import com.utpl.proyecto.Propuesta;
import com.utpl.proyecto.controladores.ComisionJpaController;
import com.utpl.proyecto.controladores.PropuestaJpaController;
import com.utpl.proyecto.controladores.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cvcaraguay
 */
public class Docente extends javax.swing.JFrame {

    public static EntityManagerFactory fabrica;
    public static PropuestaJpaController controladorPropuesta;
    public static ComisionJpaController controladorComision;
    public static DefaultTableModel modeloTablaPropuestas;
    public static DefaultTableModel modeloTablaPropuestasEvaluar;

    private Personas persona;
    private Propuesta propuesta;

    /**
     * Creates new form Estudiante
     */
    public Docente() {
        initComponents();
    }

    public Docente(Personas persona) {
        this();
        this.persona = persona;
        lblBievenido.setText("Bienvenido " + persona.getNombre() + " " + persona.getApellido() + ".");
        fabrica = Persistence.createEntityManagerFactory("ProyectoCarlosPU");
        controladorPropuesta = new PropuestaJpaController(fabrica);
        controladorComision = new ComisionJpaController(fabrica);

        modeloTablaPropuestas = (DefaultTableModel) tblListaPropuestas.getModel();
        modeloTablaPropuestasEvaluar = (DefaultTableModel) tblListaPropuestasEvaluar.getModel();
        cargarPropuestas();

        Comision c = controladorComision.findComision(1);
        if (c.getIdPersona1().getIdPersona().equals(persona.getIdPersona())
                || c.getIdPersona2().getIdPersona().equals(persona.getIdPersona())
                || c.getIdPersona3().getIdPersona().equals(persona.getIdPersona())
                || c.getIdPersona4().getIdPersona().equals(persona.getIdPersona())) {
            cargarPropuestasAEvaluar();
        }
    }

    public void cargarPropuestas() {
        List<Propuesta> listaPropuestas = controladorPropuesta.findPropuestaPorIdUsuario(persona.getIdPersona());
        if (listaPropuestas != null) {
            for (Propuesta p : listaPropuestas) {
                modeloTablaPropuestas.insertRow(0, new Object[]{
                    p.getIdPropuesta(),
                    p.getTema(),
                    p.getDescripcion(),
                    getFormatoEstado(p.getEstado())
                });
            }
        }
    }

    public void cargarPropuestasAEvaluar() {
        List<Propuesta> listaPropuestas = controladorPropuesta.findPropuestaPendientes();
        if (listaPropuestas != null) {
            for (Propuesta p : listaPropuestas) {
                modeloTablaPropuestasEvaluar.insertRow(0, new Object[]{
                    p.getIdPropuesta(),
                    p.getTema(),
                    p.getDescripcion(),
                    getFormatoEstado(p.getEstado()),
                    p.getIdPersona().getNombre() + " " + p.getIdPersona().getApellido()
                });
            }
        }
    }

    public void removerPropuestas() {
        for (int i = modeloTablaPropuestas.getRowCount() - 1; i >= 0; i--) {
            modeloTablaPropuestas.removeRow(i);
        }
    }

    public void removerPropuestasEvaluar() {
        for (int i = modeloTablaPropuestasEvaluar.getRowCount() - 1; i >= 0; i--) {
            modeloTablaPropuestasEvaluar.removeRow(i);
        }
    }

    public void limpiarFormulario() {
        txtDescripcion.setText("");
        txtPropuesta.setText("");
        btnEditar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }

    public String getFormatoEstado(int estado) {
        switch (estado) {
            case 0:
                return "Pendiente";
            case 1:
                return "Aprobada";
            case 2:
                return "Reprobada";
        }
        return "Pendiente";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBievenido = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtPropuesta = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaPropuestas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListaPropuestasEvaluar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Interfaz de Docente");
        setResizable(false);

        lblBievenido.setText("Bienvenido");

        jLabel2.setText("Nombre de Propuesta:");

        jLabel3.setText("Descripcion de la Propuesta:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPropuesta, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addComponent(txtDescripcion))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditar)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addGap(197, 197, 197))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPropuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnEditar)
                    .addComponent(btnLimpiar))
                .addContainerGap())
        );

        tblListaPropuestas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Propuesta", "Descripción", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListaPropuestas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblListaPropuestasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblListaPropuestas);
        if (tblListaPropuestas.getColumnModel().getColumnCount() > 0) {
            tblListaPropuestas.getColumnModel().getColumn(0).setResizable(false);
            tblListaPropuestas.getColumnModel().getColumn(1).setResizable(false);
            tblListaPropuestas.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblListaPropuestas.getColumnModel().getColumn(2).setResizable(false);
            tblListaPropuestas.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblListaPropuestas.getColumnModel().getColumn(3).setResizable(false);
        }

        tblListaPropuestasEvaluar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Propuesta", "Descripción", "Estado", "Persona"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListaPropuestasEvaluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblListaPropuestasEvaluarMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblListaPropuestasEvaluar);
        if (tblListaPropuestasEvaluar.getColumnModel().getColumnCount() > 0) {
            tblListaPropuestasEvaluar.getColumnModel().getColumn(0).setResizable(false);
            tblListaPropuestasEvaluar.getColumnModel().getColumn(1).setResizable(false);
            tblListaPropuestasEvaluar.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblListaPropuestasEvaluar.getColumnModel().getColumn(2).setResizable(false);
            tblListaPropuestasEvaluar.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblListaPropuestasEvaluar.getColumnModel().getColumn(3).setResizable(false);
            tblListaPropuestasEvaluar.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setText("Propuestas a Evaluar:");

        jLabel4.setText("Propuestas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBievenido)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBievenido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (txtPropuesta.getText().equals("") || txtDescripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Por favor, llene los campos.");
        } else {
            if (!txtPropuesta.getText().equals(propuesta.getTema()) || !txtDescripcion.getText().equals(propuesta.getDescripcion())) {
                propuesta.setTema(txtPropuesta.getText());
                propuesta.setDescripcion(txtDescripcion.getText());

                try {
                    controladorPropuesta.edit(propuesta);
                    limpiarFormulario();
                    removerPropuestas();
                    cargarPropuestas();
                    JOptionPane.showMessageDialog(this, "Propuesta editada correctamente.");
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showConfirmDialog(this, "Aun no ha realizado cambios.");
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtPropuesta.getText().equals("") || txtDescripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Por favor, llene los campos.");
        } else {
            controladorPropuesta.create(new Propuesta(txtPropuesta.getText(), txtDescripcion.getText(), 0, persona));
            limpiarFormulario();
            removerPropuestas();
            removerPropuestasEvaluar();
            cargarPropuestas();
            cargarPropuestasAEvaluar();
            JOptionPane.showMessageDialog(this, "Propuesta registrada correctamente.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblListaPropuestasEvaluarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaPropuestasEvaluarMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblListaPropuestasEvaluarMousePressed

    private void tblListaPropuestasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaPropuestasMousePressed
        txtPropuesta.setText(modeloTablaPropuestas.getValueAt(tblListaPropuestas.getSelectedRow(), 1).toString());
        txtDescripcion.setText(modeloTablaPropuestas.getValueAt(tblListaPropuestas.getSelectedRow(), 2).toString());
        btnEditar.setEnabled(true);
        btnGuardar.setEnabled(false);

        propuesta = controladorPropuesta.findPropuesta((Integer) modeloTablaPropuestas.getValueAt(tblListaPropuestas.getSelectedRow(), 0));
    }//GEN-LAST:event_tblListaPropuestasMousePressed

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
            java.util.logging.Logger.getLogger(Docente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Docente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Docente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Docente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Docente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBievenido;
    private javax.swing.JTable tblListaPropuestas;
    private javax.swing.JTable tblListaPropuestasEvaluar;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtPropuesta;
    // End of variables declaration//GEN-END:variables
}
