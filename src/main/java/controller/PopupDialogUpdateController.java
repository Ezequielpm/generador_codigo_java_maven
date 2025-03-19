/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.DatabaseModel;
import view.PopupDialogUpdate;

/**
 *
 * @author ezequielpena
 */
public class PopupDialogUpdateController implements ActionListener {

    PopupDialogUpdate objPopUpDialogUpdate;
    DatabaseModel objDatabaseModel;

    public PopupDialogUpdateController(PopupDialogUpdate objPopUpDialogUpdate) {
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
            return;
        }
    }
    
    public void showMessage() {
        JOptionPane.showMessageDialog(objPopUpDialogUpdate, "Database saved!");
    }
    
    public void putInformation(){
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
    
    
}
