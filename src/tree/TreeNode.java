package tree;

public class TreeNode<K> {

	K data;
	TreeNode<K> left;
	TreeNode<K> right;
	
	TreeNode(K data,TreeNode<K> left,TreeNode<K> right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		if(this == null)
			return "null";
		StringBuilder builder = new StringBuilder();
		builder.append("data:");
		builder.append(data);
		builder.append(",");
		builder.append("left:");
		if(left == null)
			builder.append("null");
		else {
			builder.append(left.data);
		}
		builder.append(",");
		builder.append("right:");
		if(right == null)
			builder.append("null");
		else {
			builder.append(right.data);
		}
		return builder.toString();
	}
	
	public boolean isLeaf() {
		return (left == null) && (right == null);
	}
}
