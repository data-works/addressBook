package main.java.addressBook;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ConfirmationOptionPane implements OptionPane  {

    public int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) {
       return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType,messageType);
    }
}
