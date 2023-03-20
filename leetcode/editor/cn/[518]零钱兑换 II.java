//给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。 
//
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。 
//
// 假设每一种面额的硬币有无限个。 
//
// 题目数据保证结果符合 32 位带符号整数。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：amount = 5, coins = [1, 2, 5]
//输出：4
//解释：有四种方式可以凑成总金额：
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// 示例 2： 
//
// 
//输入：amount = 3, coins = [2]
//输出：0
//解释：只用面额 2 的硬币不能凑成总金额 3 。
// 
//
// 示例 3： 
//
// 
//输入：amount = 10, coins = [10] 
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 300 
// 1 <= coins[i] <= 5000 
// coins 中的所有值 互不相同 
// 0 <= amount <= 5000 
// 
//
// Related Topics 数组 动态规划 👍 1027 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
* dp[i][j] 从[0,i]个数中中任意取若干个数，他们的总和为j时，总共的组合方式数
* dp[i][j] = Sum(dp[i-1][j-k*nums[i]]),0 <= k <= j/nums[i];
* dp[1][2] = dp[0][2-k*nums[1]])
* */
class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount+1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j == 0){
                    dp[i][j] = 1;
                }else if (i == 0){
                    if (j < coins[i])
                        dp[i][j] = 0;
                    else {
                        if (j % coins[i]  == 0)
                            dp[i][j] = 1;
                        else
                            dp[i][j] = 0;
                    }
                }else {
                    int k = 0;
                    while (j >= k*coins[i]){
                        dp[i][j] += dp[i-1][j-k*coins[i]];
                        k++;
                    }
                }
            }
        }
        return dp[coins.length-1][amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


class Solution {
    public int change(int amount, int[] coins) {
        //递推表达式
        int[] dp = new int[amount + 1];
        //初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
