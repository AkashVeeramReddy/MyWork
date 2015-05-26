package list.linkedlist;

import stack.IStackWithPeek;;

public class StackAsSingleLinkList<K> extends SingleLinkedList<K> implements IStackWithPeek<K> {

	@Override
	public boolean push(K data) {
		Node<K> createdNode = createNode();
		createdNode.data = data;
		if(header == null)
			header = createdNode;
		else {
			createdNode.next = header;
			header = createdNode;
		}
		return true;
	}

	@Override
	public K pop() {
		K data = header.data;
		header = header.next;
		return data;
	}

	@Override
	public boolean canPop() {
		return header != null;
	}

	@Override
	public K peek() {
		if(canPop()) {
			return header.data;
		}
		return null;
	}
	
}
