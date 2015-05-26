package list.linkedlist;

public class TestSingleLinkedList {
	public static void main(String[] args) {
		/*SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			list.add(i);
		}
		boolean listCircular = list.isListCircular();
		list.makeCircular(2);
		boolean listCircular2 = list.isListCircular();
		System.out.println();*/
		
		SortableStackAsSingleLinkList<Integer> list = 
				new SortableStackAsSingleLinkList<Integer>();
		list.add(4);
		list.add(2);
		list.add(7);
		list.add(5);
		list.add(6);
		SortableStackAsSingleLinkList<Integer> createSortedStack = list.createSortedStack();
		System.out.println();
		/*SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
		for (int i = 0; i < 4; i++) {
			list.add(i);
		}
		Integer deleteNodeInMiddle = list.deleteNodeInMiddle();
		System.out.println();
		SingleLinkedListOfNumbers listOfNumbers1 = new SingleLinkedListOfNumbers();
		listOfNumbers1.add(6);
		listOfNumbers1.add(7);
		listOfNumbers1.add(2);
		
		SingleLinkedListOfNumbers listOfNumbers2 = new SingleLinkedListOfNumbers();
		listOfNumbers2.add(8);
		listOfNumbers2.add(5);
		listOfNumbers2.add(8);
		
		SingleLinkedListOfNumbers addList = listOfNumbers1.addListWhenUnitsPlaceAtHead(listOfNumbers2);
		System.out.println();*/
	}
}
