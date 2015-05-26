package maps;

class MyHashMapEntry<K,V> extends MyAbstractMapEntry<K, V> {
	
	MyHashMapEntry<K,V> next;
	
	MyHashMapEntry(K key,V value,int hash) {
		super(key, value, hash);
	}
	
}
