package busnet.features.ridesManagement;

import java.util.ArrayList;

import busnet.Application;
import busnet.ErrorWindow;
import busnet.dbmsManagement.DBInterface;
import busnet.entity.Bus;
import busnet.entity.Employee;
import busnet.entity.Line;
import busnet.entity.Ride;
import busnet.entity.RideList;
import busnet.entity.Substitution;
import busnet.guiElements.AddNewBusWindow;
import busnet.guiElements.AddRideWindow;
import busnet.guiElements.AddSubstitutionWindow;

public class ManageRidesControl {
	
	private ArrayList<Line> lineList;
	private Line selectedLine;
	private int daySelected;
	private ManageRidesWindow mrWindow;
	private ManageRidesDataWindow mrdWindow;
	private ArrayList<Bus> availableBus;
	private SubstitutionWindow subWindow;
	private ArrayList<Substitution> subList;
	private ArrayList<Employee> availableEmployeeList;

	
	public ManageRidesControl() {
		try {
			lineList = DBInterface.rtrvLineList();
			mrWindow = new ManageRidesWindow(lineList) {
				@Override
				public void confirm() {
					try {
						selectedLine = DBInterface.rtrvLineData(this.getTabSelectionList().getLineIdSelected());
						daySelected = this.getDaySelected();
						manageRides(selectedLine, daySelected);
					} catch (NullPointerException e) {
					} catch (Exception e) {
						Application.showDbError(e);
					}
				}

				@Override
				public void showSubstitutionWindow() {
					showSubstitutions();
				}

				@Override
				public void checkSubstitutions() {
					try {
						subList = DBInterface.rtrvSubstitutionList();
					} catch (Exception e) {
						Application.showDbError(e);
					}
					if(!subList.isEmpty()) {
						for(int i=0;i<subList.size();i++) {
							if(subList.get(i).getSubDriver()==null) {
								this.notifySubstitution(true);
								return;
							}
						}
					} else this.notifySubstitution(false);
				}
			};
			mrWindow.setVisible(true);
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	private void showSubstitutions() {
		subWindow = new SubstitutionWindow(subList) {
			@Override
			public void doSubstitutionClickBtn() {
				addSubstitution();
			}
			@Override
			public void removeSubstitutionClickBtn() {
				removeSubstitution();
			}
		};
		subWindow.setVisible(true);
	}
	
	private void removeSubstitution() {
		try {
			subWindow.getSubList().get(subWindow.getTable().getSelectedRow()).setSubDriver(null);
			DBInterface.removeSubstitution(subWindow.getSubList().get(subWindow.getTable().getSelectedRow()));
			subWindow.setSubList(subList);
			subWindow.loadTable();
		} catch(NullPointerException e) {
			
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	private void addSubstitution() {
		try {
			availableEmployeeList = DBInterface.rtrvAvailableSubstitutionEmployee(subWindow.getSubList().get(subWindow.getTable().getSelectedRow()).getShiftDate(),subWindow.getSubList().get(subWindow.getTable().getSelectedRow()).getPeriod());
			AddSubstitutionWindow subWin = new AddSubstitutionWindow(availableEmployeeList) {
				@Override
				public void chose() {
					try {
						subWindow.getSubList().get(subWindow.getTable().getSelectedRow()).setSubDriver(((Employee)this.getComboBox().getSelectedItem()).getCf());
						DBInterface.saveSubstitutionData(subWindow.getSubList().get(subWindow.getTable().getSelectedRow()));
						subWindow.setSubList(subList);
						subWindow.loadTable();
						
					} catch (NullPointerException e) {
						
					}
					catch (Exception e) {
						Application.showDbError(e);
					}
					this.dispose();
				}
			};
			subWin.setVisible(true);
		} catch (Exception e) {
			Application.showDbError(e);
		}
	}

	private void manageRides(Line line, int day) {
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
					saveRidesData();
				}
			};
			mrdWindow.setVisible(true);
		} catch (Exception e) {
			Application.showDbError(e);
		}
		
	}
	
	private void saveRidesData() {
		int i = 0;
		try {
			RideList rl = mrdWindow.getrList();
			for(i=0;i<rl.getRidesSize();i++) {
				rl.peekRide(i).setStartTime(mrdWindow.getRideTabPanel().getStartTime(i));
			}
			DBInterface.saveRideList(mrdWindow.getrList());
			mrdWindow.dispose();
		} catch(NumberFormatException e) {
			Application.showError(e);
		}
		catch (Exception e) {
			Application.showDbError(e);
		}
	}
	
	

	public ArrayList<Line> getLineList() {
		return lineList;
	}

	public Line getSelectedLine() {
		return selectedLine;
	}

	public int getDaySelected() {
		return daySelected;
	}
}
