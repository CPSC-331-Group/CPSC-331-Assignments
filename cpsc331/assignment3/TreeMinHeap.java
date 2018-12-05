/*CPSC assignment 3
Umair Hassan 30047693
Betty Zhang 30040611
William Chan 30041834
*/

package cpsc331.assignment3;

import cpsc331.collections.MinHeap;
import java.util.NoSuchElementException;

/**
*
* Provides a Tree-Based Implementation of an Unbounded MinHeap<br><br>
*
* TreeMinHeap Invariant: A finite multiset of non-values of ordered type T is
* stored in a binary MinHeap, using a tree-based representation.
* <br><br>
*
*/

/**
*
* Provides a Tree-Based Implementation of an Unbounded MinHeap<br><br>
*
* TreeMinHeap Invariant: A finite multiset of non-values of ordered type T is
* stored in a binary MinHeap, using a tree-based representation.
* <br><br>
*
*/

public class TreeMinHeap<T extends Comparable<T>> implements MinHeap<T> {

  //
  // Implements a Node in a Binary MinHeap
  //
  // TreeNode Invariant:
  // a) This node stores a non-null value from an ordered type T
  // b) parent, leftChild and rightChild are (possibly null) references to other
  //    TreeNodes; indx is the position where the data at this TreeNode would be
  //    stored in an ArrayList-based implementation of the same MinHeap

  class TreeNode {

    // Data Fields
    private T value;             // Value stored at this TreeNode
    private int index;           // Position of this node in an
                                 // array-based representation
    private TreeNode parent;     // The parent of this TreeNode
    private TreeNode leftChild;  // The left child of this TreeNode
    private TreeNode rightChild; // The right child of this TreeNode

    // Creates a new TreeNode with a given value, "inputValue" and a given
    // index, "inputIndex"
    //
    // Precondition:
    // a) A non-null value inputValue with type T and non-negative integer inputIndex
    //    are given as index.
    // Postcondition:
    // a) A TreeNode storing value "inputValue" and with index "inputIndex",
    //    for which parent, leftChild and rightChild are null, has been created.

    public TreeNode (T inputValue, int inputIndex) {

      value = inputValue;
      index = inputIndex;
      parent = null;
      leftChild = null;
      rightChild = null;

    }

    // Reports the index of this node.
    //
    // Precondition: The TreeNode Invariant is satisfied.
    // Postcondition: The index of this TreeNode is returned as output.

    public int getIndex() {
      return index;
    }

    // Reports the value stored at this TreeNode
    //
    // Precondition: The TreeNode Invariant is satisfied.
    // Postcondition: The value stored at this TreeNode is returned as output.

    public T getValue() {
      return value;
    }

    // Reports the parent of this TreeNode
    //
    // Precondition: The TreeNode Invariant is satisfied.
    // Postcondition: The parent of this TreeNode is returned as output.

    public TreeNode getParent () {
      return parent;
    }

    // Reports the left child of this TreeNode
    //
    // Precondition: The TreeNode Invariant is satisfied.
    // Postcondition: The left child of this TreeNode is returned as output.

    public TreeNode getLeft () {
      return leftChild;
    }

    // Reports the right child of this TreeNode
    //
    // Precondition: The TreeNode Invariant is satisfied.
    //

    public TreeNode getRight() {
      return rightChild;
    }

    // Sets the value stored at this TreeNode
    //
    // Precondition:
    // a) The TreeNode Invariant is satisfied.
    // b) A non-null value inputValue, with type T, is given as input.
    // Postcondition:
    // a) The value stored at this TreeNode is now the given inputValue.

    public void setValue(T inputValue) {
      value = inputValue;
    }

    // Sets the parent of this TreeNode
    //
    // Precondition:
    // a) The TreeNode Invariant is satisfied.
    // b) A TreeNeed, inputParent, is given as input.
    // Postcondition:
    // a) The parent of this TreeNode is now the given inputParent.

