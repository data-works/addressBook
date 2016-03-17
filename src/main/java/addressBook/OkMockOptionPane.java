package main.java.addressBook;

import java.awt.Component;

import javax.swing.Icon;
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
	 * @see main.java.addressBook.ConfirmationOptionPane#showInputDialog(java.awt.Component, java.lang.String, java.lang.String, int, javax.swing.Icon, java.lang.Object[], java.lang.String)
	 */
	@Override
    public String showInputDialog(Component parentComponent, String message, String title, int messageType, Icon icon,
    		Object[] selectionValues, String initialSelectionValue) {
    	return "New Sample Text";
    }
	
	/* (non-Javadoc)
	 * @see main.java.addressBook.OptionPane#showMessageDialog(java.awt.Component, java.lang.String)
	 */
	@Override
	public void showMessageDialog(Component parentComponent, String message) {

	}
}
