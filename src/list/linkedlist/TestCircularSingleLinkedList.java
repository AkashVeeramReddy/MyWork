package list.linkedlist;

public class TestCircularSingleLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CircularSingleLinkedList<Integer> circ = new CircularSingleLinkedList<Integer>();
		circ.add(1);
		circ.add(2);
		circ.add(3);
		circ.add(4);
		
		System.out.println(circ);
		
		CircularSingleLinkedList<Integer> circ2 = circ.splitIntoTwoHalves();
		
		System.out.println("First list:" + circ);
		System.out.println("Second list:" + circ2);
	}

}
