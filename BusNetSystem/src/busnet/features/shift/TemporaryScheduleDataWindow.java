package busnet.features.shift;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import busnet.Window;
import busnet.entity.ShiftDefaultList;
import busnet.entity.ShiftTemporaryList;
import busnet.guiElements.Button;
import busnet.guiElements.Label;
import busnet.guiElements.TemporaryScheduleTable;
import busnet.guiElements.TextField;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Insets;
import java.awt.BorderLayout;

public abstract class TemporaryScheduleDataWindow extends Window {
	private JPanel panel;
	private JLabel lblData;
	private JComboBox<String> dayField;
	private JComboBox<String> monthField;
	private TextField yearField;
	private JButton btnNewButton;
	private TemporaryScheduleTable tsTable;
	private Button btnDone;
	private ShiftTemporaryList stList;
	

	public TemporaryScheduleDataWindow() {
		super("Nuovo turno temporaneo","Stai aggiungendo un nuovo turno temporaneo","shiftTemporary120.png");
		init();
	}
	
	public TemporaryScheduleDataWindow(boolean modify) {
		super("Turno temporaneo","Stai visualizzando il turno temporaneo salvato","shiftTemporary120.png");
		init();
		this.btnNewButton.setVisible(false);
		this.lblData.setVisible(false);
		this.dayField.setVisible(false);
		this.monthField.setVisible(false);
		this.yearField.setVisible(false);
	}

	public void init() {
		setResizable(false);
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		this.panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(this.panel, gbc_panel);
		
		this.btnNewButton = new JButton("Recupera turno");
		this.btnNewButton.setBounds(1034, 33, 223, 40);
		this.btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				confirm();
			}
		});
		
		this.lblData = new Label("Data:");
		this.lblData.setBounds(50, 33, 43, 27);
		this.panel.add(this.lblData);
		
		dayField = new JComboBox<>();
		this.dayField.setBounds(98, 33, 73, 40);
		dayField.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		dayField.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		panel.add(dayField);
		
		monthField = new JComboBox<>();
		this.monthField.setBounds(176, 33, 69, 40);
		monthField.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		monthField.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		panel.add(monthField);
		
		yearField = new TextField();
		this.yearField.setBounds(250, 33, 152, 40);
		panel.add(yearField);
		this.btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.panel.add(this.btnNewButton);
		this.btnDone = new Button("Fatto") {
			@Override
			public void click() {
				clickBtnDone();
			}
		};
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.fill = GridBagConstraints.BOTH;
		gbc_btnDone.gridx = 0;
		gbc_btnDone.gridy = 2;
		getContentPane().add(this.btnDone, gbc_btnDone);
		
		this.tsTable = new TemporaryScheduleTable();
		this.tsTable.setBounds(30, 108, 1227, 130);
		this.panel.add(this.tsTable);
		setBounds(400,400,1340,540);
	}
	
	public ShiftTemporaryList getStList() {
		return stList;
	}

	public void setStList(ShiftTemporaryList stList) {
		this.stList = stList;
	}

	public TemporaryScheduleTable getTsTable() {
		return tsTable;
	}

	public void setTsTable(TemporaryScheduleTable tsTable) {
		this.tsTable = tsTable;
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

	public TextField getYearField() {
		return yearField;
	}

	public void setYearField(TextField yearField) {
		this.yearField = yearField;
	}

	public abstract void confirm();
	public abstract void clickBtnDone();
	
}
