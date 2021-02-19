package busnet;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class Window extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblIcon;
	private JLabel lblTitle;
	private JTextPane txtpnSubtitle;

	public Window(String title, String subtitle, String icon) {
		super(Application.frame,"Window",true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/busnet/resources/null.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200,200,1300,900);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{155, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panel = new JPanel();
		panel.setBackground(Application.defColor.darker());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{20, 120, 11, 1156, 0};
		gbl_panel.rowHeights = new int[]{0, 48, 67, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblIcon = new JLabel();
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.fill = GridBagConstraints.BOTH;
		gbc_lblIcon.insets = new Insets(0, 0, 0, 0);
		gbc_lblIcon.gridheight = 2;
		gbc_lblIcon.gridx = 1;
		gbc_lblIcon.gridy = 1;
		panel.add(lblIcon, gbc_lblIcon);
		
		lblTitle = new JLabel("Title");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(Application.title);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(0, 0, 0, 0);
		gbc_lblTitle.gridx = 3;
		gbc_lblTitle.gridy = 1;
		panel.add(lblTitle, gbc_lblTitle);
		
		txtpnSubtitle = new JTextPane();
		txtpnSubtitle.setForeground(Color.WHITE);
		txtpnSubtitle.setFont(Application.plain);
		txtpnSubtitle.setEditable(false);
		txtpnSubtitle.setText("subtitle");
		txtpnSubtitle.setOpaque(false);
		txtpnSubtitle.setBorder(BorderFactory.createEmptyBorder());
		GridBagConstraints gbc_txtpnSubtitle = new GridBagConstraints();
		gbc_txtpnSubtitle.insets = new Insets(0, 0, 0, 0);
		gbc_txtpnSubtitle.fill = GridBagConstraints.BOTH;
		gbc_txtpnSubtitle.gridx = 3;
		gbc_txtpnSubtitle.gridy = 2;
		panel.add(txtpnSubtitle, gbc_txtpnSubtitle);
		
		lblTitle.setText(title);
		setTitle(title);
		txtpnSubtitle.setText(subtitle);
		try {
			lblIcon.setIcon(new ImageIcon(Window.class.getResource("/busnet/resources/"+icon)));
			setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/busnet/resources/"+icon)));
		} catch(Exception e) {
			lblIcon.setIcon(new ImageIcon(Window.class.getResource("/busnet/resources/null.png")));
		}
		
		ActionListener escListener = new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            dispose();
	            
	        }
	    };

	    getRootPane().registerKeyboardAction(escListener,
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}
	
}
