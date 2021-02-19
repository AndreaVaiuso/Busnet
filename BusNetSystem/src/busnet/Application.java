package busnet;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import busnet.entity.Employee;
import busnet.guiElements.StackTraceFrame;
import busnet.guiElements.WrapperPanel;
import busnet.login.LoginControl;

import java.awt.Toolkit;
import java.io.File;

public class Application {
	
	private static String font = "Yu Gothic UI Light";
	private static String font2 = "Yu Gothic UI";
	private static String font3 = "License Plate";
	
	public static JFrame frame;
	public static Font plain = new Font(font, Font.PLAIN, 20);
	public static Font plainSmall = new Font(font, Font.PLAIN, 15);
	public static Font bold = new Font(font, Font.BOLD, 25);
	public static Font title = new Font(font2, Font.BOLD, 30);
	public static Font titleBig = new Font(font2, Font.BOLD, 38);
	public static Font titleBig2 = new Font(font2, Font.BOLD, 55);
	public static Font plate = new Font(font3, Font.PLAIN, 78);
	public static Font plateSmall = new Font(font3, Font.PLAIN, 24);
	public static Color defColor = new Color(100, 149, 237);
	public static Color grayColor = Color.GRAY;
	public static NumberFormat formatter = NumberFormat.getCurrencyInstance();
	public static int pause = 10;
	public static int maxWeekHours = 40;
	public static int maxDayHours = 8;
	public static String driverKeyString = "autista";
	public static double overtimeIncrement = 1.3;
	
	private static boolean devMode = false;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
			        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("LicensePlate.ttf")));
					initialize();
					frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
					frame.setVisible(true);
				} catch (Exception e) {
					StackTraceFrame stf = new StackTraceFrame(e);
					stf.setVisible(true);
					frame.setVisible(true);
					e.printStackTrace();
				}
				if(devMode) {
					Employee admin = new Employee();
					admin.setCf("Developer");
					admin.setName("Developer");
					admin.setSurname("");
					admin.setPermission((short) 255);
					BUSNETWindow bw = new BUSNETWindow(admin);
					Application.setPanel(bw);
				}
			}
		});
	}
	
	/**
	 *Metodo di inizializzazione del frame principale e l'accesso alla schermata di login
	 *
	 */
	private static void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/busnet/resources/logo.png")));
		frame.setTitle("BUSNET");
		frame.setBounds(100, 100, 1688, 1145);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginControl lc = new LoginControl();
		frame.setContentPane(new WrapperPanel(lc.getlWindow()));
	}
	
	
	/**
	 *Metodo di riproduzione di un suono
	 *@param url un URL relativo alla posizione /busnet/sounds/
	 */
	public static synchronized void playSound(final String url) {
		  new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		          Application.class.getResourceAsStream("/busnet/sounds/" + url));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
	
	/**
	 *Metodo di conversione di stringhe tramite l'algoritmo MD5
	 *@param input Stringa di input
	 */
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	/**
	 *Metodo di inizializzazione del pannello principale del frame principale
	 *@param panel Pannello da inizializzare all'interno del {@link WrapperPanel}}
	 */
	public static void setPanel(JPanel panel) {
		frame.getContentPane().removeAll();
		frame.setContentPane(new WrapperPanel(panel));
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
	}
	/**
	 *Metodo di visualizzazione rapida di errori dbms
	 *@param e Eccezione da visualizzare
	 */
	public static void showDbError(Exception e) {
		ErrorWindow err = new ErrorWindow(e,"Errore DBMS");
		err.setVisible(true);
	}
	/**
	 *Metodo di visualizzazione rapida di errori a causa di loigin errato
	 */
	public static void showAccessError() {
		ErrorWindow err = new ErrorWindow("Username o password errati");
		err.setVisible(true);
	}
	
	public static void showError(String text) {
		ErrorWindow err = new ErrorWindow(text);
		err.setVisible(true);
	}
	
	public static void showError(Exception e) {
		ErrorWindow err = new ErrorWindow(e.getMessage());
		err.setVisible(true);
	}
}
