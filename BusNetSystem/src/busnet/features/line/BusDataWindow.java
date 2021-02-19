package busnet.features.line;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.Application;
import busnet.Window;
import busnet.guiElements.Button;
import busnet.guiElements.CheckBox;
import busnet.guiElements.Label;
import busnet.guiElements.TextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public abstract class BusDataWindow extends Window {
	private JPanel panel;
	private JLabel lblStopId;
	private JLabel lblNomeFermata;
	private JTextField plateField;
	private Button okBtn;
	private Button cancelBtn;
	private Label lblModello;
	private TextField modelField;
	private JComboBox<String> typeBox;
	private CheckBox isActiveBox;

	public BusDataWindow() {
		super("Nuovo veicolo","Aggiungi un nuovo veicolo alla tua flotta, e potrai instanziarlo per effetturare le corse","bus.png");
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		setBounds(200,200,822,700);
		setResizable(false);		
		this.panel = new JPanel();
		panel.setBackground(Color.WHITE);
		this.panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(this.panel, gbc_panel);
		
		this.lblStopId = new Label("Stop ID (Codice univoco fermata)");
		lblStopId.setText("Targa:");
		this.lblStopId.setBounds(15, 16, 717, 34);
		this.panel.add(this.lblStopId);
		
		this.lblNomeFermata = new Label("Nome fermata");
		lblNomeFermata.setText("Tipo:");
		this.lblNomeFermata.setBounds(15, 111, 211, 40);
		this.panel.add(this.lblNomeFermata);
		
		this.plateField = new TextField();
		this.plateField.setBounds(15, 51, 790, 56);
		this.panel.add(this.plateField);
		this.plateField.setColumns(10);
		
		this.okBtn = new Button("Fatto") {
			@Override
			public void click() {
				confirm();
			}
		};
		this.okBtn.setBounds(417, 445, 405, 63);
		this.panel.add(this.okBtn);
		
		this.cancelBtn = new Button("Annulla") {
			@Override
			public void click() {
				dispose();
			}
		};
		this.cancelBtn.setBounds(0, 445, 418, 63);
		this.panel.add(this.cancelBtn);
		
		lblModello = new Label("Nome fermata");
		lblModello.setText("Modello:");
		lblModello.setBounds(15, 225, 211, 40);
		panel.add(lblModello);
		
		modelField = new TextField();
		modelField.setColumns(10);
		modelField.setBounds(15, 267, 790, 56);
		panel.add(modelField);
		
		typeBox = new JComboBox<>();
		typeBox.setModel(new DefaultComboBoxModel(new String[] {"Auto articolato (1C, 68P)", "Bi-articolato (5P)", "Coach (bus)", "Double-decker (1C, 118P)", "Elettrico (6C, 23P)", "Full-size (38P)", "Green-bus (3C, 2P)", "Elettrico-Hybrid (2C, 45P)", "Intercity (12P)", "Low-entry (71P)", "Low-floor (167P)", "MidiBus (1C, 74P)", "MiniBus (1C, 81P)", "Tetto panoramico (27P)"}));
		typeBox.setFont(Application.plain);
		typeBox.setBounds(15, 167, 790, 56);
		panel.add(typeBox);
		
		isActiveBox = new CheckBox("Attivo (se non è attivo, non potrà essere implementato nelle corse");
		isActiveBox.setBounds(15, 339, 790, 71);
		panel.add(isActiveBox);
	}

	

	public JTextField getPlateField() {
		return plateField;
	}



	public void setPlateField(JTextField plateField) {
		this.plateField = plateField;
	}



	public TextField getModelField() {
		return modelField;
	}



	public void setModelField(TextField modelField) {
		this.modelField = modelField;
	}



	public JComboBox<String> getTypeBox() {
		return typeBox;
	}



	public void setTypeBox(JComboBox<String> typeBox) {
		this.typeBox = typeBox;
	}



	public CheckBox getIsActiveBox() {
		return isActiveBox;
	}



	public void setIsActiveBox(CheckBox isActiveBox) {
		this.isActiveBox = isActiveBox;
	}



	public abstract void confirm();
}
