package maps;

import list.arraylist.MyArrayList;

public interface MyMap<K,V> {
	
	int INITIAL_SIZE = 16;
	float SCALE_FACTOR = 0.75f;
	
	V get(Object key);
	
	V put(K key, V value);
	
	V remove(Object key);
	
	boolean contains(Object key);
	
	MyArrayList<K> getKeys();
	
	MyArrayList<V> getValues();
	
	MyArrayList<MyEntry<K, V>> getEntries();
	
}
