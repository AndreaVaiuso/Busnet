package busnet.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RideList {
	private String lineId;
	private int day;
	private Map<String, Ride> map;
	
	public RideList(String lineId, int day) {
		setLineId(lineId);
		setDay(day);
		map = new TreeMap<>();
	}
	
	public void deleteRide(String busId) {
		map.remove(busId);
	}
	
	public Ride peekRide(String busId) {
		return map.get(busId);
	}
	
	public Ride peekRide(int i) {
		List<Ride> list = new ArrayList<>(map.values());
		return list.get(i);
	}
	
	public void pushRide(String busId, Ride ride) {
		map.put(busId, ride);                
	}
	
	public String peekBus(int i) {
		List<String> list = new ArrayList<>(map.keySet());
		return list.get(i);
	}
	
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public Map<String, Ride> getMap() {
		return map;
	}
	public void setMap(Map<String, Ride> map) {
		this.map = map;
	}
	
	public int getRidesSize() {
		List<Ride> list = new ArrayList<>(map.values());
		return list.size();
	}
	@Override
	public String toString() {
		String s = "LineID: " + lineId + " day: " + day + "\n\n\n";
		List<Ride> list = new ArrayList<>(map.values());
		for(int i=0; i<list.size();i++) {
			s += "Ride " + i + "\nStart time: " + peekRide(i).getStartTime() +"\n";
			for(int j=0;j<ShiftDefaultList.periodsNum;j++) {
				s+=" "+list.get(i).getEmployeeShift(j)+" ";
			}
			s+="\n\n";
		}
		return s;
	}
	
	
}
