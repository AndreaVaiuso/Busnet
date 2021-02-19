package busnet.guiElements;

import java.util.ArrayList;

import javax.swing.JPanel;

import busnet.Application;
import busnet.entity.Line;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public abstract class LineList extends JPanel {
	
	private ArrayList<Line> lineList;
	private String lineIdSelected;
	private JPanel panel;

	public LineList(ArrayList<Line> lineList) {
		
		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.panel = new JPanel();
		this.panel.setBackground(Application.defColor);
		this.panel.setLayout(new GridLayout(0, 5, 0, 0));
		add(panel);
		setLineList(lineList);
		loadLineList();
	}
	
	private void loadLineList() {
		clearTable();
		ArrayList<Button> buttons = new ArrayList<Button>();
		try {
			for(int i=0;i<lineList.size();i++) {
				final int x = i;
				Button butt = new Button(lineList.get(x).getLineId()) {
					@Override
					public void click() {
						setLineIdSelected(lineList.get(x).getLineId());
						for(int i=0;i<lineList.size();i++) {
							buttons.get(i).setPressed(false);
							addButtons(buttons);
						}
						this.setPressed(true);
					}
				};
				butt.setIcon("bus45.png");
				buttons.add(butt);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			JLabel lab = new Label("Lista linee vuota");
			lab.setForeground(Color.WHITE);
			panel.add(lab);
		}
//		if(lineList.isEmpty()) {
//			JLabel lab = new Label("Lista linee vuota");
//			lab.setForeground(Color.WHITE);
//			panel.add(lab);
//		}
		try {
			addButtons(buttons);
			panel.revalidate();
			panel.repaint();
		} catch (NullPointerException e) {}
		
	}
	
	private void addButtons(ArrayList<Button> buttons) {
		panel.removeAll();
		for(int i=0;i<lineList.size();i++) {
			panel.add(buttons.get(i));
		}
	}

	public ArrayList<Line> getLineList() {
		return lineList;
	}

	public void setLineList(ArrayList<Line> lineList) {
		this.lineList = lineList;
		loadLineList();
	}
	
	public abstract void clickSelectBtn();

	public String getLineIdSelected() {
		if(!lineIdSelected.isEmpty()) {
			return lineIdSelected;
		} else throw new ArrayIndexOutOfBoundsException();
	}

	private void setLineIdSelected(String lineIdSelected) {
		this.lineIdSelected = lineIdSelected;
	}
	
	private void clearTable() {
		try {
			panel.removeAll();
			panel.revalidate();
			panel.repaint();
		} catch (NullPointerException e) {}
	}

}
