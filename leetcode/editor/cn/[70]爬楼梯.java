//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：2
//解释：有两种方法可以爬到楼顶。
//1. 1 阶 + 1 阶
//2. 2 阶 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：3
//解释：有三种方法可以爬到楼顶。
//1. 1 阶 + 1 阶 + 1 阶
//2. 1 阶 + 2 阶
//3. 2 阶 + 1 阶
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 45 
// 
//
// Related Topics 记忆化搜索 数学 动态规划 👍 2914 👎 0

/*
* n阶需要dp[n]种方法
* */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {
        if (n<=2)
            return n;
        int a=1,b=2,c=0;
        for (int i = 3; i <= n; i++) {
            c = a+b;
            a = b;
            b = c;
        }
        return c;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/*
* 完全背包，nums = {1,2},target = n;
* dp[j] 表示target为j时，最多有多少种方法
* dp[j] = dp[j - nums[i]]
* */
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int j = 0; j <= n; j++) {
            for (int i = 1; i < 3; i++) {
                if (j >= i)
                    dp[j] = dp[j-i];
            }
        }
        return dp[n];
    }
}