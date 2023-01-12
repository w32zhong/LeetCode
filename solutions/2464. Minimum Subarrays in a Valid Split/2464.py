class Solution:
  def validSubarraySplit(self, nums: List[int]) -> int:
    # dp[i] := min # of subarrays to validly split nums[0..i].
    dp = [math.inf] * len(nums)

    for i, num in enumerate(nums):
      for j in range(i + 1):
        if math.gcd(nums[j], num) > 1:
          dp[i] = min(dp[i], 1 if j == 0 else dp[j - 1] + 1)

    return -1 if dp[-1] == math.inf else dp[-1]
  