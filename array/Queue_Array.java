/* Array implementation of a queue.
 * @author James Zhao
 * Simple implementation with type int of a queue.
 * Enqueue, Dequeue and peek functions.
 */ 

public class Queue_Array{
    private int[] arr;

    int index = 0; //keep track of number of items inside the queue.

    public Queue_Array(int size){
    arr = new int[size];
    }

    public boolean isEmpty(){
        return (index == 0);
    }

    public int size(){
        return index;
    }

    public void Enqueue(int value){
        arr[index] = value;
        index ++;
    }

    public int peek(){
        if (index == 0){
            throw new RuntimeException("Empty queue");
        }
        return arr[0];
    }

    //Always shift all items to the left to fill the gap. Fill the gap with -1.
    public int Dequeue(){
        if (index == 0){
            throw new RuntimeException("Empty queue.");
        }

        int data = arr[0];

        for (int i = 1; i < index; i ++){
            arr[i-1] = arr[i];
        }
        
        arr[index] = -1;
        index --;
        return data; 
    }

    public String toString(){
        StringBuilder result = new StringBuilder("Queue: ");
        for (int i = 0; i < index; i ++){
            result.append("[");
            result.append(arr[i]);
            result.append("]");
        }
        return result.toString();
    }

    public static void main(String [] args){
        Queue_Array queue = new Queue_Array(10);
        queue.Enqueue(9);
        queue.Enqueue(3);
        queue.Enqueue(8);
        queue.Enqueue(2);
        System.out.println(queue);
        System.out.println("Dequque: "+queue.Dequeue());
        System.out.println(queue);
        System.out.println("Dequque: "+queue.Dequeue());
        System.out.println("Dequque: "+queue.Dequeue());
        System.out.println(queue);
        queue.Enqueue(5);
        queue.Enqueue(4);
        System.out.println(queue.size());
        System.out.println(queue);
        System.out.println("Dequque: "+queue.Dequeue());
        System.out.println(queue);




    }





}
