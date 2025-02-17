class Solution {
  public int bestRotation(int[] A) {
    final int n = A.length;
    // rotate[i] := how many points losing after rotating left i times
    int[] rotate = new int[n];

    // Rotating i - A[i] times makes A[i] == its new index
    // So rotating i - A[i] + 1 times will "start" to make A[i] > its index,
    // Which is the starting index to lose point
    for (int i = 0; i < n; ++i)
      --rotate[(i - A[i] + 1 + n) % n];

    // Each time we rotate, we make index 0 to index n - 1,
    // So we get 1 point
    for (int i = 1; i < n; ++i)
      rotate[i] += rotate[i - 1] + 1;

    int max = Integer.MIN_VALUE;
    int maxIndex = 0;

    for (int i = 0; i < n; ++i)
      if (rotate[i] > max) {
        max = rotate[i];
        maxIndex = i;
      }

    return maxIndex;
  }
}
