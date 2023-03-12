//给你一个下标从 0 开始的二维整数数组 flowers ，其中 flowers[i] = [starti, endi] 表示第 i 朵花的 花期 从 
//starti 到 endi （都 包含）。同时给你一个下标从 0 开始大小为 n 的整数数组 persons ，persons[i] 是第 i 个人来看花的时间。 
//
// 请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：flowers = [[1,6],[3,7],[9,12],[4,13]], persons = [2,3,7,11]
//输出：[1,2,2,2]
//解释：上图展示了每朵花的花期时间，和每个人的到达时间。
//对每个人，我们返回他们到达时在花期内花的数目。
// 
//
// 示例 2： 
//
// 
//
// 输入：flowers = [[1,10],[3,3]], persons = [3,3,2]
//输出：[2,2,1]
//解释：上图展示了每朵花的花期时间，和每个人的到达时间。
//对每个人，我们返回他们到达时在花期内花的数目。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= flowers.length <= 5 * 10⁴ 
// flowers[i].length == 2 
// 1 <= starti <= endi <= 10⁹ 
// 1 <= persons.length <= 5 * 10⁴ 
// 1 <= persons[i] <= 10⁹ 
// 
//
// Related Topics 数组 哈希表 二分查找 有序集合 前缀和 排序 👍 39 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
*       [1,6]  1
        [3,7]  1
        [9,12] 1
        [4,13] 1
        1 3 4 6 7 9 12 13
        1 2 3 3 2 2 2  1
* */
class Solution {

    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] flower : flowers) {
            map.compute(flower[0], (k, v) -> v == null ? 1 : v + 1);
            map.compute(flower[1] + 1, (k, v) -> v == null ? -1 : v - 1);
        }
        int[][] presonsWIndex = new int[persons.length][2];
        for (int i = 0; i < persons.length; i++) {
            presonsWIndex[i] = new int[]{persons[i], i};
        }
        int[] ret = new int[persons.length];
        Arrays.sort(presonsWIndex, Comparator.comparingInt(o -> o[0]));
        int Sum = 0;
        for (int[] wIndex : presonsWIndex) {
            int p = wIndex[0];
            int i = wIndex[1];
            while (!map.isEmpty() && map.firstKey() <= p) {
                Sum += map.pollFirstEntry().getValue();
            }
            ret[i] = Sum;
        }
        return ret;
    }


}
//leetcode submit region end(Prohibit modification and deletion)