/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1so;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dianasilvadeornelas
 */
public class Interfaz extends javax.swing.JFrame {
    Developer[] developerNarrative;
    Developer[] developerNiveles;
    Developer[] developerSprites;
    Developer[] developerSistemas;
    Developer[] developerDLC;
    Integrador[] integradores;
    Empleado projectManager;
    Empleado director;

    
    //Booleano para que si ya cargue un archivo no me deje cargar otro
    //holaaaa 
    boolean cargado;
    
    //Vectores Almacen de cada Producto
    static int[] guiones;
    static int[] niveles;
    static int[] sprites;
    static int[] sistemas;
    static int[] dlc;
  
    
    //Contadores de productos por los ensambladores. Se utilizan para asignar 0s (Quitar Producto) en la posicion que corresponde en el sentido de izquierda a derecha
    static int guionesE;
    static int nivelesE;
    static int spritesE;
    static int sistemasE;
    static int dlcE;


    //Contadores de productos por los productores. Se utilizan para asignar 1s (Agregar Producto) en la posicion que corresponde en el sentido de izquierda a derecha
    static int guionesP;
    static int nivelesP;
    static int spritesP;
    static int sistemasP;
    static int dlcP;
    
    //Cantidad inicial
    static int cantVideojuegos = 0;
    static int cantGuiones = 0;
    static int cantNiveles = 0;
    static int cantSprites = 0;
    static int cantSistemas = 0;
    static int cantDLC = 0;
    
    //Tamaño del drive (vector)
    static int guionesDrive;
    static int nivelesDrive;
    static int spritesDrive;
    static int sistemasDrive;
    static int dlcDrive;
    

    static int diasEntrega = 0;
    static int diaDuracion = 0;
    static int cantVideojuegosEnviados = 0;
    
    //Numero de developers e integradores
    static int developersGuiones;
    static int developersNiveles;
    static int developersSprites;
    static int developersSistemas;
    static int developersDLC;
    static int integrador;
    
    
     //Semaforos de los developers
    Semaphore driveGuiones;
    Semaphore driveNiveles;
    Semaphore driveSprites;
    Semaphore driveSistemas;
    Semaphore driveDLC;
    

    //Semaforo de exclusion mutua
    Semaphore exclusionGuiones;
    Semaphore exclusionNiveles;
    Semaphore exclusionSprites;
    Semaphore exclusionSistemas;
    Semaphore exclusionDLC;

    //Semaforo de integradores
    Semaphore integradorGuiones;
    Semaphore integradorNiveles;
    Semaphore integradorSprites;
    Semaphore integradorSistemas;
    Semaphore integradorDLC;

    Semaphore sDespacho;
    //Semaphore sEmpleado; 
    
    CargarArchivo c;
    
    //1

    /**
     * Creates new form Interfaz
     */
    public Interfaz() {
        initComponents();
         //this.setTitle("");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        
        Interfaz.guionesDrive = 0;
        Interfaz.nivelesDrive = 0;
        Interfaz.spritesDrive = 0;
        Interfaz.sistemasDrive = 0;
        Interfaz.dlcDrive = 0;
    
        
        
       

        Interfaz.developersGuiones = 0;
        Interfaz.developersNiveles = 0;
        Interfaz.developersSprites = 0;
        Interfaz.developersSistemas = 0;
        Interfaz.developersDLC = 0;
        
        
//        Interfaz.integrador = 0;
        
        this.cargado = false;

        Interfaz.guionesE = 0;
        Interfaz.nivelesE = 0;
        Interfaz.spritesE = 0;
        Interfaz.sistemasE = 0;
        Interfaz.dlcE = 0;

        Interfaz.guionesP = 0;
        Interfaz.nivelesP = 0;
        Interfaz.spritesP = 0;
        Interfaz.sistemasP = 0;
        Interfaz.dlcP = 0;
        
        this.c = new CargarArchivo();
        
        Interfaz.txtGuiones.setText(Integer.toString(Interfaz.developersGuiones));
        Interfaz.txtNiveles.setText(Integer.toString(Interfaz.developersNiveles));
        Interfaz.txtSprites.setText(Integer.toString(Interfaz.developersSprites));
        Interfaz.txtSistemas.setText(Integer.toString(Interfaz.developersNiveles));
        Interfaz.txtDLC.setText(Integer.toString(Interfaz.developersSprites));
        
        
    }

