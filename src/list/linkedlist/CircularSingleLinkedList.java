package list.linkedlist;


/**
 * In circular linked list, last element points to the head
 * @author nithin
 *
 * @param <K>
 */
public class CircularSingleLinkedList<K> extends SingleLinkedList<K> {
	
	/**
	 * Adds at last
	 */
	public void add(K data) {
		Node<K> createNode = createNode();
		createNode.data = data;
		if(header == null) {
			header = createNode;
			header.next = header;
		} else {
			Node<K> itr = header;
			while(itr.next != header) {
				itr = itr.next;
			}
			itr.next = createNode;
			createNode.next = header;
		}
	}
	
	@Override
	public String toString() {
		if(header == null)
			return "";
		StringBuilder builder = new StringBuilder();
		Node<K> itrNode = header;
		builder.append(header.data);
		builder.append(",");
		while (itrNode.next != header) {
			builder.append(itrNode.next.data);
			builder.append(",");
			itrNode = itrNode.next;
		}
		return builder.toString();
		
	}
	/**
	 * 
	 * @return circular linked list containing right half,original list will contain left half.
	 */
	public CircularSingleLinkedList<K> splitIntoTwoHalves() {
		// find middle
		Node<K> beg2ndhalf = null;
		Node<K> end2ndhalf = null;
		
		Node<K> end1stHalf = null;
		Node<K> itrNode = header;
		Node<K> doubleItrNode = header;
		if(header == null)
			return null;
		
		while(doubleItrNode.next != header && doubleItrNode.next.next != header) {
			itrNode = itrNode.next;
			doubleItrNode = doubleItrNode.next.next;
		}
		//even elements
		if(doubleItrNode.next.next == header) {
			end2ndhalf = doubleItrNode.next;
			beg2ndhalf = itrNode.next;
			end1stHalf = itrNode;
		} else {
			end2ndhalf = doubleItrNode;
			beg2ndhalf = itrNode.next;
			end1stHalf = itrNode;
		}

		CircularSingleLinkedList<K> right = new CircularSingleLinkedList<K>();
		right.header = beg2ndhalf;
		end2ndhalf.next = beg2ndhalf;
		
		end1stHalf.next = header;
		return right;
	}
}	
