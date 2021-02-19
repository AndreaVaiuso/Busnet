package busnet.guiElements;

import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import busnet.Application;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;

public class RideTab extends JPanel {
	private JLabel label;
	private JTextField textField;
	private MouseAdapter add;
	private MouseAdapter del;
	private boolean status;
	private int i;
	private int j;

	public RideTab(String content, int i, int j) {
		setBackground(Color.WHITE);
		setI(i);
		setJ(j);
		setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		
		if(content.equals("plate")) {
			this.label = new JLabel();
			this.label.setFont(Application.plateSmall);
			add(this.label, gbc_lblNewLabel);
		} if(content.equals("shift")) {
			this.textField = new JTextField();
			this.textField.setFont(Application.plain);
			add(this.textField, gbc_lblNewLabel);
			this.textField.setColumns(10);
		} if(content.equals("schedule")) {
			add = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					add();
				}
			};
			del = new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					del();
				}
			};
			this.label = new JLabel();
			this.label.setFont(Application.plainSmall);
			this.label.setForeground(Color.WHITE);
			this.label.setOpaque(true);
			add(this.label, gbc_lblNewLabel);
		}

	}
	
	public void setPlate(String plate) {
		label.setText(plate);
	}
	public int getTime() {
		return Integer.parseInt(textField.getText());
	}
	public void setTime(int i) {
		textField.setText(i+"");
	}
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status, String cf) {
		setStatus(status);
		label.setText(cf);
	}
	
	public void setStatus(boolean status) {
		this.status = status;
		try {
			label.removeMouseListener(add);
			label.removeMouseListener(del);
		} catch (NullPointerException e) {
			
		}
		if(status) {
			label.setBackground(Application.defColor);
			label.setIcon(new ImageIcon(RideTab.class.getResource("/busnet/resources/remove35.png")));
			label.addMouseListener(del);
		} else {
			label.setBackground(Color.WHITE);
			label.setIcon(new ImageIcon(RideTab.class.getResource("/busnet/resources/add35.png")));
			label.addMouseListener(add);
		}
	}
	
	public void add() {
		
	}
	
	public void del() {
		
	}
	
	public void init() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	

}
