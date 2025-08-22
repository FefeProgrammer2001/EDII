package Java.Queue;

public class QueueMain {
    public static void main(String[] args) {
        DynamicDoubleQueue queue = new DynamicDoubleQueue();
        
        if(args.length == 0) {
            System.out.println("Usage: java QueueMain <values>");
            return;
        }

        for(String arg : args) {
            try {
                int value = Integer.parseInt(arg);
                queue.enqueue(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid format number " + arg);
            }
        }

        queue.printAll();
        System.out.println("Greatest element: " + queue.getMax());
        System.out.println("Lowest element: " + queue.getMin());
    }
}
