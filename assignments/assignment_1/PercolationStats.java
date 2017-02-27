import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] results;
    private int trials;
    private int n;

    public PercolationStats(int n, int trials) {

        this.n = n;
        this.trials = trials;
        results = new double[trials];

        for (int i=0; i<trials; i++) {
            Percolation perc = new Percolation(n);

            runTrial(perc);

            results[i] = (double) perc.numberOfOpenSites()/(n*n);
        }

    }

    private Percolation runTrial(Percolation perc) {
        while (!perc.percolates()) {
            int row = StdRandom.uniform(1, n+1);
            int col = StdRandom.uniform(1, n+1);
            perc.open(row, col);
        }

        return perc;
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev())/java.lang.Math.pow(trials, 0.5);
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev())/java.lang.Math.pow(trials, 0.5);
    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval = [" +
                           stats.confidenceLo() + ", " +
                           stats.confidenceHi() + "]");
    }
}
