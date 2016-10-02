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

        // Convert vine to balanced tree
        // using Day-Stout-Warren algorithm
        IntegerTreeNode pseudoRoot = new IntegerTreeNode();
        int backboneLength = size - 1;
        pseudoRoot.right = root;
        for (int i=backboneLength/2; i>0; i=backboneLength/2) {
            IntegerTreeNode temp = pseudoRoot;
            for (int j=0; j<i; j++) {
                // Rotate left
                IntegerTreeNode child = temp.right;
                temp.right = child.right;
                temp = temp.right;
                child.right = temp.left;
                temp.left = child;
            }
            backboneLength = backboneLength - i - 1;
        }
        return pseudoRoot.right;
    }

    public IntegerTreeNode remove(int value) {
        System.out.println("Looking for " + value);
        IntegerTreeNode a = this, b = null, c, d, root = this;
        // Finding node to be deleted
        while (a != null && a.value != value) {
            b = a;
            a = value < a.value ? a.left : a.right;
        }
        if (a == null) {
            System.out.println(value + " not found");
        } else {
            // If just one non-empty subtree, it replaces a
            // a points to the node to be deleted and b to its parent, unless a==root
            // Now find c (perhaps null) to replace a and prepare for linking b to it
            if (a.left == null) {
                c = a.right;
            } else if (a.right == null) {
                c = a.left;
            } else {
                // Finding the in order successor of a
                // a has two non-empty subtrees, so make c=insucc(a)
                // Note: insucc(a).left == null
                c = a.right;
                if (c.left != null) {
                    do {
                        d = c;
                        c = c.left;
                    } while (c.left != null);
                    // Now link c's right subtree to c's parent d
                    d.left = c.right;
                    c.right = a.right;
                }
                c.left = a.left;    // c inherits a's subtrees
            }
            // Lastly link b to c
            if (a == root) {
                root = c;
            } else if (b.left == a) {
                b.left = c;
            } else {
                b.right = c;
            }
        }
        return root;
    }

    public void inOrderTraversal() {
        System.out.println("\nIn Order traversal of: " + this);
        inOrderTraversal(this);
        System.out.println();
    }

    public void inOrderTraversal(IntegerTreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println("\tNode: " + node.value + "  Left: " + (node.left != null ? node.left.value : "null")
                    + "  Right: " + (node.right != null ? node.right.value : "null"));
            inOrderTraversal(node.right);
        }
    }
}