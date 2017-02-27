import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private WeightedQuickUnionUF uf;
    private boolean[] state;

    public Percolation(int size) {
        /**
        * Initializes the weighted union find class and creates a state
        * array that keeps track of which sites are open/closed.
        */

        n = size;
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        uf = new WeightedQuickUnionUF(n*n + 2);
        state = new boolean[n*n + 2];

        //Set state of top/bottom virtual sites
        state[0] = true;
        state[state.length - 1] = true;
    }

    public void open(int row, int col) {
        /**
        * Open a site if it is not already open. Create connections to
        * neighbouring open sites. Number neighbouring sites from top,
        * clockwise from 0-3, mark ones to connect, then connect them.
        */

        if (row < 1 || row > n || col < 1 || col > n) {
            throw new java.lang.IllegalArgumentException();
        }

        if (isOpen(row, col) == false){
            state[idx(row, col)] = true;
        }

        boolean[] neighbourOpen = new boolean[4];

        // check which sites need to be
        if (row == 1) {
            uf.union(0, idx(row, col));

            if (col == 1) {
                neighbourOpen[1] = true;
                neighbourOpen[2] = true;
            }
            else if (col == n) {
                neighbourOpen[2] = true;
                neighbourOpen[3] = true;
            }
            else {
                neighbourOpen[1] = true;
                neighbourOpen[2] = true;
                neighbourOpen[3] = true;
            }
        }
        else if (row == n) {
            uf.union(idx(row, col), n*n+1);

            if (col == 1) {
                neighbourOpen[0] = true;
                neighbourOpen[1] = true;
            }
            else if (col == n) {
                neighbourOpen[0] = true;
                neighbourOpen[3] = true;
            }
            else {
                neighbourOpen[0] = true;
                neighbourOpen[1] = true;
                neighbourOpen[3] = true;
            }
        }
        else {
            if (col == 1) {
                neighbourOpen[0] = true;
                neighbourOpen[1] = true;
                neighbourOpen[2] = true;
            }
            else if (col == n) {
                neighbourOpen[0] = true;
                neighbourOpen[2] = true;
                neighbourOpen[3] = true;
            }
            else {
                neighbourOpen[0] = true;
                neighbourOpen[1] = true;
                neighbourOpen[2] = true;
                neighbourOpen[3] = true;
            }
        }

        // make connections to neighbouring open sites
        if (neighbourOpen[0]) {
            if (isOpen(row-1, col)) {uf.union(idx(row,col), idx(row-1,col));}
        }
        if (neighbourOpen[1]) {
            if (isOpen(row, col+1)) {uf.union(idx(row,col), idx(row,col+1));}
        }
        if (neighbourOpen[2]) {
            if (isOpen(row+1, col)) {uf.union(idx(row,col), idx(row+1,col));}
        }
        if (neighbourOpen[3]) {
            if (isOpen(row, col-1)) {uf.union(idx(row,col), idx(row,col-1));}
        }

    }

    public boolean isOpen(int row, int col) {
        /**
        * Checks whether a site is open.
        */

        if (row < 1 || row > n || col < 1 || col > n) {
            throw new java.lang.IllegalArgumentException();
        }

        return state[idx(row, col)];
    }

    public boolean isFull(int row, int col) {
        /**
        * Check whether site is connected to top virtual site.
        */

        if (row < 1 || row > n || col < 1 || col > n) {
            throw new java.lang.IllegalArgumentException();
        }

        return uf.connected(0, idx(row, col));

    }

    public int numberOfOpenSites() {
        /**
        * Calculates the total number of open sites.
        */

        int count = 0;
        // exlude virtual top/bottom
        for (int i = 1; i<state.length-1; i++) {
            if (state[i] == true) {
                count += 1;
            }
        }

        return count;
    }

    public boolean percolates() {
        /**
        * Checks whether the system percolates.
        */

        return uf.connected(0, n*n+1);
    }

    private int idx(int row, int col) {
        /**
        * Calculate a single array index from a row and column index (1-based).
        */
        return (col-1) + (row-1)*n + 1;
    }

    public static void main(String[] args) {

        int m = 3;
        Percolation perc = new Percolation(m);

        perc.open(1,1);
        perc.open(1,2);
        perc.open(1,3);
        perc.open(2,2);
        perc.open(3,1);
        perc.open(3,2);
        perc.open(3,3);

        System.out.println(perc.isOpen(1,1));
        System.out.println(perc.isOpen(2,2));

    }
}
