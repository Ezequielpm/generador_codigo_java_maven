/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package experiments;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author ezequielpena
 */
public class ClassGenerator {
    String generatedCode;
    public ClassGenerator() {
    }
    
    public void generateClass(String path, String fileName){
        FileWriter fw = null;
        PrintWriter pw = null;
        
        try {
            File file = new File(path);
            
            // Crear el archivo si no existe
            file.getParentFile().mkdirs(); // Asegura que la carpeta exista
            fw = new FileWriter(file);
            pw = new PrintWriter(fw);
            System.out.println("EL CODIGO GENERADO:::");
            System.out.println(generatedCode);
            // Contenido de prueba
            pw.println(generatedCode);
            
            System.out.println("Archivo generado con éxito en: " + path);
        } catch (Exception e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        } finally {
            try {
                if (pw != null) pw.close();
                if (fw != null) fw.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }
    
    public void getMetadataDB(){
        
    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }
    
    
}
