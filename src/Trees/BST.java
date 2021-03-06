package Trees;

public class BST {
    private TreeNode root;

    public void insert(int data) {
        if(root == null) {
            root = new TreeNode(data);
        } else {
            root.insert(data);
        }
    }

    // basically means if the element is in the  tree
    public TreeNode get(int target) {
        if(root == null) {
            return null;
        } else {
            return root.get(target);
        }
    }

    public void delete(int target) {
        root = delete(root, target);
    }

    // this method returns the replacement tree node to the caller
    public TreeNode delete(TreeNode subtreeRoot, int target) {
        // check if the subtree is empty
        if(subtreeRoot == null) {
            return null;
        }

        // locate the target value to be deleted
        if(target < subtreeRoot.getData()) {
            subtreeRoot.setLeftChild(delete(subtreeRoot.getLeftChild(), target));
        } else if(target > subtreeRoot.getData()) {
            subtreeRoot.setRightChild(delete(subtreeRoot.getRightChild(), target));
        } else { // found the target value
            // check whether it has 0 or 1 child
            if(subtreeRoot.getLeftChild() == null) {
                // no left child
                return subtreeRoot.getRightChild();
            } else if (subtreeRoot.getRightChild() == null){
                // no right child
                return subtreeRoot.getLeftChild();
            } else {
                // having two children nodes
                // this implementation selects the smallest value from the right subtree
                // and replace the current root with its value
                subtreeRoot.setData(subtreeRoot.getRightChild().getMin());

                // restructure the right subtree
                subtreeRoot.setRightChild(delete(subtreeRoot.getRightChild(), subtreeRoot.getData()));
            }
        }
        return subtreeRoot;
    }

    public int getMin() {
        if(root != null) {
            return root.getMin();
        } else {
            return Integer.MIN_VALUE;
        }
    }

    public int getMax() {
        if(root != null) {
            return root.getMax();
        } else {
            return Integer.MAX_VALUE;
        }
    }

    // In-order traversal method
    public void traverse() {
        if(root == null) {
            return;
        } else {
            root.traverse();
        }
    }

    // Pre-order traversal
    public void preTraverse() {
        if(root == null) {
            return;
        } else {
            // call the method in the TreeNode class
            root.preTraverse();
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(35);
        tree.insert(25);
        tree.insert(15);
        tree.insert(55);
        tree.insert(85);
        tree.insert(75);
        tree.insert(95);
        tree.insert(5);
        tree.insert(65);
        tree.insert(65); // will be omitted
        tree.insert(30);
        tree.insert(45);
        tree.insert(80);
        tree.insert(20);
        tree.insert(105);

        // in-order traversal
        System.out.print("In-Order Traversal: ");
        tree.traverse();
        System.out.println();

        // pre-order traversal
        System.out.print("Pre-Order Traversal: ");
        tree.preTraverse();

        // retrieve value
        System.out.println();
        System.out.println(tree.get(105));

        System.out.println("Minimum: " + tree.getMin());
        System.out.println("Maximum: " + tree.getMax());

        // delete value 45, which it has no child
        //tree.delete(45);
        //tree.traverse();

        // delete value 95, which it only has a right child
        //tree.delete(95);
        //tree.traverse();

        // delete value 35, which is the root of the entire tree and has both right and left subtrees
        System.out.print("Removing the root (35) : ");
        tree.delete(35);
        tree.traverse();
    }
}
