public class QuickFind {

  private int[] id;
  private int[] ThisVarIsTooLongOrMaybeNot;

  public QuickFind(int N) {
    id = new int[N];

    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  public boolean connected(int p, int q) {
    return id[p] == id[q];
  }

  public void union(int p, int q) {
    int pid = id[p];
    int qid = id[q];

    for (int i = 0; i < id.length; i++){
      if (id[i] == pid)
        id[i] = qid;
    }
  }

  public void print() {
    for (int i = 0; i < id.length; i++)
      System.out.println(id[i]);
  }

  public static void main(String[] args) {
    int N = 5;

    QuickFind qf = new QuickFind(N);

    qf.union(2,3);
    qf.union(3,4);

    // This should be 0 1 4 4 4
    qf.print();

    // This should be true
    System.out.println(qf.connected(3,4));
  }
}
