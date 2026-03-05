import java.util.NoSuchElementException;

/*To Compile and run
javac -cp FIFOmain.jar FIFO.java
java -cp ".:FIFOmain.jar" FIFOmain
*/

public class FIFO implements Queue {

    // instance variables
    private Object[] data;
    private int size;
    private int front;
    private int back;

    /**
     * Creates a FIFO queue with default capacity of 100.
     */
    public FIFO() {
        this(100); 
    }

    /**
     * Creates a FIFO queue with specified capacity.
     */
    public FIFO(int capacity) {
        data = new Object[capacity];
        size = 0;
        front = 0;
        back = 0;
    }

    // getters 
    @Override
    public int size() {return size;}

    @Override
    public boolean isEmpty() {return size == 0;}

    @Override
    public int maxSize() {return data.length;}

    @Override
    public Object first() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        return data[front];
    }

    /*
     * back/front = (back/fornt + 1) % data.length
     * this is a common technique to wrap around the indices in a circular buffer.
     * When back or front reaches the end of the array, it wraps around to the beginning.
     * This allows us to efficiently use the array space without needing to shift elements when adding or removing from the queue.
     */

    // Remove first element and adjust front and size accordingly
    @Override
    public void removeFirst() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        data[front] = null; 
        front = (front + 1) % data.length;
        size--;
    }

    // Add element at back and adjust back and size accordingly
    @Override
    public void add(Object o) {
        if (size == data.length) throw new IllegalStateException("Queue is full");

        data[back] = o;
        back = (back + 1) % data.length;
        size++;
    }

    
    // printing the queue for debugging purposess and testing
   @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: ");
        for (int i = 0; i < size; i++) {
            Object val = data[(front + i) % data.length];
            sb.append("(").append(val).append(") ");
        }
        return sb.toString();
    }


    /**
     * compares two FIFO queues for equality. 
     * Two queues are considered equal if they have the same size and contain the same elements in the same order.
     * The method first checks if the other object is an instance of FIFO. If not, it returns false.
     * Then it casts the other object to FIFO and compares their sizes. If the sizes are different, it returns false.
     * Finally, it iterates through the elements of both queues and compares them. 
     * If any pair of elements is not equal, it returns false. 
     * If all checks pass successfully, it returns true, indicating that the two queues are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FIFO)) return false;

        FIFO other = (FIFO) obj;

        if (this.size != other.size) return false;
        

        for (int i = 0; i < size; i++) {
            Object a = this.data[(front + i) % data.length];
            Object b = other.data[(other.front + i) % other.data.length];

            if (a == null ? b != null : !a.equals(b)) return false;
            //objs
        }

        return true;
    }
}

