package busnet.features.employeeManagement;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

import com.mysql.jdbc.MysqlDataTruncation;

import busnet.Application;
import busnet.ConfirmationWindow;
import busnet.ErrorWindow;
import busnet.InfoWindow;
import busnet.dbmsManagement.DBInterface;
import busnet.entity.Employee;
import busnet.exception.ConnectionDownException;
import busnet.exception.FieldErrorException;
import busnet.mailManagement.MailInterface;

public class ManageEmployeeControl {
	private EmployeesWindow seWindow;
	private EmployeeDataWindow sedWindow;
	private SalaryWindow sWindow;
	
	public ManageEmployeeControl(){
		//Load employee list
		ArrayList<Employee> empList = rtrvEmployeeList();
		//Create Window
		seWindow = new EmployeesWindow(empList) {
			private static final long serialVersionUID = 1L;
			@Override
			public void clickAddEmployeeButton() {
				newEmployee();
			}
			@Override
			public void clickDelEmployeeButton() {
				rmvEmployee();
			}
			@Override
			public void clickShowEmployeeButton() {
				showEmployeeData(false);
				
			}
			@Override
			public void clickuUpdateEmployeeButton() {
				showEmployeeData(true);
			}
			@Override
			public void clickResetPasswordButton() {
				resetPassword();
			}
			@Override
			public void clickCalculateSalaryBtn() {
				calculateSalary();
				
			}
		};
		seWindow.setVisible(true);
	}
	
	public ArrayList<Employee> rtrvEmployeeList() {
		try {
			return DBInterface.rtrvEmployeeList();
		} catch (Exception e) {
			ErrorWindow err = new ErrorWindow(e,"Impossibile caricare i dati sugli impiegati");
			err.setVisible(true);
		}
		return null;
	}

	public void newEmployee() {
		sedWindow = new EmployeeDataWindow() {

			private static final long serialVersionUID = -2443237431128636176L;
			@Override
			public void confirm() {
				saveEmployee();
			}
			
		};
		sedWindow.setVisible(true);
	}

	public void rmvEmployee() {
		try {
			String cfSelected = (String)seWindow.getTable().getModel().getValueAt(seWindow.getTable().getSelectedRow(), 2);
			ConfirmationWindow win = new ConfirmationWindow("Eliminazione","Sei sicuro di voler eliminare questo dato? L'operazione è irreversibile.\nCF: " + cfSelected) {
				@Override
				public void confirm() {
					try {
						DBInterface.removeEmployee(cfSelected);
						updateTable();
						InfoWindow win = new InfoWindow("Operazione completata. Rimozione completata");
						win.setVisible(true);
					} catch (Exception e) {
						Application.showDbError(e);
					}
				}
			};
			win.setVisible(true);
		} catch (ArrayIndexOutOfBoundsException ex) {
			
		}
	}

	public void showEmployeeData(boolean modify) {
		Employee emp = null;
		try {
				String cfSelected = (String)seWindow.getTable().getModel().getValueAt(seWindow.getTable().getSelectedRow(), 2);
				emp = DBInterface.rtrvEmployeeData(cfSelected);
				sedWindow = new EmployeeDataWindow(emp) {
					
					private static final long serialVersionUID = 3238420535508313115L;
					@Override
					public void confirm() {
						updateEmployee(modify);
						updateTable();
					}
			};
			sedWindow.setEditable(modify);
			sedWindow.setVisible(true);
		} catch (ArrayIndexOutOfBoundsException ex) {

		}
		catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	public void showWindow() {
		seWindow.setVisible(true);
	}

	public void saveEmployee(){		
			String random = getRandomPassword();
			Employee emp = null;
			try {
				emp = getEmployeeInstance();
				MailInterface.sendNewHiringMail(emp, random); 
				DBInterface.saveEmployeeData(emp, random); 
				sedWindow.dispose();
			} catch (FieldErrorException e) {
				Application.showError(e);
			} catch (MysqlDataTruncation e) {
				Application.showError("Valori troppo lunghi per il database. Ricontrolla i form");
			} catch (ClassNotFoundException e) {
				Application.showDbError(e);
			} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
				Application.showError("Impossibile salvare un impiegato con codice fiscale o email uguale ad un impiegato già registrato");
			} catch (InterruptedException | MessagingException | ConnectionDownException e) {
				Application.showError("Impossibile spedire la mail, l'indirizzo non è valido");
				try {
					DBInterface.removeEmployee(emp.getCf());
					e.printStackTrace();
				} catch (Exception ex) {
					
				}
			} catch (java.util.concurrent.ExecutionException e) {
				if(e.getCause() instanceof ConnectionDownException) {
					Application.showError("Impossibile effettuare l'invio della mail a causa di un errore di connessione");
			}
			} catch (Exception e) {
				ErrorWindow err = new ErrorWindow(e, "Errore generico");
				err.setVisible(true);
			}
			updateTable();
	}

