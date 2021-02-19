package busnet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.guiElements.Button;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ConfirmationWindow extends JDialog {

	private static final long serialVersionUID = -4569645232413238994L;
	
	public ConfirmationWindow(String title, String message) {
		super(Application.frame,"Window",true);
		Application.playSound("question.wav");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConfirmationWindow.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-confirm.png")));
		setTitle(title);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 339);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ConfirmationWindow.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-confirm@2x.png")));
		label.setBounds(15, 16, 96, 96);
		contentPane.add(label);
		
		JTextPane txtpnMessageText = new JTextPane();
		txtpnMessageText.setFont(Application.plain);
		txtpnMessageText.setEditable(false);
		txtpnMessageText.setOpaque(false);
		txtpnMessageText.setText(message);
		txtpnMessageText.setBounds(126, 82, 478, 121);
		contentPane.add(txtpnMessageText);
		
		JLabel lblMessageTitle = new JLabel(title);
		lblMessageTitle.setForeground(new Color(0, 191, 255));
		lblMessageTitle.setFont(Application.title);
		lblMessageTitle.setBounds(126, 16, 418, 50);
		contentPane.add(lblMessageTitle);
		
		Button btnOk = new Button();
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				confirm();
				dispose();
			}
		});
		btnOk.setText("Si");
		btnOk.setBounds(313, 244, 306, 66);
		contentPane.add(btnOk);
		
		Button btnNo = new Button();
		btnNo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});
		btnNo.setText("No");
		btnNo.setBounds(0, 244, 314, 66);
		contentPane.add(btnNo);
	}
	
	public void confirm() {
		
	}
}
