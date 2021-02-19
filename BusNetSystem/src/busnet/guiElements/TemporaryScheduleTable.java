package busnet.guiElements;

import javax.swing.JPanel;

import busnet.Application;
import busnet.entity.ShiftDefaultList;
import busnet.entity.ShiftTemporaryList;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TemporaryScheduleTable extends JPanel {
	private ShiftTemporaryList stList;
	
	private JLabel lblNewLabel_1;
	private JLabel label_5;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel lblLuned;
	
	private int dayHours = 0;
	
	private ShiftTab[] shiftTabList;
	
	public TemporaryScheduleTable() {
		init();
	}
	
	public void loadShiftTemporaryList(ShiftTemporaryList stList, boolean editable) {
		setStList(stList);
		loadCells(editable);
	}
	
	public void init() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100, 100, 100, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{50, 50, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
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
		
		lblLuned = new JLabel("",JLabel.CENTER);
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
	}
	
	private void loadCells(boolean editable) {
		shiftTabList = new ShiftTab[6];
		for(int i = 0; i<6; i++) {
			shiftTabList[i] = new ShiftTab(i, 0) {
				@Override
				public void click() {
					if(editable) {
						toggleShift(getI());
					}
				}
			};
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 0);
			gbc_lblNewLabel.gridx = i+1;
			gbc_lblNewLabel.gridy = 1;
			add(shiftTabList[i], gbc_lblNewLabel);
		}
		for(int i = 0; i<6; i++) {
			if(getStList().getShift(i)) {
				dayHours+=4;
			}
		}

	}
	
	
	public void refreshShift() {
		for(int i=0;i<6;i++) {
			shiftTabList[i].setAvailable(true);
		}
		for(int i = 0; i<6; i++) {
			if(getStList().getShift(i)) {
				shiftTabList[i].setPresent(true);
				try {
					shiftTabList[i+1].setAvailable(false);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					shiftTabList[i-1].setAvailable(false);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				
			} else {
				shiftTabList[i].setPresent(false);
			}
		}
		System.out.println("DayHours: " + dayHours + ", maxDayHours: " + Application.maxDayHours);
		if(dayHours>=Application.maxDayHours) {
			for(int x = 0; x<ShiftDefaultList.periodsNum; x++) {
				if(!getStList().getShift(x)) {
					shiftTabList[x].setAvailable(false);
				}
			}
		}
	}
	
	public void toggleShift(int i) {
		if(stList.getShift(i)) {
			dayHours-=4;
			removeShift(i);
		} else {
			dayHours+=4;
			addShift(i);
		}
	}
	
	public void addShift(int i) {
		getStList().setShift(i);
		refreshShift();
	}
	
	public void removeShift(int i) {
		getStList().delShift(i);
		refreshShift();
	}

	public ShiftTemporaryList getStList() {
		return stList;
	}

	public void setStList(ShiftTemporaryList stList) {
		this.stList = stList;
	}

	public void setEditable(boolean b) {
		if(!b) {
			for(int i=0;i<6;i++) {
				shiftTabList[i].setEditable(false);
				if(!stList.getShift(i)) {
					shiftTabList[i].setAvailable(false);
				}
			}
		}
		
	}

}
