package GameProject;

import java.util.Map;

public class gameMenu
	extends Menu

{
	//--------
	// Name:			Game Menu Class
	// Author:			Adam Ryan
	// Created:			2021-05-10
	// Description:		This is a type of menu.
	//					It has all of the characteristics of a menu, but it has a set list of options (quit or new player)
	//					-This is set as a separate class to avoid needing a hashtable including functions to call the game.
	//					-It has options which the user should select.
	//					-It has logic on how to call the game which the use entered (see 1).
	//					-This is essentially an instance of Menu but with a static set of options.
	//					-Set as a class to avoid needing to change in runGame.
	//				
	// NOTE:			-This is a redundant class mostly, initially I was going to use it to show a leaderboard for a game
	//					but I realised later the leaderboard was meant to be gloabl and not game specific so this menu became
	//					redundant and primarily replaced by instructions in the game classes.
	//
	//--------
	
	private static Map<Integer, String> mainOptions = Map.ofEntries(
		    Map.entry(1, "Play Game"),
		    //Map.entry(2, "View Leaderboard"), -> I had misread the brief initially.
		    Map.entry(2, "Quit")
		);
	
	private Game game;
	private static String gameMenuQuestion="Please choose an option: ";
	
	public gameMenu() 
	{
		this(new Game(), gameMenuQuestion, mainOptions);
	}
	
	public gameMenu(Game game, String gameMenuQuestion, Map<Integer, String> mainOptions) 
	{
		super(game.getGameName(), gameMenuQuestion, mainOptions);
		this.game=game;
	}
	
	
	public void setGame(Game game) 
	{
		this.game=game;
	}
	
	public Game getGame(Game game) 
	{
		return this.game;
	}
	
}
