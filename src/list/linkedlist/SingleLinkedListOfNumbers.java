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
