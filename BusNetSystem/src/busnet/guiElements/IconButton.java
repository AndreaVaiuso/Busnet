package busnet.guiElements;

import javax.swing.JPanel;

import busnet.Application;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;


public abstract class IconButton extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel text;
	private JLabel lblIcon;
	private boolean active;
	private boolean selected;
	private String imageName;
	
	public IconButton(String text, String icon) {
		init();
		setIcon(icon);
		setText(text);
		setSelected(false);
	}
	
	public void init() {
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(active) {
					setBackground(Color.WHITE);
					text.setForeground(Color.BLACK);
					click();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(active) {
					setBackground(Application.defColor);
					text.setForeground(Color.WHITE);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.WHITE);
				text.setForeground(Color.BLACK);
				if(selected) {
					setBackground(Application.defColor.darker());
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(active) {
					setBackground(Application.defColor.darker());
					text.setForeground(Color.WHITE);
				}
			}
		});
		setFont(Application.plain);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 81, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 64, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		this.lblIcon = new JLabel("");
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
		gbc_lblIcon.gridx = 1;
		gbc_lblIcon.gridy = 1;
		add(this.lblIcon, gbc_lblIcon);
		
		text = new JLabel("",JLabel.LEFT);
		text.setForeground(Color.BLACK);
		text.setFont(Application.plain);
		GridBagConstraints gbc_text = new GridBagConstraints();
		gbc_text.insets = new Insets(0, 0, 5, 5);
		gbc_text.gridx = 1;
		gbc_text.gridy = 2;
		add(text, gbc_text);
	}
	
	public abstract void click();
	
	public void setIcon(String imageName) {
		this.imageName = imageName;
		lblIcon.setIcon(new ImageIcon(IconButton.class.getResource("/busnet/resources/" + imageName)));
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}
	
	public void setButtonFont(Font font) {
		text.setFont(font);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		if(this.active) {
			setForeground(Color.BLACK);
			setIcon(this.imageName);
		} else {
			setForeground(Color.GRAY);
			setIcon(this.imageName+".disabled.png");
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		if(selected) {
			setBackground(Application.defColor.darker());
		} else {
			setBackground(Color.WHITE);
		}
	}

}
