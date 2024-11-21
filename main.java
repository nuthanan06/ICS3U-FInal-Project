
/*
 * Date: Friday, June 3, 2022
 * Author: Nuthanan Tharmarajah
 * Description: Welcome to The Guessers! Here you will challenge your luck through 3 games, Mastermind, Lucky Seven and Wordle! Think you have the courage to challenge the game? Good luck! 
 */

import java.util.Scanner;
import java.util.Random; 
import java.lang.Math; 
import java.text.DecimalFormat; 

public class ICS3U_FP_Code_Nuthanan {
	
	static double amountOfGames = 0;
	static double wins = 0; 
	
	public static void main(String[] args) {

		
		//declaring and displaying introduction
		System.out.println("Welcome to The Guessers, where luck defies logic.");
		System.out.println("Here, you have the opportunity to play 3 different games, and test your luck and logic.");
		Scanner input = new Scanner(System.in); 
		
		//declaring main menu 
		mainMenu(input); 
	}
	
	/*
	 * displays the main menu, and provides options for user to select from
	 * pre: makes sure input has the scanner class
	 * post: main menu and options
	 */
	public static void mainMenu(Scanner input) {
		
		
		//declaring variables and displaying border
		int option; 
		DecimalFormat percentage = new DecimalFormat("0.00%"); 
		System.out.println("--------------------------------------------------------------------------------\n");
		
		
		do {
			//displaying main menu
			System.out.println("[1] - Mastermind");
			System.out.println("[2] - Wordle"); 
			System.out.println("[3] - Lucky Seven"); 
			System.out.println("[4] - Help"); 
			System.out.println("[5] - Exit\n"); 
			
			//getting data
			System.out.print("Enter an option: ");
			option = input.nextInt(); 
			input.nextLine(); 
			if (option > 5 || option < 1) {
				System.out.println("Your option is out of range! Try again. \n"); 
			}

		} while (option > 5 || option < 1); 
		
		//checks which game user wants to play
		if (option == 1) {
			mastermindGame(input); 
		}
		else if (option == 2) {
			wordleGame(input); 
		}
		else if (option == 3){
			luckySevenGame(input); 
		}
		else if (option == 4) {
			helpButton(input);
		}
		else {
			
			//displays final messages and statistics
			System.out.println("--------------------------------------------------------------------------------\n");
			System.out.println("Thank you for trying The Guessers out!\n");
			System.out.println("STATISTICS: ");
			System.out.println("You played " + (int) amountOfGames + " rounds of games!");
			System.out.println("Out of all of the rounds, you won " + (int) wins + " rounds!");
			if (!(amountOfGames == 0)) {
				System.out.println("In percentage that is " +  percentage.format((wins/amountOfGames)) + "!"); 
			}
			System.out.println("\nRemember, to challenge your luck and curiosity. :D");
		}
		
	}
	
	/*
	 * proceeds to the mastermind game
	 * pre: makes sure scanner is an input
	 * post: displays and plays game
	 */
	
