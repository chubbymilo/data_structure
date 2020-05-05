/* PriorityQueue using the heap data structure.
 * @author James Zhao
 * Similar to a queue, but each time we dequeue the PQ, we take the largest value in the queue.
 * If there are two nodes with the same value, return the one that arrives first.
 */
public class PriorityQueue<T>{

    private ArrayHeap<QueueNode<T>> heap;
    private static int arrivalNumber = 0;

    private class QueueNode<T> implements Comparable<QueueNode<T>>{
        private T value;
        private int priority;
        private int arrival_num;

        private QueueNode(T value, int priority){
            this.value = value;
            this.priority = priority;
            this.arrival_num = arrivalNumber;
            arrivalNumber++;
        }

        public int compareTo(QueueNode<T> newNode){
            
            if (this.priority < newNode.priority){
                return -1;
            }
            if (this.priority > newNode.priority){
                return 1;
            }  
                return newNode.arrival_num - this.arrival_num; // first come, first serve.
            
        }
    }


    public PriorityQueue(){
        heap = new ArrayHeap<QueueNode<T>>();
    }

    public void add(T item, int priority){
        heap.add(new QueueNode<T>(item, priority));
    }

    public T removeNext(){
        return heap.remove().value;
    }

    public static void main(String[] args){
        PriorityQueue<Integer> p_queue = new PriorityQueue<Integer>();
        p_queue.add(1, 100);
        p_queue.add(95, 101);
        p_queue.add(8, 1000);
        p_queue.add(9,1000);
        System.out.println(p_queue.removeNext());
    }



}