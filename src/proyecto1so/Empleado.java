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
    
    public Empleado(Semaphore sExclusionGuiones, Semaphore sExclusionNiveles, Semaphore sExclusionSprites, Semaphore sExlusionSistemas,Semaphore sExclusionDLC,Semaphore sEnsambladorGuiones, Semaphore sEnsambladorNiveles, Semaphore sEnsambladorSprites,Semaphore sEnsambladorSistemas, Semaphore sEnsambladorDLC, int horasProjectManager, int horasDirector, int dias, Semaphore sDespacho, Semaphore sEmpleado){
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
        
        
    }

    public void run(){
        while(true){
            try{
                switch(this.tipoEmpleado){
                case 1:
                      this.sEmpleado.acquire();
                    for (int i=0;i<this.horasProjectManager;i++){
                        System.out.println("Viendo streams");
                        this.viendoStreams=true;
                        Thread.sleep((long)((this.horasProjectManager-0.5)*(Interfaz.diaDuracion)/24));
                      
                        System.out.println("Trabajando");
                        this.viendoStreams=false;
                        Thread.sleep((long)((this.horasProjectManager-0.5)*(Interfaz.diaDuracion)/24));
                       
                        
                    }
                
                    Interfaz.txtEstadoPM.setText("Trabajando");
                    Thread.sleep((int)((Interfaz.diaDuracion)/24)*(24-this.horasProjectManager));
                    this.dias=this.dias+1;
                    if (Interfaz.diasEntrega == 0) {
                            Interfaz.diasEntrega = CargarArchivo.auxDiasEntrega;
                        }
                        Interfaz.diasEntrega = Interfaz.diasEntrega - 1;
                        Interfaz.txtDiasEntrega.setText(Integer.toString(Interfaz.diasEntrega));
                        
                        
                        break;
                    this.sEmpleado.release();     
                case 2:
                    
                    if(Interfaz.diasEntrega==0){
                        this.sDespacho.acquire();
                        
                        Interfaz.txtEstadoDirector.setText("Entregando videojuegos");
                        Thread.sleep(Interfaz.diaDuracion);
                        Interfaz.cantVideojuegosEnviados=Interfaz.cantVideojuegosEnviados+Interfaz.cantVideojuegos;
                        Interfaz.cantVideojuegos=0;
                        Interfaz.diasEntrega=CargarArchivo.auxDiasEntrega;
                        Interfaz.txtVideojuegosEnviados.setText((Integer.toString(Interfaz.cantVideojuegosEnviados)));
                        Interfaz.txtVideojuegosDisponibles.setText((Integer.toString(Interfaz.cantVideojuegos)));
                        Interfaz.txtDiasEntrega.setText((Integer.toString(Interfaz.diasEntrega)));
                        this.sDespacho.release();
                        
                        
                        
                    }else{
                    Random randomGenerator= new Random();
                    int numeroRandom=randomGenerator.nextInt(24);
                    //se genera numero random de 1 a 24 (cada uno representa una hora en el dia) eso se multiplica por la conversion de la duracion de una hora en milisegundos del dia para obtener el momento en que se hace el chequeo
                    int horaChequeo= numeroRandom;
                    
                    System.out.println("La hora de chequeo es "+ horaChequeo);
                    
                    Interfaz.txtEstadoDirector.setText("Verificando PM");
                    

                        Thread.sleep((Interfaz.diaDuracion / 24) * (horaChequeo));
                        if (this.viendoStreams=true){
                            Interfaz.sueldoTotalPM=Interfaz.sueldoTotalPM-50;
                            Interfaz.descuentoPM=Interfaz.descuentoPM+50;

                    
                        
                    }
                    }
                        
                    }
                    
                    
                    
                   
                    
          
        }
    }
    }
}
  
    
    

