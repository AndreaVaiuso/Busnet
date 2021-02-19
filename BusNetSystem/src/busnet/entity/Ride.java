package busnet.entity;

public class Ride {
	private String startTime;
	private String[] employeeShifts;

	public Ride(String startTime) {
		this.startTime = startTime;
		employeeShifts = new String[ShiftDefaultList.periodsNum];
	}
	
	public Ride() {
		employeeShifts = new String[ShiftDefaultList.periodsNum];
	}
	public void setEmployeeShift(int period, String employeeShift) {
		employeeShifts[period] = employeeShift;
	}
	public void delEmployeeShift(int period) {
		employeeShifts[period] = null;
	}
	public String getEmployeeShift(int period) {
		return employeeShifts[period];
	}

	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Override
	public String toString() {
		String s = "Start time: " + startTime + "\n ";
		for(int i=0;i<ShiftDefaultList.periodsNum;i++) {
			s += getEmployeeShift(i) + " ";
		}
		return s;
	}
	
}
