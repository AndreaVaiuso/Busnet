package busnet.guiElements;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import busnet.Application;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class ShiftTab extends JPanel {
	private JLabel label;
	private int i;
	private int j;
	private MouseAdapter ma;

	public ShiftTab(int i, int j) {
		setI(i);
		setJ(j);
		setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		ma = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				click();
			}
		};
		
		label = new JLabel();
		label.addMouseListener(ma);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(label, gbc_label);
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
			this.label.setIcon(new ImageIcon(ShiftTab.class.getResource("/busnet/resources/remove35.png")));
		} else {
			setBackground(Color.WHITE);
			this.label.setIcon(new ImageIcon(ShiftTab.class.getResource("/busnet/resources/add35.png")));
		}
	}
	
	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public abstract void click();

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

	public void setEditable(boolean b) {
		if(b) {
			label.addMouseListener(ma);
		} else {
			label.removeMouseListener(ma);
		}
		
	}

}
