import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Boggle implements WordSearchGame
{
		
   private TreeSet<String> dictionary; 
	//private Set foundSet;
   private List<Integer> path;
   private List<Integer> finalPath;
   private String[] dict;
   private double dimension;
   private String[][] board;
   private Boolean[][]tries;
   private SortedSet<String> validList;
        //private String[] letters;
   private List<String> resultSet;
   private int minLength;
   private boolean lexiconLoaded;

   public Boggle(){
      path = new ArrayList<Integer>();
      finalPath = new ArrayList<Integer>();
      dictionary = new TreeSet<String>();
   	//dimension = 0.0;
      validList=new TreeSet<String>();
   	//foundSet = new TreeSet<String>();
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
         throw new IllegalArgumentException("Invalid argument");
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
         /*dict=new String[dictionary.size()];
         for (int i=0;i<dictionary.size();i++){
            dict[i]=(String)dictionary.get(i);
         }*/
      
      } 
      catch (IOException e) {
         throw new IllegalArgumentException("Invalid Parameters");
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
   public void setBoard(String[] letterArray){ //tested: working
   	
      dimension = Math.sqrt(letterArray.length);
   	//System.out.println("Dimension:::: "+dimension);
   	
      if (letterArray==null || dimension != (int)dimension){
         throw new IllegalArgumentException("Sorry!!");
      }
      else{
         board = new String[(int)dimension][(int)dimension];
         tries = new Boolean[(int)dimension][(int)dimension];
         int count = 0;
         for (int i=0;i<(int)dimension;i++){
            for (int j=0;j<(int)dimension;j++){
               board[i][j]=letterArray[count].toLowerCase();
            	//System.out.println(board[i][j]);
               tries[i][j]=false;
               count++;
            }
         }
      }
   	
   	//for (int i=0;i<(int)dimension;i++)
   		//for (int j=0;j<(int)dimension;j++)
   		 	//System.out.println(board[i][j]);
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
   public SortedSet getAllValidWords(int minimumWordLength){ //tested: working
      minLength=minimumWordLength;
   	//System.out.println("-->");
      validList.clear();
      if (minimumWordLength<1){
         throw new IllegalArgumentException("Please Input a Valid Number!!");
      }
      if(dictionary==null) {
         throw new IllegalStateException("The Required method's not called yet");
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
         throw new IllegalArgumentException("word cannot be null");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Must load lexicon");
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
         throw new IllegalArgumentException("word cannot be null");
      }
      
      if (lexiconLoaded == false) {
         throw new IllegalStateException("Must load lexicon");
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
   public List<Integer> isOnBoard(String wordToCheck){ //tested: working
   
      if(wordToCheck ==null) 
         throw new IllegalArgumentException("Invalid Parameter");
      if(dictionary ==null) 
         throw new IllegalStateException("loadDictionary not called yet");
   	
      path.clear();
      finalPath.clear();
   	
      for(int i = 0; i< (int)dimension; i++)
      {	
         for(int j = 0; j< (int)dimension; j++)
         {
            if(board[i][j].charAt(0)== wordToCheck.charAt(0)) 
            {
               path.add(i*(int)dimension+j);
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
	
	/**
	* An optional method that gives a user-defined boggle board to the GUI.
	* 
	* @return a String array in the same form as the input to setBoard().
	*/
   public String[] getCustomBoard(){ //tested: working
      String[] letters = {"A","B","I","D","E","K","O","S","U"};
   	//for (int i=0;i<letters.length;i++)
   	//System.out.println(letters[i]);
      return letters;
   }
	
	
	
	//this method will check and not let the index to the array 
	//out of bounds
   public boolean outOfBounds(int x, int y, int i, int j)
   {
      if((x+i)<=((int)dimension-1) && (y+j)<=((int)dimension-1)
      		&& (x+i)>=0 && (y+j)>=0 && !tries[x+i][y+j])
         return false;
      return true; 
   }
	
	//In this recursive method, the method takes parameters
	//the word, and x, and y, the location in the array
	//and finds the valid words in the board that appear in the
	//dictionary
   public void findWord(String word , int x , int y)
   {
   	//System.out.println("===++++"+word);
      if(!isValidPrefix(word)) {
      	//System.out.println("Are we here yet?");
         return;
      }
   	
      tries[x][y]=true;
   	
      if (isValidWord(word) && word.length()>=minLength) 
         validList.add(word);
   	
      for(int i=-1;i<=1;i++)
      {
         for (int j=-1;j<=1;j++)
         {
            if (!outOfBounds(x,y,i,j))
            {
               tries[x+i][y+j]=true;
               findWord(word+board[x+i][y+j],x+i,y+j);
            	//System.out.println("$$#$#$  "+word+board[x+i][y+j]);
               tries[x+i][y+j]=false;
            }
         }
      }
      tries[x][y]=false;
   }
	
   public void recursive(String wordToCheck, String word, int x, int y){
      tries[x][y]=true;
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
            if (!outOfBounds(x,y,i,j))
            {
               tries[x+i][y+j]=true;
               path.add((x+i)*(int)dimension+y+j);
               recursive(wordToCheck,word+board[x+i][y+j],x+i,y+j);
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
   
   public String getBoard() {
   
      String result = "";
      for (String[] s: board) {
         for (String str: s) {
         
            result = result + str;
         }
      }
      
      return result;
   }
	
	/*
	public static void main(String[] args){
		BogglePlayerImpl testing = new BogglePlayerImpl();
		
		testing.loadDictionary("dictionary.txt"); //tests loadDictionary
		
		
		if (testing.isValidWord("totallyRandomWord")==true)
			System.out.println("word was found in the Dictionary");
		else
			System.out.println("word Not Found in the Dictionary");
		
		if (testing.isValidPrefix("in")==true)
			System.out.println("prefix was found in the Dictionary");
		else
			System.out.println("PRefix not found in Dictionary");
		
		
		
		testing.setBoard(testing.getCustomBoard());
		System.out.println(testing.getAllValidWords(3));
		//System.out.println(testing.getCustomBoard());
	}
	*/

}
