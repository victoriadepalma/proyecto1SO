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
    Semaphore sIntegradorNivel;
    Semaphore sIntegradorSprite;
    Semaphore sIntegradorSistema;
    Semaphore sIntegradorDLC;

    Semaphore sDespacho;
    int diasEnsam;
    String empresa;
    int cantGuionesEnsamblar;
    int cantNivelesEnsamblar;
    int cantSpritesEnsamblar;
    int cantSistemasEnsamblar;
    int cantDLCEnsamblar;
    boolean hired;
    int contVideos;
    int gananciasVideojuegoEstandar;
    int gananciasVideojuegoDLC;

    public Integrador(Semaphore sDriveGuion, Semaphore sDriveNivel, Semaphore sDriveSprite, Semaphore sDriveSistema, Semaphore sDriveDLC, Semaphore sExclusionGuion, Semaphore sExclusionNivel, Semaphore sExclusionSprite, Semaphore sExclusionSistema, Semaphore sExclusionDLC, Semaphore sIntegradorGuiones, Semaphore sIntegradorNivel, Semaphore sIntegradorSprite, Semaphore sIntegradorSistema, Semaphore sIntegradorDLC, int diasEnsam, Semaphore sDespacho, String empresa, boolean hired) {
        this.sDriveGuion = sDriveGuion;
        this.sDriveNivel = sDriveNivel;
        this.sDriveSprite = sDriveSprite;
        this.sDriveSistema = sDriveSistema;
        this.sDriveDLC = sDriveDLC;
        this.sExclusionGuion = sExclusionGuion;
        this.sExclusionNivel = sExclusionNivel;
        this.sExclusionSprite = sExclusionSprite;
        this.sExclusionSistema = sExclusionSistema;
        this.sExclusionDLC = sExclusionDLC;
        this.sIntegradorGuiones = sIntegradorGuiones;
        this.sIntegradorNivel = sIntegradorNivel;
        this.sIntegradorSprite = sIntegradorSprite;
        this.sIntegradorSistema = sIntegradorSistema;
        this.sIntegradorDLC = sIntegradorDLC;
        this.sDespacho = sDespacho;
        this.diasEnsam = diasEnsam;
        this.empresa = empresa;
        this.contVideos = 0;
        this.hired = hired;
        if (empresa.equals("Nintendo")) {
            this.cantGuionesEnsamblar = 2;
            this.cantNivelesEnsamblar = 1;
            this.cantSpritesEnsamblar = 4;
            this.cantSistemasEnsamblar = 4;
            this.cantDLCEnsamblar = 2;
   
        } else {
            this.cantGuionesEnsamblar = 1;
            this.cantNivelesEnsamblar = 2;
            this.cantSpritesEnsamblar = 6;
            this.cantSistemasEnsamblar = 5;
            this.cantDLCEnsamblar = 1;
         
        }

    }

    public void run() {
        while (this.hired) {
            try {
              

                if (this.contVideos == 5) {
                    this.sExclusionDLC.acquire();
                }

                this.sExclusionSistema.acquire();
                this.sExclusionSprite.acquire();
                this.sExclusionNivel.acquire();
                this.sExclusionGuion.acquire();
                boolean created = false;

                if (Interfaz.cantGuiones >= this.cantGuionesEnsamblar && Interfaz.cantNiveles >= this.cantNivelesEnsamblar && Interfaz.cantSprites >= this.cantSpritesEnsamblar && Interfaz.cantSistemas >= this.cantSistemasEnsamblar) {

                    Interfaz.cantGuiones = Interfaz.cantGuiones - this.cantGuionesEnsamblar;
                    this.restarProductoGuiones(this.cantGuionesEnsamblar);
                    Interfaz.txtCantGuiones.setText(Integer.toString(Interfaz.cantGuiones));

                    Interfaz.cantNiveles = Interfaz.cantNiveles - this.cantNivelesEnsamblar;
                    this.restarProductoNiveles(this.cantNivelesEnsamblar);
                    Interfaz.txtCantNiveles.setText(Integer.toString(Interfaz.cantNiveles));

                    Interfaz.cantSprites = Interfaz.cantSprites - this.cantSpritesEnsamblar;
                    this.restarProductoSprites(this.cantSpritesEnsamblar);
                    Interfaz.txtCantSprites.setText(Integer.toString(Interfaz.cantSprites));
                    //System.out.println("posee: " + Interfaz.spritesE);

                    Interfaz.cantSistemas = Interfaz.cantSistemas - this.cantSistemasEnsamblar;
                    this.restarProductoSistemas(this.cantSistemasEnsamblar);
                    Interfaz.txtCantSistemas.setText(Integer.toString(Interfaz.cantSistemas));

                    if (this.contVideos == 5) {
                        Interfaz.cantDLC = Interfaz.cantDLC - this.cantDLCEnsamblar;
                        this.restarProductoDLC(this.cantDLCEnsamblar);
                        Interfaz.txtCantSistemas.setText(Integer.toString(Interfaz.cantDLC));
                    }

                    if (this.contVideos == 5) {
                        this.sDriveDLC.release(this.cantDLCEnsamblar);
                    }

                    this.sDriveSistema.release(this.cantSistemasEnsamblar);
                    this.sDriveSprite.release(this.cantSpritesEnsamblar);
                    this.sDriveNivel.release(this.cantNivelesEnsamblar);
                    this.sDriveGuion.release(this.cantGuionesEnsamblar);

                    created = true;

                }

                this.sExclusionGuion.release();
                this.sExclusionNivel.release();
                this.sExclusionSprite.release();
                this.sExclusionSistema.release();
                if (this.contVideos == 5) {
                    this.sExclusionDLC.release();
                }

                if (created) {

                    //System.out.println("Contador videojuegos: " + this.contVideos);
                    Thread.sleep(Interfaz.diaDuracion * this.diasEnsam);

                    this.sDespacho.acquire();
                    //Despacha el video juego
                    if (this.contVideos == 5) {
                        Interfaz.cantVideojuegosDLC = Interfaz.cantVideojuegosDLC + 1;
                        Interfaz.txtVideojuegosDLCListos.setText(Integer.toString(Interfaz.cantVideojuegosDLC));
//                        System.out.println("Video juego DLC" + Interfaz.cantVideojuegosDLC);
                    } else {
                        Interfaz.cantVideojuegos = Interfaz.cantVideojuegos + 1;
                        Interfaz.txtVideojuegosEstandarListos.setText(Integer.toString(Interfaz.cantVideojuegos));
                        //System.out.println("Video juego" + Interfaz.cantVideojuegos);
                    }
                    if (this.contVideos == 5) {
                        this.contVideos = 0;
                    } else {
                        this.contVideos++;
                    }
                    this.sDespacho.release();
                }

            } catch (InterruptedException ex) {
                System.out.println("Ocurrió un error!");
            }
        }
    }

    public void restarProductoGuiones(int n) {
        for (int i = 0; i < n; i++) {
            Interfaz.guiones[Interfaz.guionesE] = 0;

            Interfaz.guionesE = (Interfaz.guionesE + 1) % Interfaz.guiones.length;

        }

    }

    public void restarProductoSistemas(int n) {
        for (int i = 0; i < n; i++) {
            Interfaz.sistemas[Interfaz.sistemasE] = 0;
            Interfaz.sistemasE = (Interfaz.sistemasE + 1) % Interfaz.sistemas.length;

        }

    }

    public void restarProductoNiveles(int n) {
        for (int i = 0; i < n; i++) {
            Interfaz.niveles[Interfaz.nivelesE] = 0;

            Interfaz.nivelesE = (Interfaz.nivelesE + 1) % Interfaz.niveles.length;

        }

    }

    public void restarProductoSprites(int n) {
        for (int i = 0; i < n; i++) {
            Interfaz.sprites[Interfaz.spritesE] = 0;

            Interfaz.spritesE = (Interfaz.spritesE + 1) % Interfaz.sprites.length;

        }

    }

    public void restarProductoDLC(int n) {
        for (int i = 0; i < n; i++) {
            Interfaz.dlc[Interfaz.dlcE] = 0;

            Interfaz.dlcE = (Interfaz.dlcE + 1) % Interfaz.dlc.length;

        }

    }
//    public void print() {
//        System.out.println("-----------");
//        for (int i = 0; i < Interfaz.sprites.length; i++) {
//
//            System.out.print(" " + Interfaz.sprites[i] + " ");
//
//        }
//        System.out.println("");
//        System.out.println("-----------");
//    }
}
