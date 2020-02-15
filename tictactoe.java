package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class tictactoe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();


	public static void main(String[] args) {
		
		// create the game board using characters
		char [][] gameBoard = {{' ', '|', ' ', '|', ' '}, 
				{'-', '+', '-', '+', '-'}, 
				{' ', '|', ' ', '|', ' '}, 
				{'-', '+', '-', '+', '-'}, 
				{' ', '|', ' ', '|', ' '}};
		
		
		// prompt user to choose a position and store their choice
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("Enter your placement (1-9):");
			int playerPos = scan.nextInt();
			// ensure player doesn't override a past move
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("Position taken! Please enter a legal position.");
				playerPos = scan.nextInt();
			}
				
			
		
			// call method for player to place piece on the game board
			placePiece(gameBoard, playerPos, "player");
			
			// call method to check winner
			String  result = checkWinner();
			if (result.length() > 0) {
					System.out.println(result);
					break;
				}
			
			// call method for computer to randomly place piece on the game board
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			// ensure that cpu doesn't override a past move
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, cpuPos, "cpu");
			
			// call method to print game board
			printGameBoard(gameBoard);
			
			// call method to check the winner
			result = checkWinner();
			if (result.length() > 0) { 
				System.out.println(result);
				break;
			}
			
		}
		
		
	}
	
	
	
	// create a method to print the game board separate from the main method
	public static void printGameBoard(char [] [] gameBoard) {
		// use for loops to print game board
				for(char[] row : gameBoard) { // for every char array (row) in gameBoard
					for(char c : row) { // for ever char in row
						System.out.print(c);
					}
					System.out.println();
				}

	}
	
	
	// create a method to place a symbol on the gameBoard
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		
		// initialize a symbol
		char symbol = ' ';
		
		
		// set symbol X for user and O for computer
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		} else if(user.contentEquals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		// switch case --> similar to a series of if else statements 	
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
	
	
	// create a method that checks for winning scenarios
	public static String checkWinner() {
		
		// create a lists from the 2 by 2 array of all winning scenarios
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(7,5,3);
		
		// list of lists of winning scenarios
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		// loop through all 3 end scenarios for all lists of possible wins
		for(List l : winning) {
			if(playerPositions.containsAll(l)) {
				return "Congratulations you won!";
			} else if (cpuPositions.containsAll(l)){
				return "CPU wins! Sorry :(";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "TIE!";
			}
		}
		
		return "";
		
	}

}

