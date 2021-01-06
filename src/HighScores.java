import java.io.*;
import java.util.*;

public class HighScores {
    private TreeSet<Score> scoreTreeSet;

    /**
     * Default constructor
     */
    @SuppressWarnings("unchecked")
    public HighScores(){
        boolean fileExist = true;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("scores.dat"))) {
            scoreTreeSet = (TreeSet<Score>) in.readObject();
        } catch (IOException e) {
            System.out.println("File Not Found\nCreating file...");
            fileExist = false;
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            fileExist = false;
        }
        if (!fileExist){
            this.scoreTreeSet = new TreeSet<>();
            writeToFile();
        }
        System.out.println("Current State of File");
        printScoresInOrder();
        System.out.println("END Current state of File");
    }
    public void addHighScore(String nameOfPlayer, int score) {
        Score current = null;
        for (Score item : scoreTreeSet) {
            if (item.getPlayerName().equals(nameOfPlayer))
                current = item;
        }
        if (current == null){
            current = new Score(nameOfPlayer, score, 1);
        }
        else {
            int oldScore = current.getHighestPoints();
            if (score > oldScore)
                oldScore = score;
            scoreTreeSet.remove(current);
            current = new Score(nameOfPlayer, oldScore, current.getNumberOfWins()+1);

        }scoreTreeSet.add(current);

        writeToFile();
    }

    /**
     *
     * @return An [n][3] Array where n the number of entries
     *             In the first collum player name.<br>
     *             In the second collum player high score.<br>
     *             In the third collum player number of wins.<br>
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
     * Prints all the saved scores useful for debugging.
     */
    public void printScoresInOrder(){
        Iterator<Score> scoreIterator = scoreTreeSet.descendingIterator();
        while (scoreIterator.hasNext()){
            Score item = scoreIterator.next();
            System.out.println(item.toString()+ " CODE"+item.hashCode());
        }
    }
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
