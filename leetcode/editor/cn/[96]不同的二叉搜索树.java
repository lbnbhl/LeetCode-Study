//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 2138 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
* dp[n] 代表有n个节点时，从1到n互不相同的二叉树有多少种。dp[n] = Sum(dp[i]*dp[n-i-1],0,n-1);
* dp[0] = 1,dp[1] = 1,dp[2] = 2,dp[3] = 5
* 5 = 2+1+2
* */
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
