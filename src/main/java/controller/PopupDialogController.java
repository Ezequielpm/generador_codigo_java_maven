/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FileDBOperations;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.DatabaseModel;
import view.MainDashboard;
import view.PopupDialog;

/**
 *
 * @author ezequielpena
 */
public class PopupDialogController implements ActionListener {

    PopupDialog objPopupDialog;
    // DBCodeGeneratorOperations objDBCodeGeneratorOperations;
    FileDBOperations objFileDBOperations;
    DatabaseModel objDatabaseModel;

    Frame parentContainer;
    public PopupDialogController() {
    }

    public PopupDialogController(PopupDialog objPopupDialog) {
        //this.objDBCodeGeneratorOperations = new DBCodeGeneratorOperations();
        this.objFileDBOperations = new FileDBOperations();
        
        this.objPopupDialog = objPopupDialog;
        this.objPopupDialog.cancelButton.addActionListener(this);
        this.objPopupDialog.createButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objPopupDialog.cancelButton) {
            this.objPopupDialog.dispose();
            return;
        }
        if (e.getSource() == this.objPopupDialog.createButton) {
            //validate the fields

            saveDatabase();
            
            return;
        }
    }

    public void saveDatabase() {
       /* DatabaseModel objDatabaseModel = new DatabaseModel(this.objPopupDialog.ipField.getText(),
                this.objPopupDialog.portField.getText(), this.objPopupDialog.databaseField.getText(),
                this.objPopupDialog.userField.getText(), this.objPopupDialog.passwordField.getText());*/

        // ya no se guardará en una base de datos
        /*objDBCodeGeneratorOperations.setObjDatabaseModel(objDatabaseModel);
        objDBCodeGeneratorOperations.create();*/
        
        
        //objFileDBOperations.setObjDatabaseModel(objDatabaseModel);
        //this.objFileDBOperations.create();
        
        
        //store in a file
        DatabaseModel objDatabaseModel = new DatabaseModel(2,this.objPopupDialog.ipField.getText(),
                this.objPopupDialog.portField.getText(), this.objPopupDialog.databaseField.getText(),
                this.objPopupDialog.userField.getText(), this.objPopupDialog.passwordField.getText());
        objFileDBOperations.setObjDatabaseModel(objDatabaseModel);
        objFileDBOperations.create();
        showMessage();
        
        
        
        MainDashboard objMainDashBoard = (MainDashboard)this.parentContainer;
        objMainDashBoard.objMainDashboardController.showDatabaseConnections();
        
        this.objPopupDialog.dispose();
        

    }

    public void showMessage() {
        JOptionPane.showMessageDialog(objPopupDialog, "Database saved!");
    }
    
    public void putInformation(){
        this.objPopupDialog.ipField.setText(this.objDatabaseModel.getIp());
        this.objPopupDialog.portField.setText(this.objDatabaseModel.getPort());
        this.objPopupDialog.databaseField.setText(this.objDatabaseModel.getDatabaseName());
        this.objPopupDialog.userField.setText(this.objDatabaseModel.getUser());
        this.objPopupDialog.passwordField.setText(this.objDatabaseModel.getPassword());
    }

    public DatabaseModel getObjDatabaseModel() {
        return objDatabaseModel;
    }

    public void setObjDatabaseModel(DatabaseModel objDatabaseModel) {
        this.objDatabaseModel = objDatabaseModel;
    }

    public Frame getParentContainer() {
        return parentContainer;
    }

    public void setParentContainer(Frame parentContainer) {
        this.parentContainer = parentContainer;
    }
    
    
    
    

}
