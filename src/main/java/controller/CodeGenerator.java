/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import java.util.stream.Collectors;
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
    private boolean generateDao;

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
            vars.append("\n\n");
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
                gettersAndSetters.append(";\n\t}\n");
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
                gettersAndSetters.append(";\n\t}\n");
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
    
    public boolean isGenerateDao() {
        return generateDao;
    }

    public void setGenerateDao(boolean generateDao) {
        this.generateDao = generateDao;
    }
    
    /*public StringBuilder generateDaoCode() {
        if (!generateDao) {
            return new StringBuilder(); 
        }

        StringBuilder daoCode = new StringBuilder();
        String entityName = table.getNameTable().toUpperCase(); 
        String idType = getIdColumnType(table);

        daoCode.append(generateDaoInterfaceCode(table));

        return daoCode;
    }*/
    
    public StringBuilder generateDaoCode() {
        if (!generateDao) {
            return new StringBuilder();
        }

        StringBuilder daoCode = new StringBuilder();
        String entityName = table.getNameTable().toUpperCase();

        if (hasCompositePrimaryKey(table)) {
            daoCode.append(generateEmbeddedIdClass(table));
            daoCode.append(generateDaoInterfaceCode(table, true)); // Indicate composite key
        } else {
            String idType = getIdColumnType(table);
            daoCode.append(generateDaoInterfaceCode(table, false, idType)); // Indicate single key
        }

        return daoCode;
    }
    
    
    public StringBuilder generateDaoInterfaceCode(Table table, boolean compositeKey) {
        return generateDaoInterfaceCode(table, compositeKey, getIdColumnType(table));
    }
    
    public StringBuilder generateDaoInterfaceCode(Table table, boolean compositeKey, String idType) {
        StringBuilder interfaceCode = new StringBuilder();
        String entityName = table.getNameTable().toUpperCase();

        interfaceCode.append("package com.example.demo.repository;\n\n");
        interfaceCode.append("import com.example.demo.model.").append(entityName).append(";\n");
        interfaceCode.append("import org.springframework.data.jpa.repository.JpaRepository;\n");
        interfaceCode.append("import org.springframework.stereotype.Repository;\n\n");
        interfaceCode.append("import java.util.List;\n");
        interfaceCode.append("import java.util.Optional;\n\n");

        interfaceCode.append("@Repository\n");
        interfaceCode.append(generateDaoInterfaceDeclaration(entityName, entityName, compositeKey ? entityName + "Id" : idType)); // Use EmbeddedId class
        interfaceCode.append("{\n\n");
        interfaceCode.append(generateCustomRepositoryMethods(table));
        interfaceCode.append("}\n");

        return interfaceCode;
    }
    
     private StringBuilder generateDaoInterfaceDeclaration(String interfaceName, String entityType, String idType) {
        return new StringBuilder("public interface " + interfaceName + "Repository extends JpaRepository<" + entityType + ", " + idType + "> ");
    }
     
     private StringBuilder generateCustomRepositoryMethods(Table table) {
        StringBuilder methods = new StringBuilder();

        for (Column column : table.getAttributeList()) {
            if (!column.isPrimaryKey()) { // Don't generate findById for composite keys
                String javaType = transformSQLTypeToJavaType(column.getDataType());
                String columnName = column.getName();
                String capitalizedColumnName = columnName.substring(0, 1).toUpperCase() + columnName.substring(1);

                if (javaType.equals("String")) {
                    methods.append("\tOptional<").append(table.getNameTable().toUpperCase()).append("> findBy").append(capitalizedColumnName).append("(String ").append(columnName).append(");\n\n");
                    methods.append("\tboolean existsBy").append(capitalizedColumnName).append("(String ").append(columnName).append(");\n\n");
                    methods.append("\tList<").append(table.getNameTable().toUpperCase()).append("> findBy").append(capitalizedColumnName).append("Containing(String ").append(columnName).append(");\n\n");
                } else if (javaType.equals("int") || javaType.equals("long")) {
                    methods.append("\tList<").append(table.getNameTable().toUpperCase()).append("> findBy").append(capitalizedColumnName).append("(").append(javaType).append(" ").append(columnName).append(");\n\n");
                }
            }
        }

        return methods;
    }
     
     private StringBuilder generateEmbeddedIdClass(Table table) {
        StringBuilder idClass = new StringBuilder();
        String entityName = table.getNameTable().toUpperCase();
        List<Column> idColumns = getIdColumns(table);

        idClass.append("package com.example.demo.model;\n\n"); 
        idClass.append("import java.io.Serializable;\n");
        idClass.append("import javax.persistence.Column;\n");
        idClass.append("import javax.persistence.Embeddable;\n\n");

        idClass.append("@Embeddable\n");
        idClass.append("public class ").append(entityName).append("Id implements Serializable {\n\n");

        // Fields
        for (Column col : idColumns) {
            idClass.append("\t@Column(name = \"").append(col.getName()).append("\")\n");
            idClass.append("\tprivate ").append(transformSQLTypeToJavaType(col.getDataType())).append(" ").append(col.getName()).append(";\n\n");
        }

        // Constructor (default)
        idClass.append("\tpublic ").append(entityName).append("Id() {}\n\n");

        // Constructor (with args)
        idClass.append("\tpublic ").append(entityName).append("Id(");
        idClass.append(idColumns.stream()
                .map(col -> transformSQLTypeToJavaType(col.getDataType()) + " " + col.getName())
                .collect(Collectors.joining(", ")));
        idClass.append(") {\n");
        for (Column col : idColumns) {
            idClass.append("\t\tthis.").append(col.getName()).append(" = ").append(col.getName()).append(";\n");
        }
        idClass.append("\t}\n\n");

        // Getters and Setters
        for (Column col : idColumns) {
            String javaType = transformSQLTypeToJavaType(col.getDataType());
            String capitalizedName = col.getName().substring(0, 1).toUpperCase() + col.getName().substring(1);
            idClass.append("\tpublic ").append(javaType).append(" get").append(capitalizedName).append("() { return ").append(col.getName()).append("; }\n");
            idClass.append("\tpublic void set").append(capitalizedName).append("(").append(javaType).append(" ").append(col.getName()).append(") { this.").append(col.getName()).append(" = ").append(col.getName()).append("; }\n\n");
        }

        // equals() and hashCode() (IMPORTANT for @Embeddable)
        idClass.append("\t@Override\n");
        idClass.append("\tpublic boolean equals(Object o) {\n");
        idClass.append("\t\tif (this == o) return true;\n");
        idClass.append("\t\tif (o == null || getClass() != o.getClass()) return false;\n");
        idClass.append("\t\t").append(entityName).append("Id that = (").append(entityName).append("Id) o;\n");
        for (Column col : idColumns) {
            idClass.append("\t\tif (!").append(col.getName()).append(".equals(that.").append(col.getName()).append(")) return false;\n");
        }
        idClass.append("\t\treturn true;\n");
        idClass.append("\t}\n\n");

        idClass.append("\t@Override\n");
        idClass.append("\tpublic int hashCode() {\n");
        idClass.append("\t\tint result = 1;\n");
        for (Column col : idColumns) {
            idClass.append("\t\tresult = 31 * result + ").append(col.getName()).append(".hashCode();\n");
        }
        idClass.append("\t\treturn result;\n");
        idClass.append("\t}\n");

        idClass.append("}\n");

        return idClass;
    }
     
     private List<Column> getIdColumns(Table table) {
        return table.getAttributeList().stream()
                .filter(Column::isPrimaryKey)
                .collect(Collectors.toList());
    }
     
     private boolean hasCompositePrimaryKey(Table table) {
        return table.getAttributeList().stream().filter(Column::isPrimaryKey).count() > 1;
    }

    /*
    public StringBuilder generateDaoInterfaceCode(Table table) {
        StringBuilder interfaceCode = new StringBuilder();
        String entityName = table.getNameTable().toUpperCase();
        String idType = getIdColumnType(table);

        interfaceCode.append("package com.example.demo.repository;\n\n"); 
        interfaceCode.append("import com.example.demo.model.").append(entityName).append(";\n"); 
        interfaceCode.append("import org.springframework.data.jpa.repository.JpaRepository;\n");
        interfaceCode.append("import org.springframework.stereotype.Repository;\n\n");
        interfaceCode.append("import java.util.List;\n");
        interfaceCode.append("import java.util.Optional;\n\n");

        interfaceCode.append("@Repository\n");
        interfaceCode.append(generateDaoInterfaceDeclaration(entityName, entityName, idType));
        interfaceCode.append("{\n\n");
        interfaceCode.append(generateCustomRepositoryMethods(table));
        interfaceCode.append("}\n");

        return interfaceCode;
    }*/

    /* private StringBuilder generateDaoInterfaceDeclaration(String interfaceName, String entityType, String idType) {
        return new StringBuilder("public interface " + interfaceName + "Repository extends JpaRepository<" + entityType + ", " + idType + "> ");
    }*/
