              
// write a function that given a tree, returns the second highest sum
//
//          3     (3)
//         / \    
//        2   5   (7)       
//       /
//      1         (1)

public int returnHighest(TreeNode root) {
    if (root == null) return 0;
    HashMap<Integer,Integer> result = new HashMap<Integer,Integer>();
    height(root, result, 1);
    int heightest = 0, second = 0, level1 = 0, level2 = 0;
    
    for (Map.Entry<Integer,Integer> e : result.getEntry()) {
        if (e.getValue() > heightest ) {
            second = heightest;
            level2 = level1;
            heightest = e.getValue();
            level1 = e.getKey();
        }
        if (e.getValue() <= heightest && e.getValue() > second) {
            level2 = e.getKey();
        }
    }    
    return level2;    
}

private void height(TreeNode root, HashMap<Integer,Integer> result, int level) {
    if (root == null) return;    
    if (result.get(level) == null) {
        result.put(level, root.val);
    } else {
        int curr = result.get(level);
        result.put(level, curr + root.val);
    }    
    level++;
    height(root.left, result, level);
    height(root.right, result, level);
}
