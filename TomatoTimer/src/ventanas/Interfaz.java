/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;


import java.awt.Color;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.sound.sampled.*;
import javax.swing.JLabel;

/**
 *
 * @author Mauricio
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form tomatoTimer
     */
    private int contador = 0;
    private int convertirE1;
    private int convertirE2;
    private int convertirE3 = 1;
    private String StrConE3;
    private int agarrarE3;
    private int copValE3 = 1;
    private int contSonido = 1;
    private int contPVSonido = 0;
//    private int contadorE3;

    private int cantVeces = 0;
    private String cantVecesTxt;
    private Timer tiempo;
    private int segundos = 59;
    private int minutosConc = 24;
    private int minPomPre = 24;
    private int minBrePre = 4;
    private int centesimas_segundos = 0;
    private int guardar = 1;
    private int gTiempoPomodoro1 = 0;
    private int gTiempoBreakTime = 0;
    private String pEtTi;
    private String currentDir = System.getProperty("user.dir");
    
   
    

    private JLabel etiqueta1 = new JLabel("");
    private JLabel etiqueta2 = new JLabel("");
    private JLabel etiqueta3 = new JLabel("");

    public void ReproducirSonido(String nombreSonido) {
//        InputStream path = getClass().getResourceAsStream(nombreSonido);
        try {
            BufferedInputStream bis = new BufferedInputStream(getClass().getResourceAsStream(nombreSonido));
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private ActionListener acciones = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            centesimas_segundos++;
            System.out.println("currentDir = " + currentDir);
            if (agarrarE3 != 1 || guardar == 2) {
                if (gTiempoPomodoro1 != 0 && guardar == 1 && contador == 0) {
                    minutosConc = gTiempoPomodoro1;
                }
                if (gTiempoPomodoro1 == 0 && guardar == 1 && contador == 0) {
                    minutosConc = minPomPre;
                }
                if (guardar == 2 && gTiempoBreakTime != 0 && contador == 0) {
                    minutosConc = gTiempoBreakTime;
                }
                if (guardar == 2 && gTiempoBreakTime == 0 && contador == 0) {
                    minutosConc = minBrePre;
                }
                if (centesimas_segundos == 100) {
                    centesimas_segundos = 0;
                    segundos--;
                }
                if (segundos == 0) {
                    minutosConc--;
                    segundos = 59;
                }
                contador = 1;

                if (minutosConc == -1 && segundos == 59 && centesimas_segundos == 0) {
                    if (convertirE3 > 1 && guardar == 2) {
                        convertirE3--;
                        copValE3 = convertirE3;
                        StrConE3 = String.valueOf(copValE3);
                        CanPom.setText(StrConE3);
                    }
                    if (tiempo.isRunning()) {
                        ReproducirSonido("/notificacion/SD_ALERT_27.wav");
                        tiempo.stop();
                    }

                    Start.setEnabled(
                            true);
                    Pause.setEnabled(
                            false);
                    Stop.setEnabled(
                            false);
                    switch (guardar) {
                        case 1 ->
                            guardar = 2;
                        case 2 ->
                            guardar = 1;
                        case 0 ->
                            guardar = 1;
                        default -> {
                        }
                    }
                    if (gTiempoBreakTime != 0 && guardar == 2) {
                        minutosConc = gTiempoBreakTime;
                        segundos = 59;
                    }
                    if (guardar == 2 && gTiempoBreakTime == 0) {
                        minutosConc = 4;
                        segundos = 59;
                    }
                    if (gTiempoPomodoro1 != 0 && guardar == 1) {
                        minutosConc = gTiempoPomodoro1;
                        segundos = 59;
                    }
                    if (guardar == 1 && gTiempoPomodoro1 == 0) {
                        minutosConc = 24;
                        segundos = 59;
                    }

                    if (convertirE3 == 2 || convertirE3 == 1 && guardar == 1) {
                        contSonido--;
                    }
                    if (convertirE3 == 1 && contSonido == 0 && guardar == 1) {
                        contSonido = 1;
                    }

                    if (guardar == 2 && convertirE3 == 1 && contSonido == -1) {
                        ReproducirSonido("/notificacion/good_bad_ugly_short.wav");
                        contSonido = 1;
                    }
                    contador = 0;

                }

                actualizarEtiquetaTiempo();

            }
        }
    };

    private void actualizarEtiquetaTiempo() {
        String texto = (minutosConc <= 9 ? "0" : "") + minutosConc + ":" + (segundos <= 9 ? "0" : "") + segundos;
        etiquetaTiempo.setText(texto);
    }

    @SuppressWarnings("unchecked")

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPTemp = new javax.swing.JPanel();
        etiquetaTiempo = new javax.swing.JLabel();
        Start = new javax.swing.JButton();
        Pause = new javax.swing.JButton();
        Stop = new javax.swing.JButton();
        jLabelTitulo = new javax.swing.JLabel();
        TiempoBreakTime = new javax.swing.JTextField();
        TituloBreakTime = new javax.swing.JLabel();
        EnviarBreakTime = new javax.swing.JButton();
        TituloPomTime1 = new javax.swing.JLabel();
        TiempoPomodoro1 = new javax.swing.JTextField();
        EnviarPomodoro1 = new javax.swing.JButton();
        Aclaracion = new javax.swing.JLabel();
        APomVeces = new javax.swing.JPanel();
        SCBtn = new javax.swing.JButton();
        CanPom = new javax.swing.JTextField();
        Atras = new javax.swing.JButton();
        Adelante = new javax.swing.JButton();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelPTemp.setBackground(new java.awt.Color(0, 0, 0));

        etiquetaTiempo.setBackground(new java.awt.Color(0, 0, 0));
        etiquetaTiempo.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        etiquetaTiempo.setForeground(new java.awt.Color(255, 255, 255));
        etiquetaTiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaTiempo.setText("25:00");

        Start.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        Start.setText("Start");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });

        Pause.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        Pause.setText("Pause");
        Pause.setEnabled(false);
        Pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PauseActionPerformed(evt);
            }
        });

        Stop.setFont(new java.awt.Font("Roboto", 1, 13)); // NOI18N
        Stop.setText("Stop");
        Stop.setEnabled(false);
        Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopActionPerformed(evt);
            }
        });

        jLabelTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/LogoMakr-3XFgSm.png"))); // NOI18N

        javax.swing.GroupLayout jPanelPTempLayout = new javax.swing.GroupLayout(jPanelPTemp);
        jPanelPTemp.setLayout(jPanelPTempLayout);
        jPanelPTempLayout.setHorizontalGroup(
            jPanelPTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPTempLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaTiempo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPTempLayout.createSequentialGroup()
                        .addComponent(Start, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Pause, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Stop, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPTempLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );
        jPanelPTempLayout.setVerticalGroup(
            jPanelPTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPTempLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(etiquetaTiempo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPTempLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Start)
                    .addComponent(Pause)
                    .addComponent(Stop))
                .addGap(385, 385, 385))
        );

        getContentPane().add(jPanelPTemp, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 290, 170));

        TiempoBreakTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TiempoBreakTimeActionPerformed(evt);
            }
        });
        getContentPane().add(TiempoBreakTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 90, 30));

        TituloBreakTime.setBackground(new java.awt.Color(255, 255, 255));
        TituloBreakTime.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TituloBreakTime.setForeground(new java.awt.Color(255, 255, 255));
        TituloBreakTime.setText(" Break Time:");
        getContentPane().add(TituloBreakTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 90, -1));

        EnviarBreakTime.setText("Send");
        EnviarBreakTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarBreakTimeActionPerformed(evt);
            }
        });
        getContentPane().add(EnviarBreakTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, 60, 30));

        TituloPomTime1.setBackground(new java.awt.Color(255, 255, 255));
        TituloPomTime1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        TituloPomTime1.setForeground(new java.awt.Color(255, 255, 255));
        TituloPomTime1.setText("Pomodoro Time:");
        getContentPane().add(TituloPomTime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        TiempoPomodoro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TiempoPomodoro1ActionPerformed(evt);
            }
        });
        getContentPane().add(TiempoPomodoro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 90, 30));

        EnviarPomodoro1.setText("Send");
        EnviarPomodoro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnviarPomodoro1ActionPerformed(evt);
            }
        });
        getContentPane().add(EnviarPomodoro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 60, 30));

        Aclaracion.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        Aclaracion.setForeground(new java.awt.Color(255, 255, 255));
        Aclaracion.setText("(Only Minutes)");
        getContentPane().add(Aclaracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, -1, -1));

        APomVeces.setBackground(new java.awt.Color(0, 0, 0));

        SCBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SCBtnActionPerformed(evt);
            }
        });

        CanPom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CanPom.setText("1");
        CanPom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CanPomActionPerformed(evt);
            }
        });

        Atras.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Atras.setLabel("<");
        Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasActionPerformed(evt);
            }
        });

        Adelante.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Adelante.setText(">");
        Adelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdelanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout APomVecesLayout = new javax.swing.GroupLayout(APomVeces);
        APomVeces.setLayout(APomVecesLayout);
        APomVecesLayout.setHorizontalGroup(
            APomVecesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(APomVecesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SCBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CanPom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Atras)
                .addGap(5, 5, 5)
                .addComponent(Adelante, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        APomVecesLayout.setVerticalGroup(
            APomVecesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Adelante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(APomVecesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(APomVecesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(CanPom)
                    .addComponent(SCBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Atras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(APomVeces, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 160, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/malte-schmidt-enGr5YbjQKQ-unsplash.jpg"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        // TODO add your handling code here:
        tiempo.start();
        Start.setEnabled(false);
        Start.setText("Restart");
        Pause.setEnabled(true);
        Stop.setEnabled(true);
    }//GEN-LAST:event_StartActionPerformed

    private void PauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PauseActionPerformed
        // TODO add your handling code here:
        tiempo.stop();
        Start.setEnabled(true);
    }//GEN-LAST:event_PauseActionPerformed

    private void StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopActionPerformed
        // TODO add your handling code here:
        if (tiempo.isRunning()) {
            tiempo.stop();
        }
        Start.setEnabled(true);
        Pause.setEnabled(false);
        Stop.setEnabled(false);

        if (convertirE3 > 1 && guardar == 2) {
            convertirE3--;
            copValE3 = convertirE3;
            StrConE3 = String.valueOf(copValE3);
            CanPom.setText(StrConE3);
        }
        if (gTiempoBreakTime != 0 && guardar == 2) {
            minutosConc = gTiempoBreakTime;
            segundos = 59;
        }
        if (guardar == 2 && gTiempoBreakTime == 0) {
            minutosConc = 4;
            segundos = 59;
        }
        if (gTiempoPomodoro1 != 0 && guardar == 1) {
            minutosConc = gTiempoPomodoro1;
            segundos = 59;
        }
        if (guardar == 1 && gTiempoPomodoro1 == 0) {
            minutosConc = 24;
            segundos = 59;
        }
        if (convertirE3 == 2 || convertirE3 == 1 && guardar == 1) {
            contSonido--;
        }
        if (convertirE3 == 1 && contSonido == 0 && guardar == 1) {
            contSonido = 1;
        }

        if (guardar == 2 && convertirE3 == 1 && contSonido == -1) {
            ReproducirSonido("/notificacion/good_bad_ugly_short.wav");
            contSonido = 1; 
        }

        switch (guardar) {
            case 1 ->
                guardar = 2;
            case 2 ->
                guardar = 1;
            case 0 ->
                guardar = 1;
            default -> {
            }
        }
        contador = 0;

    }//GEN-LAST:event_StopActionPerformed

    private void EnviarBreakTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarBreakTimeActionPerformed
        etiqueta2.setText(TiempoBreakTime.getText());
        TiempoBreakTime.setText("");
        convertirE2 = Integer.parseInt(etiqueta2.getText());
        pEtTi = ((convertirE2 <= 9 ? "0" : "") + convertirE2 + ":" + "00");
        etiquetaTiempo.setText(pEtTi);
        System.out.println(etiqueta2);
        gTiempoBreakTime = convertirE2 - 1;
    }//GEN-LAST:event_EnviarBreakTimeActionPerformed

    private void TiempoBreakTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TiempoBreakTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TiempoBreakTimeActionPerformed

    private void TiempoPomodoro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TiempoPomodoro1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TiempoPomodoro1ActionPerformed

    private void EnviarPomodoro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnviarPomodoro1ActionPerformed
        etiqueta1.setText(TiempoPomodoro1.getText());
        TiempoPomodoro1.setText("");
        convertirE1 = Integer.parseInt(etiqueta1.getText());
        pEtTi = ((convertirE1 <= 9 ? "0" : "") + convertirE1 + ":" + "00");
        etiquetaTiempo.setText(pEtTi);
        System.out.println(etiqueta1);
        gTiempoPomodoro1 = convertirE1 - 1;
    }//GEN-LAST:event_EnviarPomodoro1ActionPerformed

    private void SCBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SCBtnActionPerformed
        etiqueta3.setText(CanPom.getText());
        convertirE3 = Integer.parseInt(etiqueta3.getText());
        CanPom.setBackground(Color.BLACK);
        CanPom.setForeground(Color.WHITE);
        agarrarE3 = convertirE3 + 1;
        try {
            Thread.sleep(1000);
            CanPom.setBackground(Color.WHITE);
            CanPom.setForeground(Color.BLACK);
        } catch (InterruptedException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_SCBtnActionPerformed

    private void AdelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdelanteActionPerformed
        cantVeces++;
        Atras.setEnabled(true);
        cantVecesTxt = String.valueOf(cantVeces);
        CanPom.setText(cantVecesTxt);
    }//GEN-LAST:event_AdelanteActionPerformed

    private void CanPomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CanPomActionPerformed

    }//GEN-LAST:event_CanPomActionPerformed

    private void AtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtrasActionPerformed
        if (cantVeces < 2) {
            Atras.setEnabled(false);
        } else if (cantVeces > 1) {
            cantVeces--;
            cantVecesTxt = String.valueOf(cantVeces);
            CanPom.setText(cantVecesTxt);
        }
    }//GEN-LAST:event_AtrasActionPerformed

    /**
     * @param args the command line arguments
     */
    public Interfaz() {

        initComponents();
        APomVeces.setBackground(new java.awt.Color(0, 0, 0, 210));
        this.setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/LogoTomato.png")).getImage());
        this.setLocationRelativeTo(null);
        this.setTitle("Temporizador");
        tiempo = new Timer(10, acciones);
    }

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
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel APomVeces;
    private javax.swing.JLabel Aclaracion;
    private javax.swing.JButton Adelante;
    private javax.swing.JButton Atras;
    private javax.swing.JTextField CanPom;
    private javax.swing.JButton EnviarBreakTime;
    private javax.swing.JButton EnviarPomodoro1;
    private javax.swing.JButton Pause;
    private javax.swing.JButton SCBtn;
    private javax.swing.JButton Start;
    private javax.swing.JButton Stop;
    private javax.swing.JTextField TiempoBreakTime;
    private javax.swing.JTextField TiempoPomodoro1;
    private javax.swing.JLabel TituloBreakTime;
    private javax.swing.JLabel TituloPomTime1;
    private javax.swing.JLabel etiquetaTiempo;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelPTemp;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the etiqueta1
     */
    public JLabel getEtiqueta1() {
        return etiqueta1;
    }

    /**
     * @param etiqueta1 the etiqueta1 to set
     */
    public void setEtiqueta1(JLabel etiqueta1) {
        this.etiqueta1 = etiqueta1;
    }
}