    public void setParent(TreeNode inputParent) {
      parent = inputParent;
    }

    // Sets the left child of this TreeNode
    //
    // Precondition:
    // a) The TreeNode Invariant is satisfied.
    // b) A TreeNode, inputLeft, is given as input.
    // Postcondition:
    // a) The left child of this TreeNode is now the given inputLeft

    public void setLeft(TreeNode inputLeft) {
      leftChild = inputLeft;
    }

    // Sets the right child of this TreeNode
    //
    // Precondition:
    // a) The TreeNode Invariant is satisfied.
    // b) A TreeNode, inputRight, is given as input.
    // Postcondition:
    // a) The right child of this TreeNode is now the given inputRight.

    public void setRight(TreeNode inputRight) {
      rightChild = inputRight;
    }

  }

  // TrreMinHeap Invariant:
  // a) root is the root of a binary tree representing a MinHeap, whose nodes store
  //    non=null values from the ordered type T. Thus this binary tree has the shape of
  //    a binary heap. The value at each node is greater than or equal to the value at
  //    its parent, if the parent exists, and is less than the values stored at its
  //    left and right children, if these exist.
  // b) heapSize is the current size of this binary heap.
  // c) latest is the existing node that was most recently added to this MinHeap.


  // Data Fields
  private int heapSize;     // The size of this MinHeap
  private TreeNode root;    // The root of the binary tree representing this MinHeap
  private TreeNode latest;  // The last remaining TreeNode added to this MaxHeap

  /**
  *
  * Creates an empty MinHeap.<br><br>
  *
  * Precondition: None<br>
  * Postcondition: The TreeMinHeap Invariant is satisfied; the MinHeap represneted
  *  is an empty heap, so that its heapSize is zero.
  *
  */

  public TreeMinHeap () {
    heapSize = 0;
    root = null;
    latest = null;

  }

  // Returns the existing node most recently added before "latest" or null, if
  // no such node exists.
  //
  // Precondition: The TreeMinHeap Invariant is satisfied, and the MinHeap represented
  //   is not empty.
  // Postcondition: The TreeNode added most recently before latest is returned
  //   as output.

  private TreeNode predecessor () {
		// 0. latest is root: return null
		// 0.5. latest.parent is root: return null
		// 1. latest is right: pred=latest.parent.getLeft()
		// 2. its a left child
		// a. its not edge: wierd while loop thing
		// b. its edge :while loop
		if (latest == root || latest == null) {     //If latest is the root or null, return null
			return null;
		}
		if (latest.getParent() == root) {           //If the parent of latest is root
			if(latest==latest.getParent().getLeft()) { //If latest is a left child, set the left child of root to null
        root.setLeft(null);
      }
			else {                                     //Else return the left child of root
				return root.getLeft();
			}
		}
		if (latest == latest.getParent().getRight()) {  //If latest is a right child, reutnr the left child of the parent of latest
			return latest.getParent().getLeft();
		}
		TreeNode x = latest.getParent();      //Create node x for parent of latest
		boolean right = false;                //Create boolean to check right child
		int count = 1;                      //Create int to track max iterations
		while (x != root) {                   //While x is not root, if x is a right child
			if (x == x.getParent().getRight()) {
				right = true;                     //Set right to true and break out of loop
				break;
			}
			count++;                           //Increment counter and set x to parent of x
			x = x.getParent();
		}
		if (right) {                          //If right is true
			x = x.getParent().getLeft();                   //Set x to left child of parent of x
			int j = 0;                         //Create int j for controlling iterations
			while (j < count) {              //While j is less than counter, set x to right child of x
				x = x.getRight();
				j++;                            //Increment j by 1
			}
		} else {                            //Else right is false, set j to 1
			int j = 1;
			while (j < count) {            //While j is less than counter, set x to right child and increment j
				x = x.getRight();
				j++;
			}
		}
		return x;                           //Return x
	}

