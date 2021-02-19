package busnet.entity;

public class LineStop {
	private String stopId;
	private String lineId;
	private double timeToArrive;
	
	public LineStop() {
		
	}
	
	public LineStop(String stopId, String lineId, double timeToArrive) {
		setStopId(stopId);
		setLineId(lineId);
		setTimeToArrive(timeToArrive);
	}
	public String getStopId() {
		return stopId;
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public double getTimeToArrive() {
		return timeToArrive;
	}
	public void setTimeToArrive(double timeToArrive) {
		this.timeToArrive = timeToArrive;
	}
}
