
//Zongru Li
//947539
public class InvalidInputException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public InvalidInputException(String s){
		message = s;
	}
	
	public String getMessage() {
		return message;
	}
}
