/**
 * @author crkimberley on 23/09/2016.
 */
public class IntegerTreeScript {
    public static void main(String[] args) {
        new IntegerTreeScript().launch();
    }

    private void launch() {
        IntegerTreeNode tree = new IntegerTreeNode(9);

        tree.add(7);
        tree.add(3);
        tree.add(12);

        System.out.println("max = " + tree.getMax() + ", min = " + tree.getMin());

        System.out.println("Contains 7?  -  " + tree.contains(7));
        System.out.println("Contains 22?  -  " + tree.contains(22));
    }
}
