package busnet.features.mainWindow;

import javax.swing.JPanel;

import busnet.Application;
import busnet.entity.Employee;
import busnet.features.employee.ManageEmployeeControl;
import busnet.features.line.ManageLinesControl;
import busnet.features.login.LoginControl;
import busnet.features.rides.ManageRidesControl;
import busnet.features.shift.ManageScheduleControl;
import busnet.guiElements.Button;
import busnet.guiElements.IconButton;
import busnet.guiElements.InfoWindow;
import busnet.guiElements.Label;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

public class BUSNETWindow extends JPanel {

	private Employee emp;
	private JPanel topPanel;
	private JPanel bodyPanel;
	private JLabel lblBenvenuto;
	private JLabel lblAddetto;
	private JLabel lblOggi;
	private JLabel label;
	private JLabel label_1;
	private JPanel panel_1;
	private JLabel label_2;
	private JLabel lblPannelloDegliStrumenti;
	private JLabel lblDaQuestoMonu;
	private Button button;
	private IconButton employeeIcon;
	private IconButton lineButton;
	private IconButton rideButton;
	private IconButton myShiftButton;
	private IconButton iconButton;
	private JLabel label_3;
	
	public BUSNETWindow(Employee emp) {
		setEmp(emp);
		init();
		loadPermission();
	}

