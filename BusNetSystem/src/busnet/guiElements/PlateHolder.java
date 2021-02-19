package busnet.guiElements;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import busnet.Application;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;

public class PlateHolder extends JPanel {
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblPlate;
	

	public PlateHolder(String plate) {
		setBorder(BorderFactory.createMatteBorder(8,8,8,8,Color.BLACK));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{59, 117, 54, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBorder(BorderFactory.createMatteBorder(0,0,0,8,Color.BLACK));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		lblPlate = new JLabel("AA 000 AA");
		lblPlate.setFont(Application.plate);
		lblPlate.setText(plate);
		GridBagConstraints gbc_lblPlate = new GridBagConstraints();
		gbc_lblPlate.insets = new Insets(0, 0, 0, 5);
		gbc_lblPlate.gridx = 1;
		gbc_lblPlate.gridy = 0;
		add(lblPlate, gbc_lblPlate);
		
		panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.textHighlight);
		panel_1.setBorder(BorderFactory.createMatteBorder(0,8,0,0,Color.BLACK));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
	}
	
	public void setText(String plate) {
		lblPlate.setText(plate);
	}

}
