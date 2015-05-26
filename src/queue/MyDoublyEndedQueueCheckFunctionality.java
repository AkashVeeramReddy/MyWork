package queue;

import java.util.Iterator;

public class MyDoublyEndedQueueCheckFunctionality {
	public static void main(String[] args) {
		MyDoublyEndedArrayQueue<Integer> queue= new MyDoublyEndedArrayQueue<Integer>(8);
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.enqueue(7);
		queue.enqueue(8);
		queue.enqueue(9);
		queue.enqueue(10);
		queue.enqueue(11);
		for (Integer integer : queue) {
			System.out.println(integer);
		}
		for (Iterator<Integer> iterator = queue.iterator(); iterator.hasNext();) {
			Integer type = iterator.next();
			if(type==10 || type==11)
				iterator.remove();
		}
		System.out.println();
	}
}
