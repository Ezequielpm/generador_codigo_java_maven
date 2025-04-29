/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import model.DatabaseModel;
import model.Table;
import view.DBViewList;
import view.DatabaseTables;
import view.MainDashboard;
import view.TableViewList;

/**
 *
 * @author ezequielpena
 */
public class DatabaseTablesController implements ActionListener {

    DatabaseTables objDatabaseTables;
    DatabaseModel objDatabaseModel;

    Frame parentContainer;

    public DatabaseTablesController(DatabaseTables objDatabaseTables) {
        this.objDatabaseTables = objDatabaseTables;
        this.objDatabaseTables.backButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objDatabaseTables.backButton) {
            //MainDashboard objMainDashboard = new MainDashboard();
            //objMainDashboard.setVisible(true);

            MainDashboard objMainDashboard = (MainDashboard) this.parentContainer;

            MainDashboard objMainDashboardViewRestored = new MainDashboard();
            objMainDashboardViewRestored.setVisible(true);

            objMainDashboard.dispose();

            /*objMainDashboard.removeAll();
             objMainDashboard.revalidate();
            objMainDashboard.repaint();
            objMainDashboard.restartComponents();*/
            return;
        }
    }

    public void putNameDatabase() {
        this.objDatabaseTables.nameDatabase.setText(this.objDatabaseModel.getDatabaseName());
    }

    public void showTables() {
        TablesExtractor objTablesExtractor = new TablesExtractor(objDatabaseModel);
        ArrayList<Table> listTables = objTablesExtractor.extractTables();
        
        //validate that at least one table exists
        if(listTables.isEmpty()){
           this.objDatabaseTables.interPanel.setLayout(new BoxLayout(this.objDatabaseTables.interPanel, BoxLayout.Y_AXIS));
           return;
            
        }
        this.objDatabaseTables.interPanel.remove(this.objDatabaseTables.errorMessage);

        //MainDashboard objMainDashboard = (MainDashboard)this.parentContainer;
        this.objDatabaseTables.interPanel.setLayout(new BoxLayout(this.objDatabaseTables.interPanel, BoxLayout.Y_AXIS));

        for (Table table : listTables) {
            TableViewList objTableViewList = new TableViewList();
            objTableViewList.objTableViewListController.setObjDatabaseModel(objDatabaseModel);
            objTableViewList.objTableViewListController.setObjTable(table);
            objTableViewList.objTableViewListController.putNameTable();
            this.objDatabaseTables.interPanel.add(objTableViewList);
            //DBViewList objDBViewList = new DBViewList();
            /*objDBViewList.objDBViewListController.setObjDatabaseModel(database);
            objDBViewList.objDBViewListController.putNameView();
            objDBViewList.objDBViewListController.setParentContainer(objMainDashboard);
            this.objMainDashboard.ConnectionsPanelaux.add(objDBViewList);

            System.out.println("dt: " + database.getDatabaseName());*/
        }

        this.objDatabaseTables.interPanel.revalidate();
        this.objDatabaseTables.interPanel.repaint();
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
