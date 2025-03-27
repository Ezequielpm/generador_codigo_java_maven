/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Table;
import view.CodePreview;
import view.PopupDialogGenerateCode;

/**
 *
 * @author ezequielpena
 */
public class PopupDialogGenerateCodeController implements ActionListener {
    //this class must to generate the code and pass it to the codepreview class
    PopupDialogGenerateCode objPopupDialogGenerateCode;
    Table objTable;
    String generatedCode;
    public PopupDialogGenerateCodeController(PopupDialogGenerateCode objPopupDialogGenerateCode) {
        this.objPopupDialogGenerateCode = objPopupDialogGenerateCode;
        this.objPopupDialogGenerateCode.cancelButton.addActionListener(this);
        this.objPopupDialogGenerateCode.generateButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objPopupDialogGenerateCode.cancelButton) {
            this.objPopupDialogGenerateCode.dispose();
            return;
        }
        if (e.getSource() == this.objPopupDialogGenerateCode.generateButton) {
            CodePreview objCodePreview = new CodePreview(null, true);
            objCodePreview.objCodePreviewController.setObjTable(objTable);
            generatedCode = generateCode();
            objCodePreview.codeArea.setText(generatedCode);
            
            
            this.objPopupDialogGenerateCode.dispose();
            objCodePreview.setVisible(true);

            return;
        }
    }

    public Table getObjTable() {
        return objTable;
    }

    public void setObjTable(Table objTable) {
        this.objTable = objTable;
    }
    
    private String generateCode(){
         CodeGenerator objCodeGenerator = new CodeGenerator();
         objCodeGenerator.setTable(objTable);
         
         StringBuilder code = objCodeGenerator.generateCode2();
         generatedCode = code.toString();
         return generatedCode;
    }
    
    

}
