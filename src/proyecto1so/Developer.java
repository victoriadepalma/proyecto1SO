/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author dianasilvadeornelas
 */
public class Developer extends Thread {
    Semaphore sDrive;
    Semaphore sExclusion;
    Semaphore sIntegrador;
    int tipoDeveloper;
    float diasProduccion;
    float cantProduccion;
    boolean hired;
    Interfaz Interfaz;
    
    public Developer (Semaphore sDrive, Semaphore sExclusion,Semaphore sIntegrador,int tipoDeveloper, int diasProduccion,int cantProduccion, boolean hired, Interfaz Interfaz ){
        this.sDrive=sDrive;
        this.sExclusion=sExclusion;
        this.sIntegrador=sIntegrador;
        this.tipoDeveloper=tipoDeveloper;
        this.diasProduccion= (float) diasProduccion;
         this.cantProduccion= (float) cantProduccion;
        this.hired=hired;
        this.Interfaz=Interfaz;
    
        }
    public void run() {
        while (this.hired){
            try{
                //Acquire del semaforo del Drive
              
                this.sDrive.acquire();
//                System.out.println("En semaforo drive: "+this.sDrive.availablePermits());
                
            
                //tiempo en que va a durar el 'hilo ejecutandose'
               
                Thread.sleep((long) ((Interfaz.diaDuracion * this.diasProduccion)/this.cantProduccion));
                 
              
                //Acquire del semaforo de Exclusion
                this.sExclusion.acquire();
              
                //agrego los elementos
                switch(this.tipoDeveloper){
                    case 1:
                        //si el elemento es un guion  se le agrega a la variable de cantidad de guiones de la interfaz
                         
                        Interfaz.cantGuiones=Interfaz.cantGuiones+1;
                        // se agrega un 1 en el vector guiones
                        Interfaz.guiones[Interfaz.guionesP]=1;
                        //actualizamos valor de la siguiente posicion 
                        Interfaz.guionesP=(Interfaz.guionesP+1)%Interfaz.guiones.length;
                        Interfaz.txtCantGuiones.setText(Integer.toString(Interfaz.cantGuiones));
                        if(this.tipoDeveloper==1){
//                      System.out.println("Available drive guion : "+this.sDrive.availablePermits());
                        }
                        //falta unirlo con la interfaz luego
                        break;
                    case 2:
                        //si el elemento es un nivel se le agrega a la variebla de cantidad de nieveles de la interfaz 
                        Interfaz.cantNiveles=Interfaz.cantNiveles+1;
                        // se agrega un 1 en el vector niveles
                        Interfaz.niveles[Interfaz.nivelesP]=1;
                         //actualizamos valor a la siguiente posicion
                        Interfaz.nivelesP=(Interfaz.nivelesP+1)%Interfaz.niveles.length;
                        Interfaz.txtCantNiveles.setText(Integer.toString(Interfaz.cantNiveles));
                        if(this.tipoDeveloper==2){
//                      System.out.println("Available drive nivel : "+this.sDrive.availablePermits());
                        }
                        break;
                        
                    case 3:
                        //si el elemento es un sprite se le agrega a a la variable de cantidad de sprites de la interfaz
                        Interfaz.cantSprites=Interfaz.cantSprites+1;
                        //se agrega un 1 en el vector sprites
                        Interfaz.sprites[Interfaz.spritesP]=1;
                        //actualizamos valor a la siguiente posicion
                        Interfaz.spritesP=(Interfaz.spritesP+1)%Interfaz.sprites.length;
                        Interfaz.txtCantSprites.setText(Integer.toString(Interfaz.cantSprites));
//                        System.out.println("posp: "+Interfaz.spritesP);
//                        this.print();
                        if(this.tipoDeveloper==3){
//                      System.out.println("Available drive sprites : "+this.sDrive.availablePermits());
                        }
                        break;
                        
                    case 4:
                        
                        //si el elemento es de sistema se agrega a la variable de cantidad de sistema de la interfaz
                        Interfaz.cantSistemas=Interfaz.cantSistemas+1;
                        //se agrega un 1 en el vector de sistemas
                        Interfaz.sistemas[Interfaz.sistemasP]=1;
                        //actualizamos valor a a siguiente posicion
                        Interfaz.sistemasP=(Interfaz.sistemasP+1)%Interfaz.sistemas.length;
                        Interfaz.txtCantSistemas.setText(Integer.toString(Interfaz.cantSistemas));
                         if(this.tipoDeveloper==4){
//                      System.out.println("Available drive sistema : "+this.sDrive.availablePermits());
                        }
                        break;
                        
                    case 5:
                        //si el elemento es de sistema se agrega a la variable de cantidad de sistema de la interfaz
                        Interfaz.cantDLC=Interfaz.cantDLC+1;
                        //se agrega un 1 en el vector de sistemas
                        Interfaz.dlc[Interfaz.dlcP]=1;
                        //actualizamos el valor de la siguiente posicion
                        Interfaz.dlcP=(Interfaz.dlcP+1)%Interfaz.dlc.length;
                        Interfaz.txtCantDLC.setText(Integer.toString(Interfaz.cantDLC));
                        break;
                        
                               
                }
                this.sExclusion.release();
                //se le avisa a integrador que ya tiene un elemento
             
//                this.sIntegrador.release();
                  //if(this.tipoDeveloper==3){
                      //System.out.println("Available: "+this.sIntegrador.availablePermits());
                  
                  
                        //if(this.tipoDeveloper==3){
                      //System.out.println("Available drive sprites : "+this.sDrive.availablePermits());
                     // System.out.println("Available integrador sprites : "+this.sIntegrador.availablePermits());
                  
                
                        
          
            //}
                        
            }catch (InterruptedException ex) {
                System.out.println("Ha ocurrido un error");
                     
            }
            
        }
        
    }
    
    
//     public void print() {
//               System.out.println("-----------");
//        for (int i = 0; i < Interfaz.sprites.length; i++) {
//      
//            System.out.print(" "+Interfaz.sprites[i]+" ");
//     
//        }
//               System.out.println("");
//            System.out.println("-----------");
//    }
//   
    
    public void despedir(){
        this.hired=false;
    }
    
}


    