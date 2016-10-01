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

        System.out.println("Depth = " + tree.depth());

        IntegerTreeNode tree2 = new IntegerTreeNode(3);
        tree2.add(2);
        tree2.add(4);
        tree2.add(1);
        tree2.add(5);
        tree2.add(6);

        System.out.println(tree2);
        tree2.depth();
        System.out.println(tree2.rebalance());

        IntegerTreeNode tree3 = new IntegerTreeNode(1);
        tree3.add(2);
        tree3.add(3);
        tree3.add(4);
        tree3.add(5);
        tree3.add(6);
        tree3.add(7);
        tree3.add(8);
        tree3.add(9);
        tree3.add(10);
        tree3.add(11);
        tree3.add(12);
        tree3.add(13);
        tree3.add(14);
        tree3.add(15);
        tree3.add(16);
        tree3.add(17);
        tree3.add(18);
        tree3.add(19);
        tree3.add(20);

        System.out.println(tree3);
        tree3.depth();
        tree3.inOrderTraversal();
        tree3 = tree3.rebalance();
        System.out.println(tree3);
        tree3.inOrderTraversal();



    }
}
