/*
 Brandon Pauly
 CSC 300 - 402
 Dr. Tomuro
 Assignment 6
 */
public class Store
{
  // fields
  private int[] elementData;
  private int elementCount;
  private static int DEFAULT_CAPACITY = 1;

  // methods
  public Store()
  {
    elementData = new int[DEFAULT_CAPACITY];
    elementCount = 0;
  }

  public Store(int initCapacity)
  {
    elementData = new int[initCapacity];
    elementCount = 0;
  }

  public int capacity() { return elementData.length; }
  public int size() { return elementCount; }

  public void insertNumber(int elt)
  {
    if (elementCount == elementData.length)
    {
    	int[] temp = new int[2 * elementData.length];
    	for(int i = 0; i < elementCount; i++)
    		temp[i] = elementData[i];
    	temp[elementCount] = elt;
    	elementData = temp;
    	elementCount++;
    }
    else
    {
    	elementData[elementCount] = elt;
    	elementCount++;
    }
  }

  public double average()
  {
    double sum = 0.0;
	if (elementCount == 0)
    	return 0.0;
    else
    {
    	for (int i = 0; i < elementCount; i++)
    		sum += elementData[i];
    	return sum/elementCount;
    }
  }

  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    else if (obj == null || getClass() != obj.getClass())
      return false;
    else
    {
      Store s2 = (Store) obj;  // type-cast obj to Store; then we can call Store methods in s2
      if (s2.size() == this.size() && s2.average() == this.average())
      {
    	  return true;
      }
      else
    	  return false;
    }
  }

  public String toString()
  {
    String ret = "[";

    if (size() > 0) {
      ret += elementData[0]; // first element to kick off
      for (int i = 1; i < size(); i++)
        ret = ret + ", " + elementData[i];
    }

    return ret + "]";
  }
}