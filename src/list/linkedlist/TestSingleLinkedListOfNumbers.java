package list.linkedlist;

public class TestSingleLinkedListOfNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleLinkedListOfNumbers list = new SingleLinkedListOfNumbers();
		list.add(17);
		list.add(15);
		list.add(8);
		list.add(12);
		list.add(10);
		list.add(5);
		list.add(4);
		list.add(1);
		list.add(7);
		list.add(6);
		
		System.out.println(list);
		list.segregateEvenAndOddNodes();
		System.out.println(list);
	}

}
