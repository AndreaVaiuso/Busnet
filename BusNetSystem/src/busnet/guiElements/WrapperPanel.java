package busnet.guiElements;

import javax.swing.JPanel;
import javax.swing.Timer;

import busnet.Application;
import busnet.dbmsManagement.DBInterface;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WrapperPanel extends JPanel {
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblTime;
	private JLabel label;
	private JLabel dbStatusIcon;

	public WrapperPanel(JPanel nestedPanel) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{107, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		panel.setBackground(Application.defColor.darker());
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{42, 119, 0, 85, 152, 44, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(WrapperPanel.class.getResource("/busnet/resources/busnetLogo.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		dbStatusIcon = new JLabel("");
		this.dbStatusIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ConnectionWindow conn = new ConnectionWindow();
				conn.setVisible(true);
			}
		});
		
		if(DBInterface.chkConnection()) {
			dbStatusIcon.setIcon(new ImageIcon(WrapperPanel.class.getResource("/busnet/resources/dbOn.png")));
		}
		else {
			dbStatusIcon.setIcon(new ImageIcon(WrapperPanel.class.getResource("/busnet/resources/dbOff.png")));
		}
		GridBagConstraints gbc_dbStatusIcon = new GridBagConstraints();
		gbc_dbStatusIcon.insets = new Insets(0, 0, 5, 5);
		gbc_dbStatusIcon.gridx = 3;
		gbc_dbStatusIcon.gridy = 1;
		panel.add(dbStatusIcon, gbc_dbStatusIcon);
		
		lblTime = new JLabel("00:00");
		lblTime.setFont(Application.titleBig);
		lblTime.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblTime.gridx = 4;
		gbc_lblTime.gridy = 1;
		panel.add(lblTime, gbc_lblTime);
		
		final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	    int interval = 100; // 1000 ms

	    new Timer(interval, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Calendar now = Calendar.getInstance();
	            lblTime.setText(dateFormat.format(now.getTime()));
	        }
	    }).start();
		
		panel_1 = nestedPanel;
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);

	}

}
