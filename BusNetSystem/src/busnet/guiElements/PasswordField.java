package busnet.guiElements;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import busnet.Application;

public class PasswordField extends JPasswordField{
	private static final long serialVersionUID = 1L;

	public PasswordField() {
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Application.defColor));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
			}
		});
	}
}