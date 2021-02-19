package busnet.features.employee;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

import com.mysql.jdbc.MysqlDataTruncation;

import busnet.Application;
import busnet.DBInterface;
import busnet.MailInterface;
import busnet.entity.Employee;
import busnet.exception.ConnectionDownException;
import busnet.guiElements.ConfirmationWindow;
import busnet.guiElements.ErrorWindow;
import busnet.guiElements.InfoWindow;

public class ManageEmployeeControl {
	private EmployeesWindow seWindow;
	private EmployeeDataWindow sedWindow;
	
	/*CONSTRUCTOR
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * */
	
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
		};
		seWindow.setVisible(true);
	}
	
	

	/*METHODS
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * */
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
				public void yes() {
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
			Employee emp = getEmployeeInstance();
			try {
				DBInterface.saveEmployeeData(emp, random);
				MailInterface.sendNewHiringMail(emp, random);  
				sedWindow.dispose();
			} catch (MysqlDataTruncation e) {
				ErrorWindow erw = new ErrorWindow(e,"Impossibile salvare i dati, ricontrolla i form:");
				erw.setVisible(true);
			} catch (ClassNotFoundException e) {
				Application.showDbError(e);
			} catch (SQLException e) {
				Application.showDbError(e);
			} catch (ConnectionDownException e) {
				ErrorWindow erw = new ErrorWindow(e, "Impossibile spedire la mail, l'indirizzo non è valido o non è raggiungibile:");
				erw.setVisible(true);
			} catch (MessagingException ex) {
				ErrorWindow erw = new ErrorWindow(ex, "Impossibile spedire la mail, l'indirizzo non è valido o non è raggiungibile:");
				erw.setVisible(true);
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
		} catch(Exception e) {
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
				public void yes() {
					String random = getRandomPassword();	
					try {
						Employee emp = DBInterface.rtrvEmployeeData(cfSelected);
						DBInterface.resetEmployeePassword(emp, random);
						MailInterface.sendResetPasswordMail(emp, random);  
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
					} catch (ConnectionDownException e) {
						ErrorWindow erw = new ErrorWindow(e, "Impossibile spedire la mail, l'indirizzo non è valido o non è raggiungibile:");
						erw.setVisible(true);
					} catch (MessagingException ex) {
						ErrorWindow erw = new ErrorWindow(ex, "Impossibile spedire la mail, l'indirizzo non è valido o non è raggiungibile:");
						erw.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			conf.setVisible(true);
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}
		
	}

	
	private Employee getEmployeeInstance() {
		Employee employee = new Employee();
		employee.setCf(sedWindow.getCfField().getText());
		employee.setName(sedWindow.getNameField().getText());
		employee.setSurname(sedWindow.getSurnameField().getText());
		employee.setBirthplace(sedWindow.getPlaceField().getText());
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
				ErrorWindow win = new ErrorWindow(ex,"Formato non valido per il campo: Salario\nSalario impostato al valore di default: 0");
				win.setVisible(true);
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
		employee.setPermission(permissionMask);
		
		return employee;
	}
	
	private void updateTable() {
		ArrayList<Employee> empList = rtrvEmployeeList();
		seWindow.setEmpList(empList);
		seWindow.loadTable();
	}
}
