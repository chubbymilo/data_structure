/*
 *Manipulation of a sub_array.
 *The subarray is inclusive on the left and exclusive on the right.
 *Insertion, deletion at an index.
 *Binary search concept. It has O(log n) complexity but it requires a sorted array.
 *The binary search method is implemented in two ways, recursively and iteratively.
 *@ author James Zhao
 */

public class SubArray{

    public static void insertion(int arr[], int value, int index){
        // when the whole array is the subarray.
        insertion(arr, 0, arr.length, value, index);
    }

    public static void insertion(int arr[], int left, int right, int value, int index){
        if (index < left || index >=right){
            return;
        }
        int len = right - left;
        for (int i = right-1; i > index ; i--){
            arr[i] = arr[i-1];
        }
        arr[index] = value;

    }

    public static void deletion(int arr[], int value, int index){
        // when the whole array is the subarray.
        deletion(arr, 0, arr.length, value, index);
       
    }

    public static void deletion(int arr[], int left, int right, int value, int index){
        if (index < left || index >=right){
            return;
        }
        for (int i = index + 1; i < right; i++){// use index + 1 rather than index to avoid index out of bound for the last element.
            arr[i-1] = arr[i];
        }
        arr[right-1] = 0; // fill the empty cell with 0.
    }




    public static int binarySearch(int[] arr, int value){
        // when the whole array is the subarray.
        //return binarySearch(arr, 0, arr.length, value);
        // Using iterative method.
        return binarySearchIterative(arr, 0, arr.length, value); 
        
    }
    public static int binarySearch(int[] arr, int left, int right, int value){
        //The array must be sorted and this is a recursive implementation.
        // The subarray is inclusive on the left and exclusive on the right.
        // Therefore,we search from left to mid or mid+1 to right.
        
        if (left >= right){
            return -1;
        }
        int mid = (right + left)/2;

        // Three cases: arr[mid] == value, > value or < value.
        if (arr[mid] == value){
            return mid;
        }
        if (arr[mid] > value){
            return binarySearch(arr, left, mid, value);
        }

        return binarySearch(arr, mid+1, right, value); // starts search at mid + 1, since we compared the arr[mid] with the target value already.
        
    }
    
    public static int binarySearchIterative(int[] arr, int left, int right, int value){
        while (left <= right){
            int mid = left + (right - left)/2;
            if (arr[mid] == value){
                return mid;
            }
            else if (arr[mid] > value){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return -1;
    }
        
    public static void printArray(int arr[]){
        for (int i = 0; i < arr.length; i++){
            System.out.print("[" + arr[i] + "}");
        }
    }





public static void main(String [] args){
    int[] array = new int[10];
    for (int i = 0; i < 10; i++){
        array[i] = i;
    }
    SubArray.insertion(array, 15, 5);
    SubArray.printArray(array);
    SubArray.deletion(array, 15, 5);
    System.out.println();
    SubArray.printArray(array);
    SubArray.insertion(array,12,9);
    System.out.println();
    SubArray.printArray(array);
    System.out.println();
    System.out.println(SubArray.binarySearch(array, 8));



}


}