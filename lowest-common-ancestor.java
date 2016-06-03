/**
 * A simple class defining a binary tree with some utlity methods on it.
 */
public class Node {
    private int mValue;
    private Node mLeft;
    private Node mRight;

    /**
     * Suppose you are given a binary search tree, e.g.
     *      4
     *     / \
     *    2   5
     *   / \
     *  1   3
     * and a list of targets, e.g. 1,3. Then their lowest common ancestor would be 2.
     * This algorithm finds the lowest common ancestor of a list of targets in a tree.
     *
     * This is the fastest solution, with O(k + log(n/k)) runtime, but I'll show
     * a few other solutions as well.
     *
     * This function supposes that all of the targets are in the tree, if that
     * assumption is not valid, you can run {@code verifyExistence}, but that
     * algorithm has runtime O(k log(n)), so it is a speed concern.
     *
     * @param root the head of a tree to search for a lowest common ancestor in.
     * @param targets the list of targets to find the lowest common ancestor of.
     */
    public static Node lowestCommonAncestor(Node root, List<Integer> targets) {
        // O(k) operation
        int minTarget = findMin(targets);
        // O(k) operation
        int maxTarget = findMax(targets);

        if (root == null) {
            return null;
        }

        /*
         * O(log n/k) operation.
         *
         * Note that this assumes that all of targets are in the tree, since you
         * could theoretically have k targets that are not in the tree, and you
         * have no guarantee that they aren't all parented by a leaf node.
         *
         * The reason we divide by a factor of k is because when all of the
         * targets are in the tree, there is a guarantee that we won't have to
         * search any deeper than the first target. In fact, because of the
         * structure of the algorithm, the case where we go deepest in the tree
         * is when the targets form the values of a leaf subtree. In that case,
         * we only go as deep as the first target node, i.e. O(log n - log k),
         * or O(log n/k). In all other cases, the parent of the min and max will
         * actually be above the target node, so the complexity is even lower.
         */
        return lowestCommonAncestor(root, minTarget, maxTarget);
    }

    /**
     * Suppose you are given a binary search tree, e.g.
     *      4
     *     / \
     *    2   5
     *   / \
     *  1   3
     * and a list of targets, e.g. 1,3. Then their lowest common ancestor would be 2.
     * This algorithm finds the lowest common ancestor of a list of targets in a tree.
     *
     * This is not a very fast solution, but it does have the benefit of
     * verifying the existence of every node in the tree. There is another
     * optimization that could be made to only look at the min/max targets, but
     * it doesn't improve the runtime complexity of the algorithm, so I've
     * skipped that implementation.
     *
     * The runtime complexity of this algorithm is O(k log n).
     *
     * @param root the head of a tree to search for a lowest common ancestor in.
     * @param targets the list of targets to find the lowest common ancestor of.
     */
    public static Node lowestCommonAncestorStackBased(Node root, List<Integer> targets) {
        if (root == null) {
            return null;
        }

        // First, we just grab a list of paths to all of the targets.
        List<Stack<Node>> pathsToTargets = new ArrayList<Stack<Node>>(targets.size());
        for (Integer target : targets) {
            Stack<Node> pathToTarget = findPathToTarget(root, target);

            if (pathToTarget == null) {
                return null;
            }

            pathsToTargets.add(findPathToTarget(root, target));
        }

        int minLength = pathsToTargets.get(0).size();
        for (Stack<Node> path : pathsToTargets) {
            if (path.size() < minLength) {
                minLength = path.size();
            }
        }

        for (Stack<Node> path : pathsToTargets) {
            while (path.size() > minLength) {
                path.pop();
            }
        }

        // Now we can just keep popping until all of the stacks line up.
        while (pathsToTargets.get(0).size() > 0) {
            boolean areEqual = true;
            Node top = pathsToTargets.get(0).peek();

            // verify that they're all equal.
            for (Stack<Node> path : pathsToTargets) {
                if (path.peek() != top) {
                    areEqual = false;
                    break;
                }
            }

            if (areEqual) {
                return top;
            }

            // look at the next node since nothing lined up
            for (Stack<Node> path : pathsToTargets) {
                path.pop();
            }
        }

        // This should never happen - if root is unchanged and the targets are
        // in the tree, they should always have a common ancestor of root.
        return null;
    }


    private static Node lowestCommonAncestor(Node root, int minTarget, int maxTarget) {
        if (root.getValue() > minTarget && root.getValue() < maxTarget) {
            return root;
        } else if (root.getValue() > maxTarget && root.getLeft() != null) {
            return lowestCommonAncestor(root.getLeft(), minTarget, maxTarget);
        } else if (root.getValue() < minTarget && root.getRight() != null) {
            return lowestCommonAncestor(root.getRight(), minTarget, maxTarget);
        } else {
            return root;
        }
    }

    /**
     * Finds the lowest value in a list of integers. Note that the best
     * solution would probably be to use Math.min(targets), but I've implemented
     * it myself here to demonstrate the understanding.
     * @param targets the list of targets to find the min of
     * @return the target with the lowest value.
     */
    private static int findMin(List<Integer> targets) {
        findBest(targets, true);
    }

    /**
     * Finds the highest value in a list of integers. Note that the best
     * solution would probably be to use Math.max(targets), but I've implemented
     * it myself here to demonstrate the understanding.
     * @param targets the list of targets to find the max of
     * @return the target with the highest value.
     */
    private static int findMax(List<Integer> targets) {
        findBest(targets, false);
    }

    /**
     * A worker function to provide a common implementation for findMin/findMax.
     */
    private static int findBest(List<Integer> targets, boolean findMin) {
        int best = findMin ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        for (Integer target : targets) {
            if ((findMin && target < best) && (findMax && target > best)) {
                best = target;
            }
        }
        return best;
    }
}
