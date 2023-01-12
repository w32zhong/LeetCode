class Solution:
  def numberOfNodes(self, n: int, queries: List[int]) -> int:
    # flipped[i] := True if we should flip all values in the subtree of node i.
    flipped = [False] * (n + 1)

    for query in queries:
      flipped[query] = flipped[query] ^ True

    def dfs(label: int, value: int) -> int:
      if label > n:
        return 0
      value ^= flipped[label]
      return value + dfs(label * 2, value) + dfs(label * 2 + 1, value)

    return dfs(1, 0)
