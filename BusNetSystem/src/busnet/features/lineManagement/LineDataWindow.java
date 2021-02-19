package busnet.features.lineManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.Application;
import busnet.dbmsManagement.DBInterface;
import busnet.entity.Line;
import busnet.entity.Stop;
import busnet.guiElements.Button;
import busnet.guiElements.Label;
import busnet.guiElements.TextField;
import busnet.guiElements.Window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public abstract class LineDataWindow extends Window {
	private JPanel panel;
	private JLabel lblStopId;
	private JTextField lineIdField;
	private Button okBtn;
	private Button cancelBtn;
	private JComboBox<Stop> comboBox;
	private JButton button;
	private JButton button_1;
	private JButton btnUp;
	private JButton btnDown;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField timeField;
	private JLabel lblTempoTotaleDi;
	private JLabel label;
	private JLabel lblInserireIlCapolinea;

	public LineDataWindow() {
		super("Nuova linea","Qui puoi creare una nuova linea. Per ogni linea creata potrai aggiungere delle fermate","lineIcon120.png");
		init();
		loadStops();
		
	}
	
	public LineDataWindow(Line line) {
		super("Linea " + line.getLineId(), "Da qui puoi modificare la tua linea","lineIcon120.png");
		init();
		loadStops();
		this.addStopsFromLine(line.getStopList(), line.getTimeList());
		lineIdField.setText(line.getLineId());
		lblTempoTotaleDi.setText("Tempo totale di percorrenza: "+line.getHours()+":"+line.getMinutes()+"");
	}
	
	public void init() {
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		setBounds(200,200,822,831);
		setResizable(false);		
		this.panel = new JPanel();
		panel.setBackground(Color.WHITE);
		this.panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(this.panel, gbc_panel);
		
		this.lblStopId = new Label("Line ID (Codice univoco linea)");
		this.lblStopId.setBounds(15, 16, 717, 34);
		this.panel.add(this.lblStopId);
		
		this.lineIdField = new TextField();
		this.lineIdField.setBounds(15, 51, 790, 56);
		this.panel.add(this.lineIdField);
		this.lineIdField.setColumns(10);
		
		this.cancelBtn = new Button("Annulla") {
			@Override
			public void click() {
				dispose();
			}
		};
		
		this.okBtn = new Button("Fatto") {
			@Override
			public void click() {
				confirm();
			}
		};
		this.okBtn.setBounds(404, 591, 418, 63);
		this.panel.add(this.okBtn);
		
		this.cancelBtn.setBounds(0, 591, 407, 63);
		this.panel.add(this.cancelBtn);
		
		this.comboBox = new JComboBox<>();
		this.comboBox.setBounds(15, 173, 296, 45);
		this.panel.add(this.comboBox);
		
		this.button = new JButton("+");
		this.button.setFont(new Font("Tahoma", Font.BOLD, 28));
		this.button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					addStop((Stop)comboBox.getSelectedItem(),Integer.parseInt(timeField.getText()));
				} catch (NumberFormatException e) {
					
				}
			}
		});
		this.button.setBounds(326, 173, 55, 45);
		this.panel.add(this.button);
		
		this.button_1 = new JButton("-");
		this.button_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		this.button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				removeStop();
			}
		});
		this.button_1.setBounds(396, 173, 55, 45);
		this.panel.add(this.button_1);
		
		this.btnUp = new JButton("\uD83E\uDC51");
		this.btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				move(-1);
			}
		});
		this.btnUp.setBounds(466, 173, 55, 45);
		this.panel.add(this.btnUp);
		
		this.btnDown = new JButton("\uD83E\uDC53");
		this.btnDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				move(1);
			}
		});
		this.btnDown.setBounds(536, 173, 55, 45);
		this.panel.add(this.btnDown);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(15, 267, 790, 301);
		this.panel.add(this.scrollPane);
		
		this.table = new JTable();
		this.table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID fermata", "Nome fermata", "tempo di percorrenza (minuti)"
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
		
		this.timeField = new TextField();
		this.timeField.setBounds(606, 173, 199, 45);
		this.panel.add(this.timeField);
		this.timeField.setColumns(10);
		
		lblTempoTotaleDi = new Label("");
		lblTempoTotaleDi.setBounds(15, 123, 756, 34);
		panel.add(lblTempoTotaleDi);
		
		label = new Label("Tempo di percorrenza:");
		label.setBounds(606, 123, 199, 34);
		panel.add(label);
		
		this.lblInserireIlCapolinea = new Label("Inserire come tempo per la prima fermata il tempo di arrivo al capolinea");
		this.lblInserireIlCapolinea.setBounds(15, 220, 771, 45);
		this.panel.add(this.lblInserireIlCapolinea);
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public void loadStops() {
		ArrayList<Stop> stopList = null;
		try {
			stopList = DBInterface.rtrvOrderedStopList();
			for(int i=0;i<stopList.size();i++) {
				comboBox.addItem(stopList.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object[] row = {"Pause", "Pausa", Application.pause};
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(row);	
	}
	
	private void addStop(Stop stop, int time) {
		try {
			if(time>0) {
				Object[] row = {stop.getStopId(), stop.getStopName(), time};
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(row);
				timeField.setText("");
			}
			else throw new NumberFormatException();
		} catch (NumberFormatException e) {
			
		}
	}
	
	private void addStopsFromLine(List<Stop> stops, List<Integer> times) {
		for(int i=0; i<stops.size();i++) {
			addStop(stops.get(i), times.get(i));
		}
	}
	
	private void removeStop() {
		try {
			if(table.getSelectedRow()!=0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.removeRow(table.getSelectedRow());
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}
	}
	private void move(int num) {
		try {
			if(table.getSelectedRow()!=0) {
				if(!((table.getSelectedRow()-1)==0 && num==-1)) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.moveRow(table.getSelectedRow(),table.getSelectedRow(),(table.getSelectedRow()+num));
					table.setRowSelectionInterval(table.getSelectedRow()+num, table.getSelectedRow()+num);
				}
			}
		} catch (Exception e) {
			
		}
	}
	
	public JTextField getLineIdField() {
		return lineIdField;
	}

	public abstract void confirm();
}
