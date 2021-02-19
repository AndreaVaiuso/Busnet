package busnet;

import javax.swing.JPanel;

import busnet.entity.Employee;
import busnet.features.employeeManagement.ManageEmployeeControl;
import busnet.features.lineManagement.ManageLinesControl;
import busnet.features.ridesManagement.ManageRidesControl;
import busnet.features.shiftManagement.ManageScheduleControl;
import busnet.features.shiftManagement.VisualizeShiftControl;
import busnet.guiElements.Button;
import busnet.guiElements.IconButton;
import busnet.guiElements.Label;
import busnet.login.LoginControl;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BUSNETWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Employee emp;
	
	private IconButton employeeIcon;
	private IconButton lineButton;
	private IconButton rideButton;
	private IconButton myShiftButton;
	private IconButton iconButton;
	/**
	 *Costruttore della finestra principale.
	 *@param emp Impiegato che effettua l'accesso
	 */
	public BUSNETWindow(Employee emp) {
		this.emp = emp;
		init();
		setCredentials();
	}

	private void init() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{178, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Application.defColor);
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.insets = new Insets(0, 0, 0, 0);
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		add(topPanel, gbc_topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{17, 142, 638, 58, 284, 187, 0};
		gbl_topPanel.rowHeights = new int[]{74, 27, 69, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(BUSNETWindow.class.getResource("/busnet/resources/user120.png")));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 0, 5);
		gbc_label_3.gridheight = 3;
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 0;
		topPanel.add(label_3, gbc_label_3);
		
		JLabel lblBenvenuto = new JLabel("Benvenuto " + emp.getName() + " " + emp.getSurname());
		lblBenvenuto.setForeground(Color.WHITE);
		lblBenvenuto.setFont(Application.title);
		GridBagConstraints gbc_lblBenvenuto = new GridBagConstraints();
		gbc_lblBenvenuto.anchor = GridBagConstraints.SOUTH;
		gbc_lblBenvenuto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBenvenuto.insets = new Insets(0, 0, 5, 5);
		gbc_lblBenvenuto.gridx = 2;
		gbc_lblBenvenuto.gridy = 0;
		topPanel.add(lblBenvenuto, gbc_lblBenvenuto);
		
		JLabel lblOggi = new JLabel(new SimpleDateFormat("d/M/yyyy").format(new Date()),JLabel.RIGHT);
		lblOggi.setFont(Application.titleBig2);
		lblOggi.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblOggi = new GridBagConstraints();
		gbc_lblOggi.anchor = GridBagConstraints.SOUTH;
		gbc_lblOggi.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOggi.insets = new Insets(0, 0, 5, 5);
		gbc_lblOggi.gridheight = 2;
		gbc_lblOggi.gridx = 4;
		gbc_lblOggi.gridy = 0;
		topPanel.add(lblOggi, gbc_lblOggi);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(BUSNETWindow.class.getResource("/busnet/resources/cal.png")));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_1.gridheight = 3;
		gbc_label_1.gridx = 5;
		gbc_label_1.gridy = 0;
		topPanel.add(label_1, gbc_label_1);
		
		Label lblAddetto = new Label(emp.getRole());
		lblAddetto.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblAddetto = new GridBagConstraints();
		gbc_lblAddetto.anchor = GridBagConstraints.NORTH;
		gbc_lblAddetto.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAddetto.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddetto.gridx = 2;
		gbc_lblAddetto.gridy = 1;
		topPanel.add(lblAddetto, gbc_lblAddetto);
		
		JLabel label = new JLabel(new SimpleDateFormat("E").format(new Date()),JLabel.RIGHT);
		label.setForeground(Color.WHITE);
		label.setFont(Application.title);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 2;
		topPanel.add(label, gbc_label);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_bodyPanel = new GridBagConstraints();
		gbc_bodyPanel.fill = GridBagConstraints.BOTH;
		gbc_bodyPanel.gridx = 0;
		gbc_bodyPanel.gridy = 1;
		add(bodyPanel, gbc_bodyPanel);
		GridBagLayout gbl_bodyPanel = new GridBagLayout();
		gbl_bodyPanel.columnWidths = new int[]{0, 920, 0, 0};
		gbl_bodyPanel.rowHeights = new int[]{110, 384, 0, 0};
		gbl_bodyPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_bodyPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		bodyPanel.setLayout(gbl_bodyPanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		bodyPanel.add(panel_1, gbc_panel_1);
		
		JLabel lblDaQuestoMonu = new JLabel("Da questo menu puoi accedere alle funzionalit\u00E0 disponibili del sistema",JLabel.CENTER);
		lblDaQuestoMonu.setForeground(Color.WHITE);
		lblDaQuestoMonu.setFont(Application.plain);
		lblDaQuestoMonu.setBounds(0, 57, 900, 57);
		panel_1.add(lblDaQuestoMonu);
		
		JLabel lblPannelloDegliStrumenti = new JLabel("Pannello degli strumenti",JLabel.CENTER);
		lblPannelloDegliStrumenti.setFont(Application.title);
		lblPannelloDegliStrumenti.setForeground(Color.WHITE);
		lblPannelloDegliStrumenti.setBounds(0, 0, 915, 75);
		panel_1.add(lblPannelloDegliStrumenti);
		
		JLabel label_2 = new JLabel("");
		label_2.setBackground(Application.defColor);
		label_2.setOpaque(true);
		label_2.setBounds(0, 0, 915, 114);
		panel_1.add(label_2);
		
		Button button = new Button("Logout") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void click() {
				clickLogoutButton();
			}
		};
		GridBagLayout gridBagLayout_1 = (GridBagLayout) button.getLayout();
		gridBagLayout_1.rowWeights = new double[]{1.0, 0.0, 1.0};
		gridBagLayout_1.rowHeights = new int[]{0, 64, 0};
		gridBagLayout_1.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gridBagLayout_1.columnWidths = new int[]{0, 6, 81, 0};
		button.setIcon("key35.png");
		button.setBounds(0, 315, 915, 64);
		panel_1.add(button);
		
		employeeIcon = new IconButton("Personale", "emp45.png") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void click() {
				clickManageEmployeeButton();
			}
		};
		employeeIcon.setBounds(207, 130, 158, 153);
		panel_1.add(employeeIcon);
		
		lineButton = new IconButton("Percorsi e mezzi","map45.png") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void click() {
				clickManageLinesButton();
			}
		};
		GridBagLayout gbl_lineButton = (GridBagLayout) lineButton.getLayout();
		gbl_lineButton.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_lineButton.rowHeights = new int[]{0, 0, 64, 0};
		gbl_lineButton.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_lineButton.columnWidths = new int[]{0, 81, 0};;
		lineButton.setBounds(380, 130, 158, 153);
		panel_1.add(lineButton);
		
		rideButton = new IconButton("Gestione corse","nav45.png") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void click() {
				clickManageRidesButton();
				
			}
			
		};
		GridBagLayout gbl_rideButton = (GridBagLayout) rideButton.getLayout();
		gbl_rideButton.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_rideButton.rowHeights = new int[]{0, 0, 64, 0};
		gbl_rideButton.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_rideButton.columnWidths = new int[]{0, 81, 0};
		rideButton.setBounds(726, 130, 158, 153);
		panel_1.add(rideButton);
		
		myShiftButton = new IconButton("Gestione turni","cal45.png") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void click() {
				clickManageScheduleButton();
				
			}
			
		};
		GridBagLayout gbl_myShiftButton = (GridBagLayout) myShiftButton.getLayout();
		gbl_myShiftButton.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_myShiftButton.rowHeights = new int[]{0, 0, 64, 0};
		gbl_myShiftButton.columnWeights = new double[]{1.0, 0.0, 1.0};
		gbl_myShiftButton.columnWidths = new int[]{0, 81, 0};
		myShiftButton.setBounds(553, 130, 158, 153);
		panel_1.add(myShiftButton);
		
		iconButton = new IconButton("Il mio turno","shift45.png") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void click() {
				clickVisualizeShiftButton();
				
			}
			
		};
		GridBagLayout gridBagLayout_2 = (GridBagLayout) iconButton.getLayout();
		gridBagLayout_2.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gridBagLayout_2.rowHeights = new int[]{0, 0, 64, 0};
		gridBagLayout_2.columnWeights = new double[]{1.0, 0.0, 1.0};
		gridBagLayout_2.columnWidths = new int[]{0, 81, 0};
		iconButton.setBounds(34, 130, 158, 153);
		panel_1.add(iconButton);
		
	}

	protected void clickVisualizeShiftButton() {
		new VisualizeShiftControl(emp.getCf());
	}

	protected void clickManageScheduleButton() {
		new ManageScheduleControl();
		
	}

	protected void clickManageRidesButton() {
		new ManageRidesControl();
		
	}

	protected void clickManageLinesButton() {
		new ManageLinesControl();
		
	}

	protected void clickManageEmployeeButton() {
		new ManageEmployeeControl();
		
	}

	protected void clickLogoutButton() {
		LoginControl lc = new LoginControl();
		Application.setPanel(lc.getlWindow());
		InfoWindow info = new InfoWindow("Logout effettuato");
		info.setVisible(true);
		
	}
	/**
	 *Metodo di impostazione delle credenziali. Per la corretta visualizzazione e' necessario che le icone da disattivare abbiano un'altra icona nella stessa
	 * posizione con nome identico seguito da ".disabled.png"
	 */
	private void setCredentials() {
		short permissionMask = emp.getPermission();
		if ((permissionMask & 1)!=0){
			employeeIcon.setActive(true);
		}
		else {
			employeeIcon.setActive(false);
		}
		if ((permissionMask & (1 << 1))!=0){
			lineButton.setActive(true);
		} else {
			lineButton.setActive(false);
		}
		if ((permissionMask & (1 << 2))!=0){
			rideButton.setActive(true);
		} else {
			rideButton.setActive(false);
		}
		if ((permissionMask & (1 << 3))!=0){
			myShiftButton.setActive(true);
		} else {

		}
		iconButton.setActive(true);
	}

}
