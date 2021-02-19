package busnet.features.ridesManagement;

import java.awt.EventQueue;

import javax.swing.JDialog;

import busnet.entity.Line;
import busnet.guiElements.Button;
import busnet.guiElements.Label;
import busnet.guiElements.LineList;
import busnet.guiElements.Window;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Insets;

public abstract class ManageRidesWindow extends Window {
	private JPanel panel;
	private LineList tabSelectionList;
	private int daySelected;
	private String lineSelected;
	private ArrayList<Line> lineList;
	private JLabel lblSelezionaLaLinea;
	private JPanel panel_1;
	private Button btn6;
	private Button btn5;
	private Button btn4;
	private Button btn3;
	private Button btn2;
	private Button btn1;
	private Button btn0;
	private Button subBtn;
	
	public ManageRidesWindow(ArrayList<Line> lineList) {
		super("Gestione corse","Qui puoi gestire le corse che verranno instanziate. Innanzitutto scegli una linea ed un giorno della settimana.\n"
				+ "Se ci sono turni temporanei dovresti poter sostituire il personale tramite il bottone sostituzioni","ride120.png");
		setLineList(lineList);
		init();
		checkSubstitutions();
	}

	public void init() {
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowHeights = new int[]{163, 60, 0, 66};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		
		this.subBtn = new Button("Sostituzioni") {
			@Override
			public void click() {
				showSubstitutionWindow();
			}
		};
		GridBagConstraints gbc_subBtn = new GridBagConstraints();
		gbc_subBtn.insets = new Insets(0, 0, 5, 0);
		gbc_subBtn.fill = GridBagConstraints.BOTH;
		gbc_subBtn.gridx = 0;
		gbc_subBtn.gridy = 1;
		getContentPane().add(this.subBtn, gbc_subBtn);
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{16, 1247, 15, 0};
		gbl_panel.rowHeights = new int[]{15, 46, 572, 14, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		this.panel.setLayout(gbl_panel);
		
		this.lblSelezionaLaLinea = new Label("Seleziona la linea:");
		GridBagConstraints gbc_lblSelezionaLaLinea = new GridBagConstraints();
		gbc_lblSelezionaLaLinea.anchor = GridBagConstraints.WEST;
		gbc_lblSelezionaLaLinea.fill = GridBagConstraints.VERTICAL;
		gbc_lblSelezionaLaLinea.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelezionaLaLinea.gridx = 1;
		gbc_lblSelezionaLaLinea.gridy = 1;
		this.panel.add(this.lblSelezionaLaLinea, gbc_lblSelezionaLaLinea);
		
		this.tabSelectionList = new LineList(lineList) {

			@Override
			public void clickSelectBtn() {
				setLineSelected(this.getLineIdSelected());
			}
			
		};
		GridBagConstraints gbc_tabSelectionList = new GridBagConstraints();
		gbc_tabSelectionList.insets = new Insets(0, 0, 5, 5);
		gbc_tabSelectionList.fill = GridBagConstraints.BOTH;
		gbc_tabSelectionList.gridx = 1;
		gbc_tabSelectionList.gridy = 2;
		this.panel.add(this.tabSelectionList, gbc_tabSelectionList);
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		getContentPane().add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.btn0 = new Button("Lunedì") {
			@Override
			public void click() {
				setDaySelected(0);
				confirm();
			}
		};
		GridBagConstraints gbc_btn0 = new GridBagConstraints();
		gbc_btn0.insets = new Insets(0, 0, 0, 0);
		gbc_btn0.fill = GridBagConstraints.BOTH;
		gbc_btn0.gridx = 0;
		gbc_btn0.gridy = 0;
		this.panel_1.add(this.btn0, gbc_btn0);
		
		this.btn1 = new Button("Martedì") {
			@Override
			public void click() {
				setDaySelected(1);
				confirm();
			}
		};
		GridBagConstraints gbc_btn1 = new GridBagConstraints();
		gbc_btn1.insets = new Insets(0, 0, 0, 0);
		gbc_btn1.fill = GridBagConstraints.BOTH;
		gbc_btn1.gridx = 1;
		gbc_btn1.gridy = 0;
		this.panel_1.add(this.btn1, gbc_btn1);
		
		this.btn2 = new Button("Mercoledì") {
			@Override
			public void click() {
				setDaySelected(2);
				confirm();
			}
		};
		GridBagConstraints gbc_btn2 = new GridBagConstraints();
		gbc_btn2.insets = new Insets(0, 0, 0, 0);
		gbc_btn2.fill = GridBagConstraints.BOTH;
		gbc_btn2.gridx = 2;
		gbc_btn2.gridy = 0;
		this.panel_1.add(this.btn2, gbc_btn2);
		
		this.btn3 = new Button("Giovedì") {
			@Override
			public void click() {
				setDaySelected(3);
				confirm();
			}
		};
		GridBagConstraints gbc_btn3 = new GridBagConstraints();
		gbc_btn3.insets = new Insets(0, 0, 0, 0);
		gbc_btn3.fill = GridBagConstraints.BOTH;
		gbc_btn3.gridx = 3;
		gbc_btn3.gridy = 0;
		this.panel_1.add(this.btn3, gbc_btn3);
		
		this.btn4 = new Button("Venerdì") {
			@Override
			public void click() {
				setDaySelected(4);
				confirm();
			}
		};
		GridBagConstraints gbc_btn4 = new GridBagConstraints();
		gbc_btn4.insets = new Insets(0, 0, 0, 0);
		gbc_btn4.fill = GridBagConstraints.BOTH;
		gbc_btn4.gridx = 4;
		gbc_btn4.gridy = 0;
		this.panel_1.add(this.btn4, gbc_btn4);
		
		this.btn5 = new Button("Sabato") {
			@Override
			public void click() {
				setDaySelected(5);
				confirm();
			}
		};
		GridBagConstraints gbc_btn5 = new GridBagConstraints();
		gbc_btn5.insets = new Insets(0, 0, 0, 0);
		gbc_btn5.fill = GridBagConstraints.BOTH;
		gbc_btn5.gridx = 5;
		gbc_btn5.gridy = 0;
		this.panel_1.add(this.btn5, gbc_btn5);
		
		this.btn6 = new Button("Domenica") {
			@Override
			public void click() {
				setDaySelected(6);
				confirm();
			}
		};
		GridBagConstraints gbc_btn6 = new GridBagConstraints();
		gbc_btn6.fill = GridBagConstraints.BOTH;
		gbc_btn6.gridx = 6;
		gbc_btn6.gridy = 0;
		this.panel_1.add(this.btn6, gbc_btn6);
	}
	
	public abstract void showSubstitutionWindow();
	public abstract void checkSubstitutions();

	public void notifySubstitution(boolean blink) {
		if(blink) {
			subBtn.setIcon("btnAnim.gif");
		} else {
			subBtn.setIcon("null.png");
		}
	}
	
	public ArrayList<Line> getLineList() {
		return lineList;
	}

	public LineList getTabSelectionList() {
		return tabSelectionList;
	}

	public void setTabSelectionList(LineList tabSelectionList) {
		this.tabSelectionList = tabSelectionList;
	}

	public void setLineList(ArrayList<Line> lineList) {
		this.lineList = lineList;
	}
	
	public abstract void confirm();



	public int getDaySelected() {
		return daySelected;
	}



	public void setDaySelected(int daySelected) {
		this.daySelected = daySelected;
	}



	public String getLineSelected() {
		return lineSelected;
	}



	public void setLineSelected(String lineSelected) {
		this.lineSelected = lineSelected;
	}
}
