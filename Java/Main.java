package Java.Trees;

public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();

        if(args.length == 0) {
            System.out.println("Usage: java main <node_values>");
            return;
        }

        for(String arg : args) {
            int num = Integer.parseInt(arg);
            tree.insert(num);
        }

        System.out.print("In-Order: ");
        tree.inOrder();

        System.out.print("Pre-Order: ");
        tree.preOrder();

        System.out.print("Post-Order: ");
        tree.postOrder();

        Node min = tree.minValueNode(tree.getRoot());
        Node max = tree.maxValueNode(tree.getRoot());
        System.out.println("Min: " + min.getKey());
        System.out.println("Max: " + max.getKey());
    }
}
