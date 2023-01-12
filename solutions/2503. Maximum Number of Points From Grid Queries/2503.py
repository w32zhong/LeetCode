class Solution:
  def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
    m = len(grid)
    n = len(grid[0])
    dirs = [0, 1, 0, -1, 0]
    ans = [0] * len(queries)
    queryAndIndexes = sorted([(query, i) for i, query in enumerate(queries)])
    minHeap = [(grid[0][0], 0, 0)]  # (grid[i][j], i, j)
    seen = {(0, 0)}
    accumulate = 0

    for query, index in queryAndIndexes:
      while minHeap:
        val, i, j = heapq.heappop(minHeap)
        if val >= query:
          # The smallest neighbor is still larger than `query`, so no need to
          # keep exploring. Re-push (i, j, grid[i][j]) back to the `minHeap`.
          heapq.heappush(minHeap, (val, i, j))
          break
        accumulate += 1
        for k in range(4):
          x = i + dirs[k]
          y = j + dirs[k + 1]
          if x < 0 or x == m or y < 0 or y == n:
            continue
          if (x, y) in seen:
            continue
          heapq.heappush(minHeap, (grid[x][y], x, y))
          seen.add((x, y))
      ans[index] = accumulate

    return ans
