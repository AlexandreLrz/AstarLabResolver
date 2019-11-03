package Lab;

import Utils.ConsoleColor;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alexandre on 05/05/2019.
 */
public class Lab {
    private Cell[][] cells;
    private Cell start;
    private Cell end;

    public Lab(int x, int y) {
        this.cells = new Cell[x+(x-1)+2][y+(y-1)+2];
        buildBase();
        build();
    }

    private void build() {
        ArrayList<Cell> list = new ArrayList<>();
        for(Cell[] col : cells) {
            for(Cell cell: col) {
                if(cell.getBloc() == Cell.WALL) {
                    list.add(cell);
                }
            }
        }

        int nbGroups= (cells.length-1)/2 * (cells[0].length-1)/2;
        while(nbGroups != 1) {
            int random = new Random().nextInt(list.size());
            Cell cell = list.get(random);

            Cell c1;
            Cell c2;

            if(cell.getType() == 'l') {
                c1 = cells[cell.getX()][cell.getY()+1];
                c2 = cells[cell.getX()][cell.getY()-1];

            } else {
                c1 = cells[cell.getX()-1][cell.getY()];
                c2 = cells[cell.getX()+1][cell.getY()];
            }

            int c2Group = c2.getGroup();

            if(c1.getGroup() != c2.getGroup()) {
                nbGroups--;
                cell.setBloc(Cell.EMPTY);
                cell.setGroup(c1.getGroup());
                // Toutes les cases dans le groupe de c2 prennent le groupe de c1
                for (int i = 0; i < cells.length; i++) {
                    Cell[] col = cells[i];
                    for (int j = 0; j < col.length; j++) {
                        if(cells[i][j].getGroup() == c2Group) {
                            cells[i][j].setGroup(c1.getGroup());
                        }
                    }
                }
            }

            list.remove(cell);
        }
        // Ajout entrée / sortie
        cells[1][1].setBloc(Cell.END);
        this.end = cells[1][1];
        int maxX = cells.length-2;
        int maxY = cells[0].length-2;
        cells[maxX][maxY].setBloc(Cell.START);
        this.start = cells[maxX][maxY];

        // Remplacement des NO_BREAKABE_WALL par des WALL
        for (int i = 0; i < cells.length; i++) {
            Cell[] col = cells[i];
            for (int j = 0; j < col.length; j++) {
                if(cells[i][j].getBloc() == Cell.NO_BREAKABLE_WALL) {
                    cells[i][j].setBloc(Cell.WALL);
                }
            }
        }

    }

    private void buildBase() {
        int nb = 0;
        for (int i = 0; i < cells.length; i++) {
            Cell[] col = cells[i];
            for (int j = 0; j < col.length; j++) {
                if(i == 0 || i == cells.length-1) {
                    cells[i][j] = new Cell(-1, Cell.NO_BREAKABLE_WALL, i, j);
                    continue;
                }
                if(j == 0 || j == col.length-1) {
                    cells[i][j] = new Cell(-1, Cell.NO_BREAKABLE_WALL, i, j);
                    continue;
                }
                if(j % 2 == 0 && i % 2 == 0) {
                    cells[i][j] = new Cell(-1, Cell.NO_BREAKABLE_WALL, i, j);
                    continue;
                }
                if(j % 2 == 0 || i % 2 == 0) {
                    cells[i][j] = new Cell(-1, Cell.WALL, i, j);
                    continue;
                }

                cells[i][j] = new Cell(nb++, Cell.EMPTY, i, j);
            }
        }
    }

    public void print() {
        System.out.println(ConsoleColor.RED);
        for (int i = 0; i < cells.length; i++) {
            Cell[] col = cells[i];
            for (int j = 0; j < col.length; j++) {
                System.out.print(cells[i][j].getBloc()+" ");
            }
            System.out.print("\n");
        }
        System.out.println(ConsoleColor.RESET);
    }

    public void printFile(File f) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(f, true));
            for (int i = 0; i < cells.length; i++) {
                Cell[] col = cells[i];
                for (int j = 0; j < col.length; j++) {
                    writer.print(cells[i][j].getBloc()+" ");
                }
                writer.print("\n");
            }
            writer.print("\n");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Cell getStart() {
        return start;
    }

    public Cell getEnd() {
        return end;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
