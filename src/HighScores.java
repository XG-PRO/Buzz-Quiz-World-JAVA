import java.io.*;
import java.util.*;

public class HighScores {
    private TreeSet<Score> scoreTreeSet;

    /**
     * Default constructor, reads and/or creates the leaderboard file
     */
    @SuppressWarnings("unchecked")
    public HighScores(){
        boolean fileExist = true;
        //If the file exists, read it and store it
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("scores.dat"))) {
            scoreTreeSet = (TreeSet<Score>) in.readObject();   //Stores the score information into a dynamic Score tree
        } catch (IOException e) {
            System.out.println("File Not Found\nCreating file...");
            fileExist = false;
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            fileExist = false;
        }

        //If the file doesn't exists, create it
        if (!fileExist){
            this.scoreTreeSet = new TreeSet<>();
            writeToFile();
        }
        //printScoresInOrder();
    }

    /**
     * Adds the scores and wins of a player into the leaderboard
     * @param currentPlayer the corresponding player
     */
    public void addHighScore(Player currentPlayer ) {
        Score current = null;
        int win = 0;
        if (currentPlayer.getHasWon()){ //If the player has won, add a win, otherwise don't
            win = 1;
        }

        //Search the current player into the leaderboard
        for (Score item : scoreTreeSet) {
            if (item.getPlayerName().equals(currentPlayer.getName()))
                current = item;
        }

        //If the player searched doesn't exist, create him
        if (current == null){
            current = new Score(currentPlayer.getName(), currentPlayer.getPoints(), win);
        }

        //If the player already exists, update him
        else {
            //If his current score is higher than his older one, update it by removing the old one and recreating it with the new values, otherwise don't
            int oldScore = current.getHighestPoints();
            if (currentPlayer.getPoints() > oldScore)
                oldScore = currentPlayer.getPoints();
            scoreTreeSet.remove(current);
            current = new Score(currentPlayer.getName(), oldScore, current.getNumberOfWins()+win);

        }scoreTreeSet.add(current);

        writeToFile();
    }

    /**
     *
     * @return An [n][3] Array where n the number of entries
     *             In the first collum lies the player name.<br>
     *             In the second collum lies the player's high score.<br>
     *             In the third collum lies the player's number of wins.<br>
     */
    public String[][] getHighScoresTable(){
        String[][]Array = new String[scoreTreeSet.size()][3];
        int i = 0;
        Iterator<Score> scoreIterator = scoreTreeSet.descendingIterator();
        while (scoreIterator.hasNext()){
            Score item = scoreIterator.next();
            Array[i][0]= item.getPlayerName();
            Array[i][1]= String.valueOf(item.getHighestPoints());
            Array[i][2]= String.valueOf(item.getNumberOfWins());
            i++;
        }
        return Array;
    }

    /**
     * Prints all the saved scores, useful for debugging.
     */
    public void printScoresInOrder(){
        Iterator<Score> scoreIterator = scoreTreeSet.descendingIterator();
        while (scoreIterator.hasNext()){
            Score item = scoreIterator.next();
            System.out.println(item.toString()+ " CODE"+item.hashCode());
        }
    }

    /**
     * Method that writes the current stored information into the file
     */
    private void writeToFile(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("scores.dat"))) {
            out.writeObject(this.scoreTreeSet);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound. Problem when trying to find the file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Problem when trying to create the file");
            e.printStackTrace();
        }
    }
}
