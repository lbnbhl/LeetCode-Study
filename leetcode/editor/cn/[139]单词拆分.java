//给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。 
//
// 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
// 
//
// 示例 2： 
//
// 
//输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
//     注意，你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 
//输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 300 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 20 
// s 和 wordDict[i] 仅有小写英文字母组成 
// wordDict 中的所有字符串 互不相同 
// 
//
// Related Topics 字典树 记忆化搜索 数组 哈希表 字符串 动态规划 👍 2027 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
* 完全背包
* dp[j],表示从[0,i]个元素中任意取若干个元素，刚好能够放入长度为j且和sum(s,0,j)相等的字符串
* dp[j] = (sum(s,0,j) == dp[j-wordDict[i].length] + wordDict[i])?sum(s,0,j):
* * */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        char[] dp = new char[s.length()+1];
        char[] chars = s.toCharArray();
        for (int i = 0; i < wordDict.size(); i++) {
            for (int j = wordDict.get(i).length(); j <= chars.length; j++) {
//                dp[j] = (sum(s,0,j) == dp[j-wordDict[i].length] + wordDict[i])?sum(s,0,j):
                StringBuilder str1 = new StringBuilder();
                StringBuilder str2 = new StringBuilder();
//                String s1 = String.valueOf(str.append(chars, 0, j));
//                dp[j] = str.equals (dp[j-wordDict.get(i).length()] + wordDict.get(i))?s1:dp[j-1];
                String s1 = String.valueOf(str1.append(chars, 0, j));
                String s2 = String.valueOf(str2.append(dp, 0, j-wordDict.get(i).length()));
                if (s1.equals(s2)){
                    for (int k = j-wordDict.get(i).length() + 1; k <= j; k++) {
                        dp[k] = chars[k];
                    }
                }else
                    dp[j] = dp[j-1];
            }
        }
        StringBuilder str = new StringBuilder();

        return String.valueOf(str.append(dp, 0, s.length())).equals(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
