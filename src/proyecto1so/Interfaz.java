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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


/**
 *
 * @author dianasilvadeornelas
 */
public class Interfaz extends javax.swing.JFrame {
    Developer[] developerGuiones;
    Developer[] developerNiveles;
    Developer[] developerSprites;
    Developer[] developerSistemas;
    Developer[] developerDLC;
    Integrador[] integradores;
    Empleado projectManager;
    Empleado director;

    

    
    //Booleano para que si ya cargue un archivo no me deje cargar otro

    boolean cargado;
    
    //Vectores Almacen de cada Producto
    static int[] guiones;
    static int[] niveles;
    static int[] sprites;
    static int[] sistemas;
    static int[] dlc;
  
    
    //Contadores de productos por los ensambladores. Se utilizan para asignar 0s (Quitar Producto) en la spritesEicion que corresponde en el sentido de izquierda a derecha
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
     static int cantVideojuegosDLC = 0;
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
    

    static int diasDespacho = 0;
    static int diaDuracion = 0;
    static int cantVideojuegosEnviados = 0;
    
    //Numero de developers e integradores
    static int developersGuiones;
    static int developersNiveles;
    static int developersSprites;
    static int developersSistemas;
    static int developersDLC;
    static int integrador;
    
    //sueldos
    
        static int sueldoDeveloperGuiones=0;
    static int sueldoDeveloperNiveles=0;
    static int sueldoDeveloperSprites=0;
    static int sueldoDeveloperSistemas=0;
    static int sueldoDeveloperDLC=0;
    static int sueldoIntegrador=0;
    static int sueldoTotalPM=0;
    static int sueldoDirector=0;
    //descuento PM
    static int descuentoPM=0;
    static int contadorFaltasPM=0;
    
    //ganancia,costo y utilidad empresa
    
    static int gananciaEmpresa=0;
    static int costoEmpresa=0;
    static int utilidadEmpresa = 0; 
    //static int utilidadEmpresa=gananciaEmpresa-costoEmpresa;
    

    
    
    
    
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
    Semaphore sEmpleado; 
    
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
        

   
        
        Interfaz.guionesDrive = 25;
        Interfaz.nivelesDrive = 20;
        Interfaz.spritesDrive = 55;
        Interfaz.sistemasDrive = 35;
        Interfaz.dlcDrive = 10;
    
        
        
       

        Interfaz.developersGuiones = 0;
        Interfaz.developersNiveles = 0;
        Interfaz.developersSprites = 0;
        Interfaz.developersSistemas = 0;
        Interfaz.developersDLC = 0;
        
        
        Interfaz.integrador = 0;
        
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
        Interfaz.txtVideojuegosEstandarListos.setText(Integer.toString(Interfaz.cantVideojuegos));
        Interfaz.txtVideojuegosDLCListos.setText(Integer.toString(Interfaz.cantVideojuegosDLC));
        Interfaz.txtEstadoDirector.setText("Trabajando");
        Interfaz.txtEstadoPM.setText("Viendo streams");
        Interfaz.txtDescuentoPM.setText(Integer.toString(Interfaz.descuentoPM));
        Interfaz.txtGananciaEmpresa.setText(Integer.toString(Interfaz.gananciaEmpresa));
        Interfaz.txtCostoEmpresa.setText(Integer.toString(Interfaz.costoEmpresa));
        //Interfaz.txtUtilidadEmpresa.setText(Integer.toString(Interfaz.utilidadEmpresa));
        Interfaz.txtCantFaltasPM.setText(Integer.toString(Interfaz.contadorFaltasPM));
      
        
        
        
        Interfaz.txtCantIntegradores.setText(Integer.toString(Interfaz.integrador));
        
        Interfaz.txtDiasDespacho.setText(Integer.toString(Interfaz.diasDespacho));
        
