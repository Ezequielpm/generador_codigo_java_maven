/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainView;

/**
 *
 * @author mariormoreno
 */
public class MainController implements ActionListener{
    MainView objMainView;

    public MainController(MainView objMainView) {
        this.objMainView = objMainView;
        this.objMainView.buttonAddDB.addActionListener(this);
    }
    
    

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.objMainView.buttonAddDB){
            
        }
    }
    
    
}
