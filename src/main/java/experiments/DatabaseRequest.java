/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package experiments;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.*;
import java.util.logging.Logger;

/**
 *
 * @author ezequielpena
 */
public class DatabaseRequest {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://127.0.0.1/aseguradora";
        String user = "postgres";
        String password = "ezequielpm123";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            DatabaseMetaData metaData = conn.getMetaData();

            // Obtener todas las tablas
            try (ResultSet tablas = metaData.getTables(null, null, "%", new String[]{"TABLE"})) {
                System.out.println("Tablas en la base de datos:");
                while (tablas.next()) {
                    String nombreTabla = tablas.getString("TABLE_NAME");
                    System.out.println(" - " + nombreTabla);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
