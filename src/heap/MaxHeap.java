package heap;

import utils.MyUtilities;

public class MaxHeap<K extends Comparable<? super K>> extends Heap<K>{
	/*
	protected Object[] data;
	protected int size;
	
	public MaxHeap(){
		data = new Object[10];
	}
	
	public MaxHeap(K[] array){
		data = new Object[array.length + 10];
		System.arraycopy(array, 0, data, 0, array.length);
		size = array.length;
		//Code for Build-Max Heap
		int itrLimit = (int) Math.ceil(size/2.0f);
		for(int idx=itrLimit;idx>=0;idx--) {
			maxHeapify(idx);
		}
	}
	*/
	
	public MaxHeap(){
		super();
	}
	
	public MaxHeap(K[] array){
		super(array);
	}
	
	protected void maxHeapify(int idx) {
		int maxIndexAmongItselfAndChildren = getMaxIndexAmongItselfAndChildren(idx);
		if(maxIndexAmongItselfAndChildren != idx) {
			MyUtilities.swap(data, idx, maxIndexAmongItselfAndChildren);
			maxHeapify(maxIndexAmongItselfAndChildren);
		}
	}
	
	/*
	@SuppressWarnings("unchecked")
	public K getMaxElement() {
		if(size==0)
			return null;
		return (K) data[0];
	}
	
	@SuppressWarnings("unchecked")
	public K extractMaxElement() {
		if(size == 0)
			return null;
		else {
			Object object = data[0];
			MyUtilities.swap(data, 0, size - 1);
			size = size - 1;
			data[size] = null;
			maxHeapify(0);
			return (K) object;
		}
	}
	*/
	public K getMaxElement() {
		return getRoot();
	}
	
	public K extractMaxElement() {
		return extractRoot();
	}
	
	@SuppressWarnings("unchecked")
	protected int getMaxIndexAmongItselfAndChildren(int idx){
		int idxMaxElement = idx;
		int leftIndex = getLeftIndex(idx);
		if(leftIndex < size && (((K) data[leftIndex]).compareTo((K)data[idxMaxElement]) > 0)) {
			idxMaxElement = leftIndex;
		}
		int rightIndex = getRightIndex(idx);
		if(rightIndex < size && (((K) data[rightIndex]).compareTo((K)data[idxMaxElement]) > 0)) {
			idxMaxElement = rightIndex;
		}
		return idxMaxElement;
	}
	
	/*
	public void doHeapSort() {
		for(int i=size;i>1;i--) {
			MyUtilities.swap(data, 0, size - 1);
			size = size - 1;
			maxHeapify(0);
		}
	}
	*/
	/*
	private int getParentIndex(int idx) {
		return (int) Math.ceil(idx/2.0f) - 1;
	}
	
	private int getLeftIndex(int idx) {
		return idx*2 + 1;
	}
	
	private int getRightIndex(int idx) {
		return idx*2 + 2;
	}
	*/
	/*@SuppressWarnings("unchecked")
	private K getElementAtParentIndex(int idx) {
		return (K) data[getParentIndex(idx)];
	}
	
	@SuppressWarnings("unchecked")
	private K getElementAtLeftIndex(int idx) {
		return (K) data[getLeftIndex(idx)];
	}
	
	@SuppressWarnings("unchecked")
	private K getElementAtRightIndex(int idx) {
		return (K) data[getRightIndex(idx)];
	}
	
	private void setElementAtParentIndex(K obj,int idx){
		data[getParentIndex(idx)] = obj;
	}
	
	private void setElementAtLeftIndex(K obj,int idx){
		data[getLeftIndex(idx)] = obj;
	}
	
	private void setElementAtRightIndex(K obj,int idx){
		data[getRightIndex(idx)] = obj;
	}*/
	
	@SuppressWarnings("unchecked")
	public K replaceElementAt(K replacement,int idx) {
		if(idx>=0 && idx<size) {
			K ele = (K) data[idx];
			int compareTo = ele.compareTo(replacement);
			data[idx] = replacement;
			if(compareTo == 0) {
				
			} else if(compareTo == 1) {
				maxHeapify(idx);
			} else if(compareTo == -1) {
				bubbleUp(idx);
			}
			return ele;
		}
		return null;
	}

	protected void bubbleUp(int idx) {
		int parentIndex = getParentIndex(idx);
		while(parentIndex >= 0 && (((K) data[idx]).compareTo((K) data[parentIndex]) > 0)) {
			MyUtilities.swap(data, parentIndex, idx);
			idx = parentIndex;
			parentIndex = getParentIndex(parentIndex);
		}
	}
	
	/*
	public void insertToHeap(K ele) {
		if(size == data.length) {
			resizeHeap();
		}
		data[size] = ele;
		size++;
		bubbleUp(size-1);
	}
	*/
	/*
	private void resizeHeap() {
		//copy to new array
		Object[] data = new Object[size + 10];
		System.arraycopy(this.data, 0, data, 0, size);
		this.data = data;
	}
	*/
	/*public static <T> void swap(T [] array,int idx1,int idx2) {
		T temp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = temp;
	}
	
	public static <T> void swap(T data1,T data2) {
		T temp = data1;
		data1 = data2;
		data2 = temp;
	}*/
	
	
	/*
	public static void main(String[] args) {
		Integer[] a = new Integer[10];
		for (int i = 0; i < 10; i++) {
			a[i] = i;
		}
		//MaxHeap<Integer> heap = new MaxHeap<Integer>(a);
		Integer removeFirstElement = heap.extractMaxElement();
		System.out.println(removeFirstElement);
		Integer replaceElementAt = heap.replaceElementAt(25, 1);
		System.out.println(replaceElementAt);
		heap.doHeapSort();
		System.out.println();
	}
	*/
	
	@Override
	protected void heapify(int idx) {
		maxHeapify(idx);
	}
}
