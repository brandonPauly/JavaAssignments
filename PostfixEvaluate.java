/*
 Brandon Pauly
 CSC 300 - 402
 Dr. Tomuro
 30 October 2013
*/

import java.util.Arrays;
import java.util.Stack;

import stdlib.StdOut;

public class PostfixEvaluate
{
  final static String[] expAr =
    {"4 5 7 2 + - * == -16",
     "3 4 + 2 * 7 / == 2",
     "5 7 + 6 2 - * == 48",
     "4 2 3 5 1 - + * + == 18",
     "2 3 * 10 4 / - == 4",
     "4 23 12 - 2 * + == 26" };

  public static void main(String[] args)
  {
    int index;
    String expression;
    String[] postfix;
    int answer, result;

    for (int i = 0; i < expAr.length; ++i) {
      expression = expAr[i];
      index = expression.indexOf("==");
      postfix = expression.substring(0,index).trim().split("\\s+");
      answer = Integer.parseInt(expression.substring(index+2).trim()); // convert to an int

      // Call the RPN function, receive the returned result
      result = calcRPN(postfix);

      // compare result against answer
      printCalcResult(postfix, answer, result);
    }
  } // end main

  public static void printCalcResult(String[] postfix, int ans, int res)
  {
    System.out.print("Postfix: " + Arrays.toString(postfix) + ", Answer: " + ans + ", Your answer: " + res);
    if (ans == res)
      System.out.println(" ==> MATCHED!");
    else
      System.out.println(" ==> NO match...");
  }

  public static int calcRPN(String[] postfix)
  {
    Stack<Integer> st = new Stack<Integer>();

    int i = 0;
    String operators = "+-*/";
    
    while (i < postfix.length)
    {
    	if (operators.contains(postfix[i]))
    	{
    		String operator = postfix[i];
    		int realOp2 = st.pop().intValue();
    		int realOp1 = st.pop().intValue();
    		int ans;
    		
			if (operator.equals("+"))
			{
    			ans = realOp1 + realOp2;
			}
			else if (operator.equals("-"))
			{
				ans = realOp1 - realOp2;
			}
			else if (operator.equals("*"))
			{
				ans = realOp1 * realOp2;
			}
			else
			{
				ans = realOp1 / realOp2;
			}
			
			st.push(ans);
    		
    	}
    	
    	else
    	{
    		Integer tInt = Integer.parseInt(postfix[i]);
    		st.push(tInt);
    	}
    	
    	i++;
    }
    
    return st.pop();
    
    
  }

}

/* Output of the program

Postfix: [4, 5, 7, 2, +, -, *], Answer: -16, Your answer: -16 ==> MATCHED!
Postfix: [3, 4, +, 2, *, 7, /], Answer: 2, Your answer: 2 ==> MATCHED!
Postfix: [5, 7, +, 6, 2, -, *], Answer: 48, Your answer: 48 ==> MATCHED!
Postfix: [4, 2, 3, 5, 1, -, +, *, +], Answer: 18, Your answer: 18 ==> MATCHED!
Postfix: [2, 3, *, 10, 4, /, -], Answer: 4, Your answer: 4 ==> MATCHED!
Postfix: [4, 23, 12, -, 2, *, +], Answer: 26, Your answer: 26 ==> MATCHED!

*/