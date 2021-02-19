package busnet.features.shiftManagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import busnet.Application;
import busnet.entity.Employee;
import busnet.guiElements.Button;
import busnet.guiElements.TextField;
import busnet.guiElements.Window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public abstract class ManageScheduleWindow extends Window {
	
	private ArrayList<Employee> empList;
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField searchBar;
	private Button editScheduleBtn;
	private Button editScheduleTempButton;
	private JPanel panel_1;
	private Button addOvertimeBtn;
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public ManageScheduleWindow(ArrayList<Employee> employeeList) {
		super("Gestione turni","Da questa finestra è possibile gestire i turni degli impiegati. Puoi gestire dei turni di default, che saranno caricati per i vari giorni della settimana, ogni settimana, oppure creare dei turni temporanei"
				+ "che saranno caricati solo per un determinato giorno. Tutti gli impiegati verranno informati dei cambiamenti ai propri turni al salvataggio delle modifiche via email","shift120.png");
		setEmployeeList(employeeList);
		init();
		loadTable();
	}
	
	private void init() {
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowHeights = new int[]{173, 0, 44};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{15, 1214, 15, 0};
		gbl_panel.rowHeights = new int[]{15, 40, 16, 479, 15, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		this.panel.setLayout(gbl_panel);
		
		searchBar = new JTextField();
		searchBar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				searchEmployee();
			}
		});
		searchBar.setFont(Application.plain);
		GridBagConstraints gbc_searchBar = new GridBagConstraints();
		gbc_searchBar.fill = GridBagConstraints.BOTH;
		gbc_searchBar.insets = new Insets(0, 0, 5, 5);
		gbc_searchBar.gridx = 1;
		gbc_searchBar.gridy = 1;
		panel.add(searchBar, gbc_searchBar);
		searchBar.setColumns(10);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Cognome", "Codice Fiscale", "Ruolo"
			}
		){
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		});
		scrollPane.setViewportView(table);
		table.setFont(Application.bold);
		table.setRowHeight(30);
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		getContentPane().add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.panel_1.setLayout(gbl_panel_1);
		
		editScheduleBtn = new Button("Modifica turno di default") {
			@Override
			public void click() {
				clickUpdateDefaultSchedule();
			}
		};
		GridBagConstraints gbc_editScheduleBtn = new GridBagConstraints();
		gbc_editScheduleBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_editScheduleBtn.insets = new Insets(0, 0, 0, 0);
		gbc_editScheduleBtn.gridx = 0;
		gbc_editScheduleBtn.gridy = 0;
		this.panel_1.add(this.editScheduleBtn, gbc_editScheduleBtn);
		editScheduleBtn.setIcon("edit35.png");
		
		this.editScheduleTempButton = new Button("Modifica turno temporaneo") {
			@Override
			public void click() {
				clickManageTemporarySchedule();
			}
		};
		GridBagConstraints gbc_editScheduleTempButton = new GridBagConstraints();
		gbc_editScheduleTempButton.insets = new Insets(0, 0, 0, 0);
		gbc_editScheduleTempButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_editScheduleTempButton.gridx = 1;
		gbc_editScheduleTempButton.gridy = 0;
		this.panel_1.add(this.editScheduleTempButton, gbc_editScheduleTempButton);
		this.editScheduleTempButton.setIcon("edit35.png");
		
		this.addOvertimeBtn = new Button("Aggiungi ore straordinarie") {
			@Override
			public void click() {
				addOvertimeClickBtn();
			}
		};
		this.addOvertimeBtn.setIcon("add35.png");
		GridBagConstraints gbc_addOvertimeBtn = new GridBagConstraints();
		gbc_addOvertimeBtn.fill = GridBagConstraints.BOTH;
		gbc_addOvertimeBtn.gridx = 2;
		gbc_addOvertimeBtn.gridy = 0;
		this.panel_1.add(this.addOvertimeBtn, gbc_addOvertimeBtn);
		
	}
	
	public JTextField getSearchBar() {
		return searchBar;
	}

	public void setSearchBar(JTextField searchBar) {
		this.searchBar = searchBar;
	}

	public void loadTable() {
		clearTable();
		for(int i = 0; i<empList.size();i++) {
			Object[] row = {empList.get(i).getName(), empList.get(i).getSurname(), empList.get(i).getCf(),empList.get(i).getRole()};
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

	public void searchEmployee() {
		clearTable();
		for(int i=0;i<empList.size();i++) {
			if(empList.get(i).getName().toLowerCase().contains(getSearchBar().getText().toLowerCase()) 
					|| empList.get(i).getSurname().toLowerCase().contains(getSearchBar().getText().toLowerCase()) 
					|| empList.get(i).getCf().toLowerCase().contains(getSearchBar().getText().toLowerCase()) 
					|| empList.get(i).getRole().toLowerCase().contains(getSearchBar().getText().toLowerCase())) {
				Object[] row = {empList.get(i).getName(), empList.get(i).getSurname(), empList.get(i).getCf(),empList.get(i).getRole()};
				DefaultTableModel model = (DefaultTableModel) getTable().getModel();	
				model.addRow(row);
			}
		}
	
	}
	

	public ArrayList<Employee> getEmployeeList() {
		return empList;
	}

	public void setEmployeeList(ArrayList<Employee> employeeList) {
		this.empList = employeeList;
	}
	
	public abstract void clickUpdateDefaultSchedule();
	public abstract void clickManageTemporarySchedule();
	public abstract void addOvertimeClickBtn();
}
