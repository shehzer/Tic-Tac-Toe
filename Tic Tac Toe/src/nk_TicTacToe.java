/**
 * 251036612
 * @author Shehzer Naumani
 *
 */
public class nk_TicTacToe {

		/**
		 * These instance variables are used later in the code so they are declared here.
		 * @gameBoard The gameBoard is a 2d array that stores characters this is the board.
		 * @boardsize This is the size of the board.
		 * @inline This is the number of empty positions .
		 * @max_levels This is the max level of the computer.
		 */
		private char[][] gameBoard;
		private int boardsize;
		private int max_levels;
		private int inline;

		/** 
		 * Constructor of the class nk_TicTacToe, It creates the board and fills all the positions with empty tiles.
		 * @param boardsize The size of the board.
		 * @param inline The number of empty positions that must remain on the board.
		 * @param max_levels The max level of the computer.
		 */
		public nk_TicTacToe(int boardsize, int inline, int max_levels) {
			
			this.boardsize = boardsize;
			this.max_levels = max_levels;
			this.inline=inline;
			

			// Makes the game board with the specified size.
			gameBoard = new char[boardsize][boardsize];

			// Goes to every index of the game board and makes them all empty tiles
			   for (int i = 0; i < boardsize; i++) {
		            for (int j = 0; j < boardsize; j++) {
		                gameBoard[i][j] = ' ';
		            }
		        }
		    }
		

		/**
		 * Returns an empty Dictionary with a size that is prime.
		 * @return configurations The empty hash Dictionary with the specified size.
		 */
		public Dictionary createDictionary() {
			Dictionary configurations = new Dictionary(13);
			return configurations;
		}

		/**
		 * Checks whether the string representation of the game board is in the Dictionary. Will return score or negative 1
		 * @param configurations The Dictionary to search the string Record of the game board.
		 * @return configurations.getScore(gameBoardString) Will return the associated score if the string Record of gameBoard exists in the Dictionary other wise returns -1.
		 */
		public int repeatedConfig(Dictionary configurations) {
			String board = gameBoardString();
			return configurations.get(board);
		
		}
		/**
		 * 
		 * @param configurations, where the new configuration will be inserted
		 * @param score, of the current game board
		 */
		public void insertConfig(Dictionary configurations, int score) {
			String board = gameBoardString();
			configurations.insert(new Record(board, score));	
		}

		/**
		 * Stores the symbol in correct tile
		 * @param row - the row where the symbol is inputted
		 * @param col- column of the symbol inputted
		 * @param symbol - assigned to the 2d array gameboard with index [i] [j] ie. row and column
		 */
		public void storePlay(int row, int col, char symbol) {
			gameBoard[row][col] = symbol;
		}

		/**
		 * Checks to see if a tile is empty or not
		 * @param row
		 * @param col
		 * @return true if the index is empty
		 */
		public boolean squareIsEmpty(int row, int col) {
			return gameBoard[row][col] == ' ';
		}

