package tree.string;

public class TSTNode {
	
	public boolean isEndOfString = false;
	public char data = 0;
	
	public TSTNode left;
	public TSTNode equal;
	public TSTNode right;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(data);
		builder.append(',');
		
		builder.append("isEndOfString");
		builder.append(":");
		builder.append(isEndOfString);
		builder.append(',');
		
		builder.append("left");
		builder.append(":");
		if(left == null) {
			builder.append("null");
		} else {
			builder.append(left.data);
		}
		builder.append(',');
		
		builder.append("equal");
		builder.append(":");
		if(equal == null) {
			builder.append(String.valueOf("null"));
		} else {
			builder.append(equal.data);
		}
		builder.append(',');
		
		builder.append("right");
		builder.append(":");
		if(right == null) {
			builder.append(String.valueOf("null"));
		} else {
			builder.append(right.data);
		}
		return builder.toString();
	}
	
	
}
