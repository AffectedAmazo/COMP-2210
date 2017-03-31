import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class boggleGame implements WordSearchGame {
   String[][] board;
   TreeSet lexicon;

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
   }


   public void setBoard(String[] letterArray) {
   
      int dimension = (int) Math.sqrt(letterArray.length);
      board = new String[dimension][dimension];
      
      for (String[] s: board) {
         for (int i = 0; i < dimension; i++) {
            s[i] = letterArray[i];
         }
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
   
      return null;
   }

   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
   
      return 0;
   }

   public boolean isValidWord(String wordToCheck) {
   
      if (lexicon.contains(wordToCheck)) {
         return true;
      }
   
      return false;
   }

   public boolean isValidPrefix(String prefixToCheck) {
   
      if (lexicon.contains(prefixToCheck)) {
         return true;
      }
   
      return false;
   }

   public   List<Integer> isOnBoard(String wordToCheck) {
   
      return null;
   }
   
}
