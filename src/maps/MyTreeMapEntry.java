package maps;

public class MyTreeMapEntry<K extends Comparable<? super K>,V> extends MyAbstractMapEntry<K,V> {
	
	MyTreeMapEntry(K key, V value, int hash) {
		super(key, value, hash);
	}

	MyTreeMapEntry(K key, V value) {
		this(key, value, 0);
	}

	MyTreeMapEntry<K, V> left;
	
	MyTreeMapEntry<K, V> right;
	
}
