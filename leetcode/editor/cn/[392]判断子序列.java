//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。 
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而
//"aec"不是）。 
//
// 进阶： 
//
// 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代
//码？ 
//
// 致谢： 
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abc", t = "ahbgdc"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "axc", t = "ahbgdc"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 100 
// 0 <= t.length <= 10^4 
// 两个字符串都只由小写字符组成。 
// 
//
// Related Topics 双指针 字符串 动态规划 👍 817 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
     * dp[i][j] 表示从长度为i的序列是否是另一个长度为j的序列的子序列
     * dp[i][j] = dp[i-1][j-1] && chars1[i-1] == chars2[j-1] || ;
     * */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0)
            return true;
        if (t.length() == 0)
            return false;
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        boolean[][] dp = new boolean[s.length()+1][t.length()+1];
        for (int j = 0; j < t.length(); j++) {
            if (j==0)
                dp[0][j] = chars1[0] == chars2[j];
            else
                dp[0][j] = chars1[0] == chars2[j] || dp[0][j-1];
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                dp[i][j] = (dp[i-1][j-1] && (chars1[i-1] == chars2[j-1])) || dp[i][j-1];
            }
        }
        return dp[s.length()][t.length()];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
1 2 3 4 5
1 2 3