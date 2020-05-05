/* Doubly linked list
 * @author James Zhao.
 * Implementation of doubly linked list, which means each node has reference of previous and next nodes.
 * Add a node at the start or end.
 * Peek a node at the start or end.
 * Remove a node at the star or end.
 * Add a node at certain position.
 * Remove a node at certain postition.
 * 
 * Using addLast, peekLast and removeLast methods, we can implement a linked-list based stack data structure.
 * Using addLast, peekFirst and removeFirst methods, we can implement a linked-list based queue data structure.
 * Linked list based implementation of stack and queue do not require pre-defined size.
 * 
 */ 
import java.io.*;
public class LinkedList<T>{
    //Keep the referneces of head and tail of a linked list.
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public LinkedList(){
        this.head = null;
        this.tail = null;      
    }
    
    //Inner class Node that is used for the linked list.
    //Each node has two references next and prev.
    private class Node <T>{
        private T value;
        private Node<T> next;
        private Node<T> prev;

        private Node(T value, Node<T> prev, Node<T> next){
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }


public String toString(){
    StringBuilder result = new StringBuilder("Dll:");
    Node<T> node = this.head;
    while (node != null){
        result.append(node.value);
        result.append("--->");
        node = node.next;
    }
    return result.toString();
}

public int size(){
    return size;
}

public boolean isEmpty(){
    return (size() == 0);
}

/* addFirst method always adds a new node at the start of the linked list.
 * @param T element. The method takes a generic type of input node.
 * Check if the list is empty, if yes, update the head and tail.
 * If not, update the head and head's prev reference.
 * Update the size.
 */

public void addFirst(T element){
    if (isEmpty()){
        Node<T> node = new Node<T>(element, null, null);
        head = node;
        tail = node;
    }else{
        Node<T> node = new Node<T>(element, null, head); // new element's next node is the current head.
        head.prev = node;// current head's previous node is the newly added element.
        head = head.prev; // Update the head to be the newly added element.
    }
    size ++;
}

/* addLast method adds the node at the end of the linked list.
 * @param T element. A generic type of input node.
 * Check if the list is empty, if yse, let the head and tail point to the new node.
 * If not, update the tail's next rference and tail.
 * Update the size.
 */

public void addLast(T element){
    if (isEmpty()){
        Node<T> node = new Node<T>(element, null, null);
        head = node;
        tail = node;
    }else{
        Node<T> node = new Node<T>(element, tail, null);
        tail.next = node;
        tail = tail.next;
    }
    size ++;
}

/* addAt method adds a new node at a specific index.
 * @param T element, int index. A T generic type of a input node and a interger type of index position.
 * Check if the index input is valid, if not, throw a exception.
 * If yes, check if the position is at the start or end, call according methods.
 * Otherwise, find the specific index by loop through the list.
 * If the index position is valid, add the new node in front of the index. (could be behind)
 * Update the current_node'prev reference and previous node of the current_node's reference.
 * Update the size.
 */

public void addAt(T element, int index) throws IndexOutOfBoundsException{
    if (index < 0){
        throw new IndexOutOfBoundsException("Negative number");
    }
    if (index == 0){
        addFirst(element);
        return;
    }
    if (index == size){
        addLast(element);
        return;
    }
    Node<T> current_node = this.head;
    while (current_node != null && index!=0){
        current_node = current_node.next;
        index --;
    }
    if (current_node == null){
        throw new IndexOutOfBoundsException("Index is too large");
    }
    Node<T> node= new Node<T>(element, current_node.prev, current_node);
    current_node.prev.next = node;
    current_node.prev = node;
    size ++;
}

public Node<T> getNode(int index) throws IndexOutOfBoundsException{
    if (index < 0){
        throw new IndexOutOfBoundsException("Negative number");
    }
    Node<T> current_node = this.head;
    while (current_node !=null && index >0){
        current_node = current_node.next;
        index --;
    }
    if (current_node == null){
        throw new IndexOutOfBoundsException("Index too large");
    }
    return current_node; // return the node, if we want the value, return current_node.value.
}

public T removeFirst(){
    if (isEmpty()){
        throw new RuntimeException("Empty list.");
    }
    T data = head.value;
    head.next.prev = null; // update the next node of the head's reference.
    head = head.next; //update the head.
    size --;

    if (isEmpty()){
        tail = null;
    }
    return data;
}

public T removeLast(){
    if (isEmpty()){
        throw new RuntimeException("Empty list.");
    }
    T data = tail.value;
    tail.prev.next = null; // update the previous node of the tail node's reference.
    tail = tail.prev; //update the tail.
    size --;

    if (isEmpty()){
        head = null;
    }
    return data;
}

public T removeAt(int index){
    if (isEmpty()){
        throw new RuntimeException("Empty list.");
    }
    if (index == 0){
        return (removeFirst());
        
    }
    if (index == size){
       return removeLast();      
    }

    Node<T> node = getNode(index);
    T data = node.value;
    node.prev.next = node.next; // Update the previous node of the current node'reference.
    node.next.prev = node.prev; // Update the next node of the current node's reference.
    size --;
    return data;
}

public T peekFirst(){
    if (isEmpty()){
        throw new RuntimeException("Empty list");
    }
    return head.value;  
}

public T peekLast(){
    if (isEmpty()){
        throw new RuntimeException("Empty list.");
    }
    return tail.value;
    
}

public static void main(String[] args){
    //A doubly linked list.
    LinkedList<Integer> list = new LinkedList<Integer>();
    list.addFirst(12);
    list.addLast(13);
    list.addFirst(18);
    System.out.println(list);
    System.out.println(list.getNode(2).value);
    list.addAt(1,1);
    System.out.println(list);
    System.out.println(list.removeLast());
    System.out.println(list);
    System.out.println(list.removeAt(1));
    System.out.println(list);
    System.out.println(list.peekFirst());
    System.out.println(list.peekLast());

    // A linked-list based stack.
    LinkedList<Integer> stack = new LinkedList<Integer>();
    stack.addLast(3);
    stack.addLast(4);
    stack.addLast(9);
    stack.addLast(7);
    System.out.println(stack);
    System.out.println(stack.peekLast());
    stack.removeLast();
    System.out.println(stack);

    // A linked-list based queue.
    LinkedList<Integer> queue = new LinkedList<Integer>();
    queue.addLast(8);
    queue.addLast(7);
    queue.addLast(2);
    queue.addLast(4);
    System.out.println(queue);
    System.out.println(queue.peekFirst());
    queue.removeFirst();
    System.out.println(queue);
}





}