	private void init() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{178, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.topPanel = new JPanel();
		topPanel.setBackground(Application.defColor);
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.insets = new Insets(0, 0, 0, 0);
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		add(this.topPanel, gbc_topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{17, 142, 638, 58, 284, 187, 0};
		gbl_topPanel.rowHeights = new int[]{74, 27, 69, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.topPanel.setLayout(gbl_topPanel);
		
		this.label_3 = new JLabel("");
		this.label_3.setIcon(new ImageIcon(BUSNETWindow.class.getResource("/busnet/resources/user120.png")));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 0, 5);
		gbc_label_3.gridheight = 3;
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 0;
		this.topPanel.add(this.label_3, gbc_label_3);
		
		this.lblBenvenuto = new JLabel("Benvenuto " + emp.getName() + " " + emp.getSurname());
		this.lblBenvenuto.setForeground(Color.WHITE);
		this.lblBenvenuto.setFont(Application.title);
		GridBagConstraints gbc_lblBenvenuto = new GridBagConstraints();
		gbc_lblBenvenuto.anchor = GridBagConstraints.SOUTH;
		gbc_lblBenvenuto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBenvenuto.insets = new Insets(0, 0, 5, 5);
		gbc_lblBenvenuto.gridx = 2;
		gbc_lblBenvenuto.gridy = 0;
		this.topPanel.add(this.lblBenvenuto, gbc_lblBenvenuto);
		
		this.lblOggi = new JLabel(new SimpleDateFormat("d/M/yyyy").format(new Date()),JLabel.RIGHT);
		this.lblOggi.setFont(Application.titleBig2);
		this.lblOggi.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblOggi = new GridBagConstraints();
		gbc_lblOggi.anchor = GridBagConstraints.SOUTH;
		gbc_lblOggi.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOggi.insets = new Insets(0, 0, 5, 5);
		gbc_lblOggi.gridheight = 2;
		gbc_lblOggi.gridx = 4;
		gbc_lblOggi.gridy = 0;
		this.topPanel.add(this.lblOggi, gbc_lblOggi);
		
		this.label_1 = new JLabel("");
		this.label_1.setIcon(new ImageIcon(BUSNETWindow.class.getResource("/busnet/resources/cal.png")));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_1.gridheight = 3;
		gbc_label_1.gridx = 5;
		gbc_label_1.gridy = 0;
		this.topPanel.add(this.label_1, gbc_label_1);
		
		this.lblAddetto = new Label(emp.getRole());
		this.lblAddetto.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblAddetto = new GridBagConstraints();
		gbc_lblAddetto.anchor = GridBagConstraints.NORTH;
		gbc_lblAddetto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddetto.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddetto.gridx = 2;
		gbc_lblAddetto.gridy = 1;
		this.topPanel.add(this.lblAddetto, gbc_lblAddetto);
		
		this.label = new JLabel(new SimpleDateFormat("E").format(new Date()),JLabel.RIGHT);
		this.label.setForeground(Color.WHITE);
		this.label.setFont(Application.title);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 2;
		this.topPanel.add(this.label, gbc_label);
		
		this.bodyPanel = new JPanel();
		bodyPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_bodyPanel = new GridBagConstraints();
		gbc_bodyPanel.fill = GridBagConstraints.BOTH;
		gbc_bodyPanel.gridx = 0;
		gbc_bodyPanel.gridy = 1;
		add(this.bodyPanel, gbc_bodyPanel);
		GridBagLayout gbl_bodyPanel = new GridBagLayout();
		gbl_bodyPanel.columnWidths = new int[]{0, 920, 0, 0};
		gbl_bodyPanel.rowHeights = new int[]{110, 384, 0, 0};
		gbl_bodyPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_bodyPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		this.bodyPanel.setLayout(gbl_bodyPanel);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBackground(Color.WHITE);
		this.panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		this.bodyPanel.add(this.panel_1, gbc_panel_1);
		
		this.lblDaQuestoMonu = new JLabel("Da questo menu puoi accedere alle funzionalit\u00E0 disponibili del sistema",JLabel.CENTER);
		this.lblDaQuestoMonu.setForeground(Color.WHITE);
		this.lblDaQuestoMonu.setFont(Application.plain);
		this.lblDaQuestoMonu.setBounds(0, 57, 900, 57);
		this.panel_1.add(this.lblDaQuestoMonu);
		
		this.lblPannelloDegliStrumenti = new JLabel("Pannello degli strumenti",JLabel.CENTER);
		this.lblPannelloDegliStrumenti.setFont(Application.title);
		this.lblPannelloDegliStrumenti.setForeground(Color.WHITE);
		this.lblPannelloDegliStrumenti.setBounds(0, 0, 915, 75);
		this.panel_1.add(this.lblPannelloDegliStrumenti);
		
		this.label_2 = new JLabel("");
		this.label_2.setBackground(Application.defColor);
		this.label_2.setOpaque(true);
		this.label_2.setBounds(0, 0, 915, 114);
		this.panel_1.add(this.label_2);
		
		this.button = new Button("Logout") {
			public void click() {
				LoginControl lc = new LoginControl();
				Application.setPanel(lc.getlWindow());
				InfoWindow info = new InfoWindow("Logout effettuato");
				info.setVisible(true);
			}
		};
		GridBagLayout gridBagLayout_1 = (GridBagLayout) this.button.getLayout();
		gridBagLayout_1.rowWeights = new double[]{1.0, 0.0, 1.0};
		gridBagLayout_1.rowHeights = new int[]{0, 64, 0};
		gridBagLayout_1.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gridBagLayout_1.columnWidths = new int[]{0, 6, 81, 0};
		this.button.setIcon("key35.png");
		this.button.setBounds(0, 315, 915, 64);
		this.panel_1.add(this.button);
		
		this.employeeIcon = new IconButton("Personale", "emp45.png") {
			@Override
			public void click() {
				new ManageEmployeeControl();
			}
		};
		this.employeeIcon.setBounds(207, 130, 158, 153);
		this.panel_1.add(this.employeeIcon);
		
		this.lineButton = new IconButton("Percorsi e mezzi","map45.png") {
			@Override
			public void click() {
				new ManageLinesControl();
			}
		};
		GridBagLayout gbl_lineButton = (GridBagLayout) this.lineButton.getLayout();
		gbl_lineButton.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_lineButton.rowHeights = new int[]{0, 0, 64, 0};
		gbl_lineButton.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_lineButton.columnWidths = new int[]{0, 81, 0};;
		this.lineButton.setBounds(380, 130, 158, 153);
		this.panel_1.add(this.lineButton);
		
		this.rideButton = new IconButton("Gestione corse","nav45.png") {

			@Override
			public void click() {
				new ManageRidesControl();
				
			}
			
		};
		GridBagLayout gbl_rideButton = (GridBagLayout) this.rideButton.getLayout();
		gbl_rideButton.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_rideButton.rowHeights = new int[]{0, 0, 64, 0};
		gbl_rideButton.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_rideButton.columnWidths = new int[]{0, 81, 0};
		this.rideButton.setBounds(726, 130, 158, 153);
		this.panel_1.add(this.rideButton);
		
		this.myShiftButton = new IconButton("Gestione turni","cal45.png") {

			@Override
			public void click() {
				new ManageScheduleControl();
				
			}
			
		};
		GridBagLayout gbl_myShiftButton = (GridBagLayout) this.myShiftButton.getLayout();
		gbl_myShiftButton.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_myShiftButton.rowHeights = new int[]{0, 0, 64, 0};
		gbl_myShiftButton.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_myShiftButton.columnWidths = new int[]{0, 81, 0};
		this.myShiftButton.setBounds(553, 130, 158, 153);
		this.panel_1.add(this.myShiftButton);
		
		this.iconButton = new IconButton("Il mio turno","shift45.png") {

			@Override
			public void click() {
				// TODO Auto-generated method stub
				
			}
			
		};
		GridBagLayout gridBagLayout_2 = (GridBagLayout) this.iconButton.getLayout();
		gridBagLayout_2.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gridBagLayout_2.rowHeights = new int[]{0, 0, 64, 0};
		gridBagLayout_2.columnWeights = new double[]{1.0, 0.0, 1.0};
		gridBagLayout_2.columnWidths = new int[]{0, 81, 0};
		this.iconButton.setBounds(34, 130, 158, 153);
		this.panel_1.add(this.iconButton);
		
	}

	private void loadPermission() {
		short permissionMask = emp.getPermission();
		if ((permissionMask & 1)!=0){
			this.employeeIcon.setActive(true);
		}
		else {
			this.employeeIcon.setActive(false);
		}
		if ((permissionMask & (1 << 1))!=0){
			this.lineButton.setActive(true);
		} else {
			this.lineButton.setActive(false);
		}
		if ((permissionMask & (1 << 2))!=0){
			this.rideButton.setActive(true);
		} else {
			this.rideButton.setActive(false);
		}
		if ((permissionMask & (1 << 3))!=0){
			this.myShiftButton.setActive(true);
		} else {

		}
		this.iconButton.setActive(true);
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
}
