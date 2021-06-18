package GameProject;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;

public class Menu 
{
	//--------
	// Name:			Menu Class
	// Author:			Adam Ryan
	// Created:			2021-04-17
	// Description:		This is the main class which is used for menus.
	//					Games and the Interface have menus.
	//					The characteristic of a menu is that:
	//					-It has options which the user should select.
	//					-It prints a Welcome Message.
	//					-It has actions which are taken after receiving an input.
	//
	//--------
	
		private String menuTitle;
		private String menuQuestion;
		private Map<Integer, String> menuOptions;

		
		public Menu() 
		{
			this("Menu","Please choose an option:\n", new HashMap<Integer,String>());
		}
		
		public Menu(String menuTitle, String menuQuestion,Map<Integer, String> optionDict) 
		{
			this.menuTitle = menuTitle;
			this.menuQuestion= menuQuestion;
			this.menuOptions = optionDict;
		}
		
		public String getTitle() 
		{
			return this.menuTitle;
		}
		
		public String getQuestion() 
		{
			return this.menuQuestion;
		}

		public Map<Integer, String> getOptionDict() 
		{
			return this.menuOptions;
		}

		
		
		
		
		public void setTitle(String menuTitle) 
		{
			this.menuTitle = menuTitle;
		}
		
		public void setQuestion(String menuQuestion) 
		{
			this.menuQuestion = menuQuestion;
		}

		public void setOptionDict(Map<Integer, String> optionDict) 
		{
			this.menuOptions = optionDict;
		}
		
		
		
		public int getEntry(Scanner input) 
		{
			int optionChosen=-1;
			
			try 
			{

				String choice = input.nextLine();
				int intChoice = Integer.parseInt(choice);

				//Remove next line
				//input.nextLine();
				
				if (this.getOptionDict().containsKey(intChoice))
				{
					
					//User did not try to quit.
					if (this.getOptionDict().get(intChoice)!="Quit")
					{
						optionChosen=intChoice;
					}
					
					//This will still be -1 if they tried to quit.
					return optionChosen;
				}
				else
				{
					System.out.println("You entered '" + choice + "' but this was not a valid option.");
				}
				return optionChosen;
				
			}
			catch (Exception except)
			{
				System.out.println("Please enter an integer.");
				return optionChosen;
			}
		}
		
		public String stringOptions() 
		{
			Map<Integer, String> optionDictionary = this.getOptionDict();
			String outputString="";
			Object[] sortedKeyArray = optionDictionary.keySet().toArray();
			Arrays.sort(sortedKeyArray);
			
			for (Object key: sortedKeyArray)
			{
				//Start from the reverse order of the hashmap to get the options in the right order
				outputString+= String.valueOf(key) + ". " + optionDictionary.get(key) + "\n";
			}
			
			return outputString;
			
		}
		
		
		@Override
		public String toString() 
		{
			return "------------------\n\nMENU: " + this.getTitle()+"\n\n------------------\n\n"
					+ this.getQuestion() + "\n" + this.stringOptions()+ "\n\n";
		}
		
}
