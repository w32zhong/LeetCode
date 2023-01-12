class UnionFind {
  public UnionFind(int n) {
    id = new int[n];
    rank = new int[n];
    for (int i = 0; i < n; ++i)
      id[i] = i;
  }

  public void unionByRank(int u, int v) {
    final int i = find(u);
    final int j = find(v);
    if (i == j)
      return;
    if (rank[i] < rank[j]) {
      id[i] = id[j];
    } else if (rank[i] > rank[j]) {
      id[j] = id[i];
    } else {
      id[i] = id[j];
      ++rank[j];
    }
  }

  public int find(int u) {
    return id[u] == u ? u : (id[u] = find(id[u]));
  }

  private int[] id;
  private int[] rank;
}

class Solution {
  public int minimumCost(int n, int[][] connections) {
    int ans = 0;
    UnionFind uf = new UnionFind(n + 1);

    // Sort by cost.
    Arrays.sort(connections, (a, b) -> a[2] - b[2]);

    for (int[] c : connections) {
      final int u = c[0];
      final int v = c[1];
      final int cost = c[2];
      if (uf.find(u) == uf.find(v))
        continue;
      uf.unionByRank(u, v);
      ans += cost;
    }

    final int root = uf.find(1);
    for (int i = 1; i <= n; ++i)
      if (uf.find(i) != root)
        return -1;

    return ans;
  }
}
