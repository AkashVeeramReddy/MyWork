package maps;

import list.arraylist.MyArrayList;

public class MyLinkedHashMap<K, V> extends MyHashMap<K, V> {
	
	private MyLinkedMapEntry<K,V> header = null;
	
	public MyLinkedHashMap() {
		data = new MyLinkedMapEntry[INITIAL_SIZE];
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void doPostProcessingDuringAddition(MyHashMapEntry myEntry) {
		MyLinkedMapEntry<K, V> ele = (MyLinkedMapEntry<K, V>) myEntry;
		ele.nextEntry = header;
		if(header != null)
			header.prevEntry = ele;
		header = ele;
	}
	
	protected MyHashMapEntry<K, V> createNewElement(K key, V value, int hashCode) {
		return new MyLinkedMapEntry<K, V>(key, value, hashCode);
	}
	
	@SuppressWarnings({ })
	protected void doPostProcessingDuringRemoval(
			MyHashMapEntry<K, V> myEntry) {
		MyLinkedMapEntry<K, V> ele = (MyLinkedMapEntry<K, V>) myEntry;
		if(ele.prevEntry != null) {
			ele.prevEntry.nextEntry = ele.nextEntry;
		}
		if(ele.nextEntry != null) {
			ele.nextEntry.prevEntry = ele.prevEntry;
		}
		if(ele == header)
			header = ele.nextEntry;
	}
	
	@Override
	public MyArrayList<K> getKeys() {
		MyArrayList<K> keys = new MyArrayList<K>();
		MyLinkedMapEntry<K, V> itr = header;
		while(itr != null) {
			keys.add(itr.key);
			itr=itr.nextEntry;
		}
		return keys;
	}
	
	@Override
	public MyArrayList<V> getValues() {
		MyArrayList<V> values = new MyArrayList<V>();
		MyLinkedMapEntry<K, V> itr = header;
		while(itr != null) {
			values.add(itr.value);
			itr=itr.nextEntry;
		}
		return values;
	}
	
	@Override
	public MyArrayList<MyEntry<K, V>> getEntries() {
		MyArrayList<MyEntry<K, V>> entries = new MyArrayList<MyEntry<K, V>>();
		MyLinkedMapEntry<K, V> itr = header;
		while(itr != null) {
			entries.add(itr);
			itr=itr.nextEntry;
		}
		return entries;
	}
	
}
