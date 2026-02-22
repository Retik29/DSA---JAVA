
// Min Heap Implementation
// Time Complexity: Insert: O(log N), Extract Min: O(log N) | Space Complexity: O(N)
import java.util.*;

public class HeapImplementation {
    private int[] heap;
    private int size;
    private int capacity;

    public HeapImplementation(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity + 1];
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    public void insert(int key) {
        if (size == capacity)
            return;
        size++;
        int i = size;
        heap[i] = key;

        while (i > 1 && heap[parent(i)] > heap[i]) {
            int temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = parent(i);
        }
    }

    public int extractMin() {
        if (size <= 0)
            return Integer.MAX_VALUE;
        if (size == 1) {
            size--;
            return heap[1];
        }

        int root = heap[1];
        heap[1] = heap[size];
        size--;
        minHeapify(1);

        return root;
    }

    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l <= size && heap[l] < heap[i])
            smallest = l;
        if (r <= size && heap[r] < heap[smallest])
            smallest = r;
        if (smallest != i) {
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            minHeapify(smallest);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter heap capacity: ");
        int capacity = sc.nextInt();
        HeapImplementation minHeap = new HeapImplementation(capacity);

        System.out.print("Enter number of elements to insert: ");
        int n = sc.nextInt();
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            minHeap.insert(sc.nextInt());
        }

        System.out.println("Extracted Min: " + minHeap.extractMin());
        System.out.println("Extracted Min: " + minHeap.extractMin());
        sc.close();
    }
}
