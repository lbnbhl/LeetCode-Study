//给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。 
//
// 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
//，而 "AEC" 不是） 
//
// 题目数据保证答案符合 32 位带符号整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "rabbbit", t = "rabbit"
//输出：3
//解释：
//如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
//rabbbit
//rabbbit
//rabbbit 
//
// 示例 2： 
//
// 
//输入：s = "babgbag", t = "bag"
//输出：5
//解释：
//如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 
//babgbag
//babgbag
//babgbag
//babgbag
//babgbag
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length, t.length <= 1000 
// s 和 t 由英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 983 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    * dp[i][j] 表示从[0,i-1]的字符组成的字符串中，拥有另一个，从[0,j-1]的字符组成的字符串，它出现的个数
    * dp[i][j] =
    * */
    public int numDistinct(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        int[][] dp = new int[chars1.length+1][chars2.length+1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= Math.min(i,chars2.length-1); j++) {
                if (chars1[i-1] == chars2[j-1]){
                    if (i == 1){
                        dp[i][j] = chars1[0] == chars2[0]?1:0;
                    }else {
                        if (dp[i-1][j] != 0){
                            dp[i][j] = dp[i-1][j-1] + 1;
                        } else if (dp[i-1][j-1] != 0){

                        }else {

                        }

                    }
                } else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[chars1.length][chars2.length];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

//   r  a  b  b  b  i  t
//r  1  1  1  1  1  1  1
//a  0  1  1  1  1  1  1
//b  0  0  1  2  3  3  3
//b  0  0  0  1  3  3  3
//i  0  0  0  0  0  3  3
//t  0  0  0  0  0  0  3

//0  0  0  0  0  0  0  0
//0  1  1  1  1  1  1  1
//0  0  1  1  1  1  1  1
//0  0  0  1  2  3  3  3
//0  0  0  0  1  3  3  3
//0  0  0  0  0  0  3  3
//0  0  0  0  0  0  0  3
