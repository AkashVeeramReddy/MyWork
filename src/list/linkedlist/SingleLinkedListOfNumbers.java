package list.linkedlist;

public class SingleLinkedListOfNumbers extends SingleLinkedList<Integer> {
	
	public SingleLinkedListOfNumbers addListWhenUnitsPlaceAtHead(SingleLinkedListOfNumbers secondList) {
		SingleLinkedListOfNumbers sum = new SingleLinkedListOfNumbers();
		populateSumWhenUnitsPlaceAtHead(sum,this.header,secondList.header,0);
		return sum;
	}
	
	private void populateSumWhenUnitsPlaceAtHead(SingleLinkedListOfNumbers sum,
			Node<Integer> listNode1,
			Node<Integer> listNode2,int carry) {
		if(listNode1 == null && (listNode2 ==null)) {
			sum.add(carry);
		} else {
			int v1 = 0;
			if(listNode1 != null) {
				v1 = listNode1.data;
				listNode1 = listNode1.next;
			}
			int v2 = 0;
			if(listNode2 != null) {
				v2 = listNode2.data;
				listNode2 = listNode2.next;
			}
			SumAndCarry sumAndCarry = getSumAndCarry(v1,v2,carry);
			sum.add(sumAndCarry.sum);
			populateSumWhenUnitsPlaceAtHead(sum, listNode1, listNode2, sumAndCarry.carry);
		}
	}
	
	/**
	 * Segregate even and odd nodes in a Linked List
		Given a Linked List of integers, write a function to modify the linked list such that all even numbers appear before all the odd numbers in the modified linked list. Also, keep the order of even and odd numbers same.
		
		Examples:
		Input: 17->15->8->12->10->5->4->1->7->6->NULL
		Output: 8->12->10->4->6->17->15->5->1->7->NULL
		
		Input: 8->12->10->5->4->1->6->NULL
		Output: 8->12->10->4->6->5->1->NULL
		
		// If all numbers are even then do not change the list
		Input: 8->12->10->NULL
		Output: 8->12->10->NULL
		
		// If all numbers are odd then do not change the list
		Input: 1->3->5->7->NULL
		Output: 1->3->5->7->NULL
	 */
	public void segregateEvenAndOddNodes() {
		if(header == null)
			return;
		Node<Integer> evenItr = null;
		Node<Integer> oddItr = null;
		
		Node<Integer> evenHeader = null;
		Node<Integer> oddHeader = null;
		
		Node<Integer> itr = header;
		Node<Integer> prevItr;
		
		while(itr != null) {
			Integer data = itr.data;
			if(data % 2 == 0) {
				if(evenItr == null) {
					evenItr = itr;
					evenHeader = itr;
				} else {
					evenItr.next = itr;
					evenItr = itr;
				}
			} else {
				if(oddItr == null) {
					oddItr = itr;
					oddHeader = itr;
				} else {
					oddItr.next = itr;
					oddItr = itr;
				}
			}
			prevItr = itr;
			itr = itr.next;
			prevItr.next = null;
		}
		if(evenItr == null) {
			//only odd nodes
			header = oddHeader;
			return;
		}
		if(oddItr == null) {
			//only even nodes
			header = evenHeader;
			return;
		}
		//both odd and even nodes
		header = evenHeader;
		evenItr.next = oddHeader;
	}

	private static class SumAndCarry {
		
		private int sum;
		private int carry;
		
	}
	
	public static SumAndCarry getSumAndCarry(int ...n1) {
		SumAndCarry sumAndCarry = new SumAndCarry();
		int sum = 0;
		for (int i : n1) {
			sum = sum + i;
		}
		sumAndCarry.sum = (sum)%10;
		sumAndCarry.carry = (sum)/10;
		return sumAndCarry;
	}
}
