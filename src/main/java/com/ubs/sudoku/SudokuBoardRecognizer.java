package com.ubs.sudoku;

import java.io.IOException;
import java.util.stream.IntStream;

class SudokuBoardRecognizer {
  private final int[][] board;
  private static final int EMPTY = 0; // empty cell
  private static final int SIZE = 9;

  SudokuBoardRecognizer(int[][] board) {
    this.board = board;
  }

  private boolean isInRow(int row, int value) {
    return IntStream.range(0, SIZE).anyMatch(col -> board[row][col] == value);
  }

  private boolean isInColumn(int col, int value) {
    return IntStream.range(0, SIZE).anyMatch(row -> board[row][col] == value);
  }

  private boolean isIn3x3Box(int row, int col, int value) {
    int r = row - row % 3;
    int c = col - col % 3;
    return IntStream.range(r, r + 3).anyMatch(rowIndex -> IntStream.range(c, c + 3)
        .anyMatch(colIndex -> board[rowIndex][colIndex] == value));
  }

  private boolean isValid(int row, int col, int number) {
    return !isInRow(row, number)  &&  !isInColumn(col, number)  &&  !isIn3x3Box(row, col, number);
  }

  public boolean validate() {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        // look for an empty cell
        if (board[row][col] == EMPTY) {
          // try possibilities
          for (int possibleValue = 1; possibleValue <= SIZE; possibleValue++) {
            if (isValid(row, col, possibleValue)) {
              // most possibleValue for now
              board[row][col] = possibleValue;
              if (validate()) { // backtrack recursively
                return true;
              } else { // the possible value not the fit,reset the cell
                board[row][col] = EMPTY;
              }
            }
          }
          return false; //invalid
        }
      }
    }
    return true; // valid
  }

  void print() {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        System.out.print(" " + board[row][col]);
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args)  {
    if (args.length != 1) {
      System.out.println("Command line: .\\validate.sh puzzleName.txt");
      System.exit(0);
    }
    try {
      SudokuBoardRecognizer sudoku = new SudokuBoardRecognizer(new SudokuFileOps().readData(args[0]));
      System.out.println("Sudoku grid to solve");
      sudoku.print();

      if (sudoku.validate()) {
        System.out.println("Sudoku Grid valid");
        sudoku.print();
      } else {
        System.out.println("Unsolvable");
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(0);
    }
  }
}
