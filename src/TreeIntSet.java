/**
 * @author crkimberley on 23/09/2016.
 */
public class TreeIntSet implements IntSet {
    private int value;
    private TreeIntSet left;
    private TreeIntSet right;

    public TreeIntSet() {
        left = null;
        right = null;
    }

    public TreeIntSet(int value) {
        this.value = value;
    }

    @Override
    public void add(int newNumber) {
        if (!contains(newNumber)) {
            addToSet(newNumber);
        }
    }

    private void addToSet(int newNumber) {
        if (newNumber > value) {
            if (right == null) {
                right = new TreeIntSet(newNumber);
            } else {
                right.add(newNumber);
            }
        } else {
            if (left == null) {
                left = new TreeIntSet(newNumber);
            } else {
                left.add(newNumber);
            }
        }
    }

    @Override
    public boolean contains(int numberToCheck) {
        if (numberToCheck == value) {
            return true;
        } else if (numberToCheck > value) {
            return right != null && right.contains(numberToCheck);
        } else {
            return left != null && left.contains(numberToCheck);
        }
    }

    @Override
    public boolean containsVerbose(int numberToCheck) {
        System.out.println("Checking: " + value);
        if (numberToCheck == value) {
            return true;
        } else if (numberToCheck > value) {
            return right != null && right.containsVerbose(numberToCheck);
        } else {
            return left != null && left.containsVerbose(numberToCheck);
        }
    }

    public String toString() {
        return value + (left != null ? "," + left : "")
                + (right != null ? "," + right : "");
    }
}
