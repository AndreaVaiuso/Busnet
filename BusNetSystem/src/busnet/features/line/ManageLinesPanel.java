package busnet.features.line;

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

	public ManageLinesPanel(ArrayList<Line> lineList) {
		setLineList(lineList);
		setBackground(Color.WHITE);
		init();
	}
	
	public void init() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 206, 200, 206, 120, 15, 0};
		gridBagLayout.rowHeights = new int[]{16, 64, 16, 408, 16, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.addLineBtn = new Button("Aggiungi linea") {
			@Override
			public void click() {
				addLine();
			}
		};
		addLineBtn.setIcon("add35.png");
		GridBagConstraints gbc_addLineBtn = new GridBagConstraints();
		gbc_addLineBtn.fill = GridBagConstraints.BOTH;
		gbc_addLineBtn.insets = new Insets(0, 0, 5, 5);
		gbc_addLineBtn.gridx = 1;
		gbc_addLineBtn.gridy = 1;
		add(this.addLineBtn, gbc_addLineBtn);
		
		this.removeLineBtn = new Button("Rimuovi linea") {
			@Override
			public void click() {
				removeLine();
			}
			
		};
		this.removeLineBtn.setIcon("remove35.png");
		GridBagConstraints gbc_removeLineBtn = new GridBagConstraints();
		gbc_removeLineBtn.fill = GridBagConstraints.BOTH;
		gbc_removeLineBtn.insets = new Insets(0, 0, 5, 5);
		gbc_removeLineBtn.gridx = 2;
		gbc_removeLineBtn.gridy = 1;
		add(this.removeLineBtn, gbc_removeLineBtn);
		
		this.modifyButton = new Button("Modifica linea") {
			public void click() {
				modifyLine();
			}
		};
		GridBagLayout gbl_modifyButton = (GridBagLayout) this.modifyButton.getLayout();
		gbl_modifyButton.rowWeights = new double[]{1.0, 0.0, 1.0};
		gbl_modifyButton.rowHeights = new int[]{0, 64, 0};
		gbl_modifyButton.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_modifyButton.columnWidths = new int[]{0, 6, 81, 0};
		this.modifyButton.setIcon("edit35.png");
		GridBagConstraints gbc_modifyButton = new GridBagConstraints();
		gbc_modifyButton.fill = GridBagConstraints.BOTH;
		gbc_modifyButton.insets = new Insets(0, 0, 5, 5);
		gbc_modifyButton.gridx = 3;
		gbc_modifyButton.gridy = 1;
		add(this.modifyButton, gbc_modifyButton);
		
		this.label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 4;
		gbc_label.gridy = 1;
		add(this.label, gbc_label);
		
		this.lList = new LineList(lineList) {
			@Override
			public void clickSelectBtn() {
				addLine();
			}

		};
		GridBagConstraints gbc_lList = new GridBagConstraints();
		gbc_lList.insets = new Insets(0, 0, 5, 5);
		gbc_lList.fill = GridBagConstraints.BOTH;
		gbc_lList.gridwidth = 4;
		gbc_lList.gridx = 1;
		gbc_lList.gridy = 3;
		add(this.lList, gbc_lList);
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
