import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @autor wwl
 * @date 2023/3/2-21:34
 */
public class L102 {

    TreeNode res;
//    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
//        res = root1;
//        dfs(root2);
//        return root1;
//    }
//    void dfs(TreeNode root2){
//        if (res == null && root2 == null) {
//            return;
//        }else if (res != null && root2 == null){
//            return;
//        }else if (res == null) {
//            res = root2;
//            return;
//        }else{
//            res.val += root2.val;
//        }
//        TreeNode temp = res;
//        res = res.left;
//        dfs(root2.left);
//        res = temp.right;
//        dfs(root2.right);
//    }

//    public static void main(String[] args) {
//        L102 l102 = new L102();
//        l102.mergeTrees(new TreeNode(1),new TreeNode(1,new TreeNode(2),null));
////        List<TreeNode> list1 = new ArrayList<>();
////        List<Integer> list = list1.stream().map(treeNode -> {
////            return treeNode.val;
////        }).collect(Collectors.toList());
////        ArrayDeque<TreeNode> queue = new ArrayDeque((Collection) new TreeNode(1));
////        List<Integer> list = new ArrayList<>();
////        System.out.println(list.get(0));
////        System.out.println();
//
//    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()){
//            double n = scanner.nextInt();
//            double m = scanner.nextInt();
//            double res = (n-Math.pow(n,m)*n)/(1-n);
//            System.out.printf("%.2f\n",res);
//        }
//    }

    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j]*dp[i-j-1];
            }
        }
        return dp[n];
    }

    public int findContentChildren(int[] g, int[] s) {
        g = sort(g);
        s = sort(s);
        int res = 0,k=0;
        for (int i = 0; i < g.length; i++) {
            for (int j = k; j < s.length;j=k) {
                if (g[i] <= s[j]) {
                    res++;
                    k = j+1;
                    break;
                }else
                    k++;
            }
        }
        return res;
    }

    int[] sort(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            int min = Integer.MAX_VALUE,p = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < min){
                    min = nums[j];
                    p = j;
                }
            }
            nums[p] = nums[i];
            nums[i] = min;
        }
        return nums;
    }

    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int low = 0, res = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i == 0) {
                if (prices[i] <= prices[i + 1]) {
                    res -= prices[i];
                    low = 1;
                }
            }else if (i == prices.length - 1) {
                if (low == 1)
                    res += prices[i];
                return res;
            }else {
                if (prices[i] <= prices[i - 1] && prices[i] < prices[i + 1]) {
                    low = 1;
                    res -= prices[i];
                }
                if (prices[i] > prices[i - 1] && prices[i] >= prices[i + 1]) {
                    if (low == 1) {
                        res += prices[i];
                        low = 0;
                    }
                }
            }
        }
        return res;
    }

    public int jump(int[] nums) {
        int m=0,res = 0;
        if (nums.length < 2) return 0;
        for (int i = 0; i < nums.length;) {
            int k = i+nums[i];
            if (k >= nums.length-1){
                res++;
                return res;
            }
            res++;
            for (int j = i+1; j <= k; j++) {
                if (j+nums[j] >= m){
                    m = j+nums[j];
                    i = j;
                }
                if (j+nums[j] >= nums.length-1) {
                    res++;
                    return res;
                }
            }
        }
        return res;
    }

    /**
     分两个阶段
     1、起点下标1 从左往右，只要 右边 比 左边 大，右边的糖果=左边 + 1
     2、起点下标 ratings.length - 2 从右往左， 只要左边 比 右边 大，此时 左边的糖果应该 取本身的糖果数（符合比它左边大） 和 右边糖果数 + 1 二者的最大值，这样才符合 它比它左边的大，也比它右边大
     */
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candyVec = new int[len];
        candyVec[0] = 1;
        for (int i = 1; i < len; i++) {
            candyVec[i] = (ratings[i] > ratings[i - 1]) ? candyVec[i - 1] + 1 : 1;
        }

        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
            }
        }

        int ans = 0;
        for (int num : candyVec) {
            ans += num;
        }
        return ans;
    }

    public int[][] reconstructQueue(int[][] people) {
        // 身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return -a[1] + b[1];
            return -b[0] + a[0];
        });

        int[][] res = new int[people.length][2];
        int[] temp = new int[]{0,0};
        int[] flag = new int[]{-1,-1};
        for (int i = 0; i < people.length; i++) {
            if (Arrays.equals(people[i], temp)) {
                res[0] = flag;
                continue;
            }
            int count = 0;
            int j = 0;
            while (!Arrays.equals(res[j], temp)){
                j++;
            }
            for (; j < res.length; j++) {
                if (count == people[i][1] && Arrays.equals(res[j], temp)){
                    res[j] = people[i];
                    break;
                }else {
                    if (Arrays.equals(res[j], temp))
                        count++;
                }

            }
        }
        if (Arrays.equals(res[0], flag))
            res[0] = temp;
        return res;
    }

    public int lastStoneWeightII(int[] stones) {
        int sum = 0, target = 0, min = Integer.MAX_VALUE;
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


    public int findTargetSumWays(int[] nums, int target) {
        if(nums.length == 1) return nums[0] == target?1:0;
        int sum = 0,count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        target = (sum + target)/2;
        int[] dp = new int[target+1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i] ; j--) {
                dp[j] = Math.max(dp[j],dp[j-nums[j]] + nums[j]);
            }
            count = dp[target] == target ? count+1:count;
        }
        return count;
    }


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
                        dp[i][a][b] = 1;
                    }else
                        dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-a][k-b]+1);
                }
            }
        }
        return dp[strs.length-1][m][n];
    }


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


    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j <= target; j++) {
                dp[j] += dp[j-nums[i]];
            }
        }
        return dp[target];
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        String[] dp = new String[s.length()+1];
//        char[] chars = s.toCharArray();
        for (int i = 0; i < wordDict.size(); i++) {
            for (int j = wordDict.get(i).length(); j <= s.length(); j++) {
                String str = new String();
                if (dp[j-wordDict.get(i).length()] == null){
                    str = wordDict.get(i);
                }
                if ((str).equals(s.substring(0,j))){
                    dp[j] = s.substring(0,j);
                }
            }
        }
        return dp[s.length()].equals(s);
    }


