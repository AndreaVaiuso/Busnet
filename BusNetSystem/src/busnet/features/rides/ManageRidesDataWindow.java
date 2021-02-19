package busnet.features.rides;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.Window;
import busnet.entity.Employee;
import busnet.entity.Line;
import busnet.entity.RideList;
import busnet.guiElements.Button;
import busnet.guiElements.RideTable;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;

public abstract class ManageRidesDataWindow extends Window {
	private JPanel panel;
	private RideTable RideTabPanel;
	private RideList rList;
	private Button addTripBtn;
	private Button panel_1;
	private ArrayList<ArrayList<Employee>> availableEmployee;
	private JPanel panel_2;

	public ManageRidesDataWindow(RideList rList, ArrayList<ArrayList<Employee>> availableEmployee) {
		super("Gestione corse linea " + rList.getLineId(),"Ora seleziona gli autobus che serviranno la lina " + rList.getLineId() + " nel giorno selezionato, scegli gli"
				+ " orari di partenza e quale autista dovrà guidarlo\nPer rimuovere una corsa, elimina tutte le istanze dei turni","ride120.png");
		setrList(rList);
		this.availableEmployee = availableEmployee;
		init();
	}
	
	public void init() {
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowHeights = new int[]{0, 0, 54};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		setBounds(300,300,1300,900);
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{15, 223, 223, 798, 15, 0};
		gbl_panel.rowHeights = new int[]{15, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.panel.setLayout(gbl_panel);
		
		this.RideTabPanel = new RideTable(rList) {

			@Override
			public void addRideClickBtn(int i, int j) {
				addRide(i,j);
			}

			@Override
			public void delRideClickBtn(int i, int j) {
				delRide(i,j);
			}
			
		};
		GridBagConstraints gbc_RideTabPanel = new GridBagConstraints();
		gbc_RideTabPanel.insets = new Insets(0, 0, 5, 5);
		gbc_RideTabPanel.fill = GridBagConstraints.BOTH;
		gbc_RideTabPanel.gridwidth = 3;
		gbc_RideTabPanel.gridx = 1;
		gbc_RideTabPanel.gridy = 1;
		this.panel.add(this.RideTabPanel, gbc_RideTabPanel);
		
		this.panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		getContentPane().add(this.panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		this.panel_2.setLayout(gbl_panel_2);
		
		this.addTripBtn = new Button("Aggiungi autobus") {
			@Override
			public void click() {
				addBus();
			}
		};
		GridBagConstraints gbc_addTripBtn = new GridBagConstraints();
		gbc_addTripBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_addTripBtn.insets = new Insets(0, 0, 0, 0);
		gbc_addTripBtn.gridx = 0;
		gbc_addTripBtn.gridy = 0;
		this.panel_2.add(this.addTripBtn, gbc_addTripBtn);
		this.addTripBtn.setIcon("add35.png");
		
		this.panel_1 = new Button("Fatto") {
			@Override
			public void click() {
				save();
			}
		};
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		this.panel_2.add(this.panel_1, gbc_panel_1);
	}

	public RideList getrList() {
		return rList;
	}

	public void setrList(RideList rList) {
		this.rList = rList;
	}
	
	public abstract void addRide(int i, int j);
	public abstract void delRide(int i, int j);
	public abstract void addBus();
	public abstract void save();

	public RideTable getRideTabPanel() {
		return RideTabPanel;
	}

	public void setRideTabPanel(RideTable rideTabPanel) {
		RideTabPanel = rideTabPanel;
	}

	public ArrayList<Employee> getAvailableEmployee(int period) {
		return availableEmployee.get(period);
	}

}
