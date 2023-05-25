public class AVLTree {

    Node root;
    int addCount = 0;
    int searchCount = 0;
    int deleteCount = 0;

    public Node find(int key) {
        searchCount = 0;
        Node current = root;
        while (current != null) {
            searchCount += 1;
            if (current.key == key) {
                break;
            }
            current = current.key < key ? current.right : current.left;
        }
        return current;
    }

    public void insert(int key) {
        addCount = 0;
        root = insert(root, key);
    }

    public void delete(int key) {
        deleteCount = 0;
        root = delete(root, key);
    }

    public Node getRoot() {
        return root;
    }

    public int height() {
        return root == null ? -1 : root.height;
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            addCount += 1;
            return new Node(key);
        } else if (node.key > key) {
            addCount += 1;
            node.left = insert(node.left, key);
        } else if (node.key < key) {
            addCount += 1;
            node.right = insert(node.right, key);
        }
        return rebalance(node);
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            deleteCount += 1;
            return node;
        } else if (node.key > key) {
            deleteCount += 1;
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            deleteCount += 1;
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                deleteCount += 1;
                node = (node.left == null) ? node.right : node.left;
            } else {
                deleteCount += 1;
                Node mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    private Node mostLeftChild(Node node) {
        Node current = node;

        while (current.left != null) {
            deleteCount += 1;
            current = current.left;
        }
        return current;
    }

    private Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right)) {
                z = rotateRight(z);
            } else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }


    private Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }


    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }


    private void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }


    private int height(Node n) {
        return n == null ? -1 : n.height;
    }


    public int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    public int getAddCount() {
        return addCount;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public int getDeleteCount() {
        return deleteCount;
    }
}