package tree.fns;

import tree.TreeNode;
/**
 * 
 * @author nithin
 *
 * @param <I>
 * @param <T>
 */
public interface IMakeNode<K,T extends TreeNode<K>> {

	public T makeNode(K element);
}
