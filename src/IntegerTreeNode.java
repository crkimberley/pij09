import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author crkimberley on 23/09/2016.
 */
public class IntegerTreeNode {

    private int value;
    private IntegerTreeNode left;
    private IntegerTreeNode right;

    public IntegerTreeNode() {
        left = null;
        right = null;
    }

    public IntegerTreeNode(int value) {
        this.value = value;
    }

    public void add(int newNumber) {
        if (newNumber > value) {
            if (right == null) {
                right = new IntegerTreeNode(newNumber);
            } else {
                right.add(newNumber);
            }
        } else {
            if (left == null) {
                left = new IntegerTreeNode(newNumber);
            } else {
                left.add(newNumber);
            }
        }
    }

    public boolean contains(int n) {
        if (n == value) {
            return true;
        } else if (n > value) {
            return right != null && right.contains(n);
        } else {
            return left != null && left.contains(n);
        }
    }

    public int getMax() {
        if (right == null) {
            return value;
        } else {
            return right.getMax();
        }
    }

    public int getMin() {
        if (left == null) {
            return value;
        } else {
            return left.getMin();
        }
    }

    public int depth() {
        int leftDepth = 0;
        int rightDepth = 0;
        if (left != null) {
            leftDepth = 1 + left.depth();
        }
        if (right != null) {
            rightDepth = 1 + right.depth();
        }
        return leftDepth > rightDepth ? leftDepth : rightDepth;
    }

    // Question 1.2 - complicated version
    /*public String toString() {
        return "[" + value + " L" + (left != null ? left : "[]") + " R" + (right != null ? right : "[]") + "]";
    }*/

    // Question 1.2 - simplified version
    public String toString() {
        return "[" + value + (left != null ? " " + left : "")
                + (right != null ? " " + right : "") + "]";
    }

    public IntegerTreeNode rebalance() {
        return rebalance(this);
    }

    public IntegerTreeNode rebalance(IntegerTreeNode root) {
        // Convert tree to a sorted "vine" or "backbone" - a linked list
        // with right pointers from the tree pointing to the next node in the list.
        // In Order traversal of the tree is accomplished using a stack (Deque).
        Deque<IntegerTreeNode> stack = new ArrayDeque<IntegerTreeNode>();
        IntegerTreeNode currentNode = root, front = null, rear = null;
        int size = 0;

        for (;;) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            // When stack is empty, the whole tree has been traversed
            if (stack.isEmpty()) {
                break;
            } else {
                currentNode = stack.pop();
                size++;
                // Set 1st node/front
                if (front == null) {
                    front = currentNode;
                    rear = currentNode;
                } else {
                    rear.right = currentNode;
                    rear = currentNode;
                }
                currentNode.left = null;
                currentNode = currentNode.right;
            }
        }
        root = front;

        // Convert vine to balanced tree - using Day–Stout–Warren (DSW) algorithm
        IntegerTreeNode pseudoRoot = new IntegerTreeNode();
        pseudoRoot.right = root;
        int leaves = size + 1 - (2 * log2(size + 1));
        compress(pseudoRoot, leaves);
        size = size - leaves;
        while (size > 1) {
            compress(pseudoRoot, size / 2);
            size = size / 2;
        }
        return pseudoRoot.right;
    }

    private void compress(IntegerTreeNode root, int count) {
        IntegerTreeNode temp, child;
        temp = root;
        // Rotate left
        for (int i=1; i<=count; i++) {
            child = temp.right;
            temp.right = child.right;
            temp = temp.right;
            child.right = temp.left;
            temp.left = child;
        }
    }

    private int log2(int x) {
        int y,z;
        // Calculate floor log2
        z = x;
        y = -1;
        while (z > 0) {
            // signed right bit shift
            z >>= 1;
            y++;
        }
        return y;
    }

    public void inOrderTraversal() {
        inOrderTraversal(this);
    }

    public void inOrderTraversal(IntegerTreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println("Node: " + node.value + "  Left: " + (node.left != null ? node.left.value : "null")
                    + "  Right: " + (node.right != null ? node.right.value : "null"));
            inOrderTraversal(node.right);
        }
    }
}
