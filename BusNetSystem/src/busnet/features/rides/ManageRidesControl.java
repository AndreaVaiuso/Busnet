package busnet.features.rides;

import java.util.ArrayList;

import busnet.Application;
import busnet.DBInterface;
import busnet.entity.Bus;
import busnet.entity.Employee;
import busnet.entity.Line;
import busnet.entity.Ride;
import busnet.entity.RideList;
import busnet.guiElements.ErrorWindow;

public class ManageRidesControl {
	
	private ArrayList<Line> lineList;
	private Line selectedLine;
	private int daySelected;
	private ManageRidesWindow mrWindow;
	private ManageRidesDataWindow mrdWindow;
	private ArrayList<Bus> availableBus;
	private SubstitutionWindow subWindow;

	public ManageRidesControl() {
		try {
			setLineList(DBInterface.rtrvLineList());
			mrWindow = new ManageRidesWindow(lineList) {
				@Override
				public void confirm() {
					try {
						selectedLine = DBInterface.rtrvLineData(this.getTabSelectionList().getLineIdSelected());
						daySelected = this.getDaySelected();
						manageRides(selectedLine, daySelected);
					} catch (NullPointerException e) {
						ErrorWindow err = new ErrorWindow(e,"Seleziona una linea e poi scegli il giorno");
						err.setVisible(true);
					} catch (Exception e) {
						Application.showDbError(e);
					}
				}

				@Override
				public void showSubstitutionWindow() {
					subWindow = new SubstitutionWindow() {
						
					};
					subWindow.setVisible(true);
					
				}
			};
			mrWindow.setVisible(true);
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	public void manageRides(Line line, int day) {
		try {
			availableBus = new ArrayList<>();
			try {
				availableBus = DBInterface.rtrvFreeBusList(day);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			RideList rl = DBInterface.rtrvRideList(line.getLineId(), (short)day);
			ArrayList<ArrayList<Employee>> availableEmployee = DBInterface.rtrvFreeEmployeeOnDay(day);
			mrdWindow = new ManageRidesDataWindow(rl, availableEmployee) {
				@Override
				public void addRide(int i, int j) {
					try {
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("i: " + i + " j: " + j);
					AddRideWindow anrWindow = new AddRideWindow(mrdWindow.getAvailableEmployee(j)) {
						@Override
						public void clickAddDriverBtn() {
							mrdWindow.getrList().peekRide(i).setEmployeeShift(j, ((Employee)this.getComboBox().getSelectedItem()).getCf());
							mrdWindow.getAvailableEmployee(j).remove(this.getComboBox().getSelectedIndex());
							mrdWindow.getRideTabPanel().setSelected(i,j+2, true, ((Employee)this.getComboBox().getSelectedItem()).toString());
						}	
					};
					anrWindow.setVisible(true);
					
				}
				@Override
				public void delRide(int i, int j) {
					Employee emp = null;
					try {
						emp = DBInterface.rtrvEmployeeData(mrdWindow.getrList().peekRide(i).getEmployeeShift(j));
					} catch (Exception e) {
						e.printStackTrace();
					}
					mrdWindow.getAvailableEmployee(j).add(emp);
					mrdWindow.getRideTabPanel().setSelected(i, j+2, false);
					mrdWindow.getrList().peekRide(i).delEmployeeShift(j);
				}
				@Override
				public void addBus() {
					AddNewBusWindow anbWindow = new AddNewBusWindow(availableBus) {
						@Override
						public void clickAddBusBtn() {
							mrdWindow.getRideTabPanel().addRow(this.getBusSelected());
							availableBus.remove(this.getComboBox().getSelectedIndex());
						}

					};
					anbWindow.setVisible(true);
					
				}
				@Override
				public void save() {
					try {
						RideList rl = getrList();
						for(int i=0;i<rl.getRidesSize();i++) {
							rl.peekRide(i).setStartTime(getRideTabPanel().getStartTime(i));
						}
						DBInterface.saveRideList(getrList());
						this.dispose();
					} catch(NumberFormatException e) {
						ErrorWindow err = new ErrorWindow(e,"Errore input:");
						err.setVisible(true);
					}
					catch (Exception e) {
						Application.showDbError(e);
					}
					
				}
			};
			mrdWindow.setVisible(true);
		} catch (Exception e) {
			Application.showDbError(e);
		}
		
	}

	public ArrayList<Line> getLineList() {
		return lineList;
	}

	public void setLineList(ArrayList<Line> lineList) {
		this.lineList = lineList;
	}

	public Line getSelectedLine() {
		return selectedLine;
	}

	public void setSelectedLine(Line selectedLine) {
		this.selectedLine = selectedLine;
	}

	public int getDaySelected() {
		return daySelected;
	}

	public void setDaySelected(int daySelected) {
		this.daySelected = daySelected;
	}
}
