/**
 * @author crkimberley on 23/09/2016.
 */
public class ListIntSetTest {
    public static void main(String[] args) {
        new ListIntSetTest().launch();
    }

    private void launch() {
        ListIntSet set = new ListIntSet(100);

        set.add(33);
        set.add(117);
        set.add(45);
        set.add(69);
        set.add(157);
        set.add(94);
        set.add(22);

        System.out.println(set);

        set.add(157);

        System.out.println(set);

        System.out.println("contains 69?  -  " + set.contains(69));
        System.out.println("contains 69?  -  " + set.containsVerbose(69));
        System.out.println("contains 999?  -  " + set.contains(999));
        System.out.println("contains 999?  -  " + set.containsVerbose(999));
        System.out.println("contains 22?  -  " + set.contains(22));
        System.out.println("contains 22?  -  " + set.containsVerbose(22));
    }
}