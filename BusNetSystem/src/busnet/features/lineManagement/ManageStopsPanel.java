package busnet.features.lineManagement;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import busnet.Application;
import busnet.entity.Stop;
import busnet.guiElements.Button;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public abstract class ManageStopsPanel extends JPanel {
	private Button addBtn;
	private Button delBtn;
	private JTable table;
	private JScrollPane scrollPane;
	private ArrayList<Stop> stopList;
	private JTextField searchBar;
	private JPanel panel;
	private JPanel panel_1;

	public ManageStopsPanel(ArrayList<Stop> stopList) {
		this.stopList = stopList;
		init();
		try {
			loadTable();
		} catch (NullPointerException e) {
			
		}
	}
	
	public ArrayList<Stop> getStopList() {
		return stopList;
	}

	public void setStopList(ArrayList<Stop> stopList) {
		this.stopList = stopList;
	}

	public void init() {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1255, 0};
		gridBagLayout.rowHeights = new int[]{769, 53, 0};
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
		gbl_panel.columnWidths = new int[]{16, 1084, 16, 0};
		gbl_panel.rowHeights = new int[]{16, 52, 16, 516, 16, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		this.panel.setLayout(gbl_panel);
		
		this.searchBar = new JTextField();
		GridBagConstraints gbc_searchBar = new GridBagConstraints();
		gbc_searchBar.fill = GridBagConstraints.BOTH;
		gbc_searchBar.insets = new Insets(0, 0, 0, 5);
		gbc_searchBar.gridx = 1;
		gbc_searchBar.gridy = 1;
		this.panel.add(this.searchBar, gbc_searchBar);
		this.searchBar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				searchStop();
			}
		});
		this.searchBar.setFont(Application.plain);
		this.searchBar.setColumns(10);
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		this.panel.add(this.scrollPane, gbc_scrollPane);
		this.scrollPane.setBackground(Color.WHITE);
		
		this.table = new JTable();
		this.table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Codice", "Nome"
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
		this.scrollPane.setViewportView(this.table);
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.addBtn = new Button("Aggiungi") {
			@Override
			public void click() {
				clickAddBtn();
			}
		};
		GridBagConstraints gbc_addBtn = new GridBagConstraints();
		gbc_addBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_addBtn.insets = new Insets(0, 0, 0, 0);
		gbc_addBtn.gridx = 0;
		gbc_addBtn.gridy = 0;
		this.panel_1.add(this.addBtn, gbc_addBtn);
		this.addBtn.setIcon("add35.png");
		
		this.delBtn = new Button("Rimuovi") {
			@Override
			public void click() {
				clickDelBtn();
			}
		};
		GridBagConstraints gbc_delBtn = new GridBagConstraints();
		gbc_delBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_delBtn.gridx = 1;
		gbc_delBtn.gridy = 0;
		this.panel_1.add(this.delBtn, gbc_delBtn);
		this.delBtn.setIcon("remove35.png");
	}
	
	public JTextField getSearchBar() {
		return searchBar;
	}

	public void setSearchBar(JTextField searchBar) {
		this.searchBar = searchBar;
	}

	public void loadTable() {
		clearTable();
		for(int i = 0; i<stopList.size();i++) {
			Object[] row = {stopList.get(i).getStopId(), stopList.get(i).getStopName()};
			DefaultTableModel model = (DefaultTableModel) getTable().getModel();	
			model.addRow(row);
		}
	}
	
	private void clearTable() {
		DefaultTableModel dm = (DefaultTableModel)getTable().getModel();
		while(dm.getRowCount() > 0)
		{
		    dm.removeRow(0);
		}
	}
	
	public void searchStop() {
		clearTable();
		for(int i=0;i<stopList.size();i++) {
			if(stopList.get(i).getStopId().toLowerCase().contains(getSearchBar().getText().toLowerCase()) 
					|| stopList.get(i).getStopName().toLowerCase().contains(getSearchBar().getText().toLowerCase()) ) {
				Object[] row = {stopList.get(i).getStopId(),stopList.get(i).getStopName()};
				DefaultTableModel model = (DefaultTableModel) getTable().getModel();	
				model.addRow(row);
			}
		}
	
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}


	
	public abstract void clickAddBtn();
	public abstract void clickDelBtn();
}
