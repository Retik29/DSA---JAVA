
/**
 * Binary Heap Implementation (Min Heap)
 * 
 * A Binary Heap is a Complete Binary Tree where the parent node is always 
 * smaller than (Min Heap) or larger than (Max Heap) its children.
 * 
 * Logic & Properties:
 * 1. Shape Property: It's a complete binary tree (all levels filled except possibly 
 *    the last, which is filled from left to right).
 * 2. Heap Property: In a Min Heap, for every node 'i', value(parent(i)) <= value(i).
 * 
 * Storage:
 * - Represented as an Array. 
 * - This implementation uses 1-based indexing for simpler child/parent math:
 *   Parent(i) = i / 2
 *   LeftChild(i) = 2 * i
 *   RightChild(i) = 2 * i + 1
 * 
 * Time Complexity : O(log n) for Insert and Extract Min.
 * Space Complexity: O(n) to store the heap.
 */
import java.util.*;

public class HeapImplementation {
    private int[] heap;
    private int size;
    private int capacity;

    /**
     * Initializes the heap with a fixed capacity.
     */
    public HeapImplementation(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        // Using capacity + 1 because we use 1-based indexing
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

    /**
     * Inserts a new element into the heap.
     * Strategy: Add at the end (size+1) and 'Percolate Up' until heap property is
     * restored.
     */
    public void insert(int key) {
        if (size == capacity) {
            System.out.println("Heap Overflow: Cannot insert " + key);
            return;
        }

        size++;
        int i = size;
        heap[i] = key;

        // Percolate Up: Swap with parent if parent is larger
        while (i > 1 && heap[parent(i)] > heap[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Removes and returns the minimum element (root).
     * Strategy: Replace root with the last element, decrease size, and 'Percolate
     * Down' (Heapify).
     */
    public int extractMin() {
        if (size <= 0)
            return Integer.MAX_VALUE;
        if (size == 1) {
            size--;
            return heap[1];
        }

        int rootValue = heap[1];

        // Move the last element to root and reduce size
        heap[1] = heap[size];
        size--;

        // Restore heap property by sinking the new root down
        minHeapify(1);

        return rootValue;
    }

    /**
     * Recursively restores the Min Heap property starting from index 'i'.
     */
    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;

        // Find the smallest among root, left child, and right child
        if (l <= size && heap[l] < heap[smallest]) {
            smallest = l;
        }
        if (r <= size && heap[r] < heap[smallest]) {
            smallest = r;
        }

        // If root is not the smallest, swap and continue heapifying
        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Min Heap Interactive Demo ---");
        System.out.print("Set Heap Capacity: ");
        int capacity = sc.nextInt();
        HeapImplementation minHeap = new HeapImplementation(capacity);

        System.out.print("Number of elements to insert: ");
        int n = sc.nextInt();
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++) {
            minHeap.insert(sc.nextInt());
        }

        System.out.println("\nMinimum elements extracted in order:");
        while (minHeap.size > 0) {
            System.out.print(minHeap.extractMin() + " ");
        }
        System.out.println("\n(This demonstrates that Heap can be used for sorting - Heapsort)");

        sc.close();
    }
}
