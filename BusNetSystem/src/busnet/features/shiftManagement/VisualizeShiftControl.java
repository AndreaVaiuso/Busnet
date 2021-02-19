package busnet.features.shiftManagement;

import busnet.Application;
import busnet.dbmsManagement.DBInterface;

public class VisualizeShiftControl {
	
	private MyShiftWindow myShiftWindow;
	
	public VisualizeShiftControl(String emp) {
		try {
			String [][] myShift = DBInterface.rtrvMyShift(emp);
			myShiftWindow = new MyShiftWindow(myShift);
			myShiftWindow.setVisible(true);
		} catch (Exception e) {
			Application.showDbError(e);
		}
		
	}
}
