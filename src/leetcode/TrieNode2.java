package leetcode;

public class TrieNode2 {
	
	public char data = 0;
	public boolean endOfStr = false;
	
	public int noOfChildren;
	public TrieNode2 [] children;
	
	public TrieNode2() {
		this(26);
	}
	
	public TrieNode2(int noOfChildren) {
		this.noOfChildren = noOfChildren;
		children = new TrieNode2[noOfChildren];
	}
	
	public TrieNode2 getChildrenAt(int idx) {
		return children[idx];
	}
	
	public void setChildrenAt(TrieNode2 child,int idx) {
		children[idx] = child;
	}
	
	public TrieNode2 getChildrenByChar(char childChar) {
		return getChildrenAt(getIndexOfCharacter(childChar));
	}
	
	public void setChildrenByChar(TrieNode2 child,char childChar) {
		setChildrenAt(child,getIndexOfCharacter(childChar));
	}
	
	/**
	 * subclasses need to override. default is handing small letters
	 * @param childChar
	 * @return
	 */
	public int getIndexOfCharacter(char childChar) {
		return childChar - 'a';
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(data);
		builder.append(',');
		builder.append("endOfStr:");
		builder.append(endOfStr);
		builder.append(',');
			//append child
		builder.append('{');
		for (int i = 0; i < children.length; i++) {
			TrieNode2 child = children[i];
			if(child != null) {
				builder.append(child.data);
				builder.append(":");
				builder.append(i);
				builder.append(',');
			}
		}
		builder.append('}');
		
		return builder.toString();
	}
}
