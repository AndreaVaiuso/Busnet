package busnet.guiElements;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import busnet.Application;

public class TextField extends JTextField{

	private static final long serialVersionUID = 1L;

	public TextField() {
		setFont(Application.bold);
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
	
	public boolean containsSpaces() {
		String s = super.getText();
		if(s.startsWith(" ") || s.endsWith(" ")) {
			return true;
		}
		else return false;
	}
	
	public boolean isCfValid() {
		String s = super.getText();
		if(s.length()==16) return true;
		else return false;
	}
	
	public boolean isNameValid() {
		return !super.getText().matches(".*\\d+.*");

	}
}