	public void updateEmployee(boolean modify){
		try {
			if(modify) {
				DBInterface.saveEmployeeData(getEmployeeInstance());
				InfoWindow win = new InfoWindow("Operazione completata. Dati aggiornati");
				win.setVisible(true);
			}
			sedWindow.dispose();	
		} catch (FieldErrorException e) {
			Application.showError(e);
		} catch (com.mysql.jdbc.MysqlDataTruncation e) {
			Application.showError("Valori troppo lunghi per il database. Ricontrolla i form");
		}
		catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
			Application.showError("Non puoi modificare un autista se ha delle corse instanziate. Elimina le corse e riprova");
		}
		catch(Exception e) {
			Application.showDbError(e);
		}	
	}
	
	protected String getRandomPassword() {
	    String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    StringBuilder salt = new StringBuilder();
	    Random rnd = new Random();
	    while (salt.length() < 7) {
	        int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	        salt.append(SALTCHARS.charAt(index));
	    }
	    String saltStr = salt.toString();
	    return saltStr;
	}
	
	private void resetPassword() {
		try {
			String cfSelected = (String)seWindow.getTable().getModel().getValueAt(seWindow.getTable().getSelectedRow(), 2);
			ConfirmationWindow conf = new ConfirmationWindow("Attenzione","La password dell'impiegato selezionato verrà resettata. Continuare?\nCodice fiscale: " + cfSelected) {
				@Override
				public void confirm() {
					String random = getRandomPassword();	
					try {
						Employee emp = DBInterface.rtrvEmployeeData(cfSelected);
						MailInterface.sendResetPasswordMail(emp, random);  
						DBInterface.resetEmployeePassword(emp, random);
						dispose();
						InfoWindow info = new InfoWindow("Operazione completata, password resettata");
						info.setVisible(true);
					} catch (MysqlDataTruncation e) {
						ErrorWindow erw = new ErrorWindow(e,"Impossibile salvare i dati, ricontrolla i form:");
						erw.setVisible(true);
					} catch (ClassNotFoundException e) {
						Application.showDbError(e);
					} catch (SQLException e) {
						Application.showDbError(e);
					} catch (java.util.concurrent.ExecutionException e) {
						if(e.getCause() instanceof ConnectionDownException) {
							Application.showError("Impossibile effettuare l'invio della mail a causa di un errore di connessione");
					}
					} catch (MessagingException ex) {
						Application.showError("Impossibile spedire la mail, l'indirizzo non è valido");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			conf.setVisible(true);
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}
		
	}
	
	private void calculateSalary() {
		String cfSelected = (String)seWindow.getTable().getModel().getValueAt(seWindow.getTable().getSelectedRow(), 2);
		sWindow = new SalaryWindow(cfSelected) {
			@Override
			public void calc() {
				try {
					
					Employee emp = DBInterface.rtrvEmployeeData(cfSelected);
					
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					String moneyString = formatter.format(emp.getSalary());
					
					this.getSalaryField().setText(moneyString);
					int month = this.getMonthField().getSelectedIndex();
					int year = Integer.parseInt(this.getYearField().getText());
					
					int overtimeHours = DBInterface.calculateOvertime(month+1, year, emp.getCf()) + (DBInterface.calculateTemporaryHours(emp.getCf(), year+"-"+(month+1)+"-01"))*4;
					
					System.out.println("Ore straordinarie nel mese di " + month + ": " + overtimeHours);
					this.getStraordinaryField().setText(overtimeHours+"");
					
					YearMonth yearMonthObject = YearMonth.of(year, month+1);
					int daysInMonth = yearMonthObject.lengthOfMonth();
					
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date fDay = df.parse(year+"-"+month+"-1");
					
					int days[] = DBInterface.calculateWorkHoursOnWeek(cfSelected);
					
					System.out.println("Ore settimanali:");
					for(int i=0;i<7;i++) {
						System.out.println(days[i]);
					}
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(fDay);
					int firstDay = cal.get(Calendar.DAY_OF_WEEK);
					firstDay -=2;
					if(firstDay==-1) {
						firstDay = 6;
					}
					
					int totalHours = 0;
					
					int counter = firstDay;
					
					for(int i = 0; i<daysInMonth; i++) {
						totalHours+=(days[counter]*4);
						counter++;
						counter = counter%7;
					}
					
					this.getHoursField().setText(totalHours+"");
					double totalAmount = (emp.getSalary() * totalHours) + (emp.getSalary() * Application.overtimeIncrement * overtimeHours);
					String totalString = formatter.format(totalAmount);
					this.getTotalField().setText(totalString);
					
					this.getIbanField().setVisible(true);
					this.getIbanField().setText(this.getIbanField().getText()+emp.getIban());
					
					
					
				} catch (NumberFormatException e) {
					
				} catch (Exception e) {
					Application.showDbError(e);
				}
			}
		};
		sWindow.setVisible(true);
	}

	
	private Employee getEmployeeInstance() throws FieldErrorException{
		Employee employee = new Employee();
		employee.setCf(sedWindow.getCfField().getText());
		employee.setName(sedWindow.getNameField().getText());
		employee.setSurname(sedWindow.getSurnameField().getText());
		employee.setIban(sedWindow.getIbanField().getText());
		employee.setRole(sedWindow.getRoleField().getText());
		employee.setEmail(sedWindow.getMailField().getText());
		
		String month = sedWindow.getMonthField().getItemAt(sedWindow.getMonthField().getSelectedIndex());
		String day = sedWindow.getDayField().getItemAt(sedWindow.getDayField().getSelectedIndex());
		String birthday = sedWindow.getYearField().getText() + "-" + month + "-" + day;
		
		employee.setBirthday(birthday);	
		employee.setHiring_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		Number num = null;
		try {
			num = Application.formatter.parse(sedWindow.getSalaryField().getText());
			employee.setSalary(num.doubleValue());
		} catch (ParseException e) {
			try {
				employee.setSalary(Double.parseDouble(sedWindow.getSalaryField().getText()));
			} catch (NumberFormatException ex) {
				throw new FieldErrorException("Formato campo non valido per salario: " + sedWindow.getSalaryField().getText());
			}
		}
		short permissionMask = 0;
		if (sedWindow.getChkEmployee().isEnabled()){
			permissionMask |= 1;
		}
		if (sedWindow.getChkLine().isEnabled()){
			permissionMask |= 1 << 1;
		}
		if (sedWindow.getChkRide().isEnabled()){
			permissionMask |= 1 << 2;
		}
		if (sedWindow.getChkTurn().isEnabled()){
			permissionMask |= 1 << 3;
		}
		
		
		
		
		if(sedWindow.getCfField().containsSpaces()) {
			throw new FieldErrorException("Il campo Codice Fiscale non deve contenere spazi all'inizio o alla fine");
		}
		
		if(sedWindow.getNameField().containsSpaces()) {
			throw new FieldErrorException("Il campo nome non deve contenere spazi all'inizio o alla fine");
		}
		
		if(sedWindow.getSurnameField().containsSpaces()) {
			throw new FieldErrorException("Il campo cognome non deve contenere spazi all'inizio o alla fine");
		}
		
		if(sedWindow.getIbanField().containsSpaces()) {
			throw new FieldErrorException("Il campo IBAN non deve contenere spazi all'inizio o alla fine");
		}
		
		if(sedWindow.getRoleField().containsSpaces()) {
			throw new FieldErrorException("Il campo ruolo non deve contenere spazi all'inizio o alla fineI form non possono contenere spazi o all'inizio o alla fine");
		}
		Calendar cal = Calendar.getInstance();
		try {
			if(Integer.parseInt(sedWindow.getYearField().getText())<1900 || Integer.parseInt(sedWindow.getYearField().getText())>=(cal.get(Calendar.YEAR)-18)) {
				throw new FieldErrorException("Anno di nascita non valido: " + sedWindow.getYearField().getText());
			}
		} catch (NumberFormatException e) {
			throw new FieldErrorException("Anno di nascita non valido: " + sedWindow.getYearField().getText());
		}
		
		if(sedWindow.getMailField().containsSpaces()) {
			throw new FieldErrorException("Il campo e-mail non deve contenere spazi all'inizio o alla fine");
		}
		
		if(sedWindow.getCfField().getText().isEmpty() || sedWindow.getIbanField().getText().isEmpty() || sedWindow.getMailField().getText().isEmpty() || sedWindow.getNameField().getText().isEmpty() || sedWindow.getRoleField().getText().isEmpty() || sedWindow.getSalaryField().getText().isEmpty() || sedWindow.getSurnameField().getText().isEmpty()) {
			throw new FieldErrorException("Devi inserire i dati in tutti i campi");
		}
		if(!sedWindow.getNameField().isNameValid()) {
			throw new FieldErrorException("Input non valido per: " + sedWindow.getNameField().getText() + ". Caratteri non validi inseriti");
		}
		if(!sedWindow.getSurnameField().isNameValid()) {
			throw new FieldErrorException("Input non valido per: " + sedWindow.getSurnameField().getText() + ". Caratteri non validi inseriti");
		}
		if(employee.getSalary()<6.5) {
			throw new FieldErrorException("Valore del salario deve essere almeno pari al salario minimo secondo la legislazione corrente: 6,50EUR/h");
		} 
		if(employee.getIban().length()<27) {
			throw new FieldErrorException("Formato codice IBAN non corretto");
		}
		if(!sedWindow.getCfField().isCfValid()) {
			throw new FieldErrorException("Formato codice fiscale non corretto");
		}
		
		employee.setPermission(permissionMask);
		return employee;
	}
	
	private void updateTable() {
		ArrayList<Employee> empList = rtrvEmployeeList();
		seWindow.setEmpList(empList);
		seWindow.loadTable();
	}
}
