package busnet.features.ridesManagement;

import java.awt.EventQueue;

import javax.swing.JDialog;

import busnet.Application;
import busnet.entity.Employee;
import busnet.entity.Substitution;
import busnet.guiElements.Button;
import busnet.guiElements.Window;
import busnet.mailManagement.MailInterface;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Insets;

public abstract class SubstitutionWindow extends Window {
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollPane;
	private ArrayList<Employee> availableEmpList;
	private ArrayList<Substitution> subList;
	private JPanel panel_1;
	private Button btnRemove;
	private Button subBtn;

	public SubstitutionWindow(ArrayList<Substitution> subList) {
		super("Sostituzioni","a causa di uno o più turni temporanei per oggi, dovrai effettuare delle sostituzioni se vuoi rendere effettive le corse degli autobus","shift120.png");
		setSubList(subList);
		init();
		loadTable();
	}
	
	public void init() {
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowHeights = new int[]{135, 0, 44};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		setBounds(300, 300, 1085, 694);
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{15, 943, 15, 0};
		gbl_panel.rowHeights = new int[]{15, 394, 15, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		this.panel.setLayout(gbl_panel);
		
				this.scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 1;
				this.panel.add(this.scrollPane, gbc_scrollPane);
				
						this.table = new JTable();
						this.scrollPane.setViewportView(this.table);
						table.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
										"Autobus", "Linea", "Data", "Orario", "Autista assente", "Autista sostitutivo"
								}
								){
							private static final long serialVersionUID = 1L;

							@Override
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						});
						table.setFont(Application.bold);
						table.setRowHeight(30);
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		getContentPane().add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.subBtn = new Button("Effettua sostituzione") {
			@Override
			public void click() {
				doSubstitutionClickBtn();
			}
		};
		this.subBtn.setIcon("add35.png");
		GridBagConstraints gbc_subBtn = new GridBagConstraints();
		gbc_subBtn.insets = new Insets(0, 0, 0, 0);
		gbc_subBtn.fill = GridBagConstraints.BOTH;
		gbc_subBtn.gridx = 0;
		gbc_subBtn.gridy = 0;
		this.panel_1.add(this.subBtn, gbc_subBtn);
		
		this.btnRemove = new Button("Rimuovi sostituzione") {
			@Override
			public void click() {
				removeSubstitutionClickBtn();
			}
		};
		this.btnRemove.setIcon("remove35.png");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.fill = GridBagConstraints.BOTH;
		gbc_btnRemove.gridx = 1;
		gbc_btnRemove.gridy = 0;
		this.panel_1.add(this.btnRemove, gbc_btnRemove);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public abstract void doSubstitutionClickBtn();
	public abstract void removeSubstitutionClickBtn();

	public void loadTable() {
		clearTable();
		for(int i=0;i<subList.size(); i++) {
			Object[] row = {subList.get(i).getBusId(), subList.get(i).getLineId(), subList.get(i).getShiftDate(),MailInterface.periods[subList.get(i).getPeriod()],subList.get(i).getMissingDriver(), subList.get(i).getSubDriver()};
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(row);
		}
	}
	
	private void clearTable() {
		DefaultTableModel dm = (DefaultTableModel)table.getModel();
		while(dm.getRowCount() > 0)
		{
		    dm.removeRow(0);
		}
	}

	public ArrayList<Employee> getAvailableEmpList() {
		return availableEmpList;
	}

	public void setAvailableEmpList(ArrayList<Employee> availableEmpList) {
		this.availableEmpList = availableEmpList;
	}

	public ArrayList<Substitution> getSubList() {
		return subList;
	}

	public void setSubList(ArrayList<Substitution> subList) {
		this.subList = subList;
	}
}
