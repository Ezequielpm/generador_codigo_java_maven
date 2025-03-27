/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.PopupDialogDelete;
/**
 *
 * @author ezequielpena
 */
public class PopupDialogDeleteController implements ActionListener{
    PopupDialogDelete objPopupDialogDelete;

    public PopupDialogDeleteController(PopupDialogDelete objPopupDialogDelete) {
        this.objPopupDialogDelete = objPopupDialogDelete;
        this.objPopupDialogDelete.cancelButton.addActionListener(this);
        this.objPopupDialogDelete.deleteButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.objPopupDialogDelete.cancelButton){
            
            return;
        }
        if(e.getSource()==this.objPopupDialogDelete.deleteButton){
            //delete the database
            int choice = JOptionPane.showConfirmDialog(objPopupDialogDelete, "Are you sure?");
            return;
        }
        
    }
    
    
}
