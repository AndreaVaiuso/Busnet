package busnet.entity;

public class Substitution {
	private String busId;
	private String lineId;
	private int period;
	private String missingDriver;
	private String subDriver;
	private String shiftDate;
	
	public Substitution() {
		
	}
	
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getMissingDriver() {
		return missingDriver;
	}
	public void setMissingDriver(String missingDriver) {
		this.missingDriver = missingDriver;
	}
	public String getSubDriver() {
		return subDriver;
	}
	public void setSubDriver(String subDriver) {
		this.subDriver = subDriver;
	}

	public String getShiftDate() {
		return shiftDate;
	}

	public void setShiftDate(String shiftDate) {
		this.shiftDate = shiftDate;
	}
	
	
}
