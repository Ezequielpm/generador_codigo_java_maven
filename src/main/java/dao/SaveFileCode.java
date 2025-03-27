/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import experiments.ClassGenerator;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author ezequielpena
 */
public class SaveFileCode {
    String generatedCode;
    String fileName;

    public SaveFileCode() {
    }

    public SaveFileCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
    
    public void storeCode(){
        JFileChooser fileChooser = new JFileChooser();
        
        // Configurar para que solo seleccione directorios (carpetas)
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        // Mostrar el diálogo y capturar la respuesta del usuario
        int seleccion = fileChooser.showSaveDialog(null);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            // Obtener la carpeta seleccionada
            File carpetaSeleccionada = fileChooser.getSelectedFile();
            
            // Definir el nombre del archivo
            String fileName = this.fileName + ".java"; // Puedes modificar esto o pedirlo al usuario
            
            // Obtener la ruta de la carpeta
            String path = carpetaSeleccionada.getAbsolutePath() + File.separator + fileName;
            
            // Llamar a la clase generadora con la ruta obtenida
            ClassGenerator generator = new ClassGenerator();
            generator.setGeneratedCode(generatedCode);
            generator.generateClass(path, fileName);
            
            System.out.println("Archivo creado en: " + path);
        } else {
            System.out.println("No se seleccionó ninguna carpeta.");
        }
    }
}
