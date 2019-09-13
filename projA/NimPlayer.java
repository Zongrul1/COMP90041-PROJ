//NimPlayer.java
//Name:Zongru Li 
//StudentID:947539
public class NimPlayer { 
	private String name;
	public NimPlayer(String name)
	{
		this.name = name;
	}
	public int removeStone(int i)
	{
		return i;
	}
	public String Name()
	{
		return name;
	}
	public boolean WinOrNot(int i)
	{
		if (i == 0)
		{
			System.out.println("Game Over");
			System.out.println(name + " wins!");
			System.out.println();
			return true;
		}
		else
			return false;
	}
	public void Turn()
	{
		System.out.println(name + "\'s turn - remove how many?");//Player's turn
		System.out.println();
	}	
}
