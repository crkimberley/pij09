/**
 * @author crkimberley on 24/09/2016.
 */
public class ListIntSortedListTest {
    public static void main(String[] args) {
        new ListIntSortedListTest().launch();
    }

    private void launch() {
        IntSortedList list = new ListIntSortedList(50);

        list.add(23);

        System.out.println(list);

        list.add(33);
        list.add(117);
        list.add(45);
        list.add(69);
        list.add(157);
        list.add(94);
        list.add(22);

        System.out.println(list);

        list.add(157);

        System.out.println(list);

        System.out.println("contains 69?  -  " + list.contains(69));
        System.out.println("contains 999?  -  " + list.contains(999));
        System.out.println("contains 22?  -  " + list.contains(22));
    }
}
