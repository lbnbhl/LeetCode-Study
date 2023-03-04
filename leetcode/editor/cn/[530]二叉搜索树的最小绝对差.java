//给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。 
//
// 差值是一个正数，其数值等于两值之差的绝对值。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [4,2,6,1,3]
//输出：1
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,0,48,null,null,12,49]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目范围是 [2, 10⁴] 
// 0 <= Node.val <= 10⁵ 
// 
//
// 
//
// 注意：本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-
//nodes/ 相同 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉搜索树 二叉树 👍 431 👎 0


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
 *
 * 根据二叉搜索树的特点，可按中序遍历的思想来
 */
class Solution {

    public static min=Integer.MAX_VALUE;

    int pre;
    int res = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        pre = -1;
        dfs2(root);
        return res;
    }

//    错误的认为，最小插值一定诞生在|根-左|，|根-右|中,实际上根的右右右和根的父节点也很近
    int dfs(TreeNode root){
        int left=Integer.MAX_VALUE,right=Integer.MAX_VALUE;

        if ((root == null) || (root.left == null && root.right == null))
            return Integer.MAX_VALUE;
        else {
            int min=Math.min(dfs(root.left),dfs(root.right));
            if (root.left != null)
                left = Math.abs(root.val-root.left.val);
            if (root.right != null)
                right = Math.abs(root.val-root.right.val);
            return Math.min(Math.min(left,right),min);
        }
    }

    //将当前值和其中序遍历的前一个值做差.....稀烂
    int dfs1(TreeNode root){
//        int temp=Integer.MAX_VALUE;

        if (root.left == null && root.right == null)
            return root.val;
        else {
            if (root.left != null && root.right == null){
                int left = dfs1(root.left);
                min = Math.min(root.val - left,min);
                return root.val;
            }else if (root.right != null && root.left == null){
                int right = dfs1(root.right);
//                temp = Math.min(min,right-root.val);
                min = Math.min(right-root.val,min);
                return root.right.val;
            }else {
                int left = dfs1(root.left);
//                temp = root.val - left;
                min = Math.min(root.val - left,min);

                int right = dfs1(root.right);
//                temp = Math.min(temp,right-root.val);
                min = Math.min(right-root.val,min);
                return root.right.val;
            }
        }
    }

    void dfs2(TreeNode root){
        if (root == null) return;
        dfs2(root.left);
        if (pre == -1) {
            pre = root.val;
        }else {
            res = Math.min(root.val - pre,res);
            pre = root.val;
        }
        dfs2(root.right);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
