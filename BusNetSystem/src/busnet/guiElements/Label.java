package busnet.guiElements;

import javax.swing.JLabel;

import busnet.Application;

public class Label extends JLabel{
	private static final long serialVersionUID = 1L;

	public Label(String s) {
		super(s);
		super.setFont(Application.plain);
	}
}
