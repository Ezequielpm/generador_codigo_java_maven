/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SaveFileCode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Table;
import view.CodePreview;

/**
 *
 * @author ezequielpena
 */
public class CodePreviewController implements ActionListener{
    CodePreview objCodePreview;
    String generatedCode;
    String daoCode;
    Table objTable;
    private String generatedIdClassCode = "\n";
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
            saveCode();
            showMessage();
            this.objCodePreview.dispose();
            return;
        }
        
    }
    
    public void showGeneratedCode(){
        this.objCodePreview.codeArea.setText(generatedCode+generatedIdClassCode+daoCode);
        
        /*StringBuilder allCode = new StringBuilder();
        allCode.append(generatedCode).append("\n\n");
        if (generatedIdClassCode != null && !generatedIdClassCode.isEmpty()) {
            allCode.append(generatedIdClassCode).append("\n\n");
        }
        allCode.append(daoCode);
        this.objCodePreview.codeArea.setText(allCode.toString());*/
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
    
    
    public void saveCode(){
        String modifiedCode = this.objCodePreview.codeArea.getText();
        SaveFileCode objSaveFileCode = new SaveFileCode(modifiedCode, daoCode, generatedIdClassCode);
        objSaveFileCode.setFileName(this.objTable.getNameTable());
        objSaveFileCode.storeCode();
    }
    
    private void showMessage(){
        JOptionPane.showMessageDialog(objCodePreview, "Code saved succesfully!");
    }

    public String getDaoCode() {
        return daoCode;
    }

    public void setDaoCode(String daoCode) {
        this.daoCode = daoCode;
    }

    public String getGeneratedIdClassCode() {
        return generatedIdClassCode;
    }

    public void setGeneratedIdClassCode(String generatedIdClassCode) {
        this.generatedIdClassCode = generatedIdClassCode;
    }
    
    
    
}
