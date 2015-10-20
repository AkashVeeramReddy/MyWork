package heap;

import utils.ArrayUtilities;

public class MinHeap<K extends Comparable<? super K>> extends Heap<K>{
	
	public MinHeap(){
		super();
	}
	
	public MinHeap(K[] array){
		super(array);
	}
	
	@Override
	protected void heapify(int idx) {
		minHeapify(idx);
	}
	
	protected void minHeapify(int idx) {
		int maxIndexAmongItselfAndChildren = getMinIndexAmongItselfAndChildren(idx);
		if(maxIndexAmongItselfAndChildren != idx) {
			ArrayUtilities.swapInArray(data, idx, maxIndexAmongItselfAndChildren);
			minHeapify(maxIndexAmongItselfAndChildren);
		}
	}

	@Override
	protected void bubbleUp(int idx) {
		int parentIndex = getParentIndex(idx);
		while(parentIndex >= 0 && (((K) data[idx]).compareTo((K) data[parentIndex]) < 0)) {
			ArrayUtilities.swapInArray(data, parentIndex, idx);
			idx = parentIndex;
			parentIndex = getParentIndex(parentIndex);
		}
	}
	
	public K getMinElement() {
		return getRoot();
	}
	
	public K extractMinElement() {
		return extractRoot();
	}
	
	@SuppressWarnings("unchecked")
	protected int getMinIndexAmongItselfAndChildren(int idx){
		int idxMinElement = idx;
		int leftIndex = getLeftIndex(idx);
		if(leftIndex < size && (((K) data[leftIndex]).compareTo((K)data[idxMinElement]) < 0)) {
			idxMinElement = leftIndex;
		}
		int rightIndex = getRightIndex(idx);
		if(rightIndex < size && (((K) data[rightIndex]).compareTo((K)data[idxMinElement]) < 0)) {
			idxMinElement = rightIndex;
		}
		return idxMinElement;
	}
	
}
