package busnet.login;

import javax.swing.JPanel;

import busnet.Application;
import busnet.guiElements.Button;
import busnet.guiElements.Label;
import busnet.guiElements.PasswordField;
import busnet.guiElements.TextField;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class LoginPanel extends JPanel {
	private JPanel panel;
	private JLabel label;
	private JLabel lblLogin;
	private JLabel usrLabel;
	private Label pswLabel;
	private JTextField userField;
	private PasswordField passField;
	private JLabel lblSeHaiBisogno;
	private Button loginButton;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setBackground(Color.DARK_GRAY);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 687, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 537, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		
		lblSeHaiBisogno = new JLabel("se hai bisogno di aiuto, contatta un addetto al personale",JLabel.CENTER);
		lblSeHaiBisogno.setFont(new Font("Yu Gothic UI", Font.PLAIN, 18));
		lblSeHaiBisogno.setForeground(Color.WHITE);
		lblSeHaiBisogno.setBounds(0, 61, 681, 76);
		panel.add(lblSeHaiBisogno);
		
		lblLogin = new JLabel("Finestra di accesso",JLabel.CENTER);
		lblLogin.setFont(Application.title);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setBounds(0, 0, 681, 120);
		panel.add(lblLogin);
		
		label = new JLabel("");
		label.setBackground(Application.defColor);
		label.setOpaque(true);
		label.setBounds(0, 0, 682, 137);
		panel.add(label);
		
		usrLabel = new Label("Username (codice fiscale)");
		usrLabel.setBounds(99, 187, 274, 34);
		panel.add(usrLabel);
		
		pswLabel = new Label("Password");
		pswLabel.setBounds(99, 288, 274, 34);
		panel.add(pswLabel);
		
		userField = new TextField();
		userField.setBounds(99, 228, 495, 50);
		panel.add(userField);
		userField.setColumns(10);
		
		passField = new PasswordField();
		passField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
			        confirm();
			    }
			}
		});
		passField.setColumns(10);
		passField.setBounds(99, 338, 495, 50);
		panel.add(passField);
		
		loginButton = new Button("Accedi") {
			@Override
			public void click() {
				confirm();
			}
		};
		loginButton.setBounds(433, 453, 161, 44);
		panel.add(loginButton);

	}
	
	public JTextField getUserField() {
		return userField;
	}

	public PasswordField getPassField() {
		return passField;
	}

	public abstract void confirm();
}
