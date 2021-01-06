import java.io.Serializable;
import java.util.Objects;

public class Score implements Comparable<Score>, Serializable {


    private final String  playerName;
    private final int highestPoints;
    private final int numberOfWins;

    public Score(String playerName, int highestPoints, int numberOfWins) {
        this.playerName = playerName;
        this.highestPoints = highestPoints;
        this.numberOfWins = numberOfWins;
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
        int result = Integer.compare(this.highestPoints, o.highestPoints);
        if (result == 0 )// If the players have the same points check their wins
            result = Integer.compare(this.numberOfWins, o.numberOfWins);
        if (result == 0 )// If the players have the same wins check their names
            result = playerName.compareTo(o.playerName);
        return result;
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
