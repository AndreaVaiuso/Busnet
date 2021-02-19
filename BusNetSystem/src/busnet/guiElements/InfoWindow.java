package busnet.guiElements;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import busnet.Application;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class InfoWindow extends JDialog {


	private static final long serialVersionUID = -4569645232413238994L;
	private JPanel contentPane;
	private JLabel label;
	private JTextPane txtpnMessageText;
	private JLabel lblMessageTitle;
	private Button btnOk;
	private JScrollPane scrollPane;
	
	public InfoWindow(String message) {
		super(Application.frame,"Window",true);
		init();
		Application.playSound("done.wav");
		txtpnMessageText.setText(message);
	}
	
	public void init() {
		
		String title = "Informazione";
		setIconImage(Toolkit.getDefaultToolkit().getImage(InfoWindow.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-information.png")));
		setTitle(title);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 339);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(InfoWindow.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-information@2x.png")));
		label.setBounds(15, 16, 96, 96);
		contentPane.add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBounds(126, 82, 478, 96);
		contentPane.add(scrollPane);
		
		txtpnMessageText = new JTextPane();
		txtpnMessageText.setBorder(BorderFactory.createEmptyBorder());
		txtpnMessageText.setBackground(Color.WHITE);
		scrollPane.setViewportView(txtpnMessageText);
		txtpnMessageText.setFont(Application.plain);
		txtpnMessageText.setEditable(false);
		
		lblMessageTitle = new JLabel(title);
		lblMessageTitle.setForeground(new Color(0, 191, 255));
		lblMessageTitle.setFont(Application.title);
		lblMessageTitle.setBounds(126, 16, 418, 50);
		contentPane.add(lblMessageTitle);
		
		btnOk = new Button();
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				ok();
				dispose();
			}
		});
		btnOk.setText("Ok");
		btnOk.setBounds(0, 240, 619, 59);
		contentPane.add(btnOk);
	}
	
	public void ok() {
		
	}
}
