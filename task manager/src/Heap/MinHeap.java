package Heap;

import Heap.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    private final List<Task> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < heap.size();
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < heap.size();
    }

    private Task getParent(int index) {
        return heap.get(getParentIndex(index));
    }

    private Task getLeftChild(int index) {
        return heap.get(getLeftChildIndex(index));
    }

    private Task getRightChild(int index) {
        return heap.get(getRightChildIndex(index));
    }

    public List<Task> getHeap() {
        return heap;
    }

    private void swap(int index1, int index2) {
        Task temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert(Task value) {
        // Insert the new element at the end of the array
        heap.add(value);

        // Fix the min-heap property by bubbling up the newly inserted element
        heapifyUp();
    }

    private void heapifyUp() {
        int index = heap.size() - 1;

        while (hasParent(index) && getParent(index).compareTo(heap.get(index)) > 0) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    public Task extractMin() {
        if (heap.isEmpty()) {
            // Heap is empty
            return null; // or throw an exception
        }

        // Extract the minimum element (at the root)
        Task min = heap.get(0);

        // Replace the root with the last element in the heap
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        // Fix the min-heap property by bubbling down the root
        heapifyDown();

        return min;
    }

    public Task getMin() {
        if (heap.isEmpty()) {
            // Heap is empty
            return null; // or throw an exception
        }

        // Extract the minimum element (at the root)
        return heap.get(0);
    }

    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && getRightChild(index).compareTo(getLeftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (heap.get(index).compareTo(heap.get(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            index = smallerChildIndex;
        }
    }

    public void inOrderTraversal() {
        inOrderTraversal(0);
    }

    /**shows tasks in order*/
    private void inOrderTraversal(int index) {
        if (index < heap.size()) {
            inOrderTraversal(getLeftChildIndex(index));  // Visit left child
            System.out.println(heap.get(index));         // Visit current node (print)
            inOrderTraversal(getRightChildIndex(index)); // Visit right child
        }
    }
 }
