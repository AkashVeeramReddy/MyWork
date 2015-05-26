package sorting;

import list.linkedlist.MyLinkedList;
import list.linkedlist.MyLinkedListNode;

public class SortableLinkList<K extends Comparable<? super K>> extends MyLinkedList<K> {
	
	public void recursiveInsertionSort() {
		if(noOfElements > 0) {
			recursiveInsertionSort(header);
		}
	}
	
	public void recursiveInsertionSort(MyLinkedListNode<K> node) {
		if(node != null) {
			K idxElement = node.getData();
			MyLinkedListNode<K> toBeReplacedNode = node;
			recursiveInsertionSort(node.getNext());
			for(MyLinkedListNode<K> i = node.getNext();i!=null;i = i.getNext()) {
				K itrData = i.getData();
				if(idxElement.compareTo(itrData) == 1) {
					i.getPrev().setData(itrData);
					toBeReplacedNode = i;
				} else {
					break;
				}
			}
			if(toBeReplacedNode != node) {
				toBeReplacedNode.setData(idxElement);
			}
		}
	}
}
