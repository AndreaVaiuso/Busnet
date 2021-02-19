package busnet.features.ridesManagement;

import java.awt.EventQueue;

import javax.swing.JDialog;

import busnet.entity.Employee;
import busnet.guiElements.Button;
import busnet.guiElements.Label;
import busnet.guiElements.Window;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Insets;

public abstract class AddSubstitutionWindow extends Window {
	private JPanel panel;
	private JComboBox<Employee> comboBox;
	private JLabel lblSelezionaImpiegatoDisponibile;
	private ArrayList<Employee> empList;
	private Button panel_1;


	public AddSubstitutionWindow(ArrayList<Employee> empList) {
		super("Aggiungi sostituzione","Seleziona un impiegato da sostituire","shift120.png");
		setEmpList(empList);
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		gridBagLayout.columnWeights = new double[]{1.0};
		setResizable(false);
		
		setBounds(400, 400, 615, 446);
		
		this.panel = new JPanel();
		this.panel.setBackground(Color.WHITE);
		this.panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(this.panel, gbc_panel);
		
		this.comboBox = new JComboBox<>();
		this.comboBox.setBounds(85, 75, 438, 51);
		this.panel.add(this.comboBox);
		
		this.lblSelezionaImpiegatoDisponibile = new Label("Seleziona impiegato disponibile");
		this.lblSelezionaImpiegatoDisponibile.setBounds(85, 36, 438, 42);
		this.panel.add(this.lblSelezionaImpiegatoDisponibile);
		
		this.panel_1 = new Button("Scegli") {
			@Override
			public void click() {
				chose();
			}
		};
		this.panel_1.setBounds(0, 211, 609, 51);
		this.panel.add(this.panel_1);
		
		for(int i=0;i<empList.size();i++) {
			comboBox.addItem(empList.get(i));
		}
	}
	
	public abstract void chose();


	public JComboBox<Employee> getComboBox() {
		return comboBox;
	}


	public void setComboBox(JComboBox<Employee> comboBox) {
		this.comboBox = comboBox;
	}


	public ArrayList<Employee> getEmpList() {
		return empList;
	}


	public void setEmpList(ArrayList<Employee> empList) {
		this.empList = empList;
	}
}
