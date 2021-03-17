package com.ubs.sudoku;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class SudokuValidator {

  @Test
  @SneakyThrows
  void givenAValidPuzzleShouldReturnTrue() {
    final int[][] grid = new SudokuFileOps().readData("src//test//resources//validSudokuPuzzle.txt");
    SudokuBoardRecognizer sudoku = new SudokuBoardRecognizer(grid);
    assertThat(sudoku.validate()).isTrue();
  }
  @Test
  @SneakyThrows
  void givenAnIValidPuzzleShouldReturnFalse() {
    final int[][] grid = new SudokuFileOps().readData("src//test//resources//invalidSudokuPuzzle1.txt");
    SudokuBoardRecognizer sudoku = new SudokuBoardRecognizer(grid);
    assertThat(sudoku.validate()).isFalse();
  }
}
