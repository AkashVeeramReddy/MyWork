package list.linkedlist;

public class SortableStackAsSingleLinkList<K extends Comparable<? super K>>
			extends StackAsSingleLinkList<K> {
	
	public SortableStackAsSingleLinkList<K> createSortedStack() {
		SortableStackAsSingleLinkList<K> newStack = new SortableStackAsSingleLinkList<K>();
		while (this.canPop()) {
			K popedFromOrgStack = pop();
			//insert into correct position
			K topOfEleSortedStack = newStack.peek();
			int noOfElementsRemoved = 0;
			while(topOfEleSortedStack != null && (
					topOfEleSortedStack.compareTo(popedFromOrgStack) < 0)) {
				push(newStack.pop());
				noOfElementsRemoved++;
				topOfEleSortedStack = newStack.peek();
			}
			newStack.push(popedFromOrgStack);
			while(noOfElementsRemoved > 0) {
				newStack.push(pop());
				noOfElementsRemoved --;
			}
		}
		return newStack;
	}
	
}
