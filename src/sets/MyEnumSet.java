package sets;

import maps.MyEnumMap;

public class MyEnumSet<K extends Enum<K>> extends MyAbstractSet<K> {
	
	public MyEnumSet(Class<K> clazz) {
		data = new MyEnumMap<K, Object>(clazz);
	}
	
}
