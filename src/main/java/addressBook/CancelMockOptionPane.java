package main.java.addressBook;

import java.awt.Component;

import javax.swing.JOptionPane;

public class CancelMockOptionPane extends ConfirmationOptionPane {

	@Override
	public int showConfirmDialog(Component parentComponent, Object message, String title, int optionType,
			int messageType) {
		return JOptionPane.CANCEL_OPTION;
	}
}
