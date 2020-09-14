
/**
* @author  Clarisca Lawrencia
* @idnumber 1152594
* @username clawrencia
*/

public class NimGame 
{
	//Initializing the variable
	private int currentStone;
	private int upperBound;
	private int token =1;
	private int stoneRemoved;
	
	private NimPlayer player1, player2;
	
	
	private NimPlayer winner = null;
	private NimPlayer loser = null; 
	
	//Default setter
	public NimGame()
	{
		currentStone=0;
		upperBound=0;
		player1 = null;
		player2 = null;
		
	}
	
	//Setter for NimGame
	public NimGame(int currentStone, int upperBound, NimPlayer player1, NimPlayer player2)
	{
		this.currentStone = currentStone;
		this.upperBound = upperBound;
		this.player1 = player1;
		this.player2 = player2; 
	}
	
	//Setter for upperbound
	public void setUpperBound(int upperBound)
	{
		this.upperBound = upperBound;
	}
	
	//Getter for upperbound
	public int getUpperBound()
	{
		return upperBound;
	}
	
	//Setter for stones
	public void setStone(int currentStone)
	{
		this.currentStone = currentStone;
	}
	
	//Getter for stones
	public int getStone()
	{
		return currentStone;
	}
	
	//Setter for player1
	public void setPlayer1(NimPlayer player1)
	{
		this.player1 = player1;
	}
	
	//Setter for player2 
	public void setPlayer2(NimPlayer player2)
	{
		this.player2 = player2; 
	}
	
	//Getter for player1
	public NimPlayer getPlayer1()
	{
		return player1;
	}
	
	//Getter for player2
	public NimPlayer getPlayer2()
	{
		return player2;
	}
	
	//Method to create stone images
	private String stoneImage(int stonesAmount) 
	{
		String stoneImage="*";
		
		 //repetition for the stoneImage
		String repeat = new String(new char[stonesAmount]).replace("\0", stoneImage);
		
		//adding space between * and removing the space at the end 
		repeat= repeat.replaceAll(".(?!$)", "$0 ");
		return repeat;
	}
	
	private void playerTurn(int stones, NimPlayer player)
	{
		System.out.println(stones+ " stones left: "+stoneImage(stones));
		System.out.println(player.getFName()+"'s turn - remove how many?");
	}
	
	//Method to remove stones
	private int removeStone(int stoneRemovedAmt, int stonesAmt)
	{
		stonesAmt = stonesAmt - stoneRemovedAmt;
		return stonesAmt;	
	}
	

	//Method used to start the game 
	public void startGame(int stones,  int upperBound, NimPlayer player1, NimPlayer player2) throws InvalidMove
	{	
		
		NimGame game = new NimGame();
		//Printing before game commences 
		System.out.print("\n");
		System.out.println("Initial stone count: "+stones);
		System.out.println("Maximum stone removal: "+upperBound);
		System.out.println("Player 1: "+player1.getFName() +" "+player1.getLName());
		System.out.println("Player 2: "+player2.getFName() +" "+player2.getLName());
		System.out.print("\n");
		
		//Repeat the while loop when Stones is not equal to 0
		while(stones !=0)
		{
			switch(token) 
			{
				//Player 1 turn
				case 1:
					stoneRemoved=0;
					while(stoneRemoved==0)
					{
						game.playerTurn(stones, player1);
						stoneRemoved = player1.moveStone(upperBound, stones);
					}
	
						System.out.print("\n");
						//Deduct the number of stones
						stones = removeStone(stoneRemoved, stones);
						
						if(stones == 0)
						{
							winner = player2;
							loser = player1; 
							
						}
						
						token = token + 1;
						break;

			
				//Player 2 turn
				case 2:
					stoneRemoved =0;
					while(stoneRemoved==0)
					{
						game.playerTurn(stones, player2);
						stoneRemoved = player2.moveStone(upperBound, stones);
					}
						System.out.print("\n");
						//Deduct the stone count
						stones = removeStone(stoneRemoved, stones);
						
						if(stones == 0)
						{
							winner = player1;
							loser = player2;

						}
						
						token = token - 1;
						break;				
			}
		}
		
		//Printing the winner's name and adding their stats
		System.out.println("Game Over");
		System.out.println(winner.getFName()+" "+winner.getLName()+" wins!\n");
		winner.addGamesPlayed();
		winner.addGamesWon();
		loser.addGamesPlayed();
		
	}
}
