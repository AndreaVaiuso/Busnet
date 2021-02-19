package busnet.guiElements;

import javax.swing.JPanel;

import busnet.Application;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckBox extends JPanel {
	private JLabel icon;
	private JLabel text;
	private boolean enabled;
	private boolean editable;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if(enabled) {
			icon.setIcon(new ImageIcon(CheckBox.class.getResource("/busnet/resources/switchOn.png")));
		} else {
			icon.setIcon(new ImageIcon(CheckBox.class.getResource("/busnet/resources/switchOff.png")));
		}
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		if(editable) {
			text.setForeground(Color.BLACK);
		} else {
			text.setForeground(Color.GRAY);
		}
	}
	
	public CheckBox(String txt) {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{121, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		icon = new JLabel("",JLabel.LEFT);
		icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				if(isEditable()) {
					if(isEnabled()) {
						setEnabled(false);
						
					} else {
						setEnabled(true);
					}
				}
				click();
			}
		});
		icon.setIcon(new ImageIcon(CheckBox.class.getResource("/busnet/resources/switchOff.png")));
		GridBagConstraints gbc_icon = new GridBagConstraints();
		gbc_icon.anchor = GridBagConstraints.WEST;
		gbc_icon.insets = new Insets(0, 0, 0, 5);
		gbc_icon.gridx = 0;
		gbc_icon.gridy = 0;
		add(icon, gbc_icon);
		
		text = new JLabel(txt);
		text.setFont(Application.plain);
		GridBagConstraints gbc_text = new GridBagConstraints();
		gbc_text.anchor = GridBagConstraints.WEST;
		gbc_text.gridx = 1;
		gbc_text.gridy = 0;
		add(text, gbc_text);
		
		setEnabled(false);
		setEditable(true);
	}
	
	public void click() {
		
	}



}
