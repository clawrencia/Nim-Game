
/**
* @author  Clarisca Lawrencia
* @idnumber 1152594
* @username clawrencia
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner; 
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;


public class Nimsys 
{
	 //Initializing a scanner
	public static Scanner keyboard = new Scanner(System.in);
	
	static int KEYBOARD_SIZE =4;
	
	
	public static void main(String args []) throws Exception
	{ 
		//Initializing the variable
		
		String [] keyboardInput = new String [KEYBOARD_SIZE];
		String command,usernameInput,userFirstname, userLastname, sortInput;
		String quit= "Y";
		String input = "Correct";
		String player1User, player2User;
		
		ArrayList <NimPlayer> user= new ArrayList<NimPlayer>();
		NimPlayer player1;
		NimPlayer player2;
		
		Nimsys obj = new Nimsys();
		
		int stoneInput;
		int upperBoundInput;
		
		//Load file
		obj.loadFile(user);
		
		//Welcome statement
		System.out.println("Welcome to Nim\n");
			
		while (quit.contentEquals("Y"))
		{
			//Receive command
			System.out.print("$");
			command = keyboard.next();
			
			//Delimiting the input with commas
			keyboardInput = keyboard.nextLine().split(",");
			input = "correct";
		
			//Run the loop while the command is correct
			while (input.equals("correct"))
			{
				try {
					try
					{
						//Adding a new human player
						if(command.equals("addplayer")) 
						{	
							usernameInput = keyboardInput[0].trim();
							userLastname = keyboardInput[1];
							userFirstname = keyboardInput[2];							
								
							//Create a new player object
							obj.addNewPlayer(usernameInput, userFirstname, userLastname, user);
							System.out.print("\n");
							input = "incorrect";

						}
						
						//Adding a new AI player
						else if(command.equals("addaiplayer"))
						{
							usernameInput = keyboardInput[0].trim();
							userLastname = keyboardInput[1];
							userFirstname = keyboardInput[2];
						
							//Create new AI player
							obj.addAIPlayer(usernameInput, userFirstname, userLastname, user);
							System.out.print("\n");
							input = "incorrect";
						}
							
						//Edit an existing player
						else if(command.equals("editplayer"))
						{	
							usernameInput = keyboardInput[0].trim();
							userLastname = keyboardInput[1];
							userFirstname = keyboardInput[2];
							
							obj.editPlayer(usernameInput, userFirstname, userLastname, user);
							System.out.print("\n");
							input = "incorrect";
							break;	

						}
						
						//Remove an existing player
						else if(command.equals("removeplayer"))
						{	
							usernameInput = keyboardInput[0].trim();
							
							//Removing all existing player
							if(usernameInput.isEmpty())
							{
								System.out.println("Are you sure you want to remove all players? (y/n)");
								String removeAll = keyboard.nextLine();
								if(removeAll.equalsIgnoreCase("Y"))
								{
									obj.removeAllPlayer(user);
								}
								System.out.print("\n");
								input = "Incorrect";
								break;
							}
							
							else 
							{
								obj.removePlayer(usernameInput, user);
								System.out.print("\n");
								input = "Incorrect";
								break;
	
							}
								
						}
						
						//Display an existing player
						else if(command.equals("displayplayer"))
						{
							usernameInput = keyboardInput[0].trim();
							
							//Display all player
							if(usernameInput.isEmpty())
							{
								obj.CompareDesc(user);;
								obj.displayAllPlayer(user);
								System.out.print("\n");
								input = "Incorrect";
								break; 
							}
							else
							{
								System.out.println(obj.displayPlayer(usernameInput,user));
								System.out.print("\n");
								input = "Incorrect";
								break;
							}
						}
						
						//Reset stats of existing players
						else if (command.equals("resetstats"))
						{	
							usernameInput = keyboardInput[0].trim();
							
							//Reset stats of all exisitng players
							if(usernameInput.isEmpty())
							{
								System.out.println("Are you sure you want to reset all player statistics? (y/n)");
								String reset = keyboard.nextLine();
								
								if(reset.equals("y"))
								{
									obj.resetAll(user);
									System.out.print("\n");
									input = "Incorrect";
								}
								break;
							}
							else 
							{
								obj.resetPlayer(usernameInput, user);
								System.out.print("\n");
								input = "Incorrect";
								break;
							}
						}
						
						//Display rankings of the players
						else if (command.contentEquals("rankings"))
						{
							sortInput = keyboardInput[0].trim();
							
							//sort the array of objects in descending manner
							if(sortInput.equals("desc"))
							{
								obj.CompareDesc(user);
							}
							
							//sort the array of objects in ascending manner
							if(sortInput.equals("asc"))
							{
								obj.CompareAsc(user);
							}
		
							//sort the array of objects in descending manner
							if(sortInput.isEmpty())
							{
								obj.CompareDesc(user);
							}
							
							obj.displayRank(user);
							
							System.out.print("\n");
							input = "incorrect";
							break;
						}
						
						//Command to start the game
						else if (command.contentEquals("startgame"))
						{	
							//Initializing the variables
							stoneInput = Integer.parseInt(keyboardInput[0].trim());
							upperBoundInput = Integer.parseInt(keyboardInput[1]);
							player1User = keyboardInput[2];
							player2User = keyboardInput[3];
							
							//Check if player exist and set as Player 1
							player1 = obj.setGamePlayer(player1User, user);
							
							//Check if player exist and set as Player 2 
							player2 = obj.setGamePlayer(player2User, user);
							
							if(player2 == null || player1==null)
							{
								System.out.println("One of the players does not exist.\n");
								input = "incorrect";
								break;
							}
							
							//Create a new game
							NimGame newGame = new NimGame(stoneInput, upperBoundInput,  player1, player2);
							
							//New game commences
							newGame.startGame(stoneInput, upperBoundInput, player1, player2);
							input = "incorrect";
							
						}
						//Exit command
						else if (command.contentEquals("exit"))
						{
							System.out.print("\n");
							obj.writeFile(user);
							System.exit(0);
						}
						else
						{
							//the command input is not valid
							throw new InvalidCommand("'"+command+"' is not a valid command.\n");
						}
					}
					// Number of argument not enough
					catch(ArrayIndexOutOfBoundsException e)
					{
						 System.out.println( "Incorrect number of arguments supplied to command.\n" );
						 break;
					}
				}
				catch(InvalidCommand NotValidCommand)
				{
					System.out.println(NotValidCommand.getMessage());
					input = "Incorrect";
					break;
				}
			}
		}
	}

	//method to load a file
	private void loadFile(ArrayList<NimPlayer> userList)
	{
		try
		{	
			//assign a buffered reader variable
			BufferedReader loadFile = null;
			
			//load a players.dat file
			loadFile = new BufferedReader(new FileReader("players.dat"));
			String read = loadFile.readLine();
			
			while(read!= null)
			{
				//read the text
				String [] split = read.split(",");
				
				//add for human player
				if(split[5].equalsIgnoreCase("HUMAN"))
				{
					userList.add(new NimHumanPlayer(split));
					
				}
				
				//add for ai player
				else if(split[5].equalsIgnoreCase("AI"))
				{
					userList.add(new NimAIPlayer(split));
				}
				
				read = loadFile.readLine();
			}

			loadFile.close();
			
		}
		catch(Exception e)
		{	
			//Doesn't return anything since it will start the system
			return;
		}
	}
	
	//method to write to save file
	private void writeFile(ArrayList<NimPlayer> userList)
	{
		try 
		{
			BufferedWriter saveFile = null;
			saveFile = new BufferedWriter(new FileWriter("players.dat"));
			
			//write for each file
			for (NimPlayer i : userList)
			{
				saveFile.write(i.toString());
			}
			saveFile.close();
		}
		catch (Exception e)
		{
			System.out.println("an error occured");
		}		
	}

	//Method to add a new AI player
	private void addAIPlayer(String username, String firstName, String lastName, ArrayList<NimPlayer> list)
	{
		//Check existing player
		boolean found= false; 
		Nimsys obj = new Nimsys();
		found = obj.checkPlayer(username, list, found);
		if(found == true)
		{
			System.out.println("The player already exists");
		}
		else 
		{
			list.add(new NimAIPlayer(username, firstName, lastName, "AI"));
		}
	}

	//Method to add a new player
	private void addNewPlayer(String username, String firstName, String lastName, ArrayList<NimPlayer> list )
	{
		//Check existing player
		boolean found= false; 
		Nimsys obj = new Nimsys();
		found = obj.checkPlayer(username, list, found);
		if(found == true)
		{
			System.out.println("The player already exists.");
		}
		else 
		{
			list.add(new NimHumanPlayer(username, firstName,lastName,"Human"));
		}
	}
	
	//Method to check if player exist
	private boolean checkPlayer(String username, ArrayList<NimPlayer> userlist, boolean found)
	{
		//Traverse the entire array
		for (NimPlayer i : userlist)
		{	
			//Process if array is not null
			if(i!=null && i.getUserName()!=null)
			{
				if(i.getUserName().equals(username))
				{
					found = true;
					break;
				}			
			}	
			
		}
		return found;		
	}
	
	//Method to edit existing player
	private void editPlayer(String username,String firstName, String lastName, ArrayList<NimPlayer> userList)
	{
		//Check existing player
		boolean found= false; 
		Nimsys obj = new Nimsys();
		found = obj.checkPlayer(username, userList, found);
		if(found == false)
		{
			System.out.println("The player does not exist.");
		}
		else 
		{
			//Traverse the entire array
			for (NimPlayer i :  userList)
			{		
				//Process if array is not null
				if(i!=null && i.getUserName()!=null)
				{
					if(i.getUserName().equals(username))
					{
						i.setFName(firstName);
						i.setLName(lastName);
						break; 
					}
				}	
			}
		}
	}
	
	//Method to remove existing player
	private void removePlayer(String username, ArrayList<NimPlayer> userList)
	{
		//Check existing player
		boolean found= false; 
		Nimsys obj = new Nimsys();
		found = obj.checkPlayer(username, userList, found);
		if(found == false)
		{
			System.out.println("The player does not exist.");
		}
		else 
		{
			//Traverse the entire array
			for(NimPlayer i : userList)
			{
				//Only process if the array is not null
				if(i!=null && i.getUserName()!=null)
				{
					if(i.getUserName().equals(username))
					{
						userList.remove(i);
					}
				}
			}
		}
	}
	
	//Method to remove all existing player
	private void removeAllPlayer(ArrayList<NimPlayer> userList)
	{
		//Traverse the entire array
		userList.clear();
	}
	
	//Method to display an existing player
	private String displayPlayer(String username, ArrayList<NimPlayer> userList)
	{	
		String s = null;
		//Check existing player
		boolean found= false; 
		Nimsys obj = new Nimsys();
		found = obj.checkPlayer(username, userList, found);
		if(found == false)
		{
			System.out.println("The player does not exist.");
		}
		else 
		{
			//Traverse the entire array
			for (NimPlayer i : userList)
			{
				//Only process if array is not null 
				if(i!=null && i.getUserName()!=null)
				{
					if(i.getUserName().equals(username))
					{
						s = i.getUserName()+","+i.getFName()+","+i.getLName()+","+i.getGamesPlayed()+" games,"+i.getGamesWon()+ " wins";
						break;	
					}
				}
			}
		}
		return s;
	}
	
	//Method to display all existing player
	private void displayAllPlayer(ArrayList<NimPlayer> userList)
	{	
		//Traverse the entire array
		for (NimPlayer i : userList)
		{
			//Only process if array is not null
			if(i!=null&&i.getUserName()!=null)
			{
				System.out.println(i.getUserName()+","+i.getFName()+","+i.getLName()+","
							+ ""+i.getGamesPlayed()+" games,"+i.getGamesWon()+ " wins");
			}
		}
		return;
	}
	
	//Method to display rank
	private void displayRank(ArrayList<NimPlayer> userList)
	{	
		String column="|";
		String percentage;
		
		//Only show 10 players
		if(userList.size() > 10)
		{	
			for(int i=0; i<=10; i++)
			{
				if(userList.get(i)!=null && userList.get(i).getUserName()!=null)
				{
					percentage  = String.format("%.0f",userList.get(i).calculatePercentage())+"%";
				
					System.out.printf("%-4s %s %02d games %s %s %s%n",percentage,column,userList.get(i).getGamesPlayed(), 
					column, userList.get(i).getFName(), userList.get(i).getLName());
				}
			}
		}
		else
		{
			for(NimPlayer i : userList)
			{
				if(i!=null && i.getUserName()!=null)
				{
					percentage  = String.format("%.0f",i.calculatePercentage())+"%";
				
					System.out.printf("%-4s %s %02d games %s %s %s%n",percentage,column,i.getGamesPlayed(), 
					column, i.getFName(), i.getLName());
				}
			}
		}
		return;
	}
	
	//Method to reset all player stats
	private void resetAll(ArrayList<NimPlayer> userList)
	{
		//Traverse the entire array
		for (NimPlayer i : userList)
		{
			//Only process if array is not null 
			if(i!=null)
			{
				i.setGamesPlayed(0);
				i.setGamesWon(0);
			}
		}
	}
	
	//Method to reset an existing player
	private void resetPlayer(String username, ArrayList<NimPlayer> userList)
	{
		//Check existing player
		boolean found= false; 
		Nimsys obj = new Nimsys();
		found = obj.checkPlayer(username, userList, found);
		if(found == false)
		{
			System.out.println("The player does not exist.");
		}
		else 
		{
			//Traverse the entire array
			for (NimPlayer i : userList)
			{
				//Only process if array is not null
				if(i!=null && i.getUserName()!=null)
				{
					if(i.getUserName().equals(username))
					{
						i.setGamesWon(0);
						i.setGamesPlayed(0);
						break;
					}
				}
			}
		}
	}
	
	//Method to set the player for a game
	private NimPlayer setGamePlayer(String username, ArrayList<NimPlayer>userList)
	{
		NimPlayer player = null;
		
		//Traverse the entire array
		for(NimPlayer i : userList)
		{
			//Only process if array is not null
			if(i != null && i.getUserName()!=null)
			{
				if(i.getUserName().equals(username))
				{
					player = i;
					break;
				}
			}
		}
		return player;
	}
	
	
	//method to compare the array in ascending format
	private void CompareAsc(ArrayList<NimPlayer> userList)
	{
		Comparator<NimPlayer> WonCompareAsc = Comparator.comparing(NimPlayer::calculatePercentage)
				.thenComparing(NimPlayer::getUserName);
		Comparator<NimPlayer> nullLast = Comparator.nullsLast(WonCompareAsc);
		
		Collections.sort(userList,nullLast);
		
	}
	
	//method to compare the array in descending format
	private void CompareDesc(ArrayList<NimPlayer> userList)
	{
		Comparator<NimPlayer> WonCompareDesc = Comparator.comparing(NimPlayer::calculatePercentage);
		Comparator<NimPlayer> CompareDesc = WonCompareDesc.reversed().thenComparing(NimPlayer::getUserName);
		Comparator<NimPlayer> ReadNull = Comparator.nullsLast(CompareDesc);
		
		Collections.sort(userList,ReadNull);
	}
	

}


		
	
	