	public static void mastermindGame(Scanner input) {
		
		
		//declaring variables
		int numbersNeeded, counter, points; 
		int[] codeNumbers, userInputNumbers; 
		boolean running = true, playAgain = true, repetitive; 
		String playAgainUser, repetitiveUser, option = ""; 
		String[] guesses;
		int[] rightPosition, rightNumber; 
		
		//displaying instructions
		System.out.println("--------------------------------------------------------------------------------\n");
		System.out.println("Welcome to Mastermind! Here are the instructions, pay attention carefully!\n");
		System.out.println("\t1. You will first be prompted to enter how many one digit numbers (1-9)\n\t   the secret code should have and whether the digits can repeat in the secret code.");
		System.out.println("\t2. Afterwards, the computer will generate a secret code based on your responses. \n\t   Your job is to identify the secret code of numbers that the computer generated.");
		enterToContinue(input); 
		System.out.println("\t3. To identify the code, you are prompted to enter a set of numbers. In order to input \n\t   your guess at the code, you would put one-digit integers, followed by a space \n\t   separating the digits. Make sure that the number of digits you enter corresponds \n\t   with the number of digits the code has.");
		System.out.println("\t\ta. Example: if the secret code has 4 one-digit numbers, “1 2 3 4” is an example of a valid \n\t\t   input. ");
		System.out.println("\t\tb. If you enter any letters, the program will ignore such letters, and check what numbers are present.");
		System.out.println("\t\tc. Once you enter your guess at the code, the computer will provide feedback based on how many \n\t\t   numbers are correct and whether they are in the right position.");
		System.out.println("\t\t\ti. The thing is, you will not know what positions are correct, or what numbers \n\t\t\t   are correct."); 
		enterToContinue(input); 
		System.out.println("\t4. Using the feedback, you can guess up to 10 times to try to crack the code. If you cannot get the code \n\t   within 10 tries, you lose the game. ");
		System.out.println("\t\ta. If you input \"guess\", all of the guesses you entered with the feedback will be displayed in a table. ");
		System.out.println("\t\tb. If you feel like giving up, input \"give up\". Don't worry, try again next time. :)"); 
		System.out.println("\t5. There is also a point system. You will start off with 10 points, and for every guess you make \n\t   will result in 1 point being taken away."); 
		enterToContinue(input); 
		
		while (playAgain) {
			
			//getting and initializing data;
			amountOfGames++;
			counter = 0; 
			points = 10; 
			running = true; 
			guesses = new String[10]; 
			rightPosition = new int[10]; 
			rightNumber = new int[10]; 
			repetitive = true; 

			//gets input on the length of the code
			System.out.println("--------------------------------------------------------------------------------\n");
			do {
				System.out.print("How many numbers would you like the length of the code to be (1-9): ");
				numbersNeeded = input.nextInt(); 
				input.nextLine(); 
				if (numbersNeeded < 1) {
					System.out.println("Please enter a number that is a positive integer.");
					
				}
				else if (numbersNeeded > 9) {
					System.out.println("Please enter a number less than 10.");
				}
			} while (numbersNeeded < 1 || numbersNeeded > 9); 
			
			//gets input of whether the user would like repetitive numbers in the code
			do {
				System.out.print("Would you like repetitive numbers in the code (yes/no): ");
				repetitiveUser = input.nextLine(); 		
				if (repetitiveUser.equalsIgnoreCase("yes")) {
					repetitive = true;
				} 
				else if (repetitiveUser.equalsIgnoreCase("no")) {
					repetitive = false; 
				}
				else {
					System.out.println("\nPlease enter either yes or no.");
				}
				
			} while (!(repetitiveUser.equalsIgnoreCase("yes")) && !(repetitiveUser.equalsIgnoreCase("no")));

			
			codeNumbers = new int[numbersNeeded]; 
			
			//generating numbers 
			if (repetitive) {
				generateRepetativeNumbers(numbersNeeded, codeNumbers); 
			}
			else {
				generateNumbers(numbersNeeded, codeNumbers); 
			}
			
			//game begins
			while (running && counter < 10) {
				counter++;
				userInputNumbers = new int[numbersNeeded]; 
				option = gettingUserInput(input, userInputNumbers, numbersNeeded, counter, guesses, rightPosition, rightNumber);
				if (option.equalsIgnoreCase("true")) {
					counter--; 
				}
				else if (option.equalsIgnoreCase("give up")) {
					running = false; 
				}
				else {	
					running = checkUserInput(codeNumbers, userInputNumbers, numbersNeeded, rightPosition, rightNumber, counter);
					if (running) {
						points--; 
					}
				}
			}
			
			//checks if the user won or not
			System.out.println("--------------------------------------------------------------------------------\n");
			if (counter == 10 && running) {
				System.out.print("Uh oh! You ran out of guesses! You lost the game. The code was: "); 
				for (int x = 0; x < codeNumbers.length; x++) {
					System.out.print(codeNumbers[x] + " ");
				}
				System.out.println("\nYou got " + points + "/10 points. Better luck next time!");
			}
			else if (option.equalsIgnoreCase("give up")) {
				System.out.print("Nice try! The code was: "); 
				for (int x = 0; x < codeNumbers.length; x++) {
					System.out.print(codeNumbers[x] + " ");
				}
				System.out.println(); 
			}
			else {
				if (points > 5) {
					System.out.println("Great work! You got " + points +"/10 points, which is " + (points)*10 + "%! Keep it up!"); 
				}
				else {
					System.out.println("Well done! You got " + points +"/10 points, which is " + (points)*10 + "%! Let's get that score up!"); 
				}
				wins++; 
			}
			
			playAgain = playAgain(input);
		}
		
		
		
	}
	
