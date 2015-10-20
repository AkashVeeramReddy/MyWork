package heap;

import stack.IStack;
import utils.ArrayUtilities;

public class LinkedMaxHeap<K extends Comparable<? super K>> implements IStack<K> {
	
	private Object [] data;
	private int size;
	private Entry<K> head;
	private Entry<K> tail;
	
	public LinkedMaxHeap(){
		data = new Object[10];
	}
	
	public LinkedMaxHeap(K[] array){
		data = new Object[array.length + 10];
		size = array.length;
		for (int i = 0; i < array.length; i++) {
			Entry<K> entry = new Entry<K>();
			if(head == null)
				head = entry;
			entry.itemData = array[i];
			entry.prev = tail;
			tail.next = entry;
			tail = entry;
			
		}
		//Code for Build-Max Heap
		int itrLimit = (int) Math.ceil(size/2.0f);
		for(int idx=itrLimit;idx>=0;idx--) {
			maxHeapify(idx);
		}
	}
	
	private void maxHeapify(int idx) {
		int maxIndexAmongItselfAndChildren = getMaxIndexAmongItselfAndChildren(idx);
		if(maxIndexAmongItselfAndChildren != idx) {
			ArrayUtilities.swapInArray(data, idx, maxIndexAmongItselfAndChildren);
			maxHeapify(maxIndexAmongItselfAndChildren);
		}
	}
	
	public void doHeapSort() {
		for(int i=size;i>1;i--) {
			ArrayUtilities.swapInArray(data, 0, size - 1);
			size = size - 1;
			maxHeapify(0);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int getMaxIndexAmongItselfAndChildren(int idx){
		int idxMaxElement = idx;
		int leftIndex = getLeftIndex(idx);
		if(leftIndex < size && (((Comparable) data[leftIndex]).compareTo((Comparable)data[idxMaxElement]) == 1)) {
			idxMaxElement = leftIndex;
		}
		int rightIndex = getRightIndex(idx);
		if(rightIndex < size && (((Comparable) data[rightIndex]).compareTo((Comparable)data[idxMaxElement]) == 1)) {
			idxMaxElement = rightIndex;
		}
		return idxMaxElement;
	}
	
	
	@SuppressWarnings("unused")
	private int getParentIndex(int idx) {
		return (int) Math.ceil(idx/2.0f) - 1;
	}
	
	private int getLeftIndex(int idx) {
		return idx*2 + 1;
	}
	
	private int getRightIndex(int idx) {
		return idx*2 + 2;
	}
	
	@Override
	public boolean push(K data) {
		return false;
	}

	@Override
	public K pop() {
		return null;
	}

	@Override
	public boolean canPop() {
		return size > 0;
	}
	
	@SuppressWarnings("unchecked")
	public K getMaxElement() {
		if(size==0)
			return null;
		return ((Entry<K>)data[0]).itemData;
	}
	
	@SuppressWarnings("unchecked")
	public K extractMaxElement() {
		if(size == 0)
			return null;
		else {
			Entry<K> object = (Entry<K>) data[0];
			ArrayUtilities.swapInArray(data, 0, size - 1);
			size = size - 1;
			data[size] = null;
			maxHeapify(0);
			return  object.itemData;
		}
	}
	
	private class Entry<K extends Comparable<? super K>> implements Comparable<Entry<K>>{
		
		Entry<K> prev;
		Entry<K> next;
		K itemData;
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(String.valueOf(itemData));
			return builder.toString();
		}

		@Override
		public int compareTo(Entry<K> o) {
			return itemData.compareTo(o.itemData);
		}
	}

}
