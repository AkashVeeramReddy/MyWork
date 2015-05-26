package list.arraylist;

import java.util.Iterator;
import java.util.ListIterator;

import list.IList;
import utils.MyUtilities;

public class MyArrayList<K> implements IList<K>{
	protected int maxElements;
	protected int noOfElements;
	protected Object[] data;
	
	public MyArrayList() {
		this(10);
	}
	
	public MyArrayList(int size) {
		this.maxElements = size;
		data = new Object[size];
	}
	
	public boolean add(K element) {
		if(noOfElements == maxElements) {
			increaseCapacity();
		}
		data[noOfElements] = element;
		noOfElements++;
		return true;
	}
	
	public boolean addAt(K element,int idx) {
		if(idx >= noOfElements)
			throw new IndexOutOfBoundsException();
		if(noOfElements == maxElements) {
			increaseCapacity();
		}
		System.arraycopy(data, idx, data, idx+1,noOfElements-idx);
		data[idx] = element;
		noOfElements++;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public K get(int idx) {
		if(idx >= noOfElements)
			throw new IndexOutOfBoundsException();
		return (K) data[idx];
	}
	
	@SuppressWarnings("unchecked")
	public K removeAt(int idx) {
		if(idx >= noOfElements)
			throw new IndexOutOfBoundsException();
		 Object object = data[idx];
		 System.arraycopy(data, idx+1, data, idx, noOfElements - idx -1);
		 noOfElements--;
		 data[noOfElements] = null;
		 return (K) object;
	}

	private void increaseCapacity() {
		int newSize = maxElements + 10;
		Object[] newData = new Object[newSize];
		System.arraycopy(data, 0, newData, 0, noOfElements);
		this.maxElements = newSize;
		this.data = newData;
	}
	
	public int size() {
		return noOfElements;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < noOfElements; i++) {
			builder.append(data[i]);
			builder.append(",");
		}
		MyUtilities.removeCommaAtEndOfBuilder(builder);
		return builder.toString();
	}
	
	public static void main(String[] args) {
		MyArrayList<Integer> list = new MyArrayList<Integer>(3);
		list.add(6);
		list.add(5);
		list.add(2);
		list.add(4);
		list.add(3);
		list.add(1);
//		list.insertionSort();
		System.out.println();
	}

	@Override
	public Iterator<K> iterator() {
		return new MyArrayListIterator<K>(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public K set(K ele, int index) {
		if(index >= noOfElements)
			throw new IndexOutOfBoundsException();
		Object removedEle = data[index];
		data[index] = ele;
		return (K) removedEle;
	}

	@Override
	public boolean remove(K ele) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(K ele) {
		return false;
	}

	@Override
	public int indexOf(K ele) {
		return 0;
	}

}
