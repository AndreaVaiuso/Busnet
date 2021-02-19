package busnet.entity;

public class Bus {
	private String busID;
	private String type;
	private String model;
	private boolean active;
	
	public Bus (String busID, String type, String model, boolean active) {
		setBusID(busID);
		setType(type);
		setModel(model);
		setActive(active);
	}
	
	public String getBusID() {
		return busID;
	}
	public void setBusID(String busID) {
		this.busID = busID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return busID + ", " + model + ", " + type;
	}
	
	
}
