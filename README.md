To help us filter out CVs and reduce the time spent in interviews (for both us and you) please complete the
following programming task...

Create a java based command line tool for validating a standard 9x9 Sudoku puzzle:

**Command line:** validate.bat puzzleName.txt

**File format:** csv format each line representing a row e.g.:

```
1,2,3,4,5,6,7,8,
1,2,3,4,5,6,7,8,
1,2,3,4,5,6,7,8,
1,2,3,4,5,6,7,8,
1,2,3,4,5,6,7,8,
1,2,3,4,5,6,7,8,
1,2,3,4,5,6,7,8,
1,2,3,4,5,6,7,8,
1,2,3,4,5,6,7,8,
```
The program should print **VALID** or **INVALID** on stdout with the error text (in case of an
invalid solution or file) and an appropriate status code.

There should be unit tests covering a range of error conditions and the project should be
**maven** based.

It should be possible to unpack the code from a zip, generate a surefire report, build it
and use a batch file to call the code from a packaged jar.

**Please Note:** The zip file should not contain any files with a .bat or .sh extension.
Instead please rename the validate.bat file to validate.bat_rename. If the zip file
contains this file extension it will not be accepted by the mail system (or for example be
sent by a GMail account).

INSTRUCTION

build
```
mvn clean install
```

generate report
```
mvn surefire-report:report
```

launch example 1
```
.\validate.sh puzzleName.txt
```
launch example 2
```
.\validate.sh sudokuBoardRecogniser/src/test/resources/validSudokuPuzzle.txt
```
