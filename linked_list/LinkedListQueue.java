public class LinkedListQueue<T>{
    private LinkedList<T> queue;
    
    public LinkedListQueue(){
        queue = new LinkedList<T>();
    }

    public int size(){
        return queue.size();
    }

    public void enqueue(T item){
        queue.addLast(item);
    }
    public T peek(){
        return queue.peekFirst();
    }
    public T dequeue(){
        return queue.removeFirst();
    }
    public String toString(){
        return queue.toString();
    }

        
   
   
    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();

        queue.enqueue(12);
        queue.enqueue(18);
        queue.enqueue(8);
        System.out.println(queue);

        System.out.println(queue.peek());
        System.out.println(queue.dequeue());
        System.out.println(queue);
    }




}