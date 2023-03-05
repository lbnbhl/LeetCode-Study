//在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。 
//
// 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记； 
//
// 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。 
//
// 
//
// 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。 
//
// 
//
// 示例 1： 
//
// 输入：label = 14
//输出：[1,3,4,14]
// 
//
// 示例 2： 
//
// 输入：label = 26
//输出：[1,2,6,10,26]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= label <= 10^6 
// 
//
// Related Topics 树 数学 二叉树 👍 192 👎 0

/*
* 由每个节点都有两个子节点，可想到完全二叉树的标号规律
* 偶数层的值 num = （2的depth-1次方）+（2的depth次方）-1-id ，例如 3 = 2的1次方+2的2次方-1-2
* */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        int depth = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        while (label > pow(2,depth)-1){
            depth++;
        }
        while (depth > 0){
            if (depth % 2 == 0){
                queue.addFirst(label);
                int id = pow(2,depth-1) + pow(2,depth) -1 - label;
                label = id / 2;
                depth--;
            }else {
                queue.addFirst(label);
                depth--;
                label = pow(2,depth-1) + pow(2,depth) -1 - label/2;
            }
        }
        return new ArrayList<Integer>(queue);
    }

    int pow(int a,int b){
        int res=1;
        for (int i = 0; i < b; i++) {
            res = res*a;
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
