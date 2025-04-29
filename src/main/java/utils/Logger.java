/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author ezequielpena
 */
public class Logger {
    private static final String FILE_PATH = "activity.log";

    public static void log(String level, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(String.format("[%s] %s - %s%n", time, level.toUpperCase(), message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

