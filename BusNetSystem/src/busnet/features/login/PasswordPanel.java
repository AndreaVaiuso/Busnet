package busnet.features.login;

import javax.swing.JPanel;

import busnet.Application;
import busnet.guiElements.Button;
import busnet.guiElements.Label;
import busnet.guiElements.PasswordField;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;

public abstract class PasswordPanel extends JPanel {
	private JPanel panel;
	private JLabel label;
	private JLabel lblReimpostaLaTua;
	private JLabel lblPerLaTua;
	private JLabel lblNuovaPassword;
	private JLabel lblRipetiPassword;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private Button confirmButton;

	/**
	 * Create the panel.
	 */
	public PasswordPanel() {
		setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 687, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 566, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		this.panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(this.panel, gbc_panel);
		
		this.lblPerLaTua = new JLabel("Per la tua sicurezza, ti chiediamo di reimpostare la password temporanea",JLabel.CENTER);
		this.lblPerLaTua.setForeground(Color.WHITE);
		this.lblPerLaTua.setFont(Application.plain);
		this.lblPerLaTua.setBounds(0, 66, 682, 79);
		this.panel.add(this.lblPerLaTua);
		
		this.lblReimpostaLaTua = new JLabel("Reimposta la tua password", JLabel.CENTER);
		this.lblReimpostaLaTua.setFont(Application.title);
		this.lblReimpostaLaTua.setForeground(Color.WHITE);
		this.lblReimpostaLaTua.setBounds(0, 0, 682, 107);
		this.panel.add(this.lblReimpostaLaTua);
		
		this.label = new JLabel("");
		this.label.setBounds(0, 0, 682, 145);
		this.panel.add(this.label);
		label.setOpaque(true);
		label.setBackground(Application.defColor);
		
		this.lblNuovaPassword = new Label("Nuova password:");
		this.lblNuovaPassword.setBounds(61, 187, 210, 34);
		this.panel.add(this.lblNuovaPassword);
		
		this.lblRipetiPassword = new Label("Ripeti password:");
		this.lblRipetiPassword.setBounds(61, 316, 210, 34);
		this.panel.add(this.lblRipetiPassword);
		
		this.passwordField = new PasswordField();
		this.passwordField.setBounds(61, 237, 537, 50);
		this.panel.add(this.passwordField);
		
		this.passwordField_1 = new PasswordField();
		this.passwordField_1.setBounds(61, 366, 537, 50);
		this.panel.add(this.passwordField_1);
		
		this.confirmButton = new Button("Conferma") {
			@Override
			public void click() {
				confirm();
			}
		};
		this.confirmButton.setBounds(408, 484, 190, 50);
		this.panel.add(this.confirmButton);

	}
	
	public abstract void confirm();

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JPasswordField getPasswordField_1() {
		return passwordField_1;
	}
}
