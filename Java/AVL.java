package Java.Trees;

public class AVL {
    private Node root;

    private int height(Node n) {
        return n == null ? 0 : n.getHeight();
    }

    private int getBalance(Node n) {
        return n == null ? 0 : height(n.getLeft()) - height(n.getRight());
    }

    private Node rightRotate(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);

        return y;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if(node == null)
            return new Node(key);

        if(key < node.getKey())
            node.setLeft(insertRec(node.getLeft(), key));
        else if(key > node.getKey())
            node.setRight(insertRec(node.getRight(), key));
        else
            return node;

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        int balance = getBalance(node);

        if(balance > 1 && key < node.getLeft().getKey())
            return rightRotate(node);

        if(balance < -1 && key > node.getRight().getKey())
            return leftRotate(node);

        if(balance > 1 && key > node.getLeft().getKey()) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if(balance < -1 && key < node.getRight().getKey()) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if(root == null)
            return root;

        if(key < root.getKey())
            root.setLeft(deleteRec(root.getLeft(), key));
        else if(key > root.getKey())
            root.setRight(deleteRec(root.getRight(), key));
        else {
            if((root.getLeft() == null) || (root.getRight() == null)) {
                Node temp = (root.getLeft() != null) ? root.getLeft() : root.getRight();

                if(temp == null) {
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = minValueNode(root.getRight());
                root.setKey(temp.getKey());
                root.setRight(deleteRec(root.getRight(), temp.getKey()));
            }
        }

        if(root == null)
            return root;

        root.setHeight(1 + Math.max(height(root.getLeft()), height(root.getRight())));

        int balance = getBalance(root);

        if(balance > 1 && getBalance(root.getLeft()) >= 0)
            return rightRotate(root);

        if(balance > 1 && getBalance(root.getLeft()) < 0) {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }

        if(balance < -1 && getBalance(root.getRight()) <= 0)
            return leftRotate(root);

        if(balance < -1 && getBalance(root.getRight()) > 0) {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }

        return root;
    }

    public Node minValueNode(Node node) {
        Node current = node;
        while(current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

    public Node maxValueNode(Node node) {
        Node current = node;
        while(current.getRight() != null)
            current = current.getRight();
        return current;
    }

    public void inOrder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if(node != null) {
            inorderRec(node.getLeft());
            System.out.print(node.getKey() + " ");
            inorderRec(node.getRight());
        }
    }

    public void preOrder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node node) {
        if(node != null) {
            System.out.print(node.getKey() + " ");
            preorderRec(node.getLeft());
            preorderRec(node.getRight());
        }
    }

    public void postOrder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node node) {
        if(node != null) {
            postorderRec(node.getLeft());
            postorderRec(node.getRight());
            System.out.print(node.getKey() + " ");
        }
    }

    public Node getRoot() {
        return root;
    }
}
