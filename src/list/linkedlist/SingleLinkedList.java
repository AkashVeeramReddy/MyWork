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
