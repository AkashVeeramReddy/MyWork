package heap;

import utils.ArrayUtilities;

public abstract class Heap<K extends Comparable<? super K>> {
	
	protected Object[] data;
	protected int size;
	
	public Heap(){
		data = new Object[10];
	}
	
	public Heap(K[] array){
		data = new Object[array.length + 10];
		System.arraycopy(array, 0, data, 0, array.length);
		size = array.length;
		//Code for Build-Max Heap
		int itrLimit = (int) Math.ceil(size/2.0f);
		for(int idx=itrLimit;idx>=0;idx--) {
			heapify(idx);
		}
	}

	protected abstract void heapify(int idx) ;
	
	@SuppressWarnings("unchecked")
	public K getRoot() {
		if(size==0)
			return null;
		return (K) data[0];
	}
	
	@SuppressWarnings("unchecked")
	public K extractRoot() {
		if(size == 0)
			return null;
		else {
			Object object = data[0];
			ArrayUtilities.swapInArray(data, 0, size - 1);
			size = size - 1;
			data[size] = null;
			heapify(0);
			return (K) object;
		}
	}
	
	/**
	 * for {@link MaxHeap} it produces ascending order
	 * for {@link MinHeap} it produces descending order
	 */
	public void doHeapSort() {
		for(int i=size;i>1;i--) {
			ArrayUtilities.swapInArray(data, 0, size - 1);
			size = size - 1;
			heapify(0);
		}
	}
	
	protected int getParentIndex(int idx) {
		return (int) Math.ceil(idx/2.0f) - 1;
	}
	
	protected int getLeftIndex(int idx) {
		return idx*2 + 1;
	}
	
	protected int getRightIndex(int idx) {
		return idx*2 + 2;
	}
	
	protected abstract void bubbleUp(int idx);
	
	public void add(K ele) {
		if(size == data.length) {
			resizeHeap();
		}
		data[size] = ele;
		size++;
		bubbleUp(size-1);
	}
	
	protected void resizeHeap() {
		//copy to new array
		Object[] data = new Object[size + 10];
		System.arraycopy(this.data, 0, data, 0, size);
		this.data = data;
	}
	
	public int getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		return ArrayUtilities.getStringRepOfArray(data);
	}
}
