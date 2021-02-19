package busnet.features.employee;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.Application;
import busnet.Window;
import busnet.entity.Employee;
import busnet.guiElements.BigButton;
import busnet.guiElements.Button;
import busnet.guiElements.TextField;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ListSelectionModel;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public abstract class EmployeesWindow extends Window {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private Button btnAdd;
	private Button btnRemove;
	private Button btnView;
	private JTextField searchBar;
	private JLabel lblSearch;
	private JTable table;
	private JScrollPane scrollPane;
	private ArrayList<Employee> empList;
	private Button modifyBtn;
	private Button resetPasswordButton;
	private JPanel panel_1;


	/*CONSTRUCTOR
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * */
	public EmployeesWindow(ArrayList<Employee> empList) {
		super("Gestione impiegati","Da questo menu è possibile gestire gli impiegati della tua azienda","employee120.png");
		setEmpList(empList);
		createGUI();
		try {
			loadTable();
		} catch (NullPointerException e) {
			
		}

	}
	
	/*GETTER AND SETTER
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * */
	
	public ArrayList<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList<Employee> empList) {
		this.empList = empList;
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	private JTextField getSearchBar() {
		return searchBar;
	}
	
	/*ABSTRACT METHODS
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * */

	public abstract void clickAddEmployeeButton();
	
	public abstract void clickShowEmployeeButton();
	
	public abstract void clickuUpdateEmployeeButton();
	
	public abstract void clickDelEmployeeButton();
	
	public abstract void clickResetPasswordButton();
	
	/*METHODS
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * */
	
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
	


	
	/*GUI METHOD
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * */
	
	private void createGUI() {
		
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowHeights = new int[]{134, 0, 02};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{23, 1419, 15, 0};
		gbl_panel.rowHeights = new int[]{15, 63, 15, 612, 16, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(EmployeesWindow.class.getResource("/busnet/resources/show.png")));
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.EAST;
		gbc_lblSearch.fill = GridBagConstraints.VERTICAL;
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.gridx = 1;
		gbc_lblSearch.gridy = 1;
		panel.add(lblSearch, gbc_lblSearch);
		
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
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2) {
		            clickShowEmployeeButton();
		        }
		    }
		});
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		getContentPane().add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		this.panel_1.setLayout(gbl_panel_1);
		
		modifyBtn = new Button("Modifica") {
			public void click() {
				clickuUpdateEmployeeButton();
			}
		};
		GridBagConstraints gbc_modifyBtn = new GridBagConstraints();
		gbc_modifyBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_modifyBtn.insets = new Insets(0, 0, 0, 0);
		gbc_modifyBtn.gridx = 0;
		gbc_modifyBtn.gridy = 0;
		this.panel_1.add(this.modifyBtn, gbc_modifyBtn);
		modifyBtn.setIcon("edit35.png");
		GridBagLayout gbl_modifyBtn = (GridBagLayout) modifyBtn.getLayout();
		gbl_modifyBtn.rowWeights = new double[]{1.0, 0.0, 1.0};
		gbl_modifyBtn.rowHeights = new int[]{0, 64, 0};
		gbl_modifyBtn.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_modifyBtn.columnWidths = new int[]{0, 6, 81, 0};
		
		btnAdd = new Button("Aggiungi"){
			@Override
			public void click() {
				clickAddEmployeeButton();
			}
		};
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 0, 0);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 0;
		this.panel_1.add(this.btnAdd, gbc_btnAdd);
		btnAdd.setIcon("add35.png");
		
		btnRemove = new Button("Rimuovi"){
			@Override
			public void click() {
				clickDelEmployeeButton();
			}
		};
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemove.insets = new Insets(0, 0, 0, 0);
		gbc_btnRemove.gridx = 2;
		gbc_btnRemove.gridy = 0;
		this.panel_1.add(this.btnRemove, gbc_btnRemove);
		btnRemove.setIcon("remove35.png");
		
		btnView = new Button("Visualizza") {
			@Override
			public void click() {
				clickShowEmployeeButton();
			}
		};
		GridBagConstraints gbc_btnView = new GridBagConstraints();
		gbc_btnView.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnView.insets = new Insets(0, 0, 0, 0);
		gbc_btnView.gridx = 3;
		gbc_btnView.gridy = 0;
		this.panel_1.add(this.btnView, gbc_btnView);
		btnView.setIcon("show35.png");
		
		this.resetPasswordButton = new Button("Reset Password") {
			public void click() {
				clickResetPasswordButton();
			}
		};
		GridBagConstraints gbc_resetPasswordButton = new GridBagConstraints();
		gbc_resetPasswordButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_resetPasswordButton.gridx = 4;
		gbc_resetPasswordButton.gridy = 0;
		this.panel_1.add(this.resetPasswordButton, gbc_resetPasswordButton);
		GridBagLayout gbl_resetPasswordButton = (GridBagLayout) this.resetPasswordButton.getLayout();
		gbl_resetPasswordButton.rowWeights = new double[]{1.0, 0.0, 1.0};
		gbl_resetPasswordButton.rowHeights = new int[]{0, 64, 0};
		gbl_resetPasswordButton.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0};
		gbl_resetPasswordButton.columnWidths = new int[]{0, 6, 81, 0};
		this.resetPasswordButton.setIcon("reset35.png");
		setBounds(100,100,1500,1000);
	}

}