    //Inicializa a mi vector de developers. 
    public void inicializarDevelopers(int productores, Developer[] developer, Semaphore sDrive,
            Semaphore sExclusion, Semaphore sIntegrador, int tipoProductor, int diasProd) {
        for (int i = 0; i < developer.length; i++) {
            if (i < developers) {
                developer[i] = new  Developer(sDrive, sExclusion, sIntegrador, tipoDeveloper, diasProd, true);
                developer[i].start();
            }

        }

    }
     //Llena mis drives de productos (vectores) con 0s (estan vacios)
    public void inicializarDrives(int[] Drive) {
        for (int i = 0; i < Drive.length; i++) {
            Drive[i] = 0;
        }
    }
    
     public void inicializarIntegradores(int integradores, Integrador[] integrador, 
             Semaphore sDriveGuion, Semaphore sDriveNivel, Semaphore sDriveSprite, Semaphore sDriveSistema, Semaphore sDriveDLC,
             Semaphore sExclusionGuion, Semaphore sExclusionNivel, Semaphore sExclusionSprite,  Semaphore sExclusionSistema, Semaphore sExclusionDLC, 
             Semaphore sIntegradorGuiones, Semaphore sIntegradorNiveles, Semaphore sIntegradorSprites, Semaphore sIntegradorSistemas, Semaphore sIntegradorDLC,
             int diasInteg, Semaphore sDespacho) 
     
     {
        for (int i = 0; i < integrador.length; i++) {
            if (i < integradores) {
                integrador[i] = new Integrador(sDriveGuion, sDriveNivel, sDriveSprite, sDriveSistema, sDriveDLC, 
                        sExclusionGuion, sExclusionNivel, sExclusionSprite, sExclusionSistema, sExclusionDLC, 
                        sIntegradorGuiones, sIntegradorNiveles, sIntegradorSprites, sIntegradorSistemas, sIntegradorDLC,
                        diasInteg, sDespacho);
                integrador[i].start();
            }

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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCantGuiones = new javax.swing.JTextField();
        txtCantNiveles = new javax.swing.JTextField();
        txtSCantprites = new javax.swing.JTextField();
        txtCantSistemas = new javax.swing.JTextField();
        txtCantDLC = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        txtGuiones = new javax.swing.JTextField();
        txtNiveles = new javax.swing.JTextField();
        txtSprites = new javax.swing.JTextField();
        txtSistemas = new javax.swing.JTextField();
        txtDLC = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        txtEstadoPM = new javax.swing.JTextField();
        txtEstadoDirector = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCantIntegradores = new javax.swing.JTextField();
        btnIniciar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Numero desarrolladores");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel2.setText("Desarrolladores Guiones");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel3.setText("Desarrolladores Niveles");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jLabel4.setText("Desarrolladores Sprites set");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));

        jLabel6.setText("Desarrolladores Sistemas");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel7.setText("Desarrolladores DLC");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jLabel8.setText("Ganancias");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        jLabel9.setText("Costos");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, -1, -1));

        jLabel10.setText("Utilidad");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        jLabel11.setText("Guiones");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, -1, -1));

        jLabel12.setText("Niveles");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, -1, -1));

        jLabel14.setText("Sprite set");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        jLabel15.setText("Sistemas");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, -1, -1));

        txtCantGuiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantGuionesActionPerformed(evt);
            }
        });
        jPanel1.add(txtCantGuiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 40, -1));
        jPanel1.add(txtCantNiveles, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, 40, 20));

        txtSCantprites.setText("jTextField3");
        jPanel1.add(txtSCantprites, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, -1, -1));

        txtCantSistemas.setText("jTextField4");
        jPanel1.add(txtCantSistemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, -1, -1));

        txtCantDLC.setText("jTextField5");
        jPanel1.add(txtCantDLC, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, -1, -1));

        jLabel17.setText("Que hace director");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, -1, -1));

        jLabel18.setText("Que hace PM");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, -1, -1));

        jLabel19.setText("Dias que faltan para la entrega");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, -1, -1));

        jLabel20.setText("Videojuegos listos DLC");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));

        jLabel21.setText("Videojuegos listos estandar");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 160, 20));
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, -1, -1));

        jLabel23.setText("Cantidad faltas PM");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, -1, -1));

        jLabel24.setText("Dinero descontado PM");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, -1));

        jTextField7.setText("jTextField7");
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));

        txtGuiones.setText("jTextField8");
        txtGuiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuionesActionPerformed(evt);
            }
        });
        jPanel1.add(txtGuiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        txtNiveles.setText("jTextField9");
        jPanel1.add(txtNiveles, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, -1, -1));

        txtSprites.setText("jTextField10");
        jPanel1.add(txtSprites, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        txtSistemas.setText("jTextField11");
        jPanel1.add(txtSistemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, -1));

        txtDLC.setText("jTextField12");
        jPanel1.add(txtDLC, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));

        jTextField13.setText("jTextField13");
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 60, -1));

        jTextField14.setText("jTextField13");
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 60, -1));

        jTextField15.setText("jTextField15");
        jPanel1.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 60, 30));

        jTextField16.setText("jTextField16");
        jPanel1.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, -1));

        jTextField17.setText("jTextField17");
        jPanel1.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, -1, -1));

        jTextField18.setText("jTextField18");
        jPanel1.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, -1, -1));

        txtEstadoPM.setText("jTextField19");
        jPanel1.add(txtEstadoPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 200, -1, -1));

        txtEstadoDirector.setText("jTextField20");
        jPanel1.add(txtEstadoDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, -1, -1));

        jTextField21.setText("jTextField21");
        jPanel1.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, -1, -1));

        jTextField22.setText("jTextField22");
        jPanel1.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, -1, -1));

        jLabel25.setText("DLC");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, -1, -1));

        jLabel16.setText("Integradores");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        txtCantIntegradores.setText("jTextField1");
        jPanel1.add(txtCantIntegradores, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, -1));

        btnIniciar.setText("INICIAR");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        jPanel1.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

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

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void txtCantGuionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantGuionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantGuionesActionPerformed

    private void txtGuionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGuionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGuionesActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
               
