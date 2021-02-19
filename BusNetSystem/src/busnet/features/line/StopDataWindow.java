package busnet.features.line;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.Window;
import busnet.guiElements.Button;
import busnet.guiElements.Label;
import busnet.guiElements.TextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public abstract class StopDataWindow extends Window {
	private JPanel panel;
	private JLabel lblStopId;
	private JLabel lblNomeFermata;
	private JTextField stopIdField;
	private JTextField stopNameField;
	private Button okBtn;
	private Button cancelBtn;

	public StopDataWindow() {
		super("Nuova fermata","Aggiungi una nuova fermata. Dopo aver finito potrai implementarla in un percorso di una linea","stop.png");
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		setBounds(200,200,822,508);
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
		this.lblStopId.setBounds(15, 16, 717, 34);
		this.panel.add(this.lblStopId);
		
		this.lblNomeFermata = new Label("Nome fermata");
		this.lblNomeFermata.setBounds(15, 111, 211, 40);
		this.panel.add(this.lblNomeFermata);
		
		this.stopIdField = new TextField();
		this.stopIdField.setBounds(15, 51, 790, 56);
		this.panel.add(this.stopIdField);
		this.stopIdField.setColumns(10);
		
		this.stopNameField = new TextField();
		this.stopNameField.setColumns(10);
		this.stopNameField.setBounds(15, 153, 790, 56);
		this.panel.add(this.stopNameField);
		
		this.okBtn = new Button("Fatto") {
			@Override
			public void click() {
				confirm();
			}
		};
		this.okBtn.setBounds(415, 254, 405, 63);
		this.panel.add(this.okBtn);
		
		this.cancelBtn = new Button("Annulla") {
			@Override
			public void click() {
				dispose();
			}
		};
		this.cancelBtn.setBounds(0, 254, 418, 63);
		this.panel.add(this.cancelBtn);
	}
	
	public JTextField getStopIdField() {
		return stopIdField;
	}

	public void setStopIdField(JTextField stopIdField) {
		this.stopIdField = stopIdField;
	}

	public JTextField getStopNameField() {
		return stopNameField;
	}

	public void setStopNameField(JTextField stopNameField) {
		this.stopNameField = stopNameField;
	}

	public abstract void confirm();
}
