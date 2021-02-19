package busnet.features.shiftManagement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;

import busnet.Application;
import busnet.ConfirmationWindow;
import busnet.ErrorWindow;
import busnet.InfoWindow;
import busnet.dbmsManagement.DBInterface;
import busnet.entity.Employee;
import busnet.entity.ShiftDefaultList;
import busnet.entity.ShiftTemporaryList;
import busnet.exception.ConnectionDownException;
import busnet.exception.FieldErrorException;
import busnet.mailManagement.MailInterface;

public class ManageScheduleControl {
	private ManageScheduleWindow msWindow;
	private ShiftDefaultList sdList;
	private ArrayList<ShiftTemporaryList> stList;
	private ArrayList<Employee> empList;
	private DefaultScheduleDataWindow dsdWindow;
	private ManageTemporaryScheduleWindow mtsWindow;
	private TemporaryScheduleDataWindow tsdWindow;
	private OvertimeWindow oWindow;
	
	public ManageScheduleControl() {
		try {
			empList = DBInterface.rtrvEmployeeList();
			msWindow = new ManageScheduleWindow(empList) {
				@Override
				public void clickUpdateDefaultSchedule() {
					updateDefaultSchedule();
				}

				@Override
				public void clickManageTemporarySchedule() {
					manageTemporarySchedule();
				}

				@Override
				public void addOvertimeClickBtn() {
					addOvertime();
				}
				
			};
			msWindow.setVisible(true);
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	private void manageTemporarySchedule() {
		try {
			String selectedEmployee = (String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2);
			stList = DBInterface.rtrvShiftTempList(selectedEmployee);
			mtsWindow = new ManageTemporaryScheduleWindow(stList) {

				@Override
				public void addTemporaryShift() {
					addTempSchedule();
				}

				@Override
				public void rmvTemporaryShift() {
					rmvTempSchedule();
				}

				@Override
				public void showTemporaryShift() {
					showTempSchedule();
				}
				
			};
			mtsWindow.setVisible(true);
			mtsWindow.setStList(stList);
			mtsWindow.loadTable();	
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}

	private void rmvTempSchedule() {
		ConfirmationWindow cfWindow = new ConfirmationWindow("Conferma","Questo turno temporaneo verrà annullato") {
			@Override public void confirm() {
				try {
					this.dispose();
					MailInterface.sendTempScheduleRemovedMail(stList.get(mtsWindow.getTable().getSelectedRow()), DBInterface.rtrvEmployeeData((String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2)));
					DBInterface.rmvTempShiftData(stList.get(mtsWindow.getTable().getSelectedRow()));
					String selectedEmployee = (String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2);
					stList = DBInterface.rtrvShiftTempList(selectedEmployee);
				} catch (java.util.concurrent.ExecutionException e) {
					if(e.getCause() instanceof ConnectionDownException) {
						Application.showError("Impossibile effettuare l'invio della mail a causa di un errore di connessione");
					}
					
				}
				
				catch (ArrayIndexOutOfBoundsException e) {
					
				} catch (Exception e) {
					Application.showDbError(e);
				}
			}
		};
		cfWindow.setVisible(true);
		mtsWindow.setStList(stList);
		mtsWindow.loadTable();	
	}
	
	private void showTempSchedule() {
		ShiftTemporaryList stListx = stList.get(mtsWindow.getTable().getSelectedRow());
		tsdWindow = new TemporaryScheduleDataWindow(true) {
			@Override
			public void confirm() {
				tsdWindow.getTsTable().loadShiftTemporaryList(stListx, false);
				tsdWindow.getTsTable().refreshShift();
			}

			@Override
			public void clickBtnDone() {
				this.dispose();
			}
		};
		tsdWindow.confirm();
		tsdWindow.setVisible(true);
	}

	private void addTempSchedule() {
		tsdWindow = new TemporaryScheduleDataWindow() {
			@Override
			public void confirm() {
				String date = null;
				try {
					date = this.getYearField().getText() + "-" + (String)getMonthField().getSelectedItem() + "-" + (String)getDayField().getSelectedItem(); 
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date datey = formatter.parse(date);
					ShiftTemporaryList stList = DBInterface.rtrvShiftTempListOnDate((String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2), datey);
					this.getTsTable().loadShiftTemporaryList(stList, true);
					this.getTsTable().refreshShift();
					
				} catch(java.text.ParseException e) {
					Application.showError("Data non valida: " + date);
				}
				
				catch (Exception e) {
					Application.showDbError(e);
				}
			}

			@Override
			public void clickBtnDone() {
				try {
					tsdWindow.dispose();
					MailInterface.sendTempScheduleModifiedMail(this.getTsTable().getStList(), DBInterface.rtrvEmployeeData((String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2)));
					DBInterface.saveTempShiftData(this.getTsTable().getStList());
					String selectedEmployee = (String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2);
					stList = DBInterface.rtrvShiftTempList(selectedEmployee);
					mtsWindow.setStList(stList);
					mtsWindow.loadTable();	
				} catch (NullPointerException e) {
					
				} catch (java.util.concurrent.ExecutionException e) {
					if(e.getCause() instanceof ConnectionDownException) {
						Application.showError("Impossibile effettuare l'invio della mail a causa di un errore di connessione");
					}
					
				}
				catch (Exception e) {
					Application.showDbError(e);
				}
			}
			
		};
		tsdWindow.setVisible(true);
	}
	
	private void addOvertime() {
		try {
			String selectedEmployee = (String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2);
			oWindow = new OvertimeWindow(selectedEmployee) {
				@Override
				public void addOvertimeClickBtn() {
					try {
						String date = this.getYearField().getText() + "-" + (String)this.getMonthField().getSelectedItem() + "-" + (String)this.getDayField().getSelectedItem();
						int hours = Integer.parseInt(this.getHoursField().getText());
						Calendar cal = Calendar.getInstance();
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Date d = df.parse(date);
						Date today = cal.getTime();
						if(d.compareTo(today)<=0 && hours>=1 && hours<=4) {
							DBInterface.saveOvertime(selectedEmployee, date, hours);
							InfoWindow info = new InfoWindow("Operazione completata:\nOre straordinarie aggiunte: " + hours + " in data: " + date + " all'impiegato: " + selectedEmployee);
							info.setVisible(true);
							this.dispose();
						} else throw new FieldErrorException("la data deve essere oggi o nel passato, inoltre puoi inserire da 1 a massimo 4 ore straordinarie");
					} catch (NumberFormatException e) {
						Application.showError("Campo non valido: ore");
					} catch (java.text.ParseException e) {
						Application.showError("Capo non valido: data");
					}
					catch (FieldErrorException e) {
						Application.showError(e);
					} catch (Exception e) {
						Application.showDbError(e);
					}
					
				}
				
			};
			oWindow.setVisible(true);
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	private void updateDefaultSchedule() {
		try {
			String selectedEmployee = (String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2);
			sdList = DBInterface.rtrvShiftDefaultList(selectedEmployee);
			dsdWindow = new DefaultScheduleDataWindow(sdList) {

				@Override
				public void confirm() {
					saveDefaultSchedule();
				}
				
			};
			dsdWindow.setVisible(true);
		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	private void saveDefaultSchedule() {
		try {
			String selectedEmployee = (String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2);
			sdList = DBInterface.rtrvShiftDefaultList(selectedEmployee);
			MailInterface.sendDefaultScheduleModifiedMail(dsdWindow.getSdList(), DBInterface.rtrvEmployeeData(selectedEmployee));
			DBInterface.saveDefaultScheduleData(sdList, dsdWindow.getSdList());
			
			dsdWindow.dispose();
		} catch (java.util.concurrent.ExecutionException e) {
			if(e.getCause() instanceof ConnectionDownException) {
				Application.showError("Impossibile effettuare l'invio della mail a causa di un errore di connessione");
			}
		} catch (Exception e) {
			Application.showError(e);
		}
	}
}
