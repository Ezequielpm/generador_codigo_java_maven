/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connections.JavaPostgreSQL;
import java.util.ArrayList;
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
    /*    
        try {
            objJavaPostgreSQL.getStatement().execute("INSERT INTO codegenerator(idcat, iddif, idjug, puntos, fecha, resultado) VALUES"
                    + "(" + objPartida.getIdCategoria() + "," + objPartida.getIdDificultad() + "," 
                    + objPartida.getIdJugador() + "," + objPartida.getPuntos() + ",'"
                    + objPartida.getFecha() + "','" + objPartida.getResultado() + "');");
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesDBPartida.class.getName()).log(Level.SEVERE, null, ex);
        }


        
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
