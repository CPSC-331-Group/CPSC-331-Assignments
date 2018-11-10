/*CPSC assignment 2
Umair Hassan 30047693
Betty Zhang 30040611
William Chan 30041834
*/

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

  /*
	reference: the search method from the BSTDictionary.java
	supplied in the lecture
	return the values associated with the input key, return
	NoSuchElementException if no such value exist
	precondition:
		a) AVLDictionary invariant satisfied
		b) a key with type K and AVLNode x are given as input
	postcondition:
		a) AVLDictionary invariant satisfied
		b) AVLDictionary not changed
		c) return the value of the node with key k if a node whose key
		the k is included in the subtree with root x, or else throw
		a NoSuchElementException.
	Bound Function: Depth of subtree with root x + 1
  */

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

/*
    The rotate left function implemented by student
	precondition:
		a) a non-null node x is given as input
		b) the right child of x is also non-null
	postcondition:
		a) a left rotation is performed on the tree on the node x
  */
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

	}else{
		z.parent = null;
		root = z;
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
	x.parent = z;

  }

  /*
    The rotate right function implemented by student
	precondition:
		a) a non-null node x is given as input
		b) the left child of x is also non-null
	postcondition:
		a) a right rotation is perfomred on the tree on the node x
  */
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
	}else{
		z.parent = null;
		root = z;
	}

	AVLNode zRChild = z.right();
	if(zRChild == null){
		x.left = null;

	}else{
		x.left = zRChild;
		zRChild.parent = x;
	}
	z.right = x;
	x.parent = z;

  }

  // Implements the "set" method supplied by Dictionary

  public void set(K k, V v) {

    if (root == null) {

      root = new AVLNode(k, v);

    } else {

      change(k, v, root);

    }

  }
  /*reference: modified from the change method from BSTDictionary.java
  provided in class lectures
  Sets the value associated with the input key, k, to be the
  input value, v - assuming that the node storing these values
  should belong to the subtree whose root is the input node x
  Precondition:
	a) The AVLDictionary Invariant is satisfied.
	b) A key k with type K, and value v with type V, and non-null
     node x in this BSTDictionary have been given as input.
	c) The AVLDictionary invariant would still be satisfied if a
		node storing (k, v) was included in the subtree with root x
		- either replacing an existing node with key k if such a
		node exists, or added as a new node otherwise.
  Postcondition:
	a) The AVLDictionary Invariant is satisfied.
	b) A node storing key k and value v has been included in the
		subtree with root x - either replacing an existing node
		with key k if one existed, or added as a new node otherwise.

  Bound Function for This Algorithm: Depth of subtree with root x
  */
  private void change (K k, V v, AVLNode x) {
	int result = k.compareTo(x.key);
	if (result < 0 ){
		if(x.left == null){
			AVLNode newNode = new AVLNode(k, v);
			x.left = newNode;
			newNode.parent = x;
			//use the following method to ensure loop invariants
			//are still satisfied
			insertionAdjust(newNode);
			updateHeight(root);

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
			//use the following method to ensure loop invariants
			//are still satisfied
			insertionAdjust(newNode);
			updateHeight(root);

		}else{
			change(k, v, x.right);
		}
	}
  }


   /* method created by student to adjust the AVLTree
  through rotations to ensure AVL invariants are satisfied after insertion
  precondition:
	a) the inserted node x is given as input
  postcondition:
	a) AVLDictionary invariants are satisfied
  */
  private void insertionAdjust(AVLNode x){
	updateHeight(x);
	//loop invariant
	// a) x is a non-null node that lies on the path of
	//    the deleted node form the deleteNode method
	// b) loop invariant is satisfied for the subtree of the node x
	// Bound function: depth of the root of the AVLtree - depth of the x
	while(x != null){
		if(x.balanceFactor() == 2){
		//case where the problem node has a balance factor of 2
		//corresponding adjustments are described in the assignment's instruction
		//and the written portion of the assignment
			AVLNode xLeft = x.left();
			if (xLeft.balanceFactor() == 1){
				rotateRight(x);
			}else if(xLeft.balanceFactor() == -1){
				rotateLeft(xLeft);
				rotateRight(x);
			}
			//once the deepest problem node is adjusted
			//the rest of the tree should also balance, so there is
			//no need to iterate the rest of the nodes
			break;
		}else if (x.balanceFactor() == -2){
		//case where the problem node has a balance factor of -2
		///corresponding adjustments are described in the assignment's instruction
		//and the written portion of the assignment
			AVLNode xRight = x.right();
			if (xRight.balanceFactor() == 1){
				rotateRight(xRight);
				rotateLeft(x);
			} else if (xRight.balanceFactor() == -1){
				rotateLeft(x);
			}
			//once the deepest problem node is adjusted
			//the rest of the tree should also balance, so there is
			//no need to iterate the rest of the nodes
			break;
		}
		x = x.parent;
	}
}


  /*reference: modified from correctHeight method from the
  AVLUtilities.java provided for this assignment
  correct and update the height of the nodes in the subtree where
  x is the root of the subtree
  precondition: a (possible null) node x from the AVL tree is given as input
  postcondition: a) the height of all nodes in the subtree where x is the
				root has been adjusted
				b) the structure of the tree is unchanged
  */
	private int updateHeight(AVLNode x) {
		if(x == null){
			//only reached if the initially given input x is null
			return -1;
		}

		int leftHeight = -1;
		if (x.left() != null) {
		  leftHeight = updateHeight(x.left());
		};

		int rightHeight = -1;
		if (x.right() != null) {
		  rightHeight = updateHeight(x.right());
		};
		x.height = Math.max(leftHeight, rightHeight) + 1;
		return x.height;

	}

  // Implements the "remove" method supplied by Dictionary
  public V remove (K k) throws NoSuchElementException {
	return deleteFromSubtree(k, root);

  }

  //debugging function to display properties of a given node
  //to be deleted
  private void debugPrint(AVLNode x){
	 if(x != null){
		System.out.print("node: " + x.key() + " " );
		if(x.parent() != null){
		System.out.print("parent: " + x.parent.key() + " " );
		 }
		 if(x.left() != null){
			System.out.print("left: " + x.left.key() + " " );
		 }
		 if(x.right() != null){
			System.out.print("right: " + x.right.key() + " " );
		 }
	 }else{
		 System.out.println("node is null");
	 }
	 System.out.println("");

  }



  /*reference: the deleteFromSubtree method from the BSTDictionary file
  provided in lecture notes
  set the value associate with the input k to undefined, throwing null
  pointer exception if the node associated with k does not exist
  precondition:
	a) The AVLDictionary invariant is satisfied
	b) a key k (type K) and a node x in the in AVL tree is given as input
	c) if x is null, then there is no node storing k in the tree, otherwise,
		if there is a node storing k then the node is in the subtree of x
  postcondition:
	a) the AVLDictionary invariant is satisfied
	b) f(k) is set to undefined if f(k) was defined before the deletion, or
		else a NoSuchElementException has been thrown
  Bond function: the death of the subtree with root x
  */
  private V deleteFromSubtree(K k, AVLNode x)
    throws NoSuchElementException {

    if (x == null) {
      throw new
      NoSuchElementException("No value is associated with this key.");

    } else {

      int result = k.compareTo(x.key);

      if (result < 0) {
        return deleteFromSubtree(k, x.left);

      } else if (result > 0) {
        return deleteFromSubtree(k, x.right);

      } else { // k is stored at x
		//debugPrint(x);
        V v = x.value;
        deleteNode(x);
        return v;

      }

    }

  }



  /* Reference: modified from the deleteNode method in
	BSTDictionary.java provided from the lectures
	remove a given node and updating the tree so that
	AVLDictionary invariant is stills satisfied
	precondition: a) the AVLDictionary invariant is satisfied
		b) a non-null x in the AVLDictionary is given as input
	postcondition:
		a) The AVLDictionary invariant is satisfied
		b) remove x from the binary search tree by setting
		f(k) to be undefined, where k is the key stored at x
  */

  private void deleteNode (AVLNode x) {
	if (x.left == null) {

     if (x.right == null) {

       if (x.parent == null) { //This was the only node

         root = null;

       } else {  // x should be removed as a child

         AVLNode parent = x.parent;
         int result = (x.key).compareTo(parent.key);
         if (result < 0) { // x is a left child
           parent.left = null;
         } else {  // x is a right child
           parent.right = null;
         }

		 //update the height of each nodes in the subtree of x's parent
		 updateHeight(parent);
		 // adjust the AVLTree (rotate) as needed to satisfy the invariant
		 deletionAdjust(parent);
       }

     } else {

       AVLNode rightChild = x.right;

       if (x.parent == null) {  // Right child should become root

         rightChild.parent = null;
         root = rightChild;

       } else {  // Right child should be promoted

         AVLNode parent = x.parent;
         int result = (x.key).compareTo(parent.key);
         if (result < 0) { // x is a left child
           parent.left = rightChild;
         } else { // x is a right child
           parent.right = rightChild;
         }
		 //update the height of nodes in the subtree of x's parent after deletion
		 updateHeight(parent);
         rightChild.parent = parent;

       }
	    //adjust/rotate the AVL tree in the path of the deleted node (where right child is promoted)
		deletionAdjust(rightChild);
     }

   } else if (x.right == null) {

     AVLNode leftChild = x.left;

     if (x.parent == null) {  // Left child should become root

       leftChild.parent = null;
       root = leftChild;

     } else { // Left child should be promoted

       AVLNode parent = x.parent;
       int result = (x.key).compareTo(parent.key);
       if (result < 0) { // x is a left child
         parent.left = leftChild;
       } else {  // x is a right child
         parent.right = leftChild;
       }
	   //update height of nodes of subtree of x's parent
	   updateHeight(parent);
       leftChild.parent = parent;

     }
	 //adjust the AVL tree to satisfy the loop invariant
	deletionAdjust(leftChild);

   } else {  // Successor of x should replace x

     AVLNode successor = successor(x);
     x.key = successor.key;
     x.value = successor.value;
     deleteNode(successor);
   }
  }

  /* reference: successor method from BSTDictionary.java
	provided by in the lectures
	reports the smallest node on the right subtree of the input node
	(given that the input node is not empty
	precondition:
		a) the AVLDictionary Invariant is satisfied
		b) a non-null node x, whose right subtree is not empty,
		is given as input
	postcondition:
		a) the AVLDictionary invariant is satisfied
		b) the AVLDictionary is not changed
		c) the node storing the smallest value of the right subtree
		of the given node is returned
  */

  private AVLNode successor (AVLNode x) {
	AVLNode y = x.right;

    // Loop Invariant:
    // a) The BSTDictionary Invariant is satisfied.
    // b) A non-null node x in this BSTDictionary, whose
    //    right subtree is not empty, has been given as input.
    // c) y is a node in the right subtree. If the left subtree
    //    of y is nonempty then the node the right subtree of x
    //    storing the smallest key is the node with smallest key
    //    in the left subtree of y. Otherwise y is this node.
    // Bound Function: Depth of subtree with root y.

    while (y.left != null) {
      y = y.left;
    }
    return y;

  }

   /* method created by student to adjust the AVLTree
  through rotations to ensure AVL invariants are satisfied
  precondition:
	a) x, the promoted node, that lies in the path of the node being
	 deleted is given as input
  postcondition:
	a) AVLDictionary invariants are satisfied
	b) the height of the nodes of the tree is adjusted
  */
  private void deletionAdjust(AVLNode x){

	//loop invariant
	// a) x is a non-null node that lies on the path of
	//    the deleted node form the deleteNode method
	// b) loop invariant is satisfied for the subtree of the node x
	// Bound function: depth of the root of the AVLtree - depth of the x
	while(x != null){

		if(x.balanceFactor() == 2){
		//case where the problem node has a balance factor of 2
		//corresponding adjustments are described in the assignment's instruction
		//and the written portion of the assignment
			AVLNode xLeft = x.left();
			if(xLeft.balanceFactor() == 1){
				rotateRight(x);
			}else if (xLeft.balanceFactor() == -1){
				rotateLeft(xLeft);
				rotateRight(x);
			}
		}else if(x.balanceFactor() == -2){
		//case where the problem node has a balance factor of -2
		//corresponding adjustments are described in the assignment's instruction
		//and the written portion of the assignment
			AVLNode xRight = x.right();
			if (xRight.balanceFactor() == 1){
				rotateRight(xRight);
				rotateLeft(x);
			} else if (xRight.balanceFactor() == -1){
				rotateLeft(x);
			}
		}
		updateHeight(x); //update the nodes in the subtree where x is the root
		x = x.parent();

	}

  }


}