//    public int rob(int[] nums) {
//        int flag = 1;
//        int[] dp = new int[nums.length];
//        dp[0] = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            if (flag == 1){
//                if (nums[i] > nums[i-1])
//                    if (i-2 < 0)
//                        dp[i] = dp[i-1] - nums[i-1] + nums[i];
//                    else
//                        dp[i] = dp[i-1] - nums[i-1] + nums[i] + dp[i-2];
//                else{
//                    if (dp[i-2] + nums[i] > dp[i-1]){
//                        dp[i] = dp[i-2] + nums[i];
//                    } else {
//                        dp[i] = dp[i-1];
//                        flag = 0;
//                    }
//                }
//            }else {
//                dp[i] = dp[i-1] + nums[i];
//                flag = 1;
//            }
//        }
//        return dp[nums.length-1];
//    }
//
//    public int rob1(int[] nums) {
//        int len = nums.length;
//        if (len == 1)
//            return nums[0];
//        if (len == 2)
//            return Math.max(nums[0],nums[1]);
//        if (len == 3)
//            return Math.max(Math.max(nums[0],nums[1]),nums[2]);
//        int[] dp1 = new int[len-1];
//        int[] dp2 = new int[len-1];
//        dp1[0] = nums[0];
//        dp1[1] = Math.max(nums[0],nums[1]);
//        dp2[0] = nums[1];
//        dp2[1] = Math.max(nums[1],nums[2]);
//        for (int i = 2; i < len-1; i++) {
//            dp1[i] = Math.max(dp1[i-2]+nums[i],dp1[i-1]);
//        }
//        for (int i = 2; i < len-1; i++) {
//            dp2[i] = Math.max(dp2[i-2]+nums[i+1],dp2[i-1]);
//        }
//        return Math.max(dp1[len-2],dp2[len-2]);
//    }

//    public int rob2(TreeNode root) {
//        List<Integer> dp = new ArrayList<>();
//        dfs(root,1,dp);
//        return dp.get(dp.size()-1);
//    }

//    void dfs(TreeNode root,int id,List<Integer> dp){
//        if (root == null)
//            return;
//        if (id == 1)
//            dp.add(1,root.val);
//        if (id == 2)
//            dp.add(2,Math.max(root.val,dp.get(1)));
//        dp.add(id,Math.max(dp.get(id/4)+root.val,dp.get(id/2)));
//        dfs(root.left,2*id,dp);
//        dfs(root.right,2*id+1,dp);
//    }

//    public int rob(TreeNode root) {
////        List<Integer[]> list = new ArrayList<>();
//        int[] ints = dfs(root);
//        return Math.max(root.val+ints[0],ints[1]);
//    }


