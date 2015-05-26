package queue;

public interface IQueueWithPeek<K> extends IQueue<K> {
	
	K peek();
}
