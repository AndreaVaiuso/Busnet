package busnet.features.lineManagement;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import busnet.entity.Line;
import busnet.guiElements.Button;
import busnet.guiElements.Label;
import busnet.guiElements.LineList;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public abstract class ManageLinesPanel extends JPanel {
	private LineList lList;
	private Button addLineBtn;
	private Button removeLineBtn;
	private Button modifyButton;
	private JLabel label;
	private ArrayList<Line> lineList;
	private JPanel panel;
	private JPanel panel_1;

	public ManageLinesPanel(ArrayList<Line> lineList) {
		setLineList(lineList);
		setBackground(Color.WHITE);
		init();
	}
	
	public void init() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1110, 0};
		gridBagLayout.rowHeights = new int[]{688, 55, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{16, 1001, 16, 0};
		gbl_panel.rowHeights = new int[]{16, 512, 16, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		this.panel.setLayout(gbl_panel);
		
		this.lList = new LineList(lineList) {
			@Override
			public void clickSelectBtn() {
				addLine();
			}

		};
		GridBagConstraints gbc_lList = new GridBagConstraints();
		gbc_lList.insets = new Insets(0, 0, 5, 5);
		gbc_lList.fill = GridBagConstraints.BOTH;
		gbc_lList.gridx = 1;
		gbc_lList.gridy = 1;
		this.panel.add(this.lList, gbc_lList);
		
		this.label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.HORIZONTAL;
		gbc_label.anchor = GridBagConstraints.NORTH;
		gbc_label.insets = new Insets(0, 0, 0, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(this.label, gbc_label);
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.addLineBtn = new Button("Aggiungi linea") {
			@Override
			public void click() {
				addLine();
			}
		};
		GridBagConstraints gbc_addLineBtn = new GridBagConstraints();
		gbc_addLineBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_addLineBtn.insets = new Insets(0, 0, 0, 0);
		gbc_addLineBtn.gridx = 0;
		gbc_addLineBtn.gridy = 0;
		this.panel_1.add(this.addLineBtn, gbc_addLineBtn);
		addLineBtn.setIcon("add35.png");
		
		this.removeLineBtn = new Button("Rimuovi linea") {
			@Override
			public void click() {
				removeLine();
			}
			
		};
		GridBagConstraints gbc_removeLineBtn = new GridBagConstraints();
		gbc_removeLineBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeLineBtn.insets = new Insets(0, 0, 0, 0);
		gbc_removeLineBtn.gridx = 1;
		gbc_removeLineBtn.gridy = 0;
		this.panel_1.add(this.removeLineBtn, gbc_removeLineBtn);
		this.removeLineBtn.setIcon("remove35.png");
		
		this.modifyButton = new Button("Modifica linea") {
			public void click() {
				modifyLine();
			}
		};
		GridBagConstraints gbc_modifyButton = new GridBagConstraints();
		gbc_modifyButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_modifyButton.gridx = 2;
		gbc_modifyButton.gridy = 0;
		this.panel_1.add(this.modifyButton, gbc_modifyButton);
		GridBagLayout gbl_modifyButton = (GridBagLayout) this.modifyButton.getLayout();
		gbl_modifyButton.rowWeights = new double[]{1.0, 0.0, 1.0};
		gbl_modifyButton.rowHeights = new int[]{0, 64, 0};
		gbl_modifyButton.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_modifyButton.columnWidths = new int[]{0, 6, 81, 0};
		this.modifyButton.setIcon("edit35.png");
	}
	
	public LineList getLList() {
		return lList;
	}

	public void setLList(LineList lList) {
		this.lList = lList;
	}
	
	public void updateLineList() {
		getLList().setLineList(lineList);
	}
	
	public abstract void addLine();
	public abstract void modifyLine();
	public abstract void removeLine();

	public ArrayList<Line> getLineList() {
		return lineList;
	}

	public void setLineList(ArrayList<Line> lineList) {
		this.lineList = lineList;
	}
}
