package pojo;

public class RevenueSummary {
	private int NumberOfTickets;
	private int Revenue;
	private int month;
	
	public RevenueSummary(int numberOfTickets, int revenue , int month) {
		super();
		NumberOfTickets = numberOfTickets;
		Revenue = revenue;
		this.month = month;
	}

	public int getNumberOfTickets() {
		return NumberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		NumberOfTickets = numberOfTickets;
	}

	public int getRevenue() {
		return Revenue;
	}

	public void setRevenue(int revenue) {
		Revenue = revenue;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}
