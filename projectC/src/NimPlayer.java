
/**
* @author  Clarisca Lawrencia
* @idnumber 1152594
* @username clawrencia
*/

public abstract class NimPlayer{
	
	//Initializing of variables
	private String userName;
	private String firstName;
	private String lastName;
	private String playerType;
	
	private int gamesWon;
	private int gamesPlayed;
	
	//Default setter
	public NimPlayer()
	{
		userName=null;
		firstName=null; 
		lastName=null;
		gamesWon=0;
		gamesPlayed=0; 
		playerType = null;
	}
	
	//Setter for NimPlayer
	public NimPlayer(String userName, String firstName, String lastName, String playerType)
	{
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gamesWon = 0;
		this.gamesPlayed= 0;
		this.playerType = playerType;
	}
	
	public NimPlayer(String userName, String firstName, String lastName, int gamesWon, int gamesPlayed) 
	{
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gamesWon = gamesWon;
		this.gamesPlayed= gamesPlayed;
		this.playerType = null;
	}

	
	public NimPlayer(String [] user)
	{
		this.userName = user[0];
		this.firstName = user[1];
		this.lastName = user[2];
		this.gamesWon = Integer.parseInt(user[3]);
		this.gamesPlayed= Integer.parseInt(user[4]);
		this.playerType = user[5];
	}
	
	public void setPlayerType(String PlayerType)
	{
		this.playerType = PlayerType;
	}
	
	public String getPlayerType()
	{
		return playerType;
	}
	
	//Username setter
	public void setUsername(String userName)
	{
		this.userName = userName;
	}
	
	//First Name Setter
	public void setFName(String firstName)
	{
		this.firstName = firstName;
	}
	
	//Last Name Setter
	public void setLName(String lastName)
	{
		this.lastName = lastName;
	}
	
	//setter for games played
	public void setGamesPlayed(int gamesPlayed)
	{
		this.gamesPlayed = gamesPlayed;
	}
	
	//setter for games won
	public void setGamesWon (int gamesWon)
	{
		this.gamesWon = gamesWon;
	}
		
	//adding the number of games won for 1 player
	public void addGamesWon() 
	{
		this.gamesWon += 1 ;
		setGamesWon(this.gamesWon); 
	}
	
	//adding the number of games played for 1 player
	public void addGamesPlayed()
	{
		this.gamesPlayed += 1;
		setGamesPlayed(this.gamesPlayed);
	}
	
	//getter for first name
	public String getFName()
	{
		return firstName;
	}
	
	//getter for last name
	public String getLName()
	{
		return lastName; 
	}
	
	//getter for username
	public String getUserName()
	{
		return userName;
	}
	
	//getter for number of games won
	public int getGamesWon()
	{
		return gamesWon;
	}
	
	//getter for number of games played
	public int getGamesPlayed()
	{
		return gamesPlayed;
	}
	
	//method to calculate the winning percentage
	public float calculatePercentage()
	{
		float percentage=0;
		if (gamesPlayed!=0)
		{	
			percentage = ((float)gamesWon/(float)gamesPlayed)*100;
		}
		return percentage;
	}
	
	//abstract method
	public  abstract int moveStone(int UpperBound, int Stones) throws InvalidMove;
	
	@Override
	public String toString()
	{
		return ""+userName+","+firstName+","+lastName+","+gamesWon+","+gamesPlayed+","+playerType+"\n";
	}
}

