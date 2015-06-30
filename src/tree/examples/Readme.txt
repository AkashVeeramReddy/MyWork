Binary tree of integers

1) First nodes are represented starting with //nodes,preferably in level order
	//nodes
	NULL child nodes may be present in graph so that the image looks balanced.
	If a node's left child is null and right child is null, then the NULL nodes need not be
	present
	If a node's left child is null and right child is not null, then a dummynode
	called left3 [label="null"] where 3 is the node
	If a node's right child is null and left child is not null, then a dummynode
	called right3 [label="null"] where 3 is the node

2) Then edges are represented starting with //edges, preferably in level order. The edges can be 
	split into levels like //level 0 , //level 1
	
	the label of edges is either l or r signifying the child
	
	3 -> 4 [label="r"]
	3 -> left3 [label = "l"]
	
3) First node is root