	/*
	 * allows user to click enter rather than overloading with information
	 * pre: input is scanner
	 * post: allows user to enter once information has been read
	 */
	
	public static void enterToContinue(Scanner input) {
		
		System.out.print("\nPRESS ENTER TO CONTINUE: "); 
		input.nextLine(); 
		System.out.print("\n"); 
	}
	
	/*
	 * generates the code without any repetition
	 * pre: input is scanner, numbersNeeded is integer, the code number is an integer array
	 * post: generates integers in codeNumbers
	 */
	
	public static void generateNumbers(int numbersNeeded, int[] codeNumbers) {
		
		//declaring variables
		int index = 0; 
		int[] listOfNumber = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		boolean isRepeat; 
		Random random = new Random(); 
		
		
		//generates numbers and ensures that there are no repetitions in the code 
		for (int x = 0; x < numbersNeeded; x++) {
			index = random.nextInt(9); 
			isRepeat = false; 
			for (int y = 0; y < codeNumbers.length; y++) {
				if (codeNumbers[y] == listOfNumber[index]) {
					isRepeat = true; 
				}
			}
			//checks to see if the number is in the code
			if (!(isRepeat)) {
				codeNumbers[x] = listOfNumber[index];
			}
			else {
				x--; 
			}		
		}
	}
	
	
	/*
	 * generates the code with repetition
	 * pre: input is scanner, numbersNeeded is integer, the code number is an integer array
	 * post: generates integers in codeNumbers
	 */
	public static void generateRepetativeNumbers(int numbersNeeded, int[] codeNumbers) {
		
		Random random = new Random(); 
		for (int x = 0; x < numbersNeeded; x++) {
			codeNumbers[x] = random.nextInt(9) + 1; 
		}
		
	}
	
	/*
	 * gets the user input
	 * pre: input is scanner, userInputNumbers is an integer array, numbersNeeded and counter are integers
	 * post: gets user input
	 */
	public static String gettingUserInput (Scanner input, int[] userInputNumbers, int numbersNeeded, int counter, String[] guesses, int[] rightPosition, int[] rightNumber) {
		
		//declaring variables
		int indexOfList, counterNumbers;
		String guessNumber, userNumberEntered = ""; 
		
		//getting input 
		System.out.print("--------------------------------------------------------------------------------\n"); 
		do {
			counterNumbers = 0; 
			System.out.print("GUESS #" + counter + " - Enter your guess: "); 
			guessNumber = input.nextLine();
			//checks to see if they would like a table of guesses or give up
			guessNumber = guessNumber.trim();
			
			if (guessNumber.equalsIgnoreCase("guess")) {
				displayPreviousGuesses(guesses, rightPosition, rightNumber);
				return "true"; 
			} 
			else if (guessNumber.equalsIgnoreCase("give up")) {
				return "give up";
			}
			else {	
				for (int x = 0; x < guessNumber.length(); x++) {
					//checks to see if it is a valid number
					if ((int) guessNumber.charAt(x) > 48 && (int) guessNumber.charAt(x) < 58) {
						counterNumbers++; 
					}
				}
				
				//if the numbers are not valid, then program will provide feedback 
				if (counterNumbers > numbersNeeded) {
					System.out.println("We noticed that you entered more numbers than what is needed or you entered invalid characters. You need to have " + numbersNeeded + " digits in your guess."); 
				}
				else if (counterNumbers < numbersNeeded) {
					System.out.println("We noticed that you entered less numbers than what is needed or you entered invalid characters. You need to have " + numbersNeeded + " digits in your guess."); 
				}
			}
		} while (!(counterNumbers == numbersNeeded)); 
		
		//converts string into an array integer using ASCII
		indexOfList = 0;
		for (int x = 0; x < guessNumber.length(); x++) {
			if ((int) guessNumber.charAt(x) > 48 && (int) guessNumber.charAt(x) < 58) {
				userInputNumbers[indexOfList] = (int) guessNumber.charAt(x) - 48; 
				indexOfList++; 
				userNumberEntered += guessNumber.substring(x, x + 1) + " "; 
			}
		}
		
		//adds guess to all the guesses array
		guesses[counter-1] = userNumberEntered; 
		return "false"; 
	}
	
