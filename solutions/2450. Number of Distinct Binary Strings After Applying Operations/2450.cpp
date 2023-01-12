class Solution {
 public:
  int countDistinctStrings(string s, int k) {
    // Since content of `s` doesn't matter, for each i in [0, n - k], we can
    // flip s[i..i + k] or not flip it. Therefore, there's 2^(n - k + 1) ways.
    return myPow(2, s.length() - k + 1);
  }

 private:
  constexpr static int kMod = 1'000'000'007;

  int myPow(long x, int n) {
    if (n == 0)
      return 1;
    if (n & 1)
      return x * myPow(x, n - 1) % kMod;
    return myPow(x * x % kMod, n / 2);
  }
};
