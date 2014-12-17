

/*
Brandon Pauly
CSC 300 - 402
Dr. Tomuro
14 October 2013
 */
/**=========================================================
 ** Class SLList
 ** ------------------------------
 ** Implementation of a singly-linked list.
 **  - Data type of the node data is Object (i.e., not generic)
 **  - No dummy first node.
 **
 **========================================================*/
public class SLList
{
  /*-------------------------------------------------------
   * Class Node (as an inner class)
   * ------------------------------
   * It is a private member (but definition) in SLList, so only the members
   * in SLList can access this class (as 'Node').  But since this is an
   * inner class, members of both classes (SLList and Node) can access
   * all members (private as well as public) freely.
   *------------------------------------------------------*/
  private class Node
  {
    private Object data;
    private Node next;

    public Node() { data = null; next = null; }
    public Node(Object x) { data = x; next = null; }
    public Node(Object x, Node ptr) { data = x; next = ptr; }

  } //-- end class Node ---------------------

  /**
   * Constructs an empty list
   */
  public SLList( )
  {
    first = null;
    elementCount = 0;
  }

  /**
   * Tests if the list is empty.
   * @return true if empty, false otherwise.
   */
  public boolean isEmpty()
  {
    return (first == null); // or (elementCount == 0);
  }

  /**
   * Returns the size of the list -- number of nodes
   */
  public int size() { return elementCount; }

  /**
   * inserts element x at the end of this list.
   * @param x the element to insert;
   *   Be careful when 'first' is null (i.e., list is empty)
   *   Be sure to increment 'elementCount'.
   */
  public void add(Object x)
  {
	  if (isEmpty())
		  first = new Node(x);
	  else
	  {
		  Node temp;
		  for (temp = first; temp != null && temp.next != null; temp = temp.next){}
		  
		  temp.next = new Node(x);
	  }
	  
	  elementCount++;
		  


  }

  /**
   * checks if element x is in this SLList
   * @param x the element to check;
   *   Use equals() to compare data.
   * @return true if x is in this list; else false.
   */
  public boolean contains(Object x)
  {
    Node temp;
    
    for (temp = first; temp != null; temp = temp.next)
    {
    	if (temp.data.equals(x))
    		return true;
    }
	return false;


  }

  /**
   * returns the element at the parameter kth position
   * @param k 0-based index into the list
   * The function return null if k < 0 or k >= size()
   * @return the data of the node at the parameter k
   */
  public Object get(int k)
  {
    Node temp = first;
    
    if (k < 0 || k >= size())
    	return null;
    else
    {
    	for (int n = 0; n < k; n++)
    	{
    		temp = temp.next;
    	}
    	
    
        return temp.data;
    }
    
    

  }

  /**
   * Removes the left-most occurrence (only) of the item x
   *   if it exists in the list; otherwise no change to the list.
   *   Use equals() to compare data.
   *   Be careful when 'first' is null (i.e., list is empty) or
   *   the list has just one node.
   * @param x the element to be removed
   */
  public void remove(Object x)
  {
	  if (first != null)
	  {
	  
	    if (first.next == null)
	    {
		    if (first.data.equals(x))
		    {
			    first = null;
			    elementCount--;
			    return;
		    }
		  
		    else
			    return;
	    }
	  
	    Node ptr;
	  
	    for (ptr = first; ptr != null && !ptr.data.equals(x); ptr = ptr.next);
	  
	    if (ptr != null)
	    {
		    if (ptr == first)
		    {
			    first = first.next;
			    elementCount--;
			    return;
		    }
		  
		    else
		    {
			    Node temp;
			  
			    for (temp = first; temp.next != ptr; temp = temp.next);
			  
			    temp.next = temp.next.next;
			    elementCount--;
			    return;
		    }
	    }
	  }
	  
	  return;
  }
  

  /**
   * Removes the item at position k.
   * The position of the first item is 0, the position of the second
   * item is 1 and so on. So removeAt(0) should remove the first item.
   * The function does nothing if k < 0 or k >= size().
   *   Be careful when 'first' is null (i.e., list is empty) or
   *   the list has just one node.
   * @param k position where item is to be removed
   */
  public void removeAt(int k)
  {
	Node temp = first;
	
	if (first != null && (k >= 0 && k < size()))
	{
		if (k == 0)
		{
			if (first.next == null)
			{
				first = null;
				elementCount--;
				return;
			}
			
			first = first.next;
			elementCount--;
			return;
		}
		
		for (int i = 0; i < (k-1); i++)
			temp = temp.next;
		
		temp.next = temp.next.next;
		elementCount--;
		return;
	}
	
	return;
  }

  /**
   * Prints the items in this list PRECISELY in the form [xx, yyy, zz,..] to System.out.
   *   The list MUST be enclosed by "[ ]" and there must be a delimiter "," between elements,
   *   as shown in the assignment sheet.
   */
  public void print()
  {
	  Node temp;
	  
	  System.out.print("[");
	  
	  for (temp = first; temp != null; temp = temp.next)
	  {
		  System.out.print(temp.data);
		  if (temp.next != null)
			  System.out.print(", ");
	  }
	  
	  System.out.print("] with size() = " + size());
	  
  }
  
  public void removeAll(Object x)
  {
	  if (first != null)
	  {
	  
	    if (first.next == null)
	    {
		    if (first.data.equals(x))
		    {
			    first = null;
			    elementCount--;
			    return;
		    }
		  
		    else
			    return;
	    }
	    
	    Node ptr;
	    
	    for (ptr = first; ptr != null; ptr = ptr.next)
	    {
	    	
	    	if (ptr == first && ptr.data.equals(x))
	    	{
	    		first = first.next;
	    		elementCount--;
	    	}
	    	
	    	else if (ptr.data.equals(x))
	    	{
	    		Node temp;
	    		for (temp = first; temp.next != ptr; temp = temp.next);
				temp.next = temp.next.next;
	    		elementCount--;
	    	}
	    }
	    
	    return;
	  }
	  
	  return;
  }
  
  public boolean find(Object x)
  {
	    Node temp;
	    
	    for (temp = first; temp != null; temp = temp.next)
	    {
	    	if (temp.data.equals(x))
	    	{
	    		if (temp == first)
	    			return true;
	    		
	    		Node ptr;
	    		for (ptr = first; ptr.next != temp; ptr = ptr.next);
				ptr.next = ptr.next.next;
				temp.next = first;
				first = temp;
				return true;
	    	}
	    }
		return false;

  }
  
  public void reverse()
  {
	  Node ptrA = first;
	  Node ptrB = first.next;
	  Node ptrC;
	  first.next = null;
	  
	  if (first != null)
	  {
	      while (ptrB != null)
	      {
		      ptrC = ptrB.next;
		      ptrB.next = ptrA;
		      ptrA = ptrB;
		      ptrB = ptrC;
	      }
	  
	     first = ptrA; 
	  }
	  
	  return;
  }
  
  public boolean equals(Object obj)
  {
	  if(this == obj)
		  return true;
	  else if(obj == null || getClass() != obj.getClass())
		  return false;
	  else
	  {
		  SLList s2 = (SLList) obj;
		  if(this.elementCount != s2.elementCount)
			  return false;
		  else
		  {
			  for(Node temp = first; temp != null; temp = temp.next)
			  {
				if(!this.contains(temp))
					return false;
			  }
		      return true;
		  }
	  }
  }

  //** data members **/
  private Node first;
  private int elementCount;

  //-------------------------------------------------------
  /**
   * Simple test application. See JUnit test class MySLListTest.java
   * for a bit more extensive unit test.
 * @return 
   */
  public static void main(String[] args)
  {
  }
}
