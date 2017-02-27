import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    public static void main(String[] args) {

        int size = 50;
        Percolation perc = new Percolation(size);

        while (!perc.percolates()) {
            int row = StdRandom.uniform(1, size+1);
            int col = StdRandom.uniform(1, size+1);
            System.out.println(row + " " + col + " " + perc.numberOfOpenSites());
            perc.open(row, col);
        }

        System.out.println((double) perc.numberOfOpenSites()/(size*size));

    }

}
