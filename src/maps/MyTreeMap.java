package maps;

import list.arraylist.MyArrayList;

public class MyTreeMap<K extends Comparable<? super K>,V> implements MyMap<K, V> {
	
	private MyTreeMapEntry<K, V> root;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public V get(Object key) {
		Comparable castedKey = (Comparable) key;
		MyTreeMapEntry<K, V> itr = root;
		int compareTo = 0;
		while (itr != null) {
			compareTo = castedKey.compareTo(itr.key);
			if(compareTo == 0) {
				return itr.value;
			} else {
				if(compareTo == -1) {
					itr = itr.left;
				} else if(compareTo == 1) {
					itr = itr.right;
				}
			}
		
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		MyTreeMapEntry<K, V> entry = new MyTreeMapEntry<K, V>(key, value);
		if(root == null) {
			root = entry;
		} else {
			int compareTo = 0;
			MyTreeMapEntry<K, V> itr = root;
			MyTreeMapEntry<K, V> itrParent = null;
			while (itr != null) {
//				compareTo = itr.data.compareTo(element);
				compareTo = key.compareTo(itr.key);
				if(compareTo == 0) {
					V oldValue = itr.value;
					itr.value = value;
					return oldValue;
				} else {
					itrParent = itr;
					if(compareTo == -1) {
						itr = itr.left;
					} else if(compareTo == 1) {
						itr = itr.right;
					}
				}
			
			}
			if(compareTo == -1) {
				itrParent.left = entry;
			} else if(compareTo == 1) {
				itrParent.right = entry;
			}
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public V remove(Object key) {
		Comparable castedKey = (Comparable) key;
		MyTreeMapEntry<K, V> itr = root;
		MyTreeMapEntry<K, V> itrParent = null;
		int compareTo = 0;
		while (itr != null) {
			compareTo = castedKey.compareTo(itr.key);
			if(compareTo == 0) {
				V value = itr.value;
				MyTreeMapEntry<K, V> nextElement = findReplacementElement(itr);
				if(nextElement != null) {
					itr.value = nextElement.value;
					itr.key = nextElement.key;
				} else {
					int compareTo2 = itr.key.compareTo(itrParent.key);
					if(compareTo2 == -1) {
						itrParent.right = null;
					} else if(compareTo2 == 1) {
						itrParent.left = null;
					}
				}
				return value;
			} else {
				itrParent = itr;
				if(compareTo == -1) {
					itr = itr.left;
				} else if(compareTo == 1) {
					itr = itr.right;
				}
			}
		
		}
		return null;
	}

	private MyTreeMapEntry<K, V> findReplacementElement(MyTreeMapEntry<K, V> ele) {
		if(ele.right != null) {
			return findNextElement(ele);
		} else if(ele.left != null) {
			return findPreviousElement(ele);
		}
		return null;
	}

	private MyTreeMapEntry<K, V> findPreviousElement(MyTreeMapEntry<K, V> ele) {
		MyTreeMapEntry<K,V> itr = ele.left;
		MyTreeMapEntry<K,V> parent = ele;
		while(itr.right != null) {
			parent = itr;
			itr = itr.right;
		}
		parent.right = null;
		return itr;
	}

	private MyTreeMapEntry<K, V> findNextElement(MyTreeMapEntry<K, V> ele) {
		MyTreeMapEntry<K, V> itr = ele.right;
		MyTreeMapEntry<K, V> parent = ele;
		while(itr.left != null) {
			parent = itr;
			itr = itr.left;
		}
		parent.left = null;
		return itr;
	}

	@Override
	public boolean contains(Object key) {
		return get(key) != null;
	}
	
	public static void main(String[] args) {
		MyTreeMap<Integer, Integer> tree = new MyTreeMap<Integer, Integer>();
		tree.put(50,1);
		tree.put(25,1);
		tree.put(75,1);
		tree.put(13,1);
		tree.put(38,1);
		tree.put(62,1);
		tree.put(87,1);
		tree.put(0,1);
		tree.put(20,1);
		tree.put(30,1);
		tree.put(45,1);
		tree.put(55,1);
		tree.put(70,1);
		tree.put(80,1);
		tree.put(100,1);
		tree.remove(75);
		System.out.println();
	}

	@Override
	public MyArrayList<K> getKeys() {
		MyArrayList<K> keys = new MyArrayList<K>();
		populateKeys(keys,root);
		return keys;
	}

	private void populateKeys(MyArrayList<K> keys, MyTreeMapEntry<K, V> itr) {
		while(itr != null) {
			populateKeys(keys, itr.left);
			keys.add(itr.key);
			populateKeys(keys, itr.right);
		}
	}

	@Override
	public MyArrayList<V> getValues() {
		MyArrayList<V> values = new MyArrayList<V>();
		populateValues(values,root);
		return values;
	}

	private void populateValues(MyArrayList<V> values,
			MyTreeMapEntry<K, V> itr) {
		while(itr != null) {
			populateValues(values, itr.left);
			values.add(itr.value);
			populateValues(values, itr.right);
		}
	}

	@Override
	public MyArrayList<MyEntry<K, V>> getEntries() {
		MyArrayList<MyEntry<K, V>> entries = new MyArrayList<MyEntry<K,V>>();
		populateEntries(entries,root);
		return entries;
	}

	private void populateEntries(MyArrayList<MyEntry<K, V>> entries,
			MyTreeMapEntry<K, V> itr) {
		while(itr != null) {
			populateEntries(entries, itr.left);
			entries.add(itr);
			populateEntries(entries, itr.right);
		}
	}

}
