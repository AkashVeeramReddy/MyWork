package maps;

class MyAbstractMapEntry<K ,V> implements MyEntry<K, V> {
	
	K key;
	V value;
	int hash;
	
	MyAbstractMapEntry(K key,V value,int hash) {
		this.key = key;
		this.value = value;
		this.hash = hash;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	@Override
	public int getHash() {
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		return false;
	}
	
	@Override
	public String toString() {
		return getKey() + "=" + getValue();
	}

}
