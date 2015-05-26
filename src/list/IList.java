package list;


public interface IList<K> extends Iterable<K> {
	
	boolean add(K ele);
	
	boolean addAt(K ele,int index) throws IndexOutOfBoundsException;
	
	K set(K ele,int index) throws IndexOutOfBoundsException;
	
	K removeAt(int index) throws IndexOutOfBoundsException;
	
	boolean remove(K ele);
	
	K get(int index) throws IndexOutOfBoundsException;
	
	int size();
	
	boolean isEmpty();
	
	boolean contains(K ele);
	
	int indexOf(K ele);
	
}
