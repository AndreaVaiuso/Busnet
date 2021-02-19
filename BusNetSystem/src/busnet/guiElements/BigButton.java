package busnet.guiElements;

import javax.swing.JPanel;

import busnet.Application;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BigButton extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel textLabel;
	private JLabel iconLabel;

	public BigButton(String text, ImageIcon icon) {
		init();		
		iconLabel.setIcon(icon);
		textLabel.setText(text);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public BigButton(String littleText, String bigText) {
		init();
		iconLabel.setText(bigText);
		textLabel.setText(littleText);
	}
	
	public void init() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Application.defColor.darker());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(Application.defColor.darker().darker());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(Color.WHITE);
				click();
			}
		});
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 243, 0, 0};
		gridBagLayout.rowHeights = new int[]{10, 180, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		iconLabel = new JLabel("");
		iconLabel.setFont(Application.title);
		iconLabel.setForeground(Color.BLACK);
		GridBagConstraints gbc_iconLabel = new GridBagConstraints();
		gbc_iconLabel.insets = new Insets(0, 0, 5, 5);
		gbc_iconLabel.gridx = 1;
		gbc_iconLabel.gridy = 1;
		add(iconLabel, gbc_iconLabel);
		
		textLabel = new JLabel();
		textLabel.setForeground(Color.BLACK);
		textLabel.setFont(Application.plain);
		GridBagConstraints gbc_textLabel = new GridBagConstraints();
		gbc_textLabel.insets = new Insets(0, 0, 5, 5);
		gbc_textLabel.gridx = 1;
		gbc_textLabel.gridy = 2;
		add(textLabel, gbc_textLabel);
		
	}
	
	public void click() {
		
	}

}
