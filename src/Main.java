import AStar.AStar;
import AStar.CellResolver;
import Lab.Lab;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alexandre on 05/05/2019.
 */
public class Main {
    public static void main(String[] args) {

        int x;
        int y;

        if(args.length != 2) {
            System.err.println("Erreur arguments");
            return;
        }

        try {
            x = Integer.valueOf(args[0]);
            y = Integer.valueOf(args[1]);
        } catch (Exception e) {
            System.err.println("Erreur arguments");
            return;
        }
        File f = new File("./Labs/Lab_" + x + "x" + y + "_" + getDate()+".txt");
        Lab b = new Lab(x,y);
        b.print();
        b.printFile(f);
        AStar aStar = new AStar(b);
        CellResolver resolver = aStar.resolve(false);
        aStar.printEndedFile(resolver, f);
        aStar.printEnded(resolver);
    }

    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }
}
