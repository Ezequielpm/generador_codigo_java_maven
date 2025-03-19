package experiments;

import connections.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Basic connection to PostgreSQL database using Singleton pattern.
 * Conexión básica a la base de datos PostgreSQL utilizando el patrón Singleton.
 */
public class JavaPostgreSQLTest {

    private static JavaPostgreSQLTest instance = null;
    private Connection connection = null;
    private Statement stmt = null;

    // Private constructor to prevent instantiation
    private JavaPostgreSQLTest() {
        connectDatabase();
    }

    // Static method to get the single instance of the class
    public static JavaPostgreSQLTest getInstance() {
        if (instance == null) {
            instance = new JavaPostgreSQLTest();
        }
        return instance;
    }

    /**
     * Establishes the connection with the database <b>customerdb</b>.
     * Establece la conexión con la base de datos <b>customerdb</b>.
     */
    private void connectDatabase() {
        try {
            // Register the PostgreSQL driver
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            // Connect to the database
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1/aseguradora",
                    "postgres", "ezequielpm123");
            stmt = connection.createStatement();

            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return stmt;
    }

    /**
     * Testing Java PostgreSQL connection with host and port
     * Probando la conexión en Java a PostgreSQL especificando el host y el puerto.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JavaPostgreSQLTest javaPostgreSQL = JavaPostgreSQLTest.getInstance();
        javaPostgreSQL.connectDatabase();
    }
}
