/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1so;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author victoriadepalma
 */
public class Archivo {
    
    String texto;
    String[] arreglo;

    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;
    
     public Archivo() {
    }

    public String Abrir(File archivo) {
        String contenido = "";
        try {
            entrada = new FileInputStream(archivo);
            int ascci;
            while ((ascci = entrada.read()) != -1) {
                char caracter = (char) ascci;
                contenido += caracter;
            }
        } catch (Exception e) {

        }
        return contenido;
    }
    
    public String Guardar(File archivo, String contenido) {
        String respuesta = null;
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytesTxt = contenido.getBytes();
            salida.write(bytesTxt);
            respuesta = "Se Guardo exitosamente el Archivo";

        } catch (Exception e) {

        }
        return respuesta;
    }
    
}
