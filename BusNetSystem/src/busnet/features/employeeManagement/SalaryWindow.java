package busnet.features.employeeManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.Application;
import busnet.guiElements.Button;
import busnet.guiElements.Label;
import busnet.guiElements.TextField;
import busnet.guiElements.Window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public abstract class SalaryWindow extends Window {
	private JPanel panel;
	private Button calculateBtn;
	private JLabel lblMese;
	private JComboBox<String> monthField;
	private JLabel lblStipendio;
	private JLabel salaryField;
	private JLabel lblOreStraordinarieSostenute;
	private JLabel straordinaryField;
	private JLabel lblTotale;
	private JLabel totalField;
	private JLabel label;
	private Label label_1;
	private JTextField yearField;
	private JLabel hoursField;
	private Label label_3;
	private JLabel ibanField;

	public SalaryWindow(String employee) {
		super("Calcolo del salario","A seconda del mese scelto, è possibile visualizzare l'importo dovuto per l'impiegato " + employee,"salary120.png");
		setResizable(false);
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowHeights = new int[]{139, 0, 49};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		setBounds(400, 400, 823, 762);
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		this.panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(this.panel, gbc_panel);
		
		this.lblMese = new Label("Mese:");
		this.lblMese.setBounds(36, 27, 66, 27);
		this.panel.add(this.lblMese);
		
		this.monthField = new JComboBox<>();
		this.monthField.setModel(new DefaultComboBoxModel<String>(new String[] {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"}));
		this.monthField.setFont(Application.bold);
		this.monthField.setBounds(36, 65, 486, 45);
		this.panel.add(this.monthField);
		
		this.lblStipendio = new Label("Stipendio orario:");
		this.lblStipendio.setBounds(36, 134, 219, 27);
		this.panel.add(this.lblStipendio);
		
		this.salaryField = new JLabel("\u20AC 0,0");
		this.salaryField.setFont(new Font("Consolas", Font.PLAIN, 30));
		this.salaryField.setBounds(36, 172, 703, 53);
		this.panel.add(this.salaryField);
		
		this.lblOreStraordinarieSostenute = new Label("Ore straordinarie sostenute");
		this.lblOreStraordinarieSostenute.setBounds(419, 236, 259, 38);
		this.panel.add(this.lblOreStraordinarieSostenute);
		
		this.straordinaryField = new JLabel("0");
		this.straordinaryField.setFont(Application.bold);
		this.straordinaryField.setBounds(419, 285, 196, 38);
		this.panel.add(this.straordinaryField);
		
		this.lblTotale = new Label("Totale mensile:");
		this.lblTotale.setBounds(36, 359, 259, 38);
		this.panel.add(this.lblTotale);
		
		this.totalField = new JLabel("\u20AC 0,0");
		this.totalField.setFont(new Font("Consolas", Font.PLAIN, 40));
		this.totalField.setBounds(36, 403, 703, 77);
		this.panel.add(this.totalField);
		
		this.label = new JLabel("________________________________________________________________________________________________________________________");
		this.label.setBounds(36, 334, 733, 14);
		this.panel.add(this.label);
		
		this.label_1 = new Label("Anno:");
		this.label_1.setBounds(551, 27, 66, 27);
		this.panel.add(this.label_1);
		
		this.yearField = new TextField();
		Calendar cal = Calendar.getInstance();
		yearField.setText(cal.get(Calendar.YEAR)+"");
		this.yearField.setBounds(551, 65, 188, 45);
		this.panel.add(this.yearField);
		this.yearField.setColumns(10);
		
		this.hoursField = new JLabel("0");
		this.hoursField.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		this.hoursField.setBounds(36, 285, 196, 38);
		this.panel.add(this.hoursField);
		
		this.label_3 = new Label("Ore sostenute");
		this.label_3.setBounds(36, 236, 259, 38);
		this.panel.add(this.label_3);
		
		this.ibanField = new JLabel("Da applicare come versamento al seguente codice IBAN: ");
		this.ibanField.setBounds(36, 476, 733, 28);
		this.panel.add(this.ibanField);
		this.ibanField.setVisible(false);
		this.getIbanField().setFont(Application.plainSmall);
		
		this.calculateBtn = new Button("Calcola stipendio") {
			@Override
			public void click() {
				calc();
			}
		};
		this.calculateBtn.setIcon("salary35.png");
		GridBagConstraints gbc_calculateBtn = new GridBagConstraints();
		gbc_calculateBtn.fill = GridBagConstraints.BOTH;
		gbc_calculateBtn.gridx = 0;
		gbc_calculateBtn.gridy = 2;
		getContentPane().add(this.calculateBtn, gbc_calculateBtn);
	}
	
	public JLabel getIbanField() {
		return ibanField;
	}
	public JLabel getHoursField() {
		return hoursField;
	}
	public JComboBox<String> getMonthField() {
		return monthField;
	}

	public JLabel getSalaryField() {
		return salaryField;
	}

	public JLabel getStraordinaryField() {
		return straordinaryField;
	}
	public JLabel getTotalField() {
		return totalField;
	}

	public JTextField getYearField() {
		return yearField;
	}

	public abstract void calc();
}
