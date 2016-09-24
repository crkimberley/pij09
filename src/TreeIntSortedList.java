/**
 * @author crkimberley on 23/09/2016.
 */
public class TreeIntSortedList implements IntSortedList {
    private IntNode root;

    public TreeIntSortedList() {
        root = null;
    }

    public TreeIntSortedList(int value) {
        root = new IntNode(value);
    }

    @Override
    public void add(int newNumber) {
        add(root, newNumber);
    }

    public void add(IntNode node, int newNumber) {
        if (newNumber < node.value) {
            if (node.left == null) {
                node.left = new IntNode(newNumber);
            } else {
                add(node.left, newNumber);
            }
        } else {
            if (node.right == null) {
                node.right = new IntNode(newNumber);
            } else {
                add(node.right, newNumber);
            }
        }
    }

    @Override
    public boolean contains(int numberToCheck) {
        return contains(root, numberToCheck);
    }

    public boolean contains(IntNode node, int numberToCheck) {
        if (numberToCheck == node.value) {
            return true;
        } else if (numberToCheck > node.value) {
            return node.right != null && contains(node.right, numberToCheck);
        } else {
            return node.left != null && contains(node.left, numberToCheck);
        }
    }

    public String toString() {
        return recursiveStringBuilder(root);
    }

    private String recursiveStringBuilder(IntNode node) {
        return node.value + (node.left != null ? "," + recursiveStringBuilder(node.left) : "")
                + (node.right != null ? "," + recursiveStringBuilder(node.right) : "");
    }

    private static class IntNode {
        private int value;
        private IntNode left;
        private IntNode right;

        public IntNode(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        public String toString() {
            return "" + value;
        }
    }
}
