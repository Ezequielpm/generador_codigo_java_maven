/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ezequielpena
 */
public class Table {
    String nameTable;
    ArrayList<Column> attributeList;

    public Table() {
    }

    public Table(String nameTable, ArrayList<Column> attributeList) {
        this.nameTable = nameTable;
        this.attributeList = attributeList;
    }

    
    

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public ArrayList<Column> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(ArrayList<Column> attributeList) {
        this.attributeList = attributeList;
    }
    
    
    
    
}
