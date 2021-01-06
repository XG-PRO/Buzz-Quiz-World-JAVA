import java.io.Serializable;
import java.util.Objects;

public class Score implements Comparable<Score>, Serializable {


    private final String  playerName;
    private int highestPoints;
    private int numberOfWins;

    public Score(String playerName, int highestPoints, int numberOfWins) {
        this.playerName = playerName;
        this.highestPoints = highestPoints;
        this.numberOfWins = numberOfWins;
    }
    public void setHighestPoints(int highestPoints) {
        this.highestPoints = highestPoints;
    }

    public void increaseNumberOfWins() {
        this.numberOfWins++;
    }


    public String getPlayerName() {
        return playerName;
    }

    public int getHighestPoints() {
        return highestPoints;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }


    /**
     * It compares two score objects points.
     * @param o A Score object.
     * @return 0 If both have the same points. <br>
     *         1 If This object has more points that <b>'o'</b> object<br>
     *         -1 If This object has less points that <b>'o'</b> object
     */
    @Override
    public int compareTo(Score o) {
        return Integer.compare(this.highestPoints, o.highestPoints);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(getPlayerName(), score.getPlayerName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerName());
    }

    @Override
    public String toString() {
        return "Score{" +
                "playerName='" + playerName + '\'' +
                ", highestPoints=" + highestPoints +
                ", numberOfWins=" + numberOfWins +
                '}';
    }
}
