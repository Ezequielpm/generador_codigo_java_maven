/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;

/**
 *
 * @author ezequ
 */
public class Validator {

    public Validator() {

    }

    public boolean validaCajaTextoCadena(javax.swing.JTextField cajaTexto) {
        if (cajaTexto.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "You must fill in all fields","Blank field not allowed",2);
            return false;
        }
        return true;
    }

    public boolean validaCajaTextoEntero(javax.swing.JTextField cajaTexto) {
        if (cajaTexto.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "You must fill in all fields","Blank field not allowed",2);
            return false;
        } else {
            try {
                int a = Integer.parseInt(cajaTexto.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Value not allowed, port field must be a number","Invalid field",0);
                System.err.println("Convertion error");
                return false;
            }
            return true;
        }
    }

    /* La siguiente función no fue hecha en clases, sino como tarea (validar
    la edad)*/
    public boolean validaCajaTextoEnteroEdad(javax.swing.JTextField cajaTexto) {
        int a = 0;
        if (cajaTexto.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "You must fill in all fields");
            return false;
        } else {
            try {
                a = Integer.parseInt(cajaTexto.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Value not allowed, must be a number");
                System.err.println("Error de conversión");
                return false;
            }
            if (a > 0 && a < 100) {
                //bien
            } else {
                JOptionPane.showMessageDialog(null, "Age not allowed");
                return false;
            }
            return true;
        }
    }

    public boolean validaCadena(String cadena) {
        if (cadena.isBlank()) {
            JOptionPane.showMessageDialog(null, "Required field of string");
            return false;
        }
        return true;
    }

}