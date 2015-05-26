package list.linkedlist;

import java.util.Iterator;

public class CheckMyLinkedList {
	public static void main(String[] args) {
		MyLinkedList<Integer> myLinkedList = new MyLinkedList<Integer>();
		myLinkedList.addFirst(10);
		myLinkedList.addLast(20);
		myLinkedList.add(30);
		myLinkedList.add(40);
		myLinkedList.add(50);
		for (Integer integer : myLinkedList) {
			System.out.println(integer);
		}
		for (Iterator<Integer> iterator = myLinkedList.iterator(); iterator.hasNext();) {
			Integer type = iterator.next();
			if(type == 30)
				iterator.remove();
		}
		/*myLinkedList.add(40);
		myLinkedList.add(50);
		myLinkedList.removeFirst();
		myLinkedList.removeLast();
		myLinkedList.removeElement(30);*/
		myLinkedList.reverse();
		System.out.println(myLinkedList);
	}
}
