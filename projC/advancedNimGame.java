
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
//Zongru Li
//947539
public class advancedNimGame {
	private int InitStone;
	private int currentStone;
	private NimPlayer Player1;
	private NimPlayer Player2;
	private Scanner S;
	//private boolean[] c;
	private boolean[] c;
	private String LastMove = "0 0";
	public advancedNimGame(int currentStone,NimPlayer Player1,NimPlayer Player2,Scanner S)
	{
		this.InitStone = currentStone;
		this.currentStone = currentStone;
		this.Player1 = Player1;
		this.Player2 = Player2;
		this.S = S;
		c = new boolean[InitStone];//init
		for(int i = 0;i < InitStone;i++) {
			c[i] = true;
		}
	}
	//display
	private void advanceddisplay(int Number,boolean[] c){
		System.out.print(currentStone + " stones left:");
		for(int i = 0;i < Number;i++)
		{
			if(c[i] == true) {
				System.out.print(" <" + (i + 1) + ",*>");
			}
			else {
				System.out.print(" <" + (i + 1) + ",x>");
			}
		}
		System.out.println();
	}
	//init
	private void advancedInitdisplay(int Number,boolean[] c){
		System.out.println();
		System.out.println("Initial stone count: " + InitStone);
		System.out.print("Stones display:");
		for(int i = 0;i < Number;i++)
		{
			if(c[i] == true) {
				System.out.print(" <" + (i + 1) + ",*>");
			}
			else {
				System.out.print(" <" + (i + 1) + ",x>");
			}
		}
		System.out.println();
		System.out.println("Player 1: " + Player1.getGivenName() + " " + Player1.getFamilyName());
		System.out.println("Player 2: " + Player2.getGivenName() + " " + Player2.getFamilyName());
		System.out.println();
		Player1.setNumberGamesPlayed(Player1.getNumberGamesPlayed()+1);
		Player2.setNumberGamesPlayed(Player2.getNumberGamesPlayed()+1);	
	}
	//judge
	public String adjudge(String l,String s,boolean[] c,NimPlayer Player) {
		boolean flag = true;
		while(flag) {
			try {
				StringTokenizer st = new StringTokenizer(s);
				int n1 = Integer.parseInt(st.nextToken()) - 1;
				int n2 = Integer.parseInt(st.nextToken());
				if(n2 != 1&&n2 != 2) {
					throw new InvalidInputException("Invalid move.");
				}
				else if(n1 > InitStone) {
					throw new InvalidInputException("Invalid move.");
				}
				else if(n2 == 1&&c[n1] == false) {
					throw new InvalidInputException("Invalid move.");
				}
				else if(n2 == 1&&c[n1] == true){
					c[n1] = false;
				}
				else if(n2 == 2&&(c[n1] == false||c[n1 + 1] == false)) {
					throw new InvalidInputException("Invalid move.");
				}
				else if(n2 == 2&&(c[n1] == true&&c[n1 + 1] == true)){
					c[n1 + 1] = false;
					c[n1] = false;
				}
				currentStone = currentStone - n2;
				flag = false;
			}
			catch(NoSuchElementException e) {
				System.out.println("Invalid move.");
				reput(Player,c);
				s = Player.advancedRemove(this);
			}
			catch(NullPointerException e) {
				System.out.println("Invalid move.");
				reput(Player,c);
				s = Player.advancedRemove(this);
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid move.");
				reput(Player,c);
				s = Player.advancedRemove(this);
			}
			catch(InvalidInputException e) {
				System.out.println(e.getMessage());
				reput(Player,c);
				s = Player.advancedRemove(this);
			}
		}
		return s;
	}
	//reput
	private void reput(NimPlayer Player,boolean[] c) {
		System.out.println();
		advanceddisplay(InitStone,c);
		System.out.println(Player.getGivenName()+"\'s turn - which to remove?");
		System.out.println();		
	}
	//win or not
	private void advancedwin(NimPlayer p) {
		System.out.println("Game Over");
		System.out.println(p.getGivenName() + " " + p.getFamilyName()+" wins!");
		p.setNumberGamesWon(p.getNumberGamesWon()+1);
		
	}
	//start the ad game
		public void advancedgame() {
			String LastMoveTest;
			advancedInitdisplay(InitStone,c);
			while(true)
			{			
				advanceddisplay(InitStone,c);
				System.out.println(Player1.getGivenName()+"\'s turn - which to remove?");//Player1's turn
				System.out.println();
				LastMoveTest = Player1.advancedRemove(this);
				LastMove = adjudge(LastMove,LastMoveTest,c,Player1);
				if(currentStone == 0)
				{
					advancedwin(Player1);
					break;
				}	
				advanceddisplay(InitStone,c);
				System.out.println(Player2.getGivenName()+"\'s turn - which to remove?");//Player2's turn
				System.out.println();
				LastMoveTest = Player2.advancedRemove(this);
				LastMove = adjudge(LastMove,LastMoveTest,c,Player2);
				if(currentStone == 0)
				{
					advancedwin(Player2);
					break;
				}
			}
		}
		//getters
		public int getInitStone() {
			return InitStone;
		}
		
		public int getCurrentStone() {
			return currentStone;
		}
		
		public NimPlayer getPlayer1() {
			return Player1;
		}
		
		public NimPlayer getPlayer2() {
			return Player2;
		}
		
		public Scanner getS() {
			return S;
		}
		
		public boolean[] getC() {
			return c;
		}
		
		public String getLastMove() {
			return LastMove;
		}

}