	/*
	 * compares whether the code numbers and user input numbers are equal, if not, which numbers are in the right position, or in the code
	 * pre: codeNumbers and userInputNumbers are integers arrays, numbersNeeded is an integer value
	 * post: returns false or true
	 */
	
	public static boolean checkUserInput(int[] codeNumbers, int[] userInputNumbers, int numbersNeeded, int[] rightPosition, int[] rightNumber, int counter) {
		
		//declaring variables
		int numbersInCorrectPosition = 0, numbersInCode = 0; 
		int[] temporaryCodeNumbers = new int[codeNumbers.length]; 
		
		//extracts the code number to the temporary code numbers
		for (int x = 0; x < codeNumbers.length; x++) {
			temporaryCodeNumbers[x] = codeNumbers[x];
		}
		
		//checks to see if there are numbers in the correct position
		for (int x = 0; x < userInputNumbers.length; x++) {
			if (temporaryCodeNumbers[x] == userInputNumbers[x]) {
				numbersInCorrectPosition++;
				temporaryCodeNumbers[x] = 99; 
				userInputNumbers[x] = 100; 
			}
		}
		
		//checks to see if the numbers are in the code 
		for (int x = 0; x < userInputNumbers.length; x++) { 
			for (int y = 0; y < temporaryCodeNumbers.length; y++) {
				if (userInputNumbers[x] == temporaryCodeNumbers[y]) {
					numbersInCode++; 
					userInputNumbers[x] = 100;
					temporaryCodeNumbers[y] = 99; 
					y = codeNumbers.length; 

				}
			}
		}
		
		//inserts number in position and numbers in code into the array
		rightPosition[counter-1] = numbersInCorrectPosition;
		rightNumber[counter-1] = numbersInCode; 
		
		//checks to see if user got code or not
		if (numbersInCorrectPosition == numbersNeeded) {
			System.out.println("You got the code!");
			return false; 
		}
		else {
			if (numbersInCorrectPosition == 1) {
				System.out.println("\nThere is " + numbersInCorrectPosition + " in the correct position."); 
			}
			else {
				System.out.println("\nThere are " + numbersInCorrectPosition + " in the correct position."); 
			}
			if (numbersInCode == 1) {
				System.out.println("There is " + numbersInCode + " that is in the code."); 

			}
			else {
				System.out.println("There are " + numbersInCode + " that are in the code."); 
			}
			return true; 
		}
	}
	
	/*
	 * displays previous guesses
	 * pre: guesses is a string array, and rightPosition and rightNumber are integer arrays
	 * post: the table with all guesses included
	 */
	public static void displayPreviousGuesses(String[] guesses, int[] rightPosition, int[] rightNumber) {
		
		//displays table
		System.out.print("--------------------------------------------------------------------------------\n"); 
		System.out.println("Here are your guesses: \n");
		System.out.println("Guess: \t\tRight Place: \tRight Number: "); 
		
		for (int x = 0; x < guesses.length; x++) {
			if ((!(guesses[x] == null))) {
				System.out.print(guesses[x] + "\t" + rightPosition[x] + "\t\t" + rightNumber[x]);
				System.out.println(); 	
			}
		}
	}
	
	/*
	 * checks to see if user wants to play again
	 * pre: input is apart of scanner class
	 * post: determines whether the user wants to play again or not
	 */
	
