package busnet.features.shift;

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
import busnet.DBInterface;
import busnet.MailInterface;
import busnet.entity.Employee;
import busnet.entity.ShiftDefaultList;
import busnet.entity.ShiftTemporaryList;
import busnet.exception.ConnectionDownException;
import busnet.guiElements.ConfirmationWindow;
import busnet.guiElements.ErrorWindow;

public class ManageScheduleControl {
	private ManageScheduleWindow msWindow;
	private ShiftDefaultList sdList;
	private ArrayList<ShiftTemporaryList> stList;
	private DefaultScheduleDataWindow dsdWindow;
	private ManageTemporaryScheduleWindow mtsWindow;
	private TemporaryScheduleDataWindow tsdWindow;
	
	public ManageScheduleControl() {
		ArrayList<Employee> empList = new ArrayList<>();
		try {
			empList = DBInterface.rtrvEmployeeList();
		} catch (Exception e) {
			Application.showDbError(e);
		}
		msWindow = new ManageScheduleWindow(empList) {

			@Override
			public void clickUpdateDefaultSchedule() {
				updateDefaultSchedule();
			}

			@Override
			public void clickManageTemporarySchedule() {
				manageTemporarySchedule();
			}
			
		};
		msWindow.setVisible(true);
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
			@Override public void yes() {
				try {
					DBInterface.rmvTempShiftData(stList.get(mtsWindow.getTable().getSelectedRow()));
					
					try {
						MailInterface.sendTempScheduleRemovedMail(stList.get(mtsWindow.getTable().getSelectedRow()), DBInterface.rtrvEmployeeData((String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2)));
						
					} catch (ConnectionDownException | MessagingException e) {
						ErrorWindow err = new ErrorWindow(e,"Errore email:");
						err.setVisible(true);
					}
					String selectedEmployee = (String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2);
					stList = DBInterface.rtrvShiftTempList(selectedEmployee);
				} catch (ArrayIndexOutOfBoundsException e) {
					
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
				try {

					String date = this.getYearField().getText() + "-" + (String)getMonthField().getSelectedItem() + "-" + (String)getDayField().getSelectedItem(); 
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date datey = formatter.parse(date);
					ShiftTemporaryList stList = DBInterface.rtrvShiftTempListOnDate((String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2), datey);
					this.getTsTable().loadShiftTemporaryList(stList, true);
					this.getTsTable().refreshShift();
					
				} catch (Exception e) {
					Application.showDbError(e);
				}
			}

			@Override
			public void clickBtnDone() {
				try {
					DBInterface.saveTempShiftData(this.getTsTable().getStList());
					tsdWindow.dispose();
					String selectedEmployee = (String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2);
					stList = DBInterface.rtrvShiftTempList(selectedEmployee);
					mtsWindow.setStList(stList);
					mtsWindow.loadTable();	
					try {
						MailInterface.sendTempScheduleModifiedMail(this.getTsTable().getStList(), DBInterface.rtrvEmployeeData((String) msWindow.getTable().getValueAt(msWindow.getTable().getSelectedRow(), 2)));
					} catch (ConnectionDownException | MessagingException e) {
						ErrorWindow err = new ErrorWindow(e,"Errore email:");
						err.setVisible(true);
					}
				} catch (Exception e) {
					Application.showDbError(e);
				}
			}
			
		};
		tsdWindow.setVisible(true);
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
			DBInterface.saveDefaultScheduleData(sdList, dsdWindow.getSdList());
			MailInterface.sendDefaultScheduleModifiedMail(dsdWindow.getSdList(), DBInterface.rtrvEmployeeData(selectedEmployee));
			dsdWindow.dispose();
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
}