  // Returns the node that should become the parent of the next node to be added.
  //
  // Precondition: The TreeMinHeap Invariant is satisfied, and the MinHeap represented
  //   is not empty.
  // Postcondition: The TreeNode that should be the parent of the next node to be
  //   added is returned.

  private TreeNode successorParent ()  {
		if (latest == null) {       //If the latest is null, then return null
			return null;
		}
		if (latest == root) {       //If the latest is the root, then return root
			return root;
		}
		if (latest == latest.getParent().getLeft()) {   //If the latest is a left child, return the parent of latest
			return latest.getParent();
		} else {                                      //Else create x to the parent of latest
			TreeNode x = latest.getParent();
			boolean left = false;                        //Create a boolean for left child to false
			int howmuch=0;								//howmuch is how far x was from root
			while (x != root) {                        //While x is not the root
				if (x == x.getParent().getLeft()) {       //If x is a left child, set left to true and break out of while loop
					left = true;
					break;
				}
				x = x.getParent();                        //Update x to the parent of x
				howmuch++;
			}
			if (left) {                                //If left is true, then return the right child of the parent of the parent of latest
				return latest.getParent().getParent().getRight();
			} else {
				int j =0;
				while (j<=howmuch) {			//goes down to left child depending on how far x was from root
					x = x.getLeft();
					j++;
				}
				return x;                                 //Return x
			}
		}
	}

  //
  // Implements the "bubbleUp" method needed to complete an insertion
  //
  // Precondition:
  // a) root if the root of a binary tree, with the shape of a binary heap,
  //    storing values from an ordered type T.
  // b) A non-null node x in this binary tree has been given as input.
  // c) For every node y in this binary tree, except for x, if y has a parent
  //    then the value stored at the parent is less than or equal to the value
  //    stored at y. If y has a grandparent then the value at the grandparent
  //    is less than or equal to the value stored at y, as well.
  // Postcondition:
  // a) The values stored at the nodes of this binary tree have been exchanged
  //    between nodes but otherwise unchanged - the same multiset is represented
  //    by the nodes in this binary tree.
  // b) This binary tree is now a representation of a binary MinHeap.
  //

  private void bubbleUp(TreeNode x) {
    //While x has a parent and the value at parent is greater than the value at x
		while (x.getParent() != null && x.getParent().getValue().compareTo(x.getValue()) > 0) {
			T temp = x.getValue();                   //Create temp to store x value and set value of x to value at parent
			x.setValue(x.getParent().getValue());
			x.getParent().setValue(temp);            //Set the value of the parent of x to temp
			x = x.getParent();                       //Update x to the parent of x
		}
	}

  //
  // Implements the "bubbleDown" method used to complete a deletion
  //
  // Precondition:
  // a) root is the root of a binary tree, with the shape of a binary heap,
  //    storing non-null values from an ordered type T.
  // b) A non-null node x in this binary tree, has been given as input.
  // c) For every node y in this binary tree except for x, if y has a child
  //    then the value stored at the child is greater than or equal to the value
  //    stored at y, and if y has a grandchild then the value stored at the
  //    grandchild is greater than or equal to the value stored at y, as well.
  // Postcondition:
  // a) The values stored at the nodes of this binary tree have been exchanged
  //    between nodes but otherwise unchanged - the same multiset is represented
  //    by the nodes in this binary tree.
  // b) This binary tree is now a representation of a binary MinHeap.