	public static boolean playAgain(Scanner input) {
		
		//initializing variables
		String playAgainUser; 
		
		//checks to see if user wants to play again
		do {
			System.out.print("Would you like to play again (yes/no): ");
			playAgainUser = input.nextLine();
			
			if (playAgainUser.equalsIgnoreCase("yes")) {
				System.out.println("\nAlright, playing again!");
				return true; 

			}
			else if (playAgainUser.equalsIgnoreCase("no")) {
				System.out.println("\nBringing you back to the main menu!");
				mainMenu(input); 
			}
			else {
				System.out.println("\nPlease enter either yes or no. "); 
			}
		
		} while (!(playAgainUser.equalsIgnoreCase("yes")) && !(playAgainUser.equalsIgnoreCase("no")));
		
		return false; 

	}
	
	/*
	 * displays and proceeds with wordle game
	 * pre: input is scanner class
	 * post: proceed and plays wordle game with user
	 */
	
	public static void wordleGame(Scanner input) {
		
		//declaring variables
		String word, playAgainUser; 
		String[] userInputList, responses; 
		boolean running, gameRun = true; 
		int counter; 
		
		//displaying instructions
		System.out.println("--------------------------------------------------------------------------------\n");
		System.out.println("Welcome to Wordle! Here are the instructions, pay attention carefully!\n");
		System.out.println("\t1. You will be prompted to enter five-letter words. Afterwards, the program will then provide feedback\n\t   on which letters are correct and whether the letter is in the right or wrong position.");
		System.out.println("\t2. If you manage to get the hidden word within 6 tries, you win the game, else, you would lose the game.");
		System.out.println("\t3. Here are the symbols you should pay attention to, which will be in the feedback provided:"); 
		System.out.println("\t\ta. If you see “-”, that means that letter is not in the word."); 
		System.out.println("\t\tb. If you see “?”, that means the letter is in the word, just not in the right spot.");
		System.out.println("\t\tc. If you see “!”, the letter is right and in the right spot. "); 
		System.out.println("\t4. Please remember to enter letters, if you enter numbers or symbols, you will be prompted to enter again."); 
		
		enterToContinue(input); 
		
		//game loop begins
		while (gameRun) {
	
			//initializing values to variables 
			amountOfGames++;
			word = generateWord();
			userInputList = new String[6]; 
			responses = new String[6]; 
			counter = 0; 
			running = true; 
			
			while (running && counter < 6) {
				
				//gets input and checks while responding with appropriate feedback
				System.out.println("--------------------------------------------------------------------------------\n");
				displayBoard(userInputList, responses);
				System.out.println(" "); 
				inputWordle(input, userInputList, counter); 
				if (userInputList[counter].equalsIgnoreCase(word)) {
					running = false; 
				}
				else {
					checkWordle(responses, counter, userInputList, word);
				}
				counter++; 
				
				
			}
			
			//checks to see if user gets the word or not
			System.out.print("--------------------------------------------------------------------------------\n"); 
			if (running) {
				System.out.println("Oh no! You lost! The word was \"" + word + "\"!"); 
				System.out.println("Better luck next time!"); 
			}
			else {
				System.out.println("You won! Congratulations!"); 
				wins++;
			}
			
			gameRun = playAgain(input);
		}


	}
	
	/*
	 * generates a word for the wordle game from a list 
	 * pre: none
	 * post: returns a word from a list 
	 */
	public static String generateWord() {
		
		String[] wordList = {"lucky", "logic", "codes", "water", "bloom", "trait", "world", "apple", "phone", "stair", "adieu", "watch", "latch", "japan", "price", "extra", "equip", "witch", "false", "pizza"};
		Random random = new Random(); 
		
		return wordList[random.nextInt(19)]; 
	
	}
	
	/*
	 * displays all of the previous guesses made by the user for the wordle
	 * pre: userInputList and responses are arrays that have string values
	 * post: displays the previous guesses to the user
	 */
	
