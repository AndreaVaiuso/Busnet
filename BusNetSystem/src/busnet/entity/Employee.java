package busnet.entity;

public class Employee {	
	private String cf;
	private String name;
	private String surname;
	private String iban;
	private String birthday;
	private String hiring_date;
	private double salary;
	private String role;
	private short permission;
	private String email;
	
	public Employee(String cf, String name, String surname, String birthplace, String birthday, String hiring_date,
			double salary, String role, short permission, String email) {
		super();
		this.cf = cf;
		this.name = name;
		this.surname = surname;
		this.iban = birthplace;
		this.birthday = birthday;
		this.hiring_date = hiring_date;
		this.salary = salary;
		this.role = role;
		this.permission = permission;
		this.email = email;
	}
	
	public Employee() {}
	
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getHiring_date() {
		return hiring_date;
	}
	public void setHiring_date(String hirig_date) {
		this.hiring_date = hirig_date;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public short getPermission() {
		return permission;
	}
	public void setPermission(short permission) {
		this.permission = permission;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return getCf() + " | " + getName() + " " + getSurname();
	}
	
	
}
