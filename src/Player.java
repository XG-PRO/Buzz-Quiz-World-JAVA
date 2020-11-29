public class Player {
    private int points;
    //private ArrayList<String> keyboard_responses;

    public Player(){
        points = 0;
        /*

        //Defensive Copying
        this.keyboard_responses = new ArrayList<String>(keyboard_responses.size());
        this.keyboard_responses.addAll(keyboard_responses);

       */
    }
    public int getPoints()
    {
        return points;
    }

    public void increasePoints(int p){
        points+=p;
    }

    public void decreasePoints(int p){
        points-=p;
    }

}

