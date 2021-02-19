package busnet.features.line;


import busnet.Window;
import busnet.entity.Bus;
import busnet.entity.Line;
import busnet.entity.Stop;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import busnet.guiElements.Button;
import busnet.guiElements.ConfirmationWindow;

public abstract class ManageLinesWindow extends Window {
	private JPanel panel;
	private JPanel buttonPanel;
	private JPanel content;
	private Button stopButton;
	private Button busButton;
	private Button lineButton;
	private ManageLinesPanel mlPanel;
	private ManageBusPanel mbPanel;
	private ManageStopsPanel msPanel;
	
	public ManageLinesWindow(ArrayList<Line> lineList, ArrayList<Stop> stopList, ArrayList<Bus> busList) {
		super("Gestione linee, percorsi e mezzi","Da questa finestra è possibile gestire tutto ciò che riguarda le linee, le fermate ed i mezzi della tua azienda","bus.png");
		mlPanel = new ManageLinesPanel(lineList) {
			@Override
			public void addLine() {
				clickAddLineBtn();
			}

			@Override
			public void removeLine() {
				clickRemoveLineBtn();
				
			}

			@Override
			public void modifyLine() {
				clickUpdateLineBtn();
				
			}
			
		};
		mbPanel = new ManageBusPanel(busList) {

			@Override
			public void clickAddBtn() {
				clickAddBusBtn();
			}

			@Override
			public void clickDelBtn() {
				clickDelBusBtn();
			}
			
		};
		msPanel = new ManageStopsPanel(stopList) {
			@Override
			public void clickAddBtn() {
				clickAddStopBtn();
			}
			@Override
			public void clickDelBtn() {
				clickDelStopBtn();
			}
		};
		init();	
		content.setLayout(new CardLayout());
		content.add(mlPanel, "lines");
		content.add(mbPanel, "bus");
		content.add(msPanel, "stop");
		setPanel("lines");
		lineButton.setPressed(true);
	}
	
	public ManageLinesPanel getMlPanel() {
		return mlPanel;
	}

	public void setMlPanel(ManageLinesPanel mlPanel) {
		this.mlPanel = mlPanel;
	}

	public ManageBusPanel getMbPanel() {
		return mbPanel;
	}

	public void setMbPanel(ManageBusPanel mbPanel) {
		this.mbPanel = mbPanel;
	}

	public ManageStopsPanel getMsPanel() {
		return msPanel;
	}

	public void setMsPanel(ManageStopsPanel msPanel) {
		this.msPanel = msPanel;
	}
	
	private void init() {
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{65, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		this.panel.setLayout(gbl_panel);
		
		this.buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.insets = new Insets(0, 0, 0, 0);
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 0;
		this.panel.add(this.buttonPanel, gbc_buttonPanel);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0};
		gbl_buttonPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.buttonPanel.setLayout(gbl_buttonPanel);
		
		this.lineButton = new Button("Gestione linee") {
			@Override
			public void click() {
				setPanel("lines");
				lineButton.setPressed(true);
			}
		};
		GridBagConstraints gbc_lineButton = new GridBagConstraints();
		gbc_lineButton.insets = new Insets(0, 0, 0, 0);
		gbc_lineButton.fill = GridBagConstraints.BOTH;
		gbc_lineButton.gridx = 0;
		gbc_lineButton.gridy = 0;
		this.buttonPanel.add(this.lineButton, gbc_lineButton);
		
		this.stopButton = new Button("Gestione fermate") {
			@Override
			public void click() {
				setPanel("stop");
				stopButton.setPressed(true);
			}
		};
		GridBagConstraints gbc_stopButton = new GridBagConstraints();
		gbc_stopButton.insets = new Insets(0, 0, 0, 0);
		gbc_stopButton.fill = GridBagConstraints.BOTH;
		gbc_stopButton.gridx = 1;
		gbc_stopButton.gridy = 0;
		this.buttonPanel.add(this.stopButton, gbc_stopButton);
		
		this.busButton = new Button("Gestione autobus") {
			@Override
			public void click() {
				setPanel("bus");
				busButton.setPressed(true);
			}
		};
		GridBagConstraints gbc_busButton = new GridBagConstraints();
		gbc_busButton.fill = GridBagConstraints.BOTH;
		gbc_busButton.gridx = 2;
		gbc_busButton.gridy = 0;
		this.buttonPanel.add(this.busButton, gbc_busButton);
		
		this.content = new JPanel();
		GridBagConstraints gbc_content = new GridBagConstraints();
		gbc_content.fill = GridBagConstraints.BOTH;
		gbc_content.gridx = 0;
		gbc_content.gridy = 1;
		this.panel.add(this.content, gbc_content);
	}
	
	public void setPanel(String panelName) {
		this.stopButton.setPressed(false);
		this.lineButton.setPressed(false);
		this.busButton.setPressed(false);
		CardLayout layout = (CardLayout) content.getLayout();
		layout.show(content, panelName);
	}
	
	public abstract void clickAddStopBtn();
	public abstract void clickDelStopBtn();
	public abstract void clickAddBusBtn();
	public abstract void clickDelBusBtn();
	public abstract void clickAddLineBtn();
	public abstract void clickRemoveLineBtn();
	public abstract void clickUpdateLineBtn();
	
}