        Interfaz.txtContadorDias.setText(Integer.toString(0));
        
        
    }

    
    
    //Inicializa a mi vector de developers. 
    public void inicializarDevelopers(int developers, Developer[] developer, Semaphore sDrive,
            Semaphore sExclusion, Semaphore sIntegrador,  int diasProd,int cant,int tipoDeveloper) {

        for (int i = 0; i < developer.length; i++) {
    
            if (i < developers) {
                System.out.println(tipoDeveloper);
                developer[i] = new  Developer(sDrive, sExclusion, sIntegrador, tipoDeveloper, diasProd,cant, true);
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
     public int[] asignarDiasCantidades(String empresa, int tipoDeveloper){
        int [] diasCantidades=new int[2];
        if(empresa=="Nintendo"){
            switch(tipoDeveloper){
                case 1: //guion
                  
                   diasCantidades=new int[]{2,1};
                   break;
                case 2: //niveles
                  
                    diasCantidades=new int[]{2,1};
                      break;
                case 3: //sprite
                    
                    diasCantidades=new int []{1,3};
                      break;
                case 4: //sistema
                   
                    diasCantidades=new int[]{1,3};
                      break;
                case 5: //dlc
                   
                    diasCantidades=new int[]{3,1};
                      break;
           
                
            }
        }else{
            switch(tipoDeveloper){
                case 1:
                   diasCantidades=new int[]{4,1};
                     break;
                case 2:
                    diasCantidades=new int[]{4,1};
                      break;
                case 3:
                    diasCantidades=new int []{1,1};
                      break;
                case 4:
                    diasCantidades=new int[]{1,5};
                      break;
                case 5:
                    diasCantidades=new int[]{2,1};
                      break;
           
                
            }
        }
        
        return diasCantidades;
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
                        diasInteg, sDespacho,"Nintendo",true);
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
        txtCantSprites = new javax.swing.JTextField();
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
        txtCostoEmpresa = new javax.swing.JTextField();
        txtGananciaEmpresa = new javax.swing.JTextField();
        txtUtilidadEmpresa = new javax.swing.JTextField();
        txtVideojuegosEstandarListos = new javax.swing.JTextField();
        txtVideojuegosDLCListos = new javax.swing.JTextField();
        txtDiasDespacho = new javax.swing.JTextField();
        txtEstadoPM = new javax.swing.JTextField();
        txtEstadoDirector = new javax.swing.JTextField();
        txtCantFaltasPM = new javax.swing.JTextField();
        txtDescuentoPM = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCantIntegradores = new javax.swing.JTextField();
        btnIniciar = new javax.swing.JToggleButton();
        btnCargar = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtMaxDriveGuiones = new javax.swing.JTextField();
        txtMaxDriveNiveles = new javax.swing.JTextField();
        txtMaxDriveSprites = new javax.swing.JTextField();
        txtMaxDriveSistemas = new javax.swing.JTextField();
        txtMaxDriveDLC = new javax.swing.JTextField();
        txtContadorDias = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();

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
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jLabel9.setText("Costos");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, -1));

        jLabel10.setText("Utilidad");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

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

        txtCantSprites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantSpritesActionPerformed(evt);
            }
        });
        jPanel1.add(txtCantSprites, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, 50, -1));
        jPanel1.add(txtCantSistemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 50, -1));
        jPanel1.add(txtCantDLC, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 50, -1));

        jLabel17.setText("Que hace director");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, -1, -1));

        jLabel18.setText("Que hace PM");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        jLabel19.setText("Dias que faltan para la entrega");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, -1, -1));

        jLabel20.setText("Videojuegos listos DLC");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, -1));

        jLabel21.setText("Videojuegos listos estandar");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 160, 20));
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, -1, -1));

        jLabel23.setText("Cantidad faltas PM");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, -1, -1));

        jLabel24.setText("Dinero descontado PM");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 320, -1, -1));

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 50, 30));

        txtGuiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuionesActionPerformed(evt);
            }
        });
        jPanel1.add(txtGuiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 40, 30));
        jPanel1.add(txtNiveles, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 40, -1));
        jPanel1.add(txtSprites, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 40, 30));
        jPanel1.add(txtSistemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 60, 30));
        jPanel1.add(txtDLC, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 50, -1));

        txtCostoEmpresa.setText("jTextField13");
        txtCostoEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoEmpresaActionPerformed(evt);
            }
        });
        jPanel1.add(txtCostoEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 130, 30));

        txtGananciaEmpresa.setText("jTextField13");
        txtGananciaEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGananciaEmpresaActionPerformed(evt);
            }
        });
        jPanel1.add(txtGananciaEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 170, 30));

        txtUtilidadEmpresa.setText("jTextField15");
        jPanel1.add(txtUtilidadEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 160, 30));

        txtVideojuegosEstandarListos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVideojuegosEstandarListosActionPerformed(evt);
            }
        });
        jPanel1.add(txtVideojuegosEstandarListos, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 80, -1));

        txtVideojuegosDLCListos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVideojuegosDLCListosActionPerformed(evt);
            }
        });
        jPanel1.add(txtVideojuegosDLCListos, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 70, 30));

        txtDiasDespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasDespachoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDiasDespacho, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 80, 30));

        txtEstadoPM.setText("jTextField19");
        txtEstadoPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoPMActionPerformed(evt);
            }
        });
        jPanel1.add(txtEstadoPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, -1, -1));

        txtEstadoDirector.setText("jTextField20");
        jPanel1.add(txtEstadoDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, -1, -1));

        txtCantFaltasPM.setText("jTextField21");
        jPanel1.add(txtCantFaltasPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, -1, -1));

        txtDescuentoPM.setText("jTextField22");
        jPanel1.add(txtDescuentoPM, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 320, -1, -1));

        jLabel25.setText("DLC");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, -1, -1));

        jLabel16.setText("Integradores");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));
        jPanel1.add(txtCantIntegradores, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, 40, -1));

        btnIniciar.setText("INICIAR");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        jPanel1.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, -1, -1));

        btnCargar.setText("CARGAR");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, -1, -1));

        jLabel26.setText("Maximos en Drive");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, -1, -1));

        txtMaxDriveGuiones.setText("jTextField1");
        txtMaxDriveGuiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaxDriveGuionesActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaxDriveGuiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, -1));

        txtMaxDriveNiveles.setText("jTextField2");
        jPanel1.add(txtMaxDriveNiveles, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, -1, -1));

        txtMaxDriveSprites.setText("jTextField3");
        jPanel1.add(txtMaxDriveSprites, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, -1, -1));

        txtMaxDriveSistemas.setText("jTextField4");
        jPanel1.add(txtMaxDriveSistemas, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, -1, -1));

        txtMaxDriveDLC.setText("jTextField5");
        jPanel1.add(txtMaxDriveDLC, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, -1, -1));

        txtContadorDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContadorDiasActionPerformed(evt);
            }
        });
        jPanel1.add(txtContadorDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 410, 60, -1));

        jLabel27.setText("Contador de dias");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCostoEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoEmpresaActionPerformed

    private void txtGananciaEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGananciaEmpresaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGananciaEmpresaActionPerformed

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
            this.sEmpleado = new Semaphore(1);

           this.developerGuiones = new Developer[16];
           this.developerNiveles = new Developer[16];            
           this.developerSprites = new Developer[16];
           this.developerSistemas = new Developer[16];
            this.developerDLC = new Developer[16];
           this.integradores = new Integrador[16];
            System.out.println(this.developerGuiones.length);
           
            Interfaz.guiones = new int[Interfaz.guionesDrive];
            Interfaz.niveles = new int[Interfaz.nivelesDrive];
            Interfaz.sprites = new int[Interfaz.spritesDrive];
            Interfaz.sistemas = new int[Interfaz.sistemasDrive];
            Interfaz.dlc = new int[Interfaz.dlcDrive];
            this.projectManager = new Empleado(this.exclusionGuiones, this.exclusionNiveles, this.exclusionSprites, this.exclusionSistemas,this.exclusionDLC,this.integradorGuiones, this.integradorNiveles, this.integradorSprites,this.integradorSistemas, this.integradorDLC, this.sDespacho, this.sEmpleado,1, "Nintendo");
            this.director = new Empleado(this.exclusionGuiones, this.exclusionNiveles, this.exclusionSprites, this.exclusionSistemas,this.exclusionDLC,this.integradorGuiones, this.integradorNiveles, this.integradorSprites,this.integradorSistemas, this.integradorDLC, this.sDespacho, this.sEmpleado,2, "Nintendo");
            this.inicializarDrives(Interfaz.guiones);
            this.inicializarDrives(Interfaz.niveles);
            this.inicializarDrives(Interfaz.sprites);
            this.inicializarDrives(Interfaz.sistemas);
            this.inicializarDrives(Interfaz.dlc);
            this.inicializarDevelopers(Interfaz.developersGuiones, this.developerGuiones, this.driveGuiones, this.exclusionGuiones, this.integradorGuiones,asignarDiasCantidades("Nintendo", 1)[0],asignarDiasCantidades("Nintendo", 1)[1], 1);
           this.inicializarDevelopers(Interfaz.developersNiveles, this.developerNiveles, this.driveNiveles, this.exclusionNiveles, this.integradorNiveles,asignarDiasCantidades("Nintendo", 2)[0],asignarDiasCantidades("Nintendo", 2)[1], 2);
          this.inicializarDevelopers(Interfaz.developersSprites, this.developerSprites, this.driveSprites, this.exclusionSprites, this.integradorSprites,asignarDiasCantidades("Nintendo", 3)[0],asignarDiasCantidades("Nintendo", 3)[1], 3);
            this.inicializarDevelopers(Interfaz.developersSistemas, this.developerSistemas, this.driveSistemas, this.exclusionSistemas, this.integradorSistemas,asignarDiasCantidades("Nintendo", 4)[0],asignarDiasCantidades("Nintendo", 4)[1], 4);
          this.inicializarDevelopers(Interfaz.developersDLC, this.developerDLC, this.driveDLC, this.exclusionDLC, this.integradorDLC,asignarDiasCantidades("Nintendo", 5)[0],asignarDiasCantidades("Nintendo", 5)[1], 5);
          this.inicializarIntegradores(Interfaz.integrador, this.integradores, this.driveGuiones, this.driveNiveles, this.driveSprites, this.driveSistemas, this.driveDLC,this.exclusionGuiones, this.exclusionNiveles, this.exclusionSprites,  this.exclusionSistemas, this.exclusionDLC,this.integradorGuiones, this.integradorSprites, this.integradorNiveles, this.integradorSistemas, this.integradorDLC,2, this.sDespacho);

           this.projectManager.start();
           this.director.start();
        }
        
        
        
        
        
        

    }//GEN-LAST:event_btnIniciarActionPerformed

    private void txtDiasDespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasDespachoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasDespachoActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        if (this.cargado == false) {
            this.c.setVisible(true);
            this.c.setLocationRelativeTo(null);
            this.cargado = true;
        } else {
            JOptionPane.showMessageDialog(this, "Ya existe un archivo cargado");
        }

                 // TODO add your handling code here:
    }//GEN-LAST:event_btnCargarActionPerformed

    private void txtMaxDriveGuionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaxDriveGuionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaxDriveGuionesActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void txtEstadoPMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstadoPMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoPMActionPerformed

    private void txtCantSpritesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantSpritesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantSpritesActionPerformed

    private void txtContadorDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContadorDiasActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtContadorDiasActionPerformed

    private void txtVideojuegosEstandarListosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVideojuegosEstandarListosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVideojuegosEstandarListosActionPerformed

    private void txtVideojuegosDLCListosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVideojuegosDLCListosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVideojuegosDLCListosActionPerformed

   
    
    
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
    private javax.swing.JButton btnCargar;
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
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField7;
    public static javax.swing.JTextField txtCantDLC;
    public static javax.swing.JTextField txtCantFaltasPM;
    public static javax.swing.JTextField txtCantGuiones;
    public static javax.swing.JTextField txtCantIntegradores;
    public static javax.swing.JTextField txtCantNiveles;
    public static javax.swing.JTextField txtCantSistemas;
    public static javax.swing.JTextField txtCantSprites;
    public static javax.swing.JTextField txtContadorDias;
    public static javax.swing.JTextField txtCostoEmpresa;
    public static javax.swing.JTextField txtDLC;
    public static javax.swing.JTextField txtDescuentoPM;
    public static javax.swing.JTextField txtDiasDespacho;
    public static javax.swing.JTextField txtEstadoDirector;
    public static javax.swing.JTextField txtEstadoPM;
    public static javax.swing.JTextField txtGananciaEmpresa;
    public static javax.swing.JTextField txtGuiones;
    public static javax.swing.JTextField txtMaxDriveDLC;
    public static javax.swing.JTextField txtMaxDriveGuiones;
    public static javax.swing.JTextField txtMaxDriveNiveles;
    public static javax.swing.JTextField txtMaxDriveSistemas;
    public static javax.swing.JTextField txtMaxDriveSprites;
    public static javax.swing.JTextField txtNiveles;
    public static javax.swing.JTextField txtSistemas;
    public static javax.swing.JTextField txtSprites;
    public static javax.swing.JTextField txtUtilidadEmpresa;
    public static javax.swing.JTextField txtVideojuegosDLCListos;
    public static javax.swing.JTextField txtVideojuegosEstandarListos;
    // End of variables declaration//GEN-END:variables
}
