package busnet.entity;

public class ShiftDefaultList {
	private String employeeId;
	private boolean[][] shifts;
	public static final int periodsNum = 6;
	
	public ShiftDefaultList(String employeeId) {
		this.employeeId = employeeId;
		shifts = new boolean[7][periodsNum];
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public void setShift(int day, int period) {
		shifts[day][period] = true;
	}
	public void delShift(int day, int period) {
		shifts[day][period] = false;
	}
	public boolean getShift(int day, int period) {
		return shifts[day][period];
	}
	
}
