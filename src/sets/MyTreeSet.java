package sets;

import maps.MyTreeMap;

public class MyTreeSet<K extends Comparable<? super K>> extends MyAbstractSet<K> {

	public MyTreeSet() {
		data = new MyTreeMap<K, Object>();
	}
	
	public static void main(String[] args) {
		MyTreeSet<Integer> tree = new MyTreeSet<Integer>();
		tree.add(50);
		tree.add(25);
		tree.add(75);
		tree.add(13);
		tree.add(38);
		tree.add(62);
		tree.add(87);
		tree.add(0);
		tree.add(20);
		tree.add(30);
		tree.add(45);
		tree.add(55);
		tree.add(70);
		tree.add(80);
		tree.add(100);
		tree.remove(75);
	}
	
}
