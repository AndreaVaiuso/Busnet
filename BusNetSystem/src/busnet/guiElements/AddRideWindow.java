package busnet.guiElements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.Application;
import busnet.entity.Bus;
import busnet.entity.Employee;

public abstract class AddRideWindow extends Window {
	
	private JPanel panel;
	private JComboBox<Employee> comboBox;
	private Button btnAdd;
	private String driverSelected;
	
	public AddRideWindow(ArrayList<Employee> availableEmployeeList) {
		super("Aggiungi nuova corsa","Seleziona un autista che guiderà quella corsa","shift120.png");
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
		for(int i=0;i<availableEmployeeList.size();i++) {
			comboBox.addItem(availableEmployeeList.get(i));
		}
		this.comboBox.setBounds(99, 29, 467, 64);
		this.panel.add(this.comboBox);
		
		this.btnAdd = new Button("Fatto") {
			@Override
			public void click() {
				try {
					dispose();
					driverSelected = (((Employee)comboBox.getSelectedItem()).getCf());
					clickAddDriverBtn();
				} catch (NullPointerException e) {}
			}
		};
		this.btnAdd.setBounds(0, 130, 704, 52);
		this.panel.add(this.btnAdd);
	}
	
	public JComboBox<Employee> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<Employee> comboBox) {
		this.comboBox = comboBox;
	}

	public abstract void clickAddDriverBtn();

}
