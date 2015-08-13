package online.hackerrank.list.linked.single;
/**
 * All code for online single linked list programs are done first in {@link SingleIntLinkedList}
 * and then should be moved here
 * @author user
 *
 */
public class ExtSingleLinkedList extends SingleIntLinkedList {
	/**
	 * /MyWork/src/online/hackerrank/list/linked/single/doc/Compare 2 Linklist.odt
	 * Compare the two linked lists and return 1 if the lists are equal. Otherwise, return 0
	 * @param list2
	 * @return
	 */
	public int compareTwoLinkedList(SingleIntLinkedList list2) {
		return CompareLists(head,list2.head);
	}
	
	public int CompareLists(Node headA, Node headB) {
		if(headA == null && headB == null) {
			return 1;
		} else if(headA !=null && headB != null) {
			return (headA.data == headB.data) ? CompareLists(headA.next, headB.next):0;
		}
		return 0;
	}
}
