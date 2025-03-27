/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.DatabaseModel;
import model.Table;
import view.PopupDialogGenerateCode;
import view.TableViewList;

/**
 *
 * @author ezequielpena
 */
public class TableViewListController implements ActionListener{
    TableViewList objTableViewList;
    
    DatabaseModel objDatabaseModel;
    Table objTable;
    Frame parentContainer;

    public TableViewListController(TableViewList objTableViewList) {
        this.objTableViewList = objTableViewList;
        this.objTableViewList.generateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.objTableViewList.generateButton){
            PopupDialogGenerateCode objPopupDialogGenerateCode = new PopupDialogGenerateCode(parentContainer, true);
            objPopupDialogGenerateCode.objPopupDialogGenerateCodeController.setObjTable(objTable);
            objPopupDialogGenerateCode.setVisible(true);
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

    public Table getObjTable() {
        return objTable;
    }

    public void setObjTable(Table objTable) {
        this.objTable = objTable;
    }
    
    
    
    public void putNameTable(){
        this.objTableViewList.nameTable.setText(this.objTable.getNameTable());
    }
    
    
    
    
}
