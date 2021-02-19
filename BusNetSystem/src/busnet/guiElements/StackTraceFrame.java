package busnet.guiElements;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import busnet.Application;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.awt.Color;

public class StackTraceFrame extends JDialog {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextPane textPane;

	public StackTraceFrame(Exception e) {
		super(Application.frame,"Window",true);
		setTitle("Stack trace");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 963, 1127);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		textPane.setBackground(Color.BLACK);
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Consolas", Font.PLAIN, 18));
		scrollPane.setViewportView(textPane);
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String sStackTrace = sw.toString();
		
		textPane.setText(sStackTrace);
		
		File file = new File("");
		textPane.setText(textPane.getText()+"\n\n\n" + file.getAbsolutePath());
	}

}
