import java.io.*;
import java.util.*;

public class HighScores {
    private TreeSet<Score> scoreTreeSet;
    public HighScores(){
        boolean fileExist = true;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("scores.dat"))) {
            scoreTreeSet = (TreeSet<Score>) in.readObject();
        } catch (IOException e) {
            System.out.println("File Not Found\nCreating file...");
            fileExist = false;
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //toe.printStackTrace();
            fileExist = false;
        }
        if (!fileExist){
            this.scoreTreeSet = new TreeSet<>();
            writeToFile();
        }
        System.out.println("Current State of Object");
        printScoresInOrder();
        System.out.println("END Current state of Object");
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
            scoreTreeSet.remove(current);
            current = new Score(nameOfPlayer, score, current.getNumberOfWins()+1);

        }scoreTreeSet.add(current);

        writeToFile();
    }

    void printScoresInOrder(){
        Iterator scoreIterator = scoreTreeSet.descendingIterator();
        while (scoreIterator.hasNext()){
            Score item = (Score) scoreIterator.next();
            System.out.println(item.toString()+ " CODE"+item.hashCode());
        }
    }
    void writeToFile(){
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
