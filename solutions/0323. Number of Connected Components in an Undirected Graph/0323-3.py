class UnionFind:
  def __init__(self, n: int):
    self.count = n
    self.id = list(range(n))
    self.rank = [0] * n

  def unionByRank(self, u: int, v: int) -> None:
    i = self._find(u)
    j = self._find(v)
    if i == j:
      return
    if self.rank[i] < self.rank[j]:
      self.id[i] = self.id[j]
    elif self.rank[i] > self.rank[j]:
      self.id[j] = self.id[i]
    else:
      self.id[i] = self.id[j]
      self.rank[j] += 1
    self.count -= 1

  def _find(self, u: int) -> int:
    if self.id[u] != u:
      self.id[u] = self._find(self.id[u])
    return self.id[u]


class Solution:
  def countComponents(self, n: int, edges: List[List[int]]) -> int:
    uf = UnionFind(n)

    for u, v in edges:
      uf.unionByRank(u, v)

    return uf.count
