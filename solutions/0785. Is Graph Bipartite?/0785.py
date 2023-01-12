from enum import Enum


class Color(Enum):
  kWhite = 0
  kRed = 1
  kGreen = 2


class Solution:
  def isBipartite(self, graph: List[List[int]]) -> bool:
    colors = [Color.kWhite] * len(graph)

    for i in range(len(graph)):
      # Already colored, do nothing
      if colors[i] != Color.kWhite:
        continue
      # colors[i] == Color.kWhite
      colors[i] = Color.kRed  # Always paint w/ Color.kRed
      # BFS
      q = collections.deque([i])
      while q:
        u = q.popleft()
        for v in graph[u]:
          if colors[v] == colors[u]:
            return False
          if colors[v] == Color.kWhite:
            colors[v] = Color.kRed if colors[u] == Color.kGreen else Color.kGreen
            q.append(v)

    return True