//    int[] dfs(TreeNode root){
//        if (root == null){
//            return new int[]{0,0};
//        }
//        int[] left = dfs(root.left);
//        int[] right = dfs(root.right);
//        return new int[]{root.val,Math.max(left[1]+right[1],left[0]+right[0])};
//    }

    public int maxProfit3(int[] prices) {
        int min = 100000,max = 0,m=0,n=0,k=0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min){
                m = i;
                min = prices[i];
            }
            if (prices[i] - min > max){
                n = i;
                max = prices[i] - min;
            }
        }
        k = max;
        min = 100000;
        max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (m <= i && i <= n) {
                i = n;
                continue;
            }
            if (prices[i] < min){
                min = prices[i];
            }
            if (prices[i] - min > max){
                max = prices[i] - min;
            }
        }
        return max+k;
    }

    public int maxProfit4(int k, int[] prices) {
        int[][][] dp = new int[prices.length][k][2];
        for (int i = 0; i < k; i++) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j][0] = Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1],dp[i-1][j][0]-prices[i]);
            }
        }
        return dp[prices.length-1][k-1][0];
    }

    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;

        // [天数][交易次数][是否持有股票]
        int len = prices.length;
        int[][][] dp = new int[len][k + 1][2];

        // dp数组初始化
        // 初始化所有的交易次数是为确保 最后结果是最多 k 次买卖的最大利润
        for (int i = 0; i <= k; i++) {
            dp[0][i][1] = -prices[0];
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= k; j++) {
                // dp方程, 0表示不持有/卖出, 1表示持有/买入
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][k][0];
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        TreeNode node = findNode(root,key);
        changeTree(node);
        return root;
    }

    //    找到值为key的节点并返回
    TreeNode findNode(TreeNode root,int key){
        if (root == null)
            return null;
        if (root.val == key)
            return root;
        TreeNode left = null,right = null;
        if (root.val > key)
            left = findNode(root.left, key);
        else
            right = findNode(root.right, key);
        if (left != null)
            return left;
        else if (right != null)
            return right;
        else return null;
    }

    //    变化树形，使其符合条件
    TreeNode changeTree(TreeNode root){
        if (root == null)
            return null;
        if (root.left == null && root.right == null){
            root = null;
        }else {
            if (root.left != null && root.right != null){
                TreeNode[] t = dfs(root.right);
                changeTree(t[1]);
                root.val = t[1].val;
                root.right = t[0];
            }else if (root.left == null){
                root = root.right;
            }else {
                root = root.left;
            }
        }
        return root;
    }

    TreeNode[] dfs(TreeNode root){
        if (root.left == null){
            return new TreeNode[]{root,root};
        }
        TreeNode[] t = dfs(root.left);
        return new TreeNode[]{root,t[1]};
    }

    public int findLengthOfLCIS(int[] nums) {
        int left = 0,right = 1,len = 0,res=1;
        while (right < nums.length){
            if (nums[right] > nums[right-1]){
                len = right - left + 1;
                res = Math.max(res,len);
            }else {
                left = right;
            }
            right++;
        }
        return res;
    }

    public int findLength(int[] nums1, int[] nums2) {
        int max = 0,len = 0;
//        int[] dp = new int[Math.min(nums1.length,nums2.length)];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                len = 0;
                if (nums1[i] == nums2[j]){
                    int k = i;
                    while (k < nums1.length && k+j-i < nums2.length){
                        if (nums1[k] == nums2[j+k-i])
                            len++;
                        else
                            break;
                        k++;
                    }
                    if (len == nums1.length || len == nums2.length)
                        return len;
                    max = Math.max(len,max);
                }
            }
        }
        return max;
    }

    /*
     * dp[i][j]表示以下标为i-1结尾的和以下标为j-1结尾的最长公共子序列的长度
     * chars1[i] == chars2[j]:
     *        dp[i][j] = dp[i-1][j-1] + 1;
     * */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text1.toCharArray();
        int[][] dp = new int[chars1.length+1][chars2.length+1];
        for (int i = 1; i < chars1.length; i++) {
            for (int j = 1; j < chars2.length; j++) {
                if (chars1[i-1] == chars2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[chars1.length][chars2.length];
    }

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
                        if (chars1[j-1] == chars2[i-2])
                            dp[i][j] = dp[i-1][j-1]+1;
                        else
                            dp[i][j] = dp[i-1][j-1];
                    }
                } else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[chars1.length][chars2.length];
    }

    public int removeElement(int[] nums, int val) {
        int res = 0,temp;
        for (int i = 0; i < nums.length-res; i++) {
            if (nums[i] == val){
                res++;
                while (nums[nums.length-res] == val){
                    res++;
                }
                temp = nums[i];
                nums[i] = nums[nums.length-res];
            }
        }
        return nums.length-res;
    }

    public static void main(String args[]){
        L102 l102 = new L102();
        l102.removeElement(new int[]{3,2,2,3},3);
        l102.numDistinct("rabbbit","rabbit");
        l102.isSubsequence("bb","ahbgdc");
        l102.longestCommonSubsequence("b","abc");
        int[] g = new int[]{1,2,3,2,1};
        int[] k = new int[]{3,2,1,4,7};
        int[] l = new int[]{0,0,0,0,0};
        int[] m = new int[]{0,0,0,0,0};
        l102.findLength(g,k);
        l102.findLengthOfLCIS(g);
        l102.maxProfit(2,g);
        TreeNode node = new TreeNode(3, new TreeNode(2, null, new TreeNode(3)), new TreeNode(3, null, new TreeNode(1)));
//        l102.rob(node);
        String s = "applepenapple";
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
//        queue.addLast();
        int n = 12;
        int sqrt = (int) Math.sqrt(n)+1;
        System.out.println(sqrt);

//        l102.rob1(g);
//        l102.wordBreak(s,list);
        l102.combinationSum4(g,4);
        l102.change(5,g);
//        int[] s = new int[]{1,1,1,1,1};
//        l102.findTargetSumWays(s,3);
//        l102.lastStoneWeightII(s);
//        int[] a = new int[]{1,2,2,5,4,3,2};
        int[][] people= new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2},{0,0}};

        l102.reconstructQueue(people);
        // 身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });



//        l102.candy(a);
//        l102.jump(a);
//        l102.maxProfit(a);
//        l102.findContentChildren(g,s);
    }


}