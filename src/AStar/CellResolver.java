package AStar;

import Lab.Cell;

/**
 * Created by Alexandre on 11/05/2019.
 */
public class CellResolver {
    private Cell cell;
    private CellResolver lastCellResolver;
    private int ECost; // End cost
    private int SCost; // Start cost

    public CellResolver(Cell cell, CellResolver lastCellResolver, int ECost, int SCost) {
        this.cell = cell;
        this.lastCellResolver = lastCellResolver;
        this.ECost = ECost;
        this.SCost = SCost;
    }

    public Cell getCell() {
        return cell;
    }

    public CellResolver getLastCellResolver() {
        return lastCellResolver;
    }

    public int getECost() {
        return ECost;
    }

    public int getSCost() {
        return SCost;
    }

    public int getTotalCost() {
        return 5*ECost + SCost;
    }
}
