/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FileDBOperations;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.DatabaseModel;
import view.DBViewList;
import view.MainDashboard;
import view.PopupDatabaseForm;
import view.PopupDialog;

/**
 *
 * @author ezequielpena
 */
public class MainDashboardController implements ActionListener {

    MainDashboard objMainDashboard;
    
    FileDBOperations objFileDBOperations;
    //DBCodeGeneratorOperations objDBCodeGeneratorOperations;

   
    /*public MainDashboardController(MainDashboard objMainDashboard) {
        this.objDBCodeGeneratorOperations = new DBCodeGeneratorOperations();
        this.objMainDashboard = objMainDashboard;
        this.objMainDashboard.connectDBButton.addActionListener(this);
        showDatabaseConnections();
    }*/
    
    
     public MainDashboardController(MainDashboard objMainDashboard) {
        this.objFileDBOperations = new FileDBOperations();
        this.objMainDashboard = objMainDashboard;
        this.objMainDashboard.connectDBButton.addActionListener(this);
        this.objMainDashboard.panelContainer.getVerticalScrollBar().setUnitIncrement(10);
        showDatabaseConnections();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objMainDashboard.connectDBButton) {
            //create popup
            //PopupDatabaseForm objPopupDatabaseForm = new PopupDatabaseForm();
            //objPopupDatabaseForm.setVisible(true);

            PopupDialog objDialog = new PopupDialog(objMainDashboard, true);
            objDialog.setVisible(true);
            //showDatabaseConnections();
        }
    }

    public void showDatabaseConnections() {

        //So far, we store databases in a database, but it's necessary store them in a file in order to make the program portable
        /*ArrayList<DatabaseModel> listDatabases = objDBCodeGeneratorOperations.read();
        this.objMainDashboard.ConnectionsPanelaux.setLayout(new BoxLayout(objMainDashboard.ConnectionsPanelaux, BoxLayout.Y_AXIS));

        for (DatabaseModel database : listDatabases) {
            System.out.println("dt: " + database.getDatabaseName());
            this.objMainDashboard.ConnectionsPanelaux.add(new DBViewList(database.getDatabaseName()));
        }
        this.objMainDashboard.ConnectionsPanelaux.revalidate();
        this.objMainDashboard.ConnectionsPanelaux.repaint();
         */
        
        
        ArrayList<DatabaseModel> listDatabases = objFileDBOperations.read();
        if(listDatabases.isEmpty()){
            return;
        }
        
        this.objMainDashboard.ConnectionsPanelaux.setLayout(new BoxLayout(objMainDashboard.ConnectionsPanelaux, BoxLayout.Y_AXIS));

       /* for (DatabaseModel database : listDatabases) {
            System.out.println("dt: " + database.getDatabaseName());
            this.objMainDashboard.ConnectionsPanelaux.add(new DBViewList(database.getDatabaseName()));
        }*/
        
         for (DatabaseModel database : listDatabases) {
             DBViewList objDBViewList = new DBViewList();
             objDBViewList.objDBViewListController.setObjDatabaseModel(database);
             objDBViewList.objDBViewListController.putNameView();
             objDBViewList.objDBViewListController.setParentContainer(objMainDashboard);
             this.objMainDashboard.ConnectionsPanelaux.add(objDBViewList);
             
            System.out.println("dt: " + database.getDatabaseName());
           // this.objMainDashboard.ConnectionsPanelaux.add(new DBViewList(database.getDatabaseName()));
        }
        
        
        this.objMainDashboard.ConnectionsPanelaux.revalidate();
        this.objMainDashboard.ConnectionsPanelaux.repaint();
        
        
        
       
        
    }

}
