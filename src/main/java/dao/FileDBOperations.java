/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CRUD;
import model.DatabaseModel;

/**
 *
 * @author ezequielpena
 */
public class FileDBOperations extends CRUD {

    String header = "ID,IP,Port,Database Name,Username, Password";
    DatabaseModel objDatabaseModel;
    File file = null;
    File folder;

    //read
    FileReader fr;
    BufferedReader br;

    //write
    FileWriter writer = null;
    PrintWriter pw = null;
    
    //update
    ArrayList<DatabaseModel> updatedListDatabases;

    public FileDBOperations() {
        folder = new File("databases_user");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        file = new File(folder, "databases2.csv");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileDBOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    /*
       File fichero = null;
            FileWriter writer = null;
            PrintWriter pw = null;
            try {
                  fichero = new File("C:\\Users\\Guissela\\Desktop\\ejemplo.csv");
                  
                  writer = new FileWriter(fichero);
                  pw = new PrintWriter(writer);
                  System.out.println("antes");
                  for (int i = 0; i < 10; i++) {
                        pw.println("Linea, " + i);
                        System.out.println("valor "+i);
                  }
                  
            } catch (IOException e) {
                  e.printStackTrace();
            } finally {
                  try {
                        if (null != fichero) {
                             pw.close();
                        }
                  } catch (Exception e2) {
                        e2.printStackTrace();
                  }
            }
     */
    @Override
    public void create() {
        /*File folder = new File("databases_user");
        if (!folder.exists()) {
            folder.mkdirs();
        }*/
        
        
        //before of insert the database, it is important check the number of lines in the current file
        //in order to set the right id in the model
        
        ArrayList<DatabaseModel> listDatabases = this.read();
        int numberLines = listDatabases.size();
        this.objDatabaseModel.setId(numberLines+1);
        try {
            writer = new FileWriter(file, true);
            pw = new PrintWriter(writer);

            //System.out.println("antes");
            /*for (int i = 0; i < 10; i++) {
                pw.println("Linea, " + i);
                System.out.println("valor " + i);
            }*/
            //for (int i = 0; i < 10; i++) {
            pw.println(this.objDatabaseModel.getId()+","+this.objDatabaseModel.getIp() + "," + this.objDatabaseModel.getPort() + ","
                    + this.objDatabaseModel.getDatabaseName() + "," + this.objDatabaseModel.getUser()
                    + "," + this.objDatabaseModel.getPassword());
            //System.out.println("valor " + i);
            // }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        /*File fichero = null;
            FileWriter writer = null;
            PrintWriter pw = null;
            try {
                  fichero = new File("/databases_user/databases.txt");
                  
                  writer = new FileWriter(fichero);
                  pw = new PrintWriter(writer);
                  System.out.println("antes");
                  for (int i = 0; i < 10; i++) {
                        pw.println("Linea, " + i);
                        System.out.println("valor "+i);
                  }
                  
            } catch (IOException e) {
                  e.printStackTrace();
            } finally {
                  try {
                        if (null != fichero) {
                             pw.close();
                        }
                  } catch (Exception e2) {
                        e2.printStackTrace();
                  }
            }*/
    }

    @Override
    public ArrayList read() {
        ArrayList<DatabaseModel> databaseList = new ArrayList<>();

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String currentLine, data[];
            try {
                currentLine = br.readLine();
                if (currentLine == null) {
                    return new ArrayList();
                }
                while (currentLine != null) {
                    data = currentLine.split(",");
                    if (data[0].equals("ID")) {
                        currentLine = br.readLine();
                        continue;
                    }
                    DatabaseModel currentDatabaseModel = new DatabaseModel();
                    currentDatabaseModel.setId(Integer.parseInt(data[0]));
                    currentDatabaseModel.setIp(data[1]);
                    currentDatabaseModel.setPort(data[2]);
                    currentDatabaseModel.setDatabaseName(data[3]);
                    currentDatabaseModel.setUser(data[4]);
                    currentDatabaseModel.setPassword(data[5]);
                    currentLine = br.readLine();
                    databaseList.add(currentDatabaseModel);
                }
            } catch (IOException ex) {
                Logger.getLogger(FileDBOperations.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return databaseList;
    }

    @Override
    public void update() {
        try {
            writer = new FileWriter(file);
            writer.close();
            String currentData;
            for(DatabaseModel dbModel: updatedListDatabases){
                this.objDatabaseModel = dbModel;
                this.create();
            }
        } catch (IOException ex) {
            Logger.getLogger(FileDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    //create
    public DatabaseModel getObjDatabaseModel() {
        return objDatabaseModel;
    }

    public void setObjDatabaseModel(DatabaseModel objDatabaseModel) {
        this.objDatabaseModel = objDatabaseModel;
    }
    
    
    //update

    public ArrayList<DatabaseModel> getUpdatedListDatabases() {
        return updatedListDatabases;
    }

    public void setUpdatedListDatabases(ArrayList<DatabaseModel> updatedListDatabases) {
        this.updatedListDatabases = updatedListDatabases;
    }
    
    
    

}
