//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。 
//
// 
//
// 示例 : 给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
//
// Related Topics 树 深度优先搜索 二叉树 👍 1242 👎 0


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
 * 解：方法一（不知道咋实现）
 *    1.直径长度一定是两个叶子节点间的距离
 *    2.任意两个叶子节点间的距离可看作她们各自到根节点的距离之和减去重复的那部分
 *    3.借用哈夫曼树的思想，是否叶子节点
 *        4 可以看作 0 0
 *        5 可以看作 1 0
 *        3 可以看作   1
 *      那么对应位置一个存在一个不存在则长度+1，对应位异或为1，则长度+2
 *
 *    方法二dfs
 *    1.从底层向上开始，dfs（root）表示root节点的长度
 *
 */
class Solution {
    int max = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root.left == null && root.right == null)
            return 0;
        dfs(root);
        return max;
    }

    int dfs(TreeNode root){
        if (root.left == null && root.right == null)
            return 0;
        int left = 0,right = 0;
        if (root.left != null) {
            left = dfs(root.left) + 1;
        }
        if (root.right != null) {
            right = dfs(root.right) + 1;
        }
        max = Math.max(max,left+right);
        return Math.max(left,right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
