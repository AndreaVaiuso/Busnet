package busnet.login;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import busnet.Application;
import busnet.BUSNETWindow;
import busnet.ErrorWindow;
import busnet.dbmsManagement.DBInterface;
import busnet.entity.Employee;

public class LoginControl {
	private LoginPanel lWindow;
	private PasswordPanel pWindow;
	private Employee emp;
	private BUSNETWindow bw;
	
	public LoginControl() {
		lWindow = new LoginPanel() {
			@Override
			public void confirm() {
				checkUserCredential();
			}
		};
	}

	public void checkUserCredential() {
		try {
			if(DBInterface.chkEmployeeCredentials(lWindow.getUserField().getText(),String.valueOf(lWindow.getPassField().getPassword()))) {
				emp = DBInterface.rtrvEmployeeData(lWindow.getUserField().getText());
				if(DBInterface.chkTemporaryPsw(lWindow.getUserField().getText())) {
					pWindow = new PasswordPanel() {
						@Override
						public void confirm() {
							String pw1 = String.valueOf(pWindow.getPasswordField().getPassword());
							String pw2 = String.valueOf(pWindow.getPasswordField_1().getPassword());
							if(pw1.equals(pw2)) {
								try {
									DBInterface.updateEmployeePassword(emp, pw1);
									bw = new BUSNETWindow(emp);
									Application.setPanel(bw);
								} catch (Exception e) {
									ErrorWindow erw = new ErrorWindow(e,"Errore DBMS");
									erw.setVisible(true);
								}
							}
							else {
								try {
									throw new Exception("Le password non corrispondono");
								} catch (Exception e) {
									ErrorWindow erw = new ErrorWindow(e,"Errore nel reset della password:");
									erw.setVisible(true);
								}
							}
						}
					};
					Application.setPanel(pWindow);
				}
				else {
					bw = new BUSNETWindow(emp);
					Application.setPanel(bw);
				}
			}
			else Application.showAccessError();
		}
		catch(NullPointerException e) {
			Application.showAccessError();
		}
		catch(SQLException e) {
			Application.showDbError(e);
		}
		catch(Exception e) {
			Application.showDbError(e);
		}
	}
	
	public LoginPanel getlWindow() {
		return lWindow;
	}

	public PasswordPanel getpWindow() {
		return pWindow;
	}
}
