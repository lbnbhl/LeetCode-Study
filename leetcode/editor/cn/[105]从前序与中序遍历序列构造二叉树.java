//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
// 
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1896 👎 0


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
 * 左 cur+1
 *
 */
class Solution {
    int cur;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return traversal(preorder,inorder,0,preorder.length-1);
    }

    TreeNode traversal(int[] preorder, int[] inorder, int left, int right){
        if (left > right){
            return null;
        }
        int num = preorder[cur++];
        TreeNode res = new TreeNode(num);
        int i;
        for (i = left; i < right; i++) {
            if (num == inorder[i])
                break;
        }
        TreeNode tl = traversal(preorder,inorder, left,i-1);
        TreeNode tr = traversal(preorder,inorder,i+1,right);
        res.left = tl;
        res.right = tr;
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

//3  1,2  4
//1   ,2

9  8  20
