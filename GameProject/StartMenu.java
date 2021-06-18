package GameProject;

import java.util.Map;
import java.util.Scanner;

public class StartMenu
	extends Menu

{
	//--------
	// Name:			Start Menu Class
	// Author:			Adam Ryan
	// Created:			2021-04-17
	// Description:		This is a type of menu.
	//					It has all of the characteristics of a menu, but it has a set list of options (quit or new player)
	//					-This is set as a separate class to avoid needing a hashtable including functions to call the game.
	//					-It has options which the user should select.
	//					-It has logic on how to call the game which the use entered (see 1).
	//					-This is essentially an instance of Menu but with a static set of options.
	//					-Set as a class to avoid needing to change in runGame.
	//
	//--------
	
	
	//This could potentially be updated to an Array of Games that can then be called with the CallAction as a loop
	//Keeping this simple for ease.
	
	private static Map<Integer, String> mainOptions = Map.ofEntries(
		    Map.entry(1, "New Player"),
		    Map.entry(2, "Quit")
																	);
	
	private static String mainMenuTitle="Start Menu";
	private static String mainMenuQuestion="Please choose an option: ";
	
	public StartMenu() 
	{
		this(mainMenuTitle, mainMenuQuestion, mainOptions);
	}
	
	public StartMenu(String mainMenuTitle, String mainMenuQuestion, Map<Integer, String> mainOptions) 
	{
		super(mainMenuTitle, mainMenuQuestion,mainOptions);
	}

	
}
