package stack;

public interface IStack<K> {
	
	boolean push(K data);
	
	K pop();
	
	boolean canPop();
	
}
