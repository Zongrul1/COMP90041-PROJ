import java.util.Scanner;
//Zongru Li
//947539

public class NimGame {
	private int currentStone;
	private int upperBound;
	private NimPlayer Player1;
	private NimPlayer Player2;
	private Scanner S;
	//method
	public NimGame(int currentStone,int upperBound,NimPlayer Player1,NimPlayer Player2,Scanner S)
	{
		this.currentStone = currentStone;
		this.upperBound = upperBound;
		this.Player1 = Player1;
		this.Player2 = Player2;
		this.S = S;
	}
	private void display(int Number){//display method
		for(int i=0;i<Number;i++)
		{
			System.out.print(" *");
		}
		System.out.println();
	}
	private void information() {
		//output the information
		System.out.println();
		System.out.println("Initial stone count: " + currentStone);
		System.out.println("Maximum stone removal: " + upperBound);
		System.out.println("Player 1: " + Player1.getGivenName() + " " + Player1.getFamilyName());
		System.out.println("Player 2: " + Player2.getGivenName() + " " + Player2.getFamilyName());
		Player1.setNumberGamesPlayed(Player1.getNumberGamesPlayed()+1);
		Player2.setNumberGamesPlayed(Player2.getNumberGamesPlayed()+1);		
	}
	private int judge(int removalcurrentStone,NimPlayer p) {
		while(removalcurrentStone<=0||removalcurrentStone>upperBound||currentStone-removalcurrentStone<0)//judge input whether meets requirements
		{
			int validNumber = (upperBound>currentStone)?currentStone:upperBound;
			System.out.println("Invalid move. You must remove between 1 and " + validNumber  + " stones.");
			System.out.println();
			System.out.print(currentStone+" stones left:");
			display(currentStone);
			System.out.println(p.getGivenName()+"\'s turn - remove how many?");
			System.out.println();
			removalcurrentStone = p.removeStone(S.nextInt());
		}
		return removalcurrentStone;		
	}
	private void win(NimPlayer p) {
		System.out.println("Game Over");
		System.out.println(p.getGivenName() + " " + p.getFamilyName()+" wins!");
		p.setNumberGamesWon(p.getNumberGamesWon()+1);
		
	}
	public void game()
	{
		int removalcurrentStone;
		information();
		System.out.println();
		System.out.print(currentStone+" stones left:");
		display(currentStone);
		//start the game
		while(true)
		{
			System.out.println(Player1.getGivenName()+"\'s turn - remove how many?");//Player1's turn
			System.out.println();
			removalcurrentStone = Player1.removeStone(S.nextInt());
			currentStone = currentStone - judge(removalcurrentStone,Player1);
			if(currentStone == 0)
			{
				win(Player2);
				break;
			}				
			System.out.print(currentStone+" stones left:");
			display(currentStone);
			//Player2's turn
			System.out.println(Player2.getGivenName()+"\'s turn - remove how many?");
			System.out.println();
			removalcurrentStone = Player2.removeStone(S.nextInt());
			currentStone = currentStone - judge(removalcurrentStone,Player2);
			if(currentStone == 0)//judge who wins the game
			{
				win(Player1);
				break;
			}				
			System.out.print(currentStone+" stones left:");
			display(currentStone);
		}
		String junk = S.nextLine();//clear the \n
	}
}
