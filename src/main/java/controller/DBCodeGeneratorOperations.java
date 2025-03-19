/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connections.JavaPostgreSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CRUD;
import model.DatabaseModel;

/**
 *
 * @author mariormoreno
 */
public class DBCodeGeneratorOperations extends CRUD {

    /*
    private PartidaObjeto objPartida;
    private JavaPostgreSQL objJavaPostgreSQL;
    private int idDificultad;
    private int idJugador;
    private int puntos;
    private String resultado;

    public OperacionesDBPartida() {
        // Use the Singleton instance
        this.objJavaPostgreSQL = JavaPostgreSQL.getInstance();
    }
     */
    DatabaseModel objDatabaseModel;
    JavaPostgreSQL objJavaPostgreSQL;

    public DBCodeGeneratorOperations() {
        this.objJavaPostgreSQL = JavaPostgreSQL.getInstance();

    }

    @Override
    public void create() {
       
        try {
            objJavaPostgreSQL.getStatement().execute("INSERT INTO databases(ip, port, database_name, user_name, password_db) VALUES"
                    + "('" + objDatabaseModel.getIp() + "','" + objDatabaseModel.getPort() + "','" 
                    + objDatabaseModel.getDatabaseName() + "','" + objDatabaseModel.getUser() + "','"
                    + objDatabaseModel.getPassword() + "'"  + ");");
        } catch (SQLException ex) {
            Logger.getLogger(DBCodeGeneratorOperations.class.getName()).log(Level.SEVERE, null, ex);
        }


      /*  
        try {
            objJavaPostgreSQL.getStatement().execute("INSERT INTO partida(idcat, iddif, idjug, puntos, fecha, resultado) VALUES"
                    + "(" + objPartida.getIdCategoria() + "," + objPartida.getIdDificultad() + "," 
                    + objPartida.getIdJugador() + "," + objPartida.getPuntos() + ",'"
                    + objPartida.getFecha() + "','" + objPartida.getResultado() + "');");
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesDBPartida.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
    }

    @Override
    public ArrayList read() {
        ArrayList<DatabaseModel> databaseList = new ArrayList<>();
        DatabaseModel objDatabaseModel;
        
        try{
            ResultSet result = objJavaPostgreSQL.getStatement().executeQuery("SELECT * FROM databases;");
            while (result.next()) {
                objDatabaseModel = new DatabaseModel();
                objDatabaseModel.setIp(result.getString("ip"));
                objDatabaseModel.setPort(result.getString("port"));
                objDatabaseModel.setDatabaseName(result.getString("database_name"));
                objDatabaseModel.setUser(result.getString("user_name"));
                objDatabaseModel.setPassword(result.getString("password_db"));
                databaseList.add(objDatabaseModel);
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        /*
        ArrayList<Usuario> objListaUsuarios = new ArrayList<>();
        Usuario objUsuariol;
        try {
            ResultSet resultado = objJavaPostgreSQL.getStatement().executeQuery("SELECT * FROM jugador;");
            while (resultado.next()) {
                objUsuariol = new Usuario();
                objUsuariol.setId(resultado.getInt("idjug"));
                objUsuariol.setUser(resultado.getString("nombre"));
                objUsuariol.setContrasenia(resultado.getString("contrasenia"));
                objListaUsuarios.add(objUsuariol);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBDUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objListaUsuarios;
        */
        return databaseList;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setObjDatabaseModel(DatabaseModel objDatabaseModel) {
        this.objDatabaseModel = objDatabaseModel;
    }

    
}