		/**
		 * 
		 * @param symbol - 2 distinct symbols that are compared in tiles to determine whether comp. or human has won
		 * @return true if x adjacent tiles of type symbol in either a row, column or diagonal
		 */
		public boolean wins(char symbol) {

			// Declares variables to determine wins later in the method.
			boolean win = false;
			boolean leftwins = true;
			boolean rightwins = true;
			int count;
			
			// Checks if each column contains the same symbol.
			for (int col = 0; col < boardsize; col++) {
				
				// Initializes count to 0 
				count = 0;
		
				for (int row = 0; row < gameBoard[col].length; row++) {
					
					// If the tile is the same symbol then increment the count.
					if (gameBoard[col][row] == symbol) {
						count++;

						// When the count is the same as the board size then we know it is a win.
						if (count == boardsize) {
							
							// Set win to true.
							win = true;
							
							// break because a win has already happend
							break;
						} 
						
						// If count is not the same as the boards size the continue to search the next column for a win.
						else {
							continue;
						}
					}

				}
			}

			// checks if each row contains the same symbol.
			for (int row = 0; row < boardsize; row++) {
				
				// Initializes count to 0 and resets for every new row.
				count = 0;
				
				for (int col = 0; col < gameBoard[row].length; col++) {
					
					// If the tile is the same symbol then increment the count.
					if (gameBoard[col][row] == symbol) {
						count++;

						// When the count is the same as the board size then we know it is a win.
						if (count == boardsize) {
							
							// Set win to true.
							win = true;
							
							// break because nothing else needs to be tested if it is a win.
							break;
						} 
						
						// If count is not the same value as board size then continue to search the next row for a win.
						else {
							continue;
						}
					}

				}
			}

			// Checks if the left diagonal contains the same symbol. ( top half- along rows- descending)
			for (int i = 0; i < boardsize; i++) {

				// If the symbol doesn't match going diagonally from the left. Then it is not a win.
				if (gameBoard[i][i] != symbol) {

					// Set leftwins to false because it is not a win.
					leftwins = false;
					
					// break because the diagonal is not a win so we don't need to check any other index's on the diagonal.
					break;
				} 
				
				// Otherwise continue to check each index on the diagonal.
				else {
					continue;
				}

			}
			// Checks if the right diagonal contains the same symbol.
			for (int i = 0; i < boardsize; i++) {
				
				// If the symbol doesn't match going from the right. The it is not a win.
				if (gameBoard[boardsize - i - 1][i] != symbol) {
					
					// Set rightwins to false because it is not a win.
					rightwins = false;
					
					// break because the diagonal is not a win so we dont't need to check any other index's on the diagonal.
					break;
				} 
				
				// Otherwise continue to check if each index on the diagonal.
				else {
					continue;
				}
			}

			// If either the leftwins or the rightwins is true that means it is a win.
			if (leftwins || rightwins) {
				
				// Set win equal to true.
				win = true;
			}

			// Return whether the game is a win or not.
			return win;

		}

		/**
		 * This method is to determine whether or not the game is a draw or not.
		 * @param symbol The symbol is the next players move.
		 * @param inline The number of positions on the game board must remain empty.
		 * @return True is the game is a draw and false if is not a draw.
		 */
		//METHOD CHECKS FOR DRAW
		 public boolean isDraw(){

		        // Check for empty spots
		        for (int i = 0; i < gameBoard.length; i++) {
		            for (int j = 0; j < gameBoard.length; j++) {
		                if (getTileSymbol(i,j) == ' '){
		                    return false;
		                }
		            }
		        }
		                    // Check if player or computer has won the game
		                    if (wins('o') || wins('x')) {
		                        return false;
		                    }

		                    // Otherwise, return it's a draw
		                    return true;	                 	        
		 }


		/**
		 * This method is to evaluate the board and check for a win.
		 * @param symbol The symbol to check for a win.
		 * @param inline The number of empty positions required on the game board.
		 * @return 0 If the human player has won. 
		 * @return 1 If the game is still undecided.
		 * @return 2 If the game has ended as a draw.
		 * @return 3 If the computer has won.
		 */
		public int evalBoard() {
			
			// Checks if the computer has won.
			if (wins('o')) {
				return 3;
			}
			
			// Checks if the human has won.
			if (wins('x')) {
				return 0;
			}
			
			// Checks if the game is a draw or not.
			if (isDraw()) {
				return 2;
			}

			// Returns 1 if the game is still undecided.
			return 1;

		}

		/**
		 * This method is to represent the game board as a String.
		 * @return boardConfig The board represented as a string.
		 */
		private String gameBoardString() {
			
			// Initializes and empty string.
			String boardConfiguration = "";

			// Goes through each tile and add the symbol of the tile to the string boardConfig.
			for (int col = 0; col < boardsize; col++) {
				for (int row = 0; row < boardsize; row++) {
					boardConfiguration = boardConfiguration + gameBoard[col][row];
				}
			}
			
			// Returns the board as a string.
			return boardConfiguration;

		}

        private int getTileSymbol(int row, int col){
            try {
                // Attempt to look up tile type and return it
                return gameBoard[row][col];
            } catch (IndexOutOfBoundsException e) {
                // If out of bounds, return blocked (since this cannot be played)
                return 'b';
            }
        }
}

