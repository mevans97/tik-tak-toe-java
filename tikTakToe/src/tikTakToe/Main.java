package tikTakToe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class Main {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		//Game Board Method

		char[][] gameBoard = {  {' ', '|', ' ', '|', ' '}, 
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '}  };
			
			
			
			
			
			//Creating while statement to loop user input and cpu response for positioning
			
			while(true) {
				
				Scanner scan = new Scanner(System.in);//creating new scanner
			
				System.out.println("Enter your placement (1-9)");//prompt user to enter position on board
				int playerPos = scan.nextInt(); //user enters position
				
				//Check positioning of pieces so player does not place a duplicate piece.
				
				while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
					System.out.println("Position Taken! Enter A Position That Is Not Taken");
					playerPos = scan.nextInt();
				}
							
				placePiece(gameBoard, playerPos, "player"); //Places piece on game board for user using placePiece method below
				
				//Checks if there is a winner or draw after player places piece
				String result = checkWinner();
				
				Random rand = new Random(); //initializes a random generator variable
				int cpuPos = rand.nextInt(9) + 1; //initializes a random number generator for CPU postion option
				
				//Check positioning of pieces so cpu does not place a duplicate piece.
				
				while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
					cpuPos = rand.nextInt(9) + 1;
				}
				
				placePiece(gameBoard, cpuPos, "cpu"); //Places piece on game board for CPU using placePiece method below
				
				printGameBoard(gameBoard); // prints game board using printGameBoard method below
				
				//checking for wins or ties
				result = checkWinner(); 
				if(result.length() > 0) {
					System.out.println(result);	
					break;
				}
			}
								
	}
		
		//Print Game board method
	
		public static void printGameBoard(char [][] gameBoard) {
			//For loop that prints out game board by iterating through gameBoard
			
			for(char[] row : gameBoard) {
				for(char c : row) {
					System.out.print(c);
				}
				System.out.println();
			}
		}
		
		
		//placePiece Method
		
		public static void placePiece(char[][] gameBoard, int pos, String user) {
			
			//switch cases are similar to if-then statements. Determines where to place marker based on user input
			//SWTICH CASE TO PLACE PIECE
				
				char symbol = ' '; // default character symbol
				
				if(user.equals("player")) {
					symbol = 'X'; // User symbol will be X
					playerPositions.add(pos);
				}
				else if(user.equals("cpu")) {
					symbol ='O'; //CPU symbol will be 0
					cpuPositions.add(pos);
				}
				
				
				switch(pos) { 
				
					case 1:
						gameBoard[0][0] = symbol;
						break;
					case 2:
						gameBoard[0][2] = symbol;
						break;
					case 3:
						gameBoard[0][4] = symbol;
						break;
					case 4:
						gameBoard[2][0] = symbol;
						break;
					case 5:
						gameBoard[2][2] = symbol;
						break;
					case 6:
						gameBoard[2][4] = symbol;
						break;
					case 7:
						gameBoard[4][0] = symbol;
						break;
					case 8:
						gameBoard[4][2] = symbol;
						break;
					case 9:
						gameBoard[4][4] = symbol;
						break;
					default:
						break;
				}
		}
		
		public static String checkWinner() {
			
			//All Win Conditions, numbers represent position on board from case switch board placement method
			//player wins when they get three in a row
			List topRow = Arrays.asList(1, 2, 3);
			List midRow = Arrays.asList(4, 5, 6);
			List botRow = Arrays.asList(7, 8, 9);
			List leftCol = Arrays.asList(1, 4, 7);
			List midCol = Arrays.asList(2, 5, 8);
			List rightCol = Arrays.asList(3, 6, 9);
			List cross1 = Arrays.asList(3, 5, 7);
			List cross2 = Arrays.asList(1, 5, 9);
			
			//Adding all win conditions to one list so we may use one for loop instead of a ton of different ones. 
			List<List> winning = new ArrayList<List>();
			
			
			winning.add(topRow);
			winning.add(midRow);
			winning.add(botRow);
			winning.add(leftCol);
			winning.add(midCol);
			winning.add(rightCol);
			winning.add(cross1);
			winning.add(cross2);
			
			//for loop checking if a winning position was found
			
			for(List x: winning) {
				if (playerPositions.containsAll(x)) {
					return "Congratulations You Won";
				}
				else if(cpuPositions.containsAll(x)) {
					return "CPU Wins! Better Luck Next Time";
				}
			}
			//if statement checking if draw was found
			if (playerPositions.size() + cpuPositions.size() == 9) {
				return "Draw";
			}
			
			return "";
			
		}
		
}


	
