package list.linkedlist;


public class MyLinkedListNode<K> {
	
	K data;
	MyLinkedListNode<K> prev = null;
	MyLinkedListNode<K> next = null;
	
	MyLinkedListNode(K data,MyLinkedListNode<K> prev,MyLinkedListNode<K> next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}
	
	@Override
	public String toString() {
		if(this==null)
			return "null";
		StringBuilder builder = new StringBuilder();
		builder.append("data:");
		builder.append(data);
		builder.append(",prev ele:");
		builder.append(prev==null?"null":prev.data);
		builder.append(",next ele:");
		builder.append(next==null?"null":next.data);
		return builder.toString();
	}

	public K getData() {
		return data;
	}

	public MyLinkedListNode<K> getPrev() {
		return prev;
	}

	public MyLinkedListNode<K> getNext() {
		return next;
	}

	public void setData(K data) {
		this.data = data;
	}

	public void setPrev(MyLinkedListNode<K> prev) {
		this.prev = prev;
	}

	public void setNext(MyLinkedListNode<K> next) {
		this.next = next;
	}
	
}