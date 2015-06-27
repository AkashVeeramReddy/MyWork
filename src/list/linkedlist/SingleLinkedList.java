package list.linkedlist;

public class SingleLinkedList<K> {
	
	protected Node<K> header;
	
	public void add(K data) {
		Node<K> createNode = createNode();
		createNode.data = data;
		if(header == null) {
			header = createNode;
		} else {
			Node<K> itr = header;
			Node<K> prevItr = null;
			while(itr != null) {
				prevItr = itr;
				itr = itr.next;
			}
			prevItr.next = createNode;
		}
	}
	
	protected Node<K> createNode() {
		return new Node<K>();
	}
	
	protected void delete(K data) {
		Node<K> itr = header;
		Node<K> prevItr = null;
		while(itr != null) {
			K data2 = itr.data;
			if(data == data2 || data.equals(data2)) {
				prevItr.next = itr.next;
				return;
			}
			prevItr = itr;
			itr = itr.next;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node<K> itrNode = header;
		while (itrNode != null) {
			builder.append(itrNode.data);
			builder.append(",");
			itrNode = itrNode.next;
		}
		return builder.toString();
		
	}
	
	public void reverse() {
		header = reverse(header);
	}
	
	/**
	 * Reverse link list in chunks of size k.
	 * Suppose list is 1-2-3-4-5-6-7-8 and k is 3,
	 * the list should now be 3-2-1-6-5-4-7-8
	 * Note that 7 and 8 have not been reversed as it is a chunk of size 2.
	 * @param k
	 */
	public void reverseK(int k) {
		if(k == 1) {
			reverse();
		} else {
			Node<K> itr = header;
			Node<K> prevItr = null;
			int noOfReverse = 0;
			while(itr != null) {
				Node<K> kthNodeFromItr = getNodeFrom(itr, k);
				if(kthNodeFromItr == null) {
					//no reverse
					break;
				} else {
					Node<K> kthNodeNext = kthNodeFromItr.next;
					reverseBetween(itr, kthNodeFromItr, prevItr);
					if(noOfReverse == 0) {
						header = kthNodeFromItr;
						noOfReverse++;
					}
					itr.next = kthNodeNext;
					//prevItr for next round
					prevItr = itr;
					itr = kthNodeNext;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param start
	 * @param k
	 * @return - returns kth node from start(so if k=2,next node is returned)
	 */
	protected Node<K> getNodeFrom(Node<K> start,int k) {
		Node<K> itr = start;
		int i = 1;
		while((i < k) && (itr !=null)) {
			i++;
			itr=itr.next;
		}
		return itr;
	}
	
	/**
	 * Reverses nodes between start and end(both inclusive)
	 * @param start - not null
	 * @param end - not null
	 * @param prevStart - node before start
	 * @return returns the new start which is usually the end
	 */
	protected Node<K> reverseBetween(Node<K> start,Node<K> end,Node<K> prevStart) {
		Node<K> itr = start;
		Node<K> itrNext = start.next;
		Node<K> itrNextNext = null;
		
		Node<K> endBackup = end;
		while(itr != end) {
			
			itrNextNext = itrNext.next;
			itrNext.next = itr;
			
			itr = itrNext;
			itrNext = itrNextNext;
					
		}
		if(prevStart != null) {
			prevStart.next = end;
		}
		start.next = endBackup.next;
		return end;
	}
	
	/**
	 * 
	 * @param node - starting from where you have to reverse
	 * @return - header
	 */
	protected Node<K> reverse(Node<K> node) {
		if(node == null || node.next == null) {
			return node;
		}
		Node<K> first = node;
		Node<K> rest = node.next;
		
		Node<K> reverse = reverse(rest);
		
		rest.next = first;
		first.next = null;
		// TODO Auto-generated method stub
		return reverse;
	}

	public K deleteNodeInMiddle() {
		if(header == null)
			return null;
		Node<K> itrNode = header;
		Node<K> doubleItrNode = header.next;
		Node<K> prevItrNode = null;
		while(doubleItrNode !=null && doubleItrNode.next != null) {
			prevItrNode = itrNode;
			itrNode = itrNode.next;
			doubleItrNode = doubleItrNode.next.next;
		}
		prevItrNode.next = itrNode.next;
		return itrNode.data;
	}
	
	public void makeCircular(K data) {
		Node<K> itrNode = header;
		Node<K> prevItrNode = null;
		Node<K> dataNode = null;
		while (itrNode != null) {
			K data2 = itrNode.data;
			if(data2 == data || (data2.equals(data))) {
				dataNode = itrNode;
			}
			prevItrNode = itrNode;
			itrNode = itrNode.next;
		}
		prevItrNode.next = dataNode;
	}
	
	public boolean isLoopOccursInList(){
		boolean isListCircular = false;
		Node<K> itrNode = header;
		Node<K> doubleItrNode = header;
		Node<K> prevItrNode = null;
		while(doubleItrNode != null && doubleItrNode.next != null) {
//			prevItrNode = itrNode;
			itrNode = itrNode.next;
			doubleItrNode = doubleItrNode.next.next;
			if(itrNode == doubleItrNode)
				return true;
		}
		return isListCircular;
	}
	
	public boolean isListPalindrome() {
		//get middle of linklist. if even, find the element of right side
		Node<K> middleItr = header;
		Node<K> doubleItr = header;
		while(doubleItr !=null && doubleItr.next != null) {
			middleItr = middleItr.next;
			doubleItr = doubleItr.next.next;
		}
		Node<K> itr = header;
		if(doubleItr != null) {
			//no of elements odd
			//checking for equality should start after the element after middle
			middleItr = middleItr.next;
		}
		Node<K> reverse = reverse(middleItr);
		
		while(reverse != null) {
			if(!(itr.data.equals(reverse.data))) {
				return false;
			}
			reverse = reverse.next;
			itr = itr.next;
		}
		
		return true;
	}
	
	public K getNodeFromEndofEndlist(int num) {
		Node<K> itr = header;
		for(int i = 1; i< num ; i++) {
			if(itr == null) {
				return null;
			}
			itr = itr.next;
		}
		if(itr == null)
			return null;
		Node<K> node = header;
		while(itr != null) {
			itr = itr.next;
			node = node.next;
		}
		return node.data;
	}
	
	public static class Node<K> {
		K data;
		Node<K> next;
		
		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
}
