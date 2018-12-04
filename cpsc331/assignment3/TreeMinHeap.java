/*CPSC assignment 3
Umair Hassan 30047693
Betty Zhang 30040611
William Chan 30041834
*/

package cpsc331.assignment3;

import cpsc331.collections.MinHeap;
import java.util.NoSuchElementException;

/**
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
    //0. latest is root: return null
	  //0.5. latest.parent is root: return null
	  //1. latest is right: pred=latest.parent.getLeft()
	  //2. its a left child
	  //a. its not edge: latest.parent.parent.left.right
	  //b. its edge	:while loop
	  if(latest==root||latest==null) {
		  return null;
	  }
	  if(latest.getParent()==root) {
		  return root;
	  }
	  if(latest==latest.getParent().getRight()) {
		  return latest.getParent().getLeft();
	  }
	  TreeNode x = latest.getParent();
	  boolean right=false;
	  int howmuch=1;
		 while(x!=root) {
			 if(x==null) {
		    	  System.out.println("break in while x is null");
		      }
			 if(x==x.getParent().getRight()) {
				 right=true;
				 break;
			 }
			 howmuch++;
			 x=x.getParent();
		  }
		 if(x==null) {
	    	  System.out.println("break after while x is null");
	      }
		 if(right) {
			// int j=0;
			// x=latest;
			//while(j<=howmuch) {
			//	x=x.getParent();
			//}
			x=x.getParent();
			x=x.getLeft();
			int j=0;
			while(j<howmuch) {
				x=x.getRight();
				j++;
			}
			if(x==null) {
		    	  System.out.println("break in return1 x is null");
		      }
			return x;
		 }else {
			 while(x.getRight()!=null) {
			 x=x.getRight();
			 }
			 if(x==null) {
		    	  System.out.println("break in return2 x is null");
		      }
			 return x;
  }
  }

  // Returns the node that should become the parent of the next node to be added.
  //
  // Precondition: The TreeMinHeap Invariant is satisfied, and the MinHeap represented
  //   is not empty.
  // Postcondition: The TreeNode that should be the parent of the next node to be
  //   added is returned.

  private TreeNode successorParent ()  {
	  if(latest==null) {
		  return null;
	  }
	  if(latest==root) {
		  return root;
	  }
	  if(latest==latest.getParent().getLeft()) {
		  return latest.getParent();
	  }else {
		 TreeNode x = latest.getParent();
		 boolean left=false;
		 while(x!=root) {
			 if(x==x.getParent().getLeft()) {
				 left=true;
				 break;
			 }
			 x=x.getParent();
		  }
		 if(left) {
			 return latest.getParent().getParent().getRight();
		 }else {
			 while(x.getLeft()!=null) {
			 x=x.getLeft();
			 }
			 return x;
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
    //TreeNode i = x;
	  while (x.getParent() != null  && x.getParent().getValue().compareTo(x.getValue()) > 0) {
      TreeNode temp = x;
      x.setValue(x.getParent().getValue());
      x.getParent().setValue(temp.getValue());
      x = x.getParent();
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
	    while(x.getLeft() != null) {
	        TreeNode small = x.getLeft();

	        if (x.getRight() != null && x.getLeft().getValue().compareTo(x.getRight().getValue()) > 0) {
	          small = x.getRight();
	        }
	        if (x.getValue().compareTo(small.getValue()) > 0) {
	          TreeNode temp = x;
	          x.setValue(small.getValue());
	          x.setParent(temp);
	        } else {
	            break;
	        }
	      }
	      // To be supplied by students

	    }

  // Implementation of the insert method provided by a MinHeap. The preconditions
  // and postcondition for this problem are the same, except that they now also
  // include the fact that the "TreeMinHeap Invariant" is satisfied when execution
  // of the algorithm begins and, again, when it ends.

  public void insert (T v) {
	  if(heapSize==0) {
		  TreeNode x = new TreeNode(v,heapSize);
		  root= x;
		  //root.setParent(null);
		  latest=root;
		  heapSize++;
	  }else if(heapSize==1){
		  TreeNode x = new TreeNode(v,heapSize);
		  heapSize++;
		  bubbleUp(x);
		  latest=x;
		  latest.setParent(root);
	  }
	  else {
	  heapSize++;
	  if(latest!=null) {
	  TreeNode x = new TreeNode(v,heapSize);
	  x.setParent(successorParent());
	  if(latest==latest.getParent().getLeft()) {
		  x.getParent().setRight(x);
	  }else {
		  x.getParent().setLeft(x);
	  }

	  latest= x;
	  //System.out.println("fuck you");
	  bubbleUp(x);
	  }
	  }
  }

  // Implementation of the deleteMin method provided by a MinHeap. The precondition
  // and postcondition for this problem are the same, except that they now also
  // include the fact that the "TreeMinHeap Invariant" is satisfied when execution
  // of the algorithm begins, and when it ends.

  public T deleteMin () throws NoSuchElementException {
	    if (heapSize == 0) {
	        throw new NoSuchElementException("Heap is empty. ");
	      }
	      T result = getRoot().getValue();
	      if(latest==null) {
	    	  System.out.println("fuck you");
	      }
	      root.setValue(latest.getValue());

	      heapSize--;
	      if(predecessor()==null) {
	    	  System.out.println("NANI");
	      }
	       latest = predecessor();
	       System.out.println(root.getValue());
	       bubbleDown(root);
	       System.out.println(root.getValue());
//	       heapSize--;
//	       latest = predecessor();
	      return result;   // To be supplied by students

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
    //0. latest is root: return null
	  //0.5. latest.parent is root: return null
	  //1. latest is right: pred=latest.parent.getLeft()
	  //2. its a left child
	  //a. its not edge: latest.parent.parent.left.right
	  //b. its edge	:while loop
	  if(latest==root||latest==null) {
		  return null;
	  }
	  if(latest.getParent()==root) {
		  return null;
	  }
	  if(latest==latest.getParent().getRight()) {
		  return latest.getParent().getLeft();
	  }
	  TreeNode x = latest.getParent();
	  boolean right=false;
	  int howmuch=1;
		 while(x!=root) {
			 if(x==x.getParent().getRight()) {
				 right=true;
				 break;
			 }
			 howmuch++;
			 x=x.getParent();
		  }
		 if(right) {
			// int j=0;
			// x=latest;
			//while(j<=howmuch) {
			//	x=x.getParent();
			//}
			x=x.getParent();
			x=x.getLeft();
			int j=0;
			while(j<=howmuch) {
				x=x.getRight();
				j++;
			}
			return x;
		 }else {
			 while(x.getRight()!=null) {
			 x=x.getRight();
			 }
			 return x;
  }
  }

  // Returns the node that should become the parent of the next node to be added.
  //
  // Precondition: The TreeMinHeap Invariant is satisfied, and the MinHeap represented
  //   is not empty.
  // Postcondition: The TreeNode that should be the parent of the next node to be
  //   added is returned.

  private TreeNode successorParent ()  {
	  if(latest==null) {
		  return null;
	  }
	  if(latest==root) {
		  return root;
	  }
	  if(latest==latest.getParent().getLeft()) {
		  return latest.getParent();
	  }else {
		 TreeNode x = latest.getParent();
		 boolean left=false;
		 while(x!=root) {
			 if(x==x.getParent().getLeft()) {
				 left=true;
				 break;
			 }
			 x=x.getParent();
		  }
		 if(left) {
			 return latest.getParent().getParent().getRight();
		 }else {
			 while(x.getLeft()!=null) {
			 x=x.getLeft();
			 }
			 return x;
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
    //TreeNode i = x;
	  while (x.getParent() != null  && x.getParent().getValue().compareTo(x.getValue()) > 0) {
      TreeNode temp = x;
      x.setValue(x.getParent().getValue());
      x.getParent().setValue(temp.getValue());
      x = x.getParent();
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
	    while(x.getLeft() != null) {
	        TreeNode small = x.getLeft();

	        if (x.getRight() != null && x.getLeft().getValue().compareTo(x.getRight().getValue()) > 0) {
	          small = x.getRight();
	        }
	        if (x.getValue().compareTo(small.getValue()) > 0) {
	          TreeNode temp = x;
	          x.setValue(small.getValue());
	          x.setParent(temp);
	        } else {
	            break;
	        }
	      }
	      // To be supplied by students

	    }

  // Implementation of the insert method provided by a MinHeap. The preconditions
  // and postcondition for this problem are the same, except that they now also
  // include the fact that the "TreeMinHeap Invariant" is satisfied when execution
  // of the algorithm begins and, again, when it ends.

  public void insert (T v) {
	  if(heapSize==0) {
		  TreeNode x = new TreeNode(v,heapSize);
		  root= x;
		  //root.setParent(null);
		  latest=root;
		  heapSize++;
	  }else if(heapSize==1){
		  TreeNode x = new TreeNode(v,heapSize);
		  heapSize++;
		  bubbleUp(x);
		  latest=x;
		  latest.setParent(root);
	  }
	  else {
	  heapSize++;
	  if(latest!=null) {
	  TreeNode x = new TreeNode(v,heapSize);
	  x.setParent(successorParent());
	  if(latest==latest.getParent().getLeft()) {
		  x.getParent().setRight(x);
	  }else {
		  x.getParent().setLeft(x);
	  }

	  latest= x;
	  //System.out.println("fuck you");
	  bubbleUp(x);
	  }
	  }
  }

  // Implementation of the deleteMin method provided by a MinHeap. The precondition
  // and postcondition for this problem are the same, except that they now also
  // include the fact that the "TreeMinHeap Invariant" is satisfied when execution
  // of the algorithm begins, and when it ends.

  public T deleteMin () throws NoSuchElementException {
	    if (heapSize == 0) {
	        throw new NoSuchElementException("Heap is empty. ");
	      }
	      T result = getRoot().getValue();
	      root.setValue(latest.getValue());

	      heapSize--;
	       latest = predecessor();
	       bubbleDown(root);

	      return result;   // To be supplied by students

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
