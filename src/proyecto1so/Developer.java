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
    int diasProduccion;
    
    public Developer (Semaphore sDrive, Semaphore sExclusion,Semaphore sIntegrador,int tipoDeveloper,int dayDuration, int diasProduccion ){
        this.sDrive=sDrive;
        this.sExclusion=sExclusion;
        this.sIntegrador=sIntegrador;
        this.tipoDeveloper=tipoDeveloper;
        this.diasProduccion= diasProduccion;
        
                
    }
    public void run() {
        while (true){
            try{
                //Acquire del semaforo del Drive
                this.sDrive.acquire();
                //tiempo en que va a durar el 'hilo ejecutandose'
                Thread.sleep(Interfaz.diaDuracion*this.diasProduccion);
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
                        Interfaz.guionesP=(Interfaz.guionesP)%Interfaz.guiones.length;
                        
                        
                        //falta unirlo con la interfaz luego
                        break;
                    case 2:
                        //si el elemento es un nivel se le agrega a la variebla de cantidad de nieveles de la interfaz 
                        Interfaz.cantNiveles=Interfaz.cantNiveles+1;
                        // se agrega un 1 en el vector niveles
                        Interfaz.niveles[Interfaz.nivelesP]=1;
                        //actualizamos valor a la siguiente posicion
                        Interfaz.nivelesP=(Interfaz.nivelesP)%Interfaz.niveles.length;
                        
                        break;
                        
                    case 3:
                        //si el elemento es un sprite se le agrega a a la variable de cantidad de sprites de la interfaz
                        Interfaz.cantSprites=Interfaz.cantSprites+1;
                        //se agrega un 1 en el vector sprites
                        Interfaz.sprites[Interfaz.spritesP]=1;
                        //actualizamos valor a la siguiente posicion
                        Interfaz.spritesP=(Interfaz.spritesP)%Interfaz.sprites.length;
                        
                        break;
                        
                    case 4:
                        
                        //si el elemento es de sistema se agrega a la variable de cantidad de sistema de la interfaz
                        Interfaz.cantSistemas=Interfaz.cantSistemas+1;
                        //se agrega un 1 en el vector de sistemas
                        Interfaz.sistemas[Interfaz.sistemasP]=1;
                        //actualizamos valor a a siguiente posicion
                        Interfaz.sistemasP=(Interfaz.sistemasP)%Interfaz.sistemas.length;
                        
                        break;
                        
                    case 5:
                        //si el elemento es de sistema se agrega a la variable de cantidad de sistema de la interfaz
                        Interfaz.cantDLC=Interfaz.cantDLC+1;
                        //se agrega un 1 en el vector de sistemas
                        Interfaz.dlc[Interfaz.dlcP]=1;
                        //actualizamos el valor de la siguiente posicion
                        Interfaz.dlcP=(Interfaz.dlcP)%Interfaz.dlc.length;
                        
                               
                }
                this.sExclusion.release();
                //se le avisa a integrador que ya tiene un elemento
                this.sIntegrador.release();
                
                
          
            } catch (InterruptedException ex) {
                System.out.println("Ha ocurrido un error");
                     
            }
            
        }
        
    }
    
   
    
}
    

