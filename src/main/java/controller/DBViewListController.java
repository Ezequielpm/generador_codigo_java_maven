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
import utils.Logger;
import view.DBViewList;
import view.DatabaseTables;
import view.MainDashboard;
import view.PopupDialogDelete;
import view.PopupDialogUpdate;

/**
 *
 * @author ezequielpena
 */
public class DBViewListController implements ActionListener {

    DBViewList objDBViewList;
    DatabaseModel objDatabaseModel;
    Frame parentContainer;

    //------------
    FileDBOperations objFileDBOperations;

    //----------
    public DBViewListController(DBViewList objDBViewList) {
        this.objDBViewList = objDBViewList;
        this.objDBViewList.viewButton.addActionListener(this);
        this.objDBViewList.editButton.addActionListener(this);
        this.objDBViewList.removeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objDBViewList.viewButton) {
            //show window with tables
            DatabaseTables objDatabaseTables = new DatabaseTables();
            objDatabaseTables.objDatabaseTablesController.setObjDatabaseModel(objDatabaseModel);
            objDatabaseTables.objDatabaseTablesController.putNameDatabase();
            objDatabaseTables.objDatabaseTablesController.setParentContainer(parentContainer);
            objDatabaseTables.objDatabaseTablesController.showTables();
            //CodeGenerator objCodeGenerator = new CodeGenerator(table);
           
            
            MainDashboard objMainDashBoard = (MainDashboard) this.parentContainer;
            objMainDashBoard.objMainDashboardController.changeView(this.objDatabaseModel.getDatabaseName(),objDatabaseTables);
            return;
        }
        if (e.getSource() == this.objDBViewList.editButton) {
            //show information
            /*PopupDialog objDialog = new PopupDialog(parentContainer,true);
           objDialog.objPopupDialogController.setObjDatabaseModel(objDatabaseModel);
           objDialog.objPopupDialogController.putInformation();
           objDialog.setVisible(true);*/

            PopupDialogUpdate objPopupDialogUpdate = new PopupDialogUpdate(parentContainer, true);
            objPopupDialogUpdate.objPopupDialogUpdateController.setObjDatabaseModel(objDatabaseModel);
            objPopupDialogUpdate.objPopupDialogUpdateController.setParentContainer(parentContainer);
            objPopupDialogUpdate.objPopupDialogUpdateController.putInformation();
            objPopupDialogUpdate.setVisible(true);
            return;
        }
        if (e.getSource() == this.objDBViewList.removeButton) {
            int choice = JOptionPane.showConfirmDialog(parentContainer, "Are you sure?");
            System.out.println("choice = " + choice);
            if (choice == 0) {
                deleteDatabase();
                MainDashboard objMainDashBoard = (MainDashboard) this.parentContainer;
                objMainDashBoard.objMainDashboardController.showDatabaseConnections();
                objMainDashBoard.repaint();
                Logger.log("DELETE", "Deleted database '" + this.getObjDatabaseModel().getDatabaseName() + "'");
            }
            return;
        }
    }

    private void deleteDatabase() {
        this.objFileDBOperations = new FileDBOperations();
        int i = 0;
        ArrayList<DatabaseModel> updatedList = this.objFileDBOperations.read();
        for (DatabaseModel objDatabase : updatedList) {
            System.out.println("buscando...");

            if (objDatabase.getId()==this.objDatabaseModel.getId()) {
                System.out.println("encontrado");
                updatedList.remove(i);
                /* this.objDatabaseModel.setDatabaseName(this.objPopUpDialogUpdate.databaseField.getText());
                this.objDatabaseModel.setIp(this.objPopUpDialogUpdate.ipField.getText());
                this.objDatabaseModel.setPort(this.objPopUpDialogUpdate.portField.getText());
                this.objDatabaseModel.setUser(this.objPopUpDialogUpdate.userField.getText());
                this.objDatabaseModel.setPassword(this.objPopUpDialogUpdate.passwordField.getText());
                 */
                this.objFileDBOperations.setUpdatedListDatabases(updatedList);
                this.objFileDBOperations.update();

                
                break;
            }
            i++;
        }
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

    public void putNameView() {
        this.objDBViewList.nameDB.setText(this.objDatabaseModel.getDatabaseName());
    }

}
