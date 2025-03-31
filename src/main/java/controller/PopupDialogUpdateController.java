/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FileDBOperations;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.DatabaseModel;
import view.PopupDialogUpdate;
import view.MainDashboard;

/**
 *
 * @author ezequielpena
 */
public class PopupDialogUpdateController implements ActionListener {

    PopupDialogUpdate objPopUpDialogUpdate;
    DatabaseModel objDatabaseModel;

    FileDBOperations objFileDBOperations;
    
    Frame parentContainer;

    public PopupDialogUpdateController(PopupDialogUpdate objPopUpDialogUpdate) {
        this.objFileDBOperations = new FileDBOperations();
        this.objPopUpDialogUpdate = objPopUpDialogUpdate;
        this.objPopUpDialogUpdate.updateButton.addActionListener(this);
        this.objPopUpDialogUpdate.cancelButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objPopUpDialogUpdate.cancelButton) {
            this.objPopUpDialogUpdate.dispose();
            return;
        }
        if (e.getSource() == this.objPopUpDialogUpdate.updateButton) {
            //save changes
            updateDatabase();
            showMessage();
            
            MainDashboard objMainDashBoard = (MainDashboard)this.parentContainer;
            objMainDashBoard.objMainDashboardController.showDatabaseConnections();
            
            this.objPopUpDialogUpdate.dispose();
            return;
        }
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(objPopUpDialogUpdate, "Database saved!");
    }

    public void putInformation() {
        this.objPopUpDialogUpdate.ipField.setText(this.objDatabaseModel.getIp());
        this.objPopUpDialogUpdate.portField.setText(this.objDatabaseModel.getPort());
        this.objPopUpDialogUpdate.databaseField.setText(this.objDatabaseModel.getDatabaseName());
        this.objPopUpDialogUpdate.userField.setText(this.objDatabaseModel.getUser());
        this.objPopUpDialogUpdate.passwordField.setText(this.objDatabaseModel.getPassword());
    }

    public DatabaseModel getObjDatabaseModel() {
        return objDatabaseModel;
    }

    public void setObjDatabaseModel(DatabaseModel objDatabaseModel) {
        this.objDatabaseModel = objDatabaseModel;
    }

    private void updateDatabase() {
        int i = 0;
        ArrayList<DatabaseModel> updatedList = this.objFileDBOperations.read();
        for(DatabaseModel objDatabase: updatedList){
            System.out.println("buscando...");
            
            /*if(objDatabase.getPort().equals(this.objDatabaseModel.getPort())){
                System.out.println("encontrado");
                this.objDatabaseModel.setDatabaseName(this.objPopUpDialogUpdate.databaseField.getText());
                this.objDatabaseModel.setIp(this.objPopUpDialogUpdate.ipField.getText());
                this.objDatabaseModel.setPort(this.objPopUpDialogUpdate.portField.getText());
                this.objDatabaseModel.setUser(this.objPopUpDialogUpdate.userField.getText());
                this.objDatabaseModel.setPassword(this.objPopUpDialogUpdate.passwordField.getText());
                
                updatedList.set(i, objDatabaseModel);
                this.objFileDBOperations.setUpdatedListDatabases(updatedList);
                this.objFileDBOperations.update();
                
                break;
            }*/
            
            if(objDatabase.getId()==this.objDatabaseModel.getId()){
                System.out.println("encontrado");
                this.objDatabaseModel.setDatabaseName(this.objPopUpDialogUpdate.databaseField.getText());
                this.objDatabaseModel.setIp(this.objPopUpDialogUpdate.ipField.getText());
                this.objDatabaseModel.setPort(this.objPopUpDialogUpdate.portField.getText());
                this.objDatabaseModel.setUser(this.objPopUpDialogUpdate.userField.getText());
                this.objDatabaseModel.setPassword(this.objPopUpDialogUpdate.passwordField.getText());
                
                updatedList.set(i, objDatabaseModel);
                this.objFileDBOperations.setUpdatedListDatabases(updatedList);
                this.objFileDBOperations.update();
                
                break;
            }
            
            
            i++;
        }
    }

    public Frame getParentContainer() {
        return parentContainer;
    }

    public void setParentContainer(Frame parentContainer) {
        this.parentContainer = parentContainer;
    }
    
    

}