	public static void displayBoard(String[] userInputList, String[] responses) {
		
		//declaring variables
		int length; 
		String currentString, response; 
		
		//displays list if the array has a value
		for (int x = 0; x < 6; x++) {
			if (!(userInputList[x] == null)) {
				length = userInputList[x].length(); 
				currentString = userInputList[x]; 
				response = responses[x]; 
				for (int y = 0; y < length; y++) {
					System.out.print(currentString.charAt(y) + " "); 
				}
				System.out.println(); 
				for (int y = 0; y < length; y++) {
					System.out.print(response.charAt(y) + " "); 
				}
				System.out.println(); 
				System.out.println(); 
			}
		}
		
	}
	
	/*
	 * allows user to input a word and checks to see if user inputted an appropriate word
	 * pre: input is a scanner class, userInputList is an array with string values, and counter is an integer value
	 * post: gets the users guess and inputs in array
	 */
	
	public static void inputWordle(Scanner input, String[] userInputList, int counter) {
		
		//declaring variables
		String userGuess; 
		int counterOfRealLetters; 
		
		//getting input 
		do {
			
			System.out.print("GUESS #" + (counter + 1) + " - Enter a word: ");
			userGuess = input.nextLine(); 
			userGuess = userGuess.trim(); 
			
			//checks to see if characters are valid 
			counterOfRealLetters = 0;
			for (int x = 0; x < userGuess.length(); x++) {
				if ((int) userGuess.charAt(x) > 64 && (int) userGuess.charAt(x) > 64 || (int) userGuess.charAt(x) > 97 && (int) userGuess.charAt(x) > 122) {
					counterOfRealLetters++; 
				}
			}
			//checks to see if input is valid
			if (!(userGuess.length() == 5)) {
				System.out.println("You entered a word that is not 5 letters long. Try again.\n");
			}
			else if (!(counterOfRealLetters == 5)) {
				System.out.println("You entered symbols or numbers in the word. Please ensure you are using letters. Try again. \n"); 
			}
			
		} while (!(userGuess.length() == 5) || !(counterOfRealLetters == 5)); 
		
		//stores users guess into an array
		userInputList[counter] = userGuess; 
	}
	
	/*
	 * checks to see the wordle and provide appropriate feedback
	 * pre: responses and userInputList are arrays and have string values, counter is an integer value, and a word is a string
	 * post: checks and assigns the response in an array
	 */
	
	public static void checkWordle(String responses[], int counter, String[] userInputList, String word) {
		
		//declaring variables
		String currentWord = userInputList[counter]; 
		String[] userWordArray = new String[5]; 
		String[] actualWordArray = new String[5]; 
		String[] response = new String[5]; 
		boolean running;
		String responseString = ""; 
		
		//inserts both the user word and the actual word into the console 
		for (int x = 0; x < currentWord.length(); x++) {
			userWordArray[x] = currentWord.substring(x, x+1); 
			actualWordArray[x] = word.substring(x, x+1); 
		}
		
		//compares to see which characters are in the right position 
		for (int x = 0; x < userWordArray.length; x++) {
			 if (userWordArray[x].equalsIgnoreCase(actualWordArray[x])) {
				 response[x] = "!"; 
				 userWordArray[x] = "0";
				 actualWordArray[x] = "1"; 
			 }
		}
		
		//checks to see if the letter is in the word just not in the right position 
		for (int x = 0; x < userWordArray.length; x++) {
			running = true; 
			for (int y = 0; y < actualWordArray.length && running; y++) {
				if (userWordArray[x].equalsIgnoreCase(actualWordArray[y])) {
					running = false; 
					response[x] = "?"; 
					userWordArray[x] = "0"; 
					actualWordArray[y] = "1"; 
				}
			}
		}
		
		//fills the rest of the response with "-" to indicate not in the word 
		for (int x = 0; x < response.length; x++) {
			if (response[x] == null) {
				response[x] = "-"; 
			}
			responseString += response[x]; 
		}
		
		responses[counter] = responseString; 
		
	}
	
