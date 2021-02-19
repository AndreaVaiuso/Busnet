package busnet.entity;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private String lineId;
	private int time;
	private List<Integer> timeList = new ArrayList<>();
	private List<Stop> stopList = new ArrayList<>();
	
	public Line() {
	}
	
	public Line(String lineId, int time) {
		setLineId(lineId);
		setTime(time);
	}
	
	public Line(String lineId, int time, List<Integer> timeList, List<Stop> stopList) {
		setLineId(lineId);
		setTime(time);
		this.stopList = stopList;
		this.timeList = timeList;
	}
	
	
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	private int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void addStop(Stop stop, int time) {
		stopList.add(stop);
		timeList.add(time);
	}
	public void removeStop(int i) {
		stopList.remove(i);
		timeList.remove(i);
	}
	public List<Integer> getTimeList() {
		return timeList;
	}
	public int getMinutes(int i) {
		return (getTimeList().get(i)%60);
	}
	public int getHours(int i) {	
		return (int) Math.round(getTimeList().get(i)/60);
	}
	
	public List<Stop> getStopList() {
		return stopList;
	}
	
	public int getMinutes() {
		return (getTime()%60);
	}
	
	public int getHours() {
		return (int) Math.round(getTime()/60);
	}
	
	@Override
	public String toString() {
		return "LineID: " + lineId + " Total time: " + time + timeList.toString() + stopList.toString();
	}

}
