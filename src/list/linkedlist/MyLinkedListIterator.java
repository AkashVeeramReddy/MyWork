package list.linkedlist;

import java.util.Iterator;

class MyLinkedListIterator<K> implements Iterator<K> {
	
	private MyLinkedListNode<K> currentElement;
	private MyLinkedListNode<K> lastReturnedElement;
	private MyLinkedList<K> linkedList;
	MyLinkedListIterator(MyLinkedList<K> list) {
		linkedList = list;
		currentElement = linkedList.getHeader();
	}

	@Override
	public boolean hasNext() {
		return (currentElement != null);
	}

	@Override
	public K next() {
		K data = currentElement.data;
		lastReturnedElement = currentElement;
		currentElement = currentElement.next;
		return data;
	}

	@Override
	public void remove() {
		linkedList.removeNode(lastReturnedElement);
	}
	
}