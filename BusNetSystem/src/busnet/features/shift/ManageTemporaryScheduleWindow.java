package busnet.features.shift;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import busnet.Application;
import busnet.Window;
import busnet.entity.ShiftTemporaryList;
import busnet.guiElements.Button;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Insets;

public abstract class ManageTemporaryScheduleWindow extends Window {
	private ArrayList<ShiftTemporaryList> stList;
	private JPanel panel;
	private JTable table;
	private JScrollPane scrollPane;
	private Button addBtn;
	private Button delBtn;
	private Button updateBtn;
	private JPanel panel_1;
	

	public ManageTemporaryScheduleWindow(ArrayList<ShiftTemporaryList> stList) {
		super("Gestione turni temporanei","Da questa pagina è possibile gestire i turni temporanei. Essi saranno attivi solo per "
				+ "una data specifica, e potranno permettere l'aggiunta di turni straordinari (che superano il limite di ore standard)."
				+ " Ogni modifica verrà comunicata via email al dipendente","shiftTemporary120.png");
		setStList(stList);
		init();
		loadTable();
	}
	
	public void init() {
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowHeights = new int[]{174, 52, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		
		this.panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		getContentPane().add(this.panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		this.panel_1.setLayout(gbl_panel_1);
		
		this.addBtn = new Button("Aggiungi") {
			@Override
			public void click() {
				addTemporaryShift();
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
						rmvTemporaryShift();
					}
				};
				GridBagConstraints gbc_delBtn = new GridBagConstraints();
				gbc_delBtn.fill = GridBagConstraints.HORIZONTAL;
				gbc_delBtn.insets = new Insets(0, 0, 0, 0);
				gbc_delBtn.gridx = 1;
				gbc_delBtn.gridy = 0;
				this.panel_1.add(this.delBtn, gbc_delBtn);
				this.delBtn.setIcon("remove35.png");
				
				this.updateBtn = new Button("Visualizza"){
					@Override
					public void click() {
						showTemporaryShift();
					}
				};
				GridBagConstraints gbc_updateBtn = new GridBagConstraints();
				gbc_updateBtn.fill = GridBagConstraints.HORIZONTAL;
				gbc_updateBtn.gridx = 2;
				gbc_updateBtn.gridy = 0;
				this.panel_1.add(this.updateBtn, gbc_updateBtn);
				this.updateBtn.setIcon("show35.png");
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(this.panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{15, 203, 0, 15, 0};
		gbl_panel.rowHeights = new int[]{16, 180, 14, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		this.panel.setLayout(gbl_panel);
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		this.panel.add(this.scrollPane, gbc_scrollPane);
		
		this.table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Data"
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
		setBounds(300,300,829,892);
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void loadTable() {
		clearTable();
		for(int i = 0; i<stList.size();i++) {
			Object[] row = {stList.get(i).getDate()};
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(row);
		}
	}
	
	private void clearTable(){
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		while(dm.getRowCount() > 0)
		{
		    dm.removeRow(0);
		}
	}


	public ArrayList<ShiftTemporaryList> getStList() {
		return stList;
	}


	public void setStList(ArrayList<ShiftTemporaryList> stList) {
		this.stList = stList;
	}
	
	public abstract void addTemporaryShift();
	
	public abstract void rmvTemporaryShift();
	
	public abstract void showTemporaryShift();
}
