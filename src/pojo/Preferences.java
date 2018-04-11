package pojo;

public class Preferences {

	private int AccountNumber;
	private String PreferredSeat;
	private String PreferredMeal;
	public int getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getPreferredSeat() {
		return PreferredSeat;
	}
	public void setPreferredSeat(String preferredSeat) {
		PreferredSeat = preferredSeat;
	}
	public String getPreferredMeal() {
		return PreferredMeal;
	}
	public void setPreferredMeal(String preferredMeal) {
		PreferredMeal = preferredMeal;
	}
	@Override
	public String toString() {
		return "Preferences [AccountNumber=" + AccountNumber + ", PreferredSeat=" + PreferredSeat + ", PreferredMeal="
				+ PreferredMeal + "]";
	}
	
	
	
}
