package main.java.addressBook;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ConfirmationOptionPane implements OptionPane {

    /* (non-Javadoc)
     * @see main.java.addressBook.OptionPane#showConfirmDialog(java.awt.Component, java.lang.Object, java.lang.String, int, int)
     */
    public int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType) {
       return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType);
    }
    
    /* (non-Javadoc)
     * @see main.java.addressBook.OptionPane#showInputDialog(java.awt.Component, java.lang.Object, java.lang.String, int)
     */
    public String showInputDialog(Component parentComponent, Object message, String title, int messageType) {
    	return JOptionPane.showInputDialog(parentComponent, message, title, messageType);
    }
}
