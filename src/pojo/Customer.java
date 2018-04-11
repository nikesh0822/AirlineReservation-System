package pojo;

import java.time.LocalDate;
import java.util.Date;

public class Customer {
    
	public static int counter = 2222;
	private String Lastname;
	private String Firstname;
	private String Address;
	private String City;
	private String StateLine;
	private String Zipcode;
	private String Telephone;
	private String Email;
	private LocalDate AccountCreationDate;
	private String CreditCardNumber;
	private int Rating;
	private String password;
	
	public Customer(String lastname, String firstname, String address, String city, String stateLine, String zipcode,
			String telephone, String email, LocalDate accountCreationDate, String creditCardNumber,
			int rating, String password) {
		super();
		Lastname = lastname;
		Firstname = firstname;
		Address = address;
		City = city;
		StateLine = stateLine;
		Zipcode = zipcode;
		Telephone = telephone;
		Email = email;
		AccountCreationDate = accountCreationDate;
		CreditCardNumber = creditCardNumber;
		Rating = rating;
		this.password = password;
	}
	


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
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	public LocalDate getAccountCreationDate() {
		return AccountCreationDate;
	}
	public void setAccountCreationDate(LocalDate accountCreationDate) {
		AccountCreationDate = accountCreationDate;
	}
	public String getCreditCardNumber() {
		return CreditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		CreditCardNumber = creditCardNumber;
	}
	public int getRating() {
		return Rating;
	}
	public void setRating(int rating) {
		Rating = rating;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [Lastname=" + Lastname + ", Firstname=" + Firstname + ", Address=" + Address + ", City=" + City
				+ ", StateLine=" + StateLine + ", Zipcode=" + Zipcode + ", Telephone=" + Telephone + ", Email=" + Email
				+ ", AccountCreationDate=" + AccountCreationDate
				+ ", CreditCardNumber=" + CreditCardNumber + ", Rating=" + Rating + ", password=" + password + "]";
	}
	
	
}
