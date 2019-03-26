/*** 
 * This is class implements a trie that holds a set of strings.
 * MyTrie stores stores nodes using class TreeNode
 * 
 * Name:   
 * Student Number: 822728
 * Uottawa Email: jtsai033@uottawa.ca
 * 
 *
 */

public class MyTrie {
	
	private TreeNode root = null;

	private int numNodes;

    /************************************************************
     * 
     * Constructor
     * 
     ***********************************************************/
	public MyTrie() {
		root = new TreeNode(null, null, null,false); 
		numNodes=1;
	}

	
	
	/************************************************************
     * 
     * Functions implemented by Student
     * 
     ***********************************************************/
	
	
	
	/**
	 * Method that inserts a string into the Trie, creating nodes
	 * to accommodate the string if need be and sets isUsed node corresponding
	 * to the string to true
	 * @param s
	 * 		String to be inserted
	 * @return
	 * 		True if s is a valid value.
	 * 		False if s is already entered into trie
	 */
	public boolean insert(String s) {
		
		
		TreeNode temp = fetchNode(s);	
		if(temp.getIsUsed()){
			return false;
		}
		// Loop that traverses Trie searching for TreeNode
		// that corresponds to the value of string s.
		// Creates TreeNodes to accommodate s if they do not exist in Trie
		
		temp.setIsUsed(true);							// Set isUsed to true
		return true;
	}
	
	
	
	private TreeNode fetchNode(String s){
		
		TreeNode temp = root;
		
		for (int i = 0; i < s.length();i++){
			if(s.charAt(i) == '0'){
				if (temp.getLeftChild() == null){								// No preexisting leftChild
					temp.setLeftChild(new TreeNode(temp, null, null, false));	// Create new external leftChild node
					numNodes++;
				}
				temp = temp.getLeftChild();										// Move down to node corresponding to s.charAt(i)
			}
			if (s.charAt(i) == '1'){
				if (temp.getRightChild() == null){								// No preexisting rightChild
					temp.setRightChild(new TreeNode(temp, null, null, false));	// Create new external leftChild node
					numNodes++;	
				}
				temp = temp.getRightChild();										// Move down to node corresponding to s.charAt(i)
			}
			
		}
		return temp;
	}
		
	
	
	
	/**
	 * Method that returns verifies if value of a string is in
	 * Trie
	 * @param s
	 * 		String method searches for
	 * @return
	 * 		True if string is in Trie
	 * 		False if string is not in Trie or invalid value for string s
	 */
	public boolean search(String s) {
		
		TreeNode temp = root;
		
		// Loop that traverses Trie searching for TreeNode corresponding
		// to value of s, return false if invalid value or no preexisting
		// TreeNode to accommodate s
		for (int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '0'){
				if (temp.getLeftChild() == null){								// No preexisting TreeNode indicates
																				// no value of length longer than current node in Trie
					return false;
				}
				temp = temp.getLeftChild();										// Move down to node corresponding to s.charAt(i)
			}
			else if (s.charAt(i) == '1'){
				if (temp.getRightChild() == null){								// No preexisting TreeNode indicates
																				// no value of length than current node in Trie
					return false;
				}
				temp = temp.getRightChild();										// Move down to node corresponding to s.charAt(i)
			}
			else{					// If s.charAt(i) is not '0' or '1' (invalid value)
				return false;		// Break function, return false
			}
		}
		
		return temp.getIsUsed();
	    
	}

	

	/**
	 * 
	 */
	public void printStringsInLexicoOrder() {
		
		printStringRecursive(root, new String(""));
		
	}
	
	private void printStringRecursive(TreeNode curr,String s){
		if(curr != null){
			if (curr.getIsUsed()){
				System.out.println(s);
			}
			printStringRecursive(curr.getLeftChild(), s + "0");
			printStringRecursive(curr.getRightChild(), s + "1");
		}
	}
	
	/************************************************************
     * 
     * Methods not changed
     * 
     ***********************************************************/
	
	
	
	// the following method that calls its recursive counterpart
	// prints the tree and its boolean values at nodes in 
	// in-order traversal order
	
	public void printInOrder() { // not to be changed
		printInOrder(root);
	}
	private void printInOrder(TreeNode N) { // not to be changed
		System.out.print("(");
		if (N!=null) {
			printInOrder(N.getLeftChild());
			System.out.print(N.getIsUsed());
			printInOrder(N.getRightChild());

		}
		System.out.print(")");
	}
	

	
	public TreeNode root() { // not to be changed
		return root;
	}
	
	public int numNodes() { // not to be changed
		return numNodes;
	}


}
