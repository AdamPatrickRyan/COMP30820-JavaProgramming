package GameProject;

public class HardcorePlayer 
	extends Player
{
	//--------
	// Name:			hardCore Player Class
	// Author:			Adam Ryan
	// Created:			2021-05-10
	// Description:		This is a type of Player.
	//					It has all of the characteristics of a Player but with a type called 'Hardcore'.
	//					-Hardcore players get a modified gamertag.
	//					-They have randomness on the minimum Stake
	//
	//--------
	
	private static String type="Hardcore";

	public HardcorePlayer()
	{
		super();
	}
	
	public HardcorePlayer(String gamerTag)
	{
		super("("+type+")" + gamerTag);
	}
	
	public void setGamerTag(String gamerTag)
	{
		super.setGamerTag("("+type+")"+gamerTag);
	}
}