/*
    private StringBuilder generateCustomRepositoryMethods(Table table) {
        StringBuilder methods = new StringBuilder();

        for (Column column : table.getAttributeList()) {
            String javaType = transformSQLTypeToJavaType(column.getDataType()); 
            String columnName = column.getName();
            String capitalizedColumnName = columnName.substring(0, 1).toUpperCase() + columnName.substring(1);

            if (javaType.equals("String")) {
                methods.append("\tOptional<").append(table.getNameTable().toUpperCase()).append("> findBy").append(capitalizedColumnName).append("(String ").append(columnName).append(");\n\n");
                methods.append("\tboolean existsBy").append(capitalizedColumnName).append("(String ").append(columnName).append(");\n\n");
                methods.append("\tList<").append(table.getNameTable().toUpperCase()).append("> findBy").append(capitalizedColumnName).append("Containing(String ").append(columnName).append(");\n\n");
            } else if (javaType.equals("int") || javaType.equals("long")) {
                methods.append("\tList<").append(table.getNameTable().toUpperCase()).append("> findBy").append(capitalizedColumnName).append("(").append(javaType).append(" ").append(columnName).append(");\n\n");
            }
        }

        return methods;
    }*/
    
    private String getIdColumnType(Table table) {
        for (Column column : table.getAttributeList()) {
            if (column.isPrimaryKey()) {
                return transformSQLTypeToJavaType(column.getDataType());
            }
        }
        return "Long"; //default to Long if no primary key is found 
    }

}
