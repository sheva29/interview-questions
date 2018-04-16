/**
     * Wrire a binary search tree class
     * @return binary search tree class that contains height() method, find() element, checks if the tree is binary search isBTree
     * and prints preorder, in order and postorder
*/
public class BSTree {

    Node root;

    public static void insert(int val) {
        if (root == null) root = new Node(val);    
        insert(val, root);

    }

    private static void insert(int val, Node curr) {
        if (curr == null) curr = new Node(val);

        if( val > curr.val){
            insert(val, curr.right);
        } else {
            insert(val, curr.left);
        }
    }

    public static Node find(int val){
        if (val == root.val) return root;
        return find(val, root);    
    }

    private static Node find(int val, Node curr){
        if (val == curr.val) return curr;

        if( val > curr.val){
            return find(val, curr.left);
        } else {
            return find(val, curr.right);
        }
    }

    // return length of maximum branch
    public static int height(){
        if (root == null) return 0;
        return height(root);
    }

    private int height(Node current) {
        if (current == null) return 0;
        int left = height(current.left);
        int right = height(current.right);

        return Math.max(left, right) + 1;
    }

    public boolean isBST() {
        if ( root == null) return true;

        return isBST(Integer.MIN_VALUE, Integer.MAX_VALUE, root);
    }

    private boolean isBST(int min, int max, Node curr) {
        if (curr == null) return true;

        if (curr.val >= min && curr.val < max ){

            return isBST(min, curr.val ,curr.left) && isBST(curr.val, max ,curr.right);

        } else {

            return false;
        }

    }

    public void printPreOrder() {
        printPreOrder(root);

    }

    private void printPreOrder(Node curr){
        if (curr == null ) return;
        System.out.println(curr.val);
        printPreOrder(curr.left);
        printPreOrder(curr.right);    
    }
 
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder() {
        if (curr == null ) return;        
        printInOrder(curr.left);
        System.out.println(curr.val);
        printInOrder(curr.right);    
    }

    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder() {
        if (curr == null ) return;        
        printPostOrder(curr.left);        
        printPostOrder(curr.right);    
        System.out.println(curr.val);
    }
}

// base Node class for tree
public class Node {
    Node left;
    Node right;
    int val;
    Node(int val){
        this.val = val;
    }
}