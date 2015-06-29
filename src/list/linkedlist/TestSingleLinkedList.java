package list.linkedlist;

import java.util.Collections;

import utils.CollectionUtils;

public class TestSingleLinkedList {
	public static void main(String[] args) {
		SingleLinkedList<Integer> myLinkedList = new SingleLinkedList<Integer>();
		CollectionUtils.addNumbers(myLinkedList);
		
		System.out.println(myLinkedList);
		//myLinkedList.reverse();
		myLinkedList.reverseK(3);
		System.out.println(myLinkedList);
	}
}
