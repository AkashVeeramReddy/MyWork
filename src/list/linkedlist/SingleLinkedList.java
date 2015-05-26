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
	
	public boolean isListCircular(){
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
	
	protected class Node<K> {
		K data;
		Node<K> next;
		
		@Override
		public String toString() {
			return String.valueOf(data);
		}
	}
	
}
