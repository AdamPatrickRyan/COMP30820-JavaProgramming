package GameProject;


import java.util.Random;
import java.util.Scanner;


public class CoinFlip
	extends Game

{
	
	//--------
	// Name:			CoinFlip Class
	// Author:			Adam Ryan
	// Created:			2021-05-10
	// Description:		This is the main class which is used for the CoinFlip game.
	//					The game is that you get tokens.
	//					You bet those tokens on whether you will be right or wrong with what's called
	//					If you are right, you win the amount you bet.
	//					If you are wrong, you lose twice the amount you bet.
	//					Each time you play the game, you have to bet between a minimum and your total tokens
	//					As the minimum increases (easy players) or varies (hardcore players) you need to 
	//					think carefully about whether to cash out your tokens into points, or try again.
	//					It's essentially a Martingale process and your expected value tends negative, so you need
	//					to carefull consider repeated bets.
	//					
	//
	//					The game involves a static Name
	//					A static set of instructions
	//					Starting Tokens
	//					Your current token count
	//					Your active Stake
	//					Minimum Stake
	//					A modifier for hardcore players which sets randomness on the minimum stake.
	//
	//
	//					It roughly runs by:
	//						-I always set the same starting values so call the () method.
	//						-I display instructions
	//						-I check if they're a hardcore player, and if so I modify it for subsequent bets.
	//						-
	//						-While the user wishes to continue betting, it prompts for a bet and asks them to guess
	//							heads or tails after entering the bet value.
	//						-
	//--------
	
	
	//Martinggale: Expected Value should be less than zero.
	private static String gameName="Coin Flip";
	private static String instructions="\n------------------------\n"
										+"Welcome to Coin Flip!\n\n"
										+"------------------------\n"
										
										+"Instructions:\nIn this game, you need to bet tokens on whether you can guess"
										+"if a coin will land on heads or tails."
										
										+"\n"
										
										+"-If you bet correctly, you'll get additional tokens equal to "
										+  "how much you bet.\n"
										+"-If you bet incorrectly, you'll lose twice the tokens you bet."
										+"\n\n"
										
										+"While you maintain a winning streak, the minimum amount you need to bet will change, "
										+ "so think carefully on if you want to bet again!\n\n"
						
										+"You can't bet more than the tokens you have.\n\n"
										
										
										+"At the end of the game, the tokens you have will be added as points to your Player Score, or removed if you have negative points."
										+"\n------------------------\n\n";
	
	
	//Related to Points in the Game
	//--this is purely a record of how many points you start with - It might be used elsewhere
	private int startingPoints;
	private int currentPoints;
	
	private int winningStreak;
	
	private int amountStake;
	
	private int minimumStake;
	private double minimumStakeModifier;
	
	

	public CoinFlip() 
	{
		this(10, 10, 0, 1, 0.0);
		
	}
	
	public CoinFlip(int startingPoints
					, int currentPoints
					, int winningStreak
					, int minimumStake
					, double minimumStakeModifier)
	{
		super(gameName);

		this.setMinimumStake(minimumStake);
		this.setMinimumStakeModifier(minimumStakeModifier);
		this.setStartingPoints(startingPoints);
		this.setCurrentPoints(currentPoints);
		this.setWinningStreak(winningStreak);
		
	}
	
	
	public int getStartingPoints()
	{
		return this.startingPoints;
	}
	
	public int getCurrentPoints()
	{
		return this.currentPoints;
	}
	
	
	public int getWinningStreak()
	{
		return this.winningStreak;
	}
	
	public int getAmountStake()
	{
		return this.amountStake;
	}
	
	public int getMinimumStake()
	{
		return this.minimumStake;
	}
	
	public double getMinimumStakeModifier()
	{
		return this.minimumStakeModifier;
	}
	
	
	
	public void setStartingPoints(int startingPoints)
	{
		if (startingPoints>=0)
		{
			this.startingPoints=startingPoints;
		}
		else
		{
			System.out.println("Starting Points must be positive.");
		}
	}
	
	public void setCurrentPoints(int currentPoints)
	{
		if (currentPoints>=0)
		{
			this.currentPoints=currentPoints;
		}
		else
		{
			//If it's Negative, you have no points.
			this.currentPoints=currentPoints;
		}
	}
	
	public void setWinningStreak(int winningStreak)
	{
		//If it's negative it's a losing streak so let it be negative
		this.winningStreak=winningStreak;
		
	}

	public void setMinimumStake(int minimumStake)
	{
		//Over Minimum
		if (minimumStake>=0)
		{
			
			//OverPointsTotal
			if (minimumStake>this.getCurrentPoints())
			{
				this.minimumStake=this.getCurrentPoints();
				return;
			}
			
			//Less than Points Total
			else
			{
				this.minimumStake=minimumStake;
				return;
			}
		}	
		
		//Under zero so set it to be zero.
		else
		{
			this.minimumStake=0;
		}
				
		
	}
		
		public void setMinimumStakeModifier(double minimumStakeMod)
		{
			//Over Minimum
			if (minimumStakeMod>=0.0)
			{
				
				//Over1
				if (minimumStakeMod>1.0)
				{
					this.minimumStakeModifier=1.0;
					return;
				}
				
				//Less than Points Total
				else
				{
					this.minimumStakeModifier=minimumStakeMod;
					return;
				}
			}
			else
			{
				this.minimumStakeModifier=0.0;
				return;
			}
			
		}
	
	
	public void setAmountStake(int amountStake) 
	{
		//Over Minimum
		if (amountStake>=this.getMinimumStake())
		{
			
			//Not Over Current Points Total
			if (amountStake<=this.getCurrentPoints())
			{
				this.amountStake=amountStake;
				return;
			}
			
			//Over Current Points Total.
			else
			{
				System.out.println("You can't bet more points than you have.");
				return;
			}
			
		}
		
		//Under Minimum
		else
		{
			//If it's less than the minimum give an error.
			System.out.println("You did not meet the minimum bet.");
			return;
		}
		
	}


	public String getInstructions()
	{
		return instructions;
	}	
		
	public void displayInstructions()
	{
		System.out.println(this.getInstructions());
	}
	
	
	//Return True or False
	public boolean flipCoin()
	{
		System.out.println("Flipping coin...\n");
		boolean flipCoin = new Random().nextBoolean();
		
		if (flipCoin)
		{
			System.out.println("The coin landed on Heads!");
		}
		
		else 
		{
			System.out.println("The coin landed on Tails!");
		}
		return flipCoin;	
	}
	
	//Return True or False
	public boolean getHeadsOrTails(Scanner scanner)
	{
		char firstChar='f';
		
		while (firstChar=='f')
		{
			System.out.println("Do you want to choose heads (H) or tails (T)?\n");
			String enteredValue= scanner.nextLine();
			enteredValue=enteredValue.toLowerCase();			
			
			if (enteredValue.charAt(0)=='t')
			{
				firstChar='t';

			}
			
			if (enteredValue.charAt(0)=='h')
			{
				firstChar='h';
			}
			
		}
		
		//By the time it gets here it must have been picked as either true or false
		if (firstChar=='t')
		{
			return false;
		}
		
		return true;
			
	}
	
	public boolean getContinuePlay(Scanner scanner)
	{
		char firstChar='f';
		
		while (firstChar=='f')
		{
			System.out.println("Do you want to play? Y for Yes, N for No.\n");
			String enteredValue = scanner.nextLine();
			enteredValue=enteredValue.toLowerCase();			
			
			if (enteredValue.charAt(0)=='y')
			{
				firstChar='Y';

			}
			
			if (enteredValue.charAt(0)=='n')
			{
				firstChar='N';
			}
			
		}
		
		//By the time it gets here it must have been picked as either true or false
		if (firstChar=='Y')
		{
			return true;
		}
		
		return false;
			
	}
	
	//Return True or False
	public int getUserBet(Scanner scanner)
	{
		int bet=-1;
		
		while (bet<0)
		{
			System.out.println("Please enter an amount to bet between "+ this.getMinimumStake()+" and "+ this.getCurrentPoints()+" inclusive :\n");
			String enteredValue = scanner.nextLine();
			
			try
			{			
				int intEnteredValue = Integer.parseInt(enteredValue);
				
				if (intEnteredValue>=this.getMinimumStake() & intEnteredValue<=this.getCurrentPoints())
				{
					bet=intEnteredValue;
					this.setAmountStake(bet);
	
				}
				
				else
				{
					System.out.println("You need to to bet between "+ this.getMinimumStake()+" and "+ this.getCurrentPoints()+"!\n\n");
				}
				
			}
			
			//Conversion Failure
			catch (Exception e)
			{
				System.out.println("You need to enter an integer.");
				continue;
			}

			
		}
		
		return bet;
			
	}
	
	public void setHardcoreMode(Player player)
	{
		if (player instanceof HardcorePlayer)
		{
			System.out.println("\n\nDetermined Player Detected! Modifying the game.\n\n");
			this.setMinimumStake(2);
			this.setMinimumStakeModifier(0.5);
		}
	}
	
	
	public int playGame(Scanner scanner,Player player)
	{
		
		//Instructions
		this.displayInstructions();
		
		//Enable Hardcore for bet 2+
		this.setHardcoreMode(player);
		
		//Default
		boolean continuePlay=true;
		
		while (continuePlay)
		{
			//Get the current streak
			int currentStreak=this.getWinningStreak();
		
			int newMinimumStake;
			
			//If you're on a winning streak
			if (currentStreak>0)
			{
				//Not On easy mode
				if (this.getMinimumStakeModifier()>0)
				{
					//Don't let it be zero if hardcore.
					this.setMinimumStakeModifier((Math.random()+0.000001)/2.0);
					
					//Stake + Current Streak + (Random*points)
					newMinimumStake = (int) (this.getMinimumStake()/3) + currentStreak + (int) (this.getMinimumStakeModifier() * this.getCurrentPoints());
				}
				
				//easy Mode
				else
				{
					newMinimumStake = this.getMinimumStake() + currentStreak;
				}
				
			}
			
			//LosingStreak - This also covers the first run.
			else //(currentStreak<=0)
			{
				newMinimumStake=1;
			}
			
			
			//Set the new MinimumStake
			this.setMinimumStake(newMinimumStake);
			
			//The new minimum stake is the current stake so now good to ask for bet.
			System.out.println("Your current tokens to bet in this game is: " + this.getCurrentPoints());
			int userBet=this.getUserBet(scanner);
			boolean userHeadsTails=this.getHeadsOrTails(scanner);
			boolean coinHeadsTails=this.flipCoin();
			
			
			
			//User Wins
			if (coinHeadsTails==userHeadsTails)
			{
				System.out.println("\nCongrats! You betted correctly! You gain " + userBet +" tokens.\n");
				this.setCurrentPoints(this.getCurrentPoints() + userBet);
				
				//If they had been on a losing streak we need to set it to zero first
				//This could be handled in the method but originally I was going to look at the step change.
				if (currentStreak<0)
				{
					currentStreak=0;
				}
				
				this.setWinningStreak(currentStreak+1);
				
			}
			
			//Loses
			else
			{
				System.out.println("\nUnlucky! You were wrong! You lose " + 2*userBet +" tokens.\n");
				
				//remove the tokens
				this.setCurrentPoints(this.getCurrentPoints() - 2*userBet);
				
				//Reset the streak
				if (currentStreak>0)
				{
					currentStreak=0;
				}
				
				//Ensure threy're back to a negative (i.e. loss) streak position
				this.setWinningStreak(currentStreak-1);
		
			}
			
			//Inform of new balance
			System.out.println("\nYou now have: "+this.getCurrentPoints()+" tokens.\n\n");
			
			//If you lost all your tokens, game over. You don't get to retry with a negative token balance.
			if (this.getCurrentPoints()<=0)
			{
				//Technically you can start it again from the menu, but I don't want to lock the game for good.
				System.out.println("You have too few tokens to start again. You bet large, but it didn't pay off.\n"
						+ "Your user score is being updated.\n"
						+ "Leaving game."
						+ "\n"
						+"------------------\n"
						+ "\n\n");
				continuePlay=false;
			}
			
			//If you still have tokens left you've another chance to play.
			else
			{
				continuePlay=this.getContinuePlay(scanner);
			}
			
		}
		
		
		//Send back the final points total to update the player 
		//--You could also pass in the player and update here but I wanted to separate points and tokens.
		return this.getCurrentPoints();
	}
	
	
	
}

