package busnet;

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
import busnet.guiElements.Button;
import busnet.guiElements.StackTraceFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class ErrorWindow extends JDialog {


	private static final long serialVersionUID = -4569645232413238994L;
	private JPanel contentPane;
	private JLabel label;
	private JTextPane txtpnMessageText;
	private JLabel lblMessageTitle;
	private Button btnOk;
	private JLabel lblShowStackTrace;
	private JScrollPane scrollPane;
	/**
	 * @wbp.parser.constructor
	 */
	public ErrorWindow(Exception ex, String message) {
		super(Application.frame,"Window",true);
		ex.printStackTrace();
		init();
		Application.playSound("error.wav");
		txtpnMessageText.setText(message+":\n\n"+ex.getMessage());
		lblShowStackTrace = new JLabel("<HTML><U>Show stack trace ></U></HTML>");
		lblShowStackTrace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StackTraceFrame stf = new StackTraceFrame(ex);
				stf.setVisible(true);
			}
		});
		lblShowStackTrace.setForeground(SystemColor.textHighlight);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblShowStackTrace.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		lblShowStackTrace.setBounds(126, 258, 207, 20);
		contentPane.add(lblShowStackTrace);
		txtpnMessageText.setBackground(Color.WHITE);
		txtpnMessageText.setCaretPosition(0);
	}
	
	public ErrorWindow(String message) {
		super(Application.frame,"Window",true);
		init();
		Application.playSound("error.wav");
		txtpnMessageText.setText(message);
		txtpnMessageText.setBackground(Color.WHITE);

	}
	
	public void init() {

		String title = "Attenzione";
		setIconImage(Toolkit.getDefaultToolkit().getImage(ErrorWindow.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-warning.png")));
		setTitle(title);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(ErrorWindow.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-warning@2x.png")));
		label.setBounds(15, 16, 96, 96);
		contentPane.add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBounds(126, 82, 478, 170);
		contentPane.add(scrollPane);
		
		txtpnMessageText = new JTextPane();
		txtpnMessageText.setEditable(false);
		txtpnMessageText.setBackground(Color.WHITE);
		scrollPane.setViewportView(txtpnMessageText);
		txtpnMessageText.setFont(Application.plain);
		
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
		btnOk.setBounds(0, 311, 619, 66);
		contentPane.add(btnOk);
	}
	
	public void ok() {
		
	}
}
