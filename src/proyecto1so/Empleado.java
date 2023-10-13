/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author dianasilvadeornelas
 */
public class Empleado extends Thread {
    Semaphore sExclusionGuiones;
    Semaphore sExclusionNiveles;
    Semaphore sExclusionSprites;
    Semaphore sExclusionSistemas;
    Semaphore sExclusionDLC;
    Semaphore sEnsambladorGuiones;
    Semaphore sEnsambladorNiveles;
    Semaphore sEnsambladorSprites;
    Semaphore sEnsambladorSistemas;
    Semaphore sEnsambladorDLC;
    int horasProjectManager;
    int horasDirector;
    int tipoEmpleado;
    Semaphore sDespacho;
    Semaphore sEmpleado;
    int dias;
    int minutosRevision;
    int sueldoPM;
    int sueldoDirector;
    boolean viendoStreams;
    int  gananciasVideojuegoEstandar;
    int gananciasVideojuegoDLC;
    Interfaz Interfaz;
    Chart chart;
     public Empleado(Semaphore sExclusionGuiones, Semaphore sExclusionNiveles, Semaphore sExclusionSprites, Semaphore sExlusionSistemas,Semaphore sExclusionDLC,Semaphore sEnsambladorGuiones, Semaphore sEnsambladorNiveles, Semaphore sEnsambladorSprites,Semaphore sEnsambladorSistemas, Semaphore sEnsambladorDLC,  Semaphore sDespacho, Semaphore sEmpleado, int tipoEmpleado, String empresa, Interfaz Interfaz,Chart chart){
        this.sExclusionGuiones=sExclusionGuiones;
        this.sExclusionNiveles=sExclusionNiveles;
        this.sExclusionNiveles=sExclusionSprites;
        this.sExclusionSistemas=sExlusionSistemas;
        this.sExclusionDLC=sExclusionDLC;
        this.sEnsambladorGuiones=sEnsambladorGuiones;
        this.sEnsambladorNiveles=sEnsambladorNiveles;
        this.sEnsambladorSprites=sEnsambladorSprites;
        this.sEnsambladorSistemas=sEnsambladorSistemas;
        this.sEnsambladorDLC=sEnsambladorDLC;
        this.horasDirector=24; //cambiar
        this.horasProjectManager=16;
        this.tipoEmpleado= tipoEmpleado;
        this.sDespacho=sDespacho;
        this.sEmpleado=sEmpleado;
        this.dias=0;
        this.minutosRevision=25;
        this.sueldoPM=20;
        this.sueldoDirector=30;
        this.viendoStreams=false;
        this.tipoEmpleado=tipoEmpleado;
        this.Interfaz=Interfaz;
        this.chart=chart;
         if (empresa.equals("Nintendo")) {
          
            this.gananciasVideojuegoEstandar = 550000;
            this.gananciasVideojuegoDLC = 600000;
        } else {
           
            this.gananciasVideojuegoEstandar = 400000;
             this.gananciasVideojuegoDLC = 750000;
        }
        
        
    }

