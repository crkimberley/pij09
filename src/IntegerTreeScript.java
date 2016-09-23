/**
 * @author crkimberley on 23/09/2016.
 */
public class IntegerTreeScript {
    public static void main(String[] args) {
        new IntegerTreeScript().launch();
    }

    private void launch() {
        IntegerTreeNode tree = new IntegerTreeNode(6);


        System.out.println(tree);
        tree.add(9);
        System.out.println(tree);
        tree.add(5);
        System.out.println(tree);
        tree.add(3);
        System.out.println(tree);

        System.out.println("max = " + tree.getMax() + ", min = " + tree.getMin());

        System.out.println("Contains 9?  -  " + tree.contains(9));
        System.out.println("Contains 22?  -  " + tree.contains(22));
    }
}
