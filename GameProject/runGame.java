package GameProject;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class runGame 
{
	
	//--------
	// Name:			runGame Class
	// Author:			Adam Ryan
	// Created:			2021-05-10
	// Description:		This is the main class which is used to run the system.
	//
	//--------

	public static void main(String[] args) 
	{
		//global
		Scanner input= new Scanner(System.in);
		
		//Leaderboard
		Leaderboard highscoreLeaderboard= new Leaderboard("leaderboard.txt");
		
		//Sets the player array
		highscoreLeaderboard.readPlayersFromLeaderboard();
		
		//
		
		
		//Set the Main Menu
		StartMenu startMenu = new StartMenu();
		//Print the Menu
		System.out.println(startMenu.toString());
		
		//Get the chosen Game
		int chosenOption = startMenu.getEntry(input);
		
		//Not quitting
		while (chosenOption!=-1)
		{
			//Make the player
			PlayerTypeMenu playerMenu= new PlayerTypeMenu();
			
			//Player Menu
			System.out.println(playerMenu.toString());
			
			//Get Type of Player
			int chosenPlayerType=playerMenu.getEntry(input);
			
			//Set type of Player
			
			if (chosenPlayerType==-1)
			{
				System.out.println("You entered an invalid character. Setting you as a standard player");
			}
			
			Player sessionPlayer = playerMenu.callAction(chosenPlayerType, input);

			
			//Make the Main Menu
			MainMenu mainMenu = new MainMenu();
			
			//Display Menu
			System.out.println(mainMenu.toString());
			
			//Get the user's game
			int gameChoice = mainMenu.getEntry(input);
			
			//Not exit.
			while (gameChoice!=-1)
			{
				//Acquire the game
				Game chosenGame = mainMenu.callAction(gameChoice, input);
				
				//Check what game it is
				if (chosenGame instanceof CoinFlip)
				{
					CoinFlip playableGame = (CoinFlip) chosenGame;
					
					int pointsAwarded=playableGame.playGame(input,sessionPlayer);
					
					sessionPlayer.setSessionScore(sessionPlayer.getSessionScore() + pointsAwarded);
					
					System.out.println("\nAfter that game, your points total is: " + sessionPlayer.getSessionScore()+"\n");
					
					System.out.println("\nReturning to main menu...\n");
					
					//Delay the execution for three seconds so you can see it
					try 
					{
						TimeUnit.SECONDS.sleep(4);
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(mainMenu.toString());
					
					gameChoice = mainMenu.getEntry(input);
				}
				
				//Check what game it is
				if (chosenGame instanceof RockPaperScissors)
				{
					RockPaperScissors playableGame = (RockPaperScissors) chosenGame;
					
					int pointsAwarded=playableGame.playGame(input,sessionPlayer);
					
					sessionPlayer.setSessionScore(sessionPlayer.getSessionScore() + pointsAwarded);
					
					System.out.println("After that game, your points total is: " + sessionPlayer.getSessionScore()+"\n");
					
					System.out.println("\nReturning to main menu...\n");
					
					//Delay the execution for three seconds so you can see it
					try 
					{
						TimeUnit.SECONDS.sleep(4);
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(mainMenu.toString());
					
					gameChoice = mainMenu.getEntry(input);
					//.playGame(input);
				}
			}
			
			//They've quit back to the main menu, add them to the leaderboard
			highscoreLeaderboard.addPlayertoLeaderboardArray(sessionPlayer);
			
			//Back to the Choose New Player etc menu
			System.out.println(startMenu.toString());
			
			//New Player entry begins again
			chosenOption = startMenu.getEntry(input);
			
			
			
			
		}
		
		//They're quitting the game.
		//We've already added them to the leaderboard.
		//Sort it.
		highscoreLeaderboard.sortLeaderBoard();
		
		System.out.println("Thanks for playing!\n\n");
		System.out.println("--------------------\n");
		System.out.println("Leaderboard:\n");
		
		//Displayformat
		System.out.println(highscoreLeaderboard.toString());
		
		//Delay the execution for five seconds so you can see it
		try 
		{
			TimeUnit.SECONDS.sleep(5);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Saving Leaderboard...");
		//Delay the execution for two seconds so you can see it
		try 
		{
			TimeUnit.SECONDS.sleep(2);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		highscoreLeaderboard.overwriteLeaderboard();
		
		System.out.println("--------------------\n");
		System.out.println("Shutting Down...");
		
	}

}
