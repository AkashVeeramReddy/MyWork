package heap;

public class MedianHeap {
	//size of the heaps differ by at most 1
	//holds the smaller half elements
	protected MaxHeap<Integer> maxHeap;
	//holds the larger half elements
	protected MinHeap<Integer> minHeap;
	
	public MedianHeap() {
		maxHeap = new MaxHeap<Integer>();
		minHeap = new MinHeap<Integer>();
	}
	
	public Integer getMedian() {
		int maxSize = maxHeap.getSize();
		int minSize = minHeap.getSize();
		if((maxSize + minSize) >= 0) {
			if(maxSize == minSize) {
				return (maxHeap.getMaxElement() + minHeap.getMinElement())/2;
			} else if(maxSize > minSize) {
				return maxHeap.getMaxElement();
			} else {
				//maxSize < minSize
				return minHeap.getMinElement();
			}
		}
		return null;
	}
	
	public void add(Integer no) {
		int maxSize = maxHeap.getSize();
		int minSize = minHeap.getSize();
		if((maxSize + minSize) == 0) {
			maxHeap.add(no);
			return;
		}
		if(maxSize == minSize) {
			if(no <= maxHeap.getMaxElement()) {
				maxHeap.add(no);
			} else if(no >= minHeap.getMinElement()) {
				minHeap.add(no);
			} else {
				//in between. add to any of the heap
				maxHeap.add(no);
			}
		} else {
			if(maxSize < minSize) {
				if(no <= minHeap.getMinElement()) {
					maxHeap.add(no);
				} else {
					Integer min = minHeap.extractMinElement();
					maxHeap.add(min);
					minHeap.add(no);
				}
			} else {
				if(no >= maxHeap.getMaxElement()) {
					minHeap.add(no);
				} else {
					Integer max = maxHeap.extractMaxElement();
					minHeap.add(max);
					maxHeap.add(no);
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[maxHeap=");
		builder.append(maxHeap);
		builder.append(", minHeap=");
		builder.append(minHeap);
		builder.append(", median=");
		builder.append(getMedian());
		builder.append("]");
		return builder.toString();
	}
	
	
}
