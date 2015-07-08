package tree.string;


public class TernarySearchTree {
	
	public TSTNode root;
	
	public TernarySearchTree() {
	}
	
	public boolean add(String str) {
		return add(str.toCharArray());
	}
	
	public boolean add(char[] charArray) {
		 TSTNode add = add(charArray, 0, root);
		 if(add == null) {
			 return false;
		 }
		 root = add;
		 return true;
	}
	
	/**
	 * 
	 * @param charArray
	 * @param fromIdx
	 * @param rootPtr
	 * @return null if string already present
	 */
	public TSTNode add(char[] charArray,int fromIdx,TSTNode rootPtr) {
		boolean isNodeCreated = false;
		if(rootPtr == null) {
			rootPtr = new TSTNode();
			rootPtr.data = charArray[fromIdx];
			isNodeCreated = true;
		}
		char rootData = rootPtr.data;
		char ch = charArray[fromIdx];
		
		TSTNode child = null;
		if(ch == rootData) {
			if(fromIdx == (charArray.length -1)) {
				//end of string reached
				if(!isNodeCreated)
					return null;
				else {
					rootPtr.isEndOfString = true;
					return rootPtr;
				}
			}
			child = rootPtr.equal;
			if(child == null) {
				rootPtr.equal = add(charArray, fromIdx+1, null);
			} else {
				TSTNode retNode = add(charArray, fromIdx+1, rootPtr.equal);
				if(retNode == null) {
					return null;
				}
				
			}
		} else if(ch < rootData) {
			child = rootPtr.left;
			if(child == null) {
				rootPtr.left = add(charArray, fromIdx, null);
			} else {
				TSTNode retNode = add(charArray, fromIdx, rootPtr.left);
				if(retNode == null) {
					return null;
				}
			}
		} else {
			//ch > rootData
			child = rootPtr.right;
			if(child == null) {
				rootPtr.right = add(charArray, fromIdx, null);
			} else {
				TSTNode retNode = add(charArray, fromIdx, rootPtr.right);
				if(retNode == null) {
					return null;
				}
			}
		}
		
		return rootPtr;
	}
	
	public boolean search(String str) {
		return search(str.toCharArray());
	}
	
	public boolean search(char[] charArray) {
		return search(charArray, 0, root);
	}
	
	/**
	 * 
	 * @param charArray
	 * @param fromIdx
	 * @param rootPtr
	 * @return null if string already present
	 */
	public boolean search(char[] charArray,int fromIdx,TSTNode rootPtr) {
		if(rootPtr == null) {
			return false;
		}
		char rootData = rootPtr.data;
		char ch = charArray[fromIdx];
		
		TSTNode child = null;
		if(ch == rootData) {
			if(fromIdx == (charArray.length -1)) {
				//end of string reached
				return rootPtr.isEndOfString;
			}
			child = rootPtr.equal;
			fromIdx++;
		} else if(ch < rootData) {
			child = rootPtr.left;
		} else {
			//ch > rootData
			child = rootPtr.right;
		}
		
		return search(charArray,fromIdx,child);
	}
}
