package busnet.entity;

public class ShiftTemporaryList {
	private String employeeId;
	private String date;
	private boolean[] shifts;
	public static final int periodsNum = 6;

	public ShiftTemporaryList(String employeeId, String date) {
		this.employeeId = employeeId;
		this.date = date;
		shifts = new boolean[periodsNum];
	}


	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDate(){
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setShift(int period){
		shifts[period] = true;
	}

	public void delShift(int period){
		shifts[period] = false;
	}

	public boolean getShift(int period){
		return shifts[period];
	}



}