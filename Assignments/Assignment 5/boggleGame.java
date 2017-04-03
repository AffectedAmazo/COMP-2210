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
   boolean[][] visited;
   List<Integer> path;
   List<Integer> finalPath;

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
      visited = new boolean[dimension][dimension];
      
      path = new ArrayList<Integer>();
      finalPath = new ArrayList<Integer>();
      validWords = new TreeSet();
      
      int n = 0;
      int count = 0;
      
      for (int i = 0; i < dimension; i++) { //changed
         for (int j = 0; j < dimension; j++) {
            board[i][j] = letterArray[count];
            visited[i][j] = false;
            count++;
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
   
      wordLength = minimumWordLength;
   
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("min word length must be > 0");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Must load lexicon");
      }
   
      
      /*
      for (int i = 0; i < dimension; i++) {
         for (int j = 0; j < dimension; j++) {
            wordSearch(j, i, ""); //changed
         }
      }*/
      
      /*for(String s: lexicon) {
         if (isOnBoard(s).size() > 0) {
            validWords.add(s);
         }
      }*/
      
      for(int i=0 ; i < dimension ; i++)
      {
         for(int j=0 ; j < dimension ; j++)
         {
            findWord(board[i][j], i, j);
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
      
      
      /*if (wordToCheck.length() - 1 < 0) {
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
      
      if (options.length == 0) {
         if (isValidWord(wordToCheck)) {
            int value = (y * dimension) + x;
            path.add(value);
            return path;
         }
      }
      
      for (String s: options) {
         if (s == check) {
            int value = (y * dimension) + x;
            path.add(value);
            wordToCheck = wordToCheck.substring(0, wordToCheck.length() - 1);
            //added
            isOnBoard(wordToCheck);
         }
      }*/
      
      /*for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board.length; j++) {
            /*if (this.findWord(wordToCheck, i, j)) {
               int value = i * (j + 1);
               path.add(value);
               return path;
            }
            
            findWord("", i, j);
         }
      }
      
      return path;
      
      //return emptyList;*/
      
      path.clear();
      finalPath.clear();
   	
      for(int i = 0; i< dimension; i++)
      {	
         for(int j = 0; j< dimension; j++)
         {
            if(board[i][j].charAt(0)== wordToCheck.charAt(0)) 
            {
               path.add((i * dimension) + j);
               recursive(wordToCheck,board[i][j],i,j);
               if (!finalPath.isEmpty()) 
                  return finalPath;
               path.clear();
               finalPath.clear();
            }
         }
      }
      return path;
      
      
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
   
   private void findWord(String word, int i, int j) {
      /*String start = "" + word.charAt(0);
      if (row < 0 || row >= board.length || col < 0 || col >= board.length ||
      board[row][col] != start) {
         return false;
      }
      
      String safe = board[row][col];
      //board[row][col] = "";
      String rest = word.substring(1, word.length());
      boolean result = this.findWord(rest, row-1, col-1) ||
                             this.findWord(rest, row-1,   col) ||
                             this.findWord(rest, row-1, col+1) ||
                             this.findWord(rest,   row, col-1) ||
                             this.findWord(rest,   row, col+1) ||
                             this.findWord(rest, row+1, col-1) ||
                             this.findWord(rest, row+1,   col) ||
                             this.findWord(rest, row+1, col+1);
                             
      board[row][col] = safe;
      return result;*/
      
      if (i < 0 || j < 0 || i >= dimension || j >= dimension) {
         return;
      }
      
      if (visited[i][j]) {
         return;
      }
      
      visited[i][j] = true;
      
      word = word + board[i][j];
      if (lexicon.contains(word)) {
         validWords.add(word);
      }
      
      for (int x = -1; x <= 1; x++) {
         for (int y = -1; y <= 1; y++) {
            findWord(word, i + x, j + y);
         }
      }
      
      visited[i][j] = false;
   }
   
   public void recursive(String wordToCheck, String word, int x, int y){
      visited[x][y]=true;
      if (!(isValidPrefix(word)))
         return;
      if (word.equals(wordToCheck)){
         finalPath = new ArrayList(path);
         return;
      }
      for(int i=-1;i<=1;i++)
      {	
         for (int j=-1;j<=1;j++)
         {
            if(word.equals(wordToCheck)) 
               return;
            if((x+i)<=(dimension-1) && (y+j)<=(dimension-1)
            && (x+i)>=0 && (y+j)>=0 && !visited[x+i][y+j]) //(!outOfBounds(x,y,i,j))
            {
               visited[x+i][y+j]=true;
               path.add((x+i)*(int)dimension+y+j);
               recursive(wordToCheck,word+board[x+i][y+j],x+i,y+j);
               visited[x+i][y+j]=false;
               path.remove(path.size()-1);
            }
         }
      }
      visited[x][y]=false;
      return;
   }
   
}

