package main.java.addressBook;

import java.awt.Component;

public interface OptionPane {
	int showConfirmDialog(Component parentComponent, Object message, String title, int optionType, int messageType);
}
