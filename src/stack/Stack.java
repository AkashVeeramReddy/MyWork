package stack;

import utils.MyUtilities;

public class Stack<K> implements IStack<K>{
	
	private StackNode<K> start;
	
	public Stack(){
	}
	
	public boolean push(K ele) {
		StackNode<K> newData = new StackNode<K>();
		newData.data = ele;
		newData.next = start;
		start = newData;
		return true;
	}
	
	public K pop(){
		if(start != null) {
			K data = start.data;
			start = start.next;
			return data;
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		StackNode<K> itr = start;
		while(itr!=null){
			builder.append(itr.data);
			builder.append(",");
			itr = itr.next;
		}
		MyUtilities.removeCommaAtEndOfBuilder(builder);
		return builder.toString();
	}
	
	public static void main(String[] args) {
		IStack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.pop();
		System.out.println();
	}

	@Override
	public boolean canPop() {
		return start != null;
	}
}
