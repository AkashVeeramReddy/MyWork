package list.linkedlist;

import java.util.Iterator;

public class CheckMyLinkedList {
	public static void main(String[] args) {
		SingleLinkedList<Integer> myLinkedList = new SingleLinkedList<Integer>();
		myLinkedList.add(10);
		myLinkedList.add(20);
		myLinkedList.add(20);
		myLinkedList.add(10);
		
		System.out.println(myLinkedList);
		//myLinkedList.reverse();
		System.out.println(myLinkedList.isListPalindrome());
	}
}
