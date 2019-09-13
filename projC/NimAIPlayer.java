
import java.util.*;

//Zongru Li
//947539
public class NimAIPlayer extends NimPlayer implements Comparable<NimPlayer>,Testable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NimAIPlayer() {
		super();
	}
	
	public NimAIPlayer(String userName,String familyName,String givenName) {
		super(userName,familyName,givenName);

	}
	//game
	public int removeStone(NimGame g) {
		// TODO Auto-generated method stub
		int upperBound = g.getUpperBound();
		int currentStone = g.getCurrentStone();
		int validNumber = (upperBound>currentStone)?currentStone:upperBound;
		float i = ((float)currentStone - 1)/((float)upperBound + 1);
		if(i%1 == 0) {
			return (int)(1 + Math.random()*(validNumber - 1 + 1));
		}			
		else {
			for(int k = 1;k < upperBound + 1;k++) {
				i = ((float)currentStone - k - 1)/((float)upperBound + 1);
				if(i%1 == 0) {
					return k;
				}
			}
		}
		return (int)(1+Math.random()*(validNumber-1+1));
	}
	
	//advanced game
	@Override
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move;
		boolean flag = true;
		int length  = available.length;
		int sum = 0,k = 0;
		for(int i = 0;i < length;i++) {
			if(available[i] == false) {
				flag = false;
			}
			else {
				sum++;
				k = i;
			}
		}
		//only remain 1 element
		if(sum == 1) {
			String str = String.valueOf(k + 1);
			move = str + " 1";
			return move;
		}
		//only remain 2 elements
		else if(sum == 2&&available[k - 1] == true) {
			String str = String.valueOf(k);
			move = str + " 2";
			return move;
		}
		else if(length%2 == 0) {//the initial stones is even
			if(available[length/2] == true && available[length/2 - 1] == true&&flag == true) {//when you move first 
				String str = String.valueOf(length/2);//remove the middle two
				move = str + " 2";
				return move;
			}
			else if(sum == length - 1&& (available[0] == false||available[length - 1] == false)) {//when you are the second and your rival remove the boundary
				String str;
				if(available[0] == false) {	
					str = String.valueOf(length/2 + 1);
				}
				else {
					str = String.valueOf(length/2);
				}
				move = str + " 1";
				return move;
			}
			else {
				move = normalMove(length,lastMove,available);
				return move;
			}
		}
		else {//the initial stones is odd
			if(available[length/2] == true&&flag == true) {//when you move first 
				String str = String.valueOf(length/2 + 1);//remove the middle one
				move = str + " 1";
				return move;
			}
			else if(sum == length - 1&& (available[0] == false||available[length - 1] == false)) {//when you are the second and your rival remove the boundary
				String str;
				if(available[0] == false) {	
					str = String.valueOf(length/2 + 1);
				}
				else {
					str = String.valueOf(length/2);
				}
				move = str + " 2";
				return move;
			}
			else {
				move = normalMove(length,lastMove,available);
				return move;
			}
		}
	}
	
	//ad
	public String advancedRemove(advancedNimGame g) {//in order to use the advancedNimGame object
		return advancedMove(g.getC(), g.getLastMove());
	}
	
	//not the first step or the final step
	public String normalMove(int length,String lastMove,boolean[] c) {
		StringTokenizer st = new StringTokenizer(lastMove);
		int n1 = Integer.parseInt(st.nextToken()) - 1;
		int n2 = Integer.parseInt(st.nextToken());
		//when you are the first hand, follow the winning rules
		n1 = length - 1 - n1;
		if(n2 == 2) {
			if(n1 > length/2) {
				n1--;
			}
			if((n1 + 1)>length||c[n1 + 1] == false) {
				n2 = 1;
			}
		}
		//when you are the back hand
		while(c[n1] == false) {	
				n1 = (int)(0 + Math.random()*((length - 1) - 0 + 1));
		}
		//final check whether match the rule of movement
		if(n2 == 2&&((n1 + 1)>length||c[n1 + 1] == false)) {
			n2 = 1;
		}
		String str1 = String.valueOf(n1 + 1);
		String str2 = String.valueOf(n2);
		return str1 + " " + str2;
	}
}
