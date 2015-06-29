package list.linkedlist;

public class TestSingleLinkedListOfNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleLinkedListOfNumbers list = new SingleLinkedListOfNumbers();
		//list.add(5);
		//list.add(5);
		//list.add(5);
		list.add(6);
		list.add(3);
		
		SingleLinkedListOfNumbers list1 = new SingleLinkedListOfNumbers();
		//list1.add(8);
		//list1.add(4);
		list1.add(4);
		list1.add(8);
		SingleLinkedListOfNumbers sum = list.getSumWhenUnitsOccursLast(list1);
		System.out.println(sum);
	}

}
