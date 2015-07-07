package tree.string;

public class TrieNode {
	
	public char data = 0;
	public boolean isLeaf = false;
	
	public int noOfChildren;
	public TrieNode [] children;
	
	public TrieNode() {
		this(26);
	}
	
	public TrieNode(int noOfChildren) {
		this.noOfChildren = noOfChildren;
		children = new TrieNode[noOfChildren];
	}
	
	public TrieNode getChildrenAt(int idx) {
		return children[idx];
	}
	
	public void setChildrenAt(TrieNode child,int idx) {
		children[idx] = child;
	}
	
	public TrieNode getChildrenByChar(char childChar) {
		return getChildrenAt(getIndexOfCharacter(childChar));
	}
	
	public void setChildrenByChar(TrieNode child,char childChar) {
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
		if(!isLeaf) {
			//append child
			builder.append('{');
			for (int i = 0; i < children.length; i++) {
				TrieNode child = children[i];
				if(child != null) {
					builder.append(child.data);
					builder.append(":");
					builder.append(i);
					builder.append(',');
				}
			}
			builder.append('}');
		}
		
		return data + "";
	}
}
