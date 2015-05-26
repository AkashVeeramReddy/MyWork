package sets;

public interface MySet<K> {
	
	Object DUMMY = new Object();
	
	boolean contains(Object o);
	
	boolean remove(Object o);
	
	boolean add(K k);
	
}