	/*
	 * starts the lucky seven game 
	 * pre: input is a scanner class
	 * post: proceeds with the lucky seven game
	 */
	public static void luckySevenGame (Scanner input) {
		
		//declaring variables
		Random random = new Random(); 
		DecimalFormat rounding = new DecimalFormat("$0,000"); 
		int drawNumber, price, money, counter, exponent, digit; 
		String carDescription;
		boolean gameRun = true;

		//displaying instructions
		System.out.println("--------------------------------------------------------------------------------\n");
		System.out.println("Welcome to Lucky Seven! Here are the instructions, pay attention carefully!\n");
		System.out.println("\t1. At the start of the game, you will first be given 7 dollars, and the description of a \n\t   car you will be trying to win. You would then be given the first digit of the price of the car."); 
		System.out.println("\t2. You would then input a one-digit number that you think is the next digit of the price of\n\t   the car. NOTE: The car price is with tax.");
		System.out.println("\t\ta. The goal of the game is to be on the digit or get as close to the digit as possible. \n\t\t   If the digit is not correct, you will have to pay the difference from your guess \n\t\t   to the actual digit in the price, using the seven dollars they have.");
		System.out.println("\t3. The game will proceed until all digits in the price are uncovered or you do not have any money\n\t   left, in which case they lose the game. If all digits are uncovered, and you have at least\n\t   a dollar left, you win the game. \n"); 
		enterToContinue(input); 
		
		//starts the game loop
		while (gameRun) {
			
			//declaring variables
			amountOfGames++;
			drawNumber = random.nextInt(9); 
			money = 7; 
			carDescription = carGenerator(drawNumber); 
			price = priceGenerator(drawNumber); 
			counter = 1; 
			exponent = 10000;
			
			//displaying the car model 
			System.out.println("--------------------------------------------------------------------------------\n");
			System.out.println("Your car is...\n");
			System.out.println(carDescription); 
			enterToContinue(input); 
			
			//runs the loop that gets the users guesses
			while (!(price < 10) && money > 0) {
				System.out.println("--------------------------------------------------------------------------------\n");
				System.out.println("Digit #" + counter + " is " + (price/exponent) + "."); 
				price %= exponent; 
				exponent /= 10; 
				digit = luckySevenInput(input, counter); 
				money = checkDigit(price, exponent, digit, money);
				counter++;
			}
			
			//checks to see if the user won or not 
			System.out.println("--------------------------------------------------------------------------------\n");
			if (counter == 5 && money > 0) {
				System.out.println("Congratulations! All the digits have been revealed, the price was " + rounding.format(priceGenerator(drawNumber))+ "! You had $" + money + " left! \nYou won the car!");
				wins++; 
			}
			else {
				System.out.println("Tough luck! You don't have a dollar left! The price was " + rounding.format(priceGenerator(drawNumber)) + ". Better luck next time!");
			}
			
			gameRun = playAgain(input); 
		}
	}
		
	/*
	 * generates a car out of 10 options according to the draw number
	 * pre: the variable drawNumber is type integer
	 * post: generates and returns the car model 
	 */
	
	public static String carGenerator(int drawNumber) {
		String[] cars = {"A new A6 Audi Sedan! This car comes with a bunch of features, including horsepower that is up to 335HP, a high\nresolution screen with 3 display modes, and a driver assistance program to ensure that you are safe on the road!\nThis could be all yours on the Price is Right!",
				"A new Chevrolet Spark! This car comes with enhanced technology, including a diagonal colour touchscreen, also comes \nwith WiFi and Bluetooth capabilities! This could be all yours on the Price Is Right!",
				"A new Mitsubishi Mirage! This stylish and classy car can get you around anywhere, with state of the art technology, \nfuel efficent vehicle, this vehicle has a classic look to it!", 
				"A new Hyundai Accent SE! This car has a 5 inch colour touchscreen display audio, a rear view mirror,\na hands free phone system, and incredible technology that can get you everywhere!", 
				"A new Nissan Versa! This new car comes with state of the art technology allowing you to connect with others, with \nefficent performance! It even comes with a safety feature to get you to your destination much quickly!",
				"A new Honda Civic Sedan LX! This car has incredible assistance feature promoting safety, with an added \ninterior design and an elegant design!",
				"A new Hyundai SmartSense! This car comes with an incredible interior design, safety features allowing you to \nkeep you safe, like driver attention warning, while also having technology that is made for connectivity \nand high performance.",
				"A new Kia Forte! This car has immense driver assisting technologies, with a sport like car design in mind, this car \nis made to perform and is very purposeful!",
				"A new Volkswagen Jetta! This car has incredible power capabilities, and immense comfort in the car! Techonology \ninside the car is at its top level, with features including keyless control!",
				"A new Hyundai Elantra! This car makes sure your connected and comforted through its state of the art technology \nand safety features! The design is made so that you can feel comfortable, when you are on the road! "};
			
		return cars[drawNumber]; 
	}
	
