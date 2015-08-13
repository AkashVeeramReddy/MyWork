package online.hackerrank.tree.noparent;

import java.util.HashMap;
import java.util.Map;

public class ExtBinaryTreeNoParent extends BinaryTreeNoParent {
	public void printTopView() {
		printTopView(root);
	}
	
	public void printTopView(Node root) {
		if(root == null) {
			return;
		}
		Map<Integer, Node> widthVsNode = new HashMap<Integer, Node>();
		Map<Integer, Integer> widthVsHeight = new HashMap<Integer, Integer>();
		Info info = populateMaps(root, widthVsNode, widthVsHeight, 0, 0);
		
		for (int i = info.minLeftWidth; i <= info.maxRightWidth; i++) {
			Node node = widthVsNode.get(i);
			System.out.print(node.data);
			System.out.print(" ");
		}
		
	}
	
	public Info populateMaps(Node ptr,Map<Integer, Node> widthVsNode, Map<Integer, Integer> widthVsHeight,
			int width,int height) {
		if(ptr == null) {
			return new Info();
		} else {
			Node node = widthVsNode.get(width);
			if(node == null) {
				widthVsNode.put(width, ptr);
				widthVsHeight.put(width, height);
			} else {
				Integer existHeight = widthVsHeight.get(width);
				if(height < existHeight) {
					widthVsNode.put(width, ptr);
					widthVsHeight.put(width, height);
				}
				
			}
			Info left = populateMaps(ptr.left, widthVsNode, widthVsHeight, width-1, height+1);
			Info right = populateMaps(ptr.right, widthVsNode, widthVsHeight, width+1, height+1);
			
			//we will return left
			left.minLeftWidth = Math.min(width,Math.min(left.minLeftWidth, right.minLeftWidth));
			left.maxRightWidth = Math.max(width,Math.max(left.maxRightWidth, right.maxRightWidth));
			return left;
		}
	}
	
	static class Info {
		int minLeftWidth = +1;
		int maxRightWidth = -1;
	}
}
