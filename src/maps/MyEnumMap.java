package maps;

import list.arraylist.MyArrayList;
import utils.MyUtilities;

public class MyEnumMap<K extends Enum<K>,V> implements MyMap<K, V>{
	
	private Object[] data;
	private K[] enums;
	private static final Object NULL_OBJECT = new Object();
	
	public MyEnumMap(Class<K> clazz) {
		enums = clazz.getEnumConstants();
		data = new Object[enums.length];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		K obj = (K) key;
		int ordinal = obj.ordinal();
		Object object = data[ordinal];
		return getForNull(object);
	}

	@Override
	public V put(K key, V value) {
		K obj = (K) key;
		int ordinal = obj.ordinal();
		Object object = data[ordinal];
		data[ordinal] = putForNull(value);;
		return getForNull(object);
	}
	
	private Object putForNull(V value) {
		if(value == null)
			return NULL_OBJECT;
		return value;
	}
	
	@SuppressWarnings("unchecked")
	private V getForNull(Object obj) {
		if(obj == NULL_OBJECT)
			return null;
		return (V) obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {
		K obj = (K) key;
		int ordinal = obj.ordinal();
		Object object = data[ordinal];
		data[ordinal] = null;
		return getForNull(object);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object key) {
		K obj = (K) key;
		int ordinal = obj.ordinal();
		Object object = data[ordinal];
		return object != null;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			builder.append(getForNull(data[i]));
			builder.append(",");
		}
		MyUtilities.removeCommaAtEndOfBuilder(builder);
		return builder.toString();
	}

	@Override
	public MyArrayList<K> getKeys() {
		MyArrayList<K> keys = new MyArrayList<K>();
		for (int i = 0; i < data.length; i++) {
			Object object = data[i];
			if(object != null) {
				keys.add(enums[i]);
			}
		}
		return keys;
	}

	@Override
	public MyArrayList<V> getValues() {
		MyArrayList<V> values = new MyArrayList<V>();
		for (int i = 0; i < data.length; i++) {
			Object object = data[i];
			if(object != null) {
				values.add(getForNull(object));
			}
		}
		return values;
	}

	@Override
	public MyArrayList<MyEntry<K, V>> getEntries() {
		MyArrayList<MyEntry<K, V>> entries = new MyArrayList<MyEntry<K,V>>();
		for (int i = 0; i < data.length; i++) {
			Object object = data[i];
			if(object != null) {
				MyEntry<K, V> entry = new MyAbstractMapEntry<K, V>(enums[i], getForNull(object), enums[i].hashCode());
				entries.add(entry);
			}
		}
		return entries;
	}
	
}
