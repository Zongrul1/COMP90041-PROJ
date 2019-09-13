
//Zongru Li
//947539
public class NimHumanPlayer extends NimPlayer implements Comparable<NimPlayer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NimHumanPlayer() {
		super();
	}
	
	public NimHumanPlayer(String userName,String familyName,String givenName) {		
		super(userName,familyName,givenName);
	}
	//game
	public int removeStone(NimGame g) {
		int i = g.getS().nextInt();
		return i;
	}
	//advanced game
	public String advancedRemove(advancedNimGame g) {
		String Move = g.getS().nextLine();
		return Move;
	}
}
