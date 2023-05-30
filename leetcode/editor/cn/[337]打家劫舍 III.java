//小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为
// root 。 
//
// 除了
// root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的
//房子在同一天晚上被打劫 ，房屋将自动报警。 
//
// 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [3,2,3,null,3,null,1]
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7 
//
// 示例 2: 
//
// 
//
// 
//输入: root = [3,4,5,1,3,null,1]
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
// 
//
// 
//
// 提示： 
//
// 
// 
//
// 
// 树的节点数在 [1, 10⁴] 范围内 
// 0 <= Node.val <= 10⁴ 
// 
//
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1605 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
//    int temp1 = 0,temp2 = 0;
////    超时
//    public int rob(TreeNode root) {
//        List<Integer> dp = new ArrayList<>();
//        dp.add(0,0);
//        Deque<TreeNode> queue = new ArrayDeque<>();
//        queue.addLast(root);
//        int id = 0;
//        while (queue.size()>0){
//            id++;
//            TreeNode node = queue.pollFirst();
//            if (id == 1) {
//                dp.add(1, root.val);
//                temp1 = root.val;
//            }else if (id == 2){
//                dp.add(2, Math.max(root.val, dp.get(1)));
//                temp2 = root.val;
//            }else if (id == 3){
//                dp.add(3,Math.max(temp1,temp2+root.val));
//            }else if (id%2 == 0){
//                dp.add(id,Math.max(dp.get(id/4)+dp.get(id-1)-dp.get(id/2)+root.val,
//                        dp.get(id/2)+dp.get(id-1)-dp.get(id/2)));
//            }else {
//                dp.add(id,Math.max(dp.get(id/4)+dp.get(id-1)-dp.get(id/2)+root.val,
//                        dp.get(id/2)+dp.get(id-2)-dp.get(id/2)));
//            }
//
//            if (node.left == null){
//                dp.add(id,dp.get(id-1));
//            }else {
//                queue.addLast(root.left);
//            }
//            if (node.right == null){
//                dp.add(id,dp.get(id-1));
//            }else {
//                queue.addLast(root.right);
//            }
//
//        }
//        return dp.get(dp.size()-1);
//    }

    public int rob(TreeNode root) {
//        List<Integer[]> list = new ArrayList<>();
        int[] ints = dfs(root);
        return Math.max(ints[0],ints[1]);
    }


    int[] dfs(TreeNode root){
        if (root == null){
            return new int[]{0,0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        return new int[]{Math.max(root.val+left[1]+right[1],left[0]+right[0]),left[0]+right[0]};
    }

}
//leetcode submit region end(Prohibit modification and deletion)
