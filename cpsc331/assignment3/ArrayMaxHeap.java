/*CPSC assignment 3
Umair Hassan 30047693
Betty Zhang 30040611
William Chan 30041834
*/

package cpsc331.assignment3;

import cpsc331.collections.BoundedMaxHeap;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import cpsc331.collections.HeapFullException;

/**
*
* Provides an ArrayList-based implementation of a BoundedMaxHeap<br><br>
*
* ArrayMaxHeapInvariant: A finite multiset of non-null values of ordered type T is
* stored in a bounded binary MaxHeap, using an array-based representation
* <br><br>
*
*/

public class ArrayMaxHeap<T extends Comparable<T>> implements BoundedMaxHeap<T> {

  // Data fields

  private final int CAPACITY;
  private int size;
  private ArrayList<T> A;

  // Returns the index of a parent of a node that has one
  //
  // Precondition: A integer i such that 1 <= i <= this.size - 1 is given as input.
  // Postcondition: If A is viewed as a representation of a binary tree with
  //   size "this.size" then the index of the parent of the node with index i is
  //   returned as output.

  private int parent (int i) {
    if (i >= 1 && i <= this.size - 1) {
      return (i - 1)/2;
    }
    return 0;

  }

  // Reports whether a node with a given index has a left child in the binary
  // tree represented by A
  //
  // Precondition: An integer i such that 0 <= i <= size - 1 is given as input.
  // Postcondition: If A is viewed as a representation of a binary tree with size
  //   "this.size" then true is returned if the node with index i has a left
  //   child in this binary tree, and false is returned otherwise

  private boolean hasLeft (int i) {
    if (i >= 0 && 2 * i + 1 <= this.size - 1) {
        return true;
    }
    return false;

  }

  // Returns the index of a node that is the left child of a node with a given index
  //
  // Precondition:
  // a) An integer i such that 0 <= i <= this.size - 1 is given as input.
  // b) If A is viewed as a representation of a binary tree with size "this.size"
  //    then the node x with index i has a left child in this binary tree.
  // Postcondition: The index of the left child of x is returned as output.
  //

  private int leftChild (int i) {
    if (hasLeft(i)) {
      return 2*i + 1;
    }
    return 0;

  }

  // Reports whether a node with a given index has a right child in the binary
  // tree represented by A
  //
  // Precondition: An integer i such that 0 <= i <= this.size - 1 is given as input.
  // Postcondition: If A is viewed as a representation of a binary tree with size
  //   "this.size" then true is returned if the node with index i has a right
  //   child in this binary tree, and false is returned otherwise

  private boolean hasRight (int i) {
    if (i >= 0 && 2 * i + 2 <= this.size - 1) {
        return true;
    }
    return false;

  }

  // Returns the index of a node that is the right child of a node with a given index
  //
  // Precondition:
  // a) An integer i such that 0 <= i <= this.size - 1 is given as input.
  // b) If A is viewed as a representation of a binary tree with size "this.size"
  //    then the node x with index i has a right child in this binary tree.
  // Postcondition: The index of the right child of x is returned as output.
  //

  private int rightChild (int i) {
    if (hasRight(i)) {
      return 2*i + 2;
    }
    return 0;

  }


  //
  // Implements the "bubbleUp" method needed to complete an insertion
  //
  // Precondition:
  // a) If this.A is viewed as representing a binary tree with the shape of a binary
  //    heap then all entries are non-null, so that the size of this tree is the
  //    same as the value of the data field "this.size", and this tree has  non-null
  //    values at its nodes.
  // b) The value of this.CAPACITY is an integer that is greater than or equal to the
  //    value of the data field "this.size" - and equal to the capacity of the
  //    ArrayList this.A.
  // c) An integer i such that 0 <= i <= this.size - 1 has been given as input. If x
  //    is the node with index i in the above binary tree then, for every node y
  //    in this tree except for x, both the parent and grandparent of y store values
  //    that are greater than is stored at y - if these nodes exist.
  // Postcondition:
  // a) The values stored in the first "this.size" locations of A have been reordered
  //    but not otherwise changed.
  // b) this.A now represents a BoundedMaxHeap with size "this.size"
  //
  // Note: The value of i can change - by decreasing - as this problem is being solved.

  private void bubbleUp (int i) {
    int ind = i;          //Set ind to i
    //If the parent of index exists and the value at parent is less than the value at ind
    while (parent(ind) >= 0 && A.get(parent(ind)).compareTo(A.get(ind)) < 0) {
      T temp = A.get(ind);            //Set temp to value at ind
      A.set(ind, A.get(parent(ind))); //Set the value at parent to index ind
      A.set(parent(ind), temp);       //Set the value in temp to index parent
      ind = parent(ind);              //Update ind to the parent
    }

  }

  //
  // Implements the "bubbleDown" method needed to complete a deleteMax
  //
  // Precondition:
  // a) If A is viewed as representing a binary tree with the shape of a binary
  //    heap then all entries are non-null, so that the size of this tree is the
  //    same as the value of the data field "this.size", and this tree has non-null
  //    values at its nodes.
  // b) The value of this.CAPACITY is an integer that is greater than or equal to the
  //    value of the data field "this.size" - and equal to the capacity of the
  //    ArrayList A.
  // c) An integer i such that 0 <= i <= this.size - 1 has been given as input. If x
  //    is the node with index x in the above binary tree then, for every node y
  //    in this tree except for x, both the children and grandchildren of y store
  //    values that are less than the value stored at y - if these nodes exist.

