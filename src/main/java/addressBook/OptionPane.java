package main.java.addressBook;

import java.awt.Component;

import javax.swing.Icon;

public interface OptionPane {
	
	/**
	 * Show confirm dialog.
	 *
	 * @param parentComponent the parent component
	 * @param message the message
	 * @param title the title
	 * @param optionType the option type
	 * @param messageType the message type
	 * @return the resulting int
	 */
	int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType);
	

	/**
	 * Show input dialog.
	 *
	 * @param parentComponent the parent component
	 * @param message the message
	 * @param title the title
	 * @param messageType the message type
	 * @param icon the icon
	 * @param selectionValues the selection values
	 * @param initialSelectionValue the initial selection value
	 * @return the string
	 */
	String showInputDialog(Component parentComponent, String message, String title, int messageType, Icon icon, 
			Object[] selectionValues, String initialSelectionValue);
	
	/**
	 * Show message dialog.
	 *
	 * @param parentComponent the parent component
	 * @param message the message
	 */
	void showMessageDialog(Component parentComponent, String message);
}
