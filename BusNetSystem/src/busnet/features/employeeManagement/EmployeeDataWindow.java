package busnet.features.employeeManagement;

import javax.swing.JPanel;

import busnet.Application;
import busnet.entity.Employee;
import busnet.guiElements.Button;
import busnet.guiElements.CheckBox;
import busnet.guiElements.Label;
import busnet.guiElements.TextField;
import busnet.guiElements.Window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

public abstract class EmployeeDataWindow extends Window {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblNome;
	private TextField nameField;
	private JLabel lblCognome;
	private TextField surnameField;
	private JLabel lblCodiceFiscale;
	private TextField cfField;
	private JLabel lblEmail;
	private TextField mailField;
	private JLabel lblDataDiNascita;
	private CheckBox chkLine;
	private CheckBox chkEmployee;
	private CheckBox chkRide;
	private CheckBox chkTurn;
	private JLabel lblLuogoDiNascita;
	private TextField ibanField;
	private JLabel lblSalarioprezzoorario;
	private TextField salaryField;
	private TextField roleField;
	private JPanel okBtn;
	private Button backBtn;
	private Label label;
	private JLabel lblInformazioniDiBase;
	private JLabel label_1;
	private Employee emp;
	private boolean newEmployee;
	private JComboBox<String> dayField;
	private JComboBox<String> monthField;
	private TextField yearField;
	private Label hiringDateLabel;
	
	public EmployeeDataWindow(Employee emp) {
		super("Impiegato","Da questa schermata è possibile visualizzare, modificare e memorizzare le informazioni relative ad un impiegato esistente","addemployee120.png");
		setNewEmployee(false);
		setEmp(emp);
		init();
		getHiringDateLabel().setVisible(true);
		loadForms();
		setEditable(false);
	}
	public EmployeeDataWindow() {
		super("Aggiungi impiegato al database","Da questa schermata è memorizzare le informazioni relative ad un nuovo impiegato","addemployee120.png");
		setNewEmployee(true);
		init();
		getSalaryField().setText("\u20ac 0,00");
		getHiringDateLabel().setVisible(false);
		setEditable(true);
	}
	public Label getHiringDateLabel() {
		return hiringDateLabel;
	}
	public TextField getNameField() {
		return nameField;
	}
	public TextField getSurnameField() {
		return surnameField;
	}
	public TextField getCfField() {
		return cfField;
	}
	public TextField getMailField() {
		return mailField;
	}
	public CheckBox getChkLine() {
		return chkLine;
	}
	public CheckBox getChkEmployee() {
		return chkEmployee;
	}
	public CheckBox getChkRide() {
		return chkRide;
	}
	public CheckBox getChkTurn() {
		return chkTurn;
	}
	public TextField getIbanField() {
		return ibanField;
	}
	public TextField getSalaryField() {
		return salaryField;
	}
	public TextField getRoleField() {
		return roleField;
	}
	public JComboBox<String> getDayField() {
		return dayField;
	}
	public JComboBox<String> getMonthField() {
		return monthField;
	}
	public TextField getYearField() {
		return yearField;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	
	public void setEditable(boolean b) {
		if(b==true) {
			nameField.setEditable(true);
			surnameField.setEditable(true);
			if(newEmployee) {
				cfField.setEditable(true);
			} else {
				cfField.setEditable(false);
			}
			dayField.setEnabled(true);
			monthField.setEnabled(true);
			yearField.setEnabled(true);
			salaryField.setEditable(true);
			roleField.setEditable(true);
			ibanField.setEditable(true);
			this.chkEmployee.setEditable(true);
			this.chkLine.setEditable(true);
			this.chkRide.setEditable(true);
			this.chkTurn.setEditable(true);
		}
		else {
			nameField.setEditable(false);
			surnameField.setEditable(false);
			cfField.setEditable(false);
			dayField.setEnabled(false);
			monthField.setEnabled(false);
			yearField.setEnabled(false);
			mailField.setEditable(false);
			salaryField.setEditable(false);
			roleField.setEditable(false);
			ibanField.setEditable(false);
			this.chkEmployee.setEditable(false);
			this.chkLine.setEditable(false);
			this.chkRide.setEditable(false);
			this.chkTurn.setEditable(false);
		}
	}
	
	public void init() {
		setResizable(false);
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		
		lblNome = new Label("Nome");
		lblNome.setBounds(51, 69, 331, 36);
		panel.add(lblNome);
		
		nameField = new TextField();
		nameField.setBounds(51, 105, 331, 42);
		panel.add(nameField);
		nameField.setColumns(10);
		
		lblCognome = new Label("Cognome");
		lblCognome.setBounds(397, 69, 324, 36);
		panel.add(lblCognome);
		
		surnameField = new TextField();
		surnameField.setColumns(10);
		surnameField.setBounds(397, 105, 324, 42);
		panel.add(surnameField);
		
		lblCodiceFiscale = new Label("Codice Fiscale");
		lblCodiceFiscale.setBounds(736, 69, 140, 36);
		panel.add(lblCodiceFiscale);
		
		cfField = new TextField();
		cfField.setColumns(10);
		cfField.setBounds(736, 105, 331, 42);
		panel.add(cfField);
		
		lblEmail = new Label("e-mail");
		lblEmail.setBounds(51, 163, 146, 42);
		panel.add(lblEmail);
		
		mailField = new TextField();
		mailField.setColumns(10);
		mailField.setBounds(51, 205, 331, 42);
		panel.add(mailField);
		
		lblDataDiNascita = new Label("Data di nascita");
		lblDataDiNascita.setBounds(1086, 69, 320, 28);
		panel.add(lblDataDiNascita);
		
		chkLine = new CheckBox("Gestione linee");
		chkLine.setBounds(740, 396, 335, 54);
		panel.add(chkLine);
		
		chkEmployee = new CheckBox("Gestione personale");
		chkEmployee.setBounds(51, 398, 335, 52);
		panel.add(chkEmployee);
		
		chkRide = new CheckBox("Gestione corse");
		chkRide.setBounds(1086, 396, 335, 54);
		panel.add(chkRide);
		
		chkTurn = new CheckBox("Gestione turni");
		chkTurn.setBounds(401, 398, 335, 54);
		panel.add(chkTurn);
		
		lblLuogoDiNascita = new Label("Codice IBAN");
		lblLuogoDiNascita.setBounds(397, 163, 324, 42);
		panel.add(lblLuogoDiNascita);
		
		ibanField = new TextField();
		ibanField.setColumns(10);
		ibanField.setBounds(397, 205, 331, 42);
		panel.add(ibanField);
		
		lblSalarioprezzoorario = new Label("Salario (prezzo-orario)");
		lblSalarioprezzoorario.setBounds(736, 163, 324, 42);
		panel.add(lblSalarioprezzoorario);
		
		salaryField = new TextField();
		salaryField.setColumns(10);
		salaryField.setBounds(736, 205, 324, 42);
		panel.add(salaryField);
		
		roleField = new TextField();
		roleField.setColumns(10);
		roleField.setBounds(1082, 205, 324, 42);
		panel.add(roleField);
		
		okBtn = new Button("Fatto") {
			@Override
			public void click() {
				confirm();
			}
		};
		okBtn.setBounds(0, 520, 725, 54);
		panel.add(okBtn);
		
		backBtn = new Button("Annulla") {
			private static final long serialVersionUID = 1L;

			@Override
			public void click() {
				dispose();
			}
		};
		GridBagLayout gbl_backBtn = (GridBagLayout) backBtn.getLayout();
		gbl_backBtn.rowWeights = new double[]{1.0, 0.0, 1.0};
		gbl_backBtn.rowHeights = new int[]{0, 64, 0};
		gbl_backBtn.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_backBtn.columnWidths = new int[]{0, 6, 81, 0};
		backBtn.setBounds(719, 520, 751, 54);
		panel.add(backBtn);
		
		
		label = new Label("Ruolo");
		label.setBounds(1082, 163, 140, 42);
		panel.add(label);
		
		lblInformazioniDiBase = new JLabel("Informazioni di base");
		lblInformazioniDiBase.setFont(Application.title);
		lblInformazioniDiBase.setBounds(51, 0, 661, 70);
		panel.add(lblInformazioniDiBase);
		
		label_1 = new JLabel("Permessi");
		label_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		label_1.setBounds(51, 325, 661, 70);
		panel.add(label_1);
		
		dayField = new JComboBox<>();
		dayField.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		dayField.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayField.setBounds(1082, 108, 70, 42);
		panel.add(dayField);
		
		monthField = new JComboBox<>();
		monthField.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 25));
		monthField.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		monthField.setBounds(1167, 108, 67, 42);
		panel.add(monthField);
		
