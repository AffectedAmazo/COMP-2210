import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;

public class BoggleGame implements WordSearchGame {
   String[][] board;
   TreeSet<String> lexicon;
   int dimension;
   TreeSet<String> validWords;
   boolean lexiconLoaded = false;
   int wordLength;

   public void loadLexicon(String fileName) {
   
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      
      lexicon = new TreeSet();
      
      try {
         Scanner scan = 
            new Scanner(new BufferedReader(new FileReader(new File(fileName))));
            
         while (scan.hasNext()) {
         
            String str = scan.next();
            lexicon.add(str); //not type safe
            scan.nextLine();
         }
      }
      catch (Exception e) {
         throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
      }
      
      lexiconLoaded = true;
   }


   public void setBoard(String[] letterArray) {
   
      if (letterArray == null || Math.sqrt(letterArray.length) % 1 != 0) {
         throw new IllegalArgumentException("letterArray is not a square");
      }
   
      dimension = (int) Math.sqrt(letterArray.length);
      board = new String[dimension][dimension];
      
      int n = 0;
      
      for (String[] s: board) { //changed
         for (int i = 0; i < dimension; i++) {
            s[i] = letterArray[i * (n + 1)];
         }
         n++;
      }
   
   }

   public String getBoard() {
   
      String result = "";
      for (String[] s: board) {
         for (String str: s) {
         
            result = result + str;
         }
      }
   
      return result;
   }

   public SortedSet<String> getAllValidWords(int minimumWordLength) {
   
      wordLength = minimumWordLength;
   
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("min word length must be > 0");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Must load lexicon");
      }
   
      validWords = new TreeSet();
      for (int i = 0; i < dimension; i++) {
         for (int j = 0; j < dimension; j++) {
            wordSearch(j, i, ""); //changed
         }
      }
   
      return validWords;
   }

   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
   
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("min word length must be > 0");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Must load lexicon");
      }
   
      int score = 0;
   
      for (String word: words) {
         int length = word.length();
         score += 1 + (length - minimumWordLength);
      }
   
      return score;
   }

   public boolean isValidWord(String wordToCheck) {
   
      if (wordToCheck == null) {
         throw new IllegalArgumentException("word cannot be null");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Must load lexicon");
      }
   
      return lexicon.contains(wordToCheck);
   }

   public boolean isValidPrefix(String prefixToCheck) {
   
      if (prefixToCheck == null) {
         throw new IllegalArgumentException("word cannot be null");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Must load lexicon");
      }
      
      return lexicon.ceiling(prefixToCheck).startsWith(prefixToCheck);
   }

   public   List<Integer> isOnBoard(String wordToCheck) {
   
      if (wordToCheck == null) {
         throw new IllegalArgumentException("word cannot be null");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Must load lexicon");
      }
   
   //start from end, use get neighbors, check if it contains right letter. recursion
      List<Integer> path = new ArrayList<Integer>();
      List<Integer> emptyList = new ArrayList<Integer>();
      
      if (wordToCheck.length() - 1 < 0) {
         Collections.reverse(path);
         return path;
      }
   
      String check = "" + wordToCheck.charAt(wordToCheck.length() - 1);
      int x = 0;
      int y = 0;
      
      outerloop:
      for (int i = 0; i < dimension; i++) {
         x = 0;
         for (int j = 0; j < dimension; j++) {
         
            if(board[i][j] == check) {
               break outerloop;
            }
         
            x++;
         }
         y++;
      }
   
      String[] options = getNeighbors(x, y);
      
      for (String s: options) {
         if (s == check) {
            int value = (y * dimension) + x;
            path.add(value);
            wordToCheck = wordToCheck.substring(0, wordToCheck.length() - 1);
            //added
            isOnBoard(wordToCheck);
         }
      }
      
      return emptyList;
   }
   
   private String[] getNeighbors(int x, int y) {
   
      String[] neighbors = new String[1000];
      
      if (x - 1 <= 0) {
         neighbors[neighbors.length] = board[y][x - 1];
      }
      
      if (x + 1 < dimension) {
         neighbors[neighbors.length] = board[y][x + 1];
      }
      
      if (y - 1 <= 0) {
         neighbors[neighbors.length] = board[y - 1][x];
      }
      
      if (y + 1 < dimension) {
         neighbors[neighbors.length] = board[y + 1][x];
      }
      
      if (x - 1 <= 0 && y + 1 < dimension) {
         neighbors[neighbors.length] = board[y + 1][x - 1];
      }
      
      if (x + 1 <= 0 && y + 1 < dimension) {
         neighbors[neighbors.length] = board[y + 1][x + 1];
      }
      
      if (x - 1 <= 0 && y - 1 < dimension) {
         neighbors[neighbors.length] = board[y - 1][x - 1];
      }
      
      if (x + 1 <= 0 && y - 1 < dimension) {
         neighbors[neighbors.length] = board[y - 1][x + 1];
      }
   
      return neighbors;
   }
      
   private void wordSearch(int x, int y, String word) {
   
      word = word + board[y][x];
   
      if (wordLength <= word.length() && isValidWord(word)) { //changed
         validWords.add(word);
      }
      
      
      if (isValidPrefix(word)) {
        
         if (x - 1 >= 0) {
            wordSearch(x - 1, y, word);
         }
         
         if (x + 1 < dimension) {
            wordSearch(x + 1, y, word);
         }
         
         if (y + 1 < dimension) {
            wordSearch(x, y + 1, word);
         }
         
         if (y - 1 >= 0) {
            wordSearch(x, y - 1, word);
         }
         
         if (x - 1 <= 0 && y + 1 < dimension) {
            wordSearch(x - 1, y + 1, word);
         }
      
         if (x + 1 <= 0 && y + 1 < dimension) {
            wordSearch(x + 1, y + 1, word);
         }
      
         if (x - 1 <= 0 && y - 1 < dimension) {
            wordSearch(x - 1, y - 1, word);
         }
      
         if (x + 1 <= 0 && y - 1 < dimension) {
            wordSearch(x + 1, y - 1, word);
         }
      
        
      }
   }
   
}