//En el if me aseguro que no existan datos erroneos del archivo
        if (Interfaz.diaDuracion > 0 && Interfaz.diasDespacho > 0 && Interfaz.guionesDrive > 0 
                && Interfaz.nivelesDrive > 0 && Interfaz.spritesDrive > 0 && Interfaz.sistemasDrive > 0 && Interfaz.dlcDrive > 0 
              
                && Interfaz.developersGuiones >= 0 && Interfaz.developersNiveles >= 0 && Interfaz.developersSprites >= 0 && Interfaz.developersSistemas >= 0 && Interfaz.developersDLC >= 0 
 
            && Interfaz.integrador >= 0 
                ) {
            this.c.setVisible(false);

     
            //Creo todos mis semaforos. Los de Almacen les paso el valor de 30
            this.driveGuiones = new Semaphore(Interfaz.guionesDrive);
            this.driveNiveles = new Semaphore(Interfaz.nivelesDrive);
            this.driveSprites = new Semaphore(Interfaz.spritesDrive);
            this.driveSistemas = new Semaphore(Interfaz.sistemasDrive);
            this.driveDLC = new Semaphore(Interfaz.dlcDrive);
 
            this.integradorGuiones = new Semaphore(0);
            this.integradorNiveles = new Semaphore(0);
            this.integradorSprites = new Semaphore(0);
            this.integradorSistemas = new Semaphore(0);
            this.integradorDLC = new Semaphore(0);
 
            this.exclusionGuiones = new Semaphore(1);
            this.exclusionNiveles = new Semaphore(1);
            this.exclusionSprites = new Semaphore(1);
            this.exclusionSistemas = new Semaphore(1);
            this.exclusionDLC = new Semaphore(1);
           

            this.sDespacho = new Semaphore(1);
//            this.sEmpleado = new Semaphore(1);

//            this.developerGuiones = new Developer[Interfaz.maxPRuedas];
//            this.developerNivel = new Productor[Interfaz.maxPMotor];
//            this.productorParabrisas = new Productor[Interfaz.maxPParabrisas];
//            this.ensambladores = new Ensamblador[Interfaz.maxEnsambladores];
    }//GEN-LAST:event_btnIniciarActionPerformed

    
    
    
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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnIniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField7;
    public static javax.swing.JTextField txtCantDLC;
    public static javax.swing.JTextField txtCantGuiones;
    public static javax.swing.JTextField txtCantIntegradores;
    public static javax.swing.JTextField txtCantNiveles;
    public static javax.swing.JTextField txtCantSistemas;
    public static javax.swing.JTextField txtDLC;
    private javax.swing.JTextField txtEstadoDirector;
    private javax.swing.JTextField txtEstadoPM;
    public static javax.swing.JTextField txtGuiones;
    public static javax.swing.JTextField txtNiveles;
    public static javax.swing.JTextField txtSCantprites;
    public static javax.swing.JTextField txtSistemas;
    public static javax.swing.JTextField txtSprites;
    // End of variables declaration//GEN-END:variables
}
