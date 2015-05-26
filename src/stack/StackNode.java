package stack;

class StackNode<K> {
	
	K data;
	StackNode<K> next;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("data:");
		builder.append(data);
		builder.append(",");
		builder.append("next:");
		if(next==null){
			builder.append("null");
		} else {
			builder.append(next.data);
		}
		return builder.toString();
	}
}
