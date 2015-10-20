package leetcode;

/**
 * https://leetcode.com/problems/reorder-list/
 * 
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to:
 * L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example, Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * 
 * @author user
 * 
 */
public class ReorderList {
	public void reorderList(ListNode head) {
        if(head == null || head.next == null)
            return;
        ListNode middle = getNodeInMiddle(head);
        System.out.println("Middle data:"+middle.val);
        ListNode fromReverse = middle.next;
        System.out.println("fromReverse data:"+fromReverse.val);
        ListNode newHeadSecondPart = reverse(fromReverse);
         System.out.println("newHeadSecondPart data:"+newHeadSecondPart.val);
        ListNode itr1 = head;
        ListNode itr2 = newHeadSecondPart;
        ListNode prevItr1 = null;
        ListNode prevItr2 = null;
        
        while(itr2 != null) {
            System.out.println("=================");
            System.out.println("");
            System.out.println("itr1 data:"+itr1.val);
            System.out.println("itr2 data:"+itr2.val);
            System.out.println("");
            System.out.println("=================");
            
            prevItr1 = itr1;
            if(prevItr2 != null)
                prevItr2.next = prevItr1;
                
            prevItr2 = itr2;
            itr1 = itr1.next;
            prevItr1.next = prevItr2;
            
            itr2 = itr2.next;
            //itr2 = itr2.next;
            //itr1 = itr1.next;
            
            
        }
        if(itr1 == null) {
            //even length list
            prevItr2.next = null;
        } else {
            prevItr2.next = itr1;
            itr1.next = null;
        }
    }
    
    public static ListNode getNodeInMiddle(ListNode ptr) {
        if(ptr == null || ptr.next == null)
			return ptr;
		ListNode itrNode = ptr;
		ListNode doubleItrNode = ptr.next;
		ListNode prevItrNode = null;
		while(doubleItrNode !=null && doubleItrNode.next != null) {
			prevItrNode = itrNode;
			itrNode = itrNode.next;
			doubleItrNode = doubleItrNode.next.next;
		}
		return itrNode;
    }
    
    public static ListNode reverse(ListNode node) {
		if(node == null || node.next == null) {
			return node;
		}
		ListNode itr = node, itrNext;
		ListNode prevItr = null;
		
		ListNode headOfReverse = null;
		while(itr != null) {
		    itrNext = itr.next;
		    if(headOfReverse == null) {
		        headOfReverse = itr;
		        headOfReverse.next = null;
		    } else {
		        itr.next = headOfReverse;
		        headOfReverse = itr;
		    }
		    itr = itrNext;
		}
		return headOfReverse;
	}
}
