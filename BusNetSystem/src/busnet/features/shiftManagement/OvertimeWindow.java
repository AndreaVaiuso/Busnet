package busnet.features.shiftManagement;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import busnet.Application;
import busnet.guiElements.Button;
import busnet.guiElements.Label;
import busnet.guiElements.TextField;
import busnet.guiElements.Window;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;

public abstract class OvertimeWindow extends Window {
	private JPanel panel;
	private JLabel lblData;
	private JComboBox<String> dayField;
	private JComboBox<String> monthField;
	private JTextField yearField;
	private Label label;
	private JTextField hoursField;

	private Button btnDone;
	
	public OvertimeWindow(String employee) {
		super("Aggiungi straordinario per " + employee,"Se il tuo dipendente ha effettuato ulteriori ore straordinarie puoi aggiungerle qui. Attenzione, per motivi di sicurezza non è possibile rimuoverle, ma possono "
				+ "essere sovrascritte inserendo la stessa data","shiftTemporary120.png");
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		setResizable(false);
		
		setBounds(400, 400, 906, 401);
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		this.panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(this.panel, gbc_panel);
		
		this.lblData = new Label("Data:");
		this.lblData.setBounds(229, 11, 78, 40);
		this.panel.add(this.lblData);
		
		dayField = new JComboBox<>();
		this.dayField.setBounds(317, 11, 73, 40);
		dayField.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		dayField.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		panel.add(dayField);
		
		monthField = new JComboBox<>();
		this.monthField.setBounds(395, 11, 69, 40);
		monthField.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		monthField.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		panel.add(monthField);
		
		yearField = new TextField();
		this.yearField.setBounds(469, 11, 152, 40);
		panel.add(yearField);
		
		this.label = new Label("Ore:");
		this.label.setBounds(229, 62, 78, 40);
		this.panel.add(this.label);
		
		this.hoursField = new JTextField();
		this.hoursField.setFont(Application.bold);
		this.hoursField.setBounds(317, 62, 304, 40);
		this.panel.add(this.hoursField);
		this.hoursField.setColumns(10);
		
		this.btnDone = new Button("Fatto") {
			@Override
			public void click() {
				addOvertimeClickBtn();
			}
		};
		this.btnDone.setBounds(0, 165, 902, 54);
		this.panel.add(this.btnDone);

	}
	
	public JComboBox<String> getDayField() {
		return dayField;
	}

	public void setDayField(JComboBox<String> dayField) {
		this.dayField = dayField;
	}

	public JComboBox<String> getMonthField() {
		return monthField;
	}

	public void setMonthField(JComboBox<String> monthField) {
		this.monthField = monthField;
	}

	public JTextField getYearField() {
		return yearField;
	}

	public void setYearField(JTextField yearField) {
		this.yearField = yearField;
	}

	public JTextField getHoursField() {
		return hoursField;
	}

	public void setHoursField(JTextField hoursField) {
		this.hoursField = hoursField;
	}
	
	public abstract void addOvertimeClickBtn();
}
