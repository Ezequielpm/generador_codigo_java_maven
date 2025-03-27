/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Attribute;
import model.DatabaseModel;
import model.Table;

/**
 *
 * @author ezequielpena
 */
public class TablesExtractor {
    DatabaseModel objDatabaseModel;

    public TablesExtractor(DatabaseModel objDatabaseModel) {
        this.objDatabaseModel = objDatabaseModel;
    }
    
    
    public ArrayList<Table> extractTables(){
        ArrayList<Table> tablesList = new ArrayList<>();
        /*connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1/aseguradora",
                    "postgres", "ezequielpm123");*/
        
        String url = "jdbc:mysql://remotedb.example.com:3306/nombre_base_datos";
        String url2 = "jdbc:postgresql://"+this.objDatabaseModel.getIp()+"/"+this.objDatabaseModel.getDatabaseName();
        String user = this.objDatabaseModel.getUser();
        String password =this.objDatabaseModel.getPassword();

        try (Connection conn = DriverManager.getConnection(url2, user, password)) {
            DatabaseMetaData metaData = conn.getMetaData();

            // Obtener las tablas de la base de datos
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            System.out.println("Tablas en la base de datos:");
            while (tables.next()) {
                Table objTable = new Table();
                String tableName = tables.getString("TABLE_NAME");
                objTable.setNameTable(tableName);
                
                
                ArrayList<Attribute> attributesList = extractColumns(metaData, tableName);
                objTable.setAttributeList(attributesList);
                
                
                CodeGenerator cg = new CodeGenerator(objTable);
                StringBuilder sb = cg.generateCode2();
                System.out.println(sb.toString());
                
                tablesList.add(objTable);
                //System.out.println(tables.getString("TABLE_NAME"));
            }

            tables.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablesList;
    }
    

    public DatabaseModel getObjDatabaseModel() {
        return objDatabaseModel;
    }

    public void setObjDatabaseModel(DatabaseModel objDatabaseModel) {
        this.objDatabaseModel = objDatabaseModel;
    }
    
    
    
    private ArrayList<Attribute> extractColumns(DatabaseMetaData metaData, String tableName) {
        ArrayList<Attribute> columnsList = new ArrayList<>();
        try {
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            
            System.out.println("Columnas de la tabla " + tableName + ":");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                //int columnSize = columns.getInt("COLUMN_SIZE");

                Attribute column = new Attribute(columnName, columnType, "private");//ribute(columnName, columnType, columnSize);
                columnsList.add(column);

                System.out.println("  - " + columnName + " (" + columnType + " - " );//+ columnSize + ")");
            }
            columns.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnsList;
    }
    
   
    
    
}
