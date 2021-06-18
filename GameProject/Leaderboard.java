package GameProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Leaderboard {
	
	//--------
	// Name:			Leaderboard Class
	// Author:			Adam Ryan
	// Created:			2021-05-10
	// Description:		This is the main class which is used for leaderboards.
	//					The characteristic of a leaderboard
	//					-It has a filepath.
	//					-It has Players
	//
	//
	//
	//
	//--------

	private static String fileDelimiter="|"; //Maybe update this later - Caused issues with the split regex reading this.
	
	private String leaderboardFilepath;
	
	//Technically I could use a getter and setter on this, but it was handier to just make it public.
	public ArrayList<Player> playerArrayList = new ArrayList<Player>();
	
	
	public Leaderboard(String filepath)
	{
		this.setLeaderboardFilepath(filepath);
	}
	
	
	public void setLeaderboardFilepath(String filepath)
	{
		//Get the file
		File leaderboardFileLocal = new File(filepath);
		
		//Exists?
		boolean fileExists=validateFile(leaderboardFileLocal);
		
		//File Exists - Set it.
		if (fileExists)
		{
			this.leaderboardFilepath=filepath;
			return;
		}
		
		//Does not exist - Make then set it
		else
		{
			this.createBlankLeaderboardFile(leaderboardFileLocal);
			this.leaderboardFilepath=filepath;
			return;
		}
		
	}
	
	public String getLeaderboardFilepath()
	{
		return this.leaderboardFilepath;
	}
		
	


	
	//Reusing the GetFile Method from my MA3 assignment.
	public Boolean validateFile(File inputFile)
	{
		//print the error if it does not exist
		if (!inputFile.exists())
		{
			System.out.println("Warning: A leaderboard does not exist!");
		}
		
		return inputFile.exists();
	}
	

	public void createBlankLeaderboardFile(File inputFile)
	{
		try 
		{
			//create the file
			inputFile.createNewFile();
			
			//Set up a writer
			FileWriter myWriter = new FileWriter(inputFile.getPath());
			
			//Add header
			myWriter.write("Gamertag|Points\n");
			
			//end
			myWriter.close();
			
			return;
		} 
		
		catch (IOException Exc) 
		{
			Exc.printStackTrace();
		}
		
	}
	
	public void readPlayersFromLeaderboard()
	{
		String filepath=this.leaderboardFilepath;
		
		try 
		{
			//Only to track header line
			int lineNumber=0;
			
			//Import the File from filepath
			File importFile = new File(filepath);
			
			//Scanner on File
			Scanner file = new Scanner(importFile);
			
			//while there are lines in the file
			while (file.hasNextLine())
			{
				//Get next line
				String line=file.nextLine();
				
				//Has players in line
				if (lineNumber>0 & line.contains("|")&line.length()>1)
				{
					
					//Split it - Gamertag can't have a pipe in it so all good.
					String[] gamertagScore=line.split("\\|",2);
					
					//First piece
					String gamertag=gamertagScore[0];
					
					//Score
					int score= Integer.parseInt(gamertagScore[1]);
					
					//Now I can init the player
					Player linePlayer= new Player(gamertag);
					
					//I set the score using the setter explicitly as default session score is zero
					linePlayer.setSessionScore(score);
					
					//Push to array
					this.playerArrayList.add(linePlayer);
				}
				
				lineNumber+=1;
				
			}
			file.close();
					
		}
		catch (IOException except)
		{
			except.printStackTrace();
		}
	}
	
	public void addPlayertoLeaderboardArray(Player player)
	{
		
		try 
		{
			this.playerArrayList.add(player);
		}
		
		catch (Exception except)
		{
			except.printStackTrace();
		}
	}
	
	public void sortLeaderBoard()
	{
		//Comparable Implemented so just put it in descending order by Score.
		this.playerArrayList.sort(Comparator.reverseOrder());
	}
	
	public String toFileFormat()
	{
		
		String outputString="Player|Score\n";
		String lineString="";
		
		//AddTheGamerTag and Score to the String
		for (Player gamePlayer : this.playerArrayList) 
		{ 		      
	           lineString+= gamePlayer.getGamerTag() +"|" + gamePlayer.getSessionScore()+"\n";
	    } 
		
		outputString+=lineString;
		
		return outputString;
	}
	
	public void overwriteLeaderboard()
	{
		
		
		//Set to overwrite the existing one.
		try 
		{
			FileWriter myWriter = new FileWriter(this.getLeaderboardFilepath(),false);
			
			//Sorted Leaderboard
			myWriter.write(this.toFileFormat());
			
			//Close it
			myWriter.close();
		}
		
		catch (IOException except)
		{
			except.printStackTrace();	
		}
		
	}
	
	@Override
	public String toString()
	{
		
		String outputString="Player\t\t\t\t\t\t\tScore\n";
		String lineString="";
		
		//AddTheGamerTag and Score to the String
		for (Player gamePlayer : this.playerArrayList) 
		{ 		      
	           lineString+= gamePlayer.getGamerTag() +"\t\t\t\t\t\t" + gamePlayer.getSessionScore()+"\n";
	    } 
		
		outputString+=lineString;
		
		return outputString;
	}
	
}
