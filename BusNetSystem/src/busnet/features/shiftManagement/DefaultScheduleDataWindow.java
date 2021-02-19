package busnet.features.shiftManagement;

import javax.swing.JPanel;

import busnet.entity.ShiftDefaultList;
import busnet.guiElements.Button;
import busnet.guiElements.DefaultScheduleTable;
import busnet.guiElements.Label;
import busnet.guiElements.Window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;;

public abstract class DefaultScheduleDataWindow extends Window {
	
	private ShiftDefaultList sdList;
	private ShiftDefaultList oldsdList;
	private JPanel content;
	private DefaultScheduleTable shiftTable;
	private Button btnConfirm;
	private JLabel lblOreTotali;


	public DefaultScheduleDataWindow(ShiftDefaultList sdList) {
		super("Gestione turni " + sdList.getEmployeeId(),"Modificando i turni di default, il dipendente sarà informato della modifica definitiva del proprio turno","shift120.png");
		this.sdList = sdList;
		this.setOldsdList(sdList);
		init();
	}
	
	public void init() {
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		this.content = new JPanel();
		this.content.setBackground(Color.WHITE);
		GridBagConstraints gbc_content = new GridBagConstraints();
		gbc_content.insets = new Insets(0, 0, 5, 0);
		gbc_content.fill = GridBagConstraints.BOTH;
		gbc_content.gridx = 0;
		gbc_content.gridy = 1;
		getContentPane().add(this.content, gbc_content);
		GridBagLayout gbl_content = new GridBagLayout();
		gbl_content.columnWidths = new int[]{23, 985, 13, 0};
		gbl_content.rowHeights = new int[]{15, 52, 532, 13, 0};
		gbl_content.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_content.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		this.content.setLayout(gbl_content);
		
		this.shiftTable = new DefaultScheduleTable(sdList) {

			@Override
			public void updateTotalHours() {
				lblOreTotali.setText("Ore totali: " + shiftTable.getTotalHours());
			}
			
		};
		shiftTable.refreshShift();
		
		this.lblOreTotali = new Label("Ore totali: " + shiftTable.getTotalHours());
		GridBagConstraints gbc_lblOreTotali = new GridBagConstraints();
		gbc_lblOreTotali.insets = new Insets(0, 0, 5, 5);
		gbc_lblOreTotali.gridx = 1;
		gbc_lblOreTotali.gridy = 1;
		this.content.add(this.lblOreTotali, gbc_lblOreTotali);
		GridBagConstraints gbc_shiftTable = new GridBagConstraints();
		gbc_shiftTable.insets = new Insets(0, 0, 5, 5);
		gbc_shiftTable.fill = GridBagConstraints.BOTH;
		gbc_shiftTable.gridx = 1;
		gbc_shiftTable.gridy = 2;
		this.content.add(this.shiftTable, gbc_shiftTable);
		
		this.btnConfirm = new Button("Fatto") {
			@Override
			public void click() {
				confirm();
			}
		};
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.fill = GridBagConstraints.BOTH;
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = 2;
		getContentPane().add(this.btnConfirm, gbc_btnConfirm);
	}
	
	public DefaultScheduleTable getShiftTable() {
		return shiftTable;
	}

	public void setShiftTable(DefaultScheduleTable shiftTable) {
		this.shiftTable = shiftTable;
	}

	public ShiftDefaultList getSdList() {
		return sdList;
	}

	public void setSdList(ShiftDefaultList sdList) {
		this.sdList = sdList;
	}

	public abstract void confirm();

	public ShiftDefaultList getOldsdList() {
		return oldsdList;
	}

	public void setOldsdList(ShiftDefaultList oldsdList) {
		this.oldsdList = oldsdList;
	}
}
