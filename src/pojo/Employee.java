package pojo;

import java.util.Date;

public class Employee {

	
	private String Lastname;
	private String Firstname;
	private String Address;
	private String City;
	private String StateLine;
	private String Zipcode;
	private String Telephone;
	private String SSN;
	private Date StartDate;
	private int Pay;
	private String password;
	private String Email;
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getStateLine() {
		return StateLine;
	}
	public void setStateLine(String stateLine) {
		StateLine = stateLine;
	}
	public String getZipcode() {
		return Zipcode;
	}
	public void setZipcode(String zipcode) {
		Zipcode = zipcode;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public int getPay() {
		return Pay;
	}
	public void setPay(int pay) {
		Pay = pay;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	@Override
	public String toString() {
		return "Employee [Lastname=" + Lastname + ", Firstname=" + Firstname + ", Address=" + Address + ", City=" + City
				+ ", StateLine=" + StateLine + ", Zipcode=" + Zipcode + ", Telephone=" + Telephone + ", SSN=" + SSN
				+ ", StartDate=" + StartDate + ", Pay=" + Pay + ", password=" + password + ", Email=" + Email + "]";
	}
	
	
	
}
