/**
     * Base Node class
*/
// public class Node {
//     Node next;
//     int val;
//     Node(int val){
//         this.val = val;
//     }
// }

/**
     * Implement Stack class using Node
     * @return Stack class containing push() and pop() and peek()
*/
public class Stack {
    
    Node top;

    public static void push(int data){
        if (top == null){
            top = new Node(data);
        } else {
            Node temp = new Node(data);
            temp.next = top;
            top = temp;
        }
    }

    public static int pop(){
        if (top == null) return Integer.MIN_VALUE;
        int temp = top.val;
        top = top.next;
        return temp;
    } 

    public static int peek(){
        return top.val
    }
}

/**
     * Implement a class Queue using a base Node class
     * @return Queue class containing enqueue() and dequeue()
*/
public class Queue {

    Node head;    
    Node tail;

    public static int dequeue(){
        if (head  == null){
            throw new Exception();
        } else {
            int val = head.val;
            head = head.next;
            return val;
        }
    }

    public static void enqueue(int val){
        if (head == null){
            head = new Node(val);
            tail = head;
        } else {
            temp = new Node(val);
            tail.next = temp;
            tail = temp;
        }
    }
}

