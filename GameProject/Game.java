package GameProject;

public class Game 
{
	//--------
	// Name:			Game Class
	// Author:			Adam Ryan
	// Created:			2021-05-10
	// Description:		This is the main class which is used for Games.
	//					The characteristic of a game is that:
	//					-It has a name.
	//					-It has a menu (play game or quit)
	//					-As noted the play game element could be added here as an abstract method, but originally
	//					I had different intentions in how it'd be called, but it would make the calling tidier.
	//
	//--------


	private String gameName;
	private gameMenu gameMenu;
	
	public Game()
	{
		this("Default Game");
	}

	public Game(String gameName)
	{
		this.setGameName(gameName);
	}
	
	public void setGameName(String gameName)
	{
		this.gameName=gameName;
	}
	
	
	public String getGameName()
	{
		return this.gameName;
	}
	
	public void setGameMenu(gameMenu gameMenu)
	{
		this.gameMenu=gameMenu;
	}
	
	
	public gameMenu getGameMenu()
	{
		return this.gameMenu;
	}
	
	
	@Override
	public String toString()
	{
		return this.getGameName() + "\n";
	}
	
	
}
