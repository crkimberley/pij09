/**
 * @author crkimberley on 24/09/2016.
 */
public class ListIntSortedList implements IntSortedList {
    private int value;
    private ListIntSortedList next;

    public ListIntSortedList(int value) {
        this.value = value;
        next = null;
    }

    @Override
    public void add(int newNumber) {
        ListIntSortedList newNode = new ListIntSortedList(newNumber);
        if (newNumber < value) {
            int tempValue = value;
            value = newNumber;
            newNode.value = tempValue;
            newNode.next = this.next;
            this.next = newNode;
            return;
        }
        ListIntSortedList temp = this;
        while (temp.next != null && temp.next.value < newNumber) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    @Override
    public boolean contains(int numberToCheck) {
        ListIntSortedList temp = this;
        while (temp != null) {
            if (temp.value == numberToCheck) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public String toString() {
        return "" + this.value + (next != null ? "," + next : "");
    }
}
