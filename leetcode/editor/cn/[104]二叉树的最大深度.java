//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1508 👎 0


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
    public int maxDepth(TreeNode root) {
//        return dfs(root,0);
        if (root == null) return 0;
        return bfs(root);
    }

    //深度优先遍历
    int dfs(TreeNode temp,int sum){
        if (temp != null)
            sum+=1;
        else
            return sum;
        return Math.max(dfs(temp.left,sum),dfs(temp.right,sum));
    }

    //广度优先遍历的变形，为了计算层级，把每一层的节点的队列先清空再计数
    int bfs(TreeNode root){
        int sum=0;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            while (size>0){
                TreeNode temp=deque.pollFirst();
                if (!(temp.left == null && temp.right == null)){
                    if (temp.left != null)
                        deque.addLast(temp.left);
                    if (temp.right != null)
                        deque.addLast(temp.right);
                }
                size--;
            }
            sum++;
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
