package Java.Queue;

public class DynamicDoubleQueue {
    private ListNode front;
    private ListNode rear;
    private int size;

    public DynamicDoubleQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(int value) {
        ListNode newNode = new ListNode(value);
        if(rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    public Integer dequeue() {
        if(front == null) {
            return null;
        }
        int removedValue = front.getValue();
        front = front.getNext();
        if(front == null) {
            return null;
        }
        size--;
        return removedValue;
    }

    public Integer getMax() {
        if(front == null) {
            return null;
        }
        int max = front.getValue();
        ListNode current = front.getNext();
        while(current != null) {
            if(current.getValue() > max) {
                max = current.getValue();
            }
            current = current.getNext();
        }
        return max;
    }

    public Integer getMin() {
        if(front == null) {
            return null;
        }
        int min = front.getValue();
        ListNode current = front.getNext();
        while(current != null) {
            if(current.getValue() < min) {
                min = current.getValue();
            }
            current = current.getNext();
        }
        return min;
    }

    public void printAll() {
        if(front == null) {
            System.out.println("Empty Queue!");
            return;
        }
        ListNode current = front;
        System.out.print("Queue elements: ");
        while(current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }
}
