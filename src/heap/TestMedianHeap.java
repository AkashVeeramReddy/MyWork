package heap;

public class TestMedianHeap {
	
	public static void main(String[] args) {
		MedianHeap medianHeap = new MedianHeap();
		medianHeap.add(5);
		Integer median = medianHeap.getMedian();
		
		medianHeap.add(15);
		median = medianHeap.getMedian();

		medianHeap.add(1);
		median = medianHeap.getMedian();

		medianHeap.add(3);
		median = medianHeap.getMedian();
		
		System.out.println(median);
		
	}
}