    public void run(){
        while(true){
            try{
                switch(this.tipoEmpleado){
                case 1: //Project Manager
                      this.sEmpleado.acquire();
                    for (int i=0;i<this.horasProjectManager;i++){
//                        System.out.println("Viendo streams");
                        this.viendoStreams=true;
                        Interfaz.txtEstadoPM.setText("Viendo streams");
                       
                        Thread.sleep((long)((Interfaz.diaDuracion)/48));
                      
//                        System.out.println("Trabajando");
                        
                        this.viendoStreams=false;
                        Interfaz.txtEstadoPM.setText("Trabajando");
                        Thread.sleep((long)((Interfaz.diaDuracion)/48));
                       
                        
                    }
                    
                
                    Interfaz.txtEstadoPM.setText("Modificando contador");
                     Thread.sleep((long)((Interfaz.diaDuracion)/24)*(24-this.horasProjectManager));
                    this.dias=this.dias+1;
                    Interfaz.txtContadorDias.setText(Integer.toString(this.dias));
                    if(Interfaz.empresa=="Nintendo"){
                      this.chart.addValues();  
                    }
                    
                
                    System.out.println("Finalizacion dia "+this.dias );  
                    if (Interfaz.diasDespacho == 0) {
                            Interfaz.diasDespacho = CargarArchivo.auxDiasDespacho;
                        }
                        Interfaz.diasDespacho = Interfaz.diasDespacho - 1;
                        Interfaz.txtDiasDespacho.setText(Integer.toString(Interfaz.diasDespacho));
                        this.sEmpleado.release(); 
                        
                        break;
                        
                case 2: //Director
                    
                      if(Interfaz.diasDespacho==0){
//                        System.out.println("DESPACHO IGUAL A CERO");
                        this.sDespacho.acquire();
                        
                        Interfaz.txtEstadoDirector.setText("Entregando videojuegos");
                        Thread.sleep(Interfaz.diaDuracion);
                        Interfaz.cantVideojuegosEnviados=Interfaz.cantVideojuegos+Interfaz.cantVideojuegosDLC;
                        Interfaz.gananciaEmpresa += (Interfaz.cantVideojuegos*this.gananciasVideojuegoEstandar) + (Interfaz.cantVideojuegosDLC*this.gananciasVideojuegoDLC); 
                        
//                        System.out.println("cantidad enviados"+Interfaz.cantVideojuegosEnviados);
                        Interfaz.cantVideojuegos=0;
                        Interfaz.cantVideojuegosDLC=0;
                        Interfaz.diasDespacho=CargarArchivo.auxDiasDespacho-1;
                        Interfaz.txtVideojuegosEstandarListos.setText(Integer.toString(Interfaz.cantVideojuegos));
                        Interfaz.txtVideojuegosDLCListos.setText(Integer.toString(Interfaz.cantVideojuegosDLC));
                        
                        
                        //Interfaz.txtVideojuegosDisponibles.setText((Integer.toString(Interfaz.cantVideojuegos)));
                        //Interfaz.txtDiasEntrega.setText((Integer.toString(Interfaz.diasEntrega)));
                        this.sDespacho.release();
                        
                        
                        
                        
                    }else{
                    Random randomGenerator= new Random();
                    int numeroRandom=randomGenerator.nextInt(24);
                    //se genera numero random de 1 a 24 (cada uno representa una hora en el dia) eso se multiplica por la conversion de la duracion de una hora en milisegundos del dia para obtener el momento en que se hace el chequeo
                    int horaChequeo= numeroRandom;
                    
                    //System.out.println("La hora de chequeo es "+ horaChequeo);
                    for (int i=0;i<24;i++){
                    if(i==horaChequeo){
                        Interfaz.txtEstadoDirector.setText("Verificando PM");
                        if (this.viendoStreams=true){
                            Interfaz.sueldoTotalPM=Interfaz.sueldoTotalPM-50;
                    
                            Interfaz.contadorFaltasPM+=1;
                            Interfaz.txtCantFaltasPM.setText(Integer.toString(Interfaz.contadorFaltasPM));
                            
                            
                            //System.out.println("Contador faltas PM -------------------------------: "+ Interfaz.txtCantFaltasPM.getText());
  
                            Interfaz.gananciaEmpresa+=50;
                            Interfaz.utilidadEmpresa = Interfaz.gananciaEmpresa - Interfaz.costoEmpresa; 
                            System.out.println("ganancia" + Interfaz.gananciaEmpresa + "costo" + Interfaz.costoEmpresa);
                            Interfaz.txtUtilidadEmpresa.setText(Integer.toString(Interfaz.utilidadEmpresa));
                            
                            //System.out.println("Gane 50 bucos");
                            Interfaz.txtGananciaEmpresa.setText(Integer.toString(Interfaz.gananciaEmpresa));
                            Interfaz.descuentoPM=Interfaz.descuentoPM+50;
                            Interfaz.txtDescuentoPM.setText(Integer.toString(Interfaz.descuentoPM));

                    }
                        Thread.sleep((Interfaz.diaDuracion / 58));
                        //System.out.println("La hora actual es "+i+" Y mi estado es "+Interfaz.txtEstadoDirector.getText());
                        Interfaz.txtEstadoDirector.setText("Aministrativo");
                        Thread.sleep((Interfaz.diaDuracion / 41));
                        //System.out.println("La hora actual es "+i+" Y mi estado es "+Interfaz.txtEstadoDirector.getText());
                        
                        
                        
                        

                        
                    }else{Interfaz.txtEstadoDirector.setText("Administrativo");
                        Thread.sleep((Interfaz.diaDuracion / 24));
                        //System.out.println("Mi estado es:"+Interfaz.txtEstadoDirector.getText()+" y la hora es "+i);
                      }
                    
                      //System.out.println("Cantidad videojuegos" + Interfaz.cantVideojuegos);
                        //System.out.println("TXT VIDEOJUEGOS" + Interfaz.txtVideojuegosEstandarListos.getText());
                        
                    
                    Interfaz.sueldoDeveloperGuiones+=10*(Interfaz.developersGuiones);
                       // System.out.println("guiones sueldo: "+ Interfaz.sueldoDeveloperGuiones);
                    Interfaz.sueldoDeveloperNiveles+=13*(Interfaz.developersNiveles);
                        //System.out.println("niveles sueldo: "+ Interfaz.sueldoDeveloperNiveles);
                    Interfaz.sueldoDeveloperSprites+=20*(Interfaz.developersSprites);
                        //System.out.println("sprites sueldo: "+ Interfaz.sueldoDeveloperSprites);
                    Interfaz.sueldoDeveloperSistemas+=8*(Interfaz.developersSistemas);
                       /// System.out.println("sistemas sueldo: "+ Interfaz.sueldoDeveloperSistemas);
                    Interfaz.sueldoDeveloperDLC+=17*(Interfaz.developersDLC);
                        //System.out.println("DLC sueldo: "+ Interfaz.sueldoDeveloperDLC);
                    Interfaz.sueldoIntegrador+=25*(Interfaz.integrador);
                        //System.out.println("integrador sueldo: "+ Interfaz.sueldoIntegrador);
                    Interfaz.sueldoTotalPM+=20;
                       // System.out.println("PM sueldo: "+ Interfaz.sueldoTotalPM);
                    Interfaz.sueldoDirector+=30;
                        //System.out.println("Director sueldo: "+ Interfaz.sueldoDirector);
                    Interfaz.costoEmpresa+=(10*(Interfaz.developersGuiones))+(13*(Interfaz.developersNiveles))+(20*(Interfaz.developersSprites))+(8*(Interfaz.developersSistemas))+(17*(Interfaz.developersDLC))+(25*(Interfaz.integrador))+(20)+(30);
                    Interfaz.txtCostoEmpresa.setText(Integer.toString(Interfaz.costoEmpresa));
                    //Interfaz.txtUtilidadEmpresa.setText(Integer.toString(Interfaz.utilidadEmpresa));
                   // System.out.println("Costo: "+ Interfaz.costoEmpresa);
                    //System.out.println("Ganancia: "+ Interfaz.gananciaEmpresa);
                    //System.out.println("Utilidad: "+ Interfaz.utilidadEmpresa);
                        //System.out.println("DESCUENTO PM--------"+ Interfaz.descuentoPM);
                          
                    }
                        
                    }
                }
                     
        }catch (InterruptedException ex) {
                System.out.println("Ocurrió un error!");
    }
    }
}
}

    
    
                    
                        