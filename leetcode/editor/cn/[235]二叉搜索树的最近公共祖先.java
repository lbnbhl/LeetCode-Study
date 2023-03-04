//给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//输出: 6 
//解释: 节点 2 和节点 8 的最近公共祖先是 6。
// 
//
// 示例 2: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//输出: 2
//解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉搜索树中。 
// 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1024 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 */
//先找到一个节点的路径，再看另一个节点的路径的节点在不在set中
//    缺点：未考虑搜索树的有序性，时间复杂度高
class Solution {
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    //    ArrayDeque<Integer> temp = new ArrayDeque<>();
    HashSet<Integer> set;
    int res;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root,p,q);
        return new TreeNode(res);
    }
    void dfs(TreeNode root,TreeNode p,TreeNode q){
        if (root == null)
            return;
        else{
            queue.addLast(root.val);
            if (root.val == p.val || root.val == q.val){
                if (set == null){
                    set = new HashSet<>(queue);
                }else {
                    int n=queue.pollLast();
                    while (!set.contains(n)){
                        n=queue.pollLast();
                    }
                    res = n;
                }
            }
            dfs(root.left,p,q);
            dfs(root.right,p,q);
            queue.pollLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

/*
* 考虑到二叉搜索树的有序性，找分叉点
* 我们从根节点开始遍历；
如果当前节点的值大于 ppp 和 qqq 的值，说明 ppp 和 qqq 应该在当前节点的左子树，因此将当前节点移动到它的左子节点；
如果当前节点的值小于 ppp 和 qqq 的值，说明 ppp 和 qqq 应该在当前节点的右子树，因此将当前节点移动到它的右子节点；
如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」。此时，ppp 和 qqq 要么在当前节点的不同的子树中，要么其中一个就是当前节点。
*/
class Solution2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> path_p = getPath(root, p);
        List<TreeNode> path_q = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
            if (path_p.get(i) == path_q.get(i)) {
                ancestor = path_p.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    public List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<TreeNode>();
        TreeNode node = root;
        while (node != target) {
            path.add(node);
            if (target.val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        path.add(node);
        return path;
    }
}