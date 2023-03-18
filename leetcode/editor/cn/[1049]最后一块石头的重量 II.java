//有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。 
//
// 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下： 
//
// 
// 如果 x == y，那么两块石头都会被完全粉碎； 
// 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。 
// 
//
// 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。 
//
// 
//
// 示例 1： 
//
// 
//输入：stones = [2,7,4,1,8,1]
//输出：1
//解释：
//组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
//组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
//组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
//组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
// 
//
// 示例 2： 
//
// 
//输入：stones = [31,26,33,21,40]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 1 <= stones.length <= 30 
// 1 <= stones[i] <= 100 
// 
//
// Related Topics 数组 动态规划 👍 632 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
*为什么可以转01的原因： 整个题目，每个回合数两两抽出来比较，两个数之差将被再一次扔到数组里面，继续上面的过程。
* 每个回合都会丢失掉两个数字，加入一个新的数字，这个数字就是两个数的差。
* 相当于来说，就是少了a和b，但是多了一个a-b，a,b就此消失，
* 但是在下一回合，a-b可能又被抓出去pk，pk后a-b就此再消失了，又产生了新的一个差。
* 那么每一步来说，其实就相当于a,b没有真正意义消失。
* 到了最后一回合，我们可以知道，其实找出两个最接近的数字堆。
* 再举个例子：[31,26,33,21,40] 1：40-21 [19,26,31,33] 2: 31-(40-21) [12,26,33] 3: 33-(31-(40-21)) [21,26] 4: 26-(33-(31-(40-21))) [5]
* 总： （26+31+21） - （40+33） 这就是找出两个总和接近的两个堆。 如何让两个堆接近呢？ 那就是沿着中间分两半，找左右各自那一半，那么思路就来到了找出一半堆这里。那么就自然而然地来到取不取的问题，就是01背包问题。
* */
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int target = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < stones.length; i++) {
            target += stones[i];
        }
//        target /= 2;
        int[][] dp = new int[stones.length][target/2+1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = 0; j < target/2 + 1; j++) {
                if (j == 0){
                    dp[i][j] = 0;
                }else if (i == 0){
                    if (j >= stones[i])
                        dp[i][j] = stones[i];
                    else
                        dp[i][j] = 0;
                }else if (j < stones[i]){
                    dp[i][j] = dp[i-1][j];
                }else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-stones[i]] + stones[i]);
                if (target/2 + 1 - dp[i][j] >= 0){
                    min = Math.min(min,target/2 + 1 - dp[i][j]);
                }
            }
        }
        return target - 2*(target/2 + 1 - min);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/*
* 滚动
* */
class Solution {
    public int lastStoneWeightII(int[] stones) {
        if(stones.length == 1) return stones[0];
        int target = 0;
        for (int i = 0; i < stones.length; i++) {
            target += stones[i];
        }
//        target /= 2;
        int[] dp = new int[target / 2 + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = target/2; j >= stones[i] ; j--) {
                dp[j] = Math.max(dp[j],dp[j-stones[i]] + stones[i]);
            }
        }
        return target - 2*dp[j];
    }
}
