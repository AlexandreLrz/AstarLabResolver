package Lab;

/**
 * Created by Alexandre on 05/05/2019.
 */
public class Cell {
    public final static char NO_BREAKABLE_WALL = '@';
    public final static char WALL= '#';
    public final static char EMPTY= ' ';
    public final static char START = 'S';
    public final static char END = 'E';


    private int group;
    private char bloc;
    private int x;
    private int y;

    public Cell(int group, char bloc, int x, int y) {
        this.group = group;
        this.bloc = bloc;
        this.x = x;
        this.y = y;
    }

    public int getGroup() {
        return group;
    }

    public char getBloc() {
        return bloc;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setBloc(char bloc) {
        this.bloc = bloc;
    }

    public char getType() {
        if(x % 2 == 0) {
            return 'h';
        }
        else {
            return 'l';
        }
    }
}
