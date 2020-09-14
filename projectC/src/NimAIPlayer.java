
/**
* @author  Clarisca Lawrencia
* @idnumber 1152594
* @username clawrencia
*/

public class NimAIPlayer extends NimPlayer implements Testable
{
	
	public NimAIPlayer()
	{
		super();
	
	}
	
	public NimAIPlayer(String userName, String firstName, String lastName, String playerType)
	{
		super(userName, firstName, lastName, playerType);
	}
	
	public NimAIPlayer(String userName, String firstName, String lastName, int gamesWon, int gamesPlayed)
	{
		super(userName, firstName, lastName, gamesWon, gamesPlayed);
	}
	
	public NimAIPlayer(String[] user) {
		super(user);
	}

	// abstract method from parent class
	public int moveStone(int upperBound, int stones) throws InvalidMove
	{
		int stoneRemoved;
		
		//finding the remainder 
		int amountLeft = stones % (upperBound +1);
		
	
		if(amountLeft != 1 && amountLeft!=0)
		{
			//To ensure the AI always wins
			stoneRemoved = (amountLeft-1) %  (upperBound +1);
		}
		else
		{
			if(stones < upperBound)
			{
				stoneRemoved = Math.min(1, stones);
			}
			else
			{
				stoneRemoved = Math.min(stones,upperBound);
			}
		}
		return stoneRemoved;
	}
	
	public String advancedMove(boolean[] available, String lastMove)
	{
		return null;
	}


	
}
