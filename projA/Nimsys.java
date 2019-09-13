//Nimsys.java
//Name:Zongru Li 
//StudentID:947539
import java.util.Scanner;
public class Nimsys {
	public static void display(int Number){//display remaining stones
		System.out.print(Number + " stones left:");
		for (int i = 0;i < Number;i++){
			System.out.print(" *");
		}
		System.out.println();
	}	
	public static void main(String[] args){
		String again = "Y";
		int Number,upperBound,removalNumber;
		System.out.println("Welcome to Nim");
		System.out.println();
		//intialise information
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter Player 1's name:");
		NimPlayer Player1 = new NimPlayer(s.nextLine());
		System.out.println();
		System.out.println("Please enter Player 2's name:");
		NimPlayer Player2 = new NimPlayer(s.nextLine());
		System.out.println();
		while (true){
			//initialise conditions
			System.out.println("Please enter upper bound of stone removal:");
			upperBound = s.nextInt();
			System.out.println();
			System.out.println("Please enter initial number of stones:");
			Number = s.nextInt();
			System.out.println();
			display(Number);
			//game start
			while (true){
				Player1.Turn();
				removalNumber = Player1.removeStone(s.nextInt());
				Number = Number - removalNumber;
				if (Player2.WinOrNot(Number)){//judge win or not
					break;		
				}					
				display(Number);
				Player2.Turn();
				removalNumber = Player2.removeStone(s.nextInt());
				Number = Number - removalNumber;
				if (Player1.WinOrNot(Number)){//judge win or not
					break;			
				}
				display(Number);
			}
			System.out.println("Do you want to play again (Y/N):");//judge the game whether over
			again = s.next();
			if (again.equals("N")){
				break;
			}
		}	
	}
}