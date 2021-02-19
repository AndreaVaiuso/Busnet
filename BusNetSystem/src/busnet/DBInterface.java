package busnet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import busnet.entity.Bus;
import busnet.entity.Employee;
import busnet.entity.Line;
import busnet.entity.Ride;
import busnet.entity.RideList;
import busnet.entity.ShiftDefaultList;
import busnet.entity.ShiftTemporaryList;
import busnet.entity.Stop;
import busnet.entity.Substitution;

public class DBInterface {

	private static final String myUrl = "jdbc:mysql://localhost:3306/busnet?autoReconnect=true&useSSL=false";
	private static final String myDriver = "com.mysql.jdbc.Driver";
	private static final String usr = "root";
	private static final String psw = "0208";
	private static final String psw2 = "CadillacCTS1#";


	public static ArrayList<Line> rtrvLineList() throws Exception {
		ArrayList<Line> lineList = new ArrayList<>();
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT line_id, MINUTE(ride_time), HOUR(ride_time) FROM busnet.line";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
		{
			Line l = new Line();
			l.setLineId(rs.getString("line_id"));	
			String t = rs.getString("MINUTE(ride_time)");
			String t2 = rs.getString("HOUR(ride_time)");
			int min = Integer.parseInt(t);
			int h = Integer.parseInt(t2);
			l.setTime(min+(h*60));
			lineList.add(l);
		}
		st.close();
		return lineList;
	}

	public static ArrayList<Stop> rtrvStopList() throws Exception {
		ArrayList<Stop> stopList = new ArrayList<>();
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT * FROM busnet.stop";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
		{
			Stop s = new Stop();
			s.setStopId(rs.getString("stop_id"));
			s.setStopName(rs.getString("stop_name"));
			stopList.add(s);
		}
		st.close();
		return stopList;
	}
	
	public static ArrayList<Bus> rtrvBusList() throws Exception {
		ArrayList<Bus> busList = new ArrayList<>();
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT * FROM busnet.bus";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
		{
			Bus bus = new Bus(rs.getString("bus_id"),rs.getString("type"),rs.getString("model"),rs.getBoolean("active"));
			busList.add(bus);
		}
		st.close();
		return busList;
	}

	public static ArrayList<Employee> rtrvEmployeeList() throws Exception {
		ArrayList<Employee> empList = new ArrayList<>();
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT * FROM busnet.employee";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
		{
			Employee emp = new Employee();
			emp.setCf(rs.getString("employee_cf"));
			emp.setName(rs.getString("employee_name"));
			emp.setSurname(rs.getString("employee_surname"));
			emp.setRole(rs.getString("role"));
			empList.add(emp);

		}
		st.close();
		return empList;
	}
	
	

