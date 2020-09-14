
/**
* @author  Clarisca Lawrencia
* @idnumber 1152594
* @username clawrencia
*/

public class NimHumanPlayer extends NimPlayer 
{	
	public NimHumanPlayer()
	{
		super();
	
	}
	
	public NimHumanPlayer(String userName, String firstName, String lastName, String playerType)
	{
		super(userName, firstName, lastName, playerType);
	}

	
	public NimHumanPlayer(String userName, String firstName, String lastName, int gamesWon, int gamesPlayed)
	{
		super(userName, firstName, lastName, gamesWon, gamesPlayed);
		
	}
	public NimHumanPlayer(String[] user) {
		super(user);
	}

	public int moveStone(int upperBound, int stones) throws InvalidMove
	{
		
		int stoneRemoved=0;
	
		try
		{
			stoneRemoved = Nimsys.keyboard.nextInt();
			
			
			if (stoneRemoved > upperBound || stoneRemoved > stones || stoneRemoved ==0)
			{
				throw new InvalidMove("\nInvalid move. You must remove between 1 and");
			}
		
		} catch (InvalidMove e)
		
		{
			if(stoneRemoved>upperBound || stoneRemoved ==0)
			{
				stoneRemoved=0;
				System.out.println(e.getMessage() +" "+upperBound+" stones.\n");
			}
			else if(stoneRemoved>stones || stoneRemoved ==0)
			{
				stoneRemoved=0;
				System.out.println(e.getMessage() +" "+stones+ " stones.\n");
			}
		}
	
		return stoneRemoved;
	}



}

