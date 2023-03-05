//给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。 
//
// 请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,7,0,7,-8,null,null]
//输出：2
//解释：
//第 1 层各元素之和为 1，
//第 2 层各元素之和为 7 + 0 = 7，
//第 3 层各元素之和为 7 + -8 = -1，
//所以我们返回第 2 层的层号，它的层内元素之和最大。
// 
//
// 示例 2： 
//
// 
//输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在
// [1, 10⁴]范围内
// 
// -10⁵ <= Node.val <= 10⁵ 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 98 👎 0


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
* dfs和bfs同样需要遍历全部元素，dfs需要用到列表记录每层结果，bfs需要用到队列来存放元素
* 但节点值有负数，max的值不好维护，如果最后依赖列表值再找max，效率较低，使用选择用bfs
* */
class Solution {

    int max,res;

    public int maxLevelSum(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        max = root.val;
        res = 1;
        int depth = 1;
        TreeNode temp;
        while (!queue.isEmpty()){
            int size = queue.size();
            int sum=0;
            boolean flag = false;
            while (size>0){
                temp = queue.pollFirst();
                if (temp.left != null || temp.right != null)
                    flag = true;
                if (temp.left != null){
                    queue.addLast(temp.left);
                    sum+=temp.left.val;
                }
                if (temp.right != null){
                    queue.addLast(temp.right);
                    sum+=temp.right.val;
                }
                size--;
            }
            depth++;
            if (flag) {
                if (sum > max){
                    max = sum;
                    res = depth;
                }
            }
        }
        return res;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
