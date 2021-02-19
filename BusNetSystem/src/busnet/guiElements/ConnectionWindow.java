package busnet.guiElements;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.dbmsManagement.DBInterface;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnectionWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField url;
	private JTextField driver;
	private JTextField user;
	private JPasswordField passwordField;

	public ConnectionWindow() {
		setResizable(false);
		setBounds(100, 100, 499, 338);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMyurl = new JLabel("MyUrl");
			lblMyurl.setBounds(12, 13, 56, 16);
			contentPanel.add(lblMyurl);
		}
		{
			JLabel lblMydriver = new JLabel("MyDriver");
			lblMydriver.setBounds(12, 68, 56, 16);
			contentPanel.add(lblMydriver);
		}
		{
			JLabel lblUsername = new JLabel("UserName");
			lblUsername.setBounds(12, 128, 385, 16);
			contentPanel.add(lblUsername);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(12, 190, 56, 16);
			contentPanel.add(lblPassword);
		}
		
		this.url = new JTextField(DBInterface.myUrl);
		this.url.setBounds(12, 33, 469, 22);
		contentPanel.add(this.url);
		this.url.setColumns(10);
		
		this.driver = new JTextField(DBInterface.myDriver);
		this.driver.setColumns(10);
		this.driver.setBounds(12, 93, 469, 22);
		contentPanel.add(this.driver);
		
		this.user = new JTextField(DBInterface.usr);
		this.user.setColumns(10);
		this.user.setBounds(12, 155, 469, 22);
		contentPanel.add(this.user);
		
		this.passwordField = new JPasswordField(DBInterface.psw);
		this.passwordField.setBounds(12, 219, 469, 22);
		contentPanel.add(this.passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						DBInterface.myUrl = url.getText();
						DBInterface.myDriver = driver.getText();
						DBInterface.usr = user.getText();
						DBInterface.psw = String.valueOf(passwordField.getPassword());
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
