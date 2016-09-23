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

    // Question 1.2 asks for this complicated version first
    /*public String toString() {
        return "[" + value + " L" + (left != null ? left : "[]") + " R" + (right != null ? right : "[]") + "]";
    }*/

    // Question 1.2 then wants this simplified version
    public String toString() {
        return "[" + value + (left != null ? " " + left : "") + (right != null ? " " + right : "") + "]";
    }

}
