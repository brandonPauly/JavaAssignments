/*
 Brandon Pauly
 CSC 300-402
 Dr. Tomuro
 */

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DLList<E> implements Iterable<E>
{
  //** instance variables of DLList
  private Node head, tail;
  private int elementCount;

  //************************************
  //  inner class Node
  //************************************
  private class Node
  {
    private E data;
    private Node prev;
    private Node next;

    private Node() { data = null; prev = next = null; }
    private Node(E x) { data = x; prev = next = null; }
    private Node(E x, Node before, Node after)
    { data = x; prev = before; next = after; }
  }
  //*** end class Node ****************

  //************************************
  //  inner class DLIterator
  //************************************
  private class DLIterator implements ListIterator<E>
  {
    Node current; // points to a node in the DLList

    public DLIterator()
    {
      current = head.next; // node after the dummy header node
    }

    public boolean hasNext()
    {
      return current != tail;
    }

    public boolean hasPrevious()
    {
      return current != head;
    }

    public E next()
    {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      E x = current.data;
      current = current.next;
      return x;
    }

    /**
     * Inserts element e immediately before the node/element
     * currently pointed by 'current'.
     * @param e the element to insert;
     * Be sure to increment 'elementCount as well'.
     */
    public void add(E e)
    {
      Node temp = new Node(e);
      temp.prev = current.prev;
      current.prev.next = temp;
      temp.next = current;
      current.prev = temp;
      elementCount++;
    }

    /**
     * Returns the element in the node currently pointed by 'current',
     * and moves 'current' one node/position backwards.
     * If 'current' is pointing at the first (real data) node in the list,
     * the method throws a NoSuchElementException (see next() above).
     */
    public E previous()
    {
      if (!hasPrevious())
    	  throw new NoSuchElementException();
      
      E x = current.data;
      current = current.prev;
      return x;
    }

    /**
     * Removes the node currently pointed by 'current'.
     * Be sure to decrement 'elementCount' as well.
     * If 'current' is pointing at either head or tail dummy nodes,
     * the method does nothing.
     */
    public void remove()
    {
    	if (current != head && current != tail)
    	{
    		Node temp = current;
    		current = current.next;
    		temp.prev.next = current;
    		current.prev = temp.prev;
    		elementCount--;
    	}
    }

    /**
     * Replaces the element in the node currently pointed by 'current'
     * by the parameter e.
     * If 'current' is pointing at either head or tail dummy nodes,
     * the method does nothing.
     */
    public void set(E e)
    {
      if (current != head && current != tail)
      {
    	  current.data = e;
      }
    }

    public int nextIndex() { return size(); }
    public int previousIndex() { return -1; }

  }
  //*** end class DLIterator *****

  //=========================================
  //  back to outer class DLList
  //=========================================

  public DLList()
  {
    head = new Node(); // dummy head node
    tail = new Node(); // dummy tail node
    head.next = tail;
    tail.prev = head;
    elementCount = 0;
  }

  public int size() { return elementCount; }

  public boolean add(E x)
  {
    Node p = new Node(x, tail.prev, tail);
    tail.prev.next = p;
    tail.prev = p;
    ++elementCount;
    return true;
  }

  public String toString()
  {
    StringBuffer buff = new StringBuffer();
    buff.append("[");
    if (elementCount > 0) {
      buff.append(head.next.data);
      Node ptr = head.next.next;
      while (ptr != tail) {
        buff.append(", ");
        buff.append(ptr.data);
        ptr = ptr.next;
      }
    }
    buff.append("]");
    return buff.toString();
  }

  public ListIterator<E> iterator()
  {
    return new DLIterator();
  }

  public ListIterator<E> listIterator(int index) throws IndexOutOfBoundsException
  {
    if (index < 0 || index > elementCount)
      throw new IndexOutOfBoundsException();

    ListIterator<E> it = new DLIterator();
    while (index-- > 0)
      it.next();

    return it;
  }

  /**
   * Inserts the specified element at the beginning of this list.
   * @param x the element to insert;
   *   Be sure to increment 'elementCount'.
   */
  public void addFirst(E x)
  {
	  Node temp = new Node(x);
	  temp.next = head.next;
	  head.next.prev = temp;
	  temp.prev = head;
	  head.next = temp;
	  elementCount++;
  }

  /**
   * Returns the index of the first occurrence of the specified element in this list,
   * or -1 if this list does not contain the element;
   * index is 0-based (i.e., the first 'real' node (after dummy header) is index 0).
   * @param x the element to search for;
   */

  public int indexOf(Object x)
  {
	int count = 0;
	Node current = head.next;
	
    while (current != tail)
    {
    	if (current.data.equals(x))
    		return count;
    	current = current.next;
    	count++;
    }
    return -1;
  }


  //************************************
  //  main() and other static methods
  //************************************
  public static void main(String[] args)
  {
	  /*DLList<Integer> lst = new DLList<Integer>();
	  for (int i = 0; i < 5; ++i)
	    lst.add(new Integer(i));
	  
	  System.out.println(lst.indexOf(2));
	  
	  lst.addFirst(7);
	  
	  System.out.println(lst.indexOf(7));*/
  }

}
