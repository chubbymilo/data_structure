public class LinkedListStack<T>{
    private LinkedList<T> stack;
    
    public LinkedListStack(){
        stack = new LinkedList<T>();
    }

    public int size(){
        return stack.size();
    }

    public void push(T item){
        stack.addLast(item);
    }
    public T peek(){
        return stack.peekLast();
    }
    public T pop(){
        return stack.removeLast();
    }
    public String toString(){
        return stack.toString();
    }

        
   
   
    public static void main(String[] args){

        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();

        stack.push(12);
        stack.push(18);
        stack.push(8);

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack);
    }




}