  // Postcondition:
  // a) The values stored in the first "this.size" locations of A have been reordered
  //    but not changed.
  // b) A now represents a BoundedMaxHeap with size "this.size".
  //
  // Note: The value of i can change - by increasing - as this problem is being solved.

  private void bubbleDown (int i) {
    int ind = i;            //Set ind to i
     
	// Bound Function: depth of ind
    while (hasLeft(ind)) {        //While the value at ind has a left child
      int large = leftChild(ind); //Set large to the left child of ind
      //If ind also has a right child and the right child is larger than the left child
      if (hasRight(ind) && A.get(leftChild(ind)).compareTo(A.get(rightChild(ind))) < 0) {
        large = rightChild(ind);  //Set large to the right child of ind
      }
      //If the value at ind is less than the value at large
      if (A.get(ind).compareTo(A.get(large)) < 0) {
        T temp = A.get(ind);      //Set the value at ind to temp to swap
        A.set(ind, A.get(large)); //Set the value at large to index ind
        A.set(large, temp);       //Set the value in temp to index large
      } else {                    //Else break from while loop
          break;
      }
      ind = large;                //Update ind to large
    }

  }

  //
  // Converts an array into a representation of a BoundedMaxHeap
  //
  // Precondition:
  // a) A is an ArrayList with positive integer capacity this.CAPACITY, and whose
  //    size is this this.CAPACITY, storing non-null values from some ordered type T
  // Postcondition:
  // a) The entries of A have been ordered but otherwise not changed.
  // b) A is now a representation of a BoundedMaXHeap whose size is the size (and
  //    capacity) of this ArrayList.

  private void heapify(){
    int i = getSize()/2;      //Set i to size/2 and while i is greater than 0
	 // Loop Invariant:
      // i is an integer such that 0 <= i <= size/2
      // Bound Function: i
    while (i > 0) {
      bubbleDown(i-1);        //Bubble down the value at i-1 to correct position and decrement i
      i--;
    }
  }


  /**
  *
  * @param capacity the capacity of the BondedMaxHeap to be created
  * @throws IllegalArgumentException if the capacity is not positive
  * <br><br>
  *
  * Constructs an empty BoundedHeapHeap with a given positive integer capacity<br><br>
  *
  * Precondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> An integer capacity is given as input </li>
  * </ol>
  * Postcondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> If the input capacity is positive then an empty (array-based) BondedMaxHeap
  *      with this capacity has been created. An IllegalArgumentException has been
  *      thrown, and a BoundedMaxHeap has not been created, otherwise. </li>
  * </ol>
  *
  */

  public ArrayMaxHeap(int capacity) throws IllegalArgumentException {

    if (capacity > 0) {

      CAPACITY = capacity;
      size = 0;
      A = new ArrayList<T>(capacity);
      int i = 0;

      // Loop Invariant:
      // 1. A positive integer capacity is given as input.
      // 2. A is an ArrayList storing values with type T.
      // 3. i is an integer such that 0 <= i <= capacity
      // 4. The current size of A is i and, for every integer j such that
      //    0 <= j < i, A[j] = null
      // Bound Function: capacity - i

      while (i < capacity) {
        A.add(null);
        i = i+1;
      }

    } else {

      throw new IllegalArgumentException("The input capacity must be positive.");

    }

  }

  /**
  *
  * @param givenA the ArrayList to be used to create this.A
  * <br><br>
  *
  * Constructs a BoundedMaxHeap from a given ArrayList<br><br>
  *
  * Precondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> An ArrayList givenA, whose size is the same as its positive integer capacity,
  *      storing non-null values from the ordered type T, is given as input </li>
  * </ol>
  * Postcondition:<br>
  * <ol style="list-style-type: lower-alpha">
  * <li> Both this.CAPaCITY and this.size are equal to the capacity of A
  * <li> this.A is the array givenA - whose entries have been reordered, but
  *      not changed, so that this represents a BoundedMaxHeap </li>
  * </ol>
  *
  */

  public ArrayMaxHeap(ArrayList<T> givenA) {

    CAPACITY = givenA.size();
    size =  CAPACITY;
    A = givenA;
    heapify();

  }


  // Implementation of insert

  public void insert(T v) throws HeapFullException {
    if (this.size == CAPACITY) {  //If arraylist size is equal to capacity, throw HeapFullException
      throw new HeapFullException("Heap is full. ");
    }
    this.size++;                  //Increment size and add v to arraylist
    int ind = this.size - 1;
    A.set(ind, v);

    bubbleUp(ind);                //Bubble the added value up to correct position

  }

  // Implementation of deleteMax

  public T deleteMax() throws NoSuchElementException {
    if (this.size == 0) {         //If arraylist size is 0, throw NoSuchElementException
      throw new NoSuchElementException("Heap is empty. ");
    }
    T result = A.get(0);          //Get value at index 0 in arraylist
    A.set(0, A.get(this.size - 1));   //Set value at index 0 to last value in arraylist
    A.set(this.size - 1, null);   //Set the last value to null and decrement size
    this.size--;
    bubbleDown(0);                //Bubble the first value down to correct position
    return result;                //Return removed value

  }

  // Implementation of getSize; supplied by instructor

  public int getSize() {
    return size;
  }

  // Implementation of getCapacity; supplied by instructor

  public int getCapacity() {
    return CAPACITY;
  }

  // Used for testing; supplied by instructor

  T valueByIndex (int indx) throws NoSuchElementException {

    if ((indx >= 0) && (indx < size)) {

      return A.get(indx);

    } else {

      throw new NoSuchElementException("There is no node with this index.");

    }

  }

}
