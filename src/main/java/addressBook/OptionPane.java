package main.java.addressBook;

import java.awt.Component;

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
}
