//给你一个二进制字符串数组 strs 和两个整数 m 和 n 。 
//
// 
// 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。 
// 
//
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
//输出：4
//解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
//其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 
//n 的值 3 。
// 
//
// 示例 2： 
//
// 
//输入：strs = ["10", "0", "1"], m = 1, n = 1
//输出：2
//解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 600 
// 1 <= strs[i].length <= 100 
// strs[i] 仅由 '0' 和 '1' 组成 
// 1 <= m, n <= 100 
// 
//
// Related Topics 数组 字符串 动态规划 👍 899 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
*dp[i][j][k] 表示从[0,i]任意取若干数，有j个0，k个1时，最大取了多少个数
* dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-a][k-b]+1)
* a = str[i] 中 0 的个数，b = str[i]中 1 的个数
* */
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length][m+1][n+1];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            int a = 0, b = 0;
            for (char c : chars) {
                if (c == '0')
                    a++;
                else
                    b++;
            }
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j == 0 && k == 0)
                        dp[i][j][k] = 0;
                    else if (i == 0){
                        if (j < a || k < b)
                            dp[i][j][k] = 0;
                        else
                            dp[i][j][k] = 1;
                    }else if (j < a || k < b){
                        dp[i][j][k] = dp[i-1][j][k];
                    }else
                        dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-a][k-b]+1);
                }
            }
        }
        return dp[strs.length-1][m][n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/*
* 滚动数组
* dp[j][k] = Math.max(dp[j][k],dp[j-a][k-b]+1)
* */
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            int a = 0, b = 0;
            for (char c : chars) {
                if (c == '0')
                    a++;
                else
                    b++;
            }
            for (int j = m; j >= a; j--) {
                for (int k = n; k >= b; k--) {
                    dp[j][k] = Math.max(dp[j][k],dp[j-a][k-b]+1);
                }
            }
        }
        return dp[m][n];
    }
}