
/*** 
 * This is class implements a compressed trie that holds a set of strings.
 * MyCompressedTrie stores nodes using class TreeNodeWithData
 * 
 * Name:   
 * Student Number: 822728
 * Uottawa Email: jtsai033@uottawa.ca
 * 
 *
 */
public class MyCompressedTrie {
	
	private static final int DEFAULT_CAPACITY = 1000;
	
	private TreeNodeWithData root;
	
	private int numNodes;
	private StringBuilder sb;
	
	public MyCompressedTrie() { // simple constructor (empty trie consisting of root only)
		root = new TreeNodeWithData(null, null, null, true ,"");
		numNodes=1;
	}
	
	// to be implemented by you see handout Part 2A
	// Constructor that receives a regular trie and creates this
	// instance that is a compressed trie
	// 
	public MyCompressedTrie(MyTrie trie) { 
		this(); 
		sb = new StringBuilder(DEFAULT_CAPACITY);
		compress(root, trie.root());
		root.setIsUsed(false);

	}

	
	
	private void compress(TreeNodeWithData currNode, TreeNode trieNode){
		
		TreeNode left = trieNode.getLeftChild();
		TreeNode right = trieNode.getRightChild();
		//String val = currNode.getData();
		
		if (left != null && right != null){					// case 1, two children
			TreeNodeWithData newLeft = new TreeNodeWithData(currNode, null, null,
					left.getIsUsed(), "0");
			TreeNodeWithData newRight = new TreeNodeWithData(currNode, null, null,
					right.getIsUsed(), "1");
			currNode.setLeftChild(newLeft);
			currNode.setRightChild(newRight);
			numNodes+= 2;
			currNode.setData(sb.toString());
			sb.setLength(0);

			sb.append("0");
			compress(newLeft, left);
			sb.setLength(0);
			sb.append("1");
			compress(newRight, right);
			
		} else if (left != null && right == null){			// case 2, single left child
			
			if(currNode.getIsUsed()){
				TreeNodeWithData temp = new TreeNodeWithData(currNode, null, null, left.getIsUsed(), "0");
				sb.append("0");
				numNodes++;
				currNode.setLeftChild(temp);
				compress(temp, left);
			}
			else{
				sb.append("0");
				if(left.getIsUsed()){
					currNode.setIsUsed(true);
					currNode.setData(sb.toString());
					sb.setLength(0);
				}
				compress(currNode, left);
			}
			
		} else if (left == null && right != null){			// case 3, single Right child
			
			if(currNode.getIsUsed()){
				TreeNodeWithData temp = new TreeNodeWithData(currNode, null, null, right.getIsUsed(), "1");
				sb.append("1");
				numNodes++;
				currNode.setRightChild(temp);
				compress(temp, right);
			}
			else{
				sb.append("1");
				if(right.getIsUsed()){
					currNode.setIsUsed(true);
					currNode.setData(sb.toString());
					sb.setLength(0);
				}
				compress(currNode, right);
			}
		} else{
			return;
		}
	}
	

	
	
	// Method to be implemented by you. See handout part 2A	
	public void printStringsInLexicoOrder() {

		printStringRecursive(root, new String(""));
	}

	
	
	private void printStringRecursive(TreeNodeWithData curr, String val){
		if(curr != null){
			if(curr.getIsUsed()){
				System.out.println(val + curr.getData());
			}
			
			printStringRecursive((TreeNodeWithData)curr.getLeftChild(), val + curr.getData());
			printStringRecursive((TreeNodeWithData)curr.getRightChild(), val + curr.getData());
		}
	}
	
	
	
	// the following method that calls its recursive counterpart
	// prints the tree and its data values at nodes in 
	// in-order traversal order
	
	public void printInOrder() { // not to be changed
		printInOrder(root);
	}
	
	private void printInOrder(TreeNode N) { // not to be changed
		System.out.print("(");
		if (N!=null) {
			printInOrder(N.getLeftChild());
			System.out.print(((TreeNodeWithData)N).getData());
			printInOrder(N.getRightChild());

		}
		System.out.print(")");
	}
	

	
	public int numNodes() {
		return numNodes;	
	}
	

}
