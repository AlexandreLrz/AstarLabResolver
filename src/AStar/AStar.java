package AStar;

import Lab.Lab;
import Lab.Cell;
import Utils.ConsoleColor;
import Utils.CellUtils;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Alexandre on 10/05/2019.
 */
public class AStar {
    private Lab lab;

    public AStar(Lab lab) {
        this.lab = lab;
    }

    public CellResolver resolve(boolean showTrace) {
        ArrayList<Cell> ended = new ArrayList<>();
        ArrayList<CellResolver> listResolver = new ArrayList<>();

        CellResolver startResolver = new CellResolver(lab.getStart(), null, CellUtils.distanceBetween(lab.getStart(), lab.getEnd()), 0);
        listResolver.add(startResolver);

        while(listResolver.size() != 0) {
            if(showTrace) {
                printRunning(ended);
            }
            CellResolver first = listResolver.get(CellUtils.getFirstCellResolver(listResolver));
            if(!first.getCell().equals(lab.getEnd())) {
                ArrayList<Cell> nearTo = CellUtils.getEmptyCellsNextTo(first.getCell(), lab, ended);
                for(Cell cell : nearTo) {
                    int distanceE = CellUtils.distanceBetween(cell, lab.getEnd());
                    int distanceS = CellUtils.distanceBetween(cell, lab.getStart());
                    listResolver.add(new CellResolver(cell, first, distanceE, distanceS));
                }
                ended.add(first.getCell());
                listResolver.remove(first);
            }
            else { // terminé
                return first;
            }
        }
        return null;
    }

    public void printRunning(ArrayList<Cell> ended) {
        System.out.println(ConsoleColor.RED);
        Cell[][] cells = lab.getCells();
        for (int i = 0; i < cells.length; i++) {
            Cell[] col = cells[i];
            for (int j = 0; j < col.length; j++) {
                if(ended.contains(cells[i][j])) {
                    System.out.print("o ");
                }
                else {
                    System.out.print(cells[i][j].getBloc()+" ");
                }
            }
            System.out.print("\n");
        }
        System.out.println(ConsoleColor.RESET);
    }

    public void printEnded(CellResolver ended) {
        ArrayList<Cell> endedCells = new ArrayList<>();
        CellResolver resolver = ended;
        while(resolver != null) {
            endedCells.add(resolver.getCell());
            resolver = resolver.getLastCellResolver();
        }

        Cell[][] cells = lab.getCells();
        for (int i = 0; i < cells.length; i++) {
            Cell[] col = cells[i];
            for (int j = 0; j < col.length; j++) {
                if(endedCells.contains(cells[i][j]) && cells[i][j].getBloc() != Cell.END && cells[i][j].getBloc() != Cell.START) {
                    System.out.print(ConsoleColor.BLUE + "o ");
                }
                else {
                    if(cells[i][j].getBloc() == Cell.EMPTY) {
                        System.out.print("  ");
                    }
                    else {
                        System.out.print(ConsoleColor.RED);
                        System.out.print(cells[i][j].getBloc()+" ");
                    }
                }
            }
            System.out.print("\n");
        }
        System.out.println(ConsoleColor.RESET);
    }

    public void printEndedFile(CellResolver ended, File f) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(f, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ArrayList<Cell> endedCells = new ArrayList<>();
        CellResolver resolver = ended;
        while(resolver != null) {
            endedCells.add(resolver.getCell());
            resolver = resolver.getLastCellResolver();
        }

        Cell[][] cells = lab.getCells();
        for (int i = 0; i < cells.length; i++) {
            Cell[] col = cells[i];
            for (int j = 0; j < col.length; j++) {
                if(endedCells.contains(cells[i][j]) && cells[i][j].getBloc() != Cell.END && cells[i][j].getBloc() != Cell.START) {
                    writer.print("o ");
                }
                else {
                    if(cells[i][j].getBloc() == Cell.EMPTY) {
                        writer.print("  ");
                    }
                    else {
                        writer.print(cells[i][j].getBloc() + " ");
                    }
                }
            }
            writer.print("\n");
        }
        writer.print("\n");
        writer.flush();
    }
}
