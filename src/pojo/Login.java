package pojo;

public class Login {

	private String Email;
	private String Password;
	private String Role;
	
	
	public Login(String email, String password, String role) {
		super();
		Email = email;
		Password = password;
		Role = role;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	@Override
	public String toString() {
		return "Login [Email=" + Email + ", Password=" + Password + ", Role=" + Role + "]";
	}
	
	
}
