package busnet.guiElements;

import javax.swing.JPanel;

import busnet.Application;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;


public class Button extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel text;
	private JLabel lblIcon;
	private boolean pressed;

	public Button() {
		init();
		setPressed(false);
	}
	
	public Button(String text) {
		init();
		setText(text);
		setPressed(false);
	}
	
	public void init() {
		
		if(pressed) {
			setBackground(Application.defColor.darker());
		} else {
			setBackground(Application.defColor);	
		}
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(Application.defColor.darker());
				click();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(pressed) {
					setBackground(Application.defColor.darker().darker().darker());
				} else {
					setBackground(Application.defColor.darker());	
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(pressed) {
					setBackground(Application.defColor.darker().darker());
				} else {
					setBackground(Application.defColor);	
				};
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(pressed) {
					setBackground(Application.defColor.darker());
				} else {
					setBackground(Application.defColor.darker().darker());	
				}
			}
		});
		setFont(Application.plain);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 6, 81, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 64, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblIcon = new JLabel("");
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
		gbc_lblIcon.gridx = 1;
		gbc_lblIcon.gridy = 1;
		add(lblIcon, gbc_lblIcon);
		
		text = new JLabel("",JLabel.LEFT);
		text.setForeground(Color.WHITE);
		text.setFont(Application.plain);
		GridBagConstraints gbc_text = new GridBagConstraints();
		gbc_text.insets = new Insets(0, 0, 5, 5);
		gbc_text.gridx = 2;
		gbc_text.gridy = 1;
		add(text, gbc_text);
	}
	
	public void click() {
		
	}
	
	public void setIcon(String imageName) {
		lblIcon.setIcon(new ImageIcon(Button.class.getResource("/busnet/resources/" + imageName)));
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}
	
	public void setButtonFont(Font font) {
		text.setFont(font);
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
		if(pressed) {
			setBackground(Application.defColor.darker().darker());
		} else {
			setBackground(Application.defColor);	
		};
	}

}
