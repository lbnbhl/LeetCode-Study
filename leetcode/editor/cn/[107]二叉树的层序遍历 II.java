//给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历） 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[15,7],[9,20],[3]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 663 👎 0


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
/*
* 利用两个队列，一个队列做层序，一个队列将上层的数据放在栈底
* */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();
        ArrayDeque<TreeNode> queue1 = new ArrayDeque<>();
        ArrayDeque<List<Integer>> queue2 = new ArrayDeque<>();
        queue1.addFirst(root);
        while (!queue1.isEmpty()){
            int size = queue1.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0){
                TreeNode temp = queue1.pollFirst();
                list.add(temp.val);
                if (temp.left != null) queue1.addLast(temp.left);
                if (temp.right != null) queue1.addLast(temp.right);
                size--;
            }
            queue2.addFirst(list);
        }
        return new ArrayList<>(queue2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
