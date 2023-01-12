class Solution {
  public int countDistinctStrings(String s, int k) {
    // Since content of `s` doesn't matter, for each i in [0, n - k], we can
    // flip s[i..i + k] or not flip it. Therefore, there's 2^(n - k + 1) ways.
    return (int) myPow(2, s.length() - k + 1);
  }

  private final static int kMod = 1_000_000_007;

  private long myPow(long x, int n) {
    if (n == 0)
      return 1;
    if (n % 2 == 1)
      return x * myPow(x, n - 1) % kMod;
    return myPow(x * x % kMod, n / 2);
  }
}
