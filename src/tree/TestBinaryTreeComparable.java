package tree;

import java.util.LinkedList;

import utils.CollectionUtils;

public class TestBinaryTreeComparable {
	public static void main(String[] args) {
		//BinaryTreeComparable<Integer> tree = new BinaryTreeComparable<Integer>();
		BinaryTreeComparable<Integer> tree = new BinaryTreeComparable<Integer>();
		LinkedList<Integer> list = new LinkedList<Integer>();
		CollectionUtils.addToCollection(list, 10,12,15,25,30,36);
		System.out.println(list);
		tree.populateCompleteTreeFromLinkedList(list);
		tree.showImage();
	}
}
