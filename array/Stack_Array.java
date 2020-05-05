/* Stack_Array
 * @author James Zhao
 * An array implementation of a stack data structure.
 * Fixed size array.
 * push, peek and pop methods.
 * Push each node at the end of the stack (index).
 * Pop each node at the end of the stack (index).
 * Peek at the end of the stack.
 */

public class Stack_Array{
    private int[] arr;
    private int index = 0;

    public Stack_Array(int size){ // fixed size.
     arr = new int [size];
    }

public boolean isEmpty(){
    return(index <= 0);
    }

public int size(){
    return index;
}
public void push(int value){
    arr[index] = value;
    index ++;
}
public int peek(){
    if (index <= 0){
        throw new RuntimeException("Empty stack.");
    }
    return (arr[index-1]);    
}
public int pop(){
    index = index - 1;
    if (index < 0){
        throw new RuntimeException("Empty stack.");
    }
    return arr[index];
}
public String toString(){
    StringBuilder result = new StringBuilder("Stack: ");
    for (int i = 0; i < index; i ++){
        result.append("[");
        result.append(arr[i]);
        result.append("]");
    }
    return result.toString();
}

public static void main(String[] args){
    Stack_Array stack = new Stack_Array(10);
    stack.push(2);
    stack.push(4);
    System.out.println(stack);
    System.out.println(stack.peek());
    System.out.println("Poped: "+stack.pop());
    System.out.println(stack);
    System.out.println("Poped: "+stack.pop());
    System.out.println(stack.pop());

}






}