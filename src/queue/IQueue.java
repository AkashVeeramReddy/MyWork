package queue;

public interface IQueue<K> {
	
	public K dequeue();
	
	boolean enqueue(K ele);

	boolean canDequeue();
}
