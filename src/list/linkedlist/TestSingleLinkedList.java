package list.linkedlist;

public class TestSingleLinkedList {
	public static void main(String[] args) {
		SingleLinkedList<Integer> myLinkedList = new SingleLinkedList<Integer>();
		myLinkedList.add(1);
		myLinkedList.add(2);
		myLinkedList.add(3);
		myLinkedList.add(4);
		myLinkedList.add(5);
		myLinkedList.add(6);
		myLinkedList.add(7);
		myLinkedList.add(8);
		//.add(10);
		
		System.out.println(myLinkedList);
		//myLinkedList.reverse();
		myLinkedList.reverseK(3);
		System.out.println(myLinkedList);
	}
}
