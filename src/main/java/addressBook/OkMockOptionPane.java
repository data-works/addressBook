package main.java.addressBook;

import java.awt.Component;

import javax.swing.JOptionPane;

public class OkMockOptionPane extends ConfirmationOptionPane {

	/* (non-Javadoc)
	 * @see main.java.addressBook.ConfirmationOptionPane#showConfirmDialog(java.awt.Component, java.lang.Object, java.lang.String, int, int)
	 */
	@Override
	public int showConfirmDialog(Component parentComponent, Object message, String title, int optionType,
			int messageType) {
		return JOptionPane.OK_OPTION;
	}
	
	/* (non-Javadoc)
	 * @see main.java.addressBook.ConfirmationOptionPane#showInputDialog(java.lang.Object, java.lang.String)
	 */
	@Override
    public String showInputDialog(Object message, String title) {
    	return "New Sample Title";
    }
}
