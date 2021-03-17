package com.ubs.sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

class SudokuFileOps {

  private static final String DELIMITER = ",";
  private static final int SIZE = 9;
  private static final int MIN_VALUE = 0;
  private static final int MAX_VALUE = SIZE;

  int[][] readData(String filepath) throws Exception {
    int[][] grid = new int[SIZE][SIZE];
    int row = 0;
    InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filepath);

    if (resourceAsStream == null) {
      File file = new File(filepath);
      if (!file.exists()) {
        throw new FileNotFoundException("INVALID 104 The file " + filepath + " cannot be found");
      }
    }
    String line;
    try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
      while ((line = reader.readLine()) != null) {
        String[] lineValuesAsArray = line.split(DELIMITER);
        if (lineValuesAsArray.length!= SIZE) throw new IllegalArgumentException("INVALID 105 The table must have a length of 9");
        for (int col = 0; col < lineValuesAsArray.length; col++) {
          final Integer value = Integer.valueOf(lineValuesAsArray[col].trim());
          if (value< MIN_VALUE || value>MAX_VALUE) {
            throw new IllegalArgumentException("INVALID 106 The table must have a value between 1-9");
          }
          grid[row][col] = value;
        }
        row++;
      }
    } catch (NumberFormatException nfe) {
      throw new NumberFormatException("INVALID 100 Bad Input: " + nfe);
    }catch (FileNotFoundException fnfe) {
      throw new FileNotFoundException("INVALID 101 Bad Input: " + fnfe);
    }catch (IOException ioe) {
      throw new IOException("INVALID 102 Bad Input: " + ioe, ioe);
    }catch (IllegalArgumentException ioe) {
      throw ioe;
    }catch (Exception e) {
      throw new Exception("INVALID 103 Bad Input: " + e, e);
    }
    if (row!= SIZE) {
      throw new IllegalArgumentException("INVALID 104 The table must have a height of 9");
    }
    return grid;
  }
}
