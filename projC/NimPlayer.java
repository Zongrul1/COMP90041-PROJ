
import java.io.Serializable;
import java.text.DecimalFormat;  
//Zongru Li
//947539

public abstract class NimPlayer implements Comparable<NimPlayer>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String givenName;
	private String familyName;
	private int NumberGamesPlayed;
	private int NumberGamesWon;
	private double rate;
	public NimPlayer(String userName,String familyName,String givenName) {
		this.userName = userName;
		this.givenName = givenName;
		this.familyName = familyName;
		this.NumberGamesPlayed = 0;
		this.NumberGamesWon = 0;
	}
	
	public NimPlayer() {
		// TODO Auto-generated constructor stub
	}
	//getters
	public String getUserName() {
		return userName;
	}
	
	public String getGivenName() {
		return givenName;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public int getNumberGamesPlayed() {
		return NumberGamesPlayed;
	}
	
	public int getNumberGamesWon() {
		return NumberGamesWon;
	}
	
	public double getRate() {
		if(NumberGamesPlayed == 0) {
			rate = 0;
		}
		else {
		rate = (double)NumberGamesWon/(double)NumberGamesPlayed;
		}
		rate = rate * 100;		
		return rate;
	}
	//setters
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public void setNumberGamesPlayed(int numberGamesPlayed) {
		NumberGamesPlayed = numberGamesPlayed;
	}
	
	public void setNumberGamesWon(int numberGamesWon) {
		NumberGamesWon = numberGamesWon;
	}
	//rankings
	public String ratetoString() {
			DecimalFormat df = new DecimalFormat("######0");   
			return df.format(rate) + "%";
	}
	//display
	@Override
	public String toString() {
		return userName + "," + givenName + "," + familyName
				+ "," + NumberGamesPlayed + " games," + NumberGamesWon + " wins";
	}
	//sort
	@Override
	public int compareTo(NimPlayer p) {
		// TODO Auto-generated method stub
		return this.getUserName().compareTo(p.getUserName());
	}
	//edit
	public void editPlayer(String givenName,String familyName) {
		this.givenName = givenName;
		this.familyName = familyName;
	}
	//game
	public int removeStone(NimGame g) {
		int i = g.getS().nextInt();
		return i;
	}
	//advanced game
	abstract public String advancedRemove(advancedNimGame g);
}
