package cpsc331.assignment2;

import java.util.NoSuchElementException;
import cpsc331.collections.Dictionary;

/**
*
* Provides an implementation of a Dictionary using an AVL tree.
*
*/

// AVLDictionary Invariant:
//
// This AVL tree has nodes storing the ordered pairs in the
// Dictionary being represented. Thus it is a binary search
// tree such that all nodes have balance factors -1, 0, and 1.
// Furthermore, if the AVL tree is not empty, then
// - all the nodes in the left subtree include keys that are less
//   than the key stored at the root,
// - all the nodes in the right subtree include keys that are
//   greater than the nodes at the root, and
// - the left and right subtrees are also AVLDictionary's, so
//   they also satisfy this invariant.

public class AVLDictionary<K extends Comparable<K>, V>
                            implements Dictionary<K, V> {

  // Provides a node in this AVL tree

  class AVLNode {

    // Data Fields

    private K key;      // The key stored at the this node
    private V value;    // The value stored at this node
    private int height; // The height of the subtree with this node
                        // as root
    private AVLNode left;   // The left child of this node
    private AVLNode right;  // The right child of this node
    private AVLNode parent; // The parent of this node

    // Constructor; constructs an AVLNode with a given key and value
    // whose left and right node and parent are initially null

    AVLNode(K k, V v) {

      key = k;
      value = v;
      height = 0;
      left = null;
      right = null;
      parent = null;

    }

    // Returns the key stored at this node

    K key() {
      return this.key;
    }

    // Returns the value stored at this node

    V value() {
      return this.value;
    }

    // Returns the height of this node

    int height() {
      return this.height;
    }

    // Returns the left child of this node

    AVLNode left() {
      return this.left;
    }

    // Returns the right child of this node

    AVLNode right() {
      return this.right;
    }

    AVLNode parent() {
      return this.parent;
    }

    // Returns the balance factor of each node.

    int balanceFactor() {

      int leftHeight;  // Will be the height of the left child
      int rightHeight; // Will be the height of the right child

      if (this.left == null) {
        leftHeight = -1;
      } else {
        leftHeight = (this.left).height;
      };

      if (this.right == null) {
        rightHeight = -1;
      } else {
        rightHeight = (this.right).height;
      };
	
      return leftHeight - rightHeight;

    }

  }

  // Data Fields

  private AVLNode root;

  /**
  *
  * Constructs an empty AVLDictionary<br><br>
  *
  * Precondition: None<br>
  * Postcondition: An emptyAVLDictioary (satisfying the above
  *                AVLDictionary Invariant) has been created.
  *
  */

  public AVLDictionary() {
    root = null;
  }

  // Returns a reference to the root of this AVLDictionary

  AVLNode root() {
    return this.root;
  }

  // Implements the "get" method provided by Dictionary

  public V get (K key) throws NoSuchElementException {

    return search(key, root);

  }

  // Implements the required "search" method; to be supplied by
  // students

  private V search (K key, AVLNode x) throws NoSuchElementException {
    if (x == null) {
      throw new NoSuchElementException("No value associated with this key.");
    } else {
      int result = key.compareTo(x.key);
      if (result < 0) {
        return search(key, x.left);
      } else if (result == 0) {
        return x.value;
      } else {
        return search(key, x.right);
      }
    }

  }

  // Implements a left rotation at an input node; to be supplied
  // by students

  private void rotateLeft (AVLNode x) {
	AVLNode p = x.parent();
	AVLNode z = x.right();
	if(p != null) {
		//change the children of the p to z 
		//have to check if x is right or left child or p
		//update children of p
		if(p.right() == x){
			p.right = z;
		}else if(p.left == x){
			p.left = z;
		}
		z.parent = p;
		
	}
	
	AVLNode zLChild = z.left();
	if(zLChild == null){
		//change x's right child to null
		x.right = null;
	}else{
		//change x's right child to zLChild
		//change zLChild's parent to x	
		x.right = zLChild;
		zLChild.parent = x;
	}
	//change z's left child to x
	z.left = x;

  }

  // Implements a right rotation at an input node; to be supplied
  // by students

  private void rotateRight (AVLNode x) {
	AVLNode p = x.parent();
	AVLNode z = x.left();
	if(p != null){
		//change the children of the p to z
		//have to check if x is right or left child or p
		if(p.left() == x){
			p.left = z;
		}else if(p.right() == x){
			p.right = z;
			
		}
		z.parent = p;
	}
	
	AVLNode zRChild = z.right();
	if(zRChild == null){
		x.left = null;
		
	}else{
		x.left = zRChild;
		zRChild.parent = x;
	}
	z.right = x;
	
  }

  // Implements the "set" method supplied by Dictionary

  public void set(K k, V v) {

    if (root == null) {

      root = new AVLNode(k, v);

    } else {

      change(k, v, root);

    }

  }

  // Implements the required "change" method; to be supplied
  // by students

  private void change (K k, V v, AVLNode x) {
	int result = k.compareTo(x.key);
	if (result < 0 ){
		if(x.left == null){
			AVLNode newNode = new AVLNode(k, v);
			x.left = newNode;
			newNode.parent = x;
			updateHeight(x);
			adjustAVLBalance(x);
		}else{
			change(k,v,x.left());
		}
	}else if(result == 0){
		x.value = v;
	}else{
		//result > 0
		if (x.right == null) {      
			AVLNode newNode = new AVLNode(k, v);
			x.right = newNode;
			newNode.parent = x;
			updateHeight(x);
			adjustAVLBalance(x);
		}else{
			change(k, v, x.right);
		}
	}
  }
  
  private void adjustAVLBalance(AVLNode x){
	AVLNode problemNode = checkForProblem(x);
	if(problemNode.balanceFactor() == 2){
		AVLNode xLeft = x.left();
		if (xLeft.balanceFactor() == 1){
			rotateRight(problemNode);	
		}else if(xLeft.balanceFactor() == -1){
			rotateLeft(xLeft);
			rotateRight(x);
		}
	} else if (problemNode.balanceFactor() == -2){
		AVLNode xRight = x.right();
		if (xRight.balanceFactor() == 1){
			rotateRight(xRight);
			rotateLeft(x);
		} else if (xRight.balanceFactor() == -1){
			rotateLeft(x);
		}
		
	}
	  
  }
  //students
  private AVLNode checkForProblem(AVLNode x){
	while(x.parent() != null){
		if(x.balanceFactor() < -1 || x.balanceFactor() > 1){
			return x;
		}
		x = x.parent();
	}
	return null;
  }

  //students
  private void updateHeight(AVLNode x){
	while(x.parent != null){
		if((x.parent).height() < x.height || (x.parent).height() == x.height){
			(x.parent).height = x.height + 1;
			x = x.parent();
		}else{
			break;
		}
	}
  }
  
  
  // Implements the "remove" method supplied by Dictionary

  public V remove (K k) throws NoSuchElementException {

    return deleteFromSubtree(k, root);

  }

  // Implements the required "deleteFromSubtree" method; to be
  // supplied by students

  private V deleteFromSubtree(K k, AVLNode x)
    throws NoSuchElementException {

    return null;          // This line must be replaced.

  }

  // Implements the required "deleteNode" method; to be supplied
  // by students

  private void deleteNode (AVLNode x) {

  }

  // Implements the required "successor" method; to be supplied
  // by students

  private AVLNode successor (AVLNode x) {

    return null;      // This line must be replaced.

  }

}