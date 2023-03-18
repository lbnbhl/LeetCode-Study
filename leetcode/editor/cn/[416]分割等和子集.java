//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
//
// Related Topics 数组 动态规划 👍 1674 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
* sum表示背包的大小、nums[i]表示背包的重量和价值
* dp[i][j] 表示从[0,i-1]个元素任意取，放到容量为j的包，元素和可能为多少
* 第i-1个元素不放：dp[i][j] = dp[i-1][j]
* 第i-1个元素放：dp[i][j] = dp[i-1][j-nums[i-2]] + nums[i-2]
* 初始化 ：
* */

//滚动数组版本
class Solution {
    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int n = nums.length;
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        //总和为奇数，不能平分
        if(sum % 2 != 0) return false;
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for(int i = 0; i < n; i++) {
            for(int j = target; j >= nums[i]; j--) {
                //物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }
}

//二维数组版本
public class Solution {
    public static void main(String[] args) {
        int num[] = {1,5,11,5};
        canPartition(num);

    }
    public static boolean canPartition(int[] nums) {
        int len = nums.length;
        // 题目已经说非空数组，可以不做非空判断
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum %2 ) != 0) {
            return false;
        }

        int target = sum / 2; //目标背包容量
        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        /*
        dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数
          每个数只能用一次，使得这些数的和恰好等于 j。
        */
        boolean[][] dp = new boolean[len][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满  （这里的dp[][]数组的含义就是“恰好”，所以就算容积比它大的也不要）
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        // 再填表格后面几行
        //外层遍历物品
        for (int i = 1; i < len; i++) {
            //内层遍历背包
            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];

                //如果某个物品单独的重量恰好就等于背包的重量，那么也是满足dp数组的定义的
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                //如果某个物品的重量小于j，那就可以看该物品是否放入背包
                //dp[i - 1][j]表示该物品不放入背包，如果在 [0, i - 1] 这个子区间内已经有一部分元素，使得它们的和为 j ，那么 dp[i][j] = true；
                //dp[i - 1][j - nums[i]]表示该物品放入背包。如果在 [0, i - 1] 这个子区间内就得找到一部分元素，使得它们的和为 j - nums[i]。
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[len - 1][target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/*
* dp[i][j]表示[0,i]个数中取若干数放入容量为j的背包的价值最大是多少。
* dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-nums[j]] + nums[j])
* */
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1)
            return false;
        sum /= 2;
        int[][] dp =new int[nums.length][sum+1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j == 0)
                    dp[i][j] = 0;
                else if (i==0){
                    if (j >= nums[i])
                        dp[i][j] = nums[i];
                    else
                        dp[i][j] = 0;
                }
                else if (j < nums[i])
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-nums[i]] + nums[i]);
            }
            if (dp[i][sum] == sum)
                return true;
        }
        return false;
    }
}

/*
* dp[i][j]表示[0,i]个数中取若干数放入容量为j的背包的价值最大是多少。
* dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-nums[i]] + nums[i])
* 滚动数组：
* 将 d[i-1] 拷贝到 d[i]
* d[j] = Math.max(dp[j],dp[j-nums[i]] + nums[i])
* */
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1)
            return false;
        sum /= 2;
        int[] dp = new int[sum + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i] ; j--) {
                dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
            }
        }
        return dp[sum] == sum;
    }
}
//1 5 11 5;  sum = 11
//   0 1 2 3 4 5 6 7 8 9 10 11
//1  0 1 1 1 1 1 1 1 1 1  1  1
//5  0
//11 0
//5  0