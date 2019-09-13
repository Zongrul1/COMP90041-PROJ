
import java.util.Scanner;
//Zongru Li
//947539
public class NimGame {
	private int InitStone;
	private int currentStone;
	private int upperBound;
	private NimPlayer Player1;
	private NimPlayer Player2;
	private Scanner S;
	//method
	public NimGame(int currentStone,int upperBound,NimPlayer Player1,NimPlayer Player2,Scanner S)
	{
		this.InitStone = currentStone;
		this.currentStone = currentStone;
		this.upperBound = upperBound;
		this.Player1 = Player1;
		this.Player2 = Player2;
		this.S = S;
	}
	
	//show the number of currentStone
	private void display(int Number){
		System.out.print(currentStone+" stones left:");
		for(int i = 0;i < Number;i++)
		{
			System.out.print(" *");
		}
		System.out.println();
	}
	
	//output the information
	private void information() {
		System.out.println();
		System.out.println("Initial stone count: " + currentStone);
		System.out.println("Maximum stone removal: " + upperBound);
		System.out.println("Player 1: " + Player1.getGivenName() + " " + Player1.getFamilyName());
		System.out.println("Player 2: " + Player2.getGivenName() + " " + Player2.getFamilyName());
		System.out.println();
		Player1.setNumberGamesPlayed(Player1.getNumberGamesPlayed()+1);
		Player2.setNumberGamesPlayed(Player2.getNumberGamesPlayed()+1);		
	}
	
	//judge input whether meets requirements
	private int judge(int rS,NimPlayer p) {
		while(rS<=0||rS>upperBound||currentStone-rS<0)
		{
			int validNumber = (upperBound>currentStone)?currentStone:upperBound;
			try {
				String message = "Invalid move. You must remove between 1 and ";
				throw new InvalidInputException(message + validNumber  + " stones.");			
			}
			catch(InvalidInputException e) {			
				System.out.println(e.getMessage());
				System.out.println();
				display(currentStone);
				System.out.println(p.getGivenName()+"\'s turn - remove how many?");
				System.out.println();
				rS = p.removeStone(this);
			}
		}
		return rS;		
	}
	
	//judge win or not
	private void win(NimPlayer p) {
		System.out.println("Game Over");
		System.out.println(p.getGivenName() + " " + p.getFamilyName()+" wins!");
		p.setNumberGamesWon(p.getNumberGamesWon()+1);
		
	}
	
	//start the game
	public void game()
	{
		int removalcurrentStone;
		information();
		display(InitStone);
		//start the game
		while(true)
		{
			System.out.println(Player1.getGivenName()+"\'s turn - remove how many?");//Player1's turn
			System.out.println();
			removalcurrentStone = Player1.removeStone(this);
			currentStone = currentStone - judge(removalcurrentStone,Player1);
			if(currentStone == 0)
			{
				win(Player2);
				break;
			}				
			display(currentStone);
			//Player2's turn
			System.out.println(Player2.getGivenName()+"\'s turn - remove how many?");
			System.out.println();
			removalcurrentStone = Player2.removeStone(this);
			currentStone = currentStone - judge(removalcurrentStone,Player2);
			if(currentStone == 0)//judge who wins the game
			{
				win(Player1);
				break;
			}				
			display(currentStone);
		}
		if(Player1 instanceof NimHumanPlayer||Player2 instanceof NimHumanPlayer) {//AIplayer do not use the keyboard
			String junk = S.nextLine();//clear the \n
		}
	}
	//getters
	public int getCurrentStone() {
		return currentStone;
	}
	
	public int getUpperBound() {
		return upperBound;
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
}
