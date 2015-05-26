package maps;

public interface MyEntry<K,V> {
	
	K getKey();
	
	V getValue();
	
	V setValue(V value);
	
	boolean equals(Object o);
	
	int getHash();
	
}
