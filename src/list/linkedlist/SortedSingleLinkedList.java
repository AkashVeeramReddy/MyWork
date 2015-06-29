package list.linkedlist;



public class SortedSingleLinkedList<K extends Comparable<? super K>> extends SingleLinkedList<K> {
	
	public SortedSingleLinkedList() {
		
	}
	public SortedSingleLinkedList(K[] data) {
		for (K k : data) {
			super.add(k);
		}
		sort();
	}
	
	@SuppressWarnings("unchecked")
	protected void sort() {
		Node<K> itr = header;
		Node<K> prevItr = null;
		while(itr != null) {
			prevItr = itr;
			itr = itr.next;
		}
		header = sort(header,prevItr)[0];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Node[] sort(Node<K> start,
			Node<K> end) {
		if(start != end) {
			Node<K> middle = getNodeInMiddle(start, end);
			Node<K> list2Start = middle.next;
			Node[] list1Info = sort(start,middle);
			Node[] list2Info = sort(list2Start,end);
			
			return merge(list1Info[0],list1Info[1],list2Info[0],list2Info[1]);
		}
		//1 node
		return new Node[] {start,end};
	}
	
	@SuppressWarnings("rawtypes")
	protected Node[] merge(Node<K> start1,Node<K> end1,Node<K> start2,Node<K> end2) {
		Node<K> leftItr = start1;
		Node<K> rightItr = start2;
		
		Node<K> headSubList = null;
		Node<K> sortedListItr = null;
		
		boolean isLeftOver = false;
		boolean isRightOver = false;
		while(!isLeftOver || !isRightOver) {
			if(isLeftOver) {
				//left Over
				sortedListItr.next = rightItr;
				sortedListItr = end2;
				break;
			} else if(isRightOver) {
				//right over
				sortedListItr.next = leftItr;
				sortedListItr = end1;
				break;
			} else {
				//both limits have not reached
				int compareTo = leftItr.data.compareTo(rightItr.data);
				if(compareTo > 0) {
					//left Itr is greater
					if(headSubList == null) {
						headSubList = rightItr;
						sortedListItr = rightItr;
					} else {
						sortedListItr.next = rightItr;
						sortedListItr = rightItr;
					}
					if(rightItr == end2) {
						isRightOver = true;
					} else {
						rightItr = rightItr.next;
					}
					
				} else {
					//right Itr is equal or greater
					if(headSubList == null) {
						headSubList = leftItr;
						sortedListItr = leftItr;
					} else {
						sortedListItr.next = leftItr;
						sortedListItr = leftItr;
					}
					if(leftItr == end1) {
						isLeftOver = true;
					} else {
						leftItr = leftItr.next;
					}
				}
			}
			
		}
		sortedListItr.next = null;
		return new Node [] {headSubList,sortedListItr};
	}
	protected Node<K> getNodeInMiddle(Node<K> start,Node<K> end) {
		Node<K> singleItr = start;
		Node<K> doubleItr = start;
		
		while(doubleItr!= end && doubleItr.next != end) {
			singleItr = singleItr.next;
			doubleItr = doubleItr.next.next;
		}
		
		return singleItr;
	}
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
