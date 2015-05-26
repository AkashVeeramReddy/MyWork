package list.linkedlist;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.ListIterator;

import queue.IQueue;

import stack.IStack;
import utils.MyUtilities;
import list.IList;
import list.linkedlist.MyLinkedListNode;

public class MyLinkedList<K> implements IStack<K>,IQueue<K>,IList<K>{
	
	protected MyLinkedListNode<K> header = null;

	protected MyLinkedListNode<K> tail = null;
	protected int noOfElements = 0;
	
	public MyLinkedListNode<K> getHeader() {
		return header;
	}
	
	public MyLinkedListNode<K> getTail() {
		return tail;
	}
	
	public void addFirst(K element) {
		MyLinkedListNode<K> newNode = new MyLinkedListNode<K>(element, null, null);
		if(isEmptyList()) {
			header = newNode;
			tail = newNode;
		} else {
			newNode.next = header;
			newNode.prev = null;
			header.prev = newNode;
			header = newNode;
		}
		noOfElements++;
	}
	
	public K removeFirst() {
		if(!isEmptyList()) {
			MyLinkedListNode<K> temp = header;
			if(isSingleElementList()) {
				header = null;
				tail = null;
			} else {
				header = header.next;
				header.prev = null;
			}
			noOfElements--;
			return temp.data;
		}
		return null;
	}
	
	private boolean isSingleElementList(){
		return header==null?false:(header==tail);
	}
	
	private boolean isEmptyList(){
		return header==null;
	}
	
	public K removeLast() {
		if(!isEmptyList()) {
			MyLinkedListNode<K> temp = tail;
			
			if(isSingleElementList()) {
				header = null;
				tail = null;
			} else {
				tail = tail.prev;
				tail.next = null;
			}
			noOfElements--;
			return temp.data;
		}
		return null;
	}
	
	public K removeElement(K element) {
		MyLinkedListNode<K> itrNode = header;
		while(itrNode != null) {
			if(itrNode.data == element || itrNode.equals(element)) {
				noOfElements--;
				return removeNode(itrNode);
			}
			itrNode = itrNode.next;
		}
		return null;
	}

	protected K removeNode(MyLinkedListNode<K> currentElement) {
		if(isSingleElementList()) {
			header = tail = null;
		} else {
			if(currentElement == header) {
				return removeFirst();
			} else if(currentElement == tail) {
				return removeLast();
			} else {
				currentElement.prev.next = currentElement.next;
				currentElement.next.prev = currentElement.prev;
			}
		}
		return currentElement.data;
	}
	
	public void addLast(K element) {
		MyLinkedListNode<K> newNode = new MyLinkedListNode<K>(element, null, null);
		if(isEmptyList()) {
			header = newNode;
			tail = newNode;
		} else {
			newNode.prev = tail;
			newNode.next = null;
			tail.next = newNode;
			tail = newNode;
		}
		noOfElements++;
	}
	
	public boolean add(K element) {
		addLast(element);
		return true;
	}
	
	public void reverse() {
		reverse(header);
		//swap header and tail
		MyLinkedListNode<K> temp = header;
		header = tail;
		tail = temp;
		header.prev = null;
		tail.next = null;
	}
	
	private void reverse(MyLinkedListNode<K> start) {
		if(start != null) {
			MyLinkedListNode<K> rest = start.next;
			if(rest != null) {
				reverse(rest);
				rest.next = start;
				start.next = null;
				start.prev = rest;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		MyLinkedListNode<K> itrNode = header;
		while(itrNode != null) {
			stringBuilder.append(itrNode.data);
			stringBuilder.append(",");
			itrNode = itrNode.next;
		}
		MyUtilities.removeCommaAtEndOfBuilder(stringBuilder);
		return stringBuilder.toString();
	}
	
	@Override
	public Iterator<K> iterator() {
		return new MyLinkedListIterator<K>(this);
	}

	@Override
	public K dequeue() {
		return removeFirst();
	}

	@Override
	public boolean enqueue(K ele) {
		addLast(ele);
		return true;
	}

	@Override
	public boolean push(K data) {
		addFirst(data);
		return true;
	}

	@Override
	public K pop() {
		return removeFirst();
	}

	@Override
	public boolean addAt(K ele, int index) throws IndexOutOfBoundsException {
		return false;
	}

	@Override
	public K set(K ele, int index) throws IndexOutOfBoundsException {
		if(index >= noOfElements)
			throw new IndexOutOfBoundsException();
		MyLinkedListNode<K> itrNode = header;
		int count = 0;
		while(itrNode != null) {
			if(count == index) {
				K data = itrNode.data;
				itrNode.data = ele;
				return data;
			}
			count++;
			itrNode = itrNode.next;
		}
		return null;
	}

	@Override
	public K removeAt(int index) throws IndexOutOfBoundsException {
		if(index >= noOfElements)
			throw new IndexOutOfBoundsException();
		MyLinkedListNode<K> itrNode = header;
		int count = 0;
		while(itrNode != null) {
			if(count == index) {
				K data = itrNode.data;
				removeNode(itrNode);
				return data;
			}
			count++;
			itrNode = itrNode.next;
		}
		return null;
	}

	@Override
	public boolean remove(K ele) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public K get(int index) throws IndexOutOfBoundsException {
		if(index >= noOfElements)
			throw new IndexOutOfBoundsException();
		MyLinkedListNode<K> itrNode = header;
		int count = 0;
		while(itrNode != null) {
			if(count == index) {
				K data = itrNode.data;
				return data;
			}
			count++;
			itrNode = itrNode.next;
		}
		return null;
	}

	@Override
	public int size() {
		return noOfElements;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(K ele) {
		MyLinkedListNode<K> itrNode = header;
		while(itrNode != null) {
			K data = itrNode.data;
			if(ele == data || ele.equals(data))
				return true;
			itrNode = itrNode.next;
		}
		return false;
	}

	@Override
	public int indexOf(K ele) {
		MyLinkedListNode<K> itrNode = header;
		int count = 0;
		while(itrNode != null) {
			K data = itrNode.data;
			if(ele == data || ele.equals(data))
				return count;
			itrNode = itrNode.next;
			count++;
		}
		return -1;
	}

	@Override
	public boolean canDequeue() {
		return !isEmpty();
	}

	@Override
	public boolean canPop() {
		return !isEmpty();
	}

}
