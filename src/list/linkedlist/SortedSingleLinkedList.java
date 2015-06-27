package list.linkedlist;

import list.linkedlist.SingleLinkedList.Node;


public class SortedSingleLinkedList<K extends Comparable<? super K>> extends SingleLinkedList<K> {
	
	@Override
	public void add(K data) {
		Node<K> createNode = createNode();
		createNode.data = data;
		if(header == null) {
			header = createNode;
		} else {
			Node<K> itr = header;
			Node<K> prevItr = null;
			
			while(itr != null && (data.compareTo(itr.data) > 0)) {
				prevItr = itr;
				itr = itr.next;
			}
			prevItr.next = createNode;
			createNode.next = itr;
		}
	}
	
	public void removeDuplicates() {
		Node<K> itr = header;
		//Node<K> prevItr = null;
		K itrData = null;
		
		while(itr != null) {
			itrData = itr.data;
			
			Node<K> innerItr = itr.next;
			if(innerItr == null)
				return;
			else {
				while(innerItr != null && innerItr.data.equals(itrData)) {
					innerItr = innerItr.next;
				}
				itr.next = innerItr;
				itr = innerItr;
			}
		}
	}
	
	/**
	 * Find the intersection of two sorted lists. list1 - this(first list)
	 * @param list2 - 2nd list
	 * @return new sorted list containing intersection of list1,list2
	 */
	public SortedSingleLinkedList<K> getInterSection(SortedSingleLinkedList<K> list2) {
		SortedSingleLinkedList<K> interList = new SortedSingleLinkedList<K>();
		
		Node<K> list1Itr = this.header;
		Node<K> list2Itr = list2.header;
		Node<K> interListItr = null;
		
		K list1DataItr;
		K list2DataItr;
		
		K interListDataItr = null;
		
		while(list1Itr !=null && list2Itr != null) {
			list1DataItr = list1Itr.data;
			list2DataItr = list2Itr.data;
			
			if(list1DataItr == list2DataItr || list1DataItr.equals(list2DataItr)) {
				if(interListDataItr == list1DataItr || list1DataItr.equals(interListDataItr)) {
					//last added object was same. so no add
				} else {
					//last added object different. so add
					Node<K> createNode = createNode();
					createNode.data = list1DataItr;
					
					if(interListDataItr == null) {
						interList.header = createNode;
					} else {
						interListItr.next = createNode;
					}
					interListItr = createNode;
					interListDataItr = interListItr.data;
					
				}
				
				list1Itr = list1Itr.next;
				list2Itr = list2Itr.next;
			} else {
				int compareTo = list1DataItr.compareTo(list2DataItr);
				if(compareTo < 0) {
					//list1DataItr < list2DataItr
					//Move list1Itr forward
					list1Itr = list1Itr.next;
				} else {
					//compareTo > 0. list1DataItr > list2DataItr
					//Move list2Itr forward
					list2Itr = list2Itr.next;
				}
			}
		}
		
		
		
		return interList;
	}
}