	/*
	 * generates the price of the car out of 10 options according to the draw number
	 * pre: drawNumber is a variable that is type integer
	 * post: returns the price of the car
	 */
	
	public static int priceGenerator(int drawNumber) {
		int[] price = {73789, 18198, 14098, 16645, 17298, 25370, 40349, 18295, 22895, 20553};
		return price[drawNumber];
	}
	
	/*
	 * gets the user input on the digit they desire 
	 * pre: input is type scanner and counter is type integer
	 * post: returns the digit the user would like
	 */
	
	public static int luckySevenInput(Scanner input, int counter) {
		
		//declaring variables
		int digitNumber; 
		
		do {
			//getting input
			System.out.print("\nWhat do you think digit #" + (counter + 1) + " is: ");
			digitNumber = input.nextInt();
			input.nextLine(); 
			//checks to see if input is a valid digit 
			if (digitNumber > 9 || digitNumber < 0) {
				System.out.println("That is not a valid number. Try again.");
			} 
		} while (digitNumber > 9 || digitNumber < 0); 
		
		return digitNumber; 
		
	}
	
	/*
	 * checks whether the digit corresponds to the price digit, if not removes dollars depending on the difference
	 * pre: price, exponent, digit and dollars are type integer
	 * post: returns the amount of dollars that remain 
	 */
	public static int checkDigit(int price, int exponent, int digit, int dollars) {
		
		//declaring variables
		int priceDigit = price/exponent; 
		
		//checks to see whether the digit is correct or not, in which the difference is calculated 
		if (priceDigit == digit) {
			System.out.println("\nYou got the correct digit! You are still at $" + dollars + "!"); 
			return dollars;
		}
		dollars -= Math.abs(priceDigit - digit); 
		System.out.println("\nOh no! The digit was " + priceDigit + "!"); 
		if (dollars < 0) {
			System.out.println("You lost $" + Math.abs(priceDigit-digit) + " dollars! You don't have any money left! :("); 
		}
		else {
			System.out.println("You lost $" + Math.abs(priceDigit-digit) + " dollars! You are now at $" + dollars + "."); 
		}
		return dollars; 
	}
	
	/*
	 * allows user to have a help option
	 * pre: input is of scanner type
	 * post: proceeds with the options that the user can choose from
	 */
	public static void helpButton(Scanner input) {
		
		System.out.println("--------------------------------------------------------------------------------\n");
		System.out.println("Do you need some help with what game you choose? Great place to come! Here are some \ndescriptions to help you out!\n");
		System.out.println("Mastermind is a strategic game, in which you will guess a secret code in 10 guesses. \nThe program will provide feedback on the code that you entered, which will help you \ncrack the code! Think you can do it? Select 1!");
		enterToContinue(input); 
		System.out.println("Wordle is a word game, in which you would need to guess the secret five-letter word in \nsix or fewer guesses! For every word you enter, the program will provide feedback on what \nletters are correct, and whether they are in the right position! Good at words? Select 2!");
		enterToContinue(input); 
		System.out.println("Lucky Seven is a Price is a Right game, where every individual has an opportunity to win \na car! In this game, the user will first get $7, and then they will guess every digit \nin the price of the car. You would then use the money you have to pay the difference between \nthe digit you guessed and the actual digit in the price. After all the digits in the price \nhave been revealed, you must have at least one dollar to win. If you run out of money, \nyou are out of the game and you lose. Think you have a shot? Select 3!");
		enterToContinue(input); 
		mainMenu(input); 
		
	}
	
	
}
