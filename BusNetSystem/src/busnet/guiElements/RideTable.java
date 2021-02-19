package busnet.guiElements;

import javax.swing.JPanel;

import busnet.Application;
import busnet.dbmsManagement.DBInterface;
import busnet.entity.Employee;
import busnet.entity.Ride;
import busnet.entity.RideList;
import busnet.entity.ShiftDefaultList;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.Font;

public abstract class RideTable extends JPanel {
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField textField;
	private JLabel lblShiftPartenza;
	private JLabel lblTarga;
	private JLabel lblPlatte;
	
	private ArrayList<RideTab[]> rideTabList;
	
	private RideList rList;
	
	/**
	 * Create the panel.
	 */
	public RideTable(RideList rList) {
		rideTabList = new ArrayList<RideTab[]>();
		init();
		setrList(rList);
		loadRides();
	}
	
	public void init() {
		
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{139, 170, 100, 100, 100, 100, 100, 100, 0};
		gridBagLayout.rowHeights = new int[]{50, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.lblTarga = new JLabel("Targa", JLabel.CENTER);
		this.lblTarga.setFont(Application.plain);
		this.lblTarga.setForeground(Color.WHITE);
		this.lblTarga.setBackground(Application.defColor);
		this.lblTarga.setOpaque(true);
		GridBagConstraints gbc_lblTarga = new GridBagConstraints();
		gbc_lblTarga.fill = GridBagConstraints.BOTH;
		gbc_lblTarga.insets = new Insets(0, 0, 0, 0);
		gbc_lblTarga.gridx = 0;
		gbc_lblTarga.gridy = 0;
		add(this.lblTarga, gbc_lblTarga);
		
		this.lblShiftPartenza = new JLabel("Shift partenza", JLabel.CENTER);
		this.lblShiftPartenza.setFont(Application.plain);
		this.lblShiftPartenza.setForeground(Color.WHITE);
		this.lblShiftPartenza.setBackground(Application.defColor);
		this.lblShiftPartenza.setOpaque(true);
		GridBagConstraints gbc_lblShiftPartenza = new GridBagConstraints();
		gbc_lblShiftPartenza.fill = GridBagConstraints.BOTH;
		gbc_lblShiftPartenza.insets = new Insets(0, 0, 0, 0);
		gbc_lblShiftPartenza.gridx = 1;
		gbc_lblShiftPartenza.gridy = 0;
		add(this.lblShiftPartenza, gbc_lblShiftPartenza);
		
		this.label_1 = new JLabel("6 - 10");
		this.label_1.setFont(Application.plain);
		this.label_1.setForeground(Color.WHITE);
		this.label_1.setBackground(Application.defColor);
		this.label_1.setOpaque(true);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 0, 0);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 0;
		add(this.label_1, gbc_label_1);
		
		this.label_2 = new JLabel("9 - 13");
		this.label_2.setFont(Application.plain);
		this.label_2.setForeground(Color.WHITE);
		this.label_2.setBackground(Application.defColor);
		this.label_2.setOpaque(true);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 0, 0);
		gbc_label_2.gridx = 3;
		gbc_label_2.gridy = 0;
		add(this.label_2, gbc_label_2);
		
