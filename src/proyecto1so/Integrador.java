/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;
import java.util.concurrent.Semaphore;
/**
 *
 * @author dianasilvadeornelas
 */
public class Integrador extends Thread {
    
    Semaphore sDriveGuion;
    Semaphore sDriveNivel;
    Semaphore sDriveSprite;
    Semaphore sDriveSistema;
    Semaphore sDriveDLC;
    
    Semaphore sExclusionGuion;
    Semaphore sExclusionNivel;
    Semaphore sExclusionRuedas;
    Semaphore sExclusionSprite;
    Semaphore sExclusionSistema;
    Semaphore sExclusionDLC;
    
    Semaphore sIntegradorGuiones;
    Semaphore sIntegradorNiveles;
    Semaphore sIntegradorSprites;
    Semaphore sIntegradorSistemas;
    Semaphore sIntegradorDLC;
    
    Semaphore sDespacho;
    boolean hired;
    int diasEnsam;
    
    
     public Integrador(Semaphore sDriveGuion, Semaphore sDriveNivel, Semaphore sDriveSprite,Semaphore sDriveSistema, Semaphore sDriveDLC, Semaphore sExclusionGuion, Semaphore sExclusionNivel, Semaphore sExclusionSprite, Semaphore sExclusionSistema, Semaphore sExclusionDLC,
             Semaphore sIntegradorGuion, Semaphore sIntegradorNivel, Semaphore sIntegradorSprite, Semaphore sIntegradorSistema, Semaphore sIntegradorDLC,
     int diasEnsam, Semaphore sDespacho) {
         
        this.sAlmacenMotor = sAlmacenMotor;
        this.sAlmacenParabrisa = sAlmacenParabrisa;
        this.sAlmacenRuedas = sAlmacenRuedas;
        this.sExclusionMotor = sExclusionMotor;
        this.sExclusionParabrisas = sExclusionParabrisas;
        this.sExclusionRuedas = sExclusionRuedas;
        this.sEnsambladorMotor = sEnsambladorMotor;
        this.sEnsambladorParabrisas = sEnsambladorParabrisas;
        this.sEnsambladorRuedas = sEnsambladorRuedas;
        this.hired = hired;
        this.diasEnsam = diasEnsam;
        this.sDespacho = sDespacho;
    }
}
