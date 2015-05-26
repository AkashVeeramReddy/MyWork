package maps;

import list.arraylist.MyArrayList;
import utils.MyUtilities;

public class MyHashMap<K,V> implements MyMap<K, V> {
	
	@SuppressWarnings("rawtypes")
	protected MyHashMapEntry [] data = new MyHashMapEntry[INITIAL_SIZE];
	private int size = INITIAL_SIZE;
	private int noOfElements;
	private int nextResizeValue = (int) (INITIAL_SIZE*SCALE_FACTOR);
	
	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		V oldValue = null;
		int hashCode = key.hashCode();
		int index = getIndex(hashCode);
		MyHashMapEntry<K,V> entry = data[index];
		while(entry != null) {
			if(entry.key.equals(key)) {
				return entry.value;
			}
			entry = entry.next;
		}
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		V oldValue = null;
		int hashCode = key.hashCode();
		int index = getIndex(hashCode);
		MyHashMapEntry<K,V> itrEntry = data[index];
		while(itrEntry != null) {
			if(itrEntry.key.equals(key)) {
				oldValue = itrEntry.value;
				itrEntry.value = value;
				return oldValue;
			}
			itrEntry = itrEntry.next;
		}
		if((noOfElements + 1)==nextResizeValue) {
			update();
		}
		MyHashMapEntry<K, V> newEntry = createNewElement(key, value, hashCode);
		addAtIndex(newEntry);
		doPostProcessingDuringAddition(newEntry);
		noOfElements++;
		return oldValue;
	}
	
	protected MyHashMapEntry<K, V> createNewElement(K key, V value, int hashCode) {
		return new MyHashMapEntry<K, V>(key, value, hashCode);
	}

	@SuppressWarnings("rawtypes")
	protected void doPostProcessingDuringAddition(MyHashMapEntry ele) {
	}
	
	protected void doPostProcessingDuringRemoval(
			MyHashMapEntry<K, V> myEntry) {
	}
	
	@SuppressWarnings("rawtypes")
	private void update() {
		size = size *2;
		nextResizeValue = (int) (size*SCALE_FACTOR);
		MyHashMapEntry [] newData = new MyHashMapEntry[size];
		for (int i = 0; i < data.length; i++) {
			MyHashMapEntry entry = data[i];
			while(entry != null) {
				addAtIndex(entry,newData);
				entry = entry.next;
			}
		}
		data = newData;
		newData = null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addAtIndex(MyHashMapEntry entry,
			MyHashMapEntry[] data) {
		int hash = entry.hash;
		int index = getIndex(hash);
		MyHashMapEntry entry2 = data[index];
		entry.next = entry2;
		data[index] = entry;
	}
	
	@SuppressWarnings("rawtypes")
	private void addAtIndex(MyHashMapEntry entry) {
		addAtIndex(entry, data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {
		V oldValue = null;
		int hashCode = key.hashCode();
		int index = getIndex(hashCode);
		MyHashMapEntry<K,V> itrEntry = data[index];
		MyHashMapEntry<K,V> prevEntry = null;
		while(itrEntry != null) {
			if(itrEntry.key.equals(key)) {
				oldValue = itrEntry.value;
				if(prevEntry != null) {
					prevEntry.next = itrEntry.next;
				} else {
					data[index] = null;
				}
				doPostProcessingDuringRemoval(itrEntry);
				itrEntry = null;
				noOfElements--;
				return oldValue;
			}
			prevEntry = itrEntry;
			itrEntry = itrEntry.next;
		}
		return oldValue;
	}

	@Override
	public boolean contains(Object key) {
		return get(key) != null;
	}
	
	private int getIndex(int index) {
		return index%size;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			MyHashMapEntry<K,V> entry = data[i];
			while(entry != null) {
				builder.append(entry);
				builder.append(",");
				entry = entry.next;
			}
		}
		MyUtilities.removeCommaAtEndOfBuilder(builder);
		return builder.toString();
	}
	
	public static void main(String[] args) {
		MyHashMap<Integer, Integer> entry = new MyLinkedHashMap<Integer, Integer>();
		for (int i = 0; i < 20; i++) {
			entry.put(i, i+1);
		}
		System.out.println();
	}

	@SuppressWarnings("unchecked")
	@Override
	public MyArrayList<K> getKeys() {
		MyArrayList<K> keys = new MyArrayList<K>();
		for (int i = 0; i < data.length; i++) {
			MyHashMapEntry<K,V> entry = data[i];
			while(entry != null) {
				keys.add(entry.key);
				entry = entry.next;
			}
		}
		return keys;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MyArrayList<V> getValues() {
		MyArrayList<V> values = new MyArrayList<V>();
		for (int i = 0; i < data.length; i++) {
			MyHashMapEntry<K,V> entry = data[i];
			while(entry != null) {
				values.add(entry.value);
				entry = entry.next;
			}
		}
		return values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MyArrayList<MyEntry<K, V>> getEntries() {
		MyArrayList<MyEntry<K, V>> entries = new MyArrayList<MyEntry<K, V>>();
		for (int i = 0; i < data.length; i++) {
			MyHashMapEntry<K,V> entry = data[i];
			while(entry != null) {
				entries.add(entry);
				entry = entry.next;
			}
		}
		return entries;
	}
	
}
