//给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。 
//
// 返回 你可以获得的最大乘积 。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1。 
//
// 示例 2: 
//
// 
//输入: n = 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 
//
// 
//
// 提示: 
//
// 
// 2 <= n <= 58 
// 
//
// Related Topics 数学 动态规划 👍 1094 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/*
* 2 1 1 1
* 3 1 2 2
* 4 2 2 4
* 5 2 3 6
* 6 3 3 9
* 7 3 4 12
* 8 4 4 16
* 9 4 5 24
* 10 5 5 36
*
* d[i] =
* */
class Solution {
    public int integerBreak(int n) {
        if(n <= 3)
            return n-1;
        int res = 1;
        while (true){
            if (n-3 >= 2 || n-3 == 0){
                n-=3;
                res*=3;
            }else {
                if (n<=0)
                    break;
                n-=2;
                res*=2;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
