package busnet.entity;

public class Stop {
	private String stopId;
	private String stopName;
	
	public Stop() {
		
	}
	
	public Stop(String stopId, String stopName) {
		setStopId(stopId);
		setStopName(stopName);
	}
	public String getStopId() {
		return stopId;
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public String getStopName() {
		return stopName;
	}
	public void setStopName(String stopName) {
		this.stopName = stopName;
	}
	
	@Override
	public String toString() {
		return getStopName() + " | " + getStopId();
	}
}
