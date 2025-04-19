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
    int getters;
    int setters;
    int constructors;
    int annotations;

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

    public int getAnnotations() {
        return annotations;
    }

    public void setAnnotations(int annotations) {
        this.annotations = annotations;
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
            } else if (attribute.getDataType().contains("int")) {
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
            if (isSerial == 1) {
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

        StringBuilder constructorsAux = new StringBuilder(); //for constructors

        int isSerial = 0;
        StringBuilder preparedCode = new StringBuilder(); //final code
        if(annotations==1){
            preparedCode.append("@Entity\n");
            preparedCode.append("@Table(name = "+"\""+this.table.getNameTable()+"\""+")\n");
        }
        
        preparedCode.append("public class ");
        preparedCode.append(this.table.getNameTable().toUpperCase());
        preparedCode.append(" {");
        preparedCode.append("\n");
        preparedCode.append("\t");

        constructors.append("public ");
        constructors.append(this.table.getNameTable());
        constructors.append("(){\n");
        constructors.append("\t");

        constructors.append("\n\t}\n\t");

        constructors.append("public ");
        constructors.append(this.table.getNameTable());
        constructors.append("(");
        //The variables should be here

        //filling the vars
        int count = 0;
        for (Column attribute : this.table.getAttributeList()) {
            isSerial = 0;

            //vars.append("\n\t");
            if(annotations==1){
                if(attribute.isPrimaryKey()){
                vars.append("@Id\n\t");
            }
            vars.append("@Column(name = "+"\""+attribute.getName()+"\""+")\n\t");
            }
            
                    
                    
            vars.append(attribute.getAccess());
            vars.append(" ");

            /*if (attribute.getDataType().contains("serial")) {
                vars.append("int");
                dataType = "int";
                isSerial = 1;
            } else if (attribute.getDataType().contains("int")) {
                vars.append("int");
                dataType = "int";
            } else if (attribute.getDataType().contains("varchar")) {
                vars.append("String");
                dataType = "String";
            } else if (attribute.getDataType().contains("date")) {
                vars.append("Date");
                dataType = "Date";
            }*/
            
            dataType = transformSQLTypeToJavaType(attribute.getDataType());
            vars.append(dataType);
            //preparedCode.append(attribute.getDataType());

            if (this.constructors == 1) {
                constructors.append(dataType);
                constructors.append(" ");
                constructors.append(attribute.getName());
                constructors.append(", ");

                if (count == 0) {
                    constructorsAux.append("\t\tthis.");
                } else {
                    constructorsAux.append("\n\t\tthis.");
                }

                count++;
                constructorsAux.append(attribute.getName());
                constructorsAux.append(" = ");
                constructorsAux.append(attribute.getName());
                constructorsAux.append(";");
            }

            vars.append(" ");
            vars.append(attribute.getName());
            vars.append(";");
            if (isSerial == 1) {
                vars.append(" //this is the primary key");
            }
            vars.append("\n");
            vars.append("\t");

            //getters
            if (getters == 1) {
                gettersAndSetters.append("\n\tpublic ");
                gettersAndSetters.append(dataType);
                gettersAndSetters.append(" get");
                gettersAndSetters.append(attribute.getName());
                gettersAndSetters.append("(){");
                gettersAndSetters.append("\n");
                gettersAndSetters.append("\t");
                gettersAndSetters.append("\treturn ");
                gettersAndSetters.append(attribute.getName());
                gettersAndSetters.append(";\n\t}");
            }

            //setters
            if (setters == 1) {
                gettersAndSetters.append("\n\tpublic void set");
                gettersAndSetters.append(attribute.getName());
                gettersAndSetters.append("(");
                gettersAndSetters.append(dataType);
                gettersAndSetters.append(" ");
                gettersAndSetters.append(attribute.getName());
                gettersAndSetters.append("){\n\t");
                gettersAndSetters.append("\tthis.");
                gettersAndSetters.append(attribute.getName());
                gettersAndSetters.append(" = ");
                gettersAndSetters.append(attribute.getName());
                gettersAndSetters.append(";\n\t}");
            }


            /*if(attribute.getAccess().equals("private")){
                preparedCode.append("private");
            }else if(attribute.getAccess().equals("public")){
                
            }*/
            //putting getters and setters...
            /* StringBuilder sb = new StringBuilder();
            sb.append("jjjj");
            preparedCode.append(sb);*/
        }

        constructors.append("){\n");
        constructors.append(constructorsAux);
        constructors.append("\t");
        constructors.append("\n\t}\n\t");

        preparedCode.append(vars);
        if (this.constructors == 1) {
            preparedCode.append(constructors);
        }
        preparedCode.append(gettersAndSetters);
        preparedCode.append("\n}");

        return preparedCode;
    }
    
    
    
    private String transformSQLTypeToJavaType(String sqlType){
        switch(sqlType.toUpperCase()){
            case "VARCHAR","TEXT","CHAR","LONGTEXT": return "String";
            case "INT","INTEGER","SMALLINT","SERIAL": return "int";
            case "BIGINT": return "long";
            case "FLOAT","REAL": return "float";
            case "DOUBLE", "DECIMAL", "NUMERIC": return "double";
            case "DATE": return "java.time.LocalDate";
            case "TIME": return "java.time.LocalTime";
            case "TIMESTAMP","DATETIME": return "java.time.LocalDateTime";
            case "BOOLEAN","BIT": return "boolean";
            default: return "String";
        }
    }

    public int getGetters() {
        return getters;
    }

    public void setGetters(int getters) {
        this.getters = getters;
    }

    public int getSetters() {
        return setters;
    }

    public void setSetters(int setters) {
        this.setters = setters;
    }

    public int getConstructors() {
        return constructors;
    }

    public void setConstructors(int constructors) {
        this.constructors = constructors;
    }

}
