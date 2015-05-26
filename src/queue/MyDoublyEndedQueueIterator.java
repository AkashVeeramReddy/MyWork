package queue;

import java.util.Iterator;

class MyDoublyEndedQueueIterator<K> implements Iterator<K> {
	
	private MyDoublyEndedArrayQueue<K> queue;
	private int itrIndex;
	private int lastReturnedIdxOfElement;
	MyDoublyEndedQueueIterator(MyDoublyEndedArrayQueue<K> syncCache) {
		this.queue = syncCache;
		itrIndex = syncCache.head;
	}

	@Override
	public boolean hasNext() {
		return queue.isValidIndex(itrIndex);
	}

	@Override
	public K next() {
		K nextElement = queue.getElementAt(itrIndex);
		lastReturnedIdxOfElement = itrIndex;
		itrIndex = MyDoublyEndedArrayQueue.incrementItr(itrIndex, queue);
		return nextElement;
	}

	@Override
	public void remove() {
		queue.removeElementAt(lastReturnedIdxOfElement);
		itrIndex = MyDoublyEndedArrayQueue.decrementItr(itrIndex, queue);
	}

}
