package list.arraylist;

import java.util.Iterator;

import org.w3c.dom.ls.LSInput;

import list.linkedlist.MyLinkedList;

public class MyArrayListIterator<E> implements Iterator<E> {
	
	protected int currentIdx;
	protected int lastReturnedIdx;
	protected MyArrayList<E> list;
	
	MyArrayListIterator(MyArrayList<E> list) {
		this.list = list;
	}
	
	@Override
	public boolean hasNext() {
		if(currentIdx < list.size())
			return true;
		return false;
	}

	@Override
	public E next() {
		lastReturnedIdx = currentIdx;
		currentIdx = currentIdx+1;
		return list.get(lastReturnedIdx);
	}

	@Override
	public void remove() {
		list.removeAt(lastReturnedIdx);
		currentIdx = currentIdx - 1;
	}

}
