/* Heap data structure. Based on COSC241 lectures.
 * @author James Zhao
 * Implemented using an array with the children nodes are in index 2k+1 and 2k+2.
 * Max heap, the root node is bigger than all children nodes.
 * 
 */

import java.util.Arrays;
public class ArrayHeap<T extends Comparable<T>>{
    private static final int DEFAULT_CAPACITY = 31;
    private T[] heap;
    private int size = 0;

    public ArrayHeap(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayHeap(int capacity){
        heap = (T[]) new Comparable[capacity];
    }

    // always return the root node.
    public T get(){
        return heap[0];
    }

    private void expandCapacity(){
        heap = Arrays.copyOf(heap, 2*heap.length+1);
    }

    private void swap(int i, int j){
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Adding a new node in the heap.
    // Put the new node at the bottom of the tree.
    // Move the new node up to the tree, swap with any node that is smaller than it.
    public void add(T element){
        if (size == heap.length){
            expandCapacity();
        }
        heap [size] = element;
        size++;

        int childIndex = size - 1;
        int parentIndex = (childIndex - 1) / 2;

        while(parentIndex >= 0 && heap[parentIndex].compareTo (heap[childIndex]) < 0){
            swap(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = (childIndex -1 ) / 2;
        }
    }

    // Find the largest child for a node.
    // Make sure the node is not a leaf node.
    // Find the largest node among the node, the left and right node.
    // return -1, if the node is a leaf node.
    private int getLargerChildIndex(int i){
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (right > size || heap[right].compareTo(heap[left]) < 0){

            if (left < size && heap[i].compareTo(heap[left]) < 0){
                return left;
            }
        }else{
            if (heap[i].compareTo(heap[right]) < 0){
                return right;
            }
        }
        return -1;
    }

// Always remove the root node.
// Find the next biggest node to fill the root.
// Fill out the gap, each time a node moved upwards till reaching the leaf node.
    public T remove(){
        T result = heap[0];
        heap[0] = heap[size-1];
        size --;
        int parentIndex = 0;
        do{
            int LargerChildIndex = getLargerChildIndex(parentIndex);
            if (LargerChildIndex == -1){
                break;
            }
            swap(parentIndex, LargerChildIndex);
            parentIndex = LargerChildIndex;

        }while (true);
        return result;
    }
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i ++){
            result.append("["+heap[i]+"]");
        }

        return result.toString();
    }

    public static void main(String[] args){
        ArrayHeap<Integer> heap = new ArrayHeap<Integer>(10);
        heap.add(8);
        heap.add(29);
        heap.add(89);
        heap.add(2);
        heap.add(6);
        System.out.println(heap);
        System.out.println(heap.remove());
        System.out.println(heap);
        System.out.println(heap.remove());
        System.out.println(heap);
        System.out.println(heap.remove());

    }


}