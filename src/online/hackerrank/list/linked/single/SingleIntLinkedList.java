package online.hackerrank.list.linked.single;


public class SingleIntLinkedList {
	
	public Node head;
	
	public SingleIntLinkedList() {
		
	}
	
	public SingleIntLinkedList(int[] array) {
		for (int data : array) {
			add(data);
		}
	}
	
	public void add(int data) {
		Node createNode = createNode();
		createNode.data = data;
		if(head == null) {
			head = createNode;
		} else {
			Node itr = head;
			Node prevItr = null;
			while(itr != null) {
				prevItr = itr;
				itr = itr.next;
			}
			prevItr.next = createNode;
		}
	}
	
	protected Node createNode() {
		return new Node();
	}
	
	protected void delete(int data) {
		Node itr = head;
		Node prevItr = null;
		while(itr != null) {
			int data2 = itr.data;
			if(data == data2) {
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
		Node itrNode = head;
		while (itrNode != null) {
			builder.append(itrNode.data);
			builder.append(",");
			itrNode = itrNode.next;
		}
		return builder.toString();
		
	}
	
}
