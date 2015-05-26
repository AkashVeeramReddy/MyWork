package sets;

import maps.MyMap;
import sets.MySet;
import static sets.MySet.DUMMY;

@SuppressWarnings("unused")
public class MyAbstractSet<K> implements MySet<K> {
	
	protected MyMap<K,Object> data;
	
	@Override
	public boolean contains(Object o) {
		return (data.get(o) != DUMMY);
	}

	@Override
	public boolean remove(Object o) {
		return (data.remove(o)!=DUMMY);
	}

	@Override
	public boolean add(K k) {
		Object object = data.get(k);
		if(object == DUMMY)
			return false;
		else {
			data.put(k, DUMMY);
			return true;
		}
	}

}