	public static boolean chkConnection() {
		try {
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, usr, psw);
			conn.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}

	}
	
	public static Line rtrvLineData(String lineID) throws Exception {
		Line l = new Line();
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT line_id, MINUTE(ride_time), HOUR(ride_time) FROM busnet.line WHERE line_id = '"+lineID+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
		{
			l.setLineId(rs.getString("line_id"));	
			String t = rs.getString("MINUTE(ride_time)");
			String t2 = rs.getString("HOUR(ride_time)");
			int min = Integer.parseInt(t);
			int h = Integer.parseInt(t2);
			l.setTime(min+(h*60));
		}
		st.close();
		String query2 = "SELECT stop_id, stop_name, MINUTE(time_to_arrive), HOUR(time_to_arrive) FROM line_stop ls, stop s WHERE ls.FK_stop_id=s.stop_id AND FK_line_id='"+lineID+"' ORDER BY ord asc ";
		Statement st2 = conn.createStatement();
		ResultSet rs2 = st2.executeQuery(query2);
		while (rs2.next())
		{
			Stop s = new Stop();
			s.setStopId(rs2.getString("stop_id"));
			s.setStopName(rs2.getString("stop_name"));
			int min = (Integer.parseInt(rs2.getString("MINUTE(time_to_arrive)")));
			int h = (Integer.parseInt(rs2.getString("HOUR(time_to_arrive)")));
			l.addStop(s, min+(h*60));
		}
		st2.close();
		return l;
		
	}

	public static Employee rtrvEmployeeData(String cf) throws Exception {
		Employee e = new Employee();
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT * FROM busnet.employee WHERE employee_cf='"+cf+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
		{
			e.setCf(rs.getString("employee_cf"));
			e.setName(rs.getString("employee_name"));
			e.setSurname(rs.getString("employee_surname"));
			e.setRole(rs.getString("role"));
			e.setBirthday(rs.getString("birthday"));
			e.setBirthplace(rs.getString("birthplace"));
			e.setHiring_date(rs.getString("hiring_date"));
			e.setSalary(Double.parseDouble(rs.getString("salary")));
			e.setEmail(rs.getString("e_mail"));
			e.setPermission(Short.parseShort(rs.getString("permission")));
		}
		st.close();
		return e;

	}
	
	public static Bus rtrvBusData(String busID) throws Exception {
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT * FROM busnet.bus WHERE bus_id='"+busID+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		Bus b = null;
		while (rs.next())
		{
			b = new Bus(rs.getString("bus_id"),rs.getString("type"),rs.getString("model"),rs.getBoolean("active"));

		}
		st.close();
		return b;
	}

	public static void updateEmployeePassword(Employee emp, String newPassword) throws SQLException, ClassNotFoundException {
		Class.forName(myDriver);
		String pswMd5 = Application.getMD5(newPassword);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "UPDATE Employee SET password='"+pswMd5+"', temporary_password_flag=false WHERE employee_cf='"+emp.getCf()+"'";
		st.executeUpdate(query);
		st.close();
	}

	public static void updateLineData(Line line)  throws com.mysql.jdbc.MysqlDataTruncation, ClassNotFoundException, SQLException,  MySQLIntegrityConstraintViolationException{
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();

		String query = "START TRANSACTION\n";
		st.addBatch(query);
		query = "UPDATE line SET  ride_time='"+line.getHours()+":"+line.getMinutes()+":00' WHERE line_id = '" + line.getLineId() + "'\n";
		st.addBatch(query);
		query = "DELETE FROM line_stop\r\n" + 
				"WHERE FK_line_id='"+line.getLineId()+"'";
		st.addBatch(query);
		for (int i = 0; i < line.getStopList().size(); i++) {
			query = "INSERT INTO line_stop VALUES ('" + line.getStopList().get(i).getStopId() +"', '" + line.getLineId() + "', '"+line.getHours(i)+":" + line.getMinutes(i) + ":00', "+i+")\n";
			st.addBatch(query);
		}
		query = "COMMIT";
		st.addBatch(query);
		st.executeBatch();
	}

	public static void resetEmployeePassword(Employee emp, String newPassword) throws SQLException, ClassNotFoundException {
		Class.forName(myDriver);
		String pswMd5 = Application.getMD5(newPassword);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "UPDATE Employee SET password='"+pswMd5+"', temporary_password_flag=true WHERE employee_cf='"+emp.getCf()+"'";
		st.executeUpdate(query);
		st.close();
	}

	public static void saveLineData(Line line)  throws com.mysql.jdbc.MysqlDataTruncation, ClassNotFoundException, SQLException{
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "START TRANSACTION\n ";
		st.addBatch(query);
		query = "INSERT INTO line VALUES ('"+line.getLineId()+"', '"+line.getHours()+":"+line.getMinutes()+":00')\n";
		st.addBatch(query);
		for (int i = 0; i < line.getStopList().size(); i++) {
			query = "INSERT INTO line_stop VALUES ('" + line.getStopList().get(i).getStopId() +"', '" + line.getLineId() + "', '"+line.getHours(i)+":" + line.getMinutes(i) + ":00', "+i+")\n";
			st.addBatch(query);
		}
		query = "COMMIT";
		st.addBatch(query);
		st.executeBatch();
	}

	public static void saveStopData(Stop stop)  throws com.mysql.jdbc.MysqlDataTruncation, ClassNotFoundException, SQLException{
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "INSERT INTO stop VALUES ('"+stop.getStopId()+"', '"+stop.getStopName()+"')";
		st.executeUpdate(query);
	}

	public static void saveEmployeeData(Employee emp, String password) throws com.mysql.jdbc.MysqlDataTruncation, ClassNotFoundException, SQLException{
		String pswr = Application.getMD5(password);
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "INSERT INTO Employee VALUES ('"+emp.getCf()+"', '"+emp.getName()+"', '"+emp.getSurname()+"', '"+emp.getBirthplace()+"', '"+emp.getBirthday()+"', '"+emp.getHiring_date()+"', "+emp.getSalary()+", '"+emp.getRole()+"', "+emp.getPermission()+", '"+emp.getEmail()+"', '"+pswr+"', 1)";
		st.executeUpdate(query);
		st.close();
	}

	public static void saveEmployeeData(Employee emp) throws SQLException, ClassNotFoundException {
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "UPDATE Employee SET \n employee_name='"+emp.getName()+"',\n employee_surname='"+emp.getSurname()+"',\n birthplace='"+emp.getBirthplace()+"',\n birthday='"+emp.getBirthday()+"',\n hiring_date='"+emp.getHiring_date()+"',\n salary="+emp.getSalary()+",\n role='"+emp.getRole()+"',\n permission="+emp.getPermission()+"\n WHERE employee_cf='"+emp.getCf()+"'";
		st.executeUpdate(query);
		st.close();

	}

	public static void saveBusData(Bus bus)  throws MySQLSyntaxErrorException, com.mysql.jdbc.MysqlDataTruncation, ClassNotFoundException, SQLException{
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "INSERT INTO bus VALUES ('"+ bus.getBusID()+"', '"+bus.getModel()+"', "+bus.isActive()+", '"+bus.getType()+"')";
		st.executeUpdate(query);
		st.close();
	}
	
	public static void changeBusStatus(String busId, boolean active) throws com.mysql.jdbc.MysqlDataTruncation, ClassNotFoundException, SQLException {
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "UPDATE bus SET active="+active+" WHERE bus.bus_id='"+busId+"'";
		st.executeUpdate(query);
		st.close();
	}

	public static void removeBus (String busId) throws SQLException, ClassNotFoundException {
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "DELETE FROM bus\r\n" + 
				"WHERE bus_id='"+busId+"';";
		st.executeUpdate(query);
		st.close();
	}

	public static void removeStop(String stopId) throws SQLException, ClassNotFoundException, MySQLIntegrityConstraintViolationException {
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "DELETE FROM Stop\r\n" + 
				"WHERE stop_id='"+stopId+"';";
		st.executeUpdate(query);
		st.close();
	}
	
	public static void removeLine (String lineId) throws SQLException, ClassNotFoundException {
	    Class.forName(myDriver);
	    Connection conn = DriverManager.getConnection(myUrl, usr, psw);
	    Statement st = conn.createStatement();
	    String query = "DELETE FROM line\r\n" + 
	        "WHERE line_id='"+lineId+"';";
	    st.executeUpdate(query);
	    st.close();
	  }
	
	public static void removeEmployee(String cf) throws Exception {
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		Statement st = conn.createStatement();
		String query = "DELETE FROM Employee\r\n" + 
				"WHERE employee_cf='"+cf+"';";
		st.executeUpdate(query);
		st.close();
	}

	public static boolean chkEmployeeCredentials(String user, String password) throws NullPointerException, ClassNotFoundException, SQLException {
		String pasw = Application.getMD5(password);
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT * FROM busnet.employee WHERE employee_cf='"+user+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		String usern = null;
		String psx = null;
		while(rs.next()) {
			usern = rs.getString("employee_cf");
			psx = rs.getString("password");
		}
		st.close();
		if(usern.equalsIgnoreCase(user) && psx.equals(pasw)) {
			return true;
		}
		else return false;
	}

	public static boolean chkTemporaryPsw(String user) throws Exception {
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT * FROM busnet.employee WHERE employee_cf='"+user+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean bol = false;
		while(rs.next()) {
			bol = rs.getBoolean("temporary_password_flag");
		}
		st.close();
		return bol;
	}
	
	
	public static ShiftDefaultList rtrvShiftDefaultList(String employeeId) throws Exception {
        try {
        	ShiftDefaultList sdList = new ShiftDefaultList(employeeId);
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, usr, psw);
            String query = "SELECT shift_day, period FROM busnet.shift_default WHERE FK_employee_cf = '" + employeeId + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                sdList.setShift(Integer.parseInt(rs.getString("shift_day")), Integer.parseInt(rs.getString("period")));
            }
            st.close();
            return sdList;
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
        	e.printStackTrace();
        	return new ShiftDefaultList(employeeId);
        }
    }
	
	public static ArrayList<ShiftTemporaryList> rtrvShiftTempList(String employeeId) throws Exception {
        try {
         ArrayList<ShiftTemporaryList> stList = new ArrayList<ShiftTemporaryList>();
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, usr, psw);
            String query = "SELECT * FROM busnet.shift_temporary WHERE FK_employee_cf = '" + employeeId + "' AND shift_date>=CURDATE() ORDER BY shift_date asc";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            String previousDate = "";
            ShiftTemporaryList stl = null;
            while (rs.next())
            {
             String date = rs.getString("shift_date");
             if(date.compareTo(previousDate)!=0) {
              stl = new ShiftTemporaryList(employeeId, date);
              int period = rs.getInt("period");
              if (period != ShiftTemporaryList.periodsNum) {
                  stl.setShift(period);
              }
               stList.add(stl);
             } else {
              stList.get(stList.size()-1).setShift(rs.getInt("period"));
             }
             previousDate = date;
            }
            st.close();
            return stList;
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
         e.printStackTrace();
         return new ArrayList<ShiftTemporaryList>();
        }
    }
	
	public static ShiftTemporaryList rtrvShiftTempListOnDate(String empId, java.util.Date date) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		day -=2;
		if(day==-1) {
			day = 6;
		}
		ShiftDefaultList sdl = DBInterface.rtrvShiftDefaultList(empId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ShiftTemporaryList stl = new ShiftTemporaryList(empId,df.format(date));
		
		for(int i=0;i<6;i++) {
			if(sdl.getShift(day, i)) stl.setShift(i);
		}
		return stl;
	}
	
    
    public static void saveDefaultScheduleData(ShiftDefaultList oldS, ShiftDefaultList newS)  throws com.mysql.jdbc.MysqlDataTruncation, ClassNotFoundException, SQLException{
        Class.forName(myDriver);
        Connection conn = DriverManager.getConnection(myUrl, usr, psw);
        Statement st = conn.createStatement();
        String query = "START TRANSACTION\n ";
        st.addBatch(query);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < ShiftDefaultList.periodsNum; j++) {
                if (oldS.getShift(i, j) != newS.getShift(i, j)) {
                    if (oldS.getShift(i, j)) {
                        query = "DELETE FROM shift_default\r\n" + 
                                " WHERE FK_employee_cf='" + newS.getEmployeeId() + "'"
                                        + " AND shift_day = " + i 
                                        + " AND period = " + j;
                    }
                    else {
                        query = "INSERT INTO shift_default VALUES ('"+ j +"', '"+ i + "', '" + newS.getEmployeeId() + "')";
                    };
                    st.addBatch(query);
                }
            }
        }
        
        query = "COMMIT";
        st.addBatch(query);
        st.executeBatch();
    }
    
    public static void saveTempShiftData(ShiftTemporaryList stList)  throws ParseException, com.mysql.jdbc.MysqlDataTruncation, ClassNotFoundException, SQLException{
    	String date = stList.getDate();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date d = df.parse(date);
    	Calendar c = Calendar.getInstance();
    	Date today = c.getTime();
    	
    	c.setTime(d);
		int day = c.get(Calendar.DAY_OF_WEEK);
		day -=2;
		if(day==-1) {
			day = 6;
		}
    	
    	if(d.compareTo(today)>=0) {
    		boolean isFree = true;
        	Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, usr, psw);
            Statement st = conn.createStatement();
            String query = "START TRANSACTION\n ";
            st.addBatch(query);
            for (int i = 0; i < ShiftTemporaryList.periodsNum; i++) {
              if (stList.getShift(i)) {
                query = "INSERT INTO shift_temporary VALUES ('"+ i +"', '"+ stList.getDate() + "', '" + stList.getEmployeeId() + "', "+day+")";
                System.out.println(day);
                st.addBatch(query);
                isFree = false;
                }
            }
            if (isFree) {
            	query = "INSERT INTO shift_temporary VALUES ('"+ ShiftTemporaryList.periodsNum +"', '"+ stList.getDate() + "', '" + stList.getEmployeeId() + "', "+day+")";
            	st.addBatch(query);
            }
            
            query = "COMMIT";
            st.addBatch(query);
            st.executeBatch();
    	} else {
    		throw new SQLException("Devi inserire una data che sia oggi, o nel futuro");
    	}
    }


    public static void rmvTempShiftData(ShiftTemporaryList stList)  throws com.mysql.jdbc.MysqlDataTruncation, ClassNotFoundException, SQLException{
    	Class.forName(myDriver);
    	Connection conn = DriverManager.getConnection(myUrl, usr, psw);
    	Statement st = conn.createStatement();
    	String query = "START TRANSACTION\n ";
    	st.addBatch(query);

    	query = "DELETE FROM shift_temporary\r\n" + 
    			" WHERE FK_employee_cf='" + stList.getEmployeeId() + "'"
    			+ " AND shift_date = '" + stList.getDate() + "'";
    	st.addBatch(query);

    	query = "COMMIT";
    	st.addBatch(query);
    	st.executeBatch();
    }

    public static ShiftDefaultList rtrvEmployeeShiftList(String employeeId) throws Exception {
    	try {
    		ShiftDefaultList sdList = new ShiftDefaultList(employeeId);
    		Class.forName(myDriver);
    		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
    		String query = "SELECT shift_day, period FROM busnet.shift_default WHERE FK_employee_cf = '" + employeeId + "'";
    		Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(query);
    		while (rs.next())
    		{
    			sdList.setShift(Integer.parseInt(rs.getString("shift_day")), Integer.parseInt(rs.getString("period")));
    		}
    		st.close();
    		return sdList;
    	} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
    		e.printStackTrace();
    		return new ShiftDefaultList(employeeId);
    	}
    }

    public static RideList rtrvRideList(String lineId, short day) throws Exception {
    	try {
    		RideList rideList = new RideList(lineId, day);
    		Class.forName(myDriver);
    		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
    		String query = "SELECT MINUTE(start_time), FK_period, FK_employee_cf, FK_bus_id, FK_line_id FROM busnet.ride WHERE FK_line_id = '"+lineId+"' AND FK_shift_day="+day+" ORDER BY FK_bus_id asc";
    		Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(query);
    		Ride ride;
    		String previousBus = "";
    		while (rs.next()){
    			String currentBus = rs.getString("FK_bus_id");
    			if(!currentBus.equals(previousBus)) {
    				ride = new Ride();
    				ride.setStartTime(rs.getString("MINUTE(start_time)"));
    				rideList.pushRide(currentBus, ride);
    			}
    			rideList.peekRide(currentBus).setEmployeeShift(rs.getInt("FK_period"), rs.getString("FK_employee_cf"));
    			previousBus = currentBus;
    		}
    		st.close();
    		return rideList;
    	} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
    		e.printStackTrace();
    		return new RideList(lineId, day);
    	}
    }

    public static ArrayList<ArrayList<Employee>> rtrvFreeEmployeeOnDay(int day) throws Exception{
    	try {
    		ArrayList<ArrayList<Employee>> employeeFreeShiftList =  new ArrayList<>(ShiftDefaultList.periodsNum);
    		for(int i = 0; i < ShiftDefaultList.periodsNum; i++) {
    			employeeFreeShiftList.add(new ArrayList<Employee>());
    		}
    		Class.forName(myDriver);
    		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
    		String query = "SELECT * FROM busnet.shift_default, busnet.employee WHERE FK_employee_cf = employee_cf AND role = '"+Application.driverKeyString+"' AND shift_day = "+day+" AND (period, shift_day, FK_employee_cf) NOT IN( SELECT FK_period, FK_shift_day, FK_employee_cf FROM ride WHERE FK_shift_day = "+day+")";
    		Statement st = conn.createStatement();
    		ResultSet rs = st.executeQuery(query);
    		while (rs.next()){
    			Employee emp = new Employee();
    			emp.setCf(rs.getString("employee_cf"));
    			emp.setName(rs.getString("employee_name"));
    			emp.setSurname(rs.getString("employee_surname"));
    			employeeFreeShiftList.get(rs.getInt("period")).add(emp);
    		}
    		st.close();
    		return employeeFreeShiftList;
    	} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
    		e.printStackTrace();
    		return new ArrayList<ArrayList<Employee>>();
    	}
    }
    
    public static void saveRideList(RideList rlist) throws Exception{
    	Class.forName(myDriver);
    	Connection conn = DriverManager.getConnection(myUrl, usr, psw);
    	Statement st = conn.createStatement();
    	String query = "START TRANSACTION\n ";
    	st.addBatch(query);
    	query = "DELETE FROM ride WHERE FK_line_id = '"+rlist.getLineId()+"' AND FK_shift_day = "+rlist.getDay()+"";
    	st.addBatch(query);
    	for (int i = 0; i < rlist.getRidesSize(); i++) {
    		for(int j = 0; j < ShiftDefaultList.periodsNum; j++) {
    			if(rlist.peekRide(i).getEmployeeShift(j)!=null) {
    				query = "INSERT INTO ride (FK_period, FK_shift_day, FK_employee_cf, FK_bus_id, FK_line_id, start_time) VALUES ("+j+", "+rlist.getDay()+",'"+rlist.peekRide(i).getEmployeeShift(j)+"', '"+rlist.peekBus(i)+"', '"+rlist.getLineId()+"', '00:"+rlist.peekRide(i).getStartTime()+":00')";
    				st.addBatch(query);
    			}
    		}
    	}
    	query = "COMMIT";
    	st.addBatch(query);
    	st.executeBatch();
    }
    
	public static ArrayList<Bus> rtrvFreeBusList(int day) throws Exception {
		ArrayList<Bus> busList = new ArrayList<>();
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT * FROM busnet.bus WHERE active = true AND bus_id NOT IN (SELECT FK_bus_id FROM ride WHERE FK_shift_day = "+day+")";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
		{
			Bus bus = new Bus(rs.getString("bus_id"),rs.getString("type"),rs.getString("model"),rs.getBoolean("active"));
			busList.add(bus);
		}
		st.close();
		return busList;
	}
	
	public static ArrayList<Substitution> rtrvSubstitutionList() throws Exception{
		ArrayList<Substitution> subList = new ArrayList<>();
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, usr, psw);
		String query = "SELECT distinct R.FK_period, R.FK_shift_day, R.FK_employee_cf, R.FK_line_id, R.FK_bus_id, ST1.shift_date, S.FK_employee_cf_2, S.FK_period_2, S.FK_shift_date_2\r\n" + 
				"FROM busnet.ride R JOIN busnet.shift_temporary ST1 ON R.FK_shift_day = ST1.shift_temporary_day\r\n" + 
				"LEFT JOIN busnet.substitution S ON R.FK_period = S.FK_period_1 AND R.FK_employee_cf = S.FK_employee_cf_1 AND ST1.shift_date = S.FK_shift_date_1\r\n" + 
				"\r\n" + 
				"WHERE (R.FK_period, R.FK_employee_cf) NOT IN (\r\n" + 
				"SELECT ST2.period, ST2.FK_employee_cf \r\n" + 
				"FROM busnet.shift_temporary ST2 \r\n" + 
				"WHERE ST2.FK_employee_cf = R.FK_employee_cf \r\n" + 
				"AND ST2.shift_date=ST1.shift_date);\r\n" + 
				"";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next())
		{
			Substitution sub = new Substitution();
			sub.setBusId(rs.getString("R.FK_bus_id"));
			sub.setLineId(rs.getString("R.FK_line_id"));
			sub.setMissingDriver(rs.getString("R.FK_employee_cf"));
			sub.setPeriod(rs.getInt("R.FK_period"));
			sub.setShiftDate(rs.getString("ST1.shift_date"));
			try {
				sub.setSubDriver(rs.getString("S.FK_employee_cf_2"));
			} catch (Exception e) {
				sub.setSubDriver(null);
			}
			subList.add(sub);
		}
		st.close();
		return subList;
	}
	
	public static void saveSubstitutionData(Substitution sub) throws Exception{
		
	}
}







