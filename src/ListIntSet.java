/**
 * @author crkimberley on 23/09/2016.
 */
public class ListIntSet implements IntSet {
    private IntNode head;
    private int length;

    public ListIntSet(int newNumber) {
        head = new IntNode(newNumber);
    }

    public ListIntSet() {
        head = null;
        length = 0;
    }

    @Override
    public void add(int newNumber) {
        if (!contains(newNumber)) {

            IntNode newNode = new IntNode(newNumber);
            if (head == null) {
                head = newNode;
            } else {
                IntNode temp = head;
                while (temp.link != null) {
                    temp = temp.link;
                }
                temp.link = newNode;
            }
        }
    }

    @Override
    public boolean contains(int numberToCheck) {
        IntNode temp = head;
        while (temp != null) {
            if (temp.value == numberToCheck) {
                return true;
            }
            temp = temp.link;
        }
        return false;
    }

    @Override
    public boolean containsVerbose(int numberToCheck) {
        IntNode temp = head;
        while (temp != null) {
            System.out.println("Checking: " + temp.value);
            if (temp.value == numberToCheck) {
                return true;
            }
            temp = temp.link;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntNode temp = head;
        while (temp != null) {
            sb.append(temp.value);
            temp = temp.link;
            sb.append(temp != null ? "," : "");
        }
        return sb.toString();
    }

    private static class IntNode {
        private int value;
        private IntNode link;

        public IntNode(int value) {
            this.value = value;
            link = null;
        }
    }
}
