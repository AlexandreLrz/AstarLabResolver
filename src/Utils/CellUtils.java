package Utils;

import AStar.CellResolver;
import Lab.Cell;
import Lab.Lab;

import java.util.ArrayList;

/**
 * Created by Alexandre on 11/05/2019.
 */
public class CellUtils {

    public static int distanceBetween(Cell cell1, Cell cell2) {
        int maxX = Math.max(cell1.getX(), cell2.getX());
        int minX = Math.min(cell1.getX(), cell2.getX());
        int maxY = Math.max(cell1.getY(), cell2.getY());
        int minY= Math.min(cell1.getY(), cell2.getY());
        return maxX - minX + maxY - minY;
    }

    public static int getFirstCellResolver(ArrayList<CellResolver> list) {
        int maxCost = 1_000_000_000;
        int cell = -1;
        for(int i = 0; i < list.size(); i++) {
            CellResolver resolver = list.get(i);
            if(resolver.getTotalCost() < maxCost) {
                cell = i;
                maxCost = resolver.getTotalCost();
            }
        }
        return cell;
    }

    public static ArrayList<Cell> getEmptyCellsNextTo(Cell cell, Lab lab, ArrayList<Cell> ended) {
        Cell cell1 = lab.getCells()[cell.getX()][cell.getY()+1];
        Cell cell2 = lab.getCells()[cell.getX()][cell.getY()-1];
        Cell cell3 = lab.getCells()[cell.getX()+1][cell.getY()];
        Cell cell4 = lab.getCells()[cell.getX()-1][cell.getY()];

        ArrayList<Cell> res = new ArrayList<>();

        if( (cell1.getBloc() == Cell.EMPTY || cell1.getBloc() == Cell.END) && !ended.contains(cell1)) {
            res.add(cell1);
        }
        if((cell2.getBloc() == Cell.EMPTY || cell2.getBloc() == Cell.END) && !ended.contains(cell2)) {
            res.add(cell2);
        }
        if((cell3.getBloc() == Cell.EMPTY || cell3.getBloc() == Cell.END)&& !ended.contains(cell3)) {
            res.add(cell3);
        }
        if((cell4.getBloc() == Cell.EMPTY || cell4.getBloc() == Cell.END) && !ended.contains(cell4)) {
            res.add(cell4);
        }
        return res;
    }
}
