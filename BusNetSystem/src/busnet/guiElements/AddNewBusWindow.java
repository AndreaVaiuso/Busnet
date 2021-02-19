package busnet.guiElements;

import java.awt.EventQueue;

import javax.swing.JDialog;

import busnet.Application;
import busnet.entity.Bus;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JComboBox;

public abstract class AddNewBusWindow extends Window {
	private JPanel panel;
	private JComboBox<Bus> comboBox;
	private Button btnAdd;
	private String busSelected;


	public AddNewBusWindow(ArrayList<Bus> availableBusList) {
		super("Scegli un autobus","Stai creando una nuova corsa per la linea selezionata, scegli un autobus","bus.png");
		setResizable(false);
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		setBounds(100, 100, 710, 366);
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		this.panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(this.panel, gbc_panel);
		
		this.comboBox = new JComboBox<>();
		this.comboBox.setFont(Application.plain);
		for(int i=0;i<availableBusList.size();i++) {
			comboBox.addItem(availableBusList.get(i));
		}
		this.comboBox.setBounds(99, 29, 467, 64);
		this.panel.add(this.comboBox);
		
		this.btnAdd = new Button("Fatto") {
			@Override
			public void click() {
				try {
					dispose();
					setBusSelected(((Bus)comboBox.getSelectedItem()).getBusID());
					clickAddBusBtn();
				} catch (NullPointerException e) {}
			}
		};
		this.btnAdd.setBounds(0, 130, 704, 52);
		this.panel.add(this.btnAdd);

	}
	
	public JComboBox<Bus> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<Bus> comboBox) {
		this.comboBox = comboBox;
	}

	public abstract void clickAddBusBtn();

	public String getBusSelected() {
		return busSelected;
	}

	public void setBusSelected(String busSelected) {
		this.busSelected = busSelected;
	}

}
