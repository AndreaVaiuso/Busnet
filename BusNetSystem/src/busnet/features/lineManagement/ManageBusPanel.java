package busnet.features.lineManagement;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.MysqlDataTruncation;

import busnet.Application;
import busnet.dbmsManagement.DBInterface;
import busnet.entity.Bus;
import busnet.entity.Stop;
import busnet.guiElements.Button;
import busnet.guiElements.CheckBox;
import busnet.guiElements.PlateHolder;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public abstract class ManageBusPanel extends JPanel {
	private Button addBtn;
	private Button delBtn;
	private JTable table;
	private JScrollPane scrollPane;
	private ArrayList<Bus> busList;
	private JTextField searchBar;
	private JPanel panel;
	private JLabel label;
	private JLabel lblNome;
	private JLabel lblTipo;
	private JTextField modelField;
	private JTextField typeField;
	private CheckBox chkBox;
	private PlateHolder panel_1;
	private JPanel panel_2;
	private JPanel panel_3;

	public ManageBusPanel(ArrayList<Bus> busList) {
		this.busList = busList;
		init();
		try {
			loadTable();
		} catch (NullPointerException e) {
			
		}
	}
	
	public ArrayList<Bus> getBusList() {
		return busList;
	}

	public void setBusList(ArrayList<Bus> busList) {
		this.busList = busList;
	}

	public void init() {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1117, 0};
		gridBagLayout.rowHeights = new int[]{770, 64, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.panel_2 = new JPanel();
		this.panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		add(this.panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{15, 574, 16, 0};
		gbl_panel_2.rowHeights = new int[]{15, 48, 14, 294, 306, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		this.panel_2.setLayout(gbl_panel_2);
		
		this.searchBar = new JTextField();
		GridBagConstraints gbc_searchBar = new GridBagConstraints();
		gbc_searchBar.fill = GridBagConstraints.BOTH;
		gbc_searchBar.insets = new Insets(0, 0, 0, 5);
		gbc_searchBar.gridx = 1;
		gbc_searchBar.gridy = 1;
		this.panel_2.add(this.searchBar, gbc_searchBar);
		this.searchBar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				searchBus();
			}
		});
		this.searchBar.setFont(Application.plain);
		this.searchBar.setColumns(10);
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		this.panel_2.add(this.scrollPane, gbc_scrollPane);
		this.scrollPane.setBackground(Color.WHITE);
		
		this.table = new JTable();
		this.table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Targa", "Tipo", "Attivo"
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
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 1) {
		            loadBusInfo();
		        }
		    }
		});
		this.scrollPane.setViewportView(this.table);
		
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		this.panel_2.add(this.panel, gbc_panel);
		panel.setVisible(false);
		panel.setBackground(Color.WHITE);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{200, 47, 280, 32, 285, 14, 0};
		gbl_panel.rowHeights = new int[]{19, 34, 39, 21, 39, 39, 57, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/bus.empty.png")));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTHWEST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridheight = 5;
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		lblNome = new JLabel("Modello:");
		lblNome.setFont(Application.bold);
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNome.insets = new Insets(0, 0, 0, 5);
		gbc_lblNome.gridx = 2;
		gbc_lblNome.gridy = 1;
		panel.add(lblNome, gbc_lblNome);
		
		modelField = new JTextField();
		modelField.setEditable(false);
		modelField.setFont(Application.plain);
		GridBagConstraints gbc_modelField = new GridBagConstraints();
		gbc_modelField.fill = GridBagConstraints.BOTH;
		gbc_modelField.insets = new Insets(0, 0, 0, 5);
		gbc_modelField.gridwidth = 3;
		gbc_modelField.gridx = 2;
		gbc_modelField.gridy = 2;
		panel.add(modelField, gbc_modelField);
		modelField.setColumns(10);
		
		panel_1 = new PlateHolder("");
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 4;
		panel.add(panel_1, gbc_panel_1);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(Application.bold);
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.WEST;
		gbc_lblTipo.fill = GridBagConstraints.VERTICAL;
		gbc_lblTipo.insets = new Insets(0, 0, 0, 5);
		gbc_lblTipo.gridx = 4;
		gbc_lblTipo.gridy = 4;
		panel.add(lblTipo, gbc_lblTipo);
		
		typeField = new JTextField();
		typeField.setFont(Application.plain);
		typeField.setEditable(false);
		typeField.setColumns(10);
		GridBagConstraints gbc_typeField = new GridBagConstraints();
		gbc_typeField.fill = GridBagConstraints.BOTH;
		gbc_typeField.insets = new Insets(0, 0, 0, 5);
		gbc_typeField.gridx = 4;
		gbc_typeField.gridy = 5;
		panel.add(typeField, gbc_typeField);
		
		chkBox = new CheckBox("Stato") {
			@Override
			public void click() {
				try {
					DBInterface.changeBusStatus((String) getTable().getModel().getValueAt(getTable().getSelectedRow(),0), isEnabled());
					busList = DBInterface.rtrvBusList();
					loadTable();
				} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
					Application.showError("Non è possibile sostituire questo autobus in quanto non ci sono altri mezzi sostitutivi disponibili\nRimuovi il veicolo dalle corse in cui è istanziato e ripriva");
				} catch (MysqlDataTruncation e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		GridBagConstraints gbc_chkBox = new GridBagConstraints();
		gbc_chkBox.insets = new Insets(0, 0, 0, 0);
		gbc_chkBox.fill = GridBagConstraints.BOTH;
		gbc_chkBox.gridwidth = 3;
		gbc_chkBox.gridx = 2;
		gbc_chkBox.gridy = 6;
		panel.add(chkBox, gbc_chkBox);
		
		this.panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		add(this.panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		this.panel_3.setLayout(gbl_panel_3);
		
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
		this.panel_3.add(this.addBtn, gbc_addBtn);
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
		this.panel_3.add(this.delBtn, gbc_delBtn);
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
		for(int i = 0; i<busList.size();i++) {
			Object[] row = {busList.get(i).getBusID(), busList.get(i).getType(), busList.get(i).isActive()};
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
	
	public void searchBus() {
		clearTable();
		for(int i=0;i<busList.size();i++) {
			if(busList.get(i).getBusID().toLowerCase().contains(getSearchBar().getText().toLowerCase()) 
					|| busList.get(i).getType().toLowerCase().contains(getSearchBar().getText().toLowerCase()) ) {
				Object[] row = {busList.get(i).getBusID(),busList.get(i).getType(),busList.get(i).isActive()};
				DefaultTableModel model = (DefaultTableModel) getTable().getModel();	
				model.addRow(row);
			}
		}
	}
	
	public JTable getTable() {
		return table;
	}
	
	public void loadBusInfo() {
		
		try {
			Bus bus = DBInterface.rtrvBusData((String) getTable().getModel().getValueAt(getTable().getSelectedRow(), 0));
			panel.setVisible(true);
			panel_1.setText(bus.getBusID());
			typeField.setText(bus.getType());
			modelField.setText(bus.getModel());
			chkBox.setEnabled(bus.isActive());
			System.out.println(bus.getModel().toLowerCase().contains("iveco"));
			if(bus.getModel().toLowerCase().contains("autosan")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/autosan.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("bedford")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/bedford.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("busscar")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/busscar.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("daewoo")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/daewoo.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("iia")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/iia.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("ikarus")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/ikarus.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("iveco")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/iveco.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("karosa")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/karosa.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("man")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/man.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("mercedes")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/mercedes.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("navistar")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/navistar.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("otokar")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/otokar.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("renault")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/renault.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("scania")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/scania.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("temsa")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/temsa.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("vanhool")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/vanhool.jpg")));
			}
			if(bus.getModel().toLowerCase().contains("volvo")) {
				label.setIcon(new ImageIcon(ManageBusPanel.class.getResource("/busnet/resources/bus/volvo.jpg")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public abstract void clickAddBtn();
	public abstract void clickDelBtn();
}
