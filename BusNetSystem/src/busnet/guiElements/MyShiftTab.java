package busnet.guiElements;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import busnet.Application;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyShiftTab extends JPanel {
	private JTextPane label;
	private int i;
	private int j;

	public MyShiftTab(int i, int j) {
		setI(i);
		setJ(j);
		setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);	
		label = new JTextPane();
		label.setOpaque(false);
		label.setEditable(false);
		label.setForeground(Color.WHITE);
		label.setFont(Application.bold);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(label, gbc_label);
	}
	
	public void setText(String text) {
		label.setText(text);
	}
	
	public void setAvailable(boolean available) {
		if(available) {
			label.setVisible(true);
		} else {
			label.setVisible(false);
		}
	}
	
	public void setPresent(boolean present) {
		if(present) {
			setBackground(Application.defColor);
		} else {
			setBackground(Color.WHITE);
			setText("");
		}
	}
	
	public JTextPane getLabel() {
		return label;
	}

	public void setLabel(JTextPane label) {
		this.label = label;
	}
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

}
