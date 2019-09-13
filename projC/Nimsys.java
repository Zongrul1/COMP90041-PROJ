
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
//Zongru Li
//947539
public class Nimsys {
	
	//ascending sort
	private void ascsort(NimPlayer[] p) {
		int i = 0,j = 0,flag = 0;
		double min = p[0].getRate(),minn;
		NimPlayer p0 = null;
		while(i < p.length) {
			j = i + 1;
			flag = i;
			min = p[i].getRate();
			while(j < p.length) {
				minn = p[j].getRate();
				if(minn < min) {
					min = minn;
					flag = j;
				}
				else if(min == minn) {//Ties should be resolved by sorting on usernames alphabetically
					if(p[i].getUserName().compareTo(p[j].getUserName()) > 0){
					flag = j;	
					}
				}
				j++;
			}
			p0 = p[i];
			p[i] = p[flag];
			p[flag] = p0;
			i++;
		}
	}
	
	//descending sort
	private void descsort(NimPlayer[] p) {
		int i = 0,j = 0,flag = 0;
		double max = p[0].getRate(),maxx;
		NimPlayer p0 = null;
		while(i < p.length) {
			j = i + 1;
			flag = i;
			max = p[i].getRate();
			while(j < p.length) {
				maxx = p[j].getRate();
				if(max < maxx) {
					max = maxx;
					flag = j;
				}

				else if(max == maxx) {//Ties should be resolved by sorting on usernames alphabetically
					if(p[i].getUserName().compareTo(p[j].getUserName()) > 0){
						flag = j;	
					}
				}
				j++;
			}
			p0 = p[i];
			p[i] = p[flag];
			p[flag] = p0;
			i++;
		}
	}
	
	//add player
	private boolean addPlayer(NimPlayer[] p,String input){
		NimPlayer p1 = null;//check whether exist
		int i = 0;
		StringTokenizer st = new StringTokenizer(input,",");
		String username = st.nextToken();
		String familyname = st.nextToken();
		String givenname = st.nextToken();
		while(p[i] != null)
		{
			//search whether exist
			if (p[i].getUserName().equals(username)) {
				System.out.println("The player already exists.");
				System.out.println();//because of continue
				p1 = p[i];
				break;
			}
			i++;
		}//judge whether exist;	
		if (p1 != null)
		{
			return false; //leave the loop
		}
		p[i] = new NimHumanPlayer(username,familyname,givenname);//add
		return true;
	}
	
	//add AI
	private boolean addAIPlayer(NimPlayer[] p,String input){
		NimPlayer p1 = null;//check whether exist
		int i = 0;
		StringTokenizer st = new StringTokenizer(input,",");
		String username = st.nextToken();
		String familyname = st.nextToken();
		String givenname = st.nextToken();
		while(p[i] != null)
		{
			//search whether exist
			if (p[i].getUserName().equals(username)) {
				System.out.println("The player already exists.");
				System.out.println();//because of continue
				p1 = p[i];
				break;
			}
			i++;
		}//judge whether exist;	
		if (p1 != null)
		{
			return false; //leave the loop
		}
		p[i] = new NimAIPlayer(username,familyname,givenname);//add
		return true;
	}
	
	//remove player
	private boolean removePlayer(NimPlayer[] p,String username,Scanner s) {
		NimPlayer p1 = null;
		int i = 0;
		if (username == null) {
				System.out.println("Are you sure you want to remove all players? (y/n)");
				String S = s.nextLine();//pitfall of nextLine->enter
				if (S.equals("y")) {
					while(p[i] != null) {
						p[i] = null;
						i++;
					}
				}
				else{
					System.out.println();
					return false;
				}
		}
		else{
			while(p[i] != null)
			{//remove
				if(p[i].getUserName().equals(username)) {
					p1 = p[i];//it is exist
					p[i] = null; 
					break;
				}
				i++;
			}
			if(p1 == null) {
				System.out.println("The player does not exist.");
			}
			else {
				while(p[i+1] != null)
				{//fill the vacancy
					p[i] = p[i+1];
					i++;
				}
				p[i] = null;
			}
		}		
		return true;
	}
	
