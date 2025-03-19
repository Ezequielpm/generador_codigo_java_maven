/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package experiments;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ezequielpena
 */
public class DemoGenerator {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        
        // Configurar para que solo seleccione directorios (carpetas)
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        // Mostrar el diálogo y capturar la respuesta del usuario
        int seleccion = fileChooser.showSaveDialog(null);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            // Obtener la carpeta seleccionada
            File carpetaSeleccionada = fileChooser.getSelectedFile();
            
            // Definir el nombre del archivo
            String fileName = "mi_archivo.txt"; // Puedes modificar esto o pedirlo al usuario
            
            // Obtener la ruta de la carpeta
            String path = carpetaSeleccionada.getAbsolutePath() + File.separator + fileName;
            
            // Llamar a la clase generadora con la ruta obtenida
            ClassGenerator generator = new ClassGenerator();
            generator.generateClass(path, fileName);
            
            System.out.println("Archivo creado en: " + path);
        } else {
            System.out.println("No se seleccionó ninguna carpeta.");
        }
    }
}
