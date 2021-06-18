package GameProject;

import java.util.Map;
import java.util.Scanner;

public class PlayerTypeMenu
	extends Menu

{
	//--------
	// Name:			Main Menu Class
	// Author:			Adam Ryan
	// Created:			2021-05-11
	// Description:		This is a type of menu.
	//					It has all of the characteristics of a menu, but it has a set list of options (the games)
	//					-This is set as a separate class to avoid needing a hashtable including functions to call the players.
	//					-It has options which the user should select.
	//					-It has logic on how to call the player type (see 1).
	//
	//--------
	
	
	private static Map<Integer, String> mainOptions = Map.ofEntries(
		    Map.entry(1, "Standard Player"),
		    Map.entry(2, "Hardcore Player")
		);
	
	private static String playerMenuName="Player Menu";
	private static String playerMenuQuestion="Please choose your Player type. Warning that this will affect game difficulty: ";
	
	public PlayerTypeMenu() 
	{
		this(playerMenuQuestion, mainOptions);
	}
	
	public PlayerTypeMenu(String playerMenuQuestion, Map<Integer, String> mainOptions) 
	{
		super(playerMenuName, playerMenuQuestion, mainOptions);
	}
	
	public Player callAction(int optionValue, Scanner input)
	{
		
		
		if (optionValue==1)
		{
		  Player playingPerson = new Player();
		  
		  String gamertag=playingPerson.requestValidGamerTag(input);
		  
		  playingPerson.setGamerTag(gamertag);
		  
		  playingPerson.setSessionScore(0);
		  
		  return playingPerson;
		}
		
		else if (optionValue==2)
		{
		  HardcorePlayer hardcorePerson = new HardcorePlayer();
		  
		  String gamertag=hardcorePerson.requestValidGamerTag(input);
		  
		  
		  hardcorePerson.setGamerTag(gamertag);
		  
		  hardcorePerson.setSessionScore(0);
		  
		  
		  return hardcorePerson;
		}
		
		else
		{
			  Player playingPerson = new Player();
			  
			  String gamertag=playingPerson.requestValidGamerTag(input);
			  
			  playingPerson.setGamerTag(gamertag);
			  
			  playingPerson.setSessionScore(0);
			  
			  return playingPerson;
		}
		
	}
	
	
}
