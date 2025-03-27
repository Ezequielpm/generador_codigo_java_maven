/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Column;
import model.Table;

/**
 *
 * @author ezequielpena
 */
public class CodeGenerator {

    Table table;

    public CodeGenerator() {
    }

    public CodeGenerator(Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    //this fuction must to return a string with the generated code
    public StringBuilder generateCode() {
        StringBuilder libs = new StringBuilder(); //for libraries
        StringBuilder begin = new StringBuilder(); //for "public Class ..."
        StringBuilder vars = new StringBuilder(); //for variables
        StringBuilder constructors = new StringBuilder(); //for constructors
        StringBuilder gettersAndSetters = new StringBuilder(); //for getters and setters
        StringBuilder finale = new StringBuilder(); //for ";"

        
        int isSerial = 0;
        StringBuilder preparedCode = new StringBuilder();
        preparedCode.append("public class ");
        preparedCode.append(this.table.getNameTable().toUpperCase());
        preparedCode.append(" {");
        preparedCode.append("\n");
        preparedCode.append("\t");

        for (Column attribute : this.table.getAttributeList()) {
            isSerial = 0;
            preparedCode.append(attribute.getAccess());
            preparedCode.append(" ");

            if (attribute.getDataType().contains("serial")) {
                preparedCode.append("int");
                isSerial = 1;
            }
            else if (attribute.getDataType().contains("int")) {
                preparedCode.append("int");
            } else if (attribute.getDataType().contains("varchar")) {
                preparedCode.append("String");
            } else if (attribute.getDataType().contains("date")) {
                preparedCode.append("Date");
            }
            //preparedCode.append(attribute.getDataType());
            preparedCode.append(" ");
            preparedCode.append(attribute.getName());
            preparedCode.append(";");
            if(isSerial==1){
                preparedCode.append(" //this is the primary key");
            }
            preparedCode.append("\n");
            preparedCode.append("\t");
            /*if(attribute.getAccess().equals("private")){
                preparedCode.append("private");
            }else if(attribute.getAccess().equals("public")){
                
            }*/

            //putting getters and setters...
           /* StringBuilder sb = new StringBuilder();
            sb.append("jjjj");
            preparedCode.append(sb);*/
        }
        preparedCode.append("\n}");

        return preparedCode;
    }
    
    
    
    
    
    public StringBuilder generateCode2() {
        String dataType = "";
        StringBuilder libs = new StringBuilder(); //for libraries
        StringBuilder begin = new StringBuilder(); //for "public Class ..."
        StringBuilder vars = new StringBuilder(); //for variables
        StringBuilder constructors = new StringBuilder(); //for constructors
        StringBuilder gettersAndSetters = new StringBuilder(); //for getters and setters
        StringBuilder finale = new StringBuilder(); //for ";"

        
        int isSerial = 0;
        StringBuilder preparedCode = new StringBuilder(); //final code
        preparedCode.append("public class ");
        preparedCode.append(this.table.getNameTable().toUpperCase());
        preparedCode.append(" {");
        preparedCode.append("\n");
        preparedCode.append("\t");

        
        //filling the vars ones
        for (Column attribute : this.table.getAttributeList()) {
            isSerial = 0;
            vars.append("\n\t");
            vars.append(attribute.getAccess());
            vars.append(" ");

            if (attribute.getDataType().contains("serial")) {
                vars.append("int");
                dataType = "int";
                isSerial = 1;
            }
            else if (attribute.getDataType().contains("int")) {
                vars.append("int");
                dataType = "int";
            } else if (attribute.getDataType().contains("varchar")) {
                vars.append("String");
                dataType = "String";
            } else if (attribute.getDataType().contains("date")) {
                vars.append("Date");
                dataType = "Date";
            }
            //preparedCode.append(attribute.getDataType());
            vars.append(" ");
            vars.append(attribute.getName());
            vars.append(";");
            if(isSerial==1){
                vars.append(" //this is the primary key");
            }
            vars.append("\n");
            vars.append("\t");
            
            
            //getters
            gettersAndSetters.append("\n\tpublic ");
            gettersAndSetters.append(dataType);
            gettersAndSetters.append(" get");
            gettersAndSetters.append(attribute.getName());
            gettersAndSetters.append("(){");
            gettersAndSetters.append("\n");
            gettersAndSetters.append("\t");
            gettersAndSetters.append("return ");
            gettersAndSetters.append(attribute.getName());
            gettersAndSetters.append(";\n\t}");
            
            //setters
            gettersAndSetters.append("\n\tpublic void set");
            gettersAndSetters.append(attribute.getName());
            gettersAndSetters.append("(");
            gettersAndSetters.append(dataType);
            gettersAndSetters.append(" ");
            gettersAndSetters.append(attribute.getName());
            gettersAndSetters.append("){\n\t");
            gettersAndSetters.append("this.");
            gettersAndSetters.append(attribute.getName());
            gettersAndSetters.append(" = ");
            gettersAndSetters.append(attribute.getName());
            gettersAndSetters.append(";\n\t}");
            
            
            /*if(attribute.getAccess().equals("private")){
                preparedCode.append("private");
            }else if(attribute.getAccess().equals("public")){
                
            }*/

            //putting getters and setters...
           /* StringBuilder sb = new StringBuilder();
            sb.append("jjjj");
            preparedCode.append(sb);*/
        }
        
        
        
        preparedCode.append(vars);
        preparedCode.append(gettersAndSetters);
        preparedCode.append("\n}");

        return preparedCode;
    }

}
