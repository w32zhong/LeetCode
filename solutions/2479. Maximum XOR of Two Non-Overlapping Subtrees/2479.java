class TrieNode {
  public TrieNode[] children = new TrieNode[2];
}

class BitTrie {
  public BitTrie(int maxBit) {
    this.maxBit = maxBit;
  }

  public void insert(long num) {
    TrieNode node = root;
    for (int i = maxBit; i >= 0; --i) {
      final int bit = (int) (num >> i & 1);
      if (node.children[bit] == null)
        node.children[bit] = new TrieNode();
      node = node.children[bit];
    }
  }

  public long getMaxXor(long num) {
    long maxXor = 0;
    TrieNode node = root;
    for (int i = maxBit; i >= 0; --i) {
      final int bit = (int) (num >> i & 1);
      final int toggleBit = bit ^ 1;
      if (node.children[toggleBit] != null) {
        maxXor = maxXor | 1L << i;
        node = node.children[toggleBit];
      } else if (node.children[bit] != null) {
        node = node.children[bit];
      } else { // Nothing in the Bit Trie.
        return 0;
      }
    }
    return maxXor;
  }

  private int maxBit;
  private TrieNode root = new TrieNode();
}

class Solution {
  public long maxXor(int n, int[][] edges, int[] values) {
    List<Integer>[] graph = new List[n];
    long[] treeSums = new long[n];

    for (int i = 0; i < n; ++i)
      graph[i] = new ArrayList<>();

    for (int[] edge : edges) {
      final int u = edge[0];
      final int v = edge[1];
      graph[u].add(v);
      graph[v].add(u);
    }

    getTreeSum(graph, 0, -1, treeSums, values);
    final long maxSubTreeSum = getMaxSubTreeSum(treeSums);
    final int maxBit = (int) (Math.log(maxSubTreeSum) / Math.log(2));
    // Very simliar to 421. Maximum XOR of Two Numbers in an Array
    dfs(graph, 0, -1, treeSums, new BitTrie(maxBit));
    return ans;
  }

  private long ans = 0;

  // Gets tree sum rooted at node u.
  private long getTreeSum(List<Integer>[] graph, int u, int prev, long[] treeSums, int[] values) {
    long treeSum = values[u];
    for (final int v : graph[u]) {
      if (v == prev)
        continue;
      treeSum += getTreeSum(graph, v, u, treeSums, values);
    }
    treeSums[u] = treeSum;
    return treeSum;
  }

  private long getMaxSubTreeSum(long[] treeSums) {
    long maxSubTreeSum = 0;
    for (int i = 1; i < treeSums.length; ++i)
      maxSubTreeSum = Math.max(maxSubTreeSum, treeSums[i]);
    return maxSubTreeSum;
  }

  private void dfs(List<Integer>[] graph, int u, int prev, long[] treeSums, BitTrie bitTrie) {
    for (final int v : graph[u]) {
      if (v == prev)
        continue;
      // Preorder to get the ans.
      ans = Math.max(ans, bitTrie.getMaxXor(treeSums[v]));
      // Recursively call on the subtree rooted at node v.
      dfs(graph, v, u, treeSums, bitTrie);
      // Postorder to insert the tree sum rooted at node v.
      bitTrie.insert(treeSums[v]);
    }
  }
}