  private void bubbleDown(TreeNode x) {
    //While x has a left child and the index of the left child is less than or equal to heapsize
		while (x.getLeft() != null && x.getLeft().getIndex() <= heapSize) {
			boolean r = false;                     //Create boolean check for right child
			TreeNode small = x.getLeft();          //Set new tree node small to left child of x
      //If x has a right child and the left child is greater than the right child
			if (x.getRight() != null && x.getLeft().getValue().compareTo(x.getRight().getValue()) > 0) {
				small = x.getRight();               //Set small to the right child and r to true
				r = true;
			}
      //If the value at x is greater than the value at small
			if (x.getValue().compareTo(small.getValue()) > 0) {
				T temp = x.getValue();              //Create temp to store x value
				x.setValue(small.getValue());       //Set the value of x to the value at small
				if (r) {                            //If r is true, set the value of the right child of x to temp
					x.getRight().setValue(temp);
					x = x.getRight();                //Update x to the right child of x
				} else {                            //Else set the value of the left child of x to temp and update x to left child
					x.getLeft().setValue(temp);
					x = x.getLeft();
				}
			} else {       //If x is smaller than value at small, break out of while loop
        break;
			}
		}

	}

  // Implementation of the insert method provided by a MinHeap. The preconditions
  // and postcondition for this problem are the same, except that they now also
  // include the fact that the "TreeMinHeap Invariant" is satisfied when execution
  // of the algorithm begins and, again, when it ends.

  public void insert (T v) {
		if (heapSize == 0) {              //If heap size is 0, create tree node with value v at heapsize index
			TreeNode x = new TreeNode(v, heapSize);
			root = x;                      //Set root to x and latest to root and increment heapsize by 1
			latest = root;
			heapSize++;
		} else if (heapSize == 1) {       //If hea size is 1, create tree node with value v at heapsize index
			TreeNode x = new TreeNode(v, heapSize);
			latest = x;                    //Set latest to x and connect root to latest and latest to root
			root.setLeft(latest);
			latest.setParent(root);
			heapSize++;                    //Increment heap size by 1 and bubble up x to correct position
			bubbleUp(x);
			latest.setParent(root);        //Set the parent of latest to root
		} else {                          //Else increment heapsize and create tree node x with value v at heapsize index
			heapSize++;
			TreeNode x = new TreeNode(v, heapSize);
			x.setParent(successorParent());            //Set the parent of x to node returned by successorParent function
			boolean child = latest == latest.getParent().getLeft();    //Create boolean to check if latest is the left child
			latest = x;                                //Set latest to x and if latest is the left child
			if (child) {
				x.getParent().setRight(x);              //Set the right child of the parent of x to x
			} else {
				x.getParent().setLeft(x);               //Else set the left child of the parent of x to x
			}
			bubbleUp(x);                               //Bubble up x to correct position

		}
	}

  // Implementation of the deleteMin method provided by a MinHeap. The precondition
  // and postcondition for this problem are the same, except that they now also
  // include the fact that the "TreeMinHeap Invariant" is satisfied when execution
  // of the algorithm begins, and when it ends.

  public T deleteMin () throws NoSuchElementException {
		if (heapSize == 0) {          //If the heap is empty, throw NoSuchElementException
			throw new NoSuchElementException("Heap is empty. ");
		}
		T result = getRoot().getValue();    //Set result to the value at root
		if (heapSize == 1) {          //If the heap size is 1, set root and latest to null and heapsize to 0
			root = null;
			latest = null;
			heapSize = 0;
		} else if (heapSize == 2) {         //If the heap size is 2, set the root value to the latest value
			root.setValue(latest.getValue());
			latest = root;             //Set latest to root and change heapsize to 1
			heapSize = 1;
		} else {
			root.setValue(latest.getValue());  //Else set root value to value of latest and decrement heapsize by 1
			heapSize--;
			bubbleDown(root);          //Bubble down the root to correct position
			latest = predecessor();    //Set latest to node returned by predecessor function
		}
		return result;              //Return deleted value

	}

  public int getSize() {

    return heapSize;

  }

  // Used for Testing: Returns the root of the bree used to represent this
  // binary MinHeap

  TreeNode getRoot() {

    return root;

  }

  // Used for Testing: Returns the value of latest

  TreeNode getLatest() {

    return latest;

  }

}
