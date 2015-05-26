package maps;

public class MyLinkedMapEntry<K,V> extends MyHashMapEntry<K,V> {
	
	MyLinkedMapEntry<K,V> prevEntry;
	MyLinkedMapEntry<K,V> nextEntry;
	
	MyLinkedMapEntry(K key,V value,int hash) {
		super(key, value, hash);
	}
	
}
