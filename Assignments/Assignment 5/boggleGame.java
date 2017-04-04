import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
* This program is for the word game Boggle.
* COMP 2210 - Assignment 5.
* @author Grant Haislip
* @version 4/3/2017
*/
public class Boggle implements WordSearchGame {
   private TreeSet<String> dictionary; 
   private List<Integer> path;
   private List<Integer> finalPath;
   private String[] dict;
   private double dimension;
   private String[][] board;
   private Boolean[][]tries;
   private SortedSet<String> validList;
   private List<String> resultSet;
   private int minLength;
   private boolean lexiconLoaded;

/**
* The constructor for Boggle.
*/
   public Boggle() {
      path = new ArrayList<Integer>();
      finalPath = new ArrayList<Integer>();
      dictionary = new TreeSet<String>();
      validList = new TreeSet<String>();
   }

/**
* Loads the lexicon. 
* @param fileName is the file title to be loaded.
* @throws IllegalArgumentException if fileName is null or cant be loaded.
*/
   public void loadLexicon(String fileName) {
      if (fileName == null) {
         throw new IllegalArgumentException("Incorrect entry");
      }
      Scanner fileScan;
      Scanner lineScan;
      String line;
      try {
         fileScan = new Scanner(new FileReader(fileName));
         while (fileScan.hasNext()) {
            line = fileScan.nextLine();
            lineScan = new Scanner(line);
            lineScan.useDelimiter(" ");
            while (lineScan.hasNext()) {
               dictionary.add(lineScan.next());
            }
         
         }
      } 
      catch (IOException e) {
         throw new IllegalArgumentException("Incorrect entry");
      }
      fileScan.close();
      lexiconLoaded = true;
   }

/**
* Sets the Boggle board.
* @param letterArray is the letters of the board.
* @throws IllegalArgumentException letterArray null, not square. 
*/
   public void setBoard(String[] letterArray) {
   
      if (letterArray == null) {
         throw new IllegalArgumentException("Incorrect Entry");
      }
      
      dimension = Math.sqrt(letterArray.length);
   
      if (dimension != (int) dimension) {
         throw new IllegalArgumentException("Incorrect Entry");
      }
      
      
      else {
         board = new String[(int) dimension][(int) dimension];
         tries = new Boolean[(int) dimension][(int) dimension];
         int count = 0;
         for (int i = 0; i < (int) dimension; i++) {
            for (int j = 0; j < (int) dimension; j++) {
               board[i][j] = letterArray[count].toLowerCase();
               tries[i][j] = false;
               count++;
            }
         }
      }
   }

/**
* FInds words on the board that are in the lexicon.
* @param minimumWordLength is the min length of the words.
* @return validList is the list of the words on the board.
* @throws IllegalArgumentException min < 1, lexiconLoaded false.
*/
   public SortedSet getAllValidWords(int minimumWordLength) {
      minLength = minimumWordLength;
      validList.clear();
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("Invalid Number");
      }
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load Lexicon");
      }
      for (int i = 0; i < (int) dimension; i++) {
         for (int j = 0; j < (int) dimension; j++) {
            findWord(board[i][j], i, j);
         }
      }
      return validList;
   }

/**
* Finds if the word is in the lexicon.
* @param wordToCheck The word to check.
* @return true if wordToCheck is in lexicon, false if not.
* @throws IllegalArgumentException wordToCheck null, lexiconLoaded false.
*/
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Invalid word");
      }
      
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load lexicon");
      }
   
      return dictionary.contains(wordToCheck);
   }

/**
* Finds if a word in the lexicon has the prefix.
* @param prefixToCheck The prefix to check
* @return true if prefixToCheck is in lexicon, false if not.
* @throws IllegalArgumentException, prefix null, lexiconLoaded false.
*/
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) {
         throw new IllegalArgumentException("Invalid word");
      }
      
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load lexicon");
      }
      
      return dictionary.ceiling(prefixToCheck).startsWith(prefixToCheck);
   }

/**
* Sees if the word is on the board.
* @param wordToCheck The word to check
* @return path is the path of the word on the board.
* @throws IllegalArgumentException wordToCheck null, lexiconLoaded false.
*/
   public List<Integer> isOnBoard(String wordToCheck) {
   
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Invalid word");
      }
         
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load lexicon");
      }
      
      path.clear();
      finalPath.clear();
   
      for (int i = 0; i < (int) dimension; i++) {
         for (int j = 0; j < (int) dimension; j++) {
            if (Character.toUpperCase(board[i][j].charAt(0))
               == Character.toUpperCase(wordToCheck.charAt(0))) {
               path.add(i * (int) dimension + j);
               recursionMethod(wordToCheck, board[i][j], i, j);
               if (!finalPath.isEmpty()) {
                  return finalPath;
               }
               path.clear();
               finalPath.clear();
            }
         }
      }
      return path;
   }

/**
* This finds the word in the getAllValidWords method.
* @param word is the word.
* @param x is the x value of the word.
* @param y is the y value of the word.
*/
   public void findWord(String word, int x, int y) {
   
      if (!isValidPrefix(word)) {
         return;
      }
   
      tries[x][y] = true;
   
      if (isValidWord(word) && word.length() >= minLength) {
         validList.add(word);
      }
   
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if ((x + i) <= ((int) dimension - 1)
               && (y + j) <= ((int) dimension - 1)
               && (x + i) >= 0 && (y + j) >= 0 && !tries[x + i][y + j]) {
               tries[x + i][y + j] = true;
               findWord(word + board[x + i][y + j], x + i, y + j);
               tries[x + i][y + j] = false;
            }
         }
      }
      tries[x][y] = false;
   }

/**
* This method is the recursion for isOnBoard.
* @param wordToCheck is the word to check.
* @param word is the current word your're using.
* @param x is the current x value.
* @param y is the current y value.
*/
   public void recursionMethod(String wordToCheck, String word, int x, int y) {
      tries[x][y] = true;
      if (!(isValidPrefix(word))) {
         return;
      }
      if (word.toUpperCase().equals(wordToCheck.toUpperCase())) {
         finalPath = new ArrayList(path);
         return;
      }
      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if (word.equals(wordToCheck)) {
               return;
            }
            if ((x + i) <= ((int) dimension - 1)
               && (y + j) <= ((int) dimension - 1)
               && (x + i) >= 0 && (y + j) >= 0 && !tries[x + i][y + j]) {
               tries[x + i][y + j] = true;
               path.add((x + i) * (int) dimension + y + j);
               recursionMethod(wordToCheck, word
                  + board[x + i][y + j], x + i, y + j);
               tries[x + i][y + j] = false;
               path.remove(path.size() - 1);
            }
         }
      }
      tries[x][y] = false;
      return;
   }

/**
* Gets the total score for all the words on the board.
* @param words is the set of words to be scored.
* @param minimumWordLength is the min word length.
* @return score is the score for the words.
*/
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
   
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("length must be > 0");
      }
   
      if (!lexiconLoaded) {
         throw new IllegalStateException("Load lexicon");
      }
   
      int score = 0;
   
      for (String word: words) {
         int length = word.length();
         score += 1 + (length - minimumWordLength);
      }
   
      return score;
   }

/**
* Prints the board into a single string.
* @return result is the string of the board.
*/
   public String getBoard() {
   
      String result = "";
      for (String[] s: board) {
         for (String str: s) {
         
            result = result + str;
         }
      }
   
      return result;
   }
}
