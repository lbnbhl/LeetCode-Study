//给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,3,null,null,2]
//输出：[3,1,null,null,2]
//解释：3 不能是 1 的左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
// 
//
// 示例 2： 
// 
// 
//输入：root = [3,1,4,null,null,2]
//输出：[2,1,4,null,null,3]
//解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。 
//
// 
//
// 提示： 
//
// 
// 树上节点的数目在范围 [2, 1000] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// 
//
// 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用 O(1) 空间的解决方案吗？ 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 833 👎 0


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
* 1 2 3 4 5 6 7 8 9 10
* 其中任意两个两个数交换位置，首先 肯定对这两个数前面的数没有影响，因为这两个数都大于它，如2，3交换对1没影响
* 同理，对这两个数后面的数没影响
* 所以只需要关注四个数
* 如 2 ， 7 交换
* 1 7 3 4 5 6 2 8 9 10
* 只需要关注 7 3 6 2 四个数即可
* */
class Solution {
    TreeNode preNode;
    long pre = Long.MIN_VALUE;
    ArrayList<TreeNode> list = new ArrayList<>();
    public void recoverTree(TreeNode root) {
        dfs(root);
//        int[] temp = new int[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            int val = list.get(i).val;
//            temp[i] = val;
//        }
//        temp = Arrays.stream(temp).sorted().toArray();
//        for (int i = 0; i < temp.length; i++) {
//            list.get(i).val = temp[i];
//        }
    }
    void dfs(TreeNode root){
        if (root == null) return;
        dfs(root.left);
        if (pre == Long.MIN_VALUE){
            pre = root.val;
            preNode = root;
        }
        if (preNode.val > root.val){
            list.add(preNode);
            list.add(root);
        }
        preNode = root;
        dfs(root.right);

    }
}
//leetcode submit region end(Prohibit modification and deletion)