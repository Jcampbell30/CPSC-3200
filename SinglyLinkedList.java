

//import SinglyLinkedList.Node;

/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * A basic singly linked list implementation.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class SinglyLinkedList<E> implements Cloneable, Iterable<E> {
  //---------------- nested Node class ----------------
  /**
   * Node of a singly linked list, which stores a reference to its
   * element and to the subsequent node in the list (or null if this
   * is the last node).
   */
  private static class Node<E> {

    /** The element stored at this node */
    private E element;            // reference to the element stored at this node

    /** A reference to the subsequent node in the list */
    private Node<E> next;         // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }

    // Accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() { return element; }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Modifier methods
    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
  } //----------- end of nested Node class -----------

  // instance variables of the SinglyLinkedList
  /** The head node of the list */
  private Node<E> head = null;               // head node of the list (or null if empty)

  /** The last node of the list */
  private Node<E> tail = null;               // last node of the list (or null if empty)

  /** Number of nodes in the list */
  private int size = 0;                      // number of nodes in the list

  /** Constructs an initially empty list. */
  public SinglyLinkedList() { }              // constructs an initially empty list

  // access methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }

  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns (but does not remove) the first element of the list
   * @return element at the front of the list (or null if empty)
   */
  public E first() {             // returns (but does not remove) the first element
    if (isEmpty()) return null;
    return head.getElement();
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last() {              // returns (but does not remove) the last element
    if (isEmpty()) return null;
    return tail.getElement();
  }
  /**
   * Returns (but does not remove) the penultimate node of the list.
   * @return node before the end of the list - exception if size < 2 (IllegalStateException)
   */
  
  private Node<E> penultimateNode() {
	  //***** Complete this method ***** //
	  if( this.size < 2)
	  {
		  throw new IllegalStateException("list must have 2 or more entries"); 
		  
	  }
	
	  	Node<E> temp = this.head;
		while(temp.next.next != null)
		{
			temp = temp.next;
		}
		return temp;
  }
  /**
   * Returns (but does not remove) the penultimate element of the list.
   * @return element before the end of the list - exception if size < 2 (IllegalStateException)
   */
  public E penultimate() { //do not change this method - complete the penultimateNode() method
	  return penultimateNode().getElement();
  }
  /**
   * Returns (but does not remove) the middle element of the list.
   * @return element before the end of the list - exception if size = 0 (IllegalStateException)
   */
  public E getMiddle() {
	  //***** Complete this method*****//
	  if(isEmpty()) {
		  throw new IllegalStateException();
	  }
	  Node<E> fast = this.head;
	  Node<E> slow = this.head;
	  while(fast!= null && fast.next !=null)
	  {
		  fast = fast.next.next;
		  slow = slow.next;
		 
	  }
	  if(this.size%2==0)
	  {
		  Node<E> curr = this.head;
		  while(curr.next!=slow)
		  {
			  curr = curr.next;
		  }
		  return curr.getElement();
	  }
	  return slow.getElement();
	  
	  
  }
  // update methods
  /**
   * Adds an element to the front of the list.
   * @param e  the new element to add
   */
  public void addFirst(E e) {                // adds element e to the front of the list
    head = new Node<>(e, head);              // create and link a new node
    if (size == 0)
      tail = head;                           // special case: new node becomes tail also
    size++;
  }

  /**
   * Adds an element to the end of the list.
   * @param e  the new element to add
   */
  public void addLast(E e) {                 // adds element e to the end of the list
    Node<E> newest = new Node<>(e, null);    // node will eventually be the tail
    if (isEmpty())
      head = newest;                         // special case: previously empty list
    else
      tail.setNext(newest);                  // new node after existing tail
    tail = newest;                           // new node becomes the tail
    size++;
  }
  /**
   * Adds an element to be the second of the list.
   * @param e  the new element to add
   */
  public void addSecond(E e) { 
	  //***** Complete this method *****//
	  Node<E> second = new Node<>(e,null);
	  Node<E> curr;
	  if(isEmpty())
	  {
		  throw new IllegalStateException("Can't addSecond to an empty list");
	  }
	  else
	  {
		  curr = this.head.next;//set to head's next if size is >= 2
		  this.head.next = second;//makes the head of the linked list point to second
		  second.next = curr; //second's next points to curr
		  if(curr==null)
		  {
			  tail = second;
		  }
		  size++;
	  }
	  
  }
  

  public Node<E> getNodeAt(int i)
  {
	  if(i < 0 || i >= size) {
		  return null;
	  }
	  else
	  {
		  Node<E> pointer = head;
		  for(int j = 0; j < i; j++)
		  {
			  pointer = pointer.next;
		  }
		  return pointer;
	  }
	  
  }
  /**
   * Adds an element at the ith position in the list.
   * @param e  the new element to add.  
   */ 
  public void add(E e, int i) {
	  //***** Complete this method *****//
	  //The run time of this method depends on the size of the input
	  //add runs at O(n)
	  
	
	  if(i >= 0 && i <= this.size)
	  {
		  if(i == 0)
		  {
			  this.addFirst(e);
		  }
		  else
		  {
			  Node<E> prev = head;
			  int count = 1;
			  while(count < i)
			  {
				  prev = prev.next;
				  count++;
			  }
			  Node<E> curr = prev.next;
			  Node<E> newest = new Node<>(e,curr);
			  prev.next = newest;
			  if(newest.next == null)
			  {
				  tail = newest;
			  }
			  size++;
		  }
	  }
	  else 
	  {
		  throw new IndexOutOfBoundsException("Invalid index. Index must be >=0 and <=size.");
	  }
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst() {                   // removes and returns the first element
    if (isEmpty()) return null;              // nothing to remove
    E answer = head.getElement();
    head = head.getNext();                   // will become null if list had only one node
    size--;
    if (size == 0)
      tail = null;                           // special case as list is now empty
    return answer;
  }
  /**
   * Removes and returns the ith element of the list.
   * @return the removed element exception i not in proper range
   */
  public E remove(int i) {
	//***** Complete this method *****//
	  //The run time of this method depends on the size of the input
	  //add runs at O(n)
	  
	  if(i >= 0 && i <= this.size)
	  {
		  if(i == 0)
		  {
			   E answer = this.removeFirst();
			   return answer;
		  }
		  else
		  {
			 Node<E> prev = this.getNodeAt(i);
			 Node<E> back = head;
			 while(back.next!=prev)
			 {
				 back = back.next;
			 }
			 back.next = this.getNodeAt(i + 1);
			 if(back.next == null)
			 {
				 tail = back;
			 }
			 size--;
			 return prev.element;
			
		  }
	  }
	  else
	  {
		  throw new IndexOutOfBoundsException();
	  }
	 
  }
  
  /**
   * Reverses the order of the nodes in the list.
   * 
   */
  public void reverse() 
  {
	  //***** Complete this method *****//
	  	 Node<E> back = this.head;
	  	 Node<E> curr = null;
	  	 Node<E> prev = null;
		 while(back!=null)
		 {
			 curr = back;
			 back = back.next;
			 
			 curr.next = prev;
			 prev = curr;
			 head = curr;
			 if(curr.next == null)
			 {
				 tail = curr;
			 }
		 } 
  }

  @SuppressWarnings({"unchecked"})
  public boolean equals(Object o) {
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    SinglyLinkedList other = (SinglyLinkedList) o;   // use nonparameterized type
    if (size != other.size) return false;
    Node walkA = head;                               // traverse the primary list
    Node walkB = other.head;                         // traverse the secondary list
    while (walkA != null) {
      if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
      walkA = walkA.getNext();
      walkB = walkB.getNext();
    }
    return true;   // if we reach this, everything matched successfully
  }

  @SuppressWarnings({"unchecked"})
  public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
    // always use inherited Object.clone() to create the initial copy
    SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
    if (size > 0) {                    // we need independent chain of nodes
      other.head = new Node<>(head.getElement(), null);
      Node<E> walk = head.getNext();      // walk through remainder of original list
      Node<E> otherTail = other.head;     // remember most recently created node
      while (walk != null) {              // make a new node storing same element
        Node<E> newest = new Node<>(walk.getElement(), null);
        otherTail.setNext(newest);     // link previous node to this one
        otherTail = newest;
        walk = walk.getNext();
      }
    }
    return other;
  }

  public int hashCode() {
    int h = 0;
    for (Node walk=head; walk != null; walk = walk.getNext()) {
      h ^= walk.getElement().hashCode();      // bitwise exclusive-or with element's code
      h = (h << 5) | (h >>> 27);              // 5-bit cyclic shift of composite code
    }
    return h;
  }

  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = head;
    while (walk != null) {
      sb.append(walk.getElement());
      if (walk != tail)
        sb.append(", ");
      walk = walk.getNext();
    }
    sb.append(")");
    return sb.toString();
  }
	 /**
	 * Implements java.lang.Iterable<T>
	 */
	public java.util.Iterator<E> iterator() {
		return new ForwardIterator();
	}
	private class ForwardIterator implements java.util.Iterator<E> {
		//***** Define appropriate instance variables here *****//
		Node<E> curr = head;
		
		
		public boolean hasNext() {
			//***** Complete this method *****//
			if(curr!=null)
			{
				return true;
			}

			return false;
		}

		// Note: this method has undefined behavior if hasNext() return false
		public E next() {
			//***** Complete this method *****//
			E value = curr.getElement();
			curr = curr.next;
			return value;
			
		}
		
	}
}
