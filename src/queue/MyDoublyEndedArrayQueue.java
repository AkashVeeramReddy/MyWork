package queue;

import java.util.Iterator;

import utils.MyUtilities;

public class MyDoublyEndedArrayQueue<K> implements Iterable<K>,IQueue<K>{
	
	int size;
	/**
	 * points to first element in queue
	 */
	int head = 0;
	/**
	 * points where a new element will be inserted
	 */
	int tail = 0;
	Object [] array;
	
	public MyDoublyEndedArrayQueue(){
		this(10);
	}
	
	public MyDoublyEndedArrayQueue(int size){
		this.size = size+1;
		array = new Object[this.size];
	}
	
	@SuppressWarnings("unchecked")
	public K dequeue(){
		if(isEmpty())
			return null;
		Object data = array[head];
		array[head] = null;
		incrementFirst();
		return (K) data;
	}
	
	public boolean enqueue(K ele){
		if(isFull())
			return false;
		array[tail] = ele;
		incrementLast();
		return true;
	}
	
	private boolean isEmpty(){
		if(head == tail)
			return true;
		return false;
	}
	
	private boolean isFull(){
		if((head-tail==1)||((head == 0)&&(tail==(size-1)))){
			return true;
		}
		return false;
	}
	
	private void incrementFirst() {
		if(head==(size-1))
			head = 0;
		else
			head++;
	}
	
	static <K> int incrementItr(int itr,MyDoublyEndedArrayQueue<K> queue) {
		if(itr==(queue.size-1))
			itr = 0;
		else
			itr++;
		return itr;
	}
	
	static <K> int decrementItr(int itr,MyDoublyEndedArrayQueue<K> queue) {
		if(itr==(0))
			itr = queue.size - 1;
		else
			itr--;
		return itr;
	}
	
	private void incrementLast() {
		if(tail==(size-1))
			tail = 0;
		else
			tail++;
	}
	
	private void decrementLast() {
		if(tail==(0))
			tail = size - 1;
		else
			tail--;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(int itr=head;itr!=tail;) {
			stringBuilder.append(array[itr]);
			stringBuilder.append(",");
			itr=incrementItr(itr, this);
		}
		MyUtilities.removeCommaAtEndOfBuilder(stringBuilder);
		return stringBuilder.toString();
	}
	
	@Override
	public Iterator<K> iterator() {
		return new MyDoublyEndedQueueIterator<K>(this);
	}
	
	boolean isValidIndex(int idxToCheck){
		if(head < tail) {
			return((idxToCheck >=head) && (idxToCheck < tail));
		} else if(head > tail) {
			return((idxToCheck >=head) || (idxToCheck < tail));
		}
		/*if((idxToCheck >=head) && (idxToCheck < tail))
			return true;*/
		return false;
	}
	
	@SuppressWarnings("unchecked")
	K getElementAt(int idx) {
		if(isValidIndex(idx)){
			return (K) array[idx];
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	K removeElementAt(int idx) {
		if(isValidIndex(idx)) {
			Object object = array[idx];
			if((tail > head) || (idx < tail)) {
				System.arraycopy(array, idx + 1, array, idx, tail - idx - 1);
			} else {
				/*if(idx < tail) {
					//idx between 0 and tail
					System.arraycopy(array, idx + 1, array, idx, tail - idx - 1);
					decrementLast();
				} else {*/
//				Object[] newData = new Object[this.size];
				System.arraycopy(array, idx + 1, array, idx, size - idx - 1);
				if(tail != 0) {
					array[size-1] = array[0];
//				} else {
					System.arraycopy(array, 1, array, 0,tail-1);
				}
				//idx between head and the end of the array.
				//Form a new array with head pointing at 0s
				/*System.arraycopy(array,head, newData, 0, idx-head);
				System.arraycopy(array, idx+1, newData, idx-head, size - idx -1);
				System.arraycopy(array, 0, newData, size-head - 1, tail);
				array = newData;
				tail = (tail + size - head);
				head = 0;*/
			}
			decrementLast();
			array[tail] = null;
			return (K) object;
		}
		return null;
	}

	@Override
	public boolean canDequeue() {
		return !isEmpty();
	}
}
