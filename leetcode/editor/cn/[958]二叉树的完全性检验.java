//给定一个二叉树的
// root ，确定它是否是一个 完全二叉树 。 
//
// 在一个 完全二叉树 中，除了最后一个关卡外，所有关卡都是完全被填满的，并且最后一个关卡中的所有节点都是尽可能靠左的。它可以包含
// 1 到
// 2ʰ 节点之间的最后一级 h 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,6]
//输出：true
//解释：最后一层前的每一层都是满的（即，结点值为 {1} 和 {2,3} 的两层），且最后一层中的所有结点（{4,5,6}）都尽可能地向左。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,null,7]
//输出：false
//解释：值为 7 的结点没有尽可能靠向左侧。
// 
//
// 
//
// 提示： 
//
// 
// 树的结点数在范围 
// [1, 100] 内。 
// 1 <= Node.val <= 1000 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 243 👎 0


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
    public boolean isCompleteTree(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        boolean flag = false;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                TreeNode temp = queue.pollFirst();
                if (temp.left != null || temp.right != null){
                    if (flag == true) return false;
                    if (temp.left != null) {
                        queue.addLast(temp.left);
                        if (temp.right != null){
                            queue.addLast(temp.right);
                        }else {
                            flag = true;
                        }
                    }else
                        return false;
                }else
                    flag = true;
                size--;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/*
* 大佬的递归版本
* 在递归搜索过程中，记录节点个数n和当前最大节点编号值p。
* 最后判断最大节点值p和节点数n是否相等，相等则该二叉树是完全二叉树，否则不是。
* */
class Solution {

    int n = 0, p = 0;

    public boolean isCompleteTree(TreeNode root) {

        if(!dfs(root,1)) return false;

        return n == p;

    }

    public boolean dfs(TreeNode root , int k) //k是当前节点编号

    {

        if(root == null) return true;  //递归到了叶子节点

        if(k > 100) return false;

        n++;  p = Math.max(p,k); //记录节点数和最大节点编号值

        return dfs(root.left,2*k)&&dfs(root.right,2*k + 1); //递归左右子树

    }

}