package GameProject;

import java.util.Map;
import java.util.Scanner;

public class MainMenu
	extends Menu

{
	//--------
	// Name:			Main Menu Class
	// Author:			Adam Ryan
	// Created:			2021-04-17
	// Description:		This is a type of menu.
	//					It has all of the characteristics of a menu, but it has a set list of options (the games)
	//					-This is set as a separate class to avoid needing a hashtable including functions to call the game.
	//					-It has options which the user should select.
	//					-It has logic on how to call the game which the use entered (see 1).
	//
	//--------
	
	
	//This could potentially be updated to an Array of Games that can then be called with the CallAction as a loop
	//Keeping this simple for ease.
	private static Map<Integer, String> mainOptions = Map.ofEntries(
		    Map.entry(1, "Coin Flip"),
		    Map.entry(2, "Rock Paper Scissors"),
		    Map.entry(3, "Quit")
																	);
	
	private static String mainMenuTitle="Main Menu";
	private static String mainMenuQuestion="Please choose a game: ";
	
	public MainMenu() 
	{
		this(mainMenuTitle, mainMenuQuestion, mainOptions);
	}
	
	public MainMenu(String mainMenuTitle, String mainMenuQuestion, Map<Integer, String> optionDict) 
	{
		super(mainMenuTitle, mainMenuQuestion, mainOptions);
	}
	
	public Game callAction(int optionValue, Scanner input)
	{
		switch(optionValue) 
		{
		//This is used to trigger the Game sent back. I keep defaults rather than passing in the player and having to 
		//entangle everything.
		
		  case 1:
			  return new CoinFlip();
		  case 2:
			  return new RockPaperScissors();
			  
		  default:
			  return new CoinFlip();
		}
		
	}

}
