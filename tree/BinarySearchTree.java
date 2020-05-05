/* Binary search tree.
 * @author James Zhao
 * The node on the left is always smaller than the root node, the node on the right is always bigger than the root.
 * The tree consists many subtrees, each sub-tree can be treated as a whole tree.
 * Consists a lot of recursive functions as a binary search tree consists many sub-trees which can be treated as a whole tree itself.
 * 
 */

public class BinarySearchTree<T extends Comparable<T>>{
    private Node root;
    private int size;
    public BinarySearchTree(){
        this.root = null;
        this.size = 0;
    }

    private class Node{
        private T value;
        private Node left;
        private Node right;

        public Node(T value, Node left, Node right){
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        return (size() == 0);
    }

    public void add(T value){
        if (search(value)){
            System.out.println("The item exists in the tree.");
        }
        else{
        root = add(root, value);
        }
    }

    private Node add(Node node, T value){
        if (node == null){
            node = new Node(value, null, null);

            size++;
        }else{
            int comparision = value.compareTo(node.value);

            if (comparision == 0){
                System.out.println("The element exists in the tree.");
                return null;
            }else if(comparision < 0){
                if (node.left == null){
                    node.left = new Node(value, null, null);
                    size ++;
                }else{
                add(node.left, value);
                size++;
                }
            }
            else{
                if (node.right == null){
                    node.right = new Node(value, null, null);
                    size ++;
                }else{
                add(node.right, value);
                size++;
                }
            }
        }
        return node;
        }

    public boolean search(T value){
        return search(root, value);
    }

    private boolean search(Node node, T value){
        if (node == null){
            return false;
        }

        int comparision = value.compareTo(node.value);
        if (comparision == 0){
            return true;
        }else if (comparision < 0){
             return search(node.left, value);
        }
        else{
             return search(node.right, value);
        }

    }

    public void remove(T value){
        if (search(value)){
        root = remove(root, value);
        }
        else{
            System.out.println("The item does not exits");
        }

    }

    //There are three cases for removing a node.
    // The node has no children. We can simply delete it.
    // The node has one children. We can simpley replace the node with its children.
    // The node has two children. We need to find the most left tree on the right tree of the node and swap it with the node.
    private Node remove(Node node, T value){
        if (node == null){
            return null;
        }
        //Finding the node we want to delete.
        int comparision = value.compareTo(node.value);
        if (comparision < 0){
            node.left = remove(node.left, value);
        }
        else if (comparision > 0){
            node.right = remove(node.right, value);
        }
        else{ // when comparision == 0, this means we found the node we wanted to delete.
            if (node.left == null && node.right != null){
                node = node.right;
                return node;
            }else if(node.left != null && node.right == null){
                node = node.left;
                return node; 
            }else if (node.left == null && node.right == null){
                node = null;
                return node;
            }else{
                
                Node parent = node;
                Node successor = node.right;
                if (successor.left == null){
                    node = node.right;
                    return node;
                }
                else{
                    // Find the minimum node on the left tree.
                    while (successor.left != null){
                        parent = successor;
                        successor = successor.left;
                    }
                    // put everthing from the right of the minimum node to the previous node.
                    parent.left = successor.right;
                }

                node.value = successor.value; // swap the value between the minimum node and the node we want to delete.
                return node;
            }
        }
        return node;
    }

    public void preOrder(Node node){
        if (node == null){
            return;
        }
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(Node node){
        if (node == null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }

    public void postOrder(Node node){
        if (node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args){
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(36);
        bst.add(152);
        bst.add(6);
        bst.add(98);
        bst.add(1);
        bst.add(8);
        bst.preOrder(bst.root);
        System.out.println();
        bst.inOrder(bst.root);
        System.out.println();
        bst.postOrder(bst.root);
        System.out.println();
        System.out.println(bst.search(98));
        System.out.println(bst.search(99));
        bst.add(8);
        bst.add(7);
        bst.add(9);
        bst.preOrder(bst.root);
        bst.remove(6);
        System.out.println();
        bst.preOrder(bst.root);



    }


    }








