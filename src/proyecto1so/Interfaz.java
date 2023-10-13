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
    Home home;
    Chart chart;

    //Booleano para que si ya cargue un archivo no me deje cargar otro
    boolean cargado;

    public int maxTrabajadores;

    //Vectores drive de cada Producto
    public int[] guiones;
    public int[] niveles;
    public int[] sprites;
    public int[] sistemas;
    public int[] dlc;

    //Contadores de productos por los ensambladores. Se utilizan para asignar 0s (Quitar Producto) en la spritesEicion que corresponde en el sentido de izquierda a derecha
    public int guionesE;
    public int nivelesE;
    public int spritesE;
    public int sistemasE;
    public int dlcE;

    //Contadores de productos por los productores. Se utilizan para asignar 1s (Agregar Producto) en la posicion que corresponde en el sentido de izquierda a derecha
    public int guionesP;
    public int nivelesP;
    public int spritesP;
    public int sistemasP;
    public int dlcP;

    //Cantidad inicial
    public int cantVideojuegos = 0;
    public int cantVideojuegosDLC = 0;
    public int cantGuiones = 0;
    public int cantNiveles = 0;
    public int cantSprites = 0;
    public int cantSistemas = 0;
    public int cantDLC = 0;

    //Tamaño del drive (vector)
    public int guionesDrive;
    public int nivelesDrive;
    public int spritesDrive;
    public int sistemasDrive;
    public int dlcDrive;

    public int diasDespacho = 0;
    public int diaDuracion = 0;
    public int cantVideojuegosEnviados = 0;

    //Numero de developers e integradores
    public int developersGuiones;
    public int developersNiveles;
    public int developersSprites;
    public int developersSistemas;
    public int developersDLC;
    public int integrador;

    //sueldos
    public int sueldoDeveloperGuiones = 0;
    public int sueldoDeveloperNiveles = 0;
    public int sueldoDeveloperSprites = 0;
    public int sueldoDeveloperSistemas = 0;
    public int sueldoDeveloperDLC = 0;
    public int sueldoIntegrador = 0;
    public int sueldoTotalPM = 0;
    public int sueldoDirector = 0;
    //descuento PM
    public int descuentoPM = 0;
    public int contadorFaltasPM = 0;

    //ganancia,costo y utilidad empresa
    public int gananciaEmpresa = 0;
    public int costoEmpresa = 0;
    public int utilidadEmpresa = 0;
    public String empresa;
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
    public Interfaz(String empresa, Home home) {
        initComponents();
        //this.setTitle("");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.empresa = empresa;
        this.home = home;

        this.guionesDrive = 25;
        this.nivelesDrive = 20;
        this.spritesDrive = 55;
        this.sistemasDrive = 35;
        this.dlcDrive = 10;

        if (empresa == "Nintendo") {
            this.maxTrabajadores = 11;
        } else {
            this.maxTrabajadores = 16;
        }

        this.developersGuiones = 0;
        this.developersNiveles = 0;
        this.developersSprites = 0;
        this.developersSistemas = 0;
        this.developersDLC = 0;

        this.integrador = 0;

        this.cargado = false;

        this.guionesE = 0;
        this.nivelesE = 0;
        this.spritesE = 0;
        this.sistemasE = 0;
        this.dlcE = 0;

        this.guionesP = 0;
        this.nivelesP = 0;
        this.spritesP = 0;
        this.sistemasP = 0;
        this.dlcP = 0;

        this.c = new CargarArchivo(this);

        this.txtGuiones.setText(Integer.toString(this.developersGuiones));
        this.txtNiveles.setText(Integer.toString(this.developersNiveles));
        this.txtSprites.setText(Integer.toString(this.developersSprites));
        this.txtSistemas.setText(Integer.toString(this.developersNiveles));
        this.txtDLC.setText(Integer.toString(this.developersSprites));
        this.txtVideojuegosEstandarListos.setText(Integer.toString(this.cantVideojuegos));
        this.txtVideojuegosDLCListos.setText(Integer.toString(this.cantVideojuegosDLC));
        this.txtEstadoDirector.setText("Trabajando");
        this.txtEstadoPM.setText("Viendo streams");
        this.txtDescuentoPM.setText(Integer.toString(this.descuentoPM));
        this.txtGananciaEmpresa.setText(Integer.toString(this.gananciaEmpresa));
        this.txtCostoEmpresa.setText(Integer.toString(this.costoEmpresa));
        //this.txtUtilidadEmpresa.setText(Integer.toString(this.utilidadEmpresa));
        this.txtCantFaltasPM.setText(Integer.toString(this.contadorFaltasPM));

        this.txtCantIntegradores.setText(Integer.toString(this.integrador));

        this.txtDiasDespacho.setText(Integer.toString(this.diasDespacho));

        this.txtContadorDias.setText(Integer.toString(0));
        setBtnIniciarEnabled(false);
        setBtnContratarEnabled(false);
        setBtnDespedirEnabled(false);

    }

    public int getEmployees() {
        int sum = 0;
        sum = this.developersGuiones + this.developersNiveles + this.developersSprites + this.developersSistemas + this.developersDLC + this.integrador;

        return sum;
    }

        public void despedirIntegradores(Integrador[] integradores) {

         if (this.integrador > 1) {
                    for (int i = integradores.length - 1; i >= 0; i--) {
                        if (integradores[i] != null) {
                            integradores[i].despedir();
                            integradores[i] = null;
                            this.integrador--;
                            this.txtCantIntegradores.setText(Integer.toString(this.integrador));
                            return;
                        }
                    }
                }

    }
    public void despedirDeveloper(Developer[] developers, int cantDeveloper, int n, int maxDeveloper) {

        switch (n) {
            case 1:
                if (this.developersGuiones > 1) {
                    for (int i = developers.length - 1; i >= 0; i--) {
                        if (developers[i] != null) {
                            developers[i].despedir();
                            developers[i] = null;
                            this.developersGuiones--;
                            this.txtGuiones.setText(Integer.toString(this.developersGuiones));
                            return;
                        }
                    }
                }

                break;
            case 2:
                if (this.developersNiveles > 1) {
                    for (int i = developers.length - 1; i >= 0; i--) {
                        if (developers[i] != null) {
                            developers[i].despedir();
                            developers[i] = null;
                            this.developersNiveles--;
                            this.txtNiveles.setText(Integer.toString(this.developersNiveles));
                            return;
                        }
                    }
                }
                break;
            case 3:
                if (this.developersSprites > 1) {
                    for (int i = developers.length - 1; i >= 0; i--) {
                        if (developers[i] != null) {
                            developers[i].despedir();
                            developers[i] = null;
                            this.developersSprites--;
                            this.txtSprites.setText(Integer.toString(this.developersSprites));
                            return;
                        }
                    }
                }
                break;

            case 4:
                if (this.developersSistemas > 1) {
                    for (int i = developers.length - 1; i >= 0; i--) {
                        if (developers[i] != null) {
                            developers[i].despedir();
                            developers[i] = null;
                            this.developersSistemas--;
                            this.txtSistemas.setText(Integer.toString(this.developersSistemas));
                            return;
                        }
                    }
                }
                break;

            case 5:
                if (this.developersDLC > 1) {
                    for (int i = developers.length - 1; i >= 0; i--) {
                        if (developers[i] != null) {
                            developers[i].despedir();
                            developers[i] = null;
                            this.developersDLC--;
                            this.txtDLC.setText(Integer.toString(this.developersDLC));
                            return;
                        }
                    }
                }
                break;

        }

    }

    public void contratarProductor(Developer[] developers, int cantDeveloper, int n, int maxDeveloper) {

        if (getEmployees() < this.maxTrabajadores) {
            switch (n) {
                case 1:
                    developers[cantDeveloper] = new Developer(this.driveGuiones, this.exclusionGuiones, this.integradorGuiones, 1, asignarDiasCantidades(this.empresa, 1)[0], asignarDiasCantidades(this.empresa, 1)[1], true, this);
                    developers[cantDeveloper].start();
                    this.developersGuiones = this.developersGuiones + 1;
                    this.txtGuiones.setText(Integer.toString(this.developersGuiones));
                    break;
                case 2:
                    developers[cantDeveloper] = new Developer(this.driveNiveles, this.exclusionNiveles, this.integradorNiveles, 2, asignarDiasCantidades(this.empresa, 2)[0], asignarDiasCantidades(this.empresa, 2)[1], true, this);
                    developers[cantDeveloper].start();
                    this.developersNiveles = this.developersNiveles + 1;
                    this.txtNiveles.setText(Integer.toString(this.developersNiveles));
                    break;
                case 3:
                    developers[cantDeveloper] = new Developer(this.driveSprites, this.exclusionSprites, this.integradorSprites, 3, asignarDiasCantidades(this.empresa, 3)[0], asignarDiasCantidades(this.empresa, 3)[1], true, this);
                    developers[cantDeveloper].start();
                    this.developersSprites = this.developersSprites + 1;
                    this.txtSprites.setText(Integer.toString(this.developersSprites));
                    break;

                case 4:
                    developers[cantDeveloper] = new Developer(this.driveSistemas, this.exclusionSistemas, this.integradorSistemas, 4, asignarDiasCantidades(this.empresa, 4)[0], asignarDiasCantidades(this.empresa, 4)[1], true, this);
                    developers[cantDeveloper].start();
                    this.developersSistemas = this.developersSistemas + 1;
                    this.txtSistemas.setText(Integer.toString(this.developersSistemas));
                    break;

                case 5:
                    developers[cantDeveloper] = new Developer(this.driveDLC, this.exclusionDLC, this.integradorDLC, 5, asignarDiasCantidades(this.empresa, 5)[0], asignarDiasCantidades(this.empresa, 5)[1], true, this);
                    developers[cantDeveloper].start();
                    this.developersDLC = this.developersDLC + 1;
                    this.txtDLC.setText(Integer.toString(this.developersDLC));
                    break;

            }
        } else {
            System.out.println("NO SE PUEDE");
        }

    }

    public void contratarIntegradores(Integrador[] integradores, int cantIntegradores) {

        if (getEmployees() < this.maxTrabajadores) {
        
            integradores[cantIntegradores] = new Integrador(this.driveGuiones, this.driveNiveles, this.driveSprites, this.driveSistemas, this.driveDLC, this.exclusionGuiones, this.exclusionNiveles, this.exclusionSprites, this.exclusionSistemas, this.exclusionDLC,
                    this.integradorGuiones, this.integradorSprites, this.integradorNiveles, this.integradorSistemas, this.integradorDLC,
                    2, this.sDespacho, this.empresa, true, this);
            integradores[cantIntegradores].start();
            this.integrador = this.integrador + 1;
            this.txtCantIntegradores.setText(Integer.toString(this.integrador));

        } else {
            System.out.println("NO SE PUEDE");
           
        }

    } 
    
    public void setBtnContratarEnabled(boolean enabled){
        contratarGuion.setEnabled(enabled);
        contratarNivel.setEnabled(enabled);
        contratarSprite.setEnabled(enabled);
        contratarSistema.setEnabled(enabled);
        contratarDLC.setEnabled(enabled);
        contratarIntegrador.setEnabled(enabled);
    }
    public void setBtnDespedirEnabled(boolean enabled){
      
        despedirGuion.setEnabled(enabled);
        despedirNivel.setEnabled(enabled);
        despedirSprite.setEnabled(enabled);
        despedirSistema.setEnabled(enabled);
        despedirDLC.setEnabled(enabled);
        despedirIntegrador.setEnabled(enabled);
        
    }


    //Inicializa a mi vector de developers. 
    public void inicializarDevelopers(int developers, Developer[] developer, Semaphore sDrive,
            Semaphore sExclusion, Semaphore sIntegrador, int diasProd, int cant, int tipoDeveloper) {

        for (int i = 0; i < developer.length; i++) {

            if (i < developers) {
                System.out.println(tipoDeveloper);
                developer[i] = new Developer(sDrive, sExclusion, sIntegrador, tipoDeveloper, diasProd, cant, true, this);
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

    public int[] asignarDiasCantidades(String empresa, int tipoDeveloper) {
        int[] diasCantidades = new int[2];
        if (empresa == "Nintendo") {
            switch (tipoDeveloper) {
                case 1: //guion

                    diasCantidades = new int[]{2, 1};
                    break;
                case 2: //niveles

                    diasCantidades = new int[]{2, 1};
                    break;
                case 3: //sprite

                    diasCantidades = new int[]{1, 3};
                    break;
                case 4: //sistema

                    diasCantidades = new int[]{1, 3};
                    break;
                case 5: //dlc

                    diasCantidades = new int[]{3, 1};
                    break;

            }
        } else {
            switch (tipoDeveloper) {
                case 1:
                    diasCantidades = new int[]{4, 1};
                    break;
                case 2:
                    diasCantidades = new int[]{4, 1};
                    break;
                case 3:
                    diasCantidades = new int[]{1, 1};
                    break;
                case 4:
                    diasCantidades = new int[]{1, 5};
                    break;
                case 5:
                    diasCantidades = new int[]{2, 1};
                    break;

            }
        }

        return diasCantidades;
    }

    public void inicializarIntegradores(int integradores, Integrador[] integrador,
            Semaphore sDriveGuion, Semaphore sDriveNivel, Semaphore sDriveSprite, Semaphore sDriveSistema, Semaphore sDriveDLC,
            Semaphore sExclusionGuion, Semaphore sExclusionNivel, Semaphore sExclusionSprite, Semaphore sExclusionSistema, Semaphore sExclusionDLC,
            Semaphore sIntegradorGuiones, Semaphore sIntegradorNiveles, Semaphore sIntegradorSprites, Semaphore sIntegradorSistemas, Semaphore sIntegradorDLC,
            int diasInteg, Semaphore sDespacho) {
        for (int i = 0; i < integrador.length; i++) {
            if (i < integradores) {
                integrador[i] = new Integrador(sDriveGuion, sDriveNivel, sDriveSprite, sDriveSistema, sDriveDLC,
                        sExclusionGuion, sExclusionNivel, sExclusionSprite, sExclusionSistema, sExclusionDLC,
                        sIntegradorGuiones, sIntegradorNiveles, sIntegradorSprites, sIntegradorSistemas, sIntegradorDLC,
                        diasInteg, sDespacho, this.empresa, true, this);
                integrador[i].start();
            }

        }

    }

    public void start() {
        if (this.cargado == true) {
//               setBtnIniciarEnabled(true);
        

            System.out.println("La marca" + this.empresa + "cargo archivos true?" + cargado);

        }
        System.out.println("La marca" + this.empresa + "cargo archivos?" + cargado);

//En el if me aseguro que no existan datos erroneos del archivo
        if (this.diaDuracion > 0 && this.diasDespacho > 0 && this.guionesDrive > 0
                && this.nivelesDrive > 0 && this.spritesDrive > 0 && this.sistemasDrive > 0 && this.dlcDrive > 0
                && this.developersGuiones >= 0 && this.developersNiveles >= 0 && this.developersSprites >= 0 && this.developersSistemas >= 0 && this.developersDLC >= 0
                && this.integrador >= 0) {
            
            this.c.setVisible(false);

            //Creo todos mis semaforos. Los de Almacen les paso el valor de 30
            this.driveGuiones = new Semaphore(this.guionesDrive);
            this.driveNiveles = new Semaphore(this.nivelesDrive);
            this.driveSprites = new Semaphore(this.spritesDrive);
            this.driveSistemas = new Semaphore(this.sistemasDrive);
            this.driveDLC = new Semaphore(this.dlcDrive);

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

            this.guiones = new int[this.guionesDrive];
            this.niveles = new int[this.nivelesDrive];
            this.sprites = new int[this.spritesDrive];
            this.sistemas = new int[this.sistemasDrive];
            this.dlc = new int[this.dlcDrive];
            this.projectManager = new Empleado(this.exclusionGuiones, this.exclusionNiveles, this.exclusionSprites, this.exclusionSistemas, this.exclusionDLC, this.integradorGuiones, this.integradorNiveles, this.integradorSprites, this.integradorSistemas, this.integradorDLC, this.sDespacho, this.sEmpleado, 1, this.empresa, this,chart);
            this.director = new Empleado(this.exclusionGuiones, this.exclusionNiveles, this.exclusionSprites, this.exclusionSistemas, this.exclusionDLC, this.integradorGuiones, this.integradorNiveles, this.integradorSprites, this.integradorSistemas, this.integradorDLC, this.sDespacho, this.sEmpleado, 2, this.empresa, this,chart);
            this.inicializarDrives(this.guiones);
            this.inicializarDrives(this.niveles);
            this.inicializarDrives(this.sprites);
            this.inicializarDrives(this.sistemas);
            this.inicializarDrives(this.dlc);
            this.inicializarDevelopers(this.developersGuiones, this.developerGuiones, this.driveGuiones, this.exclusionGuiones, this.integradorGuiones, asignarDiasCantidades(this.empresa, 1)[0], asignarDiasCantidades(this.empresa, 1)[1], 1);
            this.inicializarDevelopers(this.developersNiveles, this.developerNiveles, this.driveNiveles, this.exclusionNiveles, this.integradorNiveles, asignarDiasCantidades(this.empresa, 2)[0], asignarDiasCantidades(this.empresa, 2)[1], 2);
            this.inicializarDevelopers(this.developersSprites, this.developerSprites, this.driveSprites, this.exclusionSprites, this.integradorSprites, asignarDiasCantidades(this.empresa, 3)[0], asignarDiasCantidades(this.empresa, 3)[1], 3);
            this.inicializarDevelopers(this.developersSistemas, this.developerSistemas, this.driveSistemas, this.exclusionSistemas, this.integradorSistemas, asignarDiasCantidades(this.empresa, 4)[0], asignarDiasCantidades(this.empresa, 4)[1], 4);
            this.inicializarDevelopers(this.developersDLC, this.developerDLC, this.driveDLC, this.exclusionDLC, this.integradorDLC, asignarDiasCantidades(this.empresa, 5)[0], asignarDiasCantidades(this.empresa, 5)[1], 5);
            this.inicializarIntegradores(this.integrador, this.integradores, this.driveGuiones, this.driveNiveles, this.driveSprites, this.driveSistemas, this.driveDLC, this.exclusionGuiones, this.exclusionNiveles, this.exclusionSprites, this.exclusionSistemas, this.exclusionDLC, this.integradorGuiones, this.integradorSprites, this.integradorNiveles, this.integradorSistemas, this.integradorDLC, 2, this.sDespacho);

            this.projectManager.start();
            this.director.start();
            setBtnContratarEnabled(true);
            setBtnDespedirEnabled(true);
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
        despedirNivel = new javax.swing.JButton();
        contratarSprite = new javax.swing.JButton();
        contratarSistema = new javax.swing.JButton();
        contratarGuion = new javax.swing.JButton();
        contratarDLC = new javax.swing.JButton();
        contratarIntegrador = new javax.swing.JButton();
        contratarNivel = new javax.swing.JButton();
        despedirSprite = new javax.swing.JButton();
        despedirSistema = new javax.swing.JButton();
        despedirDLC = new javax.swing.JButton();
        despedirIntegrador = new javax.swing.JButton();
        despedirGuion = new javax.swing.JButton();
        txtMaxDriveSistemas = new javax.swing.JTextField();
        txtMaxDriveDLC = new javax.swing.JTextField();
        txtContadorDias = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("N. Desarrolladores");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(480, 230, 140, 18);

        jLabel2.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("D. Guiones");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(500, 260, 75, 18);

        jLabel3.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("D. Niveles");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(500, 290, 69, 18);

        jLabel4.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("D. Sprites set");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(500, 320, 89, 18);
        jPanel1.add(jLabel5);
        jLabel5.setBounds(100, 240, 0, 0);

        jLabel6.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("D. Sistemas");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(500, 350, 79, 18);

        jLabel7.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("D.DLC");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(500, 380, 40, 18);

        jLabel8.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 51, 255));
        jLabel8.setText("GANANCIAS");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(10, 90, 100, 18);

        jLabel9.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 51, 255));
        jLabel9.setText("COSTOS");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(10, 130, 56, 18);

        jLabel10.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 51, 255));
        jLabel10.setText("UTILIDAD");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(10, 170, 110, 18);

        jLabel11.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Guiones");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(130, 230, 57, 18);

        jLabel12.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Niveles");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(130, 260, 51, 18);
        jPanel1.add(jLabel13);
        jLabel13.setBounds(310, 280, 0, 0);

        jLabel14.setBackground(new java.awt.Color(204, 204, 204));
        jLabel14.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Sprite set");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(130, 290, 64, 18);

        jLabel15.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Sistemas");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(130, 320, 61, 18);

        txtCantGuiones.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtCantGuiones.setForeground(new java.awt.Color(204, 51, 255));
        txtCantGuiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantGuionesActionPerformed(evt);
            }
        });
        jPanel1.add(txtCantGuiones);
        txtCantGuiones.setBounds(210, 230, 40, 30);

        txtCantNiveles.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtCantNiveles.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtCantNiveles);
        txtCantNiveles.setBounds(210, 260, 40, 30);

        txtCantSprites.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtCantSprites.setForeground(new java.awt.Color(204, 51, 255));
        txtCantSprites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantSpritesActionPerformed(evt);
            }
        });
        jPanel1.add(txtCantSprites);
        txtCantSprites.setBounds(210, 290, 40, 30);

        txtCantSistemas.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtCantSistemas.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtCantSistemas);
        txtCantSistemas.setBounds(210, 320, 40, 30);

        txtCantDLC.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtCantDLC.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtCantDLC);
        txtCantDLC.setBounds(210, 350, 40, 30);

        jLabel17.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Que hace director");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(560, 110, 122, 18);

        jLabel18.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Que hace PM");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(590, 80, 91, 18);

        jLabel19.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Due Date");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(300, 410, 80, 18);

        jLabel20.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("DLC LISTOS");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(390, 50, 77, 18);

        jLabel21.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("ESTANDAR LISTOS");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(390, 20, 160, 20);
        jPanel1.add(jLabel22);
        jLabel22.setBounds(500, 90, 0, 0);

        jLabel23.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Cantidad faltas PM");
        jPanel1.add(jLabel23);
        jLabel23.setBounds(550, 140, 132, 18);

        jLabel24.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Dinero descontado PM");
        jPanel1.add(jLabel24);
        jLabel24.setBounds(530, 170, 156, 18);

        jTextField7.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(204, 51, 255));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField7);
        jTextField7.setBounds(630, 230, 50, 20);

        txtGuiones.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtGuiones.setForeground(new java.awt.Color(204, 51, 255));
        txtGuiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGuionesActionPerformed(evt);
            }
        });
        jPanel1.add(txtGuiones);
        txtGuiones.setBounds(630, 260, 50, 20);

        txtNiveles.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtNiveles.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtNiveles);
        txtNiveles.setBounds(630, 290, 50, 20);

        txtSprites.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtSprites.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtSprites);
        txtSprites.setBounds(630, 320, 50, 20);

        txtSistemas.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtSistemas.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtSistemas);
        txtSistemas.setBounds(630, 350, 50, 20);

        txtDLC.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtDLC.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtDLC);
        txtDLC.setBounds(630, 380, 50, 30);

        txtCostoEmpresa.setFont(new java.awt.Font("Futura", 1, 12)); // NOI18N
        txtCostoEmpresa.setForeground(new java.awt.Color(204, 51, 255));
        txtCostoEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoEmpresaActionPerformed(evt);
            }
        });
        jPanel1.add(txtCostoEmpresa);
        txtCostoEmpresa.setBounds(110, 120, 130, 30);

        txtGananciaEmpresa.setFont(new java.awt.Font("Futura", 1, 12)); // NOI18N
        txtGananciaEmpresa.setForeground(new java.awt.Color(204, 51, 255));
        txtGananciaEmpresa.setBorder(null);
        txtGananciaEmpresa.setDisabledTextColor(new java.awt.Color(204, 102, 255));
        txtGananciaEmpresa.setOpaque(true);
        txtGananciaEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGananciaEmpresaActionPerformed(evt);
            }
        });
        jPanel1.add(txtGananciaEmpresa);
        txtGananciaEmpresa.setBounds(110, 80, 130, 30);

        txtUtilidadEmpresa.setFont(new java.awt.Font("Futura", 1, 12)); // NOI18N
        txtUtilidadEmpresa.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtUtilidadEmpresa);
        txtUtilidadEmpresa.setBounds(110, 170, 130, 30);

        txtVideojuegosEstandarListos.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtVideojuegosEstandarListos.setForeground(new java.awt.Color(204, 51, 255));
        txtVideojuegosEstandarListos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVideojuegosEstandarListosActionPerformed(evt);
            }
        });
        jPanel1.add(txtVideojuegosEstandarListos);
        txtVideojuegosEstandarListos.setBounds(560, 20, 80, 30);

        txtVideojuegosDLCListos.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtVideojuegosDLCListos.setForeground(new java.awt.Color(204, 51, 255));
        txtVideojuegosDLCListos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVideojuegosDLCListosActionPerformed(evt);
            }
        });
        jPanel1.add(txtVideojuegosDLCListos);
        txtVideojuegosDLCListos.setBounds(560, 50, 80, 20);

        txtDiasDespacho.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtDiasDespacho.setForeground(new java.awt.Color(255, 0, 51));
        txtDiasDespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasDespachoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDiasDespacho);
        txtDiasDespacho.setBounds(380, 410, 70, 30);

        txtEstadoPM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEstadoPMActionPerformed(evt);
            }
        });
        jPanel1.add(txtEstadoPM);
        txtEstadoPM.setBounds(690, 80, 110, 20);
        jPanel1.add(txtEstadoDirector);
        txtEstadoDirector.setBounds(690, 110, 110, 20);
        jPanel1.add(txtCantFaltasPM);
        txtCantFaltasPM.setBounds(690, 140, 110, 20);
        jPanel1.add(txtDescuentoPM);
        txtDescuentoPM.setBounds(690, 170, 110, 20);

        jLabel25.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("DLC");
        jPanel1.add(jLabel25);
        jLabel25.setBounds(160, 350, 26, 18);

        jLabel16.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Integradores");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(500, 410, 110, 18);

        txtCantIntegradores.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtCantIntegradores.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtCantIntegradores);
        txtCantIntegradores.setBounds(630, 410, 50, 30);

        btnIniciar.setText("INICIAR");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        jPanel1.add(btnIniciar);
        btnIniciar.setBounds(70, 40, 70, 27);

        btnCargar.setText("CARGAR");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCargar);
        btnCargar.setBounds(160, 40, 77, 27);

        jLabel26.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("MAX");
        jPanel1.add(jLabel26);
        jLabel26.setBounds(280, 200, 116, 18);

        txtMaxDriveGuiones.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtMaxDriveGuiones.setForeground(new java.awt.Color(204, 51, 255));
        txtMaxDriveGuiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaxDriveGuionesActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaxDriveGuiones);
        txtMaxDriveGuiones.setBounds(280, 230, 40, 30);

        txtMaxDriveNiveles.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtMaxDriveNiveles.setForeground(new java.awt.Color(204, 51, 255));
        txtMaxDriveNiveles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaxDriveNivelesActionPerformed(evt);
            }
        });
        jPanel1.add(txtMaxDriveNiveles);
        txtMaxDriveNiveles.setBounds(280, 260, 40, 30);

        txtMaxDriveSprites.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtMaxDriveSprites.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtMaxDriveSprites);
        txtMaxDriveSprites.setBounds(280, 290, 40, 30);

        despedirNivel.setText("-");
        despedirNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                despedirNivelActionPerformed(evt);
            }
        });
        jPanel1.add(despedirNivel);
        despedirNivel.setBounds(720, 290, 20, 27);

        contratarSprite.setText("+");
        contratarSprite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contratarSpriteActionPerformed(evt);
            }
        });
        jPanel1.add(contratarSprite);
        contratarSprite.setBounds(690, 320, 20, 27);

        contratarSistema.setText("+");
        contratarSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contratarSistemaActionPerformed(evt);
            }
        });
        jPanel1.add(contratarSistema);
        contratarSistema.setBounds(690, 350, 20, 27);

        contratarGuion.setText("+");
        contratarGuion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contratarGuionActionPerformed(evt);
            }
        });
        jPanel1.add(contratarGuion);
        contratarGuion.setBounds(690, 260, 20, 27);

        contratarDLC.setText("+");
        contratarDLC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contratarDLCActionPerformed(evt);
            }
        });
        jPanel1.add(contratarDLC);
        contratarDLC.setBounds(690, 380, 20, 27);

        contratarIntegrador.setText("+");
        contratarIntegrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contratarIntegradorActionPerformed(evt);
            }
        });
        jPanel1.add(contratarIntegrador);
        contratarIntegrador.setBounds(690, 410, 20, 27);

        contratarNivel.setText("+");
        contratarNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contratarNivelActionPerformed(evt);
            }
        });
        jPanel1.add(contratarNivel);
        contratarNivel.setBounds(690, 290, 20, 27);

        despedirSprite.setText("-");
        despedirSprite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                despedirSpriteActionPerformed(evt);
            }
        });
        jPanel1.add(despedirSprite);
        despedirSprite.setBounds(720, 320, 20, 27);

        despedirSistema.setText("-");
        despedirSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                despedirSistemaActionPerformed(evt);
            }
        });
        jPanel1.add(despedirSistema);
        despedirSistema.setBounds(720, 350, 20, 27);

        despedirDLC.setText("-");
        despedirDLC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                despedirDLCActionPerformed(evt);
            }
        });
        jPanel1.add(despedirDLC);
        despedirDLC.setBounds(720, 380, 20, 27);

        despedirIntegrador.setText("-");
        despedirIntegrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                despedirIntegradorActionPerformed(evt);
            }
        });
        jPanel1.add(despedirIntegrador);
        despedirIntegrador.setBounds(720, 410, 20, 27);

        despedirGuion.setText("-");
        despedirGuion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                despedirGuionActionPerformed(evt);
            }
        });
        jPanel1.add(despedirGuion);
        despedirGuion.setBounds(720, 260, 20, 27);

        txtMaxDriveSistemas.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtMaxDriveSistemas.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtMaxDriveSistemas);
        txtMaxDriveSistemas.setBounds(280, 320, 40, 30);

        txtMaxDriveDLC.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtMaxDriveDLC.setForeground(new java.awt.Color(204, 51, 255));
        jPanel1.add(txtMaxDriveDLC);
        txtMaxDriveDLC.setBounds(280, 350, 40, 30);

        txtContadorDias.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        txtContadorDias.setForeground(new java.awt.Color(204, 51, 255));
        txtContadorDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContadorDiasActionPerformed(evt);
            }
        });
        jPanel1.add(txtContadorDias);
        txtContadorDias.setBounds(210, 410, 60, 30);

        jLabel27.setFont(new java.awt.Font("Futura", 1, 13)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Contador de dias");
        jPanel1.add(jLabel27);
        jLabel27.setBounds(70, 410, 119, 18);

        jLabel28.setBackground(new java.awt.Color(204, 51, 255));
        jLabel28.setFont(new java.awt.Font("Futura", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(204, 51, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Captura de Pantalla 2023-10-12 a la(s) 1.48.57 p. m..png"))); // NOI18N
        jLabel28.setText("jLabel28");
        jLabel28.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel28.setMaximumSize(new java.awt.Dimension(100, 150));
        jLabel28.setMinimumSize(new java.awt.Dimension(100, 150));
        jPanel1.add(jLabel28);
        jLabel28.setBounds(-60, -170, 960, 630);

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
        this.home.start();


    }//GEN-LAST:event_btnIniciarActionPerformed

    private void txtDiasDespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasDespachoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasDespachoActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        if (this.cargado == false) {
            this.c.setVisible(true);
            this.c.setLocationRelativeTo(null);
            //this.cargado = true;
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

    private void txtMaxDriveNivelesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaxDriveNivelesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaxDriveNivelesActionPerformed

    private void contratarGuionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contratarGuionActionPerformed
        // TODO add your handling code here:
        contratarProductor(this.developerGuiones, this.developersGuiones, 1, 16);
    }//GEN-LAST:event_contratarGuionActionPerformed

    private void contratarSpriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contratarSpriteActionPerformed
        // TODO add your handling code here:
        contratarProductor(this.developerSprites, this.developersSprites, 3, 16);
    }//GEN-LAST:event_contratarSpriteActionPerformed

    private void contratarSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contratarSistemaActionPerformed
        // TODO add your handling code here:
        contratarProductor(this.developerSistemas, this.developersSistemas, 4, 16);
    }//GEN-LAST:event_contratarSistemaActionPerformed

    private void contratarNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contratarNivelActionPerformed
        // TODO add your handling code here:
        contratarProductor(this.developerNiveles, this.developersNiveles, 2, 16);
    }//GEN-LAST:event_contratarNivelActionPerformed

    private void contratarDLCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contratarDLCActionPerformed
        // TODO add your handling code here:
        contratarProductor(this.developerDLC, this.developersDLC, 5, 16);
    }//GEN-LAST:event_contratarDLCActionPerformed

    private void contratarIntegradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contratarIntegradorActionPerformed
        // TODO add your handling code here:

        contratarIntegradores(this.integradores, this.integrador);
    }//GEN-LAST:event_contratarIntegradorActionPerformed

    private void despedirGuionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_despedirGuionActionPerformed
        // TODO add your handling code here:
        despedirDeveloper(this.developerGuiones, this.developersGuiones - 1, 1, 16);
    }//GEN-LAST:event_despedirGuionActionPerformed

    private void despedirNivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_despedirNivelActionPerformed
        // TODO add your handling code here:
        despedirDeveloper(this.developerNiveles, this.developersNiveles - 1, 2, 16);
    }//GEN-LAST:event_despedirNivelActionPerformed

    private void despedirSpriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_despedirSpriteActionPerformed
        // TODO add your handling code here:
        despedirDeveloper(this.developerSprites, this.developersSprites - 1, 3, 16);
    }//GEN-LAST:event_despedirSpriteActionPerformed

    private void despedirSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_despedirSistemaActionPerformed
        // TODO add your handling code here:
        despedirDeveloper(this.developerSistemas, this.developersSistemas - 1, 4, 16);
    }//GEN-LAST:event_despedirSistemaActionPerformed

    private void despedirDLCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_despedirDLCActionPerformed
        // TODO add your handling code here:
        despedirDeveloper(this.developerDLC, this.developersDLC - 1, 5, 16);
    }//GEN-LAST:event_despedirDLCActionPerformed

    private void despedirIntegradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_despedirIntegradorActionPerformed
        // TODO add your handling code here:
          despedirIntegradores(this.integradores);
    }//GEN-LAST:event_despedirIntegradorActionPerformed
    public void setBtnIniciarEnabled(boolean enabled) {
        btnIniciar.setEnabled(enabled);
    }

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

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JToggleButton btnIniciar;
    private javax.swing.JButton contratarDLC;
    private javax.swing.JButton contratarGuion;
    private javax.swing.JButton contratarIntegrador;
    private javax.swing.JButton contratarNivel;
    private javax.swing.JButton contratarSistema;
    private javax.swing.JButton contratarSprite;
    private javax.swing.JButton despedirDLC;
    private javax.swing.JButton despedirGuion;
    private javax.swing.JButton despedirIntegrador;
    private javax.swing.JButton despedirNivel;
    private javax.swing.JButton despedirSistema;
    private javax.swing.JButton despedirSprite;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField jTextField7;
    public javax.swing.JTextField txtCantDLC;
    public javax.swing.JTextField txtCantFaltasPM;
    public javax.swing.JTextField txtCantGuiones;
    public javax.swing.JTextField txtCantIntegradores;
    public javax.swing.JTextField txtCantNiveles;
    public javax.swing.JTextField txtCantSistemas;
    public javax.swing.JTextField txtCantSprites;
    public javax.swing.JTextField txtContadorDias;
    public javax.swing.JTextField txtCostoEmpresa;
    public javax.swing.JTextField txtDLC;
    public javax.swing.JTextField txtDescuentoPM;
    public javax.swing.JTextField txtDiasDespacho;
    public javax.swing.JTextField txtEstadoDirector;
    public javax.swing.JTextField txtEstadoPM;
    public javax.swing.JTextField txtGananciaEmpresa;
    public javax.swing.JTextField txtGuiones;
    public javax.swing.JTextField txtMaxDriveDLC;
    public javax.swing.JTextField txtMaxDriveGuiones;
    public javax.swing.JTextField txtMaxDriveNiveles;
    public javax.swing.JTextField txtMaxDriveSistemas;
    public javax.swing.JTextField txtMaxDriveSprites;
    public javax.swing.JTextField txtNiveles;
    public javax.swing.JTextField txtSistemas;
    public javax.swing.JTextField txtSprites;
    public javax.swing.JTextField txtUtilidadEmpresa;
    public javax.swing.JTextField txtVideojuegosDLCListos;
    public javax.swing.JTextField txtVideojuegosEstandarListos;
    // End of variables declaration//GEN-END:variables
}
