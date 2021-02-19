package busnet;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.SwingWorker;

import busnet.entity.Employee;
import busnet.entity.ShiftDefaultList;
import busnet.entity.ShiftTemporaryList;

import com.sun.mail.util.MailConnectException;
import busnet.exception.*;
import busnet.guiElements.InfoDialog;

public class MailInterface {

    private static String USER_NAME = "busnet.service";  // GMail user name
    private static String PASSWORD = "milano130"; // GMail password
    
    private static String[] days = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica"};
    private static String[] periods = {"6-10", "9-13", "12-16", "15-19", "18-22", "21-1"};
    
    //Invio mail ad un impiegato appena aggiunto al sistema, comunicandogli  dati di accesso.
    public static void sendNewHiringMail(Employee employee, String password) throws ConnectionDownException, MessagingException {
    	
        String[] to = { employee.getEmail() }; // list of recipient email addresses
        String subject = "Ti diamo il benvenuto su BUSNET!";
        String body = employee.getSurname() + " " + employee.getName() + ", Benvenuto nel servizio BUSNET!"
        		+ "\nQueste sono le tue credenziali di accesso:"
        		+ "\n\nCodice utente:\t" + employee.getCf()
        		+ "\nPassoword Temporanea:\t" + password
        		+ "\n\nTi ricordiamo che, per garantire la tua sicurezza, il sevizio BUSNET ti permetter� di scegliere la tua password personale"
        		+ " al tuo primo accesso.";

        sendFromGMail(USER_NAME, PASSWORD, to, subject, body);
    }
    
    //Invio mail all'impiegato selezionato di cui si vuole resettare la password, comunicandogli i nuovi dati di accesso
    public static void sendResetPasswordMail(Employee employee, String password) throws ConnectionDownException, MessagingException{
    	
        String[] to = { employee.getEmail() }; // list of recipient email addresses
        String subject = "Richiesta di Reset Password Effettuata";
        String body = employee.getSurname() + " " + employee.getName() + ", la tua password � stata resettata!\nPer accedere nuovamente al tuo account "
        		+ "dovrai effettuare l'accesso con la password riportata qui sotto e scegliere la tua nuova password personale una volta effettuato "
        		+ "l'accesso."
        		+ "\n\nQueste sono le tue nuova credenziali di accesso:"
        		+ "\n\nCodice utente:\t" + employee.getCf()
        		+ "\nPassoword Temporanea:\t" + password;

        sendFromGMail(USER_NAME, PASSWORD, to, subject, body);
    }
    

    //Metodo generico di invio mail
    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) throws ConnectionDownException, MessagingException{
    	InfoDialog dialog = new InfoDialog();
    	SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

			@Override
			protected String doInBackground() throws Exception {

		        Properties props = System.getProperties();
		        String host = "smtp.gmail.com";
		        props.put("mail.smtp.starttls.enable", "true");
		        props.put("mail.smtp.host", host);
		        props.put("mail.smtp.user", from);
		        props.put("mail.smtp.password", pass);
		        props.put("mail.smtp.port", "587");
		        props.put("mail.smtp.auth", "true");

		        Session session = Session.getDefaultInstance(props);
		        MimeMessage message = new MimeMessage(session);

		        try {
		            message.setFrom(new InternetAddress(from));
		            InternetAddress[] toAddress = new InternetAddress[to.length];

		            // To get the array of addresses
		            for( int i = 0; i < to.length; i++ ) {
		                toAddress[i] = new InternetAddress(to[i]);
		            }

		            for( int i = 0; i < toAddress.length; i++) {
		                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		            }

		            message.setSubject(subject);
		            message.setText(body);
		            Transport transport = session.getTransport("smtp");
		            transport.connect(host, from, pass);
		            transport.sendMessage(message, message.getAllRecipients());
		            transport.close();
		        }
		        catch (AddressException ae) {
		        	ae.printStackTrace();
		        }
		        catch (MailConnectException mce) {
		        	throw new ConnectionDownException("Errore di connessione nell'invio Mail");
		        }
		        return null;

			}

			@Override
			protected void done() {
				dialog.dispose();
			}

    	};

    	worker.execute();
    	dialog.setVisible(true);
    	try {
    		worker.get();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    //Invio della mail riguardante le informazioni sui sui nuovi turni.
    public static void sendDefaultScheduleModifiedMail(ShiftDefaultList sdList, Employee employee) throws ConnectionDownException, MessagingException{

    	String[] to = { employee.getEmail() }; // list of recipient email addresses
    	String subject = "La tua turnazione è stata modificata";
    	String body = employee.getSurname() + " " + employee.getName() + ", la tua tabella turni è stata modificata come segue:\n\n";
    	for (int i = 0; i < 7; i++) {
    		body += days[i] + ": ";
    		for (int j = 0; j < periods.length; j++) {
    			if (sdList.getShift(i, j)) {
    				body += periods[j] + " ";
    			}
    		}
    		body += "\n";
    	}

    	sendFromGMail(USER_NAME, PASSWORD, to, subject, body);
    }

    public static void sendTempScheduleRemovedMail(ShiftTemporaryList stList, Employee employee) throws ConnectionDownException, MessagingException{

    	String[] to = { employee.getEmail() }; // list of recipient email addresses
    	String subject = "La tua turnazione è stata modificata";
    	String body = employee.getSurname() + " " + employee.getName() + ", la tua turnazione temporanea in data " + stList.getDate() + " è stata rimossa.\n"
    			+ "Per verificare gli orari dei tuoi turni ti invitiamo a controllare la tua turnazione tramite il portale BUSNET\n\n";

    	sendFromGMail(USER_NAME, PASSWORD, to, subject, body);
    }

    public static void sendTempScheduleModifiedMail(ShiftTemporaryList stList, Employee employee) throws ConnectionDownException, MessagingException{
    	boolean existTurn = false;
    	String[] to = { employee.getEmail() }; // list of recipient email addresses
    	String subject = "La tua turnazione è stata modificata";
    	String body = employee.getSurname() + " " + employee.getName() + ", la tua turnazione in data " + stList.getDate() + " è stata modificata come segue:\n\n";
    	for (int j = 0; j < periods.length; j++) {
    		if (stList.getShift(j)) {
    			existTurn = true;
    			body += periods[j] + " ";
    		}
    	}
    	if(!existTurn) body += "giornata libera";
    	body += "\n";

    	sendFromGMail(USER_NAME, PASSWORD, to, subject, body);
    }
}
