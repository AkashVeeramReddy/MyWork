package sets;

import maps.MyHashMap;

public class MyHashSet<K> extends MyAbstractSet<K>{

	public MyHashSet() {
		data = new MyHashMap<K, Object>();
	}
}
