package com.ubs.sudoku;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SudokuFileOpsTest {

  @Test
  void readDataShouldThrowAnExceptionWhenFileDoesNotExist() {
    assertThatThrownBy(
        () -> new SudokuFileOps().readData("iDontExist.txt"))
        .isInstanceOf(FileNotFoundException.class)
        .hasMessage("INVALID 104 The file iDontExist.txt cannot be found");
  }

  @DisplayName("apply valid files")
  @MethodSource("validFiles")
  @ParameterizedTest(name = "{index}: {0} is valid")
  @SneakyThrows
  void readDataShouldReturnTrueWhenFileIsValid(String description, String filePath) {
    // when
    final int[][] result = new SudokuFileOps().readData(filePath);
    // then
    assertThat(result.length).isEqualTo(9);
    assertThat(result[0].length).isEqualTo(9);
    for (int i = 0; i < 9; i++) {
      Arrays.stream(result[1]).forEach(e->assertThat(e).isLessThan(10).isGreaterThan(-1));
    }
  }

  @Test
  @SneakyThrows
  void readDataShouldThrowAnExceptionWhenThereIsAGapInTheBoard()  {
    assertThatThrownBy(
        () -> new SudokuFileOps().readData("src//test//resources//validBoardWithAGap.txt"))
        .isInstanceOf(NumberFormatException.class)
        .hasMessage("INVALID 100 Bad Input: java.lang.NumberFormatException: For input string: \"\"");
  }

  @Test
  @SneakyThrows
  void readDataShouldThrowAnExceptionWhenThereIsAnAlphanumericInTheBoard() {
    // when and then
    assertThatThrownBy(
        () -> new SudokuFileOps().readData("src//test//resources//containsAlphabet.txt"))
        .isExactlyInstanceOf(NumberFormatException.class)
        .hasMessage("INVALID 100 Bad Input: java.lang.NumberFormatException: For input string: \"x\"");
  }

  @Test
  @SneakyThrows
  void readDataShouldThrowAnExceptionWhenTheGridSizeIsIncorrect() {
    // when and then
    assertThatThrownBy(
        () -> new SudokuFileOps().readData("src//test//resources//incorrectGridSize.txt"))
        .isExactlyInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void readDataShouldThrowAnExceptionWhenInvalidFile() {
    // when and then
    assertThatThrownBy(
        () -> new SudokuFileOps().readData("src//test//resources//emptyFile.txt"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("INVALID 104 The table must have a height of 9");
  }
  @Test
  void readDataShouldThrowAnExceptionWhenTheValueIsOneAndOutsideRange() {
    // when and then
    assertThatThrownBy(
        () -> new SudokuFileOps().readData("src//test//resources//boardWithOutsideRangeValue-1.txt"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("INVALID 106 The table must have a value between 1-9");
  }
  @Test
  void readDataShouldThrowAnExceptionWhenTheValueIsTenAndOutsideRange() {
    // when and then
    assertThatThrownBy(
        () -> new SudokuFileOps().readData("src//test//resources//boardWithOutsideRangeValue10.txt"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("INVALID 106 The table must have a value between 1-9");
  }

  static Stream<Arguments> validFiles() {
    return Stream.of(
        Arguments.of("validSudokuBoardFile.txt",
            "src//test//resources//validSudokuBoardFile.txt"),
        Arguments.of("validSudokuPuzzle.txt",
            "src//test//resources//validSudokuPuzzle.txt")
    );
  }
}