		this.label_3 = new JLabel("12 - 16");
		this.label_3.setFont(Application.plain);
		this.label_3.setForeground(Color.WHITE);
		this.label_3.setBackground(Application.defColor);
		this.label_3.setOpaque(true);
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 0, 0);
		gbc_label_3.gridx = 4;
		gbc_label_3.gridy = 0;
		add(this.label_3, gbc_label_3);
		
		this.label_4 = new JLabel("15 - 19");
		this.label_4.setFont(Application.plain);
		this.label_4.setForeground(Color.WHITE);
		this.label_4.setBackground(Application.defColor);
		this.label_4.setOpaque(true);
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.BOTH;
		gbc_label_4.insets = new Insets(0, 0, 0, 0);
		gbc_label_4.gridx = 5;
		gbc_label_4.gridy = 0;
		add(this.label_4, gbc_label_4);
		
		this.label_5 = new JLabel("18 - 22");
		this.label_5.setFont(Application.plain);
		this.label_5.setForeground(Color.WHITE);
		this.label_5.setBackground(Application.defColor);
		this.label_5.setOpaque(true);
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.BOTH;
		gbc_label_5.insets = new Insets(0, 0, 0, 0);
		gbc_label_5.gridx = 6;
		gbc_label_5.gridy = 0;
		add(this.label_5, gbc_label_5);
		
		this.label = new JLabel("21 - 1");
		this.label.setFont(Application.plain);
		this.label.setForeground(Color.WHITE);
		this.label.setBackground(Application.defColor);
		this.label.setOpaque(true);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 0, 0);
		gbc_label.gridx = 7;
		gbc_label.gridy = 0;
		add(this.label, gbc_label);
	}	
	private void loadRides() {
		
		for(int i=0; i<rList.getRidesSize(); i++) {
			rideTabList.add(new RideTab[8]);
			for(int j=0; j<ShiftDefaultList.periodsNum+2 ; j++) {
				 if(j==0) {
					 rideTabList.get(i)[j] = new RideTab("plate",i,j);
				 } else if(j==1) {
					 rideTabList.get(i)[j] = new RideTab("shift",i,j);
				 } else {
					 rideTabList.get(i)[j] = new RideTab("schedule",i,j-2) {
						 @Override
						 public void add() {
							 addRideClickBtn(getI(),getJ());
						 }
						 public void del() {
							 delRideClickBtn(getI(),getJ());
						 }
					 };
				 }
				 GridBagConstraints gbc_textField = new GridBagConstraints();
				 gbc_textField.fill = GridBagConstraints.BOTH;
				 gbc_textField.insets = new Insets(0, 0, 0, 0);
				 gbc_textField.gridx = j;
				 gbc_textField.gridy = i+1;
				 add(rideTabList.get(i)[j], gbc_textField);
			}
		}
		refreshRides();
	}
	private void refreshRides() {
		for(int i=0; i<rList.getRidesSize(); i++) {
			for(int j=0; j<ShiftDefaultList.periodsNum+2 ; j++) {
				if(j==0) {
					rideTabList.get(i)[j].setPlate(rList.peekBus(i));
				} else if(j==1) {
					rideTabList.get(i)[j].setTime(Integer.parseInt(rList.peekRide(i).getStartTime()));
				} else {
					if(rList.peekRide(i).getEmployeeShift(j-2)!=null) {
						Employee emp = null;
						try {
							emp = DBInterface.rtrvEmployeeData(rList.peekRide(i).getEmployeeShift(j-2));
						} catch (Exception e) {
						}
						rideTabList.get(i)[j].setStatus(true,emp.toString());
					} else {
						rideTabList.get(i)[j].setStatus(false,"");
					}
				}
				rideTabList.get(i)[j].revalidate();
				rideTabList.get(i)[j].repaint();
				revalidate();
				repaint();
			}
		}
	}


	public void addRow(String plate) {
		rList.pushRide(plate, new Ride("0"));
		rideTabList.add(new RideTab[8]);
		for(int i=0;i<8;i++) {
			if(i==0) {
				rideTabList.get(rideTabList.size()-1)[i] = new RideTab("plate",rideTabList.size()-1,i);
			} else if(i==1) rideTabList.get(rideTabList.size()-1)[i] = new RideTab("shift",rideTabList.size()-1,i);
			else rideTabList.get(rideTabList.size()-1)[i] = new RideTab("schedule",rideTabList.size()-1,i-2){
				 @Override
				 public void add() {
					 addRideClickBtn(getI(),getJ());
				 }
				 public void del() {
					 delRideClickBtn(getI(),getJ());
				 }
			 };
			 GridBagConstraints gbc_textField = new GridBagConstraints();
			 gbc_textField.fill = GridBagConstraints.BOTH;
			 gbc_textField.insets = new Insets(0, 0, 0, 0);
			 gbc_textField.gridx = i;
			 gbc_textField.gridy = rideTabList.size();
			 add(rideTabList.get(rideTabList.size()-1)[i], gbc_textField);
		}
		
		refreshRides();
	}
	
	public void deleteRow(String plate) {
		rList.deleteRide(plate);
		refreshRides();
	}

	public RideList getrList() {
		return rList;
	}

	public void setrList(RideList rList) {
		this.rList = rList;
	}
	
	public abstract void addRideClickBtn(int busId, int period);
	public abstract void delRideClickBtn(int busId, int period);

	public void setSelected(int i, int j, boolean status, String cf) {
		rideTabList.get(i)[j].setStatus(status, cf);
	}

	public void setSelected(int i, int j, boolean status) {
		rideTabList.get(i)[j].setStatus(status);
	}

	public String getStartTime(int i) {
		int time = rideTabList.get(i)[1].getTime();
		if(time<60 && time>=0) {
			return time+"";
		} throw new NumberFormatException("Formato tempo non valido per: " + time);
	}




}
