import java.util.SortedSet;
import java.util.TreeSet;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
* This program is for the word game Boggle
* COMP 2210 - Assignment 5
* @author Grant Haislip
* @versoin 4/3/2017
*/
public class Boggle implements WordSearchGame
{
		
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
* The constructor for Boggle
*/
   public Boggle(){
      path = new ArrayList<Integer>();
      finalPath = new ArrayList<Integer>();
      dictionary = new TreeSet<String>();
      validList = new TreeSet<String>();
   }

	/**
	* Loads the dictionary into a data structure for later use. 
	* 
	* @param fileName A string containing the dictionary to be opened.
	* @throws IllegalArgumentException if fileName is null
	* @throws IllegalArgumentException if fileName cannot be opened.
	*/
   public void loadLexicon(String fileName){ //tested: working
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
	* Stores the incoming array of Strings in a fashion that will
	*      make it convenient to find words.
	* 
	* @param letterArray Each string in this array corresponds to 
	*      a die on the Boggle board. The die are in order left to 
	
	*      right, top to bottom. The size of letterArray = Row X Col.
	*      Note that the Strings inside may be longer than one 
	*      character. Also note that the board might not be 4x4.
	* @throws IllegalArgumentException if letterArray is null, or is 
	*      not square (i.e. it's the square-root of the length is not 
	*      a whole number).
	*/
   public void setBoard(String[] letterArray){
   	
      if (letterArray == null) {
         throw new IllegalArgumentException("Incorrect Entry");
      }
      
      dimension = Math.sqrt(letterArray.length);
   
   	
      if (dimension != (int)dimension){
         throw new IllegalArgumentException("Incorrect Entry");
      }
      
      
      else{
         board = new String[(int)dimension][(int)dimension];
         tries = new Boolean[(int)dimension][(int)dimension];
         int count = 0;
         for (int i=0;i<(int)dimension;i++){
            for (int j=0;j<(int)dimension;j++){
               board[i][j] = letterArray[count].toLowerCase();	
               tries[i][j] = false;
               count++;
            }
         }
      }
   }
	
	/**
	* Retrieves all the words in the Boggle board that appear in the 
	*       dictionary.
	* 
	* @param minimumWordLength The minimum allowed length for 
	*	strings that will be stated as being on the board.
	
	* @return java.util.SortedSet which contains all the words found 
	*	from the boggle board that appear in the dictionary.
	* @throws IllegalArgumentException if minimumWordLength < 1
        * @throws IllegalStateException if loadDictionary has not been called.
	*/
   public SortedSet getAllValidWords(int minimumWordLength){
      minLength = minimumWordLength;
      validList.clear();
      if (minimumWordLength<1){
         throw new IllegalArgumentException("Invalid Number");
      }
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Load Lexicon");
      }
   	//System.out.println("adasda-->"+dimension);
      for(int i=0 ; i<(int)dimension ; i++)
      {
         for(int j=0 ; j<(int)dimension ; j++)
         {
         	//System.out.println("-->");
            findWord(board[i][j], i, j);
         }
      }
   	//System.out.println(validList);
      return validList;
   }
	
	/**
	* Determines if the given word is in the dictionary.
	* 
	* @param wordToCheck The word to validate
	* @return true if wordToCheck appears in dictionary, false otherwise.
	* @throws IllegalArgumentException if wordToCheck is null.
        * @throws IllegalStateException if loadDictionary has not been called.
	*/
   public boolean isValidWord(String wordToCheck){ //tested : working
      if (wordToCheck == null) {
         throw new IllegalArgumentException("Invalid word");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Load lexicon");
      }
   
      return dictionary.contains(wordToCheck);
   }
	
	/**
	* Determines if there is at least one word in the dictionary with the 
	* given prefix.
	* 
	* @param prefixToCheck The prefix to validate
	* @return true if prefixToCheck appears in dictionary, false otherwise.
	* @throws IllegalArgumentException if prefixToCheck is null.
	* @throws IllegalStateException if loadDictionary has not been called.
        */
   public boolean isValidPrefix(String prefixToCheck){ //tested: working
      if (prefixToCheck == null) {
         throw new IllegalArgumentException("Invalid word");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Load lexicon");
      }
      
      return dictionary.ceiling(prefixToCheck).startsWith(prefixToCheck);
   }
	
	/**
	* Determines if the given word is in on the Boggle board. If so, 
	*	it returns the path that makes up the word.
	* 
	* @param wordToCheck The word to validate
	* @return java.util.List containing java.lang.Integer objects with 
	
	*	the path that makes up the word on the Boggle board. If word
	*	is not on the boggle board, return null.
	* @throws IllegalArgumentException if wordToCheck is null.
        * @throws IllegalStateException if loadDictionary has not been called.
	*/
   public List<Integer> isOnBoard(String wordToCheck){
   
      if(wordToCheck ==null) 
         throw new IllegalArgumentException("Invalid word");
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Load lexicon");
      }
      path.clear();
      finalPath.clear();
   	
      for(int i = 0; i< (int)dimension; i++)
      {	
         for(int j = 0; j< (int)dimension; j++)
         {
            if(Character.toUpperCase(board[i][j].charAt(0)) == Character.toUpperCase(wordToCheck.charAt(0))) 
            {
               path.add(i*(int)dimension+j);
               recursionMethod(wordToCheck,board[i][j],i,j);
               if (!finalPath.isEmpty()) 
                  return finalPath;
               path.clear();
               finalPath.clear();
            }
         }
      }
      return path;
   }	
	
	//In this recursive method, the method takes parameters
	//the word, and x, and y, the location in the array
	//and finds the valid words in the board that appear in the
	//dictionary
   public void findWord(String word , int x , int y)
   {
   	
      if(!isValidPrefix(word)) {
         return;
      }
   	
      tries[x][y]=true;
   	
      if (isValidWord(word) && word.length()>=minLength) 
         validList.add(word);
   	
      for(int i=-1;i<=1;i++)
      {
         for (int j=-1;j<=1;j++)
         {
            if ((x+i)<=((int)dimension-1) && (y+j)<=((int)dimension-1)
            && (x+i)>=0 && (y+j)>=0 && !tries[x+i][y+j])
            {
               tries[x+i][y+j]=true;
               findWord(word+board[x+i][y+j],x+i,y+j);
               tries[x+i][y+j]=false;
            }
         }
      }
      tries[x][y]=false;
   }
	
   public void recursionMethod(String wordToCheck, String word, int x, int y){
      tries[x][y]=true;
      if (!(isValidPrefix(word)))
         return;
      if (word.toUpperCase().equals(wordToCheck.toUpperCase())){
         finalPath = new ArrayList(path);
         return;
      }
      for(int i=-1;i<=1;i++)
      {	
         for (int j=-1;j<=1;j++)
         {
            if(word.equals(wordToCheck)) 
               return;
            if ((x+i)<=((int)dimension-1) && (y+j)<=((int)dimension-1)
            && (x+i)>=0 && (y+j)>=0 && !tries[x+i][y+j])
            {
               tries[x+i][y+j]=true;
               path.add((x+i)*(int)dimension+y+j);
               recursionMethod(wordToCheck,word+board[x+i][y+j],x+i,y+j);
               tries[x+i][y+j]=false;
               path.remove(path.size()-1);
            }
         }
      }
      tries[x][y]=false;
      return;
   }
   
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
   
      if (minimumWordLength < 1) {
         throw new IllegalArgumentException("length must be > 0");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Load lexicon");
      }
   
      int score = 0;
   
      for (String word: words) {
         int length = word.length();
         score += 1 + (length - minimumWordLength);
      }
   
      return score;
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
	
}
