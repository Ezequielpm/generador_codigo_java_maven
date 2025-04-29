/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FileDBOperations;
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import view.About;
import view.Activity;
import view.DBViewList;
import view.DatabaseTables;
import view.MainDashboard;
import view.PopupDatabaseForm;
import view.PopupDialog;
import view.RightEdgeMain;
import view.Settings;
//import view.TablesView;

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
        this.objMainDashboard.settingsButton.addActionListener(this);
        this.objMainDashboard.dashboardButton.addActionListener(this);
        this.objMainDashboard.activityButton.addActionListener(this);
        this.objMainDashboard.aboutButton.addActionListener(this);
        this.objMainDashboard.panelContainer.getVerticalScrollBar().setUnitIncrement(10);
        showDatabaseConnections();

        //replacePanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objMainDashboard.connectDBButton) {
            //create popup
            //PopupDatabaseForm objPopupDatabaseForm = new PopupDatabaseForm();
            //objPopupDatabaseForm.setVisible(true);

            PopupDialog objDialog = new PopupDialog(objMainDashboard, true);
            objDialog.objPopupDialogController.setParentContainer(objMainDashboard);
            objDialog.setVisible(true);
            //showDatabaseConnections();
            return;
        }
        if (e.getSource() == this.objMainDashboard.settingsButton) {
            this.objMainDashboard.MainDashboardPanel.removeAll();

            Settings objSettings = new Settings();

            this.objMainDashboard.MainDashboardPanel.setLayout(new BorderLayout());
            this.objMainDashboard.MainDashboardPanel.add(objSettings, BorderLayout.CENTER);

            this.objMainDashboard.MainDashboardPanel.revalidate();
            this.objMainDashboard.MainDashboardPanel.repaint();

            /*  this.objMainDashboard.MainDashboardPanel.removeAll();
            this.objMainDashboard.MainDashboardPanel.revalidate();
            this.objMainDashboard.MainDashboardPanel.repaint();
            Settings objSettings = new Settings();
            this.objMainDashboard.MainDashboardPanel.add(objSettings);
             */
            // this.objMainDashboard.revalidate();
            //this.objMainDashboard.repaint();
            //this.objMainDashboard.MainDashboardPanel.repaint();
            // objSettings.setVisible(true);
            return;
        }
        if(e.getSource()==this.objMainDashboard.dashboardButton){
            restoreRightEdge();
            return;
        }
        if(e.getSource()==this.objMainDashboard.aboutButton){
            showAbout();
            return;
        }
        if(e.getSource()==this.objMainDashboard.activityButton){
            showActivity();
            return;
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
        //this.objMainDashboard.ConnectionsPanelaux.removeAll();
        this.objMainDashboard.ConnectionsPanelaux.removeAll();

        ArrayList<DatabaseModel> listDatabases = objFileDBOperations.read();
        if (listDatabases.isEmpty()) {
            System.out.println("it's empty");
            /*JLabel message = new JLabel();
            message.setText("No hay ninguna base de datos");

            this.objMainDashboard.ConnectionsPanelaux.add(message);
            message.setVisible(true);
            
            this.objMainDashboard.repaint();*/

            //return;
        } else {

            this.objMainDashboard.ConnectionsPanelaux.removeAll();
            this.objMainDashboard.ConnectionsPanelaux.repaint();

        }

        this.objMainDashboard.ConnectionsPanelaux.setLayout(new BoxLayout(objMainDashboard.ConnectionsPanelaux, BoxLayout.Y_AXIS));

        for (DatabaseModel database : listDatabases) {
            DBViewList objDBViewList = new DBViewList();
            objDBViewList.objDBViewListController.setObjDatabaseModel(database);
            objDBViewList.objDBViewListController.putNameView();
            objDBViewList.objDBViewListController.setParentContainer(objMainDashboard);
            this.objMainDashboard.ConnectionsPanelaux.add(objDBViewList);

            System.out.println("dt: " + database.getDatabaseName());
        }

        this.objMainDashboard.revalidate();
        this.objMainDashboard.repaint();

        this.objMainDashboard.ConnectionsPanelaux.revalidate();
        this.objMainDashboard.ConnectionsPanelaux.repaint();

    }

    private void replacePanel() {
        // this.objMainDashboard.MainDashboardPanel.removeAll();
        //TablesView objTablesView = new TablesView();
        CardLayout objCardLayout = new CardLayout();
        // this.objMainDashboard.MainDashboardPanel.setLayout(objCardLayout);
        // this.objMainDashboard.MainDashboardPanel.add(objTablesView, "p1");

        //objCardLayout.show(this.objMainDashboard.MainDashboardPanel, "p1");
    }

    public void changeView(String nameView, DatabaseTables tableView) {
        //this.objMainDashboardAux = this.objMainDashboard;

        this.objMainDashboard.MainDashboardPanel.removeAll();
        CardLayout objCardLayout = new CardLayout();
        this.objMainDashboard.MainDashboardPanel.setLayout(objCardLayout);
        this.objMainDashboard.MainDashboardPanel.add(tableView, nameView);

        objCardLayout.show(this.objMainDashboard.MainDashboardPanel, nameView);

        this.objMainDashboard.revalidate();
        this.objMainDashboard.repaint();

    }

    public void restoreView() {
        /*this.objMainDashboard.MainDashboardPanel.removeAll();
        CardLayout objCardLayout = new CardLayout();
        this.objMainDashboard.MainDashboardPanel.setLayout(objCardLayout);
        this.objMainDashboard.MainDashboardPanel.add(this.objMainDashboardAux, "main");
        
        objCardLayout.show(this.objMainDashboard.MainDashboardPanel, "main");
        
        this.objMainDashboard.revalidate();
        this.objMainDashboard.repaint();*/
    }
    
    public void showActivity(){
        this.objMainDashboard.MainDashboardPanel.removeAll();
        Activity objActivity = new Activity();
        this.objMainDashboard.MainDashboardPanel.setLayout(new BorderLayout());
        this.objMainDashboard.MainDashboardPanel.add(objActivity, BorderLayout.CENTER);
        this.objMainDashboard.MainDashboardPanel.revalidate();
        this.objMainDashboard.MainDashboardPanel.repaint();
    }
    
    public void showAbout(){
        this.objMainDashboard.MainDashboardPanel.removeAll();
        About objAbout = new About();

        this.objMainDashboard.MainDashboardPanel.setLayout(new BorderLayout());
        this.objMainDashboard.MainDashboardPanel.add(objAbout, BorderLayout.CENTER);
        this.objMainDashboard.MainDashboardPanel.revalidate();
        this.objMainDashboard.MainDashboardPanel.repaint();
    }

    public void restoreRightEdge() {
        this.objMainDashboard.MainDashboardPanel.removeAll();
        RightEdgeMain objRightEdgeMain = new RightEdgeMain();

        this.objMainDashboard.MainDashboardPanel.setLayout(new BorderLayout());
        this.objMainDashboard.MainDashboardPanel.add(objRightEdgeMain, BorderLayout.CENTER);
        this.objMainDashboard.MainDashboardPanel.revalidate();
        this.objMainDashboard.MainDashboardPanel.repaint();
        showDatabaseConnections2();
    }
    
    
    public void showDatabaseConnections2() {
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
        //this.objMainDashboard.ConnectionsPanelaux.removeAll();
        RightEdgeMain objRightEdgeMain = (RightEdgeMain)this.objMainDashboard.MainDashboardPanel.getComponent(0);
        objRightEdgeMain.ConnectionsPanelaux.removeAll();
       // this.objMainDashboard.ConnectionsPanelaux.removeAll();

        ArrayList<DatabaseModel> listDatabases = objFileDBOperations.read();
        if (listDatabases.isEmpty()) {
            System.out.println("it's empty");
            /*JLabel message = new JLabel();
            message.setText("No hay ninguna base de datos");

            this.objMainDashboard.ConnectionsPanelaux.add(message);
            message.setVisible(true);
            
            this.objMainDashboard.repaint();*/

            //return;
        } else {
             objRightEdgeMain.ConnectionsPanelaux.removeAll();
              objRightEdgeMain.ConnectionsPanelaux.repaint();
            

           // this.objMainDashboard.ConnectionsPanelaux.removeAll();
           // this.objMainDashboard.ConnectionsPanelaux.repaint();

        }
         objRightEdgeMain.ConnectionsPanelaux.setLayout(new BoxLayout(objRightEdgeMain.ConnectionsPanelaux,BoxLayout.Y_AXIS));

        //this.objMainDashboard.ConnectionsPanelaux.setLayout(new BoxLayout(objMainDashboard.ConnectionsPanelaux, BoxLayout.Y_AXIS));

        for (DatabaseModel database : listDatabases) {
            DBViewList objDBViewList = new DBViewList();
            objDBViewList.objDBViewListController.setObjDatabaseModel(database);
            objDBViewList.objDBViewListController.putNameView();
            objDBViewList.objDBViewListController.setParentContainer(objMainDashboard);
            objRightEdgeMain.ConnectionsPanelaux.add(objDBViewList);
            //this.objMainDashboard.ConnectionsPanelaux.add(objDBViewList);

            System.out.println("dt: " + database.getDatabaseName());
        }

        this.objMainDashboard.revalidate();
        this.objMainDashboard.repaint();

        objRightEdgeMain.ConnectionsPanelaux.revalidate();
        objRightEdgeMain.ConnectionsPanelaux.repaint();
        //this.objMainDashboard.ConnectionsPanelaux.revalidate();
        //this.objMainDashboard.ConnectionsPanelaux.repaint();

    }

}
