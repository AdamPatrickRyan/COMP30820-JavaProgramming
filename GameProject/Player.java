package GameProject;

import java.util.Scanner;

public class Player 
	implements Comparable<Player>
	{
	
	//--------
	// Name:			Player Class
	// Author:			Adam Ryan
	// Created:			2021-05-10
	// Description:		This is the main class which is used for Players.
	//					Games are comprised of multiple players
	//					The characteristic of a player is that:
	//					-They have a score in the game they're playing persistent for that session.
	//					-They have a gamertag to identify them for that session.
	//					-Players can be compared on the basis of their score.
	//
	//--------
		
	
	
	//For the moment, we'll make gamertag the unique key for simplicity
	//--// in production this shouldn't be done: see Sony's Account Network's backend design for flaw in not using other PK
	
	//Technically the sessionScore is a game score as it only persists for a game.
	
	private int sessionScore;
	private String gamerTag;
	
		public Player() 
		{
			this("NewPlayer");
		}
		
		public Player(String gamerTag) 
		{
			this.setGamerTag(gamerTag);
			this.setSessionScore(0);
		}


		public void setGamerTag(String gamerTag)
		{
			this.gamerTag=gamerTag;
		}
		
		public void setSessionScore(int sessionScore)
		{
			//Already protected that it's an Int.
			//I want this to be potentially negative as you can lose points in some of my games.
			this.sessionScore=sessionScore;
		}
		
		public String getGamerTag()
		{
			return this.gamerTag;
		}
		
		
		public String requestValidGamerTag(Scanner input)
		{
			Boolean validTag = false;
			
			while (!validTag)
			{
				System.out.println("Please enter a gamertag: ");
				String gamerTag = input.nextLine();
				
				//String is not empty
				if (gamerTag.length()>0)
				{
					//String is alphanumeric - Used to avoid Separators being added or malicious String behaviour.
					if (gamerTag.matches("^[a-zA-Z0-9]*$"))
					{
						return gamerTag;
						
					}
					
					else
					{
						System.out.println("Your gamertag is not valid. Gamertags must be alphanumeric. Try again.\n");
					}
					
				}
			}
			
			return gamerTag;
			
		}
		
		
		public int getSessionScore()
		{
			return this.sessionScore;
		}
		
		
		
		@Override
		public int compareTo(Player other)
		{
			//This method will be useful for the leaderboard sorting.
			
			//Scores are Equal
		   if(this.getSessionScore()==other.getSessionScore())
		   {
			      return 0;  
		   }
		   
		   //This score is greater than the other
		   else if(this.getSessionScore()>other.getSessionScore()) 
		   {
		      return 1;  
		   }
		   
		   //Other score is larger
		   else  
		   {
		      return -1; 
		   }
		   
		}
		
		public String toLeaderboardFormat() 
		{
			return this.getGamerTag() + "|" + this.getSessionScore(); 
		}
		
		@Override
		public String toString() 
		{
			return "Gamertag: " + this.getGamerTag() 
					+ "\nScore: " + +	this.getSessionScore() + "\n\n";
		}
		
}