	//edit player
	private boolean editPlayer(NimPlayer[] p,String input){
		int i = 0;
		NimPlayer p1 = null;
		StringTokenizer st = new StringTokenizer(input,",");
		String username = st.nextToken();
		String familyname = st.nextToken();
		String givenname = st.nextToken();
		while(p[i] != null)
		{//search and set
			if(p[i].getUserName().equals(username)) {
				p[i].setFamilyName(familyname);
				p[i].setGivenName(givenname);
				p1 = p[i];
				break;
			}
			i++;
		}			
		if(p1 == null) {
			System.out.println("The player does not exist.");
		}
		return true;
		
	}
	
	//reset player
	private boolean resetPlayer(NimPlayer[] p,String username,Scanner s) {
		int i = 0;
		if (username == null){
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String S = s.nextLine();
			if (S.equals("y")) {
				//set 0 for the statistic
				while(p[i] != null) {
					p[i].setNumberGamesPlayed(0);
					p[i].setNumberGamesWon(0);
					i++;
				}
			}
			else {
				System.out.println();
				return false;
			}
		}
		else {
			i = 0;
			NimPlayer p1 = null;
			while(p[i] != null)
			{//search and set 0 for the statistic
				if (p[i].getUserName().equals(username)) {
					p[i].setNumberGamesPlayed(0);
					p[i].setNumberGamesWon(0);
					p1 = p[i];
					break;
				}
				i++;
			}
			if(p1 == null) {
				System.out.println("The player does not exist.");
			}
		}		
		return true;
	}
	
	//display
	private void displayPlayer(NimPlayer[] p,String username,Scanner s) {
		int i = 0;
		if (username == null){
			while(p[i] != null) {
				i++;
			}
			NimPlayer[] playerList0 = new NimPlayer[i];
			i = 0;
			while(p[i] != null) {
				playerList0[i] = p[i];
				i++;
				}
			Arrays.sort(playerList0);
			i = 0;
			while(i < playerList0.length)//display elements one by one
				{
					System.out.println(playerList0[i].toString());
					i++;
				}
			}
		else {
			NimPlayer p1 = null;
			while(p[i] != null)
			{//search the element and display
				if (p[i].getUserName().equals(username)) {
					System.out.println(p[i].toString());
					p1 = p[i];
					break;
				}
				i++;
			}
			if(p1 == null) {
				System.out.println("The player does not exist.");
			}
		}		
		
	}
	
	//rankings
	private void rankings(NimPlayer[] p,String command,Scanner s) {
		int i = 0;
		while(p[i] != null) {
			i++;
		}
		NimPlayer[] playerList0 = new NimPlayer[i];//to avoid changing the original order
		i = 0;
		while(p[i] != null) {
			playerList0[i] = p[i];
			i++;
			}
		Arrays.sort(playerList0);//sorted by alphabet
		if (command == null||command.equals("desc")) {
			descsort(playerList0);
		}
		else if (command.equals("asc")) {
			ascsort(playerList0);
		}
		i = 0;
		while(i < playerList0.length && i < 10)
		{
			System.out.printf("%-5s| ",playerList0[i].ratetoString());
			System.out.printf("%02d games | ",playerList0[i].getNumberGamesPlayed()); 
			System.out.print(playerList0[i].getGivenName());
			System.out.println(" " + playerList0[i].getFamilyName());
			i++;
		}
		
	}
	
	//startgame
	private void startGame(NimPlayer[] p,String input,Scanner s){
		//initialize 2 players
		NimPlayer p1 = null;
		NimPlayer p2 = null;
		StringTokenizer st = new StringTokenizer(input,",");
		String currentStone = st.nextToken();
		String upperBound = st.nextToken();
		String username1 = st.nextToken();
		String username2 = st.nextToken();
		int i = 0;
		//search players exist or not;
		while(p[i] != null) {
			if (p[i].getUserName().equals(username1)) {
				p1 = p[i];
			}
			if (p[i].getUserName().equals(username2)) {
				p2 = p[i];
			}
			i++;
		}
		if(p1 != null && p2 != null) {
			NimGame game = new NimGame(Integer.parseInt(currentStone),Integer.parseInt(upperBound),p1,p2,s);
			game.game();//start game
		}
		else {
			System.out.println("One of the players does not exist.");
		}		
	}
	
