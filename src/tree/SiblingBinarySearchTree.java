package tree;

import list.IList;
import list.arraylist.MyArrayList;
import list.linkedlist.MyLinkedList;
import queue.IQueue;
import utils.RotationType;

public class SiblingBinarySearchTree<K extends Comparable<? super K>> extends BinarySearchTree<K> {
	
	protected SiblingTreeNode<K> makeNode(K element) {
		return new SiblingTreeNode<K>(element, null, null);
	}
	
	public void initializeSiblingPointers() {
		IQueue<SiblingNodeLevelWrapper<K>> queue = new MyLinkedList<SiblingNodeLevelWrapper<K>>();
		queue.enqueue(new SiblingNodeLevelWrapper<K>((SiblingTreeNode<K>) root, 0));
		SiblingNodeLevelWrapper<K> dequeuedEle = queue.dequeue();
		SiblingNodeLevelWrapper<K> prevDequeuedEle = null;
		while(dequeuedEle != null) {
			if(prevDequeuedEle != null) {
				if(prevDequeuedEle.level == dequeuedEle.level) {
					prevDequeuedEle.siblingTreeNode.sibling = 
						dequeuedEle.siblingTreeNode;
				}
			}
			if(dequeuedEle.siblingTreeNode.left != null) {
				queue.enqueue(new SiblingNodeLevelWrapper<K>((SiblingTreeNode<K>) dequeuedEle.siblingTreeNode.left, dequeuedEle.level + 1));
			}
			if(dequeuedEle.siblingTreeNode.right != null) {
				queue.enqueue(new SiblingNodeLevelWrapper<K>((SiblingTreeNode<K>) dequeuedEle.siblingTreeNode.right, dequeuedEle.level + 1));
			}
			prevDequeuedEle = dequeuedEle;
			dequeuedEle = queue.dequeue();
		}
	}
	
	public IList<K> getSpiralTraversal() {
		IList<K> list = new MyArrayList<K>();
		populateSpiralTraversalOrderFor(root,list);
		return list;
	}

	private void populateSpiralTraversalOrderFor(TreeNode<K> node,
			IList<K> list) {
		MyLinkedList<SiblingNodeLevelWrapper<K>> linkedList = new MyLinkedList<SiblingNodeLevelWrapper<K>>();
		linkedList.addFirst(new SiblingNodeLevelWrapper<K>((SiblingTreeNode<K>) root, 0));
		RotationType rotationType = RotationType.CLOCK_WISE;
		SiblingNodeLevelWrapper<K> removedElement = removeElementFor(rotationType, linkedList);
		int lastRemovedLevel = 0;
		while (removedElement != null) {
			int level = removedElement.getLevel();
			if(level != lastRemovedLevel) {
				addSiblingNodeLevelWrapperForRotationType(rotationType, linkedList, removedElement);
				rotationType = RotationType.getInverseRotationType(rotationType);
				removedElement= removeElementFor(rotationType, linkedList);
			}
			lastRemovedLevel = removedElement.getLevel();
			list.add(removedElement.siblingTreeNode.data);
			addChildrenFor(rotationType, linkedList, removedElement);
			removedElement= removeElementFor(rotationType, linkedList);
		}
	}
	
	public static <K> SiblingNodeLevelWrapper<K> removeElementFor(RotationType rotationType,MyLinkedList<SiblingNodeLevelWrapper<K>> linkedList) {
		SiblingNodeLevelWrapper<K> removedEle = null;
		switch (rotationType) {
		case ANTI_CLOCKWISE:
			removedEle = linkedList.removeFirst();
			break;
		case CLOCK_WISE:
			removedEle = linkedList.removeLast();
		default:
			break;
		}
		return removedEle;
	}
	
	public static <K> void addSiblingNodeLevelWrapperForRotationType(RotationType rotationType,
			MyLinkedList<SiblingNodeLevelWrapper<K>> linkedList,SiblingNodeLevelWrapper<K> node){
		switch (rotationType) {
		case ANTI_CLOCKWISE:
			linkedList.addFirst(node);
			break;
		case CLOCK_WISE:
			linkedList.addLast(node);
		default:
			break;
		}
	}
	
	public static <K> void addChildrenFor(RotationType rotationType,
			MyLinkedList<SiblingNodeLevelWrapper<K>> linkedList,SiblingNodeLevelWrapper<K> node) {
		switch (rotationType) {
		case ANTI_CLOCKWISE:
			if(node != null) {
				SiblingTreeNode<K> left = (SiblingTreeNode<K>) node.getSiblingTreeNode().left;
				SiblingTreeNode<K> right = (SiblingTreeNode<K>) node.getSiblingTreeNode().right;
				if(left != null) {
					linkedList.addLast(new SiblingNodeLevelWrapper<K>(right, node.getLevel() + 1));
				}
				if(right != null) {
					linkedList.addLast(new SiblingNodeLevelWrapper<K>(left, node.getLevel() + 1));
				}
			}
			break;
		case CLOCK_WISE:
			if(node != null) {
				SiblingTreeNode<K> left = (SiblingTreeNode<K>) node.getSiblingTreeNode().left;
				SiblingTreeNode<K> right = (SiblingTreeNode<K>) node.getSiblingTreeNode().right;
				if(left != null) {
					linkedList.addFirst(new SiblingNodeLevelWrapper<K>(left, node.getLevel() + 1));
				}
				if(right != null) {
					linkedList.addFirst(new SiblingNodeLevelWrapper<K>(right, node.getLevel() + 1));
				}
			}
		default:
			break;
		}
	}
	
	public static class SiblingNodeLevelWrapper<K> {
		public SiblingTreeNode<K> getSiblingTreeNode() {
			return siblingTreeNode;
		}

		public int getLevel() {
			return level;
		}

		public void setSiblingTreeNode(SiblingTreeNode<K> siblingTreeNode) {
			this.siblingTreeNode = siblingTreeNode;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		private SiblingTreeNode<K> siblingTreeNode;
		private int level;
		
		public SiblingNodeLevelWrapper(SiblingTreeNode<K> siblingTreeNode,int level) {
			this.siblingTreeNode = siblingTreeNode;
			this.level = level;
		}
		
		@Override
		public String toString() {
			return siblingTreeNode.toString();
		}
	}
	
	public static class SiblingTreeNode<K> extends TreeNode<K> {
		
		private SiblingTreeNode<K> sibling = null;
 		
		public SiblingTreeNode(K data, SiblingTreeNode<K> left, SiblingTreeNode<K> right) {
			super(data, left, right);
		}

		/**
		 * @param sibling the sibling to set
		 */
		public void setSibling(SiblingTreeNode<K> sibling) {
			this.sibling = sibling;
		}

		/**
		 * @return the sibling
		 */
		public SiblingTreeNode<K> getSibling() {
			return sibling;
		}
		
		@Override
		public String toString() {
			String string = super.toString();
			if(!"null".equals(string)) {
				StringBuilder builder = new StringBuilder(string);
				builder.append(",");
				builder.append("sibling:");
				if(sibling == null)
					builder.append("null");
				else {
					builder.append(sibling.data);
				}
				return builder.toString();
			}
			return string;
		}
		
	}
	
}