		yearField = new TextField();
		yearField.setBounds(1249, 108, 157, 42);
		panel.add(yearField);
		
		hiringDateLabel = new Label("Data di assunzione: ");
		hiringDateLabel.setBounds(51, 289, 331, 36);
		panel.add(hiringDateLabel);
		setBounds(100, 100, 1473, 767);
	}

	private void loadForms() {
		getNameField().setText(emp.getName());
		getCfField().setText(emp.getCf());
		getMailField().setText(emp.getEmail());
		getIbanField().setText(emp.getIban());
		getRoleField().setText(emp.getRole());
		
		
		String salary = Application.formatter.format(emp.getSalary());
		
		getSalaryField().setText(salary);
		getSurnameField().setText(emp.getSurname());
		
		String hiringDate = emp.getHiring_date();
		StringTokenizer tok = new StringTokenizer(hiringDate,"-");
		String year = tok.nextToken();
		String month = tok.nextToken();
		String day = tok.nextToken();
		getHiringDateLabel().setText("Data di assunzione: " + day + "/" + month + "/" + year);
		
		String birthday = emp.getBirthday();
		tok = new StringTokenizer(birthday,"-");
		getYearField().setText(tok.nextToken());
		getMonthField().setSelectedIndex(Integer.parseInt(tok.nextToken())-1);
		getDayField().setSelectedIndex(Integer.parseInt(tok.nextToken())-1);
		
		short permissionMask = emp.getPermission();
		if ((permissionMask & 1)!=0){
			   this.getChkEmployee().setEnabled(true);
			}
			if ((permissionMask & (1 << 1))!=0){
				this.getChkLine().setEnabled(true);
			}
			if ((permissionMask & (1 << 2))!=0){
				this.getChkRide().setEnabled(true);
			}
			if ((permissionMask & (1 << 3))!=0){
				this.getChkTurn().setEnabled(true);
			}
	}
	public boolean isNewEmployee() {
		return newEmployee;
	}
	public void setNewEmployee(boolean newEmployee) {
		this.newEmployee = newEmployee;
	}
	
	public abstract void confirm();
}