	//startadvancegame
	private void startadvanceGame(NimPlayer[] p,String input,Scanner s){
		//initialize 2 players
		NimPlayer p1 = null;
		NimPlayer p2 = null;
		StringTokenizer st = new StringTokenizer(input,",");
		String currentStone = st.nextToken();
		String username1 = st.nextToken();
		String username2 = st.nextToken();
		int i = 0;
		//search players exist or not;
		while(p[i] != null) {
			if (p[i].getUserName().equals(username1)) {
				p1 = p[i];
			}
			if (p[i].getUserName().equals(username2)) {
				p2 = p[i];
			}
			i++;
		}
		if(p1 != null && p2 != null) {
			advancedNimGame game = new advancedNimGame(Integer.parseInt(currentStone),p1,p2,s);
			game.advancedgame();//start game
		}
		else {
			System.out.println("One of the players does not exist.");
		}		
	}
	
	//save the data
    public static void writeObjectToFile(NimPlayer[] p)
    {
        File file =new File("players.dat");
        try{
            file.delete();
        }catch(Exception e){
            e.printStackTrace();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            int i = 0;
            while(p[i] != null) {
            	objOut.writeObject(p[i]);//write object into the .dat
            	i++;
            }
            objOut.flush();
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //load the data
    public static void readObjectFromFile(NimPlayer[] p)
    {
        File file =new File("players.dat");
        if(file.exists()) {
	        FileInputStream in;
	        try {
	            in = new FileInputStream(file);
	            ObjectInputStream objIn=new ObjectInputStream(in);
	            int i = 0;
	            while(in.available() > 0) {
	            	p[i]=(NimPlayer) objIn.readObject();//read object from the .dat
	            	i++;
	            }
	            objIn.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
        }
    }
    
	//main
	public static void main(String[] args)
	{		
		//initialise variables
		Nimsys N = new Nimsys();
		NimPlayer playerList[] = new NimPlayer[100];
		Scanner myscanner = new Scanner(System.in);
		String INPUT,input0 = null,input1 = null;
		int i = 0;
		for (i = 0;i < 100;i++) {
			playerList[i] = null;
		}
		readObjectFromFile(playerList);
		System.out.println("Welcome to Nim");
		System.out.println();
		while (true){//loop until exit
			//input and divide the input
			System.out.print('$');
			try {
				INPUT = myscanner.nextLine();
				StringTokenizer st = new StringTokenizer(INPUT);
				input0 = st.nextToken();
				if(st.hasMoreTokens()) {
					input1 = st.nextToken();
				}
				else {
					input1 = null;
				}
			//process the command 
				switch(input0) {
					case "addplayer" :
						try {
						if(!N.addPlayer(playerList,input1)) {
							continue;//player exist
						}
						}
						catch(NullPointerException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
						catch(NoSuchElementException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
						break;	
					case "addaiplayer" :
						try {
						if(!N.addAIPlayer(playerList,input1)) {
							continue;//player exist
						}
						}
						catch(NullPointerException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
						catch(NoSuchElementException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
						break;
					case "removeplayer" :
						if(!N.removePlayer(playerList, input1, myscanner)) {
							continue;//entered "n"
						}
						break;			
					case "editplayer" :
						try {
						if(!N.editPlayer(playerList,input1)) {
							continue;//player exist
						}
						}
						catch(NullPointerException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
						catch(NoSuchElementException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
						break;				
					case "resetstats":
						if(!N.resetPlayer(playerList, input1, myscanner)) {
							continue;//entered "n"
						}
						break;					
					case "displayplayer":
						N.displayPlayer(playerList, input1, myscanner);
						break;					
					case "rankings" :
						N.rankings(playerList, input1, myscanner);
						break;				
					case "startgame" :
						try {
							N.startGame(playerList,input1, myscanner);
						}
						catch(NullPointerException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
						catch(NoSuchElementException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
						break;
					case "startadvancedgame" :
						try {
							N.startadvanceGame(playerList,input1, myscanner);
						}
						catch(NullPointerException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
						catch(NoSuchElementException e) {
							System.out.println("Incorrect number of arguments supplied to command.");
						}
						break;
					case "exit":
						writeObjectToFile(playerList);
						System.out.println();
						System.exit(0);//exit the game
						break;				
					default:
						try {
							throw new Exception("'"+ input0 + "'" + " is not a valid command."); 
						}
						catch(Exception e){
							System.out.println(e.getMessage());
							break;
						}
					}
				}
			//for input '/n'
			catch(NoSuchElementException e) {
				System.out.println("'"+ input0 + "'" + " is not a valid command.");
			}
			catch(NullPointerException e) {
				System.out.println("'"+ input0 + "'" + " is not a valid command.");
			}
			System.out.println();
		}
	}
}

