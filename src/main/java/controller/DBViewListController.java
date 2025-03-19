/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.DatabaseModel;
import view.DBViewList;
import view.PopupDialog;
import view.PopupDialogUpdate;
/**
 *
 * @author ezequielpena
 */
public class DBViewListController implements ActionListener{
    DBViewList objDBViewList;
    DatabaseModel objDatabaseModel;
    Frame parentContainer;

    public DBViewListController(DBViewList objDBViewList) {
        this.objDBViewList = objDBViewList;
        this.objDBViewList.viewButton.addActionListener(this);
        this.objDBViewList.editButton.addActionListener(this);
        this.objDBViewList.removeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.objDBViewList.viewButton){
           //show window with tables
           return;
        }
        if(e.getSource()==this.objDBViewList.editButton){
           //show information
           /*PopupDialog objDialog = new PopupDialog(parentContainer,true);
           objDialog.objPopupDialogController.setObjDatabaseModel(objDatabaseModel);
           objDialog.objPopupDialogController.putInformation();
           objDialog.setVisible(true);*/
           
           PopupDialogUpdate objPopupDialogUpdate = new PopupDialogUpdate(parentContainer, true);
           objPopupDialogUpdate.objPopupDialogUpdateController.setObjDatabaseModel(objDatabaseModel);
           objPopupDialogUpdate.objPopupDialogUpdateController.putInformation();
           objPopupDialogUpdate.setVisible(true);
           return;
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
    
    public void putNameView(){
        this.objDBViewList.nameDB.setText(this.objDatabaseModel.getDatabaseName());
    }
    
    
    
    
}
