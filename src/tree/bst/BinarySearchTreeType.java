package tree.bst;

public enum BinarySearchTreeType {
	/**
	 * Inorder taversal will be in ascending order
	 */
	INORDER_ASCENDING(-1,1),
	/**
	 * Inorder taversal will be in descending order
	 */
	INORDER_DESCENDING(1,-1);
	
	private int comparisonValueToGoToLeftPart;
	private int comparisonValueToGoToRightPart;
	
	BinarySearchTreeType(int comparisonValueToGoToLeftPart,int comparisonValueToGoToRightPart) {
		this.comparisonValueToGoToLeftPart = comparisonValueToGoToLeftPart;
		this.comparisonValueToGoToRightPart = comparisonValueToGoToRightPart;
	}

	/**
	 * @return the comparisonValueToGoToLeftPart
	 */
	public int getComparisonValueToGoToLeftPart() {
		return comparisonValueToGoToLeftPart;
	}


	/**
	 * @return the comparisonValueToGoToRightPart
	 */
	public int getComparisonValueToGoToRightPart() {
		return comparisonValueToGoToRightPart;
	}
	
}
