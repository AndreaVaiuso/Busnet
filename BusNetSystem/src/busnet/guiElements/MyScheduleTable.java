package busnet.guiElements;

import javax.swing.JPanel;

import busnet.Application;
import busnet.entity.ShiftDefaultList;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyScheduleTable extends JPanel {

	private String[][] sdList;
	
	private JLabel lblNewLabel_1;
	private JLabel label_5;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel lblLuned;
	private JLabel lblMarted;
	private JLabel lblMercoled;
	private JLabel lblGioved;
	private JLabel lblVenerd;
	private JLabel lblSabato;
	private JLabel lblDomenica;
	
	private MyShiftTab[][] shiftTabTable;
	
	public MyScheduleTable(String[][] sdList) {
		setSdList(sdList);
		init();
		loadShifts();
	}
	
	public void init() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100, 100, 100, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{50, 50, 50, 50, 50, 50, 50, 50, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		label_5 = new JLabel("");
		label_5.setOpaque(true);
		label_5.setBackground(Application.defColor);
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.BOTH;
		gbc_label_5.insets = new Insets(0, 0, 0, 0);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 0;
		add(label_5, gbc_label_5);
		
		lblNewLabel_1 = new JLabel("6-10");
		lblNewLabel_1.setFont(Application.bold);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(Application.defColor);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		label = new JLabel("9-13");
		label.setFont(Application.bold);
		label.setForeground(Color.WHITE);
		label.setOpaque(true);
		label.setBackground(Application.defColor);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 0, 0);
		gbc_label.gridx = 2;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		label_1 = new JLabel("12-16");
		label_1.setFont(Application.bold);
		label_1.setForeground(Color.WHITE);
		label_1.setOpaque(true);
		label_1.setBackground(Application.defColor);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 0, 0);
		gbc_label_1.gridx = 3;
		gbc_label_1.gridy = 0;
		add(label_1, gbc_label_1);
		
		label_2 = new JLabel("15-19");
		label_2.setFont(Application.bold);
		label_2.setForeground(Color.WHITE);
		label_2.setOpaque(true);
		label_2.setBackground(Application.defColor);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 0, 0);
		gbc_label_2.gridx = 4;
		gbc_label_2.gridy = 0;
		add(label_2, gbc_label_2);
		
		label_3 = new JLabel("18-22");
		label_3.setFont(Application.bold);
		label_3.setForeground(Color.WHITE);
		label_3.setOpaque(true);
		label_3.setBackground(Application.defColor);
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 0, 0);
		gbc_label_3.gridx = 5;
		gbc_label_3.gridy = 0;
		add(label_3, gbc_label_3);
		
		label_4 = new JLabel("21-1");
		label_4.setFont(Application.bold);
		label_4.setForeground(Color.WHITE);
		label_4.setOpaque(true);
		label_4.setBackground(Application.defColor);
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.BOTH;
		gbc_label_4.insets = new Insets(0, 0, 0, 0);
		gbc_label_4.gridx = 6;
		gbc_label_4.gridy = 0;
		add(label_4, gbc_label_4);
		
		lblLuned = new JLabel("Luned\u00EC",JLabel.CENTER);
		lblLuned.setFont(Application.bold);
		lblLuned.setForeground(Color.WHITE);
		lblLuned.setOpaque(true);
		lblLuned.setBackground(Application.defColor.darker());
		GridBagConstraints gbc_lblLuned = new GridBagConstraints();
		gbc_lblLuned.fill = GridBagConstraints.BOTH;
		gbc_lblLuned.insets = new Insets(0, 0, 0, 0);
		gbc_lblLuned.gridx = 0;
		gbc_lblLuned.gridy = 1;
		add(lblLuned, gbc_lblLuned);
		
		lblMarted = new JLabel("Marted\u00EC",JLabel.CENTER);
		lblMarted.setFont(Application.bold);
		lblMarted.setForeground(Color.WHITE);
		lblMarted.setOpaque(true);
		lblMarted.setBackground(Application.defColor.darker());
		GridBagConstraints gbc_lblMarted = new GridBagConstraints();
		gbc_lblMarted.fill = GridBagConstraints.BOTH;
		gbc_lblMarted.insets = new Insets(0, 0, 0, 0);
		gbc_lblMarted.gridx = 0;
		gbc_lblMarted.gridy = 2;
		add(lblMarted, gbc_lblMarted);
		
		lblMercoled = new JLabel("Mercoled\u00EC", JLabel.CENTER);
		lblMercoled.setFont(Application.bold);
		lblMercoled.setForeground(Color.WHITE);
		lblMercoled.setOpaque(true);
		lblMercoled.setBackground(Application.defColor.darker());
		GridBagConstraints gbc_lblMercoled = new GridBagConstraints();
		gbc_lblMercoled.fill = GridBagConstraints.BOTH;
		gbc_lblMercoled.insets = new Insets(0, 0, 0, 0);
		gbc_lblMercoled.gridx = 0;
		gbc_lblMercoled.gridy = 3;
		add(lblMercoled, gbc_lblMercoled);
		
		lblGioved = new JLabel("Gioved\u00EC", JLabel.CENTER);
		lblGioved.setFont(Application.bold);
		lblGioved.setForeground(Color.WHITE);
		lblGioved.setOpaque(true);
		lblGioved.setBackground(Application.defColor.darker());
		GridBagConstraints gbc_lblGioved = new GridBagConstraints();
		gbc_lblGioved.fill = GridBagConstraints.BOTH;
		gbc_lblGioved.insets = new Insets(0, 0, 0, 0);
		gbc_lblGioved.gridx = 0;
		gbc_lblGioved.gridy = 4;
		add(lblGioved, gbc_lblGioved);
		
		lblVenerd = new JLabel("Venerd\u00EC", JLabel.CENTER);
		lblVenerd.setFont(Application.bold);
		lblVenerd.setForeground(Color.WHITE);
		lblVenerd.setOpaque(true);
		lblVenerd.setBackground(Application.defColor.darker());
		GridBagConstraints gbc_lblVenerd = new GridBagConstraints();
		gbc_lblVenerd.fill = GridBagConstraints.BOTH;
		gbc_lblVenerd.insets = new Insets(0, 0, 0, 0);
		gbc_lblVenerd.gridx = 0;
		gbc_lblVenerd.gridy = 5;
		add(lblVenerd, gbc_lblVenerd);
		
		lblSabato = new JLabel("Sabato", JLabel.CENTER);
		lblSabato.setFont(Application.bold);
		lblSabato.setForeground(Color.WHITE);
		lblSabato.setOpaque(true);
		lblSabato.setBackground(Application.defColor.darker());
		GridBagConstraints gbc_lblSabato = new GridBagConstraints();
		gbc_lblSabato.fill = GridBagConstraints.BOTH;
		gbc_lblSabato.insets = new Insets(0, 0, 0, 0);
		gbc_lblSabato.gridx = 0;
		gbc_lblSabato.gridy = 6;
		add(lblSabato, gbc_lblSabato);
		
		lblDomenica = new JLabel("Domenica", JLabel.CENTER);
		lblDomenica.setFont(Application.bold);
		lblDomenica.setForeground(Color.WHITE);
		lblDomenica.setOpaque(true);
		lblDomenica.setBackground(Application.defColor.darker());
		GridBagConstraints gbc_lblDomenica = new GridBagConstraints();
		gbc_lblDomenica.fill = GridBagConstraints.BOTH;
		gbc_lblDomenica.insets = new Insets(0, 0, 0, 0);
		gbc_lblDomenica.gridx = 0;
		gbc_lblDomenica.gridy = 7;
		add(lblDomenica, gbc_lblDomenica);
	}

	public String[][] getSdList() {
		return sdList;
	}

	public void setSdList(String[][] sdList) {
		this.sdList = sdList;
	}
	
	private void loadShifts() {
		shiftTabTable = new MyShiftTab[7][6];
		for(int i = 0; i<7; i++) {
			for(int j = 0; j<6; j++) {
				shiftTabTable[i][j] = new MyShiftTab(i,j);
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
				gbc_lblNewLabel.insets = new Insets(0, 0, 0, 0);
				gbc_lblNewLabel.gridx = j+1;
				gbc_lblNewLabel.gridy = i+1;
				add(shiftTabTable[i][j], gbc_lblNewLabel);
			}
		}
		refreshShift();
	}
	
	public void refreshShift() {
		for(int i=0;i<7;i++) {
			for(int j=0;j<6;j++) {
				shiftTabTable[i][j].setAvailable(true);
			}
		}
		for(int i = 0; i<7; i++) {
			for(int j=0; j<6; j++) {
				if(getSdList()[i][j]!=null) {
					shiftTabTable[i][j].setPresent(true);
					shiftTabTable[i][j].setText(getSdList()[i][j]);
					int x = i*6+j;
					int xpp = ((x+43)%42);
					int xmm = ((x+41)%42);
					int ip = ((xpp)/6);
					int jp = xpp%6;
					int im = ((xmm)/6);
					int jm = xmm%6;
					shiftTabTable[ip][jp].setAvailable(false);
					shiftTabTable[im][jm].setAvailable(false);
				} else {
					shiftTabTable[i][j].setPresent(false);
				}
				
			}
		}
	}

}
