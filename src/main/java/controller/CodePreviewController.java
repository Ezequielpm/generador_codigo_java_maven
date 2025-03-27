/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Table;
import view.CodePreview;

/**
 *
 * @author ezequielpena
 */
public class CodePreviewController implements ActionListener{
    CodePreview objCodePreview;
    String generatedCode;
    Table objTable;
    public CodePreviewController(CodePreview objCodePreview) {
        this.objCodePreview = objCodePreview;
        this.objCodePreview.cancelButton.addActionListener(this);
        this.objCodePreview.saveButtton.addActionListener(this);
       
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.objCodePreview.cancelButton){
            this.objCodePreview.dispose();
            return;
        }
        if(e.getSource()==this.objCodePreview.saveButtton){
            
            
            return;
        }
        
    }
    
    private void showGeneratedCode(){
        this.objCodePreview.codeArea.setText(generatedCode);
    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }

    public Table getObjTable() {
        return objTable;
    }

    public void setObjTable(Table objTable) {
        this.objTable = objTable;
    }
    
    
    
}
