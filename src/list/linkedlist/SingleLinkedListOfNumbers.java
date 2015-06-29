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
	 * Add two numbers represented by linked lists | Set 2
		Given two numbers represented by two linked lists, write a function that returns sum list. The sum list is linked list representation of addition of two input numbers. It is not allowed to modify the lists. Also, not allowed to use explicit extra space (Hint: Use Recursion).
		
		Example
		
		Input:
		  First List: 5->6->3  // represents number 563
		  Second List: 8->4->2 //  represents number 842
		Output
		  Resultant list: 1->4->0->5  // represents number 1405
	 * @param num2
	 * @return - this + num2 in a new list
	 */
	public SingleLinkedListOfNumbers getSumWhenUnitsOccursLast(SingleLinkedListOfNumbers num2) {
		SingleLinkedListOfNumbers sum = new SingleLinkedListOfNumbers();
		ConfigSum configSum = getSumWhenUnitsOccursLast(header, num2.header);
		Integer carry = configSum.carry;
		
		if(carry == 0)
			sum.header = configSum.node;
		else {
			sum.header = new Node<Integer>();
			sum.header.data = configSum.carry;
			sum.header.next = configSum.node;
		}
		return sum;
	}
	
	public ConfigSum getSumWhenUnitsOccursLast(Node<Integer> num1,Node<Integer> num2) {
		
		
		Node<Integer> num1Next = num1.next;
		Node<Integer> num2Next = num2.next;
		
		if(num1Next == null && num2Next == null) {
			return getSumAndCarry(num1, num2,0);
		} else if(num1Next != null && num2Next != null) {
			ConfigSum config = getSumWhenUnitsOccursLast(num1Next,num2Next);
			
			ConfigSum sumAndCarry = getSumAndCarry(num1, num2, config.carry);
			sumAndCarry.node.next = config.node;
			
			return sumAndCarry;
			
		} else {
			if(num1Next == null) {
				ConfigSum config = getSumWhenUnitsOccursLast(num1, num2Next);
				
				ConfigSum sumAndCarry = getSumAndCarry(null,num2 , config.carry);
				sumAndCarry.node.next = config.node;
				
				return sumAndCarry;
				
			} else {
				//num2Next == null
				ConfigSum config = getSumWhenUnitsOccursLast(num1Next, num2);
				
				ConfigSum sumAndCarry = getSumAndCarry(num1,null , config.carry);
				sumAndCarry.node.next = config.node;
				
				return sumAndCarry;
			}
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
	
	private static ConfigSum getSumAndCarry(Node<Integer> digit1,Node<Integer> digit2,int carry) {
		ConfigSum configSum = new ConfigSum();
		
		Integer digit1Data = ( (digit1 == null) ? 0 : digit1.data );
		Integer digit2Data = ( (digit2 == null) ? 0 : digit2.data );
		
		SumAndCarry sumAndCarry = getSumAndCarry(digit1Data,digit2Data,carry);
		
		Node<Integer> node = new Node<Integer>();
		node.data = sumAndCarry.sum;
		
		configSum.node = node;
		configSum.carry = sumAndCarry.carry;
		
		return configSum;
	}
	
	private static class ConfigSum {
		private int carry;
		private Node<Integer> node;
	}
}
