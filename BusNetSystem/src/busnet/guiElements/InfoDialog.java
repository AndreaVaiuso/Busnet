package busnet.guiElements;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.Application;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class InfoDialog extends JDialog {
	private JLabel label;
	private JLabel lblStoInviandoLa;

	public InfoDialog() {
		getContentPane().setBackground(Color.WHITE);
		setModal(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 529, 255);
		setResizable(true);
		getContentPane().setLayout(null);
		
		this.label = new JLabel("");
		this.label.setIcon(new ImageIcon(InfoDialog.class.getResource("/busnet/resources/sending2.gif")));
		this.label.setBounds(173, 0, 154, 110);
		getContentPane().add(this.label);
		
		this.lblStoInviandoLa = new JLabel("Sto inviando la mail", JLabel.CENTER);
		this.lblStoInviandoLa.setFont(Application.bold);
		this.lblStoInviandoLa.setBounds(0, 106, 507, 90);
		getContentPane().add(this.lblStoInviandoLa);
		
	}
}
