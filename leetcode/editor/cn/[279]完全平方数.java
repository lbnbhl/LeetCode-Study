//给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。 
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：3 
//解释：12 = 4 + 4 + 4 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：2
//解释：13 = 4 + 9 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁴ 
// 
//
// Related Topics 广度优先搜索 数学 动态规划 👍 1654 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
* 完全背包
* dp[j] 表示取若干个数，他们的完全平方数之和为j的最少数量
* dp[j] = Math.min(dp[j-nums[i]]+1,dp[j])
* */
class Solution {
    public int numSquares(int n) {
        int m = (int) Math.sqrt(n)+1;
        int[] dp = new int[n+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 10000;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = i*i; j <= n; j++) {
                int num = i*i;
                dp[num] = 1;
                dp[j] = Math.min(dp[j-num]+1,dp[j]);
            }
        }
        return dp[n] >= 10000?0:dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
