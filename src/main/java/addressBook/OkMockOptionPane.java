package main.java.addressBook;

import java.awt.Component;

import javax.swing.JOptionPane;

public class OkMockOptionPane extends ConfirmationOptionPane {

	@Override
	public int showConfirmDialog(Component parentComponent, Object message, String title, int optionType,
			int messageType) {
		return JOptionPane.OK_OPTION;
	}